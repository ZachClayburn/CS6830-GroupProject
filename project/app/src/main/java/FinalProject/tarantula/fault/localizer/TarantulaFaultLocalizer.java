package FinalProject.tarantula.fault.localizer;

import FinalProject.CommandRunner;
import FinalProject.exceptions.FaultLocalizationException;
import FinalProject.tarantula.fault.localizer.coverage.CoverageLine;
import FinalProject.tarantula.fault.localizer.jacoco.dto.JacocoLine;
import FinalProject.tarantula.fault.localizer.jacoco.dto.JacocoPackage;
import FinalProject.tarantula.fault.localizer.jacoco.dto.JacocoReport;
import FinalProject.tarantula.fault.localizer.jacoco.dto.JacocoSourceFile;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TarantulaFaultLocalizer {
    private List<Path> testPaths;
    private final File projectDirectory;
    private final CommandRunner commandRunner;
    private final List<CoverageLine> coverageLines;
    private long totalPassedTests;
    private long totalFailedTests;

    public TarantulaFaultLocalizer(File projectDirectory, CommandRunner commandRunner) {
        this.projectDirectory = projectDirectory;
        this.commandRunner = commandRunner;
        this.coverageLines = new ArrayList<>();
        this.totalPassedTests = 0;
        this.totalFailedTests = 0;
        try {
            testPaths = new ArrayList<>();
            try (Stream<Path> stream = Files.walk(projectDirectory.toPath())) {
                List<Path> filePaths = stream.collect(Collectors.toList());
                for (Path filePath : filePaths) {
                    if (filePath.toString().contains(".java") && filePath.toString().contains("Test")) {
                        testPaths.add(filePath);
                    }
                }
            }
        } catch (IOException ioException) {
            System.err.println("Fatal error!");
            ioException.printStackTrace();
            System.exit(1);
        }
    }

    private CompilationUnit createCompilationUnit(String fullPath) {
        FileInputStream in;
        CompilationUnit cu = null;

        try {
            in = new FileInputStream(fullPath);
        } catch (FileNotFoundException fex) {
            fex.printStackTrace();
            return null;
        }

        try {
            // parse the file
            cu = StaticJavaParser.parse(in);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return cu;
    }

    private static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    private JacocoReport getCoverageReport() throws FaultLocalizationException {
        try {
            List<Path> paths = Files.find(Paths.get(projectDirectory.getAbsolutePath()), 16,
                    (path, attr) -> path.endsWith("jacocoTestReport.xml")).collect(Collectors.toList());

            if (paths.size() != 1) {
                throw new FaultLocalizationException("Error: multiple jacoco test reports found or is missing");
            }

            XmlMapper xmlMapper = new XmlMapper();
            String xml = inputStreamToString(new FileInputStream(paths.get(0).toFile()));
            return xmlMapper.readValue(xml, JacocoReport.class);
        } catch (IOException ioException) {
            throw new FaultLocalizationException("Error: could not find jacoco test report");
        }
    }

    private void readCoverage(boolean testPassed, String javaPkg) throws FaultLocalizationException {
        JacocoReport jacocoReport = getCoverageReport();
        for (JacocoPackage pkg : jacocoReport.pkg) {
            if (pkg.name.equals(javaPkg)) {
                for (JacocoSourceFile sourceFile : pkg.sourceFiles) {
                    for (JacocoLine line : sourceFile.lines) {
                        if (Integer.parseInt(line.checkedInstructions) != 0) {
                            CoverageLine foundCoverageLine = null;
                            for (CoverageLine coverageLine : coverageLines) {
                                if(testPassed) {
                                    this.totalPassedTests++;
                                } else {
                                    this.totalFailedTests++;
                                }
                                if (coverageLine.lineNumber == Integer.parseInt(line.lineNumber)) {
                                    if (testPassed) {
                                        coverageLine.passedTests++;
                                    } else {
                                        coverageLine.failedTests++;
                                    }
                                    foundCoverageLine = coverageLine;
                                    break;
                                }
                            }

                            if (foundCoverageLine == null) {
                                if (testPassed) {
                                    foundCoverageLine = new CoverageLine(Double.parseDouble(line.lineNumber),
                                            1, 0);
                                } else {
                                    foundCoverageLine = new CoverageLine(Double.parseDouble(line.lineNumber),
                                            0, 1);
                                }

                                coverageLines.add(foundCoverageLine);
                            }
                        }
                    }
                }
            }
        }
    }

    private void doTarantula() {
        for (CoverageLine line : coverageLines) {
            try {
                line.suspiciousness = (line.failedTests / totalFailedTests)
                        / ((line.passedTests / totalPassedTests) + (line.failedTests / totalFailedTests));
            } catch (Exception e) {
                System.err.println("Invalid Coverage Line");
            }
        }
    }

    public List<CoverageLine> localizeFaults() throws FaultLocalizationException {
        for (Path testPath : testPaths) {
            CompilationUnit cu = createCompilationUnit(testPath.toString());

            if (cu == null) {
                throw new FaultLocalizationException("Error: could not create compilation unit for given test class file");
            }

            PackageDeclaration packageDeclaration = cu.getPackageDeclaration().orElse(null);

            String pkgName = "";

            if (packageDeclaration != null) {
                pkgName = packageDeclaration.getNameAsString();
            }

            ClassOrInterfaceDeclaration mainClass = cu.getClassByName(testPath.toFile().getName().replace(".java", "")).orElse(null);

            if (mainClass == null) {
                throw new FaultLocalizationException("Error: class name does not match file name for file" + testPath.toString());
            }

            String testClassName = mainClass.getNameAsString();
            List<MethodDeclaration> testMethods = mainClass.getMethods();

            for (MethodDeclaration testMethod : testMethods) {
                String testMethodName = testMethod.getNameAsString();
                boolean testPassed = commandRunner.runTestMethod(testClassName, testMethodName);
                readCoverage(testPassed, pkgName);
            }
        }
        doTarantula();

        coverageLines.sort((c1, c2) -> {
            if (c1.suspiciousness > c2.suspiciousness) {
                return -1;
            } else if (c2.suspiciousness > c1.suspiciousness) {
                return 1;
            } else {
                if (c1.lineNumber > c2.lineNumber) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        return coverageLines;
    }
}





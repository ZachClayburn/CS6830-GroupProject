package FinalProject.tarantula.fault.localizer;

import FinalProject.CommandRunner;
import FinalProject.exceptions.FaultLocalizationException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TarantulaFaultLocalizer {
    private List<Path> testPaths;
    private CommandRunner commandRunner;

    public TarantulaFaultLocalizer(File projectDirectory, CommandRunner commandRunner) {
        this.commandRunner = commandRunner;
        try {
            testPaths = new ArrayList<>();
            try (Stream<Path> stream = Files.walk(projectDirectory.toPath())) {
                List<Path> filePaths = stream.toList();
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

    public void localizeFaults() throws FaultLocalizationException {
        for (Path testPath : testPaths) {
            CompilationUnit cu = createCompilationUnit(testPath.toString());

            if (cu == null) {
                throw new FaultLocalizationException("Error: could not create compilation unit for given test class file");
            }

            ClassOrInterfaceDeclaration mainClass = cu.getClassByName(testPath.toFile().getName().replace(".java", "")).orElse(null);

            if (mainClass == null) {
                throw new FaultLocalizationException("Error: class name does not match file name for file" + testPath.toString());
            }

            String testClassName = mainClass.getNameAsString();
            List<MethodDeclaration> testMethods = mainClass.getMethods();

            for (MethodDeclaration testMethod : testMethods) {
                String testMethodName = testMethod.getNameAsString();
                var x = commandRunner.runTestMethod(testClassName, testMethodName);
                System.out.println();
            }

            System.out.println();
        }
    }
}

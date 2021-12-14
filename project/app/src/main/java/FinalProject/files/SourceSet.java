package FinalProject.files;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A collection of source files to mutate. This class manages all the source files in a project that the APR algorithm
 * can apply changes to, applying changes to the files, and automatically restoring all files to their unchanged state
 * on shutdown if used in a try-with block.
 */
public class SourceSet implements Closeable {

    private final List<UnalteredFile> sourceFiles = new ArrayList<>();

    public static SourceSet fromProjectDirectory(Path projectRoot) throws IOException {
        final var temp = "glob:" + projectRoot + "/**/src/main/**/*.java";
        final var matcher = FileSystems.getDefault().getPathMatcher(temp);
        final var files = Files
                .find(projectRoot, Integer.MAX_VALUE, (path, attr) -> matcher.matches(path))
                .map(Path::toFile)
                .toArray(File[]::new);
        return new SourceSet(files);
    }

    private SourceSet(File... sourceFiles) throws IOException {
        for (File sourceFile : sourceFiles) this.sourceFiles.add(new UnalteredFile(sourceFile));
    }

    public void restoreToDefault() throws IOException {
        for (UnalteredFile sourceFile : sourceFiles) sourceFile.restoreFile();
    }

    @Override
    public void close() throws IOException {
        restoreToDefault();
    }

    public Optional<SourceFile> get(File file) {
        return sourceFiles.stream().filter(uf -> uf.matches(file)).findFirst().map(UnalteredFile::getMutableCopy);
    }

    public static void setupSymbolSolver(Path projectRoot) throws IOException {
        final var typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        final var gradleDir = Paths.get(projectRoot.toString(), "gradle");
        System.out.println(gradleDir);
        final var matcher = FileSystems.getDefault().getPathMatcher("glob:*.jar");
        Files
                .find(projectRoot, Integer.MAX_VALUE, ((path, attr) -> matcher.matches(path.getFileName())))
                .filter(f -> !f.startsWith(gradleDir))
                .map(Path::toString)
                .map(SourceSet::safeNewJarTypeSolver)
                .forEach(typeSolver::add);
        final var symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);
    }

    private static JarTypeSolver safeNewJarTypeSolver(String path) {
        try {
            return new JarTypeSolver(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

package FinalProject.files;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.resolution.SymbolResolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SourceFiles {
    public static SourceFile fromFile(File sourceFile) throws FileNotFoundException {
        return new SourceFile(sourceFile);
    }

    private static JarTypeSolver safeNewJarTypeSolver(String path) {
        try {
            return new JarTypeSolver(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setupSymbolSolver(Path projectRoot) throws IOException {
        final var typeSolver = new CombinedTypeSolver(new ReflectionTypeSolver());
        final var gradleDir = Paths.get(projectRoot.toString(), "gradle");
        System.out.println(gradleDir);
        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.jar");
        Files
                .find(projectRoot, Integer.MAX_VALUE, ((path, attr) -> matcher.matches(path.getFileName())))
                .filter(f -> !f.startsWith(gradleDir))
                .map(Path::toString)
                .map(SourceFiles::safeNewJarTypeSolver)
                .forEach(typeSolver::add);
        final var symbolSolver = new JavaSymbolSolver(typeSolver);
        StaticJavaParser
                .getConfiguration()
                .setSymbolResolver(symbolSolver);
    }

    public static void main(String[] args) throws IOException {
        setupSymbolSolver(Paths.get("./testApplication"));
    }

}

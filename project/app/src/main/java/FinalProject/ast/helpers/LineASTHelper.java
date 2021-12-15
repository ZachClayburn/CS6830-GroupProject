package FinalProject.ast.helpers;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.Statement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class LineASTHelper {
    public static Statement getLineAST(File filePath, long lineNumber) throws IOException {
        String line = Files.readAllLines(filePath.toPath()).get((int)lineNumber - 1);
        return StaticJavaParser.parseStatement(line);
    }

    public static void writeLineASTToFile(File filePath, Statement statement, long lineNumber) throws  IOException {
        List<String> lines = Files.readAllLines(filePath.toPath());
        lines.add((int)lineNumber, statement.toString());
        lines.remove((int)lineNumber - 1);
        Files.write(filePath.toPath(), lines);
    }

    public static void writeASTLinesToFile(File filePath, List<Statement> statements, long lineNumber) throws IOException {
        List<String> lines = Files.readAllLines(filePath.toPath());
        List<String> newLines = new ArrayList<>();
        for (Statement statement : statements) {
            newLines.add(statement.toString());
        }
        lines.addAll((int)lineNumber, newLines);
        lines.remove((int)lineNumber - 1);
        Files.write(filePath.toPath(), lines);
    }
}

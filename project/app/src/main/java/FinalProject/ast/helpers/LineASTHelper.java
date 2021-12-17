package FinalProject.ast.helpers;

import com.github.javaparser.Range;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.Statement;

import java.io.File;
import java.io.FileWriter;
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

    public static void writeASTLinesToFile(File filePath, List<Node> statements, long lineNumber, int numberOfLinesToDelete) throws IOException {
        List<String> lines = Files.readAllLines(filePath.toPath());
        List<String> newLines = new ArrayList<>();
        for (Node statement : statements) {
            newLines.add(statement.toString());
        }

        for (int i = 0; i < numberOfLinesToDelete; i++) {
            lines.remove((int) lineNumber - 1);
        }

        lines.addAll((int)lineNumber - 1, newLines);

        Files.write(filePath.toPath(), lines);
    }

    public static void writeASTStatements(CompilationUnit cu, File filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(cu.toString());
        fileWriter.close();
    }

    public static Node getLineAST(Node node, Integer lineNumber) {
        Range range = node.getRange().orElse(null);

        if (range == null) {
            return null;
        }

        if (range.begin.line <= lineNumber && range.end.line >= lineNumber) {
            if (node.getChildNodes().size() == 0 && node instanceof Statement) {
                return node;
            }
            for (Node subNode : node.getChildNodes()) {
                Node n = getLineAST(subNode, lineNumber);
                if (n != null) {
                    if (n instanceof Statement) {
                        return n;
                    } else {
                        return node;
                    }
                }
            }
            return node;
        }

        return null;
    }
}

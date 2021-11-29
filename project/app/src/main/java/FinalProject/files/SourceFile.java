package FinalProject.files;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import java.io.*;


public class SourceFile {
    private final File filePath;
    private final CompilationUnit unalteredFileContents;
    private CompilationUnit fileContents;

    SourceFile(File filePath) throws FileNotFoundException {
        this.filePath = filePath;
        unalteredFileContents = StaticJavaParser.parse(filePath);
        fileContents = unalteredFileContents.clone();
    }

    public void writeBackFile() throws IOException {
        LexicalPreservingPrinter.setup(fileContents);
        try (var writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(fileContents);
        }
    }

    public CompilationUnit getFileContents() {
        return fileContents;
    }

    public void resetFile() {
        fileContents = unalteredFileContents.clone();
    }
}

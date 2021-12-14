package FinalProject.files;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import java.io.*;
import java.nio.file.Paths;


public class SourceFile implements Cloneable {
    private final File filePath;
    private  CompilationUnit fileContents;

    public SourceFile(File filePath, CompilationUnit fileContents) {
        this.filePath = filePath;
        this.fileContents = fileContents;
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

    @Override
    public SourceFile clone() {
        try {
            SourceFile clone = (SourceFile) super.clone();
            clone.fileContents = fileContents.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}

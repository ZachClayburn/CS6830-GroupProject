package FinalProject.files;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import java.io.*;

public class UnalteredFile {
    private final File filePath;
    private final CompilationUnit fileContents;

    UnalteredFile(File filePath) throws FileNotFoundException {
        this.filePath = filePath;
        fileContents = StaticJavaParser.parse(filePath);
    }

    public SourceFile getMutableCopy() {
        return new SourceFile(filePath, fileContents.clone());
    }

    public void restoreFile() throws IOException {
        LexicalPreservingPrinter.setup(fileContents);
        try (var writer = new PrintWriter(new FileWriter(filePath))) {
            writer.print(fileContents);
        }
    }

}

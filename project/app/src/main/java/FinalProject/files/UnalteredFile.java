package FinalProject.files;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.lexicalpreservation.LexicalPreservingPrinter;

import java.io.*;
import java.nio.file.Files;

public class UnalteredFile {
    private final File filePath;
    private final CompilationUnit fileContents;
    private final String fileContentsAsString;

    UnalteredFile(File filePath) throws IOException {
        this.filePath = filePath;
        fileContents = StaticJavaParser.parse(filePath);
        fileContentsAsString = Files.readString(filePath.toPath());
    }

    public SourceFile getMutableCopy() {
        return new SourceFile(filePath, fileContents.clone());
    }

    public void restoreFile() throws IOException {
//        LexicalPreservingPrinter.setup(fileContents);
//        try (var writer = new PrintWriter(new FileWriter(filePath))) {
//            writer.print(LexicalPreservingPrinter.print(fileContents));
//        }
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(fileContentsAsString);
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public boolean matches(File file) {
        return filePath.getAbsoluteFile().equals(file.getAbsoluteFile());
    }

}

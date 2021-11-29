/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package FinalProject;

import FinalProject.files.SourceFiles;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParserException;

import java.io.File;
import java.io.IOException;

public class App {
    final CommandRunner commandRunner;
    App(File projectRoot) throws IOException {
        commandRunner = new CommandRunner(projectRoot);
        commandRunner.runJar(); // Ensure that the jars exist for the symbol solver to use
        SourceFiles.setupSymbolSolver(projectRoot.toPath());
    }

    void run() {
        commandRunner.runBuild();

//        commandRunner.runTests();
    }

    public static void main(String[] args) {
        var parser = ArgumentParsers
                .newFor("Par")
                .build()
                .description("Automatic program repair");
        parser
                .addArgument("ProjectDir")
                .required(true)
                .type(Arguments
                              .fileType()
                              .verifyIsDirectory())
                .help("Root directory of the project to apply automatic an automatic repair")
                .metavar("<Project root>");
        try {
            var res = parser.parseArgs(args);
            var testProjectDir = (File) res.get("ProjectDir");
            new App(testProjectDir).run();
        } catch (ArgumentParserException e) {
            parser.handleError(e);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

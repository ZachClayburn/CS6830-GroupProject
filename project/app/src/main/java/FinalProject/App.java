/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package FinalProject;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.impl.Arguments;
import net.sourceforge.argparse4j.inf.ArgumentParserException;

import java.io.File;

public class App {
    final CommandRunner commandRunner;
    App(File projectRoot) {
        commandRunner = new CommandRunner(projectRoot);
    }

    void run() {
        commandRunner.runBuild();
        commandRunner.runTests();
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
        }
    }
}

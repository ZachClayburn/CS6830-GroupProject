package FinalProject;

import java.io.File;
import java.io.IOException;

public class CommandRunner {
    private final ProcessBuilder builder;

    public CommandRunner(File workingDirectory) {
        this.builder = new ProcessBuilder().directory(workingDirectory);
    }

    public boolean runBuild() {
        return runCommands("./gradlew", "build");
    }

    public boolean runTests() {
        return runCommands("./gradlew", "test");
    }

    private boolean runCommands(String... commands) {
        try {
            var process = builder.command(commands).start();
            process.waitFor();
            return process.exitValue() == 0;
        } catch (InterruptedException e) {
            System.err.println("Command interrupted!");
            return false;
        } catch (IOException e) {
            System.err.println("Fatal error!");
            e.printStackTrace();
            System.exit(1);
            return false;
        }
    }

}

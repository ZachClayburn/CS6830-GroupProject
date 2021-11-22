package FinalProject;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;

public class CommandRunner {
    private final ProcessBuilder builder;
    private final String gradleBinary;

    public CommandRunner(File workingDirectory) {
        this.builder = new ProcessBuilder().directory(workingDirectory);

        if (SystemUtils.IS_OS_WINDOWS) {
            gradleBinary = "./gradlew.bat";
        } else {
            gradleBinary = "./gradlew";
        }
    }

    public boolean runBuild() {
        return runCommands(gradleBinary, "build");
    }

    public boolean runTests() {
        return runCommands(gradleBinary, "test");
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

package FinalProject;

import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        return runCommands("build");
    }

    public boolean runJar() {
        return runCommands("jar");
    }

    public boolean runTests() {
        return runCommands("test");
    }

    public boolean runTestMethod(String testClassName, String testMethodName) {
        return runCommands("cleanTest", "test", "--tests", testClassName + "." + testMethodName);
    }
  
    public boolean runSpecificTest(String testName) {
        return runCommands("test", "--tests", testName);
    }

    private boolean runCommands(String... commands) {
        try {
            builder.command(gradleBinary).command().addAll(List.of(commands));
            var process = builder.start();
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

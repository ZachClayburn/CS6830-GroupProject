package FinalProject;

import org.apache.commons.lang3.SystemUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public boolean runSpecificTest(String testName) {
        return runCommands("test", "--tests", testName);
    }

    private boolean runCommands(String... commands) {
        try {
            builder.command(gradleBinary).command().addAll(List.of(commands));
            System.out.printf("Running %s\n", String.join(" ", builder.command()));
            var process = builder.start();
            var strBuilder = new StringBuilder();
            var inReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = inReader.readLine()) != null) {
                strBuilder.append(line);
                strBuilder.append(System.getProperty("lind.separator"));
            }
            var errReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errReader.readLine()) != null) {
                strBuilder.append(line);
                strBuilder.append(System.getProperty("line.separator"));
            }
            process.waitFor();
            if (process.exitValue() != 0) {
                System.out.println(strBuilder);
                return false;
            }
            return true;
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

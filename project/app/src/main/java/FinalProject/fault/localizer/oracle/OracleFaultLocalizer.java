package FinalProject.fault.localizer.oracle;

import FinalProject.fault.localizer.Line;

import java.io.File;

public class OracleFaultLocalizer {
    public static Line localizeFaults(File testFile) {
        return switch (testFile.getName()) {
            case "SimpleArithmeticTest.java" -> new Line("Simple.java", 33d);
            case "SimpleBitwiseTest.java" -> new Line("Simple.java", 41);
            case "SimpleCompareTest.java" -> new Line("Simple.java", 26);
            case "SimpleDeleteLineTest.java" -> new Line("Simple.java", 61);
            case "SimpleNegationTest.java" -> new Line("Simple.java", 48);
            case "SimpleNullTest.java" -> new Line("Simple.java", 18);
            default -> new Line("Simple.java", -1);
        };
    }
}

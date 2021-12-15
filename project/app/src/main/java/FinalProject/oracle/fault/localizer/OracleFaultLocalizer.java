package FinalProject.oracle.fault.localizer;

import java.io.File;

public class OracleFaultLocalizer {
    public static long localizeFaults(File testFile) {
        return switch (testFile.getName()) {
            case "SimpleArithmeticTest.java" -> 33;
            case "SimpleBitwiseTest.java" -> 41;
            case "SimpleCompareTest.java" -> 26;
            case "SimpleDeleteLineTest.java" -> 61;
            case "SimpleNegationTest.java" -> 48;
            case "SimpleNullTest.java" -> 18;
            default -> -1;
        };
    }
}

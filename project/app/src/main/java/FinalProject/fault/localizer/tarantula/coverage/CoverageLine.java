package FinalProject.fault.localizer.tarantula.coverage;

import FinalProject.fault.localizer.Line;

public class CoverageLine extends Line {
    public double passedTests;
    public double failedTests;
    public double suspiciousness;

    public CoverageLine(String sourceFileName, double lineNumber, double passedTests, double failedTests) {
        super(sourceFileName, lineNumber);
        this.passedTests = passedTests;
        this.failedTests = failedTests;
        this.suspiciousness = 0;
    }
}

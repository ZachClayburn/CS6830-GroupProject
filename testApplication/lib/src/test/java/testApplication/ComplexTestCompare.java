package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// One point of failure
class ComplexTestCompare {
    // Asserts with inputs 1-4 will pass by returning true
    // Assert with input 5 will fail by returning false
    @Test void compareError_1() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.compareError(1), "compareError should return 'true'");
    }
    @Test void compareError_2() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.compareError(2), "compareError should return 'true'");
    }
    @Test void compareError_3() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.compareError(3), "compareError should return 'true'");
    }
    @Test void compareError_4() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.compareError(4), "compareError should return 'true'");
    }
    @Test void compareError_5() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.compareError(5), "compareError should return 'true'");
    }

}
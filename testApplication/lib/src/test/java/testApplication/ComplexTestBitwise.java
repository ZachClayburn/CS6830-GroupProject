package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Single point of failure
class ComplexTestBitwise {
    // Asserts with inputs 1-2 will pass by returning true
    // Assert with input 3 will fail by returning fail
    @Test void bitwiseError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.bitwiseError(1), "boolError should return 'true'");
    }

    @Test void bitwiseError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.bitwiseError(2), "boolError should return 'true'");
    }

    @Test void bitwiseError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.bitwiseError(3), "boolError should return 'true'");
    }

}
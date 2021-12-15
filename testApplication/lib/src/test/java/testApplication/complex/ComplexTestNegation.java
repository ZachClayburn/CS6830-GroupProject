package testApplication.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Single point of failure where wrong fix reduces number of failures by about half
class ComplexTestNegation {
    // There are 9 tests
    // If the correct fix is made by changing "if(x)..." to "if(!x)..."
    // ...then all the tests should work.  Otherwise all fail.
    // If incorrect fix is made by changing "if(d)..." to "if(!d)..."
    // ...then all inputs with even values will fail
    @Test void negationError_1() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(1), "deleteLineError should return 'true'");
    }
    @Test void negationError_2() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(2), "deleteLineError should return 'true'");
    }
    @Test void negationError_3() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(3), "deleteLineError should return 'true'");
    }
    @Test void negationError_4() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(4), "deleteLineError should return 'true'");
    }
    @Test void negationError_5() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(5), "deleteLineError should return 'true'");
    }
    @Test void negationError_6() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(6), "deleteLineError should return 'true'");
    }
    @Test void negationError_7() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(7), "deleteLineError should return 'true'");
    }
    @Test void negationError_8() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(8), "deleteLineError should return 'true'");
    }
    @Test void negationError_9() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.negationError(9), "deleteLineError should return 'true'");
    }

}
package testApplication.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Single point of failure
class ComplexDeleteLineTest {
    // Asserts with inputs 1-5 will pass by returning true
    // Assert with input 6 will fail by returning false
    @Test void deleteLineError_1() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(1), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError_2() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(2), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError_3() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(3), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError_4() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(4), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError_5() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(5), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError_6() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(6), "deleteLineError should return 'true'");
    }

}
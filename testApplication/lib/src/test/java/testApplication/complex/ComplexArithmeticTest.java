package testApplication.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Multiple points of failure
class ComplexArithmeticTest {
    // Assert with input 4 will pass by returning true
    // Asserts with inputs 1-3 will fail by returning false
    @Test void arithmeticError_1() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.arithmeticError(1), "arithmeticError should return 'true'");
    }
    @Test void arithmeticError_2() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.arithmeticError(2), "arithmeticError should return 'true'");
    }
    @Test void arithmeticError_3() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.arithmeticError(3), "arithmeticError should return 'true'");
    }
    @Test void arithmeticError_4() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.arithmeticError(4), "arithmeticError should return 'true'");
    }

}
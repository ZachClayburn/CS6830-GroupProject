package testApplication.complex;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// One point of failure
class ComplexTestNull {
    // The first assert will pass by returning true
    // The second assert will fail by returning false
    @Test
    void nullError_1() {
        Complex classUnderTest = new Complex();
        assertEquals("3", classUnderTest.nullError(3), "nullError should return 'true'");
    }

    @Test
    void nullError_2() {
        Complex classUnderTest = new Complex();
        assertEquals("", classUnderTest.nullError(null), "nullError should return 'true'");
    }

}


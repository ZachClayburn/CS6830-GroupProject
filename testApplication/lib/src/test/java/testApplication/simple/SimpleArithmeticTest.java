package testApplication.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleArithmeticTest {
    @Test void arithmeticError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.arithmeticError(), "arithmeticError should return 'true'");
    }
}






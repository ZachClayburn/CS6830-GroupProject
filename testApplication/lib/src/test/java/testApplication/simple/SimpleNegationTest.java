package testApplication.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleNegationTest {
    @Test void negationError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.negationError(), "deleteLineError should return 'true'");
    }
}



package testApplication.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBitwiseTest {
    @Test
    void bitwiseError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.bitwiseError(), "boolError should return 'true'");
    }
}
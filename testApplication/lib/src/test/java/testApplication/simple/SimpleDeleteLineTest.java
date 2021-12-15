package testApplication.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleDeleteLineTest {
    @Test void deleteLineError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.deleteLineError(), "deleteLineError should return 'true'");
    }
}
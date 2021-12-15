package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTestNull {
    @Test void nullError_0() {
        Simple classUnderTest = new Simple();
        assertEquals("3", classUnderTest.nullError(Integer.valueOf(3)), "nullError should return 'true'");
        assertEquals("", classUnderTest.nullError(null), "nullError should return 'true'");
    }
}



package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SimpleTestCompare {
    @Test void compareError_0() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.compareError(), "compareError should return 'true'");
    }

}






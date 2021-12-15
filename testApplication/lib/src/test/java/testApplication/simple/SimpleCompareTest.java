package testApplication.simple;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleCompareTest {
    @Test void compareError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.compareError(), "compareError should return 'true'");
    }

}






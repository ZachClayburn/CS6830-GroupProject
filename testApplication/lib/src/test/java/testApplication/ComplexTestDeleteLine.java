package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Single point of failure
class ComplexTestDeleteLine {

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(1), "deleteLineError should return 'true'");
    }

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(2), "deleteLineError should return 'true'");
    }

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(3), "deleteLineError should return 'true'");
    }

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(4), "deleteLineError should return 'true'");
    }

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(5), "deleteLineError should return 'true'");
    }

    @Test void deleteLineError() {
        Complex classUnderTest = new Complex();
        assertTrue(classUnderTest.deleteLineError(6), "deleteLineError should return 'true'");
    }

}
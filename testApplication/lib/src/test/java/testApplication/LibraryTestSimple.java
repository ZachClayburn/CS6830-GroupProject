/*
 * This Java source file was generated by the Gradle 'init' task.
 *  Zach Walters and Nathanael Smith -- December 8
 *  Ian Rodriguez and Nathanael Smith -- December 13
 */

package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTestSimple {
    @Test void nullError() {
        Simple classUnderTest = new Simple();
        assertEquals("3", classUnderTest.nullError(Integer.valueOf(3)), "nullError should return 'true'");
        assertEquals("", classUnderTest.nullError(null), "nullError should return 'true'");
    }
    @Test void compareError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.compareError(), "compareError should return 'true'");
    }
    @Test void arithmeticError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.arithmeticError(), "arithmeticError should return 'true'");
    }
    @Test void bitwiseError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.bitwiseError(), "boolError should return 'true'");
    }
    @Test void negationError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.negationError(), "deleteLineError should return 'true'");
    }
    @Test void deleteLineError() {
        Simple classUnderTest = new Simple();
        assertTrue(classUnderTest.deleteLineError(), "deleteLineError should return 'true'");
    }

}


// Extra code, maybe useful later.
//    @Test void ifElseIfError() {
//        Library classUnderTest = new Library();
//        assertTrue(classUnderTest.ifElseIfError(), "ifElseIfError should return 'true'");
//        fail();
//    }






/*
 * This Java source file was generated by the Gradle 'init' task.
 *  Zach Walters and Nathanael Smith -- December 8
 *  Ian Rodriguez and Nathanael Smith -- December 13
 */

package testApplication;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTestSimple {
    @Test void nullError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertEquals("3", classUnderTest.nullError_1(3), "nullError should return 'true'");
        assertEquals("", classUnderTest.nullError_1(null), "nullError should return 'true'");
        fail();
    }
    @Test void compareError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertTrue(classUnderTest.compareError_1(), "compareError should return 'true'");
        fail();
    }
    @Test void arithmeticError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertTrue(classUnderTest.arithmeticError_1(), "arithmeticError should return 'true'");
        fail();
    }
    @Test void bitwiseError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertTrue(classUnderTest.bitwiseError_1(), "boolError should return 'true'");
        fail();
    }
    @Test void negationError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertTrue(classUnderTest.negationError_1(), "deleteLineError should return 'true'");
        fail();
    }
    @Test void deleteLineError_1() {
        LibrarySimple classUnderTest = new LibrarySimple();
        assertTrue(classUnderTest.deleteLineError_1(), "deleteLineError should return 'true'");
        fail();
    }

}


// Extra code, maybe useful later.
//    @Test void ifElseIfError_1() {
//        Library classUnderTest = new Library();
//        assertTrue(classUnderTest.ifElseIfError_1(), "ifElseIfError should return 'true'");
//        fail();
    }






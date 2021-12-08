/*
 * This Java source file was generated by the Gradle 'init' task.
 *  Zach Walters and Nathanael Smith worked on this
 */

/////  Ideas  /////
// operator switch
// ... compare operator (==, &&, ||, <, >, etc.)
// ... arithmetic operator (+, -, /, *, etc.)
// ... bool operator ( &, |, ^, etc.)
// delete erroneous line
// "if" vs "else if"
//  .
//  NEED MORE

package testApplication;

public class Library {

    public boolean nullError() {
        Integer value = null;
        boolean success = true;
        int a = 5;
        int b = 10;
        int c = 15;
        success = (value == c);
        return success;
    }

    public boolean compareError() {
        int a = 3;
        int b = 4;
        // could be a <= b or a < b
        return a > b;
    }

    public boolean arithmeticError() {
        int a = 13;
        int b = 7;
        // should be a * b
        return a + b == 91
    }

    public boolean boolError() {
        int a = 5;
        int b = 3;
        // should be a | b
        return (a & b) == 7
    }

    public boolean deleteLineError() {
        int a = 2;
        int b = 3;
        int c = 5;
        // Should delete next line
        c += a + b;
        return c == 5;
    }

    public boolean ifElseIfError() {
        bool result = false;
        if true {
            result = !result;
        }
        // Should not flip result again
        if true {
            result = !result;
        }
        return result;
    }
}




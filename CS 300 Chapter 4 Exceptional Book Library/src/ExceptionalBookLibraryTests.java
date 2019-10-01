//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P04 Exceptional Book Library
// Files: ExceptionalBookLibraryTests.java
// Course: Spring 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains the code for the Subscriber class in the Exceptional Book Library
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

import java.text.ParseException;


/**
 * Contains the testing methods for the Exceptional Book Library Class/Program. Runs a variety of
 * tests to check the program functionality.
 */
public class ExceptionalBookLibraryTests {

  /**
   * Tests the Parse Card Bar Code Method in the Exceptional Library Class.
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testLibraryParseCardBarCode() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    // Tests a Valid Example
    String s = "2019000002";
    int errorOffset = 1;

    try {
      if (madisonLibrary.parseCardBarCode(s, errorOffset) != 2019000002) {
        return false;
      }

    } catch (ParseException e) {
      return false;
    }

    // Tests an invalid input that cannot be parsed
    s = "abcde";

    try {
      madisonLibrary.parseCardBarCode(s, errorOffset);
      return false;
    } catch (ParseException e) {
      // Supposed to catch error here
    }

    // Tests an invalid input that can be parsed but does not pass the other method test
    s = "1019000002";

    try {
      madisonLibrary.parseCardBarCode(s, errorOffset);

    } catch (ParseException e) {
      // Supposed to throw an exception
    }

    // If Passes all tests then it returns true
    return true;
  }

  /**
   * Tests the Parse Pin Code Method in the Exceptional Library Class.
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testParsePinCode() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    // Tests a Valid Example
    String s = "1001";
    int errorOffset = 1;

    try {
      if (madisonLibrary.parsePinCode(s, errorOffset) != 1001) {
        return false;
      }
    } catch (ParseException e) {
      return false;
    }

    // Tests an invalid Example
    s = "smdmdmff";

    try {
      madisonLibrary.parsePinCode(s, errorOffset);

    } catch (ParseException e) {
      // Supposed to catch the exception
    }

    // If Passes all tests then it returns true
    return true;
  }

  /**
   * Tests the Parse Book ID Method in the Exceptional Library Class.
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testParseBookID() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    // Tests a Valid Example
    String s = "5";
    int errorOffset = 1;

    try {
      madisonLibrary.parseBookId(s, errorOffset);

    } catch (ParseException e) {
      return false;
    }

    // Tests an invalid Example
    s = "smf";

    try {
      madisonLibrary.parsePinCode(s, errorOffset);

    } catch (ParseException e) {
      // Supposed to catch the exception
    }

    // If Passes all tests then it returns true
    return true;
  }

  /**
   * Test the Library Checkout Book Command
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testLibraryParseRunLibrarianCheckoutBookCommand() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    // Invalid input
    String[] commands = {};

    try {
      madisonLibrary.parseRunLibrarianCheckoutBookCommand(commands);

      // Should not get here because the method call should throw an exception
      return false;
    } catch (ParseException e) {
      // Should Catch the Exception
    }

    String[] commands1 = {"3", "1019000002", "s"};

    try {
      madisonLibrary.parseRunLibrarianCheckoutBookCommand(commands1);
    } catch (ParseException e) {
      // Should Catch the error because cannot parse s
    }

    // If Passes all tests then it returns true
    return true;
  }

  /**
   * Tests the Parse Run subscriber return book command Method in the Exceptional Library Class.
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testLibraryParseRunSubscriberReturnBookCommand() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");


    // Invalid input
    String[] commands = {};


    try {
      Subscriber subscriber = new Subscriber("Jake", 1234, "CloverHill", "6082123434");
      madisonLibrary.parseRunSubscriberReturnBookCommand(commands, subscriber);

    } catch (ParseException e) {
      // Should catch the exception
    } catch (InstantiationException e) {
      return false;
    }

    // Invalid input
    String[] commands1 = {"3", "a"};


    try {
      Subscriber subscriber = new Subscriber("Jake", 1234, "CloverHill", "6082123434");
      madisonLibrary.parseRunSubscriberReturnBookCommand(commands1, subscriber);

    } catch (ParseException e) {
      // Should catch the exception
    } catch (InstantiationException e) {
      return false;
    }

    // If Passes all tests then it returns true
    return true;
  }

  /**
   * Tests the Save book list Method in the Exceptional Library Class.
   * 
   * @return true if it passes all of the tests and false otherwise.
   */
  public static boolean testSaveBookList() {
    // Creates a new instance of library
    ExceptionalLibrary madisonLibrary = new ExceptionalLibrary("Madison, WI", "april", "abc");

    String[] commands = {"1", "books1.data"};

    try {
      madisonLibrary.parseRunLibrarianLoadBooksCommand(commands);
    } catch (ParseException e1) {
      return false;
    }

    commands[1] = "jacob.txt";

    try {
      madisonLibrary.parseRunLibrarianSaveBooksCommand(commands);
    } catch (ParseException e) {
      return false;
    }

    return true;
  }

  /**
   * Contains the call to the testing methods in the class.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1:
    System.out.println("TEST 1 : testLibraryParseCardBarCode(): " + testLibraryParseCardBarCode());

    // TEST 2:
    System.out.println("TEST 2 : testParsePinCode(): " + testParsePinCode());

    // TEST 3:
    System.out.println("TEST 3 : testParseBookID(): " + testParseBookID());

    // TEST 4:
    System.out.println("TEST 4 : testLibraryParseRunLibrarianCheckoutBookCommand(): "
        + testLibraryParseRunLibrarianCheckoutBookCommand());

    // TEST 5:
    System.out.println("TEST 5 : testLibraryParseRunSubscriberReturnBookCommand(): "
        + testLibraryParseRunSubscriberReturnBookCommand());

    // TEST 6:
    System.out.println("TEST 6 : testSaveBookList(): " + testSaveBookList());


  }

}

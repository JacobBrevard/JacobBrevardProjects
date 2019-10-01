//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Dictionary Using BST
// Files: Dictionary.java, DictionaryWord.java, DictionaryBST.java, DictionaryDriver.java, and
// DictionaryTests.java
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
 * This class contains the code for the Dictionary Tests Class in the Dictionary program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import Statement
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * The Dictionary Tests Class has a variety of testing methods to check the correctness of the other
 * classes in the program.
 * 
 */
public class DictionaryTests {

  /**
   * The main method to call the various tests and output the result to the counsel.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1 call and print statement
    System.out.println("TEST 1: dictionaryBstTestIsEmpty(): " + dictionaryBstTestIsEmpty());

    // TEST 2 call and print statement
    System.out.println("TEST 2: dictionaryBstTestLookup(): " + dictionaryBstTestLookup());

    // TEST 3 call and print statement
    System.out.println("TEST 3: dictionaryBstTestSize(): " + dictionaryBstTestSize());

    // TEST 4 call and print statement
    System.out.println("TEST 4: dictionaryBstTestHeight(): " + dictionaryBstTestHeight());

    // TEST 5 call and print statement
    System.out.println("TEST 5: dictionaryBstTestGetAllWords(): " + dictionaryBstTestGetAllWords());

    // TEST 6 call and print statement
    System.out.println(
        "TEST 6: dictionaryBstTestAdditionalWords(): " + dictionaryBstTestAdditionalWords());
  }

  /**
   * Tests whether the is Empty method works properly with an empty and non empty list. Also uses
   * the addWord method.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestIsEmpty() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    if (!dict1.isEmpty()) {
      return false;
    }

    // Adds a word to the list making it not empty
    dict1.addWord("Funny", "Adjective");

    if (dict1.isEmpty()) {
      return false;
    }

    return true;
  }

  /**
   * Tests whether the lookup method works properly. Also uses the addWord method.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestLookup() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    try {
      dict1.lookup("Bee");

      // Should not get here because it will throw an exception
      return false;
    } catch (NoSuchElementException e) {
      // Should catch the exception
    }

    dict1.addWord("Dog", "Is an animal2");

    // Should return the word Dog because it is in the list
    if (!dict1.lookup("dog").equals("Is an animal2")) {
      return false;
    }

    dict1.addWord("Cat", "Is an animal");

    dict1.addWord("Turtle", "Is an animal");

    dict1.addWord("Snake", "Is an animal");

    dict1.addWord("Eagle", "Is an animal4");

    // Should return the word Eagle because it is in the list
    if (!dict1.lookup("eagle").equals("Is an animal4")) {
      return false;
    }

    return true;

  }

  /**
   * Tests whether the size method works properly. Also uses the addWord method and tests that you
   * cannot add the same word that already exists in the dictionary.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestSize() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    // Checks that a call of size on an empty list returns 0
    if (dict1.size() != 0) {
      return false;
    }

    // Adds words to the list
    dict1.addWord("Dog", "Is an animal");

    dict1.addWord("Cat", "Is an animal");

    dict1.addWord("Turtle", "Is an animal");

    dict1.addWord("Snake", "Is an animal");

    dict1.addWord("Eagle", "Is an animal");

    // Make sure the correct size is returned
    if (dict1.size() != 5) {
      return false;
    }

    // Makes sure you cannot add a duplicate word even if the case is different
    if (dict1.addWord("eAgLe", "Is an animal") != false) {
      return false;
    }

    // Makes sure the word was not added to the list and therefore the size did not change
    if (dict1.size() != 5) {
      return false;
    }

    return true;
  }

  /**
   * Tests whether the height method works properly.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestHeight() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    // Checks if a call of height on an empty list correctly returns 0
    if (dict1.height() != 0) {
      return false;
    }

    // Adds words to the list
    dict1.addWord("Dog", "Is an animal");

    dict1.addWord("Cat", "Is an animal");

    dict1.addWord("Turtle", "Is an animal");

    dict1.addWord("Snake", "Is an animal");

    dict1.addWord("Eagle", "Is an animal");

    // Checks that the correct height is returned from this Dictionary BST
    if (dict1.height() != 4) {
      return false;
    }

    return true;
  }

  /**
   * Tests whether the get All Words method works properly.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestGetAllWords() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    // Adds words to the list
    dict1.addWord("Dog", "Is an animal");

    dict1.addWord("Cat", "Is an animal");

    dict1.addWord("Turtle", "Is an animal");

    dict1.addWord("Snake", "Is an animal");

    dict1.addWord("Eagle", "Is an animal");

    ArrayList<String> list1 = dict1.getAllWords();

    // Checks each element in the ArrayList to make sure that they match up and are correctly sorted
    // alphabetically
    if (!list1.get(0).equals("Cat")) {
      return false;
    }
    if (!list1.get(1).equals("Dog")) {
      return false;
    }
    if (!list1.get(2).equals("Eagle")) {
      return false;
    }
    if (!list1.get(3).equals("Snake")) {
      return false;
    }
    if (!list1.get(4).equals("Turtle")) {
      return false;
    }

    return true;
  }

  /**
   * Tests whether the add Words method works properly.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean dictionaryBstTestAdditionalWords() {
    // Creates a new empty dictionary
    DictionaryBST dict1 = new DictionaryBST();

    dict1.addWord("famous", "A good Word");

    dict1.addWord("exceptional", "A good Word");

    dict1.addWord("sofa", "A good Word");

    dict1.addWord("main", "A good Word");

    dict1.addWord("alllocte", "A good Word");

    dict1.addWord("Success", "A good Word");

    dict1.addWord("a", "A good Word");

    dict1.addWord("Success", "A good Word");

    dict1.addWord("a", "A good Word");


    if (!dict1.lookup("a").equals("A good Word")) {
      return false;
    }

    return true;
  }
}

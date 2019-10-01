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
 * This class contains the code for the Dictionary Word Class in the Dictionary program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Dictionary Word Class has a variety of methods to implement the functions and characteristics
 * of a dictionary word in a node of a Binary Search Tree.
 * 
 */
public class DictionaryWord {
  // word that represents the search key for this dictionary word
  private final String word;

  // The meaning of the word that this dictionary node defines
  private final String meaning;

  // The leftChild of the the current WebPageNode
  private DictionaryWord leftChild;

  // The rightChild of the the current WebPageNode
  private DictionaryWord rightChild;

  // The following should be the only constructor for this class
  // Creates a new dictionary word with the provided word and its meaning pair
  // Throws IllegalArgumentException when the word or meaning are either references to an empty
  // string or null references. The thrown exception should include a significant error message
  // describing which of these problems was encountred.
  /**
   * This is the constructor for the dictionary word object that is represented by a BST node.
   * 
   * @param word - The dictionary word to be stored in this object.
   * 
   * @param meaning - The meaning to be attached to this word.
   */
  public DictionaryWord(String word, String meaning) {
    // Checks if we need to throw an exception
    if (word == null) {
      throw new IllegalArgumentException("Error: word cannot be null.");
    } else if (meaning == null) {
      throw new IllegalArgumentException("Error: meaning cannot be null.");
    } else if (word.trim().equals("")) {
      throw new IllegalArgumentException("Error: word cannot be empty. Must contain a word.");
    } else if (meaning.trim().equals("")) {
      throw new IllegalArgumentException("Error: meaning cannot be empty. Must contain a word.");
    }

    // Sets the objects fields
    this.word = word;

    this.meaning = meaning;

    this.leftChild = null;

    this.rightChild = null;

  }

  // Getter for the left child of this dictionary word
  /**
   * Getter for the left child of this dictionary word.
   * 
   * @return the left child of the dictionary word.
   */
  public DictionaryWord getLeftChild() {
    return leftChild;
  }

  // Setter for the left child of this dictionary word
  /**
   * Setter for the left child of this dictionary word.
   * 
   * @param leftChild - The leftChild dictionary word passed in
   */
  public void setLeftChild(DictionaryWord leftChild) {
    this.leftChild = leftChild;
  }

  // Getter for the right child of this dictionary word
  /**
   * Getter for the right child of this dictionary word.
   * 
   * @return the right child of this dictionary word.
   */
  public DictionaryWord getRightChild() {
    return rightChild;
  }

  // Setter for the right child of this dictionary word
  /**
   * Setter for the right child of this dictionary word.
   * 
   * @param rightChild - The right child dictionary word passed in to be set to.
   */
  public void setRightChild(DictionaryWord rightChild) {
    this.rightChild = rightChild;
  }

  // Getter for the word of this dictionary word
  /**
   * Getter for the word of this dictionary word.
   * 
   * @return the dictionary word.
   */
  public String getWord() {
    return word;
  }

  // Getter for the meaning of the word of this dictionary word
  /**
   * Getter for the meaning of the word of this dictionary word.
   * 
   * @return the meaning of this dictionary word.
   */
  public String getMeaning() {
    return meaning;
  }

  // Returns a String representation of this DictionaryWord.
  // This String should be formatted as follows. "<word>: <meaning>"
  // For instance, for a dictionaryWord that has the String "Awesome"
  // for the instance field word and the String "adj. Inspiring awe; dreaded."
  // as value for meaning field, the String representing that dictionaryWord is
  // "Awesome: adj. Inspiring awe; dreaded."
  /**
   * Returns a String representation of this DictionaryWord. This String should be formatted as
   * follows. "<word>: <meaning>" For instance, for a dictionaryWord that has the String "Awesome"
   * for the instance field word and the String "adj. Inspiring awe; dreaded." as value for meaning
   * field, the String representing that dictionaryWord is "Awesome: adj. Inspiring awe; dreaded."
   * 
   * @return the string representation of the dictionary word and meaning.
   */
  public String toString() {
    return (word + ": " + meaning);
  }

}

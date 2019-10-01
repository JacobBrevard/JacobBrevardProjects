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
 * This class contains the code for the Dictionary Interface in the Dictionary program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Dictionary Interface has a variety of methods to implement the functions and characteristics
 * of a dictionary.
 * 
 */
public interface Dictionary {
  // checks whether the dictionary is empty or not
  /**
   * Checks whether the dictionary is empty or not.
   * 
   * @return either true or false.
   */
  public boolean isEmpty();

  // adds this word definition (word and the provided meaning) to the dictionary
  // Returns true if the word was successfully added to this dictionary
  // Returns false if the word was already in the dictionary
  // Throws IllegalArgumentException if either word or meaning is null or an empty
  // String
  /**
   * Adds this word definition (word and the provided meaning) to the dictionary. Returns true if
   * the word was successfully added to this dictionary Returns false if the word was already in the
   * dictionary Throws IllegalArgumentException if either word or meaning is null or an empty String
   * 
   * @param word - The word passed in.
   * 
   * @param meaning - The meaning of the word.
   * 
   * @return true if the word was successfully added to this dictionary Returns false if the word
   *         was already in the dictionary Throws IllegalArgumentException if either word or meaning
   *         is null or an empty String.
   */
  public boolean addWord(String word, String meaning);


  // Returns the meaning of the word s if it is present in this dictionary
  // Throws a NoSuchElementException if the word s was not found in this dictionary
  /**
   * Looks Up the meaning of a word in the dictionary.
   * 
   * @param s - The word passed in which we are searching for.
   * 
   * @return - Returns the meaning of the word s if it is present in this dictionary Throws a
   *         NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s);

  // Returns the number of words stored in this dictionary
  /**
   * Gets the number of words stored in the dictionary.
   * 
   * @return - the number of words stored in this dictionary.
   */
  public int size();

}

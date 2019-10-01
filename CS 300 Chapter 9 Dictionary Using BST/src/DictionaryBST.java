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
 * This class contains the code for the Dictionary BST Class in the Dictionary program.
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
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * The Dictionary BST Class has a variety of methods to implement the functions and characteristics
 * of a dictionary through a Binary Search Tree.
 * 
 */
public class DictionaryBST implements Dictionary {
  // Instance field to keep track of the root of the dictionary
  private DictionaryWord root;

  // This should be the only constructor of this class.
  // Creates an empty dictionaryBST.
  /**
   * This constructor creates an instance of a Dictionary BST object.
   * 
   */
  public DictionaryBST() {
    // Sets the root to be null
    root = null;
  }

  @Override
  /**
   * Checks whether the dictionary is empty or not.
   * 
   * @return true if the dictionary is empty and false otherwise.
   */
  public boolean isEmpty() {
    if (root == null) {
      return true;
    }
    return false;
  }

  @Override
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
  public boolean addWord(String word, String meaning) {
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

    // Creates the new dictionary word to be added
    DictionaryWord add = new DictionaryWord(word, meaning);

    // If the dictionary is empty then we set the root to be the dictionary word
    if (isEmpty()) {
      root = add;
      return true;
    }

    // If the add word helper method returns true then the word was successfully added
    if (addWordHelper(add, root)) {
      return true;
    }

    return false;
  }

  @Override
  /**
   * Looks Up the meaning of a word in the dictionary.
   * 
   * @param s - The word passed in which we are searching for.
   * 
   * @return - Returns the meaning of the word s if it is present in this dictionary Throws a
   *         NoSuchElementException if the word s was not found in this dictionary
   */
  public String lookup(String s) {
    return lookupHelper(s, root);
  }

  @Override
  /**
   * Gets the number of words stored in the dictionary.
   * 
   * @return - the number of words stored in this dictionary.
   */
  public int size() {
    return sizeHelper(root);
  }

  // Public methods not defined in the Dictionary interface
  /**
   * Computes and returns the height of this dictionaryBST, as the number of nodes from root to the
   * deepest leaf DictionaryWord node.
   * 
   * @return the height of this Binary Search Tree counting the number of DictionaryWord nodes
   */
  public int height() {
    // Adds 1 to the height to account for the root node
    return heightHelper(root) + 1;
  }

  /**
   * Returns all the words within this dictionary sorted from A to Z
   * 
   * @return an ArrayList that contains all the words within this dictionary sorted in the ascendant
   *         order
   */
  public ArrayList<String> getAllWords() {
    if (root == null) {
      return new ArrayList<String>();
    }

    // Stores the arrayList in the list 1 variable
    ArrayList<String> list1 = getAllWordsHelper(root);

    // Sorts the list lexographic
    Collections.sort(list1, String.CASE_INSENSITIVE_ORDER);

    return list1;
  }

  // Recursive private helper methods
  // Each public method should make call to the recursive helper method with the
  // corresponding name
  /**
   * Recursive helper method to add newWord in the subtree rooted at node
   * 
   * @param newWordNode a new DictionaryWord to be added to this dictionaryBST
   * @param current the current DictionaryWord that is the root of the subtree where newWord will be
   *        inserted
   * @return true if the newWordNode is successfully added to this dictionary, false otherwise
   */
  private static boolean addWordHelper(DictionaryWord newWordNode, DictionaryWord current) {
    // If the compare to method returns 0 which means the words are exactly the same we return false
    if (current.getWord().compareToIgnoreCase(newWordNode.getWord()) == 0) {
      return false;
    } // If the compare to method returns a value greater than 0 we know that the value is less than
      // our current node so we get the left child
    else if (current.getWord().compareToIgnoreCase(newWordNode.getWord()) > 0) {
      if (current.getLeftChild() == null) {
        current.setLeftChild(newWordNode);
        return true;
      } else {
        return addWordHelper(newWordNode, current.getLeftChild());
      }

    } // If the compare to method returns something less than 0 we know the value is greater than
      // our current node so we get the right child
    else if (current.getWord().compareToIgnoreCase(newWordNode.getWord()) < 0) {
      if (current.getRightChild() == null) {
        current.setRightChild(newWordNode);
        return true;
      } else {
        return addWordHelper(newWordNode, current.getRightChild());
      }

    }
    return false;
  }

  /**
   * Recursive helper method to lookup a word s in the subtree rooted at current.
   * 
   * @param s String that represents a word
   * @param current pointer to the current DictionaryWord within this dictionary
   * @return the meaning of the word s if it is present in this dictionary
   * @throws NoSuchElementException if s is not found in this dictionary
   */
  private static String lookupHelper(String s, DictionaryWord current) {
    if (current == null) {
      throw new NoSuchElementException(s);
    }

    if (current.getWord().compareToIgnoreCase(s) == 0) {
      // Will return but will not actually be the final return value
      return current.getMeaning();
    } // If the compare to method returns a value greater than 0 we know that the value is less than
      // our current node so we get the left child
    else if (current.getWord().compareToIgnoreCase(s) > 0) {
      if (current.getLeftChild() == null) {
        throw new NoSuchElementException("Error: The Word was not found in the dictionary");
      } else {
        return lookupHelper(s, current.getLeftChild());
      }

    } // If the compare to method returns something less than 0 we know the value is greater than
      // our current node so we get the right child
    else {
      if (current.getRightChild() == null) {
        throw new NoSuchElementException("Error: The Word was not found in the dictionary");
      } else {
        return lookupHelper(s, current.getRightChild());
      }
    }
  }

  /**
   * Recursive helper method that returns the number of dictionary words stored in the subtree
   * rooted at current
   * 
   * @param current current DictionaryWord within this dictionaryBST
   * @return the size of the subtree rooted at current
   */
  private static int sizeHelper(DictionaryWord current) {
    // if it's null, it doesn't exist, return 0. Base Case
    if (current == null) {
      return 0;
    }

    // count myself + my left child + my right child
    return 1 + sizeHelper(current.getLeftChild()) + sizeHelper(current.getRightChild());
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current
   * 
   * @param current pointer to the current DictionaryWord within this DictionaryBST
   * @return height of the subtree rooted at current counting the number of DictionaryWord nodes
   *         from the current node to the deepest leaf in the subtree rooted at current
   */
  private static int heightHelper(DictionaryWord current) {
    // This method does not count the root as 1

    // If the current node is 1 we return -1 as our base case
    if (current == null) {
      return -1;
    }

    // Calls the left and right child. If the element exists the value of it will be 0 an if it does
    // not exist it will be set to -1. Then at the end we add 1 so those that exist = 1 and those
    // that don't exist = 0.
    int left = heightHelper(current.getLeftChild());
    int right = heightHelper(current.getRightChild());

    // Compares the left and right and then adds 1 to the correct variable counting the ones that
    // exist and making the values of those that don't exist = 0.
    if (left > right) {
      return left + 1;
    } else {
      return right + 1;
    }

  }

  /**
   * Recursive Helper method that returns a list of all the words stored in the subtree rooted at
   * current
   * 
   * @param current pointer to the current DictionaryWord within this dictionaryBST
   * @return an ArrayList of all the words stored in the subtree rooted at current
   */
  private static ArrayList<String> getAllWordsHelper(DictionaryWord current) {
    // Creates the ArrayList For the Words to Be returned in
    ArrayList<String> list = new ArrayList<>();

    // If current != null we add it to the list
    if (current != null) {
      list.add(current.getWord());
    }

    // If left child of the current node is not null then we call the recursive method with the
    // left child node
    if (current.getLeftChild() != null) {
      list.addAll(getAllWordsHelper(current.getLeftChild()));
    }

    // If right child of the current node is not null then we call the recursive method with the
    // right child node
    if (current.getRightChild() != null) {
      list.addAll(getAllWordsHelper(current.getRightChild()));
    }

    // After the recursive method finishes we return the list
    return list;
  }

}

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating To Philosophy
// Files: EvenNumbers.java
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
 * This class contains the code for the Even Numbers Class in the Iterating to philosophy program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import Statement
import java.util.Iterator;

/**
 * The Even Numbers Class generates a sequence of even numbers started a specified starting point.
 *
 */
public class EvenNumbers implements Iterator<Integer> {

  // Private instance field of the number for the Even Number object
  private Integer number;

  // Private instance field to keep track of whether or not the next method has been called on the
  // object before
  private Boolean hasBeenCalled;

  /**
   * Sets the even number iterator object to a starting value and creates the new object.
   * 
   * @param number - The variable where the iterator starts.
   */
  public EvenNumbers(Integer number) {
    this.number = number;

    hasBeenCalled = false;
  }

  /**
   * The next method returns the number initialized to the iterator. Each subsequent call of the
   * next() method should return the smallest even number that is larger than the previously
   * returned one.
   * 
   * @return the even number generated.
   */
  public Integer next() {
    if (hasBeenCalled) {
      hasNext();
      return number;
    }

    hasBeenCalled = true;
    return number;
  }

  /**
   * This method is called by the next method to see if it has a next even number. It adds 2 to the
   * even number field and then returns true.
   * 
   * @return always returns true
   */
  public boolean hasNext() {
    number += 2;
    return true;
  }

}

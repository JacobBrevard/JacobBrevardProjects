//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating To Philosophy
// Files: FiniteIterator.java
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
 * This class contains the code for the Finite Iterator Class in the Iterating to philosophy
 * program.
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
 * The Finite Iterator Class generates a sequence of Numbers in a finite amount.
 * 
 * @param <T> - The generic type parameter
 *
 */
public class FiniteIterator<T> implements Iterator<T> {

  // The field is the generic Infinite iterator object used in the finite iterator to crete the new
  // integers
  private InfiniteIterator<T> infiniteIterator;

  // The Instance field length is the number of integers the finte iterator wants to generate from
  // the infinite iterator
  private int length;

  /**
   * The Finite Iterator class creates a new finite iterator object and sets the two private
   * instance fields.
   * 
   * @param infiniteIterator - The passed in generic infinite iterator.
   * 
   * @param length - The number of numbers to be generated from the infinite iterator.
   */
  public FiniteIterator(InfiniteIterator<T> infiniteIterator, int length) {
    this.length = length;

    this.infiniteIterator = infiniteIterator;

  }

  /**
   * The next method subtracts one from the length of this finite iterator and then calls the next
   * method of the infinite iterator class to get the next value.
   * 
   * @return the value returned by the next call to the Infinite Iterator Class.
   */
  public T next() {
    length--;
    return infiniteIterator.next();
  }

  /**
   * This method checks whether or not the number of intended values have been generated or not
   * using the length parameter.
   * 
   * @return true if the length field is greater than 0 and false otherwise.
   */
  public boolean hasNext() {
    if (length > 0) {
      return true;
    }

    return false;
  }
}

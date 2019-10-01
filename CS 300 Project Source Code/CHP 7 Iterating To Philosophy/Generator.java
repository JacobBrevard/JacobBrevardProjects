//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating To Philosophy
// Files: Generator.java
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
 * This class contains the code for the Generator Class in the Iterating to philosophy program.
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
import java.util.function.Function;

/**
 * The generator class is an extension of the iterator we created previously and has two overloaded
 * constructors to create generators.
 * 
 */
public class Generator<T> implements Iterable<T> {

  // Instance field to be the first value passed in for the iterator to start at
  private T firstValue;

  // Instance field to generate the Next from the last
  private Function<T, T> generateNextFromLast;

  // How many numbers we want generated
  private int length;

  // Instance field used to associate the iterator created
  public InfiniteIterator<T> infiniteIterator;

  // Instance field to keep track of the finiteIterator
  public FiniteIterator<T> finiteIterator;

  // Instance field to know if the object was created in the first or second constructor
  private boolean isFirst;

  /**
   * The Generator constructor is overloaded and creates a new generator object with a infinite
   * iterator.
   * 
   * @param firstValue - The starting value for the iterator.
   * @param generateNextFromLast - The type of function used to create the variable.
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast) {
    this.firstValue = firstValue;

    this.generateNextFromLast = generateNextFromLast;

    isFirst = true;

    iterator();
  }

  /**
   * The Generator constructor is overloaded and creates a new generator object with a finite
   * iterator.
   * 
   * @param firstValue - The starting value for the iterator.
   * @param generateNextFromLast - The type of function used to create the variable.
   * @param length - The number of things we want to generate in our finite iterator that we create.
   */
  public Generator(T firstValue, Function<T, T> generateNextFromLast, int length) {
    this.firstValue = firstValue;

    this.generateNextFromLast = generateNextFromLast;

    this.length = length;

    isFirst = false;

    iterator();

  }

  /**
   * The Iterator method creates a new finite iterator if the first constructor is used to create
   * the object or a finite iterator if the second constructor is called to create the object.
   * 
   * @return either an infinite iterator if the first constructor is called or a finite iterator if
   *         the second constructor is called.
   */
  public Iterator<T> iterator() {
    // Creates a new Infintite Iterator
    InfiniteIterator<T> temp = new InfiniteIterator<T>(firstValue, generateNextFromLast);

    // If the isFirst field is true we know that the object was created in the first constructor and
    // we create a new Infinite Iterator and then return it after setting it to the field.
    if (isFirst) {
      infiniteIterator = temp;
      return infiniteIterator;
    } else {
      // We create a new finite iterator if the length is specified
      finiteIterator = new FiniteIterator<T>(temp, length);
      return finiteIterator;
    }
  }

}

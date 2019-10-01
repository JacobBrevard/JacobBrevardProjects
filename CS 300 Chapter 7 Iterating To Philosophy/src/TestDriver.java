//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating To Philosophy
// Files: TestDriver.java
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
 * This class contains the code for the Test Driver Class in the Iterating to philosophy program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import Statements
import java.util.function.Function;

/**
 * The Test Driver Class is the main driver for testing the Iterating to philosophy application.
 *
 */
public class TestDriver {
  /**
   * The main method to call the test methods.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1 call and print statement
    System.out.println("TEST 1: testEvenNumbers(): " + testEvenNumbers());

    // TEST 2 call and print statement
    System.out.println("TEST 2: testPowersOfTwo(): " + testPowersOfTwo());

    // TEST 3 call and print statement
    System.out.println("TEST 3: testAddExtraSmile(): " + testAddExtraSmile());

    // TEST 4 call and print statement
    System.out.println("TEST 4: testFiniteIterator(): " + testFiniteIterator());

    // TEST 5 call and print statement
    System.out.println("TEST 5: testGenerator(): " + testGenerator());

  }

  /**
   * Tests the even numbers class.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testEvenNumbers() {
    // Creates a new Even number generator object
    EvenNumbers it = new EvenNumbers(44);

    // Checks if the first call to next returns 44
    if (it.next() != 44) {
      System.out.println("The first call of EvenNumbers.next() "
          + "did not return the value passed into its constructor.");
      return false;
    }

    // Checks to see if the next call to next returns 46
    if (it.next() != 46) {
      System.out.println(
          "The second call of EvenNumbers.next() " + "did not return the smallest even number, "
              + "that is larger than the previously returned number.");
      return false;
    }

    // Calls the hasNext method which always returns true
    if (it.hasNext() != true) {
      System.out.println("EvenNumbers.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Tests the Infinite Iterator Class with powers of 2.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testPowersOfTwo() {
    InfiniteIterator<Integer> it = new InfiniteIterator<Integer>(8, new NextPowerOfTwo());
    if (it.next() != 8) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the number passed into its constructor.");
      return false;
    }
    if (it.next() != 16) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the smallest power of two number, "
          + "that is larger than the previously returned number.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Tests the Infinite Iterator Class with the generic types and the add extra smile method.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testAddExtraSmile() {
    InfiniteIterator<String> it = new InfiniteIterator<String>("Hello", new AddExtraSmile());
    if (!it.next().equals("Hello")) {
      System.out.println("The first call of InfiniteIterator.next() "
          + "did not return the string passed into its constructor.");
      return false;
    }
    if (!it.next().contains(" :)")) {
      System.out.println("The second call of InfiniteIterator.next() "
          + "did not return the a string with one more :), "
          + "than the previously returned string.");
      return false;
    }
    if (it.hasNext() != true) {
      System.out
          .println("InfiniteIterator.next() returned false, " + "but should always return true.");
      return false;
    }
    return true;
  }

  /**
   * Tests the Finite Iterator Class with the generic types and the add next power of 2 method.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testFiniteIterator() {
    InfiniteIterator<Integer> infinite = new InfiniteIterator<>(2, new NextPowerOfTwo());
    FiniteIterator<Integer> it = new FiniteIterator<Integer>(infinite, 8);
    String s = "";
    while (it.hasNext())
      s += " " + it.next();
    if (!s.equals(" 2 4 8 16 32 64 128 256")) {
      System.out.println("Repeatedly called the next() method of a FiniteIterator,"
          + "and the incorrect valuese were returned:" + s);
      return false;
    }
    return true;
  }

  /**
   * Tests the testGenerator class to see if the method is working correctly.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testGenerator() {
    Generator<Integer> generatorInfinite = new Generator<>(2, new NextPowerOfTwo());

    if (generatorInfinite.infiniteIterator.next() != 2) {
      return false;
    }

    if (generatorInfinite.finiteIterator != null) {
      return false;
    }

    Generator<Integer> generatorFinite = new Generator<>(2, new NextPowerOfTwo(), 3);

    if (generatorFinite.infiniteIterator != null) {
      return false;
    }

    if (generatorFinite.finiteIterator.next() != 2) {
      return false;
    }

    if (generatorFinite.finiteIterator.next() != 4) {
      return false;
    }

    if (generatorFinite.finiteIterator.next() != 8) {
      return false;
    }

    if (generatorFinite.finiteIterator.hasNext() != false) {
      return false;
    }

    return true;
  }

}


/**
 * The Next Power of two class is a helper class for the Infinite Iterator class.
 */
class NextPowerOfTwo implements Function<Integer, Integer> {
  @Override
  public Integer apply(Integer previous) {
    return 2 * previous;
  }

}


/**
 * The Add Extra Smile class adds an extra smile face to the end of a string.
 */
class AddExtraSmile implements Function<String, String> {
  @Override
  public String apply(String t) {
    return t + " :)";
  }
}

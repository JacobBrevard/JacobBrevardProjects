//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 Dessert Queue
// Files: QueueTests.java, ServingQueue.java, DessertSolvers.java, Guest.java
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
 * This class contains the code for the Queue Tests Class in the Dessert Queue program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Queue Tests class runs a variety of tests to check that the Dessert Queue program is working
 * correctly.
 * 
 *
 */
public class QueueTests {

  /**
   * Main Method to call the various test methods
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1 call and print statement
    System.out.println(
        "TEST 1: servingQueueTestAddRemoveAndPeek(): " + servingQueueTestAddRemoveAndPeek());

    // TEST 2 call and print statement
    System.out.println("TEST 2: servingQueueTestIsEmpty(): " + servingQueueTestIsEmpty());

    // TEST 3 call and print statement
    System.out
        .println("TEST 3: servingQueueTestThrowsException(): " + servingQueueTestThrowsException());
  }

  /**
   * Tests The add, peek, and remove methods. Along with the guest constructor and serving queue
   * constructor.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean servingQueueTestAddRemoveAndPeek() {
    // Creates the new necessary objects for the test
    Guest guest1 = new Guest("no fish");
    Guest guest2 = new Guest();
    Guest guest3 = new Guest("no dairy");
    Guest guest4 = new Guest();
    Guest guest5 = new Guest("no gluten");

    ServingQueue queue1 = new ServingQueue(5);

    // Adds the guests to the queue
    queue1.add(guest1);
    queue1.add(guest2);
    queue1.add(guest3);
    queue1.add(guest4);
    queue1.add(guest5);

    // Checks that the peek function is working properly
    if (queue1.peek() != guest1) {
      return false;
    }

    // Checks that the remove function is working properly
    if (queue1.remove() != guest1) {
      return false;
    }

    // Checks that the peek function is working after the remove method is called
    if (queue1.peek() != guest2) {
      return false;
    }

    return true;
  }

  /**
   * Tests the is Empty method in the serving queue class.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean servingQueueTestIsEmpty() {
    ServingQueue queue1 = new ServingQueue(5);

    // checks to see that the is empty method is working correctly
    if (!queue1.isEmpty()) {
      return false;
    }

    return true;
  }

  /**
   * Tests the methods in the serving queue class. Tests to make sure we are throwing an exception
   * if we try to add too many people to the queue.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean servingQueueTestThrowsException() {
    ServingQueue queue1 = new ServingQueue(5);

    // Creates the new necessary objects for the test
    Guest guest1 = new Guest("no fish");
    Guest guest2 = new Guest();
    Guest guest3 = new Guest("no dairy");
    Guest guest4 = new Guest();
    Guest guest5 = new Guest("no gluten");

    // Adds the guests to the queue
    queue1.add(guest1);
    queue1.add(guest2);
    queue1.add(guest3);
    queue1.add(guest4);
    queue1.add(guest5);

    try {
      queue1.add(guest1);

      // Shouldn't reach here because it should throw an exception
      return false;
    } catch (IllegalStateException e) {
      // Do nothing if we catch the exception here because it is working correctly
    }

    return true;
  }

}

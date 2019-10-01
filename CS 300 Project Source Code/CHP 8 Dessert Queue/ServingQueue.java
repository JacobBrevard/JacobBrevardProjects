//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 Dessert Queue
// Files: ServingQueue.java, DessertSolvers.java, Guest.java, QueueTests.java
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
 * This class contains the code for the Serving Queue Class in the Dessert Queue program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Serving Queue class has a variety of methods to implement the object of a queue and
 * supporting methods.
 * 
 *
 */
public class ServingQueue {
  // Keeps Track of the index that contains the longest waiting guest
  private int longestGuestIndex;

  // Instance field to store the guests
  private Guest[] array;

  // Keeps track of the next index to add
  private int nextGuestIndex;

  // Keeps track of the size of the queue
  private int numberOfPeopleInQueue;

  /**
   * Creates a new array based queue with a capacity of "seatsAtTable" guests. This queue should be
   * initialized to be empty.
   * 
   * @param seatsAtTable the size of the array holding this queue data
   */
  public ServingQueue(int seatsAtTable) {
    // Creates a new array with the passed in size
    array = new Guest[seatsAtTable];
  }

  /**
   * Checks whether there are any guests in this serving queue.
   * 
   * @return true when this queue contains zero guests, and false otherwise.
   */
  public boolean isEmpty() {
    return numberOfPeopleInQueue == 0;
  }

  /**
   * Adds a single new guest to this queue (to be served after the others that were previously added
   * to the queue).
   * 
   * @param newGuest is the guest that is being added to this queue.
   * @throws IllegalStateException when called on a ServingQueue with an array that is full
   */
  public void add(Guest newGuest) {
    // Throws an exception if we try to add a guest to a full queue
    if (numberOfPeopleInQueue == array.length) {
      throw new IllegalStateException();
    }

    // Adds the guest to the correct index
    array[nextGuestIndex] = newGuest;

    // Updates the next guest index in circular indexing fashion
    if (nextGuestIndex == array.length - 1) {
      nextGuestIndex = 0;
    } else {
      nextGuestIndex++;
    }

    // Increments the size by 1
    numberOfPeopleInQueue++;

  }

  /**
   * Accessor for the guest that has been in this queue for the longest. This method does not add or
   * remove any guests.
   * 
   * @return a reference to the guest that has been in this queue the longest.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest peek() {
    // If we call peek on an empty list then we throw an exception
    if (isEmpty()) {
      throw new IllegalStateException();
    }

    // Otherwise we return the guest who has been in the queue the longest
    return array[longestGuestIndex];

  }

  /**
   * Removes the guest that has been in this queue for the longest.
   * 
   * @return a reference to the specific guest that is being removed.
   * @throws IllegalStateException when called on an empty ServingQueue
   */
  public Guest remove() {
    // If we call remove on a empty queue we throw an exception
    if (isEmpty()) {
      throw new IllegalStateException();
    }

    // The remove guest variable temporarily holds the guest who has been in the queue the longests
    Guest removeGuest = array[longestGuestIndex];

    // Set the index where the longest guest was located to null
    array[longestGuestIndex] = null;

    // If the longestIndex value equals the max index in the array then we reset it to be 0
    if (longestGuestIndex == array.length - 1) {
      longestGuestIndex = 0;
    } else {
      longestGuestIndex++;
    }

    // Decrement the size of the list by 1
    numberOfPeopleInQueue--;

    return removeGuest;
  }

  /**
   * The string representation of the guests in this queue should display each of the guests in this
   * queue (using their toString() implementation), and should display them in a comma separated
   * list that is surrounded by a set of square brackets. (this is similar to the formatting of
   * java.util.ArrayList.toString()). The order that these guests are presented in should be (from
   * left to right) the guest that has been in this queue the longest, to the guest that has been in
   * this queue the shortest. When called on an empty ServingQueue, returns "[]".
   * 
   * @return string representation of the ordered guests in this queue
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    // If the list is empty
    if (isEmpty()) {
      return "[]";
    }

    // Variable toReturn that we concatenate to for our toString method
    String toReturn = "[";

    // Count is set to 1 and is used to keep track of the number of guests converted into the string
    int count = 1;

    // Adds the person who has been in the queue the longest
    toReturn += array[longestGuestIndex].toString();

    // Checks if the count variable equals the size variable
    if (count == numberOfPeopleInQueue) {
      return toReturn += "]";
    }

    // Iterates from the longest guest index plus 1 to the end of the queue
    for (int i = longestGuestIndex + 1; i < array.length; i++) {
      toReturn += "," + array[i].toString();

      // Increment count because we added a guest to the string
      count++;

      // If the count ever equals the number of people in the queue then we add the last square
      // bracket and return the string
      if (count == numberOfPeopleInQueue) {
        return toReturn += "]";
      }
    }

    // Loops through the rest of the queue to add the people to the string
    for (int i = 0; i < (numberOfPeopleInQueue - count); i++) {
      toReturn += "," + array[i].toString();
    }

    toReturn += "]";

    return toReturn;
  }
}

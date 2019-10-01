//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P08 Dessert Queue
// Files: DessertSolvers.java, ServingQueue.java, Guest.java, QueueTests.java
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
 * This class contains the code for the Dessert Solvers Class in the Desert Queue program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Dessert Solvers Class has a variety of methods to solve dessert problems.
 * 
 */
public class DessertSolvers {
  /**
   * This method solves dessert problems for skipping a certain number of people.
   * 
   * @param numberOfGuests - The specified number of guests at the table.
   * 
   * @param guestsSkipped - The interval of the number of guests to skip until serving the next.
   * 
   * @return - The last guest served at the table.
   */
  public static Guest firstDessertVariableSkips(int numberOfGuests, int guestsSkipped) {
    // Checks for negative parameters and then throws exceptions accordingly
    if (numberOfGuests < 0) {
      throw new IllegalArgumentException("Error: the number of guests must be positive");
    }
    if (guestsSkipped < 0) {
      throw new IllegalArgumentException("Error: the number of guests skipped must be positive");
    }

    // Creates a new Serving Queue
    ServingQueue queue1 = new ServingQueue(numberOfGuests);

    // Adds the correct number of guests to the table/serving queue
    for (int i = 0; i < numberOfGuests; i++) {
      queue1.add(new Guest());
    }

    // Variable to keep track of the final guest
    Guest finalGuest = null;

    // As long as the queue is not empty then the while loop continues
    while (!queue1.isEmpty()) {

      // The for loop is used to skip the specified number of people
      for (int i = 0; i <= guestsSkipped; i++) {
        // We serve the first person so we remove from the queue
        if (i == 0) {
          finalGuest = queue1.remove();

          // If the queue is empty we know that everyone has been served and we return the last
          // served guest
          if (queue1.isEmpty()) {
            return finalGuest;
          }

          // Otherwise we remove the guest and save it in the temp variable
        } else {
          Guest temp = queue1.remove();

          // Then we re-add the guest to the queue to be served at another time
          queue1.add(temp);
        }

      }

    }
    return finalGuest;
  }

  /**
   * This method solves the dessert issue of serving multiple courses.
   * 
   * @param numberOfGuests - The specified number of guests at the table.
   * 
   * @param coursesServed - The number of courses being served for that meal.
   * 
   * @return the guest that is served dessert first
   */
  public static Guest firstDessertVariableCourses(int numberOfGuests, int coursesServed) {
    // Checks for negative parameters and then throws exceptions accordingly
    if (numberOfGuests < 0) {
      throw new IllegalArgumentException("Error: the number of guests must be positive");
    }
    if (coursesServed < 0) {
      throw new IllegalArgumentException("Error: the number of Courses Served must be positive");
    }

    // Creates a new Serving Queue
    ServingQueue currTable = new ServingQueue(numberOfGuests);

    // Creates a new Serving Queue
    ServingQueue nextTable = new ServingQueue(numberOfGuests);

    // Variable to keep track of the final guest
    Guest finalGuest = null;

    // Adds the correct number of guests to the table/serving queue
    for (int i = 0; i < numberOfGuests; i++) {
      currTable.add(new Guest());
    }

    // If courses served is 1 then we return the first person
    if (coursesServed == 1) {
      return currTable.remove();
    }

    // The for loop that iterates through the number of courses
    for (int i = 1; i < coursesServed; i++) {

      while (!currTable.isEmpty()) {

        // The for loop is used to skip the specified number of people
        for (int j = 0; j <= 1; j++) {

          // We serve the first person so we remove from the queue
          if (j == 0) {
            finalGuest = currTable.remove();

            nextTable.add(finalGuest);

            if (currTable.isEmpty()) {
              j = 2;
            }

            // Otherwise we remove the guest and save it in the temp variable
          } else {
            Guest temp = currTable.remove();

            // Then we re-add the guest to the queue to be served at another time
            currTable.add(temp);
          }
        }

      }

      // Create a new Serving queue
      currTable = new ServingQueue(numberOfGuests);

      // Re orders the list so the last person served is now first in the queue
      for (int f = 0; f < numberOfGuests - 1; f++) {
        nextTable.add(nextTable.remove());
      }

      // Adds the guest to the new Current queue
      for (int k = 0; k < numberOfGuests; k++) {
        currTable.add(nextTable.remove());
      }

      // Creates a new queue for next table serving queue
      nextTable = new ServingQueue(numberOfGuests);

    }

    // Returns the first person in the serving queue of the last table
    return currTable.remove();

  }
}

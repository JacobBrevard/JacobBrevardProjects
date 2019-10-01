//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: Action.java
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
 * This class contains the code for the Action Class in the Escape room program
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

/**
 * The Action Class is used to represent any interactions with the objects within the escape room.
 * Has several supporting methods to illustrate this interaction.
 * 
 */
public class Action {

  // message printed by this action (or null to do nothing)
  private String message;

  // Private thing instance field
  private Thing thing;

  /**
   * This overloaded constructor creates a new action.
   * 
   * @param thing- the passed in thing object
   */
  public Action(Thing thing) {
    this.thing = thing;
  }

  /**
   * This overloaded constructor creates a new action.
   * 
   * @param message - The message of the action.
   * @param thing - the thing object.
   */
  public Action(String message, Thing thing) {
    this.thing = thing;

    this.message = message;

  }



  // initialize this new action
  /**
   * The Action method initializes the new action.
   * 
   */
  public Action(String message) {
    this.message = message;
  }

  // when message is not null, message is printed to System.out
  /**
   * The act method prints out the message to System.out when the message is not null.
   * 
   */
  public void act(ArrayList<Thing> thing) {
    if (this.thing != null) {
      // Activates the thing
      this.thing.activate();

      // Adds the thing to the list
      thing.add(this.thing);

      // Sets the thing to null
      this.thing = null;

    }

    System.out.println(message);
  }

}

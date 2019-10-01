//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: Thing.java
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
 * This class contains the code for the Thing Class in the Escape room program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import statement
import processing.core.PApplet;

/**
 * The Thing Class organizes the capabilities that are common to all interactive things in our game.
 * The methods in this class implement this concept.
 * 
 */
public class Thing {
  // Static variable processing
  private static PApplet processing = null;

  // the constant name identifying this object
  private final String NAME;

  // active means thing is visible and can be interacted with
  private boolean isActive;

  // initializes processing field
  /**
   * The set processing method initializes processing field.
   * 
   * @param processing - the field passed in.
   */
  public static void setProcessing(PApplet processing) {
    // Sets the static field to the passed in parameter
    Thing.processing = processing;

  }

  // accessor method to retrieve this static field
  /**
   * The get processing method is an accessor method to retrieve this static field.
   * 
   * @return the processing field.
   */
  protected static PApplet getProcessing() {
    return processing;
  }

  // initialize name, and set isActive to true
  /**
   * The thing method initializes name, and set isActive to true.
   * 
   * @param name - the name to be initialized.
   */
  public Thing(String name) {
    // Initializes name
    this.NAME = name;

    // Sets is Active to true
    isActive = true;

  }

  // returns true only when contents of name equal NAME
  /**
   * The has Name method checks wheter or not NAME equals the passed in name.
   * 
   * @param name- the name that is passed in to be compared.
   * 
   * @return - true only when contents of name equal NAME.
   */
  public boolean hasName(String name) {
    if (this.NAME.equals(name)) {
      return true;
    }
    return false;

  }

  // returns true only when isActive is true
  /**
   * The is Active method checks if the field isActive is true.
   * 
   * @return - true only when isActive is true.
   */
  public boolean isActive() {
    if (isActive) {
      return true;
    }
    return false;

  }

  // changes isActive to true
  /**
   * The activate method changes isActive to true.
   * 
   */
  public void activate() {
    isActive = true;

  }

  // changes isActive to false
  /**
   * The deactivate method changes isActive to false.
   * 
   */
  public void deactivate() {
    isActive = false;

  }

  // this method returns null
  // subclass types will override this update() method to do more interesting things
  /**
   * Subclass types will override this update() method to do more interesting things
   * 
   * @return - null
   */
  public Action update() {
    return null;
  }

}

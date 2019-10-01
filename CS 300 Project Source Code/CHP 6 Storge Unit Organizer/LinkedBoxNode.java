//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: LinkedBoxNode.java
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
 * This class contains the code for the Linked Box Node Class in the Storage Unit Organizer program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Linked Box Node Class contains the constructor and methods for a node in our storage unit
 * program. This class models a linked node in our application.
 * 
 */
public class LinkedBoxNode {

  // box that represents the data for this Linked node
  private Box box;

  // reference to the next Linked Box Node
  private LinkedBoxNode next;

  // constructors

  // creates a new LinkedBoxNode object with a given
  // box and without referring to any next LinkedBoxNode
  /**
   * Creates a new LinkedBoxNode object with a given box and without referring to any next
   * LinkedBoxNode.
   * 
   * @param box - the box object passed in.
   */
  public LinkedBoxNode(Box box) {
    // Sets the box field to the box passed in
    this.box = box;

  }

  // creates a new LinkedBoxNode
  // object and sets its instance fields box and next to the specified ones
  /**
   * Creates a new LinkedBoxNode object and sets its instance fields box and next to the specified
   * ones.
   * 
   * @param box - the box object passed in.
   * 
   * @param next - the specified next for the linked box node.
   */
  public LinkedBoxNode(Box box, LinkedBoxNode next) {
    // Calls the single argument constructor to set the box of the node
    this(box);

    // Sets the instance field next to the on passed in
    this.next = next;

  }

  // getters and setters methods
  /**
   * Gets and returns the next node.
   * 
   * @return the next node.
   */
  public LinkedBoxNode getNext() {
    return next;
  }

  /**
   * Sets the next node given the next location.
   * 
   * @param next - the location to set.
   */
  public void setNext(LinkedBoxNode next) {
    // Sets the next field to the new next value passed in
    this.next = next;
  }

  /**
   * Gets the box object.
   * 
   * @return the box object.
   */
  public Box getBox() {
    return box;
  }

  /**
   * Sets the box.
   * 
   * @param box - the box object passed into be set.
   */
  public void setBox(Box box) {
    // Sets the box instance field to the one that was passed in
    this.box = box;
  }

}

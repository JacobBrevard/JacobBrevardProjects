//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: DragAndDroppableThing.java
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
 * This class contains the code for the Drag and Droppable Thing Class in the Escape room program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Drag and Droppable Thing Class inherits the capabilities of the Draggable class. Has
 * supporting methods to display Draggable and Droppable things/actions on the screen.
 * 
 */

public class DragAndDroppableThing extends DraggableThing {

  // object over which this object can be dropped
  private VisibleThing target;

  // action that results from dropping this object over target
  private Action action;

  // initialize new object
  /**
   * The Drag and Droppable Things Constructor creates a new object
   * 
   * @param name - Name of image
   * @param x - the x position
   * @param y - the y position
   * @param target - the goal for an object to reach
   * @param action - the action that results from dragging the object
   */
  public DragAndDroppableThing(String name, int x, int y, VisibleThing target, Action action) {
    super(name, x, y);

    this.target = target;

    this.action = action;


  }

  @Override
  // returns action and deactivates objects in response to successful
  // drop
  // When this object is over its target and its target is active:
  // deactivate both this object and the target object, and return action,
  // otherwise return null
  /**
   * When this object is over its target and its target is active: deactivate both this object and
   * the target object, and return action, otherwise return null.
   * 
   * @return the action and deactivates objects in response to successful drop.
   */
  protected Action drop() {
    // Checks if the object is over the target and that the target is active
    if (isOver(target) && target.isActive()) {
      // Deactivates the target
      target.deactivate();

      // Deactivates the drag and droppable thing
      (this).deactivate();

      // returns the action field
      return action;
    }

    return null;
  }

}

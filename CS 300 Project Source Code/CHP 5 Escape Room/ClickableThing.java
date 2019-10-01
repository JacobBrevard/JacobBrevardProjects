//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: ClickableThing.java
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
 * This class contains the code for the Clickable Thing Class in the Escape room program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Clickable Thing Class inherits the capabilities of the VisibleThing class. Has supporting
 * methods to display Clickable things/actions on the screen.
 * 
 */
public class ClickableThing extends VisibleThing {

  // action returned from update when this object is clicked
  private Action action;

  // tracks whether the mouse was pressed during the last update()
  private boolean mouseWasPressed;

  // initializes this new object
  /**
   * The Clickable Thing Constructor creates a new clickable thing object.
   * 
   * @param name - name of piture
   * @param x - x position of the picture
   * @param y - y position of the picture
   * @param action - the action object with a message
   */
  public ClickableThing(String name, int x, int y, Action action) {
    // Calls the super constuctor
    super(name, x, y);

    // Sets the field action to the passed in parameter
    this.action = action;

  }

  @Override
  // calls VisibleThing update, then returns action only when mouse is first clicked
  /**
   * Calls VisibleThing update, then returns action only when mouse is first clicked
   * 
   * @return the action or null
   */
  public Action update() {
    super.update();

    if (Thing.getProcessing().mousePressed) {
      if (!mouseWasPressed) {
        // Sets the mouse to be pressed for the next update
        mouseWasPressed = true;

        // Checks if the mouse is over the object by calling the is Over method
        if (super.isOver(Thing.getProcessing().mouseX, Thing.getProcessing().mouseY)) {
          return action;
        }

      }
    }

    // If the mouse is not currently pressed we set mouse was pressed back to false
    if (!Thing.getProcessing().mousePressed) {
      mouseWasPressed = false;
    }

    return null;
  }
}

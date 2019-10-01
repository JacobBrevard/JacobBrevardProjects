//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: DraggableThing.java
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
 * This class contains the code for the Draggable Thing Class in the Escape room program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Draggabble Thing Class inherits the capabilities of the VisibleThing class. Has supporting
 * methods to display Draggable things/actions on the screen.
 * 
 */

public class DraggableThing extends VisibleThing {

  // similar to use in ClickableThing
  private boolean mouseWasPressed;

  // true when this object is being dragged by the user
  private boolean isDragging;

  // horizontal position of mouse during last update
  private int oldMouseX;

  // vertical position of mouse during last update
  private int oldMouseY;

  // initialize new thing
  /**
   * The Draggable Thing Constructor creates a new Draggable Thing object.
   * 
   * @param filename - Name of the picture file
   * @param x - x position
   * @param y - y position
   */
  public DraggableThing(String filename, int x, int y) {
    super(filename, x, y);
  }

  @Override
  // calls VisibleThing update(), then moves according to mouse drag
  // each time isDragging changes from true to false, the drop() method below will be called once
  // and any action objects returned from that method should then be returned from update()
  public Action update() {
    super.update();

    // Checks if both the mouse is currently pressed and was not pressed in the last update
    // iteration
    if (Thing.getProcessing().mousePressed && !mouseWasPressed) {
      mouseWasPressed = true;

      // Checks if the mouse is over the object
      if (super.isOver(Thing.getProcessing().mouseX, Thing.getProcessing().mouseY)) {
        // Sets is dragging to true
        isDragging = true;

        // Changes the old mouse x and y coordinates
        oldMouseX = Thing.getProcessing().mouseX;

        oldMouseY = Thing.getProcessing().mouseY;
        return null;
      }


    }

    // If the mouse is not currently pressed then we change is dragging to false and mouse was
    // pressed to false
    if (!Thing.getProcessing().mousePressed) {
      isDragging = false;
      mouseWasPressed = false;
    }


    // If is Dragging is true we update the object by calling the move method and updating the old
    // mouse x and y positions
    if (isDragging) {

      super.move(Thing.getProcessing().mouseX - oldMouseX,
          Thing.getProcessing().mouseY - oldMouseY);

      oldMouseX = Thing.getProcessing().mouseX;

      oldMouseY = Thing.getProcessing().mouseY;


    } else {
      // Calls and returns whatever drop returns
      return drop();

    }

    return null;

  }

  // this method returns null
  // subclass types will override this drop() method to do more interesting things
  /**
   * This method returns null. Subclass types will override this drop() method to do more
   * interesting things
   * 
   * @return null
   */
  protected Action drop() {
    return null;
  }
}

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P05 Escape Room
// Files: VisibleThing.java
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
 * This class contains the code for the Visible Thing Class in the Escape room program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */



// Import Statement
import java.io.File;
import processing.core.PImage;


/**
 * The Visible Thing Class inherits the capabilities of the thing class. Has supporting methods to
 * display visible things on the screen.
 * 
 */
public class VisibleThing extends Thing {

  // the graphical representation of this thing
  private PImage image;

  // the horizontal position (in pixels of this thing's left side)
  private int x;

  // the vertical position (in pixels of this thing's top side)
  private int y;

  // initialize this new thing
  /**
   * The Visible Thing Constructor makes a new visible thing object.
   * 
   * @param name - name of picture
   * @param x - position on the left horizontal
   * @param y - position on the top vertical
   */
  public VisibleThing(String name, int x, int y) {
    // Calls the super constuctor (constructor in the thing class)
    super(name);

    this.x = x;

    this.y = y;

    // Creates the new image
    image = Thing.getProcessing().loadImage("images" + File.separator + name + ".png");

  }

  // the image for this visible thing should be loaded from :
  // "images"+File.separator+ name +".png"

  @Override
  // draws image at its position before returning null
  /**
   * Draws image at its position before returning null
   * 
   * @return - null
   */
  public Action update() {
    Thing.getProcessing().image(image, x, y);
    return null;
  }

  // changes x by adding dx to it (and y by dy)
  /**
   * Changes x by adding dx to it (and y by dy)
   * 
   * @param dx - The delta change in the x position
   * @param dy- The delta change in the y position
   */
  public void move(int dx, int dy) {
    // Adds the x delta
    x += dx;

    // Adds the y delta
    y += dy;

  }

  // return true only when point x,y is over image
  /**
   * Checks whether the point x, y is over the image or not.
   * 
   * @param x - The x coordinate
   * @param y - The y coordinate
   * @return true if the coordinate is over the picture and false otherwise
   */
  public boolean isOver(int x, int y) {
    // Gets the height and width
    int width = image.width;
    int height = image.height;

    // Solves for the bottom right coordinate of the picture
    int bottomRightXCoord = this.x + width;
    int bottomRightYCoord = this.y + height;

    // Compares if both the x and y coordinate passed in are on the picture
    if ((x >= this.x && x <= bottomRightXCoord) && (y >= this.y && y <= bottomRightYCoord)) {
      return true;
    }

    return false;

  }

  // return true only when other's image overlaps this one's
  /**
   * Checks if one picture overlaps another.
   * 
   * @param other - The other picture
   * 
   * @return true if it overlaps and false otherwise
   */
  public boolean isOver(VisibleThing other) {
    boolean isOver = false;

    // Gets the height and width of other
    int otherWidth = other.image.width;
    int otherHeight = other.image.height;

    // Top left corner
    int otherXP1 = other.x;
    int otherYP1 = other.y;

    // Top right corner
    int otherXP2 = other.x + otherWidth;
    int otherYP2 = other.y;

    // Bottom left corner
    int otherXP3 = other.x;
    int otherYP3 = other.y + otherHeight;

    // Bottom right corner
    int otherXP4 = other.x + otherWidth;
    int otherYP4 = other.y + otherHeight;

    // Middle
    int otherXP5 = other.x + (otherWidth / 2);
    int otherYP5 = other.y + (otherHeight / 2);

    // Calls the other isOver method with these 5 points to see if the image is overlapping
    if (isOver(otherXP1, otherYP1)) {
      isOver = true;
    }
    if (isOver(otherXP2, otherYP2)) {
      isOver = true;
    }
    if (isOver(otherXP3, otherYP3)) {
      isOver = true;
    }
    if (isOver(otherXP4, otherYP4)) {
      isOver = true;
    }
    if (isOver(otherXP5, otherYP5)) {
      isOver = true;
    }

    return isOver;

  }

}

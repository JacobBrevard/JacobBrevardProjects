//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: Box.java
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
 * This class contains the code for the Box Class in the Storage Unit Organizer program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import Statement
import java.util.Random;

/**
 * The Box Class contains the constructor and methods for a box in our storage unit program.
 * 
 */
public class Box implements Comparable<Box> {

  // generator of random numbers
  private static Random randGen = new Random();

  // color of this box
  private int color;

  // weight of this box in lbs between 1 inclusive and 31 exclusive
  private int weight;

  // Creates a new Box and initializes its instance fields color and weight to
  // random values
  /**
   * The box constructor Creates a new Box and initializes its instance fields color and weight to
   * random values.
   * 
   */
  public Box() {

    // Sets weight to a random number between 1 (inclusive) and 31 (exclusive)
    weight = randGen.nextInt(30) + 1;

    // Sets color to any random integer
    color = randGen.nextInt();

  }

  // Creates a new Box and initializes its instance fields color and weight to the
  // specified values
  // Throws IllegalArgumentException if the provided weight value is out of the
  // range of [1..30]
  /**
   * Creates a new Box and initializes its instance fields color and weight to the specified values
   * Throws IllegalArgumentException if the provided weight value is out of the range of [1..30].
   * 
   * @param color - The integer of the color
   * @param weight - The integer of the weight
   */
  public Box(int color, int weight) {

    // If the weight of the box is not in the correct range we throw an exception
    if (weight < 1 || weight > 30) {
      throw new IllegalArgumentException(
          "Error: Weight of the box must be between 1 and 30 inclusive.");
    }

    // Sets the color field to the passed in value
    this.color = color;

    // Sets the weight field to the passed in value
    this.weight = weight;
  }

  @Override
  // equals method defined in Object class
  /**
   * Equals(Object other) returns true if the specified other object is a Box and this box and other
   * have the same color and same weight. Otherwise, it returns false.
   * 
   * @return true if the specified other object is a Box and this box and other have the same color
   *         and same weight. Otherwise, it returns false.
   */
  public boolean equals(Object other) {
    if (other instanceof Box) {
      // Stores the other box in a temporary box object
      Box temp = (Box) other;

      if (temp.color == color && temp.weight == weight) {
        return true;
      }

      return false;
    }


    return false;
  }

  @Override
  // compareTo method defined in Comparable<Box>
  // interface
  /**
   * CompareTo(Box otherBox) returns a negative integer, a positive integer, or zero as this box is
   * lighter than, heavier than, or has the same weight as the specified otherBox.
   * 
   * @return a negative integer, a positive integer, or zero as this box is lighter than, heavier
   *         than, or has the same weight as the specified otherBox.
   */
  public int compareTo(Box otherBox) {
    int difference = weight - otherBox.weight;

    if (difference < 0) {
      // If this box is lighter than other box return a negative integer
      return -1;
    } else if (difference == 0) {
      // If the weights are the same return 0
      return 0;
    } else {
      // If this box is heavier than the other box return a positive number
      return 1;
    }

  }

  // Getter for the instance field color of this box
  /**
   * Accessor method to get and return the color value as an integer.
   * 
   * @return the integer representing the color.
   */
  public int getColor() {
    return color;
  }

  // Getter for the instance field weight of this box
  /**
   * Getter for the instance field weight of this box.
   * 
   * @return the weight value as an integer.
   */
  public int getWeight() {
    return weight;
  }

}

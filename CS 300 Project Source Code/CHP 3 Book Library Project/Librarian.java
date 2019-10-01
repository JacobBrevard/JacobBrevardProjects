//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P03 Book Library
// Files: Librarian.java
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
 * This class contains the code for the Librarian class within the book library program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Librarian Class contains the constructor and supporting methods for the Librarian object. The
 * class represents the outline for a Librarian and all of its given features.
 */
public class Librarian {
  // instance fields

  // librarian's name
  private String username;

  // librarian password to have access to this application
  private String password;

  /**
   * Creates a new Librarian with a given name and a given password.
   * 
   * @param username name of this librarian.
   * @param password password of this librarian to have access to this application.
   */
  public Librarian(String username, String password) {
    // Sets the name of the librarian
    this.username = username;

    // Sets the password of the librarian
    this.password = password;
  }

  /**
   * Returns the name of this librarian.
   * 
   * @return the name of this librarian.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Checks if a given password equals the password of this librarian.
   * 
   * @param password a given password.
   * 
   * @return true if a given password equals the password of this librarian, false otherwise.
   */
  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }

}

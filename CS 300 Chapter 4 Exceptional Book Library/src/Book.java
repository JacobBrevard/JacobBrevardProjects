//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P04 Exceptional Book Library
// Files: Book.java
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
 * This class contains the code for the Book class in the Exceptional Book Library
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */


/**
 * A class to model a simple book.
 *
 */
public class Book {

  // class/static fields

  // class variable that represents the identifier of the next book
  private static int nextId = 1;

  // Instance fields

  // unique identifier of this book
  private final int ID;

  // name of the author of this book
  private String author;

  // title of this book
  private String title;


  // card bar code of the borrower of this book. When borrowerCardBarCode == null, the book is
  // available (no one has the book)
  private Integer borrowerCardBarCode;

  /**
   * Construct a new Book object and initialize its instance fields
   * 
   * @param title title of this new book
   * @param author author of this new book
   */
  public Book(String title, String author) {
    this.title = title;
    this.author = author;

    // initially, no one has the book
    borrowerCardBarCode = null;
    this.ID = nextId;
    nextId++;
  }

  // Getters and Setters
  /**
   * Return the author of this book
   * 
   * @return the author
   */
  public String getAuthor() {
    return author;
  }

  /**
   * Return the title of this book
   * 
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * Return the the borrower's card bar code of this book if the book has been checked out or null
   * if not
   * 
   * @return the borrowerCardBarCode
   */
  public Integer getBorrowerCardBarCode() {
    return borrowerCardBarCode;
  }


  /**
   * Returns the ID of this Book object
   * 
   * @return the ID of this Book object
   */
  public int getID() {
    return ID;
  }

  /**
   * Records the borrower's card bar code of this book if the book is available. If this book is not
   * available, this method does nothing.
   * 
   * @param borrowerCardBarCode the borrowerCardBarCode to set
   */
  public void borrowBook(Integer borrowerCardBarCode) {
    if (isAvailable())
      this.borrowerCardBarCode = borrowerCardBarCode;
  }

  /**
   * Sets this book to be available. When the borrowerCardBarCode is set to null, no one is
   * borrowing it
   */
  public void returnBook() {
    this.borrowerCardBarCode = null;
  }

  /**
   * Checks if this book is available
   * 
   * @return true if no one is borrowing this book, false otherwise
   */
  public boolean isAvailable() {
    return borrowerCardBarCode == null;
  }
}

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 3 Hash Table
// Files: Book.java, BookHashTableTest.java, BookHashTable.java, BookParser.java
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements a hashTable and all of its operations.
//
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
import java.util.Objects;

/**
 * Book Class to represent the book object
 * 
 * @author Jacob Brevard
 *
 */
public class Book {
  private String isbn13;
  private String authors;
  private String original_publication_year;
  private String title;
  private String language_code;
  private String average_rating;
  private String cover_type;
  private String pages;

  /**
   * Constructor for the book object
   * 
   * @param isbn13
   * @param authors
   * @param original_publication_year
   * @param title
   * @param language_code
   * @param average_rating
   * @param cover_type
   * @param pages
   */
  public Book(String isbn13, String authors, String original_publication_year, String title,
      String language_code, String average_rating, String cover_type, String pages) {
    this.isbn13 = isbn13;
    this.title = title;
    this.authors = authors;
    this.original_publication_year = original_publication_year;
    this.language_code = language_code;
    this.average_rating = average_rating;
    this.cover_type = cover_type;
    this.pages = pages;
  }

  /**
   * Gets the key/ISBN number
   * 
   * @return
   */
  public String getKey() {
    return this.isbn13;
  }

  /**
   * Sets the key/ISBN number
   * 
   * @param isbn13
   */
  public void setKey(String isbn13) {
    this.isbn13 = isbn13;
  }

  @Override
  /**
   * Prints out the books contents with the toString method
   */
  public String toString() {
    return "ISBN13: " + this.isbn13 + "; Book: " + this.title + ", Author: " + this.authors
        + ", Original Publication Year: " + this.original_publication_year + ", Language: "
        + this.language_code + ", Average Rating: " + this.average_rating + ", Cover Type: "
        + this.cover_type + ", Pages: " + this.pages;
  }
}

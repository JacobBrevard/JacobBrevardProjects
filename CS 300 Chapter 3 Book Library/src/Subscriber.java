//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P03 Book Library
// Files: Subscriber.java
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
 * This class contains the code for the Subscriber class within the book library program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

import java.util.ArrayList;

/**
 * The Subscriber Class contains the constructor and supporting methods for the Subscriber object.
 * The class represents the outline for a Subscriber and all of its given features.
 */
public class Subscriber {
  // static fields

  // maximum number of books to be checked out one subscriber
  private final static int MAX_BOOKS_CHECKED_OUT = 10;

  // class variable that represents the card bar code of the next subscriber to be
  // created
  private static int nextCardBarCode = 2019000001;

  // Instance fields

  // 4-digits Personal Identification Number to verify the identity of this
  // subscriber
  private int pin;

  // card bar code of this subscriber
  private final Integer CARD_BAR_CODE;

  // name of this subscriber
  private String name;

  // address of this subscriber
  private String address;

  // phone number of this subscriber
  private String phoneNumber;

  // list of books checked out by this subscriber and not yet returned. A
  // subscriber can have at most 10 checked out books
  private ArrayList<Book> booksCheckedOut;

  // list of the books returned by this subscriber
  private ArrayList<Book> booksReturned;

  /**
   * Construct a new Subscriber object and initialize its instance fields.
   *
   * @param name name of subscriber.
   * @param pin pin of subscriber.
   * @param address address of subscriber.
   * @param phoneNumber phone number of subscriber.
   */
  public Subscriber(String name, int pin, String address, String phoneNumber) {
    // Sets the name of the subscriber
    this.name = name;

    // Sets the pin of the subscriber
    this.pin = pin;

    // Sets the address of the subscriber
    this.address = address;

    // Sets the phone number of the subscriber
    this.phoneNumber = phoneNumber;

    // Sets the arrayList to the subscriber to keep track of books checked out
    booksCheckedOut = new ArrayList<Book>(MAX_BOOKS_CHECKED_OUT);

    // Sets the arrayList to the subscriber to keep track of books returned
    booksReturned = new ArrayList<Book>(MAX_BOOKS_CHECKED_OUT);

    // Sets the unique card bar code for the subscriber
    CARD_BAR_CODE = nextCardBarCode;

    // Increments the next bar code by 1 so the next user has a new code
    nextCardBarCode++;

  }

  /**
   * Returns the address for the subscriber.
   * 
   * @return the address of the subscriber
   */
  public String getAdress() {
    return address;
  }

  /**
   * Changes the address of the subscriber.
   * 
   * @param address address of the subscriber.
   */
  public void setAddress(String address) {
    this.address = address;
  }

  /**
   * Returns the phone number of the subscriber.
   * 
   * @return the subscriber's phone number.
   */
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
   * Sets the phone number of the subscriber.
   * 
   * @param phoneNumber The phone number of the subscriber.
   */
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Returns the pin of the subscriber.
   * 
   * @return the pin of the subscriber.
   */
  public int getPin() {
    return pin;
  }

  /**
   * Returns the subscribers card bar code.
   * 
   * @return card bar code.
   */
  public Integer getCARD_BAR_CODE() {
    return CARD_BAR_CODE;
  }

  /**
   * Returns the name of the subscriber.
   * 
   * @return name of subscriber.
   */
  public String getName() {
    return name;
  }

  /**
   * Checks out an available book. The checkout operation fails if the maximum number of checked out
   * books by this subscriber is already reached.
   * 
   * @param book the book object.
   */
  public void checkoutBook(Book book) {
    // Checks the number of books the subscriber has checked out and checks if it is
    // greater than or equal to the limit

    // Boolean variable to hold whether the method fails or not.
    boolean failed = false;

    if (booksCheckedOut.size() >= MAX_BOOKS_CHECKED_OUT) {
      System.out.println(
          "Checkout Failed: You cannot check out more than " + MAX_BOOKS_CHECKED_OUT + "books.");
      failed = true;
    }

    // Checks if they already have the book checked out
    if (isBookInBooksCheckedOut(book)) {
      System.out.println("You have already checked out " + book.getTitle() + " book.");
      failed = true;
    }

    // Checks if the book is available
    if (!book.isAvailable()) {
      System.out.print("Sorry, " + book.getTitle() + " is not available.");
      failed = true;
    }

    if (!failed) {
      // Adds the book to the checked out list for the subscriber
      booksCheckedOut.add(book);

      // Calls the borrowBook method tying the card bar code to the book
      book.borrowBook(CARD_BAR_CODE);
    }

  }

  /**
   * This method Returns a library book.
   * 
   * @param book the book object.
   */
  public void returnBook(Book book) {
    // Removes the book from the checked out list
    booksCheckedOut.remove(book);

    // Adds the book to the returned list
    booksReturned.add(book);

    // Sets the book to returned
    book.returnBook();
  }

  /**
   * Checks if this subscriber booksCheckedOut list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksCheckedOut contains book, false otherwise
   */
  public boolean isBookInBooksCheckedOut(Book book) {
    return booksCheckedOut.contains(book);
  }

  /**
   * Checks if this subscriber booksReturned list contains a given book
   * 
   * @param book book to check if it is within this subscriber booksCheckedOut list
   * @return true if booksReturned contains book, false otherwise
   */
  public boolean isBookInBooksReturned(Book book) {
    return booksReturned.contains(book);
  }

  /**
   * Displays the list of the books checked out and not yet returned
   */
  public void displayBooksCheckedOut() {
    if (booksCheckedOut.isEmpty()) // empty list
      System.out.println("No books checked out by this subscriber");
    else
      // Traverse the list of books checked out by this subscriber and display its
      // content
      for (int i = 0; i < booksCheckedOut.size(); i++) {
        System.out.print("Book ID: " + booksCheckedOut.get(i).getID() + " ");
        System.out.print("Title: " + booksCheckedOut.get(i).getTitle() + " ");
        System.out.println("Author: " + booksCheckedOut.get(i).getAuthor());
      }
  }

  /**
   * Displays the history of the returned books by this subscriber
   */
  public void displayHistoryBooksReturned() {
    if (booksReturned.isEmpty()) // empty list
      System.out.println("No books returned by this subscriber");
    else
      // Traverse the list of borrowed books already returned by this subscriber and
      // display its content
      for (int i = 0; i < booksReturned.size(); i++) {
        System.out.print("Book ID: " + booksReturned.get(i).getID() + " ");
        System.out.print("Title: " + booksReturned.get(i).getTitle() + " ");
        System.out.println("Author: " + booksReturned.get(i).getAuthor());
      }
  }

  /**
   * Displays this subscriber's personal information
   */
  public void displayPersonalInfo() {
    System.out.println("Personal information of the subscriber: " + CARD_BAR_CODE);
    System.out.println("  Name: " + name);
    System.out.println("  Address: " + address);
    System.out.println("  Phone number: " + phoneNumber);
  }

}

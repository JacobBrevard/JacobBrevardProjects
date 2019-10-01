//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P03 Book Library
// Files: BookLibraryTests.java
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
 * This class contains the testing methods for the Book Library program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Book Library Tests class contains the test bench for the BookLibrary Program.
 */
public class BookLibraryTests {
  /**
   * Tests the book constructor and supporting methods in the book class.
   * 
   * @return true if the tests pass and false otherwise
   */
  public static boolean testBookConstructor() {
    // Creates book 1
    Book number1 = new Book("Green Eggs and Ham", "Dr. Seuss");

    // Creates book 2
    Book number2 = new Book("Harry Potter", "J.K. Rowling");

    // Checks to see if the correct title is assigned to the book
    if (number1.getTitle() != "Green Eggs and Ham") {
      return false;
    }

    // Checks to see if the correct author is assigned to the book
    if (number1.getAuthor() != "Dr. Seuss") {
      return false;
    }

    // Checks to see if the correct ID is assigned to the book
    if (number1.getID() != 1) {
      return false;
    }

    // Checks to see if the borrower code is assigned to null making the book
    // available.
    if (number1.getBorrowerCardBarCode() != null) {
      return false;
    }

    // Checks to see if the correct ID is assigned to the new book
    if (number2.getID() != 2) {
      return false;
    }

    // If all tests pass then return true
    return true;
  }

  /**
   * Tests the book getter methods in the book class.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testBookGetters() {
    // Creates book
    Book number3 = new Book("Star Wars", "Bob");

    // Checks to see if the correct title is returned
    if (number3.getTitle() != "Star Wars") {
      return false;
    }

    // Checks to see if the correct author is returned
    if (number3.getAuthor() != "Bob") {
      return false;
    }

    // Checks to see if the correct ID is returned
    if (number3.getID() != 3) {
      return false;
    }

    // Checks to see if the correct borrower code is returned
    if (number3.getBorrowerCardBarCode() != null) {
      return false;
    }

    return true;
  }

  /**
   * Tests the return book method in the book class.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testBookReturnBook() {
    // Creates book
    Book number3 = new Book("Fault in our stars", "John Green");

    // Calls the borow book method with the persons id
    number3.borrowBook(5);

    // Checks whether the correct id was assigned to the book
    if (!number3.getBorrowerCardBarCode().equals(5)) {
      return false;
    }

    // Calls the return book method
    number3.returnBook();

    // Checks if the borrower code is null meaning it is available
    if (number3.getBorrowerCardBarCode() != null) {
      return false;
    }

    // If everything passes then return true
    return true;
  }

  /**
   * This unit test method checks whether the checkoutBook() method defined within the Subscriber
   * class works correctly.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testSubscriberCheckoutBook() {
    // Creates a new Book
    Book number4 = new Book("Divergent", "Jane Doe");

    // Creates a new Subscriber
    Subscriber jake = new Subscriber("Jake", 1234, "Cloverfield Hill Way", "6082334321");

    // Calls the checkout book method
    jake.checkoutBook(number4);

    // Checks if the book is in the checked out list for the subscriber
    if (!jake.isBookInBooksCheckedOut(number4)) {
      return false;
    }

    // Checks if the borrower code on the book matches the subscribers card
    if (!number4.getBorrowerCardBarCode().equals(jake.getCARD_BAR_CODE())) {
      return false;
    }

    // If all tests pass then the method returns true
    return true;
  }

  /**
   * This unit test method checks whether the Library class functions correctly.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testLibrary() {
    Library library = new Library("K Street", "John", "John123");

    if (!library.getLibrarian().getUsername().equals("John")) {
      return false;
    }

    if (!library.getLibrarian().checkPassword("John123")) {
      return false;
    }

    return true;
  }

  /**
   * Tests the find book by author method in the Library class.
   * 
   * @return true if the tests pass and false otherwise.
   */
  public static boolean testLibraryFindBookByAuthor() {
    Library library = new Library("K Street", "John", "John123");

    library.addBook("Job", "Hunter");
    library.addBook("Fun", "Person");
    library.addBook("Lots", "Fun");
    library.addBook("Jump", "Around");
    library.addBook("Go", "Badgers");
    library.addBook("Have", "Fun");

    if (library.findBookByAuthor("Around").size() != 1) {
      return false;
    }

    return true;
  }

  /**
   * Main method to call and run the book Library testing methods.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1 call and print statement
    System.out.println("TEST 1: testBookConstructor(): " + testBookConstructor());

    // TEST 2 call and print statement
    System.out.println("TEST 2: testBookGetters(): " + testBookGetters());

    // TEST 3 call and print statement
    System.out.println("TEST 3: testBookReturnBook(): " + testBookReturnBook());

    // TEST 4 call and print statement
    System.out.println("TEST 4: testSubscriberCheckoutBook(): " + testSubscriberCheckoutBook());

    // TEST 5 call and print statement
    System.out.println("TEST 5: testLibrary(): " + testLibrary());

    // TEST 6 call and print statement
    System.out.println("TEST 6: testLibraryFindBookByAuthor(): " + testLibraryFindBookByAuthor());
  }

}

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P03 Book Library
// Files: Library.java
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

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Library Class contains the constructor and supporting methods for the Library object. The
 * class represents the outline for a Library and all of its given features.
 */
public class Library {
  // instance fields

  // Street address of this library
  private String address;

  // this library's librarian. This library must have only ONE librarian
  private Librarian librarian;

  // list of the books in this library
  private ArrayList<Book> books;

  // list of this library's subscribers
  private ArrayList<Subscriber> subscribers;

  /**
   * Constructor for the Library object.
   * 
   * @param address Address of this Library.
   * @param librarianUsername username of the librarian of this book library.
   * @param librarianLogin login of the librarian of this book library.
   */
  public Library(String address, String librarianUsername, String librarianLogin) {
    // Sets the address of the library
    this.address = address;

    // Creates the new librarian and assigns them to the library
    librarian = new Librarian(librarianUsername, librarianLogin);

    // Initializes the books arrayList to hold the books in the library
    books = new ArrayList<Book>();

    // Initializes the subscribers arrayList to hold the subscribers in the library
    subscribers = new ArrayList<Subscriber>();
  }

  /**
   * Returns the librarian of this library.
   * 
   * @return the librarian.
   */
  public Librarian getLibrarian() {
    return librarian;
  }

  /**
   * Returns the address of this library.
   * 
   * @return the address.
   */
  public String getAddress() {
    return address;
  }

  /**
   * Returns a Book given a book identifier if found, and null otherwise. If the book is not found,
   * this method displays the following message: "Error: this book identifier didn't match any of
   * our books identifiers."
   * 
   * @param bookId identifier of the book to find.
   * 
   * @return reference to the Book if found and null otherwise.
   */
  public Book findBook(int bookId) {
    // Iterates through the book list checking the book's ID
    for (Book book : books) {
      if (book.getID() == bookId) {
        return book;
      }
    }

    // Displays the error message
    System.out.println("Error: this book identifier didn't match any of our books identifiers.");

    return null;
  }

  /**
   * Returns the list of books having a given title in this library. The comparison used by this
   * method is case insensitive.
   * 
   * @param Title title of the book(s) to find.
   * 
   * @return ArrayList of the books having a given title in this library (0 or more books can be
   *         found).
   */
  public ArrayList<Book> findBookByTitle(String Title) {
    // Creates the arrayList to store the matched books
    ArrayList<Book> matched = new ArrayList<Book>();

    // Puts the title to lowerCase
    Title = Title.toLowerCase();

    // Iterates through checking whether the title of the book is the passed in
    // title
    for (Book book : books) {
      if (book.getTitle().toLowerCase().equals(Title)) {
        matched.add(book);
      }
    }

    // Returns the arrayList of all the matched titles
    return matched;
  }

  /**
   * Returns the list of books having a given author. The comparison used by this method is case
   * insensitive.
   * 
   * @param Author author of the book(s) to find.
   * 
   * @return ArrayList of the books having a given author (0 or more books can be found).
   */
  public ArrayList<Book> findBookByAuthor(String Author) {
    // Creates the arrayList to store the matched books
    ArrayList<Book> matched = new ArrayList<Book>();

    // Puts the author to lowerCase
    Author = Author.toLowerCase();

    // Iterates through checking whether the author matches the books author
    for (Book book : books) {
      if (book.getAuthor().toLowerCase().equals(Author)) {
        matched.add(book);
      }
    }

    // Returns the arrayList of all the matched titles
    return matched;
  }

  /**
   * Adds a new book to the library (to the books list). This method displays the following message:
   * "Book with Title " + title + " is successfully added to the library."
   * 
   * @param title title of the new book.
   * @param author author of the new book.
   */
  public void addBook(String title, String author) {
    // Constructs a new book and adds it to the library
    books.add(new Book(title, author));

    // Prints out the display message that the book was successfully added
    System.out.println("Book with Title " + title + " is successfully added to the library.");
  }

  /**
   * Removes a book given its identifier from the library (from books list).
   * 
   * @param bookId identifier of the book to remove.
   * 
   * @return a reference to the removed book, and null if the book is not found in this library or
   *         if it is not available.
   */
  public Book removeBook(int bookId) {
    // Iterates through checking book ID and if found checks if the book is availabe
    // and then removes it from the library books list
    for (Book book : books) {
      if (book.getID() == bookId) {
        if (book.isAvailable()) {
          books.remove(book);
          return book;
        }
      }
    }

    return null;
  }

  /**
   * Adds a new subscriber to this library (to subscribers list). This method displays the following
   * message: "Library card with bar code " + card bar code + " is successfully issued to the new
   * subscriber " + name + "."
   * 
   * @param name name of the new subscriber.
   * 
   * @param pin 4-digit personal identifier number of the new subscriber.
   * 
   * @param address address of the new subscriber.
   * 
   * @param phoneNumber phone number of the new subscriber.
   */
  public void addSubscriber(String name, int pin, String address, String phoneNumber) {
    // Creates a new Subscriber and adds them to the library list
    subscribers.add(new Subscriber(name, pin, address, phoneNumber));

    // Stores the newly added subscriber in the person variable
    Subscriber person = subscribers.get(subscribers.size() - 1);

    // Prints out the success message along with the persons card bar code
    System.out.println("Library card with bar code " + person.getCARD_BAR_CODE()
        + " is successfully issued to the new subscriber " + name + ".");
  }

  /**
   * Finds a subscriber given its cardBarCode. This method displayed the following message: "Error:
   * this card bar code didn't match any of our records." and returns null if the provided
   * cardBarCode did not match with any of the subscribers' card bar codes.
   * 
   * @param cardBardCode of the subscriber to find.
   * 
   * @return a reference to the subscriber if found, otherwise null.
   */
  public Subscriber findSubscriber(int cardBarCode) {
    // Iterates through the list searching for the specific card bar code
    for (Subscriber subscriber : subscribers) {
      if (subscriber.getCARD_BAR_CODE() == cardBarCode) {
        return subscriber;
      }
    }

    // Prints out an error message if none of the card bar code's mathc
    System.out.println("Error: this card bar code didn't match any of our records.");

    // If the subscriber is not found then we return null
    return null;
  }

  /**
   * Displays a list of books
   * 
   * @param books ArrayList of books
   */
  public static void displayBooks(ArrayList<Book> books) {
    // Traverse the list of books and display book id, title, author, and
    // availability of each book
    for (int i = 0; i < books.size(); i++) {
      System.out.print("<Book ID>: " + books.get(i).getID() + " ");
      System.out.print("<Title>: " + books.get(i).getTitle() + " ");
      System.out.print("<Author>: " + books.get(i).getAuthor() + " ");
      System.out.println("<Is Available>: " + books.get(i).isAvailable());
    }
  }

  /**
   * Reads and processes the librarian commands according to the librarian menu
   * 
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessLibrarianCommand(Scanner scanner) {
    // Prints out the librarian menu
    displayLibrarianMenu();

    System.out.print("ENTER COMMAND: ");

    // Takes in user input
    String userInput = scanner.nextLine();

    // Trims the user input
    userInput = userInput.trim();

    // Splits up the user input into an array
    String[] splitUpUserInput = userInput.split(" ");

    // Loop for library interaction
    while (userInput.charAt(0) != '9') {

      // Adds a book to the library
      if (userInput.charAt(0) == '1') {
        addBook(splitUpUserInput[1], splitUpUserInput[2]);
      }

      // Adds a Subscriber
      if (userInput.charAt(0) == '2') {
        // Converts the String pin to an int
        int pin = Integer.parseInt(splitUpUserInput[2]);

        addSubscriber(splitUpUserInput[1], pin, splitUpUserInput[3], splitUpUserInput[4]);
      }

      // Checks out a book for a subscriber
      if (userInput.charAt(0) == '3') {
        // Converts the Strings to int
        int barCode = Integer.parseInt(splitUpUserInput[1]);

        int bookID = Integer.parseInt(splitUpUserInput[2]);

        // Finds the book
        Book book = findBook(bookID);

        // Finds the subscriber
        Subscriber subscriber = findSubscriber(barCode);

        // Checks out the book
        subscriber.checkoutBook(book);
      }

      // Returns a book for the subscriber
      if (userInput.charAt(0) == '4') {
        // Converts the Strings to int
        int barCode = Integer.parseInt(splitUpUserInput[1]);

        int bookID = Integer.parseInt(splitUpUserInput[2]);

        // Finds the book
        Book book = findBook(bookID);

        // Finds the subscriber
        Subscriber subscriber = findSubscriber(barCode);

        // Returns the book
        subscriber.returnBook(book);

      }

      // Displays the subscriber's personal information
      if (userInput.charAt(0) == '5') {
        int barCode = Integer.parseInt(splitUpUserInput[1]);

        // Finds subscriber
        Subscriber subscriber = findSubscriber(barCode);

        subscriber.displayPersonalInfo();

      }
      // Displays the books checked out
      if (userInput.charAt(0) == '6') {
        int barCode = Integer.parseInt(splitUpUserInput[1]);

        Subscriber subscriber = findSubscriber(barCode);

        subscriber.displayBooksCheckedOut();

      }
      // Displays the books in the library
      if (userInput.charAt(0) == '7') {
        displayBooks(books);

      }
      // Removes a book from the library
      if (userInput.charAt(0) == '8') {
        int bookID = Integer.parseInt(splitUpUserInput[1]);

        removeBook(bookID);

      }

      // Prints menu
      displayLibrarianMenu();

      System.out.print("ENTER COMMAND: ");

      // Takes in user input
      userInput = scanner.nextLine();

      // Trims the input
      userInput = userInput.trim();

      // Splits up the user input into an array
      splitUpUserInput = userInput.split(" ");
    }

  }

  /**
   * Reads and processes the subscriber commands according to the subscriber menu
   * 
   * @param subscriber Current logged in subscriber
   * @param scanner Scanner object used to read the librarian command lines
   */
  private void readProcessSubscriberCommand(Subscriber subscriber, Scanner scanner) {
    // Prints the subscriber menu
    displaySubscriberMenu();

    System.out.print("ENTER COMMAND: ");

    // Takes in user input
    String userInput = scanner.nextLine();

    // Trims the input
    userInput = userInput.trim();

    // split user command
    String[] splitUpUserInput = userInput.split(" ");

    // Loop for the subscriber interaction
    while (userInput.charAt(0) != '9') {

      // Checks out a book
      if (userInput.charAt(0) == '1') {
        int bookID = Integer.parseInt(splitUpUserInput[1]);

        Book book = findBook(bookID);

        subscriber.checkoutBook(book);

      }

      // Returns a book
      if (userInput.charAt(0) == '2') {
        int bookID = Integer.parseInt(splitUpUserInput[1]);

        Book book = findBook(bookID);

        subscriber.returnBook(book);

      }

      // Prints out all the books with a given title
      if (userInput.charAt(0) == '3') {
        displayBooks(findBookByTitle(splitUpUserInput[1]));

      }

      // Prints out all books with a given author
      if (userInput.charAt(0) == '4') {
        displayBooks(findBookByAuthor(splitUpUserInput[1]));

      }

      // Prints out all books checked out by a subscriber
      if (userInput.charAt(0) == '5') {
        subscriber.displayBooksCheckedOut();

      }

      // Prints out all books returned by a subscriber
      if (userInput.charAt(0) == '6') {
        subscriber.displayHistoryBooksReturned();

      }

      // Changes the address of a subscriber
      if (userInput.charAt(0) == '7') {
        subscriber.setAddress(splitUpUserInput[1]);

      }

      // Changes the phone number of a subscriber
      if (userInput.charAt(0) == '8') {
        subscriber.setPhoneNumber(splitUpUserInput[1]);

      }

      // Prints out the menu
      displaySubscriberMenu();

      System.out.print("ENTER COMMAND: ");

      // Takes in user input
      userInput = scanner.nextLine();

      // Trims input
      userInput = userInput.trim();

      // Splits up user input
      splitUpUserInput = userInput.split(" ");
    }

  }

  /**
   * Reads and processes the user commands with respect to the menu of this application
   * 
   * @param scanner Scanner object used to read the user command lines
   */
  public void readProcessUserCommand(Scanner scanner) {
    final String promptCommandLine = "ENTER COMMAND: ";

    // display the library management system main menu
    displayMainMenu();

    System.out.print(promptCommandLine);

    // read user command line
    String command = scanner.nextLine();

    // split user command
    String[] commands = command.trim().split(" ");

    // '3': Exit the application
    while (commands[0].trim().charAt(0) != '3') {

      switch (commands[0].trim().charAt(0)) {

        case '1': // login as librarian commands[1]: password

          if (this.librarian.checkPassword(commands[1])) {

            // read and process librarian commands

            readProcessLibrarianCommand(scanner);

          } else { // error password
            System.out.println("Error: Password incorrect!");
          }
          break;
        case '2': // login as subscriber commands[1]: card bar code

          Subscriber subscriber = this.findSubscriber(Integer.parseInt(commands[1]));

          if (subscriber != null) {
            // correct PIN
            if (subscriber.getPin() == Integer.parseInt(commands[2]))
              // read and process subscriber commands
              readProcessSubscriberCommand(subscriber, scanner);
            else
              System.out.println("Error: Incorrect PIN.");
          }
          break;
      }
      // read and split next user command line
      displayMainMenu(); // display the library management system main menu
      System.out.print(promptCommandLine);
      command = scanner.nextLine(); // read user command line
      commands = command.trim().split(" "); // split user command line
    }
  }

  /**
   * Displays the main menu for this book library application
   */
  private static void displayMainMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("     Welcome to our Book Library Management System");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <password>] Login as a librarian");
    System.out.println("[2 <card bar code> <4-digits pin>] Login as a Subscriber");
    System.out.println("[3] Exit"); // Exit the application
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for a Subscriber
   */
  private static void displaySubscriberMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Subscriber's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <book ID>] Check out a book");
    System.out.println("[2 <book ID>] Return a book");
    System.out.println("[3 <title>] Search a Book by title");
    System.out.println("[4 <author>] Search a Book by author");
    System.out.println("[5] Print list of books checked out");
    System.out.println("[6] Print history of returned books");
    System.out.println("[7 <address>] Update address");
    System.out.println("[8 <phone number>] Update phone number");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Displays the menu for the Librarian
   */
  private static void displayLibrarianMenu() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("    Welcome to Librarian's Space");
    System.out.println("--------------------------------------------------------");
    System.out.println("Enter one of the following options:");
    System.out.println("[1 <title> <author>] Add new Book");
    System.out.println("[2 <name> <pin> <address> <phone number>] Add new subscriber");
    System.out.println("[3 <card bar code> <book ID>] Check out a Book");
    System.out.println("[4 <card bar code> <book ID>] Return a Book for a subscriber");
    System.out.println("[5 <card bar code>] Display Personal Info of a Subscriber");
    System.out.println("[6 <card bar code>] Display Books Checked out by a Subscriber");
    System.out.println("[7] Display All Books");
    System.out.println("[8 <book ID>] Remove a Book");
    System.out.println("[9] Logout");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Display the Application GoodBye and logout message.
   */
  private static void displayGoodByeLogoutMessage() {
    System.out.println("\n--------------------------------------------------------");
    System.out.println("       Thanks for Using our Book Library App!!!!");
    System.out.println("--------------------------------------------------------");
  }

  /**
   * Main method that represents the driver for this application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // create a scanner object to read user inputs
    Scanner scanner = new Scanner(System.in);

    // create a new library object
    Library madisonLibrary = new Library("Madison, WI", "april", "abc");

    // read and process user command lines
    madisonLibrary.readProcessUserCommand(scanner);

    // display good bye message
    displayGoodByeLogoutMessage();

    // close this scanner
    scanner.close();
  }

}

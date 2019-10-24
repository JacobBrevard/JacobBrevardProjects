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
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

// learn how Scanner instances that are connected to the keyboard work.
/**
 * Parses the books.csv file and creates book objects out of them and then stores them all in the
 * bookTable for our test class to utilize.
 * 
 * @author Jacob Brevard
 *
 */
public class BookParser {

  // @param booksfilename - a csv file with book database information

  // Parse the csv file into a list of book object
  /**
   * Parses the books.csv file to get all the books
   * 
   * @param booksfilename
   * @return
   * @throws FileNotFoundException
   */
  public static ArrayList<Book> parse(String booksfilename) throws FileNotFoundException {
    ArrayList<Book> bookList = new ArrayList<Book>();
    try {
      Scanner scanner = new Scanner(new File(booksfilename));

      Scanner valueScanner = null;
      int idx = 0;

      // try different methods of the Scanner STDIN
      while (scanner.hasNext()) {
        // temporary or not

        valueScanner = new Scanner(scanner.nextLine());
        valueScanner.useDelimiter(",");
        ArrayList<String> book = new ArrayList<String>();
        while (valueScanner.hasNext()) {
          String data = valueScanner.next();
          book.add(data);
        }

        Book bookobj = new Book(book.get(0), book.get(1), book.get(2), book.get(3), book.get(4),
            book.get(5), book.get(6), book.get(7));

        bookList.add(bookobj);

      }
      bookList.remove(0);

      scanner.close();

    } catch (FileNotFoundException e) {
    }
    return bookList;
  }
}

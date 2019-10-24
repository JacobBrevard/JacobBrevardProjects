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

import org.junit.After;
import java.io.FileNotFoundException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Random;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test HashTable class implementation to ensure that required functionality works for all cases.
 */
public class BookHashTableTest {

  // Default name of books data file
  public static final String BOOKS = "books.csv";

  // Empty hash tables that can be used by tests
  static BookHashTable bookObject;
  static ArrayList<Book> bookTable;

  static final int INIT_CAPACITY = 2;
  static final double LOAD_FACTOR = 0.49;

  static Random RNG = new Random(0); // seeded to make results repeatable (deterministic)

  /** Create a large array of keys and matching values for use in any test */
  @BeforeAll
  public static void beforeClass() throws Exception {
    bookTable = BookParser.parse(BOOKS);
  }

  /** Initialize empty hash table to be used in each test */
  @BeforeEach
  public void setUp() throws Exception {
    // TODO: change HashTable for final solution
    bookObject = new BookHashTable(INIT_CAPACITY, LOAD_FACTOR);
  }

  /** Not much to do, just make sure that variables are reset */
  @AfterEach
  public void tearDown() throws Exception {
    bookObject = null;
  }

  /**
   * Inserts many books into the list
   * 
   * @param bookTable
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   */
  private void insertMany(ArrayList<Book> bookTable)
      throws IllegalNullKeyException, DuplicateKeyException {
    for (int i = 0; i < bookTable.size(); i++) {
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_collision_scheme() {
    if (bookObject == null)
      fail("Gg");
    int scheme = bookObject.getCollisionResolutionScheme();
    if (scheme < 1 || scheme > 9)
      fail("collision resolution must be indicated with 1-9");
  }


  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is empty upon initialization
   */
  @Test
  public void test000_IsEmpty() {
    // "size with 0 entries:"
    assertEquals(0, bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Tests that a HashTable is not empty after adding one (key,book)
   * pair
   * 
   * @throws DuplicateKeyException
   * @throws IllegalNullKeyException
   */
  @Test
  public void test001_IsNotEmpty() throws IllegalNullKeyException, DuplicateKeyException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    String expected = "" + 1;
    // "size with one entry:"
    assertEquals(expected, "" + bookObject.numKeys());
  }

  /**
   * IMPLEMENTED AS EXAMPLE FOR YOU Test if the hash table will be resized after adding two
   * (key,book) pairs given the load factor is 0.49 and initial capacity to be 2.
   */
  @Test
  public void test002_Resize() throws IllegalNullKeyException, DuplicateKeyException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    int cap1 = bookObject.getCapacity();
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    int cap2 = bookObject.getCapacity();

    // "size with one entry:"
    assertTrue(cap2 > cap1 & cap1 == 2);
  }

  /**
   * Test the insert method and get method to make sure the elements added in the hashTable are
   * actually in the table.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test003_insert_and_get()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));

    // Checks the key is in the table
    if (!bookObject.get(bookTable.get(2).getKey()).equals(bookTable.get(2))) {
      fail("Failed to get proper book for a key");
    }

    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));

    // Checks the key is in the table
    if (!bookObject.get(bookTable.get(3).getKey()).equals(bookTable.get(3))) {
      fail("Failed to get proper book for a key");
    }

    // Checks the key is in the table
    if (!bookObject.get(bookTable.get(5).getKey()).equals(bookTable.get(5))) {
      fail("Failed to get proper book for a key");
    }

    // Checks the key is in the table
    if (!bookObject.get(bookTable.get(0).getKey()).equals(bookTable.get(0))) {
      fail("Failed to get proper book for a key");
    }

    // Checks the key is in the table
    if (!bookObject.get(bookTable.get(1).getKey()).equals(bookTable.get(1))) {
      fail("Failed to get proper book for a key");
    }
  }

  /**
   * This test the insert, remove, and get operations
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test004_insert_and_remove_and_get()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

    bookObject.remove(bookTable.get(1).getKey());

    // Checks the key is in the table
    try {
      bookObject.get(bookTable.get(1).getKey());
      fail("Should have throw a key not found exception");
    } catch (KeyNotFoundException e) {
      // The get method should throw an exception
    }

    bookObject.insert(bookTable.get(33).getKey(), bookTable.get(33));

    bookObject.insert(bookTable.get(22).getKey(), bookTable.get(22));

    bookObject.insert(bookTable.get(23).getKey(), bookTable.get(23));

    bookObject.remove(bookTable.get(0).getKey());

    try {
      bookObject.get(bookTable.get(0).getKey());
      fail("Should have throw a key not found exception");
    } catch (KeyNotFoundException e) {
      // The get method should throw an exception
    }

    if (bookObject.remove(bookTable.get(0).getKey()) != false) {
      fail(
          "The remove method should have returned false when trying to remove a book not in the list.");
    }
  }

  /**
   * This test the insert, remove, and get operations. Inserts 10 elements, removes 5 and then gets
   * the other 5.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test005_insert10_and_remove5_and_get5()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
    bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    bookObject.insert(bookTable.get(7).getKey(), bookTable.get(7));
    bookObject.insert(bookTable.get(8).getKey(), bookTable.get(8));
    bookObject.insert(bookTable.get(9).getKey(), bookTable.get(9));
    bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));


    bookObject.remove(bookTable.get(0).getKey());
    bookObject.remove(bookTable.get(1).getKey());
    bookObject.remove(bookTable.get(2).getKey());
    bookObject.remove(bookTable.get(3).getKey());
    bookObject.remove(bookTable.get(4).getKey());
    bookObject.remove(bookTable.get(5).getKey());

    for (int i = 6; i < 11; i++) {
      if (!bookObject.get(bookTable.get(i).getKey()).equals(bookTable.get(i))) {
        fail("Failed to get proper book for a key");
      }
    }
  }

  /**
   * This test the insert and resize. Inserting 10 items with 3 resizes.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test006_insert10_and_resize3()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    // Should be 2
    int cap1 = bookObject.getCapacity();

    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    // Should be 5
    int cap2 = bookObject.getCapacity();

    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));

    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    // Should be 11
    int cap3 = bookObject.getCapacity();

    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));

    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));

    bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    // Should be 23
    int cap4 = bookObject.getCapacity();

    bookObject.insert(bookTable.get(7).getKey(), bookTable.get(7));

    bookObject.insert(bookTable.get(8).getKey(), bookTable.get(8));

    bookObject.insert(bookTable.get(9).getKey(), bookTable.get(9));

    bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));

    // Checks the capacities to make sure the resizing worked
    if (cap1 != 2) {
      fail("Cap1 should be 2 but is actually: " + cap1);
    }

    if (cap2 != 5) {
      fail("Cap2 should be 5 but is actually: " + cap2);
    }

    if (cap3 != 11) {
      fail("Cap3 should be 11 but is actually: " + cap3);
    }

    if (cap4 != 23) {
      fail("Cap4 should be 23 but is actually: " + cap4);
    }
  }

  /**
   * This test the insert and remove and getNumKeys.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test007_insert_and_remove_and_getNumKeys()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
    bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    bookObject.insert(bookTable.get(7).getKey(), bookTable.get(7));
    bookObject.insert(bookTable.get(8).getKey(), bookTable.get(8));
    bookObject.insert(bookTable.get(9).getKey(), bookTable.get(9));
    bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));

    if (bookObject.numKeys() != 11) {
      fail("The number of keys in the hashTable should be 11 but was actually: "
          + bookObject.numKeys());
    }

    // Iterates through and removes everything from the list and checks the number of keys
    for (int i = 10; i > -1; i--) {
      bookObject.remove(bookTable.get(i).getKey());
      if (bookObject.numKeys() != i) {
        fail("numKeys is not the correct value");
      }
    }
  }

  /**
   * This test the insert and remove and getNumKeys.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test008_insert_and_remove_and_getLoadFactorThreshold()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
    bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    bookObject.insert(bookTable.get(7).getKey(), bookTable.get(7));
    bookObject.insert(bookTable.get(8).getKey(), bookTable.get(8));
    bookObject.insert(bookTable.get(9).getKey(), bookTable.get(9));
    bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));

    bookObject.remove(bookTable.get(0).getKey());
    bookObject.remove(bookTable.get(1).getKey());
    bookObject.remove(bookTable.get(2).getKey());
    bookObject.remove(bookTable.get(3).getKey());
    bookObject.remove(bookTable.get(4).getKey());
    bookObject.remove(bookTable.get(5).getKey());
    bookObject.remove(bookTable.get(6).getKey());
    bookObject.remove(bookTable.get(7).getKey());
    bookObject.remove(bookTable.get(8).getKey());
    bookObject.remove(bookTable.get(9).getKey());
    bookObject.remove(bookTable.get(10).getKey());

    if (bookObject.numKeys() != 0) {
      fail("numKeys should have been 0 but was actually: " + bookObject.numKeys());
    }

    if (!(bookObject.getLoadFactorThreshold() - 0.49 < 0.001)) {
      fail("The initial load factor threshold was not set correctly");
    }
  }

  /**
   * Tests the get and remove operation on a hash table with no keys in it.
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test009_remove_get_with_empty_table()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {

    if (bookObject.remove(bookTable.get(0).getKey()) != false) {
      fail("The remove method should return false for an empty Hash Table");
    }

    try {
      bookObject.get(bookTable.get(0).getKey());
      fail("Should have thrown a key not found exception");
    } catch (KeyNotFoundException e) {
      // Should throw a key not found exception when get is called on an empty hash table
    }
  }

  /**
   * Test adding, removing, and getting the num keys for all
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test010_add_remove_numKeys_all_books()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    // Adds all of the items in the bookTable list into our hashTable
    for (int i = 0; i < bookTable.size(); i++) {
      if (bookObject.numKeys() != i) {
        fail("NumKeys was incorrect for: " + i);
      }
      bookObject.insert(bookTable.get(i).getKey(), bookTable.get(i));
    }

    // Removes all the books from our hashTable
    for (int j = bookTable.size() - 1; j > -1; j--) {
      bookObject.remove(bookTable.get(j).getKey());

      if (bookObject.numKeys() != j) {
        fail("NumKeys was incorrect for: " + j);
      }
    }

    if (bookObject.numKeys() != 0) {
      fail("The number of keys in the hashTable should be 0 because we removed");
    }
  }

  /**
   * Test adding, removing, and then re adding the removed keys to make sure no Duplicate Key
   * Exception is thrown
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test011_add_remove_add_removed_key()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    bookObject.insert(bookTable.get(0).getKey(), bookTable.get(0));
    bookObject.insert(bookTable.get(1).getKey(), bookTable.get(1));
    bookObject.insert(bookTable.get(2).getKey(), bookTable.get(2));
    bookObject.insert(bookTable.get(3).getKey(), bookTable.get(3));
    bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
    bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
    bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    bookObject.insert(bookTable.get(7).getKey(), bookTable.get(7));
    bookObject.insert(bookTable.get(8).getKey(), bookTable.get(8));
    bookObject.insert(bookTable.get(9).getKey(), bookTable.get(9));
    bookObject.insert(bookTable.get(10).getKey(), bookTable.get(10));

    bookObject.remove(bookTable.get(4).getKey());
    bookObject.remove(bookTable.get(5).getKey());
    bookObject.remove(bookTable.get(6).getKey());

    try {
      bookObject.insert(bookTable.get(4).getKey(), bookTable.get(4));
      bookObject.insert(bookTable.get(5).getKey(), bookTable.get(5));
      bookObject.insert(bookTable.get(6).getKey(), bookTable.get(6));
    } catch (DuplicateKeyException e) {
      fail("Should Not have thrown a duplicate key after trying to"
          + " insert books with keys that have already been removed.");
    }
  }

  /**
   * Test adding the same books with different keys
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test012_add_same_books_different_keys()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    Book book = bookTable.get(0);

    bookObject.insert(book.getKey(), book);

    try {
      bookObject.insert("9999999999999", book);

      bookObject.insert("123456789", book);

      bookObject.insert("987654321", book);
    } catch (DuplicateKeyException e) {
      fail("DuplicateKeyException Should not have been thrown because "
          + "all the keys were different for the same book");
    }
  }

  /**
   * Test adding the same books with the same key
   * 
   * @throws IllegalNullKeyException
   * @throws DuplicateKeyException
   * @throws KeyNotFoundException
   */
  @Test
  public void test013_add_same_books_same_keys()
      throws IllegalNullKeyException, DuplicateKeyException, KeyNotFoundException {
    Book book = bookTable.get(0);

    bookObject.insert("1234", book);

    bookObject.insert("9999999999999", book);

    bookObject.insert("123456789", book);

    bookObject.insert("987654321", book);

    try {
      bookObject.insert("123456789", book);
      fail("DuplicateKeyException Should have been thrown because "
          + "the key for the book was already in the hashTable");
    } catch (DuplicateKeyException e) {
      // Should throw a duplicate key exception
    }
  }
}

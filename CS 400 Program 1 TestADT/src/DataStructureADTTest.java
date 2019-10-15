//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 1 Implement and Test an ADT
// Files: DS_My.java, TestDS_My.java, DataStrucureADT.java
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements and test an ADT. The program also tests the
// implementations of other ADT that people have written in previous semesters. The program utilizes
// an interface an Junit tests to test peoples implementations in a generic way.
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

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

  private T dataStructureInstance;

  protected abstract T createInstance();

  @BeforeAll
  static void setUpBeforeClass() throws Exception {}

  @AfterAll
  static void tearDownAfterClass() throws Exception {}

  @BeforeEach
  void setUp() throws Exception {
    dataStructureInstance = createInstance();
  }

  @AfterEach
  void tearDown() throws Exception {
    dataStructureInstance = null;
  }


  // Test 00 - Checks that the size of an empty data structure is 0
  @Test
  void test00_empty_ds_size() {
    if (dataStructureInstance.size() != 0) {
      fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
    }
  }

  // Test 01 - Checks that the size of a data strucutre with one element has a size of 1
  @Test
  void test01_after_insert_one_size_is_one() {
    dataStructureInstance.insert("brevard", "JacobBrevard");
    if (dataStructureInstance.size() != 1) {
      fail("data structure should have one element, with size=1, but size="
          + dataStructureInstance.size());
    }
  }

  // Test 02 - Adds an element and checks if size is 1 and then removes that element and checks if
  // size is 0
  @Test
  void test02_after_insert_one_remove_one_size_is_0() {
    dataStructureInstance.insert("bus", "RonaldBus");
    if (dataStructureInstance.size() != 1) {
      fail("data structure should have one element, with size=1, but size="
          + dataStructureInstance.size());
    }

    dataStructureInstance.remove("bus");
    if (dataStructureInstance.size() != 0) {
      fail("data structure should have no elements, with size=0, but size="
          + dataStructureInstance.size());
    }

  }

  // Test 03 - Inserts some key value pairs and then attempts to add an existing key to the data
  // structure. An exception should be thrown.
  @Test
  void test03_duplicate_exception_is_thrown() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    try {
      dataStructureInstance.insert("1", "Grape");
      fail("data structure should have thrown a runtime exception because of duplicate key. ");
    } catch (RuntimeException duplicateKeyException) {
      // Means the runtime exception was thrown and the test should pass
    }

  }

  // Test 04 - Checks the remove method returns false if you try to remove a key not in the list.
  @Test
  void test04_remove_returns_false_when_key_not_present() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    if (dataStructureInstance.remove("5")) {
      fail(
          "the remove method should have returned false because item being removed is not in the data structure");
    }

  }

  // Test 05 - Inserts and removes multiple keys value pairs into the data structure. Should keep
  // the correct size values throughout.
  @Test
  void test05_multiple_remove_and_inserts() {
    dataStructureInstance.insert("1", "Grape");

    dataStructureInstance.insert("2", "Pear");

    dataStructureInstance.remove("2");

    if (dataStructureInstance.size() != 1) {
      fail("the size of the data structure should be 1. The actual size is: "
          + dataStructureInstance.size());
    }

    dataStructureInstance.insert("3", "Apple");

    if (dataStructureInstance.size() != 2) {
      fail("the size of the data structure should be 1. The actual size is: "
          + dataStructureInstance.size());
    }

    dataStructureInstance.insert("4", "Orange");

    if (dataStructureInstance.size() != 3) {
      fail("the size of the data structure should be 3. The actual size is: "
          + dataStructureInstance.size());
    }

    dataStructureInstance.remove("4");

    dataStructureInstance.remove("3");

    dataStructureInstance.remove("1");

    if (dataStructureInstance.size() != 0) {
      fail("the size of the data structure should be 0. The actual size is: "
          + dataStructureInstance.size());
    }
  }

  // Test 06 - Attempts to remove a null key from the data structure. Should throw an Illegal
  // argument exception and should not decrease the size.
  @Test
  void test06_remove_a_null_key() {
    dataStructureInstance.insert("1", "Grape");

    dataStructureInstance.insert("2", "Orange");

    try {
      dataStructureInstance.remove(null);
      fail("Should have thrown an Illegal Argument Exception for attempting to insert a null key");
    } catch (IllegalArgumentException removeNullKeyException) {
      // Threw the appropriate exception so the test should pass
    }
  }

  // Test 07 - Attempts to insert a null key from the data structure. Should throw an Illegal
  // argument exception.
  @Test
  void test07_insert_a_null_key() {
    dataStructureInstance.insert("1", "Grape");

    dataStructureInstance.insert("2", "Orange");

    try {
      dataStructureInstance.insert(null, "Bob");
      fail("Should have thrown an Illegal Argument Exception for attempting to insert a null key");
    } catch (IllegalArgumentException insertNullKeyException) {
      // Threw the appropriate exception so the test should pass
    }
  }

  // Test 08 - Inserts key value pairs into the data structure and then tries to get them. Should
  // return the correct value.
  @Test
  void test08_test_get_method_with_non_null_key() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    if (!dataStructureInstance.get("3").equals("Apple")) {
      fail("The get method should have returned Apple. But instead returned: "
          + dataStructureInstance.get("3"));
    }


    if (!dataStructureInstance.get("2").equals("Pear")) {
      fail("The get method should have returned Pear. But instead returned: "
          + dataStructureInstance.get("2"));
    }


    if (!dataStructureInstance.get("1").equals("Grape")) {
      fail("The get method should have returned Grape. But instead returned: "
          + dataStructureInstance.get("1"));
    }

    if (!dataStructureInstance.get("4").equals("Orange")) {
      fail("The get method should have returned Orange. But instead returned: "
          + dataStructureInstance.get("4"));
    }
  }

  // Test 09 - Inserts key value pairs into the data structure and then tries to get a null key.
  // Should throw an Illegal argument exception.
  @Test
  void test09_test_get_method_with_null_key() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    try {
      dataStructureInstance.get(null);
      fail(
          "An Illegal argument Exception should have been thrown because a null key was passed to the get method.");
    } catch (IllegalArgumentException getNullKey) {
      // Threw the proper exception so the test should pass
    }
  }

  // Test 10 - Inserts key value pairs and then checks if the data structure contains a key value
  // pair that was added. Should return true.
  @Test
  void test10_test_contains_method_with_element_in_list() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    if (!dataStructureInstance.contains("3")) {
      fail(
          "The contains method should have returned true as we checked if the data structure contained an item we added.");
    }
  }

  // Test 11 - Inserts key value pairs and then checks if the data structure contains a key value
  // pair that was not added. Should return false.
  @Test
  void test11_test_contains_method_without_element_in_list() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    if (dataStructureInstance.contains("5")) {
      fail(
          "The contains method should have returned false as we checked if the data structure contained an item we added.");
    }
  }

  // Test 12 - Adds many items to the list and then removes some and then tries to insert a key
  // already in the list.
  @Test
  void test12_end_to_end_test() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");
    dataStructureInstance.insert("5", "Peach");
    dataStructureInstance.insert("6", "Watemellon");
    dataStructureInstance.insert("7", "Muskmellon");
    dataStructureInstance.insert("8", "Cantelope");

    dataStructureInstance.remove("2");
    dataStructureInstance.remove("4");
    dataStructureInstance.remove("6");
    dataStructureInstance.remove("8");

    try {
      dataStructureInstance.insert("1", "Grape");
      fail("The key being inserted is a duplicate so a Runtime Exception should be thrown.");
    } catch (RuntimeException duplicateKeyException) {
      // Threw the correct Exception so the test should pass
    }

  }

  // Test 13 - Inserts many keys and then removes some keys and then adds one that we removed
  // earlier. (It would not be a duplicate key)
  @Test
  void test13_end_to_end_test() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");
    dataStructureInstance.insert("5", "Peach");
    dataStructureInstance.insert("6", "Watemellon");
    dataStructureInstance.insert("7", "Muskmellon");
    dataStructureInstance.insert("8", "Cantelope");

    dataStructureInstance.remove("2");
    dataStructureInstance.remove("4");
    dataStructureInstance.remove("6");
    dataStructureInstance.remove("8");

    try {
      dataStructureInstance.insert("2", "Pear");
      dataStructureInstance.insert("4", "Orange");
      dataStructureInstance.insert("6", "Watemellon");
      dataStructureInstance.insert("8", "Cantelope");
      // None of these statements should thrown Runtime Exceptions for Duplicate Keys
    } catch (RuntimeException duplicateKeyException) {
      fail("None of the insert statements should have thrown a Runtime Exception for a duplicate "
          + "key because all of the values were added, then removed, and then re added.");
    }
  }

  // Test 14 - inserts 1000 items and then removes 1000 items and checks the size after each move.
  @Test
  void test14_end_to_end_test() {
    for (int i = 0; i < 1000; i++) {
      dataStructureInstance.insert("" + i + "", "Wisconsin Badgers");

      if (dataStructureInstance.size() != (i + 1)) {
        fail(
            "The size should be: " + (i + 1) + " but is actually: " + dataStructureInstance.size());
      }
    }

    int fullSize = 1000;

    for (int i = 0; i < 1000; i++) {
      dataStructureInstance.remove("" + i + "");

      fullSize--;

      if (dataStructureInstance.size() != (fullSize)) {
        fail("The size should be: " + (fullSize) + " but is actually: "
            + dataStructureInstance.size());
      }
    }

    // List should have a size of 0 after adding 1000 items and then removing 1000 items
    if (dataStructureInstance.size() != 0) {
      fail("The size should be 0 because the list is empty but instead the size is: "
          + dataStructureInstance.size());
    }
  }

  // Test 15 - tests the get method with a non null key that is not in the list. Should return null.
  @Test
  void test15_test_get_method_with_non_null_key_not_in_list() {
    dataStructureInstance.insert("1", "Grape");
    dataStructureInstance.insert("2", "Pear");
    dataStructureInstance.insert("3", "Apple");
    dataStructureInstance.insert("4", "Orange");

    if (dataStructureInstance.get("5") != null) {
      fail(
          "The get method should have returned null because item is not in the data structure. The get method actually returned: "
              + dataStructureInstance.get("5"));
    }
  }

  // Test 16 - Checks Similar Keys that are different. Then Checks the size
  @Test
  void test16_end_to_end_test() {
    dataStructureInstance.insert("Bob", "Grape");
    dataStructureInstance.insert("bOb", "Pear");
    dataStructureInstance.insert("boB", "Apple");
    dataStructureInstance.insert("BOB", "Orange");
    dataStructureInstance.insert("bob", "Orange");

    if (dataStructureInstance.size() != 5) {
      fail("The Size should be 5 but is actually: " + dataStructureInstance.size());
    }
  }

}

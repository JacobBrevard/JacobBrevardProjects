//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: StorageUnitTests.java
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
 * This class contains the code for the Storage Unit Tests Class in the Storage Unit Organizer
 * program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Storage Unit Tests Class contains the test bench for the Storage Unit Organizer program.
 * 
 */
public class StorageUnitTests {
  // Checks whether the behavior of equals method is correct
  /**
   * Checks whether the behavior of equals method is correct.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testBoxEquals() {
    // Tests if a box and an object created as a box are equal

    // Creates the new necessary objects for the test
    Box box1 = new Box(3, 4);

    Object box2 = new Box(3, 4);

    if (!box1.equals(box2)) {
      return false;
    }

    // Checks that the exception is thrown if the weight is larger that 30
    try {
      // Suppresses the warning. The variable box 3 is unused and is only used for this tests
      @SuppressWarnings("unused")
      Box box3 = new Box(2, 35);
      // If it gets here because the above line does not throw an exception then the test failed
      return false;
    } catch (IllegalArgumentException e) {
      // Should catch the thrown exception hereS
    }

    return true;

  }

  // Checks whether the behavior of compareTo method is correctly implemented
  /**
   * Checks whether the behavior of compareTo method is correctly implemented
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testBoxCompareTo() {
    // Creates the new necessary objects for the test
    Box thisBox = new Box(2, 4);

    Box otherBox = new Box(2, 8);

    // Tests that a the method returns a negative number when this box is lighter than the other box
    if (thisBox.compareTo(otherBox) >= 0) {
      return false;
    }

    return true;
  }

  /**
   * This test method checks to make sure the constructor and the getter method is working
   * appropriately.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testLinkedBoxNodeGetter() {
    // Creates the new necessary objects for the test
    Box box1 = new Box(3, 5);

    LinkedBoxNode node1 = new LinkedBoxNode(box1);

    // Checks if the get box method returns the correct box
    if (node1.getBox() != box1) {
      return false;
    }

    return true;
  }

  /**
   * This test method checks to make sure the constructor and the setter method is working
   * appropriately.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testLinkedBoxNodeSetter() {
    // Creates the new necessary objects for the test
    Box box1 = new Box(3, 5);

    LinkedBoxNode node1 = new LinkedBoxNode(box1);

    Box box2 = new Box(2, 15);

    LinkedBoxNode node2 = new LinkedBoxNode(box2, node1);

    // Checks if the next node in the list is correct
    if (node2.getNext() != node1) {
      return false;
    }

    // Sets the next node to a different node
    node2.setNext(node2);

    // Gets the new node to see if the set next method correctly set the node to the new value
    if (node2.getNext() != node2) {
      return false;
    }

    // Checks to see if the boxes are still in the correct nodes
    if (node2.getBox() != box2) {
      return false;
    }

    return true;
  }

  // Checks whether remove method defined in your LinkedBoxList works correctly
  /**
   * Checks whether remove method defined in your LinkedBoxList works correctly. Also Checks a
   * variety of other methods in thhe Linked box list class to make sure they are functioning
   * correctly.
   * 
   * @return true if the test passes and false otherwise.
   */
  public static boolean testLinkedBoxListRemove() {
    // Create new objects that are necessary for the test
    LinkedBoxList list1 = new LinkedBoxList(10);

    Box box1 = new Box(1, 9);

    Box box2 = new Box(2, 4);

    Box box3 = new Box(4, 7);


    // Add 6 Boxes to the list
    list1.add(box1);
    list1.add(box2);
    list1.add(box3);
    list1.add(box3);
    list1.add(box2);
    list1.add(box1);

    // Checks the get method
    if (list1.get(3).getWeight() != 7) {
      return false;
    }

    // Checks if the contains method works
    if (!list1.contains(box3)) {
      return false;
    }

    // Checks that the remove method works properly and returns the correct box
    if (list1.remove(2).getWeight() != 7 || list1.size() != 5) {
      return false;
    }

    // Checks that the remove method works properly and returns the correct box
    if (list1.remove(0).getWeight() != 9 || list1.size() != 4) {
      return false;
    }

    return true;

  }


  /**
   * Calls the testing static methods and prints out their result on the console.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // TEST 1 call and print statement
    System.out.println("TEST 1: testBoxEquals(): " + testBoxEquals());

    // TEST 2 call and print statement
    System.out.println("TEST 2: testBoxCompareTo(): " + testBoxCompareTo());

    // TEST 3 call and print statement
    System.out.println("TEST 3: testLinkedBoxNodeGetter(): " + testLinkedBoxNodeGetter());

    // TEST 4 call and print statement
    System.out.println("TEST 4: testLinkedBoxNodeSetter(): " + testLinkedBoxNodeSetter());

    // TEST 5 call and print statement
    System.out.println("TEST 5: testLinkedBoxListRemove(): " + testLinkedBoxListRemove());

  }


}

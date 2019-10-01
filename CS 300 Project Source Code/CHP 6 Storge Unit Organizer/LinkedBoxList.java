//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P06 Storage Unit Organizer
// Files: LinkedBoxList.java
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
 * This class contains the code for the Linked Box List Class in the Storage Unit Organizer program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Linked Box List Class contains the constructor and methods for a Linked List in our storage
 * unit program. This class models a linked List in our application.
 * 
 */
public class LinkedBoxList {

  // head of this LinkedBoxList (refers to the element stored at index 0 within this list)
  private LinkedBoxNode head;

  // number of boxes already stored in this list
  private int size;

  // capacity of this LinkedBoxList. Maximum number of box elements that this LinkedBoxList can
  // store
  private int capacity;

  // Creates an empty LinkedBoxList with a given initial capacity
  /**
   * Creates an empty LinkedBoxList with a given initial capacity.
   * 
   * @param capacity - The Maximum number of box elements that this LinkedBoxList can store.
   */
  public LinkedBoxList(int capacity) {
    this.capacity = capacity;

  }

  // Returns the size of this list
  /**
   * Returns the size of this list.
   * 
   * @return the size of this list.
   */
  public int size() {
    return size;
  }

  // Return the capacity of this list
  /**
   * Returns the capacity of this list.
   * 
   * @return the capacity of this list.
   */
  public int getCapacity() {
    return capacity;
  }

  // Expands the capacity of this LinkedBoxList with the specified number a of
  // additional elements
  /**
   * Expands the capacity of this LinkedBoxList with the specified number a of additional elements.
   * 
   * @param a - The specified number of elements the list wants to expand its capacity by.
   */
  public void expandCapacity(int a) {
    capacity += a;
  }

  // Checks whether this LinkedBoxList is empty
  // returns true if this LinkedBoxList is empty, false otherwise
  /**
   * Checks whether this LinkedBoxList is empty.
   * 
   * @return true if this LinkedBoxList is empty, false otherwise.
   */
  public boolean isEmpty() {
    // If the size is 0 this means the list is empty
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  // Checks whether this LinkedBoxList is full
  // Returns true if this list is full, false otherwise
  /**
   * Checks whether this LinkedBoxList is full.
   * 
   * @return true if this list is full, false otherwise.
   */
  public boolean isFull() {
    // If the size is greater than or equal to the capacity then the list is full
    if (size >= capacity) {
      return true;
    } else {
      return false;
    }

  }

  // Adds a new box into this sorted list
  // Throws IllegalArgumentException if newBox is null
  // Throws IllegalStateException if this list is full
  /**
   * Adds a new box into this sorted list. Throws IllegalArgumentException if newBox is null. Throws
   * IllegalStateException if this list is full.
   * 
   * @param newBox - the new box to be added to the linked list.
   * 
   * @throws IllegalArgumentException - if newBox is null.
   * 
   * @throws IllegalStateException if this list is full.
   */
  public void add(Box newBox) throws IllegalArgumentException, IllegalStateException {
    // Variables declared to keep track of the last and current node
    LinkedBoxNode lastNode = null;

    LinkedBoxNode currNode = head;


    // If the box passed in is null we throw an exception
    if (newBox == null) {
      throw new IllegalArgumentException(
          "Error: The box must be initialized and have a valid reference.");
    }

    // If the list is full then we throw an exception
    if (isFull()) {
      throw new IllegalStateException(
          "Error: Cannot add a new box to the list because the list is full.");
    }

    // Checks if the list is empty an if it is then we set head to the new node. If it is not empty
    // then we create a new node with the box and head as the parameter.
    if (isEmpty()) {
      // Sets head to the new linked node
      head = new LinkedBoxNode(newBox, null);

      // Sets current node to null to exit method
      currNode = null;

      // Increments size
      size++;
    }

    else {

      // If the new Box passed in is bigger than the head we change the head and link the rest of
      // the list
      if (currNode.getBox().compareTo(newBox) < 0) {
        // Saves the linked nodes from head
        LinkedBoxNode temp = head;

        // Changes head and then links the old head list to the end
        head = new LinkedBoxNode(newBox, temp);

        // Sets current node to null to exit method
        currNode = null;

        // Increments size
        size++;
      } else {
        // Iterates through the rest of the nodes looking for where the new box fits into the sorted
        // list
        while (currNode != null && currNode.getNext() != null) {
          // We set the last node to be the current node of the last iteration
          lastNode = currNode;

          // Current node becomes the next node after the old current node
          currNode = currNode.getNext();

          if (currNode.getBox().compareTo(newBox) < 0) {
            // Creates the new node and appends the rest of the list by setting the next node
            LinkedBoxNode insert = new LinkedBoxNode(newBox, currNode);

            // Appends the first part of the list
            lastNode.setNext(insert);

            // Sets current node to null to exit method
            currNode = null;

            // Increments size
            size++;
          }

        }

      }

    }
    // If the item belongs at the end of the list indicated by currNode not being null we then set
    // the currNode next to be the newly created node
    if (currNode != null) {
      // Sets the current node (the last node in the list) nex to be the new node we create that
      // contains the new box
      currNode.setNext(new LinkedBoxNode(newBox));

      // Add one to size
      size++;
    }


  }


  // Checks if this list contains a box that matches with (equals) a specific box object
  // Returns true if this list contains findBox, false otherwise
  /**
   * Checks if this list contains a box that matches with (equals) a specific box object.
   * 
   * @param findBox - the box parameter to find in the list.
   * 
   * @return true if this list contains findBox, false otherwise.
   */
  public boolean contains(Box findBox) {
    // Temporary Linked node variable
    LinkedBoxNode temp = head;

    // Checks if the box in the node equals the passed in box
    if (temp.getBox().equals(findBox)) {
      return true;
    }

    for (int i = 1; i < size; i++) {
      // Gets the next node
      temp = temp.getNext();

      // Checks if the box in the node equals the passed in box
      if (temp.getBox().equals(findBox)) {
        return true;
      }

    }

    return false;

  }

  // Returns a box stored in this list given its index
  // Throws IndexOutOfBoundsException if index is out of the range 0..size-1
  /**
   * Returns a box stored in this list given its index.
   * 
   * @param index - The index of the box we want to return.
   * 
   * @throws IndexOutOfBoundsException - if index is out of the range 0..size-1.
   * 
   * @return a box stored in this list given its index.
   */
  public Box get(int index) throws IndexOutOfBoundsException {
    // Checks if the index is in the range. If not then we throw an exception.
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException(
          "Error: The index must be between 0 and the size of the list minus 1.");
    }

    // Temporary Linked node variable
    LinkedBoxNode temp = head;

    // If the index is 0 we return the head
    if (index == 0) {
      return head.getBox();
    }

    // Advances to the node with the given index
    while (index > 0) {
      temp = temp.getNext();

      index--;
    }

    return temp.getBox();

  }

  // Removes and returns the box stored at index from this LinkedBoxList
  // Throws IndexOutOfBoundsException if index is out of bounds. index should be in
  // the range of [0.. size()-1]
  /**
   * Removes and returns the box stored at index from this LinkedBoxList.
   * 
   * @param index - The index of the box we want to remove.
   *
   * @throws IndexOutOfBoundsException if index is out of bounds. index should be in the range of
   *         [0.. size()-1].
   * 
   * @return the box stored at index from this LinkedBoxList.
   */
  public Box remove(int index) throws IndexOutOfBoundsException {
    // Variables declared to keep track of the last and current node
    LinkedBoxNode currNode = head;

    LinkedBoxNode lastNode = null;


    // Checks if the index is in the range. If not then we throw an exception.
    if (index > size - 1 || index < 0) {
      throw new IndexOutOfBoundsException(
          "Error: The index must be between 0 and the size of the list minus 1.");
    }

    // If the index is 0 we return the head
    if (index == 0) {
      // Stores the box in a temporary variable
      Box temporary = head.getBox();

      // Sets the head node to be the node after the current head
      head = head.getNext();

      // Decreases size by 1 because we removed a box
      size--;

      return temporary;
    }

    // If the index is not 0, we iterate through the list until we get to the node at the correct
    // index
    while (index > 0) {
      // Set the lastNode to the currNode of the previous iteration
      lastNode = currNode;

      // Set the currNode to the node after the last currNode
      currNode = currNode.getNext();

      // Decrease the index by 1
      index--;
    }

    // Creates a temporary box to store the box
    Box temporary = currNode.getBox();

    // Sets the last node's next to the node after current node effectively removing it from the
    // list
    lastNode.setNext(currNode.getNext());

    // Removes one from size
    size--;

    return temporary;

  }

  // Removes all the boxes from this list
  /**
   * Removes all the boxes from this list.
   */
  public void clear() {
    // Sets the head to null effectively clearing the list
    head = null;

    // Sets size to 0 because all the boxes have been removed from the list
    size = 0;
  }

  // Returns a String representation of the state and content of this LinkedBoxList
  // An example of source code for this method is provided you in the next paragraph
  /**
   * Returns a String representation for this LinkedBoxList
   */
  @Override
  public String toString() {
    // creates a StringBuilder object
    StringBuilder result = new StringBuilder();

    String newLine = System.getProperty("line.separator");

    result.append("------------------------------------------------" + newLine);

    if (!isEmpty()) {
      LinkedBoxNode runner = head;

      int index = 0;

      // traverse the list and add a String representation for each box
      while (runner != null) {

        result.insert(0,
            "Box at index " + index + ": " + runner.getBox().getWeight() + " lbs" + newLine);

        runner = runner.getNext();

        index++;
      }
      result.insert(0, "Box List Content:" + newLine);
    }

    result.insert(0, "List size: " + size + " box(es)." + newLine);

    result.insert(0, "Box List is empty: " + isEmpty() + newLine);

    result.insert(0, "------------------------------------------------" + newLine);

    return result.toString();
  }

}

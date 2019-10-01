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

/**
 * This is my outer class which implements the interface provided by the DataStructureADT interface.
 * Contains all of the methods for an abstract data type.
 * 
 * @author Jacob Brevard
 *
 */
public class DS_My implements DataStructureADT {

  // Private Fields of the Class. Field for the dataStucture and the size
  private linkedNode head;

  private int size;


  /**
   * This inner class creates my linked node object.
   * 
   * @author Jacob Brevard
   *
   */
  private class linkedNode {
    // Stores the next node
    private linkedNode next;

    // The key Value Pair Object
    private valuePair node;

    // Constructor with just the node
    /**
     * Creates a new linked Node.
     * 
     * @param node - the key value pair passed in.
     */
    private linkedNode(valuePair node) {
      this.node = node;
    }

    // Constructor with the node and the next node
    /**
     * Created new linked Node.
     * 
     * @param node - the key valuePair passed in
     * 
     * @param next - the next node in the list
     */
    private linkedNode(valuePair node, linkedNode next) {
      this(node);

      this.next = next;
    }

    /**
     * Getter method.
     * 
     * @return - returns next node in list.
     */
    private linkedNode getNext() {
      return next;
    }

    /**
     * Setter method.
     * 
     * @param next - the node to set next to.
     */
    private void setNext(linkedNode next) {
      this.next = next;
    }

    /**
     * Getter Method.
     * 
     * @return the node.
     */
    private valuePair getNode() {
      return node;
    }

    /**
     * Sets the node.
     * 
     * @param node - the passed in node to be set.
     */
    private void setNode(valuePair node) {
      this.node = node;
    }

  }

  /**
   * Inner Class to my DS_My dataStructure class. Creates the value pairNodes which are to be stored
   * in my data Strucutre ADT.
   * 
   * @author footb
   *
   */
  private class valuePair {
    // Fields for the key and the object/data
    private Comparable key;

    private Object data;

    /**
     * Constructor for the valuePairNode object. Do not need a no args constructor because it will
     * never be called without both parameters.
     * 
     * @param key - the key used to find an item in the list
     * 
     * @param data - the item that is stored in the list.
     */
    private valuePair(Comparable key, Object data) {
      this.key = key;

      this.data = data;
    }

    /**
     * Getter method for the key value.
     * 
     * @return the key.
     */
    private Comparable getKey() {
      return key;
    }

    /**
     * Setter method for the key value.
     * 
     * @param the key.
     */
    private void setKey(Comparable key) {
      this.key = key;
    }

    /**
     * Getter method for the v Value.
     * 
     * @return the data
     */
    private Object getData() {
      return data;
    }

    /**
     * Setter Method for the data item.
     * 
     * @param data - the item/object to be set.
     */
    private void setData(Object data) {
      this.data = data;
    }


  }

  /**
   * Constructor for my data Structure ADT
   * 
   */
  public DS_My() {
    head = null;

    size = 0;
  }

  @Override

  /**
   * The insert method inserts an item into the list. Add the key,value pair to the data structure
   * and increases size. If key is null, throws IllegalArgumentException("null key"); If key is
   * already in data structure, throws RuntimeException("duplicate key"); can accept and insert null
   * values
   * 
   * @param k - The key that is used to store the value
   * 
   * @param v - The object to be stored
   * 
   */
  public void insert(Comparable k, Object v) {
    linkedNode currentNode = head;

    linkedNode lastNode = null;

    // Checks if the key is null and then throws proper exception
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    // If head is empty then we add a new linked node to the list
    if (head == null) {
      head = new linkedNode(new valuePair(k, v), null);

      size++;
    }
    // If the head is not null then we check if it matches the key passed in
    else if (head.getNode().getKey().equals(k)) {
      throw new RuntimeException("duplicate key");
    } else {

      while (currentNode != null) {
        // We set the last node to be the current node of the last iteration
        lastNode = currentNode;

        // Current node becomes the next node after the old current node
        currentNode = currentNode.getNext();

        // If the current node is null we know we found an empty spot to insert our new node
        if (currentNode == null) {
          lastNode.setNext(new linkedNode(new valuePair(k, v), null));

          size++;
        }
        // If the current node is not null then we check if it matches the key pased in
        else if (currentNode.getNode().getKey().equals(k)) {
          throw new RuntimeException("duplicate key");
        }
      }
    }
  }

  @Override
  /**
   * The remove method removes an item from the list by finding and matching the key and then
   * removing the node. If key is found, Removes the key from the data structure and decreases size
   * If key is null, throws IllegalArgumentException("null key") without decreasing size If key is
   * not found, returns false.
   * 
   * @param k - the key passed in to find and remove the correct node.
   * 
   * @return - true if the item was found and removed and false if the item was not in the list.
   * 
   */
  public boolean remove(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    linkedNode currentNode = head;

    linkedNode lastNode = null;

    // Checks if the head matches the key passed in. Then removes and decreases size.
    if (head.getNode().getKey().equals(k)) {
      head = head.getNext();

      size--;
    }

    while (currentNode != null && currentNode.getNext() != null) {
      // We set the last node to be the current node of the last iteration
      lastNode = currentNode;

      // Current node becomes the next node after the old current node
      currentNode = currentNode.getNext();

      // Checks if the current node matches the key passed in. Then removes and decrements size.
      if (currentNode.getNode().getKey().equals(k)) {
        lastNode.setNext(currentNode.getNext());

        size--;

        return true;
      }
    }
    return false;
  }

  @Override
  /**
   * Contains method checks if a certain object is in the list looking by its key. Returns true if
   * the key is in the data structure. Returns false if key is null or not present.
   * 
   * @param k - the key to match and see if an item is in the list.
   * 
   * @return - true if the item is in the list and false if it is not in the list
   * 
   */
  public boolean contains(Comparable k) {
    if (k == null) {
      return false;
    }

    linkedNode currentNode = head;

    linkedNode lastNode = null;

    // Checks if the head matches the passed in key
    if (head.getNode().getKey().equals(k)) {
      return true;
    }

    while (currentNode != null && currentNode.getNext() != null) {
      // We set the last node to be the current node of the last iteration
      lastNode = currentNode;

      // Current node becomes the next node after the old current node
      currentNode = currentNode.getNext();

      // Checks if the current node
      if (currentNode.getNode().getKey().equals(k)) {
        return true;
      }

    }
    return false;
  }

  @Override
  /**
   * The get method finds an item in the list and then returns that item. Returns the value
   * associated with the specified key get - does not remove key or decrease size If key is null,
   * throws IllegalArgumentException("null key")
   * 
   * @param k - the key passed in to search with to find the item.
   * 
   * @return - the item if it is found or null if the item is not in the list
   * 
   */
  public Object get(Comparable k) {
    if (k == null) {
      throw new IllegalArgumentException("null key");
    }

    linkedNode currentNode = head;

    linkedNode lastNode = null;

    if (head.getNode().getKey().equals(k)) {
      return head.getNode().getData();
    }

    while (currentNode != null && currentNode.getNext() != null) {
      // We set the last node to be the current node of the last iteration
      lastNode = currentNode;

      // Current node becomes the next node after the old current node
      currentNode = currentNode.getNext();

      if (currentNode.getNode().getKey().equals(k)) {
        return currentNode.getNode().getData();
      }
    }

    return null;
  }

  @Override
  /**
   * The size method Returns the number of elements in the data structure
   * 
   */
  public int size() {
    return size;
  }
}

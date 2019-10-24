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
import java.util.Arrays;
import java.util.LinkedList;

// Comment and complete your HashTableADT implementation
//
// Implement all required methods
// DO ADD REQUIRED PUBLIC METHODS TO IMPLEMENT interfaces
//
// DO NOT ADD ADDITIONAL PUBLIC MEMBERS TO YOUR CLASS
// (no public or package methods that are not in implemented interfaces)
//
// Describe the collision resolution scheme you have chosen
// identify your scheme as open addressing or bucket
//
// if open addressing: describe probe sequence
// if buckets: describe data structure for each bucket - I used ArrayLists for each of my buckets
//
// Explain your hashing algorithm here : I use the absolute value of the hashCode() method %
// capacity
// NOTE: you are not required to design your own algorithm for hashing,
// since you do not know the type for K,
// you must use the hashCode provided by the <K key> object

/**
 * HashTable implementation that uses: An Array of ArrayList for the bucket collision resolution
 * strategy. Used an inner class to store my key value pair as a hashTableNode Object.
 * 
 * @param <K> unique comparable identifier for each <K,V> pair, may not be null
 * @param <V> associated value with a key, value may be null
 */
public class BookHashTable implements HashTableADT<String, Book> {
  private static boolean DEBUG = false;
  public static final String COLLISION_RESOLUTION = "Array of ArrayLists";

  /** The initial capacity that is used if none is specifed user */
  static final int DEFAULT_CAPACITY = 101;

  /** The load factor that is used if none is specified by user */
  static final double DEFAULT_LOAD_FACTOR_THRESHOLD = 0.75;

  // The current capacity of the hash table
  private int capacity;

  // The current number of keys in the hash table
  private int numKeys;

  // The current loadFactor of the table
  private double loadFactor;

  // The current loadFactorThreshold of the hash table
  private double loadFactorThreshold;

  // The hashTable - Collision strategy ArrayList of ArrayList of books (buckets)
  private ArrayList<hashTableNode>[] hashTable;

  /**
   * Inner Class to store my key value pair
   * 
   * @author Jacob Brevard
   *
   */
  private class hashTableNode {
    private String key;

    private Book value;

    public hashTableNode(String key, Book value) {
      this.key = key;

      this.value = value;
    }
  }

  /**
   * REQUIRED default no-arg constructor Uses default capacity and sets load factor threshold for
   * the newly created hash table.
   */
  public BookHashTable() {
    this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR_THRESHOLD);
  }

  /**
   * Creates an empty hash table with the specified capacity and load factor.
   * 
   * @param initialCapacity number of elements table should hold at start.
   * @param loadFactorThreshold the ratio of items/capacity that causes table to resize and rehash
   */
  public BookHashTable(int initialCapacity, double loadFactorThreshold) {
    // Sets the current capacity to the initial capacity
    capacity = initialCapacity;

    // Sets the initial number of keys to 0
    numKeys = 0;

    // Creates the new hash Table with the correct initial capacity
    hashTable = new ArrayList[initialCapacity];

    // Sets the initial load factor to 0
    loadFactor = 0;

    // Sets the current load factor to the initial load factor
    this.loadFactorThreshold = loadFactorThreshold;
  }

  @Override
  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException();
   * 
   * @param - key : the identifier of the book
   * @param - Value : the value of the book
   * 
   */
  public void insert(String key, Book value) throws IllegalNullKeyException, DuplicateKeyException {
    // If the key is null throw
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // Checking to see if the table needs to be resized
    if (loadFactor >= loadFactorThreshold) {
      reSize();
    }

    hashTableNode node = new hashTableNode(key, value);


    // Computes the hashIndex for the key to be inserted
    int hashIndex = hashFunction(key);

    // Gets the bucket at the hashIndex
    ArrayList<hashTableNode> currentBucket = hashTable[hashIndex];

    // Case: 1 - The bucket is empty
    if (currentBucket == null) {
      // Create the bucket at the hashIndex
      hashTable[hashIndex] = new ArrayList<hashTableNode>();
      // Add the value to the bucket
      hashTable[hashIndex].add(node);
      numKeys++;
    } else if (currentBucket.size() == 0) {
      currentBucket.add(node);
      numKeys++;
    }
    // Case: 2 - The bucket is not empty
    else {
      // Looks through bucket and checks to make sure there is not a duplicate
      for (int i = 0; i < currentBucket.size(); i++) {
        // Throw exception if duplicate is found
        if (currentBucket.get(i).key.equals(key)) {
          throw new DuplicateKeyException();
        }
      }

      // Adds the value to the bucket
      currentBucket.add(node);

      // Increase the number of keys by one
      numKeys++;

    }

    // Recalculate the loadFactor
    loadFactor = (double) numKeys / capacity;
  }

  /**
   * This is my hashing function that returns the hash index for the book
   * 
   * @param key - The identifier of the book
   * 
   * @return - The hash index
   */
  private int hashFunction(String key) {
    return Math.abs(key.hashCode()) % capacity;
  }

  /**
   * This method resizes the hashTable. Creates a new hashTable with the new capacity and rehashes
   * all the old items in the hashTable
   * 
   */
  private void reSize() {
    // Creates the new table size
    int newTableSize = (capacity * 2) + 1;

    // Creates the new hashTable
    ArrayList<hashTableNode>[] newHashTable = new ArrayList[newTableSize];

    // Iterates through the current hashTable rehashing every object it finds
    for (int i = 0; i < hashTable.length; i++) {
      // The hashIndex has at least one object in the ArrayList bucket
      if (hashTable[i] == null) {
        // Do nothing
      } else if (hashTable[i].size() == 0) {
        // Do nothing
      } else {
        for (int j = 0; j < hashTable[i].size(); j++) {
          // Computes new hash index and adds the book to the new hash index
          // Use book.getKey().hashCode() % new table size

          // If there is no ArrayList bucket then create the bucket and then add the book
          if (newHashTable[Math.abs(hashTable[i].get(j).key.hashCode()) % newTableSize] == null) {
            newHashTable[Math.abs(hashTable[i].get(j).key.hashCode()) % newTableSize] =
                new ArrayList<hashTableNode>();

            newHashTable[Math.abs(hashTable[i].get(j).key.hashCode()) % newTableSize]
                .add(hashTable[i].get(j));
          } else {
            newHashTable[Math.abs(hashTable[i].get(j).key.hashCode()) % newTableSize]
                .add(hashTable[i].get(j));
          }
        }
      }
    }

    // Increases the capacity of the hashTable
    capacity = (capacity * 2) + 1;

    // Sets the hashTable to be the new hash table
    hashTable = newHashTable;
  }

  @Override
  /**
   * If key is found, remove the key,value pair from the data structure decrease number of keys.
   * return true If key is null, throw IllegalNullKeyException If key is not found, return false
   * 
   * @param - key : the identifier of the book
   * 
   * @return - true if key was successfully removed, and false otherwise
   */
  public boolean remove(String key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    // If the hashTable is empty then the key was not found so return false
    if (numKeys == 0) {
      return false;
    }

    // key % capacity - hashIndex
    int hashIndex = hashFunction(key);

    // get the bucket
    ArrayList<hashTableNode> currentBucket = hashTable[hashIndex];

    // look through bucket calling getKey() on each book and compare to key
    for (int i = 0; i < currentBucket.size(); i++) {
      // If there is a match remove the book
      if (key.equals(currentBucket.get(i).key)) {
        // Remove the book
        currentBucket.remove(i);

        // Decrease the number of keys by one
        numKeys--;

        // Recalculate the loadFactor
        loadFactor = numKeys / capacity;

        return true;
      }
    }
    return false;
  }

  @Override
  /**
   * Returns the value associated with the specified key Does not remove key or decrease number of
   * keys If key is null, throw IllegalNullKeyException If key is not found, throw
   * KeyNotFoundException().
   * 
   * @param - key : the identifier of the book
   * 
   * @return - the value associated with the specified key
   */
  public Book get(String key) throws IllegalNullKeyException, KeyNotFoundException {
    // If the passed in key is null then throw an exception
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    if (numKeys == 0) {
      throw new KeyNotFoundException();
    }

    // key % capacity - hashIndex
    int hashIndex = hashFunction(key);

    // get the bucket
    ArrayList<hashTableNode> currentBucket = hashTable[hashIndex];

    // look through bucket calling getKey() on each book and compare to key
    for (int i = 0; i < currentBucket.size(); i++) {
      // If there is a match return the book
      if (key.equals(currentBucket.get(i).key)) {
        // Return the book
        return currentBucket.get(i).value;
      }
    }

    // If the key was not found
    throw new KeyNotFoundException();
  }

  @Override
  /**
   * Gets the number of key - value pairs in the data structure
   * 
   * @return - the number of key,value pairs in the data structure
   */
  public int numKeys() {
    return numKeys;
  }

  @Override
  /**
   * Gets the load factor threshold value for the data structure that determines when to increase
   * the capacity of this hash table
   * 
   * @return - the load factor threshold for the data structure
   */
  public double getLoadFactorThreshold() {
    return loadFactorThreshold;
  }

  @Override
  /**
   * Capacity is the size of the hash table array. The initial capacity must be a positive integer,
   * 1 or greater and is specified in the constructor. REQUIRED: When the load factor is reached,
   * the capacity must increase to: 2 * capacity + 1. Once increased, the capacity never decreases.
   *
   * @return - the current capacity for the data structure
   */
  public int getCapacity() {
    return capacity;
  }

  @Override
  /**
   * Returns the collision resolution scheme used for this hash table. Implement this ADT with one
   * of the following collision resolution strategies and implement this method to return an integer
   * to indicate which strategy.
   *
   * 1 OPEN ADDRESSING: linear probe 2 OPEN ADDRESSING: quadratic probe 3 OPEN ADDRESSING: double
   * hashing 4 CHAINED BUCKET: array of array lists 5 CHAINED BUCKET: array list of linked lists 6
   * CHAINED BUCKET: array list of binary search trees 7 CHAINED BUCKET: linked list of array lists
   * 8 CHAINED BUCKET: linked list of linked lists 9 CHAINED BUCKET: linked list of of binary search
   * trees
   *
   * @return - the current capacity for the data structure
   */
  public int getCollisionResolutionScheme() {
    // My strategy is buckets with ArrayList
    return 4;
  }
}

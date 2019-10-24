//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 2 Balanced Search Tree
// Files: BALST.java, BALSTTest.java, results.PNG
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This program implements and tests a Balanced Search Tree. I implemented a
// RBT (Red Black Tree) to maintain balance. This tree has many methods to recur through the tree
// and it makes sure it stays in balance by making sure their are no RBT property violations.
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
 * 
 * Class to implement a BalanceSearchTree. Can be of type AVL or Red-Black. Note which tree you
 * implement here and as a comment when you submit. I implemented a RBT.
 * 
 * @param <K> is the generic type of key
 * @param <V> is the generic type of value
 */

import java.util.ArrayList;
import java.util.List;

/**
 * This Class implements a Red Black Tree. The remove method does not follow the RBT Properties.
 * Just does a normal BST removal. The insert method follows the RBT Properties.
 * 
 * @author Jacob Brevard
 *
 * @param <K> - Key to identify the node
 * @param <V> - The data stored in the node
 */
public class BALST<K extends Comparable<K>, V> extends BSTNode<K, V> implements BALSTADT<K, V> {

  private BSTNode<K, V> root;

  private int numKeys;

  /**
   * This Class creates red black tree nodes and has the attributes needed to implement this node.
   * 
   * @author Jacob Brevard
   *
   * @param <K> - Key to identify the node
   * @param <V> - The data stored in the node
   */
  @SuppressWarnings("hiding")
  private class BSTNode<K, V> {
    K key;
    V value;
    BSTNode<K, V> parent;
    BSTNode<K, V> left;
    BSTNode<K, V> right;
    boolean isBlack;

    /**
     * The Constructor for the RBT Nodes
     * 
     * @param key - Key to identify the node
     * @param value - The data stored in the node
     * @param leftChild - The node that is the left child of the current node
     * @param rightChild - The node that is the left child of the current node
     */
    private BSTNode(K key, V value, BSTNode<K, V> leftChild, BSTNode<K, V> rightChild,
        BSTNode<K, V> parent) {
      this.key = key;
      this.value = value;
      this.parent = parent;
      this.left = leftChild;
      this.right = rightChild;
      this.isBlack = false;
    }

    /**
     * The second constructor for this clss which only takes a key and a value
     * 
     * @param key - Key to identify the node
     * @param value - The data stored in the node
     */
    private BSTNode(K key, V value) {
      this(key, value, null, null, null);
    }

    /**
     * Checks if a node is Black.
     * 
     * @return - True if the node is black and false otherwise.
     */
    private boolean isBlack() {
      return isBlack;
    }

    /**
     * Checks if a node is Red.
     * 
     * @return - True if the node is red and false otherwise
     */
    private boolean isRed() {
      return !isBlack;
    }

    /**
     * Changes the nodes color to black.
     */
    private void makeBlack() {
      isBlack = true;
    }

    /**
     * Changes the nodes color to red.
     */
    private void makeRed() {
      isBlack = false;
    }

    /**
     * Left rotate around this node. Tri Node Restructuring.
     */
    private void leftRotate() {
      boolean isRoot = false;
      boolean isRight = false;

      if (root == this) {
        isRoot = true;
      }

      // Set the first node in the list to the top node
      BSTNode<K, V> first = this;

      // Set the second node
      BSTNode<K, V> second = first.right;

      if (isRoot) {
        second.makeBlack();
      }

      // Saves the second nodes left child if it has one
      BSTNode<K, V> secondLeftC = second.left;

      // Sets our first node to the left child of second node
      first.right.left = first;

      BSTNode<K, V> firstParent = first.parent;

      if (firstParent != null && firstParent.right != null
          && firstParent.right.key.equals(first.key)) {
        isRight = true;
      }

      // Sets the root of this subtree to the second
      first = second;

      // Sets the last missing child
      first.left.right = secondLeftC;

      // Sets the new children to first to have first as their parent
      if (first.left != null && first.right != null) {
        if (firstParent != null) {

          // Sets the reference of the parent of the original first to the new subtree
          if (isRight) {
            firstParent.right = first;
          } else {
            firstParent.left = first;
          }

          // Sets the parent of the new first to the old firsts parent
          first.parent = firstParent;
        }
        first.left.parent = first;
        first.right.parent = first;
      } else if (first.left != null) {
        firstParent.left = first;
        second.parent = firstParent;
        first.left.parent = first;
      } else if (first.right != null) {
        firstParent.left = first;
        second.parent = firstParent;
        first.right.parent = first;
      } else {
        // Do not set anything
      }

      // Changes the parent of the extra child
      if (secondLeftC != null) {
        secondLeftC.parent = first.left;
      }

      // If it is the root then sets the parent of first to null
      if (isRoot) {
        first.parent = null;
      }

    }

    /**
     * Right rotate around this node. Tri Node Restructuring.
     */
    private void rightRotate() {
      boolean isRoot = false;
      boolean isRight = false;

      if (root == this) {
        isRoot = true;
      }

      // Set the first node in the list to the top node
      BSTNode<K, V> first = this;

      // Set the second node
      BSTNode<K, V> second = first.left;



      // Saves the second nodes left child if it has one
      BSTNode<K, V> secondRightC = second.right;

      // Sets the right child of our first node to the left child of second node
      first.left.right = first;

      BSTNode<K, V> firstParent = first.parent;

      if (firstParent != null && firstParent.right != null
          && firstParent.right.key.equals(first.key)) {
        isRight = true;
      }

      // Sets the root of this subtree to the second
      first = second;

      // Sets the last missing child
      first.right.left = secondRightC;

      // Sets the new children to first to have first as their parent
      if (first.left != null && first.right != null) {
        if (firstParent != null) {
          // Sets the reference of the parent of the original first to the new subtree
          if (isRight) {
            firstParent.right = first;
          } else {
            firstParent.left = first;
          }
          // Sets the parent of the new first to the old firsts parent
          first.parent = firstParent;
        }
        first.left.parent = first;
        first.right.parent = first;
      } else if (first.left != null) {
        firstParent.right = first;
        second.parent = firstParent;
        first.left.parent = first;
      } else if (first.right != null) {
        firstParent.right = first;
        second.parent = firstParent;
        first.right.parent = first;
      } else {
        // Do not set anything
      }

      // Changes the parent of the extra child
      if (secondRightC != null) {
        secondRightC.parent = first.right;
      }

      // If it is the root then sets the parent of first to null
      if (isRoot) {
        first.parent = null;
      }
    }

    /**
     * Looks for RBT violations and calls the appropriate methods to fix the balance of the tree.
     * Maintains the RBT Properties.
     */
    private void rbtFixViolators() {
      BSTNode<K, V> z = this;
      BSTNode<K, V> y = null;
      // Looking for RPV
      while (z.isRed() && z.parent.isRed()) {
        if (z.parent != null && z.parent.parent != null && z.parent == z.parent.parent.left) {
          // z's parent is a left child
          y = z.parent.parent.right;
          if (y != null && y.isRed()) {
            // Case 1: z's S is red
            z.parent.makeBlack();
            y.makeBlack();
            if (z.parent.parent.parent != null) {
              z.parent.parent.makeRed();
            }
          } else {
            if (z.parent.right != null && z == z.parent.right) {
              // Case 2: z's S is black and z is a right child
              z = z.parent;
              z.leftRotate();
            }
            // Case 3: z's y is black and z is a left child
            if (z.parent != null) {
              z.parent.makeBlack();
            }
            if (z.parent != null && z.parent.parent != null) {
              z.parent.parent.makeRed();
              z.parent.parent.rightRotate();
            }
          }
        } else {
          // z's parent is a right child. Do the same as when z's
          // parent is a left child, but exchange "left" and "right."

          if (z.parent != null && z.parent.parent != null) {
            y = z.parent.parent.left;
          }

          if (y != null && y.isRed()) {
            // Case: 1 z's y is red
            z.parent.makeBlack();
            y.makeBlack();
            if (z.parent.parent.parent != null) {
              z.parent.parent.makeRed();
            }
          } else {
            if (z.parent.left != null && z == z.parent.left) {
              // Case 2: z's y is black and z is a left child
              z = z.parent;
              z.rightRotate();
            }
            // Case 3: z's y is black and z is a right child
            if (z.parent != null) {
              z.parent.makeBlack();
            }

            if (z.parent != null && z.parent.parent != null) {
              z.parent.parent.makeRed();
              z.parent.parent.leftRotate();
            }
          }
        }
      }
    }
  } // End of the inner class

  /**
   * Constructor for the BALST (Balanced search tree)
   */
  public BALST() {
    super(null, null, null, null);

    this.root = null;

    this.numKeys = 0;
  }

  @Override
  /**
   * Returns the key that is in the root node of this BST. If root is null, returns null.
   * 
   * @return key found at root node, or null
   */
  public K getKeyAtRoot() {
    if (root == null) {
      return null;
    }

    return root.key;
  }

  @Override
  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the left child. If the left child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the left child of the found key
   * 
   * @throws IllegalNullKeyException if key argument is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  public K getKeyOfLeftChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (root == null) {
      throw new KeyNotFoundException();
    }

    BSTNode<K, V> nodeWithKey = leftChildHelper(root, key);

    if (nodeWithKey == null) {
      throw new KeyNotFoundException();
    } else {
      return nodeWithKey.left.key;
    }
  }

  /**
   * Recursive helper method for the get left child key method.
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param key - the key we are looking for
   * @return - the node with the matched key
   */
  private BSTNode<K, V> leftChildHelper(BSTNode<K, V> currentNode, K key) {
    // If node is null then return null, Base Case
    if (currentNode == null) {
      return null;
    } else if (key.compareTo(currentNode.key) == 0) {
      return currentNode;
    } else if (key.compareTo(currentNode.key) < 0) {
      return rightChildHelper(currentNode.left, key);
    } else {
      return rightChildHelper(currentNode.right, key);
    }
  }

  @Override
  /**
   * Tries to find a node with a key that matches the specified key. If a matching node is found, it
   * returns the returns the key that is in the right child. If the right child of the found node is
   * null, returns null.
   * 
   * @param key A key to search for
   * @return The key that is in the right child of the found key
   * 
   * @throws IllegalNullKeyException if key is null
   * @throws KeyNotFoundException if key is not found in this BST
   */
  public K getKeyOfRightChildOf(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }
    if (root == null) {

    }

    BSTNode<K, V> nodeWithKey = rightChildHelper(root, key);

    if (nodeWithKey == null) {
      throw new KeyNotFoundException();
    } else {
      return nodeWithKey.right.key;
    }
  }

  /**
   * Recursive helper method for the get key of right child of method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param key - the key we are looking for
   * @return - the node with the matched key
   */
  private BSTNode<K, V> rightChildHelper(BSTNode<K, V> currentNode, K key) {
    // If node is null then return null, Base Case
    if (currentNode == null) {
      return null;
    } else if (key.compareTo(currentNode.key) == 0) {
      return currentNode;
    } else if (key.compareTo(currentNode.key) < 0) {
      return rightChildHelper(currentNode.left, key);
    } else {
      return rightChildHelper(currentNode.right, key);
    }
  }

  @Override
  /**
   * Returns the height of this BST. H is defined as the number of levels in the tree.
   * 
   * If root is null, return 0 If root is a leaf, return 1 Else return 1 + max( height(root.left),
   * height(root.right) )
   * 
   * Examples: A BST with no keys, has a height of zero (0). A BST with one key, has a height of one
   * (1). A BST with two keys, has a height of two (2). A BST with three keys, can be balanced with
   * a height of two(2) or it may be linear with a height of three (3) ... and so on for tree with
   * other heights
   * 
   * @return the number of levels that contain keys in this BINARY SEARCH TREE
   */
  public int getHeight() {
    if (root == null) {
      return 0;
    }

    return 1 + heightHelper(root);
  }

  /**
   * getHeight recursive helper method.
   * 
   * @param currentNode - the current node in this iteration
   * @return - the height as an int
   */
  private int heightHelper(BSTNode<K, V> currentNode) {
    if (currentNode == null) {
      return -1;
    }

    int leftH = heightHelper(currentNode.left);
    int rightH = heightHelper(currentNode.right);

    if (leftH > rightH) {
      return leftH + 1;
    } else {
      return rightH + 1;
    }
  }

  @Override
  /**
   * Returns the keys of the data structure in sorted order. In the case of binary search trees, the
   * visit order is: L V R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in-order
   */
  public List<K> getInOrderTraversal() {
    List<K> inOrderKeys = new ArrayList<K>();

    if (root == null) {
      return inOrderKeys;
    }

    inOrderTraversalHelper(root, inOrderKeys);

    return inOrderKeys;
  }

  /**
   * The inOrderTraversal recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param keys - The list of keys
   */
  private void inOrderTraversalHelper(BSTNode<K, V> currentNode, List<K> keys) {
    if (currentNode == null) {
      return;
    }

    // First left child
    inOrderTraversalHelper(currentNode.left, keys);

    // Then Add the key of the node
    keys.add(currentNode.key);

    // Then right child
    inOrderTraversalHelper(currentNode.right, keys);
  }

  @Override
  /**
   * Returns the keys of the data structure in pre-order traversal order. In the case of binary
   * search trees, the order is: V L R
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in pre-order
   */
  public List<K> getPreOrderTraversal() {
    List<K> preOrderKeys = new ArrayList<K>();

    if (root == null) {
      return preOrderKeys;
    }

    preOrderTraversalHelper(root, preOrderKeys);

    return preOrderKeys;
  }

  /**
   * The preOrderTraversal recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param keys - The list of keys
   */
  private void preOrderTraversalHelper(BSTNode<K, V> currentNode, List<K> keys) {
    if (currentNode == null) {
      return;
    }

    // First Add the key of the node
    keys.add(currentNode.key);

    // Then left child
    preOrderTraversalHelper(currentNode.left, keys);

    // Then right child
    preOrderTraversalHelper(currentNode.right, keys);
  }

  @Override
  /**
   * Returns the keys of the data structure in post-order traversal order. In the case of binary
   * search trees, the order is: L R V
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in post-order
   */
  public List<K> getPostOrderTraversal() {
    List<K> postOrderKeys = new ArrayList<K>();

    if (root == null) {
      return postOrderKeys;
    }

    postOrderTraversalHelper(root, postOrderKeys);

    return postOrderKeys;
  }

  /**
   * The postOrderTraversal recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param keys - The list of keys
   */
  private void postOrderTraversalHelper(BSTNode<K, V> currentNode, List<K> keys) {
    if (currentNode == null) {
      return;
    }

    // First left child
    inOrderTraversalHelper(currentNode.left, keys);

    // Then right child
    inOrderTraversalHelper(currentNode.right, keys);

    // Then Add the key of the node
    keys.add(currentNode.key);
  }

  @Override
  /**
   * Returns the keys of the data structure in level-order traversal order.
   * 
   * The root is first in the list, then the keys found in the next level down, and so on.
   * 
   * If the SearchTree is empty, an empty list is returned.
   * 
   * @return List of Keys in level-order
   */
  public List<K> getLevelOrderTraversal() {
    List<K> levelOrderKeys = new ArrayList<K>();

    Integer maxLevel = getHeight();


    if (root == null) {
      return levelOrderKeys;
    }

    for (int i = 1; i <= maxLevel; i++) {
      levelOrderTraversalHelper(root, levelOrderKeys, i);
    }
    return levelOrderKeys;
  }

  /**
   * The levelOrderTraversal recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param keys - The list of keys
   */
  private void levelOrderTraversalHelper(BSTNode<K, V> currentNode, List<K> keys, Integer level) {

    if (currentNode == null) {
      return;
    }
    if (level == 1) {
      keys.add(currentNode.key);
    } else if (level > 1) {
      levelOrderTraversalHelper(currentNode.left, keys, level - 1);
      levelOrderTraversalHelper(currentNode.right, keys, level - 1);
    }
  }

  @Override
  /**
   * Add the key,value pair to the data structure and increase the number of keys. If key is null,
   * throw IllegalNullKeyException; If key is already in data structure, throw
   * DuplicateKeyException(); Do not increase the num of keys in the structure, if key,value pair is
   * not added.
   */
  public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    BSTNode<K, V> newNode = new BSTNode<K, V>(key, value, null, null, null);

    if (root == null) {
      // Changes color of node to black because it is at the root
      root = newNode;
      newNode.makeBlack();
      numKeys++;
      return;
    } else {
      root = insertHelper(root, newNode);
    }
  }

  /**
   * The insert recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param newNode - the new node we want to insert
   * @return - the new tree with the inserted item
   * @throws DuplicateKeyException - if the newNode's key is already in a node in the list
   */
  private BSTNode<K, V> insertHelper(BSTNode<K, V> currentNode, BSTNode<K, V> newNode)
      throws DuplicateKeyException {
    if ((newNode.key.compareTo(currentNode.key)) == 0) {
      throw new DuplicateKeyException();
    } else if ((newNode.key.compareTo(currentNode.key)) < 0 && currentNode.left == null) {
      // Insert into left child
      currentNode.left = newNode;

      // Set parent to current node
      newNode.parent = currentNode;

      // Fixes any RBT Violations
      newNode.rbtFixViolators();

      // Increase the number of keys
      numKeys++;
    } else if ((newNode.key.compareTo(currentNode.key)) > 0 && currentNode.right == null) {
      // Insert into right child
      currentNode.right = newNode;

      // Set parent to current node
      newNode.parent = currentNode;

      // Fixes any RBT Violations
      newNode.rbtFixViolators();

      // Increase the number of keys
      numKeys++;
    } else if ((newNode.key.compareTo(currentNode.key)) < 0) {
      return insertHelper(currentNode.left, newNode);
    } else if ((newNode.key.compareTo(currentNode.key)) > 0) {
      return insertHelper(currentNode.right, newNode);
    }

    while (currentNode.parent != null) {
      if (currentNode.isRed() && currentNode.parent.isRed()) {
        currentNode.rbtFixViolators();
      } else {
        currentNode = currentNode.parent;
      }
    }

    return currentNode;
  }

  public boolean remove(K key, V value) throws IllegalNullKeyException, KeyNotFoundException {
    return isBlack;

  }

  @Override
  /**
   * If key is found, remove the key,value pair from the data structure and decrease num keys. If
   * key is not found, do not decrease the number of keys in the data structure. If key is null,
   * throw IllegalNullKeyException If key is not found, throw KeyNotFoundException().
   */
  public boolean remove(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    boolean isASuccessfulDelete = false;

    isASuccessfulDelete = delete(key);

    if (isASuccessfulDelete) {
      numKeys--;
    }

    return isASuccessfulDelete;
  }

  /**
   * The remove recursive helper method.
   * 
   * @param key - the key we are looking to remove from the tree.
   * @return - true if the item was successfully removed and false otherwise
   * @throws IllegalNullKeyException - if the key passed in is null
   */
  private boolean delete(K key) throws IllegalNullKeyException {
    BSTNode<K, V> current = root;
    BSTNode<K, V> parent = root;

    boolean isLeftChild = false;

    while (current.key != key) {
      parent = current;
      if (current.key.compareTo(key) > 0) {
        // Move to the left if searched value is less
        current = current.left;
        isLeftChild = true;
      } else {
        // Move to the right if searched value is >=
        current = current.right;
        isLeftChild = false;
      }
      if (current == null) {
        return false;
      }
    }
    // if reached here means node to be deleted is found

    // Leaf node deletion case
    if (current.left == null && current.right == null) {
      // if root node is to be deleted
      if (current == root) {
        root = null;
        return true;
      }
      // left child
      else if (isLeftChild) {
        parent.left = null;
      }
      // right child
      else {
        parent.right = null;
      }
    }
    // Node to be deleted has one child case
    // Node to be deleted has right child
    else if (current.left == null) {
      // One right child deletion case
      // if root node is to be deleted
      if (current == root) {
        root = current.right;
        root.parent = null;
        return true;
      }
      // if deleted node is left child
      else if (isLeftChild) {
        parent.left = current.right;
        parent.left.parent = parent;
      }
      // if deleted node is right child
      else {
        parent.right = current.right;
        parent.right.parent = parent;
      }
    }
    // Node to be deleted has left child
    else if (current.right == null) {
      // One left child deletion case
      if (current == root) {
        root = current.left;
        root.parent = null;
        return true;
      }
      // if deleted node is left child
      else if (isLeftChild) {
        parent.left = current.left;
        parent.left.parent = parent;
      }
      // if deleted node is right child
      else {
        parent.right = current.left;
        parent.right.parent = parent;
      }
    }
    // Node to be deleted has two children case
    else {
      // Two children deletion case
      // find in-order successor of the node to be deleted

      BSTNode<K, V> successor = null;

      if (current == root) {
        successor = findMinimumElement(current.right);
      } else if (isLeftChild) {
        successor = findMinimumElement(current.right);
      } else {
        successor = findMinimumElement(current);
      }

      // Does different operations if root
      if (current == root) {
        if (successor != null && successor.parent != null && successor.parent.right != null
            && successor.parent.right.key.compareTo(successor.key) == 0) {
          if (successor.right != null) {
            successor.parent.right = successor.right;
            successor.right.parent = successor.parent;
          } else {
            successor.parent.right = null;
          }
        } else if (successor != null && successor.parent != null && successor.parent.left != null
            && successor.parent.left.key.compareTo(successor.key) == 0) {
          if (successor.right != null) {
            successor.parent.left = successor.right;
            successor.right.parent = successor.parent;

          } else {
            successor.parent.left = null;
          }
        }

        root = successor;



        successor.parent = null;

        if (successor != current.right) {
          successor.right = current.right;
          if (current.right != null) {
            current.right.parent = successor;
          }
        } else if (successor.right.right != null) {
          successor.right = current.right.right;
          current.right.right.parent = successor;
        } else {
          successor.right = null;
        }


        if (successor != current.left) {
          successor.left = current.left;
          current.left.parent = successor;
        } else if (current.left.right != null) {
          successor.left = current.left.right;
          successor.left.parent = successor;
        } else {
          successor.left = current.left.left;
        }

        return true;
      }
      // if deleted node is left child
      else if (isLeftChild) {
        parent.left = successor;
        if (current.right.key.compareTo(successor.key) != 0) {
          successor.right = current.right;
        } else {
          successor.left = current.left;
        }

        root = successor.right;

        if (contains(successor.key)) {

          BSTNode<K, V> successor1;
          if (isLeftChild) {
            successor1 = findMinimumElement(current.right);
          } else {
            successor1 = findMinimumElement(current);
          }
          if (successor1.parent.left.key.compareTo(successor1.key) == 0) {
            // Kils the link to the successor
            successor1.parent.left = null;
          } else {
            successor1.parent.right = null;
          }
        }

        successor.left = current.left;
      }
      // if deleted node is right child
      else {
        parent.right = successor;

        if (current.left.key.compareTo(successor.key) != 0) {
          successor.left = current.left;
        } else {
          successor.right = current.right;
        }

        while (parent.parent != null) {
          parent = parent.parent;
        }

        root = successor.left;

        if (contains(successor.key)) {
          BSTNode<K, V> successor1;
          if (isLeftChild) {
            successor1 = findMinimumElement(current.right);

          } else {
            successor1 = findMinimumElement(current);
          }

          if (successor1.parent.left.key.compareTo(successor1.key) == 0) {
            // Kils the link to the successor
            successor1.parent.left = null;
          } else {
            successor1.parent.right = null;
          }
        }

        successor.right = current.right;
      }
    }

    while (parent.parent != null) {
      parent = parent.parent;
    }

    root = parent;

    return true;
  }

  /**
   * Finds the minimum element in a tree with the passed in node
   * 
   * @param currentNode - the current node in this recursive iteration
   * 
   * @return - the node that is the minimum element
   */
  private BSTNode<K, V> findMinimumElement(BSTNode<K, V> currentNode) {
    if (currentNode.left == null) {
      return currentNode;
    } else {
      return findMinimumElement(currentNode.left);
    }
  }

  @Override
  /**
   * Returns the value associated with the specified key
   *
   * Does not remove key or decrease number of keys If key is null, throw IllegalNullKeyException If
   * key is not found, throw KeyNotFoundException().
   */
  public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    return getHelper(root, key);
  }

  /**
   * The get recursive helper method.
   * 
   * @param currentNode - the current node in this recursive iteration.
   * @param key - the key we are looking for
   * @return - the Value from the node.
   */
  private V getHelper(BSTNode<K, V> currentNode, K key) {
    // If node is null then return null, Base Case
    if (currentNode == null) {
      return null;
    } else if (key.compareTo(currentNode.key) == 0) {
      return currentNode.value;
    } else if (key.compareTo(currentNode.key) < 0) {
      return getHelper(currentNode.left, key);
    } else {
      return getHelper(currentNode.right, key);
    }
  }

  @Override
  /**
   * Returns true if the key is in the data structure If key is null, throw IllegalNullKeyException
   * Returns false if key is not null and is not present
   */
  public boolean contains(K key) throws IllegalNullKeyException {
    if (key == null) {
      throw new IllegalNullKeyException();
    }

    boolean keyIsInList = containsHelper(root, key);

    return keyIsInList;
  }

  /**
   * The contains recursive helper method
   * 
   * @param currentNode - the current node in this recursive iteration
   * @param key - the key we are looking for
   * @return - true if the key was found and false otherwise
   */
  private boolean containsHelper(BSTNode<K, V> currentNode, K key) {
    // If node is null then return null, Base Case
    if (currentNode == null) {
      return false;
    } else if (key.compareTo(currentNode.key) == 0) {
      return true;
    } else if (key.compareTo(currentNode.key) < 0) {
      return containsHelper(currentNode.left, key);
    } else {
      return containsHelper(currentNode.right, key);
    }
  }

  @Override
  /**
   * Returns the number of key,value pairs in the data structure
   */
  public int numKeys() {
    return numKeys;
  }

  @Override
  /**
   * Print the tree.
   *
   * For our testing purposes: all keys that we insert in the tree will have a string length of
   * exactly 2 characters. example: numbers 10-99, or strings aa - zz, or AA to ZZ
   *
   * This makes it easier for you to not worry about spacing issues.
   *
   * You can display in any of a variety of ways, but we should see a tree that we can identify left
   * and right children of each node
   *
   * For example:
   * 
   * | |-------50 |-------40 | |-------35 30 |-------20 | |-------10
   * 
   * Look from bottom to top. Inorder traversal of above tree (10,20,30,35,40,50)
   * 
   * Or, you can display a tree of this kind.
   * 
   * 30 /\ / \ 20 40 / /\ / / \ 10 35 50
   * 
   * Or, you can come up with your own orientation pattern, like this.
   * 
   * 10 20 30 35 40 50
   * 
   * The connecting lines are not required if we can interpret your tree.
   * 
   */
  public void print() {
    List<K> listOfKeys = getLevelOrderTraversal();

    List<K> inOrderKeys = getInOrderTraversal();

    List<K> preOrderKeys = getPreOrderTraversal();

    List<K> postOrderKeys = getPostOrderTraversal();

    // printing in a straight line
    System.out.println("The Nodes in Order are: " + inOrderKeys);

    System.out.println("The Nodes pre Order are: " + preOrderKeys);

    System.out.println("The Nodes post Order are: " + postOrderKeys);

    System.out.println("The Nodes level Order are: " + listOfKeys);

    // printing level wise
    int count = 0;
    int level = 0;

    System.out.println("");

    System.out.println("The General Structure of the Tree: ");

    while (count < listOfKeys.size()) {
      int printLen = (int) Math.pow(2, level++);

      for (int i = count; i < printLen - 1 && i < listOfKeys.size(); ++i) {
        System.out.print("(" + listOfKeys.get(i) + ")" + " ");
      }
      System.out.println();
      count = printLen - 1;
    }
  }
}

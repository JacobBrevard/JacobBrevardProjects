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
import static org.junit.Assert.fail;
import java.awt.List;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

/**
 * Testing class for my RBT.
 * 
 * @author Jacob Brevard
 *
 */
public class BALSTTest {

  BALST<String, String> balst1;
  BALST<Integer, String> balst2;

  /**
   * @throws java.lang.Exception
   */
  @BeforeEach
  void setUp() throws Exception {
    balst1 = createInstance();
    balst2 = createInstance2();
  }

  /**
   * @throws java.lang.Exception
   */
  @AfterEach
  void tearDown() throws Exception {
    balst1 = null;
    balst2 = null;
  }

  /**
   * Creates an instance of the tree.
   * 
   * @return the instance
   */
  protected BALST<String, String> createInstance() {
    return new BALST<String, String>();
  }

  /**
   * Creates an instance of the tree.
   * 
   * @return the instance
   */
  protected BALST<Integer, String> createInstance2() {
    return new BALST<Integer, String>();
  }

  /**
   * Insert three values in sorted order and then check the root, left, and right keys to see if
   * rebalancing occurred.
   */
  @Test
  void testBALST_001_insert_sorted_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("RBT insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfRightChildOf(10).equals(20))
        fail("RBT insert to right child of root does not work");


      balst2.insert(30, "30");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT rotate does not work");



      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }
  }

  /**
   * Insert three values in reverse sorted order and then check the root, left, and right keys to
   * see if rebalancing occurred in the other direction.
   */
  @Test
  void testBALST_002_insert_reversed_sorted_order_simple() {
    try {
      balst2.insert(30, "30");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("RBT insert at root does not work");

      balst2.insert(20, "20");
      if (!balst2.getKeyOfLeftChildOf(30).equals(20))
        fail("RBT insert to left child of root does not work");

      balst2.insert(10, "10");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT rotate does not work");



      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }


  /**
   * Insert three values so that a right-left rotation is needed to fix the balance.
   * 
   * Example: 10-30-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_003_insert_smallest_largest_middle_order_simple() {
    try {
      balst2.insert(10, "10");
      if (!balst2.getKeyAtRoot().equals(10))
        fail("RBT insert at root does not work");

      balst2.insert(30, "30");
      if (!balst2.getKeyOfRightChildOf(10).equals(30))
        fail("RBT insert to left child of root does not work");

      balst2.insert(20, "20");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  /**
   * Insert three values so that a left-right rotation is needed to fix the balance.
   * 
   * Example: 30-10-20
   * 
   * Then check the root, left, and right keys to see if rebalancing occurred in the other
   * direction.
   */
  @Test
  void testBALST_004_insert_largest_smallest_middle_order_simple() {
    try {
      balst2.insert(30, "30");
      if (!balst2.getKeyAtRoot().equals(30))
        fail("RBT insert at root does not work");

      balst2.insert(10, "10");
      if (!balst2.getKeyOfLeftChildOf(30).equals(10))
        fail("RBT insert to left child of root does not work");

      balst2.insert(20, "20");
      Integer k = balst2.getKeyAtRoot();
      if (!k.equals(20))
        fail("RBT rotate does not work");

      // IF rebalancing is working,
      // the tree should have 20 at the root
      // and 10 as its left child and 30 as its right child

      Assert.assertEquals(balst2.getKeyAtRoot(), new Integer(20));
      Assert.assertEquals(balst2.getKeyOfLeftChildOf(20), new Integer(10));
      Assert.assertEquals(balst2.getKeyOfRightChildOf(20), new Integer(30));

    } catch (Exception e) {
      e.printStackTrace();
      fail("Unexpected exception AVL 000: " + e.getMessage());
    }

  }

  @Test
  // Test 006 - Test getKeyOfLeftChildOf(K key). Inserts some keys into the tree and then tries to get
  // the key from the left child of the given key
  void testBALST_006_get_Key_Of_Left_Child_Of_Test() {
    try {
      balst2.insert(2, "Grape");
      balst2.insert(1, "Apple");
      balst2.insert(0, "Orange");
      balst2.insert(4, "Pineapple");
      balst2.insert(7, "Tomato");
      balst2.insert(11, "Pumpkin");
      balst2.insert(13, "MuskMellon");
      balst2.insert(14, "Corn");
      balst2.insert(17, "Tomato");
      balst2.insert(12, "Tomato");
      balst2.insert(99, "Tomato");
      balst2.insert(63, "Tomato");
      
      balst2.print();


      if (balst2.getKeyOfLeftChildOf(13) != 12) {
        fail("The left child key of 13 should be 12 but is actually: "
            + balst2.getKeyOfLeftChildOf(13));
      }

      if (balst2.getKeyOfLeftChildOf(1) != 0) {
        fail("The left child key of 1 should be 0 but is actually: "
            + balst2.getKeyOfLeftChildOf(1));
      }

      if (balst2.getKeyOfLeftChildOf(63) != 17) {
        fail("The left child key of 63 should be 17 but is actually: "
            + balst2.getKeyOfLeftChildOf(63));
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }


  @Test
  // Test 007 - Test getKeyOfRightChildOf(K key). Inserts some keys into the tree and then tries to
  // get the key from the right child of the given key.
  void testBALST_007_get_Key_Of_Right_Child_Of_Test() {
    try {
      balst2.insert(2, "Grape");
      balst2.insert(1, "Apple");
      balst2.insert(0, "Orange");
      balst2.insert(4, "Pineapple");
      balst2.insert(7, "Tomato");
      balst2.insert(11, "Pumpkin");
      balst2.insert(13, "MuskMellon");
      balst2.insert(14, "Corn");
      balst2.insert(17, "Tomato");
      balst2.insert(12, "Tomato");
      balst2.insert(99, "Tomato");
      balst2.insert(63, "Tomato");

      if (balst2.getKeyOfRightChildOf(63) != 99) {
        fail("The right child key of 63 should be 99 but is actually: "
            + balst2.getKeyOfRightChildOf(63));
      }

      if (balst2.getKeyOfRightChildOf(1) != 2) {
        fail("The right child key of 1 should be 2 but is actually: "
            + balst2.getKeyOfRightChildOf(1));
      }

      if (balst2.getKeyOfRightChildOf(14) != 63) {
        fail("The right child key of 14 should be 63 but is actually: "
            + balst2.getKeyOfRightChildOf(14));
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 008 - Test getHeight(). Inserts some keys into the tree and then tries to get
  // the height of the tree to make sure it is correct.
  void testBALST_008_get_Height_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(1, "Apple");

      balst2.insert(0, "Orange");

      balst2.insert(4, "Pineapple");

      balst2.insert(7, "Tomato");

      balst2.insert(11, "Pumpkin");

      balst2.insert(13, "MuskMellon");

      balst2.insert(14, "Corn");

      balst2.insert(17, "Tomato");

      balst2.insert(12, "Tomato");

      balst2.insert(99, "Tomato");

      balst2.insert(63, "Tomato");

      balst2.insert(37, "Tomato");

      balst2.insert(84, "Tomato");

      balst2.insert(85, "Tomato");

      balst2.insert(86, "Tomato");

      balst2.insert(87, "Tomato");

      balst2.insert(88, "Tomato");

      balst2.insert(89, "Tomato");

      if (balst2.getHeight() != 6) {
        fail("The height was incorrect. The height was supposed to be 6 but was actually: "
            + balst2.getHeight());
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }


  @Test
  // Test 009 - Test inOrder Traversal. Inserts some keys into the tree and then tries to get
  // the inOrderTraversal of the tree and then compares the keys in the list returned to those that
  // were expected.
  void testBALST_009_get_in_Order_Traversal_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(4, "Apple");

      balst2.insert(5, "Orange");

      balst2.insert(3, "Pineapple");

      balst2.insert(1, "Tomato");

      balst2.insert(6, "Pumpkin");

      ArrayList inOrderTraversalExpected = new ArrayList();
      inOrderTraversalExpected.add("1");
      inOrderTraversalExpected.add("2");
      inOrderTraversalExpected.add("3");
      inOrderTraversalExpected.add("4");
      inOrderTraversalExpected.add("5");
      inOrderTraversalExpected.add("6");

      ArrayList inOrderTraversalActual = (ArrayList) balst2.getInOrderTraversal();

      for (int i = 0; i < inOrderTraversalActual.size(); i++) {
        if ((inOrderTraversalActual.get(i)) == (inOrderTraversalExpected.get(i))) {
          fail("The value in the list was: " + inOrderTraversalActual.get(i)
              + " The Expected Value was: " + inOrderTraversalExpected.get(i));
        }
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 010 - Test preOrder Traversal. Inserts some keys into the tree and then tries to get
  // the preOrderTraversal of the tree and then compares the keys in the list returned to those that
  // were expected.
  void testBALST_010_get_pre_Order_Traversal_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(4, "Apple");

      balst2.insert(5, "Orange");

      balst2.insert(3, "Pineapple");

      balst2.insert(1, "Tomato");

      balst2.insert(6, "Pumpkin");

      ArrayList preOrderTraversalExpected = new ArrayList();
      preOrderTraversalExpected.add("4");
      preOrderTraversalExpected.add("2");
      preOrderTraversalExpected.add("1");
      preOrderTraversalExpected.add("3");
      preOrderTraversalExpected.add("5");
      preOrderTraversalExpected.add("6");

      ArrayList preOrderTraversalActual = (ArrayList) balst2.getPreOrderTraversal();

      for (int i = 0; i < preOrderTraversalActual.size(); i++) {
        if ((preOrderTraversalActual.get(i)) == (preOrderTraversalExpected.get(i))) {
          fail("The value in the list was: " + preOrderTraversalActual.get(i)
              + " The Expected Value was: " + preOrderTraversalExpected.get(i));
        }
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 011 - Test postOrder Traversal. Inserts some keys into the tree and then tries to get
  // the postOrderTraversal of the tree and then compares the keys in the list returned to those
  // that were expected.
  void testBALST_011_get_post_Order_Traversal_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(4, "Apple");

      balst2.insert(5, "Orange");

      balst2.insert(3, "Pineapple");

      balst2.insert(1, "Tomato");

      balst2.insert(6, "Pumpkin");

      ArrayList postOrderTraversalExpected = new ArrayList();
      postOrderTraversalExpected.add("6");
      postOrderTraversalExpected.add("5");
      postOrderTraversalExpected.add("3");
      postOrderTraversalExpected.add("1");
      postOrderTraversalExpected.add("2");
      postOrderTraversalExpected.add("4");

      ArrayList postOrderTraversalActual = (ArrayList) balst2.getPostOrderTraversal();

      for (int i = 0; i < postOrderTraversalActual.size(); i++) {
        if ((postOrderTraversalActual.get(i)) == (postOrderTraversalExpected.get(i))) {
          fail("The value in the list was: " + postOrderTraversalActual.get(i)
              + " The Expected Value was: " + postOrderTraversalExpected.get(i));
        }
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 012 - Test levelOrder Traversal. Inserts some keys into the tree and then tries to get
  // the levelOrderTraversal of the tree and then compares the keys in the list returned to those
  // that were expected.
  void testBALST_012_level_Order_Traversal_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(4, "Apple");

      balst2.insert(5, "Orange");

      balst2.insert(3, "Pineapple");

      balst2.insert(1, "Tomato");

      balst2.insert(6, "Pumpkin");

      ArrayList levelOrderTraversalExpected = new ArrayList();
      levelOrderTraversalExpected.add("4");
      levelOrderTraversalExpected.add("2");
      levelOrderTraversalExpected.add("5");
      levelOrderTraversalExpected.add("1");
      levelOrderTraversalExpected.add("3");
      levelOrderTraversalExpected.add("6");

      ArrayList levelOrderTraversalActual = (ArrayList) balst2.getLevelOrderTraversal();

      for (int i = 0; i < levelOrderTraversalActual.size(); i++) {
        if ((levelOrderTraversalActual.get(i)) == (levelOrderTraversalExpected.get(i))) {
          fail("The value in the list was: " + levelOrderTraversalActual.get(i)
              + " The Expected Value was: " + levelOrderTraversalExpected.get(i));
        }
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 013 - Test remove(K key). Inserts some keys into the tree and then tries to
  // remove a key from the list that is not present and then remove a key that is present.
  void testBALST_013_remove_Test() {
    try {
      balst2.insert(17, "Grape");

      balst2.insert(23, "Apple");

      balst2.insert(12, "Orange");

      balst2.insert(55, "Pineapple");

      balst2.insert(37, "Tomato");

      balst2.insert(87, "Pumpkin");

      balst2.insert(62, "Lemon");

      balst2.insert(7, "Lime");

      balst2.insert(2, "Luke");

      balst2.insert(1, "Mike");

      if (balst2.remove(92) != false) {
        fail(
            "The remove method should have returned false when trying to remove a key not in the tree");
      }

      if (balst2.numKeys() != 10) {
        fail("The number of keys in the tree should not have changed because the removal failed.");
      }

      if (balst2.remove(7) != true) {
        fail(
            "The remove method should have returned true after sucessfully removing the node with key 7.");
      }

      if (balst2.numKeys() != 9) {
        fail(
            "The number of keys in the list should be one less than the prior because we removed a node from the tree.");
      }

      if (balst2.contains(7)) {
        fail(
            "The contains method for the key 7 should return false because it was removed from the tree.");
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 014 - Test get(K key). Inserts some keys into the tree and then tries to
  // get a key from the list.
  void testBALST_014_get_Test() {
    try {

      balst2.insert(12, "Grape");

      balst2.insert(11, "Apple");

      balst2.insert(10, "Orange");

      balst2.insert(9, "Pineapple");

      balst2.insert(8, "Tomato");

      balst2.insert(7, "Pumpkin");

      balst2.insert(6, "MuskMellon");

      balst2.insert(5, "Corn");

      balst2.insert(4, "George");

      balst2.insert(3, "Mike");

      balst2.insert(2, "Bob");

      balst2.insert(1, "Tomato");

      if (!balst2.get(6).equals("MuskMellon")) {
        fail("The value returned should have been MuskMellon. Instead the actual value is: "
            + balst2.get(6));
      }

      if (!balst2.get(1).equals("Tomato")) {
        fail("The value returned should have been Tomato. Instead the actual value is: "
            + balst2.get(1));
      }

      if (!balst2.get(12).equals("Grape")) {
        fail("The value returned should have been Grape. Instead the actual value is: "
            + balst2.get(12));
      }

    } catch (IllegalNullKeyException e) {
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      e.printStackTrace();
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Test
  // Test 015 - Test contains(K key). Inserts some keys into the tree and then checks to see if the
  // tree contains the key added.
  void testBALST_015_contains_Test() {
    try {

      balst2.insert(12, "Grape");

      balst2.insert(11, "Apple");

      balst2.insert(10, "Orange");

      balst2.insert(9, "Pineapple");

      balst2.insert(8, "Tomato");

      balst2.insert(7, "Pumpkin");

      balst2.insert(6, "MuskMellon");

      balst2.insert(5, "Corn");

      balst2.insert(4, "George");

      balst2.insert(3, "Mike");

      balst2.insert(2, "Bob");

      balst2.insert(1, "Tomato");

      if (!balst2.contains(5)) {
        fail("The contains method should have returned true. Instead the actual value is: "
            + balst2.contains(5));
      }

      if (!balst2.contains(1)) {
        fail("The contains method should have returned true. Instead the actual value is: "
            + balst2.contains(1));
      }

      if (!balst2.contains(8)) {
        fail("The contains method should have returned true. Instead the actual value is: "
            + balst2.contains(8));
      }

      if (balst2.contains(99)) {
        fail("The contains method should have returned false. Instead the actual value is: "
            + balst2.contains(99));
      }

    } catch (IllegalNullKeyException e) {
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      e.printStackTrace();
    }
  }

  @Test
  // Test 016 - Test numKeys(). Inserts some keys into the tree and then checks to see if the
  // number of keys in the list matches the number of nodes inserted into the tree.
  void testBALST_016_num_Keys_Test() {
    try {
      balst2.insert(2, "Grape");

      balst2.insert(1, "Apple");

      balst2.insert(0, "Orange");

      balst2.insert(4, "Pineapple");

      balst2.insert(7, "Tomato");

      balst2.insert(11, "Pumpkin");

      balst2.insert(13, "MuskMellon");

      balst2.insert(14, "Corn");

      balst2.insert(17, "Tomato");

      balst2.insert(12, "Tomato");

      balst2.insert(99, "Tomato");

      balst2.insert(63, "Tomato");

      balst2.insert(37, "Tomato");

      balst2.insert(84, "Tomato");

      balst2.insert(85, "Tomato");

      balst2.insert(86, "Tomato");

      balst2.insert(87, "Tomato");

      balst2.insert(88, "Tomato");

      balst2.insert(89, "Tomato");

      if (balst2.numKeys() != 19) {
        fail("The number of Keys in the BST is incorrect. The number of keys should be 19. "
            + "The number of Keys was actually: " + balst2.numKeys());
      }

    } catch (IllegalNullKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    } catch (DuplicateKeyException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }

  @Test
  // Test 017 - Tests adding a key to the tree, then removing that key, and finally checking if the
  // tree contained that node.
  void testBALST_017_Test_insert_20_remove_20_contains_20() {
    try {
      balst2.insert(20, "Watermellon");

      balst2.remove(20);

      if (balst2.contains(20)) {
        fail("The contains method should return false because 2 was removed");
      }

    } catch (IllegalNullKeyException | DuplicateKeyException | KeyNotFoundException e) {
      fail("Threw an Exception when this test should not have.");
      e.printStackTrace();
    }
  }
}
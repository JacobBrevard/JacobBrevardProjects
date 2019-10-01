//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01 Shopping Cart
// Files:           ShoppingCartTests.java
// Course:          Spring 2019
//
// Author:          Jacob Brevard
// Email:           jbrevard@wisc.edu
// Lecturer's Name: Professor Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
/**
 * This class contains the code for testing the Shopping Cart program
 * 
 * 
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * The Shopping Cart Test Class contains a test bench for the shopping cart
 * class/program. This runs a variety of tests to confirm the accuracy of the
 * program.
 */

public class ShoppingCartTests {

	/**
	 * (TEST 1) Checks whether the total number of items within the cart is
	 * incremented after adding one item
	 * 
	 * Tests the Add Method (First)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testCountIncrementedAfterAddingOnlyOneItem() {
		boolean testPassed = true; // boolean local variable evaluated to true if this test passed,
									// false otherwise
		String[] cart = new String[20]; // shopping cart
		int count = 0; // number of items present in the cart (initially the cart is empty)

		// Add an item to the cart
		count = ShoppingCart.add(3, cart, count); // add an item of index 3 to the cart
		// Check that count was incremented
		if (count != 1) {
			System.out.println("Problem detected: After adding only one item to the cart, "
					+ "the cart count should be incremented. But, it was not the case.");
			testPassed = false;
		}
		return testPassed;
	}

	/**
	 * (TEST 2) Checks whether add and OccurrencesOf return the correct output when
	 * only one item is added to the cart
	 * 
	 * Tests the Add and Occurrences Of Methods (Second, First)
	 * 
	 * @return true if test passed without problems, false otherwise
	 */
	public static boolean testAddAndOccurrencesOfForOnlyOneItem() {
		boolean testPassed = true; // evaluated to true if test passed without problems, false
									// otherwise
		// define the shopping cart as an oversize array of elements of type String
		// we can set an arbitrary capacity for the cart - for instance 10
		String[] cart = new String[10]; // shopping cart
		int count = 0; // number of items present in the cart (initially the cart is empty)

		// check that OccurrencesOf returns 0 when called with an empty cart
		if (ShoppingCart.occurrencesOf(10, cart, count) != 0) {
			System.out.println(
					"Problem detected: Tried calling OccurrencesOf() method when the cart is "
							+ "empty. The result should be 0. But, it was not.");
			testPassed = false;
		}

		// add one item to the cart
		count = ShoppingCart.add(0, cart, count); // add an item of index 0 to the cart

		// check that OccurrencesOf("Apples", cart, count) returns 1 after adding the
		// item with key 0
		if (ShoppingCart.occurrencesOf(0, cart, count) != 1) {
			System.out
					.println("Problem detected: After adding only one item with key 0 to the cart, "
							+ "OccurrencesOf to count how many of that item the cart contains should return 1. "
							+ "But, it was not the case.");
			testPassed = false;
		}

		return testPassed;
	}

	/**
	 * (TEST 3) Checks that items can be added more than one time and are found
	 * 
	 * Tests the Occurrences of Method (Second)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testAddOccurrencesOfDuplicateItems() {
		// Initializes Cart and capacity
		String[] cart = new String[20];
		int count = 0;

		// Adds Items to cart including some duplicates of items
		count = ShoppingCart.add(0, cart, count);
		count = ShoppingCart.add(1, cart, count);
		count = ShoppingCart.add(2, cart, count);
		count = ShoppingCart.add(3, cart, count);
		count = ShoppingCart.add(2, cart, count);
		count = ShoppingCart.add(4, cart, count);
		count = ShoppingCart.add(2, cart, count);

		// Initializes the variable to hold the index of the item
		int itemIndex = 2;

		// Calls the Occurrence of method to make sure they find 3 occurrences of the
		// item
		if (ShoppingCart.occurrencesOf(itemIndex, cart, count) != 3) {
			return false;
		}

		// If they method returns 3 occurrences then the test method returns true
		return true;
	}

	/**
	 * (TEST 4) Checks that the correct output is returned when the user tries to
	 * add too much items to the cart exceeding its capacity.
	 * 
	 * Tests the add method (Third)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */

	public static boolean testAddingTooMuchItems() {
		// Initializes Cart and Count
		String[] cart = new String[20];
		int count = 20;

		// For Loop to fill in cart with 20 Avocados
		for (int i = 0; i <= cart.length - 1; i++) {
			cart[i] = "Avocado";
		}

		// Calls the add method with a full cart
		if (ShoppingCart.add(1, cart, count) != 20) {
			return false;
		}

		// If the method returns the same count of 20 then we return true
		return true;
	}

	/**
	 * (TEST 5) Checks that when only one attempt to remove an item present in the
	 * cart is made, only one occurrence of that item is removed from the cart.
	 * 
	 * Tests the remove method (First)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testRemoveOnlyOneOccurrenceOfItem() {
		// Initialize cart capacity to 5, count and itemToRemove to Blueberry
		String[] cart = new String[5];
		int count = 0;
		String itemToRemove = "Blueberry";

		// Calls the add method several times to add items to the cart
		count = ShoppingCart.add(1, cart, count);
		count = ShoppingCart.add(4, cart, count);
		count = ShoppingCart.add(3, cart, count);
		count = ShoppingCart.add(4, cart, count);
		count = ShoppingCart.add(5, cart, count);

		// Calls the remove method
		ShoppingCart.remove(itemToRemove, cart, count);

		// Checks to see that the first occurrence of blueberry has been removed
		if ((cart[1] == "Blueberry") || (cart[3] != "Blueberry")) {
			return false;
		}

		// If it has been removed then we return true
		return true;
	}

	/**
	 * (TEST 6) Checks that remove returns false when the user tries to remove an
	 * item not present within the cart
	 * 
	 * Tests the remove Method (Second)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testRemoveItemNotFoundInCart() {
		// Initialize Cart to a capacity of 5, count to 0, and Item to remove to Apple.
		String[] cart = new String[5];
		int count = 0;
		String itemToRemove = "Apple";

		// Adds items to the cart
		count = ShoppingCart.add(1, cart, count);
		count = ShoppingCart.add(2, cart, count);
		count = ShoppingCart.add(3, cart, count);
		count = ShoppingCart.add(4, cart, count);
		count = ShoppingCart.add(5, cart, count);

		// Checks if the call correctly returns 5
		if (ShoppingCart.remove(itemToRemove, cart, count) != 5) {
			return false;
		}

		return true;
	}

	/**
	 * (TEST 7) Checks that the get sub total price method returns the correct total
	 * for the prices of the items within the cart.
	 * 
	 * Tests the Sub Total Method (First)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testGetSubTotalPrice() {
		// Initialize cart and count
		String[] cart = new String[5];
		int count = 0;

		// Variable stores the expected sub total after calling the method
		double totalExpected = 0.59 + 0.49 + 3.79 + 6.89 + 1.79;

		// Adds Items to the cart
		count = ShoppingCart.add(1, cart, count);
		count = ShoppingCart.add(2, cart, count);
		count = ShoppingCart.add(3, cart, count);
		count = ShoppingCart.add(4, cart, count);
		count = ShoppingCart.add(5, cart, count);

		if (ShoppingCart.getSubTotalPrice(cart, count) != totalExpected) {
			return false;
		}

		return true;
	}

	/**
	 * (TEST 8) Calls the Print Market Catalog method to check if it prints out the
	 * correct output.
	 * 
	 * Tests the print Market Catalog Method (First)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testprintMarketCatalog() {
		// Prints out the market catalog
		ShoppingCart.printMarketCatalog();
		return true;
	}

	/**
	 * (TEST 9) Checks that remove returns 0 when the user tries to remove an item
	 * from an empty cart
	 * 
	 * Tests the remove method (Third)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testRemoveWithEmptyCart() {
		// Initialize variables for test
		String[] cart = new String[20];
		int count = 0;
		String itemToRemove = "Apple";

		if (ShoppingCart.remove(itemToRemove, cart, count) != 0) {
			return false;
		}

		return true;
	}

	/**
	 * (TEST 10) Checks that the get sub total price method returns the correct
	 * total for the prices of the items within the cart. Uses a different cart
	 * capacity.
	 * 
	 * Tests the Sub Total Method (Second)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testGetSubTotalPriceSecond() {
		// Initialize Variables
		String[] cart = new String[40];
		int count = 0;

		// Variable stores the expected sub total after calling the method
		double totalExpected = 1.99 + 11.5 + 0.69 + 3.09 + 1.79 + 1.79 + 1.79;

		// Adds the items to the cart to be sub totaled
		count = ShoppingCart.add(20, cart, count);
		count = ShoppingCart.add(21, cart, count);
		count = ShoppingCart.add(22, cart, count);
		count = ShoppingCart.add(23, cart, count);
		count = ShoppingCart.add(24, cart, count);
		count = ShoppingCart.add(24, cart, count);
		count = ShoppingCart.add(24, cart, count);

		if (ShoppingCart.getSubTotalPrice(cart, count) != totalExpected) {
			return false;
		}

		return true;
	}

	/**
	 * (TEST 11) Checks that the get sub total price method returns the correct
	 * total for the prices of the items within the cart. Uses a different cart
	 * capacity.
	 * 
	 * Tests the Sub Total Method (Third)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testGetSubTotalPriceThird() {
		// Initialize Variables
		String[] cart = new String[3];
		int count = 0;

		// Variable stores the expected sub total after calling the method
		double totalExpected = 1.19 + 3.69 + 3.49;

		// Adds the items to the cart to be sub totaled
		count = ShoppingCart.add(7, cart, count);
		count = ShoppingCart.add(8, cart, count);
		count = ShoppingCart.add(9, cart, count);

		if (ShoppingCart.getSubTotalPrice(cart, count) != totalExpected) {
			return false;
		}

		return true;
	}

	/**
	 * (TEST 12) Checks that the Index of method returns the correct value
	 * 
	 * Tests the Display Cart Items Method (First)
	 * 
	 * @return true if the test passes without problems, false otherwise
	 */
	public static boolean testDisplayCartItems() {
		// Initialize Variables
		String[] cart = new String[20];
		int count = 0;

		// Adds the items to the cart
		count = ShoppingCart.add(7, cart, count);
		count = ShoppingCart.add(13, cart, count);
		count = ShoppingCart.add(2, cart, count);
		count = ShoppingCart.add(18, cart, count);
		count = ShoppingCart.add(22, cart, count);
		count = ShoppingCart.add(21, cart, count);
		count = ShoppingCart.add(4, cart, count);

		ShoppingCart.displayCartContent(cart, count);

		return true;
	}

	/**
	 * main method used to call the unit tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// Test 1
		System.out.println("TEST 1: testCountIncrementedAfterAddingOnlyOneItem(): "
				+ testCountIncrementedAfterAddingOnlyOneItem());
		System.out.println("");

		// Test 2
		System.out.println("TEST 2: testAddAndOccurrencesOfForOnlyOneItem(): "
				+ testAddAndOccurrencesOfForOnlyOneItem());
		System.out.println("");

		// Test 3
		System.out.println("TEST 3: testAddingTooMuchItems(): " + testAddingTooMuchItems());
		System.out.println("");

		// Test 4
		System.out.println("TEST 4: testAddOccurrencesOfDuplicateItems(): "
				+ testAddOccurrencesOfDuplicateItems());
		System.out.println("");

		// Test 5
		System.out.println(
				"TEST 5: testRemoveItemNotFoundInCart(): " + testRemoveItemNotFoundInCart());
		System.out.println("");

		// Test 6
		System.out.println("TEST 6: testRemoveOnlyOneOccurrenceOfItem(): "
				+ testRemoveOnlyOneOccurrenceOfItem());
		System.out.println("");

		// Test 7
		System.out.println("TEST 7: testGetSubTotalPrice(): " + testGetSubTotalPrice());
		System.out.println("");

		// Test 8
		System.out.println("TEST 8: testprintMarketCatalog(): " + testprintMarketCatalog());
		System.out.println("");

		// Test 9
		System.out.println("TEST 9: testRemoveWithEmptyCart(): " + testRemoveWithEmptyCart());
		System.out.println("");

		// Test 10
		System.out
				.println("TEST 10: testGetSubTotalPriceSecond(): " + testGetSubTotalPriceSecond());
		System.out.println("");

		// Test 11
		System.out.println("TEST 11: testGetSubTotalPriceThird(): " + testGetSubTotalPriceThird());
		System.out.println("");

		// Test 12
		System.out.println("TEST 12: testDisplayCartItems(): " + testDisplayCartItems());
		System.out.println("");
	}
}
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01 Shopping Cart
// Files:           ShoppingCart.java
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
 * This class contains the code for the Shopping Cart program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

import java.util.Scanner;

/**
 * The Shopping Cart Class contains methods for the shopping cart class/program.
 * They run a variety of different operations for each method that all
 * contribute to the shopping cart application.
 * 
 */
public class ShoppingCart {
	// Define final parameters
	private static final int CART_CAPACITY = 20; // shopping cart max capacity
	private static final double TAX_RATE = 0.05; // sales tax

	// a perfect-size two-dimensional array that stores the available items in the
	// market
	// MARKET_ITEMS[i][0] refers to a String that represents the description of the
	// item
	// identified by index i
	// MARKET_ITEMS[i][1] refers to a String that represents the unit price of the
	// item
	// identified by index i in dollars.
	public static final String[][] MARKET_ITEMS = new String[][] { { "Apple", "$1.59" },
			{ "Avocado", "$0.59" }, { "Banana", "$0.49" }, { "Beef", "$3.79" },
			{ "Blueberry", "$6.89" }, { "Broccoli", "$1.79" }, { "Butter", "$4.59" },
			{ "Carrot", "$1.19" }, { "Cereal", "$3.69" }, { "Cheese", "$3.49" },
			{ "Chicken", "$5.09" }, { "Chocolate", "$3.19" }, { "Cookie", "$9.5" },
			{ "Cucumber", "$0.79" }, { "Eggs", "$3.09" }, { "Grape", "$2.29" },
			{ "Ice Cream", "$5.39" }, { "Milk", "$2.09" }, { "Mushroom", "$1.79" },
			{ "Onion", "$0.79" }, { "Pepper", "$1.99" }, { "Pizza", "$11.5" },
			{ "Potato", "$0.69" }, { "Spinach", "$3.09" }, { "Tomato", "$1.79" } };

	/**
	 * Removes the first (only one) occurrence of itemToRemove if found and returns
	 * the number of items in the cart after remove operation is completed either
	 * successfully or not.
	 * 
	 * @param count
	 *            The total number of items in the cart
	 * 
	 * @param itemToRemove
	 *            The item to remove from Market Items
	 * @param cart
	 *            shopping cart
	 * @return the updated count after the selected item is removed from the list.
	 *         If the item is not found it returns the original value of count and
	 *         displays a warning/error message to the console.
	 */
	public static int remove(String itemToRemove, String[] cart, int count) {
		// If the count is 0 we return count immediately because you cannot remove an
		// item from an empty cart.
		if (count == 0) {
			return count;
		}

		// Calls the index of method which returns the index of the first occurrence of
		// the item.
		int index = indexOf(itemToRemove, cart, count);

		// Prints out warning and returns count if the item is not found.
		if (index == -1) {
			System.out.println("WARNING: " + itemToRemove + " not found in the shopping cart.");
			return count;
		}

		// If the index is valid then we move the last item in the grocery list to the
		// selected index
		cart[index] = cart[count - 1];

		count--;

		return count;
	}

	/**
	 * Finds the total cost of all the items in the shopping cart excluding tax.
	 * 
	 * @param cart
	 *            shopping cart
	 * 
	 * @param count
	 *            The total number of items in the cart
	 * 
	 * @return the total value (cost) of the cart without tax in $ (double)
	 * 
	 */
	public static double getSubTotalPrice(String[] cart, int count) {
		// Variable total cost is used to keep track of the sub total
		double totalCost = 0.0;

		// Variable groceryItem is used to temporarily hold the food item as a string
		String groceryItem = "";

		// Loop to iterate through the cart and call the getPrice helper method and add
		// all of the prices of the food items to the totalCost variable to be returned
		// at the end
		for (int i = 0; i <= count - 1; i++) {
			groceryItem = cart[i];
			totalCost += getPrice(groceryItem);
		}
		// Return the sub total
		return totalCost;
	}

	/**
	 * Prints the Market Catalog (item identifiers, description, and unit prices).
	 * 
	 */
	public static void printMarketCatalog() {
		// Variable used to help print out the catalog with certain whitespace
		// requirements
		int spacesBetweenItems = 17;

		// Prints out the fancy title/outline
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Item id         Description      Price");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");

		// Prints out Items 0 through 9. The for loop iterates through printing out the
		// catalog piece by piece

		// The outer for loop iterates through printing the foods and necessary
		// whitespace requirements
		for (int i = 0; i < 10; i++) {
			spacesBetweenItems = 17;

			System.out.print(i + "               " + MARKET_ITEMS[i][0]);

			spacesBetweenItems = spacesBetweenItems - MARKET_ITEMS[i][0].length();

			// The inner for loop iterates through printing the prices and necessary
			// whitespace requirements
			for (int j = 0; j <= spacesBetweenItems - 1; j++) {
				System.out.print(" ");
			}
			System.out.println(MARKET_ITEMS[i][1] + "       ");
		}

		// Prints out the market items with a double digit ID. Separated the two for
		// loops for whitespace reasons.

		// The outer for loop iterates through printing the foods and necessary
		// whitespace requirements
		for (int i = 10; i < MARKET_ITEMS.length; i++) {
			spacesBetweenItems = 17;

			System.out.print(i + "              " + MARKET_ITEMS[i][0]);

			spacesBetweenItems = spacesBetweenItems - MARKET_ITEMS[i][0].length();

			// The inner for loop iterates through printing the prices and necessary
			// whitespace requirements
			for (int j = 0; j <= spacesBetweenItems - 1; j++) {
				System.out.print(" ");
			}
			System.out.println(MARKET_ITEMS[i][1] + "       ");
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
	}

	/**
	 * 
	 * Displays the cart content (items separated by commas).
	 * 
	 * @param count
	 *            The total number of items in the cart
	 * 
	 * @param cart
	 *            shopping cart
	 */
	public static void displayCartContent(String[] cart, int count) {
		System.out.print("Cart Content: ");
		System.out.print(cart[0]);

		// Loop is used to print out the items in the cart
		for (int i = 1; i <= count - 1; i++) {
			System.out.print(", " + cart[i]);
		}
		System.out.print(", ");
		System.out.println("");
	}

	/**
	 * adds the item with the given its identifier index at the end of the cart
	 * 
	 * @param count
	 *            The total number of items in the cart
	 * 
	 * @param index
	 *            of the item within the marketItems array
	 * @param cart
	 *            shopping cart
	 * @return the number of items present in the cart after the item with
	 *         identifier index is added
	 */
	public static int add(int index, String[] cart, int count) {
		// If the count is larger than the carts length then the cart is at its capacity
		// and the user cannot add another item. Prints out warning message.
		if (count >= cart.length) {
			System.out.println("WARNING: The cart is full. You cannot add any new item.");
			return count;
		}

		// Adds the item to the cart at the end
		cart[count] = MARKET_ITEMS[index][0];

		// Adds one to count signifying that you added another item
		count += 1;

		// Returns the running total on the number of items in the cart
		return count;
	}

	/**
	 * Returns how many occurrences of the item with index itemIndex are present in
	 * the shopping cart
	 * 
	 * @param itemIndex
	 *            identifier of the item to count its occurrences in the cart
	 * @param cart
	 *            shopping cart
	 * @param count
	 *            number of items present within the cart
	 * @return the number of occurrences of item in the cart
	 */
	public static int occurrencesOf(int itemIndex, String[] cart, int count) {
		// Calls the getItemDescription helper method which returns the food item at the
		// given index of the Market items array
		String itemDescription = getItemDescription(itemIndex);

		// The variable occurrencesOfItem keeps track of the number of times an item is
		// in the cart.
		int occurencesOfItem = 0;

		// The for loop iterates through the cart adding 1 to occurrencesOfItem
		// every time it finds the item in the cart
		for (int i = 0; i <= count - 1; i++) {
			if (cart[i].equals(itemDescription)) {
				occurencesOfItem += 1;
			}
		}

		// Then we return the occurrences of the item
		return occurencesOfItem;
	}

	/**
	 * Finds the item in the Market List.
	 * 
	 * @param index
	 *            of the selected item.
	 * 
	 * @return the Market item selected by the given index.
	 * 
	 */
	private static String getItemDescription(int index) {
		// Return the food item at the given index passed in
		return MARKET_ITEMS[index][0];
	}

	/**
	 * Returns the index of an item within the shopping cart
	 * 
	 * @param item
	 *            description
	 * @param cart
	 *            Shopping cart
	 * @param count
	 *            number of items present in the shopping cart
	 * @return index of the item within the shopping cart, and -1 if the item does
	 *         not exist in the cart
	 */
	private static int indexOf(String item, String[] cart, int count) {
		// The loop iterates through the cart looking for the first occurrence of an
		// item. Then it returns that index.
		for (int i = 0; i <= count - 1; i++) {
			if (cart[i].equals(item)) {
				return i;
			}
		}

		// If the item is not found then we just return -1
		return -1;
	}

	/**
	 * Returns the cost of an item in the shopping cart
	 * 
	 * @param item
	 *            description
	 * @return index of the item within the shopping cart, and -1 if the item does
	 *         not exist in the cart
	 */

	private static double getPrice(String item) {
		// price temporarily holds the string representation of the cost for an item
		String price = "";

		// The for loop iterates through the Market Items array to match the item and
		// then get the given price
		for (int i = 0; i <= MARKET_ITEMS.length - 1; i++) {
			if (MARKET_ITEMS[i][0].equals(item)) {
				price = MARKET_ITEMS[i][1];

				// Substring gets rid of the dollar ($) sign
				price = price.substring(1);

				// Converts the string to a double
				Double costOfItem = Double.valueOf(price);

				// Returns the cost of the found item
				return costOfItem;
			}
		}

		return 0.0;
	}

	/**
	 * Prints out the command menu for the user to read.
	 * 
	 * 
	 */

	public static void printCommandMenu() {
		System.out.println("COMMAND MENU:");

		System.out.println(" [P] print the market catalog");

		System.out.println(
				" [A <index>] add one occurrence of an item to the cart given its identifier");

		System.out.println(" [C] checkout");

		System.out.println(" [D] display the cart content");

		System.out.println(
				" [O <index>] number of occurrences of an item in the cart given its identifier");

		System.out.println(
				" [R <index>] remove one occurrence of an item from the cart given its identifier");

		System.out.println(" [Q]uit the application");
	}

	/**
	 * Main method used to run the function shopping cart program and call the
	 * necessary supporting methods.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		// Prints out the welcome message for the program with the proper whitespace
		System.out.println("=============   Welcome to the Shopping Cart App   =============");
		System.out.println("");
		System.out.println("");

		// Initialize cart with the field Cart Capacity
		String[] cart = new String[CART_CAPACITY];

		// Initialize count to zero because the cart starts with no items in it
		int count = 0;

		// Create an instance of scanner to read user input
		Scanner userInput = new Scanner(System.in);

		// Declare the variable UserInputValue to store the first letter typed
		char userInputFirstLetter;

		// Used to store the full string entered by the user
		String userInputTyped = "";

		// Loop variable used to terminate the loop once the program is finished
		boolean quit = false;

		// While loop is used to repeat the interaction with the user and the program
		while (!quit) {

			// Calls the method to print out the different commands the user can select from
			printCommandMenu();

			System.out.println("");
			System.out.print("ENTER COMMAND: ");

			// Reads the full line of user input
			userInputTyped = userInput.nextLine();

			// Processes the user input to have no leading or trailing whitespace
			userInputTyped = userInputTyped.trim();

			// Changes the string to all lower case and then gets the first character
			userInputFirstLetter = userInputTyped.toLowerCase().charAt(0);

			// Set of conditionals to check whether the first letter is one of the commands
			if (userInputFirstLetter == 'p') {
				// Calls the method to print out the market catalog
				printMarketCatalog();

			} else if (userInputFirstLetter == 'c') {
				// Calls the sub total method and stores the value returned in the double
				// variable
				double subTotal = getSubTotalPrice(cart, count);

				// Determines the tax amount by multiplying the sub total by the tax rate and
				// then storing it in the double variable
				double taxAmount = subTotal * TAX_RATE;

				// Add the tax to the sub total to come up with the final cost
				double finalCost = subTotal + taxAmount;

				// Prints out the three desired values with the proper formating and whitespace
				// requirements
				System.out.println(
						"#items: " + count + " Subtotal: $" + String.format("%.2f", subTotal)
								+ " Tax: $" + String.format("%.2f", taxAmount) + " TOTAL: $"
								+ String.format("%.2f", finalCost));

			} else if (userInputFirstLetter == 'd') {
				// Calls the method to display the carts content
				displayCartContent(cart, count);

			} else if (userInputFirstLetter == 'q') {
				// Sets the loop variable to true so it will exit the loop and terminate the
				// program
				quit = true;

			} else if (userInputFirstLetter == 'a') {
				// The substring method trims the string to only contain the index number in
				// string format
				userInputTyped = userInputTyped.substring(2);

				// Convert the string into an integer
				Integer index = Integer.parseInt(userInputTyped);

				// Calls the add method with the given parameter values
				count = add(index, cart, count);

			} else if (userInputFirstLetter == 'o') {
				// The substring method trims the string to only contain the index number in
				// string format
				userInputTyped = userInputTyped.substring(2);

				// Convert the string into an integer
				Integer index = Integer.parseInt(userInputTyped);

				// Calls the occurrencesOf method and then outputs the number of occurrences
				// found
				System.out.println("The number of occurrences of " + getItemDescription(index)
						+ " (id #" + index + ") is: " + occurrencesOf(index, cart, count));

			} else if (userInputFirstLetter == 'r') {
				// The substring method trims the string to only contain the index number in
				// string format
				userInputTyped = userInputTyped.substring(2);

				// Convert the string into an integer
				Integer index = Integer.parseInt(userInputTyped);

				// Calls the remove method and then updates the count variable
				count = remove(getItemDescription(index), cart, count);

			} else {
				// Not a valid input
				// This is here so if nothing matched it will just loop back through looking for
				// a new user input
			}

			// Always prints out a new line unless quit is set to true which means that they
			// exit the loop
			if (quit != true) {
				System.out.println("");
			}

		}

		// Closes the scanner to conserve resources
		userInput.close();

		// Prints out the final closing statement
		System.out.println("=============  Thank you for using this App!!!!!  =============");
	}
}
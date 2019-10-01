//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            TextConverter
// Files:            TextConverter.java
// Semester:         Fall 2018
//
// Author:           Jacob Brevard
// Email:            jbrevard@wisc.edu
// CS Login:         jbrevard
// Lecturer's Name:  Professor Williams
// Lab Section:      312
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// No help received from any person or other source.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains the code for drawing a simple text frame. For example,
 * the following is a frame of size 4.
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */
import java.util.Scanner;

public class TextConverter {

	/**
	 * 1337 - convert the string to leet-speak: Replace each L or l with a 1
	 * (numeral one) Replace each E or e with a 3 (numeral three) Replace each T or
	 * t with a 7 (numeral seven) Replace each O or o with a 0 (numeral zero)
	 * Replace each S or s with a $ (dollar sign)
	 * 
	 * @param current
	 *            Original string
	 * @return string converted to leet-speak.
	 */
	public static String action1337(String current) {

		for (int i = 0; i <= current.length() - 1; i++) {

			if ((current.charAt(i) == 'L') || (current.charAt(i) == 'l')) {
				current = current.replace("L", "1");
				current = current.replace("l", "1");
			} else if ((current.charAt(i) == 'E') || (current.charAt(i) == 'e')) {
				current = current.replace("E", "3");
				current = current.replace("e", "3");
			} else if ((current.charAt(i) == 'T') || (current.charAt(i) == 't')) {
				current = current.replace("T", "7");
				current = current.replace("t", "7");
			} else if ((current.charAt(i) == 'O') || (current.charAt(i) == 'o')) {
				current = current.replace("O", "0");
				current = current.replace("o", "0");
			} else if ((current.charAt(i) == 'S') || (current.charAt(i) == 's')) {
				current = current.replace("S", "$");
				current = current.replace("s", "$");
			} else {

			}

		}
		return current;
	}

	// Use for loop and string.lenth to iterate through the string and use
	// conditionals to check and change the needed letters

	/**
	 * tests action1337 method with various cases to ensure it is working correctly.
	 */
	public static void testAction1337() {
		boolean error = false;

		String input1 = "LEeTs";
		String expected1 = "1337$";
		String result1 = action1337(input1);
		if (!result1.equals(expected1)) {
			error = true;
			System.out.println(
					"1) testAction1337 with input " + input1 + ", expected: " + expected1 + " but result:" + result1);
		}

		String input2 = "OJacobS";
		String expected2 = "0Jac0b$";
		String result2 = action1337(input2);

		if (!result2.equals(expected2)) {
			error = true;
			System.out.println(
					"1) testAction1337 with input " + input2 + ", expected: " + expected2 + " but result:" + result2);
		}

		String input3 = "OOOOwen";
		String expected3 = "0000w3n";
		String result3 = action1337(input3);

		if (!result3.equals(expected3)) {
			error = true;
			System.out.println(
					"1) testAction1337 with input " + input3 + ", expected: " + expected3 + " but result:" + result3);
		}

		// FIX ME
		// add at least 2 other test cases. What edge cases can you think of?

		if (error) {
			System.out.println("testAction1337 failed");
		} else {
			System.out.println("testAction1337 passed");
		}
	}

	/**
	 * This reverses the order of characters in the current string.
	 * 
	 * @param current
	 *            Original string to be reversed.
	 * @return The string in reversed order.
	 */
	public static String actionReverse(String current) {
		String reverse = "";

		for (int i = current.length() - 1; i >= 0; i--) {
			reverse = reverse + current.charAt(i);
		}

		return reverse; // FIX ME
	}

	/**
	 * tests actionReverse method with various cases to ensure it is working
	 * correctly.
	 */
	public static void testActionReverse() {
		boolean error = false;

		String input1 = "Abc";
		String expected1 = "cbA";
		String result1 = actionReverse(input1);
		if (!result1.equals(expected1)) {
			error = true;
			System.out.println("1) testActionReverse with input " + input1 + ", expected: " + expected1 + " but result:"
					+ result1);
		}

		String input2 = "CDFABQ";
		String expected2 = "QBAFDC";
		String result2 = actionReverse(input2);

		if (!result2.equals(expected2)) {
			error = true;
			System.out.println("1) testActionReverse with input " + input2 + ", expected: " + expected2 + " but result:"
					+ result2);
		}

		String input3 = "ABCDEFGHIJK";
		String expected3 = "KJIHGFEDCBA";
		String result3 = actionReverse(input3);

		if (!result3.equals(expected3)) {
			error = true;
			System.out.println("1) testActionReverse with input " + input3 + ", expected: " + expected3 + " but result:"
					+ result3);
		}

		if (error) {
			System.out.println("testActionReverse failed");
		} else {
			System.out.println("testActionReverse passed");
		}
	}

	/**
	 * Provides the main menu for the text converter and calls methods based on menu
	 * options chosen.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		System.out.println("Welcome to the Text Converter.");
		System.out.println("Available Actions:");
		System.out.println("  1337) convert to 1337-speak");
		System.out.println("  rev) reverse the string");
		System.out.println("  quit) exit the program");
		System.out.println("");
		System.out.print("Please enter a string: ");

		String a = scnr.nextLine();
		String action = scnr.nextLine();
		int i = 0;
		while (!action.equals("quit")) {

			if (action.equals("1337")) {
				System.out.println("Action (1337, rev, quit): " + action1337(a));
				a = action1337(a);
			} else if (action.equals("rev")) {
				System.out.println("Action (1337, rev, quit): " + actionReverse(a));
				a = actionReverse(a);
			} else {
				System.out.println("Action (1337, rev, quit): Unrecognized action.");
				System.out.println(a);
			}
			if (i == 0) {
				action = scnr.nextLine();
			}

		}
		System.out.println("Action (1337, rev, quit): See you next time!");

		scnr.close();

		// testAction1337(); // uncomment to run the tests
		// testActionReverse(); // uncomment to run the tests

		// FIX ME
	}

}

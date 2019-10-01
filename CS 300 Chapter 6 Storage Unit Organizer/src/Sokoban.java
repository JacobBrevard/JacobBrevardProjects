//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Sokoban
// Files:            Sokoban.java
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
 * This class contains the code for the Sokoban Game
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class Sokoban {

	/**
	 * Prompts the user for a value by displaying prompt. Note: This method should
	 * not add a new line to the output of prompt.
	 *
	 * After prompting the user, the method will consume an entire line of input
	 * while reading an int. If the value read is between min and max (inclusive),
	 * that value is returned. Otherwise, "Invalid value." terminated by a new line
	 * is output to the console and the user is prompted again.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in.
	 * @param prompt
	 *            The name of the value for which the user is prompted.
	 * @param min
	 *            The minimum acceptable int value (inclusive).
	 * @param max
	 *            The maximum acceptable int value (inclusive).
	 * @return Returns the value read from the user.
	 */
	public static int promptInt(Scanner sc, String prompt, int min, int max) {
		boolean wStatement = false; // Loop Variable

		while (!wStatement) { // Loops the prompt int method until a valid integer is entered
			System.out.print(prompt);

			if (sc.hasNextInt()) { // Checks if the next token is an integer
				int temp = sc.nextInt();
				if ((temp >= min) && (temp <= max)) { // Checks if it is in between the given max and min values

					sc.nextLine(); // Clears the newline
					return temp;
				} else if (temp == -1) {
					sc.nextLine();
					return -1;
				} else {
					System.out.println("Invalid value.");
					sc.nextLine(); // Clears the newline
				}
			} else if (!sc.hasNextInt()) {
				System.out.println("Invalid value.");
				sc.nextLine(); // Clears the newline

			}

		}

		return -1;
	}

	/**
	 * Prompts the user for a char value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input and
	 * return the first non-whitespace character converted to lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @return Returns the first non-whitespace character (in lower case) read from
	 *         the user. If there are no non-whitespace characters read, the null
	 *         character is returned.
	 */
	public static char promptChar(Scanner sc, String prompt) {

		boolean wStatement = false; // Loop Variable

		while (!wStatement) {
			System.out.print(prompt);
			String temp = sc.nextLine(); // Stores the next line of input in the string variable temp
			temp = temp.trim(); // Removes leading and trailing whitespace
			temp = temp.toLowerCase(); // Converts all the letters to lower case

			if ((temp != null) && (temp.length() == 1)) { // Checks if input was null or more than 1 character
				if (temp.equals("y")) {
					return (temp.charAt(0)); // Takes the first letter of the string and converts it to a char
				} else if (temp.equals("n")) {
					return (temp.charAt(0));
				} else {
					System.out.println("Invalid value."); // If the string is not "y" or "n" it will ask for a valid
															// value
				}

			} else {
				System.out.println("Invalid value."); // If the string is longer than length 1 it will re loop asking
														// for a valid input
			}

		}
		return '\u0000';
	}

	/**
	 * Prompts the user for a string value by displaying prompt. Note: This method
	 * should not be a new line to the output of prompt.
	 *
	 * After prompting the user, the method will read an entire line of input,
	 * remove any leading and trailing whitespace, and return the input converted to
	 * lower case.
	 *
	 * @param sc
	 *            The Scanner instance to read from System.in
	 * @param prompt
	 *            The user prompt.
	 * @return Returns the string entered by the user, converted to lower case with
	 *         leading and trailing whitespace removed.
	 */
	public static String promptString(Scanner sc, String prompt) {
		boolean wStatement = false; // Loop variable

		while (!wStatement) {
			System.out.print(prompt);
			String temp = sc.nextLine(); // Stores next line of input in the String temp variable

			if ((temp != null)) {
				temp = temp.toLowerCase(); // Changes the sting to lower case
				temp = temp.trim(); // Removes leading and trailing white space
				return temp;

			}

		}
		return null;
	}

	/**
	 * Initializes the game board to a given level. You can assume that the level at
	 * lvl has been successfully verified by the checkLevel method and that pos is
	 * an array of length 2.
	 *
	 * 1 - The game board should be created row-by-row. a - For each row, copy the
	 * values from the corresponding row in the 2-d array contained at index lvl in
	 * levels. b - When the worker is located, it's position should be recorded in
	 * the pos parameter. 2 - For each goal described in the array at index lvl of
	 * goals, convert the character at the goal coordinate to: -
	 * Config.WORK_GOAL_CHAR if it contains the worker - Config.BOX_GOAL_CHAR if it
	 * contains a box - Config.GOAL_CHAR otherwise
	 * 
	 * @param lvl
	 *            The index of the level to load.
	 * @param levels
	 *            The array containing the levels.
	 * @param goals
	 *            The parallel array to levels, containing the goals for the levels.
	 * @param pos
	 *            The starting pos of the worker. A length 2 array, where index 0 is
	 *            the row and index 1 is the column.
	 * @return A two dimension array representing the initial configuration for the
	 *         given level.
	 */
	public static char[][] initBoard(int lvl, char[][][] levels, int[][] goals, int[] pos) {

		char[][] board = new char[levels[lvl].length][]; // Creates the array with only the number of rows

		for (int i = 0; i < board.length; i++) { // Creates the rest of the array with a for loop because of the
													// different lengths in the ragged array
			board[i] = new char[levels[lvl][i].length];
		}

		for (int i = 0; i < levels[lvl].length; i++) { // Marks the initial position of the worker and copies the
														// elements to the new array
			for (int j = 0; j < levels[lvl][i].length; j++) {
				if (levels[lvl][i][j] == Config.WORKER_CHAR || levels[lvl][i][j] == Config.WORK_GOAL_CHAR) {
					pos[0] = i;
					pos[1] = j;
				}
				board[i][j] = levels[lvl][i][j];
			}
		}

		for (int j = 0; j < goals[lvl].length; j += 2) { // Fills in the correct goal character for the array
			if (levels[lvl][goals[lvl][j]][goals[lvl][j + 1]] == Config.WORKER_CHAR) {
				board[goals[lvl][j]][goals[lvl][j + 1]] = Config.WORK_GOAL_CHAR;
			} else if (levels[lvl][goals[lvl][j]][goals[lvl][j + 1]] == Config.BOX_CHAR) {
				board[goals[lvl][j]][goals[lvl][j + 1]] = Config.BOX_GOAL_CHAR;
			} else {
				board[goals[lvl][j]][goals[lvl][j + 1]] = Config.GOAL_CHAR;
			}
		}

		return board;
	}

	/**
	 * Prints out the game board.
	 * 
	 * 1 - Since the game board does not contain the outer walls, print out a
	 * sequence of Config.WALL_CHAR with a length equal to that of the first row of
	 * board, plus the outer wall to the left and the right. 2 - For each row in
	 * board, print out a Config.WALL_CHAR, followed by the contents of the row,
	 * followed by a Config.WALL_CHAR. 3 - Finally, print out a sequence of
	 * Config.WALL_CHAR with a length equal to that of the last row of board, plus
	 * the outer wall to the left and the right.
	 *
	 * Note: each row printed out should be terminated by a new line.
	 *
	 * @param board
	 *            The board to print.
	 */
	public static void printBoard(char[][] board) {
		int widthFirst = board[0].length + 2;// Gets the width for the first row and adds 2 for the wall
		int widthLast = board[board.length - 1].length + 2; // Gets the width for the last row and add 2 for the wall

		for (int i = 0; i < widthFirst; i++) { // Prints out top wall
			System.out.print(Config.WALL_CHAR);
		}

		System.out.println("");

		for (int i = 0; i < board.length; i++) { // Prints out wall + board contents + wall
			System.out.print(Config.WALL_CHAR);
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]);

			}
			System.out.println(Config.WALL_CHAR);
		}

		for (int i = 0; i < widthLast; i++) { // Prints out bottom wall
			System.out.print(Config.WALL_CHAR);
		}
		System.out.println("");

	}

	/**
	 * Runs a given level through some basic sanity checks.
	 *
	 * This method performs the following tests (in order): 1 - lvl >= 0 2 - lvl is
	 * a valid index in levels, that the 2-d array at index lvl exists and that it
	 * contains at least 1 row. 3 - lvl is a valid index in goals, the 1-d array at
	 * index lvl exists and that it contains an even number of cells. 4 - the number
	 * of boxes is more than 0. 5 - the number of boxes equals the number of goals.
	 * 6 - the coordinate of each goal is valid for the given lvl and does not
	 * correspond to a wall cell. 7 - the number of workers is exactly 1. 8 - check
	 * for duplicate goals.
	 *
	 * @param lvl
	 *            The index of the level to load.
	 * @param levels
	 *            The array containing the levels.
	 * @param goals
	 *            The parallel array to levels, containg the goals for the levels.
	 * @return 1 if all tests pass. Otherwise if test: - Test 1 fails: 0 - Test 2
	 *         fails: -1 - Test 3 fails: -2 - Test 4 fails: -3 - Test 5 fails: -4 -
	 *         Test 6 fails: -5 - Test 7 fails: -6 - Test 8 fails: -7
	 * 
	 */
	public static int checkLevel(int lvl, char[][][] levels, int[][] goals) {
		// test 1-- check if lvl >= 0
		if (lvl < 0) {
			return 0; // Returns 0 if fails test 1
		}

		// test 2 -- check lvl is a valid index in levels, that the 2-d array at index
		// lvl exists and that it contains at least 1 row.
		if ((levels.length > lvl)) {
			if (levels[lvl] == null || levels[lvl].length == 0) {
				return -1;

			}
		}

		// test 3 -- check lvl is a valid index in goals, the 1-d array at index lvl
		// exists and that it contains an even number of cells.
		if ((goals.length > lvl)) {
			if (goals[lvl] == null || goals[lvl].length % 2 == 1) {
				return -2; // Returns -2 if fails test 3
			}

		} else {
			return -2;
		}

		// test 4 -- check the number of boxes is more than 0.
		boolean hasBox = false; // Variable to track if the level has at least 1 box
		for (int i = 0; i <= levels[lvl].length - 1; i++) {
			for (int j = 0; j <= levels[lvl][i].length - 1; j++) {
				if ((levels[lvl][i][j] == Config.BOX_CHAR) || (levels[lvl][i][j] == Config.WORK_GOAL_CHAR)) {
					hasBox = true;
				}
			}
		}
		if (!hasBox) {
			return -3; // Returns -3 if fails test 4
		}

		// test 5 -- check the number of boxes equals the number of goals.
		int box = 0; // Tracks the number of boxes
		int goal = goals[lvl].length / 2; // Stores the number of goals in the level
		for (int i = 0; i <= levels[lvl].length - 1; i++) {
			for (int j = 0; j <= levels[lvl][i].length - 1; j++) {
				if ((levels[lvl][i][j] == Config.BOX_CHAR) || levels[lvl][i][j] == Config.BOX_GOAL_CHAR) {
					box += 1;
				}

			}
		}

		if (box != goal) { // Checks if the number of boxes does not equal the number of goals
			return -4; // Returns -4 if fails test 5
		}

		// test 6 --the coordinate of each goal is valid for the given lvl and does not
		// correspond to a wall cell.

		for (int i = 0; i <= goals[lvl].length - 1; i++) {
			if (i % 2 == 0) { // Checks the rows of goal
				if (goals[lvl][i] > levels[lvl].length - 1) {
					return -5;
				}

			}

			if (i % 2 == 1) { // Checks the columns of goal
				if (goals[lvl][i] > levels[lvl][goals[lvl][i - 1]].length - 1) {
					return -5;
				}
			}

		}

		for (int j = 0; j < goals[lvl].length; j += 2) { // Makes sure it does not correspond to a wall cell
			if (levels[lvl][goals[lvl][j]][goals[lvl][j + 1]] == Config.WALL_CHAR) {
				return -5;
			}
		}

		// test 7 -- check the number of workers is exactly 1.
		int workerCount = 0; // Tracks the number of workers in the level
		for (int i = 0; i <= levels[lvl].length - 1; i++) { // Loops through to make sure there is only 1 worker
			for (int j = 0; j <= levels[lvl][i].length - 1; j++) {
				if ((levels[lvl][i][j] == Config.WORKER_CHAR) || (levels[lvl][i][j] == Config.WORK_GOAL_CHAR)) {
					workerCount += 1;
				}
			}
		}
		if (workerCount != 1) { // Checks if the number of workers does not equal 1
			return -6; // Returns -6 if fails test 7
		}

		// Test 8 -- check for duplicate goals.
		for (int i = 0; i < goals[lvl].length - 1; i += 2) { // Checks if the goals are duplicates
			for (int j = i + 2; j < goals[lvl].length - 1; j += 2) {
				if (goals[lvl][i] == goals[lvl][j] && goals[lvl][i + 1] == goals[lvl][j + 1]) {
					return -7; // Returns -7 if fails test 8
				}
			}
		}

		return 1; // Returns 1 if passes all tests
	}

	/**
	 * This method builds an int array with 2 cells, representing a movement vector,
	 * based on the String parameter.
	 *
	 * The rules to create the length 2 int array are as follows: - The 1st
	 * character of the String represents the direction. - The remaining characters
	 * (if there are any) are interpreted as integer and represent the magnitude or
	 * the number of steps to take.
	 *
	 * The cell at index 0 represents movement in the rows. Hence, a negative value
	 * represents moving up the rows and a positive value represents moving down the
	 * rows.
	 *
	 * The cell at index 1 represents movement in the columns. Hence, a negative
	 * value represents moving left in the columns and a positive value represents
	 * moving right in the columns.
	 *
	 * If the first character of moveStr does not match on of Config.UP_CHAR,
	 * Config.DOWN_CHAR, Config.LEFT_CHAR, or Config.RIGHT_CHAR, then return an
	 * array with 0 in both cells.
	 *
	 * If there are no characters after the first character of moveStr or the
	 * characters cannot be interpreted as an int, set the magnitude of the movement
	 * to 1.
	 *
	 * Hint: Use Scanner to parse the magnitude.
	 *
	 * Some examples: - If the parameter moveStr is "81": An array {-1, 0} would
	 * represent moving up by one character. - If the parameter moveStr is "65": An
	 * array {0, 5} would represent moving right by 5 characters.
	 *
	 * @param moveStr
	 *            The string to parse.
	 * @return The calculated movement vector as a 2 cell int array.
	 */
	public static int[] calcDelta(String moveStr) {

		int[] notConfigChar = new int[] { 0, 0 }; // An array to return if the first value is not one of the Config
													// chars

		if (moveStr.length() == 1) {
			if (moveStr.charAt(0) == Config.UP_CHAR || moveStr.charAt(0) == Config.DOWN_CHAR
					|| moveStr.charAt(0) == Config.LEFT_CHAR || moveStr.charAt(0) == Config.RIGHT_CHAR) {
				// Checks if the 1st character matches one of the directions in Config.

				moveStr = moveStr + ("1");

			} else { // If it does not then it returns an array with 2 elements of {0,0}.
				return notConfigChar;
			}
		}

		int[] moveVector = new int[2]; // Creates the array moveVector for this method to return

		int direction = (int) moveStr.charAt(0); // Gets the direction from grabbing the first char. Convert it to an
													// integer.
		String distance1 = moveStr.substring(1); // Gets the distance from getting the remaining numbers in the string

		int magnitude = 0; // Initialized Variable to store the magnitude of delta

		Scanner scnr = new Scanner(distance1);

		if (scnr.hasNextInt()) { // Uses scanner to check if the rest of the string is an int. Then stores this
									// in magnitude.
			magnitude = scnr.nextInt();
		}

		if (direction != Config.UP_CHAR && direction != Config.DOWN_CHAR && direction != Config.LEFT_CHAR
				&& direction != Config.RIGHT_CHAR) { // If the direction does not match the and of the direction
														// characters then it returns the array with two zeros as the
														// elements
			moveVector[0] = 0;
			moveVector[1] = 0;
			return moveVector;
		}

		if (direction == Config.UP_CHAR) { // If direction equals the up char I assign the negative of distance to the 0
											// th index
			moveVector[0] = -magnitude;
			moveVector[1] = 0;
			return moveVector;
		} else if (direction == Config.DOWN_CHAR) { // If direction equals the down char I assign distance to the 0 th
													// index
			moveVector[0] = magnitude;
			moveVector[1] = 0;
			return moveVector;
		} else if (direction == Config.LEFT_CHAR) { // If direction equals the left char I assign the negative of
													// distance to the 1 st index
			moveVector[0] = 0;
			moveVector[1] = -magnitude;
			return moveVector;
		} else if (direction == Config.RIGHT_CHAR) { // If direction equals the right char I assign distance to the 1 st
														// index
			moveVector[0] = 0;
			moveVector[1] = magnitude;
			return moveVector;
		}

		return null;
	}

	/**
	 * This method checks that moving from one position to another position is a
	 * valid move.
	 *
	 * To validate the move, the method should (in order) check: 1 - that pos is
	 * valid. 2 - that the character at pos in board is in the valid array. 3 - that
	 * the delta is valid. 4 - that the new position is valid and not a wall
	 * character. 5 - that the new position is not a box character For what makes
	 * each test invalid, see the return details below.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to move from. A length 2 array, where index 0 is the
	 *            row and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the change
	 *            in row and index 1 is the change in column.
	 * @param valid
	 *            A character array containing the valid characters for the cell at
	 *            pos.
	 * @return 1 if the move is valid. Otherwise: -1 : if pos is null, not length 2,
	 *         or not on the board. -2 : if the character at pos is not valid (not
	 *         in the valid array). -3 : if delta is null or not length 2. -4 : if
	 *         the new position is off the board or a wall character -5 : if the new
	 *         position is a box character
	 */
	public static int checkDelta(char[][] board, int[] pos, int[] delta, char[] valid) {

		// Test 1 -- if pos is null, not length 2, or not on the board.

		if (pos == null) { // Checks if position is null
			return -1;
		}
		if (pos.length != 2) { // Makes sure the length of position is 2
			return -1;
		}
		if (pos[0] < 0) { // Makes Sure cannot access negative index value
			return -1;
		}
		if (pos[1] < 0) { // Makes Sure cannot access negative index value
			return -1;
		}
		if (pos[0] >= board.length) { // Checks to see if the row is on the board
			return -1;
		}
		if (pos[1] >= board[pos[0]].length) { // Checks to see if the column is on the board
			return -1;
		}

		// Test 2 -- that the character at pos in board is in the valid array.
		boolean validNum1 = false;
		for (int i = 0; i < valid.length; i++) { // Iterates through the valid array checking if the character is valid
			if (board[pos[0]][pos[1]] == valid[i]) {
				validNum1 = true;
			}
		}
		if (!validNum1) { // Checks if the boolean variable stayed false in which case the test fails
			return -2;
		}

		// Test 3 -- if delta is null or not length 2.
		if (delta == null) { // Checks if Delta is null
			return -3;
		}
		if (delta.length != 2) { // Makes Sure Delta is length 2
			return -3;
		}

		int[] position = new int[2];
		position[0] = pos[0] + delta[0]; // Adjusts the initial position with delta
		position[1] = pos[1] + delta[1]; // Adjusts the initial position with delta

		// Test 4 -- if the new position is off the board or a wall character

		if (position[0] < 0) { // Makes Sure cannot access negative index value
			return -4;
		}
		if (position[1] < 0) { // Makes Sure cannot access negative index value
			return -4;
		}
		if (position[0] >= board.length) { // Row is on the board
			return -4;
		}
		if (position[1] >= board[position[0]].length) { // Column is on the board
			return -4;
		}
		if (board[position[0]][position[1]] == Config.WALL_CHAR) { // Checks to see if the new position is a wall
			return -4;
		}

		// Test 5 -- if the new position is a box character
		if (board[position[0]][position[1]] == Config.BOX_CHAR
				|| board[position[0]][position[1]] == Config.BOX_GOAL_CHAR) {
			return -5;
		}

		return 1;
	}

	/**
	 * Changes a character on the board to one of two characters (opt1 or opt2),
	 * depending on the value of the cell.
	 *
	 * Check the cell at position pos. If the character is val, change it to opt1.
	 * Otherwise, change it to opt2.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the row
	 *            and index 1 is the column.
	 * @param val
	 *            The value to check for in the board.
	 * @param opt1
	 *            The character to change to if the value is val.
	 * @param opt2
	 *            The character to change to if the value is not val.
	 */
	public static void togglePos(char[][] board, int[] pos, char val, char opt1, char opt2) {
		if (board[pos[0]][pos[1]] == val) { // Checks if the character at the position equals val
			board[pos[0]][pos[1]] = opt1; // If it equals val change the board at position to opt1
		} else {
			board[pos[0]][pos[1]] = opt2; // If it does not equal val change the board at position to opt 2
		}

	}

	/**
	 * Moves a box on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent a box. Step 2: Use your
	 * togglePos method to correctly change the character at the new position to the
	 * appropriate box character. Step 3: Again use your togglePos method to
	 * correctly change the character at pos to the the appropriate character
	 * without a box.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the row
	 *            and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the change
	 *            in row and index 1 is the change in column.
	 * @return The return value of checkDelta if less than 1. Otherwise 1.
	 */
	public static int shiftBox(char[][] board, int[] pos, int[] delta) {
		char[] valid = new char[] { Config.BOX_GOAL_CHAR, Config.BOX_CHAR };
		char val = Config.GOAL_CHAR; // Variable for togglePos to change the new pos
		char opt1 = Config.BOX_GOAL_CHAR; // Variable for togglePos to change the new pos
		char opt2 = Config.BOX_CHAR; // Variable for togglePos to change the new pos

		int valCheck = checkDelta(board, pos, delta, valid);
		if (valCheck == 1) { // Calls the checkDelta method to make sure it is valid

			pos[0] = pos[0] + delta[0]; // Accounts for the shift in rows
			pos[1] = pos[1] + delta[1]; // Accounts for the shift in columns

			togglePos(board, pos, val, opt1, opt2); // Changes the new position

			val = Config.BOX_GOAL_CHAR; // Variable for togglePos to change the old pos
			opt1 = Config.GOAL_CHAR; // Variable for togglePos to change the old pos
			opt2 = Config.EMPTY_CHAR; // Variable for togglePos to change the old pos

			pos[0] = pos[0] - delta[0]; // Returns to the original position
			pos[1] = pos[1] - delta[1]; // Returns to the original position

			togglePos(board, pos, val, opt1, opt2); // Changes the old position

			return 1; // Returns 1 if the shiftBox method passes
		} else if (valCheck == -3) {
			return valCheck;
		} else if (valCheck == -1) {
			return valCheck;
		}

		else {
			pos[0] = pos[0] - delta[0]; // Returns pos to current position
			pos[1] = pos[1] - delta[1]; // Returns pos to current position
			return valCheck; // Returns the value of checkDelta if it fails (does not
								// return 1)
		}
	}

	/**
	 * Processes a move of the worker step-by-step.
	 *
	 * Go through the delta step-by-step, calling doMove for each step. That is, if
	 * the delta is {0, -3}, your method should call doMove three times with an
	 * argument of {0, -1} for the delta parameter of doMove. Or, if the delta is
	 * {6, 0}, it would call the doMove six times with an argument of {1, 0} for the
	 * delta parameter of the doMove method.
	 *
	 * During the processing of the move, if ever a call to doMove returns a value
	 * less than 1, your method should stop processing and return that value.
	 *
	 * Note: You can assume that one of the cells of delta will be 0.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the row
	 *            and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the change
	 *            in row and index 1 is the change in column.
	 * @return If both of the cells of delta are 0, return 0. If the call to doMove
	 *         returns a value less than 1, return that value. Otherwise, return 1.
	 */
	public static int processMove(char[][] board, int[] pos, int[] delta) {
		boolean negativeRow = false; // Variable to keep track og negativity of the row
		boolean negativeCol = false; // Variable to keep track og negativity of the col

		if (delta[0] == 0 && delta[1] == 0) {
			return 0;
		}

		int rowMovement = delta[0]; // Stores the row movement value
		int colMovement = delta[1]; // Stores the colMovement value

		if (rowMovement < 0) { // Checks if the row movement is a negative number. If it is it creates the
								// array {-1,0}
			delta[0] = -1;
			delta[1] = 0;
			negativeRow = true;
			rowMovement = rowMovement * -1;
		} else if (rowMovement > 0) { // Checks if the row movement is a positive number. If it is it creates the
										// array {1,0}
			delta[0] = 1;
			delta[1] = 0;
		} else if (colMovement < 0) { // Checks if the col movement is a negative number. If it is it creates the
			// array {0,-1}
			delta[1] = -1;
			delta[0] = 0;
			negativeCol = true;
			colMovement = colMovement * -1;
		} else if (colMovement > 0) { // Checks if the col movement is a positive number. If it is it creates the
										// array {0,1}
			delta[1] = 1;
			delta[0] = 0;
		} else { // If it is neither positive or negative it assigns both to 0. {0,0}
			delta[1] = 0;
			delta[0] = 0;
		}

		for (int i = 0; i < rowMovement; i++) { // Loops through the length of rowMovement times calling doMove.
			int retVal = doMove(board, pos, delta);
			if (retVal < 1) { // Checks if doMove returns a value less than 1
				return retVal;
			}
		}

		for (int i = 0; i < colMovement; i++) { // Loops through the length of colMovement times calling doMove.
			int retVal = doMove(board, pos, delta);
			if (retVal < 1) {
				return retVal;
			}
		}

		return 1;
	}

	/**
	 * Moves the worker on the board.
	 *
	 * Step 1: Use your checkDelta method to check that the move is valid. Recall
	 * that there are 2 characters that can represent the worker. Step 2: If
	 * checkDelta returns -5, use your shiftBox method to move the box by delta
	 * before moving the worker. Step 3: Use your togglePos method to correctly
	 * change the character at the new position to the appropriate worker character.
	 * Step 4: Again use your togglePos method to correctly change the character at
	 * pos to the the appropriate character without a worker. Step 5: Update the
	 * position of the worker in pos.
	 *
	 * @param board
	 *            The current board.
	 * @param pos
	 *            The position to change. A length 2 array, where index 0 is the row
	 *            and index 1 is the column.
	 * @param delta
	 *            The move distance. A length 2 array, where index 0 is the change
	 *            in row and index 1 is the change in column.
	 * @return If checkDelta returns a value less than 1 that is not -5, return that
	 *         value. If checkDelta returns -5 and shiftBox returns a value less
	 *         than 0, return 0. Otherwise, return 1.
	 */
	public static int doMove(char[][] board, int[] pos, int[] delta) {
		char[] valid = new char[] { Config.WORK_GOAL_CHAR, Config.WORKER_CHAR };
		char val = Config.GOAL_CHAR; // Variable for togglePos to change the new pos
		char opt1 = Config.WORK_GOAL_CHAR; // Variable for togglePos to change the new pos
		char opt2 = Config.WORKER_CHAR; // Variable for togglePos to change the new pos

		int value = checkDelta(board, pos, delta, valid);
		if (value < 1 && value > -5) { // If checkDelta is not valid it returns the error number
			return value;
		} else if (value == -5) { // If checkDelta finds a box in the new location
			pos[0] = pos[0] + delta[0]; // Changes pos to the new value
			pos[1] = pos[1] + delta[1]; // Changes pos to the new value

			int shiftVal = shiftBox(board, pos, delta);

			if (shiftVal < 0) { // If checkDelta is 1 and shiftBox is less than 0
				return 0;
			}

			togglePos(board, pos, val, opt1, opt2); // Changes the new position

			val = Config.WORK_GOAL_CHAR; // Variable for togglePos to change the old pos
			opt1 = Config.GOAL_CHAR; // Variable for togglePos to change the old pos
			opt2 = Config.EMPTY_CHAR; // Variable for togglePos to change the old pos

			pos[0] = pos[0] - delta[0]; // Returns Position to the old value
			pos[1] = pos[1] - delta[1]; // Returns Position to the old value

			togglePos(board, pos, val, opt1, opt2); // Changes the old position

			pos[0] = pos[0] + delta[0]; // New position row
			pos[1] = pos[1] + delta[1]; // New position col

			return 1;
		} else if (value == 1) {
			pos[0] = pos[0] + delta[0]; // New position row
			pos[1] = pos[1] + delta[1]; // New position col

			togglePos(board, pos, val, opt1, opt2); // Changes the new position

			val = Config.WORK_GOAL_CHAR;
			opt1 = Config.GOAL_CHAR;
			opt2 = Config.EMPTY_CHAR;

			pos[0] = pos[0] - delta[0]; // Returns pos to the old value
			pos[1] = pos[1] - delta[1]; // Returns pos to the old value

			togglePos(board, pos, val, opt1, opt2); // Changes the old position

			pos[0] = pos[0] + delta[0]; // New position of worker row
			pos[1] = pos[1] + delta[1]; // New position of worker col
			return 1;
		}

		else {
			pos[0] = pos[0] + delta[0]; // New position of worker row
			pos[1] = pos[1] + delta[1]; // New position of worker col
			return 1;
		}

	}

	/**
	 * Checks all the cells in board and ensures that there are no goals that are
	 * not covered by boxes.
	 *
	 * @param board
	 *            The current board.
	 * @return true if all the goals are covered by boxes. Otherwise, false.
	 */
	public static boolean checkWin(char[][] board) {
		for (int i = 0; i < board.length; i++) { // Iterates through game board looking for a goal or worker on goal to
													// prove that not all are covered.
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == Config.GOAL_CHAR || board[i][j] == Config.WORK_GOAL_CHAR) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * This is the main method for the Sokoban game. It consists of the main game
	 * and play again loops with calls to the various supporting methods. The
	 * details of the main method for each milestone can be found in the BP1 -
	 * Sokoban write-up on the CS 200 webpage:
	 * https://cs200-www.cs.wisc.edu/wp/programs/
	 *
	 * For all milestones, you will need to create a Scanner object to read from
	 * System.in that you will pass to the helper methods.
	 *
	 * For milestone 3, you will need to create a Random object using Config.SEED as
	 * the seed. This object should be create at the beginning of the method,
	 * outside of any loops.
	 *
	 * @param args
	 *            Unused.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to Sokoban!"); // Beginning Greeting
		int min = 0; // Min Level
		int max = Config.LEVELS.length - 1; // Max Level

		int moveCounter = 0; // Tracks number of moves

		Scanner sc = new Scanner(System.in); // Creates Scanner Object

		Random rand = new Random(Config.SEED); // Creates Random Object

		int[] pos = new int[2]; // Creates the array for pos
		char board[][] = null; // Declares the game board 2d Array
		int[] arrFromCalcDelta = null; // Declares the calcDelta 1d Array

		String prompt = ("Choose a level between " + min + " and " + max + ": "); // Initializes prompt for Game
		String prompt1 = ("Play again? (y/n) "); // Initializes prompt for y/n statement loop
		String promptString = ": "; // Initializes the prompt for the promptString method

		String moveStr = ""; // Initializes the string to hold the string returned by the call to promptInt

		boolean checkW = false; // Loop variable for game (inner loop)
		boolean playAgain = true; // Loop variable for playing the game again (outer loop)

		while (playAgain) {
			int valueLvl = promptInt(sc, prompt, min, max); // Variable valueLvl stores value of prompt int

			if (valueLvl == -1) { // If the user selects -1 the game will select a level at random
				valueLvl = rand.nextInt(max + 1);
			}

			int checkLvlVal = checkLevel(valueLvl, Config.LEVELS, Config.GOALS); // Calls the checkLevel method to make
																					// sure the given level is valid

			if (checkLvlVal != 1) { // Outputs the appropriate error message. If there is an error.
				System.out.println("Error loading level!");
				if (checkLvlVal == 0) {
					System.out.println("Level " + valueLvl + " must be 0 or greater!");
				} else if (checkLvlVal == -1) {
					System.out.println("Error with Config.LEVELS");
				} else if (checkLvlVal == -2) {
					System.out.println("Error with Config.GOALS");
				} else if (checkLvlVal == -3) {
					System.out.println("Level " + valueLvl + " does not contain any boxes.");
				} else if (checkLvlVal == -4) {
					System.out.println("Level " + valueLvl + " does not have the same number of boxes as goals.");
				} else if (checkLvlVal == -5) {
					System.out.println("Level " + valueLvl + " has a goal location that is a wall.");
				} else if (checkLvlVal == -6) {
					System.out.println("Level " + valueLvl + " has 0 or more than 1 worker(s).");
				} else if (checkLvlVal == -7) {
					System.out.println("Level " + valueLvl + " contains duplicate goals.");
				} else {
					System.out.println("Unknown Error");
				}
			}

			board = initBoard(valueLvl, Config.LEVELS, Config.GOALS, pos); // Initializes the game board and stores it
																			// under the 2d array board
			System.out.println("Sokoban Level " + valueLvl);

			do {
				printBoard(board);
				moveStr = promptString(sc, promptString); // Variable moveStr stores the string returned from
															// promptString

				if (moveStr.length() <= 0) { // User inputs nothing
					continue;
				} else if (moveStr.charAt(0) == Config.QUIT_CHAR) { // String is the quit char
					checkW = true;
				} else {

					arrFromCalcDelta = calcDelta(moveStr);
					if (arrFromCalcDelta[0] == 0 && arrFromCalcDelta[1] == 0) {
						continue;
					} else {
						int valueOfMove = processMove(board, pos, arrFromCalcDelta);

						if (valueOfMove == 1) {
							if (moveStr.length() == 1) {
								moveStr = moveStr + "1";
							}
							String magnitude = moveStr.substring(1); // Saves the magnitude in the string
							Scanner scnr = new Scanner(magnitude); // Use Scanner to check if it is an int
							if (scnr.hasNextInt()) {
								moveCounter += scnr.nextInt(); // Adds the magnitude to move counter
							}
							if (checkWin(board)) { // Checks if winning board configuration
								checkW = true;
								System.out.println("Congratulations! You won in " + moveCounter + " moves!");
								printBoard(board);
							}
						} else {
							continue;
						}
					}
				}
			} while (!checkW);

			checkW = false;
			moveCounter = 0; // Resets the Move Counter
			char promptC = promptChar(sc, prompt1); // Prompts the user if they want to play again
			if (promptC == 'y') {
				playAgain = true;
			} else { // End the loop if they do not enter y
				playAgain = false;
				System.out.println("Thanks for playing!");
			}

		}

	}
}
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            TestSokoban
// Files:            TestSokoban.java
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
 * This class contains the code for testing the Sokoban Program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

/**
 * This file contains testing methods for the Sokoban project. These methods are intended to 
 * provide an example of a way to incrementally test your code, and to provide example method calls
 * for the Sokoban methods
 *
 * Toward these objectives, the expectation is that part of the grade for the Sokoban project is 
 * to write some tests and write header comments summarizing the tests that have been written. 
 * Specific places are noted with FIXME but add any other comments you feel would be useful.
 */

import java.util.Arrays;

/**
 * This class contains a few methods for testing methods in the Sokoban class as
 * they are developed. These methods are all private as they are only intended
 * for use within this class.
 * 
 * @author Marc Renault
 * @author FIXME add your name here when you add test
 *
 */
public class TestSokoban {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {
		// Milestone 1
		// testCheckLevel();
		// Milestone 2
		// testInitBoard();
		// testCheckWin();
		// testCalcDelta();
		// testCheckDelta();
		// Milestone 3
		// testTogglePos();
		// testShiftBox();
		// testDoMove();
		// testProcessMove();
	}

	/*
	 * Test bench for Sokoban Program. Tests different values of the expected method
	 * and checks if they are the same. If they are the same they do not subtract 1
	 * from passed. If it does equal the expected result then it subtracts 1 from
	 * passed. Then I print out the number of tests passed versus the total number
	 * of tests.
	 */
	private static void testCheckLevel() {
		int numTests = 5;
		int passed = numTests;
		int res;
		// Test 1 -- Checks if the passed in lvl is less than 0. Should return 0.
		if ((res = Sokoban.checkLevel(-1, Config.LEVELS, Config.GOALS)) != 0) {
			System.out.println("FAILED: Sokoban.checkLevel Test 1. Expected 0, but value returned " + res);
			passed--;
		}

		// Test 2 -- Checks if the passed in levels 3 d array is null. Should return -1.
		char[][][] lvl = new char[2][][];
		if ((res = Sokoban.checkLevel(1, lvl, Config.GOALS)) != -1) {
			System.out.println("FAILED: Sokoban.checkLevel Test 2. Expected -1, but value returned " + res);
			passed--;
		}

		// Test 3 -- Checks if a valid level passes all tests and returns 1.
		if ((res = Sokoban.checkLevel(0, Config.LEVELS, Config.GOALS)) != 1) {
			System.out.println("FAILED: Sokoban.checkLevel Test 3. Expected 1, but value returned " + res);
			passed--;
		}

		int[][] goal = new int[2][];
		// Test 4 -- Checks if the passed in goals 2 d array is null. Should return -2.
		if ((res = Sokoban.checkLevel(1, Config.LEVELS, goal)) != -2) {
			System.out.println("FAILED: Sokoban.checkLevel Test 4. Expected 1, but value returned " + res);
			passed--;
		}

		// Test 5 -- Checks if the length of the 2d array goals is smaller than the
		// given lvl. Should return -2.
		char[][][] temp = new char[5][8][8];
		int[][] tempGoal = new int[][] { { 1, 1 }, { 2, 3, 4, 5 } };
		if ((res = Sokoban.checkLevel(4, temp, tempGoal)) != -2) {
			System.out.println("FAILED: Sokoban.checkLevel Test 5. Expected -2, but value returned " + res);
			passed--;
		}

		System.out.println("testCheckLevel: Passed " + passed + " of " + numTests + " tests.");
	}

	/**
	 * Returns true if the arrays are the same size and have the same contents.
	 */
	private static boolean compBoards(char[][] a, char[][] b) {
		if (a == null || b == null)
			return false;
		if (a.length != b.length)
			return false;
		for (int i = 0; i < a.length; i++) {
			if (!Arrays.equals(a[i], b[i])) {
				return false;
			}
		}
		return true;
	}

	private static void testInitBoard() {
		int numTests = 2;
		int passed = numTests;

		// Test 1 -- Test if the initial configuration of a board is valid. Uses level 0
		// in Config to test this method.
		int[] pTest1 = new int[2];
		char[][] bTest1 = Sokoban.initBoard(0, Config.LEVELS, Config.GOALS, pTest1);
		if (!Arrays.equals(pTest1, new int[] { 4, 4 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 1. Expected initial position: {4, 4} , but value after call "
							+ Arrays.toString(pTest1));
			passed--;
		}
		char[][] bCompTest1 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WORKER_CHAR } };
		if (!compBoards(bTest1, bCompTest1)) {
			System.out.println("FAILED: Sokoban.initBoard Test 1. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest1);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest1);
			passed--;
		}

		int[] pTest2 = new int[2];
		char[][] bTest2 = Sokoban.initBoard(1, Config.LEVELS, Config.GOALS, pTest2);
		if (!Arrays.equals(pTest2, new int[] { 7, 10 })) {
			System.out.println(
					"FAILED: Sokoban.initBoard Test 2. Expected initial position: {7, 10} , but value after call "
							+ Arrays.toString(pTest2));
			passed--;
		}
		// Test 2 -- Test if the initial configuration of a board is valid. Uses level 1
		// in Config to test this method.
		char[][] bCompTest2 = new char[][] {
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR },
				{ Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WORKER_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR,
						Config.GOAL_CHAR, Config.GOAL_CHAR },
				{ Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR, Config.EMPTY_CHAR,
						Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR,
						Config.WALL_CHAR, Config.WALL_CHAR } };

		if (!compBoards(bTest2, bCompTest2)) {
			System.out.println("FAILED: Sokoban.initBoard Test 2. Board not as expected!");
			System.out.println("Generated:");
			Sokoban.printBoard(bTest2);
			System.out.println("Expected:");
			Sokoban.printBoard(bCompTest2);
			passed--;
		}

		System.out.println("testInitBoard: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testCheckWin() {
		int numTests = 2;
		int passed = numTests;

		// Test 1 -- Test an example of a winning board to see if the method returns
		// true.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };
		boolean err = false;
		if (Sokoban.checkWin(test) != true) {
			err = true;
		}
		if (err) {
			passed--;
		}

		// Test 2 -- Test an example of a non winning board to see if the method returns
		// false.
		err = false;
		char[][] test2 = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.GOAL_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		if (Sokoban.checkWin(test2) != false) {
			err = true;
		}
		if (err) {
			passed--;
		}

		System.out.println("testCheckWin: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testCalcDelta() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Checks if the Calc Delta method returns the correct delta in its
		// one dimensional array.
		String test = "6200";
		int[] expected = new int[] { 0, 200 };
		boolean err = true;
		int[] actual = Sokoban.calcDelta(test);
		if (actual[0] == expected[0] && actual[1] == expected[1]) {
			err = false;
		}

		if (err) {
			passed--;
		}
		System.out.println("testCalcDelta: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testCheckDelta() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Checks if a valid delta that passes all tests in the level returns
		// the value of 1.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };
		int[] pos = new int[] { 2, 1 };
		int[] delta = new int[] { 1, 1 };
		char[] valid = new char[] { Config.WORKER_CHAR, Config.EMPTY_CHAR };
		boolean err = false;
		int a = Sokoban.checkDelta(test, pos, delta, valid);
		if (Sokoban.checkDelta(test, pos, delta, valid) != 1) {
			err = true;
		}

		if (err) {
			System.out.println("returned incorrectly: " + a);
			passed--;
		}
		System.out.println("testCheckDelta: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testTogglePos() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Checks to see if the call to Toggle Pos changes the board
		// appropriately.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		char[][] expected = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		int[] pos = new int[] { 3, 1 };
		char val = Config.WORKER_CHAR;
		char opt1 = Config.EMPTY_CHAR;
		char opt2 = Config.BOX_CHAR;

		boolean err = false;
		Sokoban.togglePos(test, pos, val, opt1, opt2);

		if (expected == test) {
			err = true;
		}

		if (err) {
			System.out.println("returned incorrectly: " + test);
			passed--;
		}
		System.out.println("testTogglePos: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testShiftBox() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Tests if the shift box method fails and returns a value of -3
		// because delta is passed in as null.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		int[] pos = new int[] { 2, 2 };
		int[] delta = null;

		boolean err = false;
		int a = Sokoban.shiftBox(test, pos, delta);
		if (a != -3) {
			err = true;
		}

		if (err) {
			System.out.println("returned incorrectly: " + a);
			passed--;
		}
		System.out.println("testShiftBox: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testDoMove() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Checks if an appropriate move passes all of the tests called in
		// doMove and returns a value of 1.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		int[] pos = new int[] { 3, 1 };
		int[] delta = new int[] { 0, 1 };

		boolean err = false;
		int a = Sokoban.doMove(test, pos, delta);
		if (a != 1) {
			err = true;
		}

		if (err) {
			System.out.println("returned incorrectly: " + a);
			passed--;
		}
		System.out.println("testDoMove: Passed " + passed + " of " + numTests + " tests.");
	}

	private static void testProcessMove() {
		int numTests = 1;
		int passed = numTests;

		// Test 1 -- Checks if a worker is trying to move a distance but there is a wall
		// in the way. Checks if it returns -4.
		char[][] test = new char[][] {
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.EMPTY_CHAR, Config.BOX_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WORKER_CHAR, Config.EMPTY_CHAR, Config.EMPTY_CHAR, Config.WALL_CHAR },
				{ Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR, Config.WALL_CHAR } };

		int[] pos = new int[] { 3, 1 };
		int[] delta = new int[] { 0, 4 };
		boolean err = false;
		int a = Sokoban.processMove(test, pos, delta);
		if (a != -4) {
			err = true;
		}

		if (err) {
			System.out.println("returned incorrectly: " + a);
			passed--;
		}
		System.out.println("testProcessMove: Passed " + passed + " of " + numTests + " tests.");
	}

}
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            MyLevels
// Files:            MyLevels.java
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

public class MyLevels {

	/**
	 * Character values for displaying the different statuses of the game board
	 * cells.
	 */
	public static final char EMPTY_CHAR = ' '; // Empty character
	public static final char BOX_CHAR = '='; // Box character
	public static final char WALL_CHAR = '#'; // Wall character
	public static final char WORKER_CHAR = '@'; // Worker character
	public static final char GOAL_CHAR = '.'; // Worker character
	public static final char BOX_GOAL_CHAR = '*'; // Box on a goal character
	public static final char WORK_GOAL_CHAR = '+'; // Worker on a goal character

	/**
	 * Constants for the random processes.
	 */
	public static final long SEED = 1234; // The random seed

	/**
	 * Initial configuration of the levels. Note that we are using the actual
	 * characters to make it easier to visualize the initial configurations, but it
	 * would be better to use the character constants defined above.
	 */
	public static final char[][][] LEVELS = { {

			{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
			{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR },
			{ WALL_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR },
			{ WALL_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR },
			{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR }

			}, {

					{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR },
					{ WALL_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, WALL_CHAR },
					{ WALL_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, WALL_CHAR },
					{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },

			},
			{ { WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
							WALL_CHAR, WALL_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR,
							WALL_CHAR, WALL_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
							WALL_CHAR, WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
							WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, BOX_CHAR,
							WALL_CHAR },
					{ WALL_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
							WALL_CHAR },
					{ WALL_CHAR, WORKER_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR, EMPTY_CHAR,
							WALL_CHAR },
					{ WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR, WALL_CHAR,
							WALL_CHAR },

			}

	};

	public static final int[][] GOALS = { { 3, 1 }, { 1, 2, 3, 1 }, { 3, 2, 6, 4, 7, 6 } };

	public static final char UP_CHAR = '8';
	public static final char DOWN_CHAR = '2';
	public static final char LEFT_CHAR = '4';
	public static final char RIGHT_CHAR = '6';
	public static final char QUIT_CHAR = 'q';
}

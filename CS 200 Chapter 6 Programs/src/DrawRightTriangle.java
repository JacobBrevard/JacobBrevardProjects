
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            DrawRightTriangle
// Files:            DrawRightTriangle.java
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

import java.util.Scanner;

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

public class DrawRightTriangle {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String symbol;
		int height = 0;
		System.out.print("Enter a character: ");
		symbol = scnr.next();
		symbol = symbol.trim();
		System.out.print("Enter triangle height (1-10): ");
		height = scnr.nextInt();

		while ((height > 10) || (height < 1)) {
			System.out.println("Please enter height between 1 and 10.");
			height = scnr.nextInt();
		}

		scnr.close();
		// run a while loop for scnr input until it is between 1 & 10

		for (int i = 1; i <= height; i++) {
			if (i > 0) {
				System.out.println("");
			}
			for (int j = 1; j <= i; j++) {
				System.out.print(symbol + " ");
			}
		}
		System.out.println("");
	}
}

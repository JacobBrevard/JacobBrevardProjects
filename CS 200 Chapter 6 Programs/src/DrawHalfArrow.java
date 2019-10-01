//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            DrawHalfArrow
// Files:            DrawHalfArrow.java
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

public class DrawHalfArrow {
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		int arrowBaseHeight = 0;
		int arrowBaseWidth = 0;
		int arrowHeadWidth = 0;

		int i = 0;
		int j = 0;

		System.out.print("Enter arrow base height: ");
		arrowBaseHeight = scnr.nextInt();

		System.out.print("Enter arrow base width: ");
		arrowBaseWidth = scnr.nextInt();

		System.out.print("Enter arrow head width: ");
		arrowHeadWidth = scnr.nextInt();

		// Create While loop
		while (arrowHeadWidth <= arrowBaseWidth) {
			System.out.print("Enter arrow head width: ");
			arrowHeadWidth = scnr.nextInt();
		}
		// make sure arrow head width is larger than base width
		scnr.close();

		// draw arrow base
		for (i = 0; i < arrowBaseHeight; i++) {
			System.out.println("");
			for (j = 0; j < arrowBaseWidth; j++) {
				System.out.print("*");
			}
		}

		for (i = arrowHeadWidth; i > 0; i--) {
			System.out.println("");
			for (j = 0; j < i; j++) {
				System.out.print("*");
			}

		}

		System.out.println("");

		// draw arrow head
		// Use for loop

		return;
	}
}

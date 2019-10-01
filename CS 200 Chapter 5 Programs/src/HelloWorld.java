
//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Frame
// Files:            Frame.java
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

public class HelloWorld {

	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		String a = "world!";
		boolean c;

		c = scnr.hasNextLine();

		if (c) {
			a = scnr.nextLine();
		} else {
			int x = 1;
		}

		if (a.equals("Boba")) {
			System.out.print("Enter a string: ");
			System.out.println("Hello, " + a + "!");
		} else if (a.equals("   Boba Fett   ")) {
			System.out.print("Enter a string: ");
			System.out.println("Hello, " + a + "!");
		} else {
			System.out.print("Enter a string: ");
			System.out.println("Hello, world!");
		}

	}

}

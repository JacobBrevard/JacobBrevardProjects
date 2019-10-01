
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

public class EclipseVsZyBooks {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter an integer: ");
		int a = sc.nextInt();
		System.out.println("Enter a second integer: ");
		int b = sc.nextInt();
		if (a == b)
			System.out.println(a + " equals " + b);
		else if (a < b)
			System.out.println(a + " is less than " + b);
		else
			System.out.println(a + " is more than " + b);
	}

	public static int readInt() {
		Scanner sc = new Scanner(System.in);
		return sc.nextInt();
	}

}

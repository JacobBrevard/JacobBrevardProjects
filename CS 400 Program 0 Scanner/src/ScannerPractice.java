//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: Program 0 Scanner Practice
// Files: ScannerPractice.java, input.txt, output.txt, file_output.txt
// Course: Fall 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Deppeler
// Lecture Number: 001
//
// Description of Program: This Program practices the use of java scanner
// and print writer. I created a Wisconsin State trivia game where the scanner
// reads the questions off the input file and outputs them to the screen. Then
// the user inputs their answers via their keyboard and all of their responses
// are written to the output file which only contains their answers and not the
// questions.
//
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here. Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates,
// strangers, and others do. If you received no outside help from either type
// of source, then please explicitly indicate NONE.
//
// Persons: None
// Online Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This class contains the code for the Scanner Practice Program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */
// Imports the relevant resources to this project
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This Class Contains the code for my Wisconsin State trivia application. The quiz is comprised of
 * 12 question. The purpose of this program is to learn how Scanner instances that are connected to
 * the keyboard work.
 * 
 * @author Jacob Brevard
 *
 */

public class ScannerPractice {
  // A single instance of a Scanner connected to default input (keyboard)
  private static final Scanner STDIN = new Scanner(System.in);

  /**
   * This main method runs the quizzing application by taking in the necessary input and outputting
   * the appropriate questions. It also writes the users answers to a file for them to see what
   * their answers were to all of the questions.
   * 
   * @param args - The command line argument is unused
   */
  public static void main(String[] args) {
    // Stores the user's answers
    ArrayList<String> userInputAnswers = new ArrayList<String>();

    // Prints out the Welcome message
    System.out.println("Welcome to Wisconsin State Trivia!");

    // Exception handling for creating a scanner object that reads the input file
    try {
      // Creates new file
      File input = new File("input.txt");

      // Assigns the file to the scanner to be read
      Scanner fileReader = new Scanner(input);

      // Method to read and output each word from the file to the console. Reads entire Question.
      while (fileReader.hasNextLine()) {

        // put each result on a new output line and within brackets
        String question = "[" + fileReader.nextLine() + "]";

        // Outputs the question for the user to answer
        System.out.println(question);

        // Gets the user's answer to the prompted question
        String userInput = STDIN.nextLine();

        // Prints out the users answer
        System.out.println(userInput);

        // Adds the users answer to the list
        userInputAnswers.add(userInput);

      }
      // Closes the fileReader Resource
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("Error: File Not Found Exception Occured");
    }



    try {
      // Creates new file for user's answers to be written to
      File file = new File("file_output.txt");

      // Checks whether the file currently exists
      if (!file.exists()) {
        // If the file does not exist then we create a new file
        file.createNewFile();
      }

      // Create new print writer object to write answers to output file
      PrintWriter fileWriter = new PrintWriter(file);

      // Writes the user's answers to the file iterating through the arrayList
      for (int i = 0; i < userInputAnswers.size() - 1; i++) {
        fileWriter.println(userInputAnswers.get(i));
      }

      // Adds the last value in the arrayList so there is no spacing issue
      fileWriter.print(userInputAnswers.get(userInputAnswers.size() - 1));

      System.out
          .print("Thanks for taking the quiz. Your answers have been saved to file_output.txt");

      // Close the file writer resource
      fileWriter.close();
    } catch (IOException e) {
      System.out.println("Error: IO Exception Occured");
      e.printStackTrace();
    }
  }
}

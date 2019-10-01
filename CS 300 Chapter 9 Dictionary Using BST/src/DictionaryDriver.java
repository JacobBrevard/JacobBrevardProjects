//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P09 Dictionary Using BST
// Files: Dictionary.java, DictionaryWord.java, DictionaryBST.java, DictionaryDriver.java, and
// DictionaryTests.java
// Course: Spring 2019
//
// Author: Jacob Brevard
// Email: jbrevard@wisc.edu
// Lecturer's Name: Professor Dahl
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
 * This class contains the code for the Dictionary Driver Class in the Dictionary program.
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */
// Import Statements
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Dictionary Driver Class contains the main method to drive the Dictionary Program. It
 * processes user input and then calls the various methods to make the program function.
 * 
 */
public class DictionaryDriver {

  /**
   * This is the main method for the main driver application of this program.
   * 
   * @param args
   */
  public static void main(String[] args) {
    Scanner userInput = new Scanner(System.in);

    DictionaryBST dictionary = new DictionaryBST();

    boolean loop = true;

    // While Loop To Continue the Program until the user enters q or Q
    while (loop) {
      // Prints out the command menu
      printMenu();

      System.out.print("Please enter your command: ");

      String userText = userInput.nextLine();

      userText = userText.trim();

      char firstLetter = userText.charAt(0);

      // The conditional statements to check the first letter of the input and call the correctly
      // corresponding method
      if (firstLetter == 'A' || firstLetter == 'a') {
        addWord(splitUpInput(userText), dictionary);
      } else if (firstLetter == 'L' || firstLetter == 'l') {
        lookupWord(splitUpInput(userText), dictionary);
      } else if (firstLetter == 'G' || firstLetter == 'g') {
        getAllWords(dictionary);
      } else if (firstLetter == 'S' || firstLetter == 's') {
        sizeWord(dictionary);
      } else if (firstLetter == 'H' || firstLetter == 'h') {
        heightWord(dictionary);
      } else if (userText.equalsIgnoreCase("q")) {
        // Sets the loop variable to false which ends the loop
        loop = false;
      } else {
        // If it doesn't match any of the above then we continue through the loop and print out an
        // error message
        System.out.println("WARNING: Unrecognized command.");
      }

    }

    // Closes the scanner
    userInput.close();

    System.out.print("============================== END ===================================");


  }

  /**
   * This method is used to print out the command menu.
   */
  private static void printMenu() {
    String userCommands = "\n=========================== Dictionary ============================\n"
        + "Enter one of the following options:\n"
        + "[A <word> <meaning>] to add a new word and its definition in the dictionary\n"
        + "[L <word>] to search a word in the dictionary and display its definition\n"
        + "[G] to print all the words in the dictionary in sorted order\n"
        + "[S] to get the count of all words in the dictionary\n"
        + "[H] to get the height of this dictionary implemented as a binary search tree\n"
        + "[Q] to quit the program\n"
        + "======================================================================\n";
    System.out.println(userCommands);
  }

  /**
   * This Method Processes User Input and then returns it in a string array
   * 
   * @param userInput - The input from the user
   * 
   * @return - The string array with the arguments split up
   */
  private static String[] splitUpInput(String userInput) {
    String[] commands = new String[2];

    userInput = userInput.trim();

    // Removes the first letter from the input
    userInput = userInput.substring(1);

    // Trims the leading and trailing whitespace
    userInput = userInput.trim();

    String word = "";
    for (int i = 0; i < userInput.length(); i++) {
      if (userInput.charAt(i) != ' ') {
        word += userInput.charAt(i);
      } else {
        // Cuts down the string to just be the definition
        userInput = userInput.substring(i);

        // Trims leading and trailing whitespace
        userInput = userInput.trim();

        // Sets the word to be in the 0th index of commands
        commands[0] = word.trim();

        // Ends the loop
        i = userInput.length();
      }
    }

    if (!userInput.equals("")) {
      commands[1] = userInput;
    }

    if (commands[0] == null) {
      commands[0] = commands[1];

      commands[1] = null;
    }


    return commands;
  }

  /**
   * This is a private helper method to do the add function for a word.
   * 
   * @param input - The word is in the first index and the meaning is in the second
   * 
   * @param dictionary - The dictionary passed in
   */
  private static void addWord(String[] input, DictionaryBST dictionary) {
    if (input[0] != null && input[1] != null) {
      dictionary.addWord(input[0], input[1]);
    } else {
      System.out.println("WARNING: Syntax Error for [A <word> <meaning>] command line.");
    }

  }

  /**
   * This is a private helper method to do the lookup function for a word.
   * 
   * @param input - The word is in the first index and the meaning is in the second
   * 
   * @param dictionary - The dictionary passed in
   */
  private static void lookupWord(String[] input, DictionaryBST dictionary) {
    if (input[1] == null && input[0] != null) {

      try {
        System.out.println(input[0] + ": " + dictionary.lookup(input[0]));
      } catch (NoSuchElementException e) {
        System.out.println("No definition for the word " + input[0]);
      }

    } else {
      System.out.println("WARNING: Syntax Error for [L <word>] command line.");
    }

  }

  /**
   * This is a private helper method to do the get All function for a word.
   * 
   * @param dictionary - The dictionary passed in
   */
  private static void getAllWords(DictionaryBST dictionary) {
    ArrayList<String> words = dictionary.getAllWords();

    if (words.size() == 0) {
      System.out.println("Dictionary is empty.");
    } else {
      System.out.print(words.get(0));

      for (int i = 1; i < words.size(); i++) {
        System.out.print(", " + words.get(i));
      }

      System.out.println("");
    }
  }

  /**
   * This is a private helper method to do the size function for a word.
   * 
   * @param dictionary - The dictionary passed in
   */
  private static void sizeWord(DictionaryBST dictionary) {
    System.out.println(dictionary.size());
  }

  /**
   * This is a private helper method to do the height function for a word.
   * 
   * @param dictionary - The dictionary passed in
   */
  private static void heightWord(DictionaryBST dictionary) {
    System.out.println(dictionary.height());
  }

}

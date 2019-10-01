import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This Class converts an integer entered by the user to the corresponding roman numeral
 * 
 * @author Jacob Brevard
 *
 */
public class IntegerToRomanNumeral {

  // Static Fields

  // Created 2 parallel arrays to match corresponding letters to numbers

  // Numbers array contains the int values of the roman numeral characters
  private static int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

  // romanNumerals array contains the letters of all the roman numerals
  private static String[] romanNumerals =
      {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};


  /**
   * Main method that serves as the driver for the number to roman numeral application.
   * 
   * @param args
   */
  public static void main(String[] args) {
    // Creates a scanner object
    Scanner userInput = new Scanner(System.in);

    // Home welcome message for the application
    System.out.println("Welcome to the number to roman numeral converter app!");

    // Adds and Extra newline for formatting
    System.out.println("");

    // Prints out the instructions for the user
    System.out.print("Enter a number to be converted to a roman numeral (enter -1 to quit): ");

    // Gets the entered user number by calling the processUserInput Method
    int userNumberEntered = processUserInput(userInput);

    while (userNumberEntered != -1) {

      try {
        System.out.println(userNumberEntered + " converted to a roman numeral is: "
            + convertToRomanNumeral(userNumberEntered));
      } catch (ArrayIndexOutOfBoundsException e) {
        // Adds and Extra newline for formatting
        System.out.println("");

        // Prints out an error message for their invalid input
        System.out.println("ERROR: Has to Be a Valid number that is greater than or equal to 1!");

      }

      // Adds and Extra newline for formatting
      System.out.println("");

      // Prints out the instructions for the user
      System.out.print("Enter a number to be converted to a roman numeral (enter -1 to quit): ");

      // Gets the entered user number by calling the processUserInput Method
      userNumberEntered = processUserInput(userInput);

    }

    // Closes the scanner
    userInput.close();

    // Prints out the closing message
    System.out.println("Thanks for using the number to roman numeral converter application!");

  }

  /**
   * This method converts the integer entered by the user to a roman numeral by iterating through
   * the 2 parallel arrays.
   * 
   * @param number - the integer the user enters
   * 
   * @return the roman numeral.
   */
  public static String convertToRomanNumeral(int number) {
    int i = 0;
    String romanNumeral = "";

    // Loops through until the number is 0 meaning the full value of the nummber is represented in
    // Roman numerals.
    while (number != 0) {
      // Loops through this sequence until the selected value is less that the number at the current
      // index. Using a greedy algorithm.
      while (number >= numbers[i]) {
        // Adds the corresponding numeral letter to the string variable
        romanNumeral += romanNumerals[i];

        // Subtracts the value of the letter from the number variable
        number -= numbers[i];

      }

      // Increments i to the next index in the array
      i++;

    }

    return romanNumeral;
  }

  /**
   * This Method processes user input and makes sure the user enters a valid value.\
   * 
   * @return the value
   */
  public static int processUserInput(Scanner userInput) {
    // Loop variable
    boolean validInput = false;

    // Loops until the input is valid
    while (!validInput) {
      try {
        // Tries to get the next int value
        int value = userInput.nextInt();

        // If it makes it this far then valid Input is true
        validInput = true;

        // Returns the valid value
        return value;
      } catch (InputMismatchException e) {
        // Adds and Extra newline for formatting
        System.out.println("");

        // Prints out an error warning message
        System.out.println(
            "Error: Enter a valid integer that is greater than or equal to 1. Cannot be a word.");

        // Adds and Extra newline for formatting
        System.out.println("");

        // Clears the buffer
        userInput.nextLine();

        // Prints out the instructions for the user
        System.out.print("Enter a number to be converted to a roman numeral (enter -1 to quit): ");
      }

    }

    // Returns -1 if the method does not work
    return -1;
  }

}

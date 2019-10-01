//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title: P07 Iterating To Philosophy
// Files: NextWikiLink.java
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
 * This class contains the code for the Next Wiki Link Class in the Iterating to philosophy program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

// Import Statements
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


/**
 * The Next Wiki Link Class is the driver application for the iterating to philosophy program and
 * generates new wiki pages.
 * 
 */
public class NextWikiLink implements Function<String, String> {
  @Override
  public String apply(String t) {
    try {
      // Download a Wikipedia page, using t in their internal link format: /wiki/Some_Subject
      Document doc = Jsoup.connect("https://en.wikipedia.org" + t).get();
      // Use .css selector to retrieve a collection of links from this page's description
      // "p a" selects links within paragraphs
      // ":not([title^='Help'])" skips pronunciations
      // ":not(sup a)" skips citations
      Elements links = doc.select("p a:not([title^='Help']):not(sup a)");
      // return the link attribute from the first element of this list
      return links.get(0).attr("href");
      // Otherwise return an appropriate error message:
    } catch (IOException | IllegalArgumentException e) {
      return "FAILED to find wikipedia page: " + t;
    } catch (IndexOutOfBoundsException e) {
      return "FAILED to find a link in wikipedia page: " + t;
    }
  }

  public static void main(String[] args) {
    // Implement your own Wikipedia crawling application here.
    // 1. prompt the user to enter a topic name and number of iterations to follow
    // 2. prepend "/wiki/" to the user's input, and replace spaces with underscores
    // 3. use a for-each loop to iterate through the number of links requested

    // Create a new scannner
    Scanner userInput = new Scanner(System.in);

    // Prints out message and waits for user input
    System.out.print("Enter a wikipedia page topic: ");
    String Topic = userInput.nextLine();

    // Prepends "/wiki/" to the user's input, and replace spaces with underscores
    Topic = "/wiki/" + Topic;
    Topic = Topic.replace(' ', '_');

    // Prints out a message and waits for user input
    System.out.print("Enter the number of pages you'd like to step through: ");
    int maxNumOfPages = userInput.nextInt();

    // Creates a new instance of NextWikiLink to serve as our function that we pass into our
    // constructor when we create a new generator
    NextWikiLink wiki = new NextWikiLink();

    // Create a new generator
    Generator<String> newGenerator = new Generator<String>(Topic, wiki, maxNumOfPages);

    // The arraylist is created to store the next links generated
    ArrayList<Object> values = new ArrayList<>();

    // For each loop to iterate through the correct number of links
    for (String Object : newGenerator) {
      values.add(Object);
    }

    // Closes the scanner
    userInput.close();

    // Keeps track if the page was not found
    boolean pageNotFound = false;

    // Converts the object to a sting and then checks if it contains the word failed in the 2nd
    // element of the array
    if ((values.get(1).toString().contains("FAILED"))) {
      pageNotFound = true;
    }

    // If the page is not found then we print out one message from the array list and otherwise we
    // print out every link generated from every index in the array list.
    if (!pageNotFound) {
      for (int i = 0; i < values.size() - 1; i++) {
        System.out.println(values.get(i));
      }
      System.out.print(values.get(values.size() - 1));
    } else {
      System.out.print(values.get(1));
    }

  }
}

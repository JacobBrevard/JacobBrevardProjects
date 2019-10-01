//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Eliza
// Files:            Eliza.java
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
 * This class contains the code for the Eliza chat bot program
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * The Eliza class holds the user input and response formation for a system that
 * collects user input and responds appropriately. Eliza is based off of a
 * computer program written at MIT in the 1960's by Joseph Weizenbaum. Eliza
 * uses keyword matching to respond to users in a way that displays interest in
 * the users and continues the conversation until instructed otherwise.
 */
public class Eliza {

	/*
	 * This method does input and output with the user. It calls supporting methods
	 * to read and write files and process each user input.
	 * 
	 * @param args (unused)
	 */
	public static void main(String[] args) {
		// Milestone 2
		// create a scanner for reading user input
		Scanner readUserInput = new Scanner(System.in);

		// and a random number generator with Config.SEED as the seed
		Random numberGenerator = new Random(Config.SEED);

		ArrayList<String> saveDialog = new ArrayList<String>(); // ArrayList to store the
																// conversation

		// Milestone 3
		// How the program starts depends on the command-line arguments.
		// Command-line arguments can be names of therapists for example:
		// Eliza Joe Laura
		// If no command-line arguments then the therapists name is Eliza
		// and this reads a file with that name and the Config.RESPONSE_FILE_EXTENSION.
		// Example filename: Eliza.rsp
		// If only one command-line argument, then read the responses from
		// the corresponding file with Config.RESPONSE_FILE_EXTENSION.
		// If there is more than one command-line argument then offer them
		// as a list of people to talk with. For the three therapists above the prompt
		// is
		// "Would you like to speak with Eliza, Joe, or Laura?"
		// When a user types a name then read the responses from the file which
		// is the selected name and Config.RESPONSE_FILE_EXTENSION extension.
		// Whatever name the user types has the extension appended and
		// is read using loadResponseTable. If loadResponseTable can't load
		// the file then it will report an error.

		String therapist = null;
		if (args.length <= 0) { // Compares if there are no command line arguments
			therapist = "Eliza";
		} else if (args.length == 1) { // Compares if there is one command line argument
			therapist = args[0];
			loadResponseTable(therapist + Config.RESPONSE_FILE_EXTENSION);
		} else { // Compares if there are multiple command line arguments
			String fullMessage = "";
			fullMessage += "Would you like to speak with " + args[0];
			System.out.print("Would you like to speak with " + args[0]);

			for (int i = 1; i < args.length - 1; i++) {
				fullMessage += ", " + args[i];
				System.out.print(", " + args[i]);
			}
			fullMessage += ", or " + args[args.length - 1];
			fullMessage += "?";

			saveDialog.add(fullMessage); // Adds the intro Message to the arrayList

			System.out.print(", or " + args[args.length - 1]);
			System.out.println("?");

			if (readUserInput.hasNext()) { // Looks for user to choose a therapist
				therapist = readUserInput.next();
				readUserInput.nextLine(); // Clears the buffer

				if (loadResponseTable(therapist + Config.RESPONSE_FILE_EXTENSION).size() > 1) {
					// Do Nothing
				} else {
					saveDialog.add("Error reading " + therapist + ".rsp");
				}
			}
		}

		// Milestone 2
		String namePrompt = ""; // Stores the name of the user

		// Milestone 2
		String welcomePrompt = "Hi I'm " + therapist + ", what is your name?"; // Introductory
																				// welcome prompt

		saveDialog.add(welcomePrompt);
		System.out.println(welcomePrompt);

		ArrayList<ArrayList<String>> responseTable = null; // Stores the value of the response table
		ArrayList<String> separatePhrasesValue = null; // Stores the value of the separated phrase
		String[] prepareInputValue; // Stores the array returned by prepare input Value
		String userInputValue = ""; // Stores the value inputed by the user
		// Milestone 2

		if (readUserInput.hasNext()) { // Gets UserInput for their name and stores that in
										// namePrompt
			namePrompt = readUserInput.next();
			saveDialog.add(namePrompt);
			readUserInput.nextLine(); // Clearing the Buffer
			System.out.println("Nice to meet you " + namePrompt + ". What is on your mind?");
			saveDialog.add("Nice to meet you " + namePrompt + " . What is on your mind?");
		}
		boolean loopUntilQuitWord = false; // Loop Variable
		do {
			// Milestone 2
			// obtain user input
			if (readUserInput.hasNextLine()) { // Gets the user input and stores in userInputValue
				userInputValue = readUserInput.nextLine();
				saveDialog.add(userInputValue);
			} else {
				break;
			}

			if (Config.DEBUG) {
				System.out.println("DEBUG phrases: " + userInputValue);
			}

			// Milestone 2

			separatePhrasesValue = separatePhrases(userInputValue);

			// prepareInput
			prepareInputValue = prepareInput(userInputValue);

			if (prepareInputValue == null) {
				loopUntilQuitWord = true;
				break;
			}

			if (Config.DEBUG) {
				System.out.print("DEBUG prepareInput: ");
				System.out.print("[");

				System.out.print(prepareInputValue[0]);
				for (int i = 1; i < prepareInputValue.length; i++) {
					System.out.print(", " + prepareInputValue[i]);
				}
				System.out.print("]");
				System.out.println("");
			}

			// Milestone 3
			if (therapist.equals("Eliza")) { // If the therapist is Eliza we load the response table
												// from eliza's file
				responseTable = loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);
			} else { // Otherwise the response table is null
				responseTable = null;
			}

			if (!foundQuitWord(separatePhrasesValue)) { // Looks to see if there is a quit word
				String responsePrepared = prepareResponse(prepareInputValue, numberGenerator,
						responseTable); // Calls the prepare response method

				if (Config.DEBUG) {
					System.out.println("DEBUG prepareResponse: " + responsePrepared);
				}

				System.out.println(responsePrepared);
				saveDialog.add(responsePrepared);
			}

			// Milestone 2
			// end loop if quit word
		} while (!loopUntilQuitWord);

		// Milestone 2
		String endPrompt = "Goodbye " + namePrompt + ".";

		saveDialog.add(endPrompt);

		// Milestone 2
		System.out.println(endPrompt);

		// Milestone 3
		// Save all conversation (user and system responses) starting
		// with this program saying "Hi I'm..." and concludes with
		// "Goodbye <name>.".
		// Always prompt the user to see if they would like to save a
		// record of the conversation. If the user enters a y or Y as the
		// first non-whitespace character then prompt for filename and save,
		// otherwise don't save dialog. After successfully saving a dialog
		// print the message "Thanks again for talking! Our conversation is saved in:
		// <filename>".
		// If saveDialog throws an IOException then catch it, print out the error:
		// "Unable to save conversation to: " <name of file>
		// Repeat the code prompting the user if they want to save the dialog.

		boolean done = false; // Loop Variable for Saving or Not

		while (!done) {
			System.out.println("Would you like to have a record of our conversation (y/n): ");
			String yOrn = readUserInput.next(); // Saves the value inputed by the user
			readUserInput.nextLine(); // Clearing the Buffer
			yOrn = yOrn.trim();
			yOrn = yOrn.toLowerCase();
			char compare = yOrn.charAt(0); // Stores the first character of whatever is entered
			if (compare == 'y') {
				System.out.println("Enter filename: ");

				String fileName = readUserInput.nextLine();

				try { // Exception handling in case the saveDialog method throws an exception.
					done = true;
					saveDialog(saveDialog, fileName);
				} catch (IOException e) {
					System.out.println("Unable to save conversation to: " + fileName);
					done = false;
				}
				if (done == true) {
					System.out.println(
							"Thanks again for talking! Our conversation is saved in: " + fileName);
				}

			} else {
				done = true; // If the first character is not y or Y
			}

		}

		readUserInput.close(); // Closes the scanner freeing up the resource

	}

	/**
	 * This method processes the user input, returning an ArrayList containing
	 * Strings, where each String is a phrase from the user's input. This is done by
	 * removing leading and trailing whitespace, making the user's input all lower
	 * case, then going through each character of the user's input. When going
	 * through each character this keeps all digits, alphabetic characters and '
	 * (single quote). The characters ? ! , . signal the end of a phrase, and
	 * possibly the beginning of the next phrase, but are not included in the
	 * result. All other characters such as ( ) - " ] etc. should be replaced with a
	 * space. This method makes sure that every phrase has some visible characters
	 * but no leading or trailing whitespace and only a single space between words
	 * of a phrase. If userInput is null then return null, if no characters then
	 * return a 0 length list, otherwise return a list of phrases. Empty phrases and
	 * phrases with just invalid/whitespace characters should NOT be added to the
	 * list.
	 * 
	 * Example userInput: "Hi, I am! a big-fun robot!!!" Example returned: "hi", "i
	 * am", "a big fun robot"
	 * 
	 * @param userInput
	 *            text the user typed
	 * @return the phrases from the user's input
	 */
	public static ArrayList<String> separatePhrases(String userInput) {
		ArrayList<String> list = new ArrayList<>(); // Stores the list that will be returned by the
													// method

		if (userInput == null) {
			return null;
		}
		if (userInput.length() == 0) {
			return list;
		}

		userInput = userInput.trim(); // Removes Leading and trailing whitespace
		userInput = userInput.toLowerCase(); // Puts userInput to Lowercase
		String temp = ""; // String temp is used to store the intermediary values from userInput
		boolean space = false; // boolean space is used to keep track if the last character was
								// whitespace

		for (int i = 0; i < userInput.length(); i++) {
			if (userInput.charAt(i) == '?' || userInput.charAt(i) == '!'
					|| userInput.charAt(i) == ',' || userInput.charAt(i) == '.') { // Compares to
																					// see if it is
																					// signaling the
																					// end of phrase
				if (temp.length() == 0) { // If the length of the string is 0 then it does not add
											// it to the list
					temp = "";
				} else {
					list.add(temp);
					temp = ""; // Resets the temp variable
				}
			} else if (userInput.charAt(i) == '\'' || Character.isDigit(userInput.charAt(i))
					|| Character.isLetter(userInput.charAt(i))) { // Compares to see if the letter
																	// is a number, letter
																	// or single quote.
				temp += userInput.charAt(i);
				space = false;
			}

			else {
				if (!space && temp.length() > 0) {
					temp += (" ");
					space = true;
				}

			}

		}
		if (temp.length() > 0) {
			list.add(temp);
		}
		return list;
	}

	/**
	 * Checks whether any of the phrases in the parameter match a quit word from
	 * Config.QUIT_WORDS. Note: complete phrases are matched, not individual words
	 * within a phrase.
	 * 
	 * @param phrases
	 *            List of user phrases
	 * @return true if any phrase matches a quit word, otherwise false
	 */
	public static boolean foundQuitWord(ArrayList<String> phrases) {
		for (int i = 0; i < phrases.size(); i++) {
			for (int j = 0; j < Config.QUIT_WORDS.length; j++) {
				if (phrases.get(i).equals(Config.QUIT_WORDS[j])) { // Checks to see if the word
																	// matches a quit word
					return true;
				}
			}

		}
		return false;
	}

	/**
	 * Iterates through the phrases of the user's input, finding the longest phrase
	 * to which to respond. If two phrases are the same length, returns whichever
	 * has the lower index in the list. If phrases parameter is null or size 0 then
	 * return "".
	 * 
	 * @param phrases
	 *            List of user phrases
	 * @return the selected phrase
	 */
	public static String selectPhrase(ArrayList<String> phrases) {
		if (phrases == null) {
			return "";
		}
		if (phrases.size() == 0) {
			return "";
		}

		int length = 0; // Keeps track of the largest length
		int index = 0; // Keeps track of the index with the largest length
		for (int i = 0; i < phrases.size(); i++) {
			if (phrases.get(i).length() > length) {
				index = i;
				length = phrases.get(i).length();
			}
		}
		return phrases.get(index); // Returns the phrase with the largest length
	}

	/**
	 * Looks for a replacement word for the word parameter and if found, returns the
	 * replacement word. Otherwise if the word parameter is not found then the word
	 * parameter itself is returned. The wordMap parameter contains rows of match
	 * and replacement strings. On a row, the element at the 0 index is the word to
	 * match and if it matches return the string at index 1 in the same row. Some
	 * example word maps that will be passed in are Config.INPUT_WORD_MAP and
	 * Config.PRONOUN_MAP.
	 * 
	 * If word is null return null. If wordMap is null or wordMap length is 0 simply
	 * return word parameter. For this implementation it is reasonable to assume
	 * that if wordMap length is >= 1 then the number of elements in each row is at
	 * least 2.
	 * 
	 * @param word
	 *            The word to look for in the map
	 * @param wordMap
	 *            The map of words to look in
	 * @return the replacement string if the word parameter is found in the wordMap
	 *         otherwise the word parameter itself.
	 */
	public static String replaceWord(String word, String[][] wordMap) {
		if (word == null) {
			return null;
		}
		if (wordMap == null) {
			return word;
		}
		if (wordMap.length == 0) {
			return word;
		}
		for (int i = 0; i < wordMap.length; i++) {
			if (wordMap[i][0].equals(word)) { // Iterates through the wordMap with the first word in
												// each row looking
												// for a match
				return wordMap[i][1]; // If found it returns the new word found at index 1 of the
										// selected row
			}
		}

		return word; // If there are no matches we just return the word parameter
	}

	/**
	 * Concatenates the elements in words parameter into a string with a single
	 * space between each array element. Does not change any of the strings in the
	 * words array. There are no leading or trailing spaces in the returned string.
	 * 
	 * @param words
	 *            a list of words
	 * @return a string containing all the words with a space between each.
	 */
	public static String assemblePhrase(String[] words) {
		if (words == null) {
			return "";
		}
		boolean hasString = false;
		for (int i = 0; i < words.length; i++) {
			if (words[i].length() > 0) { // Checks to see that at least one of the elements has a
											// length greater than 0.
				hasString = true;
			}
		}

		if (!hasString) {
			return "";
		}

		String assembledPhrase = words[0]; // assembledPhrase variable is used to hold the value of
											// the strings this
											// method combines.
		for (int i = 1; i < words.length; i++) {
			assembledPhrase += (" " + words[i]); // Adds a space and then the next word
		}
		return assembledPhrase;
	}

	/**
	 * Replaces words in phrase parameter if matching words are found in the mapWord
	 * parameter. A word at a time from phrase parameter is looked for in wordMap
	 * which may result in more than one word. For example: i'm => i am Uses the
	 * replaceWord and assemblePhrase methods. Example wordMaps are
	 * Config.PRONOUN_MAP and Config.INPUT_WORD_MAP. If wordMap is null then phrase
	 * parameter is returned. Note: there will Not be a case where a mapping will
	 * itself be a key to another entry. In other words, only one pass through
	 * swapWords will ever be necessary.
	 * 
	 * @param phrase
	 *            The given phrase which contains words to swap
	 * @param wordMap
	 *            Pairs of corresponding match & replacement words
	 * @return The reassembled phrase
	 */
	public static String swapWords(String phrase, String[][] wordMap) {
		if (wordMap == null) { // Checks if the passed in map is null. If it is then return null.
			return phrase;
		}
		if (phrase == null) {
			return phrase;
		}
		int wordCount = 1; // Keeps track of the number of words in the phrase variable
		for (int i = 0; i < phrase.length(); i++) {
			if (phrase.charAt(i) == ' ') {
				wordCount++;
			}
		}

		boolean onceThrough = false; // Variable used to make sure spacing is correct when swaping
										// the words
		String currentWord = ""; // Keeps track of the current word from scanner
		String returnedWord = ""; // Keeps track of the word being concatenated to return in the end
		Scanner scnr = new Scanner(phrase);
		for (int i = 0; i < wordCount; i++) {
			if (onceThrough) {
				returnedWord += " ";
			}
			if (scnr.hasNext()) {
				currentWord = scnr.next();
				currentWord = replaceWord(currentWord, wordMap); // Call the replaceWord method
				returnedWord += currentWord;
				onceThrough = true;
			}

		}

		scnr.close();

		return returnedWord; // Returns the phrase with certain words replaced
	}

	/**
	 * This prepares the user input. First, it separates input into phrases (using
	 * separatePhrases). If a phrase is a quit word (foundQuitWord) then return
	 * null. Otherwise, select a phrase (selectPhrase), swap input words (swapWords
	 * with Config.INPUT_WORD_MAP) and return an array with each word its own
	 * element in the array.
	 * 
	 * @param input
	 *            The input from the user
	 * @return words from the selected phrase
	 */
	public static String[] prepareInput(String input) {
		String phraseSwapped = ""; // Keeps track of the string returned by the swapWords method

		ArrayList<String> separatedWords = new ArrayList<>(); // ArrayList to store words from
																// separated Words
		ArrayList<String> sentenceAfterSwap = new ArrayList<>(); // ArrayList to store the sentence
																	// after the swap

		separatedWords = separatePhrases(input); // Adds the elements to the arrayList by calling
													// the separatePhrases
													// Method
		if (Config.DEBUG) {
			System.out.println("DEBUG separatePhrases: " + separatedWords);
		}

		if (foundQuitWord(separatedWords)) { // Checks if quit word is found
			return null;
		} else {
			String phraseSelected = selectPhrase(separatedWords);

			phraseSwapped = swapWords(phraseSelected, Config.INPUT_WORD_MAP); // Calls the SwapWords
																				// method and saves
			if (Config.DEBUG) {
				System.out.println("DEBUG swapWords: " + phraseSwapped);
			}

			if (phraseSwapped.equals("")) {
				String[] blankString = new String[] { "" };
				return blankString;
			} else {
				sentenceAfterSwap.add(phraseSwapped); // that in phraseSwapped
			}

		}

		String toSeparate = "";
		toSeparate = sentenceAfterSwap.get(0);

		Scanner scnr = new Scanner(toSeparate); // Creates a Scanner to break up the individual
												// words

		toSeparate = toSeparate.trim();

		int wordCount = 0; // Keeps track of the number of words in the input variable
		for (int i = 0; i < toSeparate.length() - 1; i++) { // Error is here somewhere
			if (toSeparate.charAt(i) == ' ') {
				wordCount++;
			}
		}
		wordCount++;

		String[] words = new String[wordCount]; // Creates an array to return

		for (int i = 0; i < wordCount; i++) { // Copies over elements from the arrayList
			words[i] = scnr.next();
		}

		scnr.close();

		return words;
	}

	/**
	 * Reads a file that contains keywords and responses. A line contains either a
	 * list of keywords or response, any blank lines are ignored. All leading and
	 * trailing whitespace on a line is ignored. A keyword line begins with
	 * "keywords" with all the following tokens on the line, the keywords. Each line
	 * that follows a keyword line that is not blank is a possible response for the
	 * keywords. For example (the numbers are for our description purposes here and
	 * are not in the file):
	 * 
	 * 1 keywords computer 2 Do computers worry you? 3 Why do you mention computers?
	 * 4 5 keywords i dreamed 6 Really, <3>? 7 Have you ever fantasized <3> while
	 * you were awake? 8 9 Have you ever dreamed <3> before?
	 *
	 * In line 1 is a single keyword "computer" followed by two possible responses
	 * on lines 2 and 3. Line 4 and 8 are ignored since they are blank (contain only
	 * whitespace). Line 5 begins new keywords that are the words "i" and "dreamed".
	 * This keyword list is followed by three possible responses on lines 6, 7 and
	 * 9.
	 * 
	 * The keywords and associated responses are each stored in their own ArrayList.
	 * The response table is an ArrayList of the keyword and responses lists. For
	 * every keywords list there is an associated response list. They are added in
	 * pairs into the list that is returned. There will always be an even number of
	 * items in the returned list.
	 * 
	 * Note that in the event an IOException occurs when trying to read the file
	 * then an error message "Error reading <fileName>", where <fileName> is the
	 * parameter, is printed and a non-null reference is returned, which may or may
	 * not have any elements in it.
	 * 
	 * @param fileName
	 *            The name of the file to read
	 * @return The response table
	 */
	public static ArrayList<ArrayList<String>> loadResponseTable(String fileName) {
		File responses = new File(fileName); // Creates the new file
		Scanner keyWordReader = null; // Declares my scanner
		ArrayList<ArrayList<String>> responseTable = new ArrayList<ArrayList<String>>(); // Declares
																							// arrayList
																							// of
																							// arrayLists
		ArrayList<String> keyWordPhrases = new ArrayList<String>(); // Stores the keyWord Phrases

		try { // Exception handling block for file not Found
			keyWordReader = new Scanner(responses);
		} catch (FileNotFoundException e) { // Catches the fileNotFound Exception
			System.out.println("Error reading " + fileName);
			return responseTable;
		}

		boolean onceThrough = false; // Contains whether or not have been through loop once
		String current = ""; // Keeps track of current string

		while (keyWordReader.hasNextLine()) {
			current = keyWordReader.nextLine(); // Reads the first line of input
			if (current == null || current.equals("")) {
				// Nothing, Ignore
			} else if (current.length() >= 9 && current.substring(0, 8).equals("keywords")) { // Checks
				// if the first word is keywords

				if (onceThrough) {
					responseTable.add(keyWordPhrases);

				}

				keyWordPhrases = new ArrayList<String>(); // Creates new instance of the arrayList
				String keyWord = current.substring(9); // Stores the keyword in the String variable

				Scanner scnr = new Scanner(keyWord);
				while (scnr.hasNext()) {
					keyWordPhrases.add(scnr.next());
				}
				if (keyWordPhrases.isEmpty()) { // If the array is empty it adds an empty String to
												// the array
					keyWordPhrases.add("");
				}

				// adds the keyword to the arrayList
				responseTable.add(keyWordPhrases); // adds the entire arrayList to the arrayList of
													// arrayLists
													// (responseTable)
				onceThrough = true;

				keyWordPhrases = new ArrayList<String>(); // Creates a new instance of the arrayList
															// effectively
															// reseting it
			} else {
				current = current.trim();
				keyWordPhrases.add(current);
			}
		}
		responseTable.add(keyWordPhrases); // adds the last arrayList to responseTable

		keyWordReader.close();

		return responseTable;
	}

	/**
	 * Checks to see if the keywords match the sentence. In other words, checks to
	 * see that all the words in the keyword list are in the sentence and in the
	 * same order. If all the keywords match then this method returns an array with
	 * the unmatched words before, between and after the keywords. If the keywords
	 * do not match then null is returned.
	 * 
	 * When the phrase contains elements before, between, and after the keywords,
	 * each set of the three is returned in its own element String[] keywords =
	 * {"i", "dreamed"}; String[] phrase = {"do", "you", "know", that", "i", "have",
	 * "dreamed", "of", "being", "an", "astronaut"};
	 * 
	 * toReturn[0] = "do you know that" toReturn[1] = "have" toReturn[2] = "of being
	 * an astronaut"
	 * 
	 * In an example where there is a single keyword, the resulting List's first
	 * element will be the the pre-sequence element and the second element will be
	 * everything after the keyword, in the phrase String[] keywords = {"always"};
	 * String[] phrase = {"I", "always", "knew"};
	 * 
	 * toReturn[0] = "I" toReturn[1] = "knew"
	 * 
	 * In an example where a keyword is not in the phrase in the correct order, null
	 * is returned. String[] keywords = {"computer"}; String[] phrase = {"My","dog",
	 * "is", "lost"};
	 * 
	 * return null
	 * 
	 * @param keywords
	 *            The words to match, in order, in the sentence.
	 * @param phrase
	 *            Each word in the sentence.
	 * @return The unmatched words before, between and after the keywords or null if
	 *         the keywords are not all matched in order in the phrase.
	 */
	public static String[] findKeyWordsInPhrase(ArrayList<String> keywords, String[] phrase) {
		ArrayList<String> unMatched = new ArrayList<>(); // Stores the unmatched words returned
		String noMatch = ""; // Used to put the unMatched word selected into a string
		int keyWordIndex = 0; // Records the Index of the key word selected from the response table

		for (int i = 0; i < phrase.length; i++) { // Iterates through the phrase
			if (keyWordIndex == keywords.size()) {
				noMatch += phrase[i] + " ";
			} else if (!keywords.get(keyWordIndex).equals(phrase[i])) { // Checks if the keyword
																		// does not match
				noMatch += phrase[i] + " ";
			} else { // Else it does match
				noMatch = noMatch.trim();

				if (noMatch.length() > 0) { // Checks if noMatch has a length greater than 0
					unMatched.add(noMatch);
					noMatch = "";
					keyWordIndex++; // Increments the index used for keywords

					if (keyWordIndex == keywords.size()) {
						if (i < phrase.length - 1) {
							// Do Nothing
						} else {
							unMatched.add("");
						}
					}

				} else { // Otherwise it adds a blank space where the keyword was if it is at the
							// beginning or end of the sentence
					if (keyWordIndex == 0 || noMatch.length() == 0) {
						unMatched.add("");
						keyWordIndex++;
					}
				}
			}
		}
		if (noMatch.length() > 0) { // Adds the last element of unmatched words at the end of the
									// loop
			noMatch = noMatch.trim();
			unMatched.add(noMatch);
		}

		if (keyWordIndex == 0) { // If it matches no words return the phrase in a string.
			return null;
		}

		if (keyWordIndex != keywords.size()) { // If the index does not match the size then return
												// null because not all the keywords were matched
			return null;
		}

		String[] words = new String[unMatched.size()]; // Creates an array to return

		for (int i = 0; i < unMatched.size(); i++) { // Copies over elements from the arrayList
			words[i] = unMatched.get(i);
		}

		return words;
	}

	/**
	 * Selects a randomly generated response within the list of possible responses
	 * using the provided random number generator where the number generated
	 * corresponds to the index of the selected response. Use Random nextInt(
	 * responseList.size()) to generate the random number. If responseList is null
	 * or 0 length then return null.
	 * 
	 * @param rand
	 *            A random number generator.
	 * @param responseList
	 *            A list of responses to choose from.
	 * @return A randomly selected response
	 */
	public static String selectResponse(Random rand, ArrayList<String> responseList) {
		if (responseList == null) { // returns null if the response list is null
			return null;
		}
		if (responseList.size() == 0) { // Returns null if the responseList size is 0
			return null;
		}

		return responseList.get(rand.nextInt(responseList.size())); // Returns a random response
																	// from the list
	}

	/**
	 * This method takes processed user input and forms a response. This looks
	 * through the response table in order checking to see if each keyword pattern
	 * matches the userWords. The first matching keyword pattern found determines
	 * the list of responses to choose from. A keyword pattern matches the
	 * userWords, if all the keywords are found, in order, but not necessarily
	 * contiguous. This keyword matching is done by findKeyWordsInPhrase method. See
	 * the findKeyWordsInPhrase algorithm in the Eliza.pdf.
	 * 
	 * If no keyword pattern matches then Config.NO_MATCH_RESPONSE is returned.
	 * Otherwise one of possible responses for the matched keywords is selected with
	 * selectResponse method. The response selected is checked for the replacement
	 * symbol <n> where n is 1 to the length of unmatchedWords array returned by
	 * findKeyWordsInPhrase. For each replacement symbol the corresponding unmatched
	 * words element (index 0 for <1>, 1 for <2> etc.) has its pronouns swapped with
	 * swapWords using Config.PRONOUN_MAP and then replaces the replacement symbol
	 * in the response.
	 * 
	 * @param userWords
	 *            using input after preparing.
	 * @param rand
	 *            A random number generator.
	 * @param responseTable
	 *            A table containing a list of keywords and response pairs.
	 * @return The generated response
	 */
	public static String prepareResponse(String[] userWords, Random rand,
			ArrayList<ArrayList<String>> responseTable) {

		if (responseTable == null) {
			return Config.NO_MATCH_RESPONSE;
		}

		int indexOfPhrases = 0; // Stores the index of the phrase

		// Iterate through the response table.
		// The response table has paired rows. The first row is a list of key
		// words, the next a list of corresponding responses. The 3rd row another
		// list of keywords and 4th row the corresponding responses.
		boolean keyMatched = false; // Makes sure that a keyWord set was matched
		ArrayList<String> keyWordMatched = null;
		for (int rows = 0; rows < responseTable.size(); rows += 2) {

			ArrayList<String> temp = new ArrayList<>();
			temp = responseTable.get(rows);

			if (findKeyWordsInPhrase(temp, userWords) != null) { // checks to see if the current
																	// keywords match the
																	// user's words
				indexOfPhrases = rows + 1;
				keyWordMatched = temp; // using findKeyWordsInPhrase.
				keyMatched = true;
				rows = responseTable.size();
			} else {
				// Nothing
			}

		}

		if (!keyMatched) { // If a keyword set was not matched then it returns the default value
			return Config.NO_MATCH_RESPONSE;
		}

		// if no keyword pattern was matched, return Config.NO_MATCH_RESPONSE
		// else, select a response using the appropriate list of responses for the
		// keywords

		if (Config.DEBUG) {
			System.out.println("DEBUG findKeyWordsInPhrase: " + keyWordMatched.toString());
		}

		String responseSelected = selectResponse(rand, responseTable.get(indexOfPhrases));

		if (Config.DEBUG) {
			System.out.println("DEBUG selectResponse: " + responseSelected);
		}

		// Look for <1>, <2> etc in the chosen response. The number starts with 1 and
		// there won't be more than the number of elements in unmatchedWords returned by
		// findKeyWordsInPhrase. Note the number of elements in unmatchedWords will be
		// 1 more than the number of keywords.
		// For each <n> found, find the corresponding unmatchedWords phrase (n-1) and
		// swap
		// its pronoun words (swapWords using Config.PRONOUN_MAP). Then use the
		// result to replace the <n> in the chosen response.

		// in the selected echo, swap pronouns

		// inserts the new phrase with pronouns swapped, into the response

		String[] unMatchedWords;
		unMatchedWords = findKeyWordsInPhrase(keyWordMatched, userWords); // Calls the method to
																			// return the unmatched
																			// keywords

		String value = ""; // Holds the string value
		int number = 0; // Stores the number that we have to swap
		int index; // Stores the value of the index
		String swapWord = ""; // The word to be swapped

		for (int i = 0; i < responseSelected.length(); i++) { // Find the index number of <n> where
																// n is the index - 1
			if (Character.isDigit(responseSelected.charAt(i))) {
				value += responseSelected.charAt(i);
				number = Integer.parseInt(value);
				index = i;

				if (number > 0) {
					swapWord = unMatchedWords[number - 1]; // Stores the word to be swapped in
															// swapWord from
															// the unMatchedWords Array
					swapWord = swapWords(swapWord, Config.PRONOUN_MAP);

					if (Config.DEBUG) {
						System.out.println("DEBUG swapWords: " + swapWord);
					}

					if (swapWord != null) { // Replaces the given <n> character with the appropriate
											// swapWord
						responseSelected = responseSelected.replaceAll("<" + number + ">",
								swapWord);
					}

				}
				value = ""; // Resets the value
				number = 0; // Resets the value
				index = 0; // Resets the value
			}
		}

		return responseSelected;
	}

	/**
	 * Creates a file with the given name, and fills that file line-by-line with the
	 * tracked conversation. Every line ends with a newline. Throws an IOException
	 * if a writing error occurs.
	 * 
	 * @param dialog
	 *            the complete conversation
	 * @param fileName
	 *            The file in which to write the conversation
	 * @throws IOException
	 */
	public static void saveDialog(ArrayList<String> dialog, String fileName) throws IOException {
		File newFile = new File(fileName); // Creates the new file

		try (PrintWriter writer = new PrintWriter(newFile);) { // Creates a printWriter object
			for (int i = 0; i < dialog.size(); i++) { // Iterates through the ArrayList containing
														// the dialog
				writer.print(dialog.get(i)); // Writes to the document using the printWriter
												// pulling values from the dialog ArrayLists
				if (i < dialog.size() - 1) {
					writer.println();
				}

			}
		} catch (FileNotFoundException e) { // If the file is not found it throws an Exception
			throw e;
		}

	}
}

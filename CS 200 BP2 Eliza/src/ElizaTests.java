//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            Eliza Tests
// Files:            ElizaTests.java
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
 * This class contains tests for Eliza.java
 * 
 * 
 * #### # # # # ####
 * 
 * Bugs: none known
 *
 * @author Jacob Brevard
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class contains a few methods for testing methods in the Eliza class as
 * they are developed. These methods are private since they are only intended
 * for use within this class.
 * 
 * @author Jim Williams
 * @author Jacob Brevard
 *
 */
public class ElizaTests {

	/**
	 * This is the main method that runs the various tests. Uncomment the tests when
	 * you are ready for them to run.
	 * 
	 * @param args
	 *            (unused)
	 */
	public static void main(String[] args) {

		// Milestone 1: Process User Input
		// M1: main nothing to do
		// testSeparatePhrases();
		// testFoundQuitWord();
		// testSelectPhrase();
		// testReplaceWord();
		// testAssemblePhrase();

		// Milestone 2:
		// M2: implement parts of main as described in main method comments
		// testSwapWords();
		// testPrepareInput();
		// testLoadResponseTable();

		// Milestone 3:
		// main: implement the rest of main as described in the main method comments
		// testFindKeyWordsInPhrase();
		// testSelectResponse();
		// testInputAndResponse();
		// testSaveDialog();
	}

	/**
	 * This runs some tests on the separatePhrases method. 1. Tests the phrase "No.
	 * I'm talking to my dog! Bye." 2. Compares each array index for Test Case 1. 3.
	 * Tests the phrase "What? This isn't the 4th messy-sentence!" 4. Compares each
	 * array index for Test Case 2. 5. Tests the phrase "Thank you, but no, I have
	 * to go. Goodbye!!!" 6. Compares each array index for Test Case 3. 7. Tests the
	 * phrase "What?" 8. Compares each array index for Test Case 4. 9. Test the
	 * phrase "What? , , How?". 10. Compares each array index for Test Case 5. 11.
	 * Test the phrase "hi! there". 12. Compares each array index for Test Case 6.
	 * 13. Tests a sentence that is split up twice. Extra non whitespace characters.
	 * 14. Compares each array index for Test Case 7. 15. See if the method returns
	 * null if userInput is null.
	 */
	private static void testSeparatePhrases() { // 8 Total Tests
		boolean error = false;

		// 1 (Test 1). Extra spaces within a sentence
		ArrayList<String> phrases = Eliza.separatePhrases("No.  I'm talking  to my dog! Bye.");
		ArrayList<String> expected = new ArrayList<>();
		expected.add("no");
		expected.add("i'm talking to my dog");
		expected.add("bye");

		if (phrases.size() != expected.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected.size() + " but found "
					+ phrases.size() + " phrases.");
		}

		// 2 (Test 1).
		for (int i = 0; i < expected.size(); i++) {
			if (!expected.get(i).equals(phrases.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected.get(i));
				System.out.println("  " + phrases.get(i));
			}
		}

		// 3 (Test 2). Use of single quote and dash
		ArrayList<String> phrases1 = Eliza
				.separatePhrases("What? This isn't the 4th messy-sentence!");
		ArrayList<String> expected1 = new ArrayList<>();
		expected1.add("what");
		expected1.add("this isn't the 4th messy sentence");

		if (phrases1.size() != expected1.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected.size() + " but found "
					+ phrases.size() + " phrases.");
		}

		// 4 (Test 2).
		for (int i = 0; i < expected1.size(); i++) {
			if (!expected1.get(i).equals(phrases1.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected1.get(i));
				System.out.println("  " + phrases1.get(i));
			}
		}

		// 5 (Test 3). There are extra end of sentence markers
		ArrayList<String> phrases2 = Eliza
				.separatePhrases("Thank you, but no, I have to go. Goodbye!!!");
		ArrayList<String> expected2 = new ArrayList<>();
		expected2.add("thank you");
		expected2.add("but no");
		expected2.add("i have to go");
		expected2.add("goodbye");

		if (phrases2.size() != expected2.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected2.size() + " but found "
					+ phrases2.size() + " phrases.");
		}

		// 6 (Test 3).
		for (int i = 0; i < expected2.size(); i++) {
			if (!expected2.get(i).equals(phrases2.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected2.get(i));
				System.out.println("  " + phrases2.get(i));
			}
		}

		// 7 (Test 4). Normal word
		ArrayList<String> phrases3 = Eliza.separatePhrases("What?");
		ArrayList<String> expected3 = new ArrayList<>();
		expected3.add("what");

		if (phrases3.size() != expected3.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected3.size() + " but found "
					+ phrases3.size() + " phrases.");
		}

		// 8 (Test 4).
		for (int i = 0; i < expected3.size(); i++) {
			if (!expected3.get(i).equals(phrases3.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected3.get(i));
				System.out.println("  " + phrases3.get(i));
			}
		}

		// 9 (Test 5). Extra spaces
		ArrayList<String> phrases4 = Eliza.separatePhrases("What?    ,     , How?");
		ArrayList<String> expected4 = new ArrayList<>();
		expected4.add("what");
		expected4.add("how");
		if (phrases4.size() != expected4.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected4.size() + " but found "
					+ phrases4.size() + " phrases.");
		}

		// 10 (Test 5).
		for (int i = 0; i < expected4.size(); i++) {
			if (!expected4.get(i).equals(phrases4.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected4.get(i));
				System.out.println("  " + phrases4.get(i));
			}
		}

		// 11 (Test 6). No punctuation
		ArrayList<String> phrases5 = Eliza.separatePhrases("hi! there");
		ArrayList<String> expected5 = new ArrayList<>();
		expected5.add("hi");
		expected5.add("there");
		if (phrases5.size() != expected5.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected5.size() + " but found "
					+ phrases5.size() + " phrases.");
		}

		// 12 (Test 6).
		for (int i = 0; i < expected5.size(); i++) {
			if (!expected5.get(i).equals(phrases5.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected5.get(i));
				System.out.println("  " + phrases5.get(i));
			}
		}

		// 13 (Test 7). Example with 2 words. And Extra Spaces.
		ArrayList<String> phrases6 = Eliza.separatePhrases("No. I'm busy.   .   Jake");
		ArrayList<String> expected6 = new ArrayList<>();
		expected6.add("no");
		expected6.add("i'm busy");
		expected6.add("jake");
		if (phrases6.size() != expected6.size()) {
			error = true;
			System.out.println("testSeparatePhrases: expected " + expected6.size() + " but found "
					+ phrases6.size() + " phrases.");
		}

		// 14 (Test 7).
		for (int i = 0; i < expected6.size(); i++) {
			if (!expected6.get(i).equals(phrases6.get(i))) {
				error = true;
				System.out.println("testSeparatePhrases: phrases not the same");
				System.out.println("  " + expected6.get(i));
				System.out.println("  " + phrases6.get(i));
			}
		}

		// 15 (Test 8) Example if null is passed in
		if (Eliza.separatePhrases(null) != null) {
			error = true;
		}

		if (error) {
			System.out.println("testSeparatePhrases failed");
		} else {
			System.out.println("testSeparatePhrases passed");
		}

	}

	/**
	 * This runs some tests on the foundQuitWord method. 1. Tests if the quit word
	 * exists in this arrayList {"thank you for talking" , "bye"} 2. Tests if the
	 * quit word exists in this arrayList {"thank you for talking" , "i love to say
	 * bye"} 3. Tests if the quit word exists in this arrayList {"goodbye"} 4. Tests
	 * if the quit word exists in this arrayList {"hello" , "ham" , "I" , "am"}
	 */
	private static void testFoundQuitWord() { // 4 Total Tests
		boolean error = false;

		// 1. quit word is at end of the list
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("thank you for talking");
		phrases.add("bye");

		boolean quit = Eliza.foundQuitWord(phrases);
		if (!quit) {
			error = true;
			System.out.println("testFoundQuitWord 1: failed");
		}

		// 2. quit word is part of a sentence
		ArrayList<String> phrases1 = new ArrayList<>();
		phrases1.add("thank you for talking");
		phrases1.add("i love to say bye");

		boolean quit1 = Eliza.foundQuitWord(phrases1);
		if (quit1) {
			error = true;
			System.out.println("testFoundQuitWord 2: failed");
		}

		// 3. quit word is first or only word
		ArrayList<String> phrases2 = new ArrayList<>();
		phrases2.add("goodbye");

		boolean quit2 = Eliza.foundQuitWord(phrases2);
		if (!quit2) {
			error = true;
			System.out.println("testFoundQuitWord 3: failed");
		}

		// 4. No quit words included
		ArrayList<String> phrases3 = new ArrayList<>();
		phrases3.add("hello");
		phrases3.add("ham");
		phrases3.add("I");
		phrases3.add("am");

		boolean quit3 = Eliza.foundQuitWord(phrases3);
		if (quit3) {
			error = true;
			System.out.println("testFoundQuitWord 4: failed");
		}

		if (error) {
			System.err.println("testFoundQuitWord failed");
		} else {
			System.out.println("testFoundQuitWord passed");
		}
	}

	/**
	 * This runs some tests on the selectPhrase method. 1. Test to see if it picks
	 * the longest. 2. Test to see when the biggest is the same length. Make sure it
	 * picks the one that came first. 3. Test to see when they pass and empty string
	 * to the method. 4. Tests to see that the method returns a string with 0 length
	 * if the list passed in is null.
	 */
	private static void testSelectPhrase() { // 4 Total Tests
		boolean error = false;

		// 1. choose the longest
		ArrayList<String> phrases = new ArrayList<>();
		phrases.add("no");
		phrases.add("sometimes I remember being on a boat");
		phrases.add("not often");
		String choice = Eliza.selectPhrase(phrases);
		if (!choice.equals("sometimes I remember being on a boat")) {
			error = true;
			System.out.println("testSelectPhrase 1: failed");
		}

		// 2. 2 choices of the same length
		ArrayList<String> phrases1 = new ArrayList<>();
		phrases1.add("no sam");
		phrases1.add("I");
		phrases1.add("no bob");
		String choice1 = Eliza.selectPhrase(phrases1);
		if (!choice1.equals("no sam")) {
			error = true;
			System.out.println("testSelectPhrase 2: failed");
		}

		// 3. empty list is passed to selectPhrase
		ArrayList<String> phrases2 = new ArrayList<>();
		String choice2 = Eliza.selectPhrase(phrases2);
		if (!choice2.equals("")) {
			error = true;
			System.out.println("testSelectPhrase 3: failed");
		}

		// 4. null list is passed to selectPhrase
		ArrayList<String> phrases3 = null;
		String choice3 = Eliza.selectPhrase(phrases3);
		if (!choice2.equals("")) {
			error = true;
			System.out.println("testSelectPhrase 3: failed");
		}

		if (error) {
			System.err.println("testSelectPhrase failed");
		} else {
			System.out.println("testSelectPhrase passed");
		}
	}

	/**
	 * This runs some tests on the assemblePhrase method. 1. Tests the assembling of
	 * a phrase with { "This", "is a", "sentence" }. 2. Tests the assembling of a
	 * phrase with an array with no strings in it. 3. Tests the assembling of a
	 * phrase with just a list of words, with no internal spaces {"Thisisasentence",
	 * "Thisisnotasentence", "Thisisasentence"}. 4. Tests the assembling of a
	 * sentence when a null array is passed in.
	 */
	private static void testAssemblePhrase() { // 4 Total Tests
		boolean error = false;

		// 1. Tests the assembling of a normal sentence.
		String[] words = { "This", "is a", "sentence" };
		String sentence = Eliza.assemblePhrase(words);
		String expectedSentence = "This is a sentence";

		if (!sentence.equals(expectedSentence)) {
			error = true;
			System.out.println("testAssembleSentence 1 failed '" + sentence + "'");
		}

		// 2. Tests the assembling of a sentence when an array with no strings in it is
		// passed in.
		String[] words1 = {};
		String sentence1 = Eliza.assemblePhrase(words1);
		String expectedSentence1 = "";

		if (!sentence1.equals(expectedSentence1)) {
			error = true;
			System.out.println("testAssembleSentence 2 failed '" + sentence1 + "'");
		}

		// 3. Tests the assembling of a phrase with just a list of words, with no
		// internal spaces.
		String[] words2 = { "Thisisasentence", "Thisisnotasentence", "Thisisasentence" };
		String sentence2 = Eliza.assemblePhrase(words2);
		String expectedSentence2 = "Thisisasentence Thisisnotasentence Thisisasentence";

		if (!sentence2.equals(expectedSentence2)) {
			error = true;
			System.out.println("testAssembleSentence 3 failed '" + sentence2 + "'");
		}

		// 4. Tests the assembling of a sentence when a null array is passed in.
		String[] words3 = null;
		String sentence3 = Eliza.assemblePhrase(words3);
		String expectedSentence3 = "";

		if (!sentence3.equals(expectedSentence3)) {
			error = true;
			System.out.println("testAssembleSentence 4 failed '" + sentence3 + "'");
		}

		if (error) {
			System.err.println("testAssemblePhrase failed");
		} else {
			System.out.println("testAssemblePhrase passed");
		}
	}

	/**
	 * This runs some tests on the findKeyWordsInPhrase method. 1. Tests a case
	 * where 1 keyword is passed in with a phrase with 4 elements. 2. Tests a case
	 * where 1 keyword is passed in with a phrase with 3 elements. 3. Tests a case
	 * where 1 keyword is passed in with a phrase with 7 elements. 4. Tests a case
	 * where 2 keywords are passed in with a phrase with 5 elements. 5. Tests a case
	 * where 2 keywords are passed in with a phrase with 4 elements. Out of order
	 * keyword scenario. 6. Tests a case where 3 keywords are passed in with a
	 * phrase with 4 elements. Out of order keyword scenario. 7. Tests a case where
	 * 1 keyword is passed in with a phrase with 3 elements. 8. Tests a case where 2
	 * keywords are passed in with a phrase with 7 elements.
	 */
	private static void testFindKeyWordsInPhrase() { // 8 Total Tests
		boolean error = false;

		{// block so each test has its own variable scope.
			// 1. - Tests a case where 1 keyword is passed in with a phrase with 4 elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "are", "you", "a", "computer" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("are you a")
					|| !matches[1].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 1 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 2. - Tests a case where 1 keyword is passed in with a phrase with 3 elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "computer", "are", "you" };

			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("")
					|| !matches[1].equals("are you")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 2 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 3. - Tests a case where 1 keyword is passed in with a phrase with 7 elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("computer");
			String[] phrase = { "does", "that", "computer", "on", "your", "desk", "work" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 2 || !matches[0].equals("does that")
					|| !matches[1].equals("on your desk work")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 3 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 4. - Tests a case where 2 keywords are passed in with a phrase with 5
			// elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] phrase = { "why", "don't", "you", "like", "me" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, phrase);
			if (matches == null || matches.length != 3 || !matches[0].equals("why don't")
					|| !matches[1].equals("like") || !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 4 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 5. - Tests a case where 2 keywords are passed in with a phrase with 4
			// elements. Out of order keyword scenario.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			String[] sentence = { "me", "don't", "like", "you" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 6. Tests a case where 3 keywords are passed in with a phrase with 4 elements.
			// Out of order keyword scenario.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("you");
			keywords.add("me");
			keywords.add("hello");
			String[] sentence = { "me", "don't", "like", "you", "jump", "hello" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (matches != null) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 7. Tests a case where 1 keyword is passed in with a phrase with 3 elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("always");
			String[] sentence = { "I", "always", "knew" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (!matches[0].equals("I") || !matches[1].equals("knew")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		{
			// 8. Tests a case where 2 keywords are passed in with a phrase with 7 elements.
			ArrayList<String> keywords = new ArrayList<String>();
			keywords.add("jump");
			keywords.add("around");

			String[] sentence = { "I", "always", "knew", "jump", "up", "and", "around" };
			String[] matches = Eliza.findKeyWordsInPhrase(keywords, sentence);
			if (!matches[0].equals("I always knew") || !matches[1].equals("up and")
					|| !matches[2].equals("")) {
				error = true;
				System.out.println("testFindKeyWordsInPhrase 5 failed.");
				System.out.println(Arrays.toString(matches));
			}
		}

		if (error) {
			System.err.println("testFindKeyWordsInPhrase failed");
		} else {
			System.out.println("testFindKeyWordsInPhrase passed");
		}
	}

	/**
	 * This runs some tests on the saveDialog method. 1. Test 1: Tests one thing
	 * being added to the file. 2. Test 2: Tests Multiple things being added to the
	 * file. 3. Test 3: Tests Multiple things being added to the file.
	 */
	private static void testSaveDialog() { // 3 Total Tests
		boolean error = false;
		final String TEST_FILENAME = "testing.txt";
		{ // 1. Tests one thing being added to the file.
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				if (!readFromFile.equals(list.get(0) + "\n")) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		{ // 2. Tests Multiple things being added to the file.
			ArrayList<String> list = new ArrayList<>();
			list.add("this is a single line.");
			list.add("this is a double line.");
			list.add("this is a triple line.");
			list.add("this is a quadruple line.");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				if (!"this is a double line.".equals(list.get(1))) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		{ // 3. Tests Multiple things being added to the file.
			ArrayList<String> list = new ArrayList<>();
			list.add("Jacob likes to fish");
			list.add("Sam likes to run");
			list.add("Bob likes to build");
			list.add("George likes to jump");
			try {
				Eliza.saveDialog(list, TEST_FILENAME);
				String readFromFile = readFile(TEST_FILENAME);
				if (!"George likes to jump".equals(list.get(3))) {
					error = true;
					System.out.println("testSaveDialog 1 failed.");
					System.out.println("content read: " + readFromFile);
				}
				removeFile(TEST_FILENAME);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		if (error) {
			System.err.println("testSaveDialog failed");
		} else {
			System.out.println("testSaveDialog passed");
		}
	}

	/**
	 * Supporting method for testSaveDialog
	 * 
	 * @param fileName
	 *            name of the file to read
	 * @return the contents of the file
	 */
	private static String readFile(String fileName) {
		StringBuffer buf = new StringBuffer();
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName));) {
			String line;
			while ((line = reader.readLine()) != null) {
				buf.append(line);
				buf.append("\n");
			}
			return buf.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Supporting method for testSaveDialog.
	 * 
	 * @param filename
	 *            file to be removed.
	 */
	private static void removeFile(String filename) {
		File file = new File(filename);
		try {
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This runs some tests on the replaceword method. 1. Tests the word machine
	 * with the Config.INPUT_WORD_MAP 2. Tests the word yourself with the
	 * Config.PRONOUN_MAP 3. Tests if word is null 4. Tests if wordMap is null 5.
	 * Tests if wordMap has a length of 0. 6. Tests the word maybe with Config.Input
	 * Word Map.
	 */
	private static void testReplaceWord() { // 6 Total Tests
		boolean error = false;

		{ // 1. Tests the word machine
			String word = "machine";
			String expected = "computer";
			String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 2. Tests the word yourself
			String word = "yourself";
			String expected = "myself";
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 2 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}
		{ // 3. Tests if word is null
			String word = null;
			String expected = null;
			String result = Eliza.replaceWord(word, Config.PRONOUN_MAP);
			if (result != null) {
				error = true;
				System.out.println("testReplaceWord 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}
		{ // 4. Tests if wordMap is null
			String word = "bob";
			String expected = "jim";
			String result = Eliza.replaceWord(word, null);
			if (!result.equals(word)) {
				error = true;
				System.out.println("testReplaceWord 4 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}
		{ // 5. Tests if wordMap has a length of 0.
			String word = "bob";
			String expected = "jim";
			String[][] wordMap = new String[0][0];
			String result = Eliza.replaceWord(word, wordMap);
			if (!result.equals(word)) {
				error = true;
				System.out.println("testReplaceWord 4 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 6. Tests the word maybe with Config.Input Word Map
			String word = "maybe";
			String expected = "perhaps";
			String result = Eliza.replaceWord(word, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testReplaceWord 1 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		if (error) {
			System.err.println("testReplaceWord failed");
		} else {
			System.out.println("testReplaceWord passed");
		}
	}

	/**
	 * This runs some tests on the swapWords method. 1. Tests with input word map
	 * and a given phrase. 2. Tests with pronoun map and a given phrase. 3. Tests
	 * with pronoun map and a given phrase. 4. Tests with input word map and a given
	 * phrase. 5. Tests with input word map and a given phrase. 6. Tests with
	 * pronoun map and a given phrase. 7. Tests when the map passed in is null.
	 */
	private static void testSwapWords() { // 7 Total Tests
		boolean error = false;

		{ // 1. Tests with input word map and a given phrase
			String someWords = "i'm cant recollect";
			String expected = "i am cannot remember";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 1 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 2. Tests with pronoun map and a given phrase
			String someWords = "i'm happy";
			String expected = "you are happy";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 2 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 3. Tests with pronoun map and a given phrase
			String someWords = "about my dog";
			String expected = "about your dog";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 4. Tests with input word map and a given phrase
			String someWords = "about my dog i wont fight dad";
			String expected = "about my dog i won't fight father";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 5. Tests with input word map and a given phrase
			String someWords = "i'm you're were";
			String expected = "i am you are was";
			String result = Eliza.swapWords(someWords, Config.INPUT_WORD_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 6. Tests with pronoun map and a given phrase
			String someWords = "i my i'm love to go fishing";
			String expected = "you your you are love to go fishing";
			String result = Eliza.swapWords(someWords, Config.PRONOUN_MAP);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		{ // 7. Tests when the map passed in is null
			String someWords = "i my i'm love to go fishing";
			String expected = "i my i'm love to go fishing";
			String result = Eliza.swapWords(someWords, null);
			if (result == null || !result.equals(expected)) {
				error = true;
				System.out.println("testSwapWords 3 failed. result:'" + result + "' expected:'"
						+ expected + "'");
			}
		}

		if (error) {
			System.err.println("testSwapWords failed");
		} else {
			System.out.println("testSwapWords passed");
		}
	}

	/**
	 * This runs some tests on the selectResponse method. 1. Checks if one of the 3
	 * strings chosen. 2. Checks if called 1000 times the choices are approximately
	 * random. 3. What should happen when null is passed to selectResponse. 4. What
	 * should happen when a list a single string is provided.
	 */
	private static void testSelectResponse() { // 4 Total Tests
		boolean error = false;

		{ // 1.
			// is one of the 3 strings chosen?
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			strList.add("happy");
			strList.add("cat");
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice.equals("The") || choice.equals("happy") || choice.equals("cat"))) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}

		{ // 2.
			// if called 1000 times, are the choices approximately random?
			Random randGen = new Random(765);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("this");
			strList.add("is");
			strList.add("a");
			strList.add("list");
			strList.add("to");
			strList.add("choose");
			strList.add("from");
			int[] actualCount = new int[strList.size()];
			int[] expectedCount = new int[] { 156, 146, 142, 138, 160, 130, 128 };
			for (int iterationIndex = 0; iterationIndex < 1000; iterationIndex++) {
				String choice = Eliza.selectResponse(randGen, strList);
				for (int wordIndex = 0; wordIndex < strList.size(); wordIndex++) {
					if (choice.equals(strList.get(wordIndex))) {
						actualCount[wordIndex]++;
					}
				}
			}
			// since we set a seed on the random number generator we should know the
			// expected
			// outcome
			for (int countIndex = 0; countIndex < actualCount.length; countIndex++) {
				if (actualCount[countIndex] != expectedCount[countIndex]) {
					error = true;
					System.out.println("testSelectResponse 2 failed.");
					System.out.println("  expectedCount: " + Arrays.toString(expectedCount));
					System.out.println("  actualCount: " + Arrays.toString(actualCount));
				}
			}

		}

		{ // 3.
			// What should happen when null is passed to selectResponse
			Random randGen = new Random(434);
			String choice = Eliza.selectResponse(randGen, null);

			if (choice != null) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}
		{ // 4.
			// What should happen when a list a single string is provided
			Random randGen = new Random(434);
			ArrayList<String> strList = new ArrayList<>();
			strList.add("The");
			String choice = Eliza.selectResponse(randGen, strList);

			if (!(choice.equals("The"))) {
				error = true;
				System.out.println("testSelectResponse 1 failed.");
			}
		}

		if (error) {
			System.err.println("testSelectResponse failed");
		} else {
			System.out.println("testSelectResponse passed");
		}
	}

	/**
	 * This runs some tests on the prepareInput method. 1. Tests the input "bye". 2.
	 * Tests the input "I said goodbye". 3. Tests the input "I can't do that". 4.
	 * Tests the input "Hi! I am! Jacob".
	 */
	private static void testPrepareInput() { // 4 Total Tests
		boolean error = false;

		{ // 1.
			String input = "bye";
			String[] expected = null;
			String[] result = Eliza.prepareInput("bye");
			if (expected != result) {
				error = true;
				System.out.println("testPrepareInput 1: failed");
				System.out.println("  input: " + input);
				System.out.println("  result: " + Arrays.toString(result));
			}
		}

		{ // 2.
			String input = "I said goodbye";
			String[] expected = { "i", "said", "goodbye" };
			String[] result = Eliza.prepareInput("I said goodbye");

			for (int i = 0; i < expected.length; i++) {
				if (!expected[i].equals(result[i])) {
					error = true;
					System.out.println("testPrepareInput 2: failed");
					System.out.println("  input: " + input);
					System.out.println("  result: " + Arrays.toString(result));
				}
			}

		}

		{ // 3.
			String input = "I can't do that";
			String[] expected = { "i", "cannot", "do", "that" };
			String[] result = Eliza.prepareInput("I can't do that");
			for (int i = 0; i < expected.length; i++) {
				if (!expected[i].equals(result[i])) {
					error = true;
					System.out.println("testPrepareInput 3: failed");
					System.out.println("  input: " + input);
					System.out.println("  result: " + Arrays.toString(result));
				}
			}
		}

		{ // 4.
			String input = "Hi! I am! Jacob";
			String[] expected = { "jacob" };
			String[] result = Eliza.prepareInput("Hi! I am! Jacob");
			for (int i = 0; i < expected.length; i++) {
				if (!expected[i].equals(result[i])) {
					error = true;
					System.out.println("testPrepareInput 4: failed");
					System.out.println("  input: " + input);
					System.out.println("  result: " + Arrays.toString(result));
				}
			}
		}

		if (error) {
			System.err.println("testPrepareInput failed");
		} else {
			System.out.println("testPrepareInput passed");
		}
	}

	/**
	 * This runs some tests on the loadResponseTable method. 1. Makes sure the first
	 * row contains the correct keyword. 2. Makes sure there is an even number of
	 * lists. 3. Makes sure the right number of keywords and responses read in for a
	 * keyword/response pair. 4. Makes sure the right number of rows are read in. 5.
	 * Makes sure the last row of the file is read in. 6. Tests with wrong given
	 * file name.
	 *
	 */
	private static void testLoadResponseTable() { // 6 Total Tests
		boolean error = false;

		{ // 1. Makes sure the first row contains the correct keyword
			ArrayList<String> expected1stRow = new ArrayList<String>();
			expected1stRow.add("computer");
			ArrayList<ArrayList<String>> table = Eliza.loadResponseTable("Eliza.rsp");
			if (!table.get(0).equals(expected1stRow)) {
				error = true;
				System.out.println("testLoadResponseTable 1: failed");
				System.out.println("  expected1stRow: " + expected1stRow);
				System.out.println("  table1stRow: " + table.get(0));
			}

			// 2. Makes sure there is an even number of lists
			if (table.size() % 2 != 0) {
				error = true;
				System.out.println("testLoadResponseTable 2: failed");
				System.out.println("  expected an even number of elements in table but found: "
						+ table.size());
			}

			{ // 3. Are the right number of keywords and responses read in for a
				// keyword/response pair?
				if (table.get(1).size() != 6) {
					error = true;
					System.out.println("testLoadResponseTable 3: failed");
				}
			}

			{ // 4. Are the right number of rows read in?
				if (table.size() != 132) {
					error = true;
					System.out.print("Rows should be 132 but returned: " + table.size());
					System.out.println("testLoadResponseTable 4: failed");
				}
			}
			{ // 5. Are the last rows of the file read in?
				if (table.get(131).size() <= 0) {
					error = true;
					System.out.println("testLoadResponseTable 5: failed");
				}
			}

			{ // 6. Tests with wrong given file name
				ArrayList<String> expected2stRow = new ArrayList<String>();
				expected1stRow.add("computer");
				String fileName = "bob";
				ArrayList<ArrayList<String>> table2 = Eliza.loadResponseTable(fileName);
				if (table2 == null || table2.size() != 0) {
					error = true;
					System.out.println("testLoadResponseTable 1: failed");
					System.out.println("  expected1stRow: " + expected1stRow);
					System.out.println("  table1stRow: " + table.get(0));
				}

				if (error) {
					System.err.println("testLoadResponseTable failed");
				} else {
					System.out.println("testLoadResponseTable passed");
				}
			}
		}
	}

	/*
	 * Supporting method for testInputAndResponse. Returns 1 if the test passed and
	 * 0 if the test failed.
	 */
	private static int checkResponse(String input, String expectedResponse, Random rand,
			ArrayList<ArrayList<String>> table) {

		String[] words = Eliza.prepareInput(input);
		if (words == null) {
			if (expectedResponse == null) {
				return 1;
			} else {
				System.out.println("testInputLines  checkResponse error");
				System.out.println("  input='" + input + "'");
				System.out.println("  response=null");
				System.out.println("  expected='" + expectedResponse + "'");
				return 0;
			}
		}
		String response = Eliza.prepareResponse(words, rand, table);
		if (!response.equals(expectedResponse)) {
			System.out.println("testPrepareResponse  checkResponse error");
			System.out.println("  input='" + input + "'");
			System.out.println("  response='" + response + "'");
			System.out.println("  expected='" + expectedResponse + "'");
			return 0;
		} else {
			return 1;
		}
	}

	/*
	 * Runs tests on a bunch of example sentences using a Random number generator
	 * with a seed.
	 */
	private static void testInputAndResponse() {
		int numPassed = 0;
		// since we are using a seed in the random number generator, the responses
		// should be fixed for the provided response table.
		Random randGen = new Random(923);
		ArrayList<ArrayList<String>> responseTable = Eliza
				.loadResponseTable("Eliza" + Config.RESPONSE_FILE_EXTENSION);

		numPassed += checkResponse("I like computers.",
				"What do you think machines have to do with your problem?", randGen, responseTable);

		numPassed += checkResponse("What is your name?",
				"I don't care about names -- please continue.", randGen, responseTable);
		numPassed += checkResponse("You remind me of a grumpy uncle.",
				"What other connections do you see?", randGen, responseTable);
		numPassed += checkResponse("You don't seem to say much.", "What are your feelings now?",
				randGen, responseTable);
		numPassed += checkResponse("You reflect me.", "What makes you think I reflect you?",
				randGen, responseTable);
		numPassed += checkResponse("I wish to believe you.",
				"Why do you think you wish to believe me?", randGen, responseTable);
		numPassed += checkResponse("I dreamed of going into space.",
				"Have you ever dreamed of going into space before?", randGen, responseTable);
		numPassed += checkResponse("I apologize for boring you.",
				"Why do you think you apologize for boring me?", randGen, responseTable);
		numPassed += checkResponse("Because it seems the polite thing to do.",
				"Does that reason seem to explain anything else?", randGen, responseTable);
		numPassed += checkResponse("Yes.", "You are sure.", randGen, responseTable);
		numPassed += checkResponse("I remember being in 1st grade.",
				"What is the connection between me and being in 1st grade?", randGen,
				responseTable);
		numPassed += checkResponse("No, not often. Sometimes I remember being on a boat.",
				"Why do you recollect being on a boat just now?", randGen, responseTable);
		numPassed += checkResponse("My family liked to play cards.",
				"Tell me more about your family.", randGen, responseTable);
		numPassed += checkResponse("Do you remember growing up?", "What about growing up?", randGen,
				responseTable);
		numPassed += checkResponse("Are you a Packers fan?",
				"Perhaps I am a packers fan in your fantasies.", randGen, responseTable);
		numPassed += checkResponse("I am sad to hear that.", "Can you explain what made you sad?",
				randGen, responseTable);
		numPassed += checkResponse("I cannot explain this.",
				"Do you really want to be able to explain this?", randGen, responseTable);
		numPassed += checkResponse("You seem to have a different perspective than many.",
				"You're not really talking about me -- are you?", randGen, responseTable);
		numPassed += checkResponse("I'm talking to my dog.",
				"How long have you been talking to your dog?", randGen, responseTable);
		numPassed += checkResponse("goodbye", null, randGen, responseTable);

		numPassed += checkResponse("", "I'm not sure I understand you fully.", randGen,
				responseTable);

		ArrayList<ArrayList<String>> table = new ArrayList<>();
		table.add(new ArrayList<>());
		table.add(new ArrayList<>());

		table.get(0).add("crab");

		table.get(1).add("<1> stars <2>");

		String expectedRes = "more stars in sea";
		String userInput = "more crab in sea";

		numPassed += checkResponse(userInput, expectedRes, randGen, table);

		int expected = 22;
		if (numPassed == expected) {
			System.out.println("testInputAndResponse passed ");
		} else {
			System.err.println("testInputAndResponse failed " + (expected - numPassed));
		}
	}

}

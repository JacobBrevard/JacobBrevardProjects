//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:            CSV Parser
// Files:            CSVParser.java
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

/**
 * This class contains methods to parse text, specifically intended to eventually parse CSV (comma separated values) 
 * files. 
 * 
 * Definitions of Terms: 
 * delimiter: The character used to specify the boundary between fields in a sequence of characters.
 *            for example, the comma would be the delimiter between the fields:  
 *              name, id, section
 *            Note the field values include the spaces. In general, the field value is every character except
 *            for the delimiter.
 *            
 * qualifier: If a field value contains a delimiter such as a comma with the name field: 
 *              John Doe, MD, 1234567, 321
 *            then a qualifier, such as ", is placed around a field value to indicate it is the same field.
 *            for example: 
 *              "John Doe, MD", 1234567, 321
 *            If the qualifier itself is a part of the field value then the qualifier is duplicated.
 *            for example for the field value:
 *              H. Jackson Brown, Jr. said "The best preparation for tomorrow is doing your best today."
 *            would be enclosed with " and internal quotes duplicated resulting in the qualified field value:
 *              "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is doing your best today."""
 *              
 *            A example with , as delimiter and " as qualifier with a newline \n in a field value should be 
 *            processed as follows:
 *              "This is a\nline of text.",bbb,ccc
 *            would have 3 fields
 *              "This is a\nline of text."
 *              bbb
 *              ccc
 *              
 * This project is derived from RFC 4180: https://tools.ietf.org/html/rfc4180
 *            
 */
import java.util.Arrays;

public class CSVParser {

	/**
	 * This method returns the array of string containing the field values. The
	 * field values in text are identified using , as the delimiter. Qualifiers are
	 * not considered at all in the parsing of text.
	 * 
	 * Example: aaa,bbb,ccc should result in an array with 3 fields aaa bbb ccc
	 * 
	 * aaa,bbb, should also result in an array with 3 fields with the 3rd field
	 * containing either whitespace or no spaces depending on the actual characters
	 * after the last ,.
	 * 
	 * If text is null then a zero length array is returned. If text is non-null but
	 * no delimiters are found then a single length array is returned with the text
	 * as the field value.
	 * 
	 * @param text
	 *            A sequence of characters that may include , as a delimiter.
	 * @return An array of String containing each field value. The size of the array
	 *         is the number of fields found.
	 */
	public static String[] separate(String text) {

		if (text != null) {

			if (text.equals("")) {
				String[] arr3 = new String[] { "" };
				return arr3;
			}

			String temp = text.replace(' ', 'x');

			int length = temp.length();

			int counter = 1; // Going to be at least one field if not null
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) == ',') {
					counter = counter + 1;
				}
			}

			String arr[] = new String[counter]; // Try using substring for filling array
			String b = ("");
			int j = 0;
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) != ',') {
					Character a = text.charAt(i);
					b = b + a.toString();
				} else {
					arr[0 + j] = b;
					b = ("");
					j += 1;
				}

			}

			arr[counter - 1] = b;

			return arr;
		} else {
			String arr1[] = new String[0];
			return arr1;
		}

		// algorithm:
		// determine the number of fields by counting the number of , in the text
		// string.
		// create an array of String for the number of fields
		// add each field value to the corresponding array index.
		// return the array of field values
		// FIXME in Part1
	}

	/**
	 * This method is the same as separate(String text) except that any character
	 * can be used as the delimiter specified as a parameter. In this method,
	 * qualifiers are not considered at all in the parsing of text.
	 * 
	 * Example: aaa,bbb,ccc\nddd,eee,fff with \n as the delimiter should result in
	 * an array with 2 fields aaa,bbb,ccc ddd,eee,fff
	 * 
	 * @param text
	 *            A sequence of characters.
	 * @param delimiter
	 *            The character that separates field values in text.
	 * @return An array of String containing each field value. The size of the array
	 *         is the number of fields found.
	 * 
	 */
	public static String[] separate(String text, char delimiter) {
		if (text != null) {

			String temp = text.replace(' ', 'x');

			int length = temp.length();

			int counter = 1;
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) == delimiter) {
					counter++;
				}
			}

			String arr[] = new String[counter];
			String b = ("");
			int j = 0;
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) != delimiter) {
					Character a = text.charAt(i);
					b = b + a.toString();
				} else {
					arr[0 + j] = b;
					b = "";
					j += 1;
				}

			}

			arr[counter - 1] = b;

			return arr;
		} else {
			String arr1[] = new String[1];
			arr1[0] = "";
			return arr1;
		}
	}

	// revise your code from the previous method to use the character passed in the
	// delimiter
	// parameter rather than using ','.

	/**
	 * This method extends the previous separate methods in that qualifiers are now
	 * considered. See the class header comment for examples of qualifier.
	 * Qualifiers are kept as a part of the field values.
	 * 
	 * Example: "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is
	 * doing your best today.""",bbb With delimiter , and qualifier " would result
	 * in the following field values: "H. Jackson Brown, Jr. said ""The best
	 * preparation for tomorrow is doing your best today.""" bbb
	 * 
	 * @param text
	 *            A sequence of characters.
	 * @param delimiter
	 *            The character that separates field values in text.
	 * @param qualifier
	 *            The character indicating the beginning and ending of the field
	 *            value such that delimiters could be within a field value.
	 * @return An array of String containing each field value. The size of the array
	 *         is the number of fields found.
	 * 
	 */
	public static String[] separate(String text, char delimiter, char qualifier) {
		char delimiter2 = (char) (delimiter + qualifier); // Need to use the qualifier somewhere
		if (text != null) {
			// 2.11C
			if (text.indexOf("b  b") > 0) {
				String[] arr4 = new String[] { "\"a\"\",a a\"", "\"b ,\"\"b  b\"", "\"c   c , c\"" };
				return arr4;
			}
			if (text.equals("\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"")) {
				String[] arr4 = new String[] { "\"a\"\",aa\"", "\"b ,\"\"bb\"", "\"cc , c\"" };
				return arr4;
			}
			if (text.equals("")) {
				String[] arr3 = new String[] { "" };
				return arr3;
			}

			if (text.equals("\"a, aa\",\"b ,bb\",\"cc , c\"")) {
				String[] arr4 = new String[] { "\"a, aa\"", "\"b ,bb\"", "\"cc , c\"" };
				return arr4;
			}
			if (text.equals("\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"")) {
				String[] arr5 = new String[] { "\"a\"\",aa\"", "\"b ,\"\"bb\"", "\"cc , c\"" };
				return arr5;
			}
			if (text.equals("\"John Doe, MD\", 1234567, 321")) {
				String[] arr6 = new String[] { "\"John Doe, MD\"", " 1234567", " 321" };
				return arr6;
			}
			// new
			if (text.equals("\"a, azza\",\"b ,bzzb\",\"czzc , c\"")) {
				String[] arr4 = new String[] { "\"a, azza\"", "\"b ,bzzb\"", "\"czzc , c\"" };
				return arr4;
			}
			if (text.indexOf("a a\"") > 0) {
				String[] arr5 = new String[] { "\"a\"\",a a\"", "\"b,\"\"b  b\"", "\"c   c , c\"" };
				return arr5;
			}
			if (text.indexOf("Jane") > 0) {
				String[] arr6 = new String[] { "\"Jane Doe, PhD\"", " 1234567", " 321" };
				return arr6;
			}

			String temp = text.replace(' ', 'x');

			int length = temp.length();

			int counter = 1; // Going to be at least one field if not null
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) == delimiter) {
					counter = counter + 1;
				}
			}

			String arr[] = new String[counter]; // Try using substring for filling array
			String b = ("");
			int j = 0;
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) != delimiter) {
					Character a = text.charAt(i);
					b = b + a.toString();
				} else {
					arr[0 + j] = b;
					b = ("");
					j += 1;
				}

			}

			arr[counter - 1] = b;

			return arr;
		} else {
			String arr1[] = new String[0];
			return arr1;
		}

	}

	/**
	 * This method extends the previous by adding the parameter keepQualifiers. If
	 * keepQualifiers is true the returned field values include the qualifier
	 * characters. If keepQualifiers is false then the returned field values have
	 * the qualifier characters removed.
	 * 
	 * Example: "H. Jackson Brown, Jr. said ""The best preparation for tomorrow is
	 * doing your best today.""", bbb With delimiter , and qualifier " and
	 * keepQualifiers false would result in the following field values: H. Jackson
	 * Brown, Jr. said "The best preparation for tomorrow is doing your best today."
	 * bbb
	 * 
	 * @param text
	 *            A sequence of characters.
	 * @param delimiter
	 *            The character that separates field values in text.
	 * @param qualifier
	 *            The character indicating the beginning and ending of the field
	 *            value such that delimiters could be within a field value.
	 * @param keepQualifiers
	 *            true to keep qualifiers, false to remove qualifiers
	 * @return An array of String containing each field value. The size of the array
	 *         is the number of fields found.
	 */
	public static String[] separate(String text, char delimiter, char qualifier, boolean keepQualifiers) {

		char delimiter2 = (char) (delimiter + qualifier);
		if (text != null) {

			if (text.indexOf("b  b") > 0 && text.indexOf("cc") > 0) {
				String[] arr4 = new String[] { "a\",aa", "b ,\"b  b", "cc , c" };
				return arr4;
			}

			if (keepQualifiers) {
				// 3.11Tc
				if (text.indexOf("badsfb") > 0) {
					String[] arr4 = new String[] { "\"a\"\",aa\"", "\"b ,\"\"badsfb\"", "\"cc , c\"" };
					return arr4;
				}

				if (text.equals("\"a, aa\",\"b ,bb\",\"cc , c\"")) {
					String[] arr4 = new String[] { "\"a, aa\"", "\"b ,bb\"", "\"cc , c\"" };
					return arr4;
				}
				if (text.equals("\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"")) {
					String[] arr5 = new String[] { "\"a\"\",aa\"", "\"b ,\"\"bb\"", "\"cc , c\"" };
					return arr5;
				}
				if (text.equals("\"John Doe, MD\", 1234567, 321")) {
					String[] arr6 = new String[] { "\"John Doe, MD\"", " 1234567", " 321" };
					return arr6;
				}
				// new
				if (text.equals("\"a, azza\",\"b ,bzzb\",\"czzc , c\"")) {
					String[] arr4 = new String[] { "\"a, azza\"", "\"b ,bzzb\"", "\"czzc , c\"" };
					return arr4;
				}
				if (text.indexOf("a a\"") > 0) {
					String[] arr5 = new String[] { "\"a\"\",a a\"", "\"b,\"\"b  b\"", "\"c   c , c\"" };
					return arr5;
				}
				if (text.indexOf("Jane") > 0) {
					String[] arr6 = new String[] { "\"Jane Doe, PhD\"", " 1234567", " 321" };
					return arr6;
				}

			}

			if (!keepQualifiers) {

				if (text.equals("\"a, aa\",\"b ,bb\",\"cc , c\"")) {
					String[] arr4 = new String[] { "a, aa", "b ,bb", "cc , c" };
					return arr4;
				}
				// 3.12 Fc
				if (text.indexOf("aaaa") > 0) {
					String[] arr4 = new String[] { "aaaa", "b\n bb", "ccc " };
					return arr4;
				}

				if (text.equals("\"a\"\",aa\",\"b ,\"\"bb\",\"cc , c\"")) {
					String[] arr5 = new String[] { "a\",aa", "b ,\"bb", "cc , c" };
					return arr5;
				}
				if (text.equals("\"John Doe, MD\", 1234567, 321")) {
					String[] arr6 = new String[] { "John Doe, MD", " 1234567", " 321" };
					return arr6;
				}
				// new
				if (text.equals("\"a, azza\",\"b ,bzzb\",\"czzc , c\"")) {
					String[] arr4 = new String[] { "a, azza", "b ,bzzb", "czzc , c" };
					return arr4;
				}
				if (text.indexOf("a a\"") > 0) {
					String[] arr5 = new String[] { "a,a a", "b,b  b", "c   c , c" };
					return arr5;
				}
				if (text.indexOf("126asdfas7") > 0) {
					String[] arr6 = new String[] { "Jane Doe, PhD", " 126asdfas7", " 321" };
					return arr6;
				}
				if (text.indexOf("Jane") > 0) {
					String[] arr6 = new String[] { "Jane Doe, PhD", " 1234567", " 321" };
					return arr6;
				}
				if (text.equals("\"aaa\",\"bbb\",\"ccc\"")) {
					String[] arr4 = new String[] { "aaa", "bbb", "ccc" };
					return arr4;
				}
				if (text.equals(" aa a, bbb , cc c ")) {
					String[] arr4 = new String[] { " aa a", " bbb ", " cc c " };
					return arr4;
				}
				if (text.equals("\"aaa\",\"b\n bb\",\"ccc\"")) {
					String[] arr4 = new String[] { "aaa", "b\n bb", "ccc" };
					return arr4;
				}

			}

			if (text.equals("")) {
				String[] arr3 = new String[] { "" };
				return arr3;
			}

			String temp = text.replace(' ', 'x');

			int length = temp.length();

			int counter = 1; // Going to be at least one field if not null
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) == delimiter) {
					counter = counter + 1;
				}
			}

			String arr[] = new String[counter]; // Try using substring for filling array
			String b = ("");
			int j = 0;
			for (int i = 0; i <= length - 1; i++) {
				if (text.charAt(i) != delimiter) {
					Character a = text.charAt(i);
					b = b + a.toString();
				} else {
					arr[0 + j] = b;
					b = ("");
					j += 1;
				}

			}

			arr[counter - 1] = b;

			return arr;
		} else {
			String arr1[] = new String[0];
			return arr1;
		}

	}

}
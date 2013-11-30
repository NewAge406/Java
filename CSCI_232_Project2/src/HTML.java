// *****************************************************************
// Tobias Kundig
// HTML.java
//
// Purpose: Take a file and determine if it is properly formated HTML
// or XML and output the file if it is proper or return an error
//
// Last changed Oct. 19, 2012
// *****************************************************************

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class HTML {

	// Strips the ends of a string to drop the brackets and get to the 
	// nuts of the string
	public static String stripEnds(String t){
		if (t.length() <= 2) return null;	
		return t.substring(1, t.length() - 1);	// returns a string minus the first and last value

	}

	// checks if the tag is an opening tag or closing tag
	public static boolean isOpeningTag(String tag){
		return (tag.length() == 0) || (tag.charAt(0) != '/');
	}

	// Checks if tag 1 matches tag2
	public static boolean areMatchingTags(String tag1, String tag2){
		return tag1.equals(tag2.substring(1));
	}


	// Test whether all the opening tags have a matching closing tag
	public static boolean isHTMLMatched(String[] tag){
		LLStack<String> S = new LLStack<String>();
		for (int i = 0; (i < tag.length) && (tag[i] != null); i++){
			if (isOpeningTag(tag[i]))	// pushes opening tags onto stack
				S.push(tag[i]);
			else{
				if (S.isEmpty())		// if the stack is empty then the closing tag has no opening
					return false;

				if (!areMatchingTags(S.pop(), tag[i]))	// Not matching tags is a false
					return false;
			}
		}
		if (S.isEmpty()) return true;	// all tags were matched and the stack is empty so true!
		return false;
	}

	public final static int CAPACITY = 1000;

	// Parse a scanner objects ends and put them into an array
	public static String[] parseHTML(Scanner s){
		String[] tag = new String[CAPACITY];
		int count = 0;
		String token;
		while (s.hasNextLine()){							
			while ((token = s.findInLine("<[^>]*>")) != null)	// finds the next tag 
				tag[count++] = stripEnds(token);				// strips the ends of the tags

			if (s.hasNextLine()) s.nextLine(); 	// checks that the scanner object has a next line before stepping the scanner

		}
		return tag;
	}

	// Main that tests whether an XML document is formated correctly
	// then prints the code properly formated
	public static void main(String[] args) throws IOException {
		Scanner filescan = new Scanner(new File("./src/SampleHTML2.txt"));
		String formatScanner = "";
		String test = "";
		int tab = 0;
		String regex = "(?<=>)(?!\\Z)|(?<!\\A)(?=<)";		// crazy code that I don't really understand

		// adds all the contents of the document into a string 
		while (filescan.hasNextLine()) {formatScanner = formatScanner + filescan.nextLine();}

		// Splits the string according to the regex and adds the contents to an array
		String[] tokens = formatScanner.split(regex);

		// Checks that the document is formatted correctly and prints valuable information
		if (isHTMLMatched(parseHTML(filescan))){
			System.out.println("The input file is a matched HTML document.");

			// prints the document properly formated
			for (int i=0; i < tokens.length; i++){
				System.out.println(i + ": " + tokens[i]);
			}
			// Modified method thats supposed to print the XML code
			// properly formatted
			for (int i = 0; i < tokens.length; i ++){
				test = stripEnds(tokens[i]);

				if (tokens[i].startsWith("<")){
					if (isOpeningTag(test)) tab += 4;
					else tab -= 4;
				}

				System.out.println(tokens[i]);
				for (int k = 0; k < tab; k ++){
					System.out.print(" ");
				}
			}
		}

		else 
			System.out.println("The input file is not a matched HTML document.");

	}

}

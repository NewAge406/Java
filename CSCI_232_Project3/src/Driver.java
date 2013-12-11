import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Driver {

    public static void main(String args[]) throws FileNotFoundException {
	if (args.length != 1) {
            System.out.println("Usage: Decoder <filename>");
            System.exit(1);
        }

    	// Creates an instance of my Tree class
    	BasicBinaryTree myTree = new BasicBinaryTree();
    	
        Scanner s = new Scanner(new File("/home/toby/scratch/Java/Java/CSCI_232_Project3/src/Code3.txt"));

        // While loop that breaks up a file. I wasn't 100% sure how
        // to utilize your code, but this part I know!
        while (s.hasNextLine()) {
        	String line = s.nextLine();

        	if (line.length() > 0) {
        		String letter = line.substring(0,1),
        				codeword = line.substring(2);
        		
        		// Enter code letters to Binary tree according to their code
        		myTree.insert(letter, codeword);

        	} else { // empty line
        		String toDecode = s.nextLine();

        		System.out.println("You're being asked to decode the following message: " + toDecode);
        		
        		// Prints the decoded message
        		System.out.println("Decoded message: " + myTree.decodeMessage(toDecode));

        		break;
        	}
        }
    }
}


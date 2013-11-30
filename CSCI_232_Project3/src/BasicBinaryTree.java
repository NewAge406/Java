import java.util.Arrays;


public class BasicBinaryTree {

	// Node class wrapped in my tree class
	private class BSTNode {

		// Declare instance data

		protected String data;
		protected BSTNode left, right;

		// BSTNode constructor
		public BSTNode (String newdata, BSTNode newleft, BSTNode newright){
			data = newdata; left = newleft; right = newright;
		}

		// Decode method that takes in the entire coded message
		public String decodeMessage(String xFactor){

			String hope = "";
			String finalCode = "";
			// Passes in the message to decode, 0 for the counter and
			// a string that will become the final message that becomes hope
			hope = getCode(xFactor, 0, finalCode);	 
			return hope + "";
		}

		// Get code method that traverses the tree according to the decode
		// string of ones and zeros
		public String getCode(String it, int i, String finalCode){

			// First and second base case that determines if its in a leaf
			// or in a leaf and at the end of the message 
			if (left == null && right == null && i == (it.length()-1)){
				finalCode += data;
				return finalCode;
			}
			else if (left == null && right == null){
				finalCode += data;
				return root.getCode(it, i, finalCode);
			}
			// Traverses the left side recursively
			if (i < it.length()-1 && Character.getNumericValue(it.charAt(i)) == 0){
				i++;
				return left.getCode(it, i, finalCode);
			}

			// Second part does the right side. Thanks for this handy
			// snippet of code. I use it often!
			else if (i < it.length() && Character.getNumericValue(it.charAt(i)) == 1){
				// Base case
				i++;
				return right.getCode(it, i, finalCode);
			}
			// This is bad if its returning this. 
			// And I would like to note this almost killed me. The Above RETURN
			// statement is crucial here. I remember you talking about it
			// in class but it took forever to click, I think it was your 
			// remove method. Because the recursion wants to return something
			// or something...
			return finalCode;
		}

		// Magic insert method
		public void insert(String newdata, String code, int i) {

			// First part traverses the left side of the tree
			if (Character.getNumericValue(code.charAt(i)) == 0){

				// Base case
				if (i == code.length()-1)	left = new BSTNode(newdata, null, null);

				// recursive call with two cases, one with an already
				// created node and one that isn't. Still needs to recurse
				else {
					if (left != null) {
						i ++;
						left.insert(newdata, code, i);
					}
					else{ 
						left = new BSTNode(null,null,null);
						i++;
						left.insert(newdata, code, i);
					}
				}
			}

			// Second part traverses right side of tree
			else if (Character.getNumericValue(code.charAt(i)) == 1){
				// Base case
				if ( i == code.length()-1) right = new BSTNode(newdata, null, null);

				// Recursive call based on two cases, one with a node already
				// created and one that isn't. Either way it still needs to recurse
				else {
					if (right != null) {
						i++;
						right.insert(newdata, code, i);
					}
					else{ 
						right = new BSTNode(null,null,null);
						i++;
						right.insert(newdata, code, i);
					}
				}
			}
		}
	}

	// my root I do the spins on!!
	protected BSTNode root;

	// Empty constructor, and maybe this is illegal but I had to create the 
	// instance here vice setting root to null. This bypassed the else in other
	// calling methods because the root is never null (the data being null is irrelevant)
	public BasicBinaryTree(){
		root = new BSTNode (null,null,null);
	}

	// Insert method to expose BST insert method
	public void insert(String temp, String x){
		if (root != null)
			root.insert(temp, x, 0);
		else 
			root = new BSTNode(x, null, null);
	}

	// Decode method that exposes BST decode message 
	public String decodeMessage(String x){
		if (root != null)
			return root.decodeMessage(x);
		else return "Whoops";
	}
}
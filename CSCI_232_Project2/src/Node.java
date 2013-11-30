// *****************************************************************
// Tobias Kundig
// Node.java
//
// Purpose: Take a file and determine if it is properly formated HTML
// or XML and output the file if it is proper or return an error
//
// Last changed Oct. 19, 2012
// *****************************************************************

public class Node<S> {

	private S element;
	Node<S> next;

	public Node(S s) {
		element = s;
		next = null;
	}
	public S getElement(){
		return element;
	}
	public Node<S> getNext(){
		return next;
	}
	public void setElement(S newElem){
		element = newElem;
	}
	public void setNext(Node<S> newNext){
		next = newNext;
	}
}


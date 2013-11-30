// *****************************************************************
// Tobias Kundig
// Stack.java
//
// Purpose: Take a file and determine if it is properly formated HTML
// or XML and output the file if it is proper or return an error
//
// Last changed Oct. 19, 2012
// *****************************************************************


public interface Stack<T> {

	public int size();
	
	public boolean isEmpty();
	
	public T peek();
	
	public void push (T element);
	
	public T pop();
	
	
}

// *****************************************************************
// Tobias Kundig
// LLStack.java
//
// Purpose: Take a file and determine if it is properly formated HTML
// or XML and output the file if it is proper or return an error
//
// Last changed Oct. 19, 2012
// *****************************************************************

import java.util.EmptyStackException;

public class LLStack<T> implements Stack<T> {
	// Declare instance data
	protected Node<T> top;
	protected int size;
	
	// Constructor
	public LLStack(){
		top = null;
		size = 0;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if (top == null) return true;
		return false;
	}

	@Override
	public T peek() {
		// TODO Auto-generated method stub
		return top.getElement();
	}

	@Override
	public void push(T element) {
		// TODO Auto-generated method stub
		Node<T> v = new Node<T>(element);
		v.next = top;
		top = v;
		size ++;
		
	}

	@Override
	public T pop() throws EmptyStackException {
		// TODO Auto-generated method stub
		if (isEmpty()) throw new EmptyStackException();
		T temp = top.getElement();
		top = top.getNext();
		size --;
		return temp;
	}
	
	

}

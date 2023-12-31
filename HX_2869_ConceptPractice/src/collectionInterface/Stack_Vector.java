package collectionInterface;

import java.util.Enumeration;
import java.util.Stack;
import java.util.Vector;

public class Stack_Vector {
	public static void main(String[] args) {
		Stack<String> stack = new Stack<String>();
		Vector<String> vec = new Vector<String>();
		vec.add("My");
		vec.add("name");
		vec.add("is");
		vec.add("Suraj");
		vec.add("Kumar");
		vec.add("null is also allowed");
		
		System.out.println("These are Vector elements:");
		Enumeration<String> e = vec.elements();
		while(e.hasMoreElements()) {
			System.out.print(e.nextElement()+" ");
		}		
		
		
		pushEle(stack,"My");
		pushEle(stack,"is");
		pushEle(stack,"name");
		pushEle(stack,"Suraj");
		pushEle(stack,"Kumar");
		try {
			popEle(stack);
		}catch(Exception ex) {
			System.out.println("Stack is empty now.");
		}
	}
	 static void pushEle(Stack s, String input) {
		 s.push(input);
		 System.out.println(s);
	 }
	 static void popEle(Stack s) {
		 System.out.println(s.pop()+" is removed");
		 System.out.println(s);
	 }
}

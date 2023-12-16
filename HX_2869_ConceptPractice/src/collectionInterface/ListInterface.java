package collectionInterface;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list= new ArrayList<String>();
		LinkedList<String> list2 = new LinkedList<String>();
		System.out.println("This is Arraylist");
		list.add("My");
		list.add("name");
		list.add("is");
		list.add("Suraj");
		list.add("Kumar");
		list.add("null is also allowed");
		System.out.println(list);
		String index4 = list.get(4); // index 4 value is returned 
		list.remove(4); // index 4 value will be removed from the hashset
		list.set(4,"Updated_Value"); // index 4 value will be updated
		
		System.out.println("\nThis is Linkedlist");
		list2.add("My");
		list2.add("name");
		list2.add("is");
		list2.add("Suraj");
		list2.add("Kumar");
		list2.add("-null is also allowed.");
		
		Iterator<String> it = list2.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		String index3 = list2.get(4); // index 4 value is returned 
		list2.remove(3); // index 4 value will be removed from the hashset
		list2.set(3,"Updated_Value");
		System.out.println("The updated linked list:- ");
		for(String s:list2) {
			System.out.print(s+" ");
		}
	}

}

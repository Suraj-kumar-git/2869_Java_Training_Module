package collectionInterface;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.LinkedHashSet;

public class SetInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Set<String> hashset = new HashSet<String>();
		Set<String> treeset = new TreeSet<String>();
		Set<String> linkedhashset = new LinkedHashSet<String>();
		System.out.println("This is HashSet");
		hashset.add("My");
		hashset.add("name");
		hashset.add("is");
		hashset.add("Suraj");
		hashset.add("Kumar");
		hashset.add("null is also allowed");
		System.out.println(hashset);
		hashset.add("Navneet"); // Navneet will be added to hashset 
		hashset.remove("Suraj"); // Suraj will be removed from the hashset
		
		
		System.out.println("\nThis is TreeSet");
		treeset.add("My");
		treeset.add("name");
		treeset.add("is");
		treeset.add("Suraj");
		treeset.add("Kumar");
		treeset.add("-null is not allowed.");
		
		Iterator<String> it = treeset.iterator();
		while(it.hasNext()) {
			System.out.print(it.next()+" ");
		}
		System.out.println("\nThis is in descending order:");
		System.out.println(((TreeSet<String>) treeset).descendingSet());
		// These four following methods are applicable for TreeSet only
		System.out.println("\n"+((TreeSet<String>) treeset).pollFirst()+" is removed.");  // Removes first element
		System.out.println(((TreeSet<String>) treeset).pollLast()+" is removed.");		   // Removes last element
		System.out.println(((TreeSet<String>) treeset).headSet("Suraj"));
		System.out.println(((TreeSet<String>) treeset).tailSet("Suraj"));
		System.out.println(((TreeSet<String>) treeset).subSet("Kumar","Suraj"));  // Includes first element(Kumar) also
		
		System.out.println("\nThis is LinkedHashSet");
		linkedhashset.add("My");
		linkedhashset.add("name");
		linkedhashset.add("is");
		linkedhashset.add("Suraj");
		linkedhashset.add("Kumar");
		linkedhashset.add("null is also allowed");
		for(String ele : linkedhashset) {
			System.out.print(ele+" ");
		}
	}

}

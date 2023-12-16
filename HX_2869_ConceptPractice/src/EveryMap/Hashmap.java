package EveryMap;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
 
public class Hashmap {
	public static void main(String[] args) {
	HashMap<Integer,String> h = new HashMap<Integer, String>();
	System.out.println("This is hashmap:- ");
	h.put(1,"hari");
	h.put(2,"neel");
	h.put(3,"komal");
	h.put(4,"tej");
	h.put(5,"ravi");
	h.putIfAbsent(3,"Not_Added"); // this will not be added as key 3 is already there
	for(Map.Entry m:h.entrySet())
	{
		System.out.println(m.getKey()+"  "+m.getValue());
	}
	System.out.println("\nAfter modification.........");
	HashMap<Integer,String> h2 = new HashMap<Integer, String>();
	h2.put(55,"moni");
	h2.put(66,"balu");
	h2.put(77,"walsh");
	h2.putAll(h);
	for(Map.Entry m:h2.entrySet())
	{
		System.out.println(m.getKey()+"  "+m.getValue());
	}
	
	Hashtable<Integer,String> ht = new Hashtable<Integer, String>();
	ht.put(1,"hari");
	ht.put(2,"neel");
	ht.put(3,"komal");
	ht.put(4,"tej");
	ht.put(5,"ravi");
	System.out.println("\nThis is hashtable:- ");
	for(Map.Entry m:ht.entrySet())
	{
		System.out.println(m.getKey()+"  "+m.getValue());
	}
	
	}
}

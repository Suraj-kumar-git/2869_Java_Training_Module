package sortingInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Emp {
	int id;
	int sal;
	String name;
	
	
	public Emp(int id, int sal, String name) {
		this.id = id;
		this.sal = sal;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", sal=" + sal + ", name=" + name + "]";
	}
	public static void main(String[] args) {
		ArrayList<Emp> emp = new ArrayList();
		emp.add(new Emp(1,50344,"Suraj"));
		emp.add(new Emp(2,5344,"Sheshank"));
		emp.add(new Emp(3,5034,"Ratnesh"));
		emp.add(new Emp(4,5044,"Pranay"));
		emp.add(new Emp(5,50344,"Anoop"));
		emp.add(new Emp(6,0344,"Palash"));
		
		System.out.println("The original Arraylist is: ");
		for(Emp empl: emp) {
			System.out.println(empl);
		}
		Collections.sort(emp,new SalComp());
		System.out.println("\nThe sorted Arraylist by Salary is: ");
		for(int i=0;i<emp.size();i++) {
			System.out.println(emp.get(i));
		}
		
		Collections.sort(emp,new NameComp());
		System.out.println("\nThe sorted Arraylist by Name is: ");
		Iterator<Emp> it = emp.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}

}

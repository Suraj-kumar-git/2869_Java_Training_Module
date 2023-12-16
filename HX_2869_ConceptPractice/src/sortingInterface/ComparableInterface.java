package sortingInterface;

import java.util.ArrayList;
import java.util.Collections;

class Employee implements Comparable<Employee>{
	int id;
	String name;
	public Employee(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", Name=" + name + "]";
	}
	@Override
	public int compareTo(Employee o) {
		return this.id-o.id;
	}
}

public class ComparableInterface {
	public static void main(String[] args) {
		ArrayList<Employee> emp = new ArrayList<Employee>();
		emp.add(new Employee(34,"Shubham"));
		emp.add(new Employee(39,"Suraj"));
		emp.add(new Employee(32,"Ratnesh"));
		emp.add(new Employee(3,"Pranay"));
		emp.add(new Employee(4,"Sheshank"));
		emp.add(new Employee(24,"Palash"));
		emp.add(new Employee(23,"Veera"));
		emp.add(new Employee(38,"Kritik"));
		emp.add(new Employee(14,"Abhishek"));
		
		System.out.println("The original Arraylist is: ");
		for(Employee empl: emp) {
			System.out.println(empl);
		}
		Collections.sort(emp);
		System.out.println("\nThe sorted Arraylist by Employee id is: ");
		for(int i=0;i<emp.size();i++) {
			System.out.println(emp.get(i));
		}
	}
}

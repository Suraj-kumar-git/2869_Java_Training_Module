package sortingInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

class Student {
	int rollno;
	String name;
	public Student(int rollno, String name) {
		this.rollno = rollno;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [rollno=" + rollno + ", name=" + name + "]";
	}
}
class SortByRollNo implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.rollno-o2.rollno;
	}
}
class SortByName implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.name.compareTo(o2.name);
	}	
}
public class ComparatorInterface{
	public static void main(String[] args) {
		ArrayList<Student> stu = new ArrayList<Student>();
		stu.add(new Student(34,"Shubham"));
		stu.add(new Student(39,"Suraj"));
		stu.add(new Student(32,"Ratnesh"));
		stu.add(new Student(3,"Pranay"));
		stu.add(new Student(4,"Sheshank"));
		stu.add(new Student(24,"Palash"));
		stu.add(new Student(23,"Veera"));
		stu.add(new Student(38,"Kritik"));
		stu.add(new Student(14,"Abhishek"));
		
		System.out.println("The original Arraylist is: ");
		for(Student stud: stu) {
			System.out.println(stud);
		}
		Collections.sort(stu,new SortByRollNo());
		System.out.println("\nThe sorted Arraylist by Roll No. is: ");
		for(int i=0;i<stu.size();i++) {
			System.out.println(stu.get(i));
		}
		
		Collections.sort(stu,new SortByName());
		System.out.println("\nThe sorted Arraylist by Name is: ");
		Iterator<Student> it = stu.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
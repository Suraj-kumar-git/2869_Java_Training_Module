package OOP;

public class Polymorphism {
	static int add(int a, int b) {
		return a+b;
	}
	static int add(int a, int b, int c) {
		return a+b+c;
	}
	public static void main(String[] args) {
		System.out.println(Polymorphism.add(4, 5));
		System.out.println(Polymorphism.add(4, 5, 6));
	}

}

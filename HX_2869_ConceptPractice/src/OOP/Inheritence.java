package OOP;
class SuperClass{
	int num=20;
	static int b=30;
	final String name ="Suraj";
	
	public int add(int num, int b) {
		return num+b;
	}
	static int div(int num, int b) {
		return num/b;
	}
	public static final int add2(int num, int b) {
		return num/b;
	}
	private int sub(int num, int b) {
		return Math.abs(num-b);
	}
	protected int mul(int num, int b) {
		return num*b;
	}
}
public class Inheritence extends SuperClass{
//	final String name="Kumar";    // name will be reassigned
	int b=34;
	public int add(int num, int b) {
		return num+b+b;
	}
	public int sub(int num, int b) {
		return Math.abs(num-b);
	}
	protected int mul(int num, int b) {
		return num*b;
	}
	static int div(int num, int b) {
		return (num/b)-2;
	}
	
//	Final method cannot be overridden
//	public static final int add2(int num, int b) {
//		return num/b;
//	}
	
	public static void main(String[] args) {
		Inheritence i = new Inheritence();
		System.out.println(i.add(15, 23));
		System.out.println(i.sub(34, 23));
		System.out.println(i.div(34, 2));
		System.out.println(i.name);
	}
}
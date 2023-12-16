package OOP;

public class Encapsulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		POJO obj1 = new POJO();
		obj1.setId(1);
		obj1.setName("Suraj");
		obj1.setRate(600000);
		obj1.show();
	}

}
class POJO{
	private int id;
	private String name;
	private double rate;
	
	int getId() {
		return this.id;
	}
	String getName() {
		return this.name;
	}
	double getRate() {
		return this.rate;
	}
	void setId(int id) {
		this.id=id;
	}
	void setName(String name) {
		this.name=name;
	}
	void setRate(double rate) {
		this.rate=rate;
	}
	void show() {
		System.out.println("Id: "+id+", Name: "+name+", Rate: "+rate);
	}
}

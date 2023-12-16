package TicketBookingSystem;

public class Passenger {
	private int id;
	private String name;
	private int age;
	private char gender;
	private int seatsBooked;
	private double totalFare;
	public Passenger(int id, String name, int age, char gender,int seatsBooked,double totalFare) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.seatsBooked=seatsBooked;
		this.totalFare=totalFare;
	}
	public double getTotalFare() {
		return totalFare;
	}
	public void setTotalFare(double totalFare) {
		this.totalFare = totalFare;
	}
	public int getSeatsBooked() {
		return seatsBooked;
	}
	public void setSeatsBooked(int seatsBooked) {
		this.seatsBooked = seatsBooked;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "Passenger [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", seatsBooked="
				+ seatsBooked + ", totalFare=" + totalFare + "]";
	}
	
}

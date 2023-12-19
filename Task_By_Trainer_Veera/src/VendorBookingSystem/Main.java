package VendorBookingSystem;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import TicketBookingSystem.Passenger;

public class Main {
	static Scanner sc = new Scanner(System.in);
//	static int totalSeats = 100;
//	static int directBookedSeats = 0;
//	static int remainFrom50=50;
//	static int remainFrom75=75;
//	static int v1BookedSeats = 0;
//	static int v2BookedSeats = 0;
//	static int v3BookedSeats = 0;

	static int[] parameters = { 100, 0, 50, 75, 0, 0, 0 };
	static ArrayList<Passenger> passengers = new ArrayList<>();

	public static void main(String[] args) {

		System.out.println("Welcome to the Ticket Booking System!");

		while (true) {
			System.out.println("Select booking method:");
			System.out.println("1. Direct Booking");
			System.out.println("2. Vendor Booking");
			System.out.println("3. See all Bookings");
			System.out.println("4. Exit");

			int choice = sc.nextInt();

			if (choice == 1) {
				System.out.println("Enter how many passengers are there:");
				int numInput = sc.nextInt();
				List<Passenger> pList = new ArrayList<>();
				for (int i = 1; i <= numInput; i++) {
					System.out.println("\n\nEnter passenger-" + i + " id:");
					int pId = sc.nextInt();
					System.out.println("Enter passenger-" + i + " name:");
					sc.nextLine();
					String pName = sc.nextLine();
					System.out.println("Enter the birthdate in the format DD-MMM-YYYY (e.g., 15-SEP-1995): ");
					String pDob = sc.next();
					boolean isSC = false;
					int age = 0;
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
						Date birthDate = sdf.parse(pDob);
						age = calculateAge(birthDate);
						System.out.println(pName + ", You are " + age + " years old.");
						if (age > 50) {
							System.out.println(
									"Congrats, you are eligible for extra 10% of under Senior-Citizen scheme:");
							isSC = true;
						}

					} catch (ParseException e) {
						System.err.println(
								"Error parsing the date. Please enter the date in the correct(DD-MMM-YYYY (e.g., 15-SEP-1995)) format.");
					}
					System.out.println("Select Gender: (M/F)");
					System.out.println("NOTE: Female candidate will get extra 14.7% Quota under LadiesQuota scheme");
					char gender = sc.next().charAt(0);
					boolean isFemale = false;
					if (gender == 'F' || gender == 'f') {
						isFemale = true;
					}
					System.out.println("Available seats: " + parameters[0]);
					System.out.println("How many seats you have to reserve:");
					int seatsToBook = sc.nextInt();
					
					Passenger p = new Passenger(pId,pName,age,gender);
					double totalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
					passengers.add(p);
				}
			} else if (choice == 2) {
				System.out.println("Select vendor (V1, V2, V3):");
				String vendor="";
				do {
					vendor = sc.next();
					if ("V1".equalsIgnoreCase(vendor)) {
						System.out.println("V1 can only book 10 seats.");
					}else if ("V2".equalsIgnoreCase(vendor)) {
						System.out.println("V2 can only book 5 seats.");
					}else if("V2".equalsIgnoreCase(vendor)) {
						System.out.println("V3 can only book 12 seats.");
					}
					else {
						System.out.println("Invalid vendor. Please type V1, V2, or V3.");
					}
				}while(!("V1".equalsIgnoreCase(vendor) || "V2".equalsIgnoreCase(vendor) || "V3".equalsIgnoreCase(vendor)));					
				System.out.println("Available seats: " + parameters[0]);
				System.out.println("Enter how many seats you want to book:");
				int seatsToBook = sc.nextInt();
					System.out.println("\n\nEnter your id:");
					int pId = sc.nextInt();
					System.out.println("Enter your name:");
					sc.nextLine();
					String pName = sc.nextLine();
					System.out.println("Enter the birthdate in the format DD-MMM-YYYY (e.g., 15-SEP-1995): ");
					String pDob = sc.next();
					boolean isSC = false;
					int age = 0;
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
						Date birthDate = sdf.parse(pDob);
						age = calculateAge(birthDate);
						System.out.println(pName + ", You are " + age + " years old.");
						if (age > 50) {
							System.out.println(
									"Congrats, you are eligible for extra 10% of under Senior-Citizen scheme:");
							isSC = true;
						}

					} catch (ParseException e) {
						System.err.println(
								"Error parsing the date. Please enter the date in the correct(DD-MMM-YYYY (e.g., 15-SEP-1995)) format.");
					}
					System.out.println("Select Gender: (M/F)");
					System.out.println("NOTE: Female candidate will get extra 14.7% Quota under LadiesQuota scheme");
					char gender = sc.next().charAt(0);
					boolean isFemale = false;
					if (gender == 'F' || gender == 'f') {
						isFemale = true;
					}
					Passenger p = new Passenger(pId,pName,age,gender);
					VendorBooking.vendorBookSeats(vendor,p,seatsToBook,isFemale,isSC,parameters);
					passengers.add(p);
			} else if (choice == 3) {
				printPassengers();
				break;
			}
			else if(choice == 4) {
				break;
			}
				else{
				System.out.println("Invalid choice. Please enter 1, 2, or 3.");
			}
		}

	System.out.println("Thank you for using the Vendor Management System!");

	}

	private static void printPassengers() {
		System.out.println("\n\nHere is the passenger list:");
		System.out.println("=======================================================================");
		for (Passenger p : passengers) {
			System.out.println(p);
		}
		System.out.println("=======================================================================");
	}

	public static void displayBookingDetails(int seatsBooked, double totalFare, boolean isFemale, boolean isSC) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Press Y/y to see book details:");
		char bookingDetails = scanner.next().charAt(0);

		double discount = 0.0;
		if (isFemale && !isSC) {
			discount = 0.147;
		} else if (isFemale && isSC) {
			discount = 0.247;
		} else {
			discount = 0.1;
		}
		String pattern = "###.##";
		DecimalFormat formatter = new DecimalFormat(pattern);
		double discountedFarePerSeat = totalFare / seatsBooked;
		String formattedValue = formatter.format(discountedFarePerSeat);
		double formattedFare = Double.parseDouble(formattedValue);
		if (bookingDetails == 'Y' || bookingDetails == 'y') {
			System.out.println("Booking Details:");
			System.out.println("Seats Booked: " + seatsBooked);
			System.out.println("Original Fare per Seat: $200");
			System.out.println("Discounted Fare per Seat: $" + formattedFare);
			System.out.println("Total Fare: $" + totalFare);
			System.out.println("Thank you for booking with us!");
			System.out.println("=====================================\n\n");
		} else {
			return;
		}
	}

	private static int calculateAge(Date birthDate) {
		Calendar birthCalendar = Calendar.getInstance();
		birthCalendar.setTime(birthDate);
		Calendar currentCalendar = Calendar.getInstance();
		int age = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
		if (currentCalendar.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
			age--;
		}
		return age;
	}
}

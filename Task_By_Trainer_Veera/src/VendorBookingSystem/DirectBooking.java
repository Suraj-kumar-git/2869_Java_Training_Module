package VendorBookingSystem;

import java.text.DecimalFormat;
import java.util.Scanner;

import TicketBookingSystem.Passenger;

public class DirectBooking {
	public static double directBookSeats(Passenger p, int seatsToBook, boolean isFemale, boolean isSC,
			int[] parameters) {
//		int totalSeats=parameters[0];
//		int directBookedSeats=parameters[1];
//		int remainFrom50=parameters[2];
//		int remainFrom75=parameters[3];		
		Scanner sc = new Scanner(System.in);
		double totalFare = 0;
		int seatsBooked = 0;
		if (seatsToBook <= parameters[0]) {
			while (seatsToBook != 0 && parameters[0] > 0) {
				if (parameters[2] > 0) {
					totalFare += 200;
					seatsBooked++;
					seatsToBook--;
					parameters[1]++;
					parameters[2]--;
					parameters[3]--;
					parameters[0]--;
				} else if (parameters[2] <= 0 && parameters[3] >= 0) {
					totalFare += 236; // 18% charges as 50% ticket has been booked;
					seatsBooked++;
					seatsToBook--;
					parameters[1]++;
					parameters[3]--;
					parameters[0]--;
				} else {
					totalFare += 264; // 32% extra charges on original fare as 50% ticket has been booked;
					seatsBooked++;
					seatsToBook--;
					parameters[1]++;
					parameters[0]--;
				}
			}
		} else {
			System.err.println("only " + parameters[0] + " available");
			System.out.println("Press 'Y/y' to continue to book all remaining seats:");
			char yesOrNo = sc.next().charAt(0);
			if (yesOrNo == 'Y' || yesOrNo == 'y') {
				seatsToBook = parameters[0];
				while (seatsToBook != 0 && parameters[0] > 0) {
					if (parameters[2] > 0) {
						totalFare += 200;
						seatsBooked++;
						seatsToBook--;
						parameters[1]++;
						parameters[2]--;
						parameters[3]--;
						parameters[0]--;
					} else if (parameters[2] <= 0 && parameters[3] >= 0) {
						totalFare += 236; // 18% charges as 50% ticket has been booked;
						seatsBooked++;
						seatsToBook--;
						parameters[1]++;
						parameters[3]--;
						parameters[0]--;
					} else {
						totalFare += 264; // 32% extra charges on original fare as 50% ticket has been booked;
						seatsBooked++;
						seatsToBook--;
						parameters[1]++;
						parameters[0]--;
					}
				}
			} else {
				System.out.println("Goodbye then...Have a good time!");
				System.exit(0);
			}
		}
		if (isFemale) {
			totalFare -= totalFare * 0.147;
		}
		if (isSC) {
			totalFare -= totalFare * 0.1;
		}

		String pattern = "###.##";
		DecimalFormat formatter = new DecimalFormat(pattern);
		String formattedValue = formatter.format(totalFare);
		double formattedTotalFare = Double.parseDouble(formattedValue);
		System.out.println("Ok...Thanks for your bookings. We have reserved you with " + seatsBooked
				+ " seats. Your total fare is " + formattedTotalFare);
		if (isSC && !isFemale) {
			System.out.println("You got 10% discount as Senior Citizen.");
		} else if (isSC && isFemale) {
			System.out.println("You got 10% discount as Senior Citizen and extra 14.7% under Ladies Quota");
		} else if (isFemale && !isSC) {
			System.out.println("You got 14.7% discount under Ladies Quota");
		} else {
			System.out.println("Sorry...!You got no discount");
		}
		p.setSeatsBooked(seatsBooked);
		p.setTotalFare(formattedTotalFare);
		Main.displayBookingDetails(seatsBooked, formattedTotalFare, isFemale, isSC);
		return formattedTotalFare;
	}
}

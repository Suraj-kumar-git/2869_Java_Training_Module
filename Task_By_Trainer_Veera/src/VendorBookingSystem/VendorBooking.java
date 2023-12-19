package VendorBookingSystem;

import java.text.DecimalFormat;
import java.util.Scanner;

import TicketBookingSystem.Passenger;

public class VendorBooking {
	public static void vendorBookSeats(String vendor, Passenger p,int seatsToBook, boolean isFemale, boolean isSC,int[] parameters) {
		Scanner sc = new Scanner(System.in);
//		int totalSeats=parameters[0];
//		int directBookedSeats=parameters[1];
//		int remainFrom50=parameters[2];
//		int remainFrom75=parameters[3];
//		int v1BookedSeats=parameters[4];
//		int v2BookedSeats=parameters[5];
//		int v3BookedSeats=parameters[6];
		if (seatsToBook <= 0) {
			System.out.println("Invalid number of seats to book through " + vendor + ".");
			return ;
		}
		double commissionRate = 0.0;
		double originalFare=0.0;
		if ("V1".equalsIgnoreCase(vendor) && parameters[4]<=10) {
			commissionRate = 0.07;
			if (seatsToBook >10) {
				System.out.println("V1 can only book 10% of total seats.");
				System.out.println("Do you want to continue to book only 10 seats..? Press Y/y or N/n");
				char yesOrNo=sc.next().charAt(0);
				if(yesOrNo=='Y' || yesOrNo=='y') {
					seatsToBook=10;
					originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
					parameters[4]=10;
				}
			}
			else {
				originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
				parameters[4]+=seatsToBook;
			}
		} else if ("V2".equalsIgnoreCase(vendor) && parameters[5]<=5) {
			commissionRate = 0.05;
			if (seatsToBook >5) {
				System.out.println("V2 can only book 5% of total seats.");
				System.out.println("Do you want to continue to book only 10 seats..? Press Y/y or N/n");
				char yesOrNo=sc.next().charAt(0);
				if(yesOrNo=='Y' || yesOrNo=='y') {
					seatsToBook=10;
					originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
				}
			}else {
				originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
				parameters[5]+=seatsToBook;
			}
		} else if ("V3".equalsIgnoreCase(vendor) && parameters[6]<=12) {
//		}else {
			commissionRate = 0.09;
			if (seatsToBook >12) {
				System.out.println("V3 can only book 12% of total seats.");
				System.out.println("Do you want to continue to book only 10 seats..? Press Y/y or N/n");
				char yesOrNo=sc.next().charAt(0);
				if(yesOrNo=='Y' || yesOrNo=='y') {
					seatsToBook=10;
					originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
				}
			}else {
				originalFare=DirectBooking.directBookSeats(p,seatsToBook,isFemale,isSC,parameters);
				parameters[6]+=seatsToBook;
			}
		}
		double commission = originalFare * commissionRate;
		double totalFare = originalFare + commission;
		String pattern = "###.##";
		DecimalFormat formatter = new DecimalFormat(pattern);
		String formattedValue = formatter.format(totalFare);
		double formattedTotalFare = Double.parseDouble(formattedValue);
		p.setTotalFare(formattedTotalFare);
		
//		Main.displayBookingDetails(seatsToBook, formattedTotalFare,isFemale,isSC);
		return;
	}
}

package com.hexaware.mainmod;

import java.sql.Date;
import java.util.Scanner;

import com.hexaware.dao.IAdoptable;
import com.hexaware.dao.IAdoptableImpl;

public class MainModule {

    public static void main(String[] args) {
        IAdoptable iAdoptable = new IAdoptableImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an operation:");
            System.out.println("1. Display Pet Listings");
            System.out.println("2. Retrieve Adoption Events");
            System.out.println("3. Register for Adoption Event");
            System.out.println("4. Adopt a Pet");
            System.out.println("5. Record Cash Donation");
            System.out.println("6. View Perticipants in an event");
//            System.out.println("7. Handle Adoption Exception");
            System.out.println("7. Exit");

            int choice = getChoice(scanner);

            switch (choice) {
                case 1:
                	System.out.println("\n\nHere are all the pets...");
                	System.out.println("================================================");
                    iAdoptable.displayPetListings();
                	System.out.println("================================================\n");
                    break;
                case 2:
                	System.out.println("\n\nHere are all the adoption events...");
                	System.out.println("================================================");
                    iAdoptable.retrieveAdoptionEvents();
                	System.out.println("================================================\n");
                    break;
                case 3:
                    System.out.print("Enter your name: ");
                    String participantName = scanner.nextLine();
                    System.out.print("Enter the event ID: ");
                    int eventId = scanner.nextInt();
                    iAdoptable.registerForAdoptionEvent(participantName, eventId);
                    break;
                case 4:
                	System.out.println("Enter the pet id to adopt.");
                	int pid = scanner.nextInt();
                    iAdoptable.adopt(pid);
                    break;
                case 5:
                	System.out.print("Enter donor name: ");
                	String donorName = scanner.nextLine();
                	System.out.print("Enter donation amount: $");
                	double amount = scanner.nextDouble();
//                	Date currentDate = new Date(System.currentTimeMillis());
                	iAdoptable.recordCashDonation(donorName, amount);
                    break;
                case 6:
                	System.out.println("Enter the eventId:");
                	int eid=scanner.nextInt();
                	iAdoptable.displayParticipantsForEvent(eid);
                	break;
                case 7:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        }
    }

    private static int getChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return choice;
    }
}

package com.hexaware.mainModule;

import java.util.Scanner;

import com.hexaware.dao.ElectronicGadgetOnlineShop;

public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
	        ElectronicGadgetOnlineShop onlineShop = new ElectronicGadgetOnlineShop();
	        while (true) {
	            onlineShop.displayMenu();
	            int choice =scanner.nextInt();
	            scanner.nextLine(); // Consume the newline character

	            switch (choice) {
                case 1:
                    onlineShop.viewProductDetails();
                    break;
                case 2:
                    onlineShop.searchProduct();
                    break;
                case 3:
                    onlineShop.viewCart();
                    break;
//                case 4:
//                    if (onlineShop.isUserLoggedIn()) {
//                        onlineShop.placeOrder();
//                    } else {
//                        onlineShop.login();
//                    }
//                    break;
                case 4:
                    if (onlineShop.isUserLoggedIn()) {
                        continue;
                    } else {
                        onlineShop.login();
                    }
                    break;
                case 5:
                    if (onlineShop.isUserLoggedIn()) {
                        onlineShop.viewPaymentHistory();
                    } else {
                        onlineShop.register();
                    }
                    break;
                case 6:
                    onlineShop.updateProfile();
                    break;
                case 7:
                    onlineShop.loggedInCustomer= null; // Logout
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
	        }
	}
}

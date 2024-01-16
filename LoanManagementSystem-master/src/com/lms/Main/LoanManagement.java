package com.lms.Main;

import com.lms.dao.ILoanRepository;
import com.lms.dao.ILoanRepositoryImpl;
import com.lms.entity.Customer;
import com.lms.entity.HomeLoan;
import com.lms.entity.CarLoan;
import com.lms.entity.Loan;
import com.lms.entity.Loan.LoanType;
import com.lms.exception.InvalidLoanException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LoanManagement {

    private static ILoanRepository loanRepository;

    public static void main(String[] args) {
        loanRepository = new ILoanRepositoryImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "applyloan":
                    applyLoan();
                    break;
                case "getallloan":
                    getAllLoan();
                    break;
                case "getloan":
                    getLoan();
                    break;
                case "loanrepayment":
                    loanRepayment();
                    break;
                case "exit":
                    System.out.println("Exiting Loan Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n------------Loan Management System Menu-----------------");
        System.out.println("1. Apply Loan (applyloan)");
        System.out.println("2. Get All Loans (getallloan)");
        System.out.println("3. Get Loan by ID (getloan)");
        System.out.println("4. Loan Repayment (loanrepayment)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void applyLoan() {
        try {
            // Get user input for loan details
            Loan loan = getLoanDetailsFromUser();

            // Apply for the loan
            loanRepository.applyLoan(loan);
        } catch (InvalidLoanException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void getAllLoan() {
        List<Loan> loans = loanRepository.getAllLoan();

        if (loans.isEmpty()) {
            System.out.println("No loans found.");
        } else {
            System.out.println("\nAll Loans:");
            for (Loan loan : loans) {
                System.out.println(loan.getLoanDetails());
            }
        }
    }

    private static void getLoan() {
        try {
            System.out.print("Enter Loan ID: ");
            int loanId = new Scanner(System.in).nextInt();

            loanRepository.getLoanById(loanId);
        } catch (InvalidLoanException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void loanRepayment() {
        try {
            System.out.print("Enter Loan ID: ");
            int loanId = new Scanner(System.in).nextInt();

            System.out.print("Enter Repayment Amount: ");
            double amount = new Scanner(System.in).nextDouble();

            loanRepository.loanRepayment(loanId, amount);
        } catch (InvalidLoanException | InputMismatchException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static Loan getLoanDetailsFromUser() {
        System.out.print("Enter Customer ID: ");
        int customerId = new Scanner(System.in).nextInt();

        System.out.print("Enter Principal Amount: ");
        double principalAmount = new Scanner(System.in).nextDouble();

        System.out.print("Enter Interest Rate: ");
        double interestRate = new Scanner(System.in).nextDouble();

        System.out.print("Enter Loan Term (in months): ");
        int loanTerm = new Scanner(System.in).nextInt();

        System.out.print("Enter Loan Type (HOME_LOAN or CAR_LOAN): ");
        Loan.LoanType loanType = Loan.LoanType.valueOf(new Scanner(System.in).nextLine().trim().toUpperCase());

        if (loanType == Loan.LoanType.HOME_LOAN) {
            System.out.print("Enter Property Address: ");
            String propertyAddress = new Scanner(System.in).nextLine();

            System.out.print("Enter Property Value: ");
            int propertyValue = new Scanner(System.in).nextInt();

            // Create a Customer object (you may need to obtain this from your system or database)
            Customer customer = new Customer();
            customer.setCustomerId(customerId);

            // Pass the Customer object to the HomeLoan constructor
            return new HomeLoan(customerId, customer, principalAmount, interestRate, loanTerm, null, propertyAddress, propertyValue);
        } else if (loanType == Loan.LoanType.CAR_LOAN) {
            System.out.print("Enter Car Model: ");
            String carModel = new Scanner(System.in).nextLine();

            System.out.print("Enter Car Value: ");
            int carValue = new Scanner(System.in).nextInt();

            // Create a Customer object (you may need to obtain this from your system or database)
            Customer customer = new Customer();
            customer.setCustomerId(customerId);

            // Pass the Customer object to the CarLoan constructor
            return new CarLoan(customerId, customer, principalAmount, interestRate, loanTerm, LoanType.CAR_LOAN, "Pending", carModel, carValue);
        } else {
            throw new IllegalArgumentException("Invalid loan type.");
        }
    }
}

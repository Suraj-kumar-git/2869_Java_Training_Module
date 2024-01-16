package com.lms.dao;


import com.lms.entity.*;
import com.lms.entity.Loan.LoanType;
import com.lms.exception.*;
import com.lms.exception.InvalidLoanException;
import com.lms.util.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ILoanRepositoryImpl implements ILoanRepository {

    private static final String APPLY_LOAN_QUERY = "INSERT INTO loan (loan_id, customer_id, principal_amount, interest_rate, loan_term, loan_type, loan_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CALCULATE_INTEREST_QUERY = "SELECT principal_amount, interest_rate, loan_term FROM loan WHERE loan_id = ?";
    private static final String UPDATE_LOAN_STATUS_QUERY = "UPDATE loan SET loan_status = ? WHERE loan_id = ?";
    private static final String CALCULATE_EMI_QUERY = "SELECT principal_amount, interest_rate, loan_term FROM loan WHERE loan_id = ?";
    private static final String REPAYMENT_QUERY = "UPDATE loan SET no_of_emi_paid = no_of_emi_paid + 1 WHERE loan_id = ?";
    private static final String GET_ALL_LOAN_QUERY =
            "SELECT loan.*, home_loan.property_address, home_loan.property_value, car_loan.car_model, car_loan.car_value " +
            "FROM loan " +
            "LEFT JOIN home_loan ON loan.loan_id = home_loan.loan_id " +
            "LEFT JOIN car_loan ON loan.loan_id = car_loan.loan_id " +
            "INNER JOIN customer ON loan.customer_id = customer.customer_id";
    private static final String GET_LOAN_BY_ID_QUERY =
            "SELECT l.loan_id, l.customer_id, l.principal_amount, " +
                    "l.interest_rate, l.loan_term, l.loan_type, l.loan_status, " +
                    "h.property_address, h.property_value, " +
                    "c.car_model, c.car_value, " +
                    "cu.credit_score " +
                    "FROM loan l " +
                    "LEFT JOIN home_loan h ON l.loan_id = h.loan_id " +
                    "LEFT JOIN car_loan c ON l.loan_id = c.loan_id " +
                    "LEFT JOIN customer cu ON l.customer_id = cu.customer_id " +
                    "WHERE l.loan_id = ?";


    private Scanner scanner;

    public ILoanRepositoryImpl() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void applyLoan(Loan loan) throws InvalidLoanException{
        // Display loan details and confirm with the user
        System.out.println("Loan Details:\n" + loan.toString());
        System.out.print("Do you want to proceed with the loan application? (Yes/No): ");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Yes")) {
            try (Connection connection = DBUtil.getDBConn();
                 PreparedStatement preparedStatement = connection.prepareStatement(APPLY_LOAN_QUERY)) {

                // Set values for the prepared statement
                preparedStatement.setInt(1, loan.getLoanId());
                preparedStatement.setInt(2, loan.getCustomer().getCustomerId());
                preparedStatement.setDouble(3, loan.getPrincipalAmount());
                preparedStatement.setDouble(4, loan.getInterestRate());
                preparedStatement.setInt(5, loan.getLoanTerm());
                preparedStatement.setString(6, loan.getLoanType().toString());
                preparedStatement.setString(7, "Pending");

                // Execute the query
                preparedStatement.executeUpdate();
                System.out.println("Loan application successful!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new InvalidLoanException("Error applying for the loan: " + e.getMessage());
            }
        } else {
            System.out.println("Loan application canceled.");
        }
    }

    @Override
    public double calculateInterest(int loanId) throws InvalidLoanException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(CALCULATE_INTEREST_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double principalAmount = resultSet.getDouble("principal_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                int loanTerm = resultSet.getInt("loan_term");

                return (principalAmount * interestRate * loanTerm) / 12;
            } else {
                throw new InvalidLoanException("Loan not found for loan ID: " + loanId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error calculating interest: " + e.getMessage());
        }
    }

    @Override
    public double calculateInterest(Loan loan) throws InvalidLoanException {
        return calculateInterest(loan.getLoanId());
    }

    @Override
    public void loanStatus(int loanId) throws InvalidLoanException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOAN_BY_ID_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int creditScore = resultSet.getInt("credit_score");
                String loanStatus;

                if (creditScore > 650) {
                    loanStatus = "Approved";
                } else {
                    loanStatus = "Rejected";
                }

                updateLoanStatus(loanId, loanStatus);
                System.out.println("Loan Status: " + loanStatus);
            } else {
                throw new InvalidLoanException("Loan not found for loan ID: " + loanId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error checking loan status: " + e.getMessage());
        }
    }

    private void updateLoanStatus(int loanId, String status) throws SQLException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOAN_STATUS_QUERY)) {

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, loanId);

            preparedStatement.executeUpdate();
        }
    }

    @Override
    public double calculateEMI(int loanId) throws InvalidLoanException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(CALCULATE_EMI_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double principalAmount = resultSet.getDouble("principal_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                int loanTerm = resultSet.getInt("loan_term");

                return calculateEMI(principalAmount, interestRate, loanTerm);
            } else {
                throw new InvalidLoanException("Loan not found for loan ID: " + loanId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error calculating EMI: " + e.getMessage());
        }
    }

    @Override
    public double calculateEMI(Loan loan) throws InvalidLoanException {
        return calculateEMI(loan.getLoanId());
    }

    private double calculateEMI(double principalAmount, double interestRate, int loanTerm) {
        double monthlyInterestRate = interestRate / 12 / 100;
        double emi = (principalAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTerm))
                / (Math.pow(1 + monthlyInterestRate, loanTerm) - 1);
        return emi;
    }

    @Override
    public int loanRepayment(int loanId, double amount) throws InvalidLoanException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(CALCULATE_EMI_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double emi = calculateEMI(resultSet.getDouble("principal_amount"),
                        resultSet.getDouble("interest_rate"),
                        resultSet.getInt("loan_term"));

                if (amount < emi) {
                    System.out.println("Payment rejected. Amount is less than a single EMI.");
                    return 0;
                } else {
                    int noOfEmi = (int) Math.floor(amount / emi);
                    updateNoOfEmiPaid(loanId, noOfEmi);
                    System.out.println("Payment successful. Paid " + noOfEmi + " EMI(s).");
                    return noOfEmi;
                }
            } else {
                throw new InvalidLoanException("Loan not found for loan ID: " + loanId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error processing loan repayment: " + e.getMessage());
        }
    }

    private void updateNoOfEmiPaid(int loanId, int noOfEmi) throws SQLException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(REPAYMENT_QUERY)) {

            preparedStatement.setInt(1, loanId);
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public List<Loan> getAllLoan() {
        List<Loan> loans = new ArrayList<>();

        try (Connection connection = DBUtil.getDBConn();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(GET_ALL_LOAN_QUERY);

            while (resultSet.next()) {
                int loanId = resultSet.getInt("loan_id");
                int customerId = resultSet.getInt("customer_id");
                double principalAmount = resultSet.getDouble("principal_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                int loanTerm = resultSet.getInt("loan_term");
                String loanType = resultSet.getString("loan_type");
                String loanStatus = resultSet.getString("loan_status");

                Loan loan;
                if (loanType.equals("HOME_LOAN")) {
                    String propertyAddress = resultSet.getString("property_address");
                    int propertyValue = resultSet.getInt("property_value");

                    // Create a Customer object using customer information
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);  

                    // Create a HomeLoan object using the existing constructor
                    loan = new HomeLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanStatus, propertyAddress, propertyValue);
                } else {
                    String carModel = resultSet.getString("car_model");
                    int carValue = resultSet.getInt("car_value");

                    // Create a Customer object using customer information
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);  

                    // Create a CarLoan object using the existing constructor
                    loan = new CarLoan(loanId, customer, principalAmount, interestRate, loanTerm,LoanType.CAR_LOAN, loanStatus, carModel, carValue);
                }

                loans.add(loan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return loans;
    }

    private String getHomeLoanDetails(int loanId) throws SQLException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOAN_BY_ID_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("property_address");
            } else {
                throw new SQLException("Home loan details not found for loan ID: " + loanId);
            }
        }
    }

    private String getCarLoanDetails(int loanId) throws SQLException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOAN_BY_ID_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("car_model");
            } else {
                throw new SQLException("Car loan details not found for loan ID: " + loanId);
            }
        }
    }
    
    @Override
    public void getLoanById(int loanId) throws InvalidLoanException {
        try (Connection connection = DBUtil.getDBConn();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_LOAN_BY_ID_QUERY)) {

            preparedStatement.setInt(1, loanId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                double principalAmount = resultSet.getDouble("principal_amount");
                double interestRate = resultSet.getDouble("interest_rate");
                int loanTerm = resultSet.getInt("loan_term");
                String loanType = resultSet.getString("loan_type");
                String loanStatus = resultSet.getString("loan_status");

                Loan loan;

                if (loanType.equals("HOME_LOAN")) {
                    String propertyAddress = getHomeLoanDetails(loanId);
                    int propertyValue = resultSet.getInt("property_value");

                    // Create a Customer object using customer information
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);

                    // Create a HomeLoan object using the existing constructor
                    loan = new HomeLoan(loanId, customer, principalAmount, interestRate, loanTerm, loanStatus, propertyAddress, propertyValue);
                } else {
                    String carModel = getCarLoanDetails(loanId);
                    int carValue = resultSet.getInt("car_value");

                    // Create a Customer object using customer information
                    Customer customer = new Customer();
                    customer.setCustomerId(customerId);

                    // Create a CarLoan object using the existing constructor
                    loan = new CarLoan(loanId, customer, principalAmount, interestRate, loanTerm, LoanType.CAR_LOAN, loanStatus, carModel, carValue);
                }

                System.out.println("Loan Details:\n" + loan.toString());
            } else {
                throw new InvalidLoanException("Loan not found for loan ID: " + loanId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidLoanException("Error retrieving loan details: " + e.getMessage());
        }
    }
    
//    public static void main(String[] args) {
//        ILoanRepositoryImpl loanRepository = new ILoanRepositoryImpl();
//
//        // Dummy data for testing
//        Customer customer1 = new Customer(5, "John Doe", "john@example.com", "123-456-7890", "123 Main St", 700);
//        HomeLoan homeLoan = new HomeLoan(6, customer1, 100000.0, 5.0, 10, "Pending", "123 Oak St", 200000);
////        CarLoan carLoan = new CarLoan(2, customer1, 50000.0, 3.5, 5, "Pending", "Toyota Camry", 25000);
//
//        try {
//            // Apply Home Loan
//            loanRepository.applyLoan(homeLoan);
//
//            // Apply Car Loan
////            loanRepository.applyLoan(carLoan);
//
//            // Get all loans
//            List<Loan> allLoans = loanRepository.getAllLoan();
//            for (Loan loan : allLoans) {
//                System.out.println(loan.getLoanDetails());
//            }
//
//            // Get loan by ID
//            loanRepository.getLoanById(1);
//
//            // Calculate interest for a loan
//            double interest = loanRepository.calculateInterest(1);
//            System.out.println("Interest for Loan ID 1: $" + interest);
//
//            // Get loan status for a loan
//            loanRepository.loanStatus(1);
//
//            // Calculate EMI for a loan
//            double emi = loanRepository.calculateEMI(1);
//            System.out.println("EMI for Loan ID 1: $" + emi);
//
//            // Perform loan repayment
//            loanRepository.loanRepayment(1, 1500.0);
//        } catch (InvalidLoanException e) {
//            e.printStackTrace();
//        }
//    }


}




               

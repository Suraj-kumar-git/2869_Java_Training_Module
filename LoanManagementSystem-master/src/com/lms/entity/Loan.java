package com.lms.entity;


public class Loan {
    private int loanId;
    private Customer customer;
    private double principalAmount;
    private double interestRate;
    private int loanTerm;
    private LoanType loanType;
    private String loanStatus;
    public enum LoanType {
        CAR_LOAN,
        HOME_LOAN
    }
    private int noOfEmiPaid;

    
    public Loan() {
    	// Default constructor
    }

    // Overloaded constructor
    public Loan(int loanId,Customer customer, double principalAmount, double interestRate, int loanTerm, LoanType loanType, String loanStatus) {
        this.loanId = loanId;
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.interestRate = interestRate;
        this.loanTerm = loanTerm;
        this.loanType = loanType;
        this.loanStatus = loanStatus;
        this.noOfEmiPaid = 0;
    }
    public int getNoOfEmiPaid() {
        return noOfEmiPaid;
    }

    public void setNoOfEmiPaid(int noOfEmiPaid) {
        this.noOfEmiPaid = noOfEmiPaid;
    }

 // Getter and Setter methods for loanId
    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    // Getter and Setter methods for customer
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    // Getter and Setter methods for principalAmount
    public double getPrincipalAmount() {
        return principalAmount;
    }

    public void setPrincipalAmount(double principalAmount) {
        this.principalAmount = principalAmount;
    }

    // Getter and Setter methods for interestRate
    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    // Getter and Setter methods for loanTerm
    public int getLoanTerm() {
        return loanTerm;
    }

    public void setLoanTerm(int loanTerm) {
        this.loanTerm = loanTerm;
    }

    // Getter and Setter methods for loanType
    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    // Getter and Setter methods for loanStatus
    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }
    
    // Print method
    public String getLoanDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Loan ID: ").append(loanId).append("\n");
        details.append("Customer Details:\n").append(customer.getCustomerDetails()).append("\n");
        details.append("Principal Amount: ").append(principalAmount).append("\n");
        details.append("Interest Rate: ").append(interestRate).append("\n");
        details.append("Loan Term: ").append(loanTerm).append(" months").append("\n");
        details.append("Loan Type: ").append(loanType).append("\n");
        details.append("Loan Status: ").append(loanStatus).append("\n");

        return details.toString();
    }

}

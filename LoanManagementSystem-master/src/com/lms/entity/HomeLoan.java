package com.lms.entity;


public class HomeLoan extends Loan {
    private String propertyAddress;
    private int propertyValue;

    public HomeLoan() {
        // Default constructor
    }

    public HomeLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm, String loanStatus,
                    String propertyAddress, int propertyValue) {
        super(loanId, customer, principalAmount, interestRate, loanTerm, LoanType.HOME_LOAN, loanStatus);
        this.propertyAddress = propertyAddress;
        this.propertyValue = propertyValue;
    }

    // Getter and Setter methods for propertyAddress
    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    // Getter and Setter methods for propertyValue
    public int getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(int propertyValue) {
        this.propertyValue = propertyValue;
    }
    @Override
    public String toString() {
        return "HomeLoan{" +
                "loanId=" + getLoanId() +
                ", customer=" + getCustomer().getCustomerId() + // Assuming you have a method like getCustomerId() in Customer class
                ", principalAmount=" + getPrincipalAmount() +
                ", interestRate=" + getInterestRate() +
                ", loanTerm=" + getLoanTerm() +
                ", propertyAddress='" + propertyAddress + '\'' +
                ", propertyValue=" + propertyValue +
                ", loanStatus='" + getLoanStatus() + '\'' +
                '}';
    }

    @Override
    public String getLoanDetails() {
        StringBuilder details = new StringBuilder(super.getLoanDetails());
        details.append("Property Address: ").append(propertyAddress).append("\n");
        details.append("Property Value: ").append(propertyValue).append("\n");

        return details.toString();
    }
}


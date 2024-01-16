package com.lms.entity;



public class CarLoan extends Loan {
    private String carModel;
    private int carValue;

    // Default constructor
    public CarLoan() {
    }

    // Overloaded constructor with parameters
    public CarLoan(int loanId, Customer customer, double principalAmount, double interestRate, int loanTerm,
                   LoanType loanType, String loanStatus, String carModel, int carValue) {
        super(loanId,customer, principalAmount, interestRate, loanTerm, loanType, loanStatus);
        this.carModel = carModel;
        this.carValue = carValue;
    }

    // Getter and Setter methods for carModel
    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    // Getter and Setter methods for carValue
    public int getCarValue() {
        return carValue;
    }

    public void setCarValue(int carValue) {
        this.carValue = carValue;
    }

    @Override
    public String toString() {
        return "CarLoan{" +
                "loanId=" + getLoanId() +
                ", customer=" + getCustomer().getCustomerId() + // Assuming you have a method like getCustomerId() in Customer class
                ", principalAmount=" + getPrincipalAmount() +
                ", interestRate=" + getInterestRate() +
                ", loanTerm=" + getLoanTerm() +
                ", carModel='" + carModel + '\'' +
                ", carValue=" + carValue +
                ", loanStatus='" + getLoanStatus() + '\'' +
                '}';
    }


    @Override
    public String getLoanDetails() {
        StringBuilder details = new StringBuilder(super.getLoanDetails());
        details.append("Car Model: ").append(carModel).append("\n");
        details.append("Car Value: ").append(carValue).append("\n");

        return details.toString();
    }
}


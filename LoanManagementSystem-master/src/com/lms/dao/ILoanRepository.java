package com.lms.dao;

import java.util.List;

import com.lms.entity.*;
import com.lms.exception.InvalidLoanException;

public interface ILoanRepository {

    // Method to apply for a loan
    void applyLoan(Loan loan) throws InvalidLoanException;

    // Method to calculate interest for a loan
    double calculateInterest(int loanId) throws InvalidLoanException;

    // Overloaded method to calculate interest while creating a loan
    double calculateInterest(Loan loan) throws InvalidLoanException;

    // Method to check and update loan status based on credit score
    void loanStatus(int loanId) throws InvalidLoanException;

    // Method to calculate EMI for a loan
    double calculateEMI(int loanId) throws InvalidLoanException;

    // Overloaded method to calculate EMI while creating a loan
    double calculateEMI(Loan loan) throws InvalidLoanException;

    // Method to calculate the number of EMIs that can be paid from a given amount
    int loanRepayment(int loanId, double amount) throws InvalidLoanException;

    // Method to get all loans
    List<Loan> getAllLoan();
    // Method to get a loan by ID
    void getLoanById(int loanId) throws InvalidLoanException;
}


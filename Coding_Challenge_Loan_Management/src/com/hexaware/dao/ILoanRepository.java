package com.hexaware.dao;

import java.util.List;

import com.hexaware.entity.Loan;
import com.hexaware.exception.InvalidLoanException;

public interface ILoanRepository {
    boolean applyLoan(Loan loan) throws InvalidLoanException;

    double calculateInterest(int loanId) throws InvalidLoanException;

//    double calculateInterest(int loanId, double principalAmount, double interestRate, int loanTenure) throws InvalidLoanException;

    void loanStatus(int loanId) throws InvalidLoanException;

    double calculateEMI(int loanId) throws InvalidLoanException;

    double calculateEMI(int loanId, double principalAmount, double interestRate, int loanTenure) throws InvalidLoanException;

    int loanRepayment(int loanId, double amount) throws InvalidLoanException;

    List<Loan> getAllLoans();

    Loan getLoanById(int loanId) throws InvalidLoanException;
}


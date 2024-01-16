package com.hexaware.entity;

import java.time.LocalDateTime;

public class PaymentHistory {

    private int paymentID;
    private int userID;
    private int orderID;
    private LocalDateTime paymentDate;
    private double amount;

    public PaymentHistory() {
        // Default constructor
    }

    public PaymentHistory(int paymentID, int userID, int orderID, LocalDateTime paymentDate, double amount) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.orderID = orderID;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    // Getters and Setters

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentHistory{" +
                "paymentID=" + paymentID +
                ", userID=" + userID +
                ", orderID=" + orderID +
                ", paymentDate=" + paymentDate +
                ", amount=" + amount +
                '}';
    }
}

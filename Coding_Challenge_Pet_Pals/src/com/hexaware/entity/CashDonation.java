package com.hexaware.entity;

import java.time.LocalDate;

public class CashDonation extends Donation {
    private LocalDate donationDate;

    // Constructor
    public CashDonation() {
    	
    }
    public CashDonation(String donorName, double amount, LocalDate donationDate) {
        super(donorName, amount);
        this.donationDate = donationDate;
    }

    @Override
	public String toString() {
		return "CashDonation [donationDate=" + donationDate + "]";
	}
	public LocalDate getDonationDate() {
		return donationDate;
	}
	public void setDonationDate(LocalDate donationDate) {
		this.donationDate = donationDate;
	}
	// Implementation of RecordDonation
    @Override
    public void recordDonation() {
        System.out.println("Cash Donation recorded on " + donationDate + ": " + getAmount());
    }
}

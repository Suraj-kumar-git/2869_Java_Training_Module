package com.hexaware.entity;

public abstract class Donation {
    private String donorName;
    private double amount;

    // Constructor
    public Donation() {
    	
    }
    public Donation(String donorName, double amount) {
        this.donorName = donorName;
        this.amount = amount;
    }

    // Abstract method
    public abstract void recordDonation();
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Donation [donorName=" + donorName + ", amount=" + amount + "]";
	}
    
}

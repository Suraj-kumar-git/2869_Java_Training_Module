package com.lms.entity;


public class Customer {
	private int customerId;
	private String name;
	private String email;
	private String phone;
	private String address;
	private int creditScore;
	
	public Customer() {
		//default cons.
	}
	
	public Customer( int customerId,String name, String email, String phone, String address, int creditScore) {
		this.customerId = customerId;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.creditScore = creditScore;
	}
	
	// Getter and Setter methods for customerId
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Getter and Setter methods for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter methods for emailAddress
    public String getEmailAddress() {
        return email;
    }

    public void setEmailAddress(String email) {
        this.email = email;
    }

    // Getter and Setter methods for phoneNumber
    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    // Getter and Setter methods for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getter and Setter methods for creditScore
    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public String getCustomerDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Customer ID: ").append(customerId).append("\n");
        details.append("Name: ").append(name).append("\n");
        details.append("Email Address: ").append(email).append("\n");
        details.append("Phone Number: ").append(phone).append("\n");
        details.append("Address: ").append(address).append("\n");
        details.append("Credit Score: ").append(creditScore).append("\n");

        return details.toString();
    }

}


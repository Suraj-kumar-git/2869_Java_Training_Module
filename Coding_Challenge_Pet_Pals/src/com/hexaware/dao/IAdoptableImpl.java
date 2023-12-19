package com.hexaware.dao;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import com.hexaware.exception.AdoptionException;
import com.hexaware.exception.FileHandlingException;
import com.hexaware.exception.InsufficientFundsException;
import com.hexaware.exception.InvalidPetAgeException;
import com.hexaware.exception.NullReferenceException;
import com.hexaware.util.DBConnUtil;

public class IAdoptableImpl implements IAdoptable {

	private Connection connection;
	public static boolean isLoggedIn = false;

	public IAdoptableImpl() {
		this.connection = DBConnUtil.getConnection();
	}

	private static final Scanner scanner = new Scanner(System.in);

	@Override
	public void displayPetListings() {
		try{
			String query = "SELECT * FROM Pet";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					int pid = resultSet.getInt("id");
					String name = resultSet.getString("name");
					int age = resultSet.getInt("age");
					String breed = resultSet.getString("breed");
					String adopted = resultSet.getString("adopted");
					System.out.println("Pet Id: "+pid+" "+name + " - Age: " + age + ", Breed: " + breed+" Adopted: "+adopted);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void retrieveAdoptionEvents() {
		try{
			String query = "SELECT * FROM adoption_events";
			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query)) {
				while (resultSet.next()) {
					int eventId = resultSet.getInt("event_id");
					String eventName = resultSet.getString("event_name");
					Date eventDate = resultSet.getDate("event_date");
					System.out.println("Event ID: " + eventId + ", Name: " + eventName + ", Date: " + eventDate);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registerForAdoptionEvent(String participantName, int eventId) {
		try{
			String query = "INSERT INTO participants (participant_name, event_id) VALUES (?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setString(1, participantName);
				preparedStatement.setInt(2, eventId);
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) {
					System.out.println(participantName + " registered for adoption event with ID: " + eventId);
				} else {
					System.out.println("Registration failed.");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void adopt(int id) {
	    try{
	        // Simulate the adoption process by updating the pet's adoption status in the database
	        String updateQuery = "UPDATE Pet SET adopted = true WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
	            preparedStatement.setInt(1, id);
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Pet with ID " + id + " adopted successfully.");
	            } else {
	                System.out.println("Adoption failed. Pet with ID " + id + " not found.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public void displayParticipantsForEvent(int eventId) {
	    try{
	        String query = "SELECT * FROM participants WHERE event_id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1, eventId);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                System.out.println("Participants for Event ID " + eventId + ":");
	                while (resultSet.next()) {
	                    int participantId = resultSet.getInt("participant_id");
	                    String participantName = resultSet.getString("participant_name");
	                    System.out.println("Participant ID: " + participantId + ", Name: " + participantName);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}



	@Override
	public void recordCashDonation(String donorName, double amount) {
	    try{
	        // Simulate recording a cash donation by inserting a new record in the donations table
	        String query = "INSERT INTO donations (donor_name, amount) VALUES (?, ?)";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setString(1, donorName);
	            preparedStatement.setDouble(2, amount);
//	            preparedStatement.setDate(3, (java.sql.Date) donationDate);
	            int rowsAffected = preparedStatement.executeUpdate();
	            if (rowsAffected > 0) {
	                System.out.println("Recording cash donation from " + donorName + " amount: $" + amount);
	            } else {
	                System.out.println("Recording donation failed.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


	@Override
	public void handleInvalidPetAge(String petName, int petAge, String petBreed) throws InvalidPetAgeException {
		if (petAge <= 0) {
			throw new InvalidPetAgeException("Invalid pet age. Age should be a positive integer.");
		}
		System.out.println("Pet " + petName + " added to the shelter. Age: " + petAge + ", Breed: " + petBreed);
	}

	@Override
	public void handleNullReferenceExceptions() throws NullReferenceException {
		// Simulating null reference exception
		throw new NullReferenceException("Null reference exception encountered when displaying pet listings.");
	}

	@Override
	public void handleInsufficientFunds(double donationAmount) throws InsufficientFundsException {
		if (donationAmount < 10.0) {
			throw new InsufficientFundsException("Insufficient funds. Minimum donation amount is $10.");
		}
		System.out.println("Donation processed successfully. Amount: $" + donationAmount);
	}

	 @Override
	    public void handleFileHandlingExceptions(String fileName) throws FileHandlingException {
	        try {
	            // Simulating file handling exception
	            throw new FileNotFoundException("File not found: " + fileName);
	        } catch (FileNotFoundException e) {
	            throw new FileHandlingException(e.getMessage());
	        }
	    }

	@Override
	public void handleAdoptionExceptions() throws AdoptionException {
		// Simulating adoption-related exception
		throw new AdoptionException("Adoption error: Pet is not available or has missing information.");
	}
}

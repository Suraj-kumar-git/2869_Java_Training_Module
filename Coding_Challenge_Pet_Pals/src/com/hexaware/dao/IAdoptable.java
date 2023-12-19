package com.hexaware.dao;

import java.sql.Date;

import com.hexaware.exception.AdoptionException;
import com.hexaware.exception.FileHandlingException;
import com.hexaware.exception.InsufficientFundsException;
import com.hexaware.exception.InvalidPetAgeException;
import com.hexaware.exception.NullReferenceException;

public interface IAdoptable {
    // Adoption process
    void adopt(int id);

    // Display pet listings from the "pets" table
    void displayPetListings();
    
    void displayParticipantsForEvent(int eventId);

    // Record cash donations into the "donations" table
    void recordCashDonation(String donorName, double amount);

    // Retrieve information about upcoming adoption events from the "adoption_events" table
    void retrieveAdoptionEvents();

    // Register for an adoption event by adding details to the "participants" table
    void registerForAdoptionEvent(String participantName, int eventId);

    // Handle invalid pet age when adding a new pet to a shelter
    void handleInvalidPetAge(String petName, int petAge, String petBreed) throws InvalidPetAgeException;

    // Handle null reference exceptions when displaying the list of available pets
    void handleNullReferenceExceptions() throws NullReferenceException;

    // Handle insufficient funds exceptions when making cash donations
    void handleInsufficientFunds(double donationAmount) throws InsufficientFundsException;

    // Handle file-related exceptions when reading data from a file
    void handleFileHandlingExceptions(String fileName) throws FileHandlingException;

    // Handle custom adoption-related errors using the AdoptionException
    void handleAdoptionExceptions() throws AdoptionException;
}

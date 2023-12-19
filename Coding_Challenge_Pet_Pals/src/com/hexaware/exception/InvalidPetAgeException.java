package com.hexaware.exception;

public class InvalidPetAgeException extends Exception{
	public InvalidPetAgeException(String text) {
        super(text);
    }
}
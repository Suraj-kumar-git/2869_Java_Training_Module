package com.hexaware.exception;

public class NullReferenceException extends Exception {
	 public NullReferenceException(String Name) {
	        super("Name should not be null");
	    }
}
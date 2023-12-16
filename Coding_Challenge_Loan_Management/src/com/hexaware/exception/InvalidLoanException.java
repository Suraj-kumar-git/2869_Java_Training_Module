package com.hexaware.exception;

import java.sql.SQLException;

public class InvalidLoanException extends Exception {
    public InvalidLoanException(String message, SQLException e) {
        super(message);
    }

	public InvalidLoanException(String string) {
		super(string);
	}
}


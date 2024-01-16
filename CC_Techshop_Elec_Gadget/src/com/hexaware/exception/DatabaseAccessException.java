package com.hexaware.exception;

import java.sql.SQLException;

public class DatabaseAccessException extends SQLException {
    public DatabaseAccessException(String message) {
        super(message);
    }
}


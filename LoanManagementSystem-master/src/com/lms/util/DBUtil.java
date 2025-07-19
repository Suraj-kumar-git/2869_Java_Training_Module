package com.lms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String url = "jdbc:mysql://localhost:3306/loanmanagementsystem";
    private static final String username = "root";
    private static final String password = "password";

    public static Connection getDBConn() throws SQLException {
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            return DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error loading JDBC driver: " + e.getMessage());
        }
    }
}

package com.hexaware.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnUtil {
	private static Connection connection;
    public static Connection getConnection() {
    	if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String connectionString = DBPropertyUtil.getConnectionString();
                
                connection = DriverManager.getConnection(connectionString);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
//      public static Connection getConnection() throws ClassNotFoundException {
//        try {
//        	Class.forName("com.mysql.cj.jdbc.Driver");
//			String url="jdbc:mysql://localhost:3306/java_cc_2869";
//			String username="root";
//			String password="73524Idea#";
//            connection = DriverManager.getConnection(url,username,password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
    }
}

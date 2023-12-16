package databaseConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/hexaware";
			String username="root";
			String password="73524Idea#";
			Connection con = DriverManager.getConnection(url,username,password);
			if(con.isClosed()) {
				System.out.println("Connnection is closed.");
			}else {
				System.out.println("Connection established...");
				con.close();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

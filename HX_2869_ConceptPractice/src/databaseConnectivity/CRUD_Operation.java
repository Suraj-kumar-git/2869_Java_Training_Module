package databaseConnectivity;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CRUD_Operation {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String dbname="new_db";
		String url="jdbc:mysql://localhost:3306/hexaware";
		String username="root";
		String password="73524Idea#";
		Connection con = DriverManager.getConnection(url,username,password);
		Statement stmt = con.createStatement();
		
		
		// ----  View All Databases ----
        String showDatabasesQuery = "SHOW DATABASES";
        ResultSet set1 = stmt.executeQuery(showDatabasesQuery);
        System.out.println("Databases:");
        while (set1.next()) {
            String databaseName = set1.getString(1);
            System.out.println(databaseName);
        }
		
		// ----  Create New Database ----
		String createDatabaseQuery = "CREATE DATABASE " + dbname;
		stmt.executeUpdate(createDatabaseQuery);
		
		// ----  View All Tables----
		DatabaseMetaData metaData = con.getMetaData();
        ResultSet set2 = metaData.getTables(null, null, "%", new String[]{"TABLE"});
        System.out.println("\nTables in the database:");
        while (set2.next()) {
            String tableName = set2.getString("TABLE_NAME");
            System.out.println(tableName);
        }
		
		// ----  View All record----
		String select="select * from course";
		ResultSet set3 = stmt.executeQuery(select);
		while(set3.next()) {
			System.out.print(set3.getInt(1));
			System.out.print(set3.getString(2));
			System.out.println(set3.getInt(3));
		}
		
		// ----  Insert into table ----
		String insert = "insert into course values(?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(insert);
		pstmt.setInt(1,000);
		pstmt.setString(2,"Mathy");
		pstmt.setInt(3,30);
		int countInsert=pstmt.executeUpdate();
		System.out.println(countInsert+" record inserted.");
		
		// ----  Update in the existing table----
		String update ="update course set cname=? where cid=?";
		pstmt = con.prepareStatement(update);
		pstmt.setString(1, "Math");
		pstmt.setInt(2,0);
		int updateCount=pstmt.executeUpdate();
		System.out.println(updateCount+" record updated.");

		// ----  Delete from the existing table----
		String delete="Delete from course where cid=?";
		pstmt = con.prepareStatement(delete);
		pstmt.setInt(1, 0);
		int deleteCount = pstmt.executeUpdate();
		System.out.println(deleteCount+" row/s deleted.");
		
		con.close();
		stmt.close();
	}

}

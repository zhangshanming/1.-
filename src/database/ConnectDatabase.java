package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

	public static Connection connectDB() {
		String url = "jdbc:mysql://localhost:3306/mis?serverTimezone=GMT%2B8";
		String username = "root";
		String password = "root";
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver not found.");
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection successful!");
		} catch (SQLException e) {
			System.out.println("Connection failed!");
			e.printStackTrace();
		}

		if (con == null) {
			System.out.println("Failed to make connection!");
		}

		return con;
	}
}

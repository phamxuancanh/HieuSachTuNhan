package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static DBConnection instance;
	private Connection con;

	private DBConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYHIEUSACH;trustServerCertificate=true";

		String user = "sa";
		String pass = "sapassword";
		try {
			con = DriverManager.getConnection(url, user, pass);
//			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static DBConnection getInstance() {
		if (instance == null)
			instance = new DBConnection();
		return instance;
	}

	public Connection getConnection() {
		return con;
	}

}
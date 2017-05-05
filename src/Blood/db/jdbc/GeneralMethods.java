package Blood.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GeneralMethods {
	protected static Connection c;	
	
	static protected void SQLConnect() {
		try {
		// Open database connection
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:./db/blood.db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		} catch (Exception e) {
		e.printStackTrace();
		}

	}
	
	protected static void SQLDisconnect() {
		// Close database connection
		try {
		c.close();
		System.out.println("Database connection closed.");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}

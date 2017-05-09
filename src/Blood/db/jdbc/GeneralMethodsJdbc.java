package Blood.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public interface GeneralMethodsJdbc{	

	public static void SQLConnect() {
		try {
		// Open database connection
		Class.forName("org.sqlite.JDBC");
		Connect.c = DriverManager.getConnection("jdbc:sqlite:./db/blood.db");
		Connect.c.createStatement().execute("PRAGMA foreign_keys=ON");
		System.out.println("Database connection opened.");
		} catch (Exception e) {
		e.printStackTrace();
		}

	}
	
	public static void SQLDisconnect() {
		// Close database connection
		try {
		Connect.c.close();
		System.out.println("Database connection closed.");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}

package Blood.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsertMultiple {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			// Manual commit
			c.setAutoCommit(false);
			System.out.println("Database connection opened.");

			// Insert new records: begin
			// Several insertions with just one transaction
			Statement stmt = c.createStatement();
			String sql;
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}

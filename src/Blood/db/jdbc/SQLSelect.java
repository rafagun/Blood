package Blood.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import Blood.db.pojos.Hospital;


public class SQLSelect {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Blood.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			Statement stmt = c.createStatement();
			
			String sql = "SELECT * FROM Hospital";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				int range= rs.getInt("range");
				Hospital hospital = new Hospital(id,name,location,range);
				// no se que clase seria en nuestra database
				
			}
			rs.close();
			stmt.close();
			System.out.println("Search finished.");
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
			
}
		catch(Exception ex){
			ex.printStackTrace();
		}
}
}
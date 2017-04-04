package Blood.db.jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Blood.db.pojos.Hospital;

public class SQLSearch {

	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/blood.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name of the hospital to be shown: ");
			String searchName = reader.readLine();

			// Retrieve data: begin
			String sql = "SELECT * FROM hospital WHERE name LIKE ?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, searchName);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String location = rs.getString("location");
				int range = rs.getInt("range");
				// Note that department is going to show null, even if the
				// employee is assigned to one, that's because we didn't
				// retrieve the department from the database. We should!!
				Employee employee = new Employee(id, name, location, range);
				System.out.println(hospital);
				// Process the photo
				
		}
			rs.close();
			prep.close();
			System.out.println("Search finished.");
			// Retrieve data: end
		    
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}



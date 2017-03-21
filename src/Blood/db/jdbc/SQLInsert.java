package Blood.db.jdbc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInsert {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/Blood.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");

			// Get the patient information from the command prompt
			System.out.println("Please, input the patient information:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Address: ");
			String address = reader.readLine();
			System.out.println("Blood: ");
			String blood = reader.readLine();
			System.out.println("Smoker: ");
			String smoker = reader.readLine();
			System.out.println("Gender: ");
			String gender = reader.readLine();
			

			// Insert new record: begin
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO hospital (name, address, blood, smoker, gender) "
				//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
				//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
					+ "VALUES ('" + name + "', '" + address	+ "','" + blood +"','" + smoker + "','"+ gender + "');";
			
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Patient information processed");
			System.out.println("Patient has been inserted.");
			// Insert new record: end

			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

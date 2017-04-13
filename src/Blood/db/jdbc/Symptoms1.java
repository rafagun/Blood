package Blood.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Symptoms;

public class Symptoms1 {
	Connection c;

	public void SQLConnect() {
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
	
	public void SQLDisconnect() {
		// Close database connection
		try {
		c.close();
		System.out.println("Database connection closed.");
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	public ArrayList<Symptoms> SQLSelect(){
		ArrayList<Symptoms> symptoms = new ArrayList<Symptoms>();
		try {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Symptoms";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		String type = rs.getString("type");
		Symptoms symptom = new Symptoms (id,type);
		symptoms.add(symptom);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		} catch(Exception ex){
		ex.printStackTrace();
		}
		return symptoms;
	}
	
	public void SQLDrop(){
		try {

		// Drop tables: begin
		Statement stmt2 = c.createStatement();
		String sql2 = "DROP TABLE Symptoms";
		stmt2.executeUpdate(sql2);
		stmt2.close();
		System.out.println("Table Symptoms has been dropped");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void SQLDelete(String nameSymptom) throws IOException, SQLException {
		String sql = "DELETE FROM Symptoms WHERE name=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, nameSymptom);
		prep.executeUpdate();
		prep.close();
		System.out.println("Nurse with the name:" + nameSymptom+ "has been deleted");
	}
	
	
	
}

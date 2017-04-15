package Blood.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Symptoms;

public class DB_Symptoms {
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
	public void SQLCreate() throws SQLException {
		try {

		// Create tables: begin
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE Symptoms "
		+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
		+ "type TEXT NOT NULL,)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
		}
		catch (Exception e) {
		e.printStackTrace();
		}	
		}
	public void SQLInsert(Symptoms symptom){
		try {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Symptoms (type) "
		//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
		//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
		+ "VALUES ('" + symptom.getType() +"');";

		stmt.executeUpdate(sql);
		stmt.close();



		} catch (Exception e) {
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
	
	public void SQLUpdate(Symptoms symptom) throws IOException , SQLException {
		String sql = "UPDATE Symptoms SET type=?, WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, symptom.getType());
	prep.setInt(2, symptom.getId());
	prep.executeUpdate();
	}
	
	public ArrayList<Symptoms> SQLSearch(String sympType) {
		ArrayList<Symptoms> symptoms = new ArrayList<Symptoms>();
	try {
	
	String sql = "SELECT * FROM Symptoms WHERE type LIKE ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, sympType);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String type = rs.getString("type");
	Symptoms symptom = new Symptoms (id, type);
	symptoms.add(symptom);
	rs.close();
	prep.close();

		
	}

		

	} catch (Exception e) {
	e.printStackTrace();
	}
	return symptoms;
	}
	
}

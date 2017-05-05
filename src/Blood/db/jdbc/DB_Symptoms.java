package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Symptoms;

public class DB_Symptoms extends generalMethods {



	public void SQLCreate() throws SQLException {
		try {

		// Create tables: begin
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE Symptoms "
		+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
		+ "type TEXT NOT NULL,"
		+ "severity TEXT NOT NULL)";
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
		String sql = "INSERT INTO Symptoms (type, severity) "

		+ "VALUES ('" + symptom.getType() +"','"+ symptom.getSeverity() + "');";

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
		String severity =rs.getString("severity");
		Symptoms symptom = new Symptoms (id,type,severity);
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
	public Symptoms SQLSearch(String symptomsName) {
		Symptoms symptoms =new Symptoms();
	try {
		String sql = "SELECT * FROM symptoms WHERE type LIKE ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, symptomsName);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String type = rs.getString("type");
	String severity = rs.getString("severity");
	symptoms = new Symptoms (id, type, severity);
	rs.close();
	prep.close();

		}

	} catch (Exception e) {
	e.printStackTrace();
	}
	return symptoms;
	}


}

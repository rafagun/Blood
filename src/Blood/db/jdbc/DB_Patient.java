package Blood.db.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Patient;

public class DB_Patient extends generalMethods {
	
	
	public void SQLCreate() throws SQLException {
		try {	
	Statement stmt3 = c.createStatement();
	String sql3 = "CREATE TABLE Patient "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
			+ "name TEXT NOT NULL, "
			+ "blood  TEXT NOT NULL, "
			+ "age INTEGER, "
			+ "gender TEXT NOT NULL, "
			+ "smoker  BOOLEAN)";
	stmt3.executeUpdate(sql3);
	stmt3.close();
	System.out.println("Tables created.");
	}
		
	catch(Exception ex){
		ex.printStackTrace();
	}
}
	
	public void SQLDrop(){
		try {
	Statement stmt3 = c.createStatement();
	String sql3 = "DROP TABLE Patient";
	stmt3.executeUpdate(sql3);
	stmt3.close();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
}
	
	public ArrayList<Patient> SQLSelect(){
		ArrayList<Patient> patients = new ArrayList<Patient>();
		try {
		Statement stmt = c.createStatement();

		String sql = "SELECT * FROM Patient";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int age = rs.getInt("age");
			String blood = rs.getString("blood");
			String gender = rs.getString("gender");
			Boolean smoker = rs.getBoolean("smoker");
		Patient patient = new Patient (id,name,age,blood,gender,smoker);
		patients.add(patient);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		}
		catch(Exception ex){
		ex.printStackTrace();
		}
		return patients;
		}
	
	public void SQLInsert(Patient patient){
		try {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Patient (name, age, blood, gender, smoker) "

		+ "VALUES ('" + patient.getName()  + "','"+ patient.getAge() + "','"+ patient.getBlood() + "','"+ patient.getGender() + "','"+ patient.getSmoker()+ "');";

		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Patient has been inserted");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public Patient SQLSearch(String patientName) {
		Patient patient =new Patient();
	try {
		String sql = "SELECT * FROM Patient WHERE name LIKE ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, patientName);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String name = rs.getString("name");
	int age = rs.getInt("age");
	String blood = rs.getString("blood");
	String gender = rs.getString("gender");
	Boolean smoker = rs.getBoolean("smoker");
	patient = new Patient (id, name, age, blood, gender, smoker);
	rs.close();
	prep.close();

		}

	} catch (Exception e) {
	e.printStackTrace();
	}
	return patient;
	}
	public void SQLDelete(String namePatient) throws IOException, SQLException {
		String sql = "DELETE FROM Patient WHERE name=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, namePatient);
		prep.executeUpdate();
		prep.close();

		
	    
	}
	
	public void SQLUpdate(Patient patientUpdate, String patientNameUpdate) throws IOException , SQLException {
		String sql = "UPDATE Patient SET name=? ,age=? ,blood=?, gender=?, smoker=?  WHERE name=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, patientUpdate.getName());
	prep.setInt(2, patientUpdate.getAge());
	prep.setString(3, patientUpdate.getBlood());
	prep.setString(4, patientUpdate.getGender());
	prep.setBoolean(5, patientUpdate.getSmoker());
	prep.setString(6, patientNameUpdate);
	System.out.println("Update is finished");
	prep.executeUpdate();
	prep.close();

		
			}
	
}


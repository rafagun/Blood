package Blood.db.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Patient;

public class DB_Patient extends GeneralMethodsJdbc {
	
	
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
			Patient patient = new Patient();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Patient";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		patient.setId(id);
		String name = rs.getString("name");
		patient.setName(name);
		int age = rs.getInt("age");
		patient.setAge(age);
		String blood = rs.getString("blood");
		patient.setBlood(blood);
		String gender = rs.getString("gender");
		patient.setGender(gender);
		boolean smoker = rs.getBoolean("smoker");
		patient.setSmoker(smoker);
		patients.add(patient);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		} catch(Exception ex){
		ex.printStackTrace();
		}
		return patients;
	}
	public void SQLInsert(Patient patient){// falta por hacer lo de las comillas que es odioso
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
	public List<Patient> SQLSearch(String patientName) {
		List<Patient> patients = new LinkedList<>();
	try {
		String sql = "SELECT * FROM Patient WHERE name LIKE ?";
	PreparedStatement prep = super.c.prepareStatement(sql);
	prep.setString(1, patientName);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String name = rs.getString("name");
	int age = rs.getInt("age");
	String blood = rs.getString("blood");
	String gender = rs.getString("gender");
	Boolean smoker = rs.getBoolean("smoker");
	
	
	Patient patient = new Patient (id, name, age, blood, gender, smoker);
	patients.add(patient);
	}

	rs.close();
	prep.close();

	} catch (Exception e) {
	e.printStackTrace();
	}
	return patients;
	}
	
	public void SQLDelete(Patient patient) throws IOException, SQLException {
		try{
			String sql = "DELETE FROM Patient WHERE id=?";
		PreparedStatement prep = super.c.prepareStatement(sql);
		prep.setInt(1, patient.getId());
		prep.executeUpdate();
		prep.close();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			System.err.println("ERROR");
			
		}
		

		
	    
	}
	public void SQLUpdate(Patient patientUpdate) throws IOException , SQLException {

		String sql = "UPDATE Hospital SET name=? ,age=? ,blood=?, gender=?, smoker=?  WHERE id=?";
	PreparedStatement prep = super.c.prepareStatement(sql);
	prep.setString(1, patientUpdate.getName());
	prep.setInt(2, patientUpdate.getAge());
	prep.setString(3, patientUpdate.getBlood());
	prep.setString(4, patientUpdate.getGender());
	prep.setBoolean(5, patientUpdate.getSmoker());
	prep.setInt(6, patientUpdate.getId());
	System.out.println("Update is finished");
	prep.executeUpdate();
	prep.close();

	
}
}


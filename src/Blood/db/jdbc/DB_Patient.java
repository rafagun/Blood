package Blood.db.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;


import Blood.db.pojos.Patient;

public class DB_Patient extends generalMethods implements FunctionsDB<Patient> {
	
	
	public void SQLCreate() throws SQLException {
		try {	
	Statement stmt3 = c.createStatement();
	String sql3 = "CREATE TABLE Patient "
	  + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
	  + " name     TEXT     NOT NULL, "
	  + " age      INTEGER, "
	  + " blood	TEXT     NOT NULL,"
	  + "smoker     BOOLEAN "
	  + "gender     STRING NOT NULL)";
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
		boolean smoker = rs.getBoolean("smooker");
		patient.setSmoker(smoker);
		String gender = rs.getString("gender");
		patient.setGender(gender);
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
		String sql = "INSERT INTO Patient (name, age,blood,smoker,gender) "

		+ "VALUES ('" + patient.getName() + "','"+ patient.getAge() + "');";

		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Patient has been inserted");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	public Patient SQLSearchId (Integer idPatient){// no se si esto esta bien del todo
		Patient patient = new Patient();
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Patient WHERE id=?";
			ResultSet rs = stmt.executeQuery(sql);
			int id = rs.getInt("id");
			patient.setId(id);
			String name = rs.getString("name");
			patient.setName(name);
			int age = rs.getInt("age");
			patient.setAge(age);
			String blood = rs.getString("blood");
			patient.setBlood(blood);
			boolean smoker = rs.getBoolean("smooker");
			patient.setSmoker(smoker);
			String gender = rs.getString("gender");
			patient.setGender(gender);
			
			rs.close();
			stmt.close();
		
		}catch(Exception ex){
		 ex.printStackTrace();
		}
			return patient;
		}
	
	public Patient SQLSearch (String namePatient){// no se si esto esta bien del todo
		Patient patient = new Patient();
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Patient WHERE name=?";
			ResultSet rs = stmt.executeQuery(sql);
			int id = rs.getInt("id");
			patient.setId(id);
			String name = rs.getString("name");
			patient.setName(name);
			int age = rs.getInt("age");
			patient.setAge(age);
			String blood = rs.getString("blood");
			patient.setBlood(blood);
			boolean smoker = rs.getBoolean("smooker");
			patient.setSmoker(smoker);
			String gender = rs.getString("gender");
			patient.setGender(gender);
			
			rs.close();
			stmt.close();
		
		}catch(Exception ex){
		 ex.printStackTrace();
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
	
	public void SQLUpdate(Patient patient) throws IOException , SQLException {
		String sql = "UPDATE Patient SET name=?, location=? , range=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, patient.getName());
	prep.setInt(2, patient.getAge());
	prep.setString(3, patient.getBlood());
	prep.setString(5, patient.getGender());
	prep.setBoolean(4, patient.getSmoker());
	prep.setInt(6, patient.getId());
	prep.executeUpdate();

		
			}
	
	
}


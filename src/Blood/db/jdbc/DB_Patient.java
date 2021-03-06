
package Blood.db.jdbc;



import java.io.IOException;

import java.sql.*;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.LinkedList;

import java.util.List;



import Blood.db.jpa.FunctionsDB;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Nurse;
import Blood.db.pojos.Patient;



public class DB_Patient implements PatientInterface {



	public void SQLCreate() throws TableCreationException {

		try {

			Statement stmt3 = Connect.c.createStatement();

			String sql3 = "CREATE TABLE Patient " + "(id INTEGER PRIMARY KEY AUTOINCREMENT ," 
			+ "name TEXT NOT NULL, "
			+ "blood  TEXT NOT NULL, " 
			+ "age INTEGER, " 
			+ "gender TEXT NOT NULL, " 
			+ "smoker  BOOLEAN)";

			stmt3.executeUpdate(sql3);

			stmt3.close();



			stmt3 = Connect.c.createStatement();

			String sql = "CREATE TABLE PatsMolecules" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE," 
			+ "moleculeId INTEGER REFERENCES Molecules(id) ON DELETE CASCADE," 
			+ "level INTEGER  NOT NULL, " 
			+ "PRIMARY KEY (patientId,moleculeId))";



			stmt3.executeUpdate(sql);

			stmt3.close();

			stmt3 = Connect.c.createStatement();

			String sql1 = "CREATE TABLE PatsCells" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE,"
			+ "cellsId INTEGER REFERENCES Cells(id) ON DELETE CASCADE, " 
			+ "level INTEGER NOT NULL,"
			+ "PRIMARY KEY (patientId,cellsId))";

			stmt3.executeUpdate(sql1);

			stmt3.close();

			stmt3 = Connect.c.createStatement();

			String sql2 = "CREATE TABLE PatsIllnes" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE,"
					+ "illnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE," 
					+ "PRIMARY KEY (patientId, illnesId))";

			stmt3.executeUpdate(sql2);

			stmt3.close();



			stmt3 = Connect.c.createStatement();

			String sql31 = "CREATE TABLE PatsSymptoms" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE,"
					+ "sympId INTEGER REFERENCES Symptoms(id) ON DELETE CASCADE," 
					+ "level TEXT,"
					+ "place TEXT NULL,"
					+ "PRIMARY KEY (patientId, sympId))";

			stmt3.executeUpdate(sql31);

			stmt3.close();



		}



		catch (SQLException ex) {

			throw new TableCreationException(2);

		}

	}

	public void SQLRelationPI(int illnes, int patient){

		try {

		Statement stmt = Connect.c.createStatement();

		String sql = "INSERT INTO PatsIllnes (patientId, illnesId) " + "VALUES ('" + patient + "','"+ illnes + "');";

		stmt.executeUpdate(sql);

		stmt.close();

		} catch (Exception e) {

		e.printStackTrace();

		}

	}

	

	public void SQLRelationPC(int cells, int patient, int level){

		try {

		Statement stmt = Connect.c.createStatement();

		String sql = "INSERT INTO PatsCells (patientId, cellsId, level) " + "VALUES ('" + patient + "','"+ cells + "','"+ level + "');";

		stmt.executeUpdate(sql);

		stmt.close();

		} catch (Exception e) {

		e.printStackTrace();

		}

	}

	

	public void SQLRelationPM(int molecules, int patient, int level){

		try {

		Statement stmt = Connect.c.createStatement();

		String sql = "INSERT INTO PatsMolecules (patientId, moleculeId, level) " + "VALUES ('" + patient + "','"+ molecules + "','"+ level + "');";

		stmt.executeUpdate(sql);

		stmt.close();

		} catch (Exception e) {

		e.printStackTrace();

		}

	}
	
	public void SQLRelationNP(int nurse, int patient){
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "INSERT INTO NursesPatients (nurseId , patientId) " + "VALUES ('" + nurse + "','"+ patient + "');";
		stmt.executeUpdate(sql);
		stmt.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	public void SQLRelationPS(int symptoms, int patient, String level , String place){

		try {

		Statement stmt = Connect.c.createStatement();

		String sql = "INSERT INTO PatsSymptoms (patientId, sympId, level , place) " + "VALUES ('" + patient + "','"+ symptoms + "','"+ level + "','"+ place + "');";

		stmt.executeUpdate(sql);

		stmt.close();

		} catch (Exception e) {

		e.printStackTrace();

		}

	}

	


	public List<Patient> SQLSelect() {

		List<Patient> patients = new ArrayList<Patient>();

		try {

			

			Statement stmt = Connect.c.createStatement();

			String sql = "SELECT * FROM Patient";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {

				Patient patient = new Patient();

				int id = rs.getInt("id");

				patient.setId(id);

				String name = rs.getString("name");

				patient.setName(name);

				int agestr = rs.getInt("age");
				
				patient.setAge(agestr);

				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				//LocalDate age = LocalDate.parse(agestr, formatter);

				//patient.setAge(age);

				String blood = rs.getString("blood");

				patient.setBlood(blood);

				String gender = rs.getString("gender");

				patient.setGender(gender);

				String smokerStr = rs.getString("smoker");

				boolean smoker = Boolean.parseBoolean(smokerStr);

				patient.setSmoker(smoker);

				patients.add(patient);

			}

			rs.close();

			stmt.close();

			System.out.println("Search finished.");

		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return patients;

	}



	public void SQLInsert(Patient patient) {// falta por hacer lo de las

											// comillas que es odioso

		try {

			Statement stmt = Connect.c.createStatement();

			String sql = "INSERT INTO Patient (name, age, blood, gender, smoker) "



					+ "VALUES ('" + patient.getName() + "','" + patient.getAge() + "','" + patient.getBlood() + "','"

					+ patient.getGender() + "','" + patient.getSmoker() + "');";



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

			PreparedStatement prep = Connect.c.prepareStatement(sql);

			prep.setString(1, patientName);

			ResultSet rs = prep.executeQuery();

			while (rs.next()) {

				int id = rs.getInt("id");

				String name = rs.getString("name");

				int agestr = rs.getInt("age");

				//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

				//LocalDate age = LocalDate.parse(agestr, formatter);

				String blood = rs.getString("blood");

				String gender = rs.getString("gender");

				String smokerStr = rs.getString("smoker");

				boolean smoker = Boolean.parseBoolean(smokerStr);

				Patient patient = new Patient(id, name, agestr, blood, gender, smoker);

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
			PreparedStatement prep = Connect.c.prepareStatement(sql);
			prep.setInt(1, patient.getId());
			prep.executeUpdate();
			prep.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("ERROR");

		}
		System.out.println("Patient with the name:" + patient.getName()+  "  has been deleted");
	}



	public void SQLUpdate(Patient patientUpdate) throws IOException, SQLException {



		String sql = "UPDATE Hospital SET name=? ,age=? ,blood=?, gender=?, smoker=?  WHERE id=?";

		PreparedStatement prep = Connect.c.prepareStatement(sql);

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



	@Override

	public void SQLUpdate(Patient oldObj, Patient newObj) throws IOException, SQLException {

		// TODO Auto-generated method stub



	}

}


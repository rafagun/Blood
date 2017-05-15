package Blood.db.jdbc;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Patient;

public class DB_Patient implements FunctionsDB<Patient> {

	public void SQLCreate() throws SQLException {
		try {
			Statement stmt3 = Connect.c.createStatement();
			String sql3 = "CREATE TABLE Patient " + "(id INTEGER PRIMARY KEY AUTOINCREMENT ," + "name TEXT NOT NULL, "
					+ "blood  TEXT NOT NULL, " + "age INTEGER, " + "gender TEXT NOT NULL, " + "smoker  BOOLEAN)";
			stmt3.executeUpdate(sql3);
			stmt3.close();

			stmt3 = Connect.c.createStatement();
			String sql = "CREATE TABLE PatsMolecules" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE"
					+ "moleculeId INTEGER REFERENCES Molecules(id) ON DELETE CASCADE" + "Level INTEGER  NOT NULL "
					+ "PRIMARY KEY (patientId,moleculeId))";

			stmt3.executeUpdate(sql);
			stmt3.close();
			stmt3 = Connect.c.createStatement();
			String sql1 = "CREATE TABLE PatsCells" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE"
					+ "cellsId INTEGER REFERENCES Cells(id) ON DELETE CASCADE " + "Level INTEGER NOT NULL"
					+ "PRIMARY KEY (patientID,cellsId))";
			stmt3.executeUpdate(sql1);
			stmt3.close();
			stmt3 = Connect.c.createStatement();
			String sql2 = "CREATE TABLE PatsIllnes" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE"
					+ "illnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE" + "severity TEXT"
					+ "PRIMARY KEY (patientId, illnesId))";
			stmt3.executeUpdate(sql2);
			stmt3.close();

			stmt3 = Connect.c.createStatement();
			String sql31 = "CREATE TABLE PatsSymptoms" + "(patientId INTEGER REFERENCES Patient(id) ON DELETE CASCADE"
					+ "sympId INTEGER REFERENCES Symptoms(id) ON DELETE CASCADE" + "level TEXT" + "place TEXT NULL"
					+ "PRIMARY KEY (patientId, sympId))";
			stmt3.executeUpdate(sql31);
			stmt3.close();

		}

		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void SQLDrop() {
		try {
			Statement stmt3 = Connect.c.createStatement();
			String sql3 = "DROP TABLE Patient";
			stmt3.executeUpdate(sql3);
			stmt3.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Patient> SQLSelect() {
		ArrayList<Patient> patients = new ArrayList<Patient>();
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
				int age = rs.getInt("age");
				patient.setAge(age);
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
				int age = rs.getInt("age");
				String blood = rs.getString("blood");
				String gender = rs.getString("gender");
				Boolean smoker = rs.getBoolean("smoker");

				Patient patient = new Patient(id, name, age, blood, smoker, gender);
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
		try {
			String sql = "DELETE FROM Patient WHERE id=?";
			PreparedStatement prep = Connect.c.prepareStatement(sql);
			prep.setInt(1, patient.getId());
			prep.executeUpdate();
			prep.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("ERROR");

		}

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

package Blood.db.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Cells;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Nurse;
import Blood.db.pojos.Patient;


public class DB_Nurse implements NurseInterface {
	
	
	public void SQLCreate() throws TableCreationException {
		try {
		Statement stmt2 = Connect.c.createStatement();
		String sql2 = "CREATE TABLE Nurses "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "name TEXT NOT NULL, "
				+ "photo BLOB, "
				+ "hospital_id INTEGER REFERENCES hospital(id) ON DELETE CASCADE)";
		stmt2.executeUpdate(sql2);
		stmt2.close();
		Statement stmt = Connect.c.createStatement();
		String sql = "CREATE TABLE NursesPatients"
				+ "(nurseId INTEGER REFERENCES Nurses(id) ON DELETE SET NULL, "
				+ "patientId INTEGER REFERENCES Patient(id) ON DELETE SET NULL, "
				+ "PRIMARY KEY (nurseId,patientId) )";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Tables are created");
		
		} catch (Exception e){
			throw new TableCreationException(1);
		}
	}

	
	public List<Nurse> SQLSelect(){
		ArrayList<Nurse> nurseList = new ArrayList<Nurse>();
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "SELECT * FROM Nurses";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		Nurse nurse = new Nurse (id,name,photo);
		nurseList.add(nurse);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		} catch(Exception ex){
		ex.printStackTrace();
		}
		return nurseList;
	}
	
	
	public void SQLDelete(Nurse nurse) throws IOException, SQLException {
		try{
		String sql = "DELETE FROM Nurses WHERE id=?";
		PreparedStatement prep = Connect.c.prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		prep.close();
		}
		catch(SQLException ex){
			ex.printStackTrace();
			System.err.println("ERROR");
			}
		System.out.println("Nurse with the name:" + nurse.getName()+  "  has been deleted");
	}
	
	public void SQLInsert(Nurse nurse){
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "INSERT INTO Nurses (name,photo) " + "VALUES ('" + nurse.getName() +  "','" + nurse.getPhoto()+"');";
		stmt.executeUpdate(sql);
		stmt.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
		public void SQLnurseHosp(int nurse, int hosp) throws SQLException{
			
			String sql = "UPDATE Nurses SET hospital_id=? WHERE id=?";
			PreparedStatement stmt = Connect.c.prepareStatement(sql);
			stmt.setInt(1,hosp);
			stmt.setInt(2, nurse);
			stmt.executeUpdate();
			
			
		}
		

	
	public List<Nurse> SQLSearch (String nurseName){
		List<Nurse> nurses  = new LinkedList<>();
		try{
			
			/**Statement stmt = Connect.c.createStatement();
			String sql = "SELECT * FROM Nurses WHERE name=?";
			ResultSet rs = stmt.executeQuery(sql);
			int id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			Nurse nurse = new Nurse (id,name,photo);
			nurses.add(nurse);**/

			String sql = "SELECT * FROM Nurses WHERE name LIKE ?";
			PreparedStatement prep = Connect.c.prepareStatement(sql);
			prep.setString(1, nurseName);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				byte[] photo = rs.getBytes("photo");
				Nurse nurse = new Nurse (id,name,photo);
				nurses.add(nurse);
		}
			rs.close();
			prep.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			}
			return nurses;
			}
		
		
		
		
		
		
		
	@Override
	public void SQLUpdate(Nurse oldObj, Nurse newObj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}

	
	public void SQLUpdate(Nurse nurseUpdate) throws IOException , SQLException {
		String sql = "UPDATE Nurse SET name=? ,photo=? WHERE id=?";
	PreparedStatement prep = Connect.c.prepareStatement(sql);
	prep.setString(1, nurseUpdate.getName());
	prep.setBytes(2, nurseUpdate.getPhoto());
	prep.setInt(3, nurseUpdate.getId());
	System.out.println("Update is finished");
	prep.executeUpdate();
	prep.close();

		
			}
		
	}
	
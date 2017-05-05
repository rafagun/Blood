package Blood.db.jdbc;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;

import Blood.db.pojos.Nurse;


public class DB_Nurse extends GeneralMethods {
	
	
	public void SQLCreate() throws SQLException {
		try {
		Statement stmt2 = c.createStatement();
		String sql2 = "CREATE TABLE Nurses "
				+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "name TEXT NOT NULL,"
				+ "photo BLOB,"
				+ "hospital_id INTEGER REFERENCES hospital(id) ON DELETE CASCADE)";
		stmt2.executeUpdate(sql2);
		stmt2.close();
		System.out.println("Table Nurses is created");
		
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public ArrayList<Nurse> SQLSelect(){
		ArrayList<Nurse> nurses = new ArrayList<Nurse>();
		try {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Nurses";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		byte[] photo = rs.getBytes("photo");
		Nurse nurse = new Nurse (id,name,photo);
		nurses.add(nurse);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		} catch(Exception ex){
		ex.printStackTrace();
		}
		return nurses;
	}
	
	public void SQLDrop(){
		try {


		Statement stmt2 = c.createStatement();
		String sql2 = "DROP TABLE Nurses";
		stmt2.executeUpdate(sql2);
		stmt2.close();
		System.out.println("Table Nurses has been dropped");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void SQLDelete(Nurse nurse) throws IOException, SQLException {
		String sql = "DELETE FROM Nurses WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, nurse.getId());
		prep.executeUpdate();
		prep.close();
		System.out.println("Nurse with the name:" + nurse.getName()+ "has been deleted");
	}
	
	public void SQLInsert(Nurse nurse){
		try {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Nurses (name,photo) "

		+ "VALUES ('" + nurse.getName() + "','"+ nurse.getPhoto() + "');";

		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Nurse has been inserted");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public Nurse SQLSearch (String nurseName){
		Nurse nurse=null;
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Nurses WHERE name=?";
			ResultSet rs = stmt.executeQuery(sql);
			int id = rs.getInt("id");
			String name = rs.getString("name");
			byte[] photo = rs.getBytes("photo");
			nurse = new Nurse(id,name,photo);
			rs.close();
			stmt.close();
		
		}catch(Exception ex){
		 ex.printStackTrace();
		}
			return nurse;
		}

	
	/**public void SQLUpdate(Nurse nurseUpdate, String nurseNameUpdate) throws IOException , SQLException {
		String sql = "UPDATE Nurse SET name=? ,photo=? WHERE name=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, nurseUpdate.getName());
	prep.setString(2, nurseUpdate.getPhoto());
	prep.setString(3, nurseNameUpdate);
	System.out.println("Update is finished");
	prep.executeUpdate();
	prep.close();

		
			}

	@Override
	public void SQLUpdate(Nurse objeto) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}i
	**/
}
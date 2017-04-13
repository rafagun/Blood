package Blood.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Nurse;


public class Nurse1 {
	
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

		// Drop tables: begin
		Statement stmt2 = c.createStatement();
		String sql2 = "DROP TABLE Nurses";
		stmt2.executeUpdate(sql2);
		stmt2.close();
		System.out.println("Table Nurses has been dropped");
		
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public void SQLDelete(String nameNurse) throws IOException, SQLException {
		String sql = "DELETE FROM Nurses WHERE name=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, nameNurse);
		prep.executeUpdate();
		prep.close();
		System.out.println("Nurse with the name:" + nameNurse+ "has been deleted");
	}
	
	public void SQLInsert(Nurse nurse){
		try {
		Statement stmt = c.createStatement();
		String sql = "INSERT INTO Nurses (name, location, photo) "
		//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
		//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
		+ "VALUES ('" + nurse.getName() + "','"+ nurse.getPhoto() + "');";

		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("Nurse has been inserted");
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public Nurse SQLSearchId (Integer idNurse){
		Nurse nurse = null;
		try{
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM Nurses WHERE id=?";
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
}
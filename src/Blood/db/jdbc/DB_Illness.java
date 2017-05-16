package Blood.db.jdbc;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Illnes;

public class DB_Illness implements FunctionsDB<Illnes>{
	
	public void SQLCreate() throws SQLException {
		try {

		// Create tables: begin
		Statement stmt1 = Connect.c.createStatement();
		String sql = "CREATE TABLE Illnes "
		+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
		+ "name TEXT NOT NULL, "
		+ "type TEXT NOT NULL, "
		+ "chronic BOOLEAN)";
		stmt1.executeUpdate(sql);
		stmt1.close();
		String sqL1 = "CREATE TABLE Illnes-Molecules "
				+ "(moleculesId INTEGER REFERENCES Molecules(id) ON DELETE CASCADE"
				+ "IllnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE"
				+ "Level INTEGER  NOT NULL"
				+ "H-L TEXT NOT NULL "
				+ "PRIMARY KEY (moleculesId,IllnesId))";
		stmt1.executeUpdate(sqL1);
		stmt1.close();
		String sqL2 = "CREATE TABLE Illnes-Cells "
				+ "(cellsId INTEGER REFERENCES Cells(id) ON DELETE CASCADE"
				+ "IllnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE"
				+ "Level INTEGER  NOT NULL"
				+ "H-L TEXT NOT NULL "
				+ "PRIMARY KEY (cellsId,IllnesId))";
		stmt1.executeUpdate(sqL2);
		stmt1.close();
		
	
		// Create table: end

		// - Set initial values for the Primary Keys
		// - Don't try to understand this until JPA is explained
		// This is usually not needed, since the initial values
		// are set when the first row is inserted, but since we
		// are using JPA and JDBC in the same project, and JPA
		// needs an initial value, we do this


		}
		catch (Exception e) {
		e.printStackTrace();
		}	
		}
	public List<Illnes> SQLSelect() {
		List<Illnes> illness = new ArrayList<Illnes>();
		try {
		Statement stmt = Connect.c.createStatement();

		String sql = "SELECT * FROM Illnes";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String type = rs.getString("type");
		Boolean chronic = rs.getBoolean("chronic");
		Illnes illnes = new Illnes (id,name,type,chronic);
		illness.add(illnes);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		}
		catch(Exception ex){
		ex.printStackTrace();
		}
		return illness;
		}
	public void SQLDrop(){
		try {

		// Drop tables: begin
		Statement stmt1 = Connect.c.createStatement();
		String sql1 = "DROP TABLE illnes";
		stmt1.executeUpdate(sql1);
		stmt1.close();

		} catch (Exception e) {
		e.printStackTrace();
		}}
	public void SQLInsert(Illnes illnes){
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "INSERT INTO illnes (name, type, chronic) "
		//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
		//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
		+ "VALUES ('" + illnes.getName() + "', '" + illnes.getType()	+ "','"+ illnes.getChronic() + "');";

		stmt.executeUpdate(sql);
		stmt.close();



		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	public void SQLRelation(int illnes, int patient){
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "INSERT INTO NursesPatients (nurseId , patientId) " + "VALUES ('" + illnes + "','"+ patient + "');";
		stmt.executeUpdate(sql);
		stmt.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
	}

	public List<Illnes> SQLSearch(String illnessName) {
		List<Illnes> illness =new LinkedList<Illnes>();
	try {
		String sql = "SELECT * FROM Illnes WHERE name LIKE ?";
	PreparedStatement prep = Connect.c.prepareStatement(sql);
	prep.setString(1, illnessName);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String name = rs.getString("name");
	String type = rs.getString("type");
	boolean chronic = rs.getBoolean("chronic");
	Illnes illnes = new Illnes (id, name, type, chronic);
	illness.add(illnes);}
	rs.close();
	prep.close();

		

	} catch (Exception e) {
	e.printStackTrace();
	}
	return illness;
	}
	@Override
	public void SQLDelete(Illnes obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SQLUpdate(Illnes oldObj, Illnes newObj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}

}

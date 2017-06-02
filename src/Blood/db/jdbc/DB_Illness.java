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
	
	public void SQLCreate() throws TableCreationException {
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
		Statement stmt2 = Connect.c.createStatement();
		String sqL1 = "CREATE TABLE IllnesMolecules "
				+ "(moleculesId INTEGER REFERENCES Molecules(id) ON DELETE CASCADE, "
				+ "IllnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE, "
				+ "Level INTEGER  NOT NULL, "
				+ "HL TEXT NOT NULL, "
				+ "PRIMARY KEY (moleculesId,IllnesId))";
		stmt2.executeUpdate(sqL1);
		stmt2.close();
		Statement stmt3 = Connect.c.createStatement();
		String sqL2 = "CREATE TABLE IllnesCells "
				+ "(cellsId INTEGER REFERENCES Cells(id) ON DELETE CASCADE, "
				+ "IllnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE, "
				+ "Level INTEGER  NOT NULL, "
				+ "HL TEXT NOT NULL, "
				+ "PRIMARY KEY (cellsId,IllnesId))";
		stmt3.executeUpdate(sqL2);
		stmt3.close();
		
	
		// Create table: end

		// - Set initial values for the Primary Keys
		// - Don't try to understand this until JPA is explained
		// This is usually not needed, since the initial values
		// are set when the first row is inserted, but since we
		// are using JPA and JDBC in the same project, and JPA
		// needs an initial value, we do this


		}
		catch (SQLException e) {
		throw new TableCreationException(6);
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
		String strchronic = rs.getString("chronic");
		boolean chronic = Boolean.parseBoolean(strchronic);
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
	@Override
	public void SQLUpdate(Illnes obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	public void SQLRelationIM(Integer molId,Integer illId, Integer level, String hL ){
		try{
			Statement stmt = Connect.c.createStatement();
			String sql = "INSERT INTO Illnes-Molecules (moleculesID,IllnesId,Level,H-L) " + "VALUES ('"+molId+"', '"+illId+"', '"+level+"','"+hL+"');";
			stmt.executeQuery(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void SQLRelationIC(Integer IllnesId,Integer cellId, Integer level, String hL ){
		try{
			Statement stmt = Connect.c.createStatement();
			String sql = "INSERT INTO Illnes-Cells (cellsID,IllnesId,Level,H-L) " + "VALUES ('"+cellId+"', '"+IllnesId+"', '"+level+"','"+hL+"');";
			stmt.executeQuery(sql);
			stmt.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

}

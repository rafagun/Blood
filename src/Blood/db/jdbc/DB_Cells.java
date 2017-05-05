package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Cells;

public class DB_Cells extends GeneralMethods{

	public void SQLCreate() throws SQLException{
		try {

			// Create tables: begin
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE Cells "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "lowL INTEGER, "
			+ "HighL  INTEGER)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			System.out.println("Tables created.");
	}
		catch (Exception e) {
			e.printStackTrace();
			}	
}
public void SQLDrop(){
try {

// Drop tables: begin
Statement stmt5 = c.createStatement();
String sql5 = "DROP TABLE Cells";
stmt5.executeUpdate(sql5);
stmt5.close();

} catch (Exception e) {
e.printStackTrace();
}
}

public void SQLInsert(Cells cells){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO Cells (type, lowL, highL) "
//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
+ "VALUES ('" + cells.getType()	+ "','"+ cells.getLowL() + "','"+ cells.getHighL() + "' );";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}


public ArrayList<Cells> SQLSelect(){
	ArrayList<Cells> cellsList = new ArrayList<Cells>();
	try {
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM Cells";
	ResultSet rs = stmt.executeQuery(sql);
	while (rs.next()) {
	int id = rs.getInt("id");
	String type = rs.getString("type");
	float lowLevel = rs.getFloat("lowL");
	float highLevel = rs.getFloat("highL");
	Cells cells = new Cells (id,type,lowLevel,highLevel);
	cellsList.add(cells);
	}
	rs.close();
	stmt.close();
	System.out.println("Search finished.");
	
	} catch(Exception ex){
	ex.printStackTrace();
	}
	return cellsList;
}


public Cells SQLSearch(String cellsType) {
	Cells cells = new Cells();
try {
String sql = "SELECT * FROM Cells WHERE type LIKE ?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, cellsType);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
int id = rs.getInt("id");
String type = rs.getString("type");
float LowL = rs.getInt("LowL");
float HighL = rs.getInt("HighL");
cells = new Cells (id, type, LowL, HighL);
rs.close();
prep.close();

	}

} catch (Exception e) {
e.printStackTrace();
}
return cells;
}

}
	



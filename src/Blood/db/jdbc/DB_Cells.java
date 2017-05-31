package Blood.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Cells;

public class DB_Cells implements FunctionsDB<Cells>{
	
	public void SQLCreate() throws SQLException{
		try {

			// Create tables: begin
			Statement stmt5 = Connect.c.createStatement();
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


public void SQLInsert(Cells cells){
try {
Statement stmt = Connect.c.createStatement();
String sql = "INSERT INTO Cells (type, lowL, HighL) "
//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
+ "VALUES ('" + cells.getType()	+ "','"+ cells.getLowL() + "','"+ cells.getHighL() + "' );";

stmt.executeUpdate(sql);
stmt.close();
System.out.println("Cells has been added");


} catch (Exception e) {
e.printStackTrace();
}
}


public ArrayList<Cells> SQLSelect(){
	ArrayList<Cells> cellsList = new ArrayList<Cells>();
	try {
	Statement stmt = Connect.c.createStatement();
	String sql = "SELECT * FROM Cells";
	ResultSet rs = stmt.executeQuery(sql);
	while (rs.next()) {
	int id = rs.getInt("id");
	String type = rs.getString("type");
	float lowLevel = rs.getFloat("lowL");
	float highLevel = rs.getFloat("HighL");
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


public List<Cells> SQLSearch(String cellsName) {
	List<Cells> cells = new LinkedList<>();
try {
	String sql = "SELECT * FROM Cells WHERE type LIKE ?";
PreparedStatement prep = Connect.c.prepareStatement(sql);
prep.setString(1, cellsName);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
int id = rs.getInt("id");
String type = rs.getString("type");
float lowL = rs.getFloat("lowL");
float highL = rs.getFloat("HighL");
Cells cell = new Cells (id, type, lowL,highL);
cells.add(cell);
}

rs.close();
prep.close();

} catch (Exception e) {
e.printStackTrace();
}
return cells;
}

public void SQLUpdate(Cells cellsUpdate) throws IOException , SQLException {
	String sql = "UPDATE Cells SET id=? ,type=? ,lowL=? ,HighL=? WHERE id=?";
PreparedStatement prep = Connect.c.prepareStatement(sql);
prep.setInt(1, cellsUpdate.getId());
prep.setString(2, cellsUpdate.getType());
prep.setFloat(3, cellsUpdate.getLowL());
prep.setFloat(4, cellsUpdate.getHighL());
System.out.println("Update is finished");
prep.executeUpdate();
prep.close();
}

public void SQLDelete(Cells cell) {
	try{
	String sql = "DELETE FROM Cells WHERE id=?";
	PreparedStatement prep = Connect.c.prepareStatement(sql);
	prep.setInt(1, cell.getId());
	prep.executeUpdate();
	prep.close();
	}
	catch(SQLException ex){
		ex.printStackTrace();
		System.err.println("ERROR");
		
	}
    
}
@Override
public void SQLUpdate(Cells oldObj, Cells newObj) throws IOException, SQLException {
	// TODO Auto-generated method stub
	
}
}
	



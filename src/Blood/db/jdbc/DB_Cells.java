package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Cells;

public class DB_Cells extends generalMethods implements funcionesDB<Cells>{

	public void SQLCreate() throws SQLException{
		try {

			// Create tables: begin
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE Cells "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "HighL INTEGER, "
			+ "LowL  INTEGER)";
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
String sql5 = "DROP TABLE cells";
stmt5.executeUpdate(sql5);
stmt5.close();

} catch (Exception e) {
e.printStackTrace();
}
}

public void SQLInsert(Cells cells){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO cells (type, range) "
//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
+ "VALUES ('" + cells.getType()	+ "','"+ cells.getHighL() + "','"+ cells.getLowL() + "' );";

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
	float highLevel = rs.getFloat("highL");
	float lowLevel = rs.getFloat("lowL");
	Cells cells = new Cells (id,type,highLevel,lowLevel);
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



    
public void SQLUpdate(Cells cells) throws IOException , SQLException {
	String sql = "UPDATE cells SET type=? , HighL=? , LowL=?  WHERE id=?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, cells.getType());
prep.setFloat(2, cells.getHighL());
prep.setFloat(3, cells.getLowL());
prep.setInt(4, cells.getId());
prep.executeUpdate();

	
		}
@Override
public void SQLDelete(String name) throws IOException, SQLException {
	// TODO Auto-generated method stub
	
}
@Override
public Cells SQLSearch(String nombre) {
	// TODO Auto-generated method stub
	return null;
}
	

}



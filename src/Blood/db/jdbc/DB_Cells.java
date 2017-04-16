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
public ArrayList<Cells> SQLSelect(){
ArrayList<Cells> cells1 = new ArrayList<Cells>();
try {
Statement stmt = c.createStatement();

String sql = "SELECT * FROM Cells";
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
String type = rs.getString("type");
float HighL= rs.getFloat("HighL");
float LowL= rs.getFloat("LowL");
Cells cells = new Cells(type,HighL,LowL);
cells1.add(cells);
}
rs.close();
stmt.close();
System.out.println("Search finished.");
}
catch(Exception ex){
ex.printStackTrace();
}
return cells1;
}

public void SQLDrop(){
try {

// Drop tables: begin
Statement stmt5 = c.createStatement();
String sql5 = "DROP TABLE cells";
stmt5.executeUpdate(sql5);
stmt5.close();
/*Statement stmt2 = c.createStatement();
String sql2 = "DROP TABLE nurses";
stmt2.executeUpdate(sql2);
stmt2.close();
Statement stmt3 = c.createStatement();
String sql3 = "DROP TABLE patients";
stmt3.executeUpdate(sql3);
stmt3.close();
Statement stmt4 = c.createStatement();
String sql4 = "DROP TABLE molecules";
stmt4.executeUpdate(sql4);
stmt4.close();
Statement stmt5 = c.createStatement();
String sql5 = "DROP TABLE cells";
stmt5.executeUpdate(sql5);
stmt5.close();
Statement stmt6 = c.createStatement();
String sql6 = "DROP TABLE symptoms";
stmt6.executeUpdate(sql6);
stmt6.close();
Statement stmt7 = c.createStatement();
String sql7 = "DROP TABLE illness";
stmt7.executeUpdate(sql7);
stmt7.close();
System.out.println("Tables removed.");
// Drop tables: end
*/

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

public Cells SQLSearch(String cellsType) {
	Cells cells = null;
try {
	if (cellsType.equals(null)){
String sql = "SELECT * FROM cells WHERE name LIKE ?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, cellsType);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
String type = rs.getString("type");
float HighL = rs.getFloat("HighL");
float LowL = rs.getFloat("LowL");
cells = new Cells (type, HighL, LowL);
rs.close();
prep.close();

	}
}


} catch (Exception e) {
e.printStackTrace();
}
return cells;
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
	

}



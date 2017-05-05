package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Molecules;

public class DB_Molecules extends generalMethods{

	public void SQLCreate() throws SQLException{
		try {


			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE Molecules "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "LowLevels INTEGER, "
			+ "HighLevels  INTEGER)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			System.out.println("Tables created.");
	}
		catch (Exception e) {
			e.printStackTrace();
			}	
}
public ArrayList<Molecules> SQLSelect(){
ArrayList<Molecules> Molecules1 = new ArrayList<Molecules>();
try {
Statement stmt = c.createStatement();

String sql = "SELECT * FROM Molecules";
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
String type = rs.getString("type");
float LowL= rs.getFloat("LowLevels");
float HighL= rs.getFloat("HighLevels");
Molecules molecules = new Molecules(type,LowL,HighL);
Molecules1.add(molecules);
}
rs.close();
stmt.close();
System.out.println("Search finished.");
}
catch(Exception ex){
ex.printStackTrace();
}
return Molecules1;
}

public void SQLDrop(){
try {


Statement stmt4 = c.createStatement();
String sql4 = "DROP TABLE molecules";
stmt4.executeUpdate(sql4);
stmt4.close();

} catch (Exception e) {
e.printStackTrace();
}
}

public void SQLInsert(Molecules molecules){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO molecules (type, LowLevels, HighLevels) "

+ "VALUES ('" + molecules.getType()	+ "','"+ molecules.getLowLevels() + "','"+ molecules.getHighLevels() + "' );";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}

public Molecules SQLSearch(String moleculesType) {
	Molecules molecules = new Molecules();
try {
String sql = "SELECT * FROM Molecules WHERE type LIKE ?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, moleculesType);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
String type = rs.getString("type");
float LowL = rs.getFloat("LowLevels");
float HighL = rs.getFloat("HighLevels");
molecules = new Molecules (type, LowL, HighL);
rs.close();
prep.close();

}


} catch (Exception e) {
e.printStackTrace();
}
return molecules;
}

}



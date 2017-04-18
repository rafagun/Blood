package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Blood.db.pojos.Molecules;

public class DB_Molecules extends generalMethods implements FunctionsDB<Molecules>{

	public void SQLCreate() throws SQLException{
		try {


			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE Molecules "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "HighLevels INTEGER, "
			+ "LowLevels  INTEGER)";
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
float HighL= rs.getFloat("HighL");
float LowL= rs.getFloat("LowL");
Molecules molecules = new Molecules(type,HighL,LowL);
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
String sql4 = "DROP TABLE cells";
stmt4.executeUpdate(sql4);
stmt4.close();

} catch (Exception e) {
e.printStackTrace();
}
}

public void SQLInsert(Molecules molecules){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO molecules (type, range) "

+ "VALUES ('" + molecules.getType()	+ "','"+ molecules.getHighLevels() + "','"+ molecules.getLowLevels() + "' );";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}

public Molecules SQLSearch(String moleculesType) {
	Molecules molecules = null;
try {
	if (moleculesType.equals(null)){
String sql = "SELECT * FROM molecules WHERE name LIKE ?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, moleculesType);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
String type = rs.getString("type");
float HighL = rs.getFloat("HighLevels");
float LowL = rs.getFloat("LowLevels");
molecules = new Molecules (type, HighL, LowL);
rs.close();
prep.close();

	}
}


} catch (Exception e) {
e.printStackTrace();
}
return molecules;
}

    
public void SQLUpdate(Molecules molecules) throws IOException , SQLException {
	String sql = "UPDATE molecules SET type=? , HighLevels=? , LowLevels=?  WHERE id=?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, molecules.getType());
prep.setFloat(2, molecules.getHighLevels());
prep.setFloat(3, molecules.getLowLevels());
prep.setInt(4, molecules.getId());
prep.executeUpdate();

	
		}
@Override
public void SQLDelete(String name) throws IOException, SQLException {
	// TODO Auto-generated method stub
	
}
	

}



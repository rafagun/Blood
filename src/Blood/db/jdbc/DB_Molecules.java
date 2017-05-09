package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Molecules;

public class DB_Molecules extends GeneralMethodsJdbc{

	public void SQLCreate() throws SQLException{
		try {


			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE Molecules "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "lowLevels INTEGER, "
			+ "highLevels  INTEGER)";
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
Integer id = rs.getInt("id");
String type = rs.getString("type");
Integer lowL= rs.getInt ("lowLevels");
Integer highL= rs.getInt("highLevels");
Molecules molecules = new Molecules(id,type,highL,lowL);
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
String sql = "INSERT INTO molecules (id, type, lowLevels, highLevels) "

+ "VALUES ('"+molecules.getId()+"','" + molecules.getType()	+ "','"+ molecules.getLowLevels() + "','"+ molecules.getHighLevels() + "' );";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}

public List<Molecules> SQLSearch(String moleculeName) {
	List<Molecules> molecules = new LinkedList<>();
try {
	String sql = "SELECT * FROM Molecules WHERE type LIKE ?";
PreparedStatement prep = super.c.prepareStatement(sql);
prep.setString(1, moleculeName);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
int id = rs.getInt("id");
String type = rs.getString("type");
Integer lowL = rs.getInt("lowLevels");
Integer highL = rs.getInt("HighLevels");
Molecules molecule = new Molecules (id, type, highL, lowL);
molecules.add(molecule);
}

rs.close();
prep.close();

} catch (Exception e) {
e.printStackTrace();
}
return molecules;
}

public void SQLDelete(Molecules molecule) {
	try{
	String sql = "DELETE FROM Molecules WHERE id=?";
	PreparedStatement prep = super.c.prepareStatement(sql);
	prep.setInt(1, molecule.getId());
	prep.executeUpdate();
	prep.close();
	}
	catch(SQLException ex){
		ex.printStackTrace();
		System.err.println("ERROR");
		
	}
    
}

public void SQLUpdate(Molecules molecUpdate) throws IOException , SQLException {
	String sql = "UPDATE Molecules SET id= ? ,type=? ,lowLevels=? ,highLevels=?  WHERE id=?";
PreparedStatement prep = super.c.prepareStatement(sql);
prep.setInt(1,molecUpdate.getId());
prep.setString(2, molecUpdate.getType());
prep.setInt(3, molecUpdate.getLowLevels());
prep.setInt(4, molecUpdate.getHighLevels());
System.out.println("Update is finished");
prep.executeUpdate();
prep.close();
}

}



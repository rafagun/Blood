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
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Molecules;

public class DB_Molecules implements FunctionsDB<Molecules>{

	public void SQLCreate() throws SQLException{
		try {


			Statement stmt4 = Connect.c.createStatement();
			String sql4 = "CREATE TABLE Molecules "
			+ "(id INTEGER PRIMARY KEY AUTOINCREMENT , "
			+ "type  TEXT NOT NULL, "
			+ "lowLevels INTEGER, "
			+ "highLevels  INTEGER )";
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
Statement stmt = Connect.c.createStatement();

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


public void SQLInsert(Molecules molecules){
try {
Statement stmt = Connect.c.createStatement();
String sql = "INSERT INTO molecules (type, lowLevels, highLevels)"
+ "VALUES ('" + molecules.getType()	+ "','"+ molecules.getLowLevels() + "','"+ molecules.getHighLevels() + "');";

stmt.executeUpdate(sql);
stmt.close();
System.out.println("Molecules has been added");

} catch (Exception e) {
e.printStackTrace();
}
}

public List<Molecules> SQLSearch(String moleculeName) {
	List<Molecules> molecules = new LinkedList<>();
try {
	String sql = "SELECT * FROM Molecules WHERE type LIKE ?";
PreparedStatement prep = Connect.c.prepareStatement(sql);
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

public void SQLDelete(Molecules molecule) throws IOException, SQLException {
	try{
	String sql = "DELETE FROM Molecules WHERE id=?";
	PreparedStatement prep = Connect.c.prepareStatement(sql);
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
PreparedStatement prep = Connect.c.prepareStatement(sql);
prep.setInt(1,molecUpdate.getId());
prep.setString(2, molecUpdate.getType());
prep.setFloat(3, molecUpdate.getLowLevels());
prep.setFloat(4, molecUpdate.getHighLevels());
System.out.println("Update is finished");
prep.executeUpdate();
prep.close();
}
@Override
public void SQLUpdate(Molecules oldObj, Molecules newObj) throws IOException, SQLException {
	// TODO Auto-generated method stub
	
}

}



package Blood.db.jdbc;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Blood.db.pojos.Hospital;



public class DB_Hospital extends generalMethods {

public void SQLCreate() throws SQLException {
try {

// Create tables: begin
Statement stmt1 = c.createStatement();
String sql1 = "CREATE TABLE Hospital "
+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
+ "name TEXT NOT NULL, "
+ "location  TEXT, "
+ "range  INTEGER)";
stmt1.executeUpdate(sql1);
stmt1.close();
System.out.println("Tables created.");




}
catch (Exception e) {
e.printStackTrace();
}	
}

public ArrayList<Hospital> SQLSelect(){
ArrayList<Hospital> hospitals = new ArrayList<Hospital>();
try {
Statement stmt = c.createStatement();

String sql = "SELECT * FROM Hospital";
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
int id = rs.getInt("id");
String name = rs.getString("name");
String location = rs.getString("location");
int range= rs.getInt("range");
Hospital hospital = new Hospital (id,name,location,range);
hospitals.add(hospital);
}
rs.close();
stmt.close();
System.out.println("Search finished.");
}
catch(Exception ex){
ex.printStackTrace();
}
return hospitals;
}
public void SQLDrop(){
try {

// Drop tables: begin
Statement stmt1 = c.createStatement();
String sql1 = "DROP TABLE hospital";
stmt1.executeUpdate(sql1);
stmt1.close();

} catch (Exception e) {
e.printStackTrace();
}
}
public void SQLInsert(Hospital hospital){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO hospital (name, location, range) "

+ "VALUES ('" + hospital.getName() + "', '" + hospital.getLocation()	+ "','"+ hospital.getRange() + "');";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}

public Hospital SQLSearch(String hospName) {
	Hospital hospital =new Hospital();
try {
	String sql = "SELECT * FROM hospital WHERE name LIKE ?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, hospName);
ResultSet rs = prep.executeQuery();
while (rs.next()) {
int id = rs.getInt("id");
String name = rs.getString("name");
String location = rs.getString("location");
int range = rs.getInt("range");
hospital = new Hospital (id, name, location, range);
rs.close();
prep.close();

	}

} catch (Exception e) {
e.printStackTrace();
}
return hospital;
}

public void SQLDelete(String nameHospital) throws IOException, SQLException {
	String sql = "DELETE FROM Hospital WHERE name=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, nameHospital);
	prep.executeUpdate();
	prep.close();

	
    
}
public void SQLUpdate(Hospital hospUpdate, String hospNameUpdate) throws IOException , SQLException {
	String sql = "UPDATE Hospital SET name=? ,location=? ,range=?  WHERE name=?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, hospUpdate.getName());
prep.setString(2, hospUpdate.getLocation());
prep.setInt(3, hospUpdate.getRange());
prep.setString(4, hospNameUpdate);
System.out.println("Update is finished");
prep.executeUpdate();
prep.close();

	
		}

	

}



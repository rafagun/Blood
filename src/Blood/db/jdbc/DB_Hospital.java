package Blood.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Blood.db.pojos.Hospital;



public class DB_Hospital {
Connection c;


public void SQLConnect() {
try {
// Open database connection
Class.forName("org.sqlite.JDBC");
c = DriverManager.getConnection("jdbc:sqlite:./db/blood.db");
c.createStatement().execute("PRAGMA foreign_keys=ON");
System.out.println("Database connection opened.");

}
catch (Exception e) {
e.printStackTrace();
}

}
public void SQLDisconnect()
{
// Close database connection
try {
c.close();
System.out.println("Database connection closed.");
} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
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
/** Statement stmt2 = c.createStatement();
String sql2 = "CREATE TABLE Nurses "
  + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
  + " name     TEXT     NOT NULL, "
  + " photo    BLOB , "
  + " hospital_id  INTEGER  REFERENCES hospital(id) ON DELETE CASCADE ) ";

stmt2.executeUpdate(sql2);
stmt2.close();
Statement stmt3 = c.createStatement();
String sql3 = "CREATE TABLE Patient "
  + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
  + " name     TEXT     NOT NULL, "
  + " age      INTEGER, "
  + " blood	TEXT     NOT NULL,"
  + "smoker     BOOLEAN "
  + "gender     STRING NOT NULL)";
stmt3.executeUpdate(sql3);
stmt3.close();
Statement stmt4 = c.createStatement();
String sql4 = "CREATE TABLE authors "
  + "(report_id     INTEGER  REFERENCES reports(id) ON UPDATE CASCADE ON DELETE SET NULL,"
  + " employee_id   INTEGER  REFERENCES employees(id) ON UPDATE CASCADE ON DELETE SET NULL,"
  + " PRIMARY KEY (report_id,employee_id))";
stmt4.executeUpdate(sql4);
stmt4.close(); **/
System.out.println("Tables created.");
// Create table: end

// - Set initial values for the Primary Keys
// - Don't try to understand this until JPA is explained
// This is usually not needed, since the initial values
// are set when the first row is inserted, but since we
// are using JPA and JDBC in the same project, and JPA
// needs an initial value, we do this.
/**Statement stmtSeq = c.createStatement();
String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
stmtSeq.executeUpdate(sqlSeq);
sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
stmtSeq.executeUpdate(sqlSeq);
sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
stmtSeq.executeUpdate(sqlSeq);
stmtSeq.close();**/



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
public void SQLInsert(Hospital hospital){
try {
Statement stmt = c.createStatement();
String sql = "INSERT INTO hospital (name, location, range) "
//Se ponen comillas simples y comillas dobles porque las dobles dentro del parentesis se eliminan
//con las de "VALUES" y por tanto quedan las comillas simples que son las necesarias en SQL.
+ "VALUES ('" + hospital.getName() + "', '" + hospital.getLocation()	+ "','"+ hospital.getRange() + "');";

stmt.executeUpdate(sql);
stmt.close();



} catch (Exception e) {
e.printStackTrace();
}
}

public Hospital SQLSearch(String hospName, String hospLocation) {
	Hospital hospital = null;
try {
	if (hospName.equals(null)){
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
}
	if (hospLocation.equals(null)  ){
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
public void SQLUpdate(int opcion ,String newl, int id) throws IOException , SQLException {
	switch(opcion){
	case 1 : String sql = "UPDATE Hospital SET name=? WHERE name=?";
PreparedStatement prep = c.prepareStatement(sql);
prep.setString(1, newl);
prep.executeUpdate();
prep.setInt(2, id);
	case 2:
		String sql2 = "UPDATE Hospital SET location=? WHERE name=?";
		PreparedStatement prep2 = c.prepareStatement(sql2);
		prep2.setString(2, newl);
		prep2.setInt(2, id);
		prep2.executeUpdate();
	case 3:
		String sql3 = "UPDATE Hospital SET range=? WHERE name=?";
		PreparedStatement prep3 = c.prepareStatement(sql3);
		prep3.setInt(3, Integer.parseInt(newl));
		prep3.setInt(2, id);
		prep3.executeUpdate();
		}
	

}

}

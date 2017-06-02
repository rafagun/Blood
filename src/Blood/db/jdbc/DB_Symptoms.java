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
import Blood.db.pojos.Symptoms;

public class DB_Symptoms implements FunctionsDB <Symptoms> {



	public void SQLCreate() throws TableCreationException{
try{
		// Create tables: begin
		Statement stmt1 = Connect.c.createStatement();
		String sql1 = "CREATE TABLE Symptoms "
		+ "(id INTEGER PRIMARY KEY AUTOINCREMENT ,"
		+ "type TEXT NOT NULL,"
		+ "severity TEXT NOT NULL)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
		stmt1 = Connect.c.createStatement();
		String sql2 = "CREATE TABLE SympsIlls"
				+ "(symptomsId INTEGER REFERENCES Symptoms(id) ON DELETE CASCADE"
				+ "illnesId INTEGER REFERENCES Illnes(id) ON DELETE CASCADE"
				+ "place TEXT"
				+ "PRIMARY KEY (simptomsID,illnesId)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
}
catch(SQLException ex){
	throw new TableCreationException(5);
}
		
		}
	public void SQLInsert(Symptoms symptom){
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "INSERT INTO Symptoms (type, severity) "

		+ "VALUES ('" + symptom.getType() +"','"+ symptom.getSeverity() + "');";

		stmt.executeUpdate(sql);
		stmt.close();



		} catch (Exception e) {
		e.printStackTrace();
		}
		}
	public List<Symptoms> SQLSelect(){
		ArrayList<Symptoms> symptoms = new ArrayList<Symptoms>();
		try {
		Statement stmt = Connect.c.createStatement();
		String sql = "SELECT * FROM Symptoms";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
		int id = rs.getInt("id");
		String type = rs.getString("type");
		String severity =rs.getString("severity");
		Symptoms symptom = new Symptoms (id,type,severity);
		symptoms.add(symptom);
		}
		rs.close();
		stmt.close();
		System.out.println("Search finished.");
		} catch(Exception ex){
		ex.printStackTrace();
		}
		return symptoms;
	}
	

	public List<Symptoms> SQLSearch(String symptomsName) {
		List<Symptoms> symptoms= new LinkedList<>();
	try {
		String sql = "SELECT * FROM symptoms WHERE type LIKE ?";
	PreparedStatement prep = Connect.c.prepareStatement(sql);
	prep.setString(1, symptomsName);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
	int id = rs.getInt("id");
	String type = rs.getString("type");
	String severity = rs.getString("severity");
	Symptoms symptom = new Symptoms (id, type, severity);
	symptoms.add(symptom);}
	rs.close();
	prep.close();

		

	} catch (Exception e) {
	e.printStackTrace();
	}
	return symptoms;
	}
	
	@Override
	public void SQLDelete(Symptoms obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SQLUpdate(Symptoms oldObj, Symptoms newObj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void SQLUpdate(Symptoms obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	
	


}

package Blood.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Blood.db.pojos.Hospital;

public class SQLDelete {
	private static Connection c;

	private static void printHospital() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM Hospital";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String location = rs.getString("location");
			int range = rs.getInt("range");
			Hospital hospital = new Hospital(id, name, location,range);
			System.out.println(hospital);
		}
		rs.close();
		stmt.close();
	}
}

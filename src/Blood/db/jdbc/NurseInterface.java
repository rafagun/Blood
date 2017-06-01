package Blood.db.jdbc;

import java.sql.SQLException;

import Blood.db.jpa.FunctionsDB;
import Blood.db.pojos.Nurse;

public interface NurseInterface extends FunctionsDB<Nurse> {
	public void SQLnurseHosp(int idnurse, int idhospital) throws SQLException;
}

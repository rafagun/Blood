package Blood.db.jpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;



public interface FunctionsDB <Obj> {
	public void SQLCreate() throws SQLException;
	public List<Obj> SQLSelect();
	public void SQLDrop();
	public void SQLInsert(Obj obj);
	public List<Obj> SQLSearch(String name);
	public void SQLDelete(Obj obj) throws IOException, SQLException;
	public void SQLUpdate(Obj oldObj, Obj newObj) throws IOException , SQLException;
}

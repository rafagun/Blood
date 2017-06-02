package Blood.db.jpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.EntityManager;

import Blood.db.jdbc.TableCreationException;



public interface FunctionsDB <Obj> {
	public void SQLCreate() throws TableCreationException;
	public List<Obj> SQLSelect();
	public void SQLInsert(Obj obj);
	public List<Obj> SQLSearch(String name);
	public void SQLDelete(Obj obj) throws IOException, SQLException;
	public void SQLUpdate(Obj oldObj, Obj newObj) throws IOException , SQLException;
	public void SQLUpdate(Obj obj) throws IOException, SQLException;
}

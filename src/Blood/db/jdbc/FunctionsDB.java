package Blood.db.jdbc;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public interface FunctionsDB <Objeto> {
	public void SQLDisconnect();
	public void SQLConnect();
	public void SQLCreate() throws SQLException;
	public List<Objeto> SQLSelect();
	public void SQLDrop();
	public void SQLInsert(Objeto objetol);
	public List<Objeto> SQLSearch(String nombre);
	public void SQLDelete(String name) throws IOException, SQLException;
	public void SQLUpdate(Objeto objeto) throws IOException , SQLException;
}
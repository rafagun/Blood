package Blood.db.jdbc;

import java.sql.Connection;

import Blood.db.jpa.FunctionsDB;

public abstract class Connect {// the pupose of this class is to avoid the multiple inheritance problem that we had with GeneralMethods
	protected static Connection c;
	
}

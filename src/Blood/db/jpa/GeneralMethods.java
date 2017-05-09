package Blood.db.jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import Blood.db.jdbc.Connect;

public abstract class GeneralMethods extends Connect {
	protected static EntityManager em;
	
	static protected EntityManager StartMethod() {
	    em = Persistence.createEntityManagerFactory("blood-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		return em;
	}
	
}

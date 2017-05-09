package Blood.db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class GeneralMethods {
	protected static EntityManager em;
	
	static protected EntityManager StartMethod() {
	    em = Persistence.createEntityManagerFactory("blood-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		return em;
	}
	
}

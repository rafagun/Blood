package Blood.db.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class eManager {
	public static EntityManager em;

	public static  void StartMethod() {
	    em = Persistence.createEntityManagerFactory("blood-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	
	}

	}


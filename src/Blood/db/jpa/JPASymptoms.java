package Blood.db.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Symptoms;


public class JPASymptoms implements FunctionsDB<Symptoms> {
	public void SQLInsert(Symptoms symptom){
		
		
		eManager.em.getTransaction().begin();
		eManager.em.persist(symptom);
		eManager.em.getTransaction().commit();
	}
	
	public void SQLDelete(Symptoms symptom){
		
		eManager.em.getTransaction().begin();
		eManager.em.remove(symptom);
		eManager.em.getTransaction().commit();
		
	}
	
	
	 Symptoms SQLSearch1(String name){
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Symptoms WHERE name LIKE ?", Symptoms.class);
		q1.setParameter(1, "%" + name + "%");
		Symptoms symptom = (Symptoms) q1.getSingleResult();
		return symptom;
	}
	
	public List<Symptoms> SQLSearch(String name){
		
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Symptoms WHERE name LIKE ?", Symptoms.class);
		q1.setParameter(1, "%" + name + "%");
		List<Symptoms> symptoms = (List<Symptoms>) q1.getResultList();
		return symptoms;
		
	}
	
	public void SQLUpdate(Symptoms oldsymp,Symptoms newsymp){
		eManager.em.getTransaction().begin();
		newsymp.setType(oldsymp.getType());
		newsymp.setSeverity(oldsymp.getSeverity());
		eManager.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Symptoms> SQLSelect() {
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Symptoms", Symptoms.class);
		List<Symptoms> symptoms = (List<Symptoms>) q1.getResultList();
		return symptoms;
	}
	
}

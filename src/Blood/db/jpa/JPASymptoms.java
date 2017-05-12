package Blood.db.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Symptoms;


public class JPASymptoms implements FunctionsDB<Symptoms> {
	public void SQLInsert(Symptoms symptom){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(symptom);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Symptoms symptom){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(symptom);
		Connect.em.getTransaction().commit();
		
	}
	
	
	 Symptoms SQLSearch1(String name){
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Symptoms WHERE name LIKE ?", Symptoms.class);
		q1.setParameter(1, "%" + name + "%");
		Symptoms symptom = (Symptoms) q1.getSingleResult();
		return symptom;
	}
	
	public List<Symptoms> SQLSearch(String name){
		
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Symptoms WHERE name LIKE ?", Symptoms.class);
		q1.setParameter(1, "%" + name + "%");
		List<Symptoms> symptoms = (List<Symptoms>) q1.getResultList();
		return symptoms;
		
	}
	
	public void SQLUpdate(Symptoms oldsymp,Symptoms newsymp){
		Connect.em.getTransaction().begin();
		newsymp.setType(oldsymp.getType());
		newsymp.setSeverity(oldsymp.getSeverity());
		Connect.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Symptoms> SQLSelect() {
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Symptoms", Symptoms.class);
		List<Symptoms> symptoms = (List<Symptoms>) q1.getResultList();
		return symptoms;
	}

	@Override
	public void SQLDrop() {
		// JPA cannot work with tables
		
	}
	
}

package Blood.db.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Illnes;

public class JPAIllnes implements FunctionsDB<Illnes>{
	public void SQLInsert(Illnes illnes){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(illnes);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Illnes illnes){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(illnes);
		Connect.em.getTransaction().commit();
		
	}
	
	
	Illnes SQLSearch1(String name){
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Illnes WHERE name LIKE ?", Illnes.class);
		q1.setParameter(1, "%" + name + "%");
		Illnes illnes = (Illnes) q1.getSingleResult();
		return illnes;
	}
	
	public List<Illnes> SQLSearch(String name){
		
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Illnes WHERE name LIKE ?", Illnes.class);
		q1.setParameter(1, "%" + name + "%");
		List<Illnes> illness = (List<Illnes>) q1.getResultList();
		return illness;
		
	}
	
	public void SQLUpdate(Illnes oldill, Illnes newill){
		Connect.em.getTransaction().begin();
		newill.setName(oldill.getName());
		newill.setType(oldill.getType());
		newill.setChronic(oldill.getChronic());
		Connect.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Illnes> SQLSelect() {
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Illnes", Illnes.class);
		List<Illnes> illness = (List<Illnes>) q1.getResultList();
		return illness;
	}

	@Override
	public void SQLDrop() {
		// JPA cannot work with tables
		
	}
	
}

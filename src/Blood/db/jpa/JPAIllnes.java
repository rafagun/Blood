package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.pojos.Illnes;

public class JPAIllnes extends GeneralMethods {
	public void SQLInsert(Illnes illnes){
		
		
		em.getTransaction().begin();
		em.persist(illnes);
		em.getTransaction().commit();
	}
	
	public void SQLDelete(Illnes illnes){
		
		em.getTransaction().begin();
		em.remove(illnes);
		em.getTransaction().commit();
		
	}
	
	
	Illnes SQLSearch1(String name){
		Query q1 = em.createNativeQuery("SELECT * FROM Illnes WHERE name LIKE ?", Illnes.class);
		q1.setParameter(1, "%" + name + "%");
		Illnes illnes = (Illnes) q1.getSingleResult();
		return illnes;
	}
	
	public List<Illnes> SQLSearch(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM hospital WHERE name LIKE ?", Illnes.class);
		q1.setParameter(1, "%" + name + "%");
		List<Illnes> illness = (List<Illnes>) q1.getResultList();
		return illness;
		
	}
	
	public void SQLUpdate(Illnes oldill, Illnes newill){
		em.getTransaction().begin();
		newill.setName(oldill.getName());
		newill.setType(oldill.getType());
		newill.setChronic(oldill.getChronic());
		em.getTransaction().commit();
	}
	
}

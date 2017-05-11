package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.pojos.Molecules;

public class JPAMolecules extends GeneralMethods {
	public void SQLInsert(Molecules molecule){
		
		
		em.getTransaction().begin();
		em.persist(molecule);
		em.getTransaction().commit();
	}
	
	public void SQLDelete(Molecules molecule){
		
		em.getTransaction().begin();
		em.remove(molecule);
		em.getTransaction().commit();
		
	}
	
	
	Molecules SQLSearch1(String name){
		Query q1 = em.createNativeQuery("SELECT * FROM Hospital WHERE name LIKE ?", Molecules.class);
		q1.setParameter(1, "%" + name + "%");
		Molecules molecule = (Molecules) q1.getSingleResult();
		return molecule;
	}
	
	public List<Molecules> SQLSearch(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM Molecules WHERE name LIKE ?", Molecules.class);
		q1.setParameter(1, "%" + name + "%");
		List<Molecules> molecules = (List<Molecules>) q1.getResultList();
		return molecules;
		
	}
	
	public void SQLUpdate(Molecules oldmol, Molecules newmol){
		em.getTransaction().begin();
		newmol.setType(oldmol.getType());
		newmol.setHighLevels(oldmol.getHighLevels());
		newmol.setLowLevels(oldmol.getLowLevels());
		em.getTransaction().commit();
	}
	
}

package Blood.db.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Molecules;


public class JPAMolecules implements FunctionsDB<Molecules>{
	public void SQLInsert(Molecules molecule){
		
		
		eManager.em.getTransaction().begin();
		eManager.em.persist(molecule);
		eManager.em.getTransaction().commit();
	}
	
	public void SQLDelete(Molecules molecule){
		
		eManager.em.getTransaction().begin();
		eManager.em.remove(molecule);
		eManager.em.getTransaction().commit();
		
	}
	
	
	Molecules SQLSearch1(String name){
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Hospital WHERE name LIKE ?", Molecules.class);
		q1.setParameter(1, "%" + name + "%");
		Molecules molecule = (Molecules) q1.getSingleResult();
		return molecule;
	}
	
	public List<Molecules> SQLSearch(String name){
		
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Molecules WHERE name LIKE ?", Molecules.class);
		q1.setParameter(1, "%" + name + "%");
		List<Molecules> molecules = (List<Molecules>) q1.getResultList();
		return molecules;
		
	}
	
	public void SQLUpdate(Molecules oldmol, Molecules newmol){
		eManager.em.getTransaction().begin();
		newmol.setType(oldmol.getType());
		newmol.setHighLevels(oldmol.getHighLevels());
		newmol.setLowLevels(oldmol.getLowLevels());
		eManager.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Molecules> SQLSelect() {
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Molecules", Molecules.class);
		List<Molecules> molecules = (List<Molecules>) q1.getResultList();
		return molecules;
	}
	
}

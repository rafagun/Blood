package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.pojos.Cells;


public class JPACells extends GeneralMethods {

	public void SQLInsert(Cells cell){
		
		
		em.getTransaction().begin();
		em.persist(cell);
		em.getTransaction().commit();
	}
	
	public void SQLDelete(Cells cell){
		
		em.getTransaction().begin();
		em.remove(cell);
		em.getTransaction().commit();
		
	}
	
	
	 Cells SQLSearch1(String name){
		Query q1 = em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		Cells cell = (Cells) q1.getSingleResult();
		return cell;
	}
	
	public List<Cells> SQLSearch(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		List<Cells> cells = (List<Cells>) q1.getResultList();
		return cells;
		
	}
	
	public void SQLUpdate(Cells oldCell, Cells newCell){
		em.getTransaction().begin();
		newCell.setType(oldCell.getType());
		newCell.setLowL(oldCell.getLowL());
		newCell.setHighL(oldCell.getHighL());
		em.getTransaction().commit();
	}
}

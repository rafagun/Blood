package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Cells;


public class JPACells {

	public void SQLInsert(Cells cell){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(cell);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Cells cell){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(cell);
		Connect.em.getTransaction().commit();
		
	}
	
	
	 Cells SQLSearch1(String name){
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		Cells cell = (Cells) q1.getSingleResult();
		return cell;
	}
	
	public List<Cells> SQLSearch(String name){
		
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		List<Cells> cells = (List<Cells>) q1.getResultList();
		return cells;
		
	}
	
	public void SQLUpdate(Cells oldCell, Cells newCell){
		Connect.em.getTransaction().begin();
		newCell.setType(oldCell.getType());
		newCell.setLowL(oldCell.getLowL());
		newCell.setHighL(oldCell.getHighL());
		Connect.em.getTransaction().commit();
	}
}

package Blood.db.jpa;

import java.sql.SQLException;

import java.util.List;

import javax.persistence.Query;

import Blood.db.jpa.eManager;
import Blood.db.pojos.Cells;


public class JPACells implements FunctionsDB<Cells> {

	public void SQLInsert(Cells cell){
		
		
		eManager.em.getTransaction().begin();
		eManager.em.persist(cell);
		eManager.em.getTransaction().commit();
	}
	
	public void SQLDelete(Cells cell){
		
		eManager.em.getTransaction().begin();
		eManager.em.remove(cell);
		eManager.em.getTransaction().commit();
		
	}
	
	
	 Cells SQLSearch1(String name){
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		Cells cell = (Cells) q1.getSingleResult();
		return cell;
	}
	
	public List<Cells> SQLSearch(String name){
		
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Cells WHERE name LIKE ?", Cells.class);
		q1.setParameter(1, "%" + name + "%");
		List<Cells> cells = (List<Cells>) q1.getResultList();
		return cells;
		
	}
	
	public void SQLUpdate(Cells oldCell, Cells newCell){
		eManager.em.getTransaction().begin();
		newCell.setType(oldCell.getType());
		newCell.setLowL(oldCell.getLowL());
		newCell.setHighL(oldCell.getHighL());
		eManager.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Cells> SQLSelect() {
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Cells", Cells.class);
		List<Cells> cells = (List<Cells>) q1.getResultList();
		return cells;
	}
}

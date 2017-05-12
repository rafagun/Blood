package Blood.db.jpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Nurse;


public class JPANurse implements FunctionsDB<Nurse> {
	
	public void SQLInsert(Nurse nurse){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(nurse);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Nurse nurse){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(nurse);
		Connect.em.getTransaction().commit();
		
	}
	
	 Nurse SQLSearch1 (String name){
			Query q1 = Connect.em.createNativeQuery("SELECT * FROM Nurses WHERE name LIKE ?", Nurse.class);
			q1.setParameter(1, "%" + name + "%");
			Nurse nurses= (Nurse) q1.getSingleResult();
			return nurses;
		}
		
		public List<Nurse> SQLSearch(String name){
			
			Query q1 = Connect.em.createNativeQuery("SELECT * FROM Nurses WHERE name LIKE ?", Nurse.class);
			q1.setParameter(1, "%" + name + "%");
			List<Nurse> nurses = (List<Nurse>) q1.getResultList();
			return nurses;
			
		}
		
		public void SQLUpdate(Nurse oldnurse, Nurse newnurse){
			Connect.em.getTransaction().begin();
			newnurse.setName(oldnurse.getName());
//photo
			Connect.em.getTransaction().commit();
		}

		@Override
		public void SQLCreate() throws SQLException {
			// The tables must be created from JDBC	
			
		}

		@Override
		public List<Nurse> SQLSelect() {
			Query q1 = Connect.em.createNativeQuery("SELECT * FROM Nurses", Nurse.class);
			List<Nurse> nurses = (List<Nurse>) q1.getResultList();
			return nurses;
		}

		@Override
		public void SQLDrop() {
			// JPA cannot work with tables
			
		}


	
	
	

	}
	
	


package Blood.db.jpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Nurse;

public class JPANurse extends GeneralMethods {
	
	void SQLInsert(Nurse nurse){
		
		
		em.getTransaction().begin();
		em.persist(nurse);
		em.getTransaction().commit();
	}
	
	void SQLDelete(Nurse nurse){
		
		em.getTransaction().begin();
		em.remove(nurse);
		em.getTransaction().commit();
		
	}
	
	 Nurse SQLSearch1 (String name){
			Query q1 = em.createNativeQuery("SELECT * FROM Nurses WHERE name LIKE ?", Nurse.class);
			q1.setParameter(1, "%" + name + "%");
			Nurse nurses= (Nurse) q1.getSingleResult();
			return nurses;
		}
		
		public List<Nurse> SQLSearch(String name){
			
			Query q1 = em.createNativeQuery("SELECT * FROM Nurses WHERE name LIKE ?", Nurse.class);
			q1.setParameter(1, "%" + name + "%");
			List<Nurse> nurses = (List<Nurse>) q1.getResultList();
			return nurses;
			
		}
		
		public void SQLUpdate(Nurse oldnurse, Hospital newnurse){
			em.getTransaction().begin();
			newnurse.setName(oldnurse.getName());
//photo
			em.getTransaction().commit();
		}
	
	
	

	}
	
	


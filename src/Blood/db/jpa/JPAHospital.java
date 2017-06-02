package Blood.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Hospital;


public class JPAHospital implements FunctionsDB<Hospital>{
	
	public void SQLInsert(Hospital hospital){
		
		
		eManager.em.getTransaction().begin();
		eManager.em.persist(hospital);
		eManager.em.getTransaction().commit();
	}
	
	public void SQLDelete(Hospital hospital){
		
		eManager.em.getTransaction().begin();
		eManager.em.remove(hospital);
		eManager.em.getTransaction().commit();
		
	}
	
	
	 public static Hospital SQLSearch1(int id){
		Query q1 =eManager.em.createNativeQuery("SELECT * FROM Hospital WHERE id LIKE ?", Hospital.class);
		q1.setParameter(1, "%" + id + "%");
		Hospital hospital = (Hospital) q1.getSingleResult();
		return hospital;
	}
	
	public List<Hospital> SQLSearch(String name){
		
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Hospital WHERE name LIKE ?", Hospital.class);
		q1.setParameter(1, "%" + name + "%");
		List<Hospital> hospitals = (List<Hospital>) q1.getResultList();
		return hospitals;
		
	}
	
	public void SQLUpdate(Hospital oldHosp, Hospital newHosp){

		eManager.em.getTransaction().begin();
		oldHosp.setId(newHosp.getId());
		oldHosp.setName(newHosp.getName());
		oldHosp.setLocation(newHosp.getLocation());
		oldHosp.setRange(newHosp.getRange());
		eManager.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() {
		// The tables must be created from JDBC	
	}

	@Override
	public List<Hospital> SQLSelect() {
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM hospital", Hospital.class);
		List<Hospital> hospitals = (List<Hospital>) q1.getResultList();
		return hospitals;

	}

	@Override
	public void SQLUpdate(Hospital obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
}
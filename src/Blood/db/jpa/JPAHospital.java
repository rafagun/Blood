package Blood.db.jpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Hospital;
import Blood.db.pojos.Nurse;
public class JPAHospital {
	
	public void SQLInsert(Hospital hospital){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(hospital);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Hospital hospital){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(hospital);
		Connect.em.getTransaction().commit();
		
	}
	
	
	 Hospital SQLSearch1(String name){
		Query q1 =Connect.em.createNativeQuery("SELECT * FROM Hospital WHERE name LIKE ?", Hospital.class);
		q1.setParameter(1, "%" + name + "%");
		Hospital hospital = (Hospital) q1.getSingleResult();
		return hospital;
	}
	
	public List<Hospital> SQLSearch(String name){
		
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM hospital WHERE name LIKE ?", Hospital.class);
		q1.setParameter(1, "%" + name + "%");
		List<Hospital> hospitals = (List<Hospital>) q1.getResultList();
		return hospitals;
		
	}
	
	public void SQLUpdate(Hospital oldHosp, Hospital newHosp){
		Connect.em.getTransaction().begin();
		newHosp.setName(oldHosp.getName());
		newHosp.setLocation(oldHosp.getLocation());
		newHosp.setRange(oldHosp.getRange());
		Connect.em.getTransaction().commit();
	}
	




}
package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Patient;

public class JPAPatient {
	public void SQLInsert(Patient patient){
		
		
		Connect.em.getTransaction().begin();
		Connect.em.persist(patient);
		Connect.em.getTransaction().commit();
	}
	
	public void SQLDelete(Patient patient){
		
		Connect.em.getTransaction().begin();
		Connect.em.remove(patient);
		Connect.em.getTransaction().commit();
		
	}
	
	
	 Patient SQLSearch1(String name){
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		Patient patient = (Patient) q1.getSingleResult();
		return patient;
	}
	
	public List<Patient> SQLSearch(String name){
		
		Query q1 = Connect.em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		
	}
	
	public void SQLUpdate(Patient oldpat,Patient newpat){
		Connect.em.getTransaction().begin();
		newpat.setName(oldpat.getName());
		newpat.setBlood(oldpat.getBlood());
		newpat.setAge(oldpat.getAge());
		newpat.setGender(oldpat.getGender());
		newpat.setSmoker(oldpat.getSmoker());
		Connect.em.getTransaction().commit();
	}
	
}

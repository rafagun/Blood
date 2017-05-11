package Blood.db.jpa;

import java.util.List;

import javax.persistence.Query;

import Blood.db.pojos.Patient;

public class JPAPatient extends GeneralMethods {
	public void SQLInsert(Patient patient){
		
		
		em.getTransaction().begin();
		em.persist(patient);
		em.getTransaction().commit();
	}
	
	public void SQLDelete(Patient patient){
		
		em.getTransaction().begin();
		em.remove(patient);
		em.getTransaction().commit();
		
	}
	
	
	 Patient SQLSearch1(String name){
		Query q1 = em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		Patient patient = (Patient) q1.getSingleResult();
		return patient;
	}
	
	public List<Patient> SQLSearch(String name){
		
		Query q1 = em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		
	}
	
	public void SQLUpdate(Patient oldpat,Patient newpat){
		em.getTransaction().begin();
		newpat.setName(oldpat.getName());
		newpat.setBlood(oldpat.getBlood());
		newpat.setAge(oldpat.getAge());
		newpat.setGender(oldpat.getGender());
		newpat.setSmoker(oldpat.getSmoker());
		em.getTransaction().commit();
	}
	
}

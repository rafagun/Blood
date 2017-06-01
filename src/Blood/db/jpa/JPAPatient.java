package Blood.db.jpa;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.Query;

import Blood.db.jdbc.Connect;
import Blood.db.pojos.Patient;


public class JPAPatient implements FunctionsDB<Patient> {
	public void SQLInsert(Patient patient){
		
		
		eManager.em.getTransaction().begin();
		eManager.em.persist(patient);
		eManager.em.getTransaction().commit();
	}
	
	public void SQLDelete(Patient patient){
		
		eManager.em.getTransaction().begin();
		eManager.em.remove(patient);
		eManager.em.getTransaction().commit();
		
	}
	
	
	 Patient SQLSearch1(String name){
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		Patient patient = (Patient) q1.getSingleResult();
		return patient;
	}
	
	public List<Patient> SQLSearch(String name){
		
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
		
	}
	
	public void SQLUpdate(Patient oldpat,Patient newpat){
		eManager.em.getTransaction().begin();
		newpat.setName(oldpat.getName());
		newpat.setBlood(oldpat.getBlood());
		newpat.setAge(oldpat.getAge());
		newpat.setGender(oldpat.getGender());
		newpat.setSmoker(oldpat.getSmoker());
		eManager.em.getTransaction().commit();
	}

	@Override
	public void SQLCreate() throws SQLException {
		// The tables must be created from JDBC	
		
	}

	@Override
	public List<Patient> SQLSelect() {
		Query q1 = eManager.em.createNativeQuery("SELECT * FROM Patient", Patient.class);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		return patients;
	}

	@Override
	public void SQLUpdate(Patient obj) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
	}
	
}

package Blood.db.xml;

import java.io.File;

import javax.persistence.EntityTransaction;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Blood.db.jpa.eManager;
import Blood.db.pojos.Patient;


public class Xml2JavaPatient {
	
	public void xml2Java(Patient patient) throws JAXBException{
	// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the unmarshaller
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/External-Report.xml");
			Patient patient = (Patient) unmarshaller.unmarshal(file);
			// Start transaction
			EntityTransaction tx1 = eManager.em.getTransaction();

			// Start transaction
			tx1.begin();

			// Persist
			// We assume the authors are already in the system
			// If not, we must deal with them also
			eManager.em.persist(patient);
			
			// End transaction
			tx1.commit();
}
	}

package Blood.db.xml;


import java.io.File;
import java.util.*;


import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;


import Blood.db.jpa.eManager;
import Blood.db.pojos.Patient;



public class Java2XmlPatient {
	
	public void java2XML(Patient patient) throws JAXBException{
		// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			
			File file = new File("./xmls/Blood-Patient.xml");
			marshaller.marshal(patient, file);
			// Printout
			marshaller.marshal(patient, System.out);
	}
	
	
}

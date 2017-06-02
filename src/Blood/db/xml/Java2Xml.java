 package Blood.db.xml;
  
  
  import java.io.File;
  import java.util.*;
  
  
  import javax.persistence.Query;
  import javax.xml.bind.JAXBContext;
  import javax.xml.bind.JAXBException;
  import javax.xml.bind.Marshaller;
  
  
  import Blood.db.jpa.eManager;
  import Blood.db.pojos.Illnes;
  import Blood.db.pojos.Patient;
  import Blood.db.pojos.Symptoms;
  
  
  
  public class Java2Xml {
  	
  	public void java2XMLPatient(List<Patient> patient) throws JAXBException{
  		// Create the JAXBContext
  			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
  			// Get the marshaller
  			Marshaller marshaller = jaxbContext.createMarshaller();
  			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
  			
  			
  			File file = new File("./xmls/Blood Patient.xml");
  			marshaller.marshal(patient, file);
  	}
  	public void java2XMLIllnes(List<Illnes> illnes) throws JAXBException{
  		// Create the JAXBContext
  			JAXBContext jaxbContext = JAXBContext.newInstance(Illnes.class);
  			// Get the marshaller
  			Marshaller marshaller = jaxbContext.createMarshaller();
  			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
  			
  			File file = new File("./xmls/Blood Illnes.xml");
  			marshaller.marshal(illnes, file);
  	}
  	public void java2XMLSymptoms(List<Symptoms> symptom) throws JAXBException{
  		// Create the JAXBContext
  			JAXBContext jaxbContext = JAXBContext.newInstance(Symptoms.class);
  			// Get the marshaller
  			Marshaller marshaller = jaxbContext.createMarshaller();
  			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
  			
  			
  			File file = new File("./xmls/Blood Symptoms.xml");
  			marshaller.marshal(symptom, file);
  	}
  	
  }
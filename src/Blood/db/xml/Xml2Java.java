 package Blood.db.xml;
  
  import java.io.File;
  import java.util.List;
  
  import javax.persistence.EntityTransaction;
  import javax.xml.bind.JAXBContext;
  import javax.xml.bind.JAXBException;
  import javax.xml.bind.Unmarshaller;
  
  import Blood.db.jpa.eManager;
  import Blood.db.pojos.Illnes;
  import Blood.db.pojos.Patient;
  import Blood.db.pojos.Symptoms;
  
  
  public class Xml2Java {
  	
  	public List<Patient> xml2JavaPatient() throws JAXBException{
  	// Create the JAXBContext
  			JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
  			// Get the unmarshaller
  			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
  
  			// Use the Unmarshaller to unmarshal the XML document from a file
  			File file = new File("./xmls/Patient.xml");
  			List<Patient> patient2 = ((List<Patient>) unmarshaller.unmarshal(file));
  			return patient2;
  			
  	}
  	public List<Illnes> xml2JavaIllnes() throws JAXBException{
  		// Create the JAXBContext
  				JAXBContext jaxbContext = JAXBContext.newInstance(Illnes.class);
  				// Get the unmarshaller
  				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
  
  				// Use the Unmarshaller to unmarshal the XML document from a file
  				File file = new File("./xmls/Illnes.xml");
  				List<Illnes> illnes = ((List<Illnes>) unmarshaller.unmarshal(file));
  				return illnes;
  				
  	}
  	public List<Symptoms> xml2JavaSymptoms() throws JAXBException{
  		// Create the JAXBContext
  				JAXBContext jaxbContext = JAXBContext.newInstance(Symptoms.class);
  				// Get the unmarshaller
  				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
  
  				// Use the Unmarshaller to unmarshal the XML document from a file
  				File file = new File("./xmls/Symptoms.xml");
  				List<Symptoms> symp = ((List<Symptoms>) unmarshaller.unmarshal(file));
  				return symp;
  				
  	}
  	}
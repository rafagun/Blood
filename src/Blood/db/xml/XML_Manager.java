package Blood.db.xml;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Blood.db.pojos.Illnes;
import Blood.db.pojos.ListofSymptoms;
import Blood.db.pojos.Patient;
import Blood.db.pojos.Symptoms;

public class XML_Manager {
	
	public void Java2XmlListofSymptoms(List<Symptoms> symptomsList) throws JAXBException{
		
		ListofSymptoms listSymptoms = new ListofSymptoms();
		listSymptoms.setListofSymptoms(symptomsList);
		
		JAXBContext jaxbContext = JAXBContext.newInstance(ListofSymptoms.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
					
		File file = new File("./xmls/ListofSymptoms.xml");
		marshaller.marshal(listSymptoms, file);
		marshaller.marshal(listSymptoms, System.out);
	}
	
	public ListofSymptoms Xml2JavaListofSymptoms() throws JAXBException{
		Unmarshaller unmarshaller;
		ListofSymptoms symptomsList = new ListofSymptoms();
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(Symptoms.class);
			unmarshaller = jaxbContext.createUnmarshaller();

			// Use the Unmarshaller to unmarshal the XML document from a file
			File file = new File("./xmls/ListofSymptoms.xml");
			symptomsList.setListofSymptoms((List<Symptoms>) unmarshaller.unmarshal(file));
		}catch(Exception e){
			e.printStackTrace();
		}
		return symptomsList;
	}
	
public void Java2XmlPatient(Patient patient) throws JAXBException{
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
					
		File file = new File("./xmls/Patient.xml");
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);
}

public void Xml2JavaPatient() throws JAXBException{
		
	JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
	Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

	// Use the Unmarshaller to unmarshal the XML document from a file
	File file = new File("./xmls/Patient.xml");
	Patient patient = (Patient) unmarshaller.unmarshal(file);
	System.out.println(patient);
	
}

public void Java2XmlIllnes(Illnes illnes) throws JAXBException{
	
	JAXBContext jaxbContext = JAXBContext.newInstance(Illnes.class);
	Marshaller marshaller = jaxbContext.createMarshaller();
	marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
				
	File file = new File("./xmls/Illnes.xml");
	marshaller.marshal(illnes, file);
	marshaller.marshal(illnes, System.out);
}

public void Xml2JavaIllnes() throws JAXBException{
	
JAXBContext jaxbContext = JAXBContext.newInstance(Illnes.class);
Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

// Use the Unmarshaller to unmarshal the XML document from a file
File file = new File("./xmls/Illnes.xml");
Illnes illnes = (Illnes) unmarshaller.unmarshal(file);
System.out.println(illnes);

}
	
	

}

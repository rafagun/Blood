package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ListofSymptoms")

public class ListofSymptoms implements Serializable {
	
	@XmlElement(name="Symptom")
	@XmlElementWrapper(name ="Symptoms")
	private List<Symptoms> symptoms;
	
	public ListofSymptoms (List<Symptoms> symptoms){
		super();
		this.symptoms = symptoms;
	}
	
	public ListofSymptoms(){
		super();
		this.symptoms = new ArrayList<Symptoms>();
	}
	
	public List<Symptoms> getListofSymptoms(){
		return symptoms;
	}
	public void setListofSymptoms(List<Symptoms> symptoms){
		this.symptoms = symptoms;
	}
	

}

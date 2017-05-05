package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Patient implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -136657724985479318L;
private Integer id;
private String name;
private Integer age;
private String blood;
private Boolean smoker;
private String gender;
private List<Nurse> nurses;
private List<Symptoms> symptoms;
private List<Cells> cells;
private List<Molecules> molecules;
private List<Illnes> illness;

public Patient() {
	super();
	this.symptoms = new ArrayList<>();
	// TODO Auto-generated constructor stub
	this.setCells(new ArrayList<Cells>());
	this.setMolecules(new ArrayList <Molecules>());
	this.setIllness(new ArrayList<Illnes>());
	this.setNurses(new ArrayList<Nurse>());
}

public Patient(Integer id, String name, Integer age, String blood, String gender, Boolean smoker) {
	super();
	this.id = id;
	this.name = name;
	this.age = age;
	this.blood = blood;
	this.smoker = smoker;
	this.gender = gender;
	this.nurses = new ArrayList<Nurse>();
	this.symptoms = new ArrayList<Symptoms>();
	this.cells = new ArrayList<Cells>();
	this.molecules = new ArrayList<Molecules>();
	this.illness = new ArrayList<Illnes>();
}

public Patient(String name, Integer age, String blood, String gender, Boolean smoker) {
	this.name = name;
	this.age = age;
	this.blood = blood;
	this.smoker = smoker;
	this.gender= gender;
	this.symptoms = new ArrayList<Symptoms>();
	this.cells = new ArrayList<Cells>();
	this.molecules = new ArrayList<Molecules>();
	this.nurses= new ArrayList<Nurse>();
	this.illness=new ArrayList<Illnes>();
}


@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Patient other = (Patient) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	return true;
}

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
	this.age = age;
}
public String getBlood() {
	return blood;
}
public void setBlood(String blood) {
	this.blood = blood;
}
public Boolean getSmoker() {
	return smoker;
}
public void setSmoker(Boolean smoker1) {
	this.smoker = smoker1;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}

public List<Symptoms> getSymptoms() {
	return symptoms;
}

public void setSymptoms(List<Symptoms> symptoms) {
	this.symptoms = symptoms;
}

public List<Cells> getCells() {
	return cells;
}

public void setCells(List<Cells> cells) {
	this.cells = cells;
}

public List<Molecules> getMolecules() {
	return molecules;
}

public void setMolecules(List<Molecules> molecules) {
	this.molecules = molecules;
}

public List<Nurse> getNurses() {
	return nurses;
}
public void setNurses(List<Nurse> nurses) {
	this.nurses = nurses;
}
public List<Illnes> getIllness() {
	return illness;
}
public void setIllness(List<Illnes> illness) {
	this.illness = illness;
}


}

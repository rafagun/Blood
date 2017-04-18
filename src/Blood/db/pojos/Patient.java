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
private List<Symptoms> symptoms;
private List<Cells> cells;
private List<Molecules> molecules;

public Patient() {
	super();
	this.symptoms = new ArrayList<>();
	// TODO Auto-generated constructor stub
	this.setCells(new ArrayList<Cells>());
	this.setMolecules(new ArrayList <Molecules>());
}

public Patient(String name, Integer age, String blood,Boolean smoker,String gender) {
	this.name = name;
	this.age = age;
	this.blood = blood;
	this.smoker = smoker;
	this.gender= gender;
	this.symptoms = new ArrayList<Symptoms>();
	this.cells = new ArrayList<Cells>();
	this.molecules = new ArrayList<Molecules>();
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


}

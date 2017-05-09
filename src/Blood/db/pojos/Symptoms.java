package Blood.db.pojos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
@Entity
@Table(name="Symptoms")

public class Symptoms implements Serializable{
private static final long serialVersionUID = 6952444966932416547L;
    @Id
    @GeneratedValue(generator="Symptoms")
    @TableGenerator(name="Cells", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Cells")
	private Integer id;
    private String type;
    private String severity;
	@ManyToMany
	@JoinTable(name="pats-symp",
	joinColumns={@JoinColumn(name="symptoms_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
    private List<Patient> patients;
    @ManyToMany(mappedBy="symptoms")
    private List<Illnes> illness;
    
    public Symptoms() {
    	super();
    	this.patients = new ArrayList<>();
    	this.illness = new ArrayList<>();
    	// TODO Auto-generated constructor stub
    }
    
    public Symptoms(Integer id, String type, String severity){
    	super();
    	this.id = id;
    	this.type = type;
    	this.severity=severity;
    	this.patients = new ArrayList<Patient>();
    	this.illness = new ArrayList<Illnes>();
    }
    
    public Symptoms(String type, String severity){
    	super();
    	this.type = type;
    	this.severity= severity;
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
	Symptoms other = (Symptoms) obj;
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
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public List<Patient> getPatients() {
	return patients;
}
public void setPatients(List<Patient> patients) {
	this.patients = patients;
}
public List<Illnes> getIllnes() {
	return illness;
}
public void setIllnes(List<Illnes> illness) {
	this.illness = illness;
}
public void addIllnes (Illnes illnes){
	if (!illness.contains(illnes)) {
		this.illness.add(illnes);
	}
}
public void removeIllnes (Illnes illnes){
	if (illness.contains(illnes)) {
		this.illness.remove(illnes);
	}
}
public void addPatient(Patient patient) {
	if (!patients.contains(patient)) {
		this.patients.add(patient);
	}
}

public void removePatient(Patient patient) {
	if (patients.contains(patient)) {
		this.patients.remove(patient);
	}
}
@Override
public String toString() {
	return "Symptoms [id=" + id + ", type=" + type + "]";
}

public String getSeverity() {
	// TODO Auto-generated method stub
	return severity;
}

}

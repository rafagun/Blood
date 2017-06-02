package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name="Molecules")
@XmlType(propOrder={"type","lowLevels","highLevels","patients","illness"})
public class Molecules implements Serializable {
private static final long serialVersionUID = -3290462529390006987L;
	@Id
	@GeneratedValue(generator="Molecules")
	@TableGenerator(name="Molecules", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Molecules")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlElement
	private int lowLevels;
	@XmlElement
	private int highLevels;
	@ManyToMany
	@JoinTable(name="pats-mol",
	joinColumns={@JoinColumn(name="molecules_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	@XmlElementWrapper(name="Patients")
	@XmlElement(name="Patient")
	private List<Patient> patients;
	@ManyToMany(mappedBy="molecules")
	@XmlElementWrapper(name="Illness")
	@XmlElement(name="Illnes")
	private List<Illnes> illness;
	
	public Molecules() {
		super();
		// TODO Auto-generated constructor stub
		this.setPatients(new ArrayList<Patient>());
		this.setIllnes(new ArrayList<Illnes>());
	}
	public Molecules(int id, String type, int highL, int lowL){
		this.id= id;
		this.type = type;
		this.lowLevels = highL;
		this.highLevels = lowL;
		this.patients = new ArrayList<Patient>();
		this.illness = new ArrayList<Illnes>();
	}
	public Molecules (String type , int high , int low){
		this.type=type;
		this.highLevels=high;
		this.lowLevels=low;
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
		Molecules other = (Molecules) obj;
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
	public int getLowLevels() {
		return lowLevels;
	}
	public void setLowLevels(int newLowLevel) {
		this.lowLevels = newLowLevel;
	}
	public int getHighLevels() {
		return highLevels;
	}
	public void setHighLevels(int newHighLevel) {
		this.highLevels = newHighLevel;
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

	public void setIllnes(List<Illnes> illnes) {
		this.illness = illnes;
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
	@Override
	public String toString() {
		return "Molecules [id=" + id + ", type=" + type + ", lowLevels=" + lowLevels + ", highLevels=" + highLevels
				+ "]";
	}
}

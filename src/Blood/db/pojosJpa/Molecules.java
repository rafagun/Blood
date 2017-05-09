package Blood.db.pojosJpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
@Entity
@Table(name="Molecules")
public class Molecules implements Serializable {
private static final long serialVersionUID = -3290462529390006987L;
	@Id
	@GeneratedValue(generator="Molecules")
	@TableGenerator(name="Cells", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Cells")
	private Integer id;
	private String type;
	private float lowLevels;
	private float highLevels;
	@ManyToMany
	@JoinTable(name="pats-mol",
	joinColumns={@JoinColumn(name="molecules_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	private List<Patient> patients;
	@ManyToMany(mappedBy="molecules")
	private List<Illnes> illness;
	
	public Molecules() {
		super();
		// TODO Auto-generated constructor stub
		this.setPatients(new ArrayList<Patient>());
		this.setIllnes(new ArrayList<Illnes>());
	}
	public Molecules(String type,float highL, float lowL){
		this.type = type;
		this.lowLevels = highL;
		this.highLevels = lowL;
		this.patients = new ArrayList<Patient>();
		this.illness = new ArrayList<Illnes>();
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
	public float getLowLevels() {
		return lowLevels;
	}
	public void setLowLevels(Integer lowLevels) {
		this.lowLevels = lowLevels;
	}
	public float getHighLevels() {
		return highLevels;
	}
	public void setHighLevels(Integer highLevels) {
		this.highLevels = highLevels;
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

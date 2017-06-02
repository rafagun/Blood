package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@Entity
@Table(name="Cells")
@XmlType(propOrder={"type","highL","lowL","patients","illnes"})
public class Cells implements Serializable {
	private static final long serialVersionUID = -1755945494914985765L;
	@Id
	@GeneratedValue(generator="Cells")
	@TableGenerator(name="Cells", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Cells")
	@XmlAttribute
	private String type;
	@XmlElement
	private float highL;
	@XmlElement
	private float lowL;
	@XmlAttribute
	private Integer id;
	@ManyToMany
	@JoinTable(name="pats-cells",
	joinColumns={@JoinColumn(name="cells_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	@XmlElementWrapper(name="Patients")
	@XmlElement(name="Patient")
	private List<Patient> patients;
	@ManyToMany(mappedBy="cells")
	@XmlElementWrapper(name="Illness")
	@XmlElement(name="Illnes")
	private List<Illnes> illness;

	public Cells() {
		super();
		// TODO Auto-generated constructor stub
		this.setIllnes(new ArrayList<Illnes>());
		this.setPatients(new ArrayList<Patient>());
	}
	
	public Cells(Integer id,String type, float highL, float lowL) {
		super();
		this.id = id;
		this.type = type;
		this.highL = highL;
		this.lowL = lowL;
		this.illness = new ArrayList<Illnes>();
		this.patients = new ArrayList<Patient>();
		
	}
	public Cells( String type, float highL, float lowL) {
		super();
		this.type = type;
		this.highL = highL;
		this.lowL = lowL;
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
		Cells other = (Cells) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getHighL() {
		return highL;
	}
	public void setHighL(float highL) {
		this.highL = highL;
	}
	public float getLowL() {
		return lowL;
	}
	public void setLowL(float lowL) {
		this.lowL = lowL;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public List<Illnes> getIllnes() {
		return illness;
	}
	public void setIllnes(List<Illnes> illness) {
		this.illness = illness;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
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
		return "Cells [type=" + type + ", highL=" + highL + ", lowL=" + lowL + ", id=" + id + "]";
	}
}

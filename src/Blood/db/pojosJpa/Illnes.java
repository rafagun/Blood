package Blood.db.pojosJpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="Illnes")

public class Illnes implements Serializable {
private static final long serialVersionUID = -7280949047692384198L;
	@Id
	@GeneratedValue(generator="Illnes")
	@TableGenerator(name="Cells", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Cells")
	private Integer id;
	private String name;
	private String type;
	private Boolean chronic;
	@ManyToMany
	@JoinTable(name="symp-ill",
	joinColumns={@JoinColumn(name="illnes_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="symptoms_id", referencedColumnName="id")})
	private List<Symptoms> symptoms;
	@ManyToMany
	@JoinTable(name="cells-ill",
	joinColumns={@JoinColumn(name="illnes_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="cells_id", referencedColumnName="id")})
	private List<Cells> cells;
	@ManyToMany
	@JoinTable(name="pats-ill",
	joinColumns={@JoinColumn(name="illnes_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	private List<Patient> patients;
	@ManyToMany
	@JoinTable(name="mol-ill",
	joinColumns={@JoinColumn(name="illnes_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="molecules_id", referencedColumnName="id")})
	private List<Molecules> molecules;

	
	public Illnes() {
		super();
		this.symptoms = new ArrayList<>();
		// TODO Auto-generated constructor stub
		this.setCells(new ArrayList<Cells>());
		this.setPatients(new ArrayList<Patient>());
		this.setMolecules(new ArrayList<Molecules>());
	}
	
	public Illnes(Integer id, String name, String type, Boolean chronic) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.chronic = chronic;
	}

	
	public Illnes(String name, String type, Boolean chronic) {
	
		this.name = name;
		this.type = type;
		this.chronic = chronic;
		this.symptoms = new ArrayList<Symptoms>();
		this.cells = new ArrayList<Cells>();
		this.patients = new ArrayList<Patient>();
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
		Illnes other = (Illnes) obj;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Boolean getChronic() {
		return chronic;
	}
	public void setChronic(Boolean chronic) {
		this.chronic = chronic;
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

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Molecules> getMolecules() {
		return molecules;
	}

	public void setMolecules(List<Molecules> molecules) {
		this.molecules = molecules;
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
	public void addSymptoms (Symptoms symptom){
		if (!symptoms.contains(symptom)) {
			this.symptoms.add(symptom);
		}
	}
	public void removeSymptom (Symptoms symptom){
		if (symptoms.contains(symptom)) {
			this.symptoms.remove(symptom);
		}
	}
	public void addMolecule(Molecules molecule) {
		if (!molecules.contains(molecule)) {
			this.molecules.add(molecule);
		}
	}

	public void removeMolecule(Molecules molecule) {
		if (molecules.contains(molecule)) {
			this.molecules.remove(molecule);
		}
	}
	public void addCell(Cells cell) {
		if (!cells.contains(cell)) {
			this.cells.add(cell);
		}
	}

	public void removeCell(Cells cell) {
		if (cells.contains(cell)) {
			this.cells.remove(cell);
		}
	}
	@Override
	public String toString() {
		return "Illnes [id=" + id + ", name=" + name + ", type=" + type + ", chronic=" + chronic + "]";
	}

}

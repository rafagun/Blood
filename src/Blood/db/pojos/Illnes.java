package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Illnes implements Serializable {
	
	
	private static final long serialVersionUID = -7280949047692384198L;
	
	private Integer id;
	private String name;
	private String type;
	private Boolean chronic;
	private List<Symptoms> symptoms;
	private List<Cells> cells;
	private List<Patient> patients;
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

	@Override
	public String toString() {
		return "Illnes: \n id=" + id + "\n name=" + name + "\n type=" + type + "\n chronic=" + chronic ;
	}
	
}

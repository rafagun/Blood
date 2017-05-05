package Blood.db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Molecules implements Serializable {

	
	private static final long serialVersionUID = -3290462529390006987L;
	
	private Integer id;
	private String type;
	private float lowLevels;
	private float highLevels;
	private List<Patient> patients;
	private List<Illnes> illnes;
	
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
		this.illnes = new ArrayList<Illnes>();
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
		return illnes;
	}

	public void setIllnes(List<Illnes> illnes) {
		this.illnes = illnes;
	}
	@Override
	public String toString() {
		return "Molecules: \nid=" + id + " \ntype=" + type + "\n lowLevels=" + lowLevels + "\n highLevels=" + highLevels;
	}
	
}

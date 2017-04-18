package Blood.db.pojos;

import java.io.Serializable;
import java.util.*;

public class Cells implements Serializable {

	private static final long serialVersionUID = -1755945494914985765L;
	private String type;
	private float highL;
	private float lowL;
	private Integer id;
	private List<Illnes> illnes;
	private List<Patient> patients;	

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
		this.illnes = new ArrayList<Illnes>();
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
		return illnes;
	}
	public void setIllnes(List<Illnes> illnes) {
		this.illnes = illnes;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Cells [type=" + type + ", highL=" + highL + ", lowL=" + lowL + ", id=" + id + "]";
	}
	
	
	
	
}

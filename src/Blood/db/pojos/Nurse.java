package Blood.db.pojos;

import java.io.Serializable;

public class Nurse implements Serializable {
	
	
	private static final long serialVersionUID = 1857194774423858547L;
	
	private Integer id;
	private String name;
	private byte[] photo;
	private Hospital hospital;
	
	
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Nurse(String name, byte[] photo) {
		this.name = name;
		this.photo = photo;
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
		Nurse other = (Nurse) obj;
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
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", hospital=" + hospital + "]";
	}
	
	
}

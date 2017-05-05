package Blood.db.pojos;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class Nurse implements Serializable {
	
	
	private static final long serialVersionUID = 1857194774423858547L;
	
	private Integer id;
	private String name;
	private byte[] photo;
	private Hospital hospital;
	private List<Patient> patients;
	
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
		this.patients= new ArrayList<>();
	}
	//modificar constructores porq falta lista de pacientes
	public Nurse (Integer id, String name, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
		this.patients= new ArrayList<Patient>();
	}
	public Nurse (String name, byte[] photo) {
		super();
		this.name = name;
		this.photo = photo;
		this.patients=new ArrayList<Patient>();
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
	public void setPhoto(String direction) throws IOException {
		File file = new File (direction);
		BufferedImage bufferedImage = ImageIO.read(file);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 this.photo=data.getData();
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Nurse: \nid=" + id + "\n name=" + name + "\n hospital=" + hospital;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	
	
}


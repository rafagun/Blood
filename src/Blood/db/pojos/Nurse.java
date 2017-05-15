package Blood.db.pojos;
import java.awt.image.BufferedImage;

import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.*;

import Blood.db.pojos.Hospital;
import Blood.db.pojos.Patient;

@Entity
@Table(name="Nurses")
@XmlRootElement(name="Nurse")
@XmlType(propOrder={"name","photo","hospital","patients"})
public class Nurse implements Serializable {
private static final long serialVersionUID = 1857194774423858547L;
	@Id
	@GeneratedValue(generator="Nurses")
	@TableGenerator(name="Nurses", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Nurses")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String name;
	@Basic(fetch=FetchType.LAZY)
	@Lob
	@XmlTransient
	private byte[] photo;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="hosp_id")
	@XmlTransient
	private Hospital hospital;
	@XmlElementWrapper(name="Patients")
	@XmlElement(name="Patient")
	@ManyToMany(mappedBy="nurses")
	private List<Patient> patients;
	private String fileName;
	//modificar constructores lista de pacientes
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
		this.patients = new ArrayList<>();
	}
	
	public Nurse(String name, byte[] photo, Hospital hospital, List<Patient> patients, String fileName) {
		super();
		this.name = name;
		this.photo = photo;
		this.hospital = hospital;
		this.patients = patients;
		this.fileName=fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public Nurse (Integer id, String name, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.photo = photo;
	}
	public Nurse (String name, byte[] photo) {
		super();
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
	public void setPhoto(String direction) {
		try{
			File file = new File (direction);
			BufferedImage bufferedImage = ImageIO.read(file);
			
			 // get DataBufferBytes from Raster
			
			 WritableRaster raster = bufferedImage.getRaster();
			 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

			 this.photo=data.getData();
			
		}
		catch (Exception ex){
			
		}
		
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
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
		return "Nurse [id=" + id + ", name=" + name + ", hospital=" + hospital + "]";
	}
}

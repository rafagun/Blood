package Blood.db.pojos;



import java.io.Serializable;

import java.sql.Date;

import java.time.LocalDate;

import java.util.ArrayList;

import java.util.List;



import javax.persistence.Entity;

import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import javax.persistence.JoinColumn;

import javax.persistence.JoinTable;

import javax.persistence.ManyToMany;

import javax.persistence.Table;

import javax.persistence.TableGenerator;

import javax.xml.bind.annotation.*;



import Blood.db.pojos.Cells;

import Blood.db.pojos.Illnes;

import Blood.db.pojos.Molecules;

import Blood.db.pojos.Nurse;

import Blood.db.pojos.Symptoms;

@Entity

@Table(name="Patient")

@XmlRootElement(name="Patient")

@XmlType(propOrder={"name","age","blood","smoker","gender","nurses","symptoms","cells","molecules","illnes"})

public class Patient implements Serializable {

	private static final long serialVersionUID = -136657724985479318L;

	

	@Id

	@GeneratedValue(generator="Patient")

	@TableGenerator(name="Patient", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="Patient")

	@XmlAttribute

	private Integer id;

	@XmlAttribute

	private String name;

	@XmlElement

	private LocalDate age;

	@XmlElement

	private String blood;

	@XmlElement

	private Boolean smoker;

	@XmlElement

	private String gender;

	@ManyToMany

	@JoinTable(name="nurs-pats",

	joinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")},

    inverseJoinColumns={@JoinColumn(name="nurse_id", referencedColumnName="id")})

	@XmlElementWrapper(name="Nurses")

	@XmlElement(name="Nurse")

	private List<Nurse> nurses;

	@ManyToMany(mappedBy="patients")

	@XmlElementWrapper(name="Symptoms")

	@XmlElement(name="Symptom")

	private List<Symptoms> symptoms;

	@ManyToMany(mappedBy="patients")

	@XmlElementWrapper(name="Cells")

	@XmlElement(name="Cell")

	private List<Cells> cells;

	@ManyToMany(mappedBy="patients")

	@XmlElementWrapper(name="Molecules")

	@XmlElement(name="Molecule")

	private List<Molecules> molecules;

	@ManyToMany(mappedBy="patients")

	@XmlElementWrapper(name="Illness")

	@XmlElement(name="Illnes")

	private List<Illnes> illness;



	public Patient() {

		super();

		this.symptoms = new ArrayList<>();

		// TODO Auto-generated constructor stub

		this.nurses= new ArrayList<>();

		this.illness= new ArrayList<>();

		this.setCells(new ArrayList<Cells>());

		this.setMolecules(new ArrayList <Molecules>());

	}

	//modificar constructores porque falta clase nurse

	public Patient(int id, String name, LocalDate age, String blood, String gender, Boolean smoker) {

		this.id=id;

		this.name = name;

		this.age = age;

		this.blood = blood;

		this.smoker = smoker;

		this.gender= gender;

		this.symptoms = new ArrayList<Symptoms>();

		this.cells = new ArrayList<Cells>();

		this.molecules = new ArrayList<Molecules>();

		this.nurses= new ArrayList<Nurse>();

		this.illness= new ArrayList<Illnes>();

	}

	public Patient (String name , LocalDate age , String blood , String gender, Boolean smoker){

		this.name=name;

		this.age=age;

		this.blood=blood;

		this.smoker=smoker;

		this.gender=gender;

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

		Patient other = (Patient) obj;

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

	public LocalDate getAge() {

		return age;

	}

	public void setAge(LocalDate age) {

		this.age = age;

	}

	public String getBlood() {

		return blood;

	}

	public void setBlood(String blood) {

		this.blood = blood;

	}

	public Boolean getSmoker() {

		return smoker;

	}

	public void setSmoker(Boolean smoker1) {

		this.smoker = smoker1;

	}

	public String getGender() {

		return gender;

	}

	public void setGender(String gender) {

		this.gender = gender;

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



	public List<Molecules> getMolecules() {

		return molecules;

	}



	public void setMolecules(List<Molecules> molecules) {

		this.molecules = molecules;

	}



	public List<Nurse> getNurses() {

		return nurses;

	}

	public void setNurses(List<Nurse> nurses) {

		this.nurses = nurses;

	}

	public List<Illnes> getIllness() {

		return illness;

	}

	public void setIllness(List<Illnes> illness) {

		this.illness = illness;

	}

	public void addNurse (Nurse nurse){

		if (!nurses.contains(nurse)) {

			this.nurses.add(nurse);

		}

	}

	public void removeNurse (Nurse nurse){

		if (nurses.contains(nurse)) {

			this.nurses.remove(nurse);

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

		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", blood=" + blood + ", gender=" + gender

				+ ", smoker=" + smoker + "]";

	}

	

}


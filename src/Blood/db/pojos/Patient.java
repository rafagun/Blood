package Blood.db.pojos;

import java.io.Serializable;

public class Patient implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = -136657724985479318L;
private Integer id;
private String name;
private Integer age;
private String blood;
private Boolean smoker;
private String gender;
public Patient() {
	super();
	// TODO Auto-generated constructor stub
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
public Integer getAge() {
	return age;
}
public void setAge(Integer age) {
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
public void setSmoker(Boolean smoker) {
	this.smoker = smoker;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}


}

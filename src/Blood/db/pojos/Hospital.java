package Blood.db.pojos;
import java.io.Serializable;

public class Hospital implements Serializable {
//hola

private static final long serialVersionUID = -8273180762878531017L;
private Integer id;
private String name;
private String location;
private Integer range;


public Hospital() {
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
	Hospital other = (Hospital) obj;
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
public String getLocation() {
	return location;
}
public void setLocation(String location) {
	this.location = location;
}
public Integer getRange() {
	return range;
}
public void setRange(Integer range) {
	this.range = range;
}


}

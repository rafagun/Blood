package Blood.db.pojos;

import java.io.Serializable;
import java.util.*;

public class Illnes implements Serializable {
	
	
	private static final long serialVersionUID = -7280949047692384198L;
	
	private Integer id;
	private String name;
	private String type;
	private Boolean chronic;
	private List<Cells> cells;
	
	public Illnes() {
		super();
		// TODO Auto-generated constructor stub
		this.cells=new ArrayList<Cells>();
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
}

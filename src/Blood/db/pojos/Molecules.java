package Blood.db.pojos;

import java.io.Serializable;

public class Molecules implements Serializable {

	
	private static final long serialVersionUID = -3290462529390006987L;
	
	private Integer id;
	private String type;
	private Integer lowLevels;
	private Integer highLevels;
	
	public Molecules() {
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
	public Integer getLowLevels() {
		return lowLevels;
	}
	public void setLowLevels(Integer lowLevels) {
		this.lowLevels = lowLevels;
	}
	public Integer getHighLevels() {
		return highLevels;
	}
	public void setHighLevels(Integer highLevels) {
		this.highLevels = highLevels;
	}
	
	
}

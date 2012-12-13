package hu.iboard.coandco.datamagic.model.county;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTY")
public class County implements Serializable{

	private static final long serialVersionUID = -427615682781602485L;
	
	private Integer countyId;
	private String name;
	
	@Id
	@Column(name = "COUNTYID", unique = true, nullable = false)
	public Integer getCountyId() {
		return countyId;
	}
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	
	@Column(name = "NAME", length = 30)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

package hu.iboard.coandco.datamagic.model.township;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TOWNSHIP")
public class TownShip implements Serializable{

	private static final long serialVersionUID = -427615682781602485L;
	
	private Integer townShipId;
	private String zipcode;
	private String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOWNSHIP_ID", unique = true, nullable = false)
	public Integer getTownShipId() {
		return townShipId;
	}
	public void setTownShipId(Integer townShipId) {
		this.townShipId = townShipId;
	}
	
	@Column(name = "ZIPCODE")
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	@Column(name = "NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

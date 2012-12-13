package hu.iboard.coandco.datamagic.model.teruletcs;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TERULETCS")
public class Teruletcs implements Serializable {

	private static final long serialVersionUID = -144392617693872975L;
	
	private Integer id;
	private String x;
	private Integer terulet;
	private Integer irsz;
	private String varos;
	private Date moddatum;
	private Integer modkod;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "X")
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	@Column(name = "TERULET")
	public Integer getTerulet() {
		return terulet;
	}
	public void setTerulet(Integer terulet) {
		this.terulet = terulet;
	}
	
	@Column(name = "IRSZ")
	public Integer getIrsz() {
		return irsz;
	}
	public void setIrsz(Integer irsz) {
		this.irsz = irsz;
	}
	
	@Column(name = "VAROS")
	public String getVaros() {
		return varos;
	}
	public void setVaros(String varos) {
		this.varos = varos;
	}
	
	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}
	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}
	
	@Column(name = "MODKOD")
	public Integer getModkod() {
		return modkod;
	}
	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}
	
	

}

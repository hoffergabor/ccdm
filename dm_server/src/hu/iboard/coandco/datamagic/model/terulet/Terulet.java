package hu.iboard.coandco.datamagic.model.terulet;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TERULET")
public class Terulet implements Serializable{

	private static final long serialVersionUID = -9195567315518039135L;
	
	private Integer id;
	private String x;
	private String nev;
	private Date moddatum;
	private Integer modkod;
	private Integer ertekesito;
	private Integer teruletkod;
	
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
	
	@Column(name = "NEV")
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
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
	
	@Column(name = "ERTEKESITO")
	public Integer getErtekesito() {
		return ertekesito;
	}
	public void setErtekesito(Integer ertekesito) {
		this.ertekesito = ertekesito;
	}
	
	@Column(name = "TERULETKOD")
	public Integer getTeruletkod() {
		return teruletkod;
	}
	public void setTeruletkod(Integer teruletkod) {
		this.teruletkod = teruletkod;
	}


}

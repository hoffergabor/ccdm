package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARUTASITAS")
public class Arutasitas implements Serializable{

	private static final long serialVersionUID = -1785473696549800890L;
	
	private Integer id;
	private String x;
	private String nev;
	private Integer aru;
	private String ceg;
	private Boolean arres;
	private Integer akcios;
	private Date moddatum;
	private Integer modkod;
	private Boolean mlap;
	private String smegj;
	private Date kezdes;
	private Date zaras;
	
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
	
	@Column(name = "ARU")
	public Integer getAru() {
		return aru;
	}
	public void setAru(Integer aru) {
		this.aru = aru;
	}
	
	@Column(name = "CEG")
	public String getCeg() {
		return ceg;
	}
	public void setCeg(String ceg) {
		this.ceg = ceg;
	}
	
	@Column(name = "ARRES")
	public Boolean getArres() {
		return arres;
	}
	public void setArres(Boolean arres) {
		this.arres = arres;
	}
	
	@Column(name = "AKCIOS")
	public Integer getAkcios() {
		return akcios;
	}
	public void setAkcios(Integer akcios) {
		this.akcios = akcios;
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
	
	@Column(name = "MLAP")
	public Boolean getMlap() {
		return mlap;
	}
	public void setMlap(Boolean mlap) {
		this.mlap = mlap;
	}
	
	@Column(name = "SMEGJ")
	public String getSmegj() {
		return smegj;
	}
	public void setSmegj(String smegj) {
		this.smegj = smegj;
	}
	
	@Column(name = "KEZDES")
	public Date getKezdes() {
		return kezdes;
	}
	public void setKezdes(Date kezdes) {
		this.kezdes = kezdes;
	}
	
	@Column(name = "ZARAS")
	public Date getZaras() {
		return zaras;
	}
	public void setZaras(Date zaras) {
		this.zaras = zaras;
	}

	
	
}

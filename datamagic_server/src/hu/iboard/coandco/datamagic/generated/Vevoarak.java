package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "VEVOARAK")
public class Vevoarak implements Serializable {

	private static final long serialVersionUID = -5793842487409488077L;
	
	private Integer rekid;
	private Integer akod;
	private Integer vevokod;
	private BigDecimal egysegar;
	private String devnem;
	private Date toldatum;
	private Date igdatum;
	private Date insdatum;
	private Integer inskod;
	private Date moddatum;
	private Integer modkod;
	private Integer kedvezmeny;
	private BigDecimal szazalek;
	
	@Id
	@Column(name = "REKID", unique = true, nullable = false)
	public Integer getRekid() {
		return rekid;
	}
	public void setRekid(Integer rekid) {
		this.rekid = rekid;
	}
	
	@Column(name = "AKOD")
	public Integer getAkod() {
		return akod;
	}
	public void setAkod(Integer akod) {
		this.akod = akod;
	}
	
	@Column(name = "VEVOKOD")
	public Integer getVevokod() {
		return vevokod;
	}
	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}
	
	@Column(name = "EGYSEGAR")
	public BigDecimal getEgysegar() {
		return egysegar;
	}
	public void setEgysegar(BigDecimal egysegar) {
		this.egysegar = egysegar;
	}
	
	@Column(name = "DEVNEM")
	public String getDevnem() {
		return devnem;
	}
	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}
	
	@Column(name = "TOLDATUM")
	public Date getToldatum() {
		return toldatum;
	}
	public void setToldatum(Date toldatum) {
		this.toldatum = toldatum;
	}
	
	@Column(name = "IGDATUM")
	public Date getIgdatum() {
		return igdatum;
	}
	public void setIgdatum(Date igdatum) {
		this.igdatum = igdatum;
	}
	
	@Column(name = "INSDATUM")
	public Date getInsdatum() {
		return insdatum;
	}
	public void setInsdatum(Date insdatum) {
		this.insdatum = insdatum;
	}
	
	@Column(name = "INSKOD")
	public Integer getInskod() {
		return inskod;
	}
	public void setInskod(Integer inskod) {
		this.inskod = inskod;
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
	
	@Column(name = "KEDVEZMENY")
	public Integer getKedvezmeny() {
		return kedvezmeny;
	}
	public void setKedvezmeny(Integer kedvezmeny) {
		this.kedvezmeny = kedvezmeny;
	}
	
	@Column(name = "SZAZALEK")
	public BigDecimal getSzazalek() {
		return szazalek;
	}
	public void setSzazalek(BigDecimal szazalek) {
		this.szazalek = szazalek;
	}
	
	
	
	
	

}

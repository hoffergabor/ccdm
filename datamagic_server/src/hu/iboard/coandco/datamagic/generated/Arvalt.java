package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ARVALT")
public class Arvalt implements Serializable {

	private static final long serialVersionUID = -5122843972290690194L;
	
	private Integer rekid;
	private Boolean aktivalt;
	private Integer arukod;
	private Integer arnev;
	private Integer arutasitas;
	private BigDecimal beszar;
	private String ceg;
	private Boolean eltero;
	private Date enddatum;
	private Boolean hasznal;
	private Date moddatum;
	private Integer modkod;
	private Integer parvalt;
	private BigDecimal rbeszar;
	private BigDecimal rertek;
	private Date setdatum;
	private String setear;
	private BigDecimal setertek;
	private BigDecimal setertek2;
	private BigDecimal szorzo;
	private BigDecimal ubeszar;
	
	
	@Id
	@Column(name = "REKID", unique = true, nullable = false)
	public Integer getRekid() {
		return rekid;
	}
	public void setRekid(Integer rekid) {
		this.rekid = rekid;
	}
	
	@Column(name = "AKTIVALT")
	public Boolean getAktivalt() {
		return aktivalt;
	}
	public void setAktivalt(Boolean aktivalt) {
		this.aktivalt = aktivalt;
	}
	
	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return arukod;
	}
	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}
	
	@Column(name = "ARNEV")
	public Integer getArnev() {
		return arnev;
	}
	public void setArnev(Integer arnev) {
		this.arnev = arnev;
	}
	
	@Column(name = "ARUTASITAS")
	public Integer getArutasitas() {
		return arutasitas;
	}
	public void setArutasitas(Integer arutasitas) {
		this.arutasitas = arutasitas;
	}
	
	@Column(name = "BESZAR")
	public BigDecimal getBeszar() {
		return beszar;
	}
	public void setBeszar(BigDecimal beszar) {
		this.beszar = beszar;
	}
	
	@Column(name = "CEG")
	public String getCeg() {
		return ceg;
	}
	public void setCeg(String ceg) {
		this.ceg = ceg;
	}
	
	@Column(name = "ELTERO")
	public Boolean getEltero() {
		return eltero;
	}
	public void setEltero(Boolean eltero) {
		this.eltero = eltero;
	}
	
	@Column(name = "ENDDATUM")
	public Date getEnddatum() {
		return enddatum;
	}
	public void setEnddatum(Date enddatum) {
		this.enddatum = enddatum;
	}
	
	@Column(name = "HASZNAL")
	public Boolean getHasznal() {
		return hasznal;
	}
	public void setHasznal(Boolean hasznal) {
		this.hasznal = hasznal;
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
	
	@Column(name = "PARVALT")
	public Integer getParvalt() {
		return parvalt;
	}
	public void setParvalt(Integer parvalt) {
		this.parvalt = parvalt;
	}
	
	@Column(name = "RBESZAR")
	public BigDecimal getRbeszar() {
		return rbeszar;
	}
	public void setRbeszar(BigDecimal rbeszar) {
		this.rbeszar = rbeszar;
	}
	
	@Column(name = "RERTEK")
	public BigDecimal getRertek() {
		return rertek;
	}
	public void setRertek(BigDecimal rertek) {
		this.rertek = rertek;
	}
	
	@Column(name = "SETDATUM")
	public Date getSetdatum() {
		return setdatum;
	}
	public void setSetdatum(Date setdatum) {
		this.setdatum = setdatum;
	}
	
	@Column(name = "SETEAR")
	public String getSetear() {
		return setear;
	}
	public void setSetear(String setear) {
		this.setear = setear;
	}
	
	@Column(name = "SETERTEK")
	public BigDecimal getSetertek() {
		return setertek;
	}
	public void setSetertek(BigDecimal setertek) {
		this.setertek = setertek;
	}
	
	@Column(name = "SETERTEK2")
	public BigDecimal getSetertek2() {
		return setertek2;
	}
	public void setSetertek2(BigDecimal setertek2) {
		this.setertek2 = setertek2;
	}
	
	@Column(name = "SZORZO")
	public BigDecimal getSzorzo() {
		return szorzo;
	}
	public void setSzorzo(BigDecimal szorzo) {
		this.szorzo = szorzo;
	}
	
	@Column(name = "UBESZAR")
	public BigDecimal getUbeszar() {
		return ubeszar;
	}
	public void setUbeszar(BigDecimal ubeszar) {
		this.ubeszar = ubeszar;
	}
	

}

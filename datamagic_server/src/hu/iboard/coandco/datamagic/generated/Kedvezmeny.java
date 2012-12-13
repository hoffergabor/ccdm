package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "KEDVEZMENY")
public class Kedvezmeny implements Serializable{

	private static final long serialVersionUID = -6527471605505112589L;
	
	private Integer id;
	private String x;
	private Integer tipus;
	private String nev;
	private Boolean aktiv;
	private Date toldatum;
	private Date igdatum;
	private Integer terulet;
	private BigDecimal menny;
	private String meegys;
	private BigDecimal szazalek;
	private BigDecimal ertek;
	private BigDecimal mennykorl;
	private BigDecimal gratmenny;
	private String gratmeeegys;
	private String csomagnev;
	private Integer szolg; 
	private Boolean vengedely;
	private Boolean vcsengedely;
	private Boolean cengedely;
	private Boolean ccsengedely;
	private Boolean rengedely;
	private BigDecimal hasznalt;
	private Integer modkod;
	private Date moddatum;
	private BigDecimal minertek;
	private String ceg;
	private Boolean mlap;
	private Boolean gratarres;
	private Boolean vcsengedely2;
	private Integer szall;
	
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
	
	@Column(name = "TIPUS")
	public Integer getTipus() {
		return tipus;
	}
	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}
	
	@Column(name = "NEV")
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	@Column(name = "AKTIV")
	public Boolean getAktiv() {
		return aktiv;
	}
	public void setAktiv(Boolean aktiv) {
		this.aktiv = aktiv;
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
	
	@Column(name = "TERULET")
	public Integer getTerulet() {
		return terulet;
	}
	public void setTerulet(Integer terulet) {
		this.terulet = terulet;
	}
	
	@Column(name = "MENNY")
	public BigDecimal getMenny() {
		return menny;
	}
	public void setMenny(BigDecimal menny) {
		this.menny = menny;
	}
	
	@Column(name = "MEEGYS")
	public String getMeegys() {
		return meegys;
	}
	public void setMeegys(String meegys) {
		this.meegys = meegys;
	}
	
	@Column(name = "SZAZALEK")
	public BigDecimal getSzazalek() {
		return szazalek;
	}
	public void setSzazalek(BigDecimal szazalek) {
		this.szazalek = szazalek;
	}
	
	@Column(name = "ERTEK")
	public BigDecimal getErtek() {
		return ertek;
	}
	public void setErtek(BigDecimal ertek) {
		this.ertek = ertek;
	}
	
	@Column(name = "MENNYKORL")
	public BigDecimal getMennykorl() {
		return mennykorl;
	}
	public void setMennykorl(BigDecimal mennykorl) {
		this.mennykorl = mennykorl;
	}
	
	@Column(name = "GRATMENNY")
	public BigDecimal getGratmenny() {
		return gratmenny;
	}
	public void setGratmenny(BigDecimal gratmenny) {
		this.gratmenny = gratmenny;
	}
	
	@Column(name = "GRATMEEGYS")
	public String getGratmeeegys() {
		return gratmeeegys;
	}
	public void setGratmeeegys(String gratmeeegys) {
		this.gratmeeegys = gratmeeegys;
	}
	
	@Column(name = "CSOMAGNEV")
	public String getCsomagnev() {
		return csomagnev;
	}
	public void setCsomagnev(String csomagnev) {
		this.csomagnev = csomagnev;
	}
	
	@Column(name = "SZOLG")
	public Integer getSzolg() {
		return szolg;
	}
	public void setSzolg(Integer szolg) {
		this.szolg = szolg;
	}
	
	@Column(name = "VENGEDELY")
	public Boolean getVengedely() {
		return vengedely;
	}
	public void setVengedely(Boolean vengedely) {
		this.vengedely = vengedely;
	}
	
	@Column(name = "VCSENGEDELY")
	public Boolean getVcsengedely() {
		return vcsengedely;
	}
	public void setVcsengedely(Boolean vcsengedely) {
		this.vcsengedely = vcsengedely;
	}
	
	@Column(name = "CENGEDELY")
	public Boolean getCengedely() {
		return cengedely;
	}
	public void setCengedely(Boolean cengedely) {
		this.cengedely = cengedely;
	}
	
	@Column(name = "CCSENGEDELY")
	public Boolean getCcsengedely() {
		return ccsengedely;
	}
	public void setCcsengedely(Boolean ccsengedely) {
		this.ccsengedely = ccsengedely;
	}
	
	@Column(name = "RENGEDELY")
	public Boolean getRengedely() {
		return rengedely;
	}
	public void setRengedely(Boolean rengedely) {
		this.rengedely = rengedely;
	}
	
	@Column(name = "HASZNALT")
	public BigDecimal getHasznalt() {
		return hasznalt;
	}
	public void setHasznalt(BigDecimal hasznalt) {
		this.hasznalt = hasznalt;
	}
	
	@Column(name = "MODKOD")
	public Integer getModkod() {
		return modkod;
	}
	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}
	
	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}
	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}
	
	@Column(name = "MINERTEK")
	public BigDecimal getMinertek() {
		return minertek;
	}
	public void setMinertek(BigDecimal minertek) {
		this.minertek = minertek;
	}
	
	@Column(name = "CEG")
	public String getCeg() {
		return ceg;
	}
	public void setCeg(String ceg) {
		this.ceg = ceg;
	}
	
	@Column(name = "MLAP")
	public Boolean getMlap() {
		return mlap;
	}
	public void setMlap(Boolean mlap) {
		this.mlap = mlap;
	}
	
	@Column(name = "GRATARRES")
	public Boolean getGratarres() {
		return gratarres;
	}
	public void setGratarres(Boolean gratarres) {
		this.gratarres = gratarres;
	}
	
	@Column(name = "VCSENGEDELY2")
	public Boolean getVcsengedely2() {
		return vcsengedely2;
	}
	public void setVcsengedely2(Boolean vcsengedely2) {
		this.vcsengedely2 = vcsengedely2;
	}
	
	@Column(name = "SZALL")
	public Integer getSzall() {
		return szall;
	}
	public void setSzall(Integer szall) {
		this.szall = szall;
	}
	
	
	

}

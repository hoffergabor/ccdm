package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MUNKSZAM2")
public class Munkszam2 implements Serializable{

	private static final long serialVersionUID = 729087625914149167L;
	private Integer id;
	private String x;
	private String munkszam;
	private String magy;
	private Integer type;
	private Integer lakas;
	private Integer projekt;
	private String prefix;
	private Integer  eszkevad;
	private Integer eszksind;
	private Integer kikuldet;
	private Integer koltsegnem;
	private Integer fuvar;
	private Integer modkod;
	private Date moddatum;
	private Integer felelos;
	private Boolean isKozvetett;
	private Integer kategoria;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 10)
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
	
	@Column(name = "MUNKSZAM")
	public String getMunkszam() {
		return munkszam;
	}
	public void setMunkszam(String munkszam) {
		this.munkszam = munkszam;
	}
	
	@Column(name = "MAGY")
	public String getMagy() {
		return magy;
	}
	public void setMagy(String magy) {
		this.magy = magy;
	}
	
	@Column(name = "TYPE")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	@Column(name = "LAKAS")
	public Integer getLakas() {
		return lakas;
	}
	public void setLakas(Integer lakas) {
		this.lakas = lakas;
	}
	
	@Column(name = "PROJEKT")
	public Integer getProjekt() {
		return projekt;
	}
	public void setProjekt(Integer projekt) {
		this.projekt = projekt;
	}
	
	@Column(name = "PREFIX")
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	@Column(name = "ESZKEVAD")
	public Integer getEszkevad() {
		return eszkevad;
	}
	public void setEszkevad(Integer eszkevad) {
		this.eszkevad = eszkevad;
	}
	
	@Column(name = "ESZKSIND")
	public Integer getEszksind() {
		return eszksind;
	}
	public void setEszksind(Integer eszksind) {
		this.eszksind = eszksind;
	}
	
	@Column(name = "KIKULDET")
	public Integer getKikuldet() {
		return kikuldet;
	}
	public void setKikuldet(Integer kikuldet) {
		this.kikuldet = kikuldet;
	}
	
	@Column(name = "KOLTSEGNEM")
	public Integer getKoltsegnem() {
		return koltsegnem;
	}
	public void setKoltsegnem(Integer koltsegnem) {
		this.koltsegnem = koltsegnem;
	}
	
	@Column(name = "FUVAR")
	public Integer getFuvar() {
		return fuvar;
	}
	public void setFuvar(Integer fuvar) {
		this.fuvar = fuvar;
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
	
	@Column(name = "FELELOS")
	public Integer getFelelos() {
		return felelos;
	}
	public void setFelelos(Integer felelos) {
		this.felelos = felelos;
	}
	
	@Column(name = "ISKOZVETETT")
	public Boolean getIsKozvetett() {
		return isKozvetett;
	}
	public void setIsKozvetett(Boolean isKozvetett) {
		this.isKozvetett = isKozvetett;
	}
	
	@Column(name = "KATEGORIA")
	public Integer getKategoria() {
		return kategoria;
	}
	public void setKategoria(Integer kategoria) {
		this.kategoria = kategoria;
	}
	
	
	

}

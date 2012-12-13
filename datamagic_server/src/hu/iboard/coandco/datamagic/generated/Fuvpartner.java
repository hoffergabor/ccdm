package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUVPARTNER")
public class Fuvpartner implements Serializable{

	private static final long serialVersionUID = 7537986353461997572L;
	
	private Integer id;
	private Integer partner;
	private Boolean pshetfo;
	private Boolean pskedd;
	private Boolean psszerda;
	private Boolean pscsutortok;
	private Boolean pspentek;
	private Boolean psszombat;
	private Boolean psvasarnap;
	private Boolean plhetfo;
	private Boolean plkedd;
	private Boolean plszerda;
	private Boolean plcsutortok;
	private Boolean plpentek;
	private Boolean plszombat;
	private Boolean plvasarnap;
	private Boolean hivastker;
	private Integer modkod;
	private Date moddatum;
	private Boolean hivasPsH;
	private Boolean hivasPsK;
	private Boolean hivasPsSze;
	private Boolean hivasPsCs;
	private Boolean hivasPsP;
	private Boolean hivasPsSzo;
	private Boolean hivasPsV;
	private Boolean hivasPlH;
	private Boolean hivasPlK;
	private Boolean hivasPlSze;
	private Boolean hivasPlCs;
	private Boolean hivasPlP;
	private Boolean hivasPlSzo;
	private Boolean hivasPlV;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PARTNER")
	public Integer getPartner() {
		return partner;
	}
	public void setPartner(Integer partner) {
		this.partner = partner;
	}
	
	@Column(name = "PSHETFO")
	public Boolean getPshetfo() {
		return pshetfo;
	}
	public void setPshetfo(Boolean pshetfo) {
		this.pshetfo = pshetfo;
	}
	
	@Column(name = "PSKEDD")
	public Boolean getPskedd() {
		return pskedd;
	}
	public void setPskedd(Boolean pskedd) {
		this.pskedd = pskedd;
	}
	
	@Column(name = "PSSZERDA")
	public Boolean getPsszerda() {
		return psszerda;
	}
	public void setPsszerda(Boolean psszerda) {
		this.psszerda = psszerda;
	}
	
	@Column(name = "PSCSUTORTOK")
	public Boolean getPscsutortok() {
		return pscsutortok;
	}
	public void setPscsutortok(Boolean pscsutortok) {
		this.pscsutortok = pscsutortok;
	}
	
	@Column(name = "PSPENTEK")
	public Boolean getPspentek() {
		return pspentek;
	}
	public void setPspentek(Boolean pspentek) {
		this.pspentek = pspentek;
	}
	
	@Column(name = "PSSZOMBAT")
	public Boolean getPsszombat() {
		return psszombat;
	}
	public void setPsszombat(Boolean psszombat) {
		this.psszombat = psszombat;
	}
	
	@Column(name = "PSVASARNAP")
	public Boolean getPsvasarnap() {
		return psvasarnap;
	}
	public void setPsvasarnap(Boolean psvasarnap) {
		this.psvasarnap = psvasarnap;
	}
	
	@Column(name = "PLHETFO")
	public Boolean getPlhetfo() {
		return plhetfo;
	}
	public void setPlhetfo(Boolean plhetfo) {
		this.plhetfo = plhetfo;
	}
	
	@Column(name = "PLKEDD")
	public Boolean getPlkedd() {
		return plkedd;
	}
	public void setPlkedd(Boolean plkedd) {
		this.plkedd = plkedd;
	}
	
	@Column(name = "PLSZERDA")
	public Boolean getPlszerda() {
		return plszerda;
	}
	public void setPlszerda(Boolean plszerda) {
		this.plszerda = plszerda;
	}
	
	@Column(name = "PLCSUTORTOK")
	public Boolean getPlcsutortok() {
		return plcsutortok;
	}
	public void setPlcsutortok(Boolean plcsutortok) {
		this.plcsutortok = plcsutortok;
	}
	
	@Column(name = "PLPENTEK")
	public Boolean getPlpentek() {
		return plpentek;
	}
	public void setPlpentek(Boolean plpentek) {
		this.plpentek = plpentek;
	}
	
	@Column(name = "PLSZOMBAT")
	public Boolean getPlszombat() {
		return plszombat;
	}
	public void setPlszombat(Boolean plszombat) {
		this.plszombat = plszombat;
	}
	
	@Column(name = "PLVASARNAP")
	public Boolean getPlvasarnap() {
		return plvasarnap;
	}
	public void setPlvasarnap(Boolean plvasarnap) {
		this.plvasarnap = plvasarnap;
	}
	
	@Column(name = "HIVASTKER")
	public Boolean getHivastker() {
		return hivastker;
	}
	public void setHivastker(Boolean hivastker) {
		this.hivastker = hivastker;
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
	
	@Column(name = "HIVASPSH")
	public Boolean getHivasPsH() {
		return hivasPsH;
	}
	public void setHivasPsH(Boolean hivasPsH) {
		this.hivasPsH = hivasPsH;
	}
	
	@Column(name = "HIVASPSK")
	public Boolean getHivasPsK() {
		return hivasPsK;
	}
	public void setHivasPsK(Boolean hivasPsK) {
		this.hivasPsK = hivasPsK;
	}
	
	@Column(name = "HIVASPSSZE")
	public Boolean getHivasPsSze() {
		return hivasPsSze;
	}
	public void setHivasPsSze(Boolean hivasPsSze) {
		this.hivasPsSze = hivasPsSze;
	}
	
	@Column(name = "HIVASPSCS")
	public Boolean getHivasPsCs() {
		return hivasPsCs;
	}
	public void setHivasPsCs(Boolean hivasPsCs) {
		this.hivasPsCs = hivasPsCs;
	}
	
	@Column(name = "HIVASPSP")
	public Boolean getHivasPsP() {
		return hivasPsP;
	}
	public void setHivasPsP(Boolean hivasPsP) {
		this.hivasPsP = hivasPsP;
	}
	
	@Column(name = "HIVASPSSZO")
	public Boolean getHivasPsSzo() {
		return hivasPsSzo;
	}
	public void setHivasPsSzo(Boolean hivasPsSzo) {
		this.hivasPsSzo = hivasPsSzo;
	}
	
	@Column(name = "HIVASPSV")
	public Boolean getHivasPsV() {
		return hivasPsV;
	}
	public void setHivasPsV(Boolean hivasPsV) {
		this.hivasPsV = hivasPsV;
	}
	
	@Column(name = "HIVASPLH")
	public Boolean getHivasPlH() {
		return hivasPlH;
	}
	public void setHivasPlH(Boolean hivasPlH) {
		this.hivasPlH = hivasPlH;
	}
	
	@Column(name = "HIVASPLK")
	public Boolean getHivasPlK() {
		return hivasPlK;
	}
	public void setHivasPlK(Boolean hivasPlK) {
		this.hivasPlK = hivasPlK;
	}
	
	@Column(name = "HIVASPLSZE")
	public Boolean getHivasPlSze() {
		return hivasPlSze;
	}
	public void setHivasPlSze(Boolean hivasPlSze) {
		this.hivasPlSze = hivasPlSze;
	}
	
	@Column(name = "HIVASPLCS")
	public Boolean getHivasPlCs() {
		return hivasPlCs;
	}
	public void setHivasPlCs(Boolean hivasPlCs) {
		this.hivasPlCs = hivasPlCs;
	}
	
	@Column(name = "HIVASPLP")
	public Boolean getHivasPlP() {
		return hivasPlP;
	}
	public void setHivasPlP(Boolean hivasPlP) {
		this.hivasPlP = hivasPlP;
	}
	
	@Column(name = "HIVASPLSZO")
	public Boolean getHivasPlSzo() {
		return hivasPlSzo;
	}
	public void setHivasPlSzo(Boolean hivasPlSzo) {
		this.hivasPlSzo = hivasPlSzo;
	}
	
	@Column(name = "HIVASPLV")
	public Boolean getHivasPlV() {
		return hivasPlV;
	}
	public void setHivasPlV(Boolean hivasPlV) {
		this.hivasPlV = hivasPlV;
	}
	
	
	


	
	

}

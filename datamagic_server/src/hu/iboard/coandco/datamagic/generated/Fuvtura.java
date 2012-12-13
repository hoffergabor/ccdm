package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FUVTURA")
public class Fuvtura implements Serializable{

	private static final long serialVersionUID = -5846944756340372497L;
	private Integer id;
	private String megn;
	private String leiras;
	private Integer sofor;
	private Integer jarmu;
	private String megj;
	private String elotag;
	private String honnan;
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
	private Integer modkod;
	private Date moddatum;
	private Boolean nofuvossz;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "MEGN")
	public String getMegn() {
		return megn;
	}
	public void setMegn(String megn) {
		this.megn = megn;
	}
	
	@Column(name = "LEIRAS")
	public String getLeiras() {
		return leiras;
	}
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}
	
	@Column(name = "SOFOR")
	public Integer getSofor() {
		return sofor;
	}
	public void setSofor(Integer sofor) {
		this.sofor = sofor;
	}
	
	@Column(name = "JARMU")
	public Integer getJarmu() {
		return jarmu;
	}
	public void setJarmu(Integer jarmu) {
		this.jarmu = jarmu;
	}
	
	@Column(name = "MEGJ")
	public String getMegj() {
		return megj;
	}
	public void setMegj(String megj) {
		this.megj = megj;
	}
	
	@Column(name = "ELOTAG")
	public String getElotag() {
		return elotag;
	}
	public void setElotag(String elotag) {
		this.elotag = elotag;
	}
	
	@Column(name = "HONNAN")
	public String getHonnan() {
		return honnan;
	}
	public void setHonnan(String honnan) {
		this.honnan = honnan;
	}
	
	@Column(name = "NOFUVOSSZ")
	public Boolean getNofuvossz() {
		return nofuvossz;
	}
	public void setNofuvossz(Boolean nofuvossz) {
		this.nofuvossz = nofuvossz;
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
	
	
}

package hu.iboard.coandco.datamagic.generated;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AOPT")
public class Aopt implements Serializable{

	private static final long serialVersionUID = -8568691251973654153L;
	
	private String aFolder;
	private Integer animSpeed;
	private Boolean autoOpen;
	private Date created;
	private String creator;
	private Boolean deleted;
	private Boolean finalized;
	private Integer id;
	private Boolean iktFelel;
	private Boolean iktHatar;
	private Boolean iktPartner;
	private Date lastMod;
	private Integer lastNumber;
	private Integer lastYear;
	private Date modDatum;
	private Boolean szintJog;
	
	@Column(name = "AFOLDER")
	public String getAFolder() {
		return aFolder;
	}
	public void setAFolder(String folder) {
		aFolder = folder;
	}
	
	@Column(name = "ANIMSPEED")
	public Integer getAnimSpeed() {
		return animSpeed;
	}
	public void setAnimSpeed(Integer animSpeed) {
		this.animSpeed = animSpeed;
	}
	
	@Column(name = "AUTOOPEN")
	public Boolean getAutoOpen() {
		return autoOpen;
	}
	public void setAutoOpen(Boolean autoOpen) {
		this.autoOpen = autoOpen;
	}
	
	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
	@Column(name = "CREATOR")
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	@Column(name = "DELETED")
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	
	@Column(name = "FINALIZED")
	public Boolean getFinalized() {
		return finalized;
	}
	public void setFinalized(Boolean finalized) {
		this.finalized = finalized;
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "IKTFELEL")
	public Boolean getIktFelel() {
		return iktFelel;
	}
	public void setIktFelel(Boolean iktFelel) {
		this.iktFelel = iktFelel;
	}
	
	@Column(name = "IKTHATAR")
	public Boolean getIktHatar() {
		return iktHatar;
	}
	public void setIktHatar(Boolean iktHatar) {
		this.iktHatar = iktHatar;
	}
	
	@Column(name = "IKTPARTNER")
	public Boolean getIktPartner() {
		return iktPartner;
	}
	public void setIktPartner(Boolean iktPartner) {
		this.iktPartner = iktPartner;
	}
	
	@Column(name = "LASTMOD")
	public Date getLastMod() {
		return lastMod;
	}
	public void setLastMod(Date lastMod) {
		this.lastMod = lastMod;
	}
	
	@Column(name = "LASTNUMBER")
	public Integer getLastNumber() {
		return lastNumber;
	}
	public void setLastNumber(Integer lastNumber) {
		this.lastNumber = lastNumber;
	}
	
	@Column(name = "LASTYEAR")
	public Integer getLastYear() {
		return lastYear;
	}
	public void setLastYear(Integer lastYear) {
		this.lastYear = lastYear;
	}
	
	@Column(name = "MODDATUM")
	public Date getModDatum() {
		return modDatum;
	}
	public void setModDatum(Date modDatum) {
		this.modDatum = modDatum;
	}
	
	@Column(name = "SZINTJOG")
	public Boolean getSzintJog() {
		return szintJog;
	}
	public void setSzintJog(Boolean szintJog) {
		this.szintJog = szintJog;
	}

}

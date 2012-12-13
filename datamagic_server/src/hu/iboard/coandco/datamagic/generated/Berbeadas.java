package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@org.hibernate.annotations.Proxy(lazy = true)
@Table(name = "BERBEADAS")
public class Berbeadas implements Serializable {

	private static final long serialVersionUID = -2362666972170416048L;

	public Berbeadas() {
	}

	@Column(name = "X", nullable = true, length = 1)
	private Character x;

	@Column(name = "DELETED", nullable = true, length = 10)
	private Integer deleted;

	@Column(name = "FINALIZED", nullable = true, length = 10)
	private Integer finalized;

	@Column(name = "CREATOR", nullable = true, length = 100)
	private String creator;

	@Column(name = "CREATED", nullable = true)
	private java.sql.Timestamp created;

	@Column(name = "LASTMOD", nullable = true)
	private java.sql.Timestamp lastmod;

	@Column(name = "ID", nullable = false, unique = true)
	@Id
	private int id;

	@Column(name = "DATESTART", nullable = true)
	private java.sql.Timestamp dateStart;

	@Column(name = "DATEEND", nullable = true)
	private java.sql.Timestamp dateEnd;

	@Column(name = "UGYFEL", nullable = true, length = 10)
	private Integer ugyfel;

	@Column(name = "MEGJ", nullable = true, length = 254)
	private String megj;

	@Column(name = "LAKAS", nullable = true, length = 10)
	private Integer lakas;

	@Column(name = "DEPOSIT", nullable = true, precision = 22, scale = 0)
	private java.math.BigDecimal deposit;

	@Column(name = "HALADEK", nullable = true, length = 10)
	private Integer haladek;

	@Column(name = "IDXTIPUS", nullable = true, length = 10)
	private Integer idxTipus;

	@Column(name = "LSTINTERV", nullable = true, length = 10)
	private Integer lstInterv;

	@Column(name = "DEVIZANEM", nullable = true, length = 4)
	private String devizanem;

	@Column(name = "BERLETIDIJ", nullable = true, precision = 22, scale = 0)
	private java.math.BigDecimal berletiDij;

	@Column(name = "DEPOSITFT", nullable = true, precision = 22, scale = 0)
	private java.math.BigDecimal depositft;

	@Column(name = "FELMONDAS", nullable = true, length = 10)
	private Integer felmondas;

	@Column(name = "ELOJOG", nullable = true, length = 10)
	private Integer elojog;

	@Column(name = "SzerzodesSzam", nullable = true, length = 30)
	private String szerzodesSzam;

	@Column(name = "HATAROZATLAN", nullable = true, length = 10)
	private Boolean hatarozatlan;

	@Column(name = "BerStatusz", nullable = true, length = 10)
	private Integer berStatusz;

	@Column(name = "Bonyolit", nullable = true, precision = 22, scale = 0)
	private java.math.BigDecimal bonyolit;

	@Column(name = "Szolgaltat", nullable = true, precision = 22, scale = 0)
	private java.math.BigDecimal szolgaltat;

	@Column(name = "SzlaKezd", nullable = true)
	private java.sql.Timestamp szlaKezd;

	@Column(name = "szlasorsz", nullable = true, length = 10)
	private Integer szlasorsz;

	@Column(name = "szlasorsz2", nullable = true, precision = 7, scale = 0)
	private java.math.BigDecimal szlasorsz2;

	@Column(name = "szamlaszam", nullable = true, length = 26)
	private String szamlaszam;

	@Column(name = "IndexDatum", nullable = true)
	private java.sql.Timestamp indexDatum;

	@Column(name = "IndexErtek", nullable = true, precision = 12, scale = 0)
	private java.math.BigDecimal indexErtek;

	@Column(name = "IndexMegj", nullable = true, length = 250)
	private String indexMegj;

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Partner.class)
	@JoinColumn(name = "Ugyfel", nullable = true,referencedColumnName="vevokod")
	@NotFound(action = NotFoundAction.IGNORE)
	private Partner partner;

	public void setX(char value) {
		setX(new Character(value));
	}

	public void setX(Character value) {
		this.x = value;
	}

	public Character getX() {
		return x;
	}

	public void setDeleted(int value) {
		setDeleted(new Integer(value));
	}

	public void setDeleted(Integer value) {
		this.deleted = value;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setFinalized(int value) {
		setFinalized(new Integer(value));
	}

	public void setFinalized(Integer value) {
		this.finalized = value;
	}

	public Integer getFinalized() {
		return finalized;
	}

	public void setCreator(String value) {
		this.creator = value;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreated(java.sql.Timestamp value) {
		this.created = value;
	}

	public java.sql.Timestamp getCreated() {
		return created;
	}

	public void setLastmod(java.sql.Timestamp value) {
		this.lastmod = value;
	}

	public java.sql.Timestamp getLastmod() {
		return lastmod;
	}

	private void setId(int value) {
		this.id = value;
	}

	public int getId() {
		return id;
	}

	public int getORMID() {
		return getId();
	}

	public void setDateStart(java.sql.Timestamp value) {
		this.dateStart = value;
	}

	public java.sql.Timestamp getDateStart() {
		return dateStart;
	}

	public void setDateEnd(java.sql.Timestamp value) {
		this.dateEnd = value;
	}

	public java.sql.Timestamp getDateEnd() {
		return dateEnd;
	}

	public void setHatarozatl(int value) {
		setHatarozatl(new Integer(value));
	}

	public void setUgyfel(int value) {
		setUgyfel(new Integer(value));
	}

	public void setUgyfel(Integer value) {
		this.ugyfel = value;
	}

	public Integer getUgyfel() {
		return ugyfel;
	}

	public void setMegj(String value) {
		this.megj = value;
	}

	public String getMegj() {
		return megj;
	}

	public void setLakas(int value) {
		setLakas(new Integer(value));
	}

	public void setLakas(Integer value) {
		this.lakas = value;
	}

	public Integer getLakas() {
		return lakas;
	}

	public void setDeposit(java.math.BigDecimal value) {
		this.deposit = value;
	}

	public java.math.BigDecimal getDeposit() {
		return deposit;
	}

	public void setHaladek(int value) {
		setHaladek(new Integer(value));
	}

	public void setHaladek(Integer value) {
		this.haladek = value;
	}

	public Integer getHaladek() {
		return haladek;
	}

	public void setIdxTipus(int value) {
		setIdxTipus(new Integer(value));
	}

	public void setIdxTipus(Integer value) {
		this.idxTipus = value;
	}

	public Integer getIdxTipus() {
		return idxTipus;
	}

	public void setLstInterv(int value) {
		setLstInterv(new Integer(value));
	}

	public void setLstInterv(Integer value) {
		this.lstInterv = value;
	}

	public Integer getLstInterv() {
		return lstInterv;
	}

	public void setDevizanem(String value) {
		this.devizanem = value;
	}

	public String getDevizanem() {
		return devizanem;
	}

	public void setBerletiDij(java.math.BigDecimal value) {
		this.berletiDij = value;
	}

	public java.math.BigDecimal getBerletiDij() {
		return berletiDij;
	}

	public void setDepositft(java.math.BigDecimal value) {
		this.depositft = value;
	}

	public java.math.BigDecimal getDepositft() {
		return depositft;
	}

	public void setFelmondas(int value) {
		setFelmondas(new Integer(value));
	}

	public void setFelmondas(Integer value) {
		this.felmondas = value;
	}

	public Integer getFelmondas() {
		return felmondas;
	}

	public void setElojog(int value) {
		setElojog(new Integer(value));
	}

	public void setElojog(Integer value) {
		this.elojog = value;
	}

	public Integer getElojog() {
		return elojog;
	}

	public void setSzerzodesSzam(String value) {
		this.szerzodesSzam = value;
	}

	public String getSzerzodesSzam() {
		return szerzodesSzam;
	}

	public void setHatarozatlan(int value) {
		setHatarozatlan(new Integer(value));
	}

	public Boolean getHatarozatlan() {
		return hatarozatlan;
	}

	public void setHatarozatlan(Boolean hatarozatlan) {
		this.hatarozatlan = hatarozatlan;
	}

	public void setBerStatusz(int value) {
		setBerStatusz(new Integer(value));
	}

	public void setBerStatusz(Integer value) {
		this.berStatusz = value;
	}

	public Integer getBerStatusz() {
		return berStatusz;
	}

	public void setBonyolit(java.math.BigDecimal value) {
		this.bonyolit = value;
	}

	public java.math.BigDecimal getBonyolit() {
		return bonyolit;
	}

	public void setSzolgaltat(java.math.BigDecimal value) {
		this.szolgaltat = value;
	}

	public java.math.BigDecimal getSzolgaltat() {
		return szolgaltat;
	}

	public void setSzlaKezd(java.sql.Timestamp value) {
		this.szlaKezd = value;
	}

	public java.sql.Timestamp getSzlaKezd() {
		return szlaKezd;
	}

	public void setSzlasorsz(int value) {
		setSzlasorsz(new Integer(value));
	}

	public void setSzlasorsz(Integer value) {
		this.szlasorsz = value;
	}

	public Integer getSzlasorsz() {
		return szlasorsz;
	}

	public void setSzlasorsz2(java.math.BigDecimal value) {
		this.szlasorsz2 = value;
	}

	public java.math.BigDecimal getSzlasorsz2() {
		return szlasorsz2;
	}

	public void setSzamlaszam(String value) {
		this.szamlaszam = value;
	}

	public String getSzamlaszam() {
		return szamlaszam;
	}

	public void setIndexDatum(java.sql.Timestamp value) {
		this.indexDatum = value;
	}

	public java.sql.Timestamp getIndexDatum() {
		return indexDatum;
	}

	public void setIndexErtek(java.math.BigDecimal value) {
		this.indexErtek = value;
	}

	public java.math.BigDecimal getIndexErtek() {
		return indexErtek;
	}

	public void setIndexMegj(String value) {
		this.indexMegj = value;
	}

	public String getIndexMegj() {
		return indexMegj;
	}

	public String toString() {
		return String.valueOf(getId());
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}

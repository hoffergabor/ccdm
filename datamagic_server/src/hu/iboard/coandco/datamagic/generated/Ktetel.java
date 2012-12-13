package hu.iboard.coandco.datamagic.generated;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ktetel generated by hbm2java
 */
@Entity
@Table(name = "KTETEL")
public class Ktetel implements java.io.Serializable {

	private String unikazon;
	private Kszamla kszamla;
	private String sorszam;
	private Integer tetelszam;
	private Integer arukod;
	private String acikksz;
	private String amegn;
	private String afkszam;
	private BigDecimal amenny;
	private String ameegys;
	private BigDecimal avar;
	private BigDecimal aear;
	private Integer aafakod;
	private Integer szjvvtsz;
	private String szjvtsz;
	private String munkszam;
	private String megj;
	private BigDecimal aafa;
	private BigDecimal hufvar;
	private BigDecimal hufear;
	private Integer munkszuk;
	private String levazon;
	private BigDecimal nertek;
	private BigDecimal aertek;
	private BigDecimal bertek;
	private BigDecimal hnertek;
	private BigDecimal haertek;
	private BigDecimal hbertek;
	private String sortmegn;
	private boolean dmkstat;
	private Date dmkdat;
	private Integer mkateg1;
	private Integer mkateg2;
	private Integer mkateg3;
	private Integer mkateg4;
	private Integer mkateg5;
	private boolean banklehiv;
	private boolean forditott;

	public Ktetel() {
	}

	public Ktetel(String unikazon) {
		this.unikazon = unikazon;
	}

	public Ktetel(String unikazon, Kszamla kszamla, String sorszam, Integer tetelszam,
			Integer arukod, String acikksz, String amegn, String afkszam,
			BigDecimal amenny, String ameegys, BigDecimal avar,
			BigDecimal aear, Integer aafakod, Integer szjvvtsz, String szjvtsz,
			String munkszam, String megj, BigDecimal aafa, BigDecimal hufvar,
			BigDecimal hufear, Integer munkszuk, String levazon,
			BigDecimal nertek, BigDecimal aertek, BigDecimal bertek,
			BigDecimal hnertek, BigDecimal haertek, BigDecimal hbertek,
			String sortmegn, boolean dmkstat, Date dmkdat, Integer mkateg1,
			Integer mkateg2, Integer mkateg3, Integer mkateg4, Integer mkateg5,
			boolean banklehiv, boolean forditott) {
		this.unikazon = unikazon;
		this.kszamla = kszamla;
		this.sorszam = sorszam;
		this.tetelszam = tetelszam;
		this.arukod = arukod;
		this.acikksz = acikksz;
		this.amegn = amegn;
		this.afkszam = afkszam;
		this.amenny = amenny;
		this.ameegys = ameegys;
		this.avar = avar;
		this.aear = aear;
		this.aafakod = aafakod;
		this.szjvvtsz = szjvvtsz;
		this.szjvtsz = szjvtsz;
		this.munkszam = munkszam;
		this.megj = megj;
		this.aafa = aafa;
		this.hufvar = hufvar;
		this.hufear = hufear;
		this.munkszuk = munkszuk;
		this.levazon = levazon;
		this.nertek = nertek;
		this.aertek = aertek;
		this.bertek = bertek;
		this.hnertek = hnertek;
		this.haertek = haertek;
		this.hbertek = hbertek;
		this.sortmegn = sortmegn;
		this.dmkstat = dmkstat;
		this.dmkdat = dmkdat;
		this.mkateg1 = mkateg1;
		this.mkateg2 = mkateg2;
		this.mkateg3 = mkateg3;
		this.mkateg4 = mkateg4;
		this.mkateg5 = mkateg5;
		this.banklehiv = banklehiv;
		this.forditott = forditott;
	}

	@Id
	@Column(name = "UNIKAZON", unique = true, nullable = false, length = 10)
	public String getUnikazon() {
		return this.unikazon;
	}

	public void setUnikazon(String unikazon) {
		this.unikazon = unikazon;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SORSZAM", insertable = false, updatable = false)
	public Kszamla getKszamla() {
		return this.kszamla;
	}

	public void setKszamla(Kszamla kszamla) {
		this.kszamla = kszamla;
	}

	@Column(name = "SORSZAM", length = 15)
	public String getSorszam() {
		return this.sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	@Column(name = "TETELSZAM")
	public Integer getTetelszam() {
		return this.tetelszam;
	}

	public void setTetelszam(Integer tetelszam) {
		this.tetelszam = tetelszam;
	}

	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return this.arukod;
	}

	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}

	@Column(name = "ACIKKSZ", length = 25)
	public String getAcikksz() {
		return this.acikksz;
	}

	public void setAcikksz(String acikksz) {
		this.acikksz = acikksz;
	}

	@Column(name = "AMEGN", length = 50)
	public String getAmegn() {
		return this.amegn;
	}

	public void setAmegn(String amegn) {
		this.amegn = amegn;
	}

	@Column(name = "AFKSZAM", length = 8)
	public String getAfkszam() {
		return this.afkszam;
	}

	public void setAfkszam(String afkszam) {
		this.afkszam = afkszam;
	}

	@Column(name = "AMENNY", precision = 17, scale = 3)
	public BigDecimal getAmenny() {
		return this.amenny;
	}

	public void setAmenny(BigDecimal amenny) {
		this.amenny = amenny;
	}

	@Column(name = "AMEEGYS", length = 10)
	public String getAmeegys() {
		return this.ameegys;
	}

	public void setAmeegys(String ameegys) {
		this.ameegys = ameegys;
	}

	@Column(name = "AVAR", scale = 6)
	public BigDecimal getAvar() {
		return this.avar;
	}

	public void setAvar(BigDecimal avar) {
		this.avar = avar;
	}

	@Column(name = "AEAR", scale = 6)
	public BigDecimal getAear() {
		return this.aear;
	}

	public void setAear(BigDecimal aear) {
		this.aear = aear;
	}

	@Column(name = "AAFAKOD")
	public Integer getAafakod() {
		return this.aafakod;
	}

	public void setAafakod(Integer aafakod) {
		this.aafakod = aafakod;
	}

	@Column(name = "SZJVVTSZ")
	public Integer getSzjvvtsz() {
		return this.szjvvtsz;
	}

	public void setSzjvvtsz(Integer szjvvtsz) {
		this.szjvvtsz = szjvvtsz;
	}

	@Column(name = "SZJVTSZ", length = 10)
	public String getSzjvtsz() {
		return this.szjvtsz;
	}

	public void setSzjvtsz(String szjvtsz) {
		this.szjvtsz = szjvtsz;
	}

	@Column(name = "MUNKSZAM", length = 50)
	public String getMunkszam() {
		return this.munkszam;
	}

	public void setMunkszam(String munkszam) {
		this.munkszam = munkszam;
	}

	@Column(name = "MEGJ", length = 100)
	public String getMegj() {
		return this.megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}

	@Column(name = "AAFA", precision = 17)
	public BigDecimal getAafa() {
		return this.aafa;
	}

	public void setAafa(BigDecimal aafa) {
		this.aafa = aafa;
	}

	@Column(name = "HUFVAR", scale = 6)
	public BigDecimal getHufvar() {
		return this.hufvar;
	}

	public void setHufvar(BigDecimal hufvar) {
		this.hufvar = hufvar;
	}

	@Column(name = "HUFEAR", scale = 6)
	public BigDecimal getHufear() {
		return this.hufear;
	}

	public void setHufear(BigDecimal hufear) {
		this.hufear = hufear;
	}

	@Column(name = "MUNKSZUK")
	public Integer getMunkszuk() {
		return this.munkszuk;
	}

	public void setMunkszuk(Integer munkszuk) {
		this.munkszuk = munkszuk;
	}

	@Column(name = "LEVAZON", length = 50)
	public String getLevazon() {
		return this.levazon;
	}

	public void setLevazon(String levazon) {
		this.levazon = levazon;
	}

	@Column(name = "NERTEK", precision = 17)
	public BigDecimal getNertek() {
		return this.nertek;
	}

	public void setNertek(BigDecimal nertek) {
		this.nertek = nertek;
	}

	@Column(name = "AERTEK", precision = 17)
	public BigDecimal getAertek() {
		return this.aertek;
	}

	public void setAertek(BigDecimal aertek) {
		this.aertek = aertek;
	}

	@Column(name = "BERTEK", precision = 17)
	public BigDecimal getBertek() {
		return this.bertek;
	}

	public void setBertek(BigDecimal bertek) {
		this.bertek = bertek;
	}

	@Column(name = "HNERTEK", precision = 17)
	public BigDecimal getHnertek() {
		return this.hnertek;
	}

	public void setHnertek(BigDecimal hnertek) {
		this.hnertek = hnertek;
	}

	@Column(name = "HAERTEK", precision = 17)
	public BigDecimal getHaertek() {
		return this.haertek;
	}

	public void setHaertek(BigDecimal haertek) {
		this.haertek = haertek;
	}

	@Column(name = "HBERTEK", precision = 17)
	public BigDecimal getHbertek() {
		return this.hbertek;
	}

	public void setHbertek(BigDecimal hbertek) {
		this.hbertek = hbertek;
	}

	@Column(name = "SORTMEGN", length = 6)
	public String getSortmegn() {
		return this.sortmegn;
	}

	public void setSortmegn(String sortmegn) {
		this.sortmegn = sortmegn;
	}

	@Column(name = "DMKSTAT")
	public boolean isDmkstat() {
		return this.dmkstat;
	}

	public void setDmkstat(boolean dmkstat) {
		this.dmkstat = dmkstat;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "DMKDAT", length = 19)
	public Date getDmkdat() {
		return this.dmkdat;
	}

	public void setDmkdat(Date dmkdat) {
		this.dmkdat = dmkdat;
	}

	@Column(name = "MKATEG1")
	public Integer getMkateg1() {
		return this.mkateg1;
	}

	public void setMkateg1(Integer mkateg1) {
		this.mkateg1 = mkateg1;
	}

	@Column(name = "MKATEG2")
	public Integer getMkateg2() {
		return this.mkateg2;
	}

	public void setMkateg2(Integer mkateg2) {
		this.mkateg2 = mkateg2;
	}

	@Column(name = "MKATEG3")
	public Integer getMkateg3() {
		return this.mkateg3;
	}

	public void setMkateg3(Integer mkateg3) {
		this.mkateg3 = mkateg3;
	}

	@Column(name = "MKATEG4")
	public Integer getMkateg4() {
		return this.mkateg4;
	}

	public void setMkateg4(Integer mkateg4) {
		this.mkateg4 = mkateg4;
	}

	@Column(name = "MKATEG5")
	public Integer getMkateg5() {
		return this.mkateg5;
	}

	public void setMkateg5(Integer mkateg5) {
		this.mkateg5 = mkateg5;
	}

	@Column(name = "BANKLEHIV")
	public boolean isBanklehiv() {
		return this.banklehiv;
	}

	public void setBanklehiv(boolean banklehiv) {
		this.banklehiv = banklehiv;
	}

	@Column(name = "FORDITOTT")
	public boolean isForditott() {
		return this.forditott;
	}

	public void setForditott(boolean forditott) {
		this.forditott = forditott;
	}

}

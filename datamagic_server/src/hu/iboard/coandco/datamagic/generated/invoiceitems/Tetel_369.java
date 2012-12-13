package hu.iboard.coandco.datamagic.generated.invoiceitems;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import hu.iboard.coandco.datamagic.generated.Szamla;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tetel generated by hbm2java
 */
@Entity
@Table(name = "TETEL_369")
public class Tetel_369 implements java.io.Serializable {

	private String unikazon;
	private Szamla szamla;
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
	private String garido;
	private BigDecimal hufvar;
	private BigDecimal hufear;
	private Integer munkszuk;
	private String amegn1;
	private boolean ezgongy;
	private String levazon;
	private String rendazon;
	private String gyszam;
	private BigDecimal nertek;
	private BigDecimal aertek;
	private BigDecimal bertek;
	private BigDecimal hnertek;
	private BigDecimal haertek;
	private BigDecimal hbertek;
	private String sortmegn;
	private boolean helyeselt;
	private boolean dmkstat;
	private Date dmkdat;
	private Integer mkateg1;
	private Integer mkateg2;
	private Integer mkateg3;
	private Integer mkateg4;
	private Integer mkateg5;
	private BigDecimal alapar;
	private BigDecimal elszamolar;
	private boolean forditott;

	public Tetel_369() {
	}

	public Tetel_369(String unikazon) {
		this.unikazon = unikazon;
	}

	public Tetel_369(String unikazon, Szamla szamla, String sorszam, Integer tetelszam,
			Integer arukod, String acikksz, String amegn, String afkszam,
			BigDecimal amenny, String ameegys, BigDecimal avar,
			BigDecimal aear, Integer aafakod, Integer szjvvtsz, String szjvtsz,
			String munkszam, String megj, String garido, BigDecimal hufvar,
			BigDecimal hufear, Integer munkszuk, String amegn1,
			boolean ezgongy, String levazon, String rendazon, String gyszam,
			BigDecimal nertek, BigDecimal aertek, BigDecimal bertek,
			BigDecimal hnertek, BigDecimal haertek, BigDecimal hbertek,
			String sortmegn, boolean helyeselt, boolean dmkstat, Date dmkdat,
			Integer mkateg1, Integer mkateg2, Integer mkateg3, Integer mkateg4,
			Integer mkateg5, BigDecimal alapar, BigDecimal elszamolar,
			boolean forditott) {
		this.unikazon = unikazon;
		this.szamla = szamla;
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
		this.garido = garido;
		this.hufvar = hufvar;
		this.hufear = hufear;
		this.munkszuk = munkszuk;
		this.amegn1 = amegn1;
		this.ezgongy = ezgongy;
		this.levazon = levazon;
		this.rendazon = rendazon;
		this.gyszam = gyszam;
		this.nertek = nertek;
		this.aertek = aertek;
		this.bertek = bertek;
		this.hnertek = hnertek;
		this.haertek = haertek;
		this.hbertek = hbertek;
		this.sortmegn = sortmegn;
		this.helyeselt = helyeselt;
		this.dmkstat = dmkstat;
		this.dmkdat = dmkdat;
		this.mkateg1 = mkateg1;
		this.mkateg2 = mkateg2;
		this.mkateg3 = mkateg3;
		this.mkateg4 = mkateg4;
		this.mkateg5 = mkateg5;
		this.alapar = alapar;
		this.elszamolar = elszamolar;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SORSZAM", insertable = false, updatable = false)
	public Szamla getSzamla() {
		return this.szamla;
	}

	public void setSzamla(Szamla szamla) {
		this.szamla = szamla;
	}

	@Column(name = "SORSZAM")
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

	@Column(name = "AVAR", precision = 17)
	public BigDecimal getAvar() {
		return this.avar;
	}

	public void setAvar(BigDecimal avar) {
		this.avar = avar;
	}

	@Column(name = "AEAR", precision = 17)
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

	@Column(name = "GARIDO", length = 10)
	public String getGarido() {
		return this.garido;
	}

	public void setGarido(String garido) {
		this.garido = garido;
	}

	@Column(name = "HUFVAR", precision = 17)
	public BigDecimal getHufvar() {
		return this.hufvar;
	}

	public void setHufvar(BigDecimal hufvar) {
		this.hufvar = hufvar;
	}

	@Column(name = "HUFEAR", precision = 17)
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

	@Column(name = "AMEGN1", length = 50)
	public String getAmegn1() {
		return this.amegn1;
	}

	public void setAmegn1(String amegn1) {
		this.amegn1 = amegn1;
	}

	@Column(name = "EZGONGY")
	public boolean isEzgongy() {
		return this.ezgongy;
	}

	public void setEzgongy(boolean ezgongy) {
		this.ezgongy = ezgongy;
	}

	@Column(name = "LEVAZON", length = 50)
	public String getLevazon() {
		return this.levazon;
	}

	public void setLevazon(String levazon) {
		this.levazon = levazon;
	}

	@Column(name = "RENDAZON", length = 50)
	public String getRendazon() {
		return this.rendazon;
	}

	public void setRendazon(String rendazon) {
		this.rendazon = rendazon;
	}

	@Column(name = "GYSZAM", length = 40)
	public String getGyszam() {
		return this.gyszam;
	}

	public void setGyszam(String gyszam) {
		this.gyszam = gyszam;
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

	@Column(name = "HELYESELT")
	public boolean isHelyeselt() {
		return this.helyeselt;
	}

	public void setHelyeselt(boolean helyeselt) {
		this.helyeselt = helyeselt;
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

	@Column(name = "ALAPAR", precision = 20, scale = 4)
	public BigDecimal getAlapar() {
		return this.alapar;
	}

	public void setAlapar(BigDecimal alapar) {
		this.alapar = alapar;
	}

	@Column(name = "ELSZAMOLAR", precision = 20, scale = 4)
	public BigDecimal getElszamolar() {
		return this.elszamolar;
	}

	public void setElszamolar(BigDecimal elszamolar) {
		this.elszamolar = elszamolar;
	}

	@Column(name = "FORDITOTT")
	public boolean isForditott() {
		return this.forditott;
	}

	public void setForditott(boolean forditott) {
		this.forditott = forditott;
	}

}

package hu.iboard.coandco.datamagic.model.rendtet;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import hu.iboard.coandco.datamagic.model.rendel.Rendel;

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
 * Rendtet generated by hbm2java
 */
@Entity
@Table(name = "RENDTET")
public class Rendtet implements java.io.Serializable {

	private static final long serialVersionUID = -8330275382019963495L;
	private String unikazon;
	private Rendel rendel;
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
	private Date thdat;
	private String alapbizt;
	private String fuvmod;
	private String atvhely;
	private String bizszam;
	private String kivagybe;
	private boolean teljesitve;
	private BigDecimal hufvar;
	private BigDecimal hufear;
	private Integer munkszuk;
	private String amegn1;
	private Date vfizhdat;
	private String gyszam;
	private String tmegrazon;
	private Integer morestat;
	private String moreindok;
	private Long gyartido;
	private Long atfutido;
	private Date gyartkezd;
	private Date gyartkesz;
	private Date atadgyart;
	private BigDecimal gyartmenny;
	private Integer rogzkod;
	private Integer atadkod;
	private Integer gykezdkod;
	private Integer ellenkod;
	private Integer osszekod;
	private BigDecimal nertek;
	private BigDecimal aertek;
	private BigDecimal bertek;
	private BigDecimal hnertek;
	private BigDecimal haertek;
	private BigDecimal hbertek;
	private String sortmegn;
	private boolean raktaron;
	private BigDecimal felkeszmny;
	private Date bvisszaig;
	private BigDecimal selejtmny;
	private BigDecimal meomenny;
	private boolean bizki;
	private BigDecimal kiszallmny;
	private String b_megj;
	private boolean nutrend;
	private String sajatszam;
	private Integer mkateg1;
	private Integer mkateg2;
	private Integer mkateg3;
	private Integer mkateg4;
	private Integer mkateg5;
	private String gyszam2;
	private String szlevsorsz;
	private Date szlevkelt;
	private String szamlsorsz;
	private Date szamlkelt;
	private BigDecimal alapar;
	private BigDecimal elszamolar;
	private BigDecimal kiadmenny;
	private BigDecimal szabmenny;
	private BigDecimal varmenny;
	private BigDecimal elkmenny;
	private BigDecimal csommenny;
	private BigDecimal modmenny;
	private BigDecimal keszmenny;
	private BigDecimal osszemenny;
	private String mlsorszam;
	private String bsrszam;
	private String bssorszam;
	private Integer menny2;
	private Integer menny3;
	private Integer menny4;
	private Integer menny5;
	private Integer lefejto;
	private BigDecimal kedvertek;
	private String kedvmegn;
	private Integer kulsotelj;
	private Integer lefejto2;
	private String gongyazon;
	private Integer regio;
	private BigDecimal gymenny;
	private Integer sarzsid;
	private Date tvighatido;
	private Integer idozona1;
	private Integer idozona2;
	private Integer hatidotol;
	private Integer hatidoig;

	public Rendtet() {
	}

	public Rendtet(String unikazon) {
		this.unikazon = unikazon;
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
	@JoinColumn(name = "SORSZAM", columnDefinition = "SORSZAM", insertable = false, updatable = false)
	public Rendel getRendel() {
		return this.rendel;
	}

	public void setRendel(Rendel rendel) {
		this.rendel = rendel;
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

	@Column(name = "ACIKKSZ", length = 20)
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

	@Column(name = "AMENNY", scale = 3)
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

	@Column(name = "AVAR")
	public BigDecimal getAvar() {
		return this.avar;
	}

	public void setAvar(BigDecimal avar) {
		this.avar = avar;
	}

	@Column(name = "AEAR")
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

	@Column(name = "GARIDO", length = 15)
	public String getGarido() {
		return this.garido;
	}

	public void setGarido(String garido) {
		this.garido = garido;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "THDAT")
	public Date getThdat() {
		return this.thdat;
	}

	public void setThdat(Date thdat) {
		this.thdat = thdat;
	}

	@Column(name = "ALAPBIZT", length = 20)
	public String getAlapbizt() {
		return this.alapbizt;
	}

	public void setAlapbizt(String alapbizt) {
		this.alapbizt = alapbizt;
	}

	@Column(name = "FUVMOD", length = 20)
	public String getFuvmod() {
		return this.fuvmod;
	}

	public void setFuvmod(String fuvmod) {
		this.fuvmod = fuvmod;
	}

	@Column(name = "ATVHELY", length = 20)
	public String getAtvhely() {
		return this.atvhely;
	}

	public void setAtvhely(String atvhely) {
		this.atvhely = atvhely;
	}

	@Column(name = "BIZSZAM", length = 20)
	public String getBizszam() {
		return this.bizszam;
	}

	public void setBizszam(String bizszam) {
		this.bizszam = bizszam;
	}

	@Column(name = "KIVAGYBE", length = 1)
	public String getKivagybe() {
		return this.kivagybe;
	}

	public void setKivagybe(String kivagybe) {
		this.kivagybe = kivagybe;
	}

	@Column(name = "TELJESITVE")
	public boolean isTeljesitve() {
		return this.teljesitve;
	}

	public void setTeljesitve(boolean teljesitve) {
		this.teljesitve = teljesitve;
	}

	@Column(name = "HUFVAR")
	public BigDecimal getHufvar() {
		return this.hufvar;
	}

	public void setHufvar(BigDecimal hufvar) {
		this.hufvar = hufvar;
	}

	@Column(name = "HUFEAR")
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

	// @Temporal(TemporalType.DATE)
	@Column(name = "VFIZHDAT", length = 19)
	public Date getVfizhdat() {
		return this.vfizhdat;
	}

	public void setVfizhdat(Date vfizhdat) {
		this.vfizhdat = vfizhdat;
	}

	@Column(name = "GYSZAM", length = 40)
	public String getGyszam() {
		return this.gyszam;
	}

	public void setGyszam(String gyszam) {
		this.gyszam = gyszam;
	}

	@Column(name = "TMEGRAZON", length = 15)
	public String getTmegrazon() {
		return this.tmegrazon;
	}

	public void setTmegrazon(String tmegrazon) {
		this.tmegrazon = tmegrazon;
	}

	@Column(name = "MORESTAT", precision = 7, scale = 0)
	public Integer getMorestat() {
		return this.morestat;
	}

	public void setMorestat(Integer morestat) {
		this.morestat = morestat;
	}

	@Column(name = "MOREINDOK", length = 20)
	public String getMoreindok() {
		return this.moreindok;
	}

	public void setMoreindok(String moreindok) {
		this.moreindok = moreindok;
	}

	@Column(name = "GYARTIDO", precision = 11, scale = 0)
	public Long getGyartido() {
		return this.gyartido;
	}

	public void setGyartido(Long gyartido) {
		this.gyartido = gyartido;
	}

	@Column(name = "ATFUTIDO", precision = 11, scale = 0)
	public Long getAtfutido() {
		return this.atfutido;
	}

	public void setAtfutido(Long atfutido) {
		this.atfutido = atfutido;
	}

	@Column(name = "GYARTKEZD", length = 19)
	public Date getGyartkezd() {
		return this.gyartkezd;
	}

	public void setGyartkezd(Date gyartkezd) {
		this.gyartkezd = gyartkezd;
	}

	@Column(name = "GYARTKESZ", length = 19)
	public Date getGyartkesz() {
		return this.gyartkesz;
	}

	public void setGyartkesz(Date gyartkesz) {
		this.gyartkesz = gyartkesz;
	}

	@Column(name = "ATADGYART", length = 19)
	public Date getAtadgyart() {
		return this.atadgyart;
	}

	public void setAtadgyart(Date atadgyart) {
		this.atadgyart = atadgyart;
	}

	@Column(name = "GYARTMENNY", precision = 20, scale = 3)
	public BigDecimal getGyartmenny() {
		return this.gyartmenny;
	}

	public void setGyartmenny(BigDecimal gyartmenny) {
		this.gyartmenny = gyartmenny;
	}

	@Column(name = "ROGZKOD")
	public Integer getRogzkod() {
		return this.rogzkod;
	}

	public void setRogzkod(Integer rogzkod) {
		this.rogzkod = rogzkod;
	}

	@Column(name = "ATADKOD")
	public Integer getAtadkod() {
		return this.atadkod;
	}

	public void setAtadkod(Integer atadkod) {
		this.atadkod = atadkod;
	}

	@Column(name = "GYKEZDKOD")
	public Integer getGykezdkod() {
		return this.gykezdkod;
	}

	public void setGykezdkod(Integer gykezdkod) {
		this.gykezdkod = gykezdkod;
	}

	@Column(name = "ELLENKOD")
	public Integer getEllenkod() {
		return this.ellenkod;
	}

	public void setEllenkod(Integer ellenkod) {
		this.ellenkod = ellenkod;
	}

	@Column(name = "OSSZEKOD")
	public Integer getOsszekod() {
		return this.osszekod;
	}

	public void setOsszekod(Integer osszekod) {
		this.osszekod = osszekod;
	}

	@Column(name = "NERTEK")
	public BigDecimal getNertek() {
		return this.nertek;
	}

	public void setNertek(BigDecimal nertek) {
		this.nertek = nertek;
	}

	@Column(name = "AERTEK")
	public BigDecimal getAertek() {
		return this.aertek;
	}

	public void setAertek(BigDecimal aertek) {
		this.aertek = aertek;
	}

	@Column(name = "BERTEK")
	public BigDecimal getBertek() {
		return this.bertek;
	}

	public void setBertek(BigDecimal bertek) {
		this.bertek = bertek;
	}

	@Column(name = "HNERTEK")
	public BigDecimal getHnertek() {
		return this.hnertek;
	}

	public void setHnertek(BigDecimal hnertek) {
		this.hnertek = hnertek;
	}

	@Column(name = "HAERTEK")
	public BigDecimal getHaertek() {
		return this.haertek;
	}

	public void setHaertek(BigDecimal haertek) {
		this.haertek = haertek;
	}

	@Column(name = "HBERTEK")
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

	@Column(name = "RAKTARON")
	public boolean isRaktaron() {
		return this.raktaron;
	}

	public void setRaktaron(boolean raktaron) {
		this.raktaron = raktaron;
	}

	@Column(name = "FELKESZMNY", precision = 20, scale = 3)
	public BigDecimal getFelkeszmny() {
		return this.felkeszmny;
	}

	public void setFelkeszmny(BigDecimal felkeszmny) {
		this.felkeszmny = felkeszmny;
	}

	@Column(name = "BVISSZAIG", length = 19)
	public Date getBvisszaig() {
		return this.bvisszaig;
	}

	public void setBvisszaig(Date bvisszaig) {
		this.bvisszaig = bvisszaig;
	}

	@Column(name = "SELEJTMNY", precision = 20, scale = 3)
	public BigDecimal getSelejtmny() {
		return this.selejtmny;
	}

	public void setSelejtmny(BigDecimal selejtmny) {
		this.selejtmny = selejtmny;
	}

	@Column(name = "MEOMENNY", precision = 20, scale = 3)
	public BigDecimal getMeomenny() {
		return this.meomenny;
	}

	public void setMeomenny(BigDecimal meomenny) {
		this.meomenny = meomenny;
	}

	@Column(name = "BIZKI")
	public boolean isBizki() {
		return this.bizki;
	}

	public void setBizki(boolean bizki) {
		this.bizki = bizki;
	}

	@Column(name = "KISZALLMNY", precision = 20, scale = 3)
	public BigDecimal getKiszallmny() {
		return this.kiszallmny;
	}

	public void setKiszallmny(BigDecimal kiszallmny) {
		this.kiszallmny = kiszallmny;
	}

	@Column(name = "B_MEGJ", length = 100)
	public String getB_megj() {
		return this.b_megj;
	}

	public void setB_megj(String b_megj) {
		this.b_megj = b_megj;
	}

	@Column(name = "NUTREND")
	public boolean isNutrend() {
		return this.nutrend;
	}

	public void setNutrend(boolean nutrend) {
		this.nutrend = nutrend;
	}

	@Column(name = "SAJATSZAM", length = 20)
	public String getSajatszam() {
		return this.sajatszam;
	}

	public void setSajatszam(String sajatszam) {
		this.sajatszam = sajatszam;
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

	@Column(name = "GYSZAM2", length = 40)
	public String getGyszam2() {
		return this.gyszam2;
	}

	public void setGyszam2(String gyszam2) {
		this.gyszam2 = gyszam2;
	}

	@Column(name = "SZLEVSORSZ", length = 15)
	public String getSzlevsorsz() {
		return this.szlevsorsz;
	}

	public void setSzlevsorsz(String szlevsorsz) {
		this.szlevsorsz = szlevsorsz;
	}

	@Column(name = "SZLEVKELT", length = 19)
	public Date getSzlevkelt() {
		return this.szlevkelt;
	}

	public void setSzlevkelt(Date szlevkelt) {
		this.szlevkelt = szlevkelt;
	}

	@Column(name = "SZAMLSORSZ", length = 15)
	public String getSzamlsorsz() {
		return this.szamlsorsz;
	}

	public void setSzamlsorsz(String szamlsorsz) {
		this.szamlsorsz = szamlsorsz;
	}

	@Column(name = "SZAMLKELT", length = 19)
	public Date getSzamlkelt() {
		return this.szamlkelt;
	}

	public void setSzamlkelt(Date szamlkelt) {
		this.szamlkelt = szamlkelt;
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

	@Column(name = "KULSOTELJ")
	public Integer getKulsotelj() {
		return kulsotelj;
	}

	public void setKulsotelj(Integer kulsotelj) {
		this.kulsotelj = kulsotelj;
	}

	@Column(name = "KIADMENNY")
	public BigDecimal getKiadmenny() {
		return kiadmenny;
	}

	public void setKiadmenny(BigDecimal kiadmenny) {
		this.kiadmenny = kiadmenny;
	}

	@Column(name = "SZABMENNY")
	public BigDecimal getSzabmenny() {
		return szabmenny;
	}

	public void setSzabmenny(BigDecimal szabmenny) {
		this.szabmenny = szabmenny;
	}

	@Column(name = "VARMENNY")
	public BigDecimal getVarmenny() {
		return varmenny;
	}

	public void setVarmenny(BigDecimal varmenny) {
		this.varmenny = varmenny;
	}

	@Column(name = "ELKMENNY")
	public BigDecimal getElkmenny() {
		return elkmenny;
	}

	public void setElkmenny(BigDecimal elkmenny) {
		this.elkmenny = elkmenny;
	}

	@Column(name = "CSOMMENNY")
	public BigDecimal getCsommenny() {
		return csommenny;
	}

	public void setCsommenny(BigDecimal csommenny) {
		this.csommenny = csommenny;
	}

	@Column(name = "MODMENNY")
	public BigDecimal getModmenny() {
		return modmenny;
	}

	public void setModmenny(BigDecimal modmenny) {
		this.modmenny = modmenny;
	}

	@Column(name = "KESZMENNY")
	public BigDecimal getKeszmenny() {
		return keszmenny;
	}

	public void setKeszmenny(BigDecimal keszmenny) {
		this.keszmenny = keszmenny;
	}

	@Column(name = "OSSZEMNNY")
	public BigDecimal getOsszemenny() {
		return osszemenny;
	}

	public void setOsszemenny(BigDecimal osszemenny) {
		this.osszemenny = osszemenny;
	}

	@Column(name = "MLSORSZAM")
	public String getMlsorszam() {
		return mlsorszam;
	}

	public void setMlsorszam(String mlsorszam) {
		this.mlsorszam = mlsorszam;
	}

	@Column(name = "BSRSZAM")
	public String getBsrszam() {
		return bsrszam;
	}

	public void setBsrszam(String bsrszam) {
		this.bsrszam = bsrszam;
	}

	@Column(name = "BSSORSZAM")
	public String getBssorszam() {
		return bssorszam;
	}

	public void setBssorszam(String bssorszam) {
		this.bssorszam = bssorszam;
	}

	@Column(name = "MENNY2")
	public Integer getMenny2() {
		return menny2;
	}

	public void setMenny2(Integer menny2) {
		this.menny2 = menny2;
	}

	@Column(name = "MENNY3")
	public Integer getMenny3() {
		return menny3;
	}

	public void setMenny3(Integer menny3) {
		this.menny3 = menny3;
	}

	@Column(name = "MENNY4")
	public Integer getMenny4() {
		return menny4;
	}

	public void setMenny4(Integer menny4) {
		this.menny4 = menny4;
	}

	@Column(name = "MENNY5")
	public Integer getMenny5() {
		return menny5;
	}

	public void setMenny5(Integer menny5) {
		this.menny5 = menny5;
	}

	@Column(name = "LEFEJTO")
	public Integer getLefejto() {
		return lefejto;
	}

	public void setLefejto(Integer lefejto) {
		this.lefejto = lefejto;
	}

	@Column(name = "KEDVERTEK")
	public BigDecimal getKedvertek() {
		return kedvertek;
	}

	public void setKedvertek(BigDecimal kedvertek) {
		this.kedvertek = kedvertek;
	}

	@Column(name = "KEDVMEGN")
	public String getKedvmegn() {
		return kedvmegn;
	}

	public void setKedvmegn(String kedvmegn) {
		this.kedvmegn = kedvmegn;
	}

	@Column(name = "LEFEJTO2")
	public Integer getLefejto2() {
		return lefejto2;
	}

	public void setLefejto2(Integer lefejto2) {
		this.lefejto2 = lefejto2;
	}

	@Column(name = "GONGYAZON")
	public String getGongyazon() {
		return gongyazon;
	}

	public void setGongyazon(String gongyazon) {
		this.gongyazon = gongyazon;
	}

	@Column(name = "REGIO")
	public Integer getRegio() {
		return regio;
	}

	public void setRegio(Integer regio) {
		this.regio = regio;
	}

	@Column(name = "GYMENNY")
	public BigDecimal getGymenny() {
		return gymenny;
	}

	public void setGymenny(BigDecimal gymenny) {
		this.gymenny = gymenny;
	}

	@Column(name = "SARZSID")
	public Integer getSarzsid() {
		return sarzsid;
	}

	public void setSarzsid(Integer sarzsid) {
		this.sarzsid = sarzsid;
	}

	@Column(name = "TVIGHATIDO")
	public Date getTvighatido() {
		return tvighatido;
	}

	public void setTvighatido(Date tvighatido) {
		this.tvighatido = tvighatido;
	}

	@Column(name = "IDOZONA1")
	public Integer getIdozona1() {
		return idozona1;
	}

	public void setIdozona1(Integer idozona1) {
		this.idozona1 = idozona1;
	}

	@Column(name = "IDOZONA2")
	public Integer getIdozona2() {
		return idozona2;
	}

	public void setIdozona2(Integer idozona2) {
		this.idozona2 = idozona2;
	}

	@Column(name = "HATIDOTOL")
	public Integer getHatidotol() {
		return hatidotol;
	}

	public void setHatidotol(Integer hatidotol) {
		this.hatidotol = hatidotol;
	}

	@Column(name = "HATIDOIG")
	public Integer getHatidoig() {
		return hatidoig;
	}

	public void setHatidoig(Integer hatidoig) {
		this.hatidoig = hatidoig;
	}

}

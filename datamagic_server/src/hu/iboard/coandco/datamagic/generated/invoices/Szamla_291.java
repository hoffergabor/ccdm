package hu.iboard.coandco.datamagic.generated.invoices;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import hu.iboard.coandco.datamagic.generated.Megj;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Szamla generated by hbm2java
 */
@Entity
@Table(name = "SZAMLA_291")
public class Szamla_291 implements java.io.Serializable {

	private String sorszam;
	private String sorsztipk;
	private String elotag;
	private Date kelt;
	private Date telj;
	private Date esedkelt;
	private String eladoszla;
	private String fizmod;
	private String szlaszam;
	private String devnem;
	private BigDecimal arfolyam;
	private boolean sztorno;
	private Date fizetve;
	private Integer tipus;
	private Integer peldany;
	private Integer vevokod;
	private String vnev;
	private String vvaros;
	private String vcim;
	private String virsz;
	private String vadoszam;
	private String vfolyszam;
	private BigDecimal kedvezm;
	private String munkszam;
	private Integer munkszuk;
	private String kiall;
	private Integer nyelv;
	private String megj;
	private BigDecimal engedm;
	private boolean konyv;
	private boolean elkuld;
	private String jobbala;
	private String jobbtit;
	private String balala;
	private String baltit;
	private BigDecimal bruttosuly;
	private BigDecimal colli;
	private Date rfizetve;
	private BigDecimal rossz;
	private String jovair;
	private Integer szlasorsz;
	private BigDecimal gvtosszeg;
	private Date gvthatido;
	private BigDecimal netto;
	private BigDecimal brutto;
	private BigDecimal dnetto;
	private BigDecimal dbrutto;
	private BigDecimal drossz;
	private String sirsz;
	private String svaros;
	private String scim;
	private String stelefon;
	private String vfax;
	private String swiftcode;
	private String vorszag;
	private String levszamok;
	private String kozadoszam;
	private String iban;
	private boolean megmegj;
	private boolean nemfizetek;
	private String vkozadosz;
	private BigDecimal gvtrossz;
	private Integer fuvmkod;
	private Integer fuveszk;
	private BigDecimal nettosuly;
	private String snev;
	private BigDecimal afakerek;
	private String iktatoszam;
	private boolean hiteles;
	private boolean nokonyvel;
	private Date moddatum;
	private Integer modkod;
	private String cegnev;
	private Date realesed;
	private BigDecimal utafaalap;
	private BigDecimal utafa;
	private boolean isokirat;
	private boolean dmkstat;
	private Date dmkdat;
	private boolean forditott;
	private String tarsbizony;
//	private Set<Tetel> tetelSet = new HashSet<Tetel>(0);
	private Set<Megj> megjSet = new HashSet<Megj>(0);

	public Szamla_291() {
	}

	public Szamla_291(String sorszam) {
		this.sorszam = sorszam;
	}

	public Szamla_291(String sorszam, String sorsztipk, String elotag, Date kelt, Date telj, Date esedkelt,
			String eladoszla, String fizmod, String szlaszam, String devnem, BigDecimal arfolyam, boolean sztorno,
			Date fizetve, Integer tipus, Integer peldany, Integer vevokod, String vnev, String vvaros, String vcim,
			String virsz, String vadoszam, String vfolyszam, BigDecimal kedvezm, String munkszam, Integer munkszuk,
			String kiall, Integer nyelv, String megj, BigDecimal engedm, boolean konyv, boolean elkuld, String jobbala,
			String jobbtit, String balala, String baltit, BigDecimal bruttosuly, BigDecimal colli, Date rfizetve,
			BigDecimal rossz, String jovair, Integer szlasorsz, BigDecimal gvtosszeg, Date gvthatido, BigDecimal netto,
			BigDecimal brutto, BigDecimal dnetto, BigDecimal dbrutto, BigDecimal drossz, String sirsz, String svaros,
			String scim, String stelefon, String vfax, String swiftcode, String vorszag, String levszamok,
			String kozadoszam, String iban, boolean megmegj, boolean nemfizetek, String vkozadosz, BigDecimal gvtrossz,
			Integer fuvmkod, Integer fuveszk, BigDecimal nettosuly, String snev, BigDecimal afakerek,
			String iktatoszam, boolean hiteles, boolean nokonyvel, Date moddatum, Integer modkod, String cegnev,
			Date realesed, BigDecimal utafaalap, BigDecimal utafa, boolean isokirat, boolean dmkstat, Date dmkdat,
			boolean forditott, String tarsbizony, Set<Megj> megjSet) {
		this.sorszam = sorszam;
		this.sorsztipk = sorsztipk;
		this.elotag = elotag;
		this.kelt = kelt;
		this.telj = telj;
		this.esedkelt = esedkelt;
		this.eladoszla = eladoszla;
		this.fizmod = fizmod;
		this.szlaszam = szlaszam;
		this.devnem = devnem;
		this.arfolyam = arfolyam;
		this.sztorno = sztorno;
		this.fizetve = fizetve;
		this.tipus = tipus;
		this.peldany = peldany;
		this.vevokod = vevokod;
		this.vnev = vnev;
		this.vvaros = vvaros;
		this.vcim = vcim;
		this.virsz = virsz;
		this.vadoszam = vadoszam;
		this.vfolyszam = vfolyszam;
		this.kedvezm = kedvezm;
		this.munkszam = munkszam;
		this.munkszuk = munkszuk;
		this.kiall = kiall;
		this.nyelv = nyelv;
		this.megj = megj;
		this.engedm = engedm;
		this.konyv = konyv;
		this.elkuld = elkuld;
		this.jobbala = jobbala;
		this.jobbtit = jobbtit;
		this.balala = balala;
		this.baltit = baltit;
		this.bruttosuly = bruttosuly;
		this.colli = colli;
		this.rfizetve = rfizetve;
		this.rossz = rossz;
		this.jovair = jovair;
		this.szlasorsz = szlasorsz;
		this.gvtosszeg = gvtosszeg;
		this.gvthatido = gvthatido;
		this.netto = netto;
		this.brutto = brutto;
		this.dnetto = dnetto;
		this.dbrutto = dbrutto;
		this.drossz = drossz;
		this.sirsz = sirsz;
		this.svaros = svaros;
		this.scim = scim;
		this.stelefon = stelefon;
		this.vfax = vfax;
		this.swiftcode = swiftcode;
		this.vorszag = vorszag;
		this.levszamok = levszamok;
		this.kozadoszam = kozadoszam;
		this.iban = iban;
		this.megmegj = megmegj;
		this.nemfizetek = nemfizetek;
		this.vkozadosz = vkozadosz;
		this.gvtrossz = gvtrossz;
		this.fuvmkod = fuvmkod;
		this.fuveszk = fuveszk;
		this.nettosuly = nettosuly;
		this.snev = snev;
		this.afakerek = afakerek;
		this.iktatoszam = iktatoszam;
		this.hiteles = hiteles;
		this.nokonyvel = nokonyvel;
		this.moddatum = moddatum;
		this.modkod = modkod;
		this.cegnev = cegnev;
		this.realesed = realesed;
		this.utafaalap = utafaalap;
		this.utafa = utafa;
		this.isokirat = isokirat;
		this.dmkstat = dmkstat;
		this.dmkdat = dmkdat;
		this.forditott = forditott;
		this.tarsbizony = tarsbizony;
//		this.tetelSet = tetelSet;
		this.megjSet = megjSet;
	}

	@Id
	@Column(name = "SORSZAM", unique = true, nullable = false, length = 15)
	public String getSorszam() {
		return this.sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	@Column(name = "SORSZTIPK", length = 1)
	public String getSorsztipk() {
		return this.sorsztipk;
	}

	public void setSorsztipk(String sorsztipk) {
		this.sorsztipk = sorsztipk;
	}

	@Column(name = "ELOTAG", length = 10)
	public String getElotag() {
		return this.elotag;
	}

	public void setElotag(String elotag) {
		this.elotag = elotag;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "KELT", length = 19)
	public Date getKelt() {
		return this.kelt;
	}

	public void setKelt(Date kelt) {
		this.kelt = kelt;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "TELJ", length = 19)
	public Date getTelj() {
		return this.telj;
	}

	public void setTelj(Date telj) {
		this.telj = telj;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "ESEDKELT", length = 19)
	public Date getEsedkelt() {
		return this.esedkelt;
	}

	public void setEsedkelt(Date esedkelt) {
		this.esedkelt = esedkelt;
	}

	@Column(name = "ELADOSZLA", length = 26)
	public String getEladoszla() {
		return this.eladoszla;
	}

	public void setEladoszla(String eladoszla) {
		this.eladoszla = eladoszla;
	}

	@Column(name = "FIZMOD", length = 11)
	public String getFizmod() {
		return this.fizmod;
	}

	public void setFizmod(String fizmod) {
		this.fizmod = fizmod;
	}

	@Column(name = "SZLASZAM", length = 26)
	public String getSzlaszam() {
		return this.szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	@Column(name = "DEVNEM", length = 4)
	public String getDevnem() {
		return this.devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	@Column(name = "ARFOLYAM", precision = 17)
	public BigDecimal getArfolyam() {
		return this.arfolyam;
	}

	public void setArfolyam(BigDecimal arfolyam) {
		this.arfolyam = arfolyam;
	}

	@Column(name = "SZTORNO")
	public boolean isSztorno() {
		return this.sztorno;
	}

	public void setSztorno(boolean sztorno) {
		this.sztorno = sztorno;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "FIZETVE", length = 19)
	public Date getFizetve() {
		return this.fizetve;
	}

	public void setFizetve(Date fizetve) {
		this.fizetve = fizetve;
	}

	@Column(name = "TIPUS")
	public Integer getTipus() {
		return this.tipus;
	}

	public void setTipus(Integer tipus) {
		this.tipus = tipus;
	}

	@Column(name = "PELDANY")
	public Integer getPeldany() {
		return this.peldany;
	}

	public void setPeldany(Integer peldany) {
		this.peldany = peldany;
	}

	@Column(name = "VEVOKOD")
	public Integer getVevokod() {
		return this.vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	@Column(name = "VNEV", length = 100)
	public String getVnev() {
		return this.vnev;
	}

	public void setVnev(String vnev) {
		this.vnev = vnev;
	}

	@Column(name = "VVAROS", length = 25)
	public String getVvaros() {
		return this.vvaros;
	}

	public void setVvaros(String vvaros) {
		this.vvaros = vvaros;
	}

	@Column(name = "VCIM", length = 50)
	public String getVcim() {
		return this.vcim;
	}

	public void setVcim(String vcim) {
		this.vcim = vcim;
	}

	@Column(name = "VIRSZ", length = 10)
	public String getVirsz() {
		return this.virsz;
	}

	public void setVirsz(String virsz) {
		this.virsz = virsz;
	}

	@Column(name = "VADOSZAM", length = 13)
	public String getVadoszam() {
		return this.vadoszam;
	}

	public void setVadoszam(String vadoszam) {
		this.vadoszam = vadoszam;
	}

	@Column(name = "VFOLYSZAM", length = 8)
	public String getVfolyszam() {
		return this.vfolyszam;
	}

	public void setVfolyszam(String vfolyszam) {
		this.vfolyszam = vfolyszam;
	}

	@Column(name = "KEDVEZM", precision = 9, scale = 1)
	public BigDecimal getKedvezm() {
		return this.kedvezm;
	}

	public void setKedvezm(BigDecimal kedvezm) {
		this.kedvezm = kedvezm;
	}

	@Column(name = "MUNKSZAM", length = 50)
	public String getMunkszam() {
		return this.munkszam;
	}

	public void setMunkszam(String munkszam) {
		this.munkszam = munkszam;
	}

	@Column(name = "MUNKSZUK")
	public Integer getMunkszuk() {
		return this.munkszuk;
	}

	public void setMunkszuk(Integer munkszuk) {
		this.munkszuk = munkszuk;
	}

	@Column(name = "KIALL", length = 30)
	public String getKiall() {
		return this.kiall;
	}

	public void setKiall(String kiall) {
		this.kiall = kiall;
	}

	@Column(name = "NYELV")
	public Integer getNyelv() {
		return this.nyelv;
	}

	public void setNyelv(Integer nyelv) {
		this.nyelv = nyelv;
	}

	@Column(name = "MEGJ", length = 100)
	public String getMegj() {
		return this.megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}

	@Column(name = "ENGEDM", precision = 17)
	public BigDecimal getEngedm() {
		return this.engedm;
	}

	public void setEngedm(BigDecimal engedm) {
		this.engedm = engedm;
	}

	@Column(name = "KONYV")
	public boolean isKonyv() {
		return this.konyv;
	}

	public void setKonyv(boolean konyv) {
		this.konyv = konyv;
	}

	@Column(name = "ELKULD")
	public boolean isElkuld() {
		return this.elkuld;
	}

	public void setElkuld(boolean elkuld) {
		this.elkuld = elkuld;
	}

	@Column(name = "JOBBALA", length = 30)
	public String getJobbala() {
		return this.jobbala;
	}

	public void setJobbala(String jobbala) {
		this.jobbala = jobbala;
	}

	@Column(name = "JOBBTIT", length = 30)
	public String getJobbtit() {
		return this.jobbtit;
	}

	public void setJobbtit(String jobbtit) {
		this.jobbtit = jobbtit;
	}

	@Column(name = "BALALA", length = 30)
	public String getBalala() {
		return this.balala;
	}

	public void setBalala(String balala) {
		this.balala = balala;
	}

	@Column(name = "BALTIT", length = 30)
	public String getBaltit() {
		return this.baltit;
	}

	public void setBaltit(String baltit) {
		this.baltit = baltit;
	}

	@Column(name = "BRUTTOSULY", precision = 15, scale = 3)
	public BigDecimal getBruttosuly() {
		return this.bruttosuly;
	}

	public void setBruttosuly(BigDecimal bruttosuly) {
		this.bruttosuly = bruttosuly;
	}

	@Column(name = "COLLI", precision = 15, scale = 3)
	public BigDecimal getColli() {
		return this.colli;
	}

	public void setColli(BigDecimal colli) {
		this.colli = colli;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "RFIZETVE", length = 19)
	public Date getRfizetve() {
		return this.rfizetve;
	}

	public void setRfizetve(Date rfizetve) {
		this.rfizetve = rfizetve;
	}

	@Column(name = "ROSSZ", precision = 17)
	public BigDecimal getRossz() {
		return this.rossz;
	}

	public void setRossz(BigDecimal rossz) {
		this.rossz = rossz;
	}

	@Column(name = "JOVAIR", length = 1)
	public String getJovair() {
		return this.jovair;
	}

	public void setJovair(String jovair) {
		this.jovair = jovair;
	}

	@Column(name = "SZLASORSZ", precision = 6, scale = 0)
	public Integer getSzlasorsz() {
		return this.szlasorsz;
	}

	public void setSzlasorsz(Integer szlasorsz) {
		this.szlasorsz = szlasorsz;
	}

	@Column(name = "GVTOSSZEG", precision = 17)
	public BigDecimal getGvtosszeg() {
		return this.gvtosszeg;
	}

	public void setGvtosszeg(BigDecimal gvtosszeg) {
		this.gvtosszeg = gvtosszeg;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "GVTHATIDO", length = 19)
	public Date getGvthatido() {
		return this.gvthatido;
	}

	public void setGvthatido(Date gvthatido) {
		this.gvthatido = gvthatido;
	}

	@Column(name = "NETTO")
	public BigDecimal getNetto() {
		return this.netto;
	}

	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}

	@Column(name = "BRUTTO")
	public BigDecimal getBrutto() {
		return this.brutto;
	}

	public void setBrutto(BigDecimal brutto) {
		this.brutto = brutto;
	}

	@Column(name = "DNETTO")
	public BigDecimal getDnetto() {
		return this.dnetto;
	}

	public void setDnetto(BigDecimal dnetto) {
		this.dnetto = dnetto;
	}

	@Column(name = "DBRUTTO")
	public BigDecimal getDbrutto() {
		return this.dbrutto;
	}

	public void setDbrutto(BigDecimal dbrutto) {
		this.dbrutto = dbrutto;
	}

	@Column(name = "DROSSZ", precision = 17)
	public BigDecimal getDrossz() {
		return this.drossz;
	}

	public void setDrossz(BigDecimal drossz) {
		this.drossz = drossz;
	}

	@Column(name = "SIRSZ", length = 10)
	public String getSirsz() {
		return this.sirsz;
	}

	public void setSirsz(String sirsz) {
		this.sirsz = sirsz;
	}

	@Column(name = "SVAROS", length = 25)
	public String getSvaros() {
		return this.svaros;
	}

	public void setSvaros(String svaros) {
		this.svaros = svaros;
	}

	@Column(name = "SCIM", length = 50)
	public String getScim() {
		return this.scim;
	}

	public void setScim(String scim) {
		this.scim = scim;
	}

	@Column(name = "STELEFON", length = 13)
	public String getStelefon() {
		return this.stelefon;
	}

	public void setStelefon(String stelefon) {
		this.stelefon = stelefon;
	}

	@Column(name = "VFAX", length = 13)
	public String getVfax() {
		return this.vfax;
	}

	public void setVfax(String vfax) {
		this.vfax = vfax;
	}

	@Column(name = "SWIFTCODE", length = 15)
	public String getSwiftcode() {
		return this.swiftcode;
	}

	public void setSwiftcode(String swiftcode) {
		this.swiftcode = swiftcode;
	}

	@Column(name = "VORSZAG", length = 30)
	public String getVorszag() {
		return this.vorszag;
	}

	public void setVorszag(String vorszag) {
		this.vorszag = vorszag;
	}

	@Column(name = "LEVSZAMOK", length = 50)
	public String getLevszamok() {
		return this.levszamok;
	}

	public void setLevszamok(String levszamok) {
		this.levszamok = levszamok;
	}

	@Column(name = "KOZADOSZAM", length = 20)
	public String getKozadoszam() {
		return this.kozadoszam;
	}

	public void setKozadoszam(String kozadoszam) {
		this.kozadoszam = kozadoszam;
	}

	@Column(name = "IBAN", length = 4)
	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	@Column(name = "MEGMEGJ")
	public boolean isMegmegj() {
		return this.megmegj;
	}

	public void setMegmegj(boolean megmegj) {
		this.megmegj = megmegj;
	}

	@Column(name = "NEMFIZETEK")
	public boolean isNemfizetek() {
		return this.nemfizetek;
	}

	public void setNemfizetek(boolean nemfizetek) {
		this.nemfizetek = nemfizetek;
	}

	@Column(name = "VKOZADOSZ", length = 20)
	public String getVkozadosz() {
		return this.vkozadosz;
	}

	public void setVkozadosz(String vkozadosz) {
		this.vkozadosz = vkozadosz;
	}

	@Column(name = "GVTROSSZ", precision = 17)
	public BigDecimal getGvtrossz() {
		return this.gvtrossz;
	}

	public void setGvtrossz(BigDecimal gvtrossz) {
		this.gvtrossz = gvtrossz;
	}

	@Column(name = "FUVMKOD")
	public Integer getFuvmkod() {
		return this.fuvmkod;
	}

	public void setFuvmkod(Integer fuvmkod) {
		this.fuvmkod = fuvmkod;
	}

	@Column(name = "FUVESZK")
	public Integer getFuveszk() {
		return this.fuveszk;
	}

	public void setFuveszk(Integer fuveszk) {
		this.fuveszk = fuveszk;
	}

	@Column(name = "NETTOSULY", precision = 15, scale = 3)
	public BigDecimal getNettosuly() {
		return this.nettosuly;
	}

	public void setNettosuly(BigDecimal nettosuly) {
		this.nettosuly = nettosuly;
	}

	@Column(name = "SNEV", length = 50)
	public String getSnev() {
		return this.snev;
	}

	public void setSnev(String snev) {
		this.snev = snev;
	}

	@Column(name = "AFAKEREK", precision = 17)
	public BigDecimal getAfakerek() {
		return this.afakerek;
	}

	public void setAfakerek(BigDecimal afakerek) {
		this.afakerek = afakerek;
	}

	@Column(name = "IKTATOSZAM", length = 20)
	public String getIktatoszam() {
		return this.iktatoszam;
	}

	public void setIktatoszam(String iktatoszam) {
		this.iktatoszam = iktatoszam;
	}

	@Column(name = "HITELES")
	public boolean isHiteles() {
		return this.hiteles;
	}

	public void setHiteles(boolean hiteles) {
		this.hiteles = hiteles;
	}

	@Column(name = "NOKONYVEL")
	public boolean isNokonyvel() {
		return this.nokonyvel;
	}

	public void setNokonyvel(boolean nokonyvel) {
		this.nokonyvel = nokonyvel;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "MODDATUM", length = 19)
	public Date getModdatum() {
		return this.moddatum;
	}

	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}

	@Column(name = "MODKOD")
	public Integer getModkod() {
		return this.modkod;
	}

	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}

	@Column(name = "CEGNEV", length = 50)
	public String getCegnev() {
		return this.cegnev;
	}

	public void setCegnev(String cegnev) {
		this.cegnev = cegnev;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "REALESED", length = 19)
	public Date getRealesed() {
		return this.realesed;
	}

	public void setRealesed(Date realesed) {
		this.realesed = realesed;
	}

	@Column(name = "UTAFAALAP", precision = 17)
	public BigDecimal getUtafaalap() {
		return this.utafaalap;
	}

	public void setUtafaalap(BigDecimal utafaalap) {
		this.utafaalap = utafaalap;
	}

	@Column(name = "UTAFA", precision = 17)
	public BigDecimal getUtafa() {
		return this.utafa;
	}

	public void setUtafa(BigDecimal utafa) {
		this.utafa = utafa;
	}

	@Column(name = "ISOKIRAT")
	public boolean isIsokirat() {
		return this.isokirat;
	}

	public void setIsokirat(boolean isokirat) {
		this.isokirat = isokirat;
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

	@Column(name = "FORDITOTT")
	public boolean isForditott() {
		return this.forditott;
	}

	public void setForditott(boolean forditott) {
		this.forditott = forditott;
	}

	@Column(name = "TARSBIZONY", length = 15)
	public String getTarsbizony() {
		return this.tarsbizony;
	}

	public void setTarsbizony(String tarsbizony) {
		this.tarsbizony = tarsbizony;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "szamla")
//	public Set<Tetel> getTetelSet() {
//		return this.tetelSet;
//	}
//
//	public void setTetelSet(Set<Tetel> tetelSet) {
//		this.tetelSet = tetelSet;
//	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "szamla")
	public Set<Megj> getMegjSet() {
		return this.megjSet;
	}

	public void setMegjSet(Set<Megj> megjSet) {
		this.megjSet = megjSet;
	}

}

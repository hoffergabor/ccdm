package hu.iboard.coandco.datamagic.model.partner;

import hu.iboard.coandco.datamagic.model.vevocsop.Vevocsop;

import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "PARTNER")
public class Partner implements java.io.Serializable {

	private static final long serialVersionUID = 3893560008701907416L;
	
	private Integer vevokod;
	private String nev;
	private String kapcstart;
	private String varos;
	private String cim;
	private String irsz;
	private String szallcim;
	private String tel1;
	private String tel2;
	private String fax1;
	private String fax2;
	private String email;
	private String adoszam;
	private String folyszam;
	private Integer fizhal;
	private String fizmod;
	private BigDecimal kedvez;
	private String szirsz;
	private String szvaros;
	private String szcim;
	private BigDecimal hitlimit;
	private BigDecimal tartozas;
	private Date engdtol;
	private Date engdig;
	private BigDecimal engszaz;
	private Integer engtip;
	private Long vcsopkod;
	private Vevocsop vevocsop;
	private Integer ugyint;
	private boolean justone;
	private String szlaszam;
	private Integer szallkod;
	private String orszag;
	private Integer alapbkod;
	private Integer atvhkod;
	private Integer fuvmkod;
	private boolean megjfile;
	private String vkozadosz;
	private Integer nyelv;
	private Date uszla;
	private BigDecimal uossz;
	private Integer fuveszk;
	private Date uszall;
	private Date urendel;
	private boolean szlevvszla;
	private String honlap;
	private String vevokartya;
	private Integer szlfizeto;
	private BigDecimal spmlimit;
	private BigDecimal spmegyenl;
	private Integer tvpontja;
	private Integer tvosszpont;
	private String ugyvezeto;
	private String ugyvtel;
	private String ugyvemail;
	private String kapcstel;
	private String kapcsemail;
	private String cegjegyzek;
	private String banknev;
	private Date szuldatum;
	private BigDecimal mkedv1;
	private BigDecimal mkedv2;
	private BigDecimal mkedv3;
	private BigDecimal mkedv4;
	private BigDecimal mkedv5;
	private BigDecimal mkedv6;
	private BigDecimal mkedv7;
	private BigDecimal mkedv8;
	private BigDecimal mkedv9;
	private BigDecimal mkedv10;
	private BigDecimal mkedv11;
	private BigDecimal mkedv12;
	private BigDecimal mkedv13;
	private BigDecimal mkedv14;
	private BigDecimal mkedv15;
	private String uszlasor;
	private String szerzodes;
	private String kapcscim;
	private String levirsz;
	private String levvaros;
	private String levcim;
	private String megmegj;
	private boolean korlevel;
	private Long igenynap;
	private Integer v2csopkod;
	private Date insdatum;
	private Integer inskod;
	private Date moddatum;
	private Integer modkod;
	private String levnev;
	private BigDecimal nterv1;
	private BigDecimal nterv2;
	private BigDecimal nterv3;
	private BigDecimal nterv4;
	private String szemigszam;
	private String pukapcs;
	private String putel;
	private String pufax;
	private boolean belsopart;
	private Integer fkbeuex;
	private String sznev;
	private boolean iseva;
	private String weblogin;
	private String webpassw;
	private boolean letiltva;
	private Integer moreazon1;
	private Integer moreazon2;
	private String sqlcegekid;
	private boolean passziv;
	private Integer id;

	public Partner() {
	}

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEVOKOD", unique = true, nullable = false)
	public int getVevokod() {
		return this.vevokod;
	}

	public void setVevokod(int vevokod) {
		this.vevokod = vevokod;
	}
	
	@Column(name = "NEV", length = 100)
	public String getNev() {
		return this.nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	@Column(name = "KAPCSTART", length = 30)
	public String getKapcstart() {
		return this.kapcstart;
	}

	public void setKapcstart(String kapcstart) {
		this.kapcstart = kapcstart;
	}

	@Column(name = "VAROS", length = 25)
	public String getVaros() {
		return this.varos;
	}

	public void setVaros(String varos) {
		this.varos = varos;
	}

	@Column(name = "CIM", length = 50)
	public String getCim() {
		return this.cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	@Column(name = "IRSZ", length = 10)
	public String getIrsz() {
		return this.irsz;
	}

	public void setIrsz(String irsz) {
		this.irsz = irsz;
	}

	@Column(name = "SZALLCIM", length = 50)
	public String getSzallcim() {
		return this.szallcim;
	}

	public void setSzallcim(String szallcim) {
		this.szallcim = szallcim;
	}

	@Column(name = "TEL1", length = 15)
	public String getTel1() {
		return this.tel1;
	}

	public void setTel1(String tel1) {
		this.tel1 = tel1;
	}

	@Column(name = "TEL2", length = 15)
	public String getTel2() {
		return this.tel2;
	}

	public void setTel2(String tel2) {
		this.tel2 = tel2;
	}

	@Column(name = "FAX1", length = 15)
	public String getFax1() {
		return this.fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	@Column(name = "FAX2", length = 13)
	public String getFax2() {
		return this.fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	@Column(name = "EMAIL", length = 40)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADOSZAM", length = 13)
	public String getAdoszam() {
		return this.adoszam;
	}

	public void setAdoszam(String adoszam) {
		this.adoszam = adoszam;
	}

	@Column(name = "FOLYSZAM", length = 8)
	public String getFolyszam() {
		return this.folyszam;
	}

	public void setFolyszam(String folyszam) {
		this.folyszam = folyszam;
	}

	@Column(name = "FIZHAL")
	public Integer getFizhal() {
		return this.fizhal;
	}

	public void setFizhal(Integer fizhal) {
		this.fizhal = fizhal;
	}

	@Column(name = "FIZMOD", length = 11)
	public String getFizmod() {
		return this.fizmod;
	}

	public void setFizmod(String fizmod) {
		this.fizmod = fizmod;
	}

	@Column(name = "KEDVEZ", precision = 11, scale = 1)
	public BigDecimal getKedvez() {
		return this.kedvez;
	}

	public void setKedvez(BigDecimal kedvez) {
		this.kedvez = kedvez;
	}

	@Column(name = "SZIRSZ", length = 10)
	public String getSzirsz() {
		return this.szirsz;
	}

	public void setSzirsz(String szirsz) {
		this.szirsz = szirsz;
	}

	@Column(name = "SZVAROS", length = 25)
	public String getSzvaros() {
		return this.szvaros;
	}

	public void setSzvaros(String szvaros) {
		this.szvaros = szvaros;
	}

	@Column(name = "SZCIM", length = 50)
	public String getSzcim() {
		return this.szcim;
	}

	public void setSzcim(String szcim) {
		this.szcim = szcim;
	}

	@Column(name = "HITLIMIT", scale = 0)
	public BigDecimal getHitlimit() {
		return this.hitlimit;
	}

	public void setHitlimit(BigDecimal hitlimit) {
		this.hitlimit = hitlimit;
	}

	@Column(name = "TARTOZAS", precision = 20, scale = 0)
	public BigDecimal getTartozas() {
		return this.tartozas;
	}

	public void setTartozas(BigDecimal tartozas) {
		this.tartozas = tartozas;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "ENGDTOL", length = 19)
	public Date getEngdtol() {
		return this.engdtol;
	}

	public void setEngdtol(Date engdtol) {
		this.engdtol = engdtol;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "ENGDIG", length = 19)
	public Date getEngdig() {
		return this.engdig;
	}

	public void setEngdig(Date engdig) {
		this.engdig = engdig;
	}

	@Column(name = "ENGSZAZ", precision = 12)
	public BigDecimal getEngszaz() {
		return this.engszaz;
	}

	public void setEngszaz(BigDecimal engszaz) {
		this.engszaz = engszaz;
	}

	@Column(name = "ENGTIP", precision = 7, scale = 0)
	public Integer getEngtip() {
		return this.engtip;
	}

	public void setEngtip(Integer engtip) {
		this.engtip = engtip;
	}

	@Column(name = "VCSOPKOD", precision = 12, scale = 0)
	public Long getVcsopkod() {
		return this.vcsopkod;
	}

	public void setVcsopkod(Long vcsopkod) {
		this.vcsopkod = vcsopkod;
	}

	/*
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "VCSOPKOD", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public Vevocsop getVevocsop() {
		return this.vevocsop;
	}

	public void setVevocsop(Vevocsop vevocsop) {
		this.vevocsop = vevocsop;
	}
	*/
	@Column(name = "UGYINT")
	public Integer getUgyint() {
		return this.ugyint;
	}

	public void setUgyint(Integer ugyint) {
		this.ugyint = ugyint;
	}

	@Column(name = "JUSTONE")
	public boolean isJustone() {
		return this.justone;
	}

	public void setJustone(boolean justone) {
		this.justone = justone;
	}

	@Column(name = "SZLASZAM", length = 30)
	public String getSzlaszam() {
		return this.szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	@Column(name = "SZALLKOD")
	public Integer getSzallkod() {
		return this.szallkod;
	}

	public void setSzallkod(Integer szallkod) {
		this.szallkod = szallkod;
	}

	@Column(name = "ORSZAG", length = 30)
	public String getOrszag() {
		return this.orszag;
	}

	public void setOrszag(String orszag) {
		this.orszag = orszag;
	}

	@Column(name = "ALAPBKOD")
	public Integer getAlapbkod() {
		return this.alapbkod;
	}

	public void setAlapbkod(Integer alapbkod) {
		this.alapbkod = alapbkod;
	}

	@Column(name = "ATVHKOD")
	public Integer getAtvhkod() {
		return this.atvhkod;
	}

	public void setAtvhkod(Integer atvhkod) {
		this.atvhkod = atvhkod;
	}

	@Column(name = "FUVMKOD")
	public Integer getFuvmkod() {
		return this.fuvmkod;
	}

	public void setFuvmkod(Integer fuvmkod) {
		this.fuvmkod = fuvmkod;
	}

	@Column(name = "MEGJFILE")
	public boolean isMegjfile() {
		return this.megjfile;
	}

	public void setMegjfile(boolean megjfile) {
		this.megjfile = megjfile;
	}

	@Column(name = "VKOZADOSZ", length = 20)
	public String getVkozadosz() {
		return this.vkozadosz;
	}

	public void setVkozadosz(String vkozadosz) {
		this.vkozadosz = vkozadosz;
	}

	@Column(name = "NYELV", precision = 7, scale = 0)
	public Integer getNyelv() {
		return this.nyelv;
	}

	public void setNyelv(Integer nyelv) {
		this.nyelv = nyelv;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "USZLA", length = 19)
	public Date getUszla() {
		return this.uszla;
	}

	public void setUszla(Date uszla) {
		this.uszla = uszla;
	}

	@Column(name = "UOSSZ")
	public BigDecimal getUossz() {
		return this.uossz;
	}

	public void setUossz(BigDecimal uossz) {
		this.uossz = uossz;
	}

	@Column(name = "FUVESZK")
	public Integer getFuveszk() {
		return this.fuveszk;
	}

	public void setFuveszk(Integer fuveszk) {
		this.fuveszk = fuveszk;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "USZALL", length = 19)
	public Date getUszall() {
		return this.uszall;
	}

	public void setUszall(Date uszall) {
		this.uszall = uszall;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "URENDEL", length = 19)
	public Date getUrendel() {
		return this.urendel;
	}

	public void setUrendel(Date urendel) {
		this.urendel = urendel;
	}

	@Column(name = "SZLEVVSZLA")
	public boolean isSzlevvszla() {
		return this.szlevvszla;
	}

	public void setSzlevvszla(boolean szlevvszla) {
		this.szlevvszla = szlevvszla;
	}

	@Column(name = "HONLAP", length = 50)
	public String getHonlap() {
		return this.honlap;
	}

	public void setHonlap(String honlap) {
		this.honlap = honlap;
	}

	@Column(name = "VEVOKARTYA", length = 13)
	public String getVevokartya() {
		return this.vevokartya;
	}

	public void setVevokartya(String vevokartya) {
		this.vevokartya = vevokartya;
	}

	@Column(name = "SZLFIZETO")
	public Integer getSzlfizeto() {
		return this.szlfizeto;
	}

	public void setSzlfizeto(Integer szlfizeto) {
		this.szlfizeto = szlfizeto;
	}

	@Column(name = "SPMLIMIT")
	public BigDecimal getSpmlimit() {
		return this.spmlimit;
	}

	public void setSpmlimit(BigDecimal spmlimit) {
		this.spmlimit = spmlimit;
	}

	@Column(name = "SPMEGYENL")
	public BigDecimal getSpmegyenl() {
		return this.spmegyenl;
	}

	public void setSpmegyenl(BigDecimal spmegyenl) {
		this.spmegyenl = spmegyenl;
	}

	@Column(name = "TVPONTJA")
	public Integer getTvpontja() {
		return this.tvpontja;
	}

	public void setTvpontja(Integer tvpontja) {
		this.tvpontja = tvpontja;
	}

	@Column(name = "TVOSSZPONT")
	public Integer getTvosszpont() {
		return this.tvosszpont;
	}

	public void setTvosszpont(Integer tvosszpont) {
		this.tvosszpont = tvosszpont;
	}

	@Column(name = "UGYVEZETO", length = 30)
	public String getUgyvezeto() {
		return this.ugyvezeto;
	}

	public void setUgyvezeto(String ugyvezeto) {
		this.ugyvezeto = ugyvezeto;
	}

	@Column(name = "UGYVTEL", length = 15)
	public String getUgyvtel() {
		return this.ugyvtel;
	}

	public void setUgyvtel(String ugyvtel) {
		this.ugyvtel = ugyvtel;
	}

	@Column(name = "UGYVEMAIL", length = 40)
	public String getUgyvemail() {
		return this.ugyvemail;
	}

	public void setUgyvemail(String ugyvemail) {
		this.ugyvemail = ugyvemail;
	}

	@Column(name = "KAPCSTEL", length = 15)
	public String getKapcstel() {
		return this.kapcstel;
	}

	public void setKapcstel(String kapcstel) {
		this.kapcstel = kapcstel;
	}

	@Column(name = "KAPCSEMAIL", length = 40)
	public String getKapcsemail() {
		return this.kapcsemail;
	}

	public void setKapcsemail(String kapcsemail) {
		this.kapcsemail = kapcsemail;
	}

	@Column(name = "CEGJEGYZEK", length = 15)
	public String getCegjegyzek() {
		return this.cegjegyzek;
	}

	public void setCegjegyzek(String cegjegyzek) {
		this.cegjegyzek = cegjegyzek;
	}

	@Column(name = "BANKNEV", length = 40)
	public String getBanknev() {
		return this.banknev;
	}

	public void setBanknev(String banknev) {
		this.banknev = banknev;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "SZULDATUM", length = 19)
	public Date getSzuldatum() {
		return this.szuldatum;
	}

	public void setSzuldatum(Date szuldatum) {
		this.szuldatum = szuldatum;
	}

	@Column(name = "MKEDV1", precision = 13)
	public BigDecimal getMkedv1() {
		return this.mkedv1;
	}

	public void setMkedv1(BigDecimal mkedv1) {
		this.mkedv1 = mkedv1;
	}

	@Column(name = "MKEDV2", precision = 13)
	public BigDecimal getMkedv2() {
		return this.mkedv2;
	}

	public void setMkedv2(BigDecimal mkedv2) {
		this.mkedv2 = mkedv2;
	}

	@Column(name = "MKEDV3", precision = 13)
	public BigDecimal getMkedv3() {
		return this.mkedv3;
	}

	public void setMkedv3(BigDecimal mkedv3) {
		this.mkedv3 = mkedv3;
	}

	@Column(name = "MKEDV4", precision = 13)
	public BigDecimal getMkedv4() {
		return this.mkedv4;
	}

	public void setMkedv4(BigDecimal mkedv4) {
		this.mkedv4 = mkedv4;
	}

	@Column(name = "MKEDV5", precision = 13)
	public BigDecimal getMkedv5() {
		return this.mkedv5;
	}

	public void setMkedv5(BigDecimal mkedv5) {
		this.mkedv5 = mkedv5;
	}

	@Column(name = "MKEDV6", precision = 13)
	public BigDecimal getMkedv6() {
		return this.mkedv6;
	}

	public void setMkedv6(BigDecimal mkedv6) {
		this.mkedv6 = mkedv6;
	}

	@Column(name = "MKEDV7", precision = 13)
	public BigDecimal getMkedv7() {
		return this.mkedv7;
	}

	public void setMkedv7(BigDecimal mkedv7) {
		this.mkedv7 = mkedv7;
	}

	@Column(name = "MKEDV8", precision = 13)
	public BigDecimal getMkedv8() {
		return this.mkedv8;
	}

	public void setMkedv8(BigDecimal mkedv8) {
		this.mkedv8 = mkedv8;
	}

	@Column(name = "MKEDV9", precision = 13)
	public BigDecimal getMkedv9() {
		return this.mkedv9;
	}

	public void setMkedv9(BigDecimal mkedv9) {
		this.mkedv9 = mkedv9;
	}

	@Column(name = "MKEDV10", precision = 13)
	public BigDecimal getMkedv10() {
		return this.mkedv10;
	}

	public void setMkedv10(BigDecimal mkedv10) {
		this.mkedv10 = mkedv10;
	}

	@Column(name = "MKEDV11", precision = 13)
	public BigDecimal getMkedv11() {
		return this.mkedv11;
	}

	public void setMkedv11(BigDecimal mkedv11) {
		this.mkedv11 = mkedv11;
	}

	@Column(name = "MKEDV12", precision = 13)
	public BigDecimal getMkedv12() {
		return this.mkedv12;
	}

	public void setMkedv12(BigDecimal mkedv12) {
		this.mkedv12 = mkedv12;
	}

	@Column(name = "MKEDV13", precision = 13)
	public BigDecimal getMkedv13() {
		return this.mkedv13;
	}

	public void setMkedv13(BigDecimal mkedv13) {
		this.mkedv13 = mkedv13;
	}

	@Column(name = "MKEDV14", precision = 13)
	public BigDecimal getMkedv14() {
		return this.mkedv14;
	}

	public void setMkedv14(BigDecimal mkedv14) {
		this.mkedv14 = mkedv14;
	}

	@Column(name = "MKEDV15", precision = 13)
	public BigDecimal getMkedv15() {
		return this.mkedv15;
	}

	public void setMkedv15(BigDecimal mkedv15) {
		this.mkedv15 = mkedv15;
	}

	@Column(name = "USZLASOR", length = 15)
	public String getUszlasor() {
		return this.uszlasor;
	}

	public void setUszlasor(String uszlasor) {
		this.uszlasor = uszlasor;
	}

	@Column(name = "SZERZODES", length = 15)
	public String getSzerzodes() {
		return this.szerzodes;
	}

	public void setSzerzodes(String szerzodes) {
		this.szerzodes = szerzodes;
	}

	@Column(name = "KAPCSCIM", length = 50)
	public String getKapcscim() {
		return this.kapcscim;
	}

	public void setKapcscim(String kapcscim) {
		this.kapcscim = kapcscim;
	}

	@Column(name = "LEVIRSZ", length = 10)
	public String getLevirsz() {
		return this.levirsz;
	}

	public void setLevirsz(String levirsz) {
		this.levirsz = levirsz;
	}

	@Column(name = "LEVVAROS", length = 25)
	public String getLevvaros() {
		return this.levvaros;
	}

	public void setLevvaros(String levvaros) {
		this.levvaros = levvaros;
	}

	@Column(name = "LEVCIM", length = 50)
	public String getLevcim() {
		return this.levcim;
	}

	public void setLevcim(String levcim) {
		this.levcim = levcim;
	}

	@Column(name = "MEGMEGJ", length = 20)
	public String getMegmegj() {
		return this.megmegj;
	}

	public void setMegmegj(String megmegj) {
		this.megmegj = megmegj;
	}

	@Column(name = "KORLEVEL")
	public boolean isKorlevel() {
		return this.korlevel;
	}

	public void setKorlevel(boolean korlevel) {
		this.korlevel = korlevel;
	}

	@Column(name = "IGENYNAP", precision = 11, scale = 0)
	public Long getIgenynap() {
		return this.igenynap;
	}

	public void setIgenynap(Long igenynap) {
		this.igenynap = igenynap;
	}

	@Column(name = "V2CSOPKOD")
	public Integer getV2csopkod() {
		return this.v2csopkod;
	}

	public void setV2csopkod(Integer v2csopkod) {
		this.v2csopkod = v2csopkod;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "INSDATUM", length = 19)
	public Date getInsdatum() {
		return this.insdatum;
	}

	public void setInsdatum(Date insdatum) {
		this.insdatum = insdatum;
	}

	@Column(name = "INSKOD")
	public Integer getInskod() {
		return this.inskod;
	}

	public void setInskod(Integer inskod) {
		this.inskod = inskod;
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

	@Column(name = "LEVNEV", length = 50)
	public String getLevnev() {
		return this.levnev;
	}

	public void setLevnev(String levnev) {
		this.levnev = levnev;
	}

	@Column(name = "NTERV1")
	public BigDecimal getNterv1() {
		return this.nterv1;
	}

	public void setNterv1(BigDecimal nterv1) {
		this.nterv1 = nterv1;
	}

	@Column(name = "NTERV2")
	public BigDecimal getNterv2() {
		return this.nterv2;
	}

	public void setNterv2(BigDecimal nterv2) {
		this.nterv2 = nterv2;
	}

	@Column(name = "NTERV3")
	public BigDecimal getNterv3() {
		return this.nterv3;
	}

	public void setNterv3(BigDecimal nterv3) {
		this.nterv3 = nterv3;
	}

	@Column(name = "NTERV4")
	public BigDecimal getNterv4() {
		return this.nterv4;
	}

	public void setNterv4(BigDecimal nterv4) {
		this.nterv4 = nterv4;
	}

	@Column(name = "SZEMIGSZAM", length = 15)
	public String getSzemigszam() {
		return this.szemigszam;
	}

	public void setSzemigszam(String szemigszam) {
		this.szemigszam = szemigszam;
	}

	@Column(name = "PUKAPCS", length = 30)
	public String getPukapcs() {
		return this.pukapcs;
	}

	public void setPukapcs(String pukapcs) {
		this.pukapcs = pukapcs;
	}

	@Column(name = "PUTEL", length = 15)
	public String getPutel() {
		return this.putel;
	}

	public void setPutel(String putel) {
		this.putel = putel;
	}

	@Column(name = "PUFAX", length = 15)
	public String getPufax() {
		return this.pufax;
	}

	public void setPufax(String pufax) {
		this.pufax = pufax;
	}

	@Column(name = "BELSOPART")
	public boolean isBelsopart() {
		return this.belsopart;
	}

	public void setBelsopart(boolean belsopart) {
		this.belsopart = belsopart;
	}

	@Column(name = "FKBEUEX", precision = 7, scale = 0)
	public Integer getFkbeuex() {
		return this.fkbeuex;
	}

	public void setFkbeuex(Integer fkbeuex) {
		this.fkbeuex = fkbeuex;
	}

	@Column(name = "SZNEV", length = 50)
	public String getSznev() {
		return this.sznev;
	}

	public void setSznev(String sznev) {
		this.sznev = sznev;
	}

	@Column(name = "ISEVA")
	public boolean isIseva() {
		return this.iseva;
	}

	public void setIseva(boolean iseva) {
		this.iseva = iseva;
	}

	@Column(name = "WEBLOGIN", length = 40)
	public String getWeblogin() {
		return this.weblogin;
	}

	public void setWeblogin(String weblogin) {
		this.weblogin = weblogin;
	}

	@Column(name = "WEBPASSW", length = 40)
	public String getWebpassw() {
		return this.webpassw;
	}

	public void setWebpassw(String webpassw) {
		this.webpassw = webpassw;
	}

	@Column(name = "LETILTVA")
	public boolean isLetiltva() {
		return this.letiltva;
	}

	public void setLetiltva(boolean letiltva) {
		this.letiltva = letiltva;
	}

	@Column(name = "MOREAZON1")
	public Integer getMoreazon1() {
		return this.moreazon1;
	}

	public void setMoreazon1(Integer moreazon1) {
		this.moreazon1 = moreazon1;
	}

	@Column(name = "MOREAZON2")
	public Integer getMoreazon2() {
		return this.moreazon2;
	}

	public void setMoreazon2(Integer moreazon2) {
		this.moreazon2 = moreazon2;
	}

	@Column(name = "SQLCEGEKID", length = 10)
	public String getSqlcegekid() {
		return this.sqlcegekid;
	}

	public void setSqlcegekid(String sqlcegekid) {
		this.sqlcegekid = sqlcegekid;
	}

	@Column(name = "PASSZIV")
	public boolean isPassziv() {
		return this.passziv;
	}

	public void setPassziv(boolean passziv) {
		this.passziv = passziv;
	}

	@Column(name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "VCSOPKOD", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public Vevocsop getVevocsop() {
		return this.vevocsop;
	}

	public void setVevocsop(Vevocsop vevocsop) {
		this.vevocsop = vevocsop;
	}
}
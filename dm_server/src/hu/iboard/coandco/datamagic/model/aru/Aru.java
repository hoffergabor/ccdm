package hu.iboard.coandco.datamagic.model.aru;

import hu.iboard.coandco.datamagic.model.afakodok.Afakodok;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ARU")
public class Aru implements java.io.Serializable {

	private static final long serialVersionUID = -298827913864253225L;
	private Integer arukod;
	private String cikkszam;
	private String cikksz2;
	private Integer raktar;
	private String megn;
	private Integer szjvvtsz;
	private String szjvtsz;
	private Integer afakod;
	private BigDecimal var;
	private BigDecimal var2;
	private BigDecimal ear;
	private BigDecimal ear2;
	private BigDecimal ear3;
	private BigDecimal ear4;
	private BigDecimal engedm;
	private String meegys;
	private BigDecimal menny;
	private String megj;
	private String fkszam;
	private BigDecimal mins;
	private BigDecimal maxs;
	private BigDecimal jelzo;
	private boolean arvalt;
	private String megn1;
	private String helykod;
	private String devnem;
	private String garido;
	private BigDecimal rendelt;
	private BigDecimal foglalt;
	private String eankod;
	private String fkszam1;
	private BigDecimal csopkod;
	private BigDecimal minrend;
	private BigDecimal maxrend;
	private Date engdtol;
	private Date engdig;
	private BigDecimal engszaz;
	private boolean kesztermek;
	private BigDecimal engar;
	private BigDecimal dear1;
	private BigDecimal dear2;
	private BigDecimal dear3;
	private BigDecimal dear4;
	private BigDecimal beszar;
	private Integer beszall;
	private String megn2;
	private String megn3;
	private Integer kfelarkod;
	private boolean ezgongy;
	private boolean engtiltva;
	private Date utsocsok;
	private Date utsonov;
	private BigDecimal suly;
	private String sulyegys;
	private boolean megjfile;
	private BigDecimal ear5;
	private BigDecimal ear6;
	private BigDecimal ear7;
	private BigDecimal ear8;
	private BigDecimal ear9;
	private BigDecimal ear10;
	private BigDecimal ear11;
	private BigDecimal ear12;
	private BigDecimal ear13;
	private BigDecimal ear14;
	private BigDecimal ear15;
	private BigDecimal unovegys;
	private BigDecimal ucsokkegys;
	private Date uvdat;
	private Date uedat;
	private String azon3;
	private BigDecimal rcpmenny;
	private String azon4;
	private String azon5;
	private String azon6;
	private String azon7;
	private boolean webshop;
	private Date egyartdat;
	private boolean passziv;
	private boolean wpsv;
	private boolean ezszolg;
	private boolean lehetnulla;
	private String fkszam2;
	private boolean lrendre;
	private BigDecimal megys2;
	private String egys2;
	private BigDecimal megys3;
	private String egys3;
	private Long gyartido;
	private BigDecimal fifoear;
	private String megmegj;
	private String fkszam3;
	private String fkszam4;
	private BigDecimal csomag;
	private boolean neaddki;
	private String szarmorsz;
	private String azon8;
	private String azon9;
	private String azon10;
	private String azon11;
	private String azon12;
	private Integer sortkod;
	private Date insdatum;
	private Integer inskod;
	private Date moddatum;
	private Integer modkod;
	private boolean kozvszolg;
	private boolean tdij;
	private BigDecimal tdijszaz;
	private boolean rendelheto;
	private boolean rakoltseg;
	private boolean vankep;
	private boolean vanurl;
	private boolean kellgysz;
	private boolean moregysz;
	private String fkszam5;
	private boolean invisible;
	private Integer fordafatip;
	private boolean tobbszintu;
	private Afakodok afakodok;
	private String termegys;

	public Aru() {
	}

	public Aru(int arukod) {
		this.arukod = arukod;
	}

	public Aru(String megn) {
		this.megn = megn;
	}

	@Id
	@Column(name = "ARUKOD", unique = true, nullable = false)
	public Integer getArukod() {
		return this.arukod;
	}

	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}

	@Column(name = "CIKKSZAM", length = 20)
	public String getCikkszam() {
		return this.cikkszam;
	}

	public void setCikkszam(String cikkszam) {
		this.cikkszam = cikkszam;
	}

	@Column(name = "CIKKSZ2", length = 25)
	public String getCikksz2() {
		return this.cikksz2;
	}

	public void setCikksz2(String cikksz2) {
		this.cikksz2 = cikksz2;
	}

	@Column(name = "RAKTAR")
	public Integer getRaktar() {
		return this.raktar;
	}

	public void setRaktar(Integer raktar) {
		this.raktar = raktar;
	}

	@Column(name = "MEGN", length = 50)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
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

	@Column(name = "AFAKOD")
	public Integer getAfakod() {
		return this.afakod;
	}

	public void setAfakod(Integer afakod) {
		this.afakod = afakod;
	}

	@Column(name = "VAR", scale = 4)
	public BigDecimal getVar() {
		return this.var;
	}

	public void setVar(BigDecimal var) {
		this.var = var;
	}

	@Column(name = "VAR2", scale = 4)
	public BigDecimal getVar2() {
		return this.var2;
	}

	public void setVar2(BigDecimal var2) {
		this.var2 = var2;
	}

	@Column(name = "EAR")
	public BigDecimal getEar() {
		return this.ear;
	}

	public void setEar(BigDecimal ear) {
		this.ear = ear;
	}

	@Column(name = "EAR2")
	public BigDecimal getEar2() {
		return this.ear2;
	}

	public void setEar2(BigDecimal ear2) {
		this.ear2 = ear2;
	}

	@Column(name = "EAR3")
	public BigDecimal getEar3() {
		return this.ear3;
	}

	public void setEar3(BigDecimal ear3) {
		this.ear3 = ear3;
	}

	@Column(name = "EAR4")
	public BigDecimal getEar4() {
		return this.ear4;
	}

	public void setEar4(BigDecimal ear4) {
		this.ear4 = ear4;
	}

	@Column(name = "ENGEDM")
	public BigDecimal getEngedm() {
		return this.engedm;
	}

	public void setEngedm(BigDecimal engedm) {
		this.engedm = engedm;
	}

	@Column(name = "MEEGYS", length = 10)
	public String getMeegys() {
		return this.meegys;
	}

	public void setMeegys(String meegys) {
		this.meegys = meegys;
	}

	@Column(name = "MENNY", scale = 3)
	public BigDecimal getMenny() {
		return this.menny;
	}

	public void setMenny(BigDecimal menny) {
		this.menny = menny;
	}

	@Column(name = "MEGJ", length = 50)
	public String getMegj() {
		return this.megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}

	@Column(name = "FKSZAM", length = 8)
	public String getFkszam() {
		return this.fkszam;
	}

	public void setFkszam(String fkszam) {
		this.fkszam = fkszam;
	}

	@Column(name = "MINS")
	public BigDecimal getMins() {
		return this.mins;
	}

	public void setMins(BigDecimal mins) {
		this.mins = mins;
	}

	@Column(name = "MAXS")
	public BigDecimal getMaxs() {
		return this.maxs;
	}

	public void setMaxs(BigDecimal maxs) {
		this.maxs = maxs;
	}

	@Column(name = "JELZO")
	public BigDecimal getJelzo() {
		return this.jelzo;
	}

	public void setJelzo(BigDecimal jelzo) {
		this.jelzo = jelzo;
	}

	@Column(name = "ARVALT")
	public boolean isArvalt() {
		return this.arvalt;
	}

	public void setArvalt(boolean arvalt) {
		this.arvalt = arvalt;
	}

	@Column(name = "MEGN1", length = 50)
	public String getMegn1() {
		return this.megn1;
	}

	public void setMegn1(String megn1) {
		this.megn1 = megn1;
	}

	@Column(name = "HELYKOD", length = 15)
	public String getHelykod() {
		return this.helykod;
	}

	public void setHelykod(String helykod) {
		this.helykod = helykod;
	}

	@Column(name = "DEVNEM", length = 4)
	public String getDevnem() {
		return this.devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	@Column(name = "GARIDO", length = 10)
	public String getGarido() {
		return this.garido;
	}

	public void setGarido(String garido) {
		this.garido = garido;
	}

	@Column(name = "RENDELT")
	public BigDecimal getRendelt() {
		return this.rendelt;
	}

	public void setRendelt(BigDecimal rendelt) {
		this.rendelt = rendelt;
	}

	@Column(name = "FOGLALT")
	public BigDecimal getFoglalt() {
		return this.foglalt;
	}

	public void setFoglalt(BigDecimal foglalt) {
		this.foglalt = foglalt;
	}

	@Column(name = "EANKOD", length = 20)
	public String getEankod() {
		return this.eankod;
	}

	public void setEankod(String eankod) {
		this.eankod = eankod;
	}

	@Column(name = "FKSZAM1", length = 8)
	public String getFkszam1() {
		return this.fkszam1;
	}

	public void setFkszam1(String fkszam1) {
		this.fkszam1 = fkszam1;
	}

	@Column(name = "CSOPKOD", precision = 12, scale = 0)
	public BigDecimal getCsopkod() {
		return this.csopkod;
	}

	public void setCsopkod(BigDecimal csopkod) {
		this.csopkod = csopkod;
	}

	@Column(name = "MINREND")
	public BigDecimal getMinrend() {
		return this.minrend;
	}

	public void setMinrend(BigDecimal minrend) {
		this.minrend = minrend;
	}

	@Column(name = "MAXREND")
	public BigDecimal getMaxrend() {
		return this.maxrend;
	}

	public void setMaxrend(BigDecimal maxrend) {
		this.maxrend = maxrend;
	}

	// @Temporal(TemporalType.DATE)
	@Column(name = "ENGDTOL", length = 19)
	public Date getEngdtol() {
		return this.engdtol;
	}

	public void setEngdtol(Date engdtol) {
		this.engdtol = engdtol;
	}

	// @Temporal(TemporalType.DATE)
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

	@Column(name = "KESZTERMEK")
	public boolean isKesztermek() {
		return this.kesztermek;
	}

	public void setKesztermek(boolean kesztermek) {
		this.kesztermek = kesztermek;
	}

	@Column(name = "ENGAR")
	public BigDecimal getEngar() {
		return this.engar;
	}

	public void setEngar(BigDecimal engar) {
		this.engar = engar;
	}

	@Column(name = "DEAR1")
	public BigDecimal getDear1() {
		return this.dear1;
	}

	public void setDear1(BigDecimal dear1) {
		this.dear1 = dear1;
	}

	@Column(name = "DEAR2")
	public BigDecimal getDear2() {
		return this.dear2;
	}

	public void setDear2(BigDecimal dear2) {
		this.dear2 = dear2;
	}

	@Column(name = "DEAR3")
	public BigDecimal getDear3() {
		return this.dear3;
	}

	public void setDear3(BigDecimal dear3) {
		this.dear3 = dear3;
	}

	@Column(name = "DEAR4")
	public BigDecimal getDear4() {
		return this.dear4;
	}

	public void setDear4(BigDecimal dear4) {
		this.dear4 = dear4;
	}

	@Column(name = "BESZAR", scale = 4)
	public BigDecimal getBeszar() {
		return this.beszar;
	}

	public void setBeszar(BigDecimal beszar) {
		this.beszar = beszar;
	}

	@Column(name = "BESZALL")
	public Integer getBeszall() {
		return this.beszall;
	}

	public void setBeszall(Integer beszall) {
		this.beszall = beszall;
	}

	@Column(name = "MEGN2", length = 50)
	public String getMegn2() {
		return this.megn2;
	}

	public void setMegn2(String megn2) {
		this.megn2 = megn2;
	}

	@Column(name = "MEGN3", length = 50)
	public String getMegn3() {
		return this.megn3;
	}

	public void setMegn3(String megn3) {
		this.megn3 = megn3;
	}

	@Column(name = "KFELARKOD", precision = 7, scale = 0)
	public Integer getKfelarkod() {
		return this.kfelarkod;
	}

	public void setKfelarkod(Integer kfelarkod) {
		this.kfelarkod = kfelarkod;
	}

	@Column(name = "EZGONGY")
	public boolean isEzgongy() {
		return this.ezgongy;
	}

	public void setEzgongy(boolean ezgongy) {
		this.ezgongy = ezgongy;
	}

	@Column(name = "ENGTILTVA")
	public boolean isEngtiltva() {
		return this.engtiltva;
	}

	public void setEngtiltva(boolean engtiltva) {
		this.engtiltva = engtiltva;
	}

	@Column(name = "UTSOCSOK", length = 19)
	public Date getUtsocsok() {
		return this.utsocsok;
	}

	public void setUtsocsok(Date utsocsok) {
		this.utsocsok = utsocsok;
	}

	@Column(name = "UTSONOV", length = 19)
	public Date getUtsonov() {
		return this.utsonov;
	}

	public void setUtsonov(Date utsonov) {
		this.utsonov = utsonov;
	}

	@Column(name = "SULY", precision = 15, scale = 3)
	public BigDecimal getSuly() {
		return this.suly;
	}

	public void setSuly(BigDecimal suly) {
		this.suly = suly;
	}

	@Column(name = "SULYEGYS", length = 5)
	public String getSulyegys() {
		return this.sulyegys;
	}

	public void setSulyegys(String sulyegys) {
		this.sulyegys = sulyegys;
	}

	@Column(name = "MEGJFILE")
	public boolean isMegjfile() {
		return this.megjfile;
	}

	public void setMegjfile(boolean megjfile) {
		this.megjfile = megjfile;
	}

	@Column(name = "EAR5")
	public BigDecimal getEar5() {
		return this.ear5;
	}

	public void setEar5(BigDecimal ear5) {
		this.ear5 = ear5;
	}

	@Column(name = "EAR6")
	public BigDecimal getEar6() {
		return this.ear6;
	}

	public void setEar6(BigDecimal ear6) {
		this.ear6 = ear6;
	}

	@Column(name = "EAR7")
	public BigDecimal getEar7() {
		return this.ear7;
	}

	public void setEar7(BigDecimal ear7) {
		this.ear7 = ear7;
	}

	@Column(name = "EAR8")
	public BigDecimal getEar8() {
		return this.ear8;
	}

	public void setEar8(BigDecimal ear8) {
		this.ear8 = ear8;
	}

	@Column(name = "EAR9")
	public BigDecimal getEar9() {
		return this.ear9;
	}

	public void setEar9(BigDecimal ear9) {
		this.ear9 = ear9;
	}

	@Column(name = "EAR10")
	public BigDecimal getEar10() {
		return this.ear10;
	}

	public void setEar10(BigDecimal ear10) {
		this.ear10 = ear10;
	}

	@Column(name = "EAR11")
	public BigDecimal getEar11() {
		return this.ear11;
	}

	public void setEar11(BigDecimal ear11) {
		this.ear11 = ear11;
	}

	@Column(name = "EAR12")
	public BigDecimal getEar12() {
		return this.ear12;
	}

	public void setEar12(BigDecimal ear12) {
		this.ear12 = ear12;
	}

	@Column(name = "EAR13")
	public BigDecimal getEar13() {
		return this.ear13;
	}

	public void setEar13(BigDecimal ear13) {
		this.ear13 = ear13;
	}

	@Column(name = "EAR14")
	public BigDecimal getEar14() {
		return this.ear14;
	}

	public void setEar14(BigDecimal ear14) {
		this.ear14 = ear14;
	}

	@Column(name = "EAR15")
	public BigDecimal getEar15() {
		return this.ear15;
	}

	public void setEar15(BigDecimal ear15) {
		this.ear15 = ear15;
	}

	@Column(name = "UNOVEGYS")
	public BigDecimal getUnovegys() {
		return this.unovegys;
	}

	public void setUnovegys(BigDecimal unovegys) {
		this.unovegys = unovegys;
	}

	@Column(name = "UCSOKKEGYS")
	public BigDecimal getUcsokkegys() {
		return this.ucsokkegys;
	}

	public void setUcsokkegys(BigDecimal ucsokkegys) {
		this.ucsokkegys = ucsokkegys;
	}

	@Column(name = "UVDAT", length = 19)
	public Date getUvdat() {
		return this.uvdat;
	}

	public void setUvdat(Date uvdat) {
		this.uvdat = uvdat;
	}

	@Column(name = "UEDAT", length = 19)
	public Date getUedat() {
		return this.uedat;
	}

	public void setUedat(Date uedat) {
		this.uedat = uedat;
	}

	@Column(name = "AZON3", length = 25)
	public String getAzon3() {
		return this.azon3;
	}

	public void setAzon3(String azon3) {
		this.azon3 = azon3;
	}

	@Column(name = "RCPMENNY", precision = 15)
	public BigDecimal getRcpmenny() {
		return this.rcpmenny;
	}

	public void setRcpmenny(BigDecimal rcpmenny) {
		this.rcpmenny = rcpmenny;
	}

	@Column(name = "AZON4", length = 25)
	public String getAzon4() {
		return this.azon4;
	}

	public void setAzon4(String azon4) {
		this.azon4 = azon4;
	}

	@Column(name = "AZON5", length = 25)
	public String getAzon5() {
		return this.azon5;
	}

	public void setAzon5(String azon5) {
		this.azon5 = azon5;
	}

	@Column(name = "AZON6", length = 25)
	public String getAzon6() {
		return this.azon6;
	}

	public void setAzon6(String azon6) {
		this.azon6 = azon6;
	}

	@Column(name = "AZON7", length = 25)
	public String getAzon7() {
		return this.azon7;
	}

	public void setAzon7(String azon7) {
		this.azon7 = azon7;
	}

	@Column(name = "WEBSHOP")
	public boolean isWebshop() {
		return this.webshop;
	}

	public void setWebshop(boolean webshop) {
		this.webshop = webshop;
	}

	@Column(name = "EGYARTDAT", length = 19)
	public Date getEgyartdat() {
		return this.egyartdat;
	}

	public void setEgyartdat(Date egyartdat) {
		this.egyartdat = egyartdat;
	}

	@Column(name = "PASSZIV")
	public boolean isPassziv() {
		return this.passziv;
	}

	public void setPassziv(boolean passziv) {
		this.passziv = passziv;
	}

	@Column(name = "WPSV")
	public boolean isWpsv() {
		return this.wpsv;
	}

	public void setWpsv(boolean wpsv) {
		this.wpsv = wpsv;
	}

	@Column(name = "EZSZOLG")
	public boolean isEzszolg() {
		return this.ezszolg;
	}

	public void setEzszolg(boolean ezszolg) {
		this.ezszolg = ezszolg;
	}

	@Column(name = "LEHETNULLA")
	public boolean isLehetnulla() {
		return this.lehetnulla;
	}

	public void setLehetnulla(boolean lehetnulla) {
		this.lehetnulla = lehetnulla;
	}

	@Column(name = "FKSZAM2", length = 8)
	public String getFkszam2() {
		return this.fkszam2;
	}

	public void setFkszam2(String fkszam2) {
		this.fkszam2 = fkszam2;
	}

	@Column(name = "LRENDRE")
	public boolean isLrendre() {
		return this.lrendre;
	}

	public void setLrendre(boolean lrendre) {
		this.lrendre = lrendre;
	}

	@Column(name = "MEGYS2", precision = 16, scale = 4)
	public BigDecimal getMegys2() {
		return this.megys2;
	}

	public void setMegys2(BigDecimal megys2) {
		this.megys2 = megys2;
	}

	@Column(name = "EGYS2", length = 10)
	public String getEgys2() {
		return this.egys2;
	}

	public void setEgys2(String egys2) {
		this.egys2 = egys2;
	}

	@Column(name = "MEGYS3", precision = 16, scale = 4)
	public BigDecimal getMegys3() {
		return this.megys3;
	}

	public void setMegys3(BigDecimal megys3) {
		this.megys3 = megys3;
	}

	@Column(name = "EGYS3", length = 10)
	public String getEgys3() {
		return this.egys3;
	}

	public void setEgys3(String egys3) {
		this.egys3 = egys3;
	}

	@Column(name = "GYARTIDO", precision = 11, scale = 0)
	public Long getGyartido() {
		return this.gyartido;
	}

	public void setGyartido(Long gyartido) {
		this.gyartido = gyartido;
	}

	@Column(name = "FIFOEAR")
	public BigDecimal getFifoear() {
		return this.fifoear;
	}

	public void setFifoear(BigDecimal fifoear) {
		this.fifoear = fifoear;
	}

	@Column(name = "MEGMEGJ", length = 20)
	public String getMegmegj() {
		return this.megmegj;
	}

	public void setMegmegj(String megmegj) {
		this.megmegj = megmegj;
	}

	@Column(name = "FKSZAM3", length = 8)
	public String getFkszam3() {
		return this.fkszam3;
	}

	public void setFkszam3(String fkszam3) {
		this.fkszam3 = fkszam3;
	}

	@Column(name = "FKSZAM4", length = 8)
	public String getFkszam4() {
		return this.fkszam4;
	}

	public void setFkszam4(String fkszam4) {
		this.fkszam4 = fkszam4;
	}

	@Column(name = "CSOMAG")
	public BigDecimal getCsomag() {
		return this.csomag;
	}

	public void setCsomag(BigDecimal csomag) {
		this.csomag = csomag;
	}

	@Column(name = "NEADDKI")
	public boolean isNeaddki() {
		return this.neaddki;
	}

	public void setNeaddki(boolean neaddki) {
		this.neaddki = neaddki;
	}

	@Column(name = "SZARMORSZ", length = 2)
	public String getSzarmorsz() {
		return this.szarmorsz;
	}

	public void setSzarmorsz(String szarmorsz) {
		this.szarmorsz = szarmorsz;
	}

	@Column(name = "AZON8", length = 25)
	public String getAzon8() {
		return this.azon8;
	}

	public void setAzon8(String azon8) {
		this.azon8 = azon8;
	}

	@Column(name = "AZON9", length = 25)
	public String getAzon9() {
		return this.azon9;
	}

	public void setAzon9(String azon9) {
		this.azon9 = azon9;
	}

	@Column(name = "AZON10", length = 25)
	public String getAzon10() {
		return this.azon10;
	}

	public void setAzon10(String azon10) {
		this.azon10 = azon10;
	}

	@Column(name = "AZON11", length = 25)
	public String getAzon11() {
		return this.azon11;
	}

	public void setAzon11(String azon11) {
		this.azon11 = azon11;
	}

	@Column(name = "AZON12", length = 25)
	public String getAzon12() {
		return this.azon12;
	}

	public void setAzon12(String azon12) {
		this.azon12 = azon12;
	}

	@Column(name = "SORTKOD")
	public Integer getSortkod() {
		return this.sortkod;
	}

	public void setSortkod(Integer sortkod) {
		this.sortkod = sortkod;
	}

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

	@Column(name = "KOZVSZOLG")
	public boolean isKozvszolg() {
		return this.kozvszolg;
	}

	public void setKozvszolg(boolean kozvszolg) {
		this.kozvszolg = kozvszolg;
	}

	@Column(name = "TDIJ")
	public boolean isTdij() {
		return this.tdij;
	}

	public void setTdij(boolean tdij) {
		this.tdij = tdij;
	}

	@Column(name = "TDIJSZAZ", precision = 18, scale = 3)
	public BigDecimal getTdijszaz() {
		return this.tdijszaz;
	}

	public void setTdijszaz(BigDecimal tdijszaz) {
		this.tdijszaz = tdijszaz;
	}

	@Column(name = "RENDELHETO")
	public boolean isRendelheto() {
		return this.rendelheto;
	}

	public void setRendelheto(boolean rendelheto) {
		this.rendelheto = rendelheto;
	}

	@Column(name = "RAKOLTSEG")
	public boolean isRakoltseg() {
		return this.rakoltseg;
	}

	public void setRakoltseg(boolean rakoltseg) {
		this.rakoltseg = rakoltseg;
	}

	@Column(name = "VANKEP")
	public boolean isVankep() {
		return this.vankep;
	}

	public void setVankep(boolean vankep) {
		this.vankep = vankep;
	}

	@Column(name = "VANURL")
	public boolean isVanurl() {
		return this.vanurl;
	}

	public void setVanurl(boolean vanurl) {
		this.vanurl = vanurl;
	}

	@Column(name = "KELLGYSZ")
	public boolean isKellgysz() {
		return this.kellgysz;
	}

	public void setKellgysz(boolean kellgysz) {
		this.kellgysz = kellgysz;
	}

	@Column(name = "MOREGYSZ")
	public boolean isMoregysz() {
		return this.moregysz;
	}

	public void setMoregysz(boolean moregysz) {
		this.moregysz = moregysz;
	}

	@Column(name = "FKSZAM5", length = 8)
	public String getFkszam5() {
		return this.fkszam5;
	}

	public void setFkszam5(String fkszam5) {
		this.fkszam5 = fkszam5;
	}

	@Column(name = "INVISIBLE")
	public boolean isInvisible() {
		return this.invisible;
	}

	public void setInvisible(boolean invisible) {
		this.invisible = invisible;
	}

	@Column(name = "FORDAFATIP")
	public Integer getFordafatip() {
		return this.fordafatip;
	}

	public void setFordafatip(Integer fordafatip) {
		this.fordafatip = fordafatip;
	}

	@Column(name = "TOBBSZINTU")
	public boolean isTobbszintu() {
		return this.tobbszintu;
	}

	public void setTobbszintu(boolean tobbszintu) {
		this.tobbszintu = tobbszintu;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "AFAKOD", name = "afakod", insertable = false, updatable = false)
	public Afakodok getAfakodok() {
		return afakodok;
	}

	public void setAfakodok(Afakodok afakodok) {
		this.afakodok = afakodok;
	}

	@Column(name = "TERMEGYS")
	public String getTermegys() {
		return termegys;
	}

	public void setTermegys(String termegys) {
		this.termegys = termegys;
	}

}

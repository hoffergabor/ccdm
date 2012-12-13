package hu.iboard.coandco.datamagic.model.aru;

import hu.iboard.coandco.datamagic.model.afakodok.Afakodok;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ARU_KISKER")
@NamedNativeQueries({
@NamedNativeQuery(name = "GET_ARU_BY_LIKE_NAME", query = "call GET_ARU_BY_LIKE_NAME(:megnevezes)", resultClass = AruKisker.class),
@NamedNativeQuery(name = "GET_ARU_BY_CIKKCSOP_MEGN", query = "call GET_ARU_BY_CIKKCSOP_MEGN(:megnevezes)", resultClass = AruKisker.class)
})
public class AruKisker implements java.io.Serializable {

	private static final long serialVersionUID = -298827913864253225L;
	private Integer arukod;
	private String cikkszam;
	private String cikksz2;
	private Integer raktar;
	private String megn;
	private Integer szjvvtsz;
	private String szjvtsz;
	private Integer afakod;
	private Afakodok afakodok;
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
	private Boolean arvalt;
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
	private Boolean kesztermek;
	private BigDecimal engar;
	private BigDecimal dear1;
	private BigDecimal dear2;
	private BigDecimal dear3;
	private BigDecimal dear4;
	private BigDecimal beszar;
	private Integer beszall;
	private String megn2;
	private String megn3;
	private BigDecimal kfelarkod;
	private Boolean ezgongy;
	private Boolean engtiltva;
	private Date utsocsok;
	private Date utsonov;
	private BigDecimal suly;
	private String sulyegys;
	private Boolean megjfile;
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
	private Boolean webshop;
	private Date egyartdat;
	private Boolean passziv;
	private Boolean wpsv;
	private Boolean ezszolg;
	private Boolean lehetnulla;
	private String fkszam2;
	private Boolean lrendre;
	private BigDecimal megys2;
	private String egys2;
	private BigDecimal megys3;
	private String egys3;
	private BigDecimal gyartido;
	private BigDecimal fifoear;
	private String megmegj;
	private String fkszam3;
	private String fkszam4;
	private BigDecimal csomag;
	private Boolean neaddki;
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
	private Boolean kozvszolg;
	private Boolean tdij;
	private BigDecimal tdijszaz;
	private Boolean rendelheto;
	private Boolean rakoltseg;
	private Boolean vankep;
	private Boolean vanurl;
	private Boolean kellgysz;
	private Boolean moregysz;
	private String fkszam5;
	private Boolean invisible;
	private Integer fordafatip;
	private Boolean tobbszintu;
	private Boolean folyamatos;
	private BigDecimal bonusz;
	private Boolean kiemelt;
	private String eankod2;
	private String eankod3;
	private Integer mkod1;
	private Integer mkod2;
	private Integer mkod3;
	private Integer mkod4;
	private Integer mkod5;
	private Integer mkod6;
	private Integer mkod7;
	private Integer mkod8;
	private Integer mkod9;
	private Integer mkod10;
	private BigDecimal tbtam;
	private Boolean bermunka;
	private BigDecimal nsuly;
	private Integer alapdim1;
	private Integer alapdim2;
	private Integer alapdim3;
	private Integer alapdim4;
	private Integer alapdim5;
	private Integer aruid;
	private Boolean isjovedeki;
	private Integer j_csoport;
	private BigDecimal j_balling;
	private BigDecimal j_adomert;
	private BigDecimal j_szeszfok;
	private BigDecimal j_literben;
	private BigDecimal megys4;
	private String egys4;
	private String eankod4;
	private BigDecimal megys5;
	private String egys5;
	private String eankod5;
	private Integer lejarnap;
	private Integer ertnap;
	private String termegys;
	private Integer beszall2;
	private Integer gyarto;
	private BigDecimal teegszorzo;
	private BigDecimal kiszemenny;
	private BigDecimal j_mevalto;
	private Boolean adojegyes;
	private Boolean nemegal;
	private BigDecimal csom01;
	private BigDecimal csom02;
	private BigDecimal csom03;
	private BigDecimal csom04;
	private BigDecimal csom05;
	private BigDecimal csom06;
	private BigDecimal csom07;
	private BigDecimal kcsom01;
	private BigDecimal kcsom02;
	private BigDecimal kcsom03;
	private BigDecimal kcsom04;
	private BigDecimal kcsom05;
	private BigDecimal kcsom06;
	private BigDecimal kcsom07;
	private Boolean bertarol;
	private Boolean mekorlat;
	private BigDecimal mekorlegys;
	private Boolean akciosterm;
	private Boolean bonthato;
	private Boolean ajanlando;
	private Integer alaphely;
	private BigDecimal beszar2;
	private String arsormod;
	private BigDecimal varold;
	private String fkszam6;
	private BigDecimal imptdij01;
	private BigDecimal imptdij02;
	private BigDecimal imptdij03;
	private BigDecimal imptdij04;
	private BigDecimal imptdij05;
	private BigDecimal imptdij06;
	private Integer impktkod01;
	private Integer impktkod02;
	private Integer impktkod03;
	private Integer impktkod04;
	private Integer impktkod05;
	private Integer impktkod06;
	private Integer lastbesz;
	private Integer lastbesza;
	private Date lastbeszd;
	private Date lastbeszad;
	private BigDecimal csom08;
	private BigDecimal csom09;
	private BigDecimal csom10;
	private BigDecimal csom11;
	private BigDecimal csom12;
	private Integer egys3gongy;
	private Boolean uj;
	private String megjeng;
	private Integer projsablon;
	private BigDecimal nyersar;
	private BigDecimal berkolts;
	private BigDecimal onkolts;
	private Boolean nyersanyag;
	private Boolean ptgarmod;
	
	

	public AruKisker() {
	}

	public AruKisker(int arukod) {
		this.arukod = arukod;
	}

	public AruKisker(String megn) {
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

	@Column(name = "MEGN", length = 50)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Column(name = "EAR2")
	public BigDecimal getEar2() {
		return this.ear2;
	}

	public void setEar2(BigDecimal ear2) {
		this.ear2 = ear2;
	}

	@Column(name = "DEVNEM", length = 4)
	public String getDevnem() {
		return this.devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	@Column(name = "CSOPKOD", precision = 12, scale = 0)
	public BigDecimal getCsopkod() {
		return this.csopkod;
	}

	public void setCsopkod(BigDecimal csopkod) {
		this.csopkod = csopkod;
	}

	@Column(name = "MODDATUM", length = 19)
	public Date getModdatum() {
		return this.moddatum;
	}

	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}

	@Column(name = "MENNY")
	public BigDecimal getMenny() {
		return this.menny;
	}

	public void setMenny(BigDecimal menny) {
		this.menny = menny;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "afakod", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public Afakodok getAfakodok() {
		return afakodok;
	}

	public void setAfakodok(Afakodok afakodok) {
		this.afakodok = afakodok;
	}

	@Column(name = "INSDATUM")
	public Date getInsdatum() {
		return insdatum;
	}

	public void setInsdatum(Date insdatum) {
		this.insdatum = insdatum;
	}

	@Column(name = "KIEMELT")
	public Boolean getKiemelt() {
		return kiemelt;
	}

	public void setKiemelt(Boolean kiemelt) {
		this.kiemelt = kiemelt;
	}

	@Column(name = "WEBSHOP")
	public Boolean getWebshop() {
		return webshop;
	}

	public void setWebshop(Boolean webshop) {
		this.webshop = webshop;
	}
	
	@Column(name = "MEEGYS")
	public String getMeegys() {
		return meegys;
	}

	public void setMeegys(String meegys) {
		this.meegys = meegys;
	}
	
	@Column(name = "EAR6")
	public BigDecimal getEar6() {
		return this.ear6;
	}

	public void setEar6(BigDecimal ear6) {
		this.ear6 = ear6;
	}
	
	@Column(name = "EAR")
	public BigDecimal getEar() {
		return this.ear;
	}

	public void setEar(BigDecimal ear) {
		this.ear = ear;
	}

	@Column(name = "RAKTAR")
	public Integer getRaktar() {
		return raktar;
	}

	public void setRaktar(Integer raktar) {
		this.raktar = raktar;
	}

	@Column(name = "MEGN3")
	public String getMegn3() {
		return megn3;
	}

	public void setMegn3(String megn3) {
		this.megn3 = megn3;
	}

	@Column(name = "DEAR2")
	public BigDecimal getDear2() {
		return dear2;
	}

	public void setDear2(BigDecimal dear2) {
		this.dear2 = dear2;
	}

	@Column(name = "CIKKSZ2")
	public String getCikksz2() {
		return cikksz2;
	}

	public void setCikksz2(String cikksz2) {
		this.cikksz2 = cikksz2;
	}

	@Column(name = "SZJVVTSZ")
	public Integer getSzjvvtsz() {
		return szjvvtsz;
	}

	public void setSzjvvtsz(Integer szjvvtsz) {
		this.szjvvtsz = szjvvtsz;
	}

	@Column(name = "SZJVTSZ")
	public String getSzjvtsz() {
		return szjvtsz;
	}

	public void setSzjvtsz(String szjvtsz) {
		this.szjvtsz = szjvtsz;
	}

	@Column(name = "AFAKOD")
	public Integer getAfakod() {
		return afakod;
	}

	public void setAfakod(Integer afakod) {
		this.afakod = afakod;
	}

	@Column(name = "VAR")
	public BigDecimal getVar() {
		return var;
	}

	public void setVar(BigDecimal var) {
		this.var = var;
	}

	@Column(name = "VAR2")
	public BigDecimal getVar2() {
		return var2;
	}

	public void setVar2(BigDecimal var2) {
		this.var2 = var2;
	}

	@Column(name = "EAR3")
	public BigDecimal getEar3() {
		return ear3;
	}

	public void setEar3(BigDecimal ear3) {
		this.ear3 = ear3;
	}

	@Column(name = "EAR4")
	public BigDecimal getEar4() {
		return ear4;
	}

	public void setEar4(BigDecimal ear4) {
		this.ear4 = ear4;
	}

	@Column(name = "ENGEDM")
	public BigDecimal getEngedm() {
		return engedm;
	}

	public void setEngedm(BigDecimal engedm) {
		this.engedm = engedm;
	}

	@Column(name = "")
	public String getMegj() {
		return megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}

	@Column(name = "FKSZAM")
	public String getFkszam() {
		return fkszam;
	}

	public void setFkszam(String fkszam) {
		this.fkszam = fkszam;
	}

	@Column(name = "MINS")
	public BigDecimal getMins() {
		return mins;
	}

	public void setMins(BigDecimal mins) {
		this.mins = mins;
	}

	@Column(name = "MAXS")
	public BigDecimal getMaxs() {
		return maxs;
	}

	public void setMaxs(BigDecimal maxs) {
		this.maxs = maxs;
	}

	@Column(name = "")
	public BigDecimal getJelzo() {
		return jelzo;
	}

	public void setJelzo(BigDecimal jelzo) {
		this.jelzo = jelzo;
	}

	@Column(name = "ARVALT")
	public Boolean getArvalt() {
		return arvalt;
	}

	public void setArvalt(Boolean arvalt) {
		this.arvalt = arvalt;
	}

	@Column(name = "MEGN1")
	public String getMegn1() {
		return megn1;
	}

	public void setMegn1(String megn1) {
		this.megn1 = megn1;
	}

	@Column(name = "HELYKOD")
	public String getHelykod() {
		return helykod;
	}

	public void setHelykod(String helykod) {
		this.helykod = helykod;
	}

	@Column(name = "GARIDO")
	public String getGarido() {
		return garido;
	}

	public void setGarido(String garido) {
		this.garido = garido;
	}

	@Column(name = "RENDELT")
	public BigDecimal getRendelt() {
		return rendelt;
	}

	public void setRendelt(BigDecimal rendelt) {
		this.rendelt = rendelt;
	}

	@Column(name = "FOGLALT")
	public BigDecimal getFoglalt() {
		return foglalt;
	}

	public void setFoglalt(BigDecimal foglalt) {
		this.foglalt = foglalt;
	}

	@Column(name = "EANKOD")
	public String getEankod() {
		return eankod;
	}

	public void setEankod(String eankod) {
		this.eankod = eankod;
	}

	@Column(name = "FKSZAM1")
	public String getFkszam1() {
		return fkszam1;
	}

	public void setFkszam1(String fkszam1) {
		this.fkszam1 = fkszam1;
	}

	@Column(name = "")
	public BigDecimal getMinrend() {
		return minrend;
	}

	public void setMinrend(BigDecimal minrend) {
		this.minrend = minrend;
	}

	@Column(name = "MAXREND")
	public BigDecimal getMaxrend() {
		return maxrend;
	}

	public void setMaxrend(BigDecimal maxrend) {
		this.maxrend = maxrend;
	}

	@Column(name = "ENGDTOL")
	public Date getEngdtol() {
		return engdtol;
	}

	public void setEngdtol(Date engdtol) {
		this.engdtol = engdtol;
	}

	@Column(name = "ENGDIG")
	public Date getEngdig() {
		return engdig;
	}

	public void setEngdig(Date engdig) {
		this.engdig = engdig;
	}

	@Column(name = "ENGSZAZ")
	public BigDecimal getEngszaz() {
		return engszaz;
	}

	public void setEngszaz(BigDecimal engszaz) {
		this.engszaz = engszaz;
	}

	@Column(name = "KESZTERMEK")
	public Boolean getKesztermek() {
		return kesztermek;
	}

	public void setKesztermek(Boolean kesztermek) {
		this.kesztermek = kesztermek;
	}

	@Column(name = "ENGAR")
	public BigDecimal getEngar() {
		return engar;
	}

	public void setEngar(BigDecimal engar) {
		this.engar = engar;
	}

	@Column(name = "DEAR1")
	public BigDecimal getDear1() {
		return dear1;
	}

	public void setDear1(BigDecimal dear1) {
		this.dear1 = dear1;
	}

	@Column(name = "DEAR3")
	public BigDecimal getDear3() {
		return dear3;
	}

	public void setDear3(BigDecimal dear3) {
		this.dear3 = dear3;
	}

	@Column(name = "DEAR4")
	public BigDecimal getDear4() {
		return dear4;
	}

	public void setDear4(BigDecimal dear4) {
		this.dear4 = dear4;
	}

	@Column(name = "")
	public BigDecimal getBeszar() {
		return beszar;
	}

	public void setBeszar(BigDecimal beszar) {
		this.beszar = beszar;
	}

	@Column(name = "BESZALL")
	public Integer getBeszall() {
		return beszall;
	}

	public void setBeszall(Integer beszall) {
		this.beszall = beszall;
	}

	@Column(name = "MEGN2")
	public String getMegn2() {
		return megn2;
	}

	public void setMegn2(String megn2) {
		this.megn2 = megn2;
	}

	@Column(name = "KFELARKOD")
	public BigDecimal getKfelarkod() {
		return kfelarkod;
	}

	public void setKfelarkod(BigDecimal kfelarkod) {
		this.kfelarkod = kfelarkod;
	}

	@Column(name = "EZGONGY")
	public Boolean getEzgongy() {
		return ezgongy;
	}

	public void setEzgongy(Boolean ezgongy) {
		this.ezgongy = ezgongy;
	}

	@Column(name = "ENGTILTVA")
	public Boolean getEngtiltva() {
		return engtiltva;
	}

	public void setEngtiltva(Boolean engtiltva) {
		this.engtiltva = engtiltva;
	}

	@Column(name = "UTSOCSOK")
	public Date getUtsocsok() {
		return utsocsok;
	}

	public void setUtsocsok(Date utsocsok) {
		this.utsocsok = utsocsok;
	}

	@Column(name = "UTSONOV")
	public Date getUtsonov() {
		return utsonov;
	}

	public void setUtsonov(Date utsonov) {
		this.utsonov = utsonov;
	}

	@Column(name = "SULY")
	public BigDecimal getSuly() {
		return suly;
	}

	public void setSuly(BigDecimal suly) {
		this.suly = suly;
	}

	@Column(name = "SULYEGYS")
	public String getSulyegys() {
		return sulyegys;
	}

	public void setSulyegys(String sulyegys) {
		this.sulyegys = sulyegys;
	}

	@Column(name = "")
	public Boolean getMegjfile() {
		return megjfile;
	}

	public void setMegjfile(Boolean megjfile) {
		this.megjfile = megjfile;
	}

	@Column(name = "EAR5")
	public BigDecimal getEar5() {
		return ear5;
	}

	public void setEar5(BigDecimal ear5) {
		this.ear5 = ear5;
	}

	@Column(name = "EAR7")
	public BigDecimal getEar7() {
		return ear7;
	}

	public void setEar7(BigDecimal ear7) {
		this.ear7 = ear7;
	}

	@Column(name = "EAR8")
	public BigDecimal getEar8() {
		return ear8;
	}

	public void setEar8(BigDecimal ear8) {
		this.ear8 = ear8;
	}

	@Column(name = "EAR9")
	public BigDecimal getEar9() {
		return ear9;
	}

	public void setEar9(BigDecimal ear9) {
		this.ear9 = ear9;
	}

	@Column(name = "EAR10")
	public BigDecimal getEar10() {
		return ear10;
	}

	public void setEar10(BigDecimal ear10) {
		this.ear10 = ear10;
	}

	@Column(name = "EAR11")
	public BigDecimal getEar11() {
		return ear11;
	}

	public void setEar11(BigDecimal ear11) {
		this.ear11 = ear11;
	}

	@Column(name = "EAR12")
	public BigDecimal getEar12() {
		return ear12;
	}

	public void setEar12(BigDecimal ear12) {
		this.ear12 = ear12;
	}

	@Column(name = "EAR13")
	public BigDecimal getEar13() {
		return ear13;
	}

	public void setEar13(BigDecimal ear13) {
		this.ear13 = ear13;
	}

	@Column(name = "EAR14")
	public BigDecimal getEar14() {
		return ear14;
	}

	public void setEar14(BigDecimal ear14) {
		this.ear14 = ear14;
	}

	@Column(name = "")
	public BigDecimal getEar15() {
		return ear15;
	}

	public void setEar15(BigDecimal ear15) {
		this.ear15 = ear15;
	}

	@Column(name = "UNOVEGYS")
	public BigDecimal getUnovegys() {
		return unovegys;
	}

	public void setUnovegys(BigDecimal unovegys) {
		this.unovegys = unovegys;
	}

	@Column(name = "UCSOKKEGYS")
	public BigDecimal getUcsokkegys() {
		return ucsokkegys;
	}

	public void setUcsokkegys(BigDecimal ucsokkegys) {
		this.ucsokkegys = ucsokkegys;
	}

	@Column(name = "UVDAT")
	public Date getUvdat() {
		return uvdat;
	}

	public void setUvdat(Date uvdat) {
		this.uvdat = uvdat;
	}

	@Column(name = "UEDAT")
	public Date getUedat() {
		return uedat;
	}

	public void setUedat(Date uedat) {
		this.uedat = uedat;
	}

	@Column(name = "AZON3")
	public String getAzon3() {
		return azon3;
	}

	public void setAzon3(String azon3) {
		this.azon3 = azon3;
	}

	@Column(name = "RCPMENNY")
	public BigDecimal getRcpmenny() {
		return rcpmenny;
	}

	public void setRcpmenny(BigDecimal rcpmenny) {
		this.rcpmenny = rcpmenny;
	}

	@Column(name = "AZON4")
	public String getAzon4() {
		return azon4;
	}

	public void setAzon4(String azon4) {
		this.azon4 = azon4;
	}

	@Column(name = "AZON5")
	public String getAzon5() {
		return azon5;
	}

	public void setAzon5(String azon5) {
		this.azon5 = azon5;
	}

	@Column(name = "AZON6")
	public String getAzon6() {
		return azon6;
	}

	public void setAzon6(String azon6) {
		this.azon6 = azon6;
	}

	@Column(name = "AZON7")
	public String getAzon7() {
		return azon7;
	}

	public void setAzon7(String azon7) {
		this.azon7 = azon7;
	}

	@Column(name = "EGYARTDAT")
	public Date getEgyartdat() {
		return egyartdat;
	}

	public void setEgyartdat(Date egyartdat) {
		this.egyartdat = egyartdat;
	}

	@Column(name = "PASSZIV")
	public Boolean getPassziv() {
		return passziv;
	}

	public void setPassziv(Boolean passziv) {
		this.passziv = passziv;
	}

	@Column(name = "WPSV")
	public Boolean getWpsv() {
		return wpsv;
	}

	public void setWpsv(Boolean wpsv) {
		this.wpsv = wpsv;
	}

	@Column(name = "EZSZOLG")
	public Boolean getEzszolg() {
		return ezszolg;
	}

	public void setEzszolg(Boolean ezszolg) {
		this.ezszolg = ezszolg;
	}

	@Column(name = "LEHETNULLA")
	public Boolean getLehetnulla() {
		return lehetnulla;
	}

	public void setLehetnulla(Boolean leheta) {
		this.lehetnulla = leheta;
	}

	@Column(name = "FKSZAM2")
	public String getFkszam2() {
		return fkszam2;
	}

	public void setFkszam2(String fkszam2) {
		this.fkszam2 = fkszam2;
	}

	@Column(name = "LRENDRE")
	public Boolean getLrendre() {
		return lrendre;
	}

	public void setLrendre(Boolean lrendre) {
		this.lrendre = lrendre;
	}

	@Column(name = "MEGYS2")
	public BigDecimal getMegys2() {
		return megys2;
	}

	public void setMegys2(BigDecimal megys2) {
		this.megys2 = megys2;
	}

	@Column(name = "EGYS2")
	public String getEgys2() {
		return egys2;
	}

	public void setEgys2(String egys2) {
		this.egys2 = egys2;
	}

	@Column(name = "MEGYS3")
	public BigDecimal getMegys3() {
		return megys3;
	}

	public void setMegys3(BigDecimal megys3) {
		this.megys3 = megys3;
	}

	@Column(name = "EGYS3")
	public String getEgys3() {
		return egys3;
	}

	public void setEgys3(String egys3) {
		this.egys3 = egys3;
	}

	@Column(name = "GYARTIDO")
	public BigDecimal getGyartido() {
		return gyartido;
	}

	public void setGyartido(BigDecimal gyartido) {
		this.gyartido = gyartido;
	}

	@Column(name = "FIFOEAR")
	public BigDecimal getFifoear() {
		return fifoear;
	}

	public void setFifoear(BigDecimal fifoear) {
		this.fifoear = fifoear;
	}

	@Column(name = "MEGMEGJ")
	public String getMegmegj() {
		return megmegj;
	}

	public void setMegmegj(String megmegj) {
		this.megmegj = megmegj;
	}

	@Column(name = "FKSZAM3")
	public String getFkszam3() {
		return fkszam3;
	}

	public void setFkszam3(String fkszam3) {
		this.fkszam3 = fkszam3;
	}

	@Column(name = "FKSZAM4")
	public String getFkszam4() {
		return fkszam4;
	}

	public void setFkszam4(String fkszam4) {
		this.fkszam4 = fkszam4;
	}

	@Column(name = "CSOMAG")
	public BigDecimal getCsomag() {
		return csomag;
	}

	public void setCsomag(BigDecimal csomag) {
		this.csomag = csomag;
	}

	@Column(name = "NEADDKI")
	public Boolean getNeaddki() {
		return neaddki;
	}

	public void setNeaddki(Boolean neaddki) {
		this.neaddki = neaddki;
	}

	@Column(name = "SZARMORSZ")
	public String getSzarmorsz() {
		return szarmorsz;
	}

	public void setSzarmorsz(String szarmorsz) {
		this.szarmorsz = szarmorsz;
	}

	@Column(name = "AZON8")
	public String getAzon8() {
		return azon8;
	}

	public void setAzon8(String azon8) {
		this.azon8 = azon8;
	}

	@Column(name = "AZON9")
	public String getAzon9() {
		return azon9;
	}

	public void setAzon9(String azon9) {
		this.azon9 = azon9;
	}

	@Column(name = "AZON10")
	public String getAzon10() {
		return azon10;
	}

	public void setAzon10(String azon10) {
		this.azon10 = azon10;
	}

	@Column(name = "AZON11")
	public String getAzon11() {
		return azon11;
	}

	public void setAzon11(String azon11) {
		this.azon11 = azon11;
	}

	@Column(name = "AZON12")
	public String getAzon12() {
		return azon12;
	}

	public void setAzon12(String azon12) {
		this.azon12 = azon12;
	}

	@Column(name = "SORTKOD")
	public Integer getSortkod() {
		return sortkod;
	}

	public void setSortkod(Integer sortkod) {
		this.sortkod = sortkod;
	}

	@Column(name = "INSKOD")
	public Integer getInskod() {
		return inskod;
	}

	public void setInskod(Integer inskod) {
		this.inskod = inskod;
	}

	@Column(name = "MODKOD")
	public Integer getModkod() {
		return modkod;
	}

	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}

	@Column(name = "KOZVSZOLG")
	public Boolean getKozvszolg() {
		return kozvszolg;
	}

	public void setKozvszolg(Boolean kozvszolg) {
		this.kozvszolg = kozvszolg;
	}

	@Column(name = "TDIJ")
	public Boolean getTdij() {
		return tdij;
	}

	public void setTdij(Boolean tdij) {
		this.tdij = tdij;
	}

	@Column(name = "TDIJSZAZ")
	public BigDecimal getTdijszaz() {
		return tdijszaz;
	}

	public void setTdijszaz(BigDecimal tdijszaz) {
		this.tdijszaz = tdijszaz;
	}

	@Column(name = "RENDELHETO")
	public Boolean getRendelheto() {
		return rendelheto;
	}

	public void setRendelheto(Boolean rendelheto) {
		this.rendelheto = rendelheto;
	}

	@Column(name = "RAKOLTSEG")
	public Boolean getRakoltseg() {
		return rakoltseg;
	}

	public void setRakoltseg(Boolean rakoltseg) {
		this.rakoltseg = rakoltseg;
	}

	@Column(name = "VANKEP")
	public Boolean getVankep() {
		return vankep;
	}

	public void setVankep(Boolean vankep) {
		this.vankep = vankep;
	}

	@Column(name = "VANURL")
	public Boolean getVanurl() {
		return vanurl;
	}

	public void setVanurl(Boolean vanurl) {
		this.vanurl = vanurl;
	}

	@Column(name = "KELLGYSZ")
	public Boolean getKellgysz() {
		return kellgysz;
	}

	public void setKellgysz(Boolean kellgysz) {
		this.kellgysz = kellgysz;
	}

	@Column(name = "MOREGYSZ")
	public Boolean getMoregysz() {
		return moregysz;
	}

	public void setMoregysz(Boolean moregysz) {
		this.moregysz = moregysz;
	}

	@Column(name = "FKSZAM5")
	public String getFkszam5() {
		return fkszam5;
	}

	public void setFkszam5(String fkszam5) {
		this.fkszam5 = fkszam5;
	}

	@Column(name = "")
	public Boolean getInvisible() {
		return invisible;
	}

	public void setInvisible(Boolean invisible) {
		this.invisible = invisible;
	}

	@Column(name = "FORDAFATIP")
	public Integer getFordafatip() {
		return fordafatip;
	}

	public void setFordafatip(Integer fordafatip) {
		this.fordafatip = fordafatip;
	}

	@Column(name = "TOBBSZINTU")
	public Boolean getTobbszintu() {
		return tobbszintu;
	}

	public void setTobbszintu(Boolean tobbszintu) {
		this.tobbszintu = tobbszintu;
	}

	@Column(name = "")
	public Boolean getFolyamatos() {
		return folyamatos;
	}

	public void setFolyamatos(Boolean folyamatos) {
		this.folyamatos = folyamatos;
	}

	@Column(name = "BONUSZ")
	public BigDecimal getBonusz() {
		return bonusz;
	}

	public void setBonusz(BigDecimal bonusz) {
		this.bonusz = bonusz;
	}

	@Column(name = "EANKOD2")
	public String getEankod2() {
		return eankod2;
	}

	public void setEankod2(String eankod2) {
		this.eankod2 = eankod2;
	}

	@Column(name = "EANKOD3")
	public String getEankod3() {
		return eankod3;
	}

	public void setEankod3(String eankod3) {
		this.eankod3 = eankod3;
	}

	@Column(name = "MKOD1")
	public Integer getMkod1() {
		return mkod1;
	}

	public void setMkod1(Integer mkod1) {
		this.mkod1 = mkod1;
	}

	@Column(name = "MKOD2")
	public Integer getMkod2() {
		return mkod2;
	}

	public void setMkod2(Integer mkod2) {
		this.mkod2 = mkod2;
	}

	@Column(name = "MKOD3")
	public Integer getMkod3() {
		return mkod3;
	}

	public void setMkod3(Integer mkod3) {
		this.mkod3 = mkod3;
	}

	@Column(name = "MKOD4")
	public Integer getMkod4() {
		return mkod4;
	}

	public void setMkod4(Integer mkod4) {
		this.mkod4 = mkod4;
	}

	@Column(name = "MKOD5")
	public Integer getMkod5() {
		return mkod5;
	}

	public void setMkod5(Integer mkod5) {
		this.mkod5 = mkod5;
	}

	@Column(name = "MKOD6")
	public Integer getMkod6() {
		return mkod6;
	}

	public void setMkod6(Integer mkod6) {
		this.mkod6 = mkod6;
	}

	@Column(name = "MKOD7")
	public Integer getMkod7() {
		return mkod7;
	}

	public void setMkod7(Integer mkod7) {
		this.mkod7 = mkod7;
	}

	@Column(name = "MKOD8")
	public Integer getMkod8() {
		return mkod8;
	}

	public void setMkod8(Integer mkod8) {
		this.mkod8 = mkod8;
	}

	@Column(name = "MKOD9")
	public Integer getMkod9() {
		return mkod9;
	}

	public void setMkod9(Integer mkod9) {
		this.mkod9 = mkod9;
	}

	@Column(name = "MKOD10")
	public Integer getMkod10() {
		return mkod10;
	}

	public void setMkod10(Integer mkod10) {
		this.mkod10 = mkod10;
	}

	@Column(name = "TBTAM")
	public BigDecimal getTbtam() {
		return tbtam;
	}

	public void setTbtam(BigDecimal tbtam) {
		this.tbtam = tbtam;
	}

	@Column(name = "BERMUNKA")
	public Boolean getBermunka() {
		return bermunka;
	}

	public void setBermunka(Boolean bermunka) {
		this.bermunka = bermunka;
	}

	@Column(name = "NSULY")
	public BigDecimal getNsuly() {
		return nsuly;
	}

	public void setNsuly(BigDecimal nsuly) {
		this.nsuly = nsuly;
	}

	@Column(name = "ALAPDIM1")
	public Integer getAlapdim1() {
		return alapdim1;
	}

	public void setAlapdim1(Integer alapdim1) {
		this.alapdim1 = alapdim1;
	}

	@Column(name = "ALAPDIM2")
	public Integer getAlapdim2() {
		return alapdim2;
	}

	public void setAlapdim2(Integer alapdim2) {
		this.alapdim2 = alapdim2;
	}

	@Column(name = "ALAPDIM3")
	public Integer getAlapdim3() {
		return alapdim3;
	}

	public void setAlapdim3(Integer alapdim3) {
		this.alapdim3 = alapdim3;
	}

	@Column(name = "ALAPDIM4")
	public Integer getAlapdim4() {
		return alapdim4;
	}

	public void setAlapdim4(Integer alapdim4) {
		this.alapdim4 = alapdim4;
	}

	@Column(name = "ALAPDIM5")
	public Integer getAlapdim5() {
		return alapdim5;
	}

	public void setAlapdim5(Integer alapdim5) {
		this.alapdim5 = alapdim5;
	}

	@Column(name = "ARUID")
	public Integer getAruid() {
		return aruid;
	}

	public void setAruid(Integer aruid) {
		this.aruid = aruid;
	}

	@Column(name = "ISJOVEDEKI")
	public Boolean getIsjovedeki() {
		return isjovedeki;
	}

	public void setIsjovedeki(Boolean isjovedeki) {
		this.isjovedeki = isjovedeki;
	}

	@Column(name = "J_CSOPORT")
	public Integer getJ_csoport() {
		return j_csoport;
	}

	public void setJ_csoport(Integer j_csoport) {
		this.j_csoport = j_csoport;
	}

	@Column(name = "J_BALLING")
	public BigDecimal getJ_balling() {
		return j_balling;
	}

	public void setJ_balling(BigDecimal j_balling) {
		this.j_balling = j_balling;
	}

	@Column(name = "J_ADOMERT")
	public BigDecimal getJ_adomert() {
		return j_adomert;
	}

	public void setJ_adomert(BigDecimal j_adomert) {
		this.j_adomert = j_adomert;
	}

	@Column(name = "J_SZESZFOK")
	public BigDecimal getJ_szeszfok() {
		return j_szeszfok;
	}

	public void setJ_szeszfok(BigDecimal j_szeszfok) {
		this.j_szeszfok = j_szeszfok;
	}

	@Column(name = "J_LITERBEN")
	public BigDecimal getJ_literben() {
		return j_literben;
	}

	public void setJ_literben(BigDecimal j_literben) {
		this.j_literben = j_literben;
	}

	@Column(name = "MEGYS4")
	public BigDecimal getMegys4() {
		return megys4;
	}

	public void setMegys4(BigDecimal megys4) {
		this.megys4 = megys4;
	}

	@Column(name = "EGYS4")
	public String getEgys4() {
		return egys4;
	}

	public void setEgys4(String egys4) {
		this.egys4 = egys4;
	}

	@Column(name = "EANKOD4")
	public String getEankod4() {
		return eankod4;
	}

	public void setEankod4(String eankod4) {
		this.eankod4 = eankod4;
	}

	@Column(name = "MEGYS5")
	public BigDecimal getMegys5() {
		return megys5;
	}

	public void setMegys5(BigDecimal megys5) {
		this.megys5 = megys5;
	}

	@Column(name = "EGYS5")
	public String getEgys5() {
		return egys5;
	}

	public void setEgys5(String egys5) {
		this.egys5 = egys5;
	}

	@Column(name = "EANKOD5")
	public String getEankod5() {
		return eankod5;
	}

	public void setEankod5(String eankod5) {
		this.eankod5 = eankod5;
	}

	@Column(name = "LEJARNAP")
	public Integer getLejarnap() {
		return lejarnap;
	}

	public void setLejarnap(Integer lejarnap) {
		this.lejarnap = lejarnap;
	}

	@Column(name = "ERTNAP")
	public Integer getErtnap() {
		return ertnap;
	}

	public void setErtnap(Integer ertnap) {
		this.ertnap = ertnap;
	}

	@Column(name = "TERMEGYS")
	public String getTermegys() {
		return termegys;
	}

	public void setTermegys(String termegys) {
		this.termegys = termegys;
	}

	@Column(name = "BESZALL2")
	public Integer getBeszall2() {
		return beszall2;
	}

	public void setBeszall2(Integer beszall2) {
		this.beszall2 = beszall2;
	}

	@Column(name = "GYARTO")
	public Integer getGyarto() {
		return gyarto;
	}

	public void setGyarto(Integer gyarto) {
		this.gyarto = gyarto;
	}

	@Column(name = "")
	public BigDecimal getTeegszorzo() {
		return teegszorzo;
	}

	public void setTeegszorzo(BigDecimal teegszorzo) {
		this.teegszorzo = teegszorzo;
	}

	@Column(name = "KISZEMENNY")
	public BigDecimal getKiszemenny() {
		return kiszemenny;
	}

	public void setKiszemenny(BigDecimal kiszemenny) {
		this.kiszemenny = kiszemenny;
	}

	@Column(name = "J_MEVALTO")
	public BigDecimal getJ_mevalto() {
		return j_mevalto;
	}

	public void setJ_mevalto(BigDecimal j_mevalto) {
		this.j_mevalto = j_mevalto;
	}

	@Column(name = "ADOJEGYES")
	public Boolean getAdojegyes() {
		return adojegyes;
	}

	public void setAdojegyes(Boolean adojegyes) {
		this.adojegyes = adojegyes;
	}

	@Column(name = "NEMEGAL")
	public Boolean getNemegal() {
		return nemegal;
	}

	public void setNemegal(Boolean nemegal) {
		this.nemegal = nemegal;
	}

	@Column(name = "CSOM01")
	public BigDecimal getCsom01() {
		return csom01;
	}

	public void setCsom01(BigDecimal csom01) {
		this.csom01 = csom01;
	}

	@Column(name = "CSOM02")
	public BigDecimal getCsom02() {
		return csom02;
	}

	public void setCsom02(BigDecimal csom02) {
		this.csom02 = csom02;
	}

	@Column(name = "CSOM03")
	public BigDecimal getCsom03() {
		return csom03;
	}

	public void setCsom03(BigDecimal csom03) {
		this.csom03 = csom03;
	}

	@Column(name = "CSOM04")
	public BigDecimal getCsom04() {
		return csom04;
	}

	public void setCsom04(BigDecimal csom04) {
		this.csom04 = csom04;
	}

	@Column(name = "CSOM05")
	public BigDecimal getCsom05() {
		return csom05;
	}

	public void setCsom05(BigDecimal csom05) {
		this.csom05 = csom05;
	}

	@Column(name = "CSOM06")
	public BigDecimal getCsom06() {
		return csom06;
	}

	public void setCsom06(BigDecimal csom06) {
		this.csom06 = csom06;
	}

	@Column(name = "CSOM07")
	public BigDecimal getCsom07() {
		return csom07;
	}

	public void setCsom07(BigDecimal csom07) {
		this.csom07 = csom07;
	}

	@Column(name = "KCSOM01")
	public BigDecimal getKcsom01() {
		return kcsom01;
	}

	public void setKcsom01(BigDecimal kcsom01) {
		this.kcsom01 = kcsom01;
	}

	@Column(name = "KCSOM02")
	public BigDecimal getKcsom02() {
		return kcsom02;
	}

	public void setKcsom02(BigDecimal kcsom02) {
		this.kcsom02 = kcsom02;
	}

	@Column(name = "KCSOM03")
	public BigDecimal getKcsom03() {
		return kcsom03;
	}

	public void setKcsom03(BigDecimal kcsom03) {
		this.kcsom03 = kcsom03;
	}

	@Column(name = "KCSOM04")
	public BigDecimal getKcsom04() {
		return kcsom04;
	}

	public void setKcsom04(BigDecimal kcsom04) {
		this.kcsom04 = kcsom04;
	}

	@Column(name = "KCSOM05")
	public BigDecimal getKcsom05() {
		return kcsom05;
	}

	public void setKcsom05(BigDecimal kcsom05) {
		this.kcsom05 = kcsom05;
	}

	@Column(name = "KCSOM06")
	public BigDecimal getKcsom06() {
		return kcsom06;
	}

	public void setKcsom06(BigDecimal kcsom06) {
		this.kcsom06 = kcsom06;
	}

	@Column(name = "KCSOM07")
	public BigDecimal getKcsom07() {
		return kcsom07;
	}

	public void setKcsom07(BigDecimal kcsom07) {
		this.kcsom07 = kcsom07;
	}

	@Column(name = "BERTAROL")
	public Boolean getBertarol() {
		return bertarol;
	}

	public void setBertarol(Boolean bertarol) {
		this.bertarol = bertarol;
	}

	@Column(name = "MEKORLAT")
	public Boolean getMekorlat() {
		return mekorlat;
	}

	public void setMekorlat(Boolean mekorlat) {
		this.mekorlat = mekorlat;
	}

	@Column(name = "MEKORLEGYS")
	public BigDecimal getMekorlegys() {
		return mekorlegys;
	}

	public void setMekorlegys(BigDecimal mekorlegys) {
		this.mekorlegys = mekorlegys;
	}

	@Column(name = "AKCIOSTERM")
	public Boolean getAkciosterm() {
		return akciosterm;
	}

	public void setAkciosterm(Boolean akciosterm) {
		this.akciosterm = akciosterm;
	}

	@Column(name = "BONTHATO")
	public Boolean getBonthato() {
		return bonthato;
	}

	public void setBonthato(Boolean bonthato) {
		this.bonthato = bonthato;
	}

	@Column(name = "AJANLANDO")
	public Boolean getAjanlando() {
		return ajanlando;
	}

	public void setAjanlando(Boolean ajanlando) {
		this.ajanlando = ajanlando;
	}

	@Column(name = "ALAPHELY")
	public Integer getAlaphely() {
		return alaphely;
	}

	public void setAlaphely(Integer alaphely) {
		this.alaphely = alaphely;
	}

	@Column(name = "BESZAR2")
	public BigDecimal getBeszar2() {
		return beszar2;
	}

	public void setBeszar2(BigDecimal beszar2) {
		this.beszar2 = beszar2;
	}

	@Column(name = "ARSORMOD")
	public String getArsormod() {
		return arsormod;
	}

	public void setArsormod(String arsormod) {
		this.arsormod = arsormod;
	}

	@Column(name = "VAROLD")
	public BigDecimal getVarold() {
		return varold;
	}

	public void setVarold(BigDecimal varold) {
		this.varold = varold;
	}
	
	@Column(name = "FKSZAM6")
	public String getFkszam6() {
		return fkszam6;
	}

	public void setFkszam6(String fkszam6) {
		this.fkszam6 = fkszam6;
	}

	@Column(name = "IMPTDIJ01")
	public BigDecimal getImptdij01() {
		return imptdij01;
	}

	public void setImptdij01(BigDecimal imptdij01) {
		this.imptdij01 = imptdij01;
	}

	@Column(name = "IMPTDIJ02")
	public BigDecimal getImptdij02() {
		return imptdij02;
	}

	public void setImptdij02(BigDecimal imptdij02) {
		this.imptdij02 = imptdij02;
	}

	@Column(name = "IMPTDIJ03")
	public BigDecimal getImptdij03() {
		return imptdij03;
	}

	public void setImptdij03(BigDecimal imptdij03) {
		this.imptdij03 = imptdij03;
	}

	@Column(name = "IMPTDIJ04")
	public BigDecimal getImptdij04() {
		return imptdij04;
	}

	public void setImptdij04(BigDecimal imptdij04) {
		this.imptdij04 = imptdij04;
	}

	@Column(name = "IMPTDIJ05")
	public BigDecimal getImptdij05() {
		return imptdij05;
	}

	public void setImptdij05(BigDecimal imptdij05) {
		this.imptdij05 = imptdij05;
	}

	@Column(name = "IMPTDIJ06")
	public BigDecimal getImptdij06() {
		return imptdij06;
	}

	public void setImptdij06(BigDecimal imptdij06) {
		this.imptdij06 = imptdij06;
	}

	@Column(name = "IMPKTKOD01")
	public Integer getImpktkod01() {
		return impktkod01;
	}

	public void setImpktkod01(Integer impktkod01) {
		this.impktkod01 = impktkod01;
	}

	@Column(name = "IMPKTKOD02")
	public Integer getImpktkod02() {
		return impktkod02;
	}

	public void setImpktkod02(Integer impktkod02) {
		this.impktkod02 = impktkod02;
	}

	@Column(name = "IMPKTKOD03")
	public Integer getImpktkod03() {
		return impktkod03;
	}

	public void setImpktkod03(Integer impktkod03) {
		this.impktkod03 = impktkod03;
	}

	@Column(name = "IMPKTKOD04")
	public Integer getImpktkod04() {
		return impktkod04;
	}

	public void setImpktkod04(Integer impktkod04) {
		this.impktkod04 = impktkod04;
	}

	@Column(name = "IMPKTKOD05")
	public Integer getImpktkod05() {
		return impktkod05;
	}

	public void setImpktkod05(Integer impktkod05) {
		this.impktkod05 = impktkod05;
	}

	@Column(name = "IMPKTKOD06")
	public Integer getImpktkod06() {
		return impktkod06;
	}

	public void setImpktkod06(Integer impktkod06) {
		this.impktkod06 = impktkod06;
	}

	@Column(name = "LASTBESZ")
	public Integer getLastbesz() {
		return lastbesz;
	}

	public void setLastbesz(Integer lastbesz) {
		this.lastbesz = lastbesz;
	}

	@Column(name = "LASTBESZA")
	public Integer getLastbesza() {
		return lastbesza;
	}

	public void setLastbesza(Integer lastbesza) {
		this.lastbesza = lastbesza;
	}

	@Column(name = "")
	public Date getLastbeszd() {
		return lastbeszd;
	}

	public void setLastbeszd(Date lastbeszd) {
		this.lastbeszd = lastbeszd;
	}

	@Column(name = "LASTBESZAD")
	public Date getLastbeszad() {
		return lastbeszad;
	}

	public void setLastbeszad(Date lastbeszad) {
		this.lastbeszad = lastbeszad;
	}

	@Column(name = "CSOM08")
	public BigDecimal getCsom08() {
		return csom08;
	}

	public void setCsom08(BigDecimal csom08) {
		this.csom08 = csom08;
	}

	@Column(name = "CSOM09")
	public BigDecimal getCsom09() {
		return csom09;
	}

	public void setCsom09(BigDecimal csom09) {
		this.csom09 = csom09;
	}

	@Column(name = "CSOM10")
	public BigDecimal getCsom10() {
		return csom10;
	}

	public void setCsom10(BigDecimal csom10) {
		this.csom10 = csom10;
	}

	@Column(name = "CSOM11")
	public BigDecimal getCsom11() {
		return csom11;
	}

	public void setCsom11(BigDecimal csom11) {
		this.csom11 = csom11;
	}
	
	@Column(name = "CSOM12")
	public BigDecimal getCsom12() {
		return csom12;
	}

	public void setCsom12(BigDecimal csom12) {
		this.csom12 = csom12;
	}

	@Column(name = "EGYS3GONGY")
	public Integer getEgys3gongy() {
		return egys3gongy;
	}

	public void setEgys3gongy(Integer egys3gongy) {
		this.egys3gongy = egys3gongy;
	}

	@Column(name = "UJ")
	public Boolean getUj() {
		return uj;
	}

	public void setUj(Boolean uj) {
		this.uj = uj;
	}

	@Column(name = "MEGJENG")
	public String getMegjeng() {
		return megjeng;
	}

	public void setMegjeng(String megjeng) {
		this.megjeng = megjeng;
	}

	@Column(name = "PROJSABLON")
	public Integer getProjsablon() {
		return projsablon;
	}

	public void setProjsablon(Integer projsablon) {
		this.projsablon = projsablon;
	}

	@Column(name = "NYERSAR")
	public BigDecimal getNyersar() {
		return nyersar;
	}

	public void setNyersar(BigDecimal nyersar) {
		this.nyersar = nyersar;
	}

	@Column(name = "BERKOLTS")
	public BigDecimal getBerkolts() {
		return berkolts;
	}

	public void setBerkolts(BigDecimal berkolts) {
		this.berkolts = berkolts;
	}

	@Column(name = "ONKOLTS")
	public BigDecimal getOnkolts() {
		return onkolts;
	}

	public void setOnkolts(BigDecimal onkolts) {
		this.onkolts = onkolts;
	}

	@Column(name = "NYERSANYAG")
	public Boolean getNyersanyag() {
		return nyersanyag;
	}

	public void setNyersanyag(Boolean nyersanyag) {
		this.nyersanyag = nyersanyag;
	}

	@Column(name = "PTGARMOD")
	public Boolean getPtgarmod() {
		return ptgarmod;
	}

	public void setPtgarmod(Boolean ptgarmod) {
		this.ptgarmod = ptgarmod;
	}	
}

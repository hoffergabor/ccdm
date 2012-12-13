package hu.iboard.coandco.datamagic.vo.prprojekt;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRPROJEKT")
public class PRProjekt implements Serializable{

	private static final long serialVersionUID = 6235293765250296950L;
	
	private Integer id;
	private String kod;
	private Integer dimenzio;
	private String megn;
	private Integer foprojekt;
	private Integer kozvetett;
	private Integer keszultseg;
	private String leiras;
	private Date kezdes;
	private Date befejezes;
	private Integer felelos1;
	private Integer felelos2;
	private Integer felelos3;
	private Integer felelos4;
	private Integer felelos5;
	private Boolean belso;
	private Integer suly;
	private String megj;
	private Boolean publikus;
	private Boolean lezart;
	private Date insdatum;
	private String statusz;
	private BigDecimal tervksg;
	private BigDecimal alvallksg;
	private Boolean sablon;
	private Integer idotartam;
	private Integer kezdcsusz;
	private Date prjkezdet;
	private Date prjveg;
	private Integer partner;
	private Boolean potmunka;
	private Boolean dbjpublikus;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "KOD")
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	
	@Column(name = "DIMENZIO")
	public Integer getDimenzio() {
		return dimenzio;
	}
	public void setDimenzio(Integer dimenzio) {
		this.dimenzio = dimenzio;
	}
	
	@Column(name = "MEGN")
	public String getMegn() {
		return megn;
	}
	public void setMegn(String megn) {
		this.megn = megn;
	}
	
	@Column(name = "FOPROJEKT")
	public Integer getFoprojekt() {
		return foprojekt;
	}
	public void setFoprojekt(Integer foprojekt) {
		this.foprojekt = foprojekt;
	}
	
	@Column(name = "KOZVETETT")
	public Integer getKozvetett() {
		return kozvetett;
	}
	public void setKozvetett(Integer kozvetett) {
		this.kozvetett = kozvetett;
	}
	
	@Column(name = "KESZULTSEG")
	public Integer getKeszultseg() {
		return keszultseg;
	}
	public void setKeszultseg(Integer keszultseg) {
		this.keszultseg = keszultseg;
	}
	
	@Column(name = "LEIRAS")
	public String getLeiras() {
		return leiras;
	}
	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}
	
	@Column(name = "KEZDES")
	public Date getKezdes() {
		return kezdes;
	}
	public void setKezdes(Date kezdes) {
		this.kezdes = kezdes;
	}
	
	@Column(name = "BEFEJEZES")
	public Date getBefejezes() {
		return befejezes;
	}
	public void setBefejezes(Date befejezes) {
		this.befejezes = befejezes;
	}
	
	@Column(name = "FELELOS1")
	public Integer getFelelos1() {
		return felelos1;
	}
	public void setFelelos1(Integer felelos1) {
		this.felelos1 = felelos1;
	}
	@Column(name = "FELELOS2")
	public Integer getFelelos2() {
		return felelos2;
	}
	public void setFelelos2(Integer felelos2) {
		this.felelos2 = felelos2;
	}
	
	@Column(name = "FELELOS3")
	public Integer getFelelos3() {
		return felelos3;
	}
	public void setFelelos3(Integer felelos3) {
		this.felelos3 = felelos3;
	}
	
	@Column(name = "FELELOS4")
	public Integer getFelelos4() {
		return felelos4;
	}
	public void setFelelos4(Integer felelos4) {
		this.felelos4 = felelos4;
	}
	
	@Column(name = "FELELOS5")
	public Integer getFelelos5() {
		return felelos5;
	}
	public void setFelelos5(Integer felelos5) {
		this.felelos5 = felelos5;
	}
	
	@Column(name = "BELSO")
	public Boolean getBelso() {
		return belso;
	}
	public void setBelso(Boolean belso) {
		this.belso = belso;
	}
	
	@Column(name = "SULY")
	public Integer getSuly() {
		return suly;
	}
	public void setSuly(Integer suly) {
		this.suly = suly;
	}
	
	@Column(name = "MEGJ")
	public String getMegj() {
		return megj;
	}
	public void setMegj(String megj) {
		this.megj = megj;
	}
	
	@Column(name = "PUBLIKUS")
	public Boolean getPublikus() {
		return publikus;
	}
	public void setPublikus(Boolean publikus) {
		this.publikus = publikus;
	}
	
	@Column(name = "LEZART")
	public Boolean getLezart() {
		return lezart;
	}
	public void setLezart(Boolean lezart) {
		this.lezart = lezart;
	}
	
	@Column(name = "INSDATUM")
	public Date getInsdatum() {
		return insdatum;
	}
	public void setInsdatum(Date insdatum) {
		this.insdatum = insdatum;
	}
	
	@Column(name = "STATUSZ")
	public String getStatusz() {
		return statusz;
	}
	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}
	
	@Column(name = "TERVKSG")
	public BigDecimal getTervksg() {
		return tervksg;
	}
	public void setTervksg(BigDecimal tervksg) {
		this.tervksg = tervksg;
	}
	
	@Column(name = "ALVALLKSG")
	public BigDecimal getAlvallksg() {
		return alvallksg;
	}
	public void setAlvallksg(BigDecimal alvallksg) {
		this.alvallksg = alvallksg;
	}
	
	@Column(name = "SABLON")
	public Boolean getSablon() {
		return sablon;
	}
	public void setSablon(Boolean sablon) {
		this.sablon = sablon;
	}
	
	@Column(name = "IDOTARTAM")
	public Integer getIdotartam() {
		return idotartam;
	}
	public void setIdotartam(Integer idotartam) {
		this.idotartam = idotartam;
	}
	
	@Column(name = "KEZDCSUSZ")
	public Integer getKezdcsusz() {
		return kezdcsusz;
	}
	public void setKezdcsusz(Integer kezdcsusz) {
		this.kezdcsusz = kezdcsusz;
	}
	
	@Column(name = "PRJKEZDET")
	public Date getPrjkezdet() {
		return prjkezdet;
	}
	public void setPrjkezdet(Date prjkezdet) {
		this.prjkezdet = prjkezdet;
	}
	
	@Column(name = "PRJVEG")
	public Date getPrjveg() {
		return prjveg;
	}
	public void setPrjveg(Date prjveg) {
		this.prjveg = prjveg;
	}
	
	@Column(name = "PARTNER")
	public Integer getPartner() {
		return partner;
	}
	public void setPartner(Integer partner) {
		this.partner = partner;
	}
	
	@Column(name = "POTMUNKA")
	public Boolean getPotmunka() {
		return potmunka;
	}
	public void setPotmunka(Boolean potmunka) {
		this.potmunka = potmunka;
	}
	
	@Column(name = "DBJPUBLIKUS")
	public Boolean getDbjpublikus() {
		return dbjpublikus;
	}
	public void setDbjpublikus(Boolean dbjpublikus) {
		this.dbjpublikus = dbjpublikus;
	}
	
	
	
	

}

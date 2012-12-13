package hu.iboard.coandco.datamagic.vo.product;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int arukod;
	private String cikkszam;
	private String megn;
	//private String megn3;
	private BigDecimal ear;
	private BigDecimal mennyiseg;
	private BigDecimal netto;
	private BigDecimal brutto;
	private BigDecimal afa;
	private String azon3;
	private String azon4;
	private String azon5;
	private String azon6;
	private String azon7;
	private String azon8;
	private String azon9;
	private String azon10;
	private String azon11;
	private String azon12;
	private BigDecimal menny;
	private String meegys;
	private String devnem;
	private Integer raktar;
	private Boolean passziv;
	private int csopkod;
	private String megj;
	private BigDecimal kedvar;
	private BigDecimal kedvarBrutto;	

	public int getArukod() {
		return arukod;
	}

	public void setArukod(int arukod) {
		this.arukod = arukod;
	}

	public String getCikkszam() {
		return cikkszam;
	}

	public void setCikkszam(String cikkszam) {
		this.cikkszam = cikkszam;
	}

	public String getMegn() {
		return megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	public BigDecimal getEar() {
		return ear;
	}

	public void setEar(BigDecimal ear) {
		this.ear = ear;
	}

	public BigDecimal getMennyiseg() {
		return mennyiseg;
	}

	public void setMennyiseg(BigDecimal mennyiseg) {
		this.mennyiseg = mennyiseg;
	}

	public BigDecimal getAfa() {
		return afa;
	}

	public void setAfa(BigDecimal afa) {
		this.afa = afa;
	}

	public BigDecimal getNetto() {
		return netto;
	}

	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}

	public BigDecimal getBrutto() {
		return brutto;
	}

	public void setBrutto(BigDecimal brutto) {
		this.brutto = brutto;
	}

	public String getAzon3() {
		return azon3;
	}

	public void setAzon3(String azon3) {
		this.azon3 = azon3;
	}

	public String getAzon4() {
		return azon4;
	}

	public void setAzon4(String azon4) {
		this.azon4 = azon4;
	}

	public String getAzon5() {
		return azon5;
	}

	public void setAzon5(String azon5) {
		this.azon5 = azon5;
	}

	public String getAzon6() {
		return azon6;
	}

	public void setAzon6(String azon6) {
		this.azon6 = azon6;
	}

	public String getAzon7() {
		return azon7;
	}

	public void setAzon7(String azon7) {
		this.azon7 = azon7;
	}

	public String getAzon8() {
		return azon8;
	}

	public void setAzon8(String azon8) {
		this.azon8 = azon8;
	}

	public String getAzon9() {
		return azon9;
	}

	public void setAzon9(String azon9) {
		this.azon9 = azon9;
	}

	public String getAzon10() {
		return azon10;
	}

	public void setAzon10(String azon10) {
		this.azon10 = azon10;
	}

	public String getAzon11() {
		return azon11;
	}

	public void setAzon11(String azon11) {
		this.azon11 = azon11;
	}

	public String getAzon12() {
		return azon12;
	}

	public void setAzon12(String azon12) {
		this.azon12 = azon12;
	}

	public BigDecimal getMenny() {
		return menny;
	}

	public void setMenny(BigDecimal menny) {
		this.menny = menny;
	}

	public String getMeegys() {
		return meegys;
	}

	public void setMeegys(String meegys) {
		this.meegys = meegys;
	}

	public String getDevnem() {
		return devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

//	public String getMegn3() {
//		return megn3;
//	}
//
//	public void setMegn3(String megn3) {
//		this.megn3 = megn3;
//	}

	public Integer getRaktar() {
		return raktar;
	}

	public void setRaktar(Integer raktar) {
		this.raktar = raktar;
	}

	public Boolean getPassziv() {
		return passziv;
	}

	public void setPassziv(Boolean passziv) {
		this.passziv = passziv;
	}

	public int getCsopkod() {
		return csopkod;
	}

	public String getMegj() {
		return megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}

	public void setCsopkod(int csopkod) {
		this.csopkod = csopkod;
	}

	public BigDecimal getKedvar() {
		return kedvar;
	}

	public void setKedvar(BigDecimal kedvar) {
		this.kedvar = kedvar;
	}

	public BigDecimal getKedvarBrutto() {
		return kedvarBrutto;
	}

	public void setKedvarBrutto(BigDecimal kedvarBrutto) {
		this.kedvarBrutto = kedvarBrutto;
	}

}

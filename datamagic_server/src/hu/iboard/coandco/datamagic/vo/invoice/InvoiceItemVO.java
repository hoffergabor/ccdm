package hu.iboard.coandco.datamagic.vo.invoice;

import java.io.Serializable;
import java.math.BigDecimal;

public class InvoiceItemVO implements Serializable {

	private static final long serialVersionUID = -2081099027567001633L;
	private String unikazon;
	private String sorszam;
	private Integer arukod;
	private String acikksz;
	private String amegn;
	private BigDecimal amenny;
	private BigDecimal aear;
	private BigDecimal hnertek;
	private BigDecimal hbertek;
	private BigDecimal haertek;
	private Integer tetelszam;
	private BigDecimal nertek;
	private BigDecimal aertek;
	private BigDecimal bertek;

	public InvoiceItemVO() {
	}

	public InvoiceItemVO(String unikazon, String sorszam, Integer arukod, String acikksz, String amegn,
			BigDecimal amenny, BigDecimal aear, BigDecimal hnertek, BigDecimal hbertek, BigDecimal haertek,
			Integer tetelszam, BigDecimal nertek, BigDecimal aertek, BigDecimal bertek) {
		super();
		this.unikazon = unikazon;
		this.sorszam = sorszam;
		this.arukod = arukod;
		this.acikksz = acikksz;
		this.amegn = amegn;
		this.amenny = amenny;
		this.aear = aear;
		this.hnertek = hnertek;
		this.hbertek = hbertek;
		this.haertek = haertek;
		this.tetelszam = tetelszam;
		this.nertek = nertek;
		this.aertek = aertek;
		this.bertek = bertek;
	}

	public String getUnikazon() {
		return this.unikazon;
	}

	public void setUnikazon(String unikazon) {
		this.unikazon = unikazon;
	}

	public String getSorszam() {
		return sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	public Integer getArukod() {
		return arukod;
	}

	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}

	public String getAcikksz() {
		return acikksz;
	}

	public void setAcikksz(String acikksz) {
		this.acikksz = acikksz;
	}

	public String getAmegn() {
		return amegn;
	}

	public void setAmegn(String amegn) {
		this.amegn = amegn;
	}

	public BigDecimal getAmenny() {
		return amenny;
	}

	public void setAmenny(BigDecimal amenny) {
		this.amenny = amenny;
	}

	public BigDecimal getAear() {
		return aear;
	}

	public void setAear(BigDecimal aear) {
		this.aear = aear;
	}

	public BigDecimal getHnertek() {
		return hnertek;
	}

	public void setHnertek(BigDecimal hnertek) {
		this.hnertek = hnertek;
	}

	public BigDecimal getHbertek() {
		return hbertek;
	}

	public void setHbertek(BigDecimal hbertek) {
		this.hbertek = hbertek;
	}

	public BigDecimal getHaertek() {
		return haertek;
	}

	public void setHaertek(BigDecimal haertek) {
		this.haertek = haertek;
	}

	public Integer getTetelszam() {
		return tetelszam;
	}

	public void setTetelszam(Integer tetelszam) {
		this.tetelszam = tetelszam;
	}

	public BigDecimal getNertek() {
		return nertek;
	}

	public void setNertek(BigDecimal nertek) {
		this.nertek = nertek;
	}

	public BigDecimal getAertek() {
		return aertek;
	}

	public void setAertek(BigDecimal aertek) {
		this.aertek = aertek;
	}

	public BigDecimal getBertek() {
		return bertek;
	}

	public void setBertek(BigDecimal bertek) {
		this.bertek = bertek;
	}
}

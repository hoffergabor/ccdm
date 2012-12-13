package hu.iboard.coandco.datamagic.vo.invoice;

import hu.iboard.coandco.datamagic.generated.Partner;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class InvoiceVO implements Serializable {

	private static final long serialVersionUID = -2190849195602907871L;
	private String sorszam;
	private Date kelt;
	private Date telj;
	private Date esedkelt;
	private String fizmod;
	private String szlaszam;
	private String devnem;
	private BigDecimal arfolyam;
	private Date fizetve;
	private String cegnev;
	private BigDecimal dbrutto;
	private Integer vevokod;
	private BigDecimal dnetto;
	private Date realesed;
	private BigDecimal netto;
	private BigDecimal brutto;
	private Boolean kimeno;
	private BigDecimal rossz;
	private BigDecimal arrears;
	private Integer tablaVevokod;
	private Integer telepkod;
	private Partner telephely;

	public InvoiceVO() {
	}

	public InvoiceVO(String sorszam) {
		this.sorszam = sorszam;
	}

	public String getSorszam() {
		return this.sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	public Date getKelt() {
		return kelt;
	}

	public void setKelt(Date kelt) {
		this.kelt = kelt;
	}

	public Date getTelj() {
		return telj;
	}

	public void setTelj(Date telj) {
		this.telj = telj;
	}

	public Date getEsedkelt() {
		return esedkelt;
	}

	public void setEsedkelt(Date esedkelt) {
		this.esedkelt = esedkelt;
	}

	public String getFizmod() {
		return fizmod;
	}

	public void setFizmod(String fizmod) {
		this.fizmod = fizmod;
	}

	public String getSzlaszam() {
		return szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	public String getDevnem() {
		return devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	public BigDecimal getArfolyam() {
		return arfolyam;
	}

	public void setArfolyam(BigDecimal arfolyam) {
		this.arfolyam = arfolyam;
	}

	public Date getFizetve() {
		return fizetve;
	}

	public void setFizetve(Date fizetve) {
		this.fizetve = fizetve;
	}

	public BigDecimal getDbrutto() {
		return dbrutto;
	}

	public void setDbrutto(BigDecimal dbrutto) {
		this.dbrutto = dbrutto;
	}

	public Integer getVevokod() {
		return vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	public BigDecimal getDnetto() {
		return dnetto;
	}

	public void setDnetto(BigDecimal dnetto) {
		this.dnetto = dnetto;
	}

	public String getCegnev() {
		return cegnev;
	}

	public void setCegnev(String cegnev) {
		this.cegnev = cegnev;
	}

	public Date getRealesed() {
		return realesed;
	}

	public void setRealesed(Date realesed) {
		this.realesed = realesed;
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

	public Boolean getKimeno() {
		return kimeno;
	}

	public void setKimeno(Boolean kimeno) {
		this.kimeno = kimeno;
	}

	public BigDecimal getRossz() {
		return rossz;
	}

	public void setRossz(BigDecimal rossz) {
		this.rossz = rossz;
	}

	public BigDecimal getArrears() {
		return arrears;
	}

	public void setArrears(BigDecimal arrears) {
		this.arrears = arrears;
	}

	public Integer getTablaVevokod() {
		return tablaVevokod;
	}

	public void setTablaVevokod(Integer tablaVevokod) {
		this.tablaVevokod = tablaVevokod;
	}

	public Integer getTelepkod() {
		return telepkod;
	}

	public void setTelepkod(Integer telepkod) {
		this.telepkod = telepkod;
	}

	public Partner getTelephely() {
		return telephely;
	}

	public void setTelephely(Partner telephely) {
		this.telephely = telephely;
	}

}

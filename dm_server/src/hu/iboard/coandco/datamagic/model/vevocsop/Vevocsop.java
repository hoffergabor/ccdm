package hu.iboard.coandco.datamagic.model.vevocsop;


import hu.iboard.coandco.datamagic.model.arnev.Arnev;

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
@Table(name = "VEVOCSOP")
public class Vevocsop implements java.io.Serializable {

	private static final long serialVersionUID = -7909880864621908902L;
	private BigDecimal kod;
	private String megn;
	private boolean engadhat;
	private Integer arnevkod;
	private Arnev arnev;
	private String szlaszam;
	private boolean trndazon;
	private String fkszam;
	private Date moddatum;
	private Integer modkod;
	private Integer elszarsor;

	public Vevocsop() {
	}

	public Vevocsop(BigDecimal kod) {
		this.kod = kod;
	}

	public Vevocsop(BigDecimal kod, String megn, boolean engadhat,
			Integer arnevkod, Arnev arnev, String szlaszam, boolean trndazon, String fkszam,
			Date moddatum, Integer modkod, Integer elszarsor) {
		this.kod = kod;
		this.megn = megn;
		this.engadhat = engadhat;
		this.arnevkod = arnevkod;
		this.arnev = arnev;
		this.szlaszam = szlaszam;
		this.trndazon = trndazon;
		this.fkszam = fkszam;
		this.moddatum = moddatum;
		this.modkod = modkod;
		this.elszarsor = elszarsor;
	}

	@Id
	@Column(name = "KOD", unique = true, nullable = false, precision = 12, scale = 0)
	public BigDecimal getKod() {
		return this.kod;
	}

	public void setKod(BigDecimal kod) {
		this.kod = kod;
	}

	@Column(name = "MEGN", length = 30)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Column(name = "ENGADHAT")
	public boolean isEngadhat() {
		return this.engadhat;
	}

	public void setEngadhat(boolean engadhat) {
		this.engadhat = engadhat;
	}

	@Column(name = "ARNEVKOD")
	public Integer getArnevkod() {
		return this.arnevkod;
	}

	public void setArnevkod(Integer arnevkod) {
		this.arnevkod = arnevkod;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ARNEVKOD", insertable = false, updatable = false)
	public Arnev getArnev() {
		return this.arnev;
	}

	public void setArnev(Arnev arnev) {
		this.arnev = arnev;
	}

	@Column(name = "SZLASZAM", length = 26)
	public String getSzlaszam() {
		return this.szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	@Column(name = "TRNDAZON")
	public boolean isTrndazon() {
		return this.trndazon;
	}

	public void setTrndazon(boolean trndazon) {
		this.trndazon = trndazon;
	}

	@Column(name = "FKSZAM", length = 8)
	public String getFkszam() {
		return this.fkszam;
	}

	public void setFkszam(String fkszam) {
		this.fkszam = fkszam;
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

	@Column(name = "ELSZARSOR")
	public Integer getElszarsor() {
		return this.elszarsor;
	}

	public void setElszarsor(Integer elszarsor) {
		this.elszarsor = elszarsor;
	}

}
package hu.iboard.coandco.datamagic.model.afakodok;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "AFAKODOK")
public class Afakodok implements java.io.Serializable {

	private static final long serialVersionUID = 5520577604036400213L;
	private String megn;
	private Integer kulcs;
	private String jeloles;
	private BigDecimal szorzo;
	private String konyvafa;
	private Integer afacsop;
	private String tfokszam;
	private String kfokszam;
	private Integer tipuskod;
	private String telfox;
	private String afairany;
	private boolean valodiafa;
	private Integer fordkulcs;
	private boolean eubesz;

	public Afakodok() {
	}

	public Afakodok(String megn) {
		this.megn = megn;
	}

	public Afakodok(String megn, Integer kulcs, String jeloles, BigDecimal szorzo, String konyvafa, Integer afacsop,
			String tfokszam, String kfokszam, Integer tipuskod, String telfox, String afairany, boolean valodiafa,
			Integer fordkulcs, boolean eubesz) {
		this.megn = megn;
		this.kulcs = kulcs;
		this.jeloles = jeloles;
		this.szorzo = szorzo;
		this.konyvafa = konyvafa;
		this.afacsop = afacsop;
		this.tfokszam = tfokszam;
		this.kfokszam = kfokszam;
		this.tipuskod = tipuskod;
		this.telfox = telfox;
		this.afairany = afairany;
		this.valodiafa = valodiafa;
		this.fordkulcs = fordkulcs;
		this.eubesz = eubesz;
	}

	@Column(name = "MEGN")
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Id
	@Column(name = "KULCS", unique = true, nullable = false, length = 20)
	public Integer getKulcs() {
		return this.kulcs;
	}

	public void setKulcs(Integer kulcs) {
		this.kulcs = kulcs;
	}

	@Column(name = "JELOLES", length = 3)
	public String getJeloles() {
		return this.jeloles;
	}

	public void setJeloles(String jeloles) {
		this.jeloles = jeloles;
	}

	@Column(name = "SZORZO", precision = 10)
	public BigDecimal getSzorzo() {
		return this.szorzo;
	}

	public void setSzorzo(BigDecimal szorzo) {
		this.szorzo = szorzo;
	}

	@Column(name = "KONYVAFA", length = 2)
	public String getKonyvafa() {
		return this.konyvafa;
	}

	public void setKonyvafa(String konyvafa) {
		this.konyvafa = konyvafa;
	}

	@Column(name = "AFACSOP")
	public Integer getAfacsop() {
		return this.afacsop;
	}

	public void setAfacsop(Integer afacsop) {
		this.afacsop = afacsop;
	}

	@Column(name = "TFOKSZAM", length = 8)
	public String getTfokszam() {
		return this.tfokszam;
	}

	public void setTfokszam(String tfokszam) {
		this.tfokszam = tfokszam;
	}

	@Column(name = "KFOKSZAM", length = 8)
	public String getKfokszam() {
		return this.kfokszam;
	}

	public void setKfokszam(String kfokszam) {
		this.kfokszam = kfokszam;
	}

	@Column(name = "TIPUSKOD")
	public Integer getTipuskod() {
		return this.tipuskod;
	}

	public void setTipuskod(Integer tipuskod) {
		this.tipuskod = tipuskod;
	}

	@Column(name = "TELFOX", length = 8)
	public String getTelfox() {
		return this.telfox;
	}

	public void setTelfox(String telfox) {
		this.telfox = telfox;
	}

	@Column(name = "AFAIRANY", length = 1)
	public String getAfairany() {
		return this.afairany;
	}

	public void setAfairany(String afairany) {
		this.afairany = afairany;
	}

	@Column(name = "VALODIAFA")
	public boolean isValodiafa() {
		return this.valodiafa;
	}

	public void setValodiafa(boolean valodiafa) {
		this.valodiafa = valodiafa;
	}

	@Column(name = "FORDKULCS")
	public Integer getFordkulcs() {
		return this.fordkulcs;
	}

	public void setFordkulcs(Integer fordkulcs) {
		this.fordkulcs = fordkulcs;
	}

	@Column(name = "EUBESZ")
	public boolean isEubesz() {
		return this.eubesz;
	}

	public void setEubesz(boolean eubesz) {
		this.eubesz = eubesz;
	}

}

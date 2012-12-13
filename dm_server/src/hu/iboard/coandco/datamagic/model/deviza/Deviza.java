package hu.iboard.coandco.datamagic.model.deviza;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DEVIZA")
public class Deviza implements java.io.Serializable {

	private static final long serialVersionUID = 7119362554396511712L;
	private String rov;
	private String megn;
	private BigDecimal arfolyam;
	private Date mod;
	private Integer kerekites;
	private BigDecimal vetel;
	private BigDecimal kozep;
	private BigDecimal eladas;
	private BigDecimal sajat;
	private Date datumtol;
	private String szlaszam;
	private String kozafadev;
	private Integer kozafatip;

	public Deviza() {
	}

	public Deviza(String rov) {
		this.rov = rov;
	}

	public Deviza(String rov, String megn, BigDecimal arfolyam, Date mod,
			Integer kerekites, BigDecimal vetel, BigDecimal kozep,
			BigDecimal eladas, BigDecimal sajat, Date datumtol,
			String szlaszam, String kozafadev, Integer kozafatip) {
		this.rov = rov;
		this.megn = megn;
		this.arfolyam = arfolyam;
		this.mod = mod;
		this.kerekites = kerekites;
		this.vetel = vetel;
		this.kozep = kozep;
		this.eladas = eladas;
		this.sajat = sajat;
		this.datumtol = datumtol;
		this.szlaszam = szlaszam;
		this.kozafadev = kozafadev;
		this.kozafatip = kozafatip;
	}

	@Id
	@Column(name = "ROV", unique = true, nullable = false, length = 4)
	public String getRov() {
		return this.rov;
	}

	public void setRov(String rov) {
		this.rov = rov;
	}

	@Column(name = "MEGN", length = 40)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Column(name = "ARFOLYAM")
	public BigDecimal getArfolyam() {
		return this.arfolyam;
	}

	public void setArfolyam(BigDecimal arfolyam) {
		this.arfolyam = arfolyam;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "`MOD`", length = 19)
	public Date getMod() {
		return this.mod;
	}

	public void setMod(Date mod) {
		this.mod = mod;
	}

	@Column(name = "KEREKITES")
	public Integer getKerekites() {
		return this.kerekites;
	}

	public void setKerekites(Integer kerekites) {
		this.kerekites = kerekites;
	}

	@Column(name = "VETEL", precision = 15, scale = 3)
	public BigDecimal getVetel() {
		return this.vetel;
	}

	public void setVetel(BigDecimal vetel) {
		this.vetel = vetel;
	}

	@Column(name = "KOZEP", precision = 15, scale = 3)
	public BigDecimal getKozep() {
		return this.kozep;
	}

	public void setKozep(BigDecimal kozep) {
		this.kozep = kozep;
	}

	@Column(name = "ELADAS", precision = 15, scale = 3)
	public BigDecimal getEladas() {
		return this.eladas;
	}

	public void setEladas(BigDecimal eladas) {
		this.eladas = eladas;
	}

	@Column(name = "SAJAT", precision = 15, scale = 3)
	public BigDecimal getSajat() {
		return this.sajat;
	}

	public void setSajat(BigDecimal sajat) {
		this.sajat = sajat;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "DATUMTOL", length = 19)
	public Date getDatumtol() {
		return this.datumtol;
	}

	public void setDatumtol(Date datumtol) {
		this.datumtol = datumtol;
	}

	@Column(name = "SZLASZAM", length = 26)
	public String getSzlaszam() {
		return this.szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	@Column(name = "KOZAFADEV", length = 4)
	public String getKozafadev() {
		return this.kozafadev;
	}

	public void setKozafadev(String kozafadev) {
		this.kozafadev = kozafadev;
	}

	@Column(name = "KOZAFATIP")
	public Integer getKozafatip() {
		return this.kozafatip;
	}

	public void setKozafatip(Integer kozafatip) {
		this.kozafatip = kozafatip;
	}

}

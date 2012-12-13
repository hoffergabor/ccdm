package hu.iboard.coandco.datamagic.vo.shipping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "SZLEVEL")
public class ShippingVO implements java.io.Serializable {

	private static final long serialVersionUID = 2137985795191892561L;

	private String sorszam;
	private Date kelt;
	private Date esedkelt;
	private String devnem;
	private Date fizetve;
	private String vnev;
	private BigDecimal dbrutto;
	private BigDecimal dnetto;
	private Integer vevokod;

	private List<ShippingItemVO> szlevtets = new ArrayList<ShippingItemVO>(0);

	public ShippingVO() {
	}

	public ShippingVO(String sorszam) {
		this.sorszam = sorszam;
	}

	public ShippingVO(String sorszam, Date kelt, Date esedkelt,
			String devnem, Date fizetve, String vnev, BigDecimal dbrutto,
			BigDecimal dnetto, List<ShippingItemVO> szlevtets) {
		super();
		this.sorszam = sorszam;
		this.kelt = kelt;
		this.esedkelt = esedkelt;
		this.devnem = devnem;
		this.fizetve = fizetve;
		this.vnev = vnev;
		this.dbrutto = dbrutto;
		this.dnetto = dnetto;
		this.szlevtets = szlevtets;
	}

	@Id
	@Column(name = "SORSZAM", unique = true, nullable = false, length = 15)
	public String getSorszam() {
		return this.sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	@Column(name = "KELT")
	public Date getKelt() {
		return kelt;
	}

	public void setKelt(Date kelt) {
		this.kelt = kelt;
	}

	@Column(name = "ESEDKELT")
	public Date getEsedkelt() {
		return esedkelt;
	}

	public void setEsedkelt(Date esedkelt) {
		this.esedkelt = esedkelt;
	}

	@Column(name = "DEVNEM")
	public String getDevnem() {
		return devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	@Column(name = "FIZETVE")
	public Date getFizetve() {
		return fizetve;
	}

	public void setFizetve(Date fizetve) {
		this.fizetve = fizetve;
	}

	@Column(name = "VNEV")
	public String getVnev() {
		return vnev;
	}

	public void setVnev(String vnev) {
		this.vnev = vnev;
	}

	@Column(name = "DBRUTTO")
	public BigDecimal getDbrutto() {
		return dbrutto;
	}

	public void setDbrutto(BigDecimal dbrutto) {
		this.dbrutto = dbrutto;
	}

	@Column(name = "DNETTO")
	public BigDecimal getDnetto() {
		return dnetto;
	}

	public void setDnetto(BigDecimal dnetto) {
		this.dnetto = dnetto;
	}
	
	@Column(name = "VEVOKOD")
	public Integer getVevokod() {
		return vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sorszam")
	@JoinTable(name = "shipping", joinColumns = @JoinColumn(name = "sorszam"), inverseJoinColumns = @JoinColumn(name = "sorszam"))
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	public List<ShippingItemVO> getSzlevtets() {
		return szlevtets;
	}

	public void setSzlevtets(List<ShippingItemVO> szlevtets) {
		this.szlevtets = szlevtets;
	}

}

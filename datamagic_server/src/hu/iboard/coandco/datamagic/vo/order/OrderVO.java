package hu.iboard.coandco.datamagic.vo.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RENDEL")
public class OrderVO implements java.io.Serializable {

	private static final long serialVersionUID = -5067139035845508653L;

	private String sorszam;
	private Date kelt;
	private Date telj;
	private Date esedkelt;
	private String fizmod;
	private String szlaszam;
	private String devnem;
	private BigDecimal arfolyam;
	private Date fizetve;
	private String vnev;
	private String statusz;
	private String kivagybe;
	private BigDecimal dbrutto;
	private Integer vevokod;
	private BigDecimal dnetto;
	private Date vfizdat;

	private Set<OrderItemVO> rendtets = new HashSet<OrderItemVO>(0);

	public OrderVO() {
	}

	public OrderVO(String sorszam) {
		this.sorszam = sorszam;
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

	@Column(name = "TELJ")
	public Date getTelj() {
		return telj;
	}

	public void setTelj(Date telj) {
		this.telj = telj;
	}

	@Column(name = "ESEDKELT")
	public Date getEsedkelt() {
		return esedkelt;
	}

	public void setEsedkelt(Date esedkelt) {
		this.esedkelt = esedkelt;
	}

	@Column(name = "FIZMOD")
	public String getFizmod() {
		return fizmod;
	}

	public void setFizmod(String fizmod) {
		this.fizmod = fizmod;
	}

	@Column(name = "SZLASZAM")
	public String getSzlaszam() {
		return szlaszam;
	}

	public void setSzlaszam(String szlaszam) {
		this.szlaszam = szlaszam;
	}

	@Column(name = "DEVNEM")
	public String getDevnem() {
		return devnem;
	}

	public void setDevnem(String devnem) {
		this.devnem = devnem;
	}

	@Column(name = "ARFOLYAM")
	public BigDecimal getArfolyam() {
		return arfolyam;
	}

	public void setArfolyam(BigDecimal arfolyam) {
		this.arfolyam = arfolyam;
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

	@Column(name = "STATUSZ")
	public String getStatusz() {
		return statusz;
	}

	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}

	@Column(name = "KIVAGYBE")
	public String getKivagybe() {
		return kivagybe;
	}

	public void setKivagybe(String kivagybe) {
		this.kivagybe = kivagybe;
	}

	@Column(name = "DBRUTTO")
	public BigDecimal getDbrutto() {
		return dbrutto;
	}

	public void setDbrutto(BigDecimal dbrutto) {
		this.dbrutto = dbrutto;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	@JoinTable(name = "order", joinColumns = @JoinColumn(name = "sorszam"), inverseJoinColumns = @JoinColumn(name = "sorszam"))
	// @Fetch(FetchMode.SELECT)
	// @Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	// @NotFound(action = NotFoundAction.IGNORE)
	// @LazyCollection(LazyCollectionOption.LAZY)
	public Set<OrderItemVO> getRendtets() {
		return rendtets;
	}

	public void setRendtets(Set<OrderItemVO> rendtets) {
		this.rendtets = rendtets;
	}

	@Column(name = "VEVOKOD")
	public Integer getVevokod() {
		return vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	@Column(name = "DNETTO")
	public BigDecimal getDnetto() {
		return dnetto;
	}

	public void setDnetto(BigDecimal dnetto) {
		this.dnetto = dnetto;
	}

	@Column(name = "VFIZDAT")
	public Date getVfizdat() {
		return vfizdat;
	}

	public void setVfizdat(Date vfizdat) {
		this.vfizdat = vfizdat;
	}

}

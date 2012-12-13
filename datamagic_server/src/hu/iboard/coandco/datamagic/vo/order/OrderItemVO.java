package hu.iboard.coandco.datamagic.vo.order;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RENDTET")
public class OrderItemVO implements java.io.Serializable {
	private static final long serialVersionUID = -3301642727735102199L;
	private String unikazon;
	private String sorszam;
	private Integer arukod;
	private String acikksz;
	private String amegn;
	private BigDecimal amenny;
	private BigDecimal aear;
	private Date vfizhdat;
	private BigDecimal hnertek;
	private BigDecimal hbertek;
	private OrderVO order;
	private String szlevsorsz;
	private String szamlsorsz;
	private Integer mkateg1;

	public OrderItemVO() {
	}

	public OrderItemVO(String unikazon, String sorszam, Integer arukod, String acikksz, String amegn, BigDecimal amenny, BigDecimal aear, Date vfizhdat,
			BigDecimal hnertek, BigDecimal hbertek) {
		this.unikazon = unikazon;
		this.sorszam = sorszam;
		this.arukod = arukod;
		this.acikksz = acikksz;
		this.amegn = amegn;
		this.amenny = amenny;
		this.aear = aear;
		this.vfizhdat = vfizhdat;
		this.hnertek = hnertek;
		this.hbertek = hbertek;
	}

	@Id
	@Column(name = "UNIKAZON")
	public String getUnikazon() {
		return this.unikazon;
	}

	public void setUnikazon(String unikazon) {
		this.unikazon = unikazon;
	}

	@Column(name = "SORSZAM")
	public String getSorszam() {
		return sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return arukod;
	}

	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}

	@Column(name = "ACIKKSZ")
	public String getAcikksz() {
		return acikksz;
	}

	public void setAcikksz(String acikksz) {
		this.acikksz = acikksz;
	}

	@Column(name = "AMEGN")
	public String getAmegn() {
		return amegn;
	}

	public void setAmegn(String amegn) {
		this.amegn = amegn;
	}

	@Column(name = "AMENNY")
	public BigDecimal getAmenny() {
		return amenny;
	}

	public void setAmenny(BigDecimal amenny) {
		this.amenny = amenny;
	}

	@Column(name = "AEAR")
	public BigDecimal getAear() {
		return aear;
	}

	public void setAear(BigDecimal aear) {
		this.aear = aear;
	}

	@Column(name = "VFIZHDAT")
	public Date getVfizhdat() {
		return vfizhdat;
	}

	public void setVfizhdat(Date vfizhdat) {
		this.vfizhdat = vfizhdat;
	}

	@Column(name = "HNERTEK")
	public BigDecimal getHnertek() {
		return hnertek;
	}

	public void setHnertek(BigDecimal hnertek) {
		this.hnertek = hnertek;
	}

	@Column(name = "HBERTEK")
	public BigDecimal getHbertek() {
		return hbertek;
	}

	public void setHbertek(BigDecimal hbertek) {
		this.hbertek = hbertek;
	}

	@ManyToOne(targetEntity = OrderVO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "sorszam", nullable = false)
	public OrderVO getOrder() {
		return order;
	}

	public void setOrder(OrderVO order) {
		this.order = order;
	}

	@Column(name = "SZLEVSORSZ")
	public String getSzlevsorsz() {
		return szlevsorsz;
	}

	public void setSzlevsorsz(String szlevsorsz) {
		this.szlevsorsz = szlevsorsz;
	}

	@Column(name = "SZAMLSORSZ")
	public String getSzamlsorsz() {
		return szamlsorsz;
	}

	public void setSzamlsorsz(String szamlsorsz) {
		this.szamlsorsz = szamlsorsz;
	}

	@Column(name = "MKATEG1")
	public Integer getMkateg1() {
		return mkateg1;
	}

	public void setMkateg1(Integer mkateg1) {
		this.mkateg1 = mkateg1;
	}

}

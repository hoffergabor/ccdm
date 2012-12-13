package hu.iboard.coandco.datamagic.vo.shipping;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SZLEVTET")
public class ShippingItemVO implements java.io.Serializable {

	private static final long serialVersionUID = -9166807963621959768L;
	private String unikazon;
	private String sorszam;
	private Integer arukod;
	private String acikksz;
	private String amegn;
	private BigDecimal amenny;
	private BigDecimal ear;
	private BigDecimal hnertek;
	private BigDecimal hbertek;
	private ShippingVO shipping;
	
	
	public ShippingItemVO(String unikazon, String sorszam, Integer arukod,
			String acikksz, String amegn, BigDecimal amenny, BigDecimal ear,
			BigDecimal hnertek, BigDecimal hbertek) {
		super();
		this.unikazon = unikazon;
		this.sorszam = sorszam;
		this.arukod = arukod;
		this.acikksz = acikksz;
		this.amegn = amegn;
		this.amenny = amenny;
		this.ear = ear;
		this.hnertek = hnertek;
		this.hbertek = hbertek;
	}
	
	public ShippingItemVO() {
		super();
	}

	public ShippingItemVO(String sorszam) {
		super();
		this.sorszam = sorszam;
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

	@Column(name = "EAR")
	public BigDecimal getEar() {
		return ear;
	}

	public void setEar(BigDecimal ear) {
		this.ear = ear;
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

	@ManyToOne(targetEntity = ShippingVO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "sorszam", nullable = false)
	public ShippingVO getShipping() {
		return shipping;
	}

	public void setShipping(ShippingVO shipping) {
		this.shipping = shipping;
	}
	
	

}

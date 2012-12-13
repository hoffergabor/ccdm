package hu.iboard.coandco.datamagic.generated;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "KTETEL_CDH")
public class Ktetel_CDH implements java.io.Serializable {

	private static final long serialVersionUID = -6214311801687318226L;
	private String unikazon;
	private String ceg;
	private Integer mkateg4;
	private String sorszam2;
	private String unikazon2;
	private Integer arukod;
	private BigDecimal amenny;
	private BigDecimal aear;
	private String ameegys;
	private String megj;
	private BigDecimal szolgszaz;
	private BigDecimal bonyszaz;
	private Aru parentAru;
	
	@Id
	@Column(name = "UNIKAZON", unique = true, nullable = false, length = 20)
	public String getUnikazon() {
		return unikazon;
	}
	public void setUnikazon(String unikazon) {
		this.unikazon = unikazon;
	}
	
	@Column(name = "CEG")
	public String getCeg() {
		return ceg;
	}
	public void setCeg(String ceg) {
		this.ceg = ceg;
	}
	
	@Column(name = "MKATEG4")
	public Integer getMkateg4() {
		return mkateg4;
	}
	public void setMkateg4(Integer mkateg4) {
		this.mkateg4 = mkateg4;
	}
	
	@Column(name = "SORSZAM2")
	public String getSorszam2() {
		return sorszam2;
	}
	public void setSorszam2(String sorszam2) {
		this.sorszam2 = sorszam2;
	}
	
	@Column(name = "UNIKAZON2")
	public String getUnikazon2() {
		return unikazon2;
	}
	public void setUnikazon2(String unikazon2) {
		this.unikazon2 = unikazon2;
	}
	
	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return arukod;
	}
	public void setArukod(Integer arukod) {
		this.arukod = arukod;
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
	
	@Column(name = "AMEEGYS")
	public String getAmeegys() {
		return ameegys;
	}
	public void setAmeegys(String ameegys) {
		this.ameegys = ameegys;
	}
	
	@Column(name = "MEGJ")
	public String getMegj() {
		return megj;
	}
	public void setMegj(String megj) {
		this.megj = megj;
	}
	
	@Column(name = "SZOLGSZAZ")
	public BigDecimal getSzolgszaz() {
		return szolgszaz;
	}
	public void setSzolgszaz(BigDecimal szolgszaz) {
		this.szolgszaz = szolgszaz;
	}
	
	@Column(name = "BONYSZAZ")
	public BigDecimal getBonyszaz() {
		return bonyszaz;
	}
	public void setBonyszaz(BigDecimal bonyszaz) {
		this.bonyszaz = bonyszaz;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ARUKOD", insertable=false, updatable=false)
	public Aru getParentAru() {
		return this.parentAru;
	}
	public void setParentAru(Aru parentAru) {
		this.parentAru = parentAru;
	}
	
}

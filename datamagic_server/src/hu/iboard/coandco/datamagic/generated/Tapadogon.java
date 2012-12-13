package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAPADOGON")
public class Tapadogon implements Serializable{

	private static final long serialVersionUID = -9046512647108943769L;
	
	private Integer rekid;
	private Integer arukod;
	private Integer akod;
	private BigDecimal szorzo;
	private Date insdatum;
	private Integer inskod;
	private Date moddatum;
	private Integer modkod;
	private Boolean istapado;
	
	@Id
	@Column(name = "REKID", unique = true, nullable = false)
	public Integer getRekid() {
		return rekid;
	}
	public void setRekid(Integer rekid) {
		this.rekid = rekid;
	}
	
	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return arukod;
	}
	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}
	
	@Column(name = "AKOD")
	public Integer getAkod() {
		return akod;
	}
	public void setAkod(Integer akod) {
		this.akod = akod;
	}
	
	@Column(name = "SZORZO")
	public BigDecimal getSzorzo() {
		return szorzo;
	}
	public void setSzorzo(BigDecimal szorzo) {
		this.szorzo = szorzo;
	}
	
	@Column(name = "INSDATUM")
	public Date getInsdatum() {
		return insdatum;
	}
	public void setInsdatum(Date insdatum) {
		this.insdatum = insdatum;
	}
	
	@Column(name = "INSKOD")
	public Integer getInskod() {
		return inskod;
	}
	public void setInskod(Integer inskod) {
		this.inskod = inskod;
	}
	
	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}
	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}
	
	@Column(name = "MODKOD")
	public Integer getModkod() {
		return modkod;
	}
	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}
	
	@Column(name = "ISTAPADO")
	public Boolean getIstapado() {
		return istapado;
	}
	public void setIstapado(Boolean istapado) {
		this.istapado = istapado;
	}
}

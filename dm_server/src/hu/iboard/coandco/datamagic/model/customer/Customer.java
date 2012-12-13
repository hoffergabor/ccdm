package hu.iboard.coandco.datamagic.model.customer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAKOSVEVO")
public class Customer implements Serializable {

	private static final long serialVersionUID = 7185983226534187105L;

	private Integer lakosKod;
	private String nev;
	private String irsz;
	private String varos;
	private String cim;
	private String tel;
	private Integer insKod;
	private Date insDatum;
	private Integer modKod;
	private Date modDatum;
	private Boolean isDeleted;
	private Integer szlFizeto;
	private String webLogin;
	private String webPassw;
	
	@Id
	@Column(name = "LAKOSKOD", unique = true, nullable = false, length = 11)
	public Integer getLakosKod() {
		return lakosKod;
	}
	public void setLakosKod(Integer lakosKod) {
		this.lakosKod = lakosKod;
	}
	
	@Column(name = "NEV", length = 50)
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	@Column(name = "IRSZ", length = 10)
	public String getIrsz() {
		return irsz;
	}
	public void setIrsz(String irsz) {
		this.irsz = irsz;
	}
	
	@Column(name = "VAROS", length = 30)
	public String getVaros() {
		return varos;
	}
	public void setVaros(String varos) {
		this.varos = varos;
	}
	
	@Column(name = "CIM", length = 50)
	public String getCim() {
		return cim;
	}
	public void setCim(String cim) {
		this.cim = cim;
	}
	
	@Column(name = "TEL", length = 30)
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Column(name = "INSKOD")
	public Integer getInsKod() {
		return insKod;
	}
	public void setInsKod(Integer insKod) {
		this.insKod = insKod;
	}
	
	@Column(name = "INSDATUM")
	public Date getInsDatum() {
		return insDatum;
	}
	public void setInsDatum(Date insDatum) {
		this.insDatum = insDatum;
	}
	
	@Column(name = "MODKOD")
	public Integer getModKod() {
		return modKod;
	}
	public void setModKod(Integer modKod) {
		this.modKod = modKod;
	}
	
	@Column(name = "MODDATUM")
	public Date getModDatum() {
		return modDatum;
	}
	public void setModDatum(Date modDatum) {
		this.modDatum = modDatum;
	}
	
	@Column(name = "ISDELETED")
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Column(name = "SZLFIZETO")
	public Integer getSzlFizeto() {
		return szlFizeto;
	}
	public void setSzlFizeto(Integer szlFizeto) {
		this.szlFizeto = szlFizeto;
	}
	
	@Column(name = "WEBLOGIN",length=40)
	public String getWebLogin() {
		return webLogin;
	}
	public void setWebLogin(String webLogin) {
		this.webLogin = webLogin;
	}
	
	@Column(name = "WEBPASSW",length=40)
	public String getWebPassw() {
		return webPassw;
	}
	public void setWebPassw(String webPassw) {
		this.webPassw = webPassw;
	}

}

package hu.iboard.coandco.datamagic.vo.prarucsatolo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRARUCSATOLO")
public class PRArucsatolo implements Serializable{

	private static final long serialVersionUID = -8784773140050444659L;
	
	private Integer id;
	private Integer projekt;
	private Integer aru;
	private Integer darabszam;
	private Integer status;
	private Integer forras;
	private Integer darabjegyzek;
	
	
	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "PROJEKT")
	public Integer getProjekt() {
		return projekt;
	}
	public void setProjekt(Integer projekt) {
		this.projekt = projekt;
	}
	
	@Column(name = "ARU")
	public Integer getAru() {
		return aru;
	}
	public void setAru(Integer aru) {
		this.aru = aru;
	}
	
	@Column(name = "DARABSZAM")
	public Integer getDarabszam() {
		return darabszam;
	}
	public void setDarabszam(Integer darabszam) {
		this.darabszam = darabszam;
	}
	
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Column(name = "FORRAS")
	public Integer getForras() {
		return forras;
	}
	public void setForras(Integer forras) {
		this.forras = forras;
	}
	
	@Column(name = "DARABJEGYZEK")
	public Integer getDarabjegyzek() {
		return darabjegyzek;
	}
	public void setDarabjegyzek(Integer darabjegyzek) {
		this.darabjegyzek = darabjegyzek;
	}
	
	
	
	

}

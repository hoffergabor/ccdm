package hu.iboard.coandco.datamagic.model.bizkomment;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BIZKOMMENT")
public class Bizkomment implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6840941253421426793L;
	
	private String bizazon;
	private String bizmegj;
	private int modkod;
	private Date moddatum;
	private String bizmegjeng;
	
	
	@Id
	@Column(name = "BIZAZON", unique = true, nullable = false)
	public String getBizazon() {
		return bizazon;
	}
	
	public void setBizazon(String bizazon) {
		this.bizazon = bizazon;
	}
	
	@Column(name = "BIZMEGJ")
	public String getBizmegj() {
		return bizmegj;
	}
	
	public void setBizmegj(String bizmegj) {
		this.bizmegj = bizmegj;
	}
	
	@Column(name = "MODKOD")
	public int getModkod() {
		return modkod;
	}
	
	public void setModkod(int modkod) {
		this.modkod = modkod;
	}
	
	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}
	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}

	@Column(name = "BIZMEGJENG")
	public String getBizmegjeng() {
		return bizmegjeng;
	}

	public void setBizmegjeng(String bizmegjeng) {
		this.bizmegjeng = bizmegjeng;
	}
	
	
	
	

}

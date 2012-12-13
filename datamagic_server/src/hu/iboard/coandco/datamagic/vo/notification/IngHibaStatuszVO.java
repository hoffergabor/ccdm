package hu.iboard.coandco.datamagic.vo.notification;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INGHIBASTATUSZ")
public class IngHibaStatuszVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1819374862273226763L;
	private Integer id;
	private String nev;
	private Date moddatum;
	private String x;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "NEV")
	public String getNev() {
		return nev;
	}
	public void setNev(String nev) {
		this.nev = nev;
	}
	
	@Column(name = "MODDATUM")
	public Date getModdatum() {
		return moddatum;
	}
	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}
	
	@Column(name = "X")
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	
	

}

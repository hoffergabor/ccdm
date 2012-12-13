package hu.iboard.coandco.datamagic.generated;

// Generated Aug 13, 2009 10:40:44 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * IntMunkav generated by hbm2java
 */
@Entity
@Table(name = "INTMUNKAV")//, catalog = "dms")
public class IntMunkav implements java.io.Serializable {

	private int id;
	private String x;
	private String nev;
	private String irsz;
	private String varos;
	private String cim;
	private String elerhetos;

	public IntMunkav() {
	}

	public IntMunkav(int id) {
		this.id = id;
	}

	public IntMunkav(int id, String x, String nev, String irsz, String varos,
			String cim, String elerhetos) {
		this.id = id;
		this.x = x;
		this.nev = nev;
		this.irsz = irsz;
		this.varos = varos;
		this.cim = cim;
		this.elerhetos = elerhetos;
	}

	@Id
	@Column(name = "Id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "x", length = 1)
	public String getX() {
		return this.x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@Column(name = "Nev", length = 25)
	public String getNev() {
		return this.nev;
	}

	public void setNev(String nev) {
		this.nev = nev;
	}

	@Column(name = "Irsz", length = 10)
	public String getIrsz() {
		return this.irsz;
	}

	public void setIrsz(String irsz) {
		this.irsz = irsz;
	}

	@Column(name = "Varos", length = 50)
	public String getVaros() {
		return this.varos;
	}

	public void setVaros(String varos) {
		this.varos = varos;
	}

	@Column(name = "Cim", length = 150)
	public String getCim() {
		return this.cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	@Column(name = "elerhetos", length = 150)
	public String getElerhetos() {
		return this.elerhetos;
	}

	public void setElerhetos(String elerhetos) {
		this.elerhetos = elerhetos;
	}

}

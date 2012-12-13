package hu.iboard.coandco.datamagic.vo.flattype;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LAKASTIPUS")
public class FlatTypeVO implements Serializable {

	private static final long serialVersionUID = -27680413229602389L;
	
	private Integer id;
	private String nev;
	private String x;
	
	@Id
	@Column(name = "ID", unique = true, nullable = false, length = 15)
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
	
	@Column(name = "X")
	public String getX() {
		return x;
	}
	public void setX(String x) {
		this.x = x;
	}
	
	

}

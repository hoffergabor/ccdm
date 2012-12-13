package hu.iboard.coandco.datamagic.generated;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
//import javax.persistence.Id;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "RENDMEGJ")
public class Rendmegj implements java.io.Serializable {

	private static final long serialVersionUID = 8787010192872454992L;
	private Rendel rendel;
	private String sorszam;
	private String megj;
	private String kivagybe;
	private String unikazon;
	private Integer tsorsz;
	
	public Rendmegj() {
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SORSZAM", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)	
	public Rendel getRendel() {
		return this.rendel;
	}
    
    public void setRendel(Rendel rendel) {
    	this.rendel = rendel;
    }

	@Column(name = "SORSZAM")
	public String getSorszam() {
		return this.sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}    
    
	@Column(name = "MEGJ", length = 100)
	public String getMegj() {
		return this.megj;
	}

	public void setMegj(String megj) {
		this.megj = megj;
	}	
	
	@Column(name = "KIVAGYBE", length = 1)
	public String getKivagybe() {
		return this.kivagybe;
	}

	public void setKivagybe(String kivagybe) {
		this.kivagybe = kivagybe;
	}

	@Id
	@Column(name = "UNIKAZON", unique = true, nullable = false, length = 10)
	public String getUnikazon() {
		return this.unikazon;
	}
	
	public void setUnikazon(String unikazon) {
		this.unikazon = unikazon;
	}
	
	@Column(name = "TSORSZ")
	public Integer getTsorsz() {
		return this.tsorsz;
	}

	public void setTsorsz(Integer tsorsz) {
		this.tsorsz = tsorsz;
	}	
}

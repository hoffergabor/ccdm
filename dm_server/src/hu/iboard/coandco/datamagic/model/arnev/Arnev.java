package hu.iboard.coandco.datamagic.model.arnev;

import hu.iboard.coandco.datamagic.model.deviza.Deviza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ARNEV")
public class Arnev implements java.io.Serializable {

	private static final long serialVersionUID = 6324789856525705251L;
	private int kod;
	private String megn;
	private String devizanev;
	private Deviza deviza;
	private Integer arsorafa;

	public Arnev() {
	}

	public Arnev(int kod) {
		this.kod = kod;
	}

	public Arnev(int kod, String megn, String devizanev, Deviza deviza, Integer arsorafa) {
		this.kod = kod;
		this.megn = megn;
		this.devizanev = devizanev;
		this.deviza = deviza;
		this.arsorafa = arsorafa;
	}

	@Id
	@Column(name = "KOD", unique = true, nullable = false)
	public int getKod() {
		return this.kod;
	}

	public void setKod(int kod) {
		this.kod = kod;
	}

	@Column(name = "MEGN", length = 50)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Column(name = "DEVIZANEV", length = 4)
	public String getDevizanev() {
		return this.devizanev;
	}

	public void setDevizanev(String devizanev) {
		this.devizanev = devizanev;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "DEVIZANEV", insertable = false, updatable = false)
	public Deviza getDeviza() {
		return this.deviza;
	}

	public void setDeviza(Deviza deviza) {
		this.deviza = deviza;
	}

	@Column(name = "ARSORAFA")
	public Integer getArsorafa() {
		return this.arsorafa;
	}

	public void setArsorafa(Integer arsorafa) {
		this.arsorafa = arsorafa;
	}

}

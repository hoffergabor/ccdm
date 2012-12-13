package hu.iboard.coandco.datamagic.model.dolgozo;

// Generated Aug 11, 2009 4:42:50 PM by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Dolgozo generated by hbm2java
 */
@Entity
@Table(name = "DOLGOZO")
public class Dolgozo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7502809575827630016L;
	private int dkod;
	private String dnev;
	private String dirsz;
	private String dvaros;
	private String dcim;
	private String dtelefon;
	private String jelszo;
	private String menujog;
	private boolean modiar;
	private int defraktar;
	private int defarsor;
	private String settings;
	private String drights;
	private boolean smplkimut;
	private String errormail;
	private String lastdir;
	private String email;
	private String csoport;
	private boolean cegjogmod;
	private int elszarkod;
	private boolean bekarvisib;
	private String webUser;
	private String webPass;

	public Dolgozo() {
	}

	public Dolgozo(int dkod, String dnev, String dirsz, String dvaros,
			String dcim, String dtelefon, String jelszo, String menujog,
			boolean modiar, int defraktar, int defarsor, String settings,
			String drights, boolean smplkimut, String errormail,
			String lastdir, String email, String csoport, boolean cegjogmod,
			int elszarkod, boolean bekarvisib) {
		this.dkod = dkod;
		this.dnev = dnev;
		this.dirsz = dirsz;
		this.dvaros = dvaros;
		this.dcim = dcim;
		this.dtelefon = dtelefon;
		this.jelszo = jelszo;
		this.menujog = menujog;
		this.modiar = modiar;
		this.defraktar = defraktar;
		this.defarsor = defarsor;
		this.settings = settings;
		this.drights = drights;
		this.smplkimut = smplkimut;
		this.errormail = errormail;
		this.lastdir = lastdir;
		this.email = email;
		this.csoport = csoport;
		this.cegjogmod = cegjogmod;
		this.elszarkod = elszarkod;
		this.bekarvisib = bekarvisib;
	}

	@Id
	@Column(name = "DKOD", unique = true)
	public int getDkod() {
		return this.dkod;
	}

	public void setDkod(int dkod) {
		this.dkod = dkod;
	}

	@Column(name = "DNEV", length = 40)
	public String getDnev() {
		return this.dnev;
	}

	public void setDnev(String dnev) {
		this.dnev = dnev;
	}

	@Column(name = "DIRSZ", length = 4)
	public String getDirsz() {
		return this.dirsz;
	}

	public void setDirsz(String dirsz) {
		this.dirsz = dirsz;
	}

	@Column(name = "DVAROS", length = 40)
	public String getDvaros() {
		return this.dvaros;
	}

	public void setDvaros(String dvaros) {
		this.dvaros = dvaros;
	}

	@Column(name = "DCIM", length = 50)
	public String getDcim() {
		return this.dcim;
	}

	public void setDcim(String dcim) {
		this.dcim = dcim;
	}

	@Column(name = "DTELEFON", length = 40)
	public String getDtelefon() {
		return this.dtelefon;
	}

	public void setDtelefon(String dtelefon) {
		this.dtelefon = dtelefon;
	}

	@Column(name = "JELSZO", length = 10)
	public String getJelszo() {
		return this.jelszo;
	}

	public void setJelszo(String jelszo) {
		this.jelszo = jelszo;
	}

	@Column(name = "MENUJOG", length = 65535)
	public String getMenujog() {
		return this.menujog;
	}

	public void setMenujog(String menujog) {
		this.menujog = menujog;
	}

	@Column(name = "MODIAR")
	public boolean isModiar() {
		return this.modiar;
	}

	public void setModiar(boolean modiar) {
		this.modiar = modiar;
	}

	@Column(name = "DEFRAKTAR")
	public int getDefraktar() {
		return this.defraktar;
	}

	public void setDefraktar(int defraktar) {
		this.defraktar = defraktar;
	}

	@Column(name = "DEFARSOR")
	public int getDefarsor() {
		return this.defarsor;
	}

	public void setDefarsor(int defarsor) {
		this.defarsor = defarsor;
	}

	@Column(name = "SETTINGS", length = 65535)
	public String getSettings() {
		return this.settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	@Column(name = "DRIGHTS", length = 200)
	public String getDrights() {
		return this.drights;
	}

	public void setDrights(String drights) {
		this.drights = drights;
	}

	@Column(name = "SMPLKIMUT")
	public boolean isSmplkimut() {
		return this.smplkimut;
	}

	public void setSmplkimut(boolean smplkimut) {
		this.smplkimut = smplkimut;
	}

	@Column(name = "ERRORMAIL", length = 100)
	public String getErrormail() {
		return this.errormail;
	}

	public void setErrormail(String errormail) {
		this.errormail = errormail;
	}

	@Column(name = "LASTDIR", length = 254)
	public String getLastdir() {
		return this.lastdir;
	}

	public void setLastdir(String lastdir) {
		this.lastdir = lastdir;
	}

	@Column(name = "EMAIL", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CSOPORT", length = 20)
	public String getCsoport() {
		return this.csoport;
	}

	public void setCsoport(String csoport) {
		this.csoport = csoport;
	}

	@Column(name = "CEGJOGMOD")
	public boolean isCegjogmod() {
		return this.cegjogmod;
	}

	public void setCegjogmod(boolean cegjogmod) {
		this.cegjogmod = cegjogmod;
	}

	@Column(name = "ELSZARKOD")
	public int getElszarkod() {
		return this.elszarkod;
	}

	public void setElszarkod(int elszarkod) {
		this.elszarkod = elszarkod;
	}

	@Column(name = "BEKARVISIB")
	public boolean isBekarvisib() {
		return this.bekarvisib;
	}

	public void setBekarvisib(boolean bekarvisib) {
		this.bekarvisib = bekarvisib;
	}

	@Column(name = "WEBUSER")
	public String getWebUser() {
		return webUser;
	}

	public void setWebUser(String webUser) {
		this.webUser = webUser;
	}

	@Column(name = "WEBPASS")
	public String getWebPass() {
		return webPass;
	}

	public void setWebPass(String webPass) {
		this.webPass = webPass;
	}
	
	

}

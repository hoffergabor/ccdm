package hu.iboard.coandco.datamagic.vo.notification;

import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Project;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "INGHIBABEJ")
public class NotificationVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2312697628935794815L;

	private Integer id;
	private String cim;
	private String leiras;
	private Date bejelentes_ido;
	private Date eszleles_ido;
	private String kertjav_ido;
	private Date hatarido;
	private Boolean urgent;
	private Integer user;
	private String statusz;
	private String telefon;
	private String komment;
	private Boolean figyel;
	private Project project;
	private AruExt aruExt;
	private Intezkedes intezkedes;
	private Date modositva;
	private IngHibaStatuszVO ingHibaStatusz;
	private String x;
	private char userType;
	private Boolean szamlazando;
	private Boolean alapkezelo;
	private Partner vevo;
	private Integer vevoId;
	private Boolean visszahiv;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false, length = 11)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CIM")
	public String getCim() {
		return cim;
	}

	public void setCim(String cim) {
		this.cim = cim;
	}

	@Column(name = "LEIRAS", nullable = false)
	public String getLeiras() {
		return leiras;
	}

	public void setLeiras(String leiras) {
		this.leiras = leiras;
	}

	@Column(name = "BEJELENTVE", nullable = false)
	public Date getBejelentes_ido() {
		return bejelentes_ido;
	}

	public void setBejelentes_ido(Date bejelentes_ido) {
		this.bejelentes_ido = bejelentes_ido;
	}

	@Column(name = "ESZLELVE")
	public Date getEszleles_ido() {
		return eszleles_ido;
	}

	public void setEszleles_ido(Date eszleles_ido) {
		this.eszleles_ido = eszleles_ido;
	}


	@Column(name = "KERTJAVIDO")
	public String getKertjav_ido() {
		return kertjav_ido;
	}

	public void setKertjav_ido(String kertjav_ido) {
		this.kertjav_ido = kertjav_ido;
	}

	@Column(name = "HATARIDO")
	public Date getHatarido() {
		return hatarido;
	}

	public void setHatarido(Date hatarido) {
		this.hatarido = hatarido;
	}

	@Column(name = "STATUSZ", nullable = false)
	public String getStatusz() {
		return statusz;
	}

	public void setStatusz(String statusz) {
		this.statusz = statusz;
	}

	@Column(name = "SURGOS")
	public Boolean getUrgent() {
		return urgent;
	}

	public void setUrgent(Boolean urgent) {
		this.urgent = urgent;
	}

	@Column(name = "TELEFON", nullable = true)
	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Column(name = "KOMMENT", nullable = true)
	public String getKomment() {
		return komment;
	}

	public void setKomment(String komment) {
		this.komment = komment;
	}

	@Column(name = "FIGYEL")
	public Boolean getFigyel() {
		return figyel;
	}

	public void setFigyel(Boolean figyel) {
		this.figyel = figyel;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Project.class)
	@JoinColumn(name = "PROJECT")
	@NotFound(action = NotFoundAction.IGNORE)
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = AruExt.class)
	@JoinColumn(name = "ARUEXT")
	@NotFound(action = NotFoundAction.IGNORE)
	public AruExt getAruExt() {
		return aruExt;
	}

	public void setAruExt(AruExt aruExt) {
		this.aruExt = aruExt;
	}

	@Column(name = "JUSER")
	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Intezkedes.class)
	@JoinColumn(name = "INTEZKEDES")
	@NotFound(action = NotFoundAction.IGNORE)
	public Intezkedes getIntezkedes() {
		return intezkedes;
	}

	public void setIntezkedes(Intezkedes intezkedes) {
		this.intezkedes = intezkedes;
	}

	@Column(name = "MODDATUM")
	public Date getModositva() {
		return modositva;
	}

	public void setModositva(Date modositva) {
		this.modositva = modositva;
	}

	@Column(name = "X")
	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = IngHibaStatuszVO.class)
	@JoinColumn(name = "STATUSZID")
	@NotFound(action = NotFoundAction.IGNORE)
	public IngHibaStatuszVO getIngHibaStatusz() {
		return ingHibaStatusz;
	}

	public void setIngHibaStatusz(IngHibaStatuszVO ingHibaStatusz) {
		this.ingHibaStatusz = ingHibaStatusz;
	}

	@Column(name = "USERTIPUS")
	public char getUserType() {
		return userType;
	}

	public void setUserType(char userType) {
		this.userType = userType;
	}

	@Column(name = "SZAMLAZANDO")
	public Boolean getSzamlazando() {
		return szamlazando;
	}

	public void setSzamlazando(Boolean szamlazando) {
		this.szamlazando = szamlazando;
	}

	@Column(name = "ALAPKEZELO")
	public Boolean getAlapkezelo() {
		return alapkezelo;
	}

	public void setAlapkezelo(Boolean alapkezelo) {
		this.alapkezelo = alapkezelo;
	}

	@OneToOne(fetch = FetchType.LAZY, targetEntity = Partner.class)
	@JoinColumn(name = "VEVO", insertable = false, updatable = false)
	@NotFound(action = NotFoundAction.IGNORE)
	public Partner getVevo() {
		return vevo;
	}

	public void setVevo(Partner vevo) {
		this.vevo = vevo;
	}

	@Column(name = "VEVO")
	public Integer getVevoId() {
		return vevoId;
	}

	public void setVevoId(Integer vevoId) {
		this.vevoId = vevoId;
	}

	@Column(name = "VISSZAHIV")
	public Boolean getVisszahiv() {
		return visszahiv;
	}

	public void setVisszahiv(Boolean visszahiv) {
		this.visszahiv = visszahiv;
	}

	
}

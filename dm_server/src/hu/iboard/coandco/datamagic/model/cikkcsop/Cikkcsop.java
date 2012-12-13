package hu.iboard.coandco.datamagic.model.cikkcsop;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CIKKCSOP_KISKER")
public class Cikkcsop implements java.io.Serializable {

	private static final long serialVersionUID = -4040604672205676619L;
	private BigDecimal kod;
	private String megn;
	private boolean engadhat;
	private String alapmegys;
	private Integer szjvvtsz;
	private String szjvtsz;
	private Integer afakod;
	private BigDecimal fth1;
	private BigDecimal fth2;
	private BigDecimal fth3;
	private BigDecimal fth4;
	private BigDecimal devh1;
	private BigDecimal devh2;
	private BigDecimal devh3;
	private BigDecimal devh4;
	private BigDecimal fth5;
	private BigDecimal fth6;
	private BigDecimal fth7;
	private BigDecimal fth8;
	private BigDecimal fth9;
	private BigDecimal fth10;
	private BigDecimal fth11;
	private BigDecimal fth12;
	private BigDecimal fth13;
	private BigDecimal fth14;
	private BigDecimal fth15;
	private BigDecimal vamszaz;
	private BigDecimal szallszaz;
	private Integer tvrszorzo;
	private boolean garijar;
	private boolean kellserial;
	private boolean moreserial;
	private boolean receptkell;
	private String fkszam;
	private String fkszam1;
	private String fkszam2;
	private String fkszam3;
	private String fkszam4;
	private Integer sortkod;
	private Date moddatum;
	private Integer modkod;
	private BigDecimal gyujto;
	private BigDecimal tovazkod;

	public Cikkcsop() {
	}

	public Cikkcsop(BigDecimal kod) {
		this.kod = kod;
	}

	@Id
	@Column(name = "KOD", unique = true, nullable = false, precision = 12, scale = 0)
	public BigDecimal getKod() {
		return this.kod;
	}

	public void setKod(BigDecimal kod) {
		this.kod = kod;
	}

	@Column(name = "MEGN", length = 30)
	public String getMegn() {
		return this.megn;
	}

	public void setMegn(String megn) {
		this.megn = megn;
	}

	@Column(name = "ENGADHAT")
	public boolean isEngadhat() {
		return this.engadhat;
	}

	public void setEngadhat(boolean engadhat) {
		this.engadhat = engadhat;
	}

	@Column(name = "ALAPMEGYS", length = 10)
	public String getAlapmegys() {
		return this.alapmegys;
	}

	public void setAlapmegys(String alapmegys) {
		this.alapmegys = alapmegys;
	}

	@Column(name = "SZJVVTSZ", precision = 7, scale = 0)
	public Integer getSzjvvtsz() {
		return this.szjvvtsz;
	}

	public void setSzjvvtsz(Integer szjvvtsz) {
		this.szjvvtsz = szjvvtsz;
	}

	@Column(name = "SZJVTSZ", length = 10)
	public String getSzjvtsz() {
		return this.szjvtsz;
	}

	public void setSzjvtsz(String szjvtsz) {
		this.szjvtsz = szjvtsz;
	}

	@Column(name = "AFAKOD", precision = 7, scale = 0)
	public Integer getAfakod() {
		return this.afakod;
	}

	public void setAfakod(Integer afakod) {
		this.afakod = afakod;
	}

	@Column(name = "FTH1", precision = 9)
	public BigDecimal getFth1() {
		return this.fth1;
	}

	public void setFth1(BigDecimal fth1) {
		this.fth1 = fth1;
	}

	@Column(name = "FTH2", precision = 9)
	public BigDecimal getFth2() {
		return this.fth2;
	}

	public void setFth2(BigDecimal fth2) {
		this.fth2 = fth2;
	}

	@Column(name = "FTH3", precision = 9)
	public BigDecimal getFth3() {
		return this.fth3;
	}

	public void setFth3(BigDecimal fth3) {
		this.fth3 = fth3;
	}

	@Column(name = "FTH4", precision = 9)
	public BigDecimal getFth4() {
		return this.fth4;
	}

	public void setFth4(BigDecimal fth4) {
		this.fth4 = fth4;
	}

	@Column(name = "DEVH1", precision = 9)
	public BigDecimal getDevh1() {
		return this.devh1;
	}

	public void setDevh1(BigDecimal devh1) {
		this.devh1 = devh1;
	}

	@Column(name = "DEVH2", precision = 9)
	public BigDecimal getDevh2() {
		return this.devh2;
	}

	public void setDevh2(BigDecimal devh2) {
		this.devh2 = devh2;
	}

	@Column(name = "DEVH3", precision = 9)
	public BigDecimal getDevh3() {
		return this.devh3;
	}

	public void setDevh3(BigDecimal devh3) {
		this.devh3 = devh3;
	}

	@Column(name = "DEVH4", precision = 9)
	public BigDecimal getDevh4() {
		return this.devh4;
	}

	public void setDevh4(BigDecimal devh4) {
		this.devh4 = devh4;
	}

	@Column(name = "FTH5", precision = 9)
	public BigDecimal getFth5() {
		return this.fth5;
	}

	public void setFth5(BigDecimal fth5) {
		this.fth5 = fth5;
	}

	@Column(name = "FTH6", precision = 9)
	public BigDecimal getFth6() {
		return this.fth6;
	}

	public void setFth6(BigDecimal fth6) {
		this.fth6 = fth6;
	}

	@Column(name = "FTH7", precision = 9)
	public BigDecimal getFth7() {
		return this.fth7;
	}

	public void setFth7(BigDecimal fth7) {
		this.fth7 = fth7;
	}

	@Column(name = "FTH8", precision = 9)
	public BigDecimal getFth8() {
		return this.fth8;
	}

	public void setFth8(BigDecimal fth8) {
		this.fth8 = fth8;
	}

	@Column(name = "FTH9", precision = 9)
	public BigDecimal getFth9() {
		return this.fth9;
	}

	public void setFth9(BigDecimal fth9) {
		this.fth9 = fth9;
	}

	@Column(name = "FTH10", precision = 9)
	public BigDecimal getFth10() {
		return this.fth10;
	}

	public void setFth10(BigDecimal fth10) {
		this.fth10 = fth10;
	}

	@Column(name = "FTH11", precision = 9)
	public BigDecimal getFth11() {
		return this.fth11;
	}

	public void setFth11(BigDecimal fth11) {
		this.fth11 = fth11;
	}

	@Column(name = "FTH12", precision = 9)
	public BigDecimal getFth12() {
		return this.fth12;
	}

	public void setFth12(BigDecimal fth12) {
		this.fth12 = fth12;
	}

	@Column(name = "FTH13", precision = 9)
	public BigDecimal getFth13() {
		return this.fth13;
	}

	public void setFth13(BigDecimal fth13) {
		this.fth13 = fth13;
	}

	@Column(name = "FTH14", precision = 9)
	public BigDecimal getFth14() {
		return this.fth14;
	}

	public void setFth14(BigDecimal fth14) {
		this.fth14 = fth14;
	}

	@Column(name = "FTH15", precision = 9)
	public BigDecimal getFth15() {
		return this.fth15;
	}

	public void setFth15(BigDecimal fth15) {
		this.fth15 = fth15;
	}

	@Column(name = "VAMSZAZ", precision = 13)
	public BigDecimal getVamszaz() {
		return this.vamszaz;
	}

	public void setVamszaz(BigDecimal vamszaz) {
		this.vamszaz = vamszaz;
	}

	@Column(name = "SZALLSZAZ", precision = 13)
	public BigDecimal getSzallszaz() {
		return this.szallszaz;
	}

	public void setSzallszaz(BigDecimal szallszaz) {
		this.szallszaz = szallszaz;
	}

	@Column(name = "TVRSZORZO")
	public Integer getTvrszorzo() {
		return this.tvrszorzo;
	}

	public void setTvrszorzo(Integer tvrszorzo) {
		this.tvrszorzo = tvrszorzo;
	}

	@Column(name = "GARIJAR")
	public boolean isGarijar() {
		return this.garijar;
	}

	public void setGarijar(boolean garijar) {
		this.garijar = garijar;
	}

	@Column(name = "KELLSERIAL")
	public boolean isKellserial() {
		return this.kellserial;
	}

	public void setKellserial(boolean kellserial) {
		this.kellserial = kellserial;
	}

	@Column(name = "MORESERIAL")
	public boolean isMoreserial() {
		return this.moreserial;
	}

	public void setMoreserial(boolean moreserial) {
		this.moreserial = moreserial;
	}

	@Column(name = "RECEPTKELL")
	public boolean isReceptkell() {
		return this.receptkell;
	}

	public void setReceptkell(boolean receptkell) {
		this.receptkell = receptkell;
	}

	@Column(name = "FKSZAM", length = 8)
	public String getFkszam() {
		return this.fkszam;
	}

	public void setFkszam(String fkszam) {
		this.fkszam = fkszam;
	}

	@Column(name = "FKSZAM1", length = 8)
	public String getFkszam1() {
		return this.fkszam1;
	}

	public void setFkszam1(String fkszam1) {
		this.fkszam1 = fkszam1;
	}

	@Column(name = "FKSZAM2", length = 8)
	public String getFkszam2() {
		return this.fkszam2;
	}

	public void setFkszam2(String fkszam2) {
		this.fkszam2 = fkszam2;
	}

	@Column(name = "FKSZAM3", length = 8)
	public String getFkszam3() {
		return this.fkszam3;
	}

	public void setFkszam3(String fkszam3) {
		this.fkszam3 = fkszam3;
	}

	@Column(name = "FKSZAM4", length = 8)
	public String getFkszam4() {
		return this.fkszam4;
	}

	public void setFkszam4(String fkszam4) {
		this.fkszam4 = fkszam4;
	}

	@Column(name = "SORTKOD")
	public Integer getSortkod() {
		return this.sortkod;
	}

	public void setSortkod(Integer sortkod) {
		this.sortkod = sortkod;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "MODDATUM", length = 19)
	public Date getModdatum() {
		return this.moddatum;
	}

	public void setModdatum(Date moddatum) {
		this.moddatum = moddatum;
	}

	@Column(name = "MODKOD")
	public Integer getModkod() {
		return this.modkod;
	}

	public void setModkod(Integer modkod) {
		this.modkod = modkod;
	}

	@Column(name = "GYUJTO")
	public BigDecimal getGyujto() {
		return gyujto;
	}

	public void setGyujto(BigDecimal gyujto) {
		this.gyujto = gyujto;
	}

	@Column(name = "TOVAZKOD")
	public BigDecimal getTovazkod() {
		return tovazkod;
	}

	public void setTovazkod(BigDecimal tovazkod) {
		this.tovazkod = tovazkod;
	}
	
	
	

}

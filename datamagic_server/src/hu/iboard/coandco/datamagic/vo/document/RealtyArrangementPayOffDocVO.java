package hu.iboard.coandco.datamagic.vo.document;

import java.util.List;

public class RealtyArrangementPayOffDocVO {

	private String tulajdonos;
	private String sorszam;
	private String szCimzett;
	private String ingCim;
	private String bejelento;
	private String bejelentesIdo;
	private String hibaLeiras;
	private String munkaLeiras;
	private String bizonylatok;
	private String osszNettoAr;
	private String vallakozo;
	private String teljesites;
	private List<RealtyArrangementPayOffDolgDocVO> dolgozo;
	private List<RealtyArrangementPayOffAnyagDocVO> anyag;

	public String getTulajdonos() {
		return tulajdonos;
	}

	public void setTulajdonos(String tulajdonos) {
		this.tulajdonos = tulajdonos;
	}

	public String getSorszam() {
		return sorszam;
	}

	public void setSorszam(String sorszam) {
		this.sorszam = sorszam;
	}

	public String getSzCimzett() {
		return szCimzett;
	}

	public void setSzCimzett(String szCimzett) {
		this.szCimzett = szCimzett;
	}

	public String getIngCim() {
		return ingCim;
	}

	public void setIngCim(String ingCim) {
		this.ingCim = ingCim;
	}

	public String getBejelento() {
		return bejelento;
	}

	public void setBejelento(String bejelento) {
		this.bejelento = bejelento;
	}

	public String getBejelentesIdo() {
		return bejelentesIdo;
	}

	public void setBejelentesIdo(String bejelentesIdo) {
		this.bejelentesIdo = bejelentesIdo;
	}

	public String getHibaLeiras() {
		return hibaLeiras;
	}

	public void setHibaLeiras(String hibaLeiras) {
		this.hibaLeiras = hibaLeiras;
	}

	public String getMunkaLeiras() {
		return munkaLeiras;
	}

	public void setMunkaLeiras(String munkaLeiras) {
		this.munkaLeiras = munkaLeiras;
	}

	public String getBizonylatok() {
		return bizonylatok;
	}

	public void setBizonylatok(String bizonylatok) {
		this.bizonylatok = bizonylatok;
	}

	public String getOsszNettoAr() {
		return osszNettoAr;
	}

	public void setOsszNettoAr(String osszNettoAr) {
		this.osszNettoAr = osszNettoAr;
	}

	public String getVallakozo() {
		return vallakozo;
	}

	public void setVallakozo(String vallakozo) {
		this.vallakozo = vallakozo;
	}

	public String getTeljesites() {
		return teljesites;
	}

	public void setTeljesites(String teljesites) {
		this.teljesites = teljesites;
	}

	public List<RealtyArrangementPayOffDolgDocVO> getDolgozo() {
		return dolgozo;
	}

	public void setDolgozo(List<RealtyArrangementPayOffDolgDocVO> dolgozo) {
		this.dolgozo = dolgozo;
	}

	public List<RealtyArrangementPayOffAnyagDocVO> getAnyag() {
		return anyag;
	}

	public void setAnyag(List<RealtyArrangementPayOffAnyagDocVO> anyag) {
		this.anyag = anyag;
	}

}

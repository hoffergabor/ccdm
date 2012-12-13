package hu.iboard.coandco.datamagic.presentation.controllers.realtyarrangementservice;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@ManagedBean(name = "realtyarrangementserviceData")
@SessionScoped
public class RealtyArrangementData implements Serializable {

	private static final long serialVersionUID = 259252704064823162L;

	private List<RealtyArrangementVO> realtyArrangements = new ArrayList<RealtyArrangementVO>();
	private List<Aattachs> attachs = new ArrayList<Aattachs>();
	private RealtyArrangementVO selectedRealty = null;
	private Long netto = null;
	private Date to;
	private Date from;
	private String choosenDateTypeFiled = "datum";
	private Integer vevokod;
	private String realtySearchText;
	private Integer selectedProjectId;
	private List<SelectItem> flatItems;
	private List<SelectItem> allFlatItems;
	private String filteredText;
	private String ownerSearchText;
	private Integer selectedOwnerId;
	private List<SelectItem> ownerItems;
	private List<SelectItem> allOwnerItems;
	private String renterSearchText;
	private Integer selectedRenterId;
	private List<SelectItem> renterItems;
	private List<SelectItem> allRentalItems;
	private List<Integer> lakasIds;
	private Aattachs selectedAattach;
	private StreamedContent downloadableFile = new DefaultStreamedContent();
	private List<String> allBejelento;
	private String bejelentoSearchText;
	private String selectedBejelento;
	private List<RealtyArrangementVO> realtyFiltered;
	private List<Object[]> intezaruk = new ArrayList<Object[]>();

	public List<RealtyArrangementVO> getRealtyArrangements() {
		return realtyArrangements;
	}

	public void setRealtyArrangements(List<RealtyArrangementVO> realtyArrangements) {
		this.realtyArrangements = realtyArrangements;
	}

	public RealtyArrangementVO getSelectedRealty() {
		return selectedRealty;
	}

	public void setSelectedRealty(RealtyArrangementVO selectedRealty) {
		this.selectedRealty = selectedRealty;
	}

	public Long getNetto() {
		return netto;
	}

	public void setNetto(Long netto) {
		this.netto = netto;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public String getChoosenDateTypeFiled() {
		return choosenDateTypeFiled;
	}

	public void setChoosenDateTypeFiled(String choosenDateTypeFiled) {
		this.choosenDateTypeFiled = choosenDateTypeFiled;
	}

	public Integer getVevokod() {
		return vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	public String getRealtySearchText() {
		return realtySearchText;
	}

	public void setRealtySearchText(String realtySearchText) {
		this.realtySearchText = realtySearchText;
	}

	public Integer getSelectedProjectId() {
		return selectedProjectId;
	}

	public void setSelectedProjectId(Integer selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	public List<SelectItem> getFlatItems() {
		return flatItems;
	}

	public void setFlatItems(List<SelectItem> flatItems) {
		this.flatItems = flatItems;
	}

	public String getFilteredText() {
		return filteredText;
	}

	public void setFilteredText(String filteredText) {
		this.filteredText = filteredText;
	}

	public String getOwnerSearchText() {
		return ownerSearchText;
	}

	public void setOwnerSearchText(String ownerSearchText) {
		this.ownerSearchText = ownerSearchText;
	}

	public Integer getSelectedOwnerId() {
		return selectedOwnerId;
	}

	public void setSelectedOwnerId(Integer selectedOwnerId) {
		this.selectedOwnerId = selectedOwnerId;
	}

	public List<SelectItem> getOwnerItems() {
		return ownerItems;
	}

	public void setOwnerItems(List<SelectItem> ownerItems) {
		this.ownerItems = ownerItems;
	}

	public String getRenterSearchText() {
		return renterSearchText;
	}

	public void setRenterSearchText(String renterSearchText) {
		this.renterSearchText = renterSearchText;
	}

	public Integer getSelectedRenterId() {
		return selectedRenterId;
	}

	public void setSelectedRenterId(Integer selectedRenterId) {
		this.selectedRenterId = selectedRenterId;
	}

	public List<SelectItem> getRenterItems() {
		return renterItems;
	}

	public void setRenterItems(List<SelectItem> renterItems) {
		this.renterItems = renterItems;
	}

	public List<Integer> getLakasIds() {
		return lakasIds;
	}

	public void setLakasIds(List<Integer> lakasIds) {
		this.lakasIds = lakasIds;
	}

	public Aattachs getSelectedAattach() {
		return selectedAattach;
	}

	public void setSelectedAattach(Aattachs selectedAattach) {
		this.selectedAattach = selectedAattach;
	}

	public List<Aattachs> getAttachs() {
		return attachs;
	}

	public void setAttachs(List<Aattachs> attachs) {
		this.attachs = attachs;
	}

	public StreamedContent getDownloadableFile() {
		return downloadableFile;
	}

	public void setDownloadableFile(StreamedContent downloadableFile) {
		this.downloadableFile = downloadableFile;
	}

	public List<SelectItem> getAllRentalItems() {
		return allRentalItems;
	}

	public void setAllRentalItems(List<SelectItem> allRentalItems) {
		this.allRentalItems = allRentalItems;
	}

	public List<SelectItem> getAllFlatItems() {
		return allFlatItems;
	}

	public void setAllFlatItems(List<SelectItem> allFlatItems) {
		this.allFlatItems = allFlatItems;
	}

	public List<SelectItem> getAllOwnerItems() {
		return allOwnerItems;
	}

	public void setAllOwnerItems(List<SelectItem> allOwnerItems) {
		this.allOwnerItems = allOwnerItems;
	}

	public List<String> getAllBejelento() {
		return allBejelento;
	}

	public void setAllBejelento(List<String> allBejelento) {
		this.allBejelento = allBejelento;
	}

	public String getBejelentoSearchText() {
		return bejelentoSearchText;
	}

	public void setBejelentoSearchText(String bejelentoSearchText) {
		this.bejelentoSearchText = bejelentoSearchText;
	}

	public String getSelectedBejelento() {
		return selectedBejelento;
	}

	public void setSelectedBejelento(String selectedBejelento) {
		this.selectedBejelento = selectedBejelento;
	}

	public List<RealtyArrangementVO> getRealtyFiltered() {
		return realtyFiltered;
	}

	public void setRealtyFiltered(List<RealtyArrangementVO> realtyFiltered) {
		this.realtyFiltered = realtyFiltered;
	}

	public List<Object[]> getIntezaruk() {
		return intezaruk;
	}

	public void setIntezaruk(List<Object[]> intezaruk) {
		this.intezaruk = intezaruk;
	}

}

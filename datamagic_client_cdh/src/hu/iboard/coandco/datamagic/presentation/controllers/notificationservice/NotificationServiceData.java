package hu.iboard.coandco.datamagic.presentation.controllers.notificationservice;

import hu.iboard.coandco.datamagic.generated.Aattachs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "notificationserviceData")
@SessionScoped
public class NotificationServiceData implements Serializable {

	private static final long serialVersionUID = 4687147246642777962L;

	private List<Object[]> notifList = new ArrayList<Object[]>();
	private Object[] selectedNotificationVO;
	private List<Aattachs> files = new ArrayList<Aattachs>();
	private Date from;
	private Date to;
	private String realtySearchText;
	private Integer selectedProjectId;
	private List<SelectItem> flatItems;
	private List<SelectItem> allFlatItems;
	private String ownerSearchText;
	private Integer selectedOwnerId;
	private List<SelectItem> ownerItems;
	private List<SelectItem> allOwnerItems;
	private String renterSearchText;
	private Integer selectedRenterId;
	private List<SelectItem> renterItems;
	private List<SelectItem> allRentalItems;
	private String filteredText;
	private Boolean showRealEstateButton = true;
	private String modLeiras;
	private List<SelectItem> allBejelento;
	private String bejelentoSearchText;
	private String selectedBejelento;
	private List<Object[]> notifListFiltered;

	public List<Object[]> getNotifList() {
		return notifList;
	}

	public void setNotifList(List<Object[]> notifList) {
		this.notifList = notifList;
	}

	public Object[] getSelectedNotificationVO() {
		return selectedNotificationVO;
	}

	public void setSelectedNotificationVO(Object[] selectedNotificationVO) {
		this.selectedNotificationVO = selectedNotificationVO;
	}

	public List<Aattachs> getFiles() {
		return files;
	}

	public void setFiles(List<Aattachs> files) {
		this.files = files;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
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

	public String getFilteredText() {
		return filteredText;
	}

	public void setFilteredText(String filteredText) {
		this.filteredText = filteredText;
	}

	public Boolean getShowRealEstateButton() {
		return showRealEstateButton;
	}

	public void setShowRealEstateButton(Boolean showRealEstateButton) {
		this.showRealEstateButton = showRealEstateButton;
	}

	public String getModLeiras() {
		return modLeiras;
	}

	public void setModLeiras(String modLeiras) {
		this.modLeiras = modLeiras;
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

	public List<SelectItem> getAllBejelento() {
		return allBejelento;
	}

	public void setAllBejelento(List<SelectItem> allBejelento) {
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

	public List<Object[]> getNotifListFiltered() {
		return notifListFiltered;
	}

	public void setNotifListFiltered(List<Object[]> notifListFiltered) {
		this.notifListFiltered = notifListFiltered;
	}

}

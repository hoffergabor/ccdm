package hu.iboard.coandco.datamagic.presentation.controllers.notificationservice;

import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "managenotificationData")
@SessionScoped
public class ManageNotificationServiceData implements Serializable {

	private static final long serialVersionUID = -5803083896945406118L;

	private Date notificationDate;
	private Date perceptionDate;
	private Date repairDate;
	private Boolean checkNotif = false;
	private NotificationVO notification;
	private List<UploadedFile> fileList = new ArrayList<UploadedFile>();
	private UploadedFile selectedFile;
	private boolean showTelPanel = false;
	private List<SelectItem> flatItems;
	private List<SelectItem> allFlatItems;
	private Integer projectId;
	private String realtySearchText;
	private String renterSearchText;
	private List<SelectItem> rentalItems;
	private List<SelectItem> allRentalItems;
	private Integer aruextId;
	private Integer projectIdRental;
	private List<SelectItem> renterItems;
	private Integer selectedRenterId;
	private String selectedRenterName;
	private Boolean charges;
	private Boolean flatRate;
	private List<SelectItem> allPartnerItems;
	private Integer selectedCharger;
	private boolean realtyToRenter = false;
	private List<SelectItem> tempRenterItems;
	private Date actualDate;

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Date getPerceptionDate() {
		return perceptionDate;
	}

	public void setPerceptionDate(Date perceptionDate) {
		this.perceptionDate = perceptionDate;
	}

	public Date getRepairDate() {
		return repairDate;
	}

	public void setRepairDate(Date repairDate) {
		this.repairDate = repairDate;
	}

	public Boolean getCheckNotif() {
		return checkNotif;
	}

	public void setCheckNotif(Boolean checkNotif) {
		this.checkNotif = checkNotif;
	}

	public NotificationVO getNotification() {
		return notification;
	}

	public void setNotification(NotificationVO notification) {
		this.notification = notification;
	}

	public boolean isShowTelPanel() {
		return showTelPanel;
	}

	public void setShowTelPanel(boolean showTelPanel) {
		this.showTelPanel = showTelPanel;
	}

	public List<UploadedFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<UploadedFile> fileList) {
		this.fileList = fileList;
	}

	public UploadedFile getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(UploadedFile selectedFile) {
		this.selectedFile = selectedFile;
	}

	public List<SelectItem> getFlatItems() {
		return flatItems;
	}

	public void setFlatItems(List<SelectItem> flatItems) {
		this.flatItems = flatItems;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getRealtySearchText() {
		return realtySearchText;
	}

	public void setRealtySearchText(String realtySearchText) {
		this.realtySearchText = realtySearchText;
	}

	public List<SelectItem> getRentalItems() {
		return rentalItems;
	}

	public void setRentalItems(List<SelectItem> rentalItems) {
		this.rentalItems = rentalItems;
	}

	public Integer getAruextId() {
		return aruextId;
	}

	public void setAruextId(Integer aruextId) {
		this.aruextId = aruextId;
	}

	public Integer getProjectIdRental() {
		return projectIdRental;
	}

	public void setProjectIdRental(Integer projectIdRental) {
		this.projectIdRental = projectIdRental;
	}

	public String getRenterSearchText() {
		return renterSearchText;
	}

	public void setRenterSearchText(String renterSearchText) {
		this.renterSearchText = renterSearchText;
	}

	public List<SelectItem> getRenterItems() {
		return renterItems;
	}

	public void setRenterItems(List<SelectItem> renterItems) {
		this.renterItems = renterItems;
	}

	public Integer getSelectedRenterId() {
		return selectedRenterId;
	}

	public void setSelectedRenterId(Integer selectedRenterId) {
		this.selectedRenterId = selectedRenterId;
	}

	public String getSelectedRenterName() {
		return selectedRenterName;
	}

	public void setSelectedRenterName(String selectedRenterName) {
		this.selectedRenterName = selectedRenterName;
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

	public Boolean getCharges() {
		return charges;
	}

	public void setCharges(Boolean charges) {
		this.charges = charges;
	}

	public Boolean getFlatRate() {
		return flatRate;
	}

	public void setFlatRate(Boolean flatRate) {
		this.flatRate = flatRate;
	}

	public List<SelectItem> getAllPartnerItems() {
		return allPartnerItems;
	}

	public void setAllPartnerItems(List<SelectItem> allPartnerItems) {
		this.allPartnerItems = allPartnerItems;
	}

	public Integer getSelectedCharger() {
		return selectedCharger;
	}

	public void setSelectedCharger(Integer selectedCharger) {
		this.selectedCharger = selectedCharger;
	}

	public boolean isRealtyToRenter() {
		return realtyToRenter;
	}

	public void setRealtyToRenter(boolean realtyToRenter) {
		this.realtyToRenter = realtyToRenter;
	}

	public List<SelectItem> getTempRenterItems() {
		return tempRenterItems;
	}

	public void setTempRenterItems(List<SelectItem> tempRenterItems) {
		this.tempRenterItems = tempRenterItems;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}

}

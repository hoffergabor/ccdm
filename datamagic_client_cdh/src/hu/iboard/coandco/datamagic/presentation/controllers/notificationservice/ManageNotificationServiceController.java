package hu.iboard.coandco.datamagic.presentation.controllers.notificationservice;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.Berbeadas;
import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Project;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.aruext.AruExtServiceBase;
import hu.iboard.coandco.datamagic.service.email.EmailServiceBase;
import hu.iboard.coandco.datamagic.service.fileattachs.FileAttachsServiceBase;
import hu.iboard.coandco.datamagic.service.notification.NotificationServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.project.ProjectServiceBase;
import hu.iboard.coandco.datamagic.service.realty.RealtyArrangementServiceBase;
import hu.iboard.coandco.datamagic.service.user.UserServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.flattype.FlatTypeVO;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "managenotificationserviceController")
@RequestScoped
public class ManageNotificationServiceController extends AbstractController {

	public static final String MANAGENOTIFICATIONSERVICE_ACTION = "managenotification";

	@ManagedProperty(value = "#{managenotificationData}")
	private ManageNotificationServiceData data;

	@ManagedProperty(value = "#{notificationService}")
	private NotificationServiceBase notificationService;
	@ManagedProperty(value = "#{fileAttachsService}")
	private FileAttachsServiceBase fileAttachsService;
	@ManagedProperty(value = "#{projectService}")
	private ProjectServiceBase projectService;
	@ManagedProperty(value = "#{aruExtService}")
	private AruExtServiceBase aruExtService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	@ManagedProperty(value = "#{realtyArrangementService}")
	private RealtyArrangementServiceBase realtyArrangementService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{emailService}")
	private EmailServiceBase emailService;
	@ManagedProperty(value = "#{workerService}")
	private WorkerServiceBase workerService;

	@Override
	public void loadData() {
		getData().setNotification(new NotificationVO());
		getData().getNotification().setBejelentes_ido(new Date());
		getData().getNotification().setEszleles_ido(new Date());
		getData().getNotification().setKertjav_ido("");
		getData().getNotification().setHatarido(new Date());
		getData().setFileList(new ArrayList<UploadedFile>());
		getData().getNotification().setFigyel(false);
		getData().getNotification().setVisszahiv(false);
		getData().setShowTelPanel(false);
		getData().setFlatItems(new ArrayList<SelectItem>());
		getData().setRealtySearchText("");
		getData().setRenterSearchText("");
		getData().setSelectedRenterName("");
		getData().setRentalItems(new ArrayList<SelectItem>());
		getData().setRenterItems(new ArrayList<SelectItem>());
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setTempRenterItems(new ArrayList<SelectItem>());
		getData().getNotification().setUrgent(false);
		getData().setCharges(false);
		getData().setFlatRate(false);
		getData().setSelectedCharger(0);
		getData().setRealtyToRenter(false);
		getData().setActualDate(new Date());
		getData().setAllPartnerItems(new ArrayList<SelectItem>());
		if (getManagedRenter() != null) {
			initRenterFlats();
		}
		initTel();

	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(NOTIFICATIONSERVICE_CONTROLLER);

	}

	public ManageNotificationServiceData getData() {
		return data;
	}

	public void setData(ManageNotificationServiceData data) {
		this.data = data;
	}

	public String saveNotification() throws IOException {
		NotificationVO notifVO = null;
		OutputStream out = null;

		if (getData().getAruextId() == null || getData().getAruextId() == 0) {
			addErrorMessage("", "Bérlemény megadása kötelező!");
			return null;
		}
		try {

			getData().getNotification().setStatusz(NOTIFICATION_STATUS);
			getData().getNotification().setIntezkedes(null);
			getData().getNotification().setModositva(new Date());
			getData().getNotification().setIngHibaStatusz(getNotificationService().getIngHibaStatusz(new Integer(1)));
			getData().getNotification().setBejelentes_ido(addTimeToDate(getData().getNotification().getBejelentes_ido()));
			getData().getNotification().setEszleles_ido(addTimeToDate(getData().getNotification().getEszleles_ido()));
			getData().getNotification().setAruExt(getAruExtService().getAruExtById(getData().getAruextId()));
			getData().getNotification().setAlapkezelo(false);
			getData().getNotification().setSzamlazando(false);
			getData().getNotification().setVevoId(0);
			getData().getNotification().setBejelentes_ido(new Date());
			if (getManagedRenter() != null) {
				getData().getNotification().setUser(getManagedRenter().getVevokod());
				getData().getNotification().setUserType('b');
				getData().getNotification().setProject(getProjectService().getProjectById(getData().getNotification().getAruExt().getProject()));
			} else {
				if (getData().getSelectedRenterId() != null) {
					getData().setProjectId(getData().getNotification().getAruExt().getProject());
				}
				getData().getNotification().setProject(getProjectService().getProjectById(getData().getProjectId()));

			}
			if (getManagedPartner() != null) {
				getData().getNotification().setUser(getManagedPartner().getVevokod());
				getData().getNotification().setUserType('t');
			}
			if (getManagedWorker() != null) {
				getData().getNotification().setUser(getManagedWorker().getDkod());
				getData().getNotification().setUserType('d');
				getData().getNotification().setAlapkezelo(getData().getFlatRate());
				getData().getNotification().setSzamlazando(getData().getCharges());
				if (getData().getSelectedCharger() != 0)
					getData().getNotification().setVevoId(getData().getSelectedCharger());
			}
			notifVO = getNotificationService().saveOrUpdateNotification(getData().getNotification());
			if (notifVO == null) {
				addFatalMessage("", "Hibabejelentés mentése sikertelen!");
				return null;
			}
			addInfoMessage("", "A hiba elküldve!");

			Integer dolgId = getData().getNotification().getProject().getVagyonkez();
			if (dolgId != null) {
				Dolgozo dolgozo = getWorkerService().getWorkerByReferenceId(dolgId);
				if (dolgozo.getEmail() != null && !dolgozo.getEmail().equals("")) {
					getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, dolgozo.getEmail(),
							"Hibabejelentés - " + getSubject(), getEmailBody());
				}
			}

			if (getManagedWorker() != null && getData().getNotification().getFigyel() && getManagedWorker().getEmail() != null
					&& !getManagedWorker().getEmail().equals("")) {
				getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, getManagedWorker().getEmail(),
						"Hibabejelentés - " + getSubject(), getEmailBody());
			}

			if (getManagedPartner() != null && getData().getNotification().getFigyel() && getManagedPartner().getEmail() != null
					&& !getManagedPartner().getEmail().equals("")) {
				getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, getManagedPartner().getEmail(),
						"Hibabejelentés - " + getSubject(), getEmailBody());
			}

			if (getManagedRenter() != null && getManagedRenter().getEmail() != null && !getManagedRenter().getEmail().equals("")) {
				getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, getManagedRenter().getEmail(),
						"Hibabejelentés - " + getSubject(), getEmailBody());
			}
			Integer aktber = notifVO.getAruExt().getAktBer();
			if (getManagedWorker() != null || getManagedPartner() != null) {
				if (aktber != null && aktber != 0) {
					List<Integer> aktbers = new ArrayList<Integer>();
					aktbers.add(aktber);
					List<Integer> berbeadasok = getAruExtService().getBerbeadasByAktBer(aktbers);
					if (berbeadasok != null) {
						Partner renter = getPartnerService().getPartnerByReferenceId(berbeadasok.get(0));
						if (renter != null && renter.getEmail() != null && !renter.getEmail().equals("")) {
							getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, renter.getEmail(),
									"Hibabejelentés - " + getSubject(), getEmailBody());
						}
					}
				}
			}
			if (getManagedWorker() != null) {
				if (aktber == null || aktber == 0) {
					Project project = notifVO.getProject();
					if (project.getParentTulajdonos() != null && project.getParentTulajdonos().getEmail() != null
							&& !project.getParentTulajdonos().getEmail().equals("")) {
						getEmailService().sendEmail(CDH_SMTP_HOST, CDH_SMTP_USER, CDH_SMTP_PASS, CDH_EMAIL_ADDRESS, project.getParentTulajdonos().getEmail(),
								"Hibabejelentés - " + getSubject(), getEmailBody());
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error by saving notification", e);
			addFatalMessage("", "Hibabejelentés mentése sikertelen!");
			return null;
		}

		try {
			if (getData().getFileList().size() != 0) {
				for (UploadedFile item : getData().getFileList()) {
					byte[] data = item.getContents();
					out = new FileOutputStream(getRealPath() + "downloads/" + item.getFileName());
					out.write(data);
					out.flush();
					out.close();
				}
				String fileName = getNotificationService().uploadFile(getData().getFileList(), getRealPath());
				if (!fileName.equals("")) {
					for (UploadedFile item : getData().getFileList()) {
						Aattachs aattachs = new Aattachs();
						if (getManagedWorker() != null) {
							aattachs.setCreator(Integer.valueOf(getManagedWorker().getDkod()).toString());
							aattachs.setCreatorid(getManagedWorker().getDkod());
							aattachs.setCreatorn(getManagedWorker().getDnev());
						}
						if (getManagedPartner() != null) {
							aattachs.setCreator(Integer.valueOf(getManagedPartner().getVevokod()).toString());
							aattachs.setCreatorid(getManagedPartner().getVevokod());
							aattachs.setCreatorn(getManagedPartner().getNev());
						}
						if (getManagedRenter() != null) {
							aattachs.setCreator(Integer.valueOf(getManagedRenter().getVevokod()).toString());
							aattachs.setCreatorid(getManagedRenter().getVevokod());
							aattachs.setCreatorn(getManagedRenter().getNev());
						}
						aattachs.setOrigname(item.getFileName());
						aattachs.setFilenev(fileName + ".encrypted");
						aattachs.setName(item.getFileName());
						aattachs = getNotificationService().saveOrUpdateAattach(aattachs);
						if (aattachs == null) {
							logger.error("ERROR BY SAVING AATTACHS BY MANAGE NOTIFICATION!");
							addFatalMessage("", "Fájl feltöltés sikertelen!");
							return null;
						}
						Aconnect aconnect = new Aconnect();
						aconnect.setOwnerid(notifVO.getId().toString());
						aconnect.setOwnertype("inghibabej");
						aconnect.setAttachid(aattachs.getId());
						aconnect = getNotificationService().saveOrUpdateAconnect(aconnect);
						if (aconnect == null) {
							logger.error("ERROR BY SAVING ACONNECT BY MANAGE NOTIFICATION!");
							addFatalMessage("", "Fájl feltöltés sikertelen!");
							return null;
						}
					}
				}
				for (UploadedFile file : getData().getFileList()) {
					File uploadedFile = new File(getRealPath() + "downloads/" + file.getFileName());
					if (uploadedFile.exists())
						uploadedFile.delete();
				}
				addInfoMessage("", "Fájl feltöltés sikeres!");
			}
		} catch (Exception e) {
			logger.error("ERROR BY UPLOAD FILES FOR MANAGE NOTIFICATION", e);
			addFatalMessage("", "Fájl feltöltés sikertelen!");
			return null;
		}
		loadData();
		return null;
	}

	public void handleFileUpload(FileUploadEvent event) {
		try {
			if (event.getFile().getFileName().equals(""))
				return;
			UploadedFile item = event.getFile();
	
			if (item == null) {
				return;
			}
			getData().getFileList().add(item);
		}
		catch (Exception e) {
			logger.error("An error has been occured during file upload", e);
			addFatalMessage("", "A fájl feltöltése sikertelen volt, kérem próbálja újra feltölteni a fájlt.");
		}		
	}

	public String removeFile() {

		if (getData().getFileList() != null && getData().getSelectedFile() != null) {
			if (getData().getFileList().contains(getData().getSelectedFile())) {
				getData().getFileList().remove(getData().getSelectedFile());
				getData().setFileList(getData().getFileList());
				addInfoMessage("", getData().getSelectedFile().getFileName() + " törölve!");
			}
		}
		return null;
	}

	public void showTelephoneAction(ValueChangeEvent e) {
		boolean newValue = (Boolean) e.getNewValue();
		if (newValue == true)
			getData().setShowTelPanel(true);
		else {
			getData().setShowTelPanel(false);
		}
	}

	public String showRealtys() {
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setProjectId(0);
		getData().setRealtySearchText("");
		getData().setSelectedRenterName("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getManagedUser().getUserType().equals(WORKER)) {
			List<Object[]> list = getProjectService().getAllProject();
			if (list == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			for (Object[] object : list) {
				selectItemList.add(new SelectItem(object[0], (String) object[1] + " " + (String) object[2] + ", " + (String) object[3]));
			}
		}
		if (getManagedUser().getUserType().equals(PARTNER) || getManagedUser().getUserType().equals(SALES)) {
			List<Project> list = getProjectService().getProjectByPartnerId(getManagedPartner().getVevokod());
			if (list == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			for (Project item : list) {
				selectItemList.add(new SelectItem(item.getId(), item.getIrsz() + " " + item.getVaros() + ", " + item.getCim()));
			}
		}
		getData().setFlatItems(selectItemList);
		getData().setAllFlatItems(selectItemList);
		return null;
	}

	public String searchRealtyAction() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllFlatItems() == null || getData().getAllFlatItems().size() == 0)
			return null;
		for (SelectItem selectItem : getData().getAllFlatItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getRealtySearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getRealtySearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setFlatItems(selectItemList);
		return null;
	}

	public String selectRealtyAction() {
		getData().setAruextId(0);
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getProjectId() == null || getData().getProjectId() == 0)
			return null;
		List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(getData().getProjectId());

		if (aruexts == null) {
			getData().setAruextId(0);
			getData().setRentalItems(new ArrayList<SelectItem>());
			getData().getRentalItems().add(new SelectItem(0, "Nincs aktív rérlemény!"));
			return null;
		}
		String flatType = "";
		for (Object[] object : aruexts) {
			if ((Boolean) object[7]) {
				FlatTypeVO type = getNotificationService().getFlatTypeById((Integer) object[6]);
				if (type != null)
					flatType = type.getNev();
				selectItemList.add(new SelectItem(object[0], (String) object[1] + " " + (String) object[2] + ", " + (String) object[3] + " "
						+ (String) object[4] + " - " + flatType));
			}
		}
		if (selectItemList.size() == 0) {
			getData().setAruextId(0);
			getData().setRentalItems(new ArrayList<SelectItem>());
			getData().getRentalItems().add(new SelectItem(0, "Nincs aktív bérlemény!"));
		} else {
			getData().setRentalItems(selectItemList);
		}

		getData().setRenterItems(new ArrayList<SelectItem>());
		getData().setSelectedRenterId(0);
		getData().setRenterSearchText("");
		List<Integer> aktbers = new ArrayList<Integer>();
		if (aruexts != null) {
			for (Object[] object : aruexts) {
				Integer aktber = (Integer) object[5];
				if (aktber != 0)
					aktbers.add(aktber);
			}
			if (aktbers.size() > 0) {
				List<Integer> berbeadasok = getAruExtService().getBerbeadasByAktBer(aktbers);
				if (berbeadasok != null) {
					List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(berbeadasok);
					if (partners == null || partners.size() == 0)
						return null;
					for (Object[] name : partners) {
						getData().getRenterItems().add(new SelectItem((Integer) name[0], (String) name[1]));
					}
				}
				getData().setRealtyToRenter(true);
				getData().setTempRenterItems(getData().getRenterItems());
			}
		}
		return null;

	}

	public void showRenterBySelectedRealty(ValueChangeEvent event) {
		if (event.getNewValue() == null)
			return;
		getData().setAruextId((Integer) event.getNewValue());
		AruExt aruext = getAruExtService().getAruExtById(getData().getAruextId());
		if (aruext == null || aruext.getAktBer() == 0) {
			getData().setSelectedRenterName("");
			return;
		}
		List<Integer> id = new ArrayList<Integer>();
		id.add(aruext.getAktBer());
		List<Integer> partnerIds = getAruExtService().getBerbeadasByAktBer(id);
		if (partnerIds == null || partnerIds.size() == 0) {
			getData().setSelectedRenterName("");
			return;
		}
		getData().setSelectedRenterName(getPartnerService().getPartnerNameByPartnerId(partnerIds.get(0)));
	}

	private void initRenterFlats() {

		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> aruexts = getAruExtService().getAruExtForRenter(getManagedRenter().getVevokod());
		if (aruexts == null) {
			getData().setRentalItems(new ArrayList<SelectItem>());
			return;
		}
		String flatType = "";
		for (Object[] aruext : aruexts) {
			FlatTypeVO type = getNotificationService().getFlatTypeById((Integer) aruext[6]);
			if (type != null)
				flatType = type.getNev();
			selectItemList.add(new SelectItem((Integer) aruext[0], (String) aruext[2] + " " + (String) aruext[3] + ", " + (String) aruext[4] + " "
					+ (String) aruext[5] + " - " + flatType));
		}
		getData().setRentalItems(selectItemList);

	}

	@SuppressWarnings("deprecation")
	private Date addTimeToDate(Date date) {
		Date now = new Date();
		date.setHours(now.getHours());
		date.setMinutes(now.getMinutes());
		date.setSeconds(now.getSeconds());
		return date;
	}

	public void showRenters(ActionEvent event) {
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setSelectedRenterId(0);
		getData().setRenterSearchText("");
		if (getData().isRealtyToRenter()) {
			if (getData().getRenterItems() != null && getData().getRenterItems().size() > 0)
				return;
		} else
			getData().setRenterItems(new ArrayList<SelectItem>());
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getManagedUser().getUserType().equals(PARTNER) || getManagedUser().getUserType().equals(SALES)) {
			List<Integer> aktbers = new ArrayList<Integer>();
			List<Project> projects = getProjectService().getProjectByPartnerId(getManagedPartner().getVevokod());
			if (projects == null) {
				return;
			}
			for (Project project : projects) {
				List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(project.getId());
				if (aruexts != null) {
					for (Object[] object : aruexts) {
						Integer aktber = (Integer) object[5];
						if (aktber != 0)
							aktbers.add(aktber);
					}
				}
			}
			if (aktbers.size() > 0) {
				List<Berbeadas> berbeadasok = getNotificationService().getBerbeadasByAktBerCheckContract(aktbers);
				List<Integer> tempBerbeadasIds = new ArrayList<Integer>();
				if (berbeadasok == null)
					return;
				for (Berbeadas berbeadas : berbeadasok) {
					if (berbeadas != null) {
						if (berbeadas.getDateEnd().toString().compareTo(convertDateTosString3(new Date())) > 0) {
							tempBerbeadasIds.add(berbeadas.getUgyfel());
						} else {
							if (berbeadas.getDateEnd().toString().equals("1900-01-01 00:00:00.0") && berbeadas.getHatarozatlan()) {
								tempBerbeadasIds.add(berbeadas.getUgyfel());
							}
						}
					}
				}
				if (tempBerbeadasIds != null && tempBerbeadasIds.size() > 0) {
					List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(tempBerbeadasIds);
					if (partners != null && partners.size() > 0) {
						for (Object[] name : partners) {
							selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
						}
					}
				}
			}
		} else {
			List<Integer> ids = getRealtyArrangementService().getRenterIdsForRealtyArrangement();
			if (ids != null) {
				List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(ids);
				if (partners != null && partners.size() != 0) {
					for (Object[] name : partners) {
						selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
					}
				}
			}
		}
		getData().setRenterItems(selectItemList);
		getData().setAllRentalItems(selectItemList);
		getData().setTempRenterItems(getData().getRenterItems());
	}

	public void searchRenterAction(ActionEvent event) {

		if (getData().getRenterSearchText().equals("")) {
			getData().setRenterItems(getData().getTempRenterItems());
			return;
		}
		List<SelectItem> tempItemList = new ArrayList<SelectItem>();
		for (SelectItem selectItem : getData().getAllRentalItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toUpperCase() + ".*")) {
				tempItemList.add(selectItem);
			}
		}
		getData().setRenterItems(tempItemList);
	}

	public void selectRenterAction(ActionEvent event) {
		getData().setAruextId(0);
		if (getData().getSelectedRenterId() == null || getData().getSelectedRenterId() == 0) {
			return;
		}
		getData().setSelectedRenterName(getPartnerService().getAllPartnerDataById(getData().getSelectedRenterId()).getNev());
		List<AruExt> aruexts = new ArrayList<AruExt>();
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getSelectedRenterId() != 0) {
			aruexts = getAruExtService().getAruExtsForRenter(getData().getSelectedRenterId());
		}
		if (aruexts == null) {
			getData().setRentalItems(new ArrayList<SelectItem>());
			getData().getRentalItems().add(new SelectItem(0, "Nincs aktív bérlemény!"));
			return;
		}
		String flatType = "";
		for (AruExt aruext : aruexts) {
			if (aruext.isAktiv()) {
				FlatTypeVO type = getNotificationService().getFlatTypeById(aruext.getTipus());
				if (type != null)
					flatType = type.getNev();
				selectItemList.add(new SelectItem(aruext.getArukod(), aruext.getIrsz() + " " + aruext.getVaros() + ", " + aruext.getCim() + " "
						+ aruext.getCim2() + " - " + flatType));
			}
		}
		if (selectItemList.size() == 0) {
			getData().setAruextId(0);
			getData().setRentalItems(new ArrayList<SelectItem>());
			getData().getRentalItems().add(new SelectItem(0, "Nincs aktív bérlemény!"));
		} else {
			getData().setRentalItems(selectItemList);
		}
	}

	public String chargesAction() {
		if (!getData().getCharges()) {
			getData().setCharges(true);

			if (getData().getAllPartnerItems().size() == 0) {
				List<Object[]> partners = getPartnerService().getAllPartner();
				for (Object[] partner : partners) {
					getData().getAllPartnerItems().add(new SelectItem((Integer) partner[1], (String) partner[0]));
				}
			}
			AruExt aruext = getAruExtService().getAruExtById(getData().getAruextId());
			if (aruext == null)
				return null;
			Project project = getProjectService().getProjectById(aruext.getProject());
			if (project == null)
				return null;
			if (project.getTulajdonos() == null || project.getTulajdonos() == 0)
				return null;
			getData().setSelectedCharger(project.getTulajdonos());

		} else {
			getData().setCharges(false);
		}
		return null;
	}

	private String getEmailBody() {
		String body = "";
		body = body + "<img src='http://coandco.iboard.hu/cdh/email/cdh_logo.png' width='114' height='91' />";
		body = body + "<p style='font-family: Arial, Helvetica, sans-serif; font-size: 12px;'><b>Tisztelt Ügyfelünk!</b><br/><br/><br/>";
		body = body + "<strong>Tárgy:</strong> " + getData().getNotification().getId() + "<br/>";
		body = body + "<strong>Ingatlan címe:</strong> " + getData().getNotification().getAruExt().getIrsz() + " "
				+ getData().getNotification().getAruExt().getVaros() + ", " + getData().getNotification().getAruExt().getCim() + " "
				+ getData().getNotification().getAruExt().getCim2() + " - "
				+ (getData().getNotification().getAruExt().getLakasTipus() != null ? getData().getNotification().getAruExt().getLakasTipus().getNev() : "")
				+ "<br/>";
		body = body + "<strong>Bejelentés dátuma:</strong> " + convertDateTosString2(getData().getNotification().getBejelentes_ido()) + "<br/><br/>";
		String name = "";
		if (getData().getNotification().getUserType() == 'd') {
			Dolgozo dolgozo = getWorkerService().getWorkerByReferenceId(getData().getNotification().getUser());
			if (dolgozo != null)
				name = dolgozo.getDnev();
		} else {
			name = getPartnerService().getPartnerNameByPartnerId(getData().getNotification().getUser());
		}
		body = body + "<strong>Bejelentő:</strong> " + name + "<br/><br/>";
		body = body + "<strong>Hiba leírása:</strong><br/>" + getData().getNotification().getCim() + "<br/>" + getData().getNotification().getLeiras()
				+ "<br/><br/>";
		body = body + "A CD Hungary Zrt. Ügyfélszolgálata regisztrálta a hibabejelentést.<br/>";
		body = body + " Kollégánk munkaidőben a legrövidebb időn belül feldolgozza az Ön által bejelentett hibát, és értesíti a hibajavítás időpontjáról.<br/>";
		body = body + " Köszönjük, hogy a hiba pontosítása és gyors javítása érdekében a webes hibabejelentőt használta.<br/><br/>";
		body = body + "<strong>Munkanapok:</strong><br/>";
		body = body + "H-CS 8.00 - 17.00-ig<br/>";
		body = body + "P 8.00 – 15.00-ig<br/><br/>";
		Date now = new Date();
		body = body + "Budapest, " + convertDateTosString2(now) + "<br/>";
		body = body + "Tisztelettel CD Hungary Zrt. Ügyfélszolgálata<br/><br/><br/>";
		body = body + "<strong>Elérhetőségeink:</strong><br/><br/>";
		body = body + "<strong>CD Hungary Ingatlanhasznosító, Forgalmazó és Szolgáltató Zrt.</strong><br/>";
		body = body + "Budapest, 1023 Vérhalom u. 12-16.<br/>";
		body = body + "Tel:(+36-1) 325-71-77, Fax: (+36-1) 325-74-43 Tel: (+36-1) 325-7250<br/>";
		body = body + "<a href='mailto:ugyfelszolgalat@cdhungary.com'>ugyfelszolgalat@cdhungary.com</a><br/>";
		body = body + "<a href='http://www.cdhungary.com'>www.cdhungary.com</a><br/><br/></p>";
		return body;
	}

	private String getSubject() {
		String subject = "";
		subject = subject + getData().getNotification().getAruExt().getIrsz() + " " + getData().getNotification().getAruExt().getVaros() + ", "
				+ getData().getNotification().getAruExt().getCim() + " " + getData().getNotification().getAruExt().getCim2() + " - "
				+ (getData().getNotification().getAruExt().getLakasTipus() != null ? getData().getNotification().getAruExt().getLakasTipus().getNev() : "");
		return subject;
	}

	public List<String> showTels(String query) {
		List<String> results = new ArrayList<String>();

		if (getManagedWorker() != null) {
			results.add(getManagedWorker().getDtelefon());
		} else if (getManagedPartner() != null) {
			results.add(getManagedPartner().getTel1());
			results.add(getManagedPartner().getTel2());
		} else if (getManagedRenter() != null) {
			results.add(getManagedRenter().getTel1());
			results.add(getManagedRenter().getTel2());
		}
		return results;
	}

	public void initTel() {

		if (getManagedWorker() != null) {
			getData().getNotification().setTelefon(getManagedWorker().getDtelefon());
		} else if (getManagedPartner() != null) {
			getData().getNotification().setTelefon(getManagedPartner().getTel1());
		} else if (getManagedRenter() != null) {
			getData().getNotification().setTelefon(getManagedRenter().getTel1());
		}

	}

	public NotificationServiceBase getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationServiceBase notificationService) {
		this.notificationService = notificationService;
	}

	public FileAttachsServiceBase getFileAttachsService() {
		return fileAttachsService;
	}

	public void setFileAttachsService(FileAttachsServiceBase fileAttachsService) {
		this.fileAttachsService = fileAttachsService;
	}

	public ProjectServiceBase getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectServiceBase projectService) {
		this.projectService = projectService;
	}

	public AruExtServiceBase getAruExtService() {
		return aruExtService;
	}

	public void setAruExtService(AruExtServiceBase aruExtService) {
		this.aruExtService = aruExtService;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public RealtyArrangementServiceBase getRealtyArrangementService() {
		return realtyArrangementService;
	}

	public void setRealtyArrangementService(RealtyArrangementServiceBase realtyArrangementService) {
		this.realtyArrangementService = realtyArrangementService;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

	public WorkerServiceBase getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerServiceBase workerService) {
		this.workerService = workerService;
	}

}

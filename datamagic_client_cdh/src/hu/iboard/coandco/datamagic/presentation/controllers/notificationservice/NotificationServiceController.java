package hu.iboard.coandco.datamagic.presentation.controllers.notificationservice;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Project;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.aruext.AruExtServiceBase;
import hu.iboard.coandco.datamagic.service.fileattachs.FileAttachsServiceBase;
import hu.iboard.coandco.datamagic.service.notification.NotificationServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.project.ProjectServiceBase;
import hu.iboard.coandco.datamagic.service.realty.RealtyArrangementServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "notificationserviceController")
@RequestScoped
public class NotificationServiceController extends AbstractController {

	public static final String NOTIFICATIONSERVICE_ACTION = "notificationlist";
	public static final String MOBILENOTIFICATIONSERVICE_ACTION = "mobilenotificationlist";

	@ManagedProperty(value = "#{notificationserviceData}")
	private NotificationServiceData data;
	@ManagedProperty(value = "#{notificationService}")
	private NotificationServiceBase notificationService;
	@ManagedProperty(value = "#{fileAttachsService}")
	private FileAttachsServiceBase fileAttachsService;
	@ManagedProperty(value = "#{projectService}")
	private ProjectServiceBase projectService;
	@ManagedProperty(value = "#{aruExtService}")
	private AruExtServiceBase aruExtService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{realtyArrangementService}")
	private RealtyArrangementServiceBase realtyArrangementService;
	@ManagedProperty(value = "#{workerService}")
	private WorkerServiceBase workerService;

	@Override
	public void loadData() {

		getData().setNotifList(new ArrayList<Object[]>());
		getData().setSelectedNotificationVO(new Object[22]);
		getData().setFrom(new Date());
		getData().setTo(new Date());
		getData().setFilteredText("");
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		initFromAndTo();
		getData().setRenterItems(new ArrayList<SelectItem>());
		getData().setFlatItems(new ArrayList<SelectItem>());
		getData().setAllBejelento(new ArrayList<SelectItem>());
		getData().setNotifListFiltered(new ArrayList<Object[]>());
		if (getManagedRenter() != null) {
			List<AruExt> aruexts = getAruExtService().getAruExtsForRenter(getManagedRenter().getVevokod());
			if (aruexts != null) {
				getData().setNotifList(loadAllDataForNotifList(getNotificationService().getNotificationsByAruExts(aruexts)));
				initDate();
			}

		}
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(NOTIFICATIONSERVICE_CONTROLLER);
	}

	@SuppressWarnings("deprecation")
	private void initFromAndTo() {
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	public String showNotification() {
		getData().setFiles(new ArrayList<Aattachs>());
		List<Aattachs> aattachs = new ArrayList<Aattachs>();
		try {
			if (getData().getSelectedNotificationVO()[0] != null) {
				aattachs = getNotificationService().getAttachsForNotification((Integer) getData().getSelectedNotificationVO()[0]);
				if (aattachs == null) {
					return null;
				}
				for (Aattachs aattach : aattachs) {
					getData().getFiles().add(aattach);
				}
			}
		} catch (Exception e) {
			logger.error("Error by getting attachs for notification!", e);
			return null;
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public void notificationDateFromChange(DateSelectEvent event) {
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void notificationDateToChange(DateSelectEvent event) {
		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	public void notificationDateChange(ActionEvent event) {

		if (getData().getSelectedRenterId() != 0) {
			if (getData().getNotifList() != null && getData().getNotifList().size() > 0) {
				List<Object[]> objs = new ArrayList<Object[]>();
				for (Object[] notif : getData().getNotifList()) {
					if (getData().getFrom().compareTo((Date) notif[7]) <= 0 && getData().getTo().compareTo((Date) notif[7]) >= 0) {
						objs.add(notif);
					}
				}
				getData().setNotifList(objs);
				return;
			} else {
				addInfoMessage("", "Üres hibalista!");
				return;
			}
		}

		if (getData().getSelectedProjectId() != 0) {
			if (getData().getNotifList() != null && getData().getNotifList().size() > 0) {
				List<Object[]> objs = new ArrayList<Object[]>();
				for (Object[] notif : getData().getNotifList()) {
					if (getData().getFrom().compareTo((Date) notif[7]) <= 0 && getData().getTo().compareTo((Date) notif[7]) >= 0) {
						objs.add(notif);
					}
				}
				getData().setNotifList(objs);
				return;
			} else {
				addInfoMessage("", "Üres hibalista!");
				return;
			}
		}

		if (getData().getSelectedOwnerId() != 0) {
			if (getData().getNotifList() != null && getData().getNotifList().size() > 0) {
				List<Object[]> objs = new ArrayList<Object[]>();
				for (Object[] notif : getData().getNotifList()) {
					if (getData().getFrom().compareTo((Date) notif[7]) <= 0 && getData().getTo().compareTo((Date) notif[7]) >= 0) {
						objs.add(notif);
					}
				}
				getData().setNotifList(objs);
				return;
			} else {
				addInfoMessage("", "Üres hibalista!");
				return;
			}
		}

		if (!getData().getSelectedBejelento().equals("")) {
			if (getData().getNotifList() != null && getData().getNotifList().size() > 0) {
				List<Object[]> objs = new ArrayList<Object[]>();
				for (Object[] notif : getData().getNotifList()) {
					if (getData().getFrom().compareTo((Date) notif[7]) <= 0 && getData().getTo().compareTo((Date) notif[7]) >= 0) {
						objs.add(notif);
					}
				}
				getData().setNotifList(objs);
				return;
			} else {
				addInfoMessage("", "Üres hibalista!");
				return;
			}
		}
		/*
		 * Calendar cal1 = Calendar.getInstance(); Calendar cal2 =
		 * Calendar.getInstance(); cal1.setTime(getData().getFrom());
		 * cal2.setTime(getData().getTo()); int yearDiff =
		 * cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR); int monthDiff =
		 * yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
		 * 
		 * if (getManagedUser().getUserType().equals(WORKER)) { if (monthDiff >
		 * 3) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!3 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return; } } if (!getManagedUser().getUserType().equals(WORKER)) {
		 * if (monthDiff > 12) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!12 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return; } }
		 */
		if (getManagedWorker() != null) {
			getData().setNotifList(
					loadAllDataForNotifList(getNotificationService().getNotificationsByDate(WORKER, null, new Timestamp(getData().getFrom().getTime()),
							new Timestamp(getData().getTo().getTime()))));
		}
		if (getManagedPartner() != null) {
			getData().setNotifList(
					loadAllDataForNotifList(getNotificationService().getNotificationsByDate(PARTNER, getManagedPartner().getVevokod(),
							new Timestamp(getData().getFrom().getTime()), new Timestamp(getData().getTo().getTime()))));
		}
		if (getManagedRenter() != null) {
			List<AruExt> aruexts = getAruExtService().getAruExtsForRenter(getManagedRenter().getVevokod());
			if (aruexts != null)
				getData().setNotifList(
						loadAllDataForNotifList(getNotificationService().getNotificationsByAruExtsByDate(aruexts, new Timestamp(getData().getFrom().getTime()),
								new Timestamp(getData().getTo().getTime()))));
		}
		if (getData().getNotifList().size() == 0) {
			addInfoMessage("", "Üres hibalista!");
		}
		if (getData().getNotifList().size() >= 1000) {
			addInfoMessage("", "A kérés túl sok adatot eredményez!Kérjük kisebb időintervallumot adjon meg!A legutóbbi 1000 adatsor látható!");
		}
	}

	private void initDate() {

		if (getData().getNotifList() != null && getData().getNotifList().size() > 0) {
			Date tempFromDate = (Date) getData().getNotifList().get(0)[7];
			Date tempToDate = (Date) getData().getNotifList().get(0)[7];
			for (Object[] vo : getData().getNotifList()) {
				if (tempFromDate.compareTo((Date) vo[7]) > 0) {
					tempFromDate = (Date) vo[7];
				}
			}
			for (Object[] vo : getData().getNotifList()) {
				if (tempToDate.compareTo((Date) vo[7]) < 0) {
					tempToDate = (Date) vo[7];
				}
			}
			getData().setTo(tempToDate);
			getData().setFrom(tempFromDate);

		} else {
			getData().setTo(new Timestamp(System.currentTimeMillis()));
			getData().setFrom(new Timestamp(System.currentTimeMillis()));
		}
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

	public String showRealtys() {
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setSelectedProjectId(0);
		getData().setRealtySearchText("");
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
		if (getManagedUser().getUserType().equals(PARTNER)) {
			List<Project> list = getProjectService().getProjectByPartnerId(getManagedPartner().getVevokod());
			if (list == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			for (Project item : list) {
				selectItemList.add(new SelectItem(item.getId(), item.getIrsz() + " " + item.getVaros() + ", " + item.getCim()));
			}
		}
		if (getManagedRenter() != null) {
			List<Object[]> aruexts = getAruExtService().getAruExtForRenter(getManagedRenter().getVevokod());
			if (aruexts == null) {
				getData().setFlatItems(new ArrayList<SelectItem>());
				return null;
			}
			Integer lastProjId = 0;
			for (Object[] aruext : aruexts) {
				Integer projectId = (Integer) aruext[1];
				if (!projectId.equals(lastProjId)) {
					if (projectId != null) {
						Project project = getProjectService().getProjectById(projectId);
						if (project != null)
							selectItemList.add(new SelectItem(project.getId(), project.getIrsz() + " " + project.getVaros() + ", " + project.getCim()));
					}
				}
				lastProjId = projectId;
			}
		}
		getData().setFlatItems(selectItemList);
		getData().setAllFlatItems(selectItemList);
		return null;
	}

	public void selectRealtyAction(ActionEvent event) {

		getData().setNotifList(loadAllDataForNotifList(getNotificationService().getNotificationsByProjectId(getData().getSelectedProjectId())));
		Project project = getProjectService().getProjectById(getData().getSelectedProjectId());
		if (project != null)
			getData().setFilteredText(project.getIrsz() + " " + project.getVaros() + ", " + project.getCim());
		getData().setNotifListFiltered(getData().getNotifList());
		initDate();
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		if (getData().getNotifList().size() == 0) {
			addInfoMessage("", "Üres hibalista!");
		}

	}

	public void showOwners(ActionEvent event) {
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setSelectedOwnerId(0);
		getData().setOwnerSearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> names = getProjectService().getAllOwnerName();
		if (names == null)
			return;
		for (Object[] object : names) {
			selectItemList.add(new SelectItem((Integer) object[0], (String) object[1]));
		}

		getData().setOwnerItems(selectItemList);
		getData().setAllOwnerItems(selectItemList);

	}

	public void showRenters(ActionEvent event) {
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setSelectedRenterId(0);
		getData().setRenterSearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getManagedUser().getUserType().equals(PARTNER) || getManagedUser().getUserType().equals(SALES)) {
			List<Integer> aktbers = new ArrayList<Integer>();
			if (getData().getSelectedProjectId() == null || getData().getSelectedProjectId() == 0) {
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
					List<Integer> berbeadasok = getAruExtService().getBerbeadasByAktBer(aktbers);
					if (berbeadasok != null) {
						List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(berbeadasok);
						if (partners != null && partners.size() > 0) {
							for (Object[] name : partners) {
								selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
							}
						}
					}
				}
			} else {
				List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(getData().getSelectedProjectId());
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
								return;
							for (Object[] name : partners) {
								selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));
							}
						}
					}
				}
			}
		} else {
			List<Integer> ids = getRealtyArrangementService().getRenterIdsForRealtyArrangement();
			if (ids == null)
				return;
			List<Object[]> partners = getPartnerService().getPartnersNameByPartnerId(ids);
			if (partners == null || partners.size() == 0)
				return;
			for (Object[] name : partners) {
				selectItemList.add(new SelectItem((Integer) name[0], (String) name[1]));

			}
		}
		getData().setRenterItems(selectItemList);
		getData().setAllRentalItems(selectItemList);
	}

	public void selectOwnerAction(ActionEvent event) {
		getData().setNotifList(loadAllDataForNotifList(getNotificationService().getNotificationsByOwner(getData().getSelectedOwnerId())));
		Partner owner = getPartnerService().getAllPartnerDataById(getData().getSelectedOwnerId());
		if (owner != null)
			getData().setFilteredText(owner.getNev());
		getData().setNotifListFiltered(getData().getNotifList());
		initDate();
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedBejelento("");
		if (getData().getNotifList().size() == 0) {
			addInfoMessage("", "Üres hibalista!");
		}
	}

	public void selectRenterAction(ActionEvent event) {
		List<AruExt> aruext = getAruExtService().getAruExtsForRenter(getData().getSelectedRenterId());
		if (aruext == null)
			return;
		getData().setNotifList(loadAllDataForNotifList(getNotificationService().getNotificationsByAruExts(aruext)));
		Partner renter = getPartnerService().getAllPartnerDataById(getData().getSelectedRenterId());
		if (renter != null)
			getData().setFilteredText(renter.getNev());
		getData().setNotifListFiltered(getData().getNotifList());
		initDate();
		getData().setSelectedProjectId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedBejelento("");
		if (getData().getNotifList().size() == 0) {
			addInfoMessage("", "Üres hibalista!");
		}
	}

	public void searchOwnerAction(ActionEvent event) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllRentalItems() == null || getData().getAllOwnerItems().size() == 0)
			return;
		for (SelectItem selectItem : getData().getAllOwnerItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getOwnerSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getOwnerSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setOwnerItems(selectItemList);
	}

	public void searchRenterAction(ActionEvent event) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllRentalItems() == null || getData().getAllRentalItems().size() == 0)
			return;
		for (SelectItem selectItem : getData().getAllRentalItems()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getRenterSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setRenterItems(selectItemList);
		initDate();
	}

	public void urgentCheckAction(ActionEvent event) {

		if ((Boolean) getData().getSelectedNotificationVO()[11]) {
			getData().getSelectedNotificationVO()[11] = true;
		} else {
			getData().getSelectedNotificationVO()[11] = false;
		}
	}

	public void downloadAttach(ActionEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
		String value = (String) requestMap.get("aattachname");
		String name = (String) requestMap.get("filename");
		if (value == null) {
			return;
		}

		try {
			getNotificationService().downloadAttachForNotification(value, getRealPath());
			HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
			int index = request.getRequestURL().toString().indexOf("pages");
			String url = request.getRequestURL().toString().substring(0, index);
			context.getExternalContext().redirect(url + "downloads/" + name);
		} catch (Exception e) {
			logger.error("ERROR OPENING URL BY NOTIFICATION", e);
			return;
		}
	}

	public void showAllBejelento(ActionEvent event) {
		getData().setAllBejelento(new ArrayList<SelectItem>());
		getData().setBejelentoSearchText("");
		getData().setSelectedBejelento("");
		List<Object[]> users = getNotificationService().getAllBejelentoFromNotification();
		if (users == null) {
			return;
		}
		String name = null;
		for (Object[] user : users) {
			if (user[1].equals('d')) {
				Dolgozo dolgozo = getWorkerService().getWorkerByReferenceId((Integer) user[0]);
				if (dolgozo != null)
					name = dolgozo.getDnev();
			} else {
				name = getPartnerService().getPartnerNameByPartnerId((Integer) user[0]);
			}
			getData().getAllBejelento().add(new SelectItem(String.valueOf(user[1]) + (Integer) user[0], name));
		}
	}

	public void searchBejelentoAction(ActionEvent event) {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		if (getData().getAllBejelento() == null || getData().getAllBejelento().size() == 0)
			return;
		for (SelectItem selectItem : getData().getAllBejelento()) {
			if (selectItem.getLabel().matches("(?i).*" + getData().getBejelentoSearchText().toLowerCase() + ".*")
					|| selectItem.getLabel().matches("(?i).*" + getData().getBejelentoSearchText().toUpperCase() + ".*")) {
				selectItemList.add(selectItem);
			}
		}
		getData().setAllBejelento(selectItemList);
	}

	public void selectBejelentoAction(ActionEvent event) {

		if (getData().getSelectedBejelento() == null || getData().getSelectedBejelento().equals(""))
			return;
		String userType = getData().getSelectedBejelento().substring(0, 1);
		Integer userId = Integer.valueOf(getData().getSelectedBejelento().substring(1, getData().getSelectedBejelento().length()));
		List<NotificationVO> vos = getNotificationService().getNotificationsByBejelento(Integer.valueOf(userId), userType.toCharArray()[0]);
		if (vos == null) {
			getData().setNotifList(new ArrayList<Object[]>());
			return;
		}
		getData().setNotifList(loadAllDataForNotifList(vos));
		String name = "";
		if (userType.equals("d")) {
			Dolgozo dolgozo = getWorkerService().getWorkerByReferenceId(userId);
			if (dolgozo != null)
				name = dolgozo.getDnev();
		} else {
			name = getPartnerService().getPartnerNameByPartnerId(userId);
		}

		getData().setFilteredText(name);
		initDate();
		getData().setNotifListFiltered(getData().getNotifList());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		if (getData().getNotifList().size() == 0) {
			addInfoMessage("", "Üres hibalista!");
		}
	}

	public List<Object[]> loadAllDataForNotifList(List<NotificationVO> list) {
		List<Object[]> objects = new ArrayList<Object[]>();
		if (list == null || list.size() == 0)
			return new ArrayList<Object[]>();
		for (NotificationVO vo : list) {
			Object[] obj = new Object[22];
			obj[0] = vo.getId();
			if (vo.getProject() != null)
				obj[1] = vo.getProject().getIrsz() + " " + vo.getProject().getVaros() + ", " + vo.getProject().getCim();
			else
				obj[1] = "";
			if (vo.getAruExt() != null)
				obj[2] = vo.getAruExt().getIrsz() + " " + vo.getAruExt().getVaros() + ", " + vo.getAruExt().getCim() + " " + vo.getAruExt().getCim2() + " "
						+ (vo.getAruExt().getLakasTipus() != null ? vo.getAruExt().getLakasTipus().getNev() : "");
			else
				obj[2] = "";
			obj[3] = vo.getCim();
			if (vo.getProject().getParentTulajdonos() != null)
				obj[4] = vo.getProject().getParentTulajdonos().getNev();
			else
				obj[4] = "";
			if (vo.getAruExt() != null && vo.getAruExt().getBerbeadas() != null && vo.getAruExt().getBerbeadas() != null
					&& vo.getAruExt().getBerbeadas().getPartner() != null)
				obj[5] = vo.getAruExt().getBerbeadas().getPartner().getNev();
			else
				obj[5] = "";
			String name = "";
			if (vo.getUserType() == 'd') {
				Dolgozo dolgozo = getWorkerService().getWorkerByReferenceId(vo.getUser());
				if (dolgozo != null)
					name = dolgozo.getDnev();
			} else {
				name = getPartnerService().getPartnerNameByPartnerId(vo.getUser());
			}
			obj[6] = name;
			obj[7] = vo.getBejelentes_ido();
			obj[8] = vo.getEszleles_ido();
			obj[9] = vo.getKertjav_ido();
			obj[10] = vo.getHatarido();
			obj[11] = vo.getUrgent();
			obj[12] = vo.getIngHibaStatusz().getNev();
			obj[13] = vo.getTelefon();
			obj[14] = vo.getSzamlazando();
			obj[15] = vo.getAlapkezelo();
			obj[16] = vo.getModositva();
			obj[17] = vo.getLeiras();
			if (vo.getVevo() != null)
				obj[18] = vo.getVevo().getNev();
			else
				obj[18] = "";
			obj[19] = vo.getFigyel();
			obj[20] = vo.getKomment();
			obj[21] = vo.getVisszahiv();

			objects.add(obj);
		}
		return objects;
	}

	public void resetAction(ActionEvent event) {
		loadData();
	}

	public NotificationServiceData getData() {
		return data;
	}

	public void setData(NotificationServiceData data) {
		this.data = data;
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

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public RealtyArrangementServiceBase getRealtyArrangementService() {
		return realtyArrangementService;
	}

	public void setRealtyArrangementService(RealtyArrangementServiceBase realtyArrangementService) {
		this.realtyArrangementService = realtyArrangementService;
	}

	public WorkerServiceBase getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerServiceBase workerService) {
		this.workerService = workerService;
	}

}

/**
 * 
 */
package hu.iboard.coandco.datamagic.presentation.controllers.realtyinvoice;

import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Project;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.aruext.AruExtServiceBase;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.invoice.InvoiceServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.project.ProjectServiceBase;
import hu.iboard.coandco.datamagic.service.realty.RealtyArrangementServiceBase;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "realtyinvoiceserviceController")
@RequestScoped
public class RealtyInvoiceServiceController extends AbstractController implements Serializable {

	private static final long serialVersionUID = -772245678971879258L;
	public static final String REALTYINVOICESERVICE_ACTION = "realtyinvoicelist";
	public static final String MOBILEREALTYINVOICESERVICE_ACTION = "mobilerealtyinvoicelist";

	@ManagedProperty(value = "#{realtyinvoiceserviceData}")
	private RealtyInvoiceServiceData data;

	@ManagedProperty(value = "#{invoiceService}")
	private InvoiceServiceBase invoiceService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;
	@ManagedProperty(value = "#{realtyArrangementService}")
	private RealtyArrangementServiceBase realtyArrangementService;
	@ManagedProperty(value = "#{projectService}")
	private ProjectServiceBase projectService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{aruExtService}")
	private AruExtServiceBase aruExtService;

	@Override
	public void loadData() {

		getData().setIn(true);
		getData().setOut(false);
		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		} else if (getManagedRenter() != null) {
			getData().setVevokod(getManagedRenter().getVevokod());
		} else {
			getData().setVevokod(null);
		}
		getData().setInvoice(new ArrayList<InvoiceVO>());
		getData().setFrom(new Date());
		getData().setTo(new Date());
		initSummary();
		initFromAndTo();
		getData().setSelectedProjectId(0);
		getData().setSelectedOwnerId(0);
		getData().setSelectedRenterId(0);
		getData().setFilteredText("");
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setAllRentalItems(new ArrayList<SelectItem>());
		getData().setInvoiceFiltered(new ArrayList<InvoiceVO>());
		getData().setNames(new ArrayList<Integer>());
		getInvoiceNames();
		getData().setProgress(new Float(0));
	}

	@Override
	public void reloadData() {
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

	private void initDate() {

		if (getData().getInvoice().size() > 0) {
			Date tempFromDate = getData().getInvoice().get(0).getKelt();
			Date tempToDate = (Date) getData().getInvoice().get(0).getKelt();
			for (InvoiceVO invoice : getData().getInvoice()) {
				if (tempFromDate.compareTo(invoice.getKelt()) > 0) {
					tempFromDate = invoice.getKelt();
				}
			}
			for (InvoiceVO invoice : getData().getInvoice()) {
				if (tempToDate.compareTo(invoice.getKelt()) < 0) {
					tempToDate = invoice.getKelt();
				}
			}
			getData().setTo(tempToDate);
			getData().setFrom(tempFromDate);
		} else {
			getData().setTo(new Date());
			getData().setFrom(new Date());
		}
	}

	@Override
	public void resetData() {
		removeBeanFromSession(REALTYINVOICESERVICE_CONTROLLER);
	}

	@SuppressWarnings("deprecation")
	public void invoiceDateFromChange(DateSelectEvent event) {
		getData().setProgress(new Float(0));
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void invoiceDateToChange(DateSelectEvent event) {
		getData().setProgress(new Float(0));
		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	public void invoiceDateChange(ActionEvent event) {
		getData().setProgress(new Float(0));
		if (getData().getSelectedRenterId() != 0) {
			if (getData().getInvoice() != null && getData().getInvoice().size() > 0) {
				List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
				for (InvoiceVO invoice : getData().getInvoice()) {
					if (getData().getFrom().compareTo(invoice.getKelt()) <= 0 && getData().getTo().compareTo(invoice.getKelt()) >= 0) {
						invoices.add(invoice);
					}
				}
				getData().setInvoice(invoices);
				initSummary();
				getData().setProgress(new Float(100.0));
				return;
			} else {
				addInfoMessage("", "Üres számlalista!");
				return;
			}
		}

		if (getData().getSelectedProjectId() != 0) {
			if (getData().getInvoice() != null && getData().getInvoice().size() > 0) {
				List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
				for (InvoiceVO invoice : getData().getInvoice()) {
					if (getData().getFrom().compareTo(invoice.getKelt()) <= 0 && getData().getTo().compareTo(invoice.getKelt()) >= 0) {
						invoices.add(invoice);
					}
				}
				getData().setInvoice(invoices);
				initSummary();
				getData().setProgress(new Float(100.0));
				return;
			} else {
				addInfoMessage("", "Üres számlalista!");
				return;
			}
		}

		if (getData().getSelectedOwnerId() != 0) {
			if (getData().getInvoice() != null && getData().getInvoice().size() > 0) {
				List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
				for (InvoiceVO invoice : getData().getInvoice()) {
					if (getData().getFrom().compareTo(invoice.getKelt()) <= 0 && getData().getTo().compareTo(invoice.getKelt()) >= 0) {
						invoices.add(invoice);
					}
				}
				getData().setInvoice(invoices);
				getData().setProgress(new Float(100.0));
				initSummary();
				return;
			} else {
				addInfoMessage("", "Üres számlalista!");
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
		 * ); return; } } if (!getManagedUser().getUserType().equals(PARTNER)) {
		 * if (monthDiff > 12) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!12 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return; } }
		 */
		List<Integer> allPartners = buildPartnerRenterList(getData().getVevokod());
		getData().setInvoice(new ArrayList<InvoiceVO>());
		int names = getData().getNames().size();
		float perc = (float) (100.0 / names);
		float prog = 0;
		for (Integer id : getData().getNames()) {
			getData().getInvoice().addAll(
					convertToInvoiceVO(getInvoiceService().getInvoicesByPartnersCDH(allPartners, getData().getFrom(), getData().getTo(), id), id));
			prog = prog + perc;
			getData().setProgress(new Float(prog));
		}
		getData().setProgress(new Float(100.0));
		if (getData().getInvoice() == null || getData().getInvoice().size() == 0)
			addInfoMessage("", "Üres számlalista!");
		if (getData().getInvoice().size() >= 1000)
			addInfoMessage("", "A kérés túl sok adatot eredményezett!Kérjük kisebb időintervallumot adjon meg!");
		initSummary();
	}

	private void initSummary() {

		if (getData().getInvoice().size() > 0) {
			Date now = new Timestamp(System.currentTimeMillis());
			Date d1900 = Timestamp.valueOf("1900-01-01 00:00:00.000");
			Long sumSum = new Long(0);
			Long sumDebit = new Long(0);
			Long sumPayed = new Long(0);
			Long sumExpired = new Long(0);
			for (InvoiceVO invoice : getData().getInvoice()) {
				sumSum += (invoice.getBrutto()).longValue();
				if ((invoice.getFizetve()).after(d1900)) {
					sumPayed += ((BigDecimal) invoice.getBrutto()).longValue();
				} else if ((invoice.getEsedkelt()).before(now)) {
					sumExpired += (invoice.getBrutto()).longValue();
				} else {
					sumDebit += (invoice.getBrutto()).longValue();
				}
			}
			getData().setSumSum(new BigDecimal(sumSum));
			getData().setSumDebit(new BigDecimal(sumDebit));
			getData().setSumPayed(new BigDecimal(sumPayed));
			getData().setSumExpired(new BigDecimal(sumExpired));
		} else {
			getData().setSumSum(new BigDecimal(0));
			getData().setSumDebit(new BigDecimal(0));
			getData().setSumPayed(new BigDecimal(0));
			getData().setSumExpired(new BigDecimal(0));
		}
	}

	public String invoiceItemsAction() {

		getData().setInvoiceItems(
				convertToInvoiceItemVO(getInvoiceService().getInvoiceItemsByInvoiceIdCDH(getData().getSelectedInvoice().getSorszam(),
						getData().getSelectedInvoice().getTablaVevokod())));
		return null;
	}

	@SuppressWarnings("deprecation")
	public String createDataForChart() {

		Object[] objects = getInvoiceService().getSummaryForChart(getManagedUser(), getData().getVevokod());
		if (objects != null) {
			getData().setSumIn(new BigDecimal(objects[0].toString()));
			getData().setDebitIn(new BigDecimal(objects[1].toString()));
			getData().setPayedIn(new BigDecimal(objects[2].toString()));
			getData().setExpiredIn(new BigDecimal(objects[3].toString()));
			// getData().setSumOut(new BigDecimal(objects[4].toString()));
			// getData().setDebitOut(new BigDecimal(objects[5].toString()));
			// getData().setPayedOut(new BigDecimal(objects[6].toString()));
			// getData().setExpiredOut(new BigDecimal(objects[7].toString()));
		}
		Date now = new Timestamp(System.currentTimeMillis());
		Date lastYear = new Timestamp(System.currentTimeMillis());
		lastYear.setYear(lastYear.getYear() - 1);
		lastYear.setMonth(lastYear.getMonth() + 1);
		getData().setChartFrom(lastYear);
		getData().setChartTo(now);
		getData().setChartInList(getInvoiceInForChart());
		return null;
	}

	@SuppressWarnings("deprecation")
	public void invoiceChartDateToChange(DateSelectEvent event) {

		getData().getChartTo().setHours(23);
		getData().getChartTo().setMinutes(59);
		getData().getChartTo().setSeconds(59);
		getData().setChartTo(event.getDate());
		getData().setChartInList(getInvoiceInForChart());
	}

	public void invoiceChartDateFromChange(DateSelectEvent event) {

		// if (getChartFrom().getTime() > getChartTo().getTime()) {
		// setChartFrom(getChartTo());
		// } else if (getChartTo().getTime() > (new Date().getTime())) {
		// setChartTo(new Date());
		// } else if (getChartTo().getTime() < getChartFrom().getTime()) {
		// setChartTo(getChartFrom());
		// }
		getData().setChartFrom(event.getDate());
		getData().setChartInList(getInvoiceInForChart());
	}

	private List<Object[]> getInvoiceInForChart() {

		List<Object[]> listIn = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		listIn = getInvoiceService().getInvoiceInForChart(getManagedUser(), getData().getVevokod(), new Timestamp(getData().getChartFrom().getTime()),
				new Timestamp(getData().getChartTo().getTime()));

		if (listIn != null) {

			for (Object[] object : listIn) {

				switch ((Integer) object[1]) {
				case 1:
					object[1] = "JAN";
					break;
				case 2:
					object[1] = "FEB";
					break;
				case 3:
					object[1] = "MAR";
					break;
				case 4:
					object[1] = "APR";
					break;
				case 5:
					object[1] = "MAJ";
					break;
				case 6:
					object[1] = "JUN";
					break;
				case 7:
					object[1] = "JUL";
					break;
				case 8:
					object[1] = "AUG";
					break;
				case 9:
					object[1] = "SZEPT";
					break;
				case 10:
					object[1] = "OKT";
					break;
				case 11:
					object[1] = "NOV";
					break;
				case 12:
					object[1] = "DEC";
					break;
				}
				newList.add(object);
			}
		}
		return newList;
	}

	public void resetAction(ActionEvent event) {
		loadData();
	}

	public String showRealtys() {
		getData().setAllFlatItems(new ArrayList<SelectItem>());
		getData().setSelectedProjectId(null);
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

	public void selectRealtyAction(ActionEvent event) {
		getData().setProgress(new Float(0));
		List<Integer> allPartners = new LinkedList<Integer>();
		Project project = getProjectService().getProjectById(getData().getSelectedProjectId());
		allPartners.add(project.getTulajdonos());

		getData().setFilteredText(project.getCim());

		List<Integer> berbeadasok = getRenterIdsByProjectId(getData().getSelectedProjectId());
		if (berbeadasok != null) {
			allPartners.addAll(berbeadasok);
		}

		List<InvoiceVO> invoices = new ArrayList<InvoiceVO>();
		List<Integer> list = new ArrayList<Integer>();

		for (Integer partnerId : allPartners) {
			list.add(partnerId);
		}
		int names = getData().getNames().size();
		float perc = (float) (100.0 / names);
		float prog = 0;
		getData().setInvoice(new ArrayList<InvoiceVO>());
		for (Integer id : getData().getNames()) {
			invoices.addAll(convertToInvoiceVO(getInvoiceService().getInvoicesByPartnersCDH(list, null, null, id), id));
			prog = prog + perc;
			getData().setProgress(new Float(prog));
		}
		getData().setInvoice(invoices);
		initSummary();
		initDate();
		getData().setInvoiceFiltered(getData().getInvoice());
		getData().setSelectedRenterId(0);
		getData().setSelectedOwnerId(0);
		if (getData().getInvoice().size() == 0) {
			addInfoMessage("", "Üres számlalista!");
		}
	}

	private List<Integer> buildPartnerRenterList(Integer partnerId) {
		List<Integer> allPartners = new LinkedList<Integer>();
		if (partnerId != null) {
			allPartners.add(partnerId);
			List<Project> projects = getProjectService().getProjectByPartnerId(partnerId);
			if (projects != null) {
				for (Project project : projects) {
					List<Integer> newRenters = getRenterIdsByProjectId(project.getId());
					if (newRenters != null)
						allPartners.addAll(newRenters);
				}
			}
		}
		return allPartners;
	}

	private List<Integer> getRenterIdsByProjectId(Integer projectId) {
		List<Object[]> aruexts = getAruExtService().getAruExtByProjectId(projectId);
		if (aruexts != null) {
			List<Integer> aktbers = new LinkedList<Integer>();
			for (Object[] object : aruexts) {
				Integer aktber = (Integer) object[5];
				if (aktber != 0)
					aktbers.add(aktber);
			}
			if (aktbers.size() > 0) {
				return getAruExtService().getBerbeadasByAktBer(aktbers);
			}
		}
		return null;
	}

	public void showOwners(ActionEvent event) {
		getData().setAllOwnerItems(new ArrayList<SelectItem>());
		getData().setSelectedOwnerId(0);
		getData().setOwnerSearchText("");
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> names = getRealtyArrangementService().getAllOwnersFromRealtyArrangement();
		if (names == null)
			return;
		for (Object[] object : names) {
			selectItemList.add(new SelectItem((Integer) object[0], (String) object[1]));
		}
		getData().setOwnerItems(selectItemList);
		getData().setAllOwnerItems(selectItemList);
	}

	public void selectOwnerAction(ActionEvent event) {
		getData().setProgress(new Float(0));
		List<Integer> list = new ArrayList<Integer>();
		list.add(getData().getSelectedOwnerId());
		getData().setInvoice(new ArrayList<InvoiceVO>());
		int names = getData().getNames().size();
		float perc = (float) (100.0 / names);
		float prog = 0;
		for (Integer id : getData().getNames()) {
			getData().getInvoice().addAll(convertToInvoiceVO(getInvoiceService().getInvoicesByPartnersCDH(list, null, null, id), id));
			prog = prog + perc;
			getData().setProgress(new Float(prog));
		}

		Partner owner = getPartnerService().getAllPartnerDataById(getData().getSelectedOwnerId());
		if (owner != null)
			getData().setFilteredText(owner.getNev());
		initSummary();
		initDate();
		getData().setInvoiceFiltered(getData().getInvoice());
		getData().setSelectedProjectId(0);
		getData().setSelectedRenterId(0);
		if (getData().getInvoice().size() == 0)
			addInfoMessage("", "Üres számlalista!");
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
	}

	public void selectRenterAction(ActionEvent event) {
		getData().setProgress(new Float(0));
		List<Integer> list = new ArrayList<Integer>();
		list.add(getData().getSelectedRenterId());
		getData().setInvoice(new ArrayList<InvoiceVO>());
		int names = getData().getNames().size();
		float perc = (float) (100.0 / names);
		float prog = 0;
		for (Integer id : getData().getNames()) {
			getData().getInvoice().addAll(convertToInvoiceVO(getInvoiceService().getInvoicesByPartnersCDH(list, null, null, id), id));
			prog = prog + perc;
			getData().setProgress(new Float(prog));
		}

		Partner renter = getPartnerService().getAllPartnerDataById(getData().getSelectedRenterId());
		if (renter != null)
			getData().setFilteredText(renter.getNev());
		initSummary();
		initDate();
		getData().setInvoiceFiltered(getData().getInvoice());
		getData().setSelectedProjectId(0);
		getData().setSelectedOwnerId(0);
		if (getData().getInvoice().size() == 0)
			addInfoMessage("", "Üres számlalista!");
	}

	public String print() {
		try {
			if (getData().getInvoice().size() == 0) {
				addFatalMessage("Üres a számlalista!", "");
				return null;
			}
			byte[] file = getDocumentService().handleGenerateInvoiceList(getData().getInvoice());
			fileDownloader(file, "invoice_list.pdf");
		} catch (Exception e) {
			addFatalMessage("Hiba történt nyomtatáskor!", "");
			logger.error("Error by printing incoicelist!", e);
		}
		return null;
	}

	private List<InvoiceVO> convertToInvoiceVO(List<Object[]> list, Integer tablaVevokod) {
		if (list == null || list.size() == 0)
			return new ArrayList<InvoiceVO>();
		List<InvoiceVO> result = new ArrayList<InvoiceVO>();
		for (Object[] obj : list) {
			InvoiceVO vo = new InvoiceVO();
			vo.setArfolyam((BigDecimal) obj[0]);
			vo.setBrutto((BigDecimal) obj[1]);
			vo.setCegnev((String) obj[2]);
			vo.setDbrutto((BigDecimal) obj[3]);
			vo.setDevnem((String) obj[4]);
			vo.setDnetto((BigDecimal) obj[5]);
			vo.setEsedkelt((Date) obj[6]);
			vo.setFizetve((Date) obj[7]);
			vo.setFizmod((String) obj[8]);
			vo.setKelt((Date) obj[9]);
			vo.setNetto((BigDecimal) obj[10]);
			vo.setRealesed((Date) obj[11]);
			vo.setRossz((BigDecimal) obj[16]);
			vo.setSorszam((String) obj[12]);
			vo.setSzlaszam((String) obj[13]);
			vo.setTelj((Date) obj[14]);
			vo.setVevokod((Integer) obj[15]);
			vo.setArrears(vo.getBrutto().subtract(vo.getRossz()));
			vo.setTablaVevokod(tablaVevokod);
			result.add(vo);
		}
		return result;

	}

	private List<InvoiceItemVO> convertToInvoiceItemVO(List<Object[]> list) {
		if (list == null || list.size() == 0)
			return new ArrayList<InvoiceItemVO>();
		List<InvoiceItemVO> result = new ArrayList<InvoiceItemVO>();
		for (Object[] obj : list) {
			InvoiceItemVO vo = new InvoiceItemVO();
			vo.setAcikksz((String) obj[0]);
			vo.setAear((BigDecimal) obj[1]);
			vo.setAertek((BigDecimal) obj[2]);
			vo.setAmegn((String) obj[3]);
			vo.setAmenny((BigDecimal) obj[4]);
			vo.setArukod((Integer) obj[5]);
			vo.setBertek((BigDecimal) obj[6]);
			vo.setHaertek((BigDecimal) obj[7]);
			vo.setHbertek((BigDecimal) obj[8]);
			vo.setHnertek((BigDecimal) obj[9]);
			vo.setNertek((BigDecimal) obj[10]);
			vo.setSorszam((String) obj[11]);
			vo.setTetelszam((Integer) obj[12]);
			vo.setUnikazon((String) obj[13]);
			result.add(vo);
		}
		return result;

	}

	private void getInvoiceNames() {
		getData().getNames().add(47);
		getData().getNames().add(49);
		getData().getNames().add(79);
		getData().getNames().add(273);
		getData().getNames().add(274);
		getData().getNames().add(275);
		getData().getNames().add(276);
		getData().getNames().add(277);
		getData().getNames().add(278);
		getData().getNames().add(279);
		getData().getNames().add(280);
		getData().getNames().add(281);
		getData().getNames().add(283);
		getData().getNames().add(284);
		getData().getNames().add(285);
		getData().getNames().add(286);
		getData().getNames().add(287);
		getData().getNames().add(288);
		getData().getNames().add(289);
		getData().getNames().add(290);
		getData().getNames().add(291);
		getData().getNames().add(292);
		getData().getNames().add(293);
		getData().getNames().add(294);
		getData().getNames().add(295);
		getData().getNames().add(296);
		getData().getNames().add(297);
		getData().getNames().add(299);
		getData().getNames().add(300);
		getData().getNames().add(301);
		getData().getNames().add(303);
		getData().getNames().add(310);
		getData().getNames().add(312);
		getData().getNames().add(315);
		getData().getNames().add(316);
		getData().getNames().add(317);
		getData().getNames().add(332);
		getData().getNames().add(339);
		getData().getNames().add(340);
		getData().getNames().add(341);
		getData().getNames().add(343);
		getData().getNames().add(348);
		getData().getNames().add(350);
		getData().getNames().add(351);
		getData().getNames().add(352);
		getData().getNames().add(353);
		getData().getNames().add(354);
		getData().getNames().add(355);
		getData().getNames().add(356);
		getData().getNames().add(357);
		getData().getNames().add(358);
		getData().getNames().add(359);
		getData().getNames().add(360);
		getData().getNames().add(361);
		getData().getNames().add(362);
		getData().getNames().add(363);
		getData().getNames().add(364);
		getData().getNames().add(365);
		getData().getNames().add(366);
		getData().getNames().add(367);
		getData().getNames().add(442);
		getData().getNames().add(490);
		getData().getNames().add(522);
		getData().getNames().add(368);
		getData().getNames().add(369);
		getData().getNames().add(342);
		getData().getNames().add(370);
		getData().getNames().add(349);
		getData().getNames().add(338);
		getData().getNames().add(344);
		getData().getNames().add(282);
		getData().getNames().add(298);
		getData().getNames().add(313);
		getData().getNames().add(314);
		getData().getNames().add(158);
	}

	public RealtyInvoiceServiceData getData() {
		return data;
	}

	public void setData(RealtyInvoiceServiceData data) {
		this.data = data;
	}

	public InvoiceServiceBase getInvoiceService() {
		return invoiceService;
	}

	public void setInvoiceService(InvoiceServiceBase invoiceService) {
		this.invoiceService = invoiceService;
	}

	public DocumentServiceBase getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentServiceBase documentService) {
		this.documentService = documentService;
	}

	public RealtyArrangementServiceBase getRealtyArrangementService() {
		return realtyArrangementService;
	}

	public void setRealtyArrangementService(RealtyArrangementServiceBase realtyArrangementService) {
		this.realtyArrangementService = realtyArrangementService;
	}

	public ProjectServiceBase getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectServiceBase projectService) {
		this.projectService = projectService;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public AruExtServiceBase getAruExtService() {
		return aruExtService;
	}

	public void setAruExtService(AruExtServiceBase aruExtService) {
		this.aruExtService = aruExtService;
	}
}

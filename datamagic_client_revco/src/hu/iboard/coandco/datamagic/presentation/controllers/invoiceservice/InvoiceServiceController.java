package hu.iboard.coandco.datamagic.presentation.controllers.invoiceservice;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.invoice.InvoiceServiceBase;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "invoiceserviceController")
@RequestScoped
public class InvoiceServiceController extends AbstractController implements Serializable {

	private static final long serialVersionUID = 8277072398284579452L;
	public static final String INVOICESERVICE_ACTION = "invoicelist";
	public static final String MOBILEINVOICESERVICE_ACTION = "mobileinvoicelist";

	@ManagedProperty(value = "#{invoiceserviceData}")
	private InvoiceServiceData data;

	@ManagedProperty(value = "#{invoiceService}")
	private InvoiceServiceBase invoiceService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;

	@Override
	public void loadData() {

		getData().setIn(true);
		getData().setOut(true);
		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		} else {
			getData().setVevokod(null);
		}
		getData().setFrom(new Date());
		getData().setTo(new Date());
		getData().setInvoice(new ArrayList<InvoiceVO>());
		getData().setTempInvoices(new ArrayList<InvoiceVO>());
		getData().setNetto(new BigDecimal(0));
		getData().setBrutto(new BigDecimal(0));
		initFromAndTo();
	}

	@Override
	public void reloadData() {
	}

	@Override
	public void resetData() {
		removeBeanFromSession(INVOICESERVICE_CONTROLLER);
	}

	public void invoiceDateChange(ActionEvent event) {

		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(getData().getFrom());
		cal2.setTime(getData().getTo());
		int yearDiff = cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR);
		int monthDiff = yearDiff * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);

		if (getManagedUser().getUserType().equals(WORKER)) {
			if (monthDiff > 3) {
				addErrorMessage("",
						"Túl nagy időintervallumot adott meg!3 hónapnál nagyobb időintervallum túl sok adatot eredményez!");
				return;
			}
		}
		if (!getManagedUser().getUserType().equals(WORKER)) {
			if (monthDiff > 12) {
				addErrorMessage("",
						"Túl nagy időintervallumot adott meg!12 hónapnál nagyobb időintervallum túl sok adatot eredményez!");
				return;
			}
		}
		getData().setInvoice(
				getInvoiceService().getInvoices(getData().getVevokod(), getData().getFrom(), getData().getTo(),
						getData().getIn(), getData().getOut()));
		getData().setTempInvoices(getData().getInvoice());
		initSummary();
	}
	
	@SuppressWarnings("deprecation")
	private void initFromAndTo(){
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);	
	}

	@SuppressWarnings("deprecation")
	public void invoiceDateFromChange(DateSelectEvent event) {
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void invoiceDateToChange(DateSelectEvent event) {

		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	@SuppressWarnings("deprecation")
	public void invoiceChartDateToChange(DateSelectEvent event) {

		getData().getChartTo().setHours(23);
		getData().getChartTo().setMinutes(59);
		getData().getChartTo().setSeconds(59);
		getData().setChartTo(event.getDate());
		getData().setChartInList(getInvoiceInForChart());
		getData().setChartOutList(getInvoiceOutForChart());
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
		getData().setChartOutList(getInvoiceOutForChart());
	}

	public String showInvoice() {

		if (getData().getTempInvoices() == null || getData().getTempInvoices().size() == 0)
			return null;
		List<InvoiceVO> temp = new ArrayList<InvoiceVO>();
		if (getData().getIn() && getData().getOut()) {
			getData().setInvoice(getData().getTempInvoices());
			initSummary();
			return null;
		}
		if (!getData().getIn() && !getData().getOut()) {
			getData().setInvoice(new ArrayList<InvoiceVO>());
			initSummary();
			return null;
		}
		if (getData().getIn() && !getData().getOut()) {
			for (InvoiceVO invoiceVO : getData().getTempInvoices()) {
				if (!invoiceVO.getKimeno()) {
					temp.add(invoiceVO);
				}
			}
		}
		if (!getData().getIn() && getData().getOut()) {
			for (InvoiceVO invoiceVO : getData().getTempInvoices()) {
				if (invoiceVO.getKimeno()) {
					temp.add(invoiceVO);
				}
			}
		}
		getData().setInvoice(temp);
		initSummary();
		return null;
	}

	private void initSummary() {

		if (getData().getInvoice().size() > 0) {
			Long tempBrutto = new Long(0);
			Long tempNetto = new Long(0);
			for (InvoiceVO invoice : getData().getInvoice()) {
				tempBrutto += invoice.getBrutto().longValue();
				tempNetto += invoice.getNetto().longValue();
			}
			getData().setNetto(new BigDecimal(tempNetto));
			getData().setBrutto(new BigDecimal(tempBrutto));
		} else {
			getData().setNetto(new BigDecimal(0));
			getData().setBrutto(new BigDecimal(0));
		}
	}

	public String invoiceItemsAction() {

		getData().setInvoiceItems(
				getInvoiceService().getInvoiceItemsByInvoiceId(getData().getSelectedInvoice().getSorszam(),
						getData().getSelectedInvoice().getKimeno()));
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
			getData().setSumOut(new BigDecimal(objects[4].toString()));
			getData().setDebitOut(new BigDecimal(objects[5].toString()));
			getData().setPayedOut(new BigDecimal(objects[6].toString()));
			getData().setExpiredOut(new BigDecimal(objects[7].toString()));
		}
		Date now = new Timestamp(System.currentTimeMillis());
		Date lastYear = new Timestamp(System.currentTimeMillis());
		lastYear.setYear(lastYear.getYear() - 1);
		lastYear.setMonth(lastYear.getMonth() + 1);
		getData().setChartFrom(lastYear);
		getData().setChartTo(now);
		getData().setChartInList(getInvoiceInForChart());
		getData().setChartOutList(getInvoiceOutForChart());
		return null;
	}

	private List<Object[]> getInvoiceInForChart() {

		List<Object[]> listIn = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		listIn = getInvoiceService().getInvoiceInForChart(getManagedUser(), getData().getVevokod(),
				new Timestamp(getData().getChartFrom().getTime()), new Timestamp(getData().getChartTo().getTime()));

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

	private List<Object[]> getInvoiceOutForChart() {

		List<Object[]> listOut = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		listOut = getInvoiceService().getInvoiceOutForChart(getManagedUser(), getData().getVevokod(),
				new Timestamp(getData().getChartFrom().getTime()), new Timestamp(getData().getChartTo().getTime()));

		if (listOut != null) {

			for (Object[] object : listOut) {

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

//	public void mobileInvoiceClick(ActionEvent event) {
//		String selectedInvoiceSorszam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
//				.get("selectedInvoice");
//	}

	public InvoiceServiceData getData() {
		return data;
	}

	public void setData(InvoiceServiceData data) {
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

}

package hu.iboard.coandco.datamagic.presentation.controllers.openitems;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.invoice.InvoiceServiceBase;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "openitemsserviceController")
@RequestScoped
public class OpenItemsServiceController extends AbstractController {

	public static final String OPENITEMSSERVICE_ACTION = "openitemslist";

	@ManagedProperty(value = "#{openitemsData}")
	private OpenItemsData data;

	@ManagedProperty(value = "#{invoiceService}")
	private InvoiceServiceBase invoiceService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;

	@Override
	public void loadData() {

		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		}
		getData().setOpenItemList(new ArrayList<InvoiceVO>());
		getData().setNetto(new BigDecimal(0));
		getData().setBrutto(new BigDecimal(0));
		getData().setArrears(new BigDecimal(0));
		getData().setFrom(new Date());
		getData().setTo(new Date());
		initFromAndTo();
		
	}

	@Override
	public void reloadData() {
	}

	@Override
	public void resetData() {
		removeBeanFromSession(OPENITEMSSERVICE_CONTROLLER);

	}

	private void initSummary() {

		if (getData().getOpenItemList() != null) {
			Long tempBrutto = new Long(0);
			Long tempNetto = new Long(0);
			Long tempArrears = new Long(0);
			for (InvoiceVO invoice : getData().getOpenItemList()) {
				tempBrutto += invoice.getBrutto().longValue();
				tempNetto += invoice.getNetto().longValue();
				tempArrears += invoice.getArrears().longValue();
			}
			getData().setNetto(new BigDecimal(tempNetto));
			getData().setBrutto(new BigDecimal(tempBrutto));
			getData().setArrears(new BigDecimal(tempArrears));
		} else {
			getData().setNetto(new BigDecimal(0));
			getData().setBrutto(new BigDecimal(0));
		}
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

	public void invoiceDateChange(ActionEvent event) {
		/*
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
		}*/
		getData().setOpenItemList(
				getInvoiceService().getOpenItemList(getData().getVevokod(), getData().getFrom(), getData().getTo()));
		if (getData().getOpenItemList()!=null && getData().getOpenItemList().size() >= 1000)
			addInfoMessage("", "A kérés túl sok adatot eredményezett!Kérjük kisebb időintervallumot adjon meg!A legutóbbi 1000 adatsor látható!");
		initSummary();
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

	public String print() {
		try {
			if (getData().getOpenItemList() == null || getData().getOpenItemList().size() == 0) {
				addFatalMessage("Üres a számlalista!", "");
				return null;
			}
			byte[] file = getDocumentService().handleGenerateInvoiceList(getData().getOpenItemList());
			fileDownloader(file, "openinvoice_list.pdf");
		} catch (Exception e) {
			addFatalMessage("Hiba történt nyomtatáskor!", "");
			logger.error("Error by printing incoicelist!", e);
		}
		return null;
	}

	public OpenItemsData getData() {
		return data;
	}

	public void setData(OpenItemsData data) {
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

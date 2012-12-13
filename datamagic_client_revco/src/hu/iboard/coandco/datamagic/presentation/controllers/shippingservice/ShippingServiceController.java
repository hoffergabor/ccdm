package hu.iboard.coandco.datamagic.presentation.controllers.shippingservice;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.shipping.ShippingServiceBase;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

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

@ManagedBean(name = "shippingserviceController")
@RequestScoped
public class ShippingServiceController extends AbstractController {

	public static final String SHIPPINGSERVICE_ACTION = "shippinglist";

	@ManagedProperty(value = "#{shippingserviceData}")
	private ShippingServiceData data;

	@ManagedProperty(value = "#{shippingService}")
	private ShippingServiceBase shippingService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;

	@Override
	public void loadData() {

		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		} else {
			getData().setVevokod(null);
		}
		getData().setShippings(new ArrayList<ShippingVO>());
		getData().setFrom(new Date());
		getData().setTo(new Date());
		getData().setNetto(new BigDecimal(0));
		getData().setBrutto(new BigDecimal(0));
		initFromAndTo();
	}

	@Override
	public void reloadData() {
	}

	@Override
	public void resetData() {
		removeBeanFromSession(SHIPPINGSERVICE_CONTROLLER);

	}

	public String shippingItemsAction() {

		return null;
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
	public void shippingDateFromChange(DateSelectEvent event) {
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void shippingDateToChange(DateSelectEvent event) {

		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	public void shippingDateChange(ActionEvent event) {

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
		getData().setShippings(
				getShippingService().getShippingsByDateFiltered(getData().getVevokod(),
						new Date(getData().getFrom().getTime()), new Date(getData().getTo().getTime())));
		initSummary();
	}

	private void initSummary() {

		if (getData().getShippings() != null) {
			Long tempBrutto = new Long(0);
			Long tempNetto = new Long(0);
			for (ShippingVO shipping : getData().getShippings()) {
				tempBrutto += shipping.getDbrutto().longValue();
				tempNetto += shipping.getDnetto().longValue();
			}
			getData().setNetto(new BigDecimal(tempNetto));
			getData().setBrutto(new BigDecimal(tempBrutto));
		} else {
			getData().setNetto(new BigDecimal(0));
			getData().setBrutto(new BigDecimal(0));
		}
	}

	private List<Object[]> getShippingsForChart() {

		List<Object[]> listIn = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		listIn = getShippingService().getShippingsForChart(getManagedUser(), getData().getVevokod(),
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

	@SuppressWarnings("deprecation")
	public String createDataForChart() {

		Object[] objects = getShippingService().getSummaryForChart(getManagedUser(), getData().getVevokod());
		if (objects != null) {
			getData().setSum(new BigDecimal(objects[0].toString()));
			getData().setDebit(new BigDecimal(objects[1].toString()));
			getData().setPayed(new BigDecimal(objects[2].toString()));
			getData().setExpired(new BigDecimal(objects[3].toString()));

		}
		Date now = new Timestamp(System.currentTimeMillis());
		Date lastYear = new Timestamp(System.currentTimeMillis());
		lastYear.setYear(lastYear.getYear() - 1);
		lastYear.setMonth(lastYear.getMonth() + 1);
		getData().setChartFrom(lastYear);
		getData().setChartTo(now);
		getData().setChartList(getShippingsForChart());

		return null;
	}

	public void shippingChartDateFromChange(DateSelectEvent event) {

		getData().setChartFrom(event.getDate());
		getData().setChartList(getShippingsForChart());
	}

	@SuppressWarnings("deprecation")
	public void shippingChartDateToChange(DateSelectEvent event) {

		getData().setChartTo(event.getDate());
		getData().getChartTo().setHours(23);
		getData().getChartTo().setMinutes(59);
		getData().getChartTo().setSeconds(59);
		// if (getData().getChartFrom().getTime() >
		// getData().getChartTo().getTime()) {
		// getData().setChartFrom(getData().getChartTo());
		// } else if (getData().getChartTo().getTime() > (new Date().getTime()))
		// {
		// getData().setChartTo(new Date());
		// } else if (getData().getChartTo().getTime() <
		// getData().getChartFrom().getTime()) {
		// getData().setChartTo(getData().getChartFrom());
		// }
		getData().setChartList(getShippingsForChart());
	}

	public void getShippingItem(ActionEvent event) {
		if (getData().getSelectedShipping() == null) {
			getData().setShippingItems(new ArrayList<ShippingItemVO>());
			return;
		}
		List<ShippingItemVO> items = getShippingService().getShippingItemsByShippingId(
				getData().getSelectedShipping().getSorszam());
		if (items == null) {
			getData().setShippingItems(new ArrayList<ShippingItemVO>());
			return;
		}
		getData().setShippingItems(items);
	}

	public String print() {
		try {
			if (getData().getShippings() == null || getData().getShippings().size() == 0) {
				addFatalMessage("Üres a szállítólevél-lista!", "");
				return null;
			}
			byte[] file = getDocumentService().handleGenerateShippingList(getData().getShippings());
			fileDownloader(file, "shipping_list.pdf");
		} catch (Exception e) {
			addFatalMessage("Hiba történt nyomtatáskor!", "");
			logger.error("Error by printing shippinglist!", e);
		}
		return null;
	}

	public ShippingServiceData getData() {
		return data;
	}

	public void setData(ShippingServiceData data) {
		this.data = data;
	}

	public ShippingServiceBase getShippingService() {
		return shippingService;
	}

	public void setShippingService(ShippingServiceBase shippingService) {
		this.shippingService = shippingService;
	}

	public DocumentServiceBase getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentServiceBase documentService) {
		this.documentService = documentService;
	}

}

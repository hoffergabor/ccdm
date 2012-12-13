package hu.iboard.coandco.datamagic.presentation.controllers.orderservice;

import hu.iboard.coandco.datamagic.generated.Bizkomment;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice.ManageOrderServiceController;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.bizkomment.BizkommentServiceBase;
import hu.iboard.coandco.datamagic.service.document.DocumentServiceBase;
import hu.iboard.coandco.datamagic.service.fuvpartner.FuvpartnerServiceBase;
import hu.iboard.coandco.datamagic.service.order.OrderServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.primefaces.event.DateSelectEvent;

@ManagedBean(name = "orderserviceController")
@RequestScoped
public class OrderServiceController extends AbstractController {

	public static final String ORDERSERVICE_ACTION = "orderlist";

	@ManagedProperty(value = "#{orderservicedata}")
	private OrderServiceData data;

	@ManagedProperty(value = "#{orderService}")
	private OrderServiceBase orderService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentServiceBase documentService;
	@ManagedProperty(value = "#{bizkommentService}")
	private BizkommentServiceBase bizkommentService;
	@ManagedProperty(value = "#{productService}")
	private ProductServiceBase productService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{fuvpartnerService}")
	private FuvpartnerServiceBase fuvpartnerService;

	ManageOrderServiceController mosc = ManageOrderServiceController.class
			.cast(getBeanFromSession(MANAGEORDERSERVICE_CONTROLLER) == null ? addNewBeanForSession(MANAGEORDERSERVICE_CONTROLLER)
					: getBeanFromSession(MANAGEORDERSERVICE_CONTROLLER));

	@Override
	public void loadData() {
		getData().setAllOrder(false);
		if (getManagedPartner() != null) {
			getData().setVevokod(getManagedPartner().getVevokod());
		} else if (getManagedStore() != null) {
			getData().setVevokod(getManagedStore().getVevokod());
		} else {
			getData().setVevokod(null);
		}
		getData().setOrders(new ArrayList<OrderVO>());
		getData().setNetto(new BigDecimal(0));
		getData().setBrutto(new BigDecimal(0));
		getData().setFrom(new Date());
		getData().setTo(new Date());
		initFromAndTo();
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(ORDERSERVICE_CONTROLLER);

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

	public String modifyOrderAction() {

		mosc.getData().setIsModifyOrder(true);
		ProductVO productVO = new ProductVO();
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		if (getData().getOrderItems() == null) {
			return null;
		}
		for (OrderItemVO orderItem : getData().getOrderItems()) {
			if (!getManagedUser().getUserType().equals(WORKER)) {
				productVO = getProductService().getProductsById(orderItem.getArukod(), getQueryParamsVO());
			} 
//			else {
//				productVO = getProductService().getProductsById(orderItem.getArukod(), null);
//			}
			if (productVO != null) {
				productVO.setMennyiseg(orderItem.getAmenny());
				productVOList.add(productVO);
			}
		}
		mosc.getData().setOrderNum(getData().getSelectedOrder().getSorszam());
		mosc.getData().setModifyProductList(productVOList);
		if (getManagedWorker() != null)
			mosc.getData().setOrderPartner(getPartnerService().getPartnerByReferenceId(getData().getSelectedOrder().getVevokod()));

		return "manageorder";
	}

	public String reOrderAction() {
		mosc.getData().setIsModifyOrder(false);
		ProductVO productVO = new ProductVO();
		List<ProductVO> productVOList = new ArrayList<ProductVO>();
		if (getData().getOrderItems() == null) {
			return null;
		}
		for (OrderItemVO orderItem : getData().getOrderItems()) {
			if (!getManagedUser().getUserType().equals(WORKER)) {
				productVO = getProductService().getProductsById(orderItem.getArukod(), getQueryParamsVO());
			} else {
				productVO = getProductService().getProductsById(orderItem.getArukod(), null);
			}
			if (productVO != null) {
				productVO.setMennyiseg(orderItem.getAmenny());
				productVOList.add(productVO);
			}
		}
		mosc.getData().setModifyProductList(productVOList);
		if (getManagedWorker() != null)
			mosc.getData().setOrderPartner(getPartnerService().getPartnerByReferenceId(getData().getSelectedOrder().getVevokod()));

		return "manageorder";
	}

	public String newOrderAction() {
		mosc.getData().setIsModifyOrder(false);
		mosc.getData().setModifyProductList(new ArrayList<ProductVO>());
		if (getManagedUser().getUserType().equals(WORKER)) {
			getData().setAllPartner(new ArrayList<Object[]>());
			getData().setPartnerSearchText("");
			getData().setAllPartner(getPartnerService().getAllPartner());
			getData().setSelectedPartnerId(0);
			return null;
		}
		if (getManagedUser().getUserType().equals(PARTNER)) {
			if (getManagedStores() != null) {
				if (getManagedStores().size() != 1) {
					List<Object[]> temp = new ArrayList<Object[]>();
					for (Partner partner : getManagedStores()) {
						Object[] obj = new Object[2];
						obj[0] = partner.getNev();
						obj[1] = partner.getVevokod();
						temp.add(obj);
					}
					getData().setAllPartner(temp);
					getData().setPartnerSearchText("");
					getData().setSelectedPartnerId(0);
					return null;
				} else {
					mosc.getData().setOrderPartner(getManagedStores().get(0));
				}
			}
		}
		if (getManagedUser().getUserType().equals(STORE)) {
			mosc.getData().setOrderPartner(getManagedStore());
		}
		return "manageorder";
	}

	public String searchPartnerAction() {
		if (getData().getAllPartner() == null || getData().getAllPartner().size() == 0)
			return null;
		List<Object[]> selectItemList = new ArrayList<Object[]>();
		for (Object[] object : getData().getAllPartner()) {
			if (object[0].toString().matches("(?i).*" + getData().getPartnerSearchText().toLowerCase() + ".*")
					|| object[0].toString().matches("(?i).*" + getData().getPartnerSearchText().toUpperCase() + ".*")) {
				selectItemList.add(object);
			}
		}
		getData().setAllPartner(selectItemList);
		return null;
	}

	public String submitChoosePartner() {

		if (getData().getSelectedPartnerId() == null || getData().getSelectedPartnerId() == 0)
			return null;
		mosc.getData().setOrderPartner(getPartnerService().getPartnerByReferenceId(getData().getSelectedPartnerId()));
		
		List<Date> dates = getFuvpartnerService().loadAvaibleFuvDates(mosc.getData().getOrderPartner().getVevokod(), mosc.getData().getOrderPartner().getUtvonal());
		if (dates != null) {
			mosc.setManagedFuvDates(dates);
		}				
		
		return "manageorder";
	}

	public String sendFiles() {
		getData().setMailTo(null);
		return null;
	}

	public boolean isEmailValid() {

		if (getData().getMailTo().contains("@"))
			return true;
		return false;
	}

	public void sendEmailWidthPDFFiles(ActionEvent event) {

		try {
			if (!isEmailValid()) {
				addFatalMessage("A megadott e-mail cím formátuma helytelen!", "");
				logger.error("Invalid email address!");
			} else {
				List<DataSource> sourceList = new ArrayList<DataSource>();
				for (OrderVO order : getData().getOrders()) {

					byte[] file = getDocumentService().handleGenerateOrder(order.getSorszam());
					DataSource source = new ByteArrayDataSource(file, "application/pdf");

					sourceList.add(source);
				}

				Boolean isSuccess = sendMail(getData().getMailTo(), sourceList, getManagedPartner().getNev());
				if (isSuccess) {
					addInfoMessage("A PDF fájlok küldése sikeresen megtörtént.", "");
					logger.info("PDF files are sent!");
				} else {
					addFatalMessage("A PDF fájlok küldése sikertelen volt.", "");
					logger.error("PDF files are not sent!");
				}
			}
		} catch (Exception e) {
			addFatalMessage("A PDF fájlok küldése sikertelen volt.", "");
			logger.error("PDF files are not sent!", e);
		}
	}

	public void sendEmailWidthXLSFiles(ActionEvent event) {

		try {
			if (!isEmailValid()) {
				addFatalMessage("A megadott e-mail cím formátuma helytelen!", "");
				logger.error("Invalid email address!");
			} else {
				List<DataSource> sourceList = new ArrayList<DataSource>();
				for (OrderVO order : getData().getOrders()) {

					byte[] file = getDocumentService().handleGenerateOrderExcel(order.getSorszam());
					DataSource source = new ByteArrayDataSource(file, "application/vnd.ms-excel");
					sourceList.add(source);
				}

				Boolean isSuccess = sendMailWithXLS(getData().getMailTo(), sourceList, getManagedPartner().getNev());
				if (isSuccess) {
					addInfoMessage("A Excel fájlok küldése sikeresen megtörtént.", "");
					logger.info("Excel files are sent!");
				} else {
					addFatalMessage("A Excel fájlok küldése sikertelen volt.", "");
					logger.error("Excel files are not sent!");
				}
			}
		} catch (Exception e) {
			addFatalMessage("A Excel fájlok küldése sikertelen volt.", "");
			logger.error("Excel files are not sent!", e);
		}
	}

	public String print() {
		try {
			if (getData().getSelectedOrder() == null) {
				addFatalMessage("Nincsnek tételek a rendelésben.", "");
				return null;
			}
			byte[] file = getDocumentService().handleGenerateOrder(getData().getSelectedOrder().getSorszam());
			fileDownloader(file, "order_list.pdf");
		} catch (Exception e) {
			addFatalMessage("Hiba történt nyomtatás közben.", "");
			logger.error("Error by printing order item list!", e);
		}
		return null;
	}

	public void orderDateChange(ActionEvent event) {
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
		List<OrderVO> temp = new ArrayList<OrderVO>();
		List<OrderVO> list = getOrderService().getOrdersByDateFiltered(getData().getVevokod(), new Timestamp(getData().getFrom().getTime()),
				new Timestamp(getData().getTo().getTime()), getData().getAllOrder(), null);
		if (list != null && list.size() > 0)
			temp.addAll(list);
		if (getManagedStores() != null) {
			for (Partner partner : getManagedStores()) {
				List<OrderVO> storeOrders = getOrderService().getOrdersByDateFiltered(partner.getVevokod(), new Timestamp(getData().getFrom().getTime()),
						new Timestamp(getData().getTo().getTime()), getData().getAllOrder(), null);
				if (storeOrders != null) {
					temp.addAll(storeOrders);
				}
			}
		}
		getData().setOrders(temp);
		initSummary();
	}

	@SuppressWarnings("deprecation")
	public void orderDateFromChange(DateSelectEvent event) {
		getData().setFrom(event.getDate());
		getData().getFrom().setHours(0);
		getData().getFrom().setMinutes(0);
		getData().getFrom().setSeconds(0);
	}

	@SuppressWarnings("deprecation")
	public void orderDateToChange(DateSelectEvent event) {
		getData().setTo(event.getDate());
		getData().getTo().setHours(23);
		getData().getTo().setMinutes(59);
		getData().getTo().setSeconds(59);
	}

	public void orderChartFromDateChange(DateSelectEvent event) {

		// getChartTo().setHours(23);
		// getChartTo().setMinutes(59);
		// getChartTo().setSeconds(59);
		// if (getChartFrom().getTime() > getChartTo().getTime()) {
		// setChartFrom(getChartTo());
		// } else if (getChartTo().getTime() > (new Date().getTime())) {
		// setChartTo(new Date());
		// } else if (getChartTo().getTime() < getChartFrom().getTime()) {
		// setChartTo(getChartFrom());
		// }
		// setChartTo(getChartTo());
		getData().setChartFrom(event.getDate());
		getData().setChartVOList(getDataForChart());
	}

	@SuppressWarnings("deprecation")
	public void orderChartDateToChange(DateSelectEvent event) {

		getData().setChartTo(event.getDate());
		getData().getChartTo().setHours(23);
		getData().getChartTo().setMinutes(59);
		getData().getChartTo().setSeconds(59);
		// if (getChartFrom().getTime() > getChartTo().getTime()) {
		// setChartFrom(getChartTo());
		// } else if (getChartTo().getTime() > (new Date().getTime())) {
		// setChartTo(new Date());
		// } else if (getChartTo().getTime() < getChartFrom().getTime()) {
		// setChartTo(getChartFrom());
		// }
		//
		getData().setChartVOList(getDataForChart());
	}

	public String showAllOrder() {
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
		 * ); return null; } } if
		 * (!getManagedUser().getUserType().equals(PARTNER)) { if (monthDiff >
		 * 12) { addErrorMessage("",
		 * "Túl nagy időintervallumot adott meg!12 hónapnál nagyobb időintervallum túl sok adatot eredményez!"
		 * ); return null; } }
		 */
		getData().setAllOrder(!getData().getAllOrder());
		if (getData().getAllOrder()) {
			List<OrderVO> temp = new ArrayList<OrderVO>();
			List<OrderVO> list = getOrderService().getOrdersByDateFiltered(getData().getVevokod(), new Timestamp(getData().getFrom().getTime()),
					new Timestamp(getData().getTo().getTime()), true, null);
			if (list != null && list.size() > 0)
				temp.addAll(list);
			if (getManagedStores() != null) {
				for (Partner partner : getManagedStores()) {
					List<OrderVO> storeOrders = getOrderService().getOrdersByDateFiltered(partner.getVevokod(), new Timestamp(getData().getFrom().getTime()),
							new Timestamp(getData().getTo().getTime()), true, null);
					if (storeOrders != null) {
						temp.addAll(storeOrders);
					}
				}
			}
			getData().setOrders(temp);
			getData().setAllOrder(true);
		} else {
			List<OrderVO> temp = getOrderService().getOrdersByDateFiltered(getData().getVevokod(), new Timestamp(getData().getFrom().getTime()),
					new Timestamp(getData().getTo().getTime()), false, null);
			if (getManagedStores() != null) {
				for (Partner partner : getManagedStores()) {
					List<OrderVO> storeOrders = getOrderService().getOrdersByDateFiltered(partner.getVevokod(), new Timestamp(getData().getFrom().getTime()),
							new Timestamp(getData().getTo().getTime()), false, null);
					if (storeOrders != null) {
						temp.addAll(storeOrders);
					}
				}
			}
			getData().setOrders(temp);
			getData().setAllOrder(false);
		}
		initSummary();
		return null;
	}

	private void initSummary() {

		if (getData().getOrders() != null) {

			Long tempBrutto = new Long(0);
			Long tempNetto = new Long(0);
			for (OrderVO order : getData().getOrders()) {
				tempBrutto += order.getDbrutto().longValue();
				tempNetto += order.getDnetto().longValue();
			}
			getData().setNetto(new BigDecimal(tempNetto));
			getData().setBrutto(new BigDecimal(tempBrutto));
		}

		else {
			getData().setNetto(new BigDecimal(0));
			getData().setBrutto(new BigDecimal(0));
		}
	}

	@SuppressWarnings("deprecation")
	public String createDataForChart() {

		Object[] objects = getOrderService().getSummaryForChart(getManagedUser(), getData().getVevokod(), null);
		if (objects != null) {
			getData().setSumIn(new BigDecimal(objects[0].toString()));
			getData().setDebitIn(new BigDecimal(objects[1].toString()));
			getData().setPayedIn(new BigDecimal(objects[2].toString()));
			getData().setExpiredIn(new BigDecimal(objects[3].toString()));
		}
		Date now = new Date();
		Date lastYear = new Date();
		lastYear.setYear(lastYear.getYear() - 1);
		lastYear.setMonth(lastYear.getMonth() + 1);
		getData().setChartFrom(lastYear);
		getData().setChartTo(now);
		getData().setChartVOList(getDataForChart());
		return null;
	}

	private List<Object[]> getDataForChart() {

		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		list = getOrderService().getOrdersInForChart(getManagedUser(), getData().getVevokod(), new Timestamp(getData().getChartFrom().getTime()),
				new Timestamp(getData().getChartTo().getTime()), null);

		if (list != null) {

			for (Object[] object : list) {

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

	private Boolean sendMail(String email, List<DataSource> sourceList, String nev) throws MessagingException {
		logger.info("Try to send e-mail");

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", LOCALHOSTHOST);
		props.setProperty("mail.user", "emailuser");
		props.setProperty("mail.password", "");

		Session mailSession = Session.getDefaultInstance(props, null);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject("Testing javamail html");

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("<h1>Check attachment</h1>", "text/html");

		int i = 0;
		List<MimeBodyPart> mime = new ArrayList<MimeBodyPart>();
		Date now = new Date();
		String today = convertDateTosString(now).replace("-", "_");
		for (DataSource source : sourceList) {

			MimeBodyPart attachFilePart = new MimeBodyPart();
			attachFilePart.setDataHandler(new DataHandler(source));
			attachFilePart.setFileName("r_" + today + "_" + i + ".pdf");
			mime.add(attachFilePart);
			i++;
		}

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(textPart);
		for (MimeBodyPart mimeBodyPart : mime) {
			mp.addBodyPart(mimeBodyPart);
		}

		message.setContent(mp);
		message.setSender(new InternetAddress(EMAIL_ADDRESS));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

		try {
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("Sending email with orders pdf is successful!");
		} catch (Exception e) {

			logger.error("Sending email with orders pdf is failed!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	private Boolean sendMailWithXLS(String email, List<DataSource> sourceList, String nev) throws MessagingException {
		logger.info("Try to send e-mail");

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", LOCALHOSTHOST);
		props.setProperty("mail.user", "emailuser");
		props.setProperty("mail.password", "");

		Session mailSession = Session.getDefaultInstance(props, null);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);

		message.setSubject("Testing javamail html");

		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("<h1>Check attachment</h1>", "text/html");

		int i = 0;
		List<MimeBodyPart> mime = new ArrayList<MimeBodyPart>();
		Date now = new Date();
		String today = convertDateTosString(now).replace("-", "_");
		for (DataSource source : sourceList) {

			MimeBodyPart attachFilePart = new MimeBodyPart();
			attachFilePart.setDataHandler(new DataHandler(source));
			attachFilePart.setFileName("r_" + today + "_" + i + ".xls");
			mime.add(attachFilePart);
			i++;
		}

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(textPart);
		for (MimeBodyPart mimeBodyPart : mime) {
			mp.addBodyPart(mimeBodyPart);
		}

		message.setContent(mp);
		message.setSender(new InternetAddress(EMAIL_ADDRESS));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

		try {
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("Sending email with orders xls is successful!");
		} catch (Exception e) {

			logger.error("Sending email with orders xls is failed!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public String showCommentForOrder() {

		try {
			String newbizazon = "r" + getData().getSelectedOrder().getSorszam().toString().replace("/", "");
			List<Bizkomment> komments = getBizkommentService().getOrderBizkomments(newbizazon);
			if (komments != null) {
				getData().setBizkomment(komments.get(0));
			} else {
				getData().setBizkomment(new Bizkomment());
			}

		} catch (Exception e) {
			logger.error("Error by getting orders for bizkomment!", e);
			return null;
		}
		return null;

	}

	public String saveCommentForOrder() {

		if (getData().getBizkomment() == null)
			return null;
		try {
			if (getData().getBizkomment().getBizazon() == null) {
				String newbizazon = "r" + getData().getSelectedOrder().getSorszam().toString().replace("/", "");
				getData().getBizkomment().setBizazon(newbizazon);
			}
			getBizkommentService().savePartnerBizkomment(getData().getBizkomment());
			addInfoMessage("A rendeléshez írt megjegyzés mentése megtörtént.", "");
		} catch (Exception e) {
			logger.error("Cannot save or update bizkomment for order", e);
			addFatalMessage("A rendeléshez írt megjegyzés mentése sikertelen volt.", "");
			return null;
		}
		return null;
	}

	public void getOrderItem(ActionEvent event) {
		if (getData().getSelectedOrder() == null) {
			getData().setOrderItems(new ArrayList<OrderItemVO>());
			return;
		}
		List<OrderItemVO> items = getOrderService().getOrderItemsByOrderId(getData().getSelectedOrder().getSorszam());
		if (items == null) {
			getData().setOrderItems(new ArrayList<OrderItemVO>());
			return;
		}
		getData().setOrderItems(items);
	}

	public void showStores(ActionEvent event) {
		if (getManagedUser().getUserType().equals(PARTNER)) {
			if (getManagedStores() != null) {
				List<Object[]> temp = new ArrayList<Object[]>();
				for (Partner partner : getManagedStores()) {
					Object[] obj = new Object[2];
					obj[0] = partner.getNev();
					obj[1] = partner.getVevokod();
					temp.add(obj);
				}
				getData().setAllPartner(temp);
				getData().setPartnerSearchText("");
				getData().setSelectedPartnerId(0);
				return;
			}
		}
	}

	public String submitChooseStore() {
		List<OrderVO> list = getOrderService().getOrdersByDateFiltered(getData().getSelectedPartnerId(), new Timestamp(getData().getFrom().getTime()),
				new Timestamp(getData().getTo().getTime()), true, null);
		if (list == null)
			return null;
		getData().setOrders(list);
		initSummary();
		Partner store = getPartnerService().getPartnerByReferenceId(getData().getSelectedPartnerId());
		if(store==null)
			return null;
		List<Date> dates = getFuvpartnerService().loadAvaibleFuvDates(getData().getSelectedPartnerId(), store.getUtvonal());
		if (dates != null) {
			setManagedFuvDates(dates);
		}
		return null;
	}

	public void resetAction(ActionEvent event) {
		loadData();
	}

	public OrderServiceData getData() {
		return data;
	}

	public void setData(OrderServiceData data) {
		this.data = data;
	}

	public OrderServiceBase getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderServiceBase orderService) {
		this.orderService = orderService;
	}

	public DocumentServiceBase getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentServiceBase documentService) {
		this.documentService = documentService;
	}

	public BizkommentServiceBase getBizkommentService() {
		return bizkommentService;
	}

	public void setBizkommentService(BizkommentServiceBase bizkommentService) {
		this.bizkommentService = bizkommentService;
	}

	public ProductServiceBase getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceBase productService) {
		this.productService = productService;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public FuvpartnerServiceBase getFuvpartnerService() {
		return fuvpartnerService;
	}

	public void setFuvpartnerService(FuvpartnerServiceBase fuvpartnerService) {
		this.fuvpartnerService = fuvpartnerService;
	}

}

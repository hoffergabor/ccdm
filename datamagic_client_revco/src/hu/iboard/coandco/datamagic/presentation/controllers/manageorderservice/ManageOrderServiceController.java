package hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Cikkcsop;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.email.EmailServiceBase;
import hu.iboard.coandco.datamagic.service.order.OrderServiceBase;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.DateSelectEvent;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "manageorderserviceController")
@RequestScoped
public class ManageOrderServiceController extends AbstractController {

	public static final String MANAGEORDERSERVICE_ACTION = "manageorder";

	@ManagedProperty(value = "#{manageorderserviceData}")
	private ManageOrderServiceData data;

	@ManagedProperty(value = "#{productService}")
	private ProductServiceBase productService;
	@ManagedProperty(value = "#{orderService}")
	private OrderServiceBase orderService;
	@ManagedProperty(value = "#{emailService}")
	private EmailServiceBase emailService;	

	@Override
	public void loadData() {
		if (getManagedUser().getUserType().equals(PARTNER)
				|| getManagedUser().getUserType().equals(SALES))
			getData().setOrderPartner(getManagedPartner());
		getData().setProducts(new ArrayList<ProductVO>());
		getData().setOrderProductList(new ArrayList<ProductVO>());
		getData().setSku(null);
		getData().setProductName(null);
		getData().setSelectedCikkcsopId(0);
		getData().setSelectedTabIndex(0);
		getData().setOrderDate(calculateMinDate());
		// getData().setCikkcsopItems(getCikkcsopItems());
		getData().setShippingMode(0);
		if (getData().getIsModifyOrder()
				&& getData().getModifyProductList().size() > 0) {
			getData().setOrderProductList(getData().getModifyProductList());
			getData().setSelectedTabIndex(1);
		}
		if (getData().getIsModifyOrder() == false
				&& getData().getModifyProductList().size() > 0) {
			getData().setOrderProductList(getData().getModifyProductList());
			getData().setSelectedTabIndex(1);
		}
		initShipping();
		initSummary();
		getData().setShowDetail(false);
		getData().setTipus("");
		//getData().setAnyagTipus(0);
		//getData().setFeluletTipus(10);
		//getData().setSzemcseMeret(0);
		getData().setKiszereles("");
		getData().setBazis("");
		getData().setSzincsalad("");
		getData().setArnyalat("");
		getData().setMeret("");
		getData().setShippingPartnerType(1);
		getData().setShippingSubContractor(null);
		getData().setShippingAtvevo(null);
		getData().setShippingPlace(false);
		getData().setShippingZipcode(null);
		getData().setShippingCity(null);
		getData().setShippingAddress(null);
		getData().setShippingTel(null);
		getData().setShippingDaruzas(null);
		getData().setShippingBehajtas(null);
		getData().setShippingCsereraklap(false);
		getData().setShippingOtherId(null);
		getData().setShippingMegj(null);
		getData().setShowNeoColor(false);
		getData().setShowNeoArnyalat(false);
		getData().setShippingType(null);
		getData().setAnyagTipusok(getProductService().getProductsAzon3());
		getData().setTermekNevek(new ArrayList<String>());
		getData().setAnyagCsoportok(new ArrayList<String>());
		getData().setMeretek(new ArrayList<String>());
		getData().setBazisok(new ArrayList<String>());
		getData().setKiszerelesek(new ArrayList<String>());
		getData().setSzincsaladok(new ArrayList<String>());
		getData().setArnyalatok(new ArrayList<String>());
		// getData().setLevel2(new ArrayList<ProductVO>());
		// getData().setLevel3(new ArrayList<ProductVO>());
		// getData().setLevel4(new ArrayList<ProductVO>());
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() != null) {
			arnev = getProductService().getArnevById(
					getData().getOrderPartner().getVevocsop().getArnevkod());
		}
		getData().setArnev(arnev);
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(MANAGEORDERSERVICE_CONTROLLER);
	}

	public void productSearchAction(ActionEvent event) {
		getData().setTipus("");
//		getData().setAnyagTipus(0);
//		getData().setFeluletTipus(10);
//		getData().setSzemcseMeret(0);
		getData().setKiszereles("");
		getData().setBazis("");
		getData().setSzincsalad("");
		getData().setArnyalat("");
				
		getData().setProducts(getProductService().getProductForSearch(getData().getSku(), getData().getProductName(), getQueryParamsVO()));
		getData().setSelectedTabIndex(0);
	}

	public void addProductToOrderAction(ActionEvent event) {

		if (getData().getSelectedProduct() == null) {
			return;
		}
		if (!getData().getOrderProductList().contains(
				getData().getSelectedProduct())) {
			getData().getSelectedProduct().setMennyiseg(new BigDecimal(1));
			getData().getOrderProductList().add(getData().getSelectedProduct());
		} else {

			for (ProductVO vo : getData().getOrderProductList()) {

				if (vo.getArukod() == getData().getSelectedProduct()
						.getArukod()) {

					vo.setMennyiseg(vo.getMennyiseg().add(new BigDecimal(1)));
				}
			}
		}
		getData().setSelectedTabIndex(1);
		initSummary();
		addInfoMessage(getData().getSelectedProduct().getMegn()
				+ " bekerült a rendelés listába.", "");
	}

	public void deleteProductItem(ActionEvent event) {
		if (getData().getSelectedProduct() != null) {
			List<ProductVO> temp = new ArrayList<ProductVO>();
			for (ProductVO vo : getData().getOrderProductList()) {
				if (vo.getArukod() != getData().getSelectedProduct()
						.getArukod()) {
					temp.add(vo);
				}
			}
			getData().setOrderProductList(temp);
			initSummary();
			// getData().setOrderProductList(getData().getOrderProductList());
			addInfoMessage(getData().getSelectedProduct().getMegn()
					+ " törölve a rendelés listából.", "");
		}
	}

	public String quantityChange() {

		getData().getOrderProductList();
		initSummary();

		return null;
	}

	private void initSummary() {

		BigDecimal tempBrutto = new BigDecimal(0);
		BigDecimal tempNetto = new BigDecimal(0);
		for (ProductVO vo : getData().getOrderProductList()) {
			tempNetto = vo.getEar().multiply(vo.getMennyiseg()).add(tempNetto);
			tempBrutto = vo.getEar().multiply(vo.getMennyiseg())
					.multiply(vo.getAfa()).add(tempBrutto);
		}

		getData().setNetto(tempNetto);
		getData().setBrutto(tempBrutto);
	}

	public String saveNewOrderaction() {
		if (getData().getIsOrderInProgress())
			return null;
		else
			getData().setIsOrderInProgress(true);
		
		Boolean result = false;
		if (getData().getOrderProductList().size() == 0) {
			addErrorMessage("", "Nincs egy termék sem a rendelésben. Kérem, válasszon ki legalább egy terméket.");
			getData().setIsOrderInProgress(false);
			return null;
		}

		try {
			Rendel order = new Rendel();
			
			order.setSirsz(getData().getOrderPartner().getIrsz());
			order.setSvaros(getData().getOrderPartner().getVaros());
			order.setScim(getData().getOrderPartner().getCim());
			order.setStelefon(getData().getOrderPartner().getTel1());
			
			List<String> megj = new ArrayList<String>();
			if (getData().getShippingType() == 1) {
				megj.add("Szállítást végzi: Revco");
				megj.add("Daruzást igényel: " + getData().getShippingDaruzas());
				megj.add("Behajtási korlátozás: " + getData().getShippingBehajtas());
				if (getData().getShippingCsereraklap())
					megj.add("Csereraklap visszaszállítás: Igen");
				else
					megj.add("Csereraklap visszaszállítás: Nem");
				megj.add("Átvevő: " + getData().getShippingAtvevo());
				megj.add("Szállítási cím: " + getData().getShippingZipcode() + " " + getData().getShippingCity() + ", " + getData().getShippingAddress() + " Tel.: " + getData().getShippingTel());
				megj.add("Megjegyzés: " + getData().getShippingMegj());
				megj.add("Egyéb azonosító: " + getData().getShippingOtherId());
				
//				order.setSirsz(getData().getShippingZipcode());
//				order.setSvaros(getData().getShippingCity());
//				order.setScim(getData().getShippingAddress());
//				order.setStelefon(getData().getShippingTel());
			}
			if (getData().getShippingType() == 2) {
//				order.setSirsz("");
//				order.setSvaros("");
//				order.setScim("");
//				order.setStelefon("");
				
				megj.add("Szállítást végzi: Partner");
				megj.add("Megjegyzés: " + getData().getShippingMegj());
				megj.add("Egyéb azonosító: " + getData().getShippingOtherId());
				
			}
			
//			String megj = "";
//			if (getData().getShippingType() == 1) {
//				megj += "Szállítást végzi: Revco\n";
//				if (getData().getShippingLerakodas())
//					megj += "Lerakodást kér: Igen\n";
//				else
//					megj += "Lerakodást kér: Igen\n";
//				megj += "Daruzást igényel:" + getData().getShippingDaruzas()
//						+ "\n";
//				megj += "Behajtási korlátozás:"
//						+ getData().getShippingBehajtas() + "\n";
//				if (getData().getShippingCsereraklap())
//					megj += "Csereraklap visszaszállítás: Igen\n";
//				else
//					megj += "Csereraklap visszaszállítás: Nem\n";
//				megj += "Megjegyzés:" + getData().getShippingMegj() + "\n";
//				megj += "Egyéb azonosító:" + getData().getShippingOtherId();
//				order.setSirsz(getData().getShippingZipcode());
//				order.setSvaros(getData().getShippingCity());
//				order.setScim(getData().getShippingAddress());
//				order.setStelefon(getData().getShippingTel());
//			}
//			if (getData().getShippingType() == 2) {
//				order.setSirsz("");
//				order.setSvaros("");
//				order.setScim("");
//				order.setStelefon("");
//				
//				megj += "Szállítást végzi: Partner\n";
//				megj += "Megjegyzés:" + getData().getShippingMegj() + "\n";
//				megj += "Egyéb azonosító:" + getData().getShippingOtherId();
//			}
			//order.setMegj(megj);
			order.setTelj(getData().getOrderDate());
			order.setEsedkelt(getData().getOrderDate());
			if (!getData().getIsModifyOrder()) {
				if (getData().getShippingMode() == 1) {
					manageShipping();
				}
				result = getOrderService().saveOrder(order,
						getData().getOrderProductList(),
						getData().getOrderPartner(), megj);
				if (result) {
					addInfoMessage("", getTextFromPropertiesFile("SAVEORDER_SAVE", MSGFILE));
					sendOrderConfirmEmail(megj, order.getSorszam());
				}
				else {
					addFatalMessage("", getTextFromPropertiesFile("SAVEORDER_FAILED", MSGFILE));
				}
			} 
			else {

				result = getOrderService().updateOrder(order,
						getData().getOrderNum(),
						getData().getOrderProductList(),
						getData().getOrderPartner());
				if (result)
					addInfoMessage(
							"",
							getTextFromPropertiesFile("MODORDER_MODIFIED",
									MSGFILE));
				else
					addFatalMessage(
							"",
							getTextFromPropertiesFile("MODORDER_FAILED",
									MSGFILE));
			}
		} catch (Exception e) {
			logger.error("Error by saving order!", e);
			addFatalMessage("",
					getTextFromPropertiesFile("SAVEORDER_FAILED", MSGFILE));
			getData().setIsOrderInProgress(false);
			return null;
		}
		getData().setIsOrderInProgress(false);
		return "orderlist";
	}

	public void sendOrderConfirmEmail(List<String> comment, String orderNumber) {
		String emailBody = "";
		String subject = "Új rendelés leadása";
		try {
			List<ProductVO> products = getData().getOrderProductList();
			emailBody += "<strong>Rendelését rendszerünk sikeresen rögzítette.</strong><br /><br /><br />";
			emailBody += String.format("A rendelés azonosítója: %s <br /><br /><br />", orderNumber);
			emailBody += "<strong>Rendelés tételei:</strong><br /><br />";
			emailBody += "<table>";
			emailBody += "<tr>";

			emailBody += "<td align='left'>";
			emailBody += "<strong>Cikkszám</strong>";
			emailBody += "</td>";
			emailBody += "<td width='5'>&nbsp;</td>";
			emailBody += "<td align='left'>";
			emailBody += "<strong>Megnevezés</strong>";
			emailBody += "</td>";
			emailBody += "<td width='5'>&nbsp;</td>";
			emailBody += "<td align='right'>";
			emailBody += "<strong>Mennyiség</strong>";
			emailBody += "</td>";
			emailBody += "</tr>";

			for (ProductVO productVO : products) {
				emailBody += "<tr>";
				emailBody += "<td>";
				emailBody += productVO.getCikkszam();
				emailBody += "</td>";
				emailBody += "<td>&nbsp;</td>";
				emailBody += "<td>";
				emailBody += productVO.getMegn();
				emailBody += "</td>";
				emailBody += "<td>&nbsp;</td>";
				emailBody += "<td align='right'>";
				emailBody += productVO.getMennyiseg();
				emailBody += "</td>";				
				emailBody += "</tr>";
			}
			

			emailBody += "</table>";
			emailBody += "<br /><br />";
			emailBody += "<strong>A rendelés egyéb adatai:</strong><br />";
			emailBody += "<br />";
			if (comment != null && comment.size() > 0) {
				for (String c : comment) {
					emailBody += c + "<br />";
				}				
			}

			emailBody += "<br /><br />";
			emailBody += "Rendelésének további adatait a <a href=\"http://revco.hu:8080/revco\" target=\"_blank\">Revco rendelési felületén</a> tudja megtekinteni.";
	
			boolean success = getEmailService().sendEmail(EMAIL_SMTP_HOST, EMAIL_SMTP_USER, EMAIL_SMTP_PASS, EMAIL_FROM, getData().getOrderPartner().getEmail(), subject, emailBody, EMAIL_SMTP_PORT);
			if (success) {
				logger.info("E-mail elküldve a következő címről: " + getData().getOrderPartner().getEmail());
			}
			else {
				addErrorMessage("", "Visszaigazoló e-mail küldése sikertelen.");
				logger.error("Order confirmation e-mail hasn't been sent!");
			}
		} catch (Exception e) {
			addErrorMessage("", "Visszaigazoló e-mail küldése sikertelen.");
			logger.error("Order confirmation e-mail hasn't been sent!");
		}
	}	

	private void initShipping() {

		getData().setFuvDijCheck(false);
		getData().setCsomDijCheck(false);
		getData().setBetetDijCheck(false);
		getData().setHaszDijCheck(false);
		getData().setBehajEnCheck(false);
		getData().setFagySzallCheck(false);
		getData().setKcrCheck(false);
		getData().setFuvaVisszaCheck(false);

		getData().setFuvDijMenny(0);
		getData().setCsomDijMenny(0);
		getData().setBetetDijMenny(0);
		getData().setHaszDijMenny(0);
		getData().setBehajEnMenny(0);
		getData().setFagySzallMenny(0);
		getData().setKcrMenny(0);
		getData().setFuvaVisszaMenny(0);

		getData().setFuvdijsum(0);
		getData().setCsomdijsum(0);
		getData().setBetdijsum(0);
		getData().setHaszdijsum(0);
		getData().setBehensum(0);
		getData().setFagysum(0);
		getData().setKcrsum(0);
		getData().setVisszasum(0);

		getData().setFullPallet(0);
		getData().setFragmentPallet(0);
	}

	private void manageShipping() {

		QueryParamsVO queryParamsVO = getQueryParamsVO();
		
		if (getData().getFuvDijCheck()) {
			ProductVO fuvDij = getProductService().getProductsById(999999101, queryParamsVO);
			fuvDij.setMenny(new BigDecimal(getData().getFuvDijMenny()));
			getData().getOrderProductList().add(fuvDij);
		}
		if (getData().getCsomDijCheck()) {
			ProductVO csomDij = getProductService().getProductsById(999999102, queryParamsVO);
			csomDij.setMenny(new BigDecimal(getData().getCsomDijMenny()));
			getData().getOrderProductList().add(csomDij);
		}
		if (getData().getBetetDijCheck()) {
			ProductVO betetDij = getProductService().getProductsById(999999103, queryParamsVO);
			betetDij.setMenny(new BigDecimal(getData().getBetetDijMenny()));
			getData().getOrderProductList().add(betetDij);
		}
		if (getData().getHaszDijCheck()) {
			ProductVO haszDij = getProductService().getProductsById(999999104, queryParamsVO);
			haszDij.setMenny(new BigDecimal(getData().getHaszDijMenny()));
			getData().getOrderProductList().add(haszDij);
		}
		if (getData().getBehajEnCheck()) {
			ProductVO behajEng = getProductService().getProductsById(999999105, queryParamsVO);
			behajEng.setMenny(new BigDecimal(getData().getBehajEnMenny()));
			getData().getOrderProductList().add(behajEng);
		}
		if (getData().getFagySzallCheck()) {
		}
		if (getData().getKcrCheck()) {
			ProductVO kcr = getProductService().getProductsById(999999107, queryParamsVO);
			kcr.setMenny(new BigDecimal(getData().getKcrMenny()));
			getData().getOrderProductList().add(kcr);
		}
		if (getData().getFuvaVisszaCheck()) {
			ProductVO fuvVissza = getProductService().getProductsById(999999108, queryParamsVO);
			fuvVissza.setMenny(new BigDecimal(getData().getFuvaVisszaMenny()));
			getData().getOrderProductList().add(fuvVissza);
		}

	}

	public void orderDateChange(DateSelectEvent event) {
		getData().setOrderDate(event.getDate());
	}

	public List<SelectItem> getCikkcsopItems() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Cikkcsop> list = getProductService().getCikkcsopForProduct();
		if (list == null)
			return new ArrayList<SelectItem>();
		for (Cikkcsop item : list) {
			selectItemList.add(new SelectItem(item.getKod(), item.getMegn()));
		}
		return selectItemList;
	}

	public void novel(ActionEvent event) {
		getData().setSummary(getData().getSummary() + 1);
	}

	public void fdCheckAction(ActionEvent event) {
		if (!getData().getFuvDijCheck())
			getData().setFuvDijCheck(true);
		else
			getData().setFuvDijCheck(false);
	}

	public void csCheckAction(ActionEvent event) {
		if (!getData().getCsomDijCheck())
			getData().setCsomDijCheck(true);
		else
			getData().setCsomDijCheck(false);
	}

	public void btCheckAction(ActionEvent event) {
		if (!getData().getBetetDijCheck())
			getData().setBetetDijCheck(true);
		else
			getData().setBetetDijCheck(false);
	}

	public void hszCheckAction(ActionEvent event) {
		if (!getData().getHaszDijCheck())
			getData().setHaszDijCheck(true);
		else
			getData().setHaszDijCheck(false);
	}

	public void heCheckAction(ActionEvent event) {
		if (!getData().getBehajEnCheck())
			getData().setBehajEnCheck(true);
		else
			getData().setBehajEnCheck(false);
	}

	public void fszCheckAction(ActionEvent event) {
		if (!getData().getFagySzallCheck())
			getData().setFagySzallCheck(true);
		else
			getData().setFagySzallCheck(false);
	}

	public void kcrCheckAction(ActionEvent event) {
		if (!getData().getKcrCheck())
			getData().setKcrCheck(true);
		else
			getData().setKcrCheck(false);
	}

	public void fvCheckAction(ActionEvent event) {
		if (!getData().getFuvaVisszaCheck())
			getData().setFuvaVisszaCheck(true);
		else
			getData().setFuvaVisszaCheck(false);
	}

	public void sModeAction(ActionEvent event) {
		if (getData().getShippingMode() == 0)
			getData().setShippingMode(1);
		else
			getData().setShippingMode(0);
	}
	
	public void resetFilter(ActionEvent event) {
//		getData().setTipus("");
//		getData().setAnyagTipus(0);
//		getData().setFeluletTipus(10);
//		getData().setSzemcseMeret(0);
//		getData().setKiszereles("");
//		getData().setBazis("");
//		getData().setSzincsalad("");
//		getData().setArnyalat("");
//		getData().setProducts(new ArrayList<ProductVO>());
		clearAllDropDownItems(true);
		getData().setProducts(new ArrayList<ProductVO>());
		getData().setAnyagTipusok(getProductService().getProductsAzon3());
		
		getData().setTipus("");
		getData().setAnyagCsoport("");
		getData().setTermekNev("");
		getData().setMeret("");
		getData().setBazis("");
		getData().setKiszereles("");
		getData().setSzincsalad("");
		getData().setArnyalat("");
		selectTipus(event);
	}	
	
	public void selectTipus(ActionEvent event) {
		clearAllChildDropDownItems();
		clearDropDownItemsForProductProperty(ProductProperty.Terméknév);
		clearDropDownItemsForProductProperty(ProductProperty.Termékcsoport);

		if (getData().getTipus() != null && !getData().getTipus().equals("")) {
			getData().setTipusIDs(getProductService().getProductTempIDsByTipus(getData().getTipus()));
		}
		else {
			getData().setTipusIDs(null);
		}
		
		getData().setTermeknevIDs(null);
		getData().setMeretIDs(null);
		getData().setBazisIDs(null);
		getData().setKiszerelesIDs(null);
		getData().setSzincsaladIDs(null);
		getData().setArnyalatIDs(null);
		getData().setCsoportIDs(null);
		
		getData().setProducts(getProductService().getProductsTempByIDs(getData().getFilteredIDsByFilterState(), getQueryParamsVO()));		
		//getData().setProducts(null);
		
		if (getData().getTipus() != null && !getData().getTipus().equals("")) {
			getData().setAnyagCsoportok(getFilterItemsByProductProperty(getData().getProducts(), ProductProperty.Termékcsoport));
		}
	}
	
	public void selectCsoport(ActionEvent event) {
		clearAllChildDropDownItems();
		clearDropDownItemsForProductProperty(ProductProperty.Terméknév);
		
		if (getData().getAnyagCsoport() != null && !getData().getAnyagCsoport().equals("")) {
			getData().setCsoportIDs(getProductService().getProductTempIDsByTermekCsoport(getData().getAnyagCsoport()));
		}
		else {
			getData().setCsoportIDs(null);
		}
		
		getData().setTermeknevIDs(null);
		getData().setMeretIDs(null);
		getData().setBazisIDs(null);
		getData().setKiszerelesIDs(null);
		getData().setSzincsaladIDs(null);
		getData().setArnyalatIDs(null);
				
		getData().setProducts(getProductService().getProductsTempByIDs(getData().getFilteredIDsByFilterState(), getQueryParamsVO()));		
		
		if (getData().getAnyagCsoport() != null && !getData().getAnyagCsoport().equals("")) {
			getData().setTermekNevek(getFilterItemsByProductProperty(getData().getProducts(), ProductProperty.Terméknév));
		}
	}	

	public void selectTermeknev(ActionEvent event){
		clearAllChildDropDownItems();
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() != null) {
			arnev = getProductService().getArnevById(getData().getOrderPartner().getVevocsop().getArnevkod());
		}
		if (arnev == null) {
			addErrorMessage("Nem található ársor a bejelentkezett felhasználóhoz.");
			return;
		}

		if (getData().getTermekNev() != null && !getData().getTermekNev().equals("")) {
			getData().setTermeknevIDs(getProductService().getProductTempIDsByTermekNev(getData().getTermekNev()));
		}
		else {
			getData().setTermeknevIDs(null);
		}
		
		getData().setMeretIDs(null);
		getData().setBazisIDs(null);
		getData().setKiszerelesIDs(null);
		getData().setSzincsaladIDs(null);
		getData().setArnyalatIDs(null);
				
		getData().setProducts(getProductService().getProductsTempByIDs(getData().getFilteredIDsByFilterState(), getQueryParamsVO()));		
		
		if (getData().getTermekNev() != null && !getData().getTermekNev().equals("")) {
			setChildDropDownsByProducts(getData().getProducts());
		}
	}

	public void selectFilter(ActionEvent event) {
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() != null) {
			arnev = getProductService().getArnevById(getData().getOrderPartner().getVevocsop().getArnevkod());
		}
		if (arnev == null) {
			addErrorMessage("Nem található ársor a bejelentkezett felhasználóhoz.");
			return;
		}

		if (getData().getMeret() != null && !getData().getMeret().equals("")) {
			getData().setMeretIDs(getProductService().getProductTempIDsByMeret(getData().getMeret()));
		}
		else {
			getData().setMeretIDs(null);
		}

		if (getData().getBazis() != null && !getData().getBazis().equals("")) {
			getData().setBazisIDs(getProductService().getProductTempIDsByBazis(getData().getBazis()));
		}
		else {
			getData().setBazisIDs(null);
		}

		if (getData().getKiszereles() != null && !getData().getKiszereles().equals("")) {
			getData().setKiszerelesIDs(getProductService().getProductTempIDsByKiszereles(getData().getKiszereles()));
		}
		else {
			getData().setKiszerelesIDs(null);
		}

		if (getData().getSzincsalad() != null && !getData().getSzincsalad().equals("")) {
			getData().setSzincsaladIDs(getProductService().getProductTempIDsBySzincsalad(getData().getSzincsalad()));
		}
		else {
			getData().setSzincsaladIDs(null);
		}

		if (getData().getArnyalat() != null && !getData().getArnyalat().equals("")) {
			getData().setArnyalatIDs(getProductService().getProductTempIDsByArnyalat(getData().getArnyalat()));
		}
		else {
			getData().setArnyalatIDs(null);
		}
		
//		if (getData().getAnyagCsoport() != null){
//				if (!getData().getAnyagCsoport().equals("")) {
//					getData().setCsoportIDs(getProductService().getProductTempIDsByTermekCsoport(getData().getAnyagCsoport()));
//				}
//				else {
//					getData().setCsoportIDs(null);
//				}
//		}
//		else {
//			getData().setCsoportIDs(null);
//		}
		
	
		getData().setProducts(getProductService().getProductsTempByIDs(getData().getFilteredIDsByFilterState(), getQueryParamsVO()));
		//setDropDownsByProducts(getData().getProducts());
	}

	private void setChildDropDownsByProducts(List<ProductVO> products) {
		clearAllChildDropDownItems();

		if (products == null || products.size() < 1)
			return;
				
		for (ProductProperty property : ProductProperty.values())
			if (!parentDropDowns.contains(property))
				setDropDownItemsForProductProperty(getFilterItemsByProductProperty(products, property), property);
	}

	public enum ProductProperty {
		Anyagtípus,
		Termékcsoport,
		Terméknév,
		Méret,
		Bázis,
		Kiszerelés,
		Színcsalád,
		Árnyalat
	}
	
	private List<ProductProperty> parentDropDowns = new ArrayList<ProductProperty>() {
		{ add(ProductProperty.Anyagtípus); add(ProductProperty.Termékcsoport); add(ProductProperty.Terméknév); }
	};

	private List<String> getFilterItemsByProductProperty(List<ProductVO> products, ProductProperty productProperty){
		List<String> result = new ArrayList<String>(); 
		for (ProductVO vo : products) {
			switch (productProperty) {
			case Anyagtípus:
				if (!result.contains(vo.getAzon3())) { 
					result.add(vo.getAzon3()); 
					} 				
				break;
			case Termékcsoport:
				if (!result.contains(vo.getAzon4())) { 
					result.add(vo.getAzon4()); 
					} 				
				break;
			case Terméknév:
				if (!result.contains(vo.getAzon5())) { 
					result.add(vo.getAzon5()); 
					} 				
				break;
			case Méret:
				if (!result.contains(vo.getAzon8())) { 
					result.add(vo.getAzon8()); 
					} 				
				break;
			case Bázis:
				if (!result.contains(vo.getAzon9())) { 
					result.add(vo.getAzon9()); 
					} 				
				break;
			case Kiszerelés:
				if (!result.contains(vo.getAzon10())) { 
					result.add(vo.getAzon10()); 
					} 				
				break;
			case Színcsalád:
				if (!result.contains(vo.getAzon11())) { 
					result.add(vo.getAzon11()); 
					} 				
				break;
			case Árnyalat:
				if (!result.contains(vo.getAzon12())) { 
					result.add(vo.getAzon12()); 
					} 				
				break;
				
			default:
				break;
			}
		}
		Collections.sort(result);	
		return result;
	}

	private void setDropDownItemsForProductProperty(List<String> items, ProductProperty productProperty){
		if (!parentDropDowns.contains(productProperty) && items.size() < 2 ){
			return;
		}
			
		switch (productProperty) {
		case Anyagtípus:
			getData().setAnyagTipusok(items);
			break;
		case Bázis:
			getData().setBazisok(items);
			break;
		case Kiszerelés:
			getData().setKiszerelesek(items);
			break;
		case Méret:
			getData().setMeretek(items);
			break;
		case Színcsalád:
			getData().setSzincsaladok(items);
			break;
		case Termékcsoport:
			getData().setAnyagCsoportok(items);
			break;
		case Terméknév:
			getData().setTermekNevek(items);
			break;
		case Árnyalat:
			getData().setArnyalatok(items);
			break;
		default:
			break;
		}
	}

	private void clearDropDownItemsForProductProperty(ProductProperty productProperty){
		switch (productProperty) {
		case Anyagtípus:
			getData().getAnyagTipusok().clear();
			getData().setTipus("");
			break;
		case Bázis:
			getData().getBazisok().clear();
			getData().setBazis("");
			break;
		case Kiszerelés:
			getData().getKiszerelesek().clear();
			getData().setKiszereles("");
			break;
		case Méret:
			getData().getMeretek().clear();
			getData().setMeret("");
			break;
		case Színcsalád:
			getData().getSzincsaladok().clear();
			getData().setSzincsalad("");
			break;
		case Termékcsoport:
			getData().getAnyagCsoportok().clear();
			getData().setAnyagCsoport("");
			break;
		case Terméknév:
			getData().getTermekNevek().clear();
			getData().setTermekNev("");
			break;
		case Árnyalat:
			getData().getArnyalatok().clear();
			getData().setArnyalat("");
			break;
		default:
			break;
		}
	}	
	
	private void clearAllChildDropDownItems(){
		clearAllDropDownItems(false);
	}
	
	private void clearAllDropDownItems(boolean includeParentItems) {
		for (ProductProperty property : ProductProperty.values()) {
			if (!includeParentItems && !parentDropDowns.contains(property))
				clearDropDownItemsForProductProperty(property);
			else if (includeParentItems) {
				clearDropDownItemsForProductProperty(property);
			}
		}
	}
	
	/*
	 * public void selectTipus(ActionEvent event) { if
	 * (getData().getTipus().equals("")) { addErrorMessage("",
	 * "Kérem válasszon anyagtípust"); return; } Arnev arnev = null; if
	 * (getData().getOrderPartner().getVevocsop().getArnevkod() != null) { arnev
	 * =
	 * getProductService().getArnevById(getData().getOrderPartner().getVevocsop
	 * ().getArnevkod()); if (arnev != null) {
	 * getData().setProducts(getProductService().getProductsTempByAzon("azon3",
	 * getData().getTipus(), arnev));
	 * getData().setLevel2(getData().getProducts()); } } if
	 * (getData().getProducts() != null) { List<String> azon4 = new
	 * ArrayList<String>(); for (ProductVO vo : getData().getProducts()) { if
	 * (!azon4.contains(vo.getAzon4())) { azon4.add(vo.getAzon4()); } }
	 * Collections.sort(azon4); getData().setAnyagCsoportok(azon4); } }
	 * 
	 * public void selectCsoport(ActionEvent event) { if
	 * (getData().getAnyagCsoport().equals("")) { addErrorMessage("",
	 * "Kérem válasszon anyagcsoportot"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel2()) { if
	 * (vo.getAzon4().equals(getData().getAnyagCsoport())) { temp.add(vo); } }
	 * getData().setProducts(temp); getData().setLevel3(temp); if
	 * (getData().getProducts() != null) { List<String> azon5 = new
	 * ArrayList<String>(); for (ProductVO vo : getData().getProducts()) { if
	 * (!azon5.contains(vo.getAzon5())) { azon5.add(vo.getAzon5()); }
	 * 
	 * } Collections.sort(azon5); getData().setTermekNevek(azon5); }
	 * getData().setMeretek(null); getData().setBazisok(null);
	 * getData().setKiszerelesek(null); getData().setSzincsaladok(null);
	 * getData().setArnyalatok(null); }
	 * 
	 * public void selectTermeknev(ActionEvent event) { if
	 * (getData().getTermekNev().equals("")) { addErrorMessage("",
	 * "Kérem válasszon terméknevet"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel3()) { if
	 * (vo.getAzon5().equals(getData().getTermekNev())) { temp.add(vo); } }
	 * getData().setProducts(temp); getData().setLevel4(temp); if
	 * (getData().getProducts() != null) { List<String> azon8 = new
	 * ArrayList<String>(); List<String> azon9 = new ArrayList<String>();
	 * List<String> azon10 = new ArrayList<String>(); List<String> azon11 = new
	 * ArrayList<String>(); List<String> azon12 = new ArrayList<String>(); for
	 * (ProductVO vo : getData().getProducts()) { if (!vo.getAzon8().equals("")
	 * && !azon8.contains(vo.getAzon8())) { azon8.add(vo.getAzon8()); } if
	 * (!vo.getAzon9().equals("") && !azon9.contains(vo.getAzon9())) {
	 * azon9.add(vo.getAzon9()); } if (!vo.getAzon10().equals("") &&
	 * !azon10.contains(vo.getAzon10())) { azon10.add(vo.getAzon10()); } if
	 * (!vo.getAzon11().equals("") && !azon11.contains(vo.getAzon11())) {
	 * azon11.add(vo.getAzon11()); } if (!vo.getAzon12().equals("") &&
	 * !azon12.contains(vo.getAzon12())) { azon12.add(vo.getAzon12()); }
	 * 
	 * } Collections.sort(azon8); Collections.sort(azon9);
	 * Collections.sort(azon10); Collections.sort(azon11);
	 * Collections.sort(azon12); if (azon8 != null) getData().setMeretek(azon8);
	 * if (azon9 != null) getData().setBazisok(azon9); if (azon10 != null)
	 * getData().setKiszerelesek(azon10); if (azon11 != null)
	 * getData().setSzincsaladok(azon11); if (azon12 != null)
	 * getData().setArnyalatok(azon12); } }
	 * 
	 * public void selectMeret(ActionEvent event) { if
	 * (getData().getMeret().equals("")) { addErrorMessage("",
	 * "Kérem válasszon méretet!"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel4()) { if
	 * (vo.getAzon8().equals(getData().getMeret())) { temp.add(vo); } }
	 * getData().setProducts(temp); }
	 * 
	 * public void selectBazis(ActionEvent event) { if
	 * (getData().getBazis().equals("")) { addErrorMessage("",
	 * "Kérem válasszon méretet!"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel4()) { if
	 * (vo.getAzon9().equals(getData().getBazis())) { temp.add(vo); } }
	 * getData().setProducts(temp); }
	 * 
	 * public void selectKiszereles(ActionEvent event) { if
	 * (getData().getKiszereles().equals("")) { addErrorMessage("",
	 * "Kérem válasszon kiszerelést!"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel4()) { if
	 * (vo.getAzon10().equals(getData().getKiszereles())) { temp.add(vo); } }
	 * getData().setProducts(temp); }
	 * 
	 * public void selectSzincsalad(ActionEvent event) { if
	 * (getData().getSzincsalad().equals("")) { addErrorMessage("",
	 * "Kérem válasszon kiszerelést!"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel4()) { if
	 * (vo.getAzon11().equals(getData().getSzincsalad())) { temp.add(vo); } }
	 * getData().setProducts(temp); }
	 * 
	 * public void selectArnyalat(ActionEvent event) { if
	 * (getData().getArnyalat().equals("")) { addErrorMessage("",
	 * "Kérem válasszon kiszerelést!"); return; } List<ProductVO> temp = new
	 * ArrayList<ProductVO>(); for (ProductVO vo : getData().getLevel4()) { if
	 * (vo.getAzon12().equals(getData().getArnyalat())) { temp.add(vo); } }
	 * getData().setProducts(temp); }
	 */
	public void searchAdvanced(ActionEvent event) {

	}

	public void changeQuantity(ActionEvent event) {
		initSummary();
	}

	public void chanegShippingType(ValueChangeEvent event) {
		getData().setShippingType((Integer) event.getNewValue());
	}

	public void raklapAction(ValueChangeEvent event) {
		getData().setShippingCsereraklap((Boolean) event.getNewValue());
	}

	public void behajtasAction(ValueChangeEvent event) {
		getData().setShippingBehajtas((String) event.getNewValue());
	}

	public void daruzasAction(ValueChangeEvent event) {
		getData().setShippingDaruzas((String) event.getNewValue());
	}

	public List<String> completeAtvevo(String input) {
		List<String> names = new ArrayList<String>();
		if (getManagedPartner().getNev() != null
				&& !getManagedPartner().getNev().equals(""))
			names.add(getManagedPartner().getNev());
		if (getManagedPartner().getPukapcs() != null
				&& !getManagedPartner().getPukapcs().equals(""))
			names.add(getManagedPartner().getPukapcs());
		if (getManagedPartner().getKapcstart() != null
				&& !getManagedPartner().getKapcstart().equals(""))
			names.add(getManagedPartner().getKapcstart());
		if (getManagedPartner().getUgyvezeto() != null
				&& !getManagedPartner().getUgyvezeto().equals(""))
			names.add(getManagedPartner().getUgyvezeto());
		return names;
	}

	public void changeShippingPlace(ActionEvent event) {
		if (getData().getShippingPlace()) {
			getData().setShippingZipcode(getData().getOrderPartner().getIrsz());
			getData().setShippingCity(getData().getOrderPartner().getVaros());
			getData().setShippingAddress(getData().getOrderPartner().getCim());
			getData().setShippingTel(getData().getOrderPartner().getTel1());
		} else {
			getData().setShippingZipcode(null);
			getData().setShippingCity(null);
			getData().setShippingAddress(null);
			getData().setShippingTel(null);
		}
	}

	public String onFlowProcess(FlowEvent event) {
		if (event.getNewStep().equals("shippingTab")) {
			if (getData().getOrderProductList().size() > 0)
				return event.getNewStep();
			else {
				addErrorMessage("", "A rendeléshez kérem válasszon terméket!");
				return event.getOldStep();
			}
		}
		if (event.getNewStep().equals("summaryTab")) {
			if (getData().getShippingType() != null) {
				return event.getNewStep();
			} else {
				addErrorMessage("",
						"A rendeléshez kérem válasszon a szállítási lehetőségek közül!");
				return event.getOldStep();
			}
		}
		return event.getNewStep();
	}

	private Date calculateMinDate() {
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.HOUR_OF_DAY) < 12) {
			cal.add(Calendar.DATE, 2);
		} else {
			cal.add(Calendar.DATE, 3);
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			cal.add(Calendar.DATE, 2);
		}
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			cal.add(Calendar.DATE, 1);
		}
		return cal.getTime();

	}

	public ManageOrderServiceData getData() {
		return data;
	}

	public void setData(ManageOrderServiceData data) {
		this.data = data;
	}

	public ProductServiceBase getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceBase productService) {
		this.productService = productService;
	}

	public OrderServiceBase getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderServiceBase orderService) {
		this.orderService = orderService;
	}

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

}

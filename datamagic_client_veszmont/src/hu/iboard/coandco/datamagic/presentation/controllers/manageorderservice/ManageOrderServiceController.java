package hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Cikkcsop;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.order.OrderServiceBase;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.DateSelectEvent;

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

	@Override
	public void loadData() {
		if (getManagedUser().getUserType().equals(PARTNER) || getManagedUser().getUserType().equals(SALES))
			getData().setOrderPartner(getManagedPartner());
		getData().setProducts(new ArrayList<ProductVO>());
		getData().setOrderProductList(new ArrayList<ProductVO>());
		getData().setSku(null);
		getData().setProductName(null);
		getData().setSelectedCikkcsopId(0);
		getData().setSelectedTabIndex(0);
		getData().setOrderDate(new Timestamp(System.currentTimeMillis()));
		// getData().setCikkcsopItems(getCikkcsopItems());
		getData().setShippingMode(0);
		initPaydate();
		if (getData().getIsModifyOrder() && getData().getModifyProductList().size() > 0) {
			getData().setOrderProductList(getData().getModifyProductList());
			getData().setSelectedTabIndex(1);
		}
		if (getData().getIsModifyOrder() == false && getData().getModifyProductList().size() > 0) {
			getData().setOrderProductList(getData().getModifyProductList());
			getData().setSelectedTabIndex(1);
		}
		initSummary();
		initPaydate();
		getData().setShowDetail(false);
		getData().setCategoryId1(null);
		getData().setCategoryId2(null);
		getData().setCategoryId3(null);
		getData().setCategoryId4(null);
		getData().setShippingName(getManagedPartner().getNev());
		getData().setZipCode(getManagedPartner().getIrsz());
		getData().setCity(getManagedPartner().getVaros());
		getData().setAddress(getManagedPartner().getCim());
		if (getData().getAllCikkcsops().size() == 0)
			getData().setAllCikkcsops(getProductService().getAllCikkcsopObjs());
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(MANAGEORDERSERVICE_CONTROLLER);
	}

	public String productSearchAction() {
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == 0)
			arnev = getProductService().getArnevById(1);
		else
			arnev = getData().getOrderPartner().getVevocsop().getArnev();

		getData().setProducts(getProductService().getProductForSearch("", getData().getSku(), null));
		getData().setSelectedTabIndex(0);
		return null;
	}

	public void addProductToOrderAction(ActionEvent event) {

		if (getData().getSelectedProduct() == null) {
			return;
		}
		if (!getData().getOrderProductList().contains(getData().getSelectedProduct())) {
			getData().getSelectedProduct().setMennyiseg(new BigDecimal(1));
			getData().getOrderProductList().add(getData().getSelectedProduct());
		} else {

			for (ProductVO vo : getData().getOrderProductList()) {

				if (vo.getArukod() == getData().getSelectedProduct().getArukod()) {

					vo.setMennyiseg(vo.getMennyiseg().add(new BigDecimal(1)));
				}
			}
		}
		getData().setSelectedTabIndex(1);
		initSummary();
		addInfoMessage(getData().getSelectedProduct().getMegn() + " BEKERÜLT A RENDELÉS LISTÁBA!", "");
	}

	public void deleteProductItem(ActionEvent event) {

		if (getData().getSelectedProduct() != null && getData().getOrderProductList().contains(getData().getSelectedProduct())) {

			getData().getOrderProductList().remove(getData().getSelectedProduct());
			initSummary();
			getData().setOrderProductList(getData().getOrderProductList());
			addInfoMessage(getData().getSelectedProduct().getMegn() + " TOROLVE A RENDELES LISTABOL!", "");
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
			tempBrutto = vo.getEar().multiply(vo.getMennyiseg()).multiply(vo.getAfa()).add(tempBrutto);
		}

		getData().setNetto(tempNetto);
		getData().setBrutto(tempBrutto);
	}

	public void initPaydate() {

		Calendar now = Calendar.getInstance();
		now.setTime(getData().getOrderDate());
		if (getData().getOrderPartner() != null)
			now.add(Calendar.DATE, getData().getOrderPartner().getFizhal());
		getData().setPayDate(now.getTime());

	}

	public String saveNewOrderaction() {

		Boolean result = false;
		if (getData().getOrderProductList().size() == 0) {
			addErrorMessage("", "RENDELES TÉTEL ÜRES!");
			return null;
		}
		if (getData().getShippingOption() == null || getData().getShippingOption() == 0) {
			addErrorMessage("", "KÉRJÜK ADJA MEG A SZÁLLÍTÁST!");
			return null;
		}
		if (getData().getPayOption() == null || getData().getPayOption() == 0) {
			addErrorMessage("", "KÉRJÜK ADJA MEG A FIZETÉST!");
			return null;
		}

		try {
			Rendel rendel = new Rendel();
			if (getData().getShippingOption() == 1) {
				rendel.setSirsz(getData().getZipCode());
				rendel.setSvaros(getData().getCity());
				rendel.setScim(getData().getAddress());
				rendel.setSnev(getData().getShippingName());
				rendel.setMegj(getData().getComment());
			}
			if (getData().getShippingOption() == 2) {
				rendel.setSirsz("");
				rendel.setSvaros("");
				rendel.setScim("");
				rendel.setSnev("");
				rendel.setMegj("");
			}

			if (getManagedPartner() != null) {
				rendel.setVnev(getManagedPartner().getNev());
				rendel.setVirsz(getManagedPartner().getIrsz());
				rendel.setVvaros(getManagedPartner().getVaros());
				rendel.setVcim(getManagedPartner().getCim());
				rendel.setStelefon(getManagedPartner().getTel1());
				rendel.setVadoszam(getManagedPartner().getAdoszam());
				rendel.setLakosvevo(0);
				rendel.setVevokod(getManagedPartner().getVevokod());
				// TODO
				rendel.setStatus(255);
			}
			if (getData().getPayOption() == 1)
				rendel.setFizmod("Utánvét");
			if (getData().getPayOption() == 2)
				rendel.setFizmod("Átutalás");
			if (getData().getPayOption() == 3)
				rendel.setFizmod("Készpénz");
			if (!getData().getIsModifyOrder()) {
				result = getOrderService().saveOrder(rendel, getData().getOrderProductList(), getData().getOrderPartner());
				if (result)
					addInfoMessage("", getTextFromPropertiesFile("SAVEORDER_SAVE", MSGFILE));
				else
					addErrorMessage("", getTextFromPropertiesFile("SAVEORDER_FAILED", MSGFILE));
			} else {

				result = getOrderService().updateOrder(rendel, getData().getOrderNum(), getData().getOrderProductList(), getData().getOrderPartner());
				if (result)
					addInfoMessage("", getTextFromPropertiesFile("MODORDER_MODIFIED", MSGFILE));
				else
					addErrorMessage("", getTextFromPropertiesFile("MODORDER_FAILED", MSGFILE));
			}
			addInfoMessage("", "Rendelését sikeresen rögzítettük!");
		} catch (Exception e) {
			logger.error("Error by saving order!", e);
			addErrorMessage("", getTextFromPropertiesFile("SAVEORDER_FAILED", MSGFILE));
			return null;
		}
		return null;
	}

	public void orderDateChange(DateSelectEvent event) {

		getData().setOrderDate(event.getDate());
		initPaydate();
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

	public void sModeAction(ActionEvent event) {
		if (getData().getShippingMode() == 0)
			getData().setShippingMode(1);
		else
			getData().setShippingMode(0);
	}

	public void searchAdvanced(ActionEvent event) {

		String cikkszam = "";
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() != null)
			arnev = getData().getOrderPartner().getVevocsop().getArnev();
		getData().setProducts(getProductService().getProductForAdvancedSearch("", null));

	}

	private void getChildren(BigDecimal kod) {
		if (kod.toString().length() >= 4) {
			for (Object[] cikkcsop : getData().getAllCikkcsops()) {
				if (cikkcsop[2].equals(kod)) {
					getData().getCikkcsops().add(cikkcsop);
					getChildren((BigDecimal) cikkcsop[0]);
				}
			}
		}
	}

	public String showProductsLevel1() {
		getData().setCikkcsops(new ArrayList<Object[]>());
		getData().setLevel2(new ArrayList<Object[]>());
		getData().setLevel3(new ArrayList<Object[]>());
		getData().setLevel4(new ArrayList<Object[]>());
		getData().setProducts(new ArrayList<ProductVO>());
		if (getData().getCategoryId1() == null) {
			return null;
		}
		getChildren(new BigDecimal(getData().getCategoryId1()));

		for (Object[] object : getData().getCikkcsops()) {
			if (object[2].toString().equals(getData().getCategoryId1().toString())) {
				getData().getLevel2().add(object);
			}
		}
		List<BigDecimal> ids = new ArrayList<BigDecimal>();

		for (Object[] obj : getData().getCikkcsops()) {
			ids.add((BigDecimal) obj[0]);
		}
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == 0)
			arnev = getProductService().getArnevById(1);
		else
			arnev = getData().getOrderPartner().getVevocsop().getArnev();
		if (ids != null && ids.size() > 0)
			getData().getProducts().addAll(getProductService().getProductsByCsopkods(null, null));
		return null;
	}

	public String showProductsLevel2() {
		getData().setCikkcsops(new ArrayList<Object[]>());
		getData().setLevel3(new ArrayList<Object[]>());
		getData().setLevel4(new ArrayList<Object[]>());
		getData().setProducts(new ArrayList<ProductVO>());
		if (getData().getCategoryId2() == null) {
			return null;
		}
		getChildren(new BigDecimal(getData().getCategoryId2()));
		for (Object[] object : getData().getCikkcsops()) {
			if (object[2].toString().equals(getData().getCategoryId2().toString())) {
				getData().getLevel3().add(object);
			}
		}
		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		if (getData().getCikkcsops().size() > 0) {
			for (Object[] obj : getData().getCikkcsops()) {
				ids.add((BigDecimal) obj[0]);
			}
		} else {
			Cikkcsop cikk = getProductService().getCikkcsopById(new BigDecimal(getData().getCategoryId2()));
			if (cikk != null)
				ids.add(cikk.getKod());
		}
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == 0)
			arnev = getProductService().getArnevById(1);
		else
			arnev = getData().getOrderPartner().getVevocsop().getArnev();
		getData().getProducts().addAll(getProductService().getProductsByCsopkods(null, null));
		return null;
	}

	public String showProductsLevel3() {
		getData().setCikkcsops(new ArrayList<Object[]>());
		getData().setLevel4(new ArrayList<Object[]>());
		getData().setProducts(new ArrayList<ProductVO>());
		if (getData().getCategoryId3() == null) {
			return null;
		}
		getChildren(new BigDecimal(getData().getCategoryId3()));
		for (Object[] object : getData().getCikkcsops()) {
			if (object[2].toString().equals(getData().getCategoryId3().toString())) {
				getData().getLevel4().add(object);
			}
		}
		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		if (getData().getCikkcsops().size() > 0) {
			for (Object[] obj : getData().getCikkcsops()) {
				ids.add((BigDecimal) obj[0]);
			}
		} else {
			Cikkcsop cikk = getProductService().getCikkcsopById(new BigDecimal(getData().getCategoryId3()));
			if (cikk != null)
				ids.add(cikk.getKod());
		}
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == 0)
			arnev = getProductService().getArnevById(1);
		else
			arnev = getData().getOrderPartner().getVevocsop().getArnev();

		getData().getProducts().addAll(getProductService().getProductsByCsopkods(null, null));
		return null;
	}

	public String showProductsLevel4() {
		getData().setCikkcsops(new ArrayList<Object[]>());
		getData().setProducts(new ArrayList<ProductVO>());
		if (getData().getCategoryId4() == null) {
			return null;
		}

		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		ids.add(new BigDecimal(getData().getCategoryId4()));
		Arnev arnev = null;
		if (getData().getOrderPartner().getVevocsop() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == null)
			return null;
		if (getData().getOrderPartner().getVevocsop().getArnevkod() == 0)
			arnev = getProductService().getArnevById(1);
		else
			arnev = getData().getOrderPartner().getVevocsop().getArnev();
		getData().getProducts().addAll(getProductService().getProductsByCsopkods(null, null));
		return null;
	}

	public void changeQuantity(ActionEvent event) {
		initSummary();
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

}

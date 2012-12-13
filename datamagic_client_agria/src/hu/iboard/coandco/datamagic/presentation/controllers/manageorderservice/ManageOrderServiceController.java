package hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice;

import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.Cikkcsop;
import hu.iboard.coandco.datamagic.generated.Kedvezmeny;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Tapadogon;
import hu.iboard.coandco.datamagic.generated.Vevoarak;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.presentation.util.CompareAruMegn3;
import hu.iboard.coandco.datamagic.presentation.util.TableFilterComparator;
import hu.iboard.coandco.datamagic.service.order.OrderServiceBase;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.service.wishlistservice.WishlistService;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
	@ManagedProperty(value = "#{wishlistService}")
	private WishlistService wishlistService;

	@Override
	public void loadData() {
		/*
		 * if (getManagedUser().getUserType().equals(PARTNER) ||
		 * getManagedUser().getUserType().equals(SALES))
		 * getData().setOrderPartner(getManagedPartner());
		 */
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
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
		getData().setShowDetail(false);
		getData().setCategoryId1(null);
		getData().setCategoryId2(null);
		getData().setCategoryId3(null);
		getData().setCategoryId4(null);
		getData().setShippingName(getData().getOrderPartner().getNev());
		getData().setZipCode(getData().getOrderPartner().getIrsz());
		getData().setCity(getData().getOrderPartner().getVaros());
		getData().setAddress(getData().getOrderPartner().getCim());
		getData().setMinDate(initMinDate());
		getData().setShipDate(getData().getMinDate());
		getData().setSelectedFuvDate(0);
		if (getManagedFuvDates() != null) {
			getData().setHasFuvDates(true);
			getData().setFuvDates(getFuvDatesItems());
		} else
			getData().setHasFuvDates(false);
		initPaydate();
		initSummary();
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

		getData().setProducts(filterProducts(getProductService().getProductForSearch(getData().getSku(), getData().getProductName(), getQueryParamsVO())));
		if (getData().getProducts() == null || getData().getProducts().size() == 0)
			return null;
		getKedvezmenyesAruk();
		/*
		 * List<ProductVO> aruk = cikkcsopMegnSearch(getData().getProductName(),
		 * arnev); if (getData().getProducts() != null) { List<ProductVO> temp =
		 * getData().getProducts(); if (aruk != null) { for (ProductVO aru :
		 * aruk) { boolean contains = containsAru(temp, aru); if (!contains)
		 * getData().getProducts().add(aru); } } } else {
		 * getData().setProducts(aruk); }
		 */
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
		getData().getDataTable().setFirst(0);
		return null;

	}

	private void getKedvezmenyesAruk() {
		List<Vevoarak> vevoarak = getProductService().getActualVevoarakForPartner(getData().getOrderPartner().getVevokod());
		if (vevoarak != null && vevoarak.size() != 0) {
			for (Vevoarak vevoar : vevoarak) {
				Integer arukod = vevoar.getAkod() + 3000000;
				Aru aru = getProductService().getAruById(arukod);
				if (aru != null) {
					if (containsAndRemoveAru(getData().getProducts(), arukod)) {
						ProductVO vo = getProductService().convertAruToProductVO(aru, getQueryParamsVO());
						vo.setNetto(vevoar.getEgysegar() != null ? vevoar.getEgysegar() : new BigDecimal(0));
						vo.setEar(vevoar.getEgysegar().multiply(
								new BigDecimal(1).add(new BigDecimal(aru.getAfakodok().getFordkulcs()).divide(new BigDecimal(100)))));
						// vo.setBrutto(vevoar.getEgysegar().multiply(
						// new BigDecimal(1).add(new
						// BigDecimal(aru.getAfakodok().getFordkulcs()).divide(new
						// BigDecimal(100)))));
						vo.setBrutto(vo.getEar());
						getData().getProducts().add(vo);
					}
				}
			}
		}
		getData().setCurrentPage(1);
	}

	private boolean containsAndRemoveAru(List<ProductVO> aruk, Integer arukod) {
		for (ProductVO product : aruk) {
			if (product.getArukod() == arukod) {
				aruk.remove(product);
				return true;
			}
		}
		return false;
	}

	// private boolean isContainsAru(List<ProductVO> aruk, Integer arukod) {
	// for (ProductVO product : aruk) {
	// if (product.getArukod() == arukod) {
	// return true;
	// }
	// }
	// return false;
	// }

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
					getData().getSelectedProduct().setMennyiseg(vo.getMennyiseg());
				}
			}
		}
		/*
		 * List<ProductVO> gongyolegs =
		 * getTapadoGongyoleg(getData().getSelectedProduct()); if (gongyolegs !=
		 * null) { for (ProductVO gon : gongyolegs) { boolean isContain =
		 * containsGongyoleg(gon); if (isContain) { for (ProductVO vo :
		 * getData().getOrderProductList()) { if (vo.getArukod() ==
		 * gon.getArukod()) { vo.setMennyiseg(vo.getMennyiseg().add(new
		 * BigDecimal(1))); } } } else {
		 * getData().getOrderProductList().add(gon); } } } List<ProductVO>
		 * ntGongyolegs = getNemTapadoGongyoleg(getData().getSelectedProduct());
		 * if (ntGongyolegs != null && ntGongyolegs.size() > 0) { boolean
		 * isContain = containsGongyoleg(ntGongyolegs.get(0)); if (isContain) {
		 * ProductVO rem = removableGongyoleg(ntGongyolegs.get(0)); if (rem !=
		 * null) getData().getOrderProductList().remove(rem); }
		 * getData().getOrderProductList().add(ntGongyolegs.get(0)); }
		 */
		initSummary();
		addInfoMessage(getData().getSelectedProduct().getMegn() + " bekerült a rendeléslistába.", "");
	}

	private boolean containsGongyoleg(ProductVO vo, List<ProductVO> temp) {

		for (ProductVO prod : temp) {
			if (vo.getArukod() == prod.getArukod()) {
				return true;
			}
		}
		return false;
	}

	// private ProductVO removableGongyoleg(ProductVO vo) {
	//
	// for (ProductVO prod : getData().getOrderProductList()) {
	// if (vo.getArukod() == prod.getArukod()) {
	// return prod;
	// }
	// }
	// return null;
	// }

	public void deleteProductItem(ActionEvent event) {

		if (getData().getSelectedProduct() != null && getData().getOrderProductList().contains(getData().getSelectedProduct())) {
			getData().getOrderProductList().remove(getData().getSelectedProduct());
			initSummary();
			getData().setOrderProductList(getData().getOrderProductList());
			addInfoMessage(getData().getSelectedProduct().getMegn() + " törölve a rendeléslistából.", "");
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
		if (getData().getShipDate() == null)
			return;
		now.setTime(getData().getShipDate());
		if (getData().getOrderPartner() != null)
			now.add(Calendar.DATE, getData().getOrderPartner().getFizhal());
		getData().setPayDate(now.getTime());

	}

	public String saveNewOrderaction() {

		Boolean result = false;
		if (getData().getOrderProductList().size() == 0) {
			addErrorMessage("", "Nincsnek tételek a rendelésben. Kérem, vegyen fel rendelendő tételeket.");
			return null;
		}
		if (getData().getShippingOption() == null || getData().getShippingOption() == 0) {
			addErrorMessage("", "Kérem, adja meg a szálltás módját.");
			return null;
		}

		try {
			Rendel rendel = new Rendel();

			if (getData().getShippingOption() == 1) {
				rendel.setFuvmod("Házhozszállítás");
			}

			else if (getData().getShippingOption() == 2) {
				rendel.setFuvmod("Telephely átvétel");
			}
			// if (getData().getShippingOption() == 1) {
			// rendel.setSirsz(getData().getZipCode());
			// rendel.setSvaros(getData().getCity());
			// rendel.setScim(getData().getAddress());
			// rendel.setSnev(getData().getShippingName());
			// rendel.setMegj(getData().getComment());
			// }
			// if (getData().getShippingOption() == 2) {
			// rendel.setSirsz("");
			// rendel.setSvaros("");
			// rendel.setScim("");
			// rendel.setSnev("");
			// rendel.setMegj("");
			// }
			// szerver oldal csinálja. csinálta. de most már ez itt alul...

			if (getData().getOrderPartner() != null) {
				rendel.setVnev(getData().getOrderPartner().getNev());
				rendel.setVirsz(getData().getOrderPartner().getIrsz());
				rendel.setVvaros(getData().getOrderPartner().getVaros());
				rendel.setVcim(getData().getOrderPartner().getCim());
				rendel.setStelefon(getData().getOrderPartner().getTel1());
				rendel.setVadoszam(getData().getOrderPartner().getAdoszam());
				rendel.setLakosvevo(0);
				rendel.setVevokod(getData().getOrderPartner().getVevokod());
				rendel.setFizmod(getData().getOrderPartner().getFizmod());

				rendel.setSnev(getData().getOrderPartner().getSznev() != null && !getData().getOrderPartner().getSznev().isEmpty() ? getData()
						.getOrderPartner().getSznev() : getData().getOrderPartner().getNev());
				rendel.setSvaros(getData().getOrderPartner().getSzvaros() != null && !getData().getOrderPartner().getSzvaros().isEmpty() ? getData()
						.getOrderPartner().getSzvaros() : getData().getOrderPartner().getVaros());
				rendel.setScim(getData().getOrderPartner().getSzcim() != null && !getData().getOrderPartner().getSzcim().isEmpty() ? getData()
						.getOrderPartner().getSzcim() : getData().getOrderPartner().getCim());
				rendel.setSirsz(getData().getOrderPartner().getSzirsz() != null && !getData().getOrderPartner().getSzirsz().isEmpty() ? getData()
						.getOrderPartner().getSzirsz() : getData().getOrderPartner().getIrsz());

				// TODO
				rendel.setStatus(255);
			}
			rendel.setTelj(getData().getShipDate());
			rendel.setMegj(getData().getContactName());

			if (!getData().getIsModifyOrder()) {
				getData().getOrderProductList().removeAll(getData().getTapadoGongyoleg());
				getData().getOrderProductList().removeAll(getData().getNemTapadoGongyoleg());

				List<String> comment = new ArrayList<String>();
				if (getData().getComment() != null)
					comment = Arrays.asList(getData().getComment().split("\n"));

				result = getOrderService().saveOrder(rendel, getData().getOrderProductList(), getData().getOrderPartner(), comment);
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
			
//			if (result)
//				addInfoMessage("", "Rendelését sikeresen rögzítettük.");

		} catch (Exception e) {
			logger.error("Error by saving order!", e);
			addErrorMessage("", getTextFromPropertiesFile("SAVEORDER_FAILED", MSGFILE));
			return null;
		}
		return null;
	}

	public void orderDateChange(DateSelectEvent event) {

		getData().setShipDate(event.getDate());
		initPaydate();
	}

	public void orderDateChange2(ValueChangeEvent event) {
		if (event.getNewValue() == null || (Long) event.getNewValue() == 0) {
			getData().setShipDate(null);
			return;
		}
		long time = (Long) event.getNewValue();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(time);
		getData().setShipDate(cal.getTime());
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
		getData().setProducts(filterProducts(getProductService().getProductForAdvancedSearch(cikkszam + "%", getQueryParamsVO())));
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);

	}

	private void getChildren(BigDecimal kod) {
		if (kod.toString().length() >= 4) {
			for (Object[] cikkcsop : getData().getAllCikkcsops()) {
				if (cikkcsop[2].equals(kod) && !getProductService().isCikkcsopEmpty((BigDecimal) cikkcsop[0])) {
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
			if (object[2].toString().equals(getData().getCategoryId1().toString()) && !getProductService().isCikkcsopEmpty((BigDecimal) object[0])) {
				getData().getLevel2().add(object);
			}
		}
		List<BigDecimal> ids = new ArrayList<BigDecimal>();

		for (Object[] obj : getData().getCikkcsops()) {
			ids.add((BigDecimal) obj[0]);
		}

		if (ids != null && ids.size() > 0)
			getData().getProducts().addAll(filterProducts(getProductService().getProductsByCsopkods(ids, getQueryParamsVO())));
		getKedvezmenyesAruk();
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
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
			if (object[2].toString().equals(getData().getCategoryId2().toString()) && !getProductService().isCikkcsopEmpty((BigDecimal) object[0])) {
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

		getData().getProducts().addAll(filterProducts(getProductService().getProductsByCsopkods(ids, getQueryParamsVO())));
		getKedvezmenyesAruk();
		getData().setCurrentPage(1);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
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
			if (object[2].toString().equals(getData().getCategoryId3().toString()) && !getProductService().isCikkcsopEmpty((BigDecimal) object[0])) {
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

		getData().getProducts().addAll(filterProducts(getProductService().getProductsByCsopkods(ids, getQueryParamsVO())));
		getKedvezmenyesAruk();
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
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

		getData().getProducts().addAll(filterProducts(getProductService().getProductsByCsopkods(ids, getQueryParamsVO())));
		getKedvezmenyesAruk();
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
		return null;
	}

	public String onFlowProcess(FlowEvent event) {
		if (event.getNewStep().equals("shippingTab")) {
			if (getData().getTapadoGongyoleg() != null && getData().getTapadoGongyoleg().size() > 0)
				getData().getOrderProductList().removeAll(getData().getTapadoGongyoleg());
			if (getData().getNemTapadoGongyoleg() != null && getData().getNemTapadoGongyoleg().size() > 0)
				getData().getOrderProductList().removeAll(getData().getNemTapadoGongyoleg());
			if (getData().getOrderProductList().size() > 0)
				return event.getNewStep();
			else {
				addErrorMessage("", "A rendeléshez, kérem, válasszon terméket.");
				return event.getOldStep();
			}
		}
		/*
		 * if (event.getNewStep().equals("paymentTab")) { if
		 * (getData().getShippingOption() != 0) { return event.getNewStep(); }
		 * else { addErrorMessage("",
		 * "A rendeléshez kérem válasszon a szállítási lehetőségek közül!");
		 * return event.getOldStep(); } }
		 */

		Date minDate = initMinDate();

		if (event.getNewStep().equals("summaryTab")) {
			getData().setTapadoGongyoleg(new ArrayList<ProductVO>());
			getData().setNemTapadoGongyoleg(new ArrayList<ProductVO>());
			if (getData().getShipDate() == null) {
				addErrorMessage("", "Kérem, válassza ki az igénylés időpontját!");
				return event.getOldStep();
			}

			// int results = d1.compareTo(d2);
			// if(results > 0)
			// System.out.println("First Date is after second");
			// else if (results < 0)
			// System.out.println("First Date is before second");
			// else
			// System.out.println("Both dates are equal");

			else if (getData().getShipDate().before(minDate)) {
				addErrorMessage("", String.format("Nem adhat meg %s előtti dátumot.", convertDateTosString(minDate)));
				return event.getOldStep();
			}
			if (getData().getShippingOption() != 0) {
				List<ProductVO> tapadoTemp = new ArrayList<ProductVO>();
				List<ProductVO> nemTapadoTemp = new ArrayList<ProductVO>();
				tapadoTemp = calculateTapadoGongyoleg();
				nemTapadoTemp = calculateNemTapadoGongyoleg();
				if (tapadoTemp != null && tapadoTemp.size() > 0) {
					getData().setTapadoGongyoleg(tapadoTemp);
					getData().getOrderProductList().addAll(tapadoTemp);
				}
				if (nemTapadoTemp != null && nemTapadoTemp.size() > 0) {
					getData().setNemTapadoGongyoleg(nemTapadoTemp);
					getData().getOrderProductList().addAll(nemTapadoTemp);
				}
				initSummary();
				return event.getNewStep();
			} else {
				addErrorMessage("", "Kérem, válasszon a szállítási lehetőségek közül!");
				return event.getOldStep();
			}
		}
		return event.getNewStep();
	}

	private List<ProductVO> calculateTapadoGongyoleg() {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		for (ProductVO prod : getData().getOrderProductList()) {
			List<ProductVO> gongyolegs = getTapadoGongyoleg(prod);
			if (gongyolegs != null && gongyolegs.size() > 0) {
				ProductVO gon = gongyolegs.get(0);
				boolean isContain = containsGongyoleg(gon, temp);
				if (isContain) {
					for (ProductVO vo : temp) {
						if (vo.getArukod() == gon.getArukod()) {
							vo.setMennyiseg(vo.getMennyiseg().add(prod.getMennyiseg()));
						}
					}
				} else {
					gon.setMennyiseg(prod.getMennyiseg());
					temp.add(gon);
				}
			}
		}
		return temp;
	}

	private List<ProductVO> calculateNemTapadoGongyoleg() {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		for (ProductVO prod : getData().getOrderProductList()) {
			List<ProductVO> gongyolegs = getNemTapadoGongyoleg2(prod);
			if (gongyolegs != null && gongyolegs.size() > 0) {
				ProductVO gon = gongyolegs.get(0);
				boolean isContain = containsGongyoleg(gon, temp);
				if (isContain) {
					for (ProductVO vo : temp) {
						if (vo.getArukod() == gon.getArukod()) {
							vo.setMennyiseg(vo.getMennyiseg().add(gon.getMennyiseg()));
						}
					}
				} else {
					if (gon.getMennyiseg().compareTo(new BigDecimal(0)) == 1)
						temp.add(gon);
				}
			}
		}
		return temp;
	}

	private List<ProductVO> filterProducts(List<ProductVO> products) {
		if (products == null)
			return null;
		List<ProductVO> temp = new ArrayList<ProductVO>();
		for (ProductVO vo : products) {
			if (vo.getRaktar() == 3 && !vo.getPassziv()) {
				temp.add(vo);
			}
		}
		return products;
	}

	private List<ProductVO> getTapadoGongyoleg(ProductVO product) {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		List<Tapadogon> gongys = getGongyoleg(product);
		if (gongys == null)
			return null;
		for (Tapadogon gongy : gongys) {
			if (gongy.getIstapado()) {
				if (getData().getOrderPartner().getVevocsop() != null && getData().getOrderPartner().getVevocsop().getArnev() != null) {
					ProductVO vo = getProductService().getProductsById(gongy.getArukod() + 2000000, getQueryParamsVO());
					if (vo != null)
						temp.add(vo);
				}
			}
		}
		return temp;
	}

	// private List<ProductVO> getNemTapadoGongyoleg(ProductVO product) {
	// List<ProductVO> temp = new ArrayList<ProductVO>();
	// List<Tapadogon> gongys = getGongyoleg(product);
	// if (gongys == null)
	// return null;
	// for (Tapadogon gongy : gongys) {
	// if (!gongy.getIstapado()) {
	// Aru aru = getProductService().getAruById(product.getArukod());
	// if (aru != null && aru.getCsopkod() != null) {
	// Cikkcsop cikkcsop = getProductService().getCikkcsopById(new
	// BigDecimal(aru.getCsopkod()));
	// if (cikkcsop != null && cikkcsop.getAutgongy()) {
	// BigDecimal egesz =
	// product.getMennyiseg().divideToIntegralValue(gongy.getSzorzo());
	// BigDecimal maradek = product.getMennyiseg().remainder(gongy.getSzorzo());
	// Integer arukod = (gongy.getArukod() - 1000000) + (product.getRaktar() *
	// 1000000);
	// ProductVO vo = getProductService().getProductsById(arukod,
	// getQueryParamsVO());
	// if (vo != null) {
	// if (maradek.compareTo(gongy.getSzorzo().divide(new BigDecimal(2))) >= 0)
	// {
	// vo.setMennyiseg(egesz.add(new BigDecimal(1)));
	// temp.add(vo);
	// }
	// }
	// }
	// }
	// }
	// }
	// return temp;
	// }

	private List<ProductVO> getNemTapadoGongyoleg2(ProductVO product) {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		List<Tapadogon> gongys = getGongyoleg(product);
		if (gongys == null)
			return null;
		for (Tapadogon gongy : gongys) {
			if (!gongy.getIstapado()) {
				Aru aru = getProductService().getAruById(product.getArukod());
				if (aru != null && aru.getCsopkod() != null) {
					Cikkcsop cikkcsop = getProductService().getCikkcsopById(new BigDecimal(aru.getCsopkod()));
					if (cikkcsop != null && cikkcsop.getAutgongy()) {
						BigDecimal egesz = product.getMennyiseg().divideToIntegralValue(gongy.getSzorzo());
						BigDecimal maradek = product.getMennyiseg().remainder(gongy.getSzorzo());
						Integer arukod = (gongy.getArukod() - 1000000) + (product.getRaktar() * 1000000);
						ProductVO vo = getProductService().getProductsById(arukod, getQueryParamsVO());
						if (vo != null) {
							vo.setMennyiseg(egesz);
							if (maradek.compareTo(gongy.getSzorzo().divide(new BigDecimal(2))) >= 0) {
								vo.setMennyiseg(vo.getMennyiseg().add(new BigDecimal(1)));
							}
							temp.add(vo);
						}
					}
				}
			}
		}
		return temp;
	}

	private List<Tapadogon> getGongyoleg(ProductVO product) {
		Integer akod = product.getArukod() - (product.getRaktar() * 1000000);
		List<Tapadogon> gongys = getProductService().getTapadogonByAkod(akod);
		if (gongys == null)
			return null;
		return gongys;
	}

	public void changeQuantity(ActionEvent event) {
		/*
		 * List<ProductVO> gongyolegs =
		 * getTapadoGongyoleg(getData().getSelectedProduct()); if (gongyolegs !=
		 * null) { for (ProductVO gon : gongyolegs) { boolean isContain =
		 * containsGongyoleg(gon); if (isContain) { for (ProductVO vo :
		 * getData().getOrderProductList()) { if (vo.getArukod() ==
		 * gon.getArukod()) {
		 * vo.setMennyiseg(getData().getSelectedProduct().getMennyiseg()); } } }
		 * else { getData().getOrderProductList().add(gon); } } }
		 * List<ProductVO> ntGongyolegs =
		 * getNemTapadoGongyoleg2(getData().getSelectedProduct()); if
		 * (ntGongyolegs != null && ntGongyolegs.size() > 0) { boolean isContain
		 * = containsGongyoleg(ntGongyolegs.get(0)); if (isContain) { ProductVO
		 * rem = removableGongyoleg(ntGongyolegs.get(0)); if (rem != null)
		 * getData().getOrderProductList().remove(rem); } if
		 * (ntGongyolegs.get(0).getMennyiseg().compareTo(new BigDecimal(0)) > 0)
		 * { getData().getOrderProductList().add(ntGongyolegs.get(0)); } }
		 */
		initSummary();
	}

	public void getUniquePriceProducts(ActionEvent event) {
		List<Kedvezmeny> kedvezmenyek = getProductService().getAktualisKedvezmeny();
		if (kedvezmenyek == null)
			return;
		List<Vevoarak> vevoarak = new ArrayList<Vevoarak>();
		for (Kedvezmeny kedv : kedvezmenyek) {
			List<Vevoarak> arak = getProductService().getVevoarakByKedvezmenyForPartner(kedv.getId(), getData().getOrderPartner().getVevokod());
			if (arak != null && arak.size() > 0)
				vevoarak.addAll(arak);
		}
		if (vevoarak == null || vevoarak.size() == 0)
			return;
		List<ProductVO> products = new ArrayList<ProductVO>();
		for (Vevoarak vevoar : vevoarak) {
			Integer arukod = vevoar.getAkod() + 3000000;
			Aru aru = getProductService().getAruById(arukod);
			if (aru != null) {
				ProductVO vo = getProductService().convertAruToProductVO(aru, getQueryParamsVO());
				vo.setNetto(vevoar.getEgysegar() != null ? vevoar.getEgysegar() : new BigDecimal(0));
				vo.setEar(vevoar.getEgysegar().multiply(new BigDecimal(1).add(new BigDecimal(aru.getAfakodok().getFordkulcs()).divide(new BigDecimal(100)))));
				vo.setBrutto(vevoar.getEgysegar().multiply(new BigDecimal(1).add(new BigDecimal(aru.getAfakodok().getFordkulcs()).divide(new BigDecimal(100)))));
				products.add(vo);
			}
		}
		getData().setProducts(products);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
	}

	public void getAkcioProducts(ActionEvent event) {
		List<ProductVO> aruk = getProductService().getAkciosProducts(getQueryParamsVO());
		if (aruk == null)
			return;
		getData().setProducts(aruk);
		Collections.sort(getData().getProducts(), new CompareAruMegn3());
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
	}

	public List<SelectItem> getFuvDatesItems() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Date> list = getManagedFuvDates();
		if (list == null)
			return new ArrayList<SelectItem>();
		for (Date item : list) {
			if (item.compareTo(getData().getMinDate()) >= 0) {
				selectItemList.add(new SelectItem(item.getTime(), getDayByDate(item) + ", " + convertDateTosString2(item)));
			}
		}
		return selectItemList;
	}

	@SuppressWarnings("deprecation")
	private Date initMinDate() {
		Date now = new Date();
		Calendar date = Calendar.getInstance();
		date.set(Calendar.MILLISECOND, 0);

		if (now.getHours() < 12) {
			date.add(Calendar.DATE, 1);
		} else {
			date.add(Calendar.DATE, 2);
		}

		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date.add(Calendar.DATE, 2);
		}
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date.add(Calendar.DATE, 1);
		}
		now = date.getTime();
		now.setHours(0);
		now.setMinutes(0);
		now.setSeconds(0);
		// getData().setShipDate(now);
		getData().setOrderDate(now);
		return now;
	}

	// private List<ProductVO> cikkcsopMegnSearch(String megn, Arnev arnev) {
	// List<ProductVO> aruk =
	// getProductService().getProductsByCikkcsopMegn(megn, getQueryParamsVO());
	// return aruk;
	// }

	// private boolean containsAru(List<ProductVO> aruk, ProductVO aru) {
	// for (ProductVO product : aruk) {
	// if (product.getArukod() == aru.getArukod()) {
	// return true;
	// }
	// }
	// return false;
	// }

	public void pageFirst() {
		getData().getDataTable().setFirst(0);
		getData().setCurrentPage(1);
	}

	public void pagePrevious() {
		if (getData().getCurrentPage() == 1)
			return;
		getData().getDataTable().setFirst(getData().getDataTable().getFirst() - getData().getDataTable().getRows());
		getData().setCurrentPage(getData().getCurrentPage() - 1);
	}

	public void pageNext(ActionEvent event) {
		if (getData().getCurrentPage() == getTotalPages())
			return;
		getData().getDataTable().setFirst(getData().getDataTable().getFirst() + getData().getDataTable().getRows());
		getData().setCurrentPage(getData().getCurrentPage() + 1);
	}

	public void pageLast() {
		int count = getData().getDataTable().getRowCount();
		int rows = getData().getDataTable().getRows();
		getData().getDataTable().setFirst(count - ((count % rows != 0) ? count % rows : rows));
		getData().setCurrentPage(getCurrentPage());
	}

	public int getCurrentPage() {
		int rows = getData().getDataTable().getRows();
		int first = getData().getDataTable().getFirst();
		int count = getData().getDataTable().getRowCount();
		return (count / rows) - ((count - first) / rows) + 1;
	}

	public int getTotalPages() {
		int rows = getData().getDataTable().getRows();
		int count = getData().getDataTable().getRowCount();
		return (count / rows) + ((count % rows != 0) ? 1 : 0);
	}

	public void changeCurrentPage() {
		if (getData().getCurrentPage() < 1)
			getData().setCurrentPage(1);
		if (getData().getCurrentPage() > getTotalPages())
			getData().setCurrentPage(getTotalPages());
		int rows = getData().getDataTable().getRows();
		getData().getDataTable().setFirst(rows * (getData().getCurrentPage() - 1));
	}

	public void sortDataList(ActionEvent event) {
		String sortFieldAttribute = getAttribute(event, "sortField");

		if (getData().getSortField() != null && getData().getSortField().equals(sortFieldAttribute)) {
			getData().setSortAscending(!getData().isSortAscending());
		} else {
			getData().setSortField(sortFieldAttribute);
			getData().setSortAscending(true);
		}

		if (getData().getSortField() != null) {
			Collections.sort(getData().getProducts(), new TableFilterComparator(getData().getSortField(), getData().isSortAscending()));
		}
	}

	private static String getAttribute(ActionEvent event, String name) {
		return (String) event.getComponent().getAttributes().get(name);
	}

	public void showWishlist() {
		List<WishlistDist> wishlist = getWishlistService().getWishlistsByPartner(getData().getOrderPartner().getVevokod());
		if (wishlist == null)
			return;
		getData().setWishlist(wishlist);
	}

	public void showWishlistItems(ActionEvent event) {
		if (getData().getSelectedWishlistId() == null)
			return;

		List<WishlistItemDist> items = getWishlistService().getWishlistItemsByWishlistId(getData().getSelectedWishlistId());
		if (items == null || items.size() == 0)
			return;
		for (WishlistItemDist item : items) {
			ProductVO product = getProductService().convertAruTempToProductVO(item.getAru(), getQueryParamsVO());
			product.setMennyiseg(item.getQuantity());
			if (getData().getOrderProductList().size() == 0) {
				getData().getOrderProductList().add(product);
			} else {
				for (ProductVO vo : getData().getOrderProductList()) {
					if (containsAndRemoveAru(getData().getOrderProductList(), product.getArukod())) {
						vo.setMennyiseg(vo.getMennyiseg().add(product.getMennyiseg()));
						product.setMennyiseg(vo.getMennyiseg());
						break;
					} else {
						getData().getOrderProductList().add(product);
						break;
					}
				}
			}
		}
		initSummary();
	}

	public void saveWishlist() {
		List<ProductVO> products = getData().getOrderProductList();
		if (products != null && products.size() > 0) {
			saveWishlistInternal(products, getData().getOrderPartner());
		}
	}

	private void saveWishlistInternal(List<ProductVO> products, Partner partner) {
		Boolean result = getWishlistService().saveWishlist(products, partner, convertDateTimeTosString(new Date()));
		if (result != null && result == false) {
			logger.error("An error has been occured during the saving of the wishlist.");
			addErrorMessage("", "Hiba történt a kosár mentésénél");
		} else {
			addInfoMessage("A kosár mentésre került");
		}
	}

	public void deleteWishlist() {
		try {
			if (getData().getSelectedWishlistId() != null) {
				getWishlistService().deleteWishlistAndItemsById(getData().getSelectedWishlistId());
				if (getData().getWishlist() == null || getData().getSelectedWishlistId() == null)
					return;
				List<WishlistDist> temp = getData().getWishlist();
				for (WishlistDist wishlist : temp) {
					if (wishlist.getWishlistId() == getData().getSelectedWishlistId()) {
						temp.remove(wishlist);
						break;
					}
				}
				getData().setWishlist(temp);
			}
		} catch (Exception e) {
			addErrorMessage("Hiba történt a kosár törlése közben.");
			logger.error("An error has been occured during deleting a wishlist.");
		}
		addInfoMessage("A kosár törlésre került.");
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

	public WishlistService getWishlistService() {
		return wishlistService;
	}

	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}
}

package hu.iboard.coandco.datamagic.presentation.controllers.productservice;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

@ManagedBean(name = "productserviceController")
@RequestScoped
public class ProductServiceController extends AbstractController {

	public static final String PRODUCTSERVICE_ACTION = "productlist";

	@ManagedProperty(value = "#{productserviceData}")
	private ProductServiceData data;

	@ManagedProperty(value = "#{productService}")
	private ProductServiceBase productService;

	@Override
	public void loadData() {

		getData().setSku(null);
		getData().setProductName(null);
		getData().setProducts(new ArrayList<ProductVO>());
		// getData().setCikkcsopItems(getCikkcsopItems());
		getData().setSelectedCikkcsopId(0);
		getData().setTipus("");
		getData().setAnyagTipus(0);
		getData().setFeluletTipus(10);
		getData().setSzemcseMeret(0);
		getData().setKiszereles(0);
		getData().setBazis("");
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(PRODUCTSERVICE_CONTROLLER);

	}

	public String productSearchAction() {
		getData().setProducts(getProductService().getProductForSearch(getData().getSku(), getData().getProductName(),getQueryParamsVO()));
		return null;
	}

	// public List<SelectItem> getCikkcsopItems() {
	// List<SelectItem> selectItemList = new ArrayList<SelectItem>();
	// List<Cikkcsop> list = getProductService().getCikkcsopForProduct();
	// if (list == null)
	// return new ArrayList<SelectItem>();
	// for (Cikkcsop item : list) {
	// selectItemList.add(new SelectItem(item.getKod(), item.getMegn()));
	// }
	// return selectItemList;
	// }

	public void showFeluletTipus(ActionEvent event) {
		getData().setFeluletTipusok(new ArrayList<SelectItem>());
		getData().setSzemcseMeretek(new ArrayList<SelectItem>());
		if (getData().getAnyagTipus() == 1) {
			getData().getFeluletTipusok().add(new SelectItem(0, "Beltéri"));
			getData().getFeluletTipusok().add(new SelectItem(2, "Kültéri"));
			getData().getSzemcseMeretek().add(new SelectItem(7, "szilikon"));
			getData().getSzemcseMeretek().add(new SelectItem(9, "szilikát"));
		}
		if (getData().getAnyagTipus() == 2) {
			getData().getFeluletTipusok().add(new SelectItem(1, "Struktúra"));
			getData().getFeluletTipusok().add(new SelectItem(2, "Spachtel"));
			getData().getFeluletTipusok().add(new SelectItem(3, "Roll putz"));
		}
		if (getData().getAnyagTipus() == 0) {
			getData().setFeluletTipus(10);
			getData().setSzemcseMeret(0);
		}
		getData().setFeluletTipus(10);
	}

	public void showSzemcseMeret(ActionEvent event) {
		getData().setSzemcseMeretek(new ArrayList<SelectItem>());
		if (getData().getAnyagTipus() == 2 && getData().getFeluletTipus() == 1) {
			getData().getSzemcseMeretek().add(new SelectItem(2, "2 mm"));
			getData().getSzemcseMeretek().add(new SelectItem(3, "3 mm"));
			getData().getSzemcseMeretek().add(new SelectItem(5, "gold"));
			getData().getSzemcseMeretek().add(new SelectItem(7, "szilikon"));
			getData().getSzemcseMeretek().add(new SelectItem(9, "szilikát"));
		}
		if (getData().getAnyagTipus() == 2 && getData().getFeluletTipus() == 2) {
			getData().getSzemcseMeretek().add(new SelectItem(1, "1 mm"));
			getData().getSzemcseMeretek().add(new SelectItem(2, "1,5 mm"));
			getData().getSzemcseMeretek().add(new SelectItem(3, "2,5 mm"));
			getData().getSzemcseMeretek().add(new SelectItem(4, "szórt"));
			getData().getSzemcseMeretek().add(new SelectItem(5, "gold"));
			getData().getSzemcseMeretek().add(new SelectItem(7, "szilikon"));
			getData().getSzemcseMeretek().add(new SelectItem(9, "szilikát"));
		}
		if (getData().getAnyagTipus() == 1) {
			getData().getSzemcseMeretek().add(new SelectItem(7, "szilikon"));
			getData().getSzemcseMeretek().add(new SelectItem(9, "szilikát"));
		}
		getData().setSzemcseMeret(0);
	}

	public void searchAdvanced(ActionEvent event) {

		String cikkszam = "";
		if (!getData().getTipus().equals(""))
			cikkszam = cikkszam + getData().getTipus();
		else
			cikkszam = cikkszam + "_";
		if (getData().getAnyagTipus() != 0)
			cikkszam = cikkszam + getData().getAnyagTipus();
		else
			cikkszam = cikkszam + "_";
		if (getData().getFeluletTipus() != 10)
			cikkszam = cikkszam + getData().getFeluletTipus();
		else
			cikkszam = cikkszam + "_";
		if (getData().getSzemcseMeret() != 0)
			cikkszam = cikkszam + getData().getSzemcseMeret();
		else
			cikkszam = cikkszam + "_";
		if (getData().getKiszereles() != 0)
			cikkszam = cikkszam + getData().getKiszereles();
		else
			cikkszam = cikkszam + "_";
		if (!getData().getBazis().equals(""))
			cikkszam = cikkszam + getData().getBazis();
		else
			cikkszam = cikkszam + "__";

		getData().setProducts(getProductService().getProductForAdvancedSearch(cikkszam + "%",getQueryParamsVO()));

	}

	public ProductServiceData getData() {
		return data;
	}

	public void setData(ProductServiceData data) {
		this.data = data;
	}

	public ProductServiceBase getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceBase productService) {
		this.productService = productService;
	}

}

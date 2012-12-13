package hu.iboard.coandco.datamagic.presentation.controllers.productservice;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "productserviceData")
@SessionScoped
public class ProductServiceData implements Serializable {

	private static final long serialVersionUID = 3722913765063501194L;

	private List<ProductVO> products = new ArrayList<ProductVO>();
	private String sku = null;
	private String productName = null;

	private String tipus;
	private Integer anyagTipus;
	private Integer feluletTipus;
	private Integer szemcseMeret;
	private Integer kiszereles;
	private String bazis;

	private List<SelectItem> feluletTipusok = new ArrayList<SelectItem>();
	private List<SelectItem> szemcseMeretek = new ArrayList<SelectItem>();

	private Integer selectedCikkcsopId = null;
	private List<SelectItem> cikkcsopItems = new ArrayList<SelectItem>();

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getSelectedCikkcsopId() {
		return selectedCikkcsopId;
	}

	public void setSelectedCikkcsopId(Integer selectedCikkcsopId) {
		this.selectedCikkcsopId = selectedCikkcsopId;
	}

	public List<SelectItem> getCikkcsopItems() {
		return cikkcsopItems;
	}

	public void setCikkcsopItems(List<SelectItem> cikkcsopItems) {
		this.cikkcsopItems = cikkcsopItems;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public Integer getAnyagTipus() {
		return anyagTipus;
	}

	public void setAnyagTipus(Integer anyagTipus) {
		this.anyagTipus = anyagTipus;
	}

	public Integer getFeluletTipus() {
		return feluletTipus;
	}

	public void setFeluletTipus(Integer feluletTipus) {
		this.feluletTipus = feluletTipus;
	}

	public Integer getSzemcseMeret() {
		return szemcseMeret;
	}

	public void setSzemcseMeret(Integer szemcseMeret) {
		this.szemcseMeret = szemcseMeret;
	}

	public Integer getKiszereles() {
		return kiszereles;
	}

	public void setKiszereles(Integer kiszereles) {
		this.kiszereles = kiszereles;
	}

	public String getBazis() {
		return bazis;
	}

	public void setBazis(String bazis) {
		this.bazis = bazis;
	}

	public List<SelectItem> getFeluletTipusok() {
		return feluletTipusok;
	}

	public void setFeluletTipusok(List<SelectItem> feluletTipusok) {
		this.feluletTipusok = feluletTipusok;
	}

	public List<SelectItem> getSzemcseMeretek() {
		return szemcseMeretek;
	}

	public void setSzemcseMeretek(List<SelectItem> szemcseMeretek) {
		this.szemcseMeretek = szemcseMeretek;
	}

}

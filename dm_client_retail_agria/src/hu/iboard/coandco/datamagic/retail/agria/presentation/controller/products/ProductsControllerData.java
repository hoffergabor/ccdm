package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.products;

import hu.iboard.coandco.datamagic.model.bizkomment.Bizkomment;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "productsControllerData")
@SessionScoped
public class ProductsControllerData implements Serializable {

	private static final long serialVersionUID = 4754004852755864239L;

	private List<ProductVO> products = new ArrayList<ProductVO>();
	private ProductVO selectedProduct;
	private String productName;
	private BigDecimal categoryId;
	private BigDecimal category2Id;
	private BigDecimal category3Id;
	private BigDecimal category4Id;
	private Boolean search = false;
	private List<Object[]> cikkcsops = new ArrayList<Object[]>();
	private String mainMenuLabel;
	private List<Object[]> allCikkcsops = new ArrayList<Object[]>();
	private String menu;
	private Integer productId;
	private ProductVO product;
	private Boolean showProductGrid;
	private Boolean showProductList;
	private BigDecimal searchCikkcsop;
	private List<ProductVO> searchProducts = new ArrayList<ProductVO>();
	private String mainMenu;
	private Bizkomment productBizkomment;
	private BigDecimal Kedvezmeny = BigDecimal.ZERO;

	public BigDecimal getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(BigDecimal categoryId) {
		this.categoryId = categoryId;
	}

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public ProductVO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductVO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public Boolean getSearch() {
		return search;
	}

	public void setSearch(Boolean search) {
		this.search = search;
	}

	public List<Object[]> getCikkcsops() {
		return cikkcsops;
	}

	public void setCikkcsops(List<Object[]> cikkcsops) {
		this.cikkcsops = cikkcsops;
	}

	public String getMainMenuLabel() {
		return mainMenuLabel;
	}

	public void setMainMenuLabel(String mainMenuLabel) {
		this.mainMenuLabel = mainMenuLabel;
	}

	public List<Object[]> getAllCikkcsops() {
		return allCikkcsops;
	}

	public void setAllCikkcsops(List<Object[]> allCikkcsops) {
		this.allCikkcsops = allCikkcsops;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public BigDecimal getCategory2Id() {
		return category2Id;
	}

	public void setCategory2Id(BigDecimal category2Id) {
		this.category2Id = category2Id;
	}

	public BigDecimal getCategory3Id() {
		return category3Id;
	}

	public void setCategory3Id(BigDecimal category3Id) {
		this.category3Id = category3Id;
	}

	public BigDecimal getCategory4Id() {
		return category4Id;
	}

	public void setCategory4Id(BigDecimal category4Id) {
		this.category4Id = category4Id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public ProductVO getProduct() {
		return product;
	}

	public void setProduct(ProductVO product) {
		this.product = product;
	}

	public Boolean getShowProductGrid() {
		return showProductGrid;
	}

	public void setShowProductGrid(Boolean showProductGrid) {
		this.showProductGrid = showProductGrid;
	}

	public Boolean getShowProductList() {
		return showProductList;
	}

	public void setShowProductList(Boolean showProductList) {
		this.showProductList = showProductList;
	}

	public BigDecimal getSearchCikkcsop() {
		return searchCikkcsop;
	}

	public void setSearchCikkcsop(BigDecimal searchCikkcsop) {
		this.searchCikkcsop = searchCikkcsop;
	}

	public List<ProductVO> getSearchProducts() {
		return searchProducts;
	}

	public void setSearchProducts(List<ProductVO> searchProducts) {
		this.searchProducts = searchProducts;
	}

	public String getMainMenu() {
		return mainMenu;
	}

	public void setMainMenu(String mainMenu) {
		this.mainMenu = mainMenu;
	}

	public Bizkomment getProductBizkomment() {
		return productBizkomment;
	}

	public void setProductBizkomment(Bizkomment productBizkomment) {
		this.productBizkomment = productBizkomment;
	}

	public void setKedvezmeny(BigDecimal kedvezmeny){
		this.Kedvezmeny = kedvezmeny;
	}
	
	public BigDecimal getKedvezmeny(){
		return this.Kedvezmeny;
	}

}

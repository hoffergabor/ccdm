package hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice;

import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

@ManagedBean(name = "manageorderserviceData")
@SessionScoped
public class ManageOrderServiceData implements Serializable {

	private static final long serialVersionUID = -6105102800747573827L;

	private List<ProductVO> products = new ArrayList<ProductVO>();
	private List<ProductVO> orderProductList = new ArrayList<ProductVO>();
	private List<ProductVO> tapadoGongyoleg = new ArrayList<ProductVO>();
	private List<ProductVO> nemTapadoGongyoleg = new ArrayList<ProductVO>();
	private ProductVO selectedProduct = null;
	private String sku = null;
	private String productName = null;
	private String vcsopkodName = null;
	private Integer selectedTabIndex;
	private BigDecimal netto = new BigDecimal(0);
	private BigDecimal brutto = new BigDecimal(0);
	private Date orderDate;
	private Date payDate;
	private Date shipDate;
	private Boolean isModifyOrder = false;
	private List<ProductVO> modifyProductList = new ArrayList<ProductVO>();
	private String orderNum;
	private HtmlDataTable dataTable = new HtmlDataTable();
	private String sortField = null;
    private boolean sortAscending = true;
    private int currentPage;

	private List<SelectItem> cikkcsopItems = new ArrayList<SelectItem>();
	private Integer selectedCikkcsopId = null;

	private List<Object[]> cikkcsops = new ArrayList<Object[]>();
	private List<Object[]> allCikkcsops = new ArrayList<Object[]>();

	private Integer shippingMode;
	private Boolean showDetail;

	private Partner orderPartner;
	private Integer categoryId1;
	private Integer categoryId2;
	private Integer categoryId3;
	private Integer categoryId4;

	// private List<Object[]> level1 = new ArrayList<Object[]>();
	private List<Object[]> level2 = new ArrayList<Object[]>();
	private List<Object[]> level3 = new ArrayList<Object[]>();
	private List<Object[]> level4 = new ArrayList<Object[]>();

	private Integer shippingOption;
	private Integer payOption;
	private String shippingName;
	private String zipCode;
	private String city;
	private String address;
	private String contactName;
	private String comment;
	private Boolean hasFuvDates;
	private List<SelectItem> fuvDates = new ArrayList<SelectItem>();
	private Date minDate;
	private long selectedFuvDate;
	
	private List<WishlistDist> wishlist;
	private Integer selectedWishlistId;

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public List<ProductVO> getOrderProductList() {
		return orderProductList;
	}

	public void setOrderProductList(List<ProductVO> orderProductList) {
		this.orderProductList = orderProductList;
	}

	public ProductVO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductVO selectedProduct) {
		this.selectedProduct = selectedProduct;
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

	public String getVcsopkodName() {
		return vcsopkodName;
	}

	public void setVcsopkodName(String vcsopkodName) {
		this.vcsopkodName = vcsopkodName;
	}

	public Integer getSelectedTabIndex() {
		return selectedTabIndex;
	}

	public void setSelectedTabIndex(Integer selectedTabIndex) {
		this.selectedTabIndex = selectedTabIndex;
	}

	public BigDecimal getNetto() {
		return netto;
	}

	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}

	public BigDecimal getBrutto() {
		return brutto;
	}

	public void setBrutto(BigDecimal brutto) {
		this.brutto = brutto;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Boolean getIsModifyOrder() {
		return isModifyOrder;
	}

	public void setIsModifyOrder(Boolean isModifyOrder) {
		this.isModifyOrder = isModifyOrder;
	}

	public List<ProductVO> getModifyProductList() {
		return modifyProductList;
	}

	public void setModifyProductList(List<ProductVO> modifyProductList) {
		this.modifyProductList = modifyProductList;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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

	public Integer getShippingMode() {
		return shippingMode;
	}

	public void setShippingMode(Integer shippingMode) {
		this.shippingMode = shippingMode;
	}

	public Boolean getShowDetail() {
		return showDetail;
	}

	public void setShowDetail(Boolean showDetail) {
		this.showDetail = showDetail;
	}

	public Partner getOrderPartner() {
		return orderPartner;
	}

	public void setOrderPartner(Partner orderPartner) {
		this.orderPartner = orderPartner;
	}

	public List<Object[]> getCikkcsops() {
		return cikkcsops;
	}

	public void setCikkcsops(List<Object[]> cikkcsops) {
		this.cikkcsops = cikkcsops;
	}

	public List<Object[]> getAllCikkcsops() {
		return allCikkcsops;
	}

	public void setAllCikkcsops(List<Object[]> allCikkcsops) {
		this.allCikkcsops = allCikkcsops;
	}

	public Integer getCategoryId1() {
		return categoryId1;
	}

	public void setCategoryId1(Integer categoryId1) {
		this.categoryId1 = categoryId1;
	}

	public Integer getCategoryId2() {
		return categoryId2;
	}

	public void setCategoryId2(Integer categoryId2) {
		this.categoryId2 = categoryId2;
	}

	// public List<Object[]> getLevel1() {
	// return level1;
	// }
	//
	// public void setLevel1(List<Object[]> level1) {
	// this.level1 = level1;
	// }
	//
	public List<Object[]> getLevel2() {
		return level2;
	}

	public void setLevel2(List<Object[]> level2) {
		this.level2 = level2;
	}

	public List<Object[]> getLevel3() {
		return level3;
	}

	public void setLevel3(List<Object[]> level3) {
		this.level3 = level3;
	}

	public List<Object[]> getLevel4() {
		return level4;
	}

	public void setLevel4(List<Object[]> level4) {
		this.level4 = level4;
	}

	public Integer getCategoryId3() {
		return categoryId3;
	}

	public void setCategoryId3(Integer categoryId3) {
		this.categoryId3 = categoryId3;
	}

	public Integer getCategoryId4() {
		return categoryId4;
	}

	public void setCategoryId4(Integer categoryId4) {
		this.categoryId4 = categoryId4;
	}

	public Integer getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(Integer shippingOption) {
		this.shippingOption = shippingOption;
	}

	public String getShippingName() {
		return shippingName;
	}

	public void setShippingName(String shippingName) {
		this.shippingName = shippingName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getPayOption() {
		return payOption;
	}

	public void setPayOption(Integer payOption) {
		this.payOption = payOption;
	}

	public List<ProductVO> getTapadoGongyoleg() {
		return tapadoGongyoleg;
	}

	public void setTapadoGongyoleg(List<ProductVO> tapadoGongyoleg) {
		this.tapadoGongyoleg = tapadoGongyoleg;
	}

	public List<ProductVO> getNemTapadoGongyoleg() {
		return nemTapadoGongyoleg;
	}

	public void setNemTapadoGongyoleg(List<ProductVO> nemTapadoGongyoleg) {
		this.nemTapadoGongyoleg = nemTapadoGongyoleg;
	}

	public Boolean getHasFuvDates() {
		return hasFuvDates;
	}

	public void setHasFuvDates(Boolean hasFuvDates) {
		this.hasFuvDates = hasFuvDates;
	}

	public List<SelectItem> getFuvDates() {
		return fuvDates;
	}

	public void setFuvDates(List<SelectItem> fuvDates) {
		this.fuvDates = fuvDates;
	}

	public Date getMinDate() {
		return minDate;
	}

	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}

	public long getSelectedFuvDate() {
		return selectedFuvDate;
	}

	public void setSelectedFuvDate(long selectedFuvDate) {
		this.selectedFuvDate = selectedFuvDate;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}

	public HtmlDataTable getDataTable() {
		return dataTable;
	}

	public void setDataTable(HtmlDataTable dataTable) {
		this.dataTable = dataTable;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public boolean isSortAscending() {
		return sortAscending;
	}

	public void setSortAscending(boolean sortAscending) {
		this.sortAscending = sortAscending;
	}

	public List<WishlistDist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<WishlistDist> wishlist) {
		this.wishlist = wishlist;
	}

	public Integer getSelectedWishlistId() {
		return selectedWishlistId;
	}

	public void setSelectedWishlistId(Integer selectedWishlistId) {
		this.selectedWishlistId = selectedWishlistId;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}

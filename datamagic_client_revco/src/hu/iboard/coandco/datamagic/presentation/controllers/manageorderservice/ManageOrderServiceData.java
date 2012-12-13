package hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.org.apache.xerces.internal.parsers.IntegratedParserConfiguration;

@ManagedBean(name = "manageorderserviceData")
@SessionScoped
public class ManageOrderServiceData implements Serializable {

	private static final long serialVersionUID = -6105102800747573827L;

	private List<ProductVO> products = new ArrayList<ProductVO>();
	private List<ProductVO> orderProductList = new ArrayList<ProductVO>();
	private ProductVO selectedProduct = null;
	private String sku = null;
	private String productName = null;
	private String vcsopkodName = null;
	private Integer selectedTabIndex;
	private BigDecimal netto = new BigDecimal(0);
	private BigDecimal brutto = new BigDecimal(0);
	private Date orderDate;
	private Boolean isModifyOrder = false;
	private List<ProductVO> modifyProductList = new ArrayList<ProductVO>();
	private String orderNum;

	private List<SelectItem> cikkcsopItems = new ArrayList<SelectItem>();
	private Integer selectedCikkcsopId = null;

	private Boolean fuvDijCheck;
	private Boolean csomDijCheck;
	private Boolean betetDijCheck;
	private Boolean haszDijCheck;
	private Boolean behajEnCheck;
	private Boolean fagySzallCheck;
	private Boolean kcrCheck;
	private Boolean fuvaVisszaCheck;

	private Integer fuvDijMenny;
	private Integer csomDijMenny;
	private Integer betetDijMenny;
	private Integer haszDijMenny;
	private Integer behajEnMenny;
	private Integer fagySzallMenny;
	private Integer kcrMenny;
	private Integer fuvaVisszaMenny;

	private Integer summary;
	private Integer fuvdijsum;
	private Integer csomdijsum;
	private Integer betdijsum;
	private Integer haszdijsum;
	private Integer behensum;
	private Integer fagysum;
	private Integer kcrsum;
	private Integer visszasum;

	private Integer shippingMode;
	private Boolean showDetail;

	private Integer fullPallet;
	private Integer fragmentPallet;

	private Partner orderPartner;


//	private Integer anyagTipus;
//	private Integer feluletTipus;
//	private Integer szemcseMeret;

	private List<SelectItem> feluletTipusok = new ArrayList<SelectItem>();
	private List<SelectItem> szemcseMeretek = new ArrayList<SelectItem>();

	private Integer shippingType;
	private Integer shippingPartnerType;
	private String shippingSubContractor;
	private String shippingAtvevo;
	private Boolean shippingPlace;
	private String shippingZipcode;
	private String shippingCity;
	private String shippingAddress;
	private String shippingTel;
	private String shippingDaruzas;
	private String shippingBehajtas;
	private Boolean shippingCsereraklap;
	private String shippingMegj;
	private String shippingOtherId;

	private Boolean showNeoColor;
	private Boolean showNeoArnyalat;

	private Date shippingMinDate;

	private List<String> anyagTipusok = new ArrayList<String>();
	private List<String> anyagCsoportok = new ArrayList<String>();
	private List<String> termekNevek = new ArrayList<String>();
	private List<String> meretek = new ArrayList<String>();
	private List<String> bazisok = new ArrayList<String>();
	private List<String> kiszerelesek = new ArrayList<String>();
	private List<String> szincsaladok = new ArrayList<String>();
	private List<String> arnyalatok = new ArrayList<String>();
	
	private Integer anyagTipusokSize;
	private Integer anyagCsoportokSize;
	private Integer termekNevekSize;
	private Integer meretekSize;
	private Integer bazisokSize;
	private Integer kiszerelesekSize;
	private Integer szincsaladokSize;
	private Integer arnyalatokSize;

	private String tipus;
	private String anyagCsoport = "";
	private String termekNev = "";
	private String kiszereles = "";
	private String bazis = "";
	private String szincsalad = "";
	private String arnyalat = "";
	private String meret = "";

	private List<Integer> tipusIDs = new ArrayList<Integer>();
	private List<Integer> csoportIDs = new ArrayList<Integer>();
	private List<Integer> termeknevIDs = new ArrayList<Integer>();
	private List<Integer> meretIDs = new ArrayList<Integer>();
	private List<Integer> bazisIDs = new ArrayList<Integer>();
	private List<Integer> kiszerelesIDs = new ArrayList<Integer>();
	private List<Integer> szincsaladIDs = new ArrayList<Integer>();
	private List<Integer> arnyalatIDs = new ArrayList<Integer>();

	// private List<ProductVO> level2 = new ArrayList<ProductVO>();
	// private List<ProductVO> level3 = new ArrayList<ProductVO>();
	// private List<ProductVO> level4 = new ArrayList<ProductVO>();

	private Arnev arnev;
	private Boolean isOrderInProgress = false;

	public List<Integer> getTipusIDs() {
		return this.tipusIDs;
	}

	public void setTipusIDs(List<Integer> tipusIDs) {
		this.tipusIDs = tipusIDs;
	}

	public List<Integer> getCsoportIDs() {
		return this.csoportIDs;
	}

	public void setCsoportIDs(List<Integer> csoportIDs) {
		this.csoportIDs = csoportIDs;
	}

	public List<Integer> getTermeknevIDs() {
		return this.termeknevIDs;
	}

	public void setTermeknevIDs(List<Integer> termeknevIDs) {
		this.termeknevIDs = termeknevIDs;
	}

	public List<Integer> getMeretIDs() {
		return this.meretIDs;
	}

	public void setMeretIDs(List<Integer> meretIDs) {
		this.meretIDs = meretIDs;
	}

	public List<Integer> getBazisIDs() {
		return this.bazisIDs;
	}

	public void setBazisIDs(List<Integer> bazisIDs) {
		this.bazisIDs = bazisIDs;
	}

	public List<Integer> getKiszerelesIDs() {
		return this.kiszerelesIDs;
	}

	public void setKiszerelesIDs(List<Integer> kiszerelesIDs) {
		this.kiszerelesIDs = kiszerelesIDs;
	}

	public List<Integer> getSzincsaladIDs() {
		return this.szincsaladIDs;
	}

	public void setSzincsaladIDs(List<Integer> szincsaladIDs) {
		this.szincsaladIDs = szincsaladIDs;
	}

	public List<Integer> getArnyalatIDs() {
		return this.arnyalatIDs;
	}

	public void setArnyalatIDs(List<Integer> arnyalatIDs) {
		this.arnyalatIDs = arnyalatIDs;
	}

	public Arnev getArnev() {
		return arnev;
	}

	public void setArnev(Arnev arnev) {
		this.arnev = arnev;
	}

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

	public Boolean getFuvDijCheck() {
		return fuvDijCheck;
	}

	public void setFuvDijCheck(Boolean fuvDijCheck) {
		this.fuvDijCheck = fuvDijCheck;
	}

	public Boolean getCsomDijCheck() {
		return csomDijCheck;
	}

	public void setCsomDijCheck(Boolean csomDijCheck) {
		this.csomDijCheck = csomDijCheck;
	}

	public Boolean getBetetDijCheck() {
		return betetDijCheck;
	}

	public void setBetetDijCheck(Boolean betetDijCheck) {
		this.betetDijCheck = betetDijCheck;
	}

	public Boolean getHaszDijCheck() {
		return haszDijCheck;
	}

	public void setHaszDijCheck(Boolean haszDijCheck) {
		this.haszDijCheck = haszDijCheck;
	}

	public Boolean getBehajEnCheck() {
		return behajEnCheck;
	}

	public void setBehajEnCheck(Boolean behajEnCheck) {
		this.behajEnCheck = behajEnCheck;
	}

	public Boolean getFagySzallCheck() {
		return fagySzallCheck;
	}

	public void setFagySzallCheck(Boolean fagySzallCheck) {
		this.fagySzallCheck = fagySzallCheck;
	}

	public Boolean getKcrCheck() {
		return kcrCheck;
	}

	public void setKcrCheck(Boolean kcrCheck) {
		this.kcrCheck = kcrCheck;
	}

	public Boolean getFuvaVisszaCheck() {
		return fuvaVisszaCheck;
	}

	public void setFuvaVisszaCheck(Boolean fuvaVisszaCheck) {
		this.fuvaVisszaCheck = fuvaVisszaCheck;
	}

	public Integer getFuvDijMenny() {
		return fuvDijMenny;
	}

	public void setFuvDijMenny(Integer fuvDijMenny) {
		this.fuvDijMenny = fuvDijMenny;
	}

	public Integer getCsomDijMenny() {
		return csomDijMenny;
	}

	public void setCsomDijMenny(Integer csomDijMenny) {
		this.csomDijMenny = csomDijMenny;
	}

	public Integer getBetetDijMenny() {
		return betetDijMenny;
	}

	public void setBetetDijMenny(Integer betetDijMenny) {
		this.betetDijMenny = betetDijMenny;
	}

	public Integer getHaszDijMenny() {
		return haszDijMenny;
	}

	public void setHaszDijMenny(Integer haszDijMenny) {
		this.haszDijMenny = haszDijMenny;
	}

	public Integer getBehajEnMenny() {
		return behajEnMenny;
	}

	public void setBehajEnMenny(Integer behajEnMenny) {
		this.behajEnMenny = behajEnMenny;
	}

	public Integer getFagySzallMenny() {
		return fagySzallMenny;
	}

	public void setFagySzallMenny(Integer fagySzallMenny) {
		this.fagySzallMenny = fagySzallMenny;
	}

	public Integer getKcrMenny() {
		return kcrMenny;
	}

	public void setKcrMenny(Integer kcrMenny) {
		this.kcrMenny = kcrMenny;
	}

	public Integer getFuvaVisszaMenny() {
		return fuvaVisszaMenny;
	}

	public void setFuvaVisszaMenny(Integer fuvaVisszaMenny) {
		this.fuvaVisszaMenny = fuvaVisszaMenny;
	}

	public Integer getSummary() {
		return summary;
	}

	public void setSummary(Integer summary) {
		this.summary = summary;
	}

	public Integer getFuvdijsum() {
		return fuvdijsum;
	}

	public void setFuvdijsum(Integer fuvdijsum) {
		this.fuvdijsum = fuvdijsum;
	}

	public Integer getCsomdijsum() {
		return csomdijsum;
	}

	public void setCsomdijsum(Integer csomdijsum) {
		this.csomdijsum = csomdijsum;
	}

	public Integer getBetdijsum() {
		return betdijsum;
	}

	public void setBetdijsum(Integer betdijsum) {
		this.betdijsum = betdijsum;
	}

	public Integer getHaszdijsum() {
		return haszdijsum;
	}

	public void setHaszdijsum(Integer haszdijsum) {
		this.haszdijsum = haszdijsum;
	}

	public Integer getBehensum() {
		return behensum;
	}

	public void setBehensum(Integer behensum) {
		this.behensum = behensum;
	}

	public Integer getFagysum() {
		return fagysum;
	}

	public void setFagysum(Integer fagysum) {
		this.fagysum = fagysum;
	}

	public Integer getKcrsum() {
		return kcrsum;
	}

	public void setKcrsum(Integer kcrsum) {
		this.kcrsum = kcrsum;
	}

	public Integer getVisszasum() {
		return visszasum;
	}

	public void setVisszasum(Integer visszasum) {
		this.visszasum = visszasum;
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

	public Integer getFullPallet() {
		return fullPallet;
	}

	public void setFullPallet(Integer fullPallet) {
		this.fullPallet = fullPallet;
	}

	public Integer getFragmentPallet() {
		return fragmentPallet;
	}

	public void setFragmentPallet(Integer fragmentPallet) {
		this.fragmentPallet = fragmentPallet;
	}

	public Partner getOrderPartner() {
		return orderPartner;
	}

	public void setOrderPartner(Partner orderPartner) {
		this.orderPartner = orderPartner;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

//	public Integer getAnyagTipus() {
//		return anyagTipus;
//	}
//
//	public void setAnyagTipus(Integer anyagTipus) {
//		this.anyagTipus = anyagTipus;
//	}

//	public Integer getFeluletTipus() {
//		return feluletTipus;
//	}
//
//	public void setFeluletTipus(Integer feluletTipus) {
//		this.feluletTipus = feluletTipus;
//	}

//	public Integer getSzemcseMeret() {
//		return szemcseMeret;
//	}
//
//	public void setSzemcseMeret(Integer szemcseMeret) {
//		this.szemcseMeret = szemcseMeret;
//	}

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

	public List<String> getMeretek() {
		return meretek;
	}

	public void setMeretek(List<String> meretek) {
		this.meretek = meretek;
	}

	public List<String> getBazisok() {
		return bazisok;
	}

	public void setBazisok(List<String> bazisok) {
		this.bazisok = bazisok;
	}

	public List<String> getKiszerelesek() {
		return kiszerelesek;
	}

	public void setKiszerelesek(List<String> kiszerelesek) {
		this.kiszerelesek = kiszerelesek;
	}

	public List<String> getSzincsaladok() {
		return szincsaladok;
	}

	public void setSzincsaladok(List<String> szincsaladok) {
		this.szincsaladok = szincsaladok;
	}

	public List<String> getArnyalatok() {
		return arnyalatok;
	}

	public void setArnyalatok(List<String> arnyalatok) {
		this.arnyalatok = arnyalatok;
	}

	public String getKiszereles() {
		return kiszereles;
	}

	public void setKiszereles(String kiszereles) {
		this.kiszereles = kiszereles;
	}

	public String getSzincsalad() {
		return szincsalad;
	}

	public void setSzincsalad(String szincsalad) {
		this.szincsalad = szincsalad;
	}

	// public List<ProductVO> getLevel4() {
	// return level4;
	// }
	//
	// public void setLevel4(List<ProductVO> level4) {
	// this.level4 = level4;
	// }

	public String getArnyalat() {
		return arnyalat;
	}

	public void setArnyalat(String arnyalat) {
		this.arnyalat = arnyalat;
	}

	public Integer getShippingType() {
		return shippingType;
	}

	public void setShippingType(Integer shippingType) {
		this.shippingType = shippingType;
	}

	public Integer getShippingPartnerType() {
		return shippingPartnerType;
	}

	public void setShippingPartnerType(Integer shippingPartnerType) {
		this.shippingPartnerType = shippingPartnerType;
	}

	public String getShippingSubContractor() {
		return shippingSubContractor;
	}

	public void setShippingSubContractor(String shippingSubContractor) {
		this.shippingSubContractor = shippingSubContractor;
	}

	public String getShippingAtvevo() {
		return shippingAtvevo;
	}

	public void setShippingAtvevo(String shippingAtvevo) {
		this.shippingAtvevo = shippingAtvevo;
	}

	public Boolean getShippingPlace() {
		return shippingPlace;
	}

	public void setShippingPlace(Boolean shippingPlace) {
		this.shippingPlace = shippingPlace;
	}

	public String getShippingZipcode() {
		return shippingZipcode;
	}

	public void setShippingZipcode(String shippingZipcode) {
		this.shippingZipcode = shippingZipcode;
	}

	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getShippingTel() {
		return shippingTel;
	}

	public void setShippingTel(String shippingTel) {
		this.shippingTel = shippingTel;
	}

	public String getShippingDaruzas() {
		return shippingDaruzas;
	}

	public void setShippingDaruzas(String shippingDaruzas) {
		this.shippingDaruzas = shippingDaruzas;
	}

	public String getShippingBehajtas() {
		return shippingBehajtas;
	}

	public void setShippingBehajtas(String shippingBehajtas) {
		this.shippingBehajtas = shippingBehajtas;
	}

	public Boolean getShippingCsereraklap() {
		return shippingCsereraklap;
	}

	public void setShippingCsereraklap(Boolean shippingCsereraklap) {
		this.shippingCsereraklap = shippingCsereraklap;
	}

	public String getShippingMegj() {
		return shippingMegj;
	}

	public void setShippingMegj(String shippingMegj) {
		this.shippingMegj = shippingMegj;
	}

	public String getShippingOtherId() {
		return shippingOtherId;
	}

	public void setShippingOtherId(String shippingOtherId) {
		this.shippingOtherId = shippingOtherId;
	}

	public Boolean getShowNeoColor() {
		return showNeoColor;
	}

	public void setShowNeoColor(Boolean showNeoColor) {
		this.showNeoColor = showNeoColor;
	}

	public Boolean getShowNeoArnyalat() {
		return showNeoArnyalat;
	}

	public void setShowNeoArnyalat(Boolean showNeoArnyalat) {
		this.showNeoArnyalat = showNeoArnyalat;
	}

	public Date getShippingMinDate() {
		return shippingMinDate;
	}

	public void setShippingMinDate(Date shippingMinDate) {
		this.shippingMinDate = shippingMinDate;
	}

	public List<String> getAnyagTipusok() {
		return anyagTipusok;
	}

	public void setAnyagTipusok(List<String> anyagTipusok) {
		this.anyagTipusok = anyagTipusok;
	}

	public List<String> getAnyagCsoportok() {
		return anyagCsoportok;
	}

	public void setAnyagCsoportok(List<String> anyagCsoportok) {
		this.anyagCsoportok = anyagCsoportok;
	}

	public List<String> getTermekNevek() {
		return termekNevek;
	}

	public void setTermekNevek(List<String> termekNevek) {
		this.termekNevek = termekNevek;
	}

	public String getAnyagCsoport() {
		return anyagCsoport;
	}

	public void setAnyagCsoport(String anyagCsoport) {

		this.anyagCsoport = anyagCsoport;
	}

	// public List<ProductVO> getLevel2() {
	// return level2;
	// }
	//
	// public void setLevel2(List<ProductVO> level2) {
	// this.level2 = level2;
	// }
	//
	// public List<ProductVO> getLevel3() {
	// return level3;
	// }
	//
	// public void setLevel3(List<ProductVO> level3) {
	// this.level3 = level3;
	// }

	public String getTermekNev() {
		return termekNev;
	}

	public void setTermekNev(String termekNev) {
		this.termekNev = termekNev;
	}

	public String getMeret() {
		return meret;
	}

	public void setMeret(String meret) {
		this.meret = meret;
	}

	public List<Integer> getFilteredIDsByFilterState() {		
		List<Integer> result = new ArrayList<Integer>();
		List<Integer> allNumbers = new ArrayList<Integer>();

		List<List<Integer>> idList = new ArrayList<List<Integer>>();

		if (tipusIDs != null)
			idList.add(tipusIDs);

		if (csoportIDs != null)
			idList.add(csoportIDs);

		if (termeknevIDs != null)
			idList.add(termeknevIDs);

		if (meretIDs != null)
			idList.add(meretIDs);

		if (bazisIDs != null)
			idList.add(bazisIDs);

		if (kiszerelesIDs != null)
			idList.add(kiszerelesIDs);

		if (szincsaladIDs != null)
			idList.add(szincsaladIDs);

		if (arnyalatIDs != null)
			idList.add(arnyalatIDs);

		for (List<Integer> list : idList) {
			for (Integer i : list) {
				if (!allNumbers.contains(i))
					allNumbers.add(i);
			}
		}

		outer: for (Integer i : allNumbers) {
			for (List<Integer> list : idList)
				if (!list.contains(i))
					continue outer;
			result.add(i);
		}

		return result;
	}
	

	public Integer getAnyagTipusokSize(){
		if (this.anyagTipusok != null)
			return this.anyagTipusok.size();
		else
			return 0;
	}
	
//	public void setAnyagTipusokSize(Integer anyagTipusokSize){
//		this.anyagTipusokSize = anyagTipusokSize;
//	}
	
	public Integer getAnyagCsoportokSize(){
		if (this.anyagCsoportok != null)
			return this.anyagCsoportok.size();
		else
			return 0;
	}
	
//	public void setAnyagCsoportokSize(Integer anyagCsoportokSize){
//		this.anyagCsoportokSize = anyagCsoportokSize;
//	}
	
	public Integer getTermekNevekSize(){
		if (this.termekNevek != null)
			return this.termekNevek.size();
		else
			return 0;
	}
	
//	public void setTermekNevekSize(Integer termekNevekSize){
//		this.termekNevekSize = termekNevekSize;
//	}
	
	public Integer getMeretekSize(){
		if (this.meretek != null)
			return this.meretek.size();
		else
			return 0;
	}
	
//	public void setMeretekSize(Integer meretekSize){
//		this.meretekSize = meretekSize;
//	}
	
	public Integer getBazisokSize(){
		if (this.bazisok != null)
			return this.bazisok.size();
		else
			return 0;
	}
	
//	public void seBazisokSize(Integer bazisokSize){
//		this.bazisokSize = bazisokSize;
//	}
	
	public Integer getKiszerelesekSize(){
		if (this.kiszerelesek != null)
			return this.kiszerelesek.size();
		else
			return 0;
	}
	
//	public void setKiszerelesekSize(Integer kiszerelesekSize){
//		this.kiszerelesekSize = kiszerelesekSize;
//	}
	
	public Integer getSzincsaladokSize(){
		if (this.szincsaladok != null)
			return this.szincsaladok.size();
		else
			return 0;
	}
	
//	public void setSzincsaladokSize(Integer szincsaladokSize){
//		this.szincsaladokSize = szincsaladokSize;
//	}
	
	public Integer getArnyalatokSize(){
		if (this.arnyalatok != null)
			return this.arnyalatok.size();
		else
			return 0;
	}

	public Boolean getIsOrderInProgress() {
		return isOrderInProgress;
	}

	public void setIsOrderInProgress(Boolean isOrderInProgress) {
		this.isOrderInProgress = isOrderInProgress;
	}
	
//	public void setArnyalatokSize(Integer arnyalatokSize){
//		this.arnyalatokSize = arnyalatokSize;
//	}	
	
	
//	private Integer anyagTipusokSize;
//	private Integer anyagCsoportokSize;
//	private Integer termekNevekSize;
//	private Integer meretekSize;
//	private Integer bazisokSize;
//	private Integer kiszerelesekSize;
//	private Integer szincsaladokSize;
//	private Integer arnyalatokSize;	
	
}

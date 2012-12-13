package hu.iboard.coandco.datamagic.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice.ManageOrderServiceController;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.service.wishlistservice.WishlistService;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.IdleEvent;

@ManagedBean(name = "loginserviceController")
@RequestScoped
public class LoginServiceController extends AbstractController {

	public static final String LOGINSERVICE_ACTION = "mainpage";
	public static final String SESSION_TIMEOUT_ACTION = "sessiontimeout";
	public static final String LOGOUT_ACTION = "logout";
	private Locale selectedLocale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
	private HtmlInputText emergencyUserTextBox;

	@ManagedProperty(value = "#{loginserviceData}")
	private LoginServiceData data;

	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{workerService}")
	private WorkerServiceBase workerService;
	@ManagedProperty(value = "#{manageorderserviceController}")
	private ManageOrderServiceController manageOrderServiceController;
	@ManagedProperty(value = "#{productService}")
	private ProductServiceBase productService;
	@ManagedProperty(value = "#{wishlistService}")
	private WishlistService wishlistService;

	@Override
	public void loadData() {

		getData().setUserName(null);
		getData().setPassword(null);
		getData().setEmergencyUserName(null);
		getData().setUser(new UserVO());
	}

	@Override
	public void reloadData() {
	}

	@Override
	public void resetData() {
		removeBeanFromSession(LOGINSERVICE_CONTROLLER);
	}

	public String login() {

		String retVal = null;

		try {
			boolean isLogin = getLoginManager().login(getData().getUserName(), getData().getPassword());
			if (isLogin) {
				retVal = MAIN_PAGE;
			} else {
				addFatalMessage("", "Rossz felhasználónév, vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt bejelentkezés közben.");
			logger.error("Login failed!", e);
			return null;
		}
		return retVal;
	}

	public String loginMobile() {

		String retVal = null;

		try {
			boolean isLogin = getLoginManager().login(getData().getUserName(), getData().getPassword());
			if (isLogin) {
				if (getManagedUser().getUserType().equals(SALES)) {
					getData().setPartnerSearchText("");
					getData().setAllPartner(getPartnerService().getAllPartner());
					retVal = PARTNER_CHOOSE_PAGE;
				} else {
					retVal = "mobilepage";
				}
			} else {
				addFatalMessage("", "Rossz felhasználónév, vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt bejelentkezés közben!");
			logger.error("Login failed!", e);
			return null;
		}
		return retVal;
	}

	public String logout() {

		getLoginManager().logout();
		getData().setUser(null);
		return LOGINSERVICE_ACTION;
	}

	public String loginBySelectedPartner() {

		if (getData().getSelectedPartnerId() == 0) {
			addFatalMessage("Nem választott felhasználót.", "");
			return null;
		}
		try {
			getLoginManager().loginSalesByPartner(getData().getSelectedPartnerId());
		} catch (Exception e) {
			logger.error("Login failed by sales user!", e);
			addFatalMessage("Hiba történt bejelentkezés közben.", "");
		}
		return MAIN_PAGE;
	}

	public String searchPartnerAction() {

		getData().setAllPartner(getPartnerService().getPartnersByName(getData().getPartnerSearchText()));
		return null;
	}

	public List<SelectItem> getPartnerItems() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> list = getData().getAllPartner();
		if (list == null)
			return new ArrayList<SelectItem>();
		for (Object[] item : list) {
			selectItemList.add(new SelectItem(item[1], (String) item[0]));
		}
		return selectItemList;
	}

	/**
	 * A session timeout-ot kezelofuggveny
	 * 
	 * @return
	 */
	public String sessionTimeoutHandler() {
		getLoginManager().logout();
		return SESSION_TIMEOUT_ACTION;
	}

	public void idleListener(IdleEvent event) {
		List<ProductVO> products = getManageOrderServiceController().getData().getOrderProductList();
		if (products != null && products.size() > 0) {
			saveWishlist(products, getManageOrderServiceController().getData().getOrderPartner());
		}
		getManageOrderServiceController().getData().setOrderProductList(null);
		getData().setUser(null);
		getLoginManager().logout();
	}

	public void saveWishlist(List<ProductVO> products, Partner partner) {
		Boolean result = getWishlistService().saveWishlist(products, partner, convertDateTimeTosString(new Date()));
		if (result != null && result == false) {
			addErrorMessage("", "Hiba történt a kosár mentésénél, session lejártakor.");
		}
	}

	public void changerLangueComboBox(ValueChangeEvent event) {
		getData().setLang(event.getNewValue().toString());
		changeLocale();
	}

	public void changeLocale() {

		if (getData().getLang() == null)
			getData().setLang("hu");
		logger.info("New locale has been set: " + getData().getLang());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(getData().getLang()));
		setSelectedLocale(new Locale(getData().getLang()));
	}

	public LoginServiceData getData() {
		return data;
	}

	public void setData(LoginServiceData data) {
		this.data = data;
	}

	public Locale getSelectedLocale() {
		return selectedLocale;
	}

	public void setSelectedLocale(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public WorkerServiceBase getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerServiceBase workerService) {
		this.workerService = workerService;
	}

	public HtmlInputText getEmergencyUserTextBox() {
		return emergencyUserTextBox;
	}

	public void setEmergencyUserTextBox(HtmlInputText emergencyUserTextBox) {
		this.emergencyUserTextBox = emergencyUserTextBox;
	}

	public ManageOrderServiceController getManageOrderServiceController() {
		return manageOrderServiceController;
	}

	public void setManageOrderServiceController(ManageOrderServiceController manageOrderServiceController) {
		this.manageOrderServiceController = manageOrderServiceController;
	}

	public ProductServiceBase getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceBase productService) {
		this.productService = productService;
	}

	public WishlistService getWishlistService() {
		return wishlistService;
	}

	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}

}

package hu.iboard.coandco.datamagic.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandlink.CommandLink;
//import org.primefaces.event.IdleEvent;

@ManagedBean(name = "loginserviceController")
@RequestScoped
public class LoginServiceController extends AbstractController {

	public static final String LOGINSERVICE_ACTION = "login";
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

	@Override
	public void loadData() {

		getData().setUserName(null);
		getData().setPassword(null);
		getData().setEmergencyUserName(null);
		getData().setUser(new UserVO());
		FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
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
			boolean isLogin = getLoginManager().login(getData().getUserName(), encodeWithMD5(getData().getPassword()));
			if (isLogin) {
				if (getManagedUser().getUserType().equals(SALES)) {
					getData().setPartnerSearchText("");
					getData().setSelectedPartnerId(0);
					getData().setAllPartner(getPartnerService().getAllPartner());
					retVal = PARTNER_CHOOSE_PAGE;
				} else {
					retVal = MAIN_PAGE;
				}
			} else {
				addFatalMessage("", "Rossz felhasználónév vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor!");
			logger.error("Login failed!", e);
			return null;
		}
		return retVal;
	}

	public String cdhLogin() {

		String retVal = null;

		try {
			boolean isLogin = false;
			if (!getData().getPassword().equals(""))
				isLogin = getLoginManager().cdhLogin(getData().getUserName(), getData().getPassword());
			else {
				isLogin = getLoginManager().emergencyCDHLogin(getData().getUserName());
			}
			if (isLogin) {
				retVal = MAIN_PAGE;
			} else {
				addErrorMessage("", "Rossz felhasználónév vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt belépéskor!");
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
				addFatalMessage("", "Rossz felhasználónév vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba törtent a belépeskor!");
			logger.error("Login failed!", e);
			return null;
		}
		return retVal;
	}

	public String loginMobileCDH() {

		String retVal = null;
		try {
			boolean isLogin = false;
			if (!getData().getPassword().equals(""))
				isLogin = getLoginManager().cdhLogin(getData().getUserName(), getData().getPassword());
			else {
				isLogin = getLoginManager().emergencyCDHLogin(getData().getUserName());
			}
			if (isLogin) {
				retVal = "mobilepage";
			} else {
				addFatalMessage("", "Rossz felhasználónév!");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor!");
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
			addFatalMessage("Nem választott felhasznalot!", "");
			return null;
		}
		try {
			getLoginManager().loginSalesByPartner(getData().getSelectedPartnerId());
		} catch (Exception e) {
			logger.error("Login failed by sales user!", e);
			addFatalMessage("Hiba történt!", "");
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

	//public void idleListener(IdleEvent event) {
	public void idleListener() {
		getData().setUser(null);
		getLoginManager().logout();
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

	public void activateHuLanguage(ActionEvent event) {
		getData().getHuLink().setStyleClass("languageSelectorActive");
		getData().getEnLink().setStyleClass("languageSelectorNotActive");
	}

	public void activateEnLanguage(ActionEvent event) {
		getData().getEnLink().setStyleClass("languageSelectorActive");
		getData().getHuLink().setStyleClass("languageSelectorNotActive");
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

}

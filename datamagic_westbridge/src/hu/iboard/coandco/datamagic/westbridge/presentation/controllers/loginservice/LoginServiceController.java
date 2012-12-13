package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.user.UserVO;
import hu.iboard.coandco.datamagic.westbridge.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.westbridge.presentation.util.Constants;

import java.util.ArrayList;
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

	public static final String LOGINSERVICE_ACTION = "logistics";
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
				retVal = Constants.MAIN_PAGE;
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
		return Constants.MAIN_PAGE;
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

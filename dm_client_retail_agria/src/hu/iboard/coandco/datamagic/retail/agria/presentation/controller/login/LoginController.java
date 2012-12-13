package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.login;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.IdleEvent;

@ManagedBean
@RequestScoped
public class LoginController extends AbstractController {

	@ManagedProperty(value = "#{loginControllerData}")
	private LoginControllerData data;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setUserName(null);
			getData().setPassword(null);
		}
	}

	public String login() {
		if (getData().getUserName() == null || getData().getUserName().equals("")) {
			addErrorMessage("", "Belépéshez, kérem adja meg a felhasználónevét!");
			return null;
		}
		if (getData().getPassword() == null || getData().getPassword().equals("")) {
			addErrorMessage("", "Belépéshez, kérem adja meg a jelszavát!");
			return null;
		}
		try {
			boolean isLogin = getLoginManager().login(getData().getUserName(), encodeWithMD5(getData().getPassword()));
			if (!isLogin) {
				addErrorMessage("", "Rossz felhasználónév, vagy jelszó.");
				logger.error("Login failed!");
				return null;
			}
			if (getManagedAdmin() != null) {
				return "admin";
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor.");
			logger.error("Login failed.", e);
			return null;
		}
		return "profile";
	}

	public String loginAdmin() {
		try {
			boolean isLogin = getLoginManager().loginAdmin(getData().getUserName(), encodeWithMD5(getData().getPassword()));
			if (!isLogin) {
				addErrorMessage("", "Rossz felhasználónév, vagy jelszó.");
				logger.error("Login failed.");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor.");
			logger.error("Login failed.", e);
			return null;
		}
		return "adminmanager";
	}

	public String logout() {
		getLoginManager().logout();
		return "index";
	}
	
	public String logoutAdmin() {
		getLoginManager().logoutAdmin();
		return "admin";
	}

	public void idleListener(IdleEvent e) {

		getLoginManager().logout();

	}

	public LoginControllerData getData() {
		return data;
	}

	public void setData(LoginControllerData data) {
		this.data = data;
	}
}

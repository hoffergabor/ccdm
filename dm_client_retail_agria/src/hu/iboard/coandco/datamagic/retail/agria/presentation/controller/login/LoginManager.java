package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.login;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.userservice.UserService;
import hu.iboard.coandco.datamagic.util.Constants;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@ManagedBean(name = "loginManager")
@RequestScoped
public class LoginManager extends AbstractController implements Constants {

	private static Logger logger = Logger.getLogger(LoginManager.class);

	@ManagedProperty(value = "#{loginManagerData}")
	private LoginManagerData data;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	public boolean login(String userName, String passwd) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			/*
			Partner partner = getUserService().partnerLogin(userName, passwd);
			if (partner != null) {
				getData().setLoggedInPartner(partner);
				session.setAttribute("loggedInPartner", partner);
			}*/
			Customer customer = getUserService().customerLogin(userName, passwd);
			if (customer == null)
				return false;
			getData().setLoggedInCustomer(customer);
			session.setAttribute("loggedInCustomer", customer);
			session.setMaxInactiveInterval(600);
			return true;
		} catch (Exception e) {
			logger.error("LOGIN FAILED!", e);
			return false;
		}
	}

	public boolean loginAdmin(String userName, String passwd) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Admin admin = getUserService().getAdminByLogin(userName, passwd);
			if (admin == null)
				return false;
			getData().setLoggedInAdmin(admin);
			session.setAttribute("loggedInAdmin", admin);
			session.setMaxInactiveInterval(600);
			return true;
		} catch (Exception e) {
			logger.error("LOGIN ADMIN FAILED.", e);
			return false;
		}
	}

	public void logout() {
		getData().setLoggedInCustomer(null);
		getData().setLoggedInPartner(null);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("loggedInPartner");
		session.removeAttribute("loggedInCustomer");
		session.invalidate();
	}

	public void logoutAdmin() {
		getData().setLoggedInAdmin(null);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("loggedInAdmin");
		session.invalidate();
	}

	public void loginPartner(Partner partner) {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Partner partn = getUserService().partnerLogin(partner.getWeblogin(), partner.getWebpassw());
		if (partn != null) {
			getData().setLoggedInPartner(partn);
			session.setAttribute("loggedInPartner", partn);
		}
	}

	public LoginManagerData getData() {
		return data;
	}

	public void setData(LoginManagerData data) {
		this.data = data;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.login;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginManagerData")
@SessionScoped
public class LoginManagerData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 145001171618134478L;

	private Partner loggedInPartner = null;
	private Customer loggedInCustomer = null;
	private Admin loggedInAdmin=null;

	public Partner getLoggedInPartner() {
		return loggedInPartner;
	}

	public void setLoggedInPartner(Partner loggedInPartner) {
		this.loggedInPartner = loggedInPartner;
	}

	public Customer getLoggedInCustomer() {
		return loggedInCustomer;
	}

	public void setLoggedInCustomer(Customer loggedInCustomer) {
		this.loggedInCustomer = loggedInCustomer;
	}

	public Admin getLoggedInAdmin() {
		return loggedInAdmin;
	}

	public void setLoggedInAdmin(Admin loggedInAdmin) {
		this.loggedInAdmin = loggedInAdmin;
	}

}

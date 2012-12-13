package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.registration;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "registrationControllerData")
@SessionScoped
public class RegistrationControllerData implements Serializable {

	private static final long serialVersionUID = -3789340581962949409L;
	private String password1;
	private String password2;
	private Customer customer;
	private ShippingAddress shippingAddress;
	
	private Integer activateNewsLetter;
	//private Integer activateInvoice;
	private boolean activateInvoice;
	private String captcha;
	private Partner partner;
	private Boolean rules;

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Integer getActivateNewsLetter() {
		return activateNewsLetter;
	}

	public void setActivateNewsLetter(Integer activateNewsLetter) {
		this.activateNewsLetter = activateNewsLetter;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

//	public Integer getActivateInvoice() {
//		return activateInvoice;
//	}
//
//	public void setActivateInvoice(Integer activateInvoice) {
//		this.activateInvoice = activateInvoice;
//	}

	public boolean getActivateInvoice() {
		return activateInvoice;
	}

	public void setActivateInvoice(boolean activateInvoice) {
		this.activateInvoice = activateInvoice;
	}
	
	
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Boolean getRules() {
		return rules;
	}

	public void setRules(Boolean rules) {
		this.rules = rules;
	}

}

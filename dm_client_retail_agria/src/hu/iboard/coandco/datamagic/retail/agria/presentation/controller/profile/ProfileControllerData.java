package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.profile;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "profileControllerData")
@SessionScoped
public class ProfileControllerData implements Serializable {

	private static final long serialVersionUID = -8977909767970392592L;

	private ShippingAddress shippingAddress;
	private Customer customer;
	private Partner partner;
	private List<ShippingAddress> shippingAddresses = new ArrayList<ShippingAddress>();
	private Integer shippingAddressId;
	private Integer activateNewsLetter; 
	private String webPassw1 = "";
	private String webPassw2 = "";

	public ShippingAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}

	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public Integer getShippingAddressId() {
		return shippingAddressId;
	}

	public void setShippingAddressId(Integer shippingAddressId) {
		this.shippingAddressId = shippingAddressId;
	}

	public Integer getActivateNewsLetter() {
		return activateNewsLetter;
	}

	public void setActivateNewsLetter(Integer activateNewsLetter) {
		this.activateNewsLetter = activateNewsLetter;
	}

	public String getWebPassw1() {
		return webPassw1;
	}

	public void setWebPassw1(String webPassw1) {
		this.webPassw1 = webPassw1;
	}

	public String getWebPassw2() {
		return webPassw2;
	}

	public void setWebPassw2(String webPassw2) {
		this.webPassw2 = webPassw2;
	}
}

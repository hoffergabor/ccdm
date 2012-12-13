package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.order;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "orderControllerData")
@ApplicationScoped
public class OrderControllerData implements Serializable {

	private static final long serialVersionUID = 6958477137975265267L;

	private List<ShippingAddress> shippingAddresses = new ArrayList<ShippingAddress>();
	private ShippingAddress shippingAddress = new ShippingAddress();
	private Customer customer;
	private Partner partner;
	private Integer shippingAddressId;
	private String payOption;
	private Rendel order = new Rendel();
	private Boolean rules = false;
	private Integer activateInvoice = 0;
	private String invoiceName;
	private String invoiceZipcode;
	private String invoiceCity;
	private String invoiceAddress;
	private String errorMessage;
	private String shippingPrice;
	private Boolean addedExpressPrice = false;
	private Date shippingDate;
	private Boolean showAtutalas;

	public List<ShippingAddress> getShippingAddresses() {
		return shippingAddresses;
	}

	public void setShippingAddresses(List<ShippingAddress> shippingAddresses) {
		this.shippingAddresses = shippingAddresses;
	}

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

	public String getPayOption() {
		return payOption;
	}

	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}

	public Rendel getOrder() {
		return order;
	}

	public void setOrder(Rendel order) {
		this.order = order;
	}

	public Boolean getRules() {
		return rules;
	}

	public void setRules(Boolean rules) {
		this.rules = rules;
	}

	public Integer getActivateInvoice() {
		return activateInvoice;
	}

	public void setActivateInvoice(Integer activateInvoice) {
		this.activateInvoice = activateInvoice;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getInvoiceZipcode() {
		return invoiceZipcode;
	}

	public void setInvoiceZipcode(String invoiceZipcode) {
		this.invoiceZipcode = invoiceZipcode;
	}

	public String getInvoiceCity() {
		return invoiceCity;
	}

	public void setInvoiceCity(String invoiceCity) {
		this.invoiceCity = invoiceCity;
	}

	public String getInvoiceAddress() {
		return invoiceAddress;
	}

	public void setInvoiceAddress(String invoiceAddress) {
		this.invoiceAddress = invoiceAddress;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Boolean getAddedExpressPrice() {
		return addedExpressPrice;
	}

	public void setAddedExpressPrice(Boolean addedExpressPrice) {
		this.addedExpressPrice = addedExpressPrice;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public Boolean getShowAtutalas() {
		return showAtutalas;
	}

	public void setShowAtutalas(Boolean showAtutalas) {
		this.showAtutalas = showAtutalas;
	}

}

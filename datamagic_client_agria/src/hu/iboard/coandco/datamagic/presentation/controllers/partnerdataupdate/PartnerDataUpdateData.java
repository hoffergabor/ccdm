package hu.iboard.coandco.datamagic.presentation.controllers.partnerdataupdate;

import hu.iboard.coandco.datamagic.generated.Partner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "partnerdataupdateData")
@SessionScoped
public class PartnerDataUpdateData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2735226242834028469L;

	private Partner partner = null;
	private List<Object[]> allPartner = new ArrayList<Object[]>();
	private String password1 = "";
	private String password2 = "";
	private String partnerSearchText = "";
	private Integer selectedPartnerId = null;
	private Boolean newUser = false;
	private List<SelectItem> paymentItems = new ArrayList<SelectItem>();
	private String emailMessage;

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public List<Object[]> getAllPartner() {
		return allPartner;
	}

	public void setAllPartner(List<Object[]> allPartner) {
		this.allPartner = allPartner;
	}

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

	public String getPartnerSearchText() {
		return partnerSearchText;
	}

	public void setPartnerSearchText(String partnerSearchText) {
		this.partnerSearchText = partnerSearchText;
	}

	public Integer getSelectedPartnerId() {
		return selectedPartnerId;
	}

	public void setSelectedPartnerId(Integer selectedPartnerId) {
		this.selectedPartnerId = selectedPartnerId;
	}

	public Boolean getNewUser() {
		return newUser;
	}

	public void setNewUser(Boolean newUser) {
		this.newUser = newUser;
	}

	public List<SelectItem> getPaymentItems() {
		return paymentItems;
	}

	public void setPaymentItems(List<SelectItem> paymentItems) {
		this.paymentItems = paymentItems;
	}

	public List<SelectItem> getPartnerItems() {
		List<SelectItem> selectItemList = new ArrayList<SelectItem>();
		List<Object[]> list = getAllPartner();
		if (list == null)
			return new ArrayList<SelectItem>();
		for (Object[] item : list) {
			selectItemList.add(new SelectItem(item[1], (String) item[0]));
		}
		return selectItemList;
	}

	public String getEmailMessage() {
		return emailMessage;
	}

	public void setEmailMessage(String emailMessage) {
		this.emailMessage = emailMessage;
	}

}
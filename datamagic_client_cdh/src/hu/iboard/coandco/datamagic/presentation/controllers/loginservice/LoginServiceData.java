package hu.iboard.coandco.datamagic.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.component.commandlink.CommandLink;

@ManagedBean(name = "loginserviceData")
@SessionScoped
public class LoginServiceData implements Serializable {

	private static final long serialVersionUID = 3064396958369404709L;

	private UserVO user;
	private String userName = null;
	private String password = null;
	private boolean isVisibleLoginErrorMessage = false;

	private List<Object[]> allPartner = new ArrayList<Object[]>();
	private Integer selectedPartnerId = null;
	private String partnerSearchText = null;
	private String menuPropertiePath;
	private String lang;
	private String emergencyUserName = null;
	
	private CommandLink enLink;
	private CommandLink huLink;
	
	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVisibleLoginErrorMessage() {
		return isVisibleLoginErrorMessage;
	}

	public void setVisibleLoginErrorMessage(boolean isVisibleLoginErrorMessage) {
		this.isVisibleLoginErrorMessage = isVisibleLoginErrorMessage;
	}

	public List<Object[]> getAllPartner() {
		return allPartner;
	}

	public void setAllPartner(List<Object[]> allPartner) {
		this.allPartner = allPartner;
	}

	public Integer getSelectedPartnerId() {
		return selectedPartnerId;
	}

	public void setSelectedPartnerId(Integer selectedPartnerId) {
		this.selectedPartnerId = selectedPartnerId;
	}

	public String getPartnerSearchText() {
		return partnerSearchText;
	}

	public void setPartnerSearchText(String partnerSearchText) {
		this.partnerSearchText = partnerSearchText;
	}

	public String getMenuPropertiePath() {
		return menuPropertiePath;
	}

	public void setMenuPropertiePath(String menuPropertiePath) {
		this.menuPropertiePath = menuPropertiePath;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getEmergencyUserName() {
		return emergencyUserName;
	}

	public void setEmergencyUserName(String emergencyUserName) {
		this.emergencyUserName = emergencyUserName;
	}

	public CommandLink getEnLink() {
		return enLink;
	}

	public void setEnLink(CommandLink enLink) {
		this.enLink = enLink;
	}

	public CommandLink getHuLink() {
		return huLink;
	}

	public void setHuLink(CommandLink huLink) {
		this.huLink = huLink;
	}

}

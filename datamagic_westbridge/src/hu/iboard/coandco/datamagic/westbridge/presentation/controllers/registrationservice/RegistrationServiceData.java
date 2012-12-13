package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.registrationservice;

import java.io.Serializable;

import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "registrationserviceData")
@SessionScoped
public class RegistrationServiceData implements Serializable {

	private static final long serialVersionUID = 2784987528163255447L;

	private UserVO user = null;
	private Partner partner = null;
	private String password1 = "";
	private String password2 = "";

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
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
	
}

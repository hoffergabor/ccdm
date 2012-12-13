package hu.iboard.coandco.datamagic.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

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

	private UserVO loggedInUser = null;
	private Partner loggedInPartner = null;
	private Dolgozo loggedInWorker = null;
	private Partner loggedInRenter = null;
	private Boolean emergency = false;

	public UserVO getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserVO loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public Partner getLoggedInPartner() {
		return loggedInPartner;
	}

	public void setLoggedInPartner(Partner loggedInPartner) {
		this.loggedInPartner = loggedInPartner;
	}

	public Dolgozo getLoggedInWorker() {
		return loggedInWorker;
	}

	public void setLoggedInWorker(Dolgozo loggedInWorker) {
		this.loggedInWorker = loggedInWorker;
	}

	public Partner getLoggedInRenter() {
		return loggedInRenter;
	}

	public void setLoggedInRenter(Partner loggedInRenter) {
		this.loggedInRenter = loggedInRenter;
	}

	public Boolean getEmergency() {
		return emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}

}

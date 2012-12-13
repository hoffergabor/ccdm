package hu.iboard.coandco.datamagic.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
	private Partner loggedInStore = null;
	private List<Partner> stores = null;
	private List<Date> fuvDates=null;

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

	public Partner getLoggedInStore() {
		return loggedInStore;
	}

	public void setLoggedInStore(Partner loggedInStore) {
		this.loggedInStore = loggedInStore;
	}

	public List<Partner> getStores() {
		return stores;
	}

	public void setStores(List<Partner> stores) {
		this.stores = stores;
	}

	public List<Date> getFuvDates() {
		return fuvDates;
	}

	public void setFuvDates(List<Date> fuvDates) {
		this.fuvDates = fuvDates;
	}

}

package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.loginservice;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.service.fuvpartner.FuvpartnerServiceBase;
import hu.iboard.coandco.datamagic.service.partner.PartnerServiceBase;
import hu.iboard.coandco.datamagic.service.user.UserServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.vo.user.UserVO;
import hu.iboard.coandco.datamagic.westbridge.presentation.util.AbstractBaseDatabase;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

@ManagedBean(name = "loginManager")
@RequestScoped
public class LoginManager extends AbstractBaseDatabase {

	private static Logger logger = Logger.getLogger(LoginManager.class);

	@ManagedProperty(value = "#{loginManagerData}")
	private LoginManagerData data;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	@ManagedProperty(value = "#{partnerService}")
	private PartnerServiceBase partnerService;
	@ManagedProperty(value = "#{workerService}")
	private WorkerServiceBase workerService;
	@ManagedProperty(value = "#{fuvpartnerService}")
	private FuvpartnerServiceBase fuvpartnerService;

	public boolean login(String userName, String passwd) {
		try {
			UserVO user = new UserVO();
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			Dolgozo worker = getUserService().workerLogin(userName, passwd);
			if (worker != null) {
				getData().setLoggedInWorker(worker);
				session.setAttribute("loggedInWorker", worker);
				user.setUserType(WORKER);
				user.setReferenceId(worker.getDkod());
			} else {
				Partner partner = getUserService().partnerLogin(userName, passwd);
				if (partner == null) {
					return false;
				}
				if (!partner.getTelephely()) {
					getData().setLoggedInPartner(partner);
					session.setAttribute("loggedInPartner", partner);
					user.setUserType(PARTNER);
					user.setReferenceId(partner.getVevokod());
					List<Partner> stores = getPartnerService().getStoresById(partner.getVevokod());
					if (stores != null)
						if (stores.size() == 1) {
							for (Partner store : stores) {
								if (store.getVevokod() != partner.getVevokod()) {
									getData().setStores(stores);
								}
							}
						} else {
							getData().setStores(stores);
						}
				} else {
					getData().setLoggedInStore(partner);
					session.setAttribute("loggedInStore", partner);
					user.setUserType(STORE);
					user.setReferenceId(partner.getVevokod());
					List<Date> dates = getFuvpartnerService().loadAvaibleFuvDates(partner.getVevokod(), partner.getUtvonal());
					if (dates != null) {
						getData().setFuvDates(dates);
					}
				}
			}
			session.setAttribute("loggedInUser", user);
			getData().setLoggedInUser(user);
			return true;
		} catch (Exception e) {
			logger.error("LOGIN FAILED!", e);
			return false;
		}
	}

	public void logout() {
		getData().setLoggedInUser(null);
		getData().setLoggedInPartner(null);
		getData().setLoggedInWorker(null);
		getData().setLoggedInStore(null);
		getData().setStores(null);
		getData().setFuvDates(null);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.removeAttribute("loggedInUser");
		session.removeAttribute("loggedInPartner");
		session.removeAttribute("loggedInWorker");
		session.removeAttribute("loggedInStore");
		session.invalidate();
	}

	public void logoutSales() {
		getData().setLoggedInUser(null);
	}

	public void loginSalesByPartner(Integer partnerId) {
		try {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

			List<Partner> partners = getPartnerService().getPartnerById(partnerId);
			if (partners != null && partners.size() > 0) {
				getData().setLoggedInPartner(partners.get(0));
				session.setAttribute("loggedInPartner", partners.get(0));
			}

		} catch (Exception e) {
			logger.error("Login failed by sales user!", e);
		}
	}

	/**
	 * Return true if any user is logged in
	 * 
	 * @return
	 */
	public boolean isUserLoggedIn() {
		return getData().getLoggedInUser() != null;
	}

	protected String encodeWithMD5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public LoginManagerData getData() {
		return data;
	}

	public void setData(LoginManagerData data) {
		this.data = data;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public PartnerServiceBase getPartnerService() {
		return partnerService;
	}

	public void setPartnerService(PartnerServiceBase partnerService) {
		this.partnerService = partnerService;
	}

	public WorkerServiceBase getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerServiceBase workerService) {
		this.workerService = workerService;
	}

	public FuvpartnerServiceBase getFuvpartnerService() {
		return fuvpartnerService;
	}

	public void setFuvpartnerService(FuvpartnerServiceBase fuvpartnerService) {
		this.fuvpartnerService = fuvpartnerService;
	}
}

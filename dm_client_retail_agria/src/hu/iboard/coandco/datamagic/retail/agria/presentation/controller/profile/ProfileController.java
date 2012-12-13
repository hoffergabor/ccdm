package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.profile;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;
import hu.iboard.coandco.datamagic.model.township.TownShip;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.emailservice.EmailService;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressService;
import hu.iboard.coandco.datamagic.service.userservice.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean
@RequestScoped
public class ProfileController extends AbstractController {

	@ManagedProperty(value = "#{profileControllerData}")
	private ProfileControllerData data;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressService shippingAddressService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setShippingAddress(new ShippingAddress());
			getData().setShippingAddresses(new ArrayList<ShippingAddress>());
			getData().setShippingAddressId(null);
			if (getManagedCustomer() != null) {
				getData().setCustomer(getManagedCustomer());
				getData().setShippingAddresses(getShippingAddressService().getShippingsByCustomer(getData().getCustomer()));
				if (getData().getShippingAddresses() != null && getData().getShippingAddresses().size() > 0)
					getData().setShippingAddress(getData().getShippingAddresses().get(0));
				if (getManagedCustomer().getSzlFizeto() != null && getManagedCustomer().getSzlFizeto() != 0) {
					getData().setPartner(getUserService().getPartnerById(getManagedCustomer().getSzlFizeto()));
				}
				NewsLetterEmail newsLetter = getEmailService().getNewsLetterEmailByEmail(getData().getCustomer().getWebLogin());
				if (newsLetter != null) {
					if (newsLetter.getActive()) {
						getData().setActivateNewsLetter(1);
					} else {
						getData().setActivateNewsLetter(0);
					}
				}
			}
		}
	}

	public List<String> completeTown(String input) {
		List<String> list = new ArrayList<String>();
		List<TownShip> tsList = getShippingAddressService().getAllTownship(input);
		String name = "";
		for (TownShip townShip : tsList) {
			if (!name.equals(townShip.getName())) {
				list.add(townShip.getName());
			}
			name = townShip.getName();
		}
		return list;
	}

	public void addNewShippingAddress(ActionEvent event) {
		getData().setShippingAddress(new ShippingAddress());
	}

	public void saveUserDatas(ActionEvent event) {
		try {
			if (getData().getCustomer() != null) {
				getData().getCustomer().setModDatum(new Date());

				Customer customer = getUserService().saveOrUpdateCustomer(getData().getCustomer());
				if (customer == null) {
					addErrorMessage("", "Személyes adatok mentése sikertelen!");
					logger.error("ERROR BY SAVING CUSTOMER USER DATAS ON PROFILE PAGE!");
					return;
				}
			}
			if (getData().getPartner() != null) {
				getData().getPartner().setModdatum(new Date());
			}
			if (getData().getShippingAddress() == null)
				return;

			if (getData().getShippingAddress().getInsDate() == null)
				getData().getShippingAddress().setInsDate(new Date());
			getData().getShippingAddress().setModDate(new Date());
			if (getData().getCustomer() != null) {
				getData().getShippingAddress().setCustomer(getData().getCustomer());
			}
			if (getData().getPartner() != null)
				getData().getShippingAddress().setPartner(getData().getPartner());
			ShippingAddress shipping = getShippingAddressService().saveOrUpdateShipping(getData().getShippingAddress());
			if (shipping == null) {
				addErrorMessage("", "Szállítási adatok mentése sikertelen!");
				logger.error("ERROR BY SAVING SHIPPING DATAS ON PROFILE PAGE!");
				return;
			}

			if (getData().getCustomer() != null) {
				NewsLetterEmail newsLetter = getEmailService().getNewsLetterEmailByEmail(getData().getCustomer().getWebLogin());
				if (newsLetter == null) {
					newsLetter = new NewsLetterEmail();
					newsLetter.setCreated(new Date());
					newsLetter.setEmail(getData().getCustomer().getWebLogin());
					newsLetter.setCustomer(getData().getCustomer());
				}

				if (getData().getActivateNewsLetter() == 1) {
					newsLetter.setActive(true);
				} else {
					newsLetter.setActive(false);
				}
				newsLetter = getEmailService().saveOrUpdateNewsLetterEmail(newsLetter);
				if (newsLetter == null) {
					addErrorMessage("", "Hírlevél adatok mentése sikertelen!");
					logger.error("ERROR BY SUBSCRIBE TO NEWSLETTER EMAIL");
					return;
				}
			}

			getUserService().saveOrUpdatePartner(getData().getPartner());
			addInfoMessage("", "Adatok mentése sikeres");
			boolean contains = false;
			for (ShippingAddress shippingAddress : getData().getShippingAddresses()) {
				if (shippingAddress.getShippingId().equals(shipping.getShippingId()))
					contains = true;
			}
			if (!contains)
				getData().getShippingAddresses().add(shipping);

		} catch (Exception e) {
			addErrorMessage("", "Hiba történt az adatok mentése közben!");
			logger.error("ERROR BY SAVING DATAS ON PROFILE PAGE!", e);
		}
	}
	
	public String changePasswordAction() {

		if (getData().getWebPassw2().equals("") || getData().getWebPassw1().equals("")) {
			addErrorMessage("", "Nincs kitöltve a jelszó mező!");
			return null;
		}
		if (!getData().getWebPassw1().equals(getData().getWebPassw2())) {
			addErrorMessage("", "A két jelszó nem egyezik meg!");
			getData().setWebPassw1("");
			getData().setWebPassw2("");
			return null;
		}
		try {
			if (getData().getCustomer() != null) {
				
				getData().getCustomer().setWebPassw(encodeWithMD5(getData().getWebPassw1()));
				Customer customer = getUserService().saveOrUpdateCustomer(getData().getCustomer());
				if (customer == null) {
					addInfoMessage("", "Személyes adatok mentése sikertelen!");
					logger.error("ERROR BY SAVING CUSTOMER USER DATAS ON PROFILE PAGE!");
				}
				else {
					addErrorMessage("", "Az új jelszót mentettük!");
					logger.info("USER PASSWORD SAVED!");
				}
			}
			
		} catch (Exception e) {
			logger.error("Error by update user", e);
			addFatalMessage("", "Jelszó módosítás sikertelen!");
			getData().setWebPassw1("");
			getData().setWebPassw2("");
			return null;
		}
		getData().setWebPassw1("");
		getData().setWebPassw2("");
		return null;
	}

	public void changeShippingAddress(ActionEvent event) {
		if (getData().getShippingAddressId() != null)
			getData().setShippingAddress(getShippingAddressService().getShippingById(getData().getShippingAddressId()));
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ProfileControllerData getData() {
		return data;
	}

	public void setData(ProfileControllerData data) {
		this.data = data;
	}

	public ShippingAddressService getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressService shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}

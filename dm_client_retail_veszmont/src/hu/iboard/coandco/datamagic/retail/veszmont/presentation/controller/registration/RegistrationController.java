package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.registration;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;
import hu.iboard.coandco.datamagic.model.township.TownShip;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.emailservice.EmailServiceBase;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressServiceBase;
import hu.iboard.coandco.datamagic.service.userservice.UserServiceBase;

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
public class RegistrationController extends AbstractController {

	@ManagedProperty(value = "#{registrationControllerData}")
	private RegistrationControllerData data;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressServiceBase shippingAddressService;
	@ManagedProperty(value = "#{emailService}")
	private EmailServiceBase emailService;
	@ManagedProperty(value = "#{successRegistrationController}")
	private SuccessRegistrationController successRegistrationController;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setPassword1("");
			getData().setPassword2("");
			//tData().setCustomer(new Customer());
			getData().setPartner(new Partner());
			getData().setShippingAddress(new ShippingAddress());
			getData().setActivateNewsLetter(1);
			getData().setCaptcha("");
			//getData().setActivateInvoice(0);
			getData().setActivateInvoice(false);
			getData().setPartner(new Partner());
			getData().setRules(false);
		}
	}

	/*
	public String registrationCustomer() {
		if (!getData().getPassword1().equals(getData().getPassword2())) {
			addErrorMessage("Hiba", "A két jelszó nem egyezik.");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}
		if (!getData().getRules()) {
			addErrorMessage("Hiba", "Regisztrációhoz, kérjük fogadja el a vásárlási feltételeket.");
			return null;
		}
				
		Customer checkCustom = getUserService().getCustomerByEmail(getData().getCustomer().getWebLogin());
		if (checkCustom != null) {
			addErrorMessage("Hiba", "Van már ilyen email cím az adatbázisban. Kérem, válasszon másik e-mail címet!");
			return null;
		}
		try {
			getData().getCustomer().setWebPassw(encodeWithMD5(getData().getPassword1()));
			getData().getCustomer().setInsDatum(new Date());
			getData().getCustomer().setModDatum(new Date());
			getData().getCustomer().setIrsz(getData().getShippingAddress().getZipCode());
			getData().getCustomer().setCim(getData().getShippingAddress().getAddress());
			getData().getCustomer().setVaros(getData().getShippingAddress().getTownShip());
			getData().getCustomer().setIsDeleted(false);
			getData().getCustomer().setSzlFizeto(0);
			Integer id = getUserService().getLastRecordFromCustomers();
			if (id == null)
				//return null;
				id = 0;
			getData().getCustomer().setLakosKod(id + 1);
			Customer customer = getUserService().saveOrUpdateCustomer(getData().getCustomer());
			if (customer == null) {
				addErrorMessage("Hiba történt.", "A regisztráció sikertelen.");
				logger.error("ERROR BY SAVING CUSTOMER DATAS");
				return null;
			}
			getSuccessRegistrationController().getData().setCustomer(customer);
			
			getData().getShippingAddress().setModDate(new Date());
			getData().getShippingAddress().setInsDate(new Date());
			getData().getShippingAddress().setCustomer(customer);
			ShippingAddress address = getShippingAddressService().saveOrUpdateShipping(getData().getShippingAddress());
			if (address == null) {
				addErrorMessage("", "A szállítási cím mentése sikertelen.");
				logger.error("ERROR BY SAVING CUSTOMER SHIPPING ADDRESS");
				return "failedregistration";
			}
			getSuccessRegistrationController().getData().setShippingAddress(address);
			if (getData().getActivateNewsLetter() == 1) {
				NewsLetterEmail newsLetter = new NewsLetterEmail();
				newsLetter.setActive(true);
				newsLetter.setCreated(new Date());
				newsLetter.setEmail(customer.getWebLogin());
				newsLetter.setCustomer(customer);
				newsLetter = getEmailService().saveOrUpdateNewsLetterEmail(newsLetter);
				if (newsLetter == null)
					logger.error("ERROR BY SUBSCRIBE TO NEWSLETTER EMAIL");
			}
			//if (getData().getActivateInvoice() == 1) {
			if (getData().getActivateInvoice()) {
				getData().getPartner().setTel1(getData().getCustomer().getTel());
				getData().getPartner().setEmail(getData().getCustomer().getWebLogin());
				getData().getPartner().setWeblogin(getData().getCustomer().getWebLogin());
				getData().getPartner().setWebpassw(getData().getCustomer().getWebPassw());
				getData().getPartner().setSzirsz(getData().getShippingAddress().getZipCode());
				getData().getPartner().setSzvaros(getData().getShippingAddress().getTownShip());
				getData().getPartner().setSzcim(getData().getShippingAddress().getAddress());
				getData().getPartner().setVevokod(getUserService().getLastEmptyPartnerId());
				getUserService().saveOrUpdatePartner(getData().getPartner());
				getSuccessRegistrationController().getData().setPartner(getData().getPartner());
				customer.setSzlFizeto(getData().getPartner().getVevokod());
				getUserService().saveOrUpdateCustomer(customer);

			}
			addInfoMessage("", "A regisztráció sikeres.");
			String emailBody = "Tisztelt " + getData().getCustomer().getNev() + "!\n\n";
			emailBody += "Köszönjük, hogy regisztrálta magát webshopunkban!\n\n";
			emailBody += "Belépési azonosítója: " + getData().getCustomer().getWebLogin() + "\n";
			emailBody += "Belépési jelszava: " + getData().getCustomer().getWebPassw() + "\n\n";
			emailBody += "Üdvözlettel: Agria KFT";
			boolean isSuccessEmailSending = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, ORDEREMAIL, "hoffergabor@gmail.com",
					"Sikeres Veszmont webshop regisztráció", emailBody);
			if (isSuccessEmailSending) {
				addInfoMessage("", "Rendelést visszaigazoló email elküldve.");
			}
			getData().setPassword1("");
			getData().setPassword2("");
			getData().setCustomer(new Customer());
			getData().setShippingAddress(new ShippingAddress());
			getData().setPartner(new Partner());
			getData().setActivateNewsLetter(0);
			getData().setCaptcha("");
		} catch (Exception e) {
			logger.error("ERROR BY REGISTRATION!", e);
			addErrorMessage("Hiba történt!", "A regisztráció sikertelen.");
			logger.error("ERROR BY SAVING CUSTOMER DATAS");
			return "failedregistration";
		}
		return "successregistration";
	}
	*/
	public String registration() {
		if (!getData().getPassword1().equals(getData().getPassword2())) {
			addErrorMessage("Hiba", "A két jelszó nem egyezik.");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}
		if (!getData().getRules()) {
			addErrorMessage("Hiba", "Regisztrációhoz, kérjük fogadja el a vásárlási feltételeket.");
			return null;
		}
				
		Partner checkPartner = getUserService().getPartnerByEmail(getData().getPartner().getWeblogin());
		if (checkPartner != null) {
			addErrorMessage("Hiba", "Van már ilyen e-mail cím az adatbázisban. Kérem, válasszon másik e-mail címet!");
			return null;
		}
		try {
			getData().getPartner().setWebpassw(encodeWithMD5(getData().getPassword1()));
			getData().getPartner().setInsdatum(new Date());
			getData().getPartner().setModdatum(new Date());
			getData().getPartner().setIrsz(getData().getShippingAddress().getZipCode());
			getData().getPartner().setCim(getData().getShippingAddress().getAddress());
			getData().getPartner().setVaros(getData().getShippingAddress().getTownShip());
			//getData().getPartner().setIsdeleted(false);
			getData().getPartner().setSzlfizeto(0);
			Integer id = getUserService().getLastEmptyPartnerId();
			if (id == null)
				//return null;
				id = 90000000;
			getData().getPartner().setVevokod(id + 1);
			Partner partner = getUserService().saveOrUpdatePartner(getData().getPartner());
			if (partner == null) {
				addErrorMessage("Hiba történt!", "A regisztráció sikertelen.");
				logger.error("ERROR BY SAVING PARTNER DATAS");
				return null;
			}
			getSuccessRegistrationController().getData().setPartner(partner);
			getData().getShippingAddress().setModDate(new Date());
			getData().getShippingAddress().setInsDate(new Date());
			getData().getShippingAddress().setPartner(partner);
			getData().getShippingAddress().setCustomer(null);
			ShippingAddress address = getShippingAddressService().saveOrUpdateShipping(getData().getShippingAddress());
			if (address == null) {
				addErrorMessage("", "A szállítási cím mentése sikertelen.");
				logger.error("ERROR BY SAVING PARTNER SHIPPING ADDRESS");
				return "failedregistration";
			}
			getSuccessRegistrationController().getData().setShippingAddress(address);
			if (getData().getActivateNewsLetter() == 1) {
				NewsLetterEmail newsLetter = new NewsLetterEmail();
				newsLetter.setActive(true);
				newsLetter.setCreated(new Date());
				newsLetter.setEmail(partner.getWeblogin());
				newsLetter.setPartner(partner);
				newsLetter = getEmailService().saveOrUpdateNewsLetterEmail(newsLetter);
				if (newsLetter == null)
					logger.error("ERROR BY SUBSCRIBE TO NEWSLETTER EMAIL");
			}
			if (getData().getActivateInvoice()) {
				getData().getPartner().setTel1(getData().getPartner().getTel1());
				getData().getPartner().setEmail(getData().getPartner().getWeblogin());
				getData().getPartner().setWeblogin(getData().getPartner().getWeblogin());
				getData().getPartner().setWebpassw(getData().getPartner().getWebpassw());
				getData().getPartner().setSzirsz(getData().getShippingAddress().getZipCode());
				getData().getPartner().setSzvaros(getData().getShippingAddress().getTownShip());
				getData().getPartner().setSzcim(getData().getShippingAddress().getAddress());
				getData().getPartner().setVevokod(getUserService().getLastEmptyPartnerId());
				getUserService().saveOrUpdatePartner(getData().getPartner());
				getSuccessRegistrationController().getData().setPartner(getData().getPartner());
				partner.setSzlfizeto(getData().getPartner().getVevokod());
				getUserService().saveOrUpdatePartner(partner);

			}
			//TODO
			addInfoMessage("", "A regisztráció sikeres.");
			/*
			String emailBody = "Tisztelt " + getData().getPartner().getNev() + "!\n\n";
			emailBody += "Köszönjük, hogy regisztrálta magát webshopunkban!\n\n";
			emailBody += "Belépési azonosítója: " + getData().getPartner().getWeblogin() + "\n";
			emailBody += "Belépési jelszava: " + getData().getPartner().getWebpassw() + "\n\n";
			emailBody += "Üdvözlettel: Agria KFT";
			boolean isSuccessEmailSending = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, ORDEREMAIL, "hoffergabor@gmail.com",
					"Sikeres Veszmont webshop regisztráció", emailBody);
			if (isSuccessEmailSending) {
				addInfoMessage("", "Rendelést visszaigazoló email elküldve.");
			}
			*/
			getData().setPassword1("");
			getData().setPassword2("");
			getData().setPartner(new Partner());
			getData().setShippingAddress(new ShippingAddress());
			getData().setPartner(new Partner());
			getData().setActivateNewsLetter(0);
			getData().setCaptcha("");
		} catch (Exception e) {
			logger.error("ERROR BY REGISTRATION!", e);
			addErrorMessage("Hiba történt.", "A regisztráció sikertelen.");
			logger.error("ERROR BY SAVING PARTNER DATAS");
			return "failedregistration";
		}
		return "successregistration";
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

	public void activateInvoice(ActionEvent event) {
		getData().setPartner(new Partner());
	}

	public String copyDatas() {
		getData().getPartner().setNev(getData().getPartner().getNev());
		getData().getPartner().setIrsz(getData().getShippingAddress().getZipCode());
		getData().getPartner().setVaros(getData().getShippingAddress().getTownShip());
		getData().getPartner().setCim(getData().getShippingAddress().getAddress());
		return null;
	}

	public RegistrationControllerData getData() {
		return data;
	}

	public void setData(RegistrationControllerData data) {
		this.data = data;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public ShippingAddressServiceBase getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressServiceBase shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

	public SuccessRegistrationController getSuccessRegistrationController() {
		return successRegistrationController;
	}

	public void setSuccessRegistrationController(SuccessRegistrationController successRegistrationController) {
		this.successRegistrationController = successRegistrationController;
	}

}

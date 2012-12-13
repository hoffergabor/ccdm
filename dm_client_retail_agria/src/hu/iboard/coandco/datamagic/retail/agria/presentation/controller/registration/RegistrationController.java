package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.registration;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.model.partner.Partner;
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
public class RegistrationController extends AbstractController {

	@ManagedProperty(value = "#{registrationControllerData}")
	private RegistrationControllerData data;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressService shippingAddressService;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;
	@ManagedProperty(value = "#{successRegistrationController}")
	private SuccessRegistrationController successRegistrationController;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setPassword1("");
			getData().setPassword2("");
			getData().setCustomer(new Customer());
			getData().setShippingAddress(new ShippingAddress());
			getData().setActivateNewsLetter(1);
			getData().setCaptcha("");
			getData().setActivateInvoice(0);
			getData().setPartner(new Partner());
			getData().setRules(false);
		}
	}

	public String registration() {
		if (!getData().getPassword1().equals(getData().getPassword2())) {
			addErrorMessage("Hiba", "A két jelszó nem egyezik.");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}
		if (!getData().getRules()) {
			addErrorMessage("Hiba", "Regisztrációhoz, kérjük, fogadja el a vásárlási feltételeket!");
			return null;
		}
		Customer checkCustom = getUserService().getCustomerByEmail(getData().getCustomer().getWebLogin());
		if (checkCustom != null) {
			addErrorMessage("Hiba", "Van már ilyen e-mail cím az adatbázisban. Kérem, válasszon másik e-mail címet!");
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
				return null;
			getData().getCustomer().setLakosKod(id + 1);
			Customer customer = getUserService().saveOrUpdateCustomer(getData().getCustomer());
			if (customer == null) {
				addErrorMessage("Hiba történt", "A regisztráció sikertelen.");
				logger.error("ERROR BY SAVING CUSTOMER DATAS");
				return null;
			}
			getSuccessRegistrationController().getData().setCustomer(customer);
			getData().getShippingAddress().setModDate(new Date());
			getData().getShippingAddress().setInsDate(new Date());
			getData().getShippingAddress().setCustomer(customer);
			ShippingAddress address = getShippingAddressService().saveOrUpdateShipping(getData().getShippingAddress());
			if (address == null) {
				addErrorMessage("", "A szállítási cím mentése sikertelen!");
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
			if (getData().getActivateInvoice() == 1) {
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
			addInfoMessage("", "A regisztráció sikeres volt.");
			String emailBody = "Tisztelt " + getData().getCustomer().getNev() + "!</br>";
			emailBody += "Köszönjük, hogy regisztrálta magát webshopunkban.</br></br>";
			emailBody += "Belépési azonosítója: " + getData().getCustomer().getWebLogin() + "</br>";
			emailBody += "Belépési jelszava: " + getData().getCustomer().getWebPassw() + "</br></br>";
			emailBody += "Üdvözlettel: Agria KFT";
			boolean isSuccessEmailSending = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, ORDEREMAIL, getData().getCustomer().getWebLogin(),
					"Sikeres Agria webshop regisztráció", emailBody);
			if (isSuccessEmailSending) {
				addInfoMessage("", "Regisztrációt visszaigazoló email elküldve.");
			}
			getLoginManager().login(getData().getCustomer().getWebLogin(), getData().getCustomer().getWebPassw());
			getData().setPassword1("");
			getData().setPassword2("");
			getData().setCustomer(new Customer());
			getData().setShippingAddress(new ShippingAddress());
			getData().setPartner(new Partner());
			getData().setActivateNewsLetter(0);
			getData().setCaptcha("");
		} catch (Exception e) {
			logger.error("ERROR BY REGISTRATION!", e);
			addErrorMessage("Hiba történt.", "A regisztráció sikertelen!");
			logger.error("ERROR BY SAVING CUSTOMER DATAS");
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
		getData().getPartner().setNev(getData().getCustomer().getNev());
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
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

	public SuccessRegistrationController getSuccessRegistrationController() {
		return successRegistrationController;
	}

	public void setSuccessRegistrationController(SuccessRegistrationController successRegistrationController) {
		this.successRegistrationController = successRegistrationController;
	}

}

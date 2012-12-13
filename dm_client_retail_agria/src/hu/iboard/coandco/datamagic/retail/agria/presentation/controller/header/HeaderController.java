package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.header;

import java.util.Date;
import java.util.List;

import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.emailservice.EmailService;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "headerController")
@RequestScoped
public class HeaderController extends AbstractController{
	
	@ManagedProperty(value = "#{headerControllerData}")
	private HeaderControllerData data;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressService shippingAddressService;
	
	
	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setNewsLetterEmail(null);
			getData().setForgottenPasswordEmail(null);
			getData().setZipcode(null);
		}
	}
	
	public void subsciptionToNewsLetter(ActionEvent event) {
		if (getData().getNewsLetterEmail() == null || getData().getNewsLetterEmail().equals("")) {
			addErrorMessage("", "A hírlevélhez való feliratkozáshoz, kérjük adja meg email címét.");
			return;
		}
		try {
			NewsLetterEmail email = getEmailService().getNewsLetterEmailByEmail(getData().getNewsLetterEmail());
			if (email != null) {
				addFatalMessage("", "A megadott email cím már használatban van!");
				return;
			}
			NewsLetterEmail newsLetterEmail = new NewsLetterEmail();
			newsLetterEmail.setEmail(getData().getNewsLetterEmail());
			newsLetterEmail.setCreated(new Date());
			newsLetterEmail.setActive(true);
			newsLetterEmail = getEmailService().saveOrUpdateNewsLetterEmail(newsLetterEmail);
			if (newsLetterEmail == null) {
				addErrorMessage("", "Hiba történt a hírlevélre való feliratkozáskor!");
				return;
			}
			addInfoMessage("", "Köszönjük, hogy feliratkozott a hírlevelünkre.");
			getData().setNewsLetterEmail("");
		} catch (Exception e) {
			logger.error("ERROR BY SUBSCRIPTION TO NEWSLETTER", e);
			addErrorMessage("", "Hiba történt a hírlevélre való feliratkozáskor.");
			return;
		}
	}
	
	public void checkZipCode() {

		if (getData().getZipcode() == null || getData().getZipcode().equals("")) {
			addErrorMessage("", "Kérem adja meg az irányítószámot!");
			return;
		}

		List<Teruletcs> teruletcsk = getShippingAddressService().getTeruletcsByIrsz(Integer.valueOf(getData().getZipcode()));
		if (teruletcsk == null) {
			addErrorMessage("", "A megadott irányitószám alapján nincs lehetőség szállításra!");
			return;
		}
		Teruletcs terulet = null;
		for (Teruletcs teruletcs : teruletcsk) {
			if (teruletcs.getTerulet() == 3 || teruletcs.getTerulet() == 4) {
				terulet = teruletcs;
			}
		}
		if (terulet == null) {
			addErrorMessage("", "A megadott irányitószám alapján nincs lehetőség szállításra!");
			return;
		} else
			addInfoMessage("", "Házhozszállítás lehetséges a megadott irányitószám alapján!");

	}

	public ShippingAddressService getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressService shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}

	public HeaderControllerData getData() {
		return data;
	}

	public void setData(HeaderControllerData data) {
		this.data = data;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}

package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.index;

import java.math.BigInteger;
import java.security.SecureRandom;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.contentservice.ContentService;
import hu.iboard.coandco.datamagic.service.emailservice.EmailService;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.userservice.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "indexController")
@RequestScoped
public class IndexController extends AbstractController {

	@ManagedProperty(value = "#{indexControllerData}")
	private IndexControllerData data;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{contentService}")
	private ContentService contentService;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			// QueryParamsVO queryParamsVO = new
			// QueryParamsVO(getManagedPartner(),
			// ConfigurationHandler.getDefaultArnevKod(),ConfigurationHandler.getAdaptDiscount());
			getData().setProducts(getProductService().getKiemeltProducts(getQueryParamsVO()));
			getData().setPasswordEmail(null);
		}
	}

	public void sendNewPassword() {
		try {
			if (getData().getPasswordEmail() == null || getData().getPasswordEmail().length() == 0) {
				addErrorMessage("Hiba", "Email cím megadása kötelező!");
				return;
			}
			Customer customer = getUserService().getCustomerByEmail(getData().getPasswordEmail());
			if (customer == null) {
				addErrorMessage("Hiba", "Nincs ilyen email cím regisztrálva!");
				return;
			}

			SecureRandom random = new SecureRandom();
			String newPass = new BigInteger(130, random).toString(32).substring(0, 14);

			customer.setWebPassw(encodeWithMD5(newPass));
			customer = getUserService().saveOrUpdateCustomer(customer);
			if (customer == null) {
				addErrorMessage("Hiba", "Technikai hiba történt, kérjük próbálja meg később!");
				return;
			}

			String emailBody = "Új jelszót igényelt az Agria Drink webshop oldalán.<br/>";

			/*boolean success = getEmailService().sendNewPasswordEmail("smtp.gmail.com", "587", "info@totumfactum.hu", "a123b123c1", "info@totumfactum.hu",
					getData().getPasswordEmail(), "Új jelszó igénylés", emailBody, newPass);*/

			boolean success = getEmailService().sendNewPasswordEmail("agriadrink.hu", "25", "webshop@agriadrink.hu", "W3bSh0p_!", "webshop@agriadrink.hu",
					getData().getPasswordEmail(), "Új jelszó igénylés", emailBody, newPass);
			if (!success) {
				addErrorMessage("Technikai hiba történt az új jelszó küldésekor!", "Technikai hiba történt az új jelszó küldésekor!");
				logger.error("ERROR BY SENDING NEW PASSWORD EMAIL! EMAIL=" + getData().getPasswordEmail());
				return;
			}
			addInfoMessage("Új jelszót küldtük a megadott email címre!", "Új jelszót küldtük a megadott email címre!");
			logger.info("NEW PASSWORD SENDING WAS SUCCESSFUL! EMAIL=" + getData().getPasswordEmail());
		} catch (Exception e) {
			addErrorMessage("Technikai hiba történt az új jelszó küldésekor!", "Technikai hiba történt az új jelszó küldésekor!");
			logger.error("ERROR BY SENDING NEW PASSWORD EMAIL! EMAIL=" + getData().getPasswordEmail());
			return;
		}
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public IndexControllerData getData() {
		return data;
	}

	public void setData(IndexControllerData data) {
		this.data = data;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

}

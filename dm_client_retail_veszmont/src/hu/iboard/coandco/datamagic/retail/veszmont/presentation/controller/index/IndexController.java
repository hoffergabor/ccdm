package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.index;

import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.contentservice.ContentServiceBase;
import hu.iboard.coandco.datamagic.service.emailservice.EmailServiceBase;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.userservice.UserServiceBase;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "indexController")
@RequestScoped
public class IndexController extends AbstractController {

	@ManagedProperty(value = "#{indexControllerData}")
	private IndexControllerData data;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	@ManagedProperty(value = "#{newsLetterService}")
	private EmailServiceBase emailService;
	@ManagedProperty(value = "#{contentService}")
	private ContentServiceBase contentService;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setNewsLetterEmail(null);
			getData().setForgottenPasswordEmail(null);
			QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), null);
			getData().setProducts(getProductService().getKiemeltProducts(queryParamsVO));
		}
	}

	public void subsciptionToNewsLetter(ActionEvent event) {
		if (getData().getNewsLetterEmail() == null || getData().getNewsLetterEmail().equals("")) {
			addErrorMessage("", "A hírlevélhez való feliratkozáshoz kérjük, adja meg email címét.");
			return;
		}
		try {
			NewsLetterEmail email = getEmailService().getNewsLetterEmailByEmail(getData().getNewsLetterEmail());
			if (email != null) {
				addFatalMessage("", "A megadott email cím már használatban van.");
				return;
			}
			NewsLetterEmail newsLetterEmail = new NewsLetterEmail();
			newsLetterEmail.setEmail(getData().getNewsLetterEmail());
			newsLetterEmail.setCreated(new Date());
			newsLetterEmail.setActive(true);
			newsLetterEmail = getEmailService().saveOrUpdateNewsLetterEmail(newsLetterEmail);
			if (newsLetterEmail == null) {
				addErrorMessage("", "Hiba történt a hírlevélre való feliratkozáskor.");
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

	public void getNewPassword(ActionEvent event) {
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public IndexControllerData getData() {
		return data;
	}

	public void setData(IndexControllerData data) {
		this.data = data;
	}

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

	public ContentServiceBase getContentService() {
		return contentService;
	}

	public void setContentService(ContentServiceBase contentService) {
		this.contentService = contentService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

}

package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.order;

import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;
import hu.iboard.coandco.datamagic.model.township.TownShip;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.login.LoginManager;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.emailservice.EmailServiceBase;
import hu.iboard.coandco.datamagic.service.orderservice.OrderServiceBase;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressServiceBase;
import hu.iboard.coandco.datamagic.service.userservice.UserServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.soap.api.BasicAmountType;
import com.paypal.soap.api.CurrencyCodeType;
import com.paypal.soap.api.PaymentActionCodeType;
import com.paypal.soap.api.PaymentDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestDetailsType;
import com.paypal.soap.api.SetExpressCheckoutRequestType;
import com.paypal.soap.api.SetExpressCheckoutResponseType;

@ManagedBean(name = "orderController")
@RequestScoped
public class OrderController extends AbstractController {

	@ManagedProperty(value = "#{orderControllerData}")
	private OrderControllerData data;
	@ManagedProperty(value = "#{shippingAddressService}")
	private ShippingAddressServiceBase shippingAddressService;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{orderService}")
	private OrderServiceBase orderService;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;
	@ManagedProperty(value = "#{loginManager}")
	private LoginManager loginManager;
	@ManagedProperty(value = "#{emailService}")
	private EmailServiceBase emailService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setShippingDate(calculateShippingDate());
			if (getManagedPartner().getSzlfizeto() != 0) {
				getData().setPartner(getUserService().getPartnerById(getManagedPartner().getSzlfizeto()));
				getData().setInvoiceZipcode(getData().getPartner().getIrsz());
				getData().setInvoiceCity(getData().getPartner().getVaros());
				getData().setInvoiceAddress(getData().getPartner().getCim());
				getData().setInvoiceName(getData().getPartner().getNev());
				getData().setActivateInvoice(1);
			}
			if (getManagedPartner() != null) {
				getData().setShippingAddresses(getShippingAddressService().getShippingsByPartner(getManagedPartner()));
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

	public void changeShippingAddress(ActionEvent event) {
		if (getData().getShippingAddressId() != null)
			getData().setShippingAddress(getShippingAddressService().getShippingById(getData().getShippingAddressId()));
	}
	
	public String saveOrder() {
		if (!getData().getRules()) {
			addErrorMessage("", "Rendeléshez kérjük, fogadja el a vásárlási feltételeket.");
			return null;
		}
		if (getCartController().getData().getItems() == null || getCartController().getData().getItems().size() == 0) {
			addErrorMessage("", "A kosár üres. Rendeléshez kérjük, válasszon terméket.");
			return null;
		}
		if (getData().getErrorMessage() != null) {
			addErrorMessage(
					"",
					String.format("A rendelés leadásához legalább %d Ft értékben szükséges vásárolnia. Hiányzik még: ",
							ConfigurationHandler.getMinimumOrderValue())
							+ String.format("%d Ft", (ConfigurationHandler.getMinimumOrderValue() - getCartController().getData().getAllBrutto().intValue())));
			return null;
		}

		try {
			if (getManagedPartner().getSzlfizeto() == 0 && getData().getActivateInvoice() == 1) {
				getData().setPartner(new Partner());
				getData().getPartner().setNev(getData().getInvoiceName());
				getData().getPartner().setIrsz(getData().getInvoiceZipcode());
				getData().getPartner().setVaros(getData().getInvoiceCity());
				getData().getPartner().setCim(getData().getInvoiceAddress());
				getData().getPartner().setTel1(getManagedPartner().getTel1());
				getData().getPartner().setEmail(getManagedPartner().getWeblogin());
				getData().getPartner().setWeblogin(getManagedPartner().getWeblogin());
				getData().getPartner().setWebpassw(getManagedPartner().getWebpassw());
				getData().getPartner().setVevokod(getUserService().getLastEmptyPartnerId());
				getData().setPartner(getUserService().saveOrUpdatePartner(getData().getPartner()));
				// getManagedCustomer().setSzlFizeto(getData().getPartner().getVevokod());
				getUserService().saveOrUpdatePartner(getManagedPartner());
				Partner partner = getUserService().saveOrUpdatePartner(getData().getPartner());
				if (partner == null) {
					addErrorMessage("", "Hiba történt. Rendelés sikertelen.");
					logger.error("ERROR BY SAVING PARTNER ON ORDER PAGE!");
					return "failedorder";
				}
			}

			List<Aru> products = new ArrayList<Aru>();
			List<BigDecimal> mennys = new ArrayList<BigDecimal>();
			for (ProductVO tmp : getCartController().getData().getItems()) {
				Aru aru = getProductService().getProductById(tmp.getArukod());
				mennys.add(tmp.getMenny());
				products.add(aru);
			}

			getData().getOrder().setSirsz(getData().getShippingAddress().getZipCode());
			getData().getOrder().setSvaros(getData().getShippingAddress().getTownShip());
			getData().getOrder().setScim(getData().getShippingAddress().getAddress());
			getData().getOrder().setVirsz(getData().getShippingAddress().getZipCode());
			getData().getOrder().setVvaros(getData().getShippingAddress().getTownShip());
			getData().getOrder().setVcim(getData().getShippingAddress().getAddress());
			getData().getOrder().setMegj("árut átvevő személy neve: " + getData().getShippingAddress().getContactName());

			if (getManagedPartner() != null && getManagedPartner().getSzlfizeto() == 0) {
				getData().getOrder().setVnev(getManagedPartner().getNev());
				getData().getOrder().setStelefon(getManagedPartner().getTel1());
				getData().getOrder().setVadoszam("");
				getData().getOrder().setVfolyszam("");
				// getData().getOrder().setLakosvevo(getManagedCustomer().getLakosKod());
				// getData().getOrder().setLakosvevo(getManagedPartner().getVevokod());
				getData().getOrder().setVevokod(getManagedPartner().getVevokod());
				getData().getOrder().setSnev("Lakossági vevő");
				getData().getOrder().setStatus(255);
			}

			if (getManagedPartner() != null && getManagedPartner().getSzlfizeto() != 0) {
				getData().getOrder().setVnev(getData().getPartner().getNev());
				getData().getOrder().setStelefon(getData().getPartner().getTel1());
				getData().getOrder().setVadoszam(getData().getPartner().getAdoszam());
				getData().getOrder().setVfolyszam(getData().getPartner().getFolyszam());
				getData().getOrder().setLakosvevo(0);
				getData().getOrder().setVevokod(getData().getPartner().getVevokod());
				getData().getOrder().setSnev(getData().getPartner().getNev());
				getData().getOrder().setStatus(0);
			}
			getData().getOrder().setFizmod(getData().getPayOption());
			getData().getOrder().setEsedkelt(getData().getShippingDate());

			Rendel rendel = getOrderService().saveOrUpdateOrder(getData().getOrder(), products, mennys, "ear");
			if (rendel == null) {
				addErrorMessage("", "Hiba tértént. Rendelés sikertelen.");
				logger.error("ERROR BY SAVING ORDER ON ORDER PAGE!");
				return "failedorder";
			}

			addInfoMessage("", "Rendelését sikeresen rögzítettük.");

			getCartController().getData().setItems(new ArrayList<ProductVO>());
			getCartController().getData().setItemsSize(0);
			getCartController().getData().setAllBrutto(new BigDecimal(0));

			String emailBody = "";
			emailBody += "Név: " + rendel.getVnev() + "\n";
			emailBody += "Rendelés tételei: \n\n";
			List<Rendtet> rendtets = getOrderService().getOrderItemsByOrderId(rendel.getSorszam());
			if (rendtets != null) {
				for (Rendtet rendtet : rendtets) {
					emailBody += rendtet.getAmenny() + " db" + rendtet.getAmegn();
					emailBody += "Nettó egységár: " + rendtet.getAertek();
					emailBody += "Nettó ár: " + rendtet.getNertek();
					emailBody += "Bruttó ár: " + rendtet.getBertek() + "\n\n";
				}
			}
			emailBody += "Fizetendő végösszeg: " + rendel.getBrutto() + "\n\n";
			emailBody += " Szállítás címe:" + rendel.getSirsz() + " " + rendel.getSvaros() + ", " + rendel.getScim() + "\n";
			emailBody += "Fizetés típusa: " + rendel.getFizmod() + "\n\n";
			boolean isSuccessEmailSending = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, ORDEREMAIL, "hoffergabor@gmail.com",
					"Rendelés visszaigazolása", emailBody);
			if (isSuccessEmailSending) {
				addInfoMessage("", "Rendelést visszaigazoló email elküldve.");
			}
			if (getData().getPayOption().equals("Paypal")) {
				/*
				 * session.setAttribute("Payment_Amount",
				 * rendel.getBrutto().toString()); String url =
				 * "/expresscheckout"; FacesContext context =
				 * FacesContext.getCurrentInstance();
				 * context.getExternalContext().dispatch(url);
				 */
				
				String token = setExpressCheckout(rendtets, rendel);
				if (token == null)
					return null;
				FacesContext context = FacesContext.getCurrentInstance();
				context.getExternalContext().redirect(PAYPAL_URL + token);
				context.responseComplete();
				return null;


			}
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt. Rendelés sikertelen.");
			logger.error("ERROR ON ORDER PAGE!", e);
			return "failedorder";
		}
		return "successorder";
	}

	public void copyShippingDatas(ActionEvent event) {
		if (getData().getShippingAddress() != null) {
			getData().setInvoiceName(getManagedPartner().getNev());
			getData().setInvoiceZipcode(getData().getShippingAddress().getZipCode());
			getData().setInvoiceCity(getData().getShippingAddress().getTownShip());
			getData().setInvoiceAddress(getData().getShippingAddress().getAddress());
		}
	}

	@SuppressWarnings("deprecation")
	private Date calculateShippingDate() {
		Date now = new Date();
		Calendar date = Calendar.getInstance();
		if (now.getHours() < 12) {
			date.add(Calendar.DATE, 1);
		} else {
			date.add(Calendar.DATE, 2);
		}
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date.add(Calendar.DATE, 2);
		}
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date.add(Calendar.DATE, 1);
		}
		return date.getTime();
	}

	public String onFlowProcess(FlowEvent event) {
		if (event.getNewStep().equals("pay")) {
			List<Rendel> orders = getOrderService().getOrdersByPartnerId(getManagedPartner().getVevokod());
			if (orders.size() > 0) {
				getData().setShowAtutalas(true);
			}
		}
		return event.getNewStep();
	}

	public String setExpressCheckout(List<Rendtet> rendtets, Rendel rendel) throws PayPalException {
		CallerServices caller = new CallerServices();

		// construct and set the profile, these are the credentials we establish
		// as "the shop" with Paypal
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(PAYPAL_USERNAME);
		profile.setAPIPassword(PAYPAL_PASSWORD);
		profile.setSignature(PAYPAL_SIGNATURE);
		profile.setEnvironment("live");
		caller.setAPIProfile(profile);

		// construct the request
		SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
		pprequest.setVersion("63.0");

		// construct the details for the request
		SetExpressCheckoutRequestDetailsType details = new SetExpressCheckoutRequestDetailsType();
		/*
		PaymentDetailsItemType[] items = new PaymentDetailsItemType[rendtets.size()];
		int i = 0;
		for (Rendtet rendtet : rendtets) {
			BasicAmountType total = new BasicAmountType();
			BigDecimal amount = rendtet.getBertek().setScale(0, BigDecimal.ROUND_HALF_UP);
			total.set_value(amount.toString());
			total.setCurrencyID(CurrencyCodeType.HUF);
			PaymentDetailsItemType item = new PaymentDetailsItemType();
			item.setNumber(rendtet.getUnikazon());
			item.setAmount(total);
			item.setName(rendtet.getAmegn());
			item.setQuantity(rendtet.getAmenny().toBigInteger());
			items[i] = item;
			i++;
		}
		*/
		PaymentDetailsType paymentDetails = new PaymentDetailsType();
		//paymentDetails.setPaymentDetailsItem(items);
		paymentDetails.setOrderDescription("Rendelés - " + rendel.getSorszam());
		BigDecimal totalAmount = rendel.getDbrutto().setScale(0, BigDecimal.ROUND_HALF_UP);
		BasicAmountType orderTotal = new BasicAmountType(totalAmount.toString());
		orderTotal.setCurrencyID(CurrencyCodeType.EUR);
		paymentDetails.setOrderTotal(orderTotal);
		paymentDetails.setPaymentAction(PaymentActionCodeType.Sale);
		details.setPaymentDetails(new PaymentDetailsType[] { paymentDetails });

		details.setReturnURL(PAYPAL_RETURN_URL);
		details.setCancelURL(PAYPAL_CANCEL_URL);
		// details.setCustom(userId.toString());

		// set the details on the request
		pprequest.setSetExpressCheckoutRequestDetails(details);

		// call the actual webservice, passing the constructed request
		SetExpressCheckoutResponseType ppresponse = (SetExpressCheckoutResponseType) caller.call("SetExpressCheckout", pprequest);

		// get the token from the response
		return ppresponse.getToken();
	}

	public OrderControllerData getData() {
		return data;
	}

	public void setData(OrderControllerData data) {
		this.data = data;
	}

	public ShippingAddressServiceBase getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressServiceBase shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public OrderServiceBase getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderServiceBase orderService) {
		this.orderService = orderService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

	public LoginManager getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	public EmailServiceBase getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailServiceBase emailService) {
		this.emailService = emailService;
	}

}

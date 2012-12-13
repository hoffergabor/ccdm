package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.order;

import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;
import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;
import hu.iboard.coandco.datamagic.model.township.TownShip;
import hu.iboard.coandco.datamagic.retail.agria.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.controller.login.LoginManager;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.emailservice.EmailService;
import hu.iboard.coandco.datamagic.service.orderservice.OrderService;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.shippingadressservice.ShippingAddressService;
import hu.iboard.coandco.datamagic.service.userservice.UserService;
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
	private ShippingAddressService shippingAddressService;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{loginManager}")
	private LoginManager loginManager;
	@ManagedProperty(value = "#{emailService}")
	private EmailService emailService;
	@ManagedProperty(value = "#{successOrderController}")
	private SuccessOrderController successOrderController;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setShippingDate(calculateShippingDate());
			if (getManagedCustomer().getSzlFizeto() != 0) {
				getData().setPartner(getUserService().getPartnerById(getManagedCustomer().getSzlFizeto()));
				getData().setInvoiceZipcode(getData().getPartner().getIrsz());
				getData().setInvoiceCity(getData().getPartner().getVaros());
				getData().setInvoiceAddress(getData().getPartner().getCim());
				getData().setInvoiceName(getData().getPartner().getNev());
				getData().setActivateInvoice(1);
			}
			if (getManagedCustomer() != null) {
				getData().setShippingAddresses(getShippingAddressService().getShippingsByCustomer(getManagedCustomer()));
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
		if (getData().getShippingAddressId() != null && getData().getShippingAddressId() != 0)
			getData().setShippingAddress(getShippingAddressService().getShippingById(getData().getShippingAddressId()));
		checkPrice();
	}

	public String saveOrder() {
		if (!getData().getRules()) {
			addErrorMessage("", "Rendeléshez, kérjük fogadja el a vásárlási feltételeket!");
			return null;
		}
		if (getCartController().getData().getItems() == null || getCartController().getData().getItems().size() == 0) {
			addErrorMessage("", "A kosár üres! Rendeléshez, kérjük válasszon terméket!");
			return null;
		}
		if (getData().getErrorMessage() != null) {
			addErrorMessage("", "A rendelés leadásához legalább 5.000 Ft értékben szükséges vásárolnia. Hiányzik még: "
					+ (5000 - (getCartController().getData().getAllBrutto().intValue())) + " Ft");
			return null;
		}
		
		try {
			if (getManagedCustomer().getSzlFizeto() == 0 && getData().getActivateInvoice() == 1) {
				getData().setPartner(new Partner());
				getData().getPartner().setNev(getData().getInvoiceName());
				getData().getPartner().setIrsz(getData().getInvoiceZipcode());
				getData().getPartner().setVaros(getData().getInvoiceCity());
				getData().getPartner().setCim(getData().getInvoiceAddress());
				getData().getPartner().setTel1(getManagedCustomer().getTel());
				getData().getPartner().setEmail(getManagedCustomer().getWebLogin());
				getData().getPartner().setWeblogin(getManagedCustomer().getWebLogin());
				getData().getPartner().setWebpassw(getManagedCustomer().getWebPassw());
				getData().getPartner().setVevokod(getUserService().getLastEmptyPartnerId());
				getData().setPartner(getUserService().saveOrUpdatePartner(getData().getPartner()));
				getManagedCustomer().setSzlFizeto(getData().getPartner().getVevokod());
				getUserService().saveOrUpdateCustomer(getManagedCustomer());
				Partner partner = getUserService().saveOrUpdatePartner(getData().getPartner());
				if (partner == null) {
					addErrorMessage("", "Hiba történt. Rendelés sikertelen.");
					logger.error("ERROR BY SAVING PARTNER ON ORDER PAGE.");
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
			if (getData().getShippingOption() == 1) {
				getData().getOrder().setSirsz(getData().getShippingAddress().getZipCode());
				getData().getOrder().setSvaros(getData().getShippingAddress().getTownShip());
				getData().getOrder().setScim(getData().getShippingAddress().getAddress());
				getData().getOrder().setVirsz(getData().getShippingAddress().getZipCode());
				getData().getOrder().setVvaros(getData().getShippingAddress().getTownShip());
				getData().getOrder().setVcim(getData().getShippingAddress().getAddress());
				getData().getOrder().setMegj("Átveszi: " + getData().getShippingAddress().getContactName() + " - Telefon: " + getManagedCustomer().getTel());

			}
			if (getData().getShippingOption() == 2) {
				getData().getOrder().setSirsz("");
				getData().getOrder().setSvaros("");
				getData().getOrder().setScim("");
			}

			if (getData().getShippingOption() == 2 && getManagedCustomer().getSzlFizeto() == 0) {
				getData().getOrder().setVirsz(getData().getShippingAddress().getZipCode());
				getData().getOrder().setVvaros(getData().getShippingAddress().getTownShip());
				getData().getOrder().setVcim(getData().getShippingAddress().getAddress());
			}

			if (getData().getShippingOption() == 2 && getData().getPartner() != null) {
				getData().getOrder().setVirsz(getData().getPartner().getIrsz());
				getData().getOrder().setVvaros(getData().getPartner().getVaros());
				getData().getOrder().setVcim(getData().getPartner().getCim());
			}

			if (getManagedCustomer() != null && getManagedCustomer().getSzlFizeto() == 0) {
				getData().getOrder().setVnev(getManagedCustomer().getNev());
				getData().getOrder().setStelefon(getManagedCustomer().getTel());
				getData().getOrder().setVadoszam("");
				getData().getOrder().setVfolyszam("");
				getData().getOrder().setLakosvevo(getManagedCustomer().getLakosKod());
				getData().getOrder().setVevokod(8888888);
				getData().getOrder().setSnev("Lakossági vevő");
				getData().getOrder().setStatus(255);
			}

			if (getManagedCustomer() != null && getManagedCustomer().getSzlFizeto() != 0) {
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
			if (getData().getShippingArukod() != null) {
				Aru shippingProduct = getProductService().getProductById(getData().getShippingArukod());
				products.add(shippingProduct);
				mennys.add(new BigDecimal(1));
			}
			Rendel rendel = getOrderService().saveOrUpdateOrder(getData().getOrder(), products, mennys, "dear2");
			getData().getShippingAddress().setZipCode(null);
			if (rendel == null) {
				addErrorMessage("", "Hiba történt. Rendelés sikertelen.");
				logger.error("ERROR BY SAVING ORDER ON ORDER PAGE.");
				return "failedorder";
			}

			addInfoMessage("", "Rendelését sikeresen rögzítettük.");
			getSuccessOrderController().getData().setItems(getCartController().getData().getItems());
			getSuccessOrderController().getData().setAllBrutto(getCartController().getData().getAllBrutto());
			getSuccessOrderController().getData().setShippingPrice(getData().getShippingPrice());
			getSuccessOrderController().getData().setPayOption(getData().getPayOption());
			getSuccessOrderController().getData().setShippingOption(getData().getShippingOption());
			getCartController().getData().setItems(new ArrayList<ProductVO>());
			getCartController().getData().setItemsSize(0);
			getCartController().getData().setAllBrutto(new BigDecimal(0));

			String emailBody = "";
			emailBody += "Név: " + rendel.getVnev() + "</br></br>";
			emailBody += "Rendelés tételei: </br></br>";
			List<Rendtet> rendtets = getOrderService().getOrderItemsByOrderId(rendel.getSorszam());
			if (rendtets != null) {
				for (Rendtet rendtet : rendtets) {
					emailBody += rendtet.getAmenny() + " db" + rendtet.getAmegn();
					emailBody += " - Nettó egységár: " + rendtet.getAertek();
					emailBody += " - Nettó ár: " + rendtet.getNertek();
					emailBody += " - Bruttó ár: " + rendtet.getBertek() + "</br>";
				}
			}
			emailBody += "</br></br>Fizetendő végösszeg: " + rendel.getBrutto() + "</br></br>";
			if (getData().getShippingOption() == 1) {
				emailBody += " Szállítás címe:" + rendel.getSirsz() + " " + rendel.getSvaros() + ", " + rendel.getScim() + "</br>";
			}
			if (getData().getShippingOption() == 2) {
				emailBody += "Telephelyen történő átvétel</br></br>";
			}
			emailBody += "Fizetés típusa: " + rendel.getFizmod() + "\n\n";
			boolean isSuccessEmailSending = getEmailService().sendEmail(SMTP_HOST, SMTP_USER, SMTP_PASS, ORDEREMAIL, getManagedCustomer().getWebLogin(),
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
			getData().setInvoiceZipcode(getData().getShippingAddress().getZipCode());
			getData().setInvoiceCity(getData().getShippingAddress().getTownShip());
			getData().setInvoiceAddress(getData().getShippingAddress().getAddress());
		}
	}

	private void checkPrice() {
		getData().setErrorMessage(null);
		getData().setShippingPrice(null);
		String zipcode = getData().getShippingAddress().getZipCode();
		if (zipcode == null || zipcode.equals("")) {
			addErrorMessage("", "Nincs megadva irányítószám!");
			return;
		}
		List<Teruletcs> teruletcsk = getShippingAddressService().getTeruletcsByIrsz(Integer.valueOf(zipcode));
		if (teruletcsk == null) {
			addErrorMessage("", "A megadott irányítószám alapján nincs lehetőség szállításra!");
			return;
		}
		Teruletcs terulet = null;
		for (Teruletcs teruletcs : teruletcsk) {
			if (teruletcs.getTerulet() == 3 || teruletcs.getTerulet() == 4) {
				terulet = teruletcs;
			}
		}
		if (terulet == null) {
			addErrorMessage("", "A megadott irányítószám alapján nincs lehetőség szállításra!");
			return;
		}
		if (terulet.getTerulet() == 3)
			calculateShippingPriceA();
		if (terulet.getTerulet() == 4)
			calculateShippingPriceB();
		calculateExpressPrice();
	}

	private void calculateShippingPriceA() {
		Integer price = getCartController().getData().getAllBrutto().intValue();
		if (price >= 5000 && price <= 20000) {
			getData().setShippingPrice("1090");
			getData().setShippingArukod(3014424);
		}
		if (price > 20000 && price <= 30000) {
			getData().setShippingPrice("990");
			getData().setShippingArukod(3014427);
		}
		if (price > 30000 && price <= 50000) {
			getData().setShippingPrice("890");
			getData().setShippingArukod(3014428);
		}
		if (price > 50000)
			getData().setShippingPrice("0");
	}

	private void calculateShippingPriceB() {
		Integer price = getCartController().getData().getAllBrutto().intValue();
		if (price >= 5000) {
			getData().setShippingPrice("1590");
			getData().setShippingArukod(3014430);
		}
	}

	private void calculateExpressPrice() {
		if (getData().getShippingPrice() == null)
			return;
		if (getData().getShippingOption() == 2) {
			getData().setAddedExpressPrice(true);
			if (!getData().getShippingPrice().equals("ingyenes")) {
				int ar = Integer.valueOf(getData().getShippingPrice());
				ar = ar + 1590;
				getData().setShippingPrice(Integer.valueOf(ar).toString());
			} else {
				getData().setShippingPrice("1590");
			}
			getData().setShippingArukod(3014432);
		}
		if (getData().getShippingOption() == 1 && getData().getAddedExpressPrice()) {
			if (!getData().getShippingPrice().equals("1590")) {
				int ar = Integer.valueOf(getData().getShippingPrice());
				ar = ar - 1590;
				getData().setShippingPrice(Integer.valueOf(ar).toString());
			} else {
				getData().setShippingPrice("ingyenes");
			}
			getData().setAddedExpressPrice(false);
		}
		

	}

	@SuppressWarnings("deprecation")
	private Date calculateShippingDate() {
		Date now = new Date();
		Calendar date = Calendar.getInstance();
		if (getData().getShippingOption() == 1) {
			if (now.getHours() < 12) {
				date.add(Calendar.DATE, 1);
			} else {
				date.add(Calendar.DATE, 2);
			}
		}
		if (getData().getShippingOption() == 2) {
			if (now.getHours() > 12) {
				date.add(Calendar.DATE, 1);
			}
		}
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			date.add(Calendar.DATE, 2);
		}
		if (date.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			date.add(Calendar.DATE, 1);
		}
		return date.getTime();
	}

	public void changeZipCode(ActionEvent event) {
		checkPrice();
	}

	public void changeShippingOption(ActionEvent event) {
		calculateExpressPrice();
		getData().setShippingDate(calculateShippingDate());
	}

	public String onFlowProcess(FlowEvent event) {
		//TODO visszatenni
		/*
		if (event.getNewStep().equals("pay")) {
			if (getData().getShippingPrice() == null) {
				addErrorMessage("", "A megadott irányítószám alapján nincs lehetőség szállításra!");
				return event.getOldStep();
			}
		}*/
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
		orderTotal.setCurrencyID(CurrencyCodeType.HUF);
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

	public ShippingAddressService getShippingAddressService() {
		return shippingAddressService;
	}

	public void setShippingAddressService(ShippingAddressService shippingAddressService) {
		this.shippingAddressService = shippingAddressService;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public LoginManager getLoginManager() {
		return loginManager;
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	public SuccessOrderController getSuccessOrderController() {
		return successOrderController;
	}

	public void setSuccessOrderController(SuccessOrderController successOrderController) {
		this.successOrderController = successOrderController;
	}

}

package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.order;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.CallerServices;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentRequestType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseDetailsType;
import com.paypal.soap.api.DoExpressCheckoutPaymentResponseType;
import com.paypal.soap.api.GetExpressCheckoutDetailsRequestType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseDetailsType;
import com.paypal.soap.api.GetExpressCheckoutDetailsResponseType;
import com.paypal.soap.api.PayerInfoType;
import com.paypal.soap.api.PaymentDetailsType;
import com.paypal.soap.api.PaymentInfoType;
import com.paypal.soap.api.PaymentStatusCodeType;

@ManagedBean
@RequestScoped
public class SuccessOrderController extends AbstractController {

	@ManagedProperty(value = "#{successOrderControllerData}")
	private SuccessOrderControllerData data;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			try {
				Map<String, String> requestMap = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
				if (requestMap != null && requestMap.size() > 0) {
					String token = (String) requestMap.get("token");
					if (token != null && token.length() > 0) {
						GetExpressCheckoutDetailsResponseDetailsType details = getExpressCheckoutDetails(token);
						if (details != null) {
							doExpressCheckoutService(details);
						}
					}
				}
			} catch (Exception e) {
				logger.error("HIBA történt paypal visszaigazolásnál!", e);
			}

		}
	}

	public GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetails(String token) throws PayPalException {
		CallerServices caller = new CallerServices();
		
		// construct and set the profile, these are the credentials we establish
		// as "the shop" with Paypal
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(PAYPAL_USERNAME);
		profile.setAPIPassword(PAYPAL_PASSWORD);
		profile.setSignature(PAYPAL_SIGNATURE);
		profile.setEnvironment("live");
		caller.setAPIProfile(profile);

		GetExpressCheckoutDetailsRequestType pprequest = new GetExpressCheckoutDetailsRequestType();
		pprequest.setVersion("63.0");
		pprequest.setToken(token);

		GetExpressCheckoutDetailsResponseType ppresponse = (GetExpressCheckoutDetailsResponseType) caller.call("GetExpressCheckoutDetails", pprequest);

		return ppresponse.getGetExpressCheckoutDetailsResponseDetails();
	}

	public boolean doExpressCheckoutService(GetExpressCheckoutDetailsResponseDetailsType response) throws PayPalException {
		CallerServices caller = new CallerServices();
		
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		profile.setAPIUsername(PAYPAL_USERNAME);
		profile.setAPIPassword(PAYPAL_PASSWORD);
		profile.setSignature(PAYPAL_SIGNATURE);
		profile.setEnvironment("live");
		caller.setAPIProfile(profile);

		DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
		pprequest.setVersion("63.0");

		DoExpressCheckoutPaymentResponseType ppresponse = new DoExpressCheckoutPaymentResponseType();

		DoExpressCheckoutPaymentRequestDetailsType paymentDetailsRequestType = new DoExpressCheckoutPaymentRequestDetailsType();
		paymentDetailsRequestType.setToken(response.getToken());

		PayerInfoType payerInfo = response.getPayerInfo();
		paymentDetailsRequestType.setPayerID(payerInfo.getPayerID());

		PaymentDetailsType paymentDetails = response.getPaymentDetails(0);
		paymentDetailsRequestType.setPaymentAction(paymentDetails.getPaymentAction());

		paymentDetailsRequestType.setPaymentDetails(response.getPaymentDetails());
		pprequest.setDoExpressCheckoutPaymentRequestDetails(paymentDetailsRequestType);

		ppresponse = (DoExpressCheckoutPaymentResponseType) caller.call("DoExpressCheckoutPayment", pprequest);
		DoExpressCheckoutPaymentResponseDetailsType type = ppresponse.getDoExpressCheckoutPaymentResponseDetails();

		if (type != null) {
			PaymentInfoType paymentInfo = type.getPaymentInfo(0);
			if (paymentInfo.getPaymentStatus().equals(PaymentStatusCodeType.fromString("Completed"))) {
				logger.info("Payment completed.");
				return true;
			} else {
				logger.info("Payment not completed.. (" + paymentInfo.getPaymentStatus() + ")");
				return false;
			}
		} else {
			logger.info("Problem executing DoExpressCheckoutPayment.. Maybe you tried to process an ExpressCheckout that has already been processed.");
			return false;
		}
	}

	public SuccessOrderControllerData getData() {
		return data;
	}

	public void setData(SuccessOrderControllerData data) {
		this.data = data;
	}

}

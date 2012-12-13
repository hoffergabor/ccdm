package hu.iboard.coandco.datamagic.retail.agria.presentation.util;

public interface Constants {

	public static String MSGFILE = "hu.iboard.coandco.datamagic.retail.agria.resources.MessageResources";

	public static final String LOCALHOSTHOST = "localhost";

	public static final String PROPERTY_APPLICATION = "hu/iboard/coandco/datamagic/retail/agria/resources/Application.properties";

	public static final String SMTP = "smtp";
	public static final String SMTP_PORT = "25";
	public static final String SMTP_HOST = "agriadrink.hu";
	public static final String SMTP_USER = "webshop@agriadrink.hu";
	public static final String SMTP_PASS = "W3bSh0p_!";
	
//	public static final String SMTP = "smtp";
//	public static final String SMTP_PORT = "465";
//	public static final String SMTP_HOST = "smtp.gmail.com";
//	public static final String SMTP_USER = "info@totumfactum.hu";
//	public static final String SMTP_PASS = "a123b123c1";

	public static final String ORDEREMAIL = "webshop@agriadrink.hu";

	public static final String CONFIG_FILE_LOCATION = "WEB-INF/configuration.xml";
	public static final String CONFIG_ELEMENT_DEFAULT_ARNEVKOD = "DefaultArNevKod";
	public static final String CONFIG_ELEMENT_ORDER_PARAMETERS = "OrderParameters";
	public static final String CONFIG_ELEMENT_MINIMUM_ORDER_VALUE = "MinimumOrderValue";
	public static final String CONFIG_ELEMENT_ORDER_CHARGE = "OrderCharge";
	public static final String CONFIG_ELEMENT_CHARGE = "Charge";
	public static final String CONFIG_ELEMENT_FROM_VALUE = "FromValue";
	public static final String CONFIG_ELEMENT_CHARGE_ITEM_CODE = "ChargeItemCode";
	public static final String CONFIG_ELEMENT_ADAPT_DISCOUNT = "AdaptDiscount";
	public static final String CONFIG_ELEMENT_NAME_CODE = "MegnevezesKod";
	
//PAYPAL
	
	public static final String PAYPAL_USERNAME = "informatika_api1.agriadrink.hu";
	public static final String PAYPAL_PASSWORD = "93B23NFV7S5NNSPJ";
	public static final String PAYPAL_SIGNATURE = "AJstef0Lnkx1VWwOfwn3CRTGjtzrA.mhgTG8b-9cJksutfS9k88IDym3";
	public static final String PAYPAL_RETURN_URL = "http://agriadrink.hu:8180/agria/sikeresrendeles";
	public static final String PAYPAL_CANCEL_URL = "http://agriadrink.hu:8180/agria/sikertelenrendeles";
	public static final String PAYPAL_SANDBOX_URL = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	public static final String PAYPAL_URL = "https://www.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	
}

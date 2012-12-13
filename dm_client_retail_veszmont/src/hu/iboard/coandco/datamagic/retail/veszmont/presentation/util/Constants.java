package hu.iboard.coandco.datamagic.retail.veszmont.presentation.util;

public interface Constants {

	public static String MSGFILE = "hu.iboard.coandco.professional.resources.MessageResources";

	public static final String LOCALHOSTHOST = "localhost";

	public static final String PROPERTY_APPLICATION = "hu/iboard/coandco/datamagic/retail/veszmont/resources/Application.properties";

	public static final String SMTP_HOST = "mail.t-online.hu";
	public static final String SMTP_USER = "adrink1";
	public static final String SMTP_PASS = "7kN8csR2";

	public static final String ORDEREMAIL = "veszmont@vesz-mont.hu";

	public static final String CONFIG_FILE_LOCATION = "WEB-INF/configuration.xml";
	public static final String CONFIG_ELEMENT_DEFAULT_ARNEVKOD = "DefaultArNevKod";
	public static final String CONFIG_ELEMENT_ORDER_PARAMETERS = "OrderParameters";
	public static final String CONFIG_ELEMENT_MINIMUM_ORDER_VALUE = "MinimumOrderValue";
	public static final String CONFIG_ELEMENT_ORDER_CHARGE = "OrderCharge";
	public static final String CONFIG_ELEMENT_CHARGE = "Charge";
	public static final String CONFIG_ELEMENT_FROM_VALUE = "FromValue";
	public static final String CONFIG_ELEMENT_CHARGE_ITEM_CODE = "ChargeItemCode";
	public static final String CONFIG_ELEMENT_ADAPT_DISCOUNT = "AdaptDiscount";
	
	//PAPAL
	
	public static final String PAYPAL_USERNAME = "veszmont_api1.vnet.hu";
	public static final String PAYPAL_PASSWORD = "7MGRKZ37B8MZVFLC";
	public static final String PAYPAL_SIGNATURE = "AdLRojb4QaCUX29b92LglWbHFH9aAM5Aug6dqL9uqFPpRFioG4dbSn1n";
	public static final String PAYPAL_RETURN_URL = "http://ccteszt2.iboard.hu:8080/veszmontkisker/sikeresrendeles";
	public static final String PAYPAL_CANCEL_URL = "http://ccteszt2.iboard.hu:8080/veszmontkisker/sikertelenrendeles";
	public static final String PAYPAL_SANDBOX_URL = "https://www.sandbox.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	public static final String PAYPAL_URL = "https://www.paypal.com/webscr?cmd=_express-checkout&useraction=commit&token=";
	
}

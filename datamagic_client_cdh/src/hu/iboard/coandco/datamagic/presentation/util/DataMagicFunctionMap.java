package hu.iboard.coandco.datamagic.presentation.util;

import hu.iboard.coandco.datamagic.presentation.controllers.loginservice.LoginServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.notificationservice.ManageNotificationServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.notificationservice.NotificationServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.partnerdataupdate.PartnerDataUpdateController;
import hu.iboard.coandco.datamagic.presentation.controllers.realtyarrangementservice.RealtyArrangementServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.realtyinvoice.RealtyInvoiceServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.workerdataupdate.WorkerDataUpdateController;

import java.util.HashMap;

public class DataMagicFunctionMap {

	/**
	 * Az oldalak navigation rule-jainak �s controller-jeinek �szep�ros�t�s�t
	 * tartalmaz� map
	 */
	private static HashMap<String, String> pageNameToFunctionClassName = new HashMap<String, String>();

	static {
		pageNameToFunctionClassName.put(LoginServiceController.LOGINSERVICE_ACTION, "loginserviceController");
		pageNameToFunctionClassName.put(RealtyInvoiceServiceController.REALTYINVOICESERVICE_ACTION, "realtyinvoiceserviceController");
		pageNameToFunctionClassName.put(RealtyInvoiceServiceController.MOBILEREALTYINVOICESERVICE_ACTION, "realtyinvoiceserviceController");
		pageNameToFunctionClassName.put(RealtyArrangementServiceController.REALTYARRANGEMENTSERVICE_ACTION,"realtyarrangementserviceController");
		pageNameToFunctionClassName.put(RealtyArrangementServiceController.MOBILEREALTYARRANGEMENTSERVICE_ACTION,"realtyarrangementserviceController");
		pageNameToFunctionClassName.put(PartnerDataUpdateController.PARTNERDATAUPDATE_ACTION,"partnerdataupdateController");
		pageNameToFunctionClassName.put(PartnerDataUpdateController.MOBILEPARTNERDATAUPDATE_ACTION,"partnerdataupdateController");
		pageNameToFunctionClassName.put(WorkerDataUpdateController.WORKERDATAUPDATE_ACTION,"workerdataupdateController");
		pageNameToFunctionClassName.put(ManageNotificationServiceController.MANAGENOTIFICATIONSERVICE_ACTION,"managenotificationserviceController");
		pageNameToFunctionClassName.put(NotificationServiceController.NOTIFICATIONSERVICE_ACTION,"notificationserviceController");
		pageNameToFunctionClassName.put(NotificationServiceController.MOBILENOTIFICATIONSERVICE_ACTION,"notificationserviceController");
	}

	public static String getFunctionClassName(String pageName) {
		return pageNameToFunctionClassName.get(pageName);
	}

	public static HashMap<String, String> getFunctionMap() {
		return pageNameToFunctionClassName;
	}
}

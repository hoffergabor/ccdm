package hu.iboard.coandco.datamagic.presentation.util;

import hu.iboard.coandco.datamagic.presentation.controllers.bizkomment.BizkommentServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.invoiceservice.InvoiceServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.loginservice.LoginServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.managefiles.ManageFilesController;
import hu.iboard.coandco.datamagic.presentation.controllers.manageorderservice.ManageOrderServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.openitems.OpenItemsServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.orderservice.OrderServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.partnerdataupdate.PartnerDataUpdateController;
import hu.iboard.coandco.datamagic.presentation.controllers.productservice.ProductServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.registrationservice.RegistrationServiceController;
import hu.iboard.coandco.datamagic.presentation.controllers.shippingservice.ShippingServiceController;
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
		pageNameToFunctionClassName.put(OrderServiceController.ORDERSERVICE_ACTION, "orderserviceController");
		pageNameToFunctionClassName.put(ManageOrderServiceController.MANAGEORDERSERVICE_ACTION, "manageorderserviceController");
		pageNameToFunctionClassName.put(InvoiceServiceController.INVOICESERVICE_ACTION, "invoiceserviceController");
		pageNameToFunctionClassName.put(InvoiceServiceController.MOBILEINVOICESERVICE_ACTION, "invoiceserviceController");
		pageNameToFunctionClassName.put(OpenItemsServiceController.OPENITEMSSERVICE_ACTION, "openitemsserviceController");
		pageNameToFunctionClassName.put(ShippingServiceController.SHIPPINGSERVICE_ACTION, "shippingserviceController");
		pageNameToFunctionClassName.put(PartnerDataUpdateController.PARTNERDATAUPDATE_ACTION,"partnerdataupdateController");
		pageNameToFunctionClassName.put(PartnerDataUpdateController.MOBILEPARTNERDATAUPDATE_ACTION,"partnerdataupdateController");
		pageNameToFunctionClassName.put(WorkerDataUpdateController.WORKERDATAUPDATE_ACTION,"workerdataupdateController");
		pageNameToFunctionClassName.put(ProductServiceController.PRODUCTSERVICE_ACTION,"productserviceController");
		pageNameToFunctionClassName.put(BizkommentServiceController.BIZKOMMENTSERVICE_ACTION,"bizkommentserviceController");
		pageNameToFunctionClassName.put(RegistrationServiceController.REGISTRATIONSERVICE_ACTION,"registrationserviceController");
		pageNameToFunctionClassName.put(ManageFilesController.MANAGEFILES_ACTION,"managefilesController");

	}

	public static String getFunctionClassName(String pageName) {
		return pageNameToFunctionClassName.get(pageName);
	}

	public static HashMap<String, String> getFunctionMap() {
		return pageNameToFunctionClassName;
	}
}

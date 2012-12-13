package hu.iboard.coandco.datamagic.presentation.util;


import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

@ManagedBean(name = "configurationHandler")
@SessionScoped
public final class ConfigurationHandler extends AbstractController {

	public class OrderCharge {
		private Integer minimumValue;
		private Integer charge;

		public Integer getMinimumValue() {
			return minimumValue;
		}

		public void setMinimumValue(Integer minimumValue) {
			this.minimumValue = minimumValue;
		}

		public Integer getCharge() {
			return charge;
		}

		public void setCharge(Integer charge) {
			this.charge = charge;
		}

		public OrderCharge(Integer MinimumValue, Integer Charge) {
			this.charge = Charge;
			this.minimumValue = MinimumValue;
		}

		public OrderCharge() {
		}
	}

	private static ConfigurationHandler instance = null;

	private static Integer defaultArnevKod;
	private static List<OrderCharge> orderCharges = new ArrayList<OrderCharge>();
	private static Integer minimumOrderValue;
	private static Integer chargeItemCode;
	private static Boolean adaptDiscount;
	private static Integer megnevezesKod;

	private ConfigurationHandler() {
		try {
			// String s = getRealPath();
			File xmlfile = new File(getRealPath() + Constants.CONFIG_FILE_LOCATION);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlfile);
			doc.getDocumentElement().normalize();
			String valueString = doc.getElementsByTagName(Constants.CONFIG_ELEMENT_DEFAULT_ARNEVKOD).item(0).getFirstChild().getNodeValue();
			defaultArnevKod = new Integer(valueString);
			
			//OrderParameters
			Integer fromValue = null;
			Integer charge = null;
			String nodeName = "";
			for (Node childNode = doc.getElementsByTagName(Constants.CONFIG_ELEMENT_ORDER_PARAMETERS).item(0).getFirstChild(); childNode != null;) {
				Node nextChild = childNode.getNextSibling();
				nodeName = childNode.getNodeName();
				if (nodeName == Constants.CONFIG_ELEMENT_ORDER_CHARGE)
					for (Node childChildNode = childNode.getFirstChild(); childChildNode != null;) {
						Node nextChildChild = childChildNode.getNextSibling();

						nodeName = childChildNode.getNodeName();

						if (nodeName == Constants.CONFIG_ELEMENT_FROM_VALUE) {
							fromValue = new Integer(childChildNode.getFirstChild().getNodeValue());
						} else if (nodeName == Constants.CONFIG_ELEMENT_CHARGE) {
							charge = new Integer(childChildNode.getFirstChild().getNodeValue());
						}

						if (fromValue != null && charge != null) {
							orderCharges.add(new OrderCharge(fromValue, charge));
							fromValue = null;
							charge = null;
						}

						childChildNode = nextChildChild;
					}
				else if (nodeName == Constants.CONFIG_ELEMENT_MINIMUM_ORDER_VALUE) {
					minimumOrderValue = new Integer(childNode.getFirstChild().getNodeValue());
				}
				else if (nodeName == Constants.CONFIG_ELEMENT_CHARGE_ITEM_CODE) {
					chargeItemCode = new Integer(childNode.getFirstChild().getNodeValue());
				}
				else if (nodeName == Constants.CONFIG_ELEMENT_ADAPT_DISCOUNT) {
					adaptDiscount = new Boolean(childNode.getFirstChild().getNodeValue());
				}
				else if (nodeName == Constants.CONFIG_ELEMENT_NAME_CODE)
					megnevezesKod = new Integer(childNode.getFirstChild().getNodeValue());
				childNode = nextChild;
			}
			//OrderParameters

		} catch (Exception e) {
			System.out.print(e.getMessage());
			instance = null;
		}
	}

	public static ConfigurationHandler getInstance() {
		if (instance == null)
			instance = new ConfigurationHandler();
		return instance;
	}

	@SuppressWarnings("static-access")
	public static Integer getDefaultArnevKod() {
		// return ConfigurationHandler.defaultArnevKod;
		return getInstance().defaultArnevKod;
	}

	@SuppressWarnings("static-access")
	public static List<OrderCharge> getOrderCharges() {
		return getInstance().orderCharges;
	}

	public static Integer getOrderChargeByOrderValue(BigDecimal orderValue) {
		Integer result = new Integer(0);
		Integer smallestLimit = new Integer(0);
		for (OrderCharge orderCharge : getOrderCharges()) {
			if (orderValue.compareTo(new BigDecimal(orderCharge.getMinimumValue())) > 0 && orderCharge.getMinimumValue() > smallestLimit)
				result = orderCharge.getCharge();
		}
		return result;
	}

	@SuppressWarnings("static-access")
	public static Integer getMinimumOrderValue() {
		return getInstance().minimumOrderValue;
	}
	
	@SuppressWarnings("static-access")
	public static Integer getChargeItemCode() {
		return getInstance().chargeItemCode;
	}
	
	@SuppressWarnings("static-access")
	public static Boolean getAdaptDiscount() {
		return getInstance().adaptDiscount;
	}

	@SuppressWarnings("static-access")
	public static Integer getMegnevezesKod() {
		return getInstance().megnevezesKod;
	}

	@Override
	public void loadData() {
		
	}

	@Override
	public void reloadData() {
		
	}

	@Override
	public void resetData() {
		
	}
}
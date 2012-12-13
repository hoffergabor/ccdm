/**
 * PropertyUtil.java (Project JobPortal)
 *
 * Created: 11.09.2009
 */
package hu.iboard.coandco.datamagic.presentation.util;

import hu.iboard.coandco.datamagic.util.DataMagicConstants;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * This class provides methods to use the properties defined in different property files.
 * 
 */
public final class PropertyUtil
{

	private static Logger		logger					= Logger.getLogger(PropertyUtil.class);
	private static Properties	applicationProperties	= null;

	/**
	 * Constructor for PropertyUtil.
	 */
	public PropertyUtil()
	{
	}

	private static void init()
	{
		applicationProperties = new Properties();

		try
		{
			applicationProperties.load(PropertyUtil.class.getClassLoader().getResourceAsStream(DataMagicConstants.PROPERTY_APPLICATION));
		} catch (IOException e)
		{
			logger.error("Failed to initialize PropertyUtil! Cause: " + e.toString());
		}
	}

	/**
	 * This method returns a property from the Application.properties file for the given key.
	 * 
	 * @param key
	 *            the key of the requested property.
	 * @return a string representation of the property.
	 */
	public static String getAppProperty(String key)
	{
		String retVal = null;

		if (applicationProperties == null)
		{
			init();
		}

		retVal = applicationProperties.getProperty(key);

		if (retVal == null)
		{
			logger.warn("Failed to load property [" + key + "] value!");
		}

		return retVal;
	}

	/**
	 * This method converts all application properties to string format.
	 * 
	 * @return a string containing all application properties.
	 */
	public static String allAppPropertyToString()
	{
		StringBuffer out = new StringBuffer();

		out.append("\n\rApplicationProperties={");
		for (Enumeration keys = applicationProperties.keys(); keys.hasMoreElements();)
		{
			String prop = (String) keys.nextElement();

			out.append("\n\r (" + prop + "=" + applicationProperties.getProperty(prop) + ")");
		}
		out.append("}\n\r");

		return (out.toString());
	}
}

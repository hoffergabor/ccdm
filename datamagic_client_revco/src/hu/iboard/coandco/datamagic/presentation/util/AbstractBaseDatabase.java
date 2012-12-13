package hu.iboard.coandco.datamagic.presentation.util;

import hu.iboard.coandco.datamagic.util.DataMagicConstants;

import java.util.Locale;

import org.apache.log4j.Logger;

/**
 * 
 * 
 * 
 */
public abstract class AbstractBaseDatabase implements DataMagicConstants, Constants
{
	protected Logger	logger	= Logger.getLogger(this.getClass());


	/**
	 * Logolja az adatb�ziskapcsolat hib�j�t
	 * 
	 * @param string
	 * @param e
	 */
	protected void logDbConnectionError(String string, Exception e)
	{
		logger.error(string, e);
	}

	/**
	 * Beolvas egy �zenetet a megadott properties f�jlb�l
	 * 
	 * @param textKey
	 *            - az �zenet kulcsa
	 * @param fileName
	 *            - a properties f�jl neve
	 * 
	 * @return
	 */
	protected String getTextFromPropertiesFile(String textKey, String fileName)
	{
		return getTextFromPropertiesFile(textKey, fileName, null);
	}

	/**
	 * Beolvas egy �zenetet a megadott properties f�jlb�l, �s kit�lti a sz�ks�ges param�tereket
	 * 
	 * @param textKey
	 *            - az �zenet kulcsa
	 * @param fileName
	 *            - a properties f�jl neve
	 * @param parameter
	 *            - a kit�ltend� param�terek
	 * 
	 * @return
	 */
	protected String getTextFromPropertiesFile(String textKey, String fileName, Object[] parameter)
	{
		return Messages.getString(fileName, textKey, parameter);
	}

	/**
	 * Beolvas egy �zenetet a megadott properties f�jlb�l
	 * 
	 * @param textKey
	 *            - az �zenet kulcsa
	 * @param fileName
	 *            - a properties f�jl neve
	 * @param locale
	 *            - az olvas�shoz haszn�lt nyelv
	 * 
	 * @return
	 */
	protected String getTextFromPropertiesFileByLocale(String textKey, String fileName, Locale locale)
	{
		return getTextFromPropertiesFileByLocale(textKey, fileName, locale, null);
	}

	/**
	 * Beolvas egy �zenetet a megadott properties f�jlb�l, �s kit�lti a sz�ks�ges param�tereket
	 * 
	 * @param textKey
	 *            - az �zenet kulcsa
	 * @param fileName
	 *            - a properties f�jl neve
	 * @param locale
	 *            - az olvas�shoz haszn�lt nyelv
	 * @param parameter
	 *            - a kit�ltend� param�terek
	 * 
	 * @return
	 */
	protected String getTextFromPropertiesFileByLocale(String textKey, String fileName, Locale locale, Object[] parameter)
	{
		return Messages.getStringByLocale(fileName, textKey, locale, parameter);
	}

	/**
	 * Beolvas egy hiba�zenetet az stderrmsgs.properties f�jlb�l
	 * 
	 * @param textKey
	 * @return
	 */
	protected String getStandardErrorMessage(String textKey)
	{
		return getTextFromPropertiesFile(textKey, MSGFILE);
	}
}

/**
 * 
 */

package hu.iboard.coandco.datamagic.westbridge.presentation.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

/**
 * A properties f�jlokban t�rolt �zenetekhez val� hozz�f�r�st seg�t� oszt�ly
 * 
 * 
 */
public class StandardMessages extends Messages
{

	/**
	 * Az stdmsgs.properties f�jl neve
	 */
	private static String	STDMSGS_RESOURCE	= "stdmsgs";

	/**
	 * Beolvas egy �zenetet
	 * 
	 * @param resourceId
	 * @param params
	 * @param severity
	 * @return
	 */
	public static FacesMessage getStandardMessage(String resourceId, Object[] params, Severity severity)
	{
		return getMessage(STDMSGS_RESOURCE, resourceId, params, severity);
	}

	/**
	 * Beolvas egy hiba�zenetet
	 * 
	 * @return
	 */
	public static FacesMessage getErrorMessage()
	{
		return getMessage(STDMSGS_RESOURCE, "errorMessage", null, FacesMessage.SEVERITY_ERROR);
	}

	/**
	 * Beolvas egy �zenetet
	 * 
	 * @param resourceId
	 * @param params
	 * @return
	 */
	public static String getString(String resourceId, Object[] params)
	{
		return getString(STDMSGS_RESOURCE, resourceId, params);
	}
}

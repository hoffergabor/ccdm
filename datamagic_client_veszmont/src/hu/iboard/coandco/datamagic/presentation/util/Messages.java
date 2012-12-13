
package hu.iboard.coandco.datamagic.presentation.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * A properties f�jlokban t�rolt �zenetek kezel�s�t megval�s�t� oszt�ly
 * 
 *
 */
public class Messages
{

	public static final String	RESOURCE_NOT_FOUND	= "Unknown resource ID: ";

	/**
	 * Beolvas egy �zenetet
	 * 
	 * @param bundleName
	 * @param resourceId
	 * @param params
	 * @param severity
	 * @return
	 */
	public static FacesMessage getMessage(String bundleName, String resourceId, Object[] params, Severity severity)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		String summary = getString(appBundle, bundleName, resourceId, locale, loader, params);
		String detail = getString(appBundle, bundleName, resourceId + "_detail", locale, loader, params);
		FacesMessage facesMessage = new FacesMessage(summary, detail);
		facesMessage.setSeverity(severity);
		return facesMessage;
	}

	
	/**
	 * Beolvas egy �zenetet
	 * 
	 * @param bundle
	 * @param resourceId
	 * @param params
	 * @return
	 */
	public static String getString(String bundle, String resourceId, Object[] params)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		return getString(appBundle, bundle, resourceId, locale, loader, params);
	}
	
	/**
	 * Beolvas egy �zenetet a megadott Locale objektum nyelv�n
	 * 
	 * @param bundle
	 * @param resourceId
	 * @param locale
	 * @param params
	 * @return
	 */
	public static String getStringByLocale(String bundle, String resourceId, Locale locale, Object[] params)
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		ClassLoader loader = getClassLoader();
		return getString(appBundle, bundle, resourceId, locale, loader, params);
	}

	/**
	 * Beolvas egy �zenetet
	 * 
	 * @param bundle1
	 * @param bundle2
	 * @param resourceId
	 * @param locale
	 * @param loader
	 * @param params
	 * @return
	 */
	public static String getString(String bundle1, String bundle2, String resourceId, Locale locale, ClassLoader loader, Object[] params)
	{
		String resource = null;
		ResourceBundle bundle;

		if (bundle1 != null)
		{
			bundle = ResourceBundle.getBundle(bundle1, locale, loader);
			if (bundle != null)
				try
				{
					resource = bundle.getString(resourceId);
				}
				catch (MissingResourceException ex)
				{
					resource = RESOURCE_NOT_FOUND + resourceId;
				}
		}

		if ((resource == null) || (resource.startsWith(RESOURCE_NOT_FOUND)))
		{
			bundle = ResourceBundle.getBundle(bundle2, locale, loader);
			if (bundle != null)
				try
				{
					resource = bundle.getString(resourceId);
				}
				catch (MissingResourceException ex)
				{
					resource = RESOURCE_NOT_FOUND + resourceId;
				}
		}

		if (resource == null)
			return null; // no match
		if (params == null)
			return resource;

		MessageFormat formatter = new MessageFormat(resource, locale);
		return formatter.format(params);
	}

	/**
	 * Lek�rdezi az oldalon haszn�lt nyelvet
	 * 
	 * @param context
	 * @return
	 */
	public static Locale getLocale(FacesContext context)
	{
		Locale locale = null;
		UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null)
			locale = viewRoot.getLocale();
		if (locale == null)
			locale = Locale.getDefault();
		return locale;
	}

	/**
	 * Megadja a haszn�lt ClassLoader objektumot
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader()
	{
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null)
			loader = ClassLoader.getSystemClassLoader();
		return loader;
	}
}

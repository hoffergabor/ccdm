package hu.iboard.coandco.datamagic.presentation.util;

import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;


public class BeanManager
{

	private static Logger	logger	= Logger.getLogger(BeanManager.class);

	public static <T> T getManagedBean(String managedBeanName, Class<T> type)
	{

		FacesContext context = FacesContext.getCurrentInstance();
		return getManagedBean(context, managedBeanName, type);

	}

	public static <T> T getManagedBean(FacesContext context, String managedBeanName, Class<T> type)
	{

		Object retVal = null;
		retVal = context.getApplication().getExpressionFactory().createValueExpression(context.getELContext(), "#{" + managedBeanName + "}",
				Object.class).getValue(context.getELContext());

		if (retVal == null)
		{
			logger.warn("Could not retrieve managed bean #{" + managedBeanName + "} from EL context!");
		} else
		{
			logger.debug("Managed bean #{" + managedBeanName + "} has been retrieved from EL context.");
		}

		return type.cast(retVal);

	}

}

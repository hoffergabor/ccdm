package hu.iboard.coandco.datamagic.presentation.util;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;

/**
 * 
 */
public class Utils
{
	public static ValueExpression getValueExpression(String name)
	{
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		Application app = facesCtx.getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		ELContext elContext = facesCtx.getELContext();
		return elFactory.createValueExpression(elContext, name, Object.class);
	}

	public static MethodExpression getMethodExpression(String name, Class<?> type, Class<?>[] args)
	{
		FacesContext facesCtx = FacesContext.getCurrentInstance();
		Application app = facesCtx.getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		ELContext elContext = facesCtx.getELContext();
		return elFactory.createMethodExpression(elContext, name, type, args);
	}

	public static String getRequestParameter(String name)
	{
		return (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
	}
}

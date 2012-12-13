
package hu.iboard.coandco.datamagic.presentation.util;

import javax.faces.context.FacesContext;


/**
 * Az �ltal�nosan haszn�lt f�ggv�nyeket tartalmaz� oszt�ly
 * 
 *
 */
public class Util
{
	/**
	 * Megadja az lek�rt oldalhoz tartoz� jspx f�jl nev�t
	 * 
	 * @param viewId
	 * @return
	 */
	public static String getActualFunc(String viewId)
	{
		int i = viewId.lastIndexOf("/");
		int j = viewId.indexOf(".");
		return viewId.substring(i + 1, j);
	}

	/**
	 * Can reload data after request
	 * 
	 * @return
	 */
	
	public static boolean canReloadData()
	{
		boolean result = false;
		FacesContext ctx = FacesContext.getCurrentInstance();
		boolean isPostback = isPostback();
		PreviewManager previewManager = (PreviewManager) ctx.getApplication().getELResolver().getValue(ctx.getELContext(), null, CommonPhaseListener.PREVIEW_MANAGER);
		boolean isPrevPageActivated = previewManager.isPrevPageActivated(getActualFunc(ctx.getViewRoot().getViewId()));
		
		if (!isPrevPageActivated && !isPostback)
		{
			result = true;
		}
		
		return result;
	}
	
	public static boolean isPostback()
	{
		
		return true;
	} 
	
}

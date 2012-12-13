package hu.iboard.coandco.datamagic.presentation.util;

import javax.faces.application.NavigationHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.apache.log4j.Logger;

/**
 * Phase Listener for managing the controller classes
 * 
 * 
 */
public class CommonPhaseListener implements PhaseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(CommonPhaseListener.class);
	public static final String PREVIEW_MANAGER = "previewManager";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent phaseEvent) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent phaseEvent) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		UIViewRoot root = ctx.getViewRoot();
		String viewId = root.getViewId();
		if (viewId != null && viewId.endsWith(".xhtml")) {

			String actFunction = Util.getActualFunc(viewId);

			PreviewManager previewManager = (PreviewManager) ctx.getApplication().getELResolver().getValue(
					ctx.getELContext(), null, PREVIEW_MANAGER);
			previewManager.addNewPage(actFunction);

			String lastPage = previewManager.getLastPage();

			if (lastPage != null && Util.canReloadData()) {
				String ctrlBeanName = DataMagicFunctionMap.getFunctionClassName(lastPage);
				if (ctrlBeanName != null) {
					AbstractController prevBean = (AbstractController) ctx.getApplication().getELResolver().getValue(
							ctx.getELContext(), null, ctrlBeanName);
					if (prevBean != null) {
						System.out.println("Reset data");
						prevBean.resetData();
					}
				}
			}
			try {
				String ctrlBeanName = DataMagicFunctionMap.getFunctionClassName(actFunction);
				if (ctrlBeanName != null) {
					AbstractController actBean = (AbstractController) ctx.getApplication().getELResolver().getValue(
							ctx.getELContext(), null, ctrlBeanName);
					if (actBean != null) {
						if (ctx.getRenderKit().getResponseStateManager().isPostback(ctx)) {
							System.out.println("Reload data");
							actBean.reloadData();
						} else {
							System.out.println("Load data");
							actBean.loadData();
						}
					}
				}
			} catch (Exception e) {
				logger.error("Some error occurred", e);
				// navigate to error page
				NavigationHandler nh = FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
				nh.handleNavigation(FacesContext.getCurrentInstance(), null, "error");
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
}

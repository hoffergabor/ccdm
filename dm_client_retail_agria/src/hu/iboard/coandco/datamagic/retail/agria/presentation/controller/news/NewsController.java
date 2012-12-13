package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.news;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.contentservice.ContentService;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class NewsController extends AbstractController {

	@ManagedProperty(value = "#{newsControllerData}")
	private NewsControllerData data;
	@ManagedProperty(value = "#{contentService}")
	private ContentService contentService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setContentList(getContentService().getContentByPageName("news", "hu", false));
		}
	}

	public NewsControllerData getData() {
		return data;
	}

	public void setData(NewsControllerData data) {
		this.data = data;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}

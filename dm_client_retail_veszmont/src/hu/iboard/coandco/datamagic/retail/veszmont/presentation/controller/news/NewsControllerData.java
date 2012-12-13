package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.news;

import hu.iboard.coandco.datamagic.model.content.Content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "newsControllerData")
@SessionScoped
public class NewsControllerData implements Serializable {

	private static final long serialVersionUID = -4076095511920809303L;
	
	private Content content;
	private List<Content> contentList = new ArrayList<Content>();
	
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public List<Content> getContentList() {
		return contentList;
	}
	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}
	
	

}

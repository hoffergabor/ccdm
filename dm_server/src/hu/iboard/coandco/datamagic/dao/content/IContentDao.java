package hu.iboard.coandco.datamagic.dao.content;

import hu.iboard.coandco.datamagic.model.content.Content;

import java.util.List;

public interface IContentDao {

	public Content getContentById(Integer contentId);

	public List<Content> getContentByPageName(String pagename, String language, Boolean showInvisible);

	public Content saveOrUpdateContent(Content content);

	public void deleteContent(Content content);

}

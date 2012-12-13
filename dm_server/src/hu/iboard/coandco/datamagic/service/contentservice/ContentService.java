package hu.iboard.coandco.datamagic.service.contentservice;

import hu.iboard.coandco.datamagic.model.content.Content;
import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;

import java.util.Date;
import java.util.List;

public class ContentService extends ContentServiceBase {

	@Override
	public Content getContentById(Integer contentId) {
		if (contentId == null)
			return null;
		return getContentDao().getContentById(contentId);
	}

	@Override
	public List<Content> getContentByPageName(String pagename, String language, Boolean showInvisible) {
		if (pagename == null)
			return null;
		return getContentDao().getContentByPageName(pagename, language, showInvisible);
	}

	@Override
	public Content saveOrUpdateContent(Content content) {
		if (content == null)
			return null;
		content.setModified(new Date());
		return getContentDao().saveOrUpdateContent(content);
	}

	@Override
	public void deleteContent(Content content) {
		if (content == null)
			return;
		getContentDao().deleteContent(content);
	}

	@Override
	public ProductCatalog saveOrUpdateProductCatalog(ProductCatalog productCatalog) {
		if (productCatalog == null)
			return null;
		return getProductCatalogDao().saveOrUpdateProductCatalog(productCatalog);
	}

	@Override
	public List<ProductCatalog> getAllProductCatalog() {
		return getProductCatalogDao().getAllProductCatalog();
	}

	@Override
	public ProductCatalog getProductCatalogById(Integer id) {
		if (id == null)
			return null;
		return getProductCatalogDao().getProductCatalogById(id);
	}

	@Override
	public void deleteProductcatalog(ProductCatalog productCatalog) {
		if (productCatalog == null)
			return;
		getProductCatalogDao().deleteProductCatalog(productCatalog);
	}

}

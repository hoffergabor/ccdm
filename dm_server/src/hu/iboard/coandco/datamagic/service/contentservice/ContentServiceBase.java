package hu.iboard.coandco.datamagic.service.contentservice;

import hu.iboard.coandco.datamagic.dao.content.IContentDao;
import hu.iboard.coandco.datamagic.dao.productcatalog.IProductCatalogDao;
import hu.iboard.coandco.datamagic.model.content.Content;
import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;

import java.util.List;

public abstract class ContentServiceBase {
	
	private IContentDao contentDao;
	
	private IProductCatalogDao iProductCatalogDao;
	
	public abstract Content getContentById(Integer contentId);

	public abstract List<Content> getContentByPageName(String pagename, String language, Boolean showVisible);

	public abstract Content saveOrUpdateContent(Content content);

	public abstract void deleteContent(Content content);
	
	public abstract ProductCatalog saveOrUpdateProductCatalog(ProductCatalog productCatalog);

	public abstract List<ProductCatalog> getAllProductCatalog();

	public abstract ProductCatalog getProductCatalogById(Integer id);
	
	public abstract void deleteProductcatalog(ProductCatalog productCatalog);

	public IProductCatalogDao getProductCatalogDao() {
		return iProductCatalogDao;
	}

	public void setProductCatalogDao(IProductCatalogDao iProductCatalogDao) {
		this.iProductCatalogDao = iProductCatalogDao;
	}

	public IContentDao getContentDao() {
		return contentDao;
	}

	public void setContentDao(IContentDao contentDao) {
		this.contentDao = contentDao;
	}
	
	

}

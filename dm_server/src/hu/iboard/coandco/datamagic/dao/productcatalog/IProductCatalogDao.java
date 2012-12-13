package hu.iboard.coandco.datamagic.dao.productcatalog;

import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;

import java.util.List;

public interface IProductCatalogDao {

	public ProductCatalog saveOrUpdateProductCatalog(ProductCatalog productCatalog);

	public List<ProductCatalog> getAllProductCatalog();

	public ProductCatalog getProductCatalogById(Integer id);

	public void deleteProductCatalog(ProductCatalog productCatalog);

}

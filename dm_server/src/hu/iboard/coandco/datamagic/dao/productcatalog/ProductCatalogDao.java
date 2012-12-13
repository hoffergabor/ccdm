package hu.iboard.coandco.datamagic.dao.productcatalog;

import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProductCatalogDao extends HibernateDaoSupport implements IProductCatalogDao {

	@Override
	public ProductCatalog saveOrUpdateProductCatalog(ProductCatalog productCatalog) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(productCatalog);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return productCatalog;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductCatalog> getAllProductCatalog() {
		List<ProductCatalog> productCatalog = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ProductCatalog.class).addOrder(Order.asc("sequence"));
			productCatalog = getHibernateTemplate().findByCriteria(criteria);
			if (productCatalog == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING ALL PRODUCTCATALOG!!!", e);
			return null;
		}
		return productCatalog;
	}

	@Override
	public ProductCatalog getProductCatalogById(Integer id) {
		return (ProductCatalog) getHibernateTemplate().get(ProductCatalog.class, id);
	}

	@Override
	public void deleteProductCatalog(ProductCatalog productCatalog) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(productCatalog);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

}

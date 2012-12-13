package hu.iboard.coandco.datamagic.dao.bizkomment;

import hu.iboard.coandco.datamagic.model.bizkomment.Bizkomment;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BizkommentDao extends HibernateDaoSupport implements IBizkommentDao {
	
	@Override
	public Bizkomment getBizkommentById(String id) {
		return (Bizkomment) getHibernateTemplate().get(Bizkomment.class, id);
	}
	
	

}

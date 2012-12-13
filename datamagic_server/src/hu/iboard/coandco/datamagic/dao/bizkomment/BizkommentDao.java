package hu.iboard.coandco.datamagic.dao.bizkomment;

import hu.iboard.coandco.datamagic.generated.Bizkomment;

import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BizkommentDao extends HibernateDaoSupport implements IBizkommentDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Bizkomment> findBizkommentByCriteria(DetachedCriteria criteria) {
		List<Bizkomment> kommentList = null;
		kommentList = getHibernateTemplate().findByCriteria(criteria);
		if (kommentList.size() == 0) {
			return null;
		}
		return kommentList;
	}

	@Override
	public void saveBizkomment(Bizkomment bizkomment) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().save(bizkomment);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

}

package hu.iboard.coandco.datamagic.dao.dmvsorsz;

import hu.iboard.coandco.datamagic.model.dmvsorsz.DmvSorsz;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DmvSorszDao extends HibernateDaoSupport implements IDmvSorszDao {

	@Override
	public DmvSorsz saveOrUpdateDmvSorsz(DmvSorsz dmvSorsz) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(dmvSorsz);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return dmvSorsz;
	}

	@Override
	public DmvSorsz getDmvSorszById(Integer id) {
		return (DmvSorsz) getHibernateTemplate().get(DmvSorsz.class, id);
	}

}

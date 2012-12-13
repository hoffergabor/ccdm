package hu.iboard.coandco.datamagic.dao.worker;

import hu.iboard.coandco.datamagic.model.dolgozo.Dolgozo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class WorkerDao extends HibernateDaoSupport implements IWorkerDao{

	@Override
	public Dolgozo getWorkerById(Integer workerId) {
		return (Dolgozo) getHibernateTemplate().get(Dolgozo.class, workerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Dolgozo getWorkerByLogin(String userName, String password) {
		List<Dolgozo> users = new ArrayList<Dolgozo>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Dolgozo.class).add(
					Restrictions.eq("webUser", userName)).add(Restrictions.eq("webPass", password));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY WORKER LOGIN!!!WORKER NAME: " + userName, e);
			return null;
		}
		return users.get(0);
	}

	@Override
	public Dolgozo saveOrUpdateWorker(Dolgozo worker) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(worker);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return worker;
	}

}

package hu.iboard.coandco.datamagic.dao.aconnect;

import hu.iboard.coandco.datamagic.generated.Aconnect;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AconnectDao extends HibernateDaoSupport implements IAcconectDao {

	@Override
	public Aconnect saveOrUpdateAconnect(Aconnect aconnect) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(aconnect);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return aconnect;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getIdFromLastRecord() {
		List<Integer> recordsList = new ArrayList<Integer>();
		try {
			String query = "select max(a.id) from Aconnect as a where a.id like '_______'";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by max id from aconnect!", e);
			return null;
		}
		return recordsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aconnect> getAconnectForNotification(Integer id) {
		List<Aconnect> aconnects = new ArrayList<Aconnect>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Aconnect.class);
			criteria = criteria.add(Restrictions.eq("ownerid", id.toString())).add(
					Restrictions.eq("ownertype", "inghibabej"));

			aconnects = getHibernateTemplate().findByCriteria(criteria);

			if (aconnects == null || aconnects.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING ACONNETCS FOR NOTIFICATION " + e.getMessage());
			return null;
		}
		return aconnects;
	}

}

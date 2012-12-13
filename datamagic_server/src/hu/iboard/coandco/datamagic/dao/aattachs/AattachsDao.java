package hu.iboard.coandco.datamagic.dao.aattachs;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import hu.iboard.coandco.datamagic.generated.Aattachs;

public class AattachsDao extends HibernateDaoSupport implements IAattachsDao {

	@SuppressWarnings("unchecked")
	@Override
	public Aattachs getAattachsById(Integer id) {
		List<Aattachs> recordsList = new ArrayList<Aattachs>();
		try {
			String query = "from Aattachs as a where a.id=:id";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aattach by id!", e);
			return null;
		}
		return recordsList.get(0);
	}

	@Override
	public Aattachs saveOrUpdateAattach(Aattachs aattachs) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(aattachs);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return aattachs;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getIdFromLastRecord() {
		List<Integer> recordsList = new ArrayList<Integer>();
		try {
			String query = "select max(a.id) from Aattachs as a where a.id like '_______'";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by max id from aattach!", e);
			return null;
		}
		return recordsList.get(0);
	}

}

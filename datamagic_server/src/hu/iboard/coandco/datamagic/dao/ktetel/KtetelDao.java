package hu.iboard.coandco.datamagic.dao.ktetel;

import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class KtetelDao extends HibernateDaoSupport implements IKtetelDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ktetel_CDH> getKtetelByMkateg4(Integer id) {
		List<Ktetel_CDH> recordsList = null;
		try {
			String query = "from Ktetel_CDH k where k.mkateg4=:id";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting ktetel_cdh by munkszam id!", e);
			return null;
		}
		return recordsList;
	}

}

package hu.iboard.coandco.datamagic.dao.munkszam2;

import hu.iboard.coandco.datamagic.generated.Munkszam2;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class Munkszam2Dao extends HibernateDaoSupport implements IMunkszam2Dao {

	@Override
	public Munkszam2 getMunkszam2ById(Integer id) {
		return (Munkszam2) getHibernateTemplate().get(Munkszam2.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Munkszam2> getMunkszam2ByMunkszam(String munkszam) {
		List<Munkszam2> recordsList = null;
		try {
			String query = "from Munkszam2 m where m.munkszam=:munkszam";
			recordsList = getHibernateTemplate().findByNamedParam(query, "munkszam", munkszam);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting munkszam2 by munkszam!", e);
			return null;
		}
		return recordsList;
	}

}

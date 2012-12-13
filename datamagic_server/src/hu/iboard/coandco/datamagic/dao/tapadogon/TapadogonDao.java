package hu.iboard.coandco.datamagic.dao.tapadogon;

import hu.iboard.coandco.datamagic.generated.Tapadogon;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TapadogonDao extends HibernateDaoSupport implements ITapadogonDao{

	@Override
	public Tapadogon getTapadogonById(Integer id) {
		return (Tapadogon) getHibernateTemplate().get(Tapadogon.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tapadogon> getTapadogonByAkod(Integer akod) {
		List<Tapadogon> records = null;
		try {
			String query = "from Tapadogon t where t.akod=:akod";
			records = getHibernateTemplate().findByNamedParam(query, "akod", akod);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list tapadogon by akod: " + e.getMessage());
			return null;
		}
		return records;
	}

}

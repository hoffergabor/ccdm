package hu.iboard.coandco.datamagic.dao.fuvtura;

import hu.iboard.coandco.datamagic.generated.Fuvtura;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FuvturaDao extends HibernateDaoSupport implements IFuvturaDao {

	@Override
	public Fuvtura getFuvturaById(Integer id) {
		return (Fuvtura) getHibernateTemplate().get(Fuvtura.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fuvtura> getFuvturaByPartnerUtvonal(Integer id) {
		List<Fuvtura> records = null;
		try {
			String query = "from Fuvtura f where f.id=:id order by f.moddatum desc";
			records = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list fuvtura by partner utvonal: " + e.getMessage());
			return null;
		}
		return records;
	}

	
}

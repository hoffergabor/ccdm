package hu.iboard.coandco.datamagic.dao.fuvpartner;

import hu.iboard.coandco.datamagic.generated.Fuvpartner;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FuvpartnerDao extends HibernateDaoSupport implements IFuvpartnerDao {

	@Override
	public Fuvpartner getFuvpartnerById(Integer id) {
		return (Fuvpartner) getHibernateTemplate().get(Fuvpartner.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fuvpartner> getFuvpartnersByPartnerId(Integer id) {
		List<Fuvpartner> records = null;
		try {
			String query = "from Fuvpartner f where f.partner=:id order by f.moddatum desc";
			records = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list fuvpartners by partner id: " + e.getMessage());
			return null;
		}
		return records;
	}

}

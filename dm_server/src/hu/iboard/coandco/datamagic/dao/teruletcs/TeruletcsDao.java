package hu.iboard.coandco.datamagic.dao.teruletcs;

import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TeruletcsDao extends HibernateDaoSupport implements ITeruletcsDao {

	@Override
	public Teruletcs getTeruletcsById(Integer id) {
		return (Teruletcs) getHibernateTemplate().get(Teruletcs.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teruletcs> getTeruletcsByIrsz(Integer irsz) {
		List<Teruletcs> records = new ArrayList<Teruletcs>();
		try {
			String query = "from Teruletcs t where t.irsz=:irsz";
			records = getHibernateTemplate().findByNamedParam(query, "irsz", irsz);
			if (records == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING TERULETCS BY IRSZ!", e);
		}
		return records;
	}

}

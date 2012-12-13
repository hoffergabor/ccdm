package hu.iboard.coandco.datamagic.dao.arvalt;

import hu.iboard.coandco.datamagic.generated.Arvalt;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ArvaltDao extends HibernateDaoSupport implements IArvaltDao{

	@Override
	public Arvalt getArvaltById(Integer id) {
		return (Arvalt) getHibernateTemplate().get(Arvalt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Arvalt> getArvaltByArutasitasForPartner(Integer arutasitas, Integer arnev) {
		List<Arvalt> records = null;
		try {
			String query = "from Arvalt a where a.arutasitas='" + arutasitas+ "' and a.arnev='" + arnev + "'";
			records = getHibernateTemplate().find(query);
			if (records == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at list arvalt for partner by arutasitas: ", e);
			return null;
		}
		return records;
	}

	
	

}

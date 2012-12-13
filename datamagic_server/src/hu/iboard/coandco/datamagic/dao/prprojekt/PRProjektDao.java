package hu.iboard.coandco.datamagic.dao.prprojekt;

import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PRProjektDao extends HibernateDaoSupport implements IPRProjektDao {

	@Override
	public PRProjekt getPRProjektById(Integer id) {
		return (PRProjekt) getHibernateTemplate().get(PRProjekt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PRProjekt> getPRProjektByPartnerId(Integer vevokod) {
		List<PRProjekt> recordsList = null;
		String query = "";
		try {
			if (vevokod != null) {
				query = "from PRProjekt p where p.partner=:vevokod and p.foprojekt=0 and p.sablon=false and p.publikus=1";
				recordsList = getHibernateTemplate().findByNamedParam(query, "vevokod", vevokod);
			} else {
				query = "from PRProjekt p where p.foprojekt=0 and p.sablon=false and p.publikus=1";
				recordsList = getHibernateTemplate().find(query);
			}
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting prprojekt by vevokod", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PRProjekt> getAlPRProjektByFoPRProjektId(Integer id) {
		List<PRProjekt> recordsList = null;
		String query = "";
		try {
			query = "from PRProjekt p where p.foprojekt=:id and p.sablon=false and p.publikus=1";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting alprprojekt by id", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PRProjekt> getPRProjektByDimenzio(Integer dimenzio) {
		List<PRProjekt> recordsList = null;
		String query = "";
		try {
			query = "from PRProjekt p where p.dimenzio=:dimenzio";
			recordsList = getHibernateTemplate().findByNamedParam(query, "dimenzio", dimenzio);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting prprojekt by dimenzio", e);
			return null;
		}
		return recordsList;
	}

}

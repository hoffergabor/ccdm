package hu.iboard.coandco.datamagic.dao.prarucsatolo;

import hu.iboard.coandco.datamagic.vo.prarucsatolo.PRArucsatolo;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PRArucsatoloDao extends HibernateDaoSupport implements IPRArucsatoloDao {

	@Override
	public PRArucsatoloDao getPRArucsatoloById(Integer id) {
		return (PRArucsatoloDao) getHibernateTemplate().get(PRArucsatoloDao.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PRArucsatolo> getPRArucsatoloByPRProjektId(Integer id) {
		List<PRArucsatolo> recordsList = null;
		String query = "";
		try {
			query = "from PRArucsatolo p where p.projekt=:id";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting prarucsatolo by prprojekt id", e);
			return null;
		}
		return recordsList;
	}

}

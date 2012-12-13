package hu.iboard.coandco.datamagic.dao.berbeadas;

import hu.iboard.coandco.datamagic.generated.Berbeadas;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BerbeadasDao extends HibernateDaoSupport implements IBerbeadasDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getLakasByPartnerId(Integer partnerId) {
		List<Integer> recordsList = null;
		try {
			String query = "select a.arukod from AruExt a where a.berbeadas in(select distinct b.id from Berbeadas as b where b.ugyfel=:partnerId)";
			recordsList = getHibernateTemplate().findByNamedParam(query, "partnerId", partnerId);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting berbeadas lakas!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getRentersIdByForRealtyArrangement() {
		List<Integer> recordsList = null;
		try {
			String query = "select distinct b.ugyfel from Berbeadas b where b.lakas in(select distinct i.lakas from Intezkedes i where i.lakas>0)";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting renters id for realty !", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getBerbeadasUgyfelById(Integer berbeadasId) {
		List<Integer> recordsList = null;
		try {
			String query = "select b.ugyfel from Berbeadas b where b.id=:berbeadasId";
			recordsList = getHibernateTemplate().findByNamedParam(query, "berbeadasId", berbeadasId);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting berbeadas ugyfel id!", e);
			return null;
		}
		return recordsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllPartnerIdsFromBerbeadas() {
		List<Integer> recordsList = null;
		try {
			String query = "select distinct b.ugyfel from Berbeadas b where b.ugyfel>0 order by b.ugyfel asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting renter ids!", e);
			return null;
		}
		return recordsList;
	}

	@Override
	public Berbeadas getBerbeadasById(Integer id) {
		return (Berbeadas) getHibernateTemplate().get(Berbeadas.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Berbeadas> getBerbeadasByAktBerCheckContract(List<Integer> aktBer) {
		List<Berbeadas> recordsList = null;
		try {
			String query = "from Berbeadas as b where b.id in (:aktBer) and b.ugyfel!=0";
			recordsList = getHibernateTemplate().findByNamedParam(query, "aktBer", aktBer);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruext for renter!", e);
			return null;
		}
		return recordsList;
	}


}

package hu.iboard.coandco.datamagic.dao.aruext;

import hu.iboard.coandco.datamagic.generated.AruExt;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AruExtDao extends HibernateDaoSupport implements IAruExtDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAruExtByProjectId(Integer projectId) {
		List<Object[]> recordsList = null;
		try {
			String query = "select a.arukod,a.irsz,a.varos,a.cim,a.cim2,a.aktBer,a.tipus,a.aktiv from AruExt a where a.project=" + projectId
					+ " and a.aktiv=true order by a.varos,a.cim asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting aruext by projectId!", e);
			return null;
		}
		return recordsList;
	}

	@Override
	public AruExt getAruExtById(Integer id) {
		return (AruExt) getHibernateTemplate().get(AruExt.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getAruExtByPartnerId(Integer partnerId) {
		List<Object[]> recordsList = null;
		try {
			String query = "select a.arukod,a.project,a.irsz,a.varos,a.cim,a.emelet,a.szobaSzam,a.aktBer from AruExt a where a.vevo1=" + partnerId
					+ " order by a.varos asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error getting aruext by partnerId!", e);
			return null;
		}
		return recordsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] getAruExtAddressById(Integer id) {
		List<Object[]> recordsList = null;
		try {
			String query = "select a.nev,a.irsz,a.varos,a.cim,a.cim2 from AruExt a where a.arukod=" + id + "order by a.cim asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruext address!", e);
			return null;
		}
		return recordsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAruExtForRenter(Integer partnerId) {
		List<Object[]> recordsList = null;
		try {
			String query = "select a.arukod,a.project,a.irsz,a.varos,a.cim,a.cim2,a.tipus from AruExt a where a.aktBer in (select b.id from Berbeadas b where b.ugyfel="
					+ partnerId + ") and a.aktiv=true order by a.cim asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruext for renter!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getBerbeadasForRenter() {
		List<Integer> recordsList = null;
		try {
			String query = "select distinct b.ugyfel from Berbeadas as b where b.id in (select a.aktBer from AruExt as a where a.aktBer>0)";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruext for renter!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getBerbeadasByAktBer(List<Integer> aktBer) {
		List<Integer> recordsList = null;
		try {
			String query = "select distinct b.ugyfel from Berbeadas as b where b.id in (:aktBer) and b.ugyfel!=0";
			recordsList = getHibernateTemplate().findByNamedParam(query, "aktBer", aktBer);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruext for renter!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruExt> getAruExtsForRenter(Integer partnerId) {
		List<AruExt> recordsList = null;
		try {
			String query = "from AruExt a where a.aktBer in(select b.id from Berbeadas b where b.ugyfel=" + partnerId + ") and a.aktiv=true";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruextId for renter!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getBerbeadasIdsForRealtyRenter() {
		List<Integer> recordsList = null;
		try {
			String query = "select a.aktBer from AruExt as a where a.arukod in(select distinct i.lakas from Intezkedes as i) and a.aktBer>0";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting aruextId for renter!", e);
			return null;
		}
		return recordsList;

	}

}

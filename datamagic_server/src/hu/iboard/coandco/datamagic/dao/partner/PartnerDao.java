package hu.iboard.coandco.datamagic.dao.partner;

import hu.iboard.coandco.datamagic.generated.Fizimod;
import hu.iboard.coandco.datamagic.generated.Partner;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PartnerDao extends HibernateDaoSupport implements IPartnerDao {

	public PartnerDao() {
	}

	@Override
	public Partner loadPartner(Integer id) {
		return (Partner) getHibernateTemplate().get(Partner.class, id);
	}

	@Override
	public Partner updatePartner(Partner partner) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(partner);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return partner;
	}

	@SuppressWarnings("unchecked")
	public List<Partner> getPartnerById(Integer id) {

		List<Partner> recordsList = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(Restrictions.eq("vevokod", id))
					.setFetchMode("vevocsop", FetchMode.JOIN).setFetchMode("vevocsop.arnev", FetchMode.JOIN)
					.setFetchMode("vevocsop.arnev.deviza", FetchMode.JOIN);
			recordsList = getHibernateTemplate().findByCriteria(criteria);
			if (recordsList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting partner", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllPartner() {
		List<Object[]> recordsList = null;
		try {
			recordsList = getHibernateTemplate().find("select p.nev,p.vevokod from Partner p order by p.nev asc");
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting all partner", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPartnersByName(String name) {
		List<Object[]> recordsList = null;
		try {
			if (!name.equals("") || name != null) {
				String query = "select p.nev,p.vevokod from Partner p where p.nev like :name";
				recordsList = getHibernateTemplate().findByNamedParam(query, "name", "%" + name + "%");
			}
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting partner by name", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fizimod> getPaymentTypes() {
		List<Fizimod> recordsList = null;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Fizimod.class);
			recordsList = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			logger.error("Error by getting payment types", e);
			return null;
		}
		return recordsList;
	}

	@Override
	public Partner getPartnerByReferenceId(Integer referenceId) {
		return (Partner) getHibernateTemplate().get(Partner.class, referenceId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPartnersNameByPartnerId(List<Integer> partnerIds) {
		List<Object[]> recordsList = null;
		try {
			String query = "select p.vevokod,p.nev from Partner p where p.vevokod in (:ids) order by p.nev asc";
			recordsList = getHibernateTemplate().findByNamedParam(query, "ids", partnerIds);
			if (recordsList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting partners name", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getPartnersNameByPartnerName(List<Integer> partnerIds, String name) {
		List<Object[]> recordsList = null;
		String[] pNames = new String[2];
		pNames[0] = "ids";
		pNames[1] = "name";

		Object[] values = new Object[2];
		values[0] = partnerIds;
		values[1] = "%" + name + "%";

		try {
			String query = "select p.vevokod,p.nev from Partner p where p.vevokod in (:ids) and p.nev like :name order by p.nev asc";
			recordsList = getHibernateTemplate().findByNamedParam(query, pNames, values);
			if (recordsList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting partners name", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getPartnerNameByPartnerId(Integer partnerId) {
		List<String> recordsList = new ArrayList<String>();
		try {
			String query = "select p.nev from Partner p where p.vevokod=:id";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", partnerId);
			if (recordsList == null || recordsList.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting partner name", e);
			return null;
		}
		return recordsList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Partner getPartnerByLogin(String userName, String password) {
		List<Partner> users = new ArrayList<Partner>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(
					Restrictions.eq("weblogin", userName)).add(Restrictions.eq("webpassw", password));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				logger.info("No such partner in database: " + userName);
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY PARTNER LOGIN!!!PARTNER NAME: " + userName, e);
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Partner getPartnerByEmergencyLogin(String userName) {
		List<Partner> users = new ArrayList<Partner>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(
					Restrictions.eq("weblogin", userName));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				logger.info("No such partner in database: " + userName);
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY PARTNER EMERGENCY LOGIN!!!PARTNER NAME: " + userName, e);
			return null;
		}
		return users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Partner> getStoresById(Integer id) {
		List<Partner> recordsList = null;
		try {
			String query = "from Partner p where p.szlfizeto=:id";
			recordsList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (recordsList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting partner by szlfizeto", e);
			return null;
		}
		return recordsList;
	}

}

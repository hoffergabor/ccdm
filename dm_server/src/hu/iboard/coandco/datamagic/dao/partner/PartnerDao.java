package hu.iboard.coandco.datamagic.dao.partner;

import hu.iboard.coandco.datamagic.model.partner.Partner;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class PartnerDao extends HibernateDaoSupport implements IPartnerDao {

	@Override
	public Partner getPartnerById(Integer id) {
		return (Partner) getHibernateTemplate().get(Partner.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Partner getPartnerByLogin(String userName, String password) {
		List<Partner> users = new ArrayList<Partner>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(Restrictions.eq("weblogin", userName))
					.add(Restrictions.eq("webpassw", password));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
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
	public List<Object[]> getPartnersByName(String name) {
		List<Object[]> recordsList = new ArrayList<Object[]>();
		try {
			if (!name.equals("") || name != null) {
				String query = "select p.nev,p.vevokod from Partner p where p.nev like :name";
				recordsList = getHibernateTemplate().findByNamedParam(query, "name", "%" + name + "%");
			}
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting partner by name", e);
			return null;
		}
		return recordsList;
	}

	@Override
	public Partner saveOrUpdatePartner(Partner partner) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(partner);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return partner;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Integer getLastEmptyPartnerId() {
		List<Integer> recordsList = new ArrayList<Integer>();
		try {
			String query = "select max(p.vevokod) from Partner p where p.vevokod like '9_______'";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList.get(0) == null) {
				return 90000000;
			}
		} catch (Exception e) {
			logger.error("Error by getting last partner id", e);
			return null;
		}
		return recordsList.get(0) + 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Partner getPartnerByEmail(String email) {
		List<Partner> users = new ArrayList<Partner>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Partner.class).add(
					Restrictions.eq("webLogin", email));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING PARTNER BY EMAIL!!!", e);
			return null;
		}
		return users.get(0);
	}
}

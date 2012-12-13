package hu.iboard.coandco.datamagic.dao.township;

import hu.iboard.coandco.datamagic.model.township.TownShip;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class TownshipDao extends HibernateDaoSupport implements ITownshipDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<TownShip> getAllTownship(String input) {
		List<TownShip> telepulesNevek = new ArrayList<TownShip>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TownShip.class);
			criteria.add(Restrictions.like("name", input + "%")).addOrder(Order.asc("name"));
			telepulesNevek = getHibernateTemplate().findByCriteria(criteria);
			if (telepulesNevek == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR HAS OCCURED BY GETTING TOWNSHIP: " + e.getMessage());
		}
		return telepulesNevek;
	}

	@Override
	public TownShip getTownShipById(Integer id) {
		return (TownShip) getHibernateTemplate().get(TownShip.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TownShip> getTownshipsByZipCode(String zipCode) {
		List<TownShip> telepulesek = new ArrayList<TownShip>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TownShip.class);
			criteria.add(Restrictions.eq("zipcode", zipCode)).addOrder(Order.asc("name"));
			telepulesek = getHibernateTemplate().findByCriteria(criteria);
			if (telepulesek == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR HAS OCCURED GETTING TOWNSHIP BY ZIPCODE: " + e.getMessage());
		}
		return telepulesek;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TownShip> getZipCodes(String input) {
		List<TownShip> telepulesNevek = new ArrayList<TownShip>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TownShip.class);
			criteria.add(Restrictions.like("zipcode", input + "%")).addOrder(Order.asc("zipcode"));
			telepulesNevek = getHibernateTemplate().findByCriteria(criteria);
			if (telepulesNevek == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR HAS OCCURED BY GETTING TOWNSHIP: " + e.getMessage());
		}
		return telepulesNevek;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TownShip getTownShipByZipAndName(String zipcode, String name) {
		List<TownShip> telepulesek = new ArrayList<TownShip>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(TownShip.class);
			criteria.add(Restrictions.eq("zipcode", zipcode)).add(Restrictions.eq("name", name))
					.addOrder(Order.asc("name"));
			telepulesek = getHibernateTemplate().findByCriteria(criteria);
			if (telepulesek == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR HAS OCCURED GETTING TOWNSHIP BY ZIPCODE AND NAME: " + e.getMessage());
		}
		return telepulesek.get(0);
	}

}

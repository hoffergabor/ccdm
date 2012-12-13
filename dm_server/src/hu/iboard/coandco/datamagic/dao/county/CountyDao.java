package hu.iboard.coandco.datamagic.dao.county;

import hu.iboard.coandco.datamagic.model.county.County;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CountyDao extends HibernateDaoSupport implements ICountyDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<County> getAllCounty() {
		List<County> counties = new ArrayList<County>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(County.class);
			counties = getHibernateTemplate().findByCriteria(criteria);
			if (counties == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR HAS OCCURED BY GETTING COUNTIES: " + e.getMessage());
		}
		return counties;
	}

	@Override
	public County getCountyById(Integer id) {
		return (County) getHibernateTemplate().get(County.class, id);
	}

}

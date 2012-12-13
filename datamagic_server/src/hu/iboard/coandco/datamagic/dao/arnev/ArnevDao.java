package hu.iboard.coandco.datamagic.dao.arnev;

import hu.iboard.coandco.datamagic.generated.Arnev;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ArnevDao extends HibernateDaoSupport implements IArnevDao {

	@Override
	public Arnev getArnevById(Integer id) {
		return (Arnev) getHibernateTemplate().get(Arnev.class, id);
	}

}

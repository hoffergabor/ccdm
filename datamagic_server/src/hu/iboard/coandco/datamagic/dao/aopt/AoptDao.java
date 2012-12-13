package hu.iboard.coandco.datamagic.dao.aopt;

import hu.iboard.coandco.datamagic.generated.Aopt;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AoptDao extends HibernateDaoSupport implements IAoptDao {

	@Override
	public Aopt getAoptById(Integer id) {
		return (Aopt) getHibernateTemplate().get(Aopt.class, id);
	}

}

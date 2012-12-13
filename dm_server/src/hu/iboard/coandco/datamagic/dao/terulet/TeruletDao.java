package hu.iboard.coandco.datamagic.dao.terulet;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import hu.iboard.coandco.datamagic.model.terulet.Terulet;

public class TeruletDao extends HibernateDaoSupport implements ITeruletDao {

	@Override
	public Terulet getTeruletById(Integer id) {
		return (Terulet) getHibernateTemplate().get(Terulet.class, id);
	}

}

package hu.iboard.coandco.datamagic.dao.admin;

import hu.iboard.coandco.datamagic.model.admin.Admin;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class AdminDao extends HibernateDaoSupport implements IAdminDao {

	@SuppressWarnings("unchecked")
	@Override
	public Admin getAdminByLogin(String email, String password) {
		List<Admin> admins = new ArrayList<Admin>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class).add(Restrictions.eq("email", email)).add(Restrictions.eq("password", password))
					.add(Restrictions.eq("active", true));
			admins = getHibernateTemplate().findByCriteria(criteria);
			if (admins == null || admins.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY ADMIN LOGIN!!!", e);
			return null;
		}
		return admins.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> getAllAdmin() {
		List<Admin> admins;
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Admin.class);
			admins = getHibernateTemplate().findByCriteria(criteria);
			if (admins == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING ALL ADMIN!!!", e);
			return null;
		}
		return admins;
	}

	@Override
	public Admin saveOrUpdateAdmin(Admin admin) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(admin);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return admin;
	}

	@Override
	public void deleteAdmin(Admin admin) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(admin);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

}

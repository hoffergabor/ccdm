package hu.iboard.coandco.datamagic.dao.newsletteremail;

import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NewsLetterEmailDao extends HibernateDaoSupport implements INewsLetterEmailDao {

	@Override
	public NewsLetterEmail saveOrUpdateNewsLetterEmail(NewsLetterEmail email) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(email);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return email;
	}

	@SuppressWarnings("unchecked")
	@Override
	public NewsLetterEmail getNewsLetterEmailByEmail(String email) {
		List<NewsLetterEmail> emails = new ArrayList<NewsLetterEmail>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(NewsLetterEmail.class).add(
					Restrictions.eq("email", email));
			emails = getHibernateTemplate().findByCriteria(criteria);
			if (emails == null || emails.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING NEWSLETTER EMAIL BY EMAIL!", e);
			return null;
		}
		return emails.get(0);
	}

}

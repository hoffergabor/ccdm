package hu.iboard.coandco.datamagic.dao.content;

import hu.iboard.coandco.datamagic.model.content.Content;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ContentDao extends HibernateDaoSupport implements IContentDao {

	@Override
	public Content getContentById(Integer contentId) {
		return (Content) getHibernateTemplate().get(Content.class, contentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Content> getContentByPageName(String pagename, String language, Boolean showInvisible) {
		List<Content> contents = new ArrayList<Content>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Content.class).add(
					Restrictions.eq("pageName", pagename)).addOrder(Order.asc("sequence"));
			criteria.add(Restrictions.eq("language", language));
			if (!showInvisible)
				criteria.add(Restrictions.eq("visible", true));
			contents = getHibernateTemplate().findByCriteria(criteria);
			if (contents == null)
				return null;
		} catch (Exception e) {
			logger.error("ERROR GETTING CONTENT BY PAGENAME: " + e.getMessage());
			return null;
		}
		return contents;
	}

	@Override
	public Content saveOrUpdateContent(Content content) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(content);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return content;
	}

	@Override
	public void deleteContent(Content content) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(content);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

}

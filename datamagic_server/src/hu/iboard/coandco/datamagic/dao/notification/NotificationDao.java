package hu.iboard.coandco.datamagic.dao.notification;

import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.util.DataMagicConstants;
import hu.iboard.coandco.datamagic.vo.notification.IngHibaStatuszVO;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class NotificationDao extends HibernateDaoSupport implements INotificationDao, DataMagicConstants {

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> findNotificationByUserId(String userType, Integer partnerId) {
		List<NotificationVO> recordsList = null;
		try {
			String query = "";
			if (userType.equals(WORKER)) {
				query = "from NotificationVO n order by n.bejelentes_ido desc";
			}
			if (userType.equals(PARTNER)) {
				query = "from NotificationVO n where n.project in(select p.id from Project p where p.tulajdonos=" + partnerId
						+ ") order by n.bejelentes_ido desc";
			}
			if (userType.equals(RENTER)) {
				query = "from NotificationVO n where n.user=" + partnerId + " and n.userType='b' order by n.bejelentes_ido desc";
			}
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting notification", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> findNotificationByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<NotificationVO> notification = new ArrayList<NotificationVO>();
		notification = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (notification.size() == 0) {
			return null;
		}
		return notification;
	}

	@Override
	public NotificationVO saveOrUpdateNotification(NotificationVO notificationVO) {
		if (notificationVO == null)
			return null;
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(notificationVO);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return notificationVO;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> findNotificationByDate(String userType, Integer partnerId, Date from, Date to) {
		List<NotificationVO> recordsList = null;
		try {
			String query = "";
			if (userType.equals(WORKER)) {
				query = "from NotificationVO n where n.bejelentes_ido>='" + from + "' and n.bejelentes_ido<='" + to + "' order by n.bejelentes_ido desc";
			}
			if (userType.equals(PARTNER)) {
				query = "from NotificationVO n where n.project in(select p.id from Project p where p.tulajdonos=" + partnerId + ") and n.bejelentes_ido>='"
						+ from + "' and n.bejelentes_ido<='" + to + "' order by n.bejelentes_ido desc";
			}
			if (userType.equals(RENTER)) {
				query = "from NotificationVO n where n.user=" + partnerId + " and n.userType='b' and n.bejelentes_ido>='" + from + "' and n.bejelentes_ido<='"
						+ to + "' order by n.bejelentes_ido desc";
			}
			getHibernateTemplate().setMaxResults(1000);
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting notification", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> getNotificationsByProjectId(Integer projectId) {

		List<NotificationVO> recordsList = null;
		try {
			String query = "from NotificationVO n where n.project=" + projectId + " order by n.bejelentes_ido desc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting notification", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> getNotificationsByOwnerId(Integer ownerId) {
		List<NotificationVO> recordsList = null;
		try {
			String query = "from NotificationVO n where n.project in (select p.id from Project p where p.tulajdonos=" + ownerId
					+ ") order by n.bejelentes_ido desc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting notification", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> getNotificationsByAruExtList(List<AruExt> aruexts) {
		List<NotificationVO> recordsList = null;
		try {
			String query = "from NotificationVO n where n.aruExt in (:aruexts) order by n.bejelentes_ido desc";
			recordsList = getHibernateTemplate().findByNamedParam(query, "aruexts", aruexts);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error getting notifications by aruext list!", e);
			return null;
		}
		return recordsList;
	}

	@Override
	public IngHibaStatuszVO getIngHibaStatusz(Integer statuszId) {
		return (IngHibaStatuszVO) getHibernateTemplate().get(IngHibaStatuszVO.class, statuszId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> getNotificationsByAruExtList(List<AruExt> aruexts, Date from, Date to) {
		List<NotificationVO> recordsList = null;
		try {
			String query = "from NotificationVO n where n.aruExt in (:aruexts)  and n.bejelentes_ido>='" + from + "' and n.bejelentes_ido<='" + to
					+ "' order by n.bejelentes_ido desc";
			recordsList = getHibernateTemplate().findByNamedParam(query, "aruexts", aruexts);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error getting notifications by aruext list between dates!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllBejelentoFromNotification() {
		List<Object[]> recordsList = new ArrayList<Object[]>();
		try {
			String query = "select distinct n.user,n.userType from NotificationVO n order by n.user,n.userType asc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error getting bejelentok from notifications!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationVO> getNotificationsByBejelento(Integer userId, char userType) {
		List<NotificationVO> recordsList = new ArrayList<NotificationVO>();
		try {
			String query = "from NotificationVO n where n.user='" + userId + "' and n.userType='" + userType + "' order by n.bejelentes_ido desc";
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null || recordsList.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error getting notifications by bejelento!", e);
			return null;
		}
		return recordsList;
	}
}

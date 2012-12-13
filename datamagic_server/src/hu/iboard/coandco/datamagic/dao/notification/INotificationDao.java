package hu.iboard.coandco.datamagic.dao.notification;

import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.vo.notification.IngHibaStatuszVO;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface INotificationDao {

	public NotificationVO saveOrUpdateNotification(NotificationVO notificationVO);

	public List<NotificationVO> findNotificationByUserId(String userType, Integer partnerId);

	public List<NotificationVO> findNotificationByCriteria(DetachedCriteria criteria, int firstResult, int maxResult);

	public List<NotificationVO> findNotificationByDate(String userType, Integer partnerId, Date from, Date to);

	public List<NotificationVO> getNotificationsByProjectId(Integer projectId);

	public List<NotificationVO> getNotificationsByOwnerId(Integer ownerId);

	public List<NotificationVO> getNotificationsByAruExtList(List<AruExt> aruexts);

	public List<NotificationVO> getNotificationsByAruExtList(List<AruExt> aruexts, Date from, Date to);

	public IngHibaStatuszVO getIngHibaStatusz(Integer statuszId);

	public List<Object[]> getAllBejelentoFromNotification();

	public List<NotificationVO> getNotificationsByBejelento(Integer userId, char userType);
}

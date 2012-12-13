package hu.iboard.coandco.datamagic.service.notification;

import hu.iboard.coandco.datamagic.dao.aattachs.IAattachsDao;
import hu.iboard.coandco.datamagic.dao.aconnect.IAcconectDao;
import hu.iboard.coandco.datamagic.dao.aopt.IAoptDao;
import hu.iboard.coandco.datamagic.dao.berbeadas.IBerbeadasDao;
import hu.iboard.coandco.datamagic.dao.flattype.IFlatTypeDao;
import hu.iboard.coandco.datamagic.dao.notification.INotificationDao;
import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.Berbeadas;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.flattype.FlatTypeVO;
import hu.iboard.coandco.datamagic.vo.notification.IngHibaStatuszVO;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

public abstract class NotificationServiceBase extends ServiceBase {

	private INotificationDao notificationDao;
	private IFlatTypeDao flatTypeDao;
	private IAattachsDao aattachsDao;
	private IAcconectDao aconnectDao;
	private IAoptDao aoptDao;
	private IBerbeadasDao berbeadasDao;

	public abstract List<NotificationVO> getNotifications(String userType, Integer partnerId);

	public abstract List<NotificationVO> getNotificationsByDate(String userType, Integer partnerId, Date from, Date to);

	public abstract List<NotificationVO> getNotificationsByProjectId(Integer projectId);

	public abstract List<NotificationVO> getNotificationsByOwner(Integer ownerId);

	public abstract NotificationVO saveOrUpdateNotification(NotificationVO notification);

	public abstract List<NotificationVO> getNotificationsByAruExts(List<AruExt> aruexts);

	public abstract List<NotificationVO> getNotificationsByAruExtsByDate(List<AruExt> aruexts, Date from, Date to);

	public abstract IngHibaStatuszVO getIngHibaStatusz(Integer statuszId);

	public abstract String uploadFile(List<UploadedFile> files, String path);

	public abstract List<String> downloadAttachForNotification(String md5FileName, String path);

	public abstract FlatTypeVO getFlatTypeById(Integer id);

	public abstract Aattachs saveOrUpdateAattach(Aattachs aattachs);

	public abstract Aconnect saveOrUpdateAconnect(Aconnect aconnect);

	public abstract List<Aattachs> getAttachsForNotification(Integer id);

	public abstract Integer getBerbeadasUgyfelById(Integer berbeadasId);

	public abstract List<Object[]> getAllBejelentoFromNotification();

	public abstract List<NotificationVO> getNotificationsByBejelento(Integer userId, char userType);

	public abstract Berbeadas getBerbeadasById(Integer id);
	
	public abstract List<Berbeadas> getBerbeadasByAktBerCheckContract(List<Integer> aktBer);
	
	

	public INotificationDao getNotificationDao() {
		return notificationDao;
	}

	public void setNotificationDao(INotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	public IFlatTypeDao getFlatTypeDao() {
		return flatTypeDao;
	}

	public void setFlatTypeDao(IFlatTypeDao flatTypeDao) {
		this.flatTypeDao = flatTypeDao;
	}

	public IAattachsDao getAattachsDao() {
		return aattachsDao;
	}

	public void setAattachsDao(IAattachsDao aattachsDao) {
		this.aattachsDao = aattachsDao;
	}

	public IAcconectDao getAconnectDao() {
		return aconnectDao;
	}

	public void setAconnectDao(IAcconectDao aconnectDao) {
		this.aconnectDao = aconnectDao;
	}

	public IAoptDao getAoptDao() {
		return aoptDao;
	}

	public void setAoptDao(IAoptDao aoptDao) {
		this.aoptDao = aoptDao;
	}

	public IBerbeadasDao getBerbeadasDao() {
		return berbeadasDao;
	}

	public void setBerbeadasDao(IBerbeadasDao berbeadasDao) {
		this.berbeadasDao = berbeadasDao;
	}

}

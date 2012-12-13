package hu.iboard.coandco.datamagic.service.notification;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.Berbeadas;
import hu.iboard.coandco.datamagic.service.others.FtpService;
import hu.iboard.coandco.datamagic.util.Decoder;
import hu.iboard.coandco.datamagic.util.ReverseByteDecoder;
import hu.iboard.coandco.datamagic.vo.flattype.FlatTypeVO;
import hu.iboard.coandco.datamagic.vo.notification.IngHibaStatuszVO;
import hu.iboard.coandco.datamagic.vo.notification.NotificationVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

public class NotificationService extends NotificationServiceBase {

	@Override
	public List<NotificationVO> getNotifications(String userType, Integer partnerId) {
		return getNotificationDao().findNotificationByUserId(userType, partnerId);
	}

	@Override
	public NotificationVO saveOrUpdateNotification(NotificationVO notification) {
		NotificationVO notificationVO = null;
		try {
			if (notification != null) {
				notificationVO = getNotificationDao().saveOrUpdateNotification(notification);
				logger.info("Saving notification is successful");
			}
		} catch (Exception e) {
			logger.error("Error by saving notification!", e);
			return null;
		}
		return notificationVO;
	}

	@Override
	public List<NotificationVO> getNotificationsByDate(String userType, Integer partnerId, Date from, Date to) {
		return getNotificationDao().findNotificationByDate(userType, partnerId, from, to);
	}

	@Override
	public List<NotificationVO> getNotificationsByProjectId(Integer projectId) {
		return getNotificationDao().getNotificationsByProjectId(projectId);
	}

	@Override
	public List<NotificationVO> getNotificationsByOwner(Integer ownerId) {
		return getNotificationDao().getNotificationsByOwnerId(ownerId);
	}

	@Override
	public List<NotificationVO> getNotificationsByAruExts(List<AruExt> aruexts) {
		if (aruexts == null)
			return null;
		return getNotificationDao().getNotificationsByAruExtList(aruexts);
	}

	@Override
	public IngHibaStatuszVO getIngHibaStatusz(Integer statuszId) {
		if (statuszId == null)
			return null;
		return getNotificationDao().getIngHibaStatusz(statuszId);
	}

	@Override
	public String uploadFile(List<UploadedFile> files, String path) {
		FtpService ftp;
		Decoder decoder = new ReverseByteDecoder();
		String fileName = "";
		try {
			ftp = new FtpService();
			fileName = ftp.uploadCode(files, decoder, path);
			ftp.disconnect();
		} catch (Exception e) {
			logger.error("Error by dowloading realty attach file!", e);
			return null;
		}
		return fileName;
	}

	@Override
	public List<String> downloadAttachForNotification(String md5FileName, String path) {

		FtpService ftp;
		List<String> files = new ArrayList<String>();
		Decoder decoder = new ReverseByteDecoder();
		try {
			ftp = new FtpService();
			files = ftp.downloadDecodeUnzip(md5FileName, decoder, path);
			logger.info("notification attached files: " + files);
			ftp.disconnect();
		} catch (Exception e) {
			logger.error("Error by dowloading notification attach file!", e);
			return null;
		}
		return files;
	}

	@Override
	public FlatTypeVO getFlatTypeById(Integer id) {
		if (id == null)
			return null;
		return getFlatTypeDao().getFlatTypeById(id);
	}

	@Override
	public Aattachs saveOrUpdateAattach(Aattachs aattachs) {
		if (aattachs == null)
			return null;

		aattachs.setDeleted(false);
		aattachs.setFinalized(true);
		aattachs.setCreated(new Date());
		aattachs.setLocation(getAoptDao().getAoptById(0).getAFolder());
		aattachs.setRegnumber("");
		aattachs.setCategory1(-1);
		aattachs.setCategory2(-1);
		aattachs.setCategory3(-1);
		aattachs.setCategory4(-1);
		aattachs.setCategory5(-1);
		aattachs.setLastmod(new Date());
		aattachs.setDkod(0);
		aattachs.setMegj("");
		aattachs.setHatido(new Date());
		aattachs.setEhatido(new Date());
		aattachs.setJogszint(1);
		aattachs.setFontos(false);
		aattachs.setStatusz("");
		aattachs.setCeg("_2J50WFETW");
		aattachs.setEleresiut(false);
		aattachs.setModdatum(new Date());
		aattachs.setFelelos(0);
		aattachs.setFelelos2(0);
		aattachs.setFelelosok("");
		Integer id = getAattachsDao().getIdFromLastRecord();
		if (id == null)
			id = 1000000;
		else
			id = id + 1;
		aattachs.setId(id);
		return getAattachsDao().saveOrUpdateAattach(aattachs);
	}

	@Override
	public Aconnect saveOrUpdateAconnect(Aconnect aconnect) {
		if (aconnect == null)
			return null;
		aconnect.setCeg("_2J50WFETW");
		aconnect.setModdatum(new Date());
		Integer id = getAconnectDao().getIdFromLastRecord();
		if (id == null)
			id = 1000000;
		else
			id = id + 1;
		aconnect.setId(id);
		return getAconnectDao().saveOrUpdateAconnect(aconnect);
	}

	@Override
	public List<Aattachs> getAttachsForNotification(Integer id) {
		if (id == null)
			return null;
		List<Aconnect> aconnects = getAconnectDao().getAconnectForNotification(id);
		if (aconnects == null)
			return null;
		List<Aattachs> aattachs = new ArrayList<Aattachs>();
		for (Aconnect aconnect : aconnects) {
			Aattachs aattach = aconnect.getParentAattachs();
			aattachs.add(aattach);
		}
		if (aattachs == null || aattachs.size() == 0) {
			return null;
		}
		return aattachs;
	}

	@Override
	public Integer getBerbeadasUgyfelById(Integer berbeadasId) {
		if (berbeadasId == null)
			return null;
		return getBerbeadasDao().getBerbeadasUgyfelById(berbeadasId);
	}

	@Override
	public List<NotificationVO> getNotificationsByAruExtsByDate(List<AruExt> aruexts, Date from, Date to) {
		return getNotificationDao().getNotificationsByAruExtList(aruexts, from, to);
	}

	@Override
	public List<Object[]> getAllBejelentoFromNotification() {
		return getNotificationDao().getAllBejelentoFromNotification();
	}

	@Override
	public List<NotificationVO> getNotificationsByBejelento(Integer userId, char userType) {
		if(userId==null)
			return null;
		return getNotificationDao().getNotificationsByBejelento(userId, userType);
	}

	@Override
	public Berbeadas getBerbeadasById(Integer id) {
		if(id==null)
			return null;
		return getBerbeadasDao().getBerbeadasById(id);
	}
	
	@Override
	public List<Berbeadas> getBerbeadasByAktBerCheckContract(List<Integer> aktBer) {
		if(aktBer==null)
			return null;
		return getBerbeadasDao().getBerbeadasByAktBerCheckContract(aktBer);
	}


}

package hu.iboard.coandco.datamagic.service.fileattachs;

import hu.iboard.coandco.datamagic.dao.fileattachs.IFileAttachsDao;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.fileattachs.IngFileAttachsVO;

import java.util.List;

public abstract class FileAttachsServiceBase extends ServiceBase{
	
	private IFileAttachsDao fileAttachsDao;
	
	public abstract IngFileAttachsVO saveFileAttachs(IngFileAttachsVO fileAttachsVO);
	
	public abstract List<IngFileAttachsVO> getNotifications(String kod) throws Exception;
	
	public abstract List<IngFileAttachsVO> getIngFileAttachsByHibabejId(Integer id);

	public IFileAttachsDao getFileAttachsDao() {
		return fileAttachsDao;
	}

	public void setFileAttachsDao(IFileAttachsDao fileAttachsDao) {
		this.fileAttachsDao = fileAttachsDao;
	}
	
}

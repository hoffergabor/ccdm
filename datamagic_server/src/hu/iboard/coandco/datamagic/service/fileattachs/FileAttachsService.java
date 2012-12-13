package hu.iboard.coandco.datamagic.service.fileattachs;

import hu.iboard.coandco.datamagic.vo.fileattachs.IngFileAttachsVO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class FileAttachsService extends FileAttachsServiceBase {

	@Override
	public IngFileAttachsVO saveFileAttachs(IngFileAttachsVO fileAttachsVO) {
		if (fileAttachsVO == null)
			return null;
		IngFileAttachsVO fileVO = null;
		try {
			fileVO = getFileAttachsDao().saveFileAttachs(fileAttachsVO);
			logger.info("File saving is successful!");
		} catch (Exception e) {
			logger.error("Error by saving file!", e);
			return null;
		}
		return fileVO;
	}

	@Override
	public List<IngFileAttachsVO> getNotifications(String kod) throws Exception {

		List<IngFileAttachsVO> files = new ArrayList<IngFileAttachsVO>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(IngFileAttachsVO.class).add(Restrictions.eq("kod", kod))
					.addOrder(Order.desc("letrehozva"));

			files = getFileAttachsDao().findFileAttachsByCriteria(criteria);

			if (files == null || files.size() == 0) {
				return null;
			}

		} catch (Exception e) {
			logger.error("error occured at getting attached files: " + e.getMessage());
			return null;
		}
		return files;
	}

	@Override
	public List<IngFileAttachsVO> getIngFileAttachsByHibabejId(Integer id) {
		if(id==null)
			return null;
		return getFileAttachsDao().getIngFileAttachsByHibabejId(id);
	}
}

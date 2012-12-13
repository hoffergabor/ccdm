package hu.iboard.coandco.datamagic.dao.fileattachs;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import hu.iboard.coandco.datamagic.vo.fileattachs.IngFileAttachsVO;

public interface IFileAttachsDao {

	public IngFileAttachsVO saveFileAttachs(IngFileAttachsVO fileAttachsVO);

	public List<IngFileAttachsVO> findFileAttachsByCriteria(DetachedCriteria criteria);
	
	public List<IngFileAttachsVO> getIngFileAttachsByHibabejId(Integer id);

}

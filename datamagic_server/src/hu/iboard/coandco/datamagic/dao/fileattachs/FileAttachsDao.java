package hu.iboard.coandco.datamagic.dao.fileattachs;

import hu.iboard.coandco.datamagic.vo.fileattachs.IngFileAttachsVO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FileAttachsDao extends HibernateDaoSupport implements IFileAttachsDao {

	@Override
	public IngFileAttachsVO saveFileAttachs(IngFileAttachsVO fileAttachsVO) {
		if (fileAttachsVO == null)
			return null;
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(fileAttachsVO);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return fileAttachsVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IngFileAttachsVO> findFileAttachsByCriteria(DetachedCriteria criteria) {
		List<IngFileAttachsVO> fileVO = new ArrayList<IngFileAttachsVO>();
		fileVO = getHibernateTemplate().findByCriteria(criteria);
		if (fileVO.size() == 0) {
			return null;
		}
		return fileVO;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<IngFileAttachsVO> getIngFileAttachsByHibabejId(Integer id) {
		List<IngFileAttachsVO> attachs = new ArrayList<IngFileAttachsVO>();
		try {
			String query = "from IngFileAttachsVO ifa where ifa.hibabejId=:id";
			attachs = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (attachs == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting IngFileAttach by hibabej id: " + e.getMessage());
			return null;
		}
		return attachs;
	}

}

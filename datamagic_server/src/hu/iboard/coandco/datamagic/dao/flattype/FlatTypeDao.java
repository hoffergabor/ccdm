package hu.iboard.coandco.datamagic.dao.flattype;

import hu.iboard.coandco.datamagic.vo.flattype.FlatTypeVO;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class FlatTypeDao extends HibernateDaoSupport implements IFlatTypeDao {

	@Override
	public FlatTypeVO getFlatTypeById(Integer id) {
		return (FlatTypeVO) getHibernateTemplate().get(FlatTypeVO.class, id);
	}

}

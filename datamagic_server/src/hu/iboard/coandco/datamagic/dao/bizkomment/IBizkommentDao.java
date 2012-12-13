package hu.iboard.coandco.datamagic.dao.bizkomment;

import hu.iboard.coandco.datamagic.generated.Bizkomment;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IBizkommentDao {
	
	public List<Bizkomment> findBizkommentByCriteria(DetachedCriteria criteria);
	
	public void saveBizkomment(Bizkomment bizkomment);

}

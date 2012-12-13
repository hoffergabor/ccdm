package hu.iboard.coandco.datamagic.dao.shipping;

import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IShippingDao {

	/**
	 * @param user
	 * @return
	 */

	public List<ShippingVO> findShippingByCriteria(DetachedCriteria criteria);

	public List<ShippingVO> findShippingByCriteria(DetachedCriteria criteria,
			int firstResult, int maxResult);

	public List<ShippingItemVO> findShippingItemByCriteria(DetachedCriteria criteria);

	public List<Object[]> findShippingForChartByQuery(String query);
	
	public List<ShippingItemVO> getShippingItemByShippingId(String sorszam);
}

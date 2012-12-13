package hu.iboard.coandco.datamagic.dao.shipping;

import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShippingDao extends HibernateDaoSupport implements IShippingDao {

	public ShippingDao() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingVO> findShippingByCriteria(DetachedCriteria criteria) {
		List<ShippingVO> shippingVO = null;
		shippingVO = getHibernateTemplate().findByCriteria(criteria, 0, 1000);
		if (shippingVO.size() == 0) {
			return null;
		}
		return shippingVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingVO> findShippingByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<ShippingVO> shippingVO = null;
		shippingVO = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (shippingVO.size() == 0) {
			return null;
		}
		return shippingVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingItemVO> findShippingItemByCriteria(DetachedCriteria criteria) {
		List<ShippingItemVO> shippingItemVO = null;
		shippingItemVO = getHibernateTemplate().findByCriteria(criteria);
		if (shippingItemVO.size() == 0) {
			return null;
		}
		return shippingItemVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findShippingForChartByQuery(String query) {
		List<Object[]> chartVO = null;
		chartVO = getHibernateTemplate().find(query);
		if (chartVO.size() == 0) {
			return null;
		}
		return chartVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingItemVO> getShippingItemByShippingId(String sorszam) {
		List<ShippingItemVO> shippingItems = new ArrayList<ShippingItemVO>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(ShippingItemVO.class).add(Restrictions.eq("sorszam", sorszam)).addOrder(Order.desc("amegn"));
			shippingItems = getHibernateTemplate().findByCriteria(criteria);
		} catch (Exception e) {
			logger.error("Error occured at processing ListShipping items:", e);
			return null;
		}
		return shippingItems;
	}

}

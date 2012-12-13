package hu.iboard.coandco.datamagic.dao.order;

import hu.iboard.coandco.datamagic.generated.Rendmegj;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.util.DataMagicConstants;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrderDao extends HibernateDaoSupport implements IOrderDao, DataMagicConstants {

	public OrderDao() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> findOrderByCriteria(DetachedCriteria criteria) {
		List<OrderVO> orderVO = null;
		orderVO = getHibernateTemplate().findByCriteria(criteria);
		if (orderVO.size() == 0) {
			return null;
		}
		return orderVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> findOrderByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<OrderVO> orderVO = null;
		orderVO = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (orderVO == null || orderVO.size() == 0) {
			return null;
		}
		return orderVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItemVO> findOrderItemByCriteria(DetachedCriteria criteria) {
		List<OrderItemVO> orderItemVO = null;
		orderItemVO = getHibernateTemplate().findByCriteria(criteria);
		if (orderItemVO.size() == 0) {
			return null;
		}
		return orderItemVO;
	}

	@Override
	public void saveOrder(Rendel order) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().save(order);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void saveOrderItem(Rendtet orderItem) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().save(orderItem);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void deleteOrderItems(List<Rendtet> orderItems) {
		getHibernateTemplate().deleteAll(orderItems);
	}

	@Override
	public void updateOrder(Rendel order) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().update(order);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public Rendtet loadOrderItem(String unikazon) {

		return (Rendtet) getHibernateTemplate().get(Rendtet.class, unikazon);

	}

	@Override
	public Rendel loadOrder(String sorszam) {

		return (Rendel) getHibernateTemplate().get(Rendel.class, sorszam);

	}
	
	@Override
	public void saveRendMegj(Rendmegj rendmegj){
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(rendmegj);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();		
	}	

	@SuppressWarnings("unchecked")
	public List<Rendmegj> loadRendMegj(String rendsorszam){
		List<Rendmegj> result = null;
		try {
			String query = "from Rendmegj m where m.sorszam = :sorszam order by tsorsz asc";
			result = getHibernateTemplate().findByNamedParam(query, "sorszam", rendsorszam);
			if (result == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at processing ListOrders: " + e.getMessage());
			return null;
		}
		return result;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findOrderForChartByQuery(String query) {
		List<Object[]> chartVO = null;
		chartVO = getHibernateTemplate().find(query);
		if (chartVO.size() == 0) {
			return null;
		}
		return chartVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> getOrdersByDate(Integer partnerId, Date from, Date to, Boolean all, String kivagybe) {
		List<OrderVO> orders = new ArrayList<OrderVO>();
		try {
			String query = "from OrderVO o where o.kelt>='" + from + "' and o.kelt<='" + to + "'";
			if (partnerId != null) {
				query = query + " and o.vevokod=" + partnerId;
			}
			if (!all) {
				query = query + " and o.statusz like 'F_gg%'";
			}
			if (kivagybe != null)
				query = query + " and o.kivagybe='" + kivagybe + "'";
			query = query + " order by o.kelt desc";
			getHibernateTemplate().setMaxResults(1000);
			orders = getHibernateTemplate().find(query);
			if (orders == null || orders.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("error occured at processing ListOrders: " + e.getMessage());
			return null;
		}
		return orders;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rendtet> getRendtetForPRProjekt(Integer mkateg1) {
		List<Rendtet> orderItems = null;
		try {
			String query = "from Rendtet r where r.mkateg1=:mkateg1";
			orderItems = getHibernateTemplate().findByNamedParam(query, "mkateg1", mkateg1);
			if (orderItems == null)
				return null;
		} catch (Exception e) {
			logger.error("error occured at processing ListOrders: " + e.getMessage());
			return null;
		}
		return orderItems;
	}

	@Override
	public Rendel getRendelById(String sorszam) {
		return (Rendel) getHibernateTemplate().get(Rendel.class, sorszam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRendtetSzamlsorszBySorszam(String sorszam) {
		List<String> sorszamok = null;
		try {
			String query = "select distinct o.szamlsorsz from OrderItemVO o where o.sorszam=:sorszam";
			sorszamok = getHibernateTemplate().findByNamedParam(query, "sorszam", sorszam);
			if (sorszamok == null)
				return null;
		} catch (Exception e) {
			logger.error("error by getting rendtet szamlsorsz " + e.getMessage());
			return null;
		}
		return sorszamok;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getRendtetSzlevsorszBySorszam(String sorszam) {
		List<String> sorszamok = null;
		try {
			String query = "select distinct o.szlevsorsz from OrderItemVO o where o.sorszam=:sorszam";
			sorszamok = getHibernateTemplate().findByNamedParam(query, "sorszam", sorszam);
			if (sorszamok == null)
				return null;
		} catch (Exception e) {
			logger.error("error by getting rendtet szlevsorsz " + e.getMessage());
			return null;
		}
		return sorszamok;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderVO> getAllFuggoOrders(Integer partnerId, String kivagybe) {
		List<OrderVO> orders = new ArrayList<OrderVO>();
		try {
			String query = "from OrderVO o where ";
			query = query + " o.statusz like 'F_gg%'";
			if (partnerId != null) {
				query = query + " and o.vevokod=" + partnerId;
			}
			query = query + " and o.kivagybe='" + kivagybe + "'";
			query = query + " order by o.kelt desc";
			getHibernateTemplate().setMaxResults(1000);
			orders = getHibernateTemplate().find(query);
			if (orders == null || orders.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("error occured at processing fuggo ListOrders: " + e.getMessage());
			return null;
		}
		return orders;
	}
}

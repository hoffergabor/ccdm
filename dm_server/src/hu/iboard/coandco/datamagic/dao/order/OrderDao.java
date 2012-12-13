package hu.iboard.coandco.datamagic.dao.order;

import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class OrderDao extends HibernateDaoSupport implements IOrderDao {

	@Override
	public Rendel saveOrUpdateOrder(Rendel order) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.COMMIT);
		getHibernateTemplate().saveOrUpdate(order);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return order;
	}

	@Override
	public Rendtet getOrderItemById(String unikazon) {
		return (Rendtet) getHibernateTemplate().get(Rendtet.class, unikazon);
	}

	@Override
	public Rendel getOrderById(String sorszam) {
		return (Rendel) getHibernateTemplate().get(Rendel.class, sorszam);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLastSorszamFromRendel() {
		String serial = "";
		DateFormat df = new SimpleDateFormat("yyyy");
		String year = df.format(new Date()).toString();
		serial += "WSK" + year + "/";
		try {
			String query = "select r.sorszam from rendel r where r.sorszam like '" + serial + "%' order by r.insdatum desc";
			List<String> orderList = getHibernateTemplate().find(query);
			if (orderList.size() > 0) {
				String ssz = orderList.get(0);
				if (ssz.substring(3, 7).equals(year)) {
					int val = Integer.parseInt(ssz.substring(8).trim());
					serial += val + 1;
				} else {
					serial += "1";
				}
			} else {
				serial += "1";
			}
		} catch (Exception e) {
			logger.error("Error by generating serial for order rendering!", e);
			return null;
		}
		return serial;
	}

	@Override
	public Rendtet saveOrUpdateOrderItem(Rendtet rendtet) {
		getHibernateTemplate().persist(rendtet);
		return rendtet;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rendel> getOrdersByLakosvevo(Integer lakoskod) {
		List<Rendel> orderList = null;
		try {
			String query = "from Rendel r where r.lakosvevo=:lakoskod";
			orderList = getHibernateTemplate().findByNamedParam(query, "lakoskod", lakoskod);
			if (orderList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING ORDERS FOR LAKOSVEVO!", e);
			return null;
		}
		return orderList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rendtet> getOrderItemsByOrderId(String sorszam) {
		List<Rendtet> orderItemList = null;
		try {
			String query = "from Rendtet r where r.sorszam=:sorszam";
			orderItemList = getHibernateTemplate().findByNamedParam(query, "sorszam", sorszam);
			if (orderItemList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING ORDER ITEMS BY ORDER ID!", e);
			return null;
		}
		return orderItemList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rendel> getOrdersByPartnerId(Integer id) {
		List<Rendel> orderList = null;
		try {
			String query = "from Rendel r where r.vevokod=:id";
			orderList = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (orderList == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING ORDERS FOR PARTNER!", e);
			return null;
		}
		return orderList;
	}
}

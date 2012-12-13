package hu.iboard.coandco.datamagic.dao.shippingaddress;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ShippingAddressDao extends HibernateDaoSupport implements IShippingAddressDao {

	@Override
	public ShippingAddress saveOrUpdateShipping(ShippingAddress shipping) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(shipping);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return shipping;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingAddress> getShippingsByCustomer(Customer customer) {
		List<ShippingAddress> shippings = new ArrayList<ShippingAddress>();
		try {
			String query = "from ShippingAddress s where s.customer.lakosKod=:lakosKod";
			shippings = getHibernateTemplate().findByNamedParam(query, "lakosKod", customer.getLakosKod());
			if (shippings == null)
				return null;

		} catch (Exception e) {
			logger.error("ERROR AT GETTING SHIPPING BY CUSTOMER!!!", e);
			return null;
		}
		return shippings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ShippingAddress> getShippingsByPartner(Partner partner) {
		List<ShippingAddress> shippings = new ArrayList<ShippingAddress>();
		try {
//			String query = "from ShippingAddress s where s.partner.vevokod=:partner.vevokod";
//			shippings = getHibernateTemplate().find(query);
			String query = "from ShippingAddress s where s.partner.vevokod=:partner";
			shippings = getHibernateTemplate().findByNamedParam(query, "partner", partner.getVevokod());
			//shippings = getHibernateTemplate().find(query);
			
			
			if (shippings == null)
				return null;

		} catch (Exception e) {
			logger.error("ERROR AT GETTING SHIPPING BY PARTNER!!!", e);
			return null;
		}
		return shippings;
	}

	@Override
	public ShippingAddress getShippingById(Integer id) {
		return (ShippingAddress) getHibernateTemplate().get(ShippingAddress.class, id);
	}

}

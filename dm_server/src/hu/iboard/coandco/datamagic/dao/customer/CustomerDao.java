package hu.iboard.coandco.datamagic.dao.customer;

import hu.iboard.coandco.datamagic.model.customer.Customer;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CustomerDao extends HibernateDaoSupport implements ICustomerDao {

	@Override
	public Customer getCustomerById(Integer id) {
		return (Customer) getHibernateTemplate().get(Customer.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer getCustomerByLogin(String userName, String password) {
		List<Customer> users = new ArrayList<Customer>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class).add(
					Restrictions.eq("webLogin", userName)).add(Restrictions.eq("webPassw", password));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY CUSTOMER LOGIN!!!", e);
			return null;
		}
		return users.get(0);
	}

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(customer);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Customer getCustomerByEmail(String email) {
		List<Customer> users = new ArrayList<Customer>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class).add(
					Restrictions.eq("webLogin", email));
			users = getHibernateTemplate().findByCriteria(criteria);
			if (users == null || users.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING CUSTOMER BY EMAIL!!!", e);
			return null;
		}
		return users.get(0);
	}

	@Override
	public Integer getLastCustomerId() {
		Integer id = null;
		try {
			String query = "select max(c.lakosKod) from Customer c";
			id = (Integer) getHibernateTemplate().find(query).get(0);
			if (id == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR GETTING LAST RECORD FROM LAKOSKOD!!!", e);
			return null;
		}
		return id;
	}

}

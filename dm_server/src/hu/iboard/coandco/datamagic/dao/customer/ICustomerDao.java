package hu.iboard.coandco.datamagic.dao.customer;

import hu.iboard.coandco.datamagic.model.customer.Customer;

public interface ICustomerDao {

	public Customer getCustomerById(Integer id);

	public Customer getCustomerByLogin(String userName, String password);

	public Customer saveOrUpdateCustomer(Customer customer);

	public Customer getCustomerByEmail(String email);

	public Integer getLastCustomerId();

}

package hu.iboard.coandco.datamagic.dao.shippingaddress;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;

import java.util.List;

public interface IShippingAddressDao {

	public ShippingAddress saveOrUpdateShipping(ShippingAddress shipping);

	public List<ShippingAddress> getShippingsByCustomer(Customer customer);
	
	public List<ShippingAddress> getShippingsByPartner(Partner partner);

	public ShippingAddress getShippingById(Integer id);

}

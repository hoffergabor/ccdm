package hu.iboard.coandco.datamagic.service.shippingadressservice;

import hu.iboard.coandco.datamagic.model.county.County;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;
import hu.iboard.coandco.datamagic.model.terulet.Terulet;
import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;
import hu.iboard.coandco.datamagic.model.township.TownShip;

import java.util.List;

public class ShippingAddressService extends ShippingAddressServiceBase {

	@Override
	public List<County> getAllCounty() {
		return getCountyDao().getAllCounty();
	}

	@Override
	public List<TownShip> getAllTownship(String input) {
		return getTownshipDao().getAllTownship(input);
	}

	@Override
	public TownShip getTownShipById(Integer id) {
		if (id == null)
			return null;
		return getTownshipDao().getTownShipById(id);
	}

	@Override
	public ShippingAddress saveOrUpdateShipping(ShippingAddress shipping) {
		if (shipping == null)
			return null;
		return getShippingAddressDao().saveOrUpdateShipping(shipping);
	}

	@Override
	public County getCountyById(Integer id) {
		if (id == null)
			return null;
		return getCountyDao().getCountyById(id);
	}

	@Override
	public List<TownShip> getTownshipByZipCode(String zipCode) {
		if (zipCode == null)
			return null;
		return getTownshipDao().getTownshipsByZipCode(zipCode);
	}

	@Override
	public List<TownShip> getZipCodes(String input) {
		if (input == null)
			return null;
		return getTownshipDao().getZipCodes(input);
	}

	@Override
	public TownShip getTownShipByZipAndName(String zipcode, String name) {
		return getTownshipDao().getTownShipByZipAndName(zipcode, name);
	}

	@Override
	public List<ShippingAddress> getShippingsByCustomer(Customer customer) {
		if (customer == null)
			return null;
		return getShippingAddressDao().getShippingsByCustomer(customer);
	}

	@Override
	public ShippingAddress getShippingById(Integer id) {
		if (id == null)
			return null;
		return getShippingAddressDao().getShippingById(id);
	}

	@Override
	public List<ShippingAddress> getShippingsByPartner(Partner partner) {
		if (partner == null)
			return null;
		return getShippingAddressDao().getShippingsByPartner(partner);
	}

	@Override
	public Teruletcs getTeruletcsById(Integer id) {
		if(id==null)
			return null;
		return getTeruletcsDao().getTeruletcsById(id);
	}

	@Override
	public List<Teruletcs> getTeruletcsByIrsz(Integer irsz) {
		if(irsz==null)
			return null;
		return getTeruletcsDao().getTeruletcsByIrsz(irsz);
	}

	@Override
	public Terulet getTeruletById(Integer id) {
		if(id==null)
			return null;
		return getTeruletDao().getTeruletById(id);
	}

}

package hu.iboard.coandco.datamagic.service.shippingadressservice;

import hu.iboard.coandco.datamagic.dao.county.ICountyDao;
import hu.iboard.coandco.datamagic.dao.shippingaddress.IShippingAddressDao;
import hu.iboard.coandco.datamagic.dao.terulet.ITeruletDao;
import hu.iboard.coandco.datamagic.dao.teruletcs.ITeruletcsDao;
import hu.iboard.coandco.datamagic.dao.township.ITownshipDao;
import hu.iboard.coandco.datamagic.model.county.County;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.shippingaddress.ShippingAddress;
import hu.iboard.coandco.datamagic.model.terulet.Terulet;
import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;
import hu.iboard.coandco.datamagic.model.township.TownShip;

import java.util.List;

public abstract class ShippingAddressServiceBase {

	private ICountyDao countyDao;
	private ITownshipDao townshipDao;
	private IShippingAddressDao shippingAddressDao;
	private ITeruletcsDao teruletcsDao;
	private ITeruletDao teruletDao;

	public abstract List<TownShip> getAllTownship(String input);

	public abstract List<TownShip> getZipCodes(String input);

	public abstract List<TownShip> getTownshipByZipCode(String zipCode);

	public abstract TownShip getTownShipById(Integer id);

	public abstract County getCountyById(Integer id);

	public abstract List<County> getAllCounty();

	public abstract ShippingAddress saveOrUpdateShipping(ShippingAddress shipping);

	public abstract TownShip getTownShipByZipAndName(String zipcode, String name);

	public abstract List<ShippingAddress> getShippingsByCustomer(Customer customer);

	public abstract List<ShippingAddress> getShippingsByPartner(Partner partner);

	public abstract ShippingAddress getShippingById(Integer id);

	public abstract Teruletcs getTeruletcsById(Integer id);

	public abstract List<Teruletcs> getTeruletcsByIrsz(Integer irsz);
	
	public abstract Terulet getTeruletById(Integer id);

	public ICountyDao getCountyDao() {
		return countyDao;
	}

	public void setCountyDao(ICountyDao countyDao) {
		this.countyDao = countyDao;
	}

	public ITownshipDao getTownshipDao() {
		return townshipDao;
	}

	public void setTownshipDao (ITownshipDao townshipDao) {
		this.townshipDao = townshipDao;
	}

	public IShippingAddressDao getShippingAddressDao() {
		return shippingAddressDao;
	}

	public void setShippingAddressDao (IShippingAddressDao shippingAddressDao) {
		this.shippingAddressDao = shippingAddressDao;
	}

	public ITeruletcsDao getTeruletcsDao() {
		return this.teruletcsDao;
	}

	public void setTeruletcsDao (ITeruletcsDao teruletcsDao) {
		this.teruletcsDao = teruletcsDao;
	}

	public ITeruletDao getTeruletDao() {
		return teruletDao;
	}

	public void setTeruletDao(ITeruletDao teruletDao) {
		this.teruletDao = teruletDao;
	}

}


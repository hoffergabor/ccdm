package hu.iboard.coandco.datamagic.dao.township;

import hu.iboard.coandco.datamagic.model.township.TownShip;

import java.util.List;

public interface ITownshipDao {

	public List<TownShip> getAllTownship(String input);

	public TownShip getTownShipById(Integer id);

	public List<TownShip> getTownshipsByZipCode(String zipCode);

	public List<TownShip> getZipCodes(String input);

	public TownShip getTownShipByZipAndName(String zipcode, String name);

}

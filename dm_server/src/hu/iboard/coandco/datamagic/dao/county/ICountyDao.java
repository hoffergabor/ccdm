package hu.iboard.coandco.datamagic.dao.county;

import hu.iboard.coandco.datamagic.model.county.County;

import java.util.List;

public interface ICountyDao {

	public List<County> getAllCounty();

	public County getCountyById(Integer id);

}

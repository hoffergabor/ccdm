package hu.iboard.coandco.datamagic.dao.fuvpartner;

import hu.iboard.coandco.datamagic.generated.Fuvpartner;

import java.util.List;

public interface IFuvpartnerDao {

	public Fuvpartner getFuvpartnerById(Integer id);

	public List<Fuvpartner> getFuvpartnersByPartnerId(Integer id);

}

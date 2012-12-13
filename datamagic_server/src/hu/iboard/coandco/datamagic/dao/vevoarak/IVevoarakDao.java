package hu.iboard.coandco.datamagic.dao.vevoarak;

import hu.iboard.coandco.datamagic.generated.Vevoarak;

import java.util.Date;
import java.util.List;

public interface IVevoarakDao {

	public Vevoarak getVevoarakById(Integer id);

	public List<Vevoarak> getVevoarakByKedvezmenyForPartner(Integer id, Integer vevokod);
	
	public List<Vevoarak> getActualVevoarakForPartner(Integer vevokod);

}

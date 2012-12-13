package hu.iboard.coandco.datamagic.dao.tapadogon;

import hu.iboard.coandco.datamagic.generated.Tapadogon;

import java.util.List;

public interface ITapadogonDao {

	public Tapadogon getTapadogonById(Integer id);
	
	public List<Tapadogon> getTapadogonByAkod(Integer arukod);

}

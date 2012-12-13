package hu.iboard.coandco.datamagic.dao.arvalt;

import hu.iboard.coandco.datamagic.generated.Arvalt;

import java.util.List;

public interface IArvaltDao {

	public Arvalt getArvaltById(Integer id);

	public List<Arvalt> getArvaltByArutasitasForPartner(Integer arutasitras, Integer arnev);

}

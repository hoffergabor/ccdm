package hu.iboard.coandco.datamagic.dao.teruletcs;

import hu.iboard.coandco.datamagic.model.teruletcs.Teruletcs;

import java.util.List;

public interface ITeruletcsDao {
	
	public Teruletcs getTeruletcsById(Integer id);
	
	public List<Teruletcs> getTeruletcsByIrsz(Integer irsz);

}

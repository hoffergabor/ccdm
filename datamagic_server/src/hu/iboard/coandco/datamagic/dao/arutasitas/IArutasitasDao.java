package hu.iboard.coandco.datamagic.dao.arutasitas;

import hu.iboard.coandco.datamagic.generated.Arutasitas;

import java.util.List;

public interface IArutasitasDao {
	
	public Arutasitas getArutasitasById(Integer id);

	public List<Arutasitas> getAktualisAkcio();

}

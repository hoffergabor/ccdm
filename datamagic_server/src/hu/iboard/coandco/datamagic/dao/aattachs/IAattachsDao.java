package hu.iboard.coandco.datamagic.dao.aattachs;

import hu.iboard.coandco.datamagic.generated.Aattachs;

public interface IAattachsDao {

	public Aattachs getAattachsById(Integer id);

	public Aattachs saveOrUpdateAattach(Aattachs aattachs);
	
	public Integer getIdFromLastRecord();

}

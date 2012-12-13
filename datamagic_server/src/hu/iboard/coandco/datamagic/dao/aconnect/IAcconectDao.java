package hu.iboard.coandco.datamagic.dao.aconnect;

import hu.iboard.coandco.datamagic.generated.Aconnect;

import java.util.List;

public interface IAcconectDao {
	
	public Aconnect saveOrUpdateAconnect(Aconnect aconnect);
	
	public Integer getIdFromLastRecord();
	
	public List<Aconnect> getAconnectForNotification(Integer id);

}

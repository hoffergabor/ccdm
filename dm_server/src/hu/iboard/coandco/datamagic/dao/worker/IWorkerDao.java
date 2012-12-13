package hu.iboard.coandco.datamagic.dao.worker;

import hu.iboard.coandco.datamagic.model.dolgozo.Dolgozo;

public interface IWorkerDao {
	
	public Dolgozo getWorkerById(Integer workerId);

	public Dolgozo saveOrUpdateWorker(Dolgozo worker);

	public Dolgozo getWorkerByLogin(String userName, String password);

}

package hu.iboard.coandco.datamagic.dao.worker;

import hu.iboard.coandco.datamagic.generated.Dolgozo;

public interface IWorkerDao {

	public Dolgozo getWorkerByReferenceId(Integer referenceId);

	public Dolgozo saveOrUpdateWorker(Dolgozo worker);

	public Dolgozo getWorkerByLogin(String userName, String password);

}

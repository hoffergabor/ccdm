package hu.iboard.coandco.datamagic.service.worker;

import hu.iboard.coandco.datamagic.dao.worker.IWorkerDao;
import hu.iboard.coandco.datamagic.generated.Dolgozo;

public abstract class WorkerServiceBase {

	private IWorkerDao workerDao;

	public abstract Dolgozo getWorkerByReferenceId(Integer referenceId);

	public abstract Dolgozo saveOrUpdateWorker(Dolgozo worker);

	public IWorkerDao getWorkerDao() {
		return workerDao;
	}

	public void setWorkerDao(IWorkerDao workerDao) {
		this.workerDao = workerDao;
	}

}

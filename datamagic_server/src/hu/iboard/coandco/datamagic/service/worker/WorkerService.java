package hu.iboard.coandco.datamagic.service.worker;

import hu.iboard.coandco.datamagic.generated.Dolgozo;

public class WorkerService extends WorkerServiceBase{

	@Override
	public Dolgozo getWorkerByReferenceId(Integer referenceId) {
		if(referenceId==null)
			return null;
		return getWorkerDao().getWorkerByReferenceId(referenceId);
	}

	@Override
	public Dolgozo saveOrUpdateWorker(Dolgozo worker) {
		if(worker==null)
			return null;
		return getWorkerDao().saveOrUpdateWorker(worker);
	}
	
	
	

}

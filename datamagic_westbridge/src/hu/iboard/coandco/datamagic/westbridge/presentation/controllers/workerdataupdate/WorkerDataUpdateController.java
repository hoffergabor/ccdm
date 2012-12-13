package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.workerdataupdate;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.service.user.UserServiceBase;
import hu.iboard.coandco.datamagic.service.worker.WorkerServiceBase;
import hu.iboard.coandco.datamagic.westbridge.presentation.util.AbstractController;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "workerdataupdateController")
@RequestScoped
public class WorkerDataUpdateController extends AbstractController {

	public static final String WORKERDATAUPDATE_ACTION = "workerdataupdate";

	@ManagedProperty(value = "#{workerdataupdateData}")
	private WorkerDataUpdateData data;

	@ManagedProperty(value = "#{workerService}")
	private WorkerServiceBase workerService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;

	@Override
	public void loadData() {
		if (getManagedWorker() != null)
			getData().setWorker(getManagedWorker());
	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(WORKERDATAUPDATE_CONTROLLER);

	}

	public void updateWorkerData(ActionEvent event) {

		if (getData().getWorker() == null)
			return;
		try {
			Dolgozo worker = getWorkerService().saveOrUpdateWorker(getData().getWorker());
			if (worker == null) {
				addErrorMessage("", "Hiba történt az adatok mentésekor!");
				logger.error("Error by saving worker datas!");
				return;
			} else
				addInfoMessage("", "Dolgozó adatok mentése sikeres!");
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt az adatok mentésekor!");
			logger.error("Error by saving worker datas!");
		}
	}

	public WorkerDataUpdateData getData() {
		return data;
	}

	public void setData(WorkerDataUpdateData data) {
		this.data = data;
	}

	public WorkerServiceBase getWorkerService() {
		return workerService;
	}

	public void setWorkerService(WorkerServiceBase workerService) {
		this.workerService = workerService;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

}

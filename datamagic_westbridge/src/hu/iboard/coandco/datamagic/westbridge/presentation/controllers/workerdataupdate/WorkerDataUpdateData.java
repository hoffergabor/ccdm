package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.workerdataupdate;

import hu.iboard.coandco.datamagic.generated.Dolgozo;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "workerdataupdateData")
@SessionScoped
public class WorkerDataUpdateData implements Serializable {

	private static final long serialVersionUID = 7535619122830529119L;
	
	private Dolgozo worker;

	public Dolgozo getWorker() {
		return worker;
	}

	public void setWorker(Dolgozo worker) {
		this.worker = worker;
	}
	
	

	

}

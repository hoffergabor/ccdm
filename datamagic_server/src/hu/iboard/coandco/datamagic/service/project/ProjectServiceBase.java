package hu.iboard.coandco.datamagic.service.project;

import java.util.List;

import hu.iboard.coandco.datamagic.dao.project.IProjectDao;
import hu.iboard.coandco.datamagic.generated.Project;

public abstract class ProjectServiceBase {

	private IProjectDao projectDao;

	public abstract List<Project> getProjectByPartnerId(Integer partnerId);

	public abstract List<Object[]> getAllProject();

	public abstract List<Object[]> getProjectByAddress(String address, Integer vevokod);

	public abstract Project getProjectById(Integer projectId);
	
	public abstract List<Object[]> getAllOwnerName();
	
	public abstract List<Object[]> getOwnersByName(String name);

	public IProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

}

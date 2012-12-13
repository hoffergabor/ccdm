package hu.iboard.coandco.datamagic.service.project;

import hu.iboard.coandco.datamagic.generated.Project;

import java.util.List;

public class ProjectService extends ProjectServiceBase {

	@Override
	public List<Project> getProjectByPartnerId(Integer partnerId) {
		return getProjectDao().getProjectsByPartnerId(partnerId);
	}

	@Override
	public Project getProjectById(Integer projectId) {
		return getProjectDao().getProjectById(projectId);
	}

	@Override
	public List<Object[]> getAllProject() {
		return getProjectDao().getAllProject();
	}

	@Override
	public List<Object[]> getProjectByAddress(String address, Integer vevokod) {
		if (address == null)
			return null;
		return getProjectDao().getProjectByAddress(address, vevokod);
	}

	@Override
	public List<Object[]> getAllOwnerName() {
		return getProjectDao().getAllOwnerName();
	}

	@Override
	public List<Object[]> getOwnersByName(String name) {
		return getProjectDao().getOwnerByName(name);
	}

}

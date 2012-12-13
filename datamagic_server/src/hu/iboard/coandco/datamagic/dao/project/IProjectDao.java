package hu.iboard.coandco.datamagic.dao.project;

import hu.iboard.coandco.datamagic.generated.Project;

import java.util.List;

public interface IProjectDao {

	public List<Project> getProjectsByPartnerId(Integer partnerId);

	public Project getProjectById(Integer projectId);

	public Object[] getProjectAddressById(Integer projectId);

	public List<Object[]> getAllProject();
	
	public List<Object[]> getAllOwnerName();
	
	public List<Object[]> getOwnerByName(String name);

	public List<Object[]> getProjectByAddress(String address, Integer vevokod);
}

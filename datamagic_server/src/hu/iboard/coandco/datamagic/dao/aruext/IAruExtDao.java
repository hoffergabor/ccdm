package hu.iboard.coandco.datamagic.dao.aruext;

import hu.iboard.coandco.datamagic.generated.AruExt;

import java.util.List;

public interface IAruExtDao {

	public List<Object[]> getAruExtByProjectId(Integer projectId);

	public AruExt getAruExtById(Integer id);

	public Object[] getAruExtByPartnerId(Integer partnerId);
	
	public Object[] getAruExtAddressById(Integer id);
	
	public List<Object[]> getAruExtForRenter(Integer partnerId);
	
	public List<Integer> getBerbeadasForRenter();
	
	public List<AruExt> getAruExtsForRenter(Integer partnerId);
	
	public List<Integer> getBerbeadasByAktBer(List<Integer> aktBer);
	
	public List<Integer> getBerbeadasIdsForRealtyRenter();
	
}

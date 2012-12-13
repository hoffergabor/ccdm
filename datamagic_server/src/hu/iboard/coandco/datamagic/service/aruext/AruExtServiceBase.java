package hu.iboard.coandco.datamagic.service.aruext;

import java.util.List;

import hu.iboard.coandco.datamagic.dao.aruext.IAruExtDao;
import hu.iboard.coandco.datamagic.generated.AruExt;

public abstract class AruExtServiceBase {
	
	private IAruExtDao aruExtDao;
	
	public abstract List<Object[]> getAruExtByProjectId(Integer projectId);
	public abstract AruExt getAruExtById(Integer id);
	public abstract Object[] getAruExtByPartnerId(Integer partnerId);
	public abstract List<Object[]> getAruExtForRenter(Integer partnerId);
	public abstract List<Integer> getBerbeadasForRenter();
	public abstract List<AruExt> getAruExtsForRenter(Integer partnerId);
	public abstract List<Integer> getBerbeadasByAktBer(List<Integer> aktBer);
	public abstract List<Integer> getBerbeadasIdsForRealtyRenter();
	
	public IAruExtDao getAruExtDao() {
		return aruExtDao;
	}

	public void setAruExtDao(IAruExtDao aruExtDao) {
		this.aruExtDao = aruExtDao;
	}

}

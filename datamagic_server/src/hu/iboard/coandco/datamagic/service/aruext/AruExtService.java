package hu.iboard.coandco.datamagic.service.aruext;

import hu.iboard.coandco.datamagic.generated.AruExt;

import java.util.List;

public class AruExtService extends AruExtServiceBase{

	@Override
	public List<Object[]> getAruExtByProjectId(Integer projectId) {
		if(projectId==null)
			return null;
		return getAruExtDao().getAruExtByProjectId(projectId);
	}

	@Override
	public AruExt getAruExtById(Integer id) {
		if(id==null)
			return null;
		return getAruExtDao().getAruExtById(id);
	}

	@Override
	public Object[] getAruExtByPartnerId(Integer partnerId) {
		if(partnerId==null)
			return null;
		return getAruExtDao().getAruExtByPartnerId(partnerId);
	}

	@Override
	public List<Object[]> getAruExtForRenter(Integer partnerId) {
		if(partnerId==null)
			return null;
		return getAruExtDao().getAruExtForRenter(partnerId);
	}

	@Override
	public List<Integer> getBerbeadasForRenter() {
		return getAruExtDao().getBerbeadasForRenter();
	}

	@Override
	public List<AruExt> getAruExtsForRenter(Integer partnerId) {
		if(partnerId==null)
			return null;
		return getAruExtDao().getAruExtsForRenter(partnerId);
	}

	@Override
	public List<Integer> getBerbeadasByAktBer(List<Integer> aktBer) {
		return getAruExtDao().getBerbeadasByAktBer(aktBer);
	}

	@Override
	public List<Integer> getBerbeadasIdsForRealtyRenter() {
		return getAruExtDao().getBerbeadasIdsForRealtyRenter();
	}

}

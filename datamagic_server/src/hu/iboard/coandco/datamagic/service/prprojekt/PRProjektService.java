package hu.iboard.coandco.datamagic.service.prprojekt;

import hu.iboard.coandco.datamagic.vo.prarucsatolo.PRArucsatolo;
import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.util.List;

public class PRProjektService extends PRProjektServiceBase {

	@Override
	public PRProjekt getPRProjectById(Integer id) {
		if (id == null)
			return null;
		return getPrProjektDao().getPRProjektById(id);
	}

	@Override
	public List<PRProjekt> getPRProjektByPartnerId(Integer vevokod) {
		return getPrProjektDao().getPRProjektByPartnerId(vevokod);
	}

	@Override
	public List<PRProjekt> getAlPRProjektByFoPRProjektId(Integer id) {
		if (id == null)
			return null;
		return getPrProjektDao().getAlPRProjektByFoPRProjektId(id);
	}

	@Override
	public List<PRArucsatolo> getPRArucsatoloByPRProjektId(Integer id) {
		if (id == null)
			return null;
		return getPrArucsatoloDao().getPRArucsatoloByPRProjektId(id);
	}

	@Override
	public List<PRProjekt> getPRProjektByDimenzio(Integer dimenzio) {
		if (dimenzio == null)
			return null;
		return getPrProjektDao().getPRProjektByDimenzio(dimenzio);
	}

}

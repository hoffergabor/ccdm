package hu.iboard.coandco.datamagic.service.prprojekt;

import hu.iboard.coandco.datamagic.dao.prarucsatolo.IPRArucsatoloDao;
import hu.iboard.coandco.datamagic.dao.prprojekt.IPRProjektDao;
import hu.iboard.coandco.datamagic.vo.prarucsatolo.PRArucsatolo;
import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.util.List;

public abstract class PRProjektServiceBase {

	private IPRProjektDao prProjektDao;
	private IPRArucsatoloDao prArucsatoloDao;

	public abstract PRProjekt getPRProjectById(Integer id);

	public abstract List<PRProjekt> getPRProjektByPartnerId(Integer vevokod);

	public abstract List<PRProjekt> getAlPRProjektByFoPRProjektId(Integer id);

	public abstract List<PRProjekt> getPRProjektByDimenzio(Integer dimenzio);

	public abstract List<PRArucsatolo> getPRArucsatoloByPRProjektId(Integer id);

	public IPRProjektDao getPrProjektDao() {
		return prProjektDao;
	}

	public void setPrProjektDao(IPRProjektDao prProjektDao) {
		this.prProjektDao = prProjektDao;
	}

	public IPRArucsatoloDao getPrArucsatoloDao() {
		return prArucsatoloDao;
	}

	public void setPrArucsatoloDao(IPRArucsatoloDao prArucsatoloDao) {
		this.prArucsatoloDao = prArucsatoloDao;
	}

}

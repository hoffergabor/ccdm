package hu.iboard.coandco.datamagic.dao.prprojekt;

import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.util.List;

public interface IPRProjektDao {
	
	public PRProjekt getPRProjektById(Integer id);
	
	public List<PRProjekt> getPRProjektByPartnerId(Integer vevokod);
	
	public List<PRProjekt> getAlPRProjektByFoPRProjektId(Integer id);
	
	public List<PRProjekt> getPRProjektByDimenzio(Integer dimenzio);	

}

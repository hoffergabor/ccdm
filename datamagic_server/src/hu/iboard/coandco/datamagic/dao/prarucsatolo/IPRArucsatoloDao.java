package hu.iboard.coandco.datamagic.dao.prarucsatolo;

import hu.iboard.coandco.datamagic.vo.prarucsatolo.PRArucsatolo;

import java.util.List;

public interface IPRArucsatoloDao {

	public PRArucsatoloDao getPRArucsatoloById(Integer id);

	public List<PRArucsatolo> getPRArucsatoloByPRProjektId(Integer id);

}

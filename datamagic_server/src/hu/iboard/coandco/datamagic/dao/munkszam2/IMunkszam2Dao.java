package hu.iboard.coandco.datamagic.dao.munkszam2;

import hu.iboard.coandco.datamagic.generated.Munkszam2;

import java.util.List;

public interface IMunkszam2Dao {
	
	public Munkszam2 getMunkszam2ById(Integer id);
	
	public List<Munkszam2> getMunkszam2ByMunkszam(String munkszam);

}

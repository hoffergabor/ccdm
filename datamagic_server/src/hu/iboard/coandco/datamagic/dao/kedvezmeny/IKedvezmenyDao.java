package hu.iboard.coandco.datamagic.dao.kedvezmeny;

import hu.iboard.coandco.datamagic.generated.Kedvezmeny;

import java.util.List;

public interface IKedvezmenyDao {

	public Kedvezmeny getKedvezmenyById(Integer id);

	public List<Kedvezmeny> getAktualisKedvezmeny();

}

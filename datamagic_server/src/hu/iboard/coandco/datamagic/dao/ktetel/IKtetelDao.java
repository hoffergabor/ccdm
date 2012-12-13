package hu.iboard.coandco.datamagic.dao.ktetel;

import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;

import java.util.List;

public interface IKtetelDao {

	public List<Ktetel_CDH> getKtetelByMkateg4(Integer id);

}

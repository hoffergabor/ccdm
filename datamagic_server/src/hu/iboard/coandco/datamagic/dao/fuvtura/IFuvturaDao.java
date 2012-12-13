package hu.iboard.coandco.datamagic.dao.fuvtura;

import hu.iboard.coandco.datamagic.generated.Fuvtura;

import java.util.List;

public interface IFuvturaDao {

	public Fuvtura getFuvturaById(Integer id);

	public List<Fuvtura> getFuvturaByPartnerUtvonal(Integer id);

}

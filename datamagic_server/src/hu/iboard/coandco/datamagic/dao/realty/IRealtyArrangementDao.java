package hu.iboard.coandco.datamagic.dao.realty;

import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.IntMunkEl;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IRealtyArrangementDao {

	/**
	 * @param user
	 * @return
	 */

	public List<Intezkedes> findRealtyArrangementByCriteria(DetachedCriteria criteria);

	public List<Intezkedes> findRealtyArrangementByCriteria(DetachedCriteria criteria, int firstResult, int maxResult);

	public RealtyArrangementVO convertRealtyArrangementToVO(Intezkedes intezkedes);

	public RealtyArrangementVO convertRealtyArrangementToVONew(Object[] object);

	public List<Aconnect> findAttachsForRealtyArrangement(DetachedCriteria criteria);

	public Intezkedes saveOrUpdateRealtyArrangement(Intezkedes intezkedes);

	public List<Object[]> getInitRealtyArrangement(UserVO user, Integer vevokod, List<Integer> lakasids);

	public List<Object[]> getRealtyArrangementNew(UserVO user, Integer vevokod, Date from, Date to, String dateType,
			List<Integer> lakasIds);

	public List<Object[]> getRealtyArrangementByProjectIdNew(UserVO user, Integer vevokod, Integer projectId,
			List<Integer> lakasIds);

	public List<Object[]> getAllOwnersFromRealtyArrangement();

	public List<Object[]> getAllOwnersFromRealtyArrangementByName(String name);

	public List<Object[]> getRealtyArrangementByTulajdonos(Integer partnerId);

	public List<Object[]> getRealtyArrangementByAruExtIds(List<Integer> aruExtId);

	public Intezkedes getRealtyArrangementById(Integer id);

	public List<IntMunkEl> getIntMunkaElByIntezkedesId(Integer id);

	public List<IntezAru> getIntezAruByIntezkedesId(Integer id);
	
	public List<Object[]> getRealtyArrangementByLakasok(List<Integer> lakasok);
	
	public List<String> getAllBejelento();
	
	public List<Object[]> getRealtyArrangementByBejelento(String bejelento);

}

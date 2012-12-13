package hu.iboard.coandco.datamagic.service.realty;

import hu.iboard.coandco.datamagic.dao.aattachs.IAattachsDao;
import hu.iboard.coandco.datamagic.dao.aruext.IAruExtDao;
import hu.iboard.coandco.datamagic.dao.berbeadas.IBerbeadasDao;
import hu.iboard.coandco.datamagic.dao.ktetel.IKtetelDao;
import hu.iboard.coandco.datamagic.dao.munkszam2.IMunkszam2Dao;
import hu.iboard.coandco.datamagic.dao.project.IProjectDao;
import hu.iboard.coandco.datamagic.dao.realty.IRealtyArrangementDao;
import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;
import hu.iboard.coandco.datamagic.generated.Munkszam2;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Date;
import java.util.List;

public abstract class RealtyArrangementServiceBase extends ServiceBase {

	private IRealtyArrangementDao realtyArrangementDao;

	private IProjectDao projectDao;

	private IAruExtDao aruExtDao;

	private IBerbeadasDao berbeadasDao;

	private IAattachsDao aattachsDao;

	private IMunkszam2Dao munkszam2Dao;

	private IKtetelDao ktetelDao;

	public IRealtyArrangementDao getRealtyArrangementDao() {
		return realtyArrangementDao;
	}

	public void setRealtyArrangementDao(IRealtyArrangementDao realtyArrangementDao) {
		this.realtyArrangementDao = realtyArrangementDao;
	}

	public IProjectDao getProjectDao() {
		return projectDao;
	}

	public void setProjectDao(IProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	public IAruExtDao getAruExtDao() {
		return aruExtDao;
	}

	public void setAruExtDao(IAruExtDao aruExtDao) {
		this.aruExtDao = aruExtDao;
	}

	public IBerbeadasDao getBerbeadasDao() {
		return berbeadasDao;
	}

	public void setBerbeadasDao(IBerbeadasDao berbeadasDao) {
		this.berbeadasDao = berbeadasDao;
	}

	public IAattachsDao getAattachsDao() {
		return aattachsDao;
	}

	public void setAattachsDao(IAattachsDao aattachsDao) {
		this.aattachsDao = aattachsDao;
	}

	public IMunkszam2Dao getMunkszam2Dao() {
		return munkszam2Dao;
	}

	public void setMunkszam2Dao(IMunkszam2Dao munkszam2Dao) {
		this.munkszam2Dao = munkszam2Dao;
	}

	public IKtetelDao getKtetelDao() {
		return ktetelDao;
	}

	public void setKtetelDao(IKtetelDao ktetelDao) {
		this.ktetelDao = ktetelDao;
	}

	public abstract List<Intezkedes> getInitRealtyArrangement(UserVO user, Integer vevokod);

	public abstract List<Intezkedes> getRealtyArrangementByDateFiltered(UserVO user, Integer vevokod, Date from, Date to, String dateType);

	public abstract List<RealtyArrangementVO> getRealtyArrangementVOList(List<Intezkedes> intezkedesek);

	public abstract List<RealtyArrangementVO> getRealtyArrangementVOListNew(List<Object[]> intezkedesek);

	public abstract List<Aattachs> getAttachsForRealtyArrangement(String arrangementId);

	public abstract List<String> downloadAttachForRealtyArrangement(Aattachs aattachs, String path);

	public abstract Intezkedes saveOrUpdateRealtyArrangement(Intezkedes intezkedes);

	public abstract List<RealtyArrangementVO> getInitRealtyArrangementNew(UserVO user, Integer vevokod, List<Integer> lakasIds);

	public abstract List<RealtyArrangementVO> getRealtyArrangementByDateFilteredNew(UserVO user, Integer vevokod, Date from, Date to, String dateType,
			List<Integer> lakasIds);

	public abstract List<RealtyArrangementVO> getRealtyArrangementByProjectIdNew(UserVO user, Integer vevokod, Integer projectId, List<Integer> lakasIds);

	public abstract List<Object[]> getAllOwnersFromRealtyArrangementByName(String name);

	public abstract List<Object[]> getAllOwnersFromRealtyArrangement();

	public abstract List<RealtyArrangementVO> getRealtyArrangementByTulajdonos(Integer partnerId);

	public abstract List<RealtyArrangementVO> getRealtyArrangementByRenter(Integer partnerId);

	public abstract List<RealtyArrangementVO> getRealtyArrangementByRenterNew(Integer partnerId);

	public abstract List<Integer> getRenterIdsForRealtyArrangement();

	public abstract List<Integer> getLakasIdByPartnerId(Integer partnerId);

	public abstract Aattachs getAattachById(Integer id);

	public abstract List<String> getAllBejelento();

	public abstract List<RealtyArrangementVO> getRealtyArrangementByBejelento(String bejelento);

	public abstract List<IntezAru> getIntezAruListByIntezkedes(Integer id);

	public abstract Munkszam2 getMunkszam2ById(Integer id);

	public abstract List<Munkszam2> getMunkszam2ByMunkszam(String munkszam);

	public abstract List<Ktetel_CDH> getKtetelByMkateg4(Integer id);

}

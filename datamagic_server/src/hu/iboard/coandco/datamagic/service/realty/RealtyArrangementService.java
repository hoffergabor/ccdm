package hu.iboard.coandco.datamagic.service.realty;

import hu.iboard.coandco.datamagic.generated.Aattachs;
import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.AruExt;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.generated.Ktetel_CDH;
import hu.iboard.coandco.datamagic.generated.Munkszam2;
import hu.iboard.coandco.datamagic.service.others.FtpService;
import hu.iboard.coandco.datamagic.util.Decoder;
import hu.iboard.coandco.datamagic.util.ReverseByteDecoder;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class RealtyArrangementService extends RealtyArrangementServiceBase {

	@Override
	public List<Intezkedes> getInitRealtyArrangement(UserVO user, Integer vevokod) {

		List<Intezkedes> realty = new ArrayList<Intezkedes>();
		try {
			if (user != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(Intezkedes.class);
				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					criteria = criteria.add(Restrictions.eq("tulajdonos", vevokod));
				}
				if (user.getUserType().equals(UserVO.RENTER)) {
					criteria = criteria.add(Restrictions.eq("tulajdonos", vevokod));
				}
				criteria.addOrder(Order.desc("letrehozva"));
				realty = getRealtyArrangementDao().findRealtyArrangementByCriteria(criteria, 0, 10);
			} else {
				// log the error -> bad request
				throw new Exception("bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@Override
	public List<Intezkedes> getRealtyArrangementByDateFiltered(UserVO user, Integer vevokod, Date from, Date to, String dateType) {

		List<Intezkedes> realty = new ArrayList<Intezkedes>();
		try {
			if (user != null) {

				DetachedCriteria criteria = DetachedCriteria.forClass(Intezkedes.class);
				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					criteria = criteria.add(Restrictions.eq("tulajdonos", vevokod));
				}
				criteria.add(Restrictions.ge(dateType, from)).add(Restrictions.le(dateType, to)).addOrder(Order.desc("letrehozva"));

				realty = getRealtyArrangementDao().findRealtyArrangementByCriteria(criteria);
			} else {
				// log the error -> bad request
				throw new Exception("bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementVOList(List<Intezkedes> intezkedesek) {

		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();

		if (intezkedesek == null)
			return null;
		try {
			for (Intezkedes intezkedes : intezkedesek) {

				RealtyArrangementVO vo = new RealtyArrangementVO();
				vo = getRealtyArrangementDao().convertRealtyArrangementToVO(intezkedes);
				Object[] project = getProjectDao().getProjectAddressById(intezkedes.getProject());
				if (project != null) {
					vo.setObjectaddress("[" + project[0] + "] " + project[1] + " " + project[2] + ", " + project[3]);
				}
				Object[] aruext = getAruExtDao().getAruExtAddressById(intezkedes.getLakas());
				if (aruext != null) {
					vo.setFlataddress("[" + aruext[0] + "] " + aruext[1] + " " + aruext[2] + ", " + aruext[3] + " " + aruext[4]);
					vo.setItemNumber(intezkedes.getEvad() + "/" + intezkedes.getSorszam());
				}

				realtyList.add(vo);
			}
		} catch (Exception e) {
			logger.error("Error has occured by convert RealtyArrangement to VO!", e);
			return null;
		}
		return realtyList;
	}

	@Override
	public List<Aattachs> getAttachsForRealtyArrangement(String arrangementId) {

		List<Aconnect> aconnects = new ArrayList<Aconnect>();
		List<Aattachs> aattachs = new ArrayList<Aattachs>();

		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Aconnect.class);
			criteria = criteria.add(Restrictions.eq("ownerid", arrangementId)).add(Restrictions.eq("ownertype", "intezkedes"));

			aconnects = getRealtyArrangementDao().findAttachsForRealtyArrangement(criteria);

			if (aconnects == null) {
				return null;
			}

			for (Aconnect aconnect : aconnects) {

				Aattachs aattach = aconnect.getParentAattachs();
				aattachs.add(aattach);
			}
			if (aattachs == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("error occured at processing list attachs for realtyarrangement: " + e.getMessage());
			return null;
		}
		return aattachs;
	}

	@Override
	public List<String> downloadAttachForRealtyArrangement(Aattachs attach, String path) {

		FtpService ftp;
		List<String> files = new ArrayList<String>();
		String md5FileName = "";
		Decoder decoder = new ReverseByteDecoder();

		md5FileName = stringKonverter(attach.getFilenev()); // as here

		try {
			ftp = new FtpService();
			files = ftp.downloadDecodeUnzip(md5FileName, decoder, path);
			logger.info("realty attached files: " + files);
			ftp.disconnect();
		} catch (Exception e) {
			logger.error("Error by dowloading realty attach file!", e);
			return null;
		}
		return files;
	}

	@Override
	public Intezkedes saveOrUpdateRealtyArrangement(Intezkedes intezkedes) {
		if (intezkedes == null)
			return null;
		return getRealtyArrangementDao().saveOrUpdateRealtyArrangement(intezkedes);
	}

	@Override
	public List<RealtyArrangementVO> getInitRealtyArrangementNew(UserVO user, Integer vevokod, List<Integer> lakasIds) {
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getInitRealtyArrangement(user, vevokod, lakasIds);

		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementVOListNew(List<Object[]> intezkedesek) {
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();

		if (intezkedesek == null)
			return null;
		for (Object[] object : intezkedesek)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementByDateFilteredNew(UserVO user, Integer vevokod, Date from, Date to, String dateType,
			List<Integer> lakasIds) {
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementNew(user, vevokod, from, to, dateType, lakasIds);

		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	public List<RealtyArrangementVO> getRealtyArrangementByProjectIdNew(UserVO user, Integer vevokod, Integer projectId, List<Integer> lakasIds) {
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementByProjectIdNew(user, vevokod, projectId, lakasIds);
		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;

	}

	@Override
	public List<Object[]> getAllOwnersFromRealtyArrangementByName(String name) {
		return getRealtyArrangementDao().getAllOwnersFromRealtyArrangementByName(name);
	}

	@Override
	public List<Object[]> getAllOwnersFromRealtyArrangement() {
		return getRealtyArrangementDao().getAllOwnersFromRealtyArrangement();
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementByTulajdonos(Integer partnerId) {
		if (partnerId == null)
			return null;
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementByTulajdonos(partnerId);
		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementByRenter(Integer partnerId) {
		List<Integer> tempIds = new ArrayList<Integer>();
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<AruExt> aruexts = getAruExtDao().getAruExtsForRenter(partnerId);
		if (aruexts == null)
			return null;
		for (AruExt aruExt : aruexts) {
			tempIds.add(aruExt.getArukod());
		}
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementByAruExtIds(tempIds);
		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<Integer> getLakasIdByPartnerId(Integer partnerId) {
		if (partnerId == null)
			return null;
		return getBerbeadasDao().getLakasByPartnerId(partnerId);
	}

	@Override
	public Aattachs getAattachById(Integer id) {
		if (id == null)
			return null;
		return getAattachsDao().getAattachsById(id);
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementByRenterNew(Integer partnerId) {
		List<Integer> lakasok = getBerbeadasDao().getLakasByPartnerId(partnerId);
		if (lakasok == null)
			return null;
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementByLakasok(lakasok);
		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<Integer> getRenterIdsForRealtyArrangement() {
		return getBerbeadasDao().getRentersIdByForRealtyArrangement();
	}

	@Override
	public List<String> getAllBejelento() {
		return getRealtyArrangementDao().getAllBejelento();
	}

	@Override
	public List<RealtyArrangementVO> getRealtyArrangementByBejelento(String bejelento) {
		List<RealtyArrangementVO> realtyList = new ArrayList<RealtyArrangementVO>();
		List<Object[]> objects = getRealtyArrangementDao().getRealtyArrangementByBejelento(bejelento);
		if (objects == null || objects.size() == 0)
			return null;
		for (Object[] object : objects)
			realtyList.add(getRealtyArrangementDao().convertRealtyArrangementToVONew(object));
		return realtyList;
	}

	@Override
	public List<IntezAru> getIntezAruListByIntezkedes(Integer id) {
		if (id == null)
			return null;
		return getRealtyArrangementDao().getIntezAruByIntezkedesId(id);
	}

	@Override
	public Munkszam2 getMunkszam2ById(Integer id) {
		if (id == null)
			return null;
		return getMunkszam2Dao().getMunkszam2ById(id);
	}

	@Override
	public List<Munkszam2> getMunkszam2ByMunkszam(String munkszam) {
		if (munkszam == null)
			return null;
		return getMunkszam2Dao().getMunkszam2ByMunkszam(munkszam);
	}

	@Override
	public List<Ktetel_CDH> getKtetelByMkateg4(Integer id) {
		if(id==null)
			return null;
		return getKtetelDao().getKtetelByMkateg4(id);
	}

	public String stringKonverter(String s) {
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++)
			switch (c[i]) {
			case 'á':
				sb.append("a");
				break;
			case 'Á':
				sb.append("A");
				break;
			case 'é':
				sb.append("e");
				break;
			case 'É':
				sb.append("E");
				break;
			case 'í':
				sb.append("i");
				break;
			case 'Í':
				sb.append("I");
				break;
			case 'ó':
				sb.append("o");
				break;
			case 'Ó':
				sb.append("O");
				break;
			case 'ö':
				sb.append("o");
				break;
			case 'Ö':
				sb.append("O");
				break;
			case 'ő':
				sb.append("o");
				break;
			case 'Ő':
				sb.append("O");
				break;
			case 'ú':
				sb.append("u");
				break;
			case 'Ú':
				sb.append("u");
				break;
			case 'ű':
				sb.append("u");
				break;
			case 'Ű':
				sb.append("u");
				break;
			case '?':
				break;				
			default:
				if ((byte)c[i] >= 0)
					sb.append(c[i]);
			}
		return sb.toString();
	}
}

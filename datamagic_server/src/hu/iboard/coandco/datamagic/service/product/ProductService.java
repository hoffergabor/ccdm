/**
 * 
 */
package hu.iboard.coandco.datamagic.service.product;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.generated.Arutasitas;
import hu.iboard.coandco.datamagic.generated.Arvalt;
import hu.iboard.coandco.datamagic.generated.Cikkcsop;
import hu.iboard.coandco.datamagic.generated.Kedvezmeny;
import hu.iboard.coandco.datamagic.generated.Opciok;
import hu.iboard.coandco.datamagic.generated.Raktar;
import hu.iboard.coandco.datamagic.generated.Tapadogon;
import hu.iboard.coandco.datamagic.generated.Vevoarak;
import hu.iboard.coandco.datamagic.generated.WebStorages;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ProductService extends ProductServiceBase {

	@SuppressWarnings("unchecked")
	@Override
	public ProductVO getProductsById(int arukod, QueryParamsVO queryParamsVO) {

		ProductVO vo = new ProductVO();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(Aru.class).add(Restrictions.idEq(arukod));
			List<Aru> listProduct = getHibernateTemplate().findByCriteria(criteria);
			if (listProduct.size() == 0)
				return null;
			Aru aru = listProduct.get(0);
			vo = convertAruToProductVO(aru, queryParamsVO);

		} catch (Exception e) {
			logger.error("Error by getting products!", e);
			return null;
		}
		return vo;
	}

	//private ProductVO convertAruToProductVO(Aru product, Arnev arnev) {
	public ProductVO convertAruToProductVO(Aru product, QueryParamsVO queryParamsVO) {

		ProductVO vo = new ProductVO();
		
		vo.setArukod(product.getArukod());
		if (product.getAfakodok() != null)
			vo.setAfa(new BigDecimal(1 + ((float) product.getAfakodok().getFordkulcs() / 100)));
		vo.setCikkszam(product.getCikkszam());
		vo.setMegn(product.getMegn());
		vo.setMennyiseg(new BigDecimal(1));
		vo.setAzon3(product.getAzon3());
		vo.setAzon4(product.getAzon4());
		vo.setAzon5(product.getAzon5());
		vo.setAzon6(product.getAzon6());
		vo.setAzon7(product.getAzon7());
		vo.setAzon8(product.getAzon8());
		vo.setAzon9(product.getAzon9());
		vo.setAzon10(product.getAzon10());
		vo.setAzon11(product.getAzon11());
		vo.setAzon12(product.getAzon12());
		vo.setMenny(product.getMenny());
		vo.setMeegys(product.getMeegys());
		//vo.setMegn3(product.getMegn3());
		vo.setMegn(product.getMegn());
		vo.setRaktar(product.getRaktar());		
		
		Integer arkod = new Integer(0);
		Arnev arnev = getArnevFromPartner(queryParamsVO.getPartner());
		if (arnev == null) {
			arkod = queryParamsVO.getDefaultArkod();
		} else {
			arkod = arnev.getKod();
		}		
		
		switch (arkod) {
		case 1:
			vo.setEar(product.getEar());
			break;
		case 2:
			vo.setEar(product.getEar2());
			break;
		case 3:
			vo.setEar(product.getEar3());
			break;
		case 4:
			vo.setEar(product.getEar4());
			break;
		case 5:
			vo.setEar(product.getDear1());
			break;
		case 6:
			vo.setEar(product.getDear2());
			break;
		case 7:
			vo.setEar(product.getDear3());
			break;
		case 8:
			vo.setEar(product.getDear4());
			break;
		case 9:
			vo.setEar(product.getEar5());
			break;
		case 10:
			vo.setEar(product.getEar6());
			break;
		case 11:
			vo.setEar(product.getEar7());
			break;
		case 12:
			vo.setEar(product.getEar8());
			break;
		case 13:
			vo.setEar(product.getEar9());
			break;
		case 14:
			vo.setEar(product.getEar10());
			break;
		case 15:
			vo.setEar(product.getEar11());
			break;
		case 16:
			vo.setEar(product.getEar12());
			break;
		case 17:
			vo.setEar(product.getEar13());
			break;
		case 18:
			vo.setEar(product.getEar14());
			break;
		case 19:
			vo.setEar(product.getEar15());
			break;
		default:
			vo.setEar(product.getEar());
		}
		
		Integer megnKod = queryParamsVO.getMegnevezesKod();
		if (megnKod == null)
			megnKod = 0;
		
		switch (megnKod) {
		case 0:
			vo.setMegn(product.getMegn());			
			break;
		case 2:
			vo.setMegn(product.getMegn2());
			break;
		case 3:
			vo.setMegn(product.getMegn3());
			break;
		default:
			vo.setMegn(product.getMegn());
			break;
		}
		
		vo.setKedvar(vo.getEar());
		Boolean adaptDiscount = queryParamsVO.getAdaptDiscount();
		if (adaptDiscount == null)
			adaptDiscount  = true;

		if (queryParamsVO.getPartner() != null && adaptDiscount)
		{
			BigDecimal kedvezmeny = null;
			if (queryParamsVO.getPartner().getKedvez() != null)
				kedvezmeny = queryParamsVO.getPartner().getKedvez();
			if (kedvezmeny != null && !kedvezmeny.equals(0))
				vo.setKedvar(vo.getKedvar().subtract(vo.getEar().multiply(kedvezmeny.divide(new BigDecimal(100)))));
		}
		
		vo.setNetto(vo.getEar());
		if (vo.getAfa() != null)
		{
			vo.setBrutto(vo.getAfa().multiply(vo.getEar()));
			vo.setKedvarBrutto(vo.getAfa().multiply(vo.getKedvar()));
		}
		else
		{
			vo.setBrutto(new BigDecimal(0));
			vo.setKedvarBrutto(new BigDecimal(0));
		}
		if (arnev != null) {
			if (arnev.getDevizanev() != null) {
				vo.setDevnem(arnev.getDevizanev());
			}
		} else
			vo.setDevnem(product.getDevnem());

		return vo;		
	
	}

	@Override
	public ProductVO convertAruTempToProductVO(AruTemp product, QueryParamsVO queryParamsVO) {

		ProductVO vo = new ProductVO();
		vo.setArukod(product.getArukod());
		if (product.getAfakodok() != null)
			vo.setAfa(new BigDecimal(1 + ((float) product.getAfakodok().getFordkulcs() / 100)));
		vo.setCikkszam(product.getCikkszam());
		vo.setMegn(product.getMegn());
		//vo.setMegn3(product.getMegn3());
		vo.setPassziv(product.getPassziv());
		vo.setRaktar(product.getRaktar());
		vo.setAzon3(product.getAzon3());
		vo.setAzon4(product.getAzon4());
		vo.setAzon5(product.getAzon5());
		vo.setAzon8(product.getAzon8());
		vo.setAzon9(product.getAzon9());
		vo.setAzon10(product.getAzon10());
		vo.setAzon11(product.getAzon11());
		vo.setAzon12(product.getAzon12());
		
		Integer arkod = new Integer(0);
		Arnev arnev = getArnevFromPartner(queryParamsVO.getPartner());
		if (arnev == null) {
			arkod = queryParamsVO.getDefaultArkod();
		} else {
			arkod = arnev.getKod();
		}	
		
		switch (arkod) {
		case 1:
			vo.setEar(product.getEar());
			break;
		case 2:
			vo.setEar(product.getEar2());
			break;
		case 3:
			vo.setEar(product.getEar3());
			break;
		case 4:
			vo.setEar(product.getEar4());
			break;
		case 5:
			vo.setEar(product.getDear1());
			break;
		case 6:
			vo.setEar(product.getDear2());
			break;
		case 7:
			vo.setEar(product.getDear3());
			break;
		case 8:
			vo.setEar(product.getDear4());
			break;
		case 9:
			vo.setEar(product.getEar5());
			break;
		case 10:
			vo.setEar(product.getEar6());
			break;
		case 11:
			vo.setEar(product.getEar7());
			break;
		case 12:
			vo.setEar(product.getEar8());
			break;
		case 13:
			vo.setEar(product.getEar9());
			break;
		case 14:
			vo.setEar(product.getEar10());
			break;
		case 15:
			vo.setEar(product.getEar11());
			break;
		case 16:
			vo.setEar(product.getEar12());
			break;
		case 17:
			vo.setEar(product.getEar13());
			break;
		case 18:
			vo.setEar(product.getEar14());
			break;
		case 19:
			vo.setEar(product.getEar15());
			break;
		default:
			vo.setEar(product.getEar());
		}
		
		vo.setKedvar(vo.getEar());
		Boolean adaptDiscount = queryParamsVO.getAdaptDiscount();
		if (adaptDiscount == null)
			adaptDiscount  = true;

		if (queryParamsVO.getPartner() != null && adaptDiscount)
		{
			BigDecimal kedvezmeny = null;
			if (queryParamsVO.getPartner().getKedvez() != null)
				kedvezmeny = queryParamsVO.getPartner().getKedvez();
			if (kedvezmeny != null && !kedvezmeny.equals(0))
				vo.setKedvar(vo.getKedvar().subtract(vo.getEar().multiply(kedvezmeny.divide(new BigDecimal(100)))));
		}
		
		vo.setNetto(vo.getEar());
		if (vo.getAfa() != null)
		{
			vo.setBrutto(vo.getAfa().multiply(vo.getEar()));
			vo.setKedvarBrutto(vo.getAfa().multiply(vo.getKedvar()));
		}
		else
		{
			vo.setBrutto(new BigDecimal(0));
			vo.setKedvarBrutto(new BigDecimal(0));
		}
		if (arnev != null) {
			if (arnev.getDevizanev() != null) {
				vo.setDevnem(arnev.getDevizanev());
			}
		} else
			vo.setDevnem(product.getDevnem());

		return vo;				
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProductAzon(String azonField) {

		List<String> list = new ArrayList<String>();
		try {
			list = getHibernateTemplate().find(
					"SELECT DISTINCT aru." + azonField + " FROM Aru aru WHERE aru." + azonField + "!= '' OR aru." + azonField + " != ' ' ORDER BY aru."
							+ azonField + " ASC");

			if (list == null || list.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error by getting AZON List", e);
			return null;
		}
		return list;
	}

	@Override
	public List<ProductVO> getProductsByAzon(String azonField, String azonValue, QueryParamsVO queryParamsVO) {

		List<ProductVO> voList = new ArrayList<ProductVO>();
		try {

			getHibernateTemplate().initialize(Aru.class);
			DetachedCriteria criteria = DetachedCriteria.forClass(Aru.class);

			criteria.add(Restrictions.eq(azonField, azonValue));
			criteria.addOrder(Order.asc("megn"));
			List<Aru> aruList = getProductDao().findProductByCriteria(criteria);
			if (aruList == null || aruList.size() == 0) {
				return null;
			}

			for (Aru aru : aruList) {
				ProductVO vo = convertAruToProductVO(aru, queryParamsVO);
				voList.add(vo);
			}

		} catch (Exception e) {
			logger.error("Error by getting product by AZON", e);
			return null;
		}
		return voList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Opciok getOpciokName() {

		List<Opciok> options = new ArrayList<Opciok>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(Opciok.class);
			options = getHibernateTemplate().findByCriteria(criteria);

			if (options == null || options.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting opciok!", e);
			return null;
		}
		return options.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Cikkcsop> getCikkcsopForProduct() {
		List<Cikkcsop> cikkcsops = new ArrayList<Cikkcsop>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(Cikkcsop.class);
			criteria.add(Restrictions.eq("engadhat", true));
			criteria.addOrder(Order.asc("megn"));
			cikkcsops = getHibernateTemplate().findByCriteria(criteria);

			if (cikkcsops == null || cikkcsops.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting cikkcsop!", e);
			return null;
		}
		return cikkcsops;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Raktar> getActiveStorages() {
		List<Raktar> storages = new ArrayList<Raktar>();
		try {
			List<Integer> storageIds = getCheckedStorage();
			if (storageIds == null)
				return null;

			DetachedCriteria criteria = DetachedCriteria.forClass(Raktar.class);
			criteria.add(Restrictions.in("kod", storageIds));
			storages = getHibernateTemplate().findByCriteria(criteria);

			if (storages == null || storages.size() == 0)
				return null;
		} catch (Exception e) {
			logger.error("Error by getting cikkcsop!", e);
			return null;
		}
		return storages;
	}

	@SuppressWarnings("unchecked")
	private List<Integer> getCheckedStorage() {
		List<WebStorages> storages = new ArrayList<WebStorages>();
		List<Integer> ids = new ArrayList<Integer>();
		try {

			DetachedCriteria criteria = DetachedCriteria.forClass(WebStorages.class).add(Restrictions.eq("storageChecked", new Integer(1)));

			storages = getHibernateTemplate().findByCriteria(criteria);
			if (storages == null || storages.size() == 0)
				return null;
			for (WebStorages storage : storages) {
				ids.add(storage.getStorageId());
			}

		} catch (Exception e) {
			logger.error("Error by checked storage!", e);
			return null;
		}
		return ids;
	}

	@Override
	public List<ProductVO> getProductsNew(String itemNumber, String itemName, Integer csopkod, QueryParamsVO queryParamsVO) {
		List<Aru> aruk  = getProductDao().getProducts(getArnevFromPartner(queryParamsVO.getPartner()), itemNumber, itemName, csopkod);
		List<ProductVO> productVOs = new ArrayList<ProductVO>();
		if (aruk != null && aruk.size() > 0) {
			for (Aru aru : aruk) {
				productVOs.add(convertAruToProductVO(aru, queryParamsVO));
			}
		}
		return productVOs;
	}

	@Override
	public List<ProductVO> getProductForAdvancedSearch(String cikkszam, QueryParamsVO queryParamsVO) {
		if (cikkszam == null)
			return null;
		List<AruTemp> aruk = getProductDao().getProductsForAdvancedSearch(cikkszam);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<ProductVO> getProductForSearch(String sku, String megnevezes, QueryParamsVO queryParamsVO) {
		List<AruTemp> aruk = getProductDao().getProductsForSearch(sku, megnevezes);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public Arnev getArnevById(Integer id) {
		if (id == null)
			return null;
		return getArnevDao().getArnevById(id);
	}

	@Override
	public Cikkcsop getCikkcsopById(BigDecimal kod) {
		if (kod == null)
			return null;
		return getCikkcsopDao().getCikkcsopById(kod);
	}

	@Override
	public List<Object[]> getCikkcsopObjsByKod(BigDecimal kod) {
		if (kod == null)
			return null;
		return getCikkcsopDao().getCikkcsopObjsByKod(kod);
	}

	@Override
	public List<Object[]> getAllCikkcsopObjs() {
		return getCikkcsopDao().getAllCikkcsopObjs();
	}

	@Override
	public List<ProductVO> getProductsByCsopkods(List<BigDecimal> csopkods, QueryParamsVO queryParamsVO) {
		if (csopkods == null)
			return null;
		List<AruTemp> aruk = getProductDao().getProductsByCsopkods(csopkods);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public Aru getAruById(Integer id) {
		if (id == null)
			return null;
		return getProductDao().getAruById(id);
	}

	@Override
	public AruTemp getAruTempById(Integer id) {
		if (id == null)
			return null;
		return getProductDao().getAruTempById(id);
	}

	@Override
	public List<Tapadogon> getTapadogonByAkod(Integer akod) {
		if (akod == null)
			return null;
		return getTapadogonDao().getTapadogonByAkod(akod);
	}

	@Override
	public List<Kedvezmeny> getAktualisKedvezmeny() {
		return getKedvezmenyDao().getAktualisKedvezmeny();
	}

	@Override
	public List<Vevoarak> getVevoarakByKedvezmenyForPartner(Integer id, Integer vevokod) {
		if (id == null)
			return null;
		return getVevoarakDao().getVevoarakByKedvezmenyForPartner(id, vevokod);
	}

//	@Override
//	public ProductVO convertProductToVO(Aru product) {
//		if (product == null)
//			return null;
//		return getProductDao().convertProductToVO(product);
//	}

	@Override
	public List<Arvalt> getArvaltByArutasitasForPartner(Integer arutasitas, Integer arnev) {
		return getArvaltDao().getArvaltByArutasitasForPartner(arutasitas, arnev);
	}

	@Override
	public List<Arutasitas> getAktualisAkcio() {
		return getArutasitasDao().getAktualisAkcio();
	}

	@Override
	public List<ProductVO> getProductsByCikkcsopMegn(String megn, QueryParamsVO queryParamsVO) {
		if (megn == null)
			return null;
		List<AruTemp> aruk = getProductDao().getProductsByCikkcsopMegn(megn);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<String> getProductsAzon3() {
		return getProductDao().getProductsAzon3();
	}

	@Override
	public List<ProductVO> getProductsTempByAzon(String azonName, String azonValue, QueryParamsVO queryParamsVO) {
		List<AruTemp> aruk = getProductDao().getProductsByAzon(azonName, azonValue);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;

	}

	@Override
	public List<ProductVO> getProductsTempByIDs(List<Integer> IDs, QueryParamsVO queryParamsVO) {
		List<ProductVO> result = new ArrayList<ProductVO>();
		List<AruTemp> tempresult = getProductDao().getAruTempsByIds(IDs);
		for (AruTemp aruTemp : tempresult) {
			if (aruTemp == null)
				continue;
			ProductVO vo = convertAruTempToProductVO(aruTemp, queryParamsVO);
			if (vo != null)
				result.add(vo);
		}
		return result;
	}

	@Override
	// azon3
	public List<Integer> getProductTempIDsByTipus(String tipus) {
		return getProductDao().getProductTempIDsByTipus(tipus);
	}

	// azon5
	public List<Integer> getProductTempIDsByTermekNev(String termeknev) {
		return getProductDao().getProductTempIDsByTermekNev(termeknev);
	}

	// azon8
	public List<Integer> getProductTempIDsByMeret(String meret) {
		return getProductDao().getProductTempIDsByMeret(meret);
	}

	// azon9
	public List<Integer> getProductTempIDsByBazis(String bazis) {
		return getProductDao().getProductTempIDsByBazis(bazis);
	}

	// azon10
	public List<Integer> getProductTempIDsByKiszereles(String kiszereles) {
		return getProductDao().getProductTempIDsByKiszereles(kiszereles);
	}

	// azon11
	public List<Integer> getProductTempIDsBySzincsalad(String szincsalad) {
		return getProductDao().getProductTempIDsBySzincsalad(szincsalad);
	}

	// azon12
	public List<Integer> getProductTempIDsByArnyalat(String arnyalat) {
		return getProductDao().getProductTempIDsByArnyalat(arnyalat);
	}

	// azon4
	public List<Integer> getProductTempIDsByTermekCsoport(String termekcsoport) {
		return getProductDao().getProductTempIDsByTermekCsoport(termekcsoport);
	}

	@Override
	public Boolean isCikkcsopEmpty(Cikkcsop cikkcsop) {
		if (cikkcsop == null)
			return true;
		return getCikkcsopDao().isCikkcsopEmpty(cikkcsop);
	}

	@Override
	public Boolean isCikkcsopEmpty(BigDecimal cikkCsopKod) {
		if (cikkCsopKod == null)
			return true;
		return getCikkcsopDao().isCikkcsopEmpty(cikkCsopKod);
	}

	@Override
	public List<Vevoarak> getActualVevoarakForPartner(Integer vevokod) {
		if (vevokod == null)
			return null;
		return getVevoarakDao().getActualVevoarakForPartner(vevokod);
	}

	@Override
	public List<ProductVO> getAkciosProducts(QueryParamsVO queryParamsVO) {
		List<AruTemp> aruk = getProductDao().getAkciosProducts();
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruTemp aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruTempToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public Arnev getArnevFromPartner(Partner partner) {
		Arnev arnev = null;
		if (partner == null)
			return null;
		if (partner.getVevocsop() == null)
			return null;
		if (partner.getVevocsop().getArnevkod() == null)
			return null;
		if (partner.getVevocsop().getArnevkod() == 0)
			arnev = getArnevDao().getArnevById(1);
		else
			arnev = partner.getVevocsop().getArnev();

		return arnev;
	}	
	
}

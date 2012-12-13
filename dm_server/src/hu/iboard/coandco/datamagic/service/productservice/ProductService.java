package hu.iboard.coandco.datamagic.service.productservice;

import hu.iboard.coandco.datamagic.model.arnev.Arnev;
import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.model.bizkomment.Bizkomment;
import hu.iboard.coandco.datamagic.model.cikkcsop.Cikkcsop;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductService extends ProductServiceBase {

	@Override
	public Aru getProductById(Integer arukod) {
		if (arukod == null)
			return null;
		return getProductDao().getProductById(arukod);
	}

	@Override
	public List<ProductVO> getProductByName(String name, QueryParamsVO queryParamsVO) {
		if (name == null)
			return null;
		List<AruKisker> aruk = getProductDao().getProductByName(name);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<String> getOptProductByName(String name) {
		if (name == null)
			return null;
		return getProductDao().getOptProductByName(name);
	}

	@Override
	public List<ProductVO> getProductsByCsopkod(BigDecimal csopkod, QueryParamsVO queryParamsVO) {
		if (csopkod == null)
			return null;
		List<AruKisker> aruk = getProductDao().getProductsByCsopkod(csopkod);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
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
	public AruKisker getProductKiskerById(Integer arukod) {
		if (arukod == null)
			return null;
		return getProductDao().getProductKiskerById(arukod);
	}

	@Override
	public List<ProductVO> getProductByNameNew(String name, QueryParamsVO queryParamsVO) {
		if (name == null)
			return null;
		List<AruKisker> aruk = getProductDao().getProductByNameNew(name);

		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<ProductVO> getKiemeltProducts(QueryParamsVO queryParamsVO) {
		List<AruKisker> aruk = getProductDao().getKiemeltProducts();

		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public List<ProductVO> getProductsByCikkcsopMegn(String megn, QueryParamsVO queryParamsVO) {
		if (megn == null)
			return null;
		List<AruKisker> aruk = getProductDao().getProductsByCikkcsopMegn(megn);

		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public Bizkomment getBizkommentById(String id) {
		if (id == null)
			return null;
		return getBizkommentDao().getBizkommentById(id);
	}

	@Override
	public List<ProductVO> getProductsByCsopkods(List<BigDecimal> csopkods, QueryParamsVO queryParamsVO) {
		if (csopkods == null)
			return null;
		List<AruKisker> aruk = getProductDao().getProductsByCsopkods(csopkods);
		if (aruk == null)
			return null;
		List<ProductVO> vos = new ArrayList<ProductVO>();
		for (AruKisker aru : aruk) {
			ProductVO vo = new ProductVO();
			vo = convertAruKiskerToProductVO(aru, queryParamsVO);
			vos.add(vo);
		}
		return vos;
	}

	/*
	 * @Override public ProductVO convertProductToVO(Aru product) { if (product
	 * == null) return null; return getProductDao().convertProductToVO(product);
	 * }
	 * 
	 * 
	 * private ProductVO convertAruToProductVO(Aru product, Arnev arnev) {
	 * 
	 * ProductVO vo = new ProductVO();
	 * 
	 * vo = getProductDao().convertProductToVO(product); if (arnev == null)
	 * vo.setEar(product.getEar()); else { switch (arnev.getKod()) { case 1:
	 * vo.setEar(product.getEar()); break; case 2: vo.setEar(product.getEar2());
	 * break; case 3: vo.setEar(product.getEar3()); break; case 4:
	 * vo.setEar(product.getEar4()); break; case 5:
	 * vo.setEar(product.getDear1()); break; case 6:
	 * vo.setEar(product.getDear2()); break; case 7:
	 * vo.setEar(product.getDear3()); break; case 8:
	 * vo.setEar(product.getDear4()); break; case 9:
	 * vo.setEar(product.getEar5()); break; case 10:
	 * vo.setEar(product.getEar6()); break; case 11:
	 * vo.setEar(product.getEar7()); break; case 12:
	 * vo.setEar(product.getEar8()); break; case 13:
	 * vo.setEar(product.getEar9()); break; case 14:
	 * vo.setEar(product.getEar10()); break; case 15:
	 * vo.setEar(product.getEar11()); break; case 16:
	 * vo.setEar(product.getEar12()); break; case 17:
	 * vo.setEar(product.getEar13()); break; case 18:
	 * vo.setEar(product.getEar14()); break; case 19:
	 * vo.setEar(product.getEar15()); break; default:
	 * vo.setEar(product.getEar()); } } vo.setNetto(vo.getEar());
	 * vo.setBrutto(vo.getAfa().multiply(vo.getEar())); if (arnev != null)
	 * vo.setDevnem(arnev.getDevizanev()); else
	 * vo.setDevnem(product.getDevnem());
	 * 
	 * return vo; }
	 */
	
	private ProductVO convertAruKiskerToProductVO(AruKisker product, QueryParamsVO queryParamsVO) {
		ProductVO vo = new ProductVO();
		vo.setArukod(product.getArukod());
		if (product.getAfakodok() != null)
			vo.setAfa(new BigDecimal(1 + ((float) product.getAfakodok().getFordkulcs() / 100)));
		vo.setCikkszam(product.getCikkszam());
		//vo.setMegn(product.getMegn());
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
		vo.setAfakodok(product.getAfakodok());
		vo.setMenny(product.getMenny());
		vo.setCsopkod(product.getCsopkod());
		Integer arkod = new Integer(0);
		// BigDecimal ar = new BigDecimal(0);
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
	public Arnev getArnevById(Integer id) {
		if (id == null)
			return null;
		return getArnevDao().getArnevById(id);
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

	@Override
	public Boolean isCikkcsopEmpty(Cikkcsop cikkcsop) {
		if (cikkcsop == null)
			return true;
		return getCikkcsopDao().IsCikkcsopEmpty(cikkcsop);
	}

	@Override
	public Boolean isCikkcsopEmpty(BigDecimal cikkCsopKod) {
		if (cikkCsopKod == null)
			return true;
		return getCikkcsopDao().IsCikkcsopEmpty(cikkCsopKod);
	}

	@Override
	public ProductVO getProductVOById(Integer arukod, QueryParamsVO queryParamsVO) {
		if (arukod == null)
			return null;
		AruKisker aru = getProductDao().getProductKiskerById(arukod);
		if (aru == null)
			return null;
		return convertAruKiskerToProductVO(aru, queryParamsVO);
	}

}

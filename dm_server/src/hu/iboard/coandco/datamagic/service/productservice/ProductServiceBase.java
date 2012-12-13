package hu.iboard.coandco.datamagic.service.productservice;

import hu.iboard.coandco.datamagic.dao.arnev.IArnevDao;
import hu.iboard.coandco.datamagic.dao.bizkomment.IBizkommentDao;
import hu.iboard.coandco.datamagic.dao.cikkcsop.ICikkcsopDao;
import hu.iboard.coandco.datamagic.dao.product.IProductDao;
import hu.iboard.coandco.datamagic.model.arnev.Arnev;
import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.model.bizkomment.Bizkomment;
import hu.iboard.coandco.datamagic.model.cikkcsop.Cikkcsop;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProductServiceBase {

	private IProductDao productDao;
	private ICikkcsopDao cikkcsopDao;
	private IBizkommentDao bizkommentDao;
	private IArnevDao arnevDao;

	public abstract Aru getProductById(Integer arukod);

	public abstract AruKisker getProductKiskerById(Integer arukod);

	public abstract ProductVO getProductVOById(Integer arukod, QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getProductByName(String name, QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getProductByNameNew(String name, QueryParamsVO queryParamsVO);

	public abstract List<String> getOptProductByName(String name);

	public abstract List<ProductVO> getProductsByCsopkod(BigDecimal csopkod, QueryParamsVO queryParamsVO);

	public abstract Cikkcsop getCikkcsopById(BigDecimal kod);

	public abstract List<Object[]> getCikkcsopObjsByKod(BigDecimal kod);

	public abstract List<Object[]> getAllCikkcsopObjs();

	public abstract List<ProductVO> getProductsByCsopkods(List<BigDecimal> csopkods, QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getKiemeltProducts(QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getProductsByCikkcsopMegn(String megn, QueryParamsVO queryParamsVO);

	public abstract Arnev getArnevFromPartner(Partner partner);

	public abstract Arnev getArnevById(Integer id);

	public abstract Bizkomment getBizkommentById(String id);

	public abstract Boolean isCikkcsopEmpty(Cikkcsop cikkcsop);

	public abstract Boolean isCikkcsopEmpty(BigDecimal cikkCsopKod);

	public IProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public ICikkcsopDao getCikkcsopDao() {
		return cikkcsopDao;
	}

	public void setCikkcsopDao(ICikkcsopDao cikkcsopDao) {
		this.cikkcsopDao = cikkcsopDao;
	}

	public IBizkommentDao getBizkommentDao() {
		return bizkommentDao;
	}

	public void setBizkommentDao(IBizkommentDao bizkommentDao) {
		this.bizkommentDao = bizkommentDao;
	}

	public IArnevDao getArnevDao() {
		return arnevDao;
	}

	public void setArnevDao(IArnevDao arnevDao) {
		this.arnevDao = arnevDao;
	}
}
package hu.iboard.coandco.datamagic.service.product;

import hu.iboard.coandco.datamagic.dao.arnev.IArnevDao;
import hu.iboard.coandco.datamagic.dao.arutasitas.IArutasitasDao;
import hu.iboard.coandco.datamagic.dao.arvalt.IArvaltDao;
import hu.iboard.coandco.datamagic.dao.cikkcsop.CikkcsopDao;
import hu.iboard.coandco.datamagic.dao.kedvezmeny.IKedvezmenyDao;
import hu.iboard.coandco.datamagic.dao.product.IProductDao;
import hu.iboard.coandco.datamagic.dao.tapadogon.ITapadogonDao;
import hu.iboard.coandco.datamagic.dao.vevoarak.IVevoarakDao;
import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.generated.Arutasitas;
import hu.iboard.coandco.datamagic.generated.Arvalt;
import hu.iboard.coandco.datamagic.generated.Cikkcsop;
import hu.iboard.coandco.datamagic.generated.Kedvezmeny;
import hu.iboard.coandco.datamagic.generated.Opciok;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Raktar;
import hu.iboard.coandco.datamagic.generated.Tapadogon;
import hu.iboard.coandco.datamagic.generated.Vevoarak;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProductServiceBase extends ServiceBase {

	private IProductDao productDao;
	private IArnevDao arnevDao;
	private CikkcsopDao cikkcsopDao;
	private ITapadogonDao tapadogonDao;
	private IKedvezmenyDao kedvezmenyDao;
	private IVevoarakDao vevoarakDao;
	private IArvaltDao arvaltDao;
	private IArutasitasDao arutasitasDao;

	public IProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public IArnevDao getArnevDao() {
		return arnevDao;
	}

	public void setArnevDao(IArnevDao arnevDao) {
		this.arnevDao = arnevDao;
	}

	// public abstract List<ProductVO> getProducts(Arnev arnev, String
	// itemNumber, String itemName, Integer csopkod);

	public CikkcsopDao getCikkcsopDao() {
		return cikkcsopDao;
	}

	public void setCikkcsopDao(CikkcsopDao cikkcsopDao) {
		this.cikkcsopDao = cikkcsopDao;
	}

	public ITapadogonDao getTapadogonDao() {
		return tapadogonDao;
	}

	public void setTapadogonDao(ITapadogonDao tapadogonDao) {
		this.tapadogonDao = tapadogonDao;
	}

	public IKedvezmenyDao getKedvezmenyDao() {
		return kedvezmenyDao;
	}

	public void setKedvezmenyDao(IKedvezmenyDao kedvezmenyDao) {
		this.kedvezmenyDao = kedvezmenyDao;
	}

	public IVevoarakDao getVevoarakDao() {
		return vevoarakDao;
	}

	public void setVevoarakDao(IVevoarakDao vevoarakDao) {
		this.vevoarakDao = vevoarakDao;
	}

	public IArvaltDao getArvaltDao() {
		return arvaltDao;
	}

	public void setArvaltDao(IArvaltDao arvaltDao) {
		this.arvaltDao = arvaltDao;
	}

	public IArutasitasDao getArutasitasDao() {
		return arutasitasDao;
	}

	public void setArutasitasDao(IArutasitasDao arutasitasDao) {
		this.arutasitasDao = arutasitasDao;
	}

	public abstract List<String> getProductAzon(String azonField);

	public abstract List<ProductVO> getProductsByAzon(String azonField, String azonValue, QueryParamsVO queryParamsVO);

	public abstract Opciok getOpciokName();

	public abstract List<Cikkcsop> getCikkcsopForProduct();

	public abstract List<Raktar> getActiveStorages();

	public abstract ProductVO getProductsById(int arukod, QueryParamsVO queryParamsVO);

	//public abstract List<ProductVO> getProductsNew(Arnev arnev, String itemNumber, String itemName, Integer csopkod);
	public abstract List<ProductVO> getProductsNew(String itemNumber, String itemName, Integer csopkod, QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getProductForAdvancedSearch(String cikkszam, QueryParamsVO queryParamsVO);

	public abstract List<ProductVO> getProductForSearch(String sku, String megnevezes, QueryParamsVO queryParamsVO);

	public abstract Arnev getArnevById(Integer id);

	public abstract Cikkcsop getCikkcsopById(BigDecimal kod);

	public abstract List<Object[]> getCikkcsopObjsByKod(BigDecimal kod);

	public abstract List<Object[]> getAllCikkcsopObjs();

	public abstract List<ProductVO> getProductsByCsopkods(List<BigDecimal> csopkods, QueryParamsVO queryParamsVO);

	public abstract Aru getAruById(Integer id);
	
	public abstract AruTemp getAruTempById(Integer id);	

	public abstract List<Tapadogon> getTapadogonByAkod(Integer akod);

	public abstract List<Kedvezmeny> getAktualisKedvezmeny();

	public abstract List<Vevoarak> getVevoarakByKedvezmenyForPartner(Integer id, Integer vevokod);
	
	public abstract List<Vevoarak> getActualVevoarakForPartner(Integer vevokod);

	//public abstract ProductVO convertProductToVO(Aru product);

	public abstract List<Arvalt> getArvaltByArutasitasForPartner(Integer arutasitras, Integer arnev);

	public abstract List<Arutasitas> getAktualisAkcio();

	public abstract List<ProductVO> getProductsByCikkcsopMegn(String megn, QueryParamsVO queryParamsVO);

	public abstract List<String> getProductsAzon3();

	public abstract List<ProductVO> getProductsTempByAzon(String azonName, String azonValue, QueryParamsVO queryParamsVO);
	
	public abstract List<ProductVO> getProductsTempByIDs(List<Integer> IDs, QueryParamsVO queryParamsVO);
	
	public abstract List<Integer> getProductTempIDsByTipus(String tipus);

	public abstract List<Integer> getProductTempIDsByTermekNev(String termeknev);

	public abstract List<Integer> getProductTempIDsByMeret(String meret);

	public abstract List<Integer> getProductTempIDsByBazis(String bazis);

	public abstract List<Integer> getProductTempIDsByKiszereles(String kiszereles);

	public abstract List<Integer> getProductTempIDsBySzincsalad(String szincsalad);

	public abstract List<Integer> getProductTempIDsByArnyalat(String arnyalat);	
	
	public abstract List<Integer> getProductTempIDsByTermekCsoport(String termekcsoport);

	public abstract Boolean isCikkcsopEmpty(Cikkcsop cikkcsop);

	public abstract Boolean isCikkcsopEmpty(BigDecimal cikkCsopKod);
	
	public abstract List<ProductVO> getAkciosProducts(QueryParamsVO queryParamsVO);
	
	public abstract ProductVO convertAruTempToProductVO(AruTemp product, QueryParamsVO queryParamsVO);
	
	public abstract ProductVO convertAruToProductVO(Aru product, QueryParamsVO queryParamsVO);

	public abstract Arnev getArnevFromPartner(Partner partner); 

}
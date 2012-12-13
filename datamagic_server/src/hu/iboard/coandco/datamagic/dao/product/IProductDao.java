package hu.iboard.coandco.datamagic.dao.product;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IProductDao {

	/**
	 * @param user
	 * @return
	 */

	public List<Aru> findProductByCriteria(DetachedCriteria criteria);

	public List<Aru> findProductByCriteria(DetachedCriteria criteria, int firstResult, int maxResult);

	public List<Aru> getProducts(Arnev arnev, String itemNumber, String itemName, Integer csopkod);

	public Aru getProductById(int arukod);

	public List<AruTemp> getProductsForAdvancedSearch(String cikkszam);

	public List<AruTemp> getProductsForSearch(String sku, String megn);
	
	public List<AruTemp> getProductsByCsopkods(List<BigDecimal> csopkods);
	
	public Aru getAruById(Integer id);
	
	public AruTemp getAruTempById(Integer id);
	
	public List<AruTemp> getAruTempsByIds(List<Integer> IDs);
	
	public List<AruTemp> getProductsByCikkcsopMegn(String megn);
	
	public List<String> getProductsAzon3();
	
	public List<AruTemp> getProductsByAzon(String azonName, String azonValue);
	
	public List<Integer> getProductTempIDsByTipus(String tipus);

	public List<Integer> getProductTempIDsByTermekNev(String termeknev);

	public List<Integer> getProductTempIDsByMeret(String meret);

	public List<Integer> getProductTempIDsByBazis(String bazis);

	public List<Integer> getProductTempIDsByKiszereles(String kiszereles);

	public List<Integer> getProductTempIDsBySzincsalad(String szincsalad);

	public List<Integer> getProductTempIDsByArnyalat(String arnyalat);
	
	public List<Integer> getProductTempIDsByTermekCsoport(String termekcsoport);
	
	public List<AruTemp> getAkciosProducts();

}

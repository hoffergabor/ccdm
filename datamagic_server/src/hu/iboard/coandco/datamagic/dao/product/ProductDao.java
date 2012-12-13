package hu.iboard.coandco.datamagic.dao.product;

import hu.iboard.coandco.datamagic.generated.Arnev;
import hu.iboard.coandco.datamagic.generated.Aru;
import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProductDao extends HibernateDaoSupport implements IProductDao {

	public ProductDao() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aru> findProductByCriteria(DetachedCriteria criteria) {
		List<Aru> product = null;
		product = getHibernateTemplate().findByCriteria(criteria);
		if (product.size() == 0) {
			return null;
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aru> findProductByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<Aru> product = null;
		product = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (product.size() == 0) {
			return null;
		}
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aru> getProducts(Arnev arnev, String itemNumber, String itemName, Integer csopkod) {
		List<Integer> list = new ArrayList<Integer>();
		//List<ProductVO> listVO = new ArrayList<ProductVO>();
		int i = 0;
		String[] names = new String[i + 1];
		Object[] values = new Object[i + 1];
		try {
			String query = "select a.arukod from Aru a where a.raktar!=0";
			if (!itemNumber.equals("")) {
				query = query + " and a.cikkszam like :itemNumber";
				names[i] = "itemNumber";
				values[i] = "%" + itemNumber + "%";
				i++;
			}
			if (!itemName.equals("")) {
				query = query + " and a.megn like :itemName";
				names[i] = "itemName";
				values[i] = "%" + itemName + "%";
				i++;

			}
			if (csopkod != 0) {
				query = query + " and a.csopkod=:csopkod";
				names[i] = "csopkod";
				values[i] = new Long(csopkod);
				i++;
			}

			query = query + " order by a.cikkszam asc";

			list = getHibernateTemplate().findByNamedParam(query, names, values);

			if (list == null) {
				return null;
			}
			DetachedCriteria criteria = DetachedCriteria.forClass(Aru.class).add(Restrictions.in("arukod", list));
			List<Aru> aruk = getHibernateTemplate().findByCriteria(criteria);
			return aruk;
//			for (Aru aru : aruk) {
//				ProductVO vo = new ProductVO();
//				vo = convertAruToProductVO(aru, arnev);
//				listVO.add(vo);
//			}

		} catch (Exception e) {
			logger.error("Error has occured by getting products!", e);
			return null;
		}
		//return listVO;
	}

	@Override
	public Aru getProductById(int arukod) {
		return (Aru) getHibernateTemplate().get(Aru.class, arukod);
		// return (AruTemp) getHibernateTemplate().get(AruTemp.class, arukod);
	}

//	private ProductVO convertAruToProductVO(Aru product, Arnev arnev) {
//
//		ProductVO vo = new ProductVO();
//
//		vo = convertProductToVO(product);
//
//		switch (arnev.getKod()) {
//		case 1:
//			vo.setEar(product.getEar());
//			break;
//		case 2:
//			vo.setEar(product.getEar2());
//			break;
//		case 3:
//			vo.setEar(product.getEar3());
//			break;
//		case 4:
//			vo.setEar(product.getEar4());
//			break;
//		case 5:
//			vo.setEar(product.getDear1());
//			break;
//		case 6:
//			vo.setEar(product.getDear2());
//			break;
//		case 7:
//			vo.setEar(product.getDear3());
//			break;
//		case 8:
//			vo.setEar(product.getDear4());
//			break;
//		case 9:
//			vo.setEar(product.getEar5());
//			break;
//		case 10:
//			vo.setEar(product.getEar6());
//			break;
//		case 11:
//			vo.setEar(product.getEar7());
//			break;
//		case 12:
//			vo.setEar(product.getEar8());
//			break;
//		case 13:
//			vo.setEar(product.getEar9());
//			break;
//		case 14:
//			vo.setEar(product.getEar10());
//			break;
//		case 15:
//			vo.setEar(product.getEar11());
//			break;
//		case 16:
//			vo.setEar(product.getEar12());
//			break;
//		case 17:
//			vo.setEar(product.getEar13());
//			break;
//		case 18:
//			vo.setEar(product.getEar14());
//			break;
//		case 19:
//			vo.setEar(product.getEar15());
//			break;
//		default:
//			vo.setEar(product.getEar());
//		}
//
//		vo.setNetto(vo.getEar());
//		vo.setBrutto(vo.getAfa().multiply(vo.getEar()));
//		vo.setDevnem(arnev.getDevizanev());
//
//		return vo;
//	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getProductsForAdvancedSearch(String cikkszam) {

		List<AruTemp> products = new ArrayList<AruTemp>();
		try {
			String query = "from AruTemp as aru where aru.cikkszam like '" + cikkszam + "'";
			products = getHibernateTemplate().find(query);
			if (products == null || products.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY ADVANCED SEARCH!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getProductsForSearch(String sku, String megn) {
		List<AruTemp> list = new ArrayList<AruTemp>();
		try {
			DetachedCriteria criteria = DetachedCriteria.forClass(AruTemp.class);
			if (sku != null && !sku.equals("")) {
				criteria.add(Restrictions.like("cikkszam", "%" + sku + "%"));
			}
			if (megn != null && !megn.equals("")) {
				criteria.add(Restrictions.like("megn", "%" + megn + "%"));
			}
			criteria.addOrder(Order.asc("cikkszam"));
			criteria.addOrder(Order.asc("megn"));
			list = getHibernateTemplate().findByCriteria(criteria);
			if (list == null || list.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			logger.error("Error has occured by getting products!", e);
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getProductsByCsopkods(List<BigDecimal> csopkods) {
		List<AruTemp> products = new ArrayList<AruTemp>();
		try {
			String query = "from AruTemp aru where aru.csopkod in(:csopkods) order by aru.megn asc";
			products = getHibernateTemplate().findByNamedParam(query, "csopkods", csopkods);
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY CSOPKODS", e);
		}
		return products;
	}

	@Override
	public Aru getAruById(Integer id) {
		return (Aru) getHibernateTemplate().get(Aru.class, id);
	}

	@Override
	public AruTemp getAruTempById(Integer id) {
		return (AruTemp) getHibernateTemplate().get(AruTemp.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getAruTempsByIds(List<Integer> IDs) {
		List<AruTemp> products = new ArrayList<AruTemp>();
		try {
			String query = "from AruTemp aru where aru.arukod in(:ids) order by aru.megn asc";
			products = getHibernateTemplate().findByNamedParam(query, "ids", IDs);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY IDS", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getProductsByCikkcsopMegn(String megn) {
		List<AruTemp> products = null;
		try {
			products = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("GET_ARU_TEMP_BY_CIKKCSOP_MEGN").setParameter("megnevezes", megn)
					.list();
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY CIKKCSOP MEGN SEARCH!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getProductsAzon3() {
		List<String> names = new ArrayList<String>();
		try {
			String query = "select distinct aru.azon3 from AruTemp aru where aru.azon3!='' order by aru.azon3 asc";
			names = getHibernateTemplate().find(query);
			if (names == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT AZON3", e);
		}
		return names;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getProductsByAzon(String azonName, String azonValue) {
		List<AruTemp> products = new ArrayList<AruTemp>();
		try {
			String query = "from AruTemp aru where aru." + azonName + "=:azonValue order by aru.megn asc";
			products = getHibernateTemplate().findByNamedParam(query, "azonValue", azonValue);
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY AZON NAME AND VALUE", e);
		}
		return products;
	}

	// azon3
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByTipus(String tipus) {

		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon3 =:Value";
			getHibernateTemplate().setMaxResults(Integer.MAX_VALUE);
			getHibernateTemplate().setFetchSize(Integer.MAX_VALUE);
			result = getHibernateTemplate().findByNamedParam(query, "Value", tipus);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY TIPUS (AZON3)", e);
		}
		return result;
	}

	// azon5
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByTermekNev(String termeknev) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon5 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", termeknev);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY TERMEKNEV (AZON5)", e);
		}
		return result;
	}

	// azon8
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByMeret(String meret) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon8 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", meret);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY MERET (AZON8)", e);
		}
		return result;
	}

	// azon9
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByBazis(String bazis) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon9 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", bazis);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY BAZIS (AZON9)", e);
		}
		return result;
	}

	// azon10
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByKiszereles(String kiszereles) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon10 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", kiszereles);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY KISZERELES (AZON10)", e);
		}
		return result;
	}

	// azon11
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsBySzincsalad(String szincsalad) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon11 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", szincsalad);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY SZINCSALAD (AZON11)", e);
		}
		return result;
	}

	// azon12
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByArnyalat(String arnyalat) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon12 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", arnyalat);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY ARNYALAT (AZON12)", e);
		}
		return result;
	}

	// azon4
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getProductTempIDsByTermekCsoport(String termekcsoport) {
		List<Integer> result = new ArrayList<Integer>();
		try {
			String query = "select arukod from AruTemp aru where aru.azon4 =:Value";
			result = getHibernateTemplate().findByNamedParam(query, "Value", termekcsoport);
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCT IDS BY TERMEKCSOPORT (AZON12)", e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruTemp> getAkciosProducts() {
		List<AruTemp> result = new ArrayList<AruTemp>();
		try {
			String query = "from AruTemp aru where aru.akciosTerm=true";
			result = getHibernateTemplate().find(query);
		} catch (Exception e) {
			logger.error("ERROR BY AKCIOS PRODUCTS", e);
		}
		return result;
	}

	
}

package hu.iboard.coandco.datamagic.dao.product;

import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class ProductDao extends HibernateDaoSupport implements IProductDao {

	@Override
	public Aru getProductById(Integer arukod) {
		return (Aru) getHibernateTemplate().get(Aru.class, arukod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getProductByName(String name) {
		List<AruKisker> products = new ArrayList<AruKisker>();
		try {
			products = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("GET_ARU_BY_LIKE_NAME")
					.setParameter("megnevezes", "%" + name + "%").list();
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY NAME SEARCH!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getOptProductByName(String name) {
		List<String> products = null;
		try {
			// String query =
			// "select aru.arukod,aru.megn from AruKisker aru where aru.megn like :name order by aru.megn asc";
			// products = getHibernateTemplate().findByNamedParam(query, "name",
			// "%" + name + "%");

			products = getHibernateTemplate().getSessionFactory().openSession()
					.createSQLQuery("SELECT megn from AruKisker WHERE binary megn like '%" + name + "%' order by megn ASC").list();
			// .setParameter("megnevezes", "%" + name + "%").list();
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING OPT PRODUCTS BY NAME SEARCH!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getProductsByCsopkod(BigDecimal csopkod) {
		List<AruKisker> products = new ArrayList<AruKisker>();
		try {
			String query = "from AruKisker aru where aru.csopkod=:csopkod order by aru.megn asc";
			products = getHibernateTemplate().findByNamedParam(query, "csopkod", csopkod);
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING OPT PRODUCTS BY CSOPKOD!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getProductsByCsopkods(List<BigDecimal> csopkods) {
		List<AruKisker> products = new ArrayList<AruKisker>();
		try {
			String query = "from AruKisker aru where aru.csopkod in(:csopkods) order by aru.megn asc";
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
	public AruKisker getProductKiskerById(Integer arukod) {
		return (AruKisker) getHibernateTemplate().get(AruKisker.class, arukod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getProductByNameNew(String name) {
		List<AruKisker> products = null;
		try {

			String query = "from AruKisker aru where aru.megn=:name order by aru.megn asc";
			products = getHibernateTemplate().findByNamedParam(query, "name", name);
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY NAME SEARCH!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getKiemeltProducts() {
		List<AruKisker> products = null;
		try {

			String query = "from AruKisker aru where aru.kiemelt=true order by aru.megn asc";
			products = getHibernateTemplate().find(query);
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING KIEMELT PRODUCTS!", e);
		}
		return products;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AruKisker> getProductsByCikkcsopMegn(String megn) {
		List<AruKisker> products = null;
		try {
			products = getHibernateTemplate().getSessionFactory().openSession().getNamedQuery("GET_ARU_BY_CIKKCSOP_MEGN")
					.setParameter("megnevezes", megn).list();
			if (products == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY CIKKCSOP MEGN SEARCH!", e);
		}
		return products;
	}
	
//	@Override
//	public ProductVO convertProductToVO(Aru product) {
//		ProductVO vo = new ProductVO();
//
//		vo.setArukod(product.getArukod());
//		vo.setAfa(product.getAfakodok().getSzorzo());
//		vo.setCikkszam(product.getCikkszam());
//		vo.setMegn(product.getMegn());
//		vo.setMennyiseg(new BigDecimal(1));
//		vo.setAzon3(product.getAzon3());
//		vo.setAzon4(product.getAzon4());
//		vo.setAzon5(product.getAzon5());
//		vo.setAzon6(product.getAzon6());
//		vo.setAzon7(product.getAzon7());
//		vo.setAzon8(product.getAzon8());
//		vo.setAzon9(product.getAzon9());
//		vo.setAzon10(product.getAzon10());
//		vo.setAzon11(product.getAzon11());
//		vo.setAzon12(product.getAzon12());
//		vo.setMenny(product.getMenny());
//		vo.setMeegys(product.getMeegys());
//		vo.setMegn3(product.getMegn3());
//		vo.setMegn(product.getMegn());
//		vo.setMegn3(product.getMegn3());
//		vo.setRaktar(product.getRaktar());
//		
//		
//		
//
//		return vo;
//	}	

	public Boolean IsCikkcsopEmpty(BigDecimal csopkod){
		return IsCikkcsopEmpty(csopkod, false);
	}
	
	@SuppressWarnings("unchecked")
	private Boolean IsCikkcsopEmpty(BigDecimal csopkod, Boolean calledbyitself){
		//megnézem, hogy a cikkcsoporthoz tartoznak-e termékek. ha nem, akkor:
		//egy listába kinyerem a cikkcsoport alá tartozó id-kat. aztán megnézem, hogy az id-khoz tartoznak-e termékek
		//ha nem, akkor végigmegyek a listán és mindegyikre meghivom az IsCikkcsopEmpty függvényt rekurzivan. De ha egyre is true-t ad vissza, akkor kilépek a ciklusból.
		//ha ezek után sincs eredmény, akkor a cikkcsoport üres.
		try {
//			String query = "from AruKisker aru where aru.csopkod in(:csopkods) order by aru.megn asc";
//			products = getHibernateTemplate().findByNamedParam(query, "csopkods", csopkods);
			//String query = "select count(aru.arukod) from AruKisker aru where aru.csopkod = :csopkod";
			
			List<Integer> recordIDs = new ArrayList<Integer>();
			String query = "";
			if (!calledbyitself){			
				query = "select aru.arukod from AruKisker aru where aru.csopkod = :csopkod";
				 //lehet, hogy ez az első ellenőrzés csak a fában a legfőbb ágnál kéne, az összes többinél nem, mert rekurz v meg van h vva már egy ilen ellenőrzés a függvény végénél
				recordIDs = getHibernateTemplate().findByNamedParam(query, "csopkod", csopkod);
				if (recordIDs.size() > 0)
					return false;
			}

			query = "select kod from Cikkcsop c where c.gyujto = :gyujto_kod";
			List<BigDecimal> alcsoportok = new ArrayList<BigDecimal>();
			alcsoportok = getHibernateTemplate().findByNamedParam(query, "gyujto_kod", csopkod);

			//ha nincsenek alcsoportjai, akkor üres a cucc.
			if (alcsoportok.size() < 1)
				return true;
			
			query = "select aru.arukod from AruKisker aru where aru.csopkod in :csopkods )";
			recordIDs = getHibernateTemplate().findByNamedParam(query, "csopkods", alcsoportok);
			
			if (recordIDs.size() > 0)
				return false;
			
			for (BigDecimal kod : alcsoportok) {
				if (IsCikkcsopEmpty(kod))
					return true;
			}
			return true;

		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY CSOPKODS", e);
			return true;
		}
	}

}

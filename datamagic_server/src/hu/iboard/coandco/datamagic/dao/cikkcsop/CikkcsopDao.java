package hu.iboard.coandco.datamagic.dao.cikkcsop;

import hu.iboard.coandco.datamagic.generated.Cikkcsop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CikkcsopDao extends HibernateDaoSupport implements ICikkcsopDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getCikkcsopObjsByKod(BigDecimal kod) {
		List<Object[]> objects = new ArrayList<Object[]>();
		try {
			String query = "select c.kod,c.megn,c.gyujto from Cikkcsop c where c.gyujto=:kod order by c.megn asc";
			objects = getHibernateTemplate().findByNamedParam(query, "kod", kod);
			if (objects == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING CSIKKCSOPS BY KOD!", e);
		}
		return objects;
	}

	@Override
	public Cikkcsop getCikkcsopById(BigDecimal kod) {
		return (Cikkcsop) getHibernateTemplate().get(Cikkcsop.class, kod);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllCikkcsopObjs() {
		List<Object[]> objects = new ArrayList<Object[]>();
		try {
			String query = "select c.kod,c.megn,c.gyujto,c.tovazkod from Cikkcsop c order by c.tovazkod asc";
			objects = getHibernateTemplate().find(query);
			if (objects == null) {
				return null;
			}
		} catch (Exception e) {
			logger.error("ERROR BY GETTING ALL CSIKKCSOPS!", e);
		}
		return objects;
	}

	public Boolean isCikkcsopEmpty(Cikkcsop cikkcsop){
		return isCikkcsopEmpty(cikkcsop.getKod());
	}
	
	public Boolean isCikkcsopEmpty(BigDecimal csopkod){
		return isCikkcsopEmpty(csopkod, false);
	}
	
	@SuppressWarnings("unchecked")
	private Boolean isCikkcsopEmpty(BigDecimal csopkod, Boolean calledbyitself){
		//megnézem, hogy a cikkcsoporthoz tartoznak-e termékek. ha nem, akkor:
		//egy listába kinyerem a cikkcsoport alá tartozó id-kat. aztán megnézem, hogy az id-khoz tartoznak-e termékek
		//ha nem, akkor végigmegyek a listán és mindegyikre meghivom az IsCikkcsopEmpty függvényt rekurzivan. De ha egyre is false-t ad vissza, akkor kilépek a ciklusból.
		//ha ezek után sincs eredmény, akkor a cikkcsoport üres.
		try {

			List<Integer> recordIDs = new ArrayList<Integer>();
			String query = "";
			if (!calledbyitself){			
				query = "select aru.arukod from AruTemp aru where aru.csopkod = :csopkod";
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
			
			query = "select aru.arukod from AruTemp aru where aru.csopkod in (:csopkods) )";
			recordIDs = getHibernateTemplate().findByNamedParam(query, "csopkods", alcsoportok);
			
			if (recordIDs.size() > 0)
				return false;
			
			for (BigDecimal kod : alcsoportok) {
				if (!isCikkcsopEmpty(kod, true))
					return false;
			}
			return true;

		} catch (Exception e) {
			logger.error("ERROR BY GETTING PRODUCTS BY CSOPKODS", e);
			return true;
		}
	}
	
	
}

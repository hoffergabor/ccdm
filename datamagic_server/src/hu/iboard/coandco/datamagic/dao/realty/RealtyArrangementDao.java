package hu.iboard.coandco.datamagic.dao.realty;

import hu.iboard.coandco.datamagic.generated.Aconnect;
import hu.iboard.coandco.datamagic.generated.IntMunkEl;
import hu.iboard.coandco.datamagic.generated.IntezAru;
import hu.iboard.coandco.datamagic.generated.Intezkedes;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class RealtyArrangementDao extends HibernateDaoSupport implements IRealtyArrangementDao {

	public RealtyArrangementDao() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intezkedes> findRealtyArrangementByCriteria(DetachedCriteria criteria) {
		List<Intezkedes> realty = null;
		realty = getHibernateTemplate().findByCriteria(criteria);
		if (realty.size() == 0) {
			return null;
		}
		return realty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Intezkedes> findRealtyArrangementByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<Intezkedes> realty = null;
		realty = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (realty.size() == 0) {
			return null;
		}
		return realty;
	}

	@Override
	public RealtyArrangementVO convertRealtyArrangementToVO(Intezkedes intezkedes) {

		RealtyArrangementVO realtyVO = new RealtyArrangementVO();

		realtyVO.setAlapkezelo(intezkedes.isAlapkezelo());
		realtyVO.setBefejezes(intezkedes.getBefejezes());
		realtyVO.setBejelentes(intezkedes.getBejelentes());
		realtyVO.setBejelento(intezkedes.getBejelento());
		realtyVO.setDatum(intezkedes.getDatum());
		realtyVO.setElszamolt(intezkedes.isElszamolt());
		realtyVO.setEvad(intezkedes.getEvad());
		realtyVO.setHatarido(intezkedes.getHatarido());
		realtyVO.setId(intezkedes.getId());
		realtyVO.setJovahagy(intezkedes.isJovahagy());
		realtyVO.setLetrehozva(intezkedes.getLetrehozva());
		realtyVO.setLezart(intezkedes.isLezart());
		realtyVO.setMegbizott(intezkedes.isMegbizott());
		realtyVO.setMegj2(intezkedes.getMegj2());
		realtyVO.setMegj3(intezkedes.getMegj3());
		realtyVO.setMegj4(intezkedes.getMegj4());
		realtyVO.setMegjegyzes(intezkedes.getMegjegyzes());
		realtyVO.setMegn(intezkedes.getMegn());
		realtyVO.setMinosites(intezkedes.getMinosites());
		realtyVO.setSorszam(intezkedes.getSorszam());
		realtyVO.setSurgos(intezkedes.isSurgos());
		realtyVO.setSzerzSzam(intezkedes.getSzerzSzam());
		realtyVO.setUjIntezk(intezkedes.isUjIntezk());
		realtyVO.setVegrehajt(intezkedes.isVegrehajt());
		realtyVO.setNettoAr(Long.valueOf(intezkedes.getNettoAr()));
		return realtyVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Aconnect> findAttachsForRealtyArrangement(DetachedCriteria criteria) {
		List<Aconnect> attachs = null;
		attachs = getHibernateTemplate().findByCriteria(criteria);
		if (attachs.size() == 0) {
			return null;
		}
		return attachs;
	}

	@Override
	public Intezkedes saveOrUpdateRealtyArrangement(Intezkedes intezkedes) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(intezkedes);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return intezkedes;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInitRealtyArrangement(UserVO user, Integer vevokod, List<Integer> lakasIds) {

		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			if (user != null) {
				String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
				query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
				query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
				query = query + "p.munkszam,p.irsz,p.varos,p.cim,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from Intezkedes as i";
				query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";
				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					query = query + " where i.tulajdonos=" + vevokod;
				}

				if (user.getUserType().equals(UserVO.RENTER)) {
					query = query + " where i.lakas in(";
					int size = lakasIds.size();
					int i = 1;
					for (Integer lakasId : lakasIds) {
						if (i < size) {
							query = query + lakasId + ",";
							i++;
						} else
							query = query + lakasId;
					}
					query = query + ")";
				}
				query = query + " order by i.letrehozva desc limit 10";
				realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
						.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
						.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
						.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
						.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
						.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
						.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
						.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
						.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
						.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
						.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
						.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
						.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

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
	public RealtyArrangementVO convertRealtyArrangementToVONew(Object[] object) {
		RealtyArrangementVO realtyVO = new RealtyArrangementVO();

		realtyVO.setAlapkezelo((Boolean) object[0]);
		realtyVO.setBefejezes((Date) object[1]);
		realtyVO.setBejelentes((Date) object[2]);
		realtyVO.setBejelento((String) object[3]);
		realtyVO.setDatum((Date) object[4]);
		realtyVO.setElszamolt((Boolean) object[5]);
		realtyVO.setEvad((Integer) object[6]);
		realtyVO.setHatarido((Date) object[7]);
		realtyVO.setId((Integer) object[8]);
		realtyVO.setJovahagy((Boolean) object[9]);
		realtyVO.setLetrehozva((Date) object[10]);
		realtyVO.setLezart((Boolean) object[11]);
		realtyVO.setMegbizott((Boolean) object[12]);
		String megj2 = (String) object[13];
		realtyVO.setMegj2(replaceChar(megj2));
		String megj3 = (String) object[14];
		realtyVO.setMegj3(replaceChar(megj3));
		String megj4 = (String) object[15];
		realtyVO.setMegj4(replaceChar(megj4));
		String megjegyzes = (String) object[16];
		realtyVO.setMegjegyzes(replaceChar(megjegyzes));
		String megn = (String) object[17];
		realtyVO.setMegn(megn);
		realtyVO.setMinosites((Integer) object[18]);
		realtyVO.setSorszam((Integer) object[19]);
		realtyVO.setSurgos((Boolean) object[20]);
		realtyVO.setSzerzSzam((String) object[21]);
		realtyVO.setUjIntezk((Boolean) object[22]);
		realtyVO.setVegrehajt((Boolean) object[23]);
		realtyVO.setObjectaddress("[" + object[25] + "] " + object[26] + " " + object[27] + ", " + object[28]);
		realtyVO.setProjectId((Integer) object[24]);
		realtyVO.setFlataddress("[" + object[29] + "] " + object[30] + " " + object[31] + ", " + object[32] + " " + object[33]);
		realtyVO.setItemNumber(object[6] + "/" + object[19]);
		realtyVO.setAruExtId((Integer) object[34]);
		// BigDecimal one = new BigDecimal(1);
		// BigDecimal menny = (BigDecimal) object[33];
		// BigDecimal earForint = (BigDecimal) object[34];
		// BigDecimal szolgaltat = (BigDecimal) object[35];
		// BigDecimal bonyolit = (BigDecimal) object[36];
		// Long netto =
		// Math.round(menny.multiply(earForint.multiply(one.add(szolgaltat.add(bonyolit)))).doubleValue());
		realtyVO.setNettoAr(new Long(0));
		return realtyVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementNew(UserVO user, Integer vevokod, Date from, Date to, String dateType, List<Integer> lakasIds) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			if (user != null) {
				String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
				query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
				query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
				query = query + "p.munkszam,p.irsz,p.varos,p.cim,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
				query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";

				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					query = query + " where i.tulajdonos=" + vevokod;
				}
				if (user.getUserType().equals(UserVO.RENTER)) {
					query = query + " where i.lakas in(";
					int size = lakasIds.size();
					int i = 1;
					for (Integer lakasId : lakasIds) {
						if (i < size) {
							query = query + lakasId + ",";
							i++;
						} else
							query = query + lakasId;
					}
					query = query + ")";
				}
				if (dateType != null && from != null && to != null)
					query = query + " and i." + dateType + ">='" + from + "' and i." + dateType + "<='" + to + "'";
				query = query + " order by i.letrehozva desc limit 0,500";
				realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
						.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
						.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
						.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
						.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
						.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
						.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
						.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
						.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
						.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
						.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
						.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
						.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementByProjectIdNew(UserVO user, Integer vevokod, Integer projectId, List<Integer> lakasIds) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			if (user != null) {
				String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
				query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
				query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
				query = query + "p.munkszam,p.irsz,p.varos,p.cim,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
				query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";

				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					query = query + " where i.tulajdonos=" + vevokod;
				}
				if (user.getUserType().equals(UserVO.RENTER)) {
					query = query + " where i.lakas in(";
					int size = lakasIds.size();
					int i = 1;
					for (Integer lakasId : lakasIds) {
						if (i < size) {
							query = query + lakasId + ",";
							i++;
						} else
							query = query + lakasId;
					}
					query = query + ")";
				}
				query = query + " and i.project=" + projectId;
				query = query + " order by i.letrehozva desc";
				realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
						.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
						.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
						.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
						.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
						.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
						.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
						.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
						.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
						.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
						.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
						.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
						.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

			} else {
				// log the error -> bad request
				throw new Exception("bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement by projectId: " + e.getMessage());
			return null;
		}
		return realty;
	}

	private String replaceChar(String s) {

		StringBuffer st = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '[' || s.charAt(i) == ']') {
				st.append(' ');
			} else {
				st.append(s.charAt(i));
			}
		}
		return st.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllOwnersFromRealtyArrangementByName(String name) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select p.vevokod,p.nev from Partner as p where p.vevokod in(select distinct i.tulajdonos from Intezkedes as i)";
			query = query + " and p.nev like:name";
			query = query + " order by p.nev asc";
			realty = getHibernateTemplate().findByNamedParam(query, "name", "%" + name + "%");
			if (realty == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting owner from realty arrangement: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAllOwnersFromRealtyArrangement() {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select p.vevokod,p.nev from Partner as p where p.vevokod in(select distinct i.tulajdonos from Intezkedes as i)";
			query = query + " order by p.nev asc";
			realty = getHibernateTemplate().find(query);
			if (realty == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting owner from realty arrangement: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementByTulajdonos(Integer partnerId) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
			query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
			query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
			query = query + "p.munkszam,p.irsz,p.varos,p.cim,p.id,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
			query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";
			query = query + " and i.tulajdonos=" + partnerId;
			query = query + " order by i.letrehozva desc";
			realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
					.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
					.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
					.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
					.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
					.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
					.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
					.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
					.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
					.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
					.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
					.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
					.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement by projectId: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementByAruExtIds(List<Integer> aruExtIds) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
			query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
			query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
			query = query + "p.munkszam,p.irsz,p.varos,p.cim,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
			query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";

			query = query + " and i.lakas in(";
			int size = aruExtIds.size();
			int i = 1;
			for (Integer lakasId : aruExtIds) {
				if (i < size) {
					query = query + lakasId + ",";
					i++;
				} else
					query = query + lakasId;
			}
			query = query + ")";
			query = query + " order by i.letrehozva desc";
			realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
					.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
					.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
					.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
					.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
					.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
					.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
					.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
					.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
					.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
					.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
					.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
					.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement by projectId: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementByLakasok(List<Integer> lakasok) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
			query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
			query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
			query = query + "p.munkszam,p.irsz,p.varos,p.cim,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
			query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";
			query = query + " and i.lakas in(";
			int size = lakasok.size();
			int i = 1;
			for (Integer lakasId : lakasok) {
				if (i < size) {
					query = query + lakasId + ",";
					i++;
				} else
					query = query + lakasId;
			}
			query = query + ")";
			query = query + " order by i.letrehozva desc";
			realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
					.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
					.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
					.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
					.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
					.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
					.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
					.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
					.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
					.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
					.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
					.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
					.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement: " + e.getMessage());
			return null;
		}
		return realty;
	}

	@Override
	public Intezkedes getRealtyArrangementById(Integer id) {
		return (Intezkedes) getHibernateTemplate().get(Intezkedes.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IntMunkEl> getIntMunkaElByIntezkedesId(Integer id) {
		List<IntMunkEl> intMunkEl = new ArrayList<IntMunkEl>();
		try {
			String query = "from IntMunkEl ime where ime.intezkedes=:id";
			intMunkEl = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (intMunkEl == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting IntMunkEl: " + e.getMessage());
			return null;
		}
		return intMunkEl;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IntezAru> getIntezAruByIntezkedesId(Integer id) {
		List<IntezAru> intAru = new ArrayList<IntezAru>();
		try {
			String query = "from IntezAru ia where ia.intezkedes=:id";
			intAru = getHibernateTemplate().findByNamedParam(query, "id", id);
			if (intAru == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting IntezAru: " + e.getMessage());
			return null;
		}
		return intAru;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllBejelento() {
		List<String> realtys = new ArrayList<String>();
		try {
			String query = "select distinct i.bejelento from Intezkedes i order by i.bejelento asc";
			realtys = getHibernateTemplate().find(query);
			if (realtys == null)
				return null;
		} catch (Exception e) {
			logger.error("error getting all bejelento form intezkedes: " + e.getMessage());
			return null;
		}
		return realtys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getRealtyArrangementByBejelento(String bejelento) {
		List<Object[]> realty = new ArrayList<Object[]>();
		try {
			String query = "select i.alapkezelo,i.befejezes,i.bejelentes,i.bejelento,i.datum,i.elszamolt,";
			query = query + "i.evad,i.hatarido,i.id,i.jovahagy,i.letrehozva,i.lezart,i.megbizott,i.megj2,i.megj3,i.megj4,";
			query = query + "i.megjegyzes,i.megn,i.minosites,i.sorszam,i.surgos,i.szerzSzam,i.ujIntezk,i.vegrehajt,i.tulajdonos,";
			query = query + "p.munkszam,p.irsz,p.varos,p.cim,p.id,a.nev,a.irsz,a.varos,a.cim,a.cim2,a.arukod from INTEZKEDES as i";
			query = query + " join PROJECT as p on p.id=i.project join ARUEXT as a on i.lakas=a.arukod";
			query = query + " and i.bejelento='" + bejelento + "'";
			query = query + " order by i.letrehozva desc";
			realty = (List<Object[]>) getHibernateTemplate().getSessionFactory().openSession().createSQLQuery(query)
					.addScalar("i.alapkezelo", Hibernate.BOOLEAN).addScalar("i.befejezes", Hibernate.DATE).addScalar("i.bejelentes", Hibernate.DATE)
					.addScalar("i.bejelento", Hibernate.STRING).addScalar("i.datum", Hibernate.DATE).addScalar("i.elszamolt", Hibernate.BOOLEAN)
					.addScalar("i.evad", Hibernate.INTEGER).addScalar("i.hatarido", Hibernate.DATE).addScalar("i.id", Hibernate.INTEGER)
					.addScalar("i.jovahagy", Hibernate.BOOLEAN).addScalar("i.letrehozva", Hibernate.DATE).addScalar("i.lezart", Hibernate.BOOLEAN)
					.addScalar("i.megbizott", Hibernate.BOOLEAN).addScalar("i.megj2", Hibernate.STRING).addScalar("i.megj3", Hibernate.STRING)
					.addScalar("i.megj4", Hibernate.STRING).addScalar("i.megjegyzes", Hibernate.STRING).addScalar("i.megn", Hibernate.STRING)
					.addScalar("i.minosites", Hibernate.INTEGER).addScalar("i.sorszam", Hibernate.INTEGER).addScalar("i.surgos", Hibernate.BOOLEAN)
					.addScalar("i.szerzSzam", Hibernate.STRING).addScalar("i.ujIntezk", Hibernate.BOOLEAN).addScalar("i.vegrehajt", Hibernate.BOOLEAN)
					.addScalar("i.tulajdonos", Hibernate.INTEGER).addScalar("p.munkszam", Hibernate.STRING).addScalar("p.irsz", Hibernate.STRING)
					.addScalar("p.varos", Hibernate.STRING).addScalar("p.cim", Hibernate.STRING).addScalar("a.nev", Hibernate.STRING)
					.addScalar("a.irsz", Hibernate.STRING).addScalar("a.varos", Hibernate.STRING).addScalar("a.cim", Hibernate.STRING)
					.addScalar("a.cim2", Hibernate.STRING).addScalar("a.arukod", Hibernate.INTEGER).list();

		} catch (Exception e) {
			logger.error("error occured at processing ListRealtyArrangement by projectId: " + e.getMessage());
			return null;
		}
		return realty;
	}
}

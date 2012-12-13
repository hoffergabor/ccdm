/**
 * 
 */
package hu.iboard.coandco.datamagic.service.invoice;

import hu.iboard.coandco.datamagic.generated.Kszamla;
import hu.iboard.coandco.datamagic.generated.Ktetel;
import hu.iboard.coandco.datamagic.generated.Szamla;
import hu.iboard.coandco.datamagic.generated.Tetel;
import hu.iboard.coandco.datamagic.util.DataMagicConstants;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class InvoiceService extends InvoiceServiceBase {

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceVO> getInvoicesIn(Integer vevokod, Date from, Date to) {

		List<Szamla> list = new ArrayList<Szamla>();
		List<InvoiceVO> invoiceInList = new ArrayList<InvoiceVO>();
		try {
			if (vevokod != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(Szamla.class).add(Restrictions.ge("kelt", from)).add(Restrictions.le("kelt", to))
						.add(Restrictions.eq("vevokod", vevokod)).setFetchMode("tetelSet", FetchMode.SELECT);
				list = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			} else {
				DetachedCriteria criteria = DetachedCriteria.forClass(Szamla.class).add(Restrictions.ge("kelt", from)).add(Restrictions.le("kelt", to))
						.setFetchMode("tetelSet", FetchMode.SELECT);
				list = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			}
			if (list == null) {
				return null;
			}
			for (Szamla szamla : list) {
				invoiceInList.add(getInvoiceDao().convertInvoiceInToInvoiceVO(szamla));
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListInvoiceIn: " + e.getMessage());
		}
		return invoiceInList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceVO> getInvoicesOut(Integer vevokod, Date from, Date to) {

		List<Kszamla> list = new ArrayList<Kszamla>();
		List<InvoiceVO> invoiceOutList = new ArrayList<InvoiceVO>();
		try {
			if (vevokod != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).add(Restrictions.ge("kelt", from)).add(Restrictions.le("kelt", to))
						.add(Restrictions.eq("vevokod", vevokod)).setFetchMode("ktetelSet", FetchMode.SELECT);
				list = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			} else {
				DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).add(Restrictions.ge("kelt", from)).add(Restrictions.le("kelt", to))
						.setFetchMode("ktetelSet", FetchMode.SELECT);
				list = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			}
			if (list == null) {
				return null;
			}
			for (Kszamla Kszamla : list) {
				invoiceOutList.add(getInvoiceDao().convertInvoiceOutToInvoiceVO(Kszamla));
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListInvoiceOut: " + e.getMessage());
			return null;
		}
		return invoiceOutList;
	}

	@Override
	public List<InvoiceVO> getInitInvoices(Integer vevokod, Boolean invoiceIn, Boolean invoiceOut) {
		List<Integer> vevokods = new LinkedList<Integer>();
		if (vevokod != null)
			vevokods.add(vevokod);
		return getInitInvoicesByAllPartners(vevokods, invoiceIn, invoiceOut);
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<InvoiceVO> getInitInvoicesByAllPartners(List<Integer> vevokods, Boolean invoiceIn, Boolean invoiceOut) {
		List<InvoiceVO> invoiceList = new ArrayList();
		List<Kszamla> invoiceOutList = new LinkedList<Kszamla>();
		List<Szamla> invoiceInList = new LinkedList<Szamla>();

		try {
			if (vevokods.size() > 0) {
				if (invoiceOut) {
					DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).add(Restrictions.in("vevokod", vevokods))
							.setFetchMode("ktetelSet", FetchMode.SELECT).addOrder(Order.desc("kelt"));

					invoiceOutList = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria, 0, 12);
				}
				if (invoiceIn) {
					DetachedCriteria criteria2 = DetachedCriteria.forClass(Szamla.class).add(Restrictions.in("vevokod", vevokods))
							.setFetchMode("tetelSet", FetchMode.SELECT).addOrder(Order.desc("kelt"));
					invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria2, 0, 12);
				}
			} else {
				if (invoiceOut) {
					DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).setFetchMode("ktetelSet", FetchMode.SELECT)
							.addOrder(Order.desc("kelt"));

					invoiceOutList = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria, 0, 12);
				}
				if (invoiceIn) {
					DetachedCriteria criteria2 = DetachedCriteria.forClass(Szamla.class).setFetchMode("tetelSet", FetchMode.SELECT)
							.addOrder(Order.desc("kelt"));
					invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria2, 0, 12);
				}
			}

			if (invoiceInList != null) {
				for (Szamla szamla : invoiceInList) {
					invoiceList.add(getInvoiceDao().convertInvoiceInToInvoiceVO(szamla));
				}
			}
			if (invoiceOutList != null) {
				for (Kszamla kszamla : invoiceOutList) {
					invoiceList.add(getInvoiceDao().convertInvoiceOutToInvoiceVO(kszamla));
				}
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListInvoiceOut: " + e.getMessage());
			return null;
		}
		return invoiceList;
	}

	@Override
	public List<InvoiceVO> getInvoices(Integer vevokod, Date from, Date to, Boolean invoiceIn, Boolean invoiceOut) {
		List<Integer> vevokods = new LinkedList<Integer>();
		if (vevokod != null)
			vevokods.add(vevokod);

		return getInvoicesByPartners(vevokods, from, to, invoiceIn, invoiceOut);
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<InvoiceVO> getInvoicesByPartners(List<Integer> vevokods, Date from, Date to, Boolean invoiceIn, Boolean invoiceOut) {

		List<InvoiceVO> invoiceList = new ArrayList<InvoiceVO>();
		List<Kszamla> invoiceOutList = new LinkedList<Kszamla>();
		List<Szamla> invoiceInList = new LinkedList<Szamla>();

		try {
			if (vevokods.size() > 0) {
				if (invoiceOut) {
					DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).add(Restrictions.in("vevokod", vevokods)).addOrder(Order.desc("kelt"));

					if (from != null)
						criteria.add(Restrictions.ge("kelt", from));
					if (to != null)
						criteria.add(Restrictions.le("kelt", to));
					invoiceOutList = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
				}
				if (invoiceIn) {
					DetachedCriteria criteria2 = DetachedCriteria.forClass(Szamla.class).add(Restrictions.in("vevokod", vevokods)).addOrder(Order.desc("kelt"));
					if (from != null)
						criteria2.add(Restrictions.ge("kelt", from));
					if (to != null)
						criteria2.add(Restrictions.le("kelt", to));

					invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria2);
				}
			} else {
				if (invoiceOut) {
					DetachedCriteria criteria = DetachedCriteria.forClass(Kszamla.class).addOrder(Order.desc("kelt"));
					if (from != null)
						criteria.add(Restrictions.ge("kelt", from));
					if (to != null)
						criteria.add(Restrictions.le("kelt", to));

					invoiceOutList = (List<Kszamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
				}
				if (invoiceIn) {
					DetachedCriteria criteria2 = DetachedCriteria.forClass(Szamla.class).addOrder(Order.desc("kelt"));
					if (from != null)
						criteria2.add(Restrictions.ge("kelt", from));
					if (to != null)
						criteria2.add(Restrictions.le("kelt", to));
					invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria2);
				}
			}

			if (invoiceInList != null) {
				for (Szamla szamla : invoiceInList) {
					invoiceList.add(getInvoiceDao().convertInvoiceInToInvoiceVO(szamla));
				}
			}
			if (invoiceOutList != null) {
				for (Kszamla kszamla : invoiceOutList) {
					invoiceList.add(getInvoiceDao().convertInvoiceOutToInvoiceVO(kszamla));
				}
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListInvoiceOut: " + e.getMessage());
			return null;
		}
		return invoiceList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceItemVO> getInvoiceItemsByInvoiceId(String sorszam, Boolean kimeno) {
		List<InvoiceItemVO> invoiceItems = new ArrayList<InvoiceItemVO>();
		try {
			if (sorszam != null) {
				if (kimeno) {
					DetachedCriteria criteria = DetachedCriteria.forClass(Ktetel.class).add(Restrictions.eq("sorszam", sorszam));

					List<Ktetel> ktetels = (List<Ktetel>) getInvoiceDao().findInvoiceItemByCriteria(criteria);
					if (ktetels != null) {
						for (Ktetel ktetel : ktetels) {
							invoiceItems.add(getInvoiceDao().convertInvoiceItemOutToInvoiceVO(ktetel));
						}
					}

				} else {
					DetachedCriteria criteria = DetachedCriteria.forClass(Tetel.class).add(Restrictions.eq("sorszam", sorszam));

					List<Tetel> tetels = (List<Tetel>) getInvoiceDao().findInvoiceItemByCriteria(criteria);
					if (tetels != null) {
						for (Tetel tetel : tetels) {
							invoiceItems.add(getInvoiceDao().convertInvoiceItemInToInvoiceVO(tetel));
						}
					}
				}
			} else {
				// log the error -> bad request
				throw new Exception("bad request: no such order in database");
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListInvoiceItem: " + e.getMessage());
			return null;
		}
		return invoiceItems;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<InvoiceVO> getOpenItemList(Integer vevokod, Date from, Date to) {

		List<InvoiceVO> invoiceList = new ArrayList<InvoiceVO>();
		List<Szamla> invoiceInList = new LinkedList<Szamla>();

		try {
			if (vevokod != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(Szamla.class).add(Restrictions.eq("vevokod", vevokod));
				criteria.add(Restrictions.neProperty("brutto", "rossz"));
				if (from != null)
					criteria.add(Restrictions.ge("kelt", from));
				if (to != null)
					criteria.add(Restrictions.le("kelt", to));
				criteria.addOrder(Order.desc("kelt"));
				invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			} else {
				DetachedCriteria criteria = DetachedCriteria.forClass(Szamla.class);
				criteria.add(Restrictions.neProperty("brutto", "rossz"));
				if (from != null)
					criteria.add(Restrictions.ge("kelt", from));
				if (to != null)
					criteria.add(Restrictions.le("kelt", to));
				criteria.addOrder(Order.desc("kelt"));
				invoiceInList = (List<Szamla>) getInvoiceDao().findInvoiceByCriteria(criteria);
			}
			if (invoiceInList == null) {
				return null;
			}
			for (Szamla szamla : invoiceInList) {
				invoiceList.add(getInvoiceDao().convertInvoiceInToInvoiceVO(szamla));
			}
		} catch (Exception e) {
			logger.error("error occured at processing ListOpenItems: " + e.getMessage());
			return null;
		}
		return invoiceList;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getInvoiceInForChart(UserVO user, Integer partnerId, Date from, Date to) {

		List<Object[]> chartList = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {

				chartList = getInvoiceDao().findInvoiceForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Szamla sz where sz.kelt>='" + from + "' and sz.kelt<='" + to
								+ "' group by year(sz.kelt), month(sz.kelt) order by year(sz.kelt) asc, month(sz.kelt) asc)");
			} else {
				chartList = getInvoiceDao().findInvoiceForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Szamla sz where sz.vevokod='" + partnerId + "' and sz.kelt>='" + from
								+ "' and sz.kelt<='" + to + "' group by year(sz.kelt), month(sz.kelt) order by year(sz.kelt) asc, month(sz.kelt) asc)");
			}

			if (chartList == null)
				return null;
			Integer startMonth = from.getMonth() + 1;
			Integer startYear = from.getYear() + 1900;
			int count = getMonthDifference(from, to);
			int z = 0;

			for (int i = 0; i < count; i++) {
				if (z < chartList.size()) {
					if (chartList.get(z)[1].equals(startMonth) && Integer.valueOf(chartList.get(z)[0].toString()).equals(startYear)) {
						newList.add(chartList.get(z));
						startMonth++;
						z++;
					} else {
						while (startMonth <= 12) {
							Object[] temp = { startYear, startMonth, 0 };
							newList.add(temp);
							startMonth++;
							break;
						}
						if (startMonth == 13) {
							startMonth = 1;
							startYear = startYear + 1;
						}
					}
				} else {
					while (startMonth <= 12) {
						Object[] temp = { startYear, startMonth, 0 };
						newList.add(temp);
						startMonth++;
						break;
					}
					if (startMonth == 13) {
						startMonth = 1;
						startYear = startYear + 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("error  has occured at processing InvoiceIn for charts: " + e.getMessage());
			return null;
		}
		return newList;
	}

	@SuppressWarnings("deprecation")
	public static int getMonthDifference(Date from, Date to) {
		int count = 0;
		from.setDate(1);
		while (from.compareTo(to) <= 0) {
			from.setMonth(from.getMonth() + 1);
			count++;
		}
		return count;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getInvoiceOutForChart(UserVO user, Integer partnerId, Date from, Date to) {
		List<Object[]> chartList = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {

				chartList = getInvoiceDao().findInvoiceForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Kszamla sz where sz.kelt>='" + from + "' and sz.kelt<='" + to
								+ "' group by year(sz.kelt), month(sz.kelt) order by year(sz.kelt) asc, month(sz.kelt) asc)");
			} else {
				chartList = getInvoiceDao().findInvoiceForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Kszamla sz where sz.vevokod='" + partnerId + "' and sz.kelt>='" + from
								+ "' and sz.kelt<='" + to + "' group by year(sz.kelt), month(sz.kelt) order by year(sz.kelt) asc, month(sz.kelt) asc)");
			}

			if (chartList == null)
				return null;
			Integer startMonth = from.getMonth() + 1;
			Integer startYear = from.getYear() + 1900;

			int count = getMonthDifference(from, to);
			int z = 0;
			for (int i = 0; i < count; i++) {
				if (z < chartList.size()) {
					if (chartList.get(z)[1].equals(startMonth) && Integer.valueOf(chartList.get(z)[0].toString()).equals(startYear)) {
						newList.add(chartList.get(z));
						startMonth++;
						z++;
					} else {
						while (startMonth <= 12) {
							Object[] temp = { startYear, startMonth, 0 };
							newList.add(temp);
							startMonth++;
							break;
						}
						if (startMonth == 13) {
							startMonth = 1;
							startYear = startYear + 1;
						}
					}
				} else {
					while (startMonth <= 12) {
						Object[] temp = { startYear, startMonth, 0 };
						newList.add(temp);
						startMonth++;
						break;
					}
					if (startMonth == 13) {
						startMonth = 1;
						startYear = startYear + 1;
					}
				}
			}
		} catch (Exception e) {
			logger.error("error  has occured at processing InvoiceOut for charts: " + e.getMessage());
			return null;
		}
		return newList;
	}

	@Override
	public Object[] getSummaryForChart(UserVO user, Integer partnerId) {

		Object[] summary = { 0, 0, 0, 0, 0, 0, 0, 0 };
		Object sumIn;
		Object debitIn;
		Object payedIn;
		Object expiredIn;
		Object sumOut;
		Object debitOut;
		Object payedOut;
		Object expiredOut;
		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {

				sumIn = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Szamla sz").get(0);

				if (sumIn == null)
					sumIn = 0;

				debitIn = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Szamla sz where sz.fizetve = '1900-01-01'").get(0);

				if (debitIn == null)
					debitIn = 0;

				payedIn = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Szamla sz where sz.fizetve != '1900-01-01'").get(0);

				if (payedIn == null)
					payedIn = 0;

				expiredIn = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Szamla sz where sz.esedkelt < now() and sz.fizetve='1900-01-01'").get(0);

				if (expiredIn == null)
					expiredIn = 0;

				sumOut = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Kszamla sz").get(0);

				if (sumOut == null)
					sumOut = 0;

				debitOut = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Kszamla sz where sz.fizetve = '1900-01-01'").get(0);

				if (debitOut == null)
					debitOut = 0;

				payedOut = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Kszamla sz where sz.fizetve != '1900-01-01'").get(0);

				if (payedOut == null)
					payedOut = 0;

				expiredOut = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Kszamla sz where sz.esedkelt < now() and sz.fizetve='1900-01-01'").get(0);

				if (expiredOut == null)
					expiredOut = 0;
			} else {

				sumIn = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Szamla sz where sz.vevokod ='" + partnerId + "'").get(0);

				if (sumIn == null)
					sumIn = 0;

				debitIn = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Szamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve = '1900-01-01'").get(0);

				if (debitIn == null)
					debitIn = 0;

				payedIn = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Szamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve != '1900-01-01'").get(0);

				if (payedIn == null)
					payedIn = 0;

				expiredIn = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Szamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve = '1900-01-01' and sz.esedkelt < now()")
						.get(0);

				if (expiredIn == null)
					expiredIn = 0;

				sumOut = getInvoiceDao().findInvoiceForChartByQuery("select sum(sz.dbrutto) from Kszamla sz where sz.vevokod ='" + partnerId + "'").get(0);

				if (sumOut == null)
					sumOut = 0;

				debitOut = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Kszamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve = '1900-01-01'").get(0);

				if (debitOut == null)
					debitOut = 0;

				payedOut = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Kszamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve != '1900-01-01'").get(0);

				if (payedOut == null)
					payedOut = 0;

				expiredOut = getInvoiceDao().findInvoiceForChartByQuery(
						"select sum(sz.dbrutto) from Kszamla sz where sz.vevokod ='" + partnerId + "' and sz.fizetve = '1900-01-01' and sz.esedkelt < now()")
						.get(0);

				if (expiredOut == null)
					expiredOut = 0;
			}
			summary[0] = sumIn;
			summary[1] = debitIn;
			summary[2] = payedIn;
			summary[3] = expiredIn;
			summary[4] = sumOut;
			summary[5] = debitOut;
			summary[6] = payedOut;
			summary[7] = expiredOut;

		} catch (Exception e) {
			logger.error("error  has occured at processing invoice summary for charts: " + e.getMessage());
			return null;
		}
		return summary;
	}

	@Override
	public List<Object[]> getInvoicesByPartnersCDH(List<Integer> ids, Date from, Date to, Integer vevokod) {
		return getInvoiceDao().getInvoicesByPartnersCDH(ids, from, to, vevokod);
	}

	@Override
	public List<Object[]> getInvoiceItemsByInvoiceIdCDH(String sorszam, Integer tablaVevokod) {
		if (sorszam == null)
			return null;
		return getInvoiceDao().getInvoiceItemsByInvoiceIdCDH(sorszam, tablaVevokod);
	}

}

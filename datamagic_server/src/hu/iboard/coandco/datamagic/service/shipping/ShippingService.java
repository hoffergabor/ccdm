/**
 * 
 */
package hu.iboard.coandco.datamagic.service.shipping;

import hu.iboard.coandco.datamagic.util.DataMagicConstants;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ShippingService extends ShippingServiceBase {

	@Override
	public List<ShippingVO> getShippingsByDateFiltered(Integer vevokod, Date from, Date to) {

		List<ShippingVO> shippings = null;
		try {
			if (vevokod != null) {
				DetachedCriteria criteria = DetachedCriteria.forClass(ShippingVO.class).add(Restrictions.eq("vevokod", vevokod))
						.add(Restrictions.between("kelt", from, to)).addOrder(Order.desc("kelt"));
				shippings = getShippingDao().findShippingByCriteria(criteria);

			} else {
				DetachedCriteria criteria = DetachedCriteria.forClass(ShippingVO.class).add(Restrictions.between("kelt", from, to))
						.addOrder(Order.desc("kelt"));
				shippings = getShippingDao().findShippingByCriteria(criteria);
			}

		} catch (Exception e) {
			logger.error("Error occured at processing ListShippings:", e);
			return null;
		}
		return shippings;
	}

	@Override
	public List<ShippingVO> getInitShippings(UserVO user, Integer vevokod) {

		List<ShippingVO> shippings = null;
		try {
			if (user != null) {
				if (user.getUserType().equals(UserVO.PARTNER) || user.getUserType().equals(UserVO.SALES)) {
					DetachedCriteria criteria = DetachedCriteria.forClass(ShippingVO.class).add(Restrictions.eq("vevokod", vevokod))
							.addOrder(Order.desc("kelt"));

					shippings = getShippingDao().findShippingByCriteria(criteria, 0, 10);
				} else if (user.getUserType().equals(UserVO.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {
					DetachedCriteria criteria = DetachedCriteria.forClass(ShippingVO.class).addOrder(Order.desc("kelt"));
					shippings = getShippingDao().findShippingByCriteria(criteria, 0, 10);
				}
			} else {
				throw new Exception("Bad request: no such user in database");
			}
		} catch (Exception e) {
			logger.error("Error occured at processing ListShippings: ", e);
			return null;

		}
		return shippings;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Object[]> getShippingsForChart(UserVO user, Integer partnerId, Date from, Date to) {

		List<Object[]> chartList = new ArrayList<Object[]>();
		List<Object[]> newList = new ArrayList<Object[]>();
		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {

				chartList = getShippingDao().findShippingForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Szlevel sz where sz.kelt>='" + from + "' and sz.kelt<='" + to
								+ "' group by year(sz.kelt), month(sz.kelt) order by year(sz.kelt) asc, month(sz.kelt) asc)");
			} else {
				chartList = getShippingDao().findShippingForChartByQuery(
						"select year(sz.kelt), month(sz.kelt), sum(sz.dbrutto) from Szlevel sz where sz.vevokod='" + partnerId + "' and sz.kelt>='" + from
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
			logger.error("error  has occured at processing Shipping for charts: " + e.getMessage());
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

	@Override
	public Object[] getSummaryForChart(UserVO user, Integer partnerId) {

		Object[] summary = { 0, 0, 0, 0 };
		Object sum;
		Object debit;
		Object payed;
		Object expired;

		try {
			if (user.getUserType().equals(DataMagicConstants.WORKER) || user.getUserType().equals(DataMagicConstants.ADMIN)) {

				sum = getShippingDao().findShippingForChartByQuery("select sum(sz.dbrutto) from Szlevel sz").get(0);

				if (sum == null)
					sum = 0;

				debit = getShippingDao().findShippingForChartByQuery("select sum(sz.dbrutto) from Szlevel sz where sz.fizetve = '1900-01-01'").get(0);

				if (debit == null)
					debit = 0;

				payed = getShippingDao().findShippingForChartByQuery("select sum(sz.dbrutto) from Szlevel sz where sz.fizetve != '1900-01-01'").get(0);

				if (payed == null)
					payed = 0;

				expired = getShippingDao().findShippingForChartByQuery("select sum(sz.dbrutto) from Szlevel sz where sz.esedkelt > now()").get(0);

				if (expired == null)
					expired = 0;

			} else {

				sum = getShippingDao().findShippingForChartByQuery("select sum(sz.dbrutto) from Szlevel sz where sz.vevokod ='" + partnerId + "'").get(0);

				if (sum == null)
					sum = 0;

				debit = getShippingDao().findShippingForChartByQuery(
						"select sum(sz.dbrutto) from Szlevel sz where sz.vevokod ='" + partnerId + "' and sz.fizetve = '1900-01-01'").get(0);

				if (debit == null)
					debit = 0;

				payed = getShippingDao().findShippingForChartByQuery(
						"select sum(sz.dbrutto) from Szlevel sz where sz.vevokod ='" + partnerId + "' and sz.fizetve != '1900-01-01'").get(0);

				if (payed == null)
					payed = 0;

				expired = getShippingDao().findShippingForChartByQuery(
						"select sum(sz.dbrutto) from Szlevel sz where sz.vevokod ='" + partnerId + "' and sz.esedkelt > now()").get(0);

				if (expired == null)
					expired = 0;
			}
			summary[0] = sum;
			summary[1] = debit;
			summary[2] = payed;
			summary[3] = expired;

		} catch (Exception e) {
			logger.error("error  has occured at processing shipping summary for charts: " + e.getMessage());
			return null;
		}
		return summary;
	}

	@Override
	public List<ShippingItemVO> getShippingItemsByShippingId(String sorszam) {
		if (sorszam == null)
			return null;
		return getShippingDao().getShippingItemByShippingId(sorszam);
	}
}

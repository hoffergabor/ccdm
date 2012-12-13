package hu.iboard.coandco.datamagic.dao.invoice;

import hu.iboard.coandco.datamagic.generated.Kszamla;
import hu.iboard.coandco.datamagic.generated.Ktetel;
import hu.iboard.coandco.datamagic.generated.Szamla;
import hu.iboard.coandco.datamagic.generated.Tetel;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class InvoiceDao extends HibernateDaoSupport implements IInvoiceDao {

	public InvoiceDao() {
	}

	@Override
	public List<?> findInvoiceByCriteria(DetachedCriteria criteria) {
		List<?> invoiceVO = null;
		invoiceVO = getHibernateTemplate().findByCriteria(criteria, 0, 1000);
		if (invoiceVO.size() == 0) {
			return null;
		}
		return invoiceVO;
	}

	@Override
	public List<?> findInvoiceByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {
		List<?> invoiceVO = null;
		invoiceVO = getHibernateTemplate().findByCriteria(criteria, firstResult, maxResult);
		if (invoiceVO.size() == 0) {
			return null;
		}
		return invoiceVO;
	}

	public List<?> findInvoiceItemByCriteria(DetachedCriteria criteria) {
		List<?> invoiceItemVO = null;
		invoiceItemVO = getHibernateTemplate().findByCriteria(criteria);
		if (invoiceItemVO.size() == 0) {
			return null;
		}
		return invoiceItemVO;
	}

	public InvoiceVO convertInvoiceInToInvoiceVO(Szamla invoice) {

		InvoiceVO invoiceVO = new InvoiceVO();
		invoiceVO.setArfolyam(invoice.getArfolyam());
		invoiceVO.setBrutto(invoice.getBrutto());
		invoiceVO.setCegnev(invoice.getVnev());
		invoiceVO.setDbrutto(invoice.getDbrutto());
		invoiceVO.setDevnem(invoice.getDevnem());
		invoiceVO.setDnetto(invoice.getDnetto());
		invoiceVO.setEsedkelt(invoice.getEsedkelt());
		invoiceVO.setFizetve(invoice.getFizetve());
		invoiceVO.setFizmod(invoice.getFizmod());
		invoiceVO.setKelt(invoice.getKelt());
		invoiceVO.setKimeno(false);
		invoiceVO.setNetto(invoice.getNetto());
		invoiceVO.setRealesed(invoice.getRealesed());
		invoiceVO.setSorszam(invoice.getSorszam());
		invoiceVO.setSzlaszam(invoice.getSzlaszam());
		invoiceVO.setTelj(invoice.getTelj());
		invoiceVO.setVevokod(invoice.getVevokod());
		invoiceVO.setRossz(invoice.getRossz());
		invoiceVO.setArrears(invoice.getBrutto().subtract(invoice.getRossz()));
		invoiceVO.setTelepkod(invoice.getTelepkod());
		invoiceVO.setTelephely(invoice.getTelephely() != null ? invoice.getTelephely() : null);

		return invoiceVO;
	}

	public InvoiceVO convertInvoiceOutToInvoiceVO(Kszamla invoice) {

		InvoiceVO invoiceVO = new InvoiceVO();
		invoiceVO.setArfolyam(invoice.getArfolyam());
		invoiceVO.setBrutto(invoice.getBrutto());
		invoiceVO.setCegnev(invoice.getCegnev());
		invoiceVO.setDbrutto(invoice.getDbrutto());
		invoiceVO.setDevnem(invoice.getDevnem());
		invoiceVO.setDnetto(invoice.getDnetto());
		invoiceVO.setEsedkelt(invoice.getEsedkelt());
		invoiceVO.setFizetve(invoice.getFizetve());
		invoiceVO.setFizmod(invoice.getFizmod());
		invoiceVO.setKelt(invoice.getKelt());
		invoiceVO.setKimeno(true);
		invoiceVO.setNetto(invoice.getNetto());
		invoiceVO.setRealesed(invoice.getRealesed());
		invoiceVO.setSorszam(invoice.getSorszam());
		invoiceVO.setSzlaszam(invoice.getSzlaszam());
		invoiceVO.setTelj(invoice.getTelj());
		invoiceVO.setVevokod(invoice.getVevokod());

		return invoiceVO;
	}

	@Override
	public InvoiceItemVO convertInvoiceItemInToInvoiceVO(Tetel item) {

		InvoiceItemVO itemVO = new InvoiceItemVO();

		itemVO.setAcikksz(item.getAcikksz());
		itemVO.setAear(item.getAear());
		itemVO.setAertek(item.getAertek());
		itemVO.setAmegn(item.getAmegn());
		itemVO.setAmenny(item.getAmenny());
		itemVO.setArukod(item.getArukod());
		itemVO.setBertek(item.getBertek());
		itemVO.setHaertek(item.getHaertek());
		itemVO.setHbertek(item.getHbertek());
		itemVO.setHnertek(item.getHnertek());
		itemVO.setNertek(item.getNertek());
		itemVO.setSorszam(item.getSorszam());
		itemVO.setTetelszam(item.getTetelszam());
		itemVO.setUnikazon(item.getUnikazon());

		return itemVO;
	}

	@Override
	public InvoiceItemVO convertInvoiceItemOutToInvoiceVO(Ktetel item) {

		InvoiceItemVO itemVO = new InvoiceItemVO();

		itemVO.setAcikksz(item.getAcikksz());
		itemVO.setAear(item.getAear());
		itemVO.setAertek(item.getAertek());
		itemVO.setAmegn(item.getAmegn());
		itemVO.setAmenny(item.getAmenny());
		itemVO.setArukod(item.getArukod());
		itemVO.setBertek(item.getBertek());
		itemVO.setHaertek(item.getHaertek());
		itemVO.setHbertek(item.getHbertek());
		itemVO.setHnertek(item.getHnertek());
		itemVO.setNertek(item.getNertek());
		itemVO.setSorszam(item.getSorszam());
		itemVO.setTetelszam(item.getTetelszam());
		itemVO.setUnikazon(item.getUnikazon());

		return itemVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findInvoiceForChartByQuery(String query) {
		List<Object[]> chart = null;
		chart = getHibernateTemplate().find(query);
		if (chart.size() == 0) {
			return null;
		}
		return chart;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInvoicesByPartnersCDH(List<Integer> ids, Date from, Date to, Integer vevokod) {
		List<Object[]> recordsList = new ArrayList<Object[]>();
		try {
			String query = "select sz.arfolyam,sz.brutto,sz.vnev,sz.dbrutto,sz.devnem,sz.dnetto,sz.esedkelt,sz.fizetve,sz.fizmod,sz.kelt,sz.netto,sz.realesed,sz.sorszam,sz.szlaszam,sz.telj,sz.vevokod,sz.rossz from Szamla_"
					+ vevokod + " sz where";
			if (ids != null && ids.size() > 0) {
				query += " sz.vevokod in(";
				int size = ids.size();
				int i = 1;
				for (Integer id : ids) {
					if (i < size) {
						query = query + id + ",";
						i++;
					} else
						query = query + id;
				}
				query = query + ")";
			}
			if (from != null && to != null && ids.size() != 0)
				query = query + " and";
			if (from != null)
				query += " sz.kelt>='" + new Timestamp(from.getTime()) + "' and";
			if (to != null)
				query += " sz.kelt<='" + new Timestamp(to.getTime()) + "'";
			query += " order by sz.kelt desc";
			getHibernateTemplate().setMaxResults(1000);
			recordsList = getHibernateTemplate().find(query);
			if (recordsList == null)
				return null;
		} catch (Exception e) {
			logger.error("Error getting invoices for CDH!", e);
			return null;
		}
		return recordsList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getInvoiceItemsByInvoiceIdCDH(String sorszam, Integer tablaVevokod) {
		List<Object[]> invoiceItems = new ArrayList<Object[]>();
		try {

			String query = "select t.acikksz,t.aear,t.aertek,t.amegn,t.amenny,t.arukod,t.bertek,t.haertek,t.hbertek,t.hnertek,t.nertek,t.sorszam,t.tetelszam,t.unikazon from Tetel_"
					+ tablaVevokod + " t where t.sorszam='" + sorszam + "' order by t.acikksz, t.amegn asc";
			invoiceItems = getHibernateTemplate().find(query);
			if (invoiceItems == null)
				return null;

		} catch (Exception e) {
			logger.error("Error getting invoice items for CDH: " + e.getMessage());
			return null;
		}
		return invoiceItems;
	}
}

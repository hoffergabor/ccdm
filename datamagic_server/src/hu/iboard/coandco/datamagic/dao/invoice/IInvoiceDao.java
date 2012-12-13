package hu.iboard.coandco.datamagic.dao.invoice;

import hu.iboard.coandco.datamagic.generated.Kszamla;
import hu.iboard.coandco.datamagic.generated.Ktetel;
import hu.iboard.coandco.datamagic.generated.Szamla;
import hu.iboard.coandco.datamagic.generated.Tetel;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IInvoiceDao {

	public List<?> findInvoiceByCriteria(DetachedCriteria criteria);

	public List<?> findInvoiceByCriteria(DetachedCriteria criteria, int firstResult, int maxResult);

	public List<?> findInvoiceItemByCriteria(DetachedCriteria criteria);

	public InvoiceVO convertInvoiceOutToInvoiceVO(Kszamla invoice);

	public InvoiceVO convertInvoiceInToInvoiceVO(Szamla invoice);

	public InvoiceItemVO convertInvoiceItemOutToInvoiceVO(Ktetel item);

	public InvoiceItemVO convertInvoiceItemInToInvoiceVO(Tetel item);

	public List<Object[]> findInvoiceForChartByQuery(String query);

	public List<Object[]> getInvoicesByPartnersCDH(List<Integer> ids, Date from, Date to, Integer vevokod);
	
	public List<Object[]> getInvoiceItemsByInvoiceIdCDH(String sorszam, Integer tablaVevokod);


}

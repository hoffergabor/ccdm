package hu.iboard.coandco.datamagic.service.invoice;

import hu.iboard.coandco.datamagic.dao.invoice.IInvoiceDao;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Date;
import java.util.List;

public abstract class InvoiceServiceBase extends ServiceBase {

	private IInvoiceDao invoiceDao;

	private Boolean realtyCompany = false;

	public IInvoiceDao getInvoiceDao() {
		return invoiceDao;
	}

	public void setInvoiceDao(IInvoiceDao invoiceDao) {
		this.invoiceDao = invoiceDao;
	}

	public Boolean isRealtyCompany() {
		return realtyCompany;
	}

	public void setRealtyCompany(Boolean realtyCompany) {
		this.realtyCompany = realtyCompany;
	}

	public abstract List<InvoiceVO> getInvoicesIn(Integer vevokod, Date from, Date to);

	public abstract List<InvoiceVO> getInvoicesOut(Integer vevokod, Date from, Date to);

	public abstract List<InvoiceVO> getInitInvoices(Integer vevokod, Boolean in, Boolean out);

	public abstract List<InvoiceVO> getInitInvoicesByAllPartners(List<Integer> vevokods, Boolean invoiceIn,
			Boolean invoiceOut);

	public abstract List<InvoiceVO> getInvoices(Integer vevokod, Date from, Date to, Boolean invoiceIn,
			Boolean invoiceOut);

	public abstract List<InvoiceVO> getInvoicesByPartners(List<Integer> ids, Date from, Date to, Boolean invoiceIn,
			Boolean invoiceOut);

	public abstract List<InvoiceItemVO> getInvoiceItemsByInvoiceId(String sorszam, Boolean kimeno);

	public abstract List<InvoiceVO> getOpenItemList(Integer vevokod, Date from, Date to);

	public abstract List<Object[]> getInvoiceInForChart(UserVO user, Integer partnerId, Date from, Date to);

	public abstract List<Object[]> getInvoiceOutForChart(UserVO user, Integer partnerId, Date from, Date to);

	public abstract Object[] getSummaryForChart(UserVO user, Integer partnerId);

	public abstract List<Object[]> getInvoicesByPartnersCDH(List<Integer> ids, Date from, Date to, Integer vevokod);
	
	public abstract List<Object[]> getInvoiceItemsByInvoiceIdCDH(String sorszam, Integer tablaVevokod);

}

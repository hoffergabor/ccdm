package hu.iboard.coandco.datamagic.service.document;

import hu.iboard.coandco.datamagic.dao.ktetel.IKtetelDao;
import hu.iboard.coandco.datamagic.dao.munkszam2.IMunkszam2Dao;
import hu.iboard.coandco.datamagic.dao.order.IOrderDao;
import hu.iboard.coandco.datamagic.dao.partner.IPartnerDao;
import hu.iboard.coandco.datamagic.dao.realty.IRealtyArrangementDao;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;
import hu.iboard.coandco.datamagic.vo.realty.RealtyArrangementVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

import java.util.List;

public abstract class DocumentServiceBase extends ServiceBase {

	private IOrderDao orderDao;
	private IRealtyArrangementDao realtyArrangementDao;
	private IPartnerDao partnerDao;
	private IKtetelDao ktetelDao;
	private IMunkszam2Dao munkszam2Dao;

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public IRealtyArrangementDao getRealtyArrangementDao() {
		return realtyArrangementDao;
	}

	public void setRealtyArrangementDao(IRealtyArrangementDao realtyArrangementDao) {
		this.realtyArrangementDao = realtyArrangementDao;
	}

	public IPartnerDao getPartnerDao() {
		return partnerDao;
	}

	public void setPartnerDao(IPartnerDao partnerDao) {
		this.partnerDao = partnerDao;
	}

	public IKtetelDao getKtetelDao() {
		return ktetelDao;
	}

	public void setKtetelDao(IKtetelDao ktetelDao) {
		this.ktetelDao = ktetelDao;
	}
	
	public IMunkszam2Dao getMunkszam2Dao() {
		return munkszam2Dao;
	}

	public void setMunkszam2Dao(IMunkszam2Dao munkszam2Dao) {
		this.munkszam2Dao = munkszam2Dao;
	}

	public abstract byte[] handleGenerateInvoiceList(List<InvoiceVO> invoiceList);
	
	public abstract byte[] handleGenerateOpenItemList(List<InvoiceVO> invoiceList);

	public abstract byte[] handleGenerateOrder(String sorszam);

	public abstract byte[] handleGenerateOrderExcel(String sorszam);

	public abstract byte[] handleGenerateRealtyArrangementList(List<RealtyArrangementVO> realtyarrangementList);

	public abstract byte[] handleGenerateRealtyArrangementPayOff(RealtyArrangementVO realty);

	public abstract byte[] handleGenerateRealtyArrangementWorkSheet(RealtyArrangementVO realty);

	public abstract byte[] handleGenerateShippingList(List<ShippingVO> shippingList);

}

package hu.iboard.coandco.datamagic.service.document;

import hu.iboard.coandco.datamagic.dao.order.IOrderDao;

public abstract class DocumentServiceBase{

	private IOrderDao orderDao;

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public abstract byte[] handleGenerateOrder(String sorszam);

}

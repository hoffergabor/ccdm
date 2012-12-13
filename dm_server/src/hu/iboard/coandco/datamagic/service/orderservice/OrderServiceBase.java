package hu.iboard.coandco.datamagic.service.orderservice;

import hu.iboard.coandco.datamagic.dao.order.IOrderDao;
import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;

import java.math.BigDecimal;
import java.util.List;

public abstract class OrderServiceBase {

	private IOrderDao orderDao;

	public abstract Rendel saveOrUpdateOrder(Rendel rendel, List<Aru> products, List<BigDecimal> mennyisegek, String aroszlop);

	public abstract Rendel getOrderById(String sorszam);

	public abstract List<Rendel> getOrdersByLakosvevo(Integer lakoskod);

	public abstract List<Rendtet> getOrderItemsByOrderId(String sorszam);

	public abstract List<Rendel> getOrdersByPartnerId(Integer id);

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao (IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

}

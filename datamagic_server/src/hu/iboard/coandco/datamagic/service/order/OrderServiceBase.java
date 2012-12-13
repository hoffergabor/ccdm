package hu.iboard.coandco.datamagic.service.order;

import hu.iboard.coandco.datamagic.dao.order.IOrderDao;
import hu.iboard.coandco.datamagic.dao.product.IProductDao;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.service.ServiceBase;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Date;
import java.util.List;

public abstract class OrderServiceBase extends ServiceBase {

	private IOrderDao orderDao;
	private IProductDao productDao;

	public IOrderDao getOrderDao() {
		return orderDao;
	}

	public IProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public abstract List<OrderVO> getOrders(UserVO user, Integer referenceId, String kivagybe);

	public abstract List<OrderVO> getOrdersByDateFiltered(Integer partnerId, Date from, Date to, Boolean all, String kivagybe);

	public abstract List<OrderItemVO> getOrderItemsByOrderId(String sorszam);

	public abstract Boolean saveOrder(Rendel order, List<ProductVO> products, Partner partner);
	
	public abstract Boolean saveOrder(Rendel rendel, List<ProductVO> products, Partner partner, List<String> megj);	

	public abstract Boolean updateOrder(Rendel order, String orderNum, List<ProductVO> products, Partner partner);

	public abstract List<Object[]> getOrdersInForChart(UserVO user, Integer partnerId, Date from, Date to, String kivagybe);

	public abstract Object[] getSummaryForChart(UserVO user, Integer partnerId, String kivagybe);

	public abstract List<Rendtet> getRendtetForPRProjekt(Integer mkateg1);

	public abstract Rendel getRendelById(String sorszam);
	
	public abstract List<String> getRendtetSzamlsorszBySorszam(String sorszam);
	
	public abstract List<String> getRendtetSzlevsorszBySorszam(String sorszam);
	
	public abstract List<OrderVO> getAllFuggoOrders(Integer partnerId, String kivagybe);

}

package hu.iboard.coandco.datamagic.dao.order;

import hu.iboard.coandco.datamagic.generated.Rendmegj;
import hu.iboard.coandco.datamagic.generated.Rendel;
import hu.iboard.coandco.datamagic.generated.Rendtet;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IOrderDao {

	/**
	 * @param user
	 * @return
	 */

	public List<OrderVO> findOrderByCriteria(DetachedCriteria criteria);

	public List<OrderVO> findOrderByCriteria(DetachedCriteria criteria, int firstResult, int maxResult);

	public List<Object[]> findOrderForChartByQuery(String query);

	public List<OrderItemVO> findOrderItemByCriteria(DetachedCriteria criteria);

	public void saveOrder(Rendel order);

	public void updateOrder(Rendel order);

	public void saveOrderItem(Rendtet orderItem);

	public void deleteOrderItems(List<Rendtet> orderItems);

	public Rendtet loadOrderItem(String unikazon);

	public Rendel loadOrder(String sorszam);
	
	public void saveRendMegj(Rendmegj rendmegj);
	
	public List<Rendmegj> loadRendMegj(String rendsorszam);

	public List<OrderVO> getOrdersByDate(Integer partnerId, Date from, Date to, Boolean all, String kivagybe);
	
	public List<OrderVO> getAllFuggoOrders(Integer partnerId, String kivagybe);

	public List<Rendtet> getRendtetForPRProjekt(Integer mkateg1);

	public Rendel getRendelById(String sorszam);

	public List<String> getRendtetSzamlsorszBySorszam(String sorszam);
	
	public List<String> getRendtetSzlevsorszBySorszam(String sorszam);

}

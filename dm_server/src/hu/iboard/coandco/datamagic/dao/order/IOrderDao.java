package hu.iboard.coandco.datamagic.dao.order;

import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;

import java.util.List;

public interface IOrderDao {

	public Rendel saveOrUpdateOrder(Rendel rendel);

	public Rendtet saveOrUpdateOrderItem(Rendtet rendtet);

	public Rendtet getOrderItemById(String unikazon);

	public Rendel getOrderById(String sorszam);

	public String getLastSorszamFromRendel();

	public List<Rendel> getOrdersByLakosvevo(Integer lakoskod);

	public List<Rendtet> getOrderItemsByOrderId(String sorszam);

	public List<Rendel> getOrdersByPartnerId(Integer id);

}

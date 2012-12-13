package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.order;

import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "orderListControllerData")
@SessionScoped
public class OrderListControllerData implements Serializable {

	private static final long serialVersionUID = 8821907960162014603L;

	private List<Rendel> orders = new ArrayList<Rendel>();
	private Rendel selectedOrder;
	private List<Rendtet> orderItems = new ArrayList<Rendtet>();

	public List<Rendel> getOrders() {
		return orders;
	}

	public void setOrders(List<Rendel> orders) {
		this.orders = orders;
	}

	public Rendel getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Rendel selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public List<Rendtet> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<Rendtet> orderItems) {
		this.orderItems = orderItems;
	}

}

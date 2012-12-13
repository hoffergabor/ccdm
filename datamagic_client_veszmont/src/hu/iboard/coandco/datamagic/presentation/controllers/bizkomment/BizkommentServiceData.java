package hu.iboard.coandco.datamagic.presentation.controllers.bizkomment;

import hu.iboard.coandco.datamagic.generated.Bizkomment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "bizkommentserviceData")
@SessionScoped
public class BizkommentServiceData implements Serializable{
	
	private static final long serialVersionUID = 6883460902725089321L;
	
	private Bizkomment partnerBizkomment;
	private List<Object[]> orders = new ArrayList<Object[]>();
	public Bizkomment getPartnerBizkomment() {
		return partnerBizkomment;
	}
	public void setPartnerBizkomment(Bizkomment partnerBizkomment) {
		this.partnerBizkomment = partnerBizkomment;
	}
	public List<Object[]> getOrders() {
		return orders;
	}
	public void setOrders(List<Object[]> orders) {
		this.orders = orders;
	}

	
}

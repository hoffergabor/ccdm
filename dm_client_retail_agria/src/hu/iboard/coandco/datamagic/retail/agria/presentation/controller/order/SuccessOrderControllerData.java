package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.order;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "successOrderControllerData")
@SessionScoped
public class SuccessOrderControllerData implements Serializable {

	private static final long serialVersionUID = 2914185124590345223L;
	
	private List<ProductVO> items = new ArrayList<ProductVO>();
	private BigDecimal allBrutto;
	private String shippingPrice;
	private Integer shippingOption;
	private String payOption;

	public List<ProductVO> getItems() {
		return items;
	}

	public void setItems(List<ProductVO> items) {
		this.items = items;
	}

	public BigDecimal getAllBrutto() {
		return allBrutto;
	}

	public void setAllBrutto(BigDecimal allBrutto) {
		this.allBrutto = allBrutto;
	}

	public String getShippingPrice() {
		return shippingPrice;
	}

	public void setShippingPrice(String shippingPrice) {
		this.shippingPrice = shippingPrice;
	}

	public Integer getShippingOption() {
		return shippingOption;
	}

	public void setShippingOption(Integer shippingOption) {
		this.shippingOption = shippingOption;
	}

	public String getPayOption() {
		return payOption;
	}

	public void setPayOption(String payOption) {
		this.payOption = payOption;
	}

}

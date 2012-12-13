package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.product;

import hu.iboard.coandco.datamagic.model.bizkomment.Bizkomment;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "productControllerData")
@SessionScoped
public class ProductControllerData implements Serializable {

	private static final long serialVersionUID = 4754004852755864239L;

	private Integer productId;
	private ProductVO product;
	private Bizkomment productBizkomment;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public ProductVO getProduct() {
		return product;
	}
	public void setProduct(ProductVO product) {
		this.product = product;
	}
	public Bizkomment getProductBizkomment() {
		return productBizkomment;
	}
	public void setProductBizkomment(Bizkomment productBizkomment) {
		this.productBizkomment = productBizkomment;
	}

	

}

package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.index;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "indexControllerData")
@SessionScoped
public class IndexControllerData implements Serializable {

	private static final long serialVersionUID = -7129005786858556338L;
	
	private List<ProductVO> products = new ArrayList<ProductVO>();
	private ProductVO selectedProduct;
	private String passwordEmail;

	public List<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(List<ProductVO> products) {
		this.products = products;
	}

	public ProductVO getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductVO selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public String getPasswordEmail() {
		return passwordEmail;
	}

	public void setPasswordEmail(String passwordEmail) {
		this.passwordEmail = passwordEmail;
	}
	
}

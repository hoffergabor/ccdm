package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.index;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "indexControllerData")
@SessionScoped
public class IndexControllerData implements Serializable {

	private static final long serialVersionUID = -7129005786858556338L;
	
	private String newsLetterEmail;
	private String forgottenPasswordEmail;
	private List<ProductVO> products = new ArrayList<ProductVO>();
	private ProductVO selectedProduct;
	
	
	public String getNewsLetterEmail() {
		return newsLetterEmail;
	}

	public void setNewsLetterEmail(String newsLetterEmail) {
		this.newsLetterEmail = newsLetterEmail;
	}

	public String getForgottenPasswordEmail() {
		return forgottenPasswordEmail;
	}

	public void setForgottenPasswordEmail(String forgottenPasswordEmail) {
		this.forgottenPasswordEmail = forgottenPasswordEmail;
	}

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
	
	
}

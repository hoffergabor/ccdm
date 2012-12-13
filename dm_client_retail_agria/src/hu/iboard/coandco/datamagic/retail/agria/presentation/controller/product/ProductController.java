package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.product;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "productController")
@RequestScoped
public class ProductController extends AbstractController {

	@ManagedProperty(value = "#{productControllerData}")
	private ProductControllerData data;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setProduct(null);
		}
	}

	public String showSelectedProduct() {
		//QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount());
		getData().setProduct(getProductService().getProductVOById(getData().getProductId(), getQueryParamsVO()));
		getData().setProductBizkomment(getProductService().getBizkommentById("A" + getData().getProductId()));
		return null;
	}

	public ProductControllerData getData() {
		return data;
	}

	public void setData(ProductControllerData data) {
		this.data = data;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
}

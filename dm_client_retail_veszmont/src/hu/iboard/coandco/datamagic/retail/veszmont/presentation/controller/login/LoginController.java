package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.login;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import org.primefaces.event.IdleEvent;

@ManagedBean
@RequestScoped
public class LoginController extends AbstractController {

	@ManagedProperty(value = "#{loginControllerData}")
	private LoginControllerData data;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;	

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setUserName(null);
			getData().setPassword(null);
		}
	}

	public String login() {
		if (getData().getUserName() == null || getData().getUserName().equals("")) {
			addErrorMessage("", "Belépéshez, kérem adja meg a felhasználónevét.");
			return null;
		}
		if (getData().getPassword() == null || getData().getPassword().equals("")) {
			addErrorMessage("", "Belépéshez, kérem adja meg a jelszavát.");
			return null;
		}
		try {
			boolean isLogin = getLoginManager().login(getData().getUserName(), encodeWithMD5(getData().getPassword()));
			if (!isLogin) {
				addErrorMessage("", "Rossz felhasználónév vagy jelszó!");
				logger.error("Login failed!");
				return null;
			}
			if (getManagedAdmin() != null) {
				return "admin";
			}
			
			//recalc discount
			if (getCartController() != null && getManagedPartner() != null) {
				List<ProductVO> products = new ArrayList<ProductVO>();
				QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), true, null);
				for (ProductVO product : getCartController().getData().getItems()) {
					ProductVO aru = getProductService().getProductVOById(product.getArukod(), queryParamsVO);
					if (aru == null)
						continue;
					aru.setMenny(product.getMenny());
					products.add(aru);
				}
				
				BigDecimal allBrutto = new BigDecimal(0);
				
				for (ProductVO productVO : products)
					allBrutto = allBrutto.add(productVO.getMenny().multiply(productVO.getKedvarBrutto()));
				getCartController().getData().setAllBrutto(allBrutto);
				getCartController().getData().setItems(products);
			}
			//recalc discount			
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor.");
			logger.error("Login failed!", e);
			return null;
		}
		return "profile";
	}

	public String loginAdmin() {
		try {
			boolean isLogin = getLoginManager().loginAdmin(getData().getUserName(), encodeWithMD5(getData().getPassword()));
			if (!isLogin) {
				addErrorMessage("", "Rossz felhasználónév vagy jelszó.");
				logger.error("Login failed!");
				return null;
			}
		} catch (Exception e) {
			addFatalMessage("", "Hiba történt belépéskor.");
			logger.error("Login failed!", e);
			return null;
		}
		return "adminmanager";
	}

	public String logout() {
		getLoginManager().logout();
		return "index";
	}

	public String logoutAdmin() {
		getLoginManager().logoutAdmin();
		return "admin";
	}

	public void idleListener(IdleEvent event) {
		getLoginManager().logout();
	}

	public LoginControllerData getData() {
		return data;
	}

	public void setData(LoginControllerData data) {
		this.data = data;
	}
	
	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}	
}

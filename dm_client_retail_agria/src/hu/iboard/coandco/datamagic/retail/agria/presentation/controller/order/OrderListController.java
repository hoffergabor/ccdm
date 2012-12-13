package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.order;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.rendel.Rendel;
import hu.iboard.coandco.datamagic.model.rendtet.Rendtet;
import hu.iboard.coandco.datamagic.retail.agria.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.document.DocumentService;
import hu.iboard.coandco.datamagic.service.orderservice.OrderService;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.userservice.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "orderListController")
@RequestScoped
public class OrderListController extends AbstractController {

	@ManagedProperty(value = "#{orderListControllerData}")
	private OrderListControllerData data;
	@ManagedProperty(value = "#{orderService}")
	private OrderService orderService;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;
	@ManagedProperty(value = "#{documentService}")
	private DocumentService documentService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setOrders(new ArrayList<Rendel>());
			if (getManagedCustomer() != null) {
				getData().getOrders().addAll(getOrderService().getOrdersByLakosvevo(getManagedCustomer().getLakosKod()));
			}
			if (getManagedCustomer().getSzlFizeto() != 0) {
				Partner partner = getUserService().getPartnerById(getManagedCustomer().getSzlFizeto());
				if (partner != null) {
					List<Rendel> orders = getOrderService().getOrdersByPartnerId(partner.getVevokod());
					getData().getOrders().addAll(orders);
				}
			}
		}
	}

	public void showOrderItems(ActionEvent event) {

		if (getData().getSelectedOrder() == null)
			return;
		getData().setOrderItems(getOrderService().getOrderItemsByOrderId(getData().getSelectedOrder().getSorszam()));
	}

	public void addItemsToCart(ActionEvent event) {
		if (getData().getSelectedOrder() == null)
			return;
		List<Rendtet> rendtets = getOrderService().getOrderItemsByOrderId(getData().getSelectedOrder().getSorszam());
		if (rendtets == null)
			return;
		List<ProductVO> aruk = new ArrayList<ProductVO>();
		List<ProductVO> cart = getCartController().getData().getItems();
		//QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount());
		for (Rendtet rendtet : rendtets) {
			ProductVO aru = getProductService().getProductVOById(rendtet.getArukod(), getQueryParamsVO());
			if (aru != null) {
				aru.setMenny(rendtet.getAmenny());
				aruk.add(aru);
			}
		}
		if (!cart.isEmpty()) {
			for (ProductVO prod : cart) {
				for (ProductVO tmp : aruk) {
					if (tmp.getArukod() == prod.getArukod()) {
						BigDecimal menny = prod.getMenny();
						prod.setMenny(menny.add(tmp.getMenny()));
						aruk.remove(tmp);
						aruk.add(prod);
						break;
					}
				}
			}
		}
		getCartController().getData().getItems().removeAll(aruk);
		getCartController().getData().getItems().addAll(aruk);
		BigDecimal allBrutto = new BigDecimal(0);
//		for (ProductVO aruKisker : getCartController().getData().getItems()) {
//			float afa = (float) (aruKisker.getAfakodok().getFordkulcs() + 100) / 100;
//			BigDecimal brutto = aruKisker.getMenny().multiply(aruKisker.getDear2().multiply(new BigDecimal(afa)));
//			allBrutto = allBrutto.add(brutto);
//		} Kiszedtem, mert átállltunk ProductVO-re, az meg advancedebb ennél annyival, hogy nem kell bruttót számolni. /2011.09.21 -HL
		
		for (ProductVO aruKisker : getCartController().getData().getItems()) {
			allBrutto = allBrutto.add(aruKisker.getMenny().multiply(aruKisker.getBrutto()));
		}
		
//		for (ProductVO aru : getData().getItems()) {
//			if (aru.getArukod() == getData().getSelectedItem().getArukod()) {
//				aru.setMenny(aru.getMenny().add(new BigDecimal(1)));
//			}
//			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));
//			temp.add(aru);
//		}		
		
		getCartController().getData().setAllBrutto(allBrutto);
		getCartController().getData().setItemsSize(getCartController().getData().getItems().size());
		addInfoMessage("", "A tételek kosárba kerültek!");
	}
	
	public String print() {
		try {
			if (getData().getSelectedOrder() == null) {
				addFatalMessage("", "Üres a rendelés tétel lista!");
				return null;
			}
			byte[] file = getDocumentService().handleGenerateOrder(getData().getSelectedOrder().getSorszam());
			fileDownloader(file, "order_list.pdf");
		} catch (Exception e) {
			addFatalMessage("Hiba tortent nyomtataskor!", "");
			logger.error("Error by printing order item list!", e);
		}
		return null;
	}

	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public OrderListControllerData getData() {
		return data;
	}

	public void setData(OrderListControllerData data) {
		this.data = data;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
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

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}

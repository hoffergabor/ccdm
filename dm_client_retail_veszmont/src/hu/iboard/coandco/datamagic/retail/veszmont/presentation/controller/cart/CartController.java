package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.cart;

//import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;
import hu.iboard.coandco.datamagic.model.aru.Aru;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.wishlistservice.WishlistServiceBase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

@ManagedBean(name = "cartController")
@RequestScoped
public class CartController extends AbstractController {

	@ManagedProperty(value = "#{cartControllerData}")
	private CartControllerData data;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;
	@ManagedProperty(value = "#{wishlistService}")
	private WishlistServiceBase wishlistService;

	// @ManagedProperty(value = "#{orderParameters}")
	// private OrderParameters orderParameters;
	//
	// public OrderParameters getOrderParameters() {
	// return orderParameters;
	// }
	//
	// public void setOrderParameters(OrderParameters orderParameters) {
	// this.orderParameters = orderParameters;
	// }

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setMenny(new BigDecimal(1));
			getData().setWishlistName(null);
		}
	}

	public void deleteAllItem(ActionEvent event) {
		getData().setItems(new ArrayList<ProductVO>());
		getData().setAllBrutto(new BigDecimal(0));
		getData().setItemsSize(0);
		addInfoMessage("", "Kosár kiürítve.");
	}

	public void increaseQuantity(ActionEvent event) {
		if (getData().getSelectedItem() == null)
			return;
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);
		for (ProductVO aru : getData().getItems()) {
			if (aru.getArukod() == getData().getSelectedItem().getArukod()) {
				aru.setMenny(aru.getMenny().add(new BigDecimal(1)));
			}
//			float afa = (float) (aru.getAfakodok().getFordkulcs() + 100) / 100;
//			BigDecimal brutto = aru.getMenny().multiply(aru.getEar().multiply(new BigDecimal(afa)));
			//allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getKedvarBrutto()));
			temp.add(aru);
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", "+1 db " + getData().getSelectedItem().getMegn() + " került a kosárba.");
	}

	public void decreaseQuantity(ActionEvent event) {
		if (getData().getSelectedItem() == null)
			return;
		if (getData().getSelectedItem().getMenny().compareTo(new BigDecimal(1)) == 0) {
			deleteItem(event);
			return;
		}
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);

		for (ProductVO aru : getData().getItems()) {
			if (aru.getArukod() == getData().getSelectedItem().getArukod())
				aru.setMenny(aru.getMenny().subtract(new BigDecimal(1)));
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getKedvarBrutto()));
			temp.add(aru);
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", " 1 db " + getData().getSelectedItem().getMegn() + " kivéve a kosárból.");
	}

	public void deleteItem(ActionEvent event) {
		if (getData().getSelectedItem() == null)
			return;
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);

		for (ProductVO aru : getData().getItems()) {
			if (!(aru.getArukod() == getData().getSelectedItem().getArukod())) {
				allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getKedvarBrutto()));				
				temp.add(aru);
			}
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", getData().getSelectedItem().getMegn() + " kikerült a kosárból.");
	}

	public void changeQuantity(ActionEvent event) {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);

		for (ProductVO aru : getData().getItems()) {
			if (aru.getMenny().compareTo(new BigDecimal(1)) == -1) {
				aru.setMenny(new BigDecimal(1));
				addErrorMessage("", "Nem adhat meg 1-nél kisebb mennyiséget.");
			}
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getKedvarBrutto()));
			temp.add(aru);
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);

	}

	public void addProductToCart(ActionEvent event) {
		if (getData().getMenny().compareTo(new BigDecimal(1)) == -1) {
			addErrorMessage("", "Nem adhat meg 1-nél kisebb mennyiséget.");
			getData().setMenny(new BigDecimal(1));
			return;
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer arukod = Integer.valueOf(myRequest.getParameter("arukod").toString());
		if (arukod == null)
			return;

		QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount(), arukod);
		ProductVO aru = getProductService().getProductVOById(arukod, queryParamsVO);
		if (aru == null)
			return;
		aru.setMenny(getData().getMenny());
		List<ProductVO> cart = new ArrayList<ProductVO>();
		if (getData().getItems() != null && getData().getItems().size() > 0) {
			cart = getData().getItems();
		}
		List<ProductVO> temp = new ArrayList<ProductVO>();
		boolean contains = false;
		if (cart != null && cart.size() > 0) {
			for (ProductVO prod : cart) {
				if (aru.getArukod() == prod.getArukod()) {
					BigDecimal menny = prod.getMenny();
					prod.setMenny(menny.add(aru.getMenny()));
					contains = true;
				}
				temp.add(prod);
			}
		}
		if (!contains)
			temp.add(aru);
		cart = temp;
		getData().setItems(cart);
		BigDecimal allBrutto = new BigDecimal(0);

		for (ProductVO aruKisker : cart) {
//			float afa = (float) (aruKisker.getAfakodok().getFordkulcs() + 100) / 100;
//			BigDecimal brutto = aruKisker.getMenny().multiply(aruKisker.getEar().multiply(new BigDecimal(afa)));
//			if (kedvezmeny != null)
//				brutto = brutto.multiply(kedvezmeny).divide(new BigDecimal(100));
//			allBrutto = allBrutto.add(brutto);
			allBrutto = allBrutto.add(aruKisker.getMenny().multiply(aruKisker.getKedvarBrutto()));			
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(cart.size());
		addInfoMessage("", aru.getMenny() + " db " + aru.getMegn() + " a kosárba került.");
		getData().setMenny(new BigDecimal(1));
	}

	public String saveWishlist() {
		if (getManagedPartner() == null)
			return "login";
		if (getData().getItems() == null || getData().getItems().size() == 0) {
			return null;
		}
		if (getData().getWishlistName() == null || getData().getWishlistName().equals("")) {
			addErrorMessage("", "Kérem, adja meg a menteni kívánt terméklista nevét.");
			return null;
		}
		try {
			Wishlist wishlist = new Wishlist();
			//TODO
			//wishlist.setCustomer(getManagedPartner());
			wishlist.setInsDate(new Date());
			wishlist.setModDate(new Date());
			wishlist.setName(getData().getWishlistName());
		
			List<WishlistItem> items = new ArrayList<WishlistItem>();
			for (ProductVO tmp : getData().getItems()) {
				AruKisker aru = getProductService().getProductKiskerById(tmp.getArukod());
				if (aru != null) {
					WishlistItem item = new WishlistItem();
					item.setAru(aru);
					item.setQuantity(tmp.getMenny());
					item.setWishlist(wishlist);
					items.add(item);
				}
			}
			wishlist = getWishlistService().saveOrUpdateWishlist(wishlist);
			if (wishlist == null) {
				addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor.");
				logger.error("ERROR BY SAVING BASKET ON CART PAGE");
				return null;
			}
			for (WishlistItem wishlistItem : items) {
				wishlistItem = getWishlistService().saveOrUpdateWishlistItem(wishlistItem);
				if (wishlistItem == null) {
					addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor.");
					return null;
				}
			}
			addInfoMessage("", "Kosár tételeinek mentése sikeres.");
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor.");
			logger.error("ERROR BY SAVING BASKET ON CART PAGE", e);
			return null;
		}

		return null;
	}

	public String goToOrder() {
		if (getManagedPartner() == null)
			return "login";
		if (getData().getAllBrutto().compareTo(new BigDecimal(ConfigurationHandler.getMinimumOrderValue())) < 0) {
			// if (orderParameters.getMinimumOrderValue() == null)
			// return null;
			// if
			// (getData().getAllBrutto().compareTo(orderParameters.getMinimumOrderValue())
			// < 0) {
			// addErrorMessage("",
			// String.format("A rendelés leadásához legalább {0} Ft értékben szükséges vásárolnia! Hiányzik még: ",
			// orderParameters.getMinimumOrderValueAsFormattedString()) +
			// String.format("{0} Ft",
			// (orderParameters.getMinimumOrderValue().intValue() -
			// getData().getAllBrutto().intValue())));
			addErrorMessage(
					"",
					String.format("A rendelés leadásához legalább %s Ft értékben szükséges vásárolnia. Hiányzik még: ", ConfigurationHandler.getMinimumOrderValue())
							+ String.format("%d Ft", (ConfigurationHandler.getMinimumOrderValue() - getData().getAllBrutto().intValue())));
			return null;
		}
		return "order";
	}

	public void showSaveWishlistPanel(ActionEvent event) {
		getData().setWishlistName(null);
	}

	public CartControllerData getData() {
		return data;
	}

	public void setData(CartControllerData data) {
		this.data = data;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public WishlistServiceBase getWishlistService() {
		return wishlistService;
	}

	public void setWishlistService(WishlistServiceBase wishlistService) {
		this.wishlistService = wishlistService;
	}

}

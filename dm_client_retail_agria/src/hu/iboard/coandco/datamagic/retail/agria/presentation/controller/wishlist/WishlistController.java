package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.wishlist;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;
import hu.iboard.coandco.datamagic.retail.agria.presentation.controller.cart.CartController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.wishlistservice.WishlistService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "wishlistController")
@RequestScoped
public class WishlistController extends AbstractController {

	@ManagedProperty(value = "#{wishlistControllerData}")
	private WishlistControllerData data;
	@ManagedProperty(value = "#{wishlistService}")
	private WishlistService wishlistService;
	@ManagedProperty(value = "#{cartController}")
	private CartController cartController;
	@ManagedProperty(value = "#{productService}")
	private ProductService productService;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
			getData().setWishlists(getWishlistService().getWishlistsByCustomer(getManagedCustomer()));
			getData().setWishlistItems(new ArrayList<WishlistItem>());
		}
	}

	public void showWishlistItems(ActionEvent event) {
		if (getData().getSelectedWishlist() == null)
			return;
		getData().setWishlistItems(getWishlistService().getWishlistItemsByWishlistId(getData().getSelectedWishlist().getWishlistId()));
	}

	public void addWishlistToCart(ActionEvent event) {
		if (getData().getWishlistId() == null)
			return;
		List<WishlistItem> items = getWishlistService().getWishlistItemsByWishlistId(getData().getWishlistId());
		if (items == null)
			return;
		List<ProductVO> aruk = new ArrayList<ProductVO>();
		List<ProductVO> cart = getCartController().getData().getItems();
		//QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount());
		QueryParamsVO queryParamsVO = getQueryParamsVO();
		for (WishlistItem wishListItem : items) {
			ProductVO aru = getProductService().getProductVOById(wishListItem.getAru().getArukod(), queryParamsVO);
			if (aru != null) {
				aru.setMenny(wishListItem.getQuantity());
				aruk.add(aru);
			}
		}
		if (!cart.isEmpty()) {
			for (ProductVO prod : cart) {
				for (ProductVO tmp : aruk) {
					if (tmp.getArukod() ==  prod.getArukod()) {
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
		for (ProductVO aruKisker : getCartController().getData().getItems()) {
			allBrutto = allBrutto.add(aruKisker.getMenny().multiply(aruKisker.getBrutto()));			
		}
		getCartController().getData().setAllBrutto(allBrutto);
		getCartController().getData().setItemsSize(getCartController().getData().getItems().size());
		addInfoMessage("", "A tételek kosárba kerültek!");
	}

	public void deleteWishlist(ActionEvent event) {
		if (getData().getWishlistId() == null)
			return;
		try {
			Wishlist wishlist = getWishlistService().getWishlistById(getData().getWishlistId());
			if (wishlist != null) {
				List<WishlistItem> items = getWishlistService().getWishlistItemsByWishlistId(getData().getWishlistId());
				for (WishlistItem wishlistItem : items) {
					getWishlistService().deleteWishlistItem(wishlistItem);
				}
				getWishlistService().deleteWishlist(wishlist);
				getData().setWishlists(getWishlistService().getWishlistsByCustomer(getManagedCustomer()));
				addInfoMessage("", "Törölve a kívánságlistából!");
				return;
			}
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt a kívánságlista törlésekor!");
			logger.error("ERROR BY DELETE WISHLIST ON WISHLIST PAGE", e);
		}
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public WishlistControllerData getData() {
		return data;
	}

	public void setData(WishlistControllerData data) {
		this.data = data;
	}

	public WishlistService getWishlistService() {
		return wishlistService;
	}

	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}

	public CartController getCartController() {
		return cartController;
	}

	public void setCartController(CartController cartController) {
		this.cartController = cartController;
	}

}

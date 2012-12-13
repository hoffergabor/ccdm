package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.cart;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;
import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.ConfigurationHandler;
import hu.iboard.coandco.datamagic.service.productservice.ProductService;
import hu.iboard.coandco.datamagic.service.wishlistservice.WishlistService;

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
	private WishlistService wishlistService;

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
		addInfoMessage("", "Kosár kiürítve!");
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
//			BigDecimal brutto = aru.getMenny().multiply(aru.getDear2().multiply(new BigDecimal(afa)));
//			allBrutto = allBrutto.add(brutto);
//			temp.add(aru);
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));
			temp.add(aru);			
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", "+1 db " + getData().getSelectedItem().getMegn() + " került a kosárba!");
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
			if (aru.getArukod() == getData().getSelectedItem().getArukod()) {
				aru.setMenny(aru.getMenny().subtract(new BigDecimal(1)));
			}
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));
			temp.add(aru);
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", " 1 db " + getData().getSelectedItem().getMegn() + " kivéve a kosárból!");
	}

	public void deleteItem(ActionEvent event) {
		if (getData().getSelectedItem() == null)
			return;
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);
		for (ProductVO aru : getData().getItems()) {
			if (!(aru.getArukod() == getData().getSelectedItem().getArukod())) {
				allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));				
				temp.add(aru);
			}
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);
		addInfoMessage("", getData().getSelectedItem().getMegn() + " kikerült a kosárból!");
	}

	public void changeQuantity(ActionEvent event) {
		List<ProductVO> temp = new ArrayList<ProductVO>();
		BigDecimal allBrutto = new BigDecimal(0);
		for (ProductVO aru : getData().getItems()) {
			if (aru.getMenny().compareTo(new BigDecimal(1)) == -1) {
				aru.setMenny(new BigDecimal(1));
				addErrorMessage("", "Nem adhat meg 1-nél kisebb mennyiséget!");
			}
			allBrutto = allBrutto.add(aru.getMenny().multiply(aru.getBrutto()));
			temp.add(aru);
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(temp.size());
		getData().setItems(temp);

	}

	public void addProductToCart(ActionEvent event) {
		if (getData().getMenny().compareTo(new BigDecimal(1)) == -1) {
			addErrorMessage("", "Nem adhat meg 1-nél kisebb mennyiséget!");
			getData().setMenny(new BigDecimal(1));
			return;
		}
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest myRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		Integer arukod = Integer.valueOf(myRequest.getParameter("arukod").toString());
		if (arukod == null)
			return;
		ProductVO aru = getProductService().getProductVOById(arukod, getQueryParamsVO());
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
			allBrutto = allBrutto.add(aruKisker.getMenny().multiply(aruKisker.getBrutto()));
		}
		getData().setAllBrutto(allBrutto);
		getData().setItemsSize(cart.size());
		addInfoMessage("", aru.getMenny() + " db " + aru.getMegn() + " a kosárba került!");
		getData().setMenny(new BigDecimal(1));
	}

	public String saveWishlist() {
		if (getManagedCustomer() == null)
			return "login";
		if (getData().getItems() == null || getData().getItems().size() == 0) {
			return null;
		}
		if (getData().getWishlistName() == null || getData().getWishlistName().equals("")) {
			addErrorMessage("", "Kérem adja meg a menteni kívánt terméklista nevét!");
			return null;
		}
		try {
			Wishlist wishlist = new Wishlist();
			wishlist.setCustomer(getManagedCustomer());
			wishlist.setInsDate(new Date());
			wishlist.setModDate(new Date());
			wishlist.setName(getData().getWishlistName());
			//QueryParamsVO queryParamsVO = new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(), ConfigurationHandler.getAdaptDiscount());

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
				addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor!");
				logger.error("ERROR BY SAVING BASKET ON CART PAGE");
				return null;
			}
			for (WishlistItem wishlistItem : items) {
				wishlistItem = getWishlistService().saveOrUpdateWishlistItem(wishlistItem);
				if (wishlistItem == null) {
					addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor!");
					return null;
				}
			}
			addInfoMessage("", "Kosár tételeinek mentése sikeres!");
		} catch (Exception e) {
			addErrorMessage("", "Hiba történt a kosár tételeinek mentésekor!");
			logger.error("ERROR BY SAVING BASKET ON CART PAGE", e);
			return null;
		}

		return null;
	}

	public String goToOrder() {
		if (getManagedCustomer() == null)
			return "login";
		if (getData().getAllBrutto().compareTo(new BigDecimal(5000)) <= 0) {
			addErrorMessage("",
					"A rendelés leadásához legalább 5.000 Ft értékben szükséges vásárolnia! Hiányzik még: " + (5000 - (getData().getAllBrutto().intValue()))
							+ " Ft");
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

	public WishlistService getWishlistService() {
		return wishlistService;
	}

	public void setWishlistService(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}

}

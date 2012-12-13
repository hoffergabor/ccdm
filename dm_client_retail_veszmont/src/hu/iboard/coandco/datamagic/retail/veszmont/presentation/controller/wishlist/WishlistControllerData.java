package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.wishlist;

import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "wishlistControllerData")
@SessionScoped
public class WishlistControllerData implements Serializable{

	private static final long serialVersionUID = -7492408114995048205L;
	
	private Wishlist selectedWishlist; 
	private List<Wishlist> wishlists=new ArrayList<Wishlist>();
	private List<WishlistItem> wishlistItems=new ArrayList<WishlistItem>();
	private Integer wishlistId;

	public List<Wishlist> getWishlists() {
		return wishlists;
	}

	public void setWishlists(List<Wishlist> wishlists) {
		this.wishlists = wishlists;
	}

	public Wishlist getSelectedWishlist() {
		return selectedWishlist;
	}

	public void setSelectedWishlist(Wishlist selectedWishlist) {
		this.selectedWishlist = selectedWishlist;
	}

	public List<WishlistItem> getWishlistItems() {
		return wishlistItems;
	}

	public void setWishlistItems(List<WishlistItem> wishlistItems) {
		this.wishlistItems = wishlistItems;
	}

	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}
	
}

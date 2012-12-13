package hu.iboard.coandco.datamagic.service.wishlistservice;

import hu.iboard.coandco.datamagic.dao.wishlist.IWishlistDao;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;

import java.util.List;

public abstract class WishlistServiceBase {

	public IWishlistDao wishlistDao;

	public abstract Wishlist getWishlistById(Integer id);

	public abstract Wishlist saveOrUpdateWishlist(Wishlist wishlist);

	public abstract List<Wishlist> getWishlistsByCustomer(Customer customer);

	public abstract WishlistItem saveOrUpdateWishlistItem(WishlistItem item);

	public abstract List<WishlistItem> getWishlistItemsByWishlistId(Integer id);

	public abstract void deleteWishlist(Wishlist wishlist);

	public abstract void deleteWishlistItem(WishlistItem item);

	public IWishlistDao getWishlistDao() {
		return wishlistDao;
	}

	public void setWishlistDao (IWishlistDao wishlistDao) {
		this.wishlistDao = wishlistDao;
	}

}

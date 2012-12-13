package hu.iboard.coandco.datamagic.service.wishlistservice;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;

import java.util.List;

public class WishlistService extends WishlistServiceBase {

	@Override
	public Wishlist getWishlistById(Integer id) {
		if (id == null)
			return null;
		return getWishlistDao().getWishlistById(id);
	}

	@Override
	public List<Wishlist> getWishlistsByCustomer(Customer customer) {
		if (customer == null)
			return null;
		return getWishlistDao().getWishlistsByCustomer(customer);
	}

	@Override
	public Wishlist saveOrUpdateWishlist(Wishlist wishlist) {
		if (wishlist == null)
			return null;
		return getWishlistDao().saveOrUpdateWishlist(wishlist);
	}

	@Override
	public WishlistItem saveOrUpdateWishlistItem(WishlistItem item) {
		if (item == null)
			return null;
		return getWishlistDao().saveOrUpdateWishlistItem(item);
	}

	@Override
	public List<WishlistItem> getWishlistItemsByWishlistId(Integer id) {
		if (id == null)
			return null;
		return getWishlistDao().getWishlistItemsByWishlistId(id);
	}

	@Override
	public void deleteWishlist(Wishlist wishlist) {
		if (wishlist == null)
			return;
		getWishlistDao().deleteWishlist(wishlist);

	}

	@Override
	public void deleteWishlistItem(WishlistItem item) {
		if (item == null)
			return;
		getWishlistDao().deleteWishlistItem(item);

	}

}

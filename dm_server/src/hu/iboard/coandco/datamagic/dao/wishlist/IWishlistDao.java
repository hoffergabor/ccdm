package hu.iboard.coandco.datamagic.dao.wishlist;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;

import java.util.List;

public interface IWishlistDao {

	public Wishlist getWishlistById(Integer id);

	public Wishlist saveOrUpdateWishlist(Wishlist wishlist);

	public List<Wishlist> getWishlistsByCustomer(Customer customer);

	public WishlistItem saveOrUpdateWishlistItem(WishlistItem item);

	public List<WishlistItem> getWishlistItemsByWishlistId(Integer id);

	public void deleteWishlist(Wishlist wishlist);

	public void deleteWishlistItem(WishlistItem item);

}

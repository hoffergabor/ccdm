package hu.iboard.coandco.datamagic.dao.wishlist;

import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.List;

public interface IWishlistDao {

	public WishlistDist getWishlistById(Integer id);

	public WishlistDist saveOrUpdateWishlist(WishlistDist wishlist);

	public List<WishlistDist> getWishlistsByPartner(Integer vevokod);

	public WishlistItemDist saveOrUpdateWishlistItem(WishlistItemDist item);

	public List<WishlistItemDist> getWishlistItemsByWishlistId(Integer id);

	public void deleteWishlist(WishlistDist wishlist);

	public void deleteWishlistItem(WishlistItemDist item);
	
	public void deleteWishlistAndItemsById(Integer Id);
	
	public Boolean saveWishlist(List<ProductVO> products, Partner partner, String date);
}

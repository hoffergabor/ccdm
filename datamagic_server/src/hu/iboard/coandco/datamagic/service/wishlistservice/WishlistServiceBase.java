package hu.iboard.coandco.datamagic.service.wishlistservice;

import hu.iboard.coandco.datamagic.dao.product.IProductDao;
import hu.iboard.coandco.datamagic.dao.wishlist.IWishlistDao;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.List;

public abstract class WishlistServiceBase {

	public IProductDao productDao;
	
	public IWishlistDao wishlistDao;

	public abstract WishlistDist getWishlistById(Integer id);

	public abstract WishlistDist saveOrUpdateWishlist(WishlistDist wishlist);

	public abstract List<WishlistDist> getWishlistsByPartner(Integer vevokod);

	public abstract WishlistItemDist saveOrUpdateWishlistItem(WishlistItemDist item);

	public abstract List<WishlistItemDist> getWishlistItemsByWishlistId(Integer id);

	public abstract void deleteWishlist(WishlistDist wishlist);

	public abstract void deleteWishlistItem(WishlistItemDist item);
	
	public abstract void deleteWishlistAndItemsById(Integer Id);

	public abstract Boolean saveWishlist(List<ProductVO> products, Partner partner, String date);	
	
	public IWishlistDao getWishlistDao() {
		return wishlistDao;
	}

	public void setWishlistDao (IWishlistDao wishlistDao) {
		this.wishlistDao = wishlistDao;
	}

	public IProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(IProductDao productDao) {
		this.productDao = productDao;
	}
}

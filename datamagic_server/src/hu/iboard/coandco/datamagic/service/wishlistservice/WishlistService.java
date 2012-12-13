package hu.iboard.coandco.datamagic.service.wishlistservice;

import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WishlistService extends WishlistServiceBase {

	@Override
	public WishlistDist getWishlistById(Integer id) {
		if (id == null)
			return null;
		return getWishlistDao().getWishlistById(id);
	}

	@Override
	public List<WishlistDist> getWishlistsByPartner(Integer vevokod) {
		if (vevokod == null)
			return null;
		return getWishlistDao().getWishlistsByPartner(vevokod);
	}

	@Override
	public WishlistDist saveOrUpdateWishlist(WishlistDist wishlist) {
		if (wishlist == null)
			return null;
		return getWishlistDao().saveOrUpdateWishlist(wishlist);
	}

	@Override
	public WishlistItemDist saveOrUpdateWishlistItem(WishlistItemDist item) {
		if (item == null)
			return null;
		return getWishlistDao().saveOrUpdateWishlistItem(item);
	}

	@Override
	public List<WishlistItemDist> getWishlistItemsByWishlistId(Integer id) {
		if (id == null)
			return null;
		return getWishlistDao().getWishlistItemsByWishlistId(id);
	}

	@Override
	public void deleteWishlist(WishlistDist wishlist) {
		if (wishlist == null)
			return;
		getWishlistDao().deleteWishlist(wishlist);
	}

	@Override
	public void deleteWishlistItem(WishlistItemDist item) {
		if (item == null)
			return;
		getWishlistDao().deleteWishlistItem(item);
	}
	
	@Override
	public void deleteWishlistAndItemsById(Integer Id) {
		if (Id == null)
			return;
		getWishlistDao().deleteWishlistAndItemsById(Id);		
	}
	
	@Override
	public Boolean saveWishlist(List<ProductVO> products, Partner partner, String date) {
		if (products == null || products.size() < 1 || partner == null || date == null || date == "") {
			return null;
		}		
		try {
			WishlistDist wishlist = new WishlistDist();
			wishlist.setPartner(partner);
			wishlist.setInsDate(new Date());
			wishlist.setModDate(new Date());
			wishlist.setName("Vevőkód: " + partner.getVevokod() + ", Mentés dátuma: " + date);

			List<WishlistItemDist> items = new ArrayList<WishlistItemDist>();
			for (ProductVO tmp : products) {
				AruTemp aru = getProductDao().getAruTempById(tmp.getArukod());
				if (aru != null) {
					WishlistItemDist item = new WishlistItemDist();
					item.setAru(aru);
					item.setQuantity(tmp.getMennyiseg());
					item.setWishlist(wishlist);
					items.add(item);
				}
			}
			wishlist = saveOrUpdateWishlist(wishlist);
			if (wishlist == null) {
				return false;
			}
			for (WishlistItemDist wishlistItem : items) {
				wishlistItem = saveOrUpdateWishlistItem(wishlistItem);
				if (wishlistItem == null) {
					return false;
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}	
	
	
}

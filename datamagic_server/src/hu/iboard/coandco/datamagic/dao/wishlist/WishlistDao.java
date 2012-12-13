package hu.iboard.coandco.datamagic.dao.wishlist;

import hu.iboard.coandco.datamagic.generated.AruTemp;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.generated.WishlistDist;
import hu.iboard.coandco.datamagic.generated.WishlistItemDist;
import hu.iboard.coandco.datamagic.service.product.ProductServiceBase;
import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class WishlistDao extends HibernateDaoSupport implements IWishlistDao {

	private ProductServiceBase productService;
	
	@Override
	public WishlistDist getWishlistById(Integer id) {
		return (WishlistDist) getHibernateTemplate().get(WishlistDist.class, id);
	}

	@Override
	public WishlistDist saveOrUpdateWishlist(WishlistDist wishlist) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(wishlist);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return wishlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WishlistDist> getWishlistsByPartner(Integer vevokod) {
		List<WishlistDist> wishlists;
		try {
			String query = "from WishlistDist w where w.partner.vevokod=:vevokod";
			wishlists = getHibernateTemplate().findByNamedParam(query, "vevokod", vevokod);
			if (wishlists == null)
				return null;

		} catch (Exception e) {
			logger.error("ERROR AT GETTING WISHLIST FOR PARTNER!!!", e);
			return null;
		}
		return wishlists;
	}

	@Override
	public WishlistItemDist saveOrUpdateWishlistItem(WishlistItemDist item) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(item);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WishlistItemDist> getWishlistItemsByWishlistId(Integer id) {
		List<WishlistItemDist> items;
		try {
			String query = "from WishlistItemDist w where w.wishlist.wishlistId=:wishlistId";
			items = getHibernateTemplate().findByNamedParam(query, "wishlistId", id);
			if (items == null)
				return null;

		} catch (Exception e) {
			logger.error("ERROR AT GETTING WISHLIST ITEMS FOR WISHLIST!!!", e);
			return null;
		}
		return items;
	}

	@Override
	public void deleteWishlist(WishlistDist wishlist) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(wishlist);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void deleteWishlistItem(WishlistItemDist item) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(item);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}
	
	@Override
	public void deleteWishlistAndItemsById(Integer Id) {
		List<WishlistItemDist> items = getWishlistItemsByWishlistId(Id);
		if (items != null && items.size() > 0) {
			for (WishlistItemDist item : items) {
				deleteWishlistItem(item);
			}
		}
		WishlistDist list = getWishlistById(Id);
		if (list != null) {
			deleteWishlist(list);
		}
	}

	public Boolean saveWishlist(List<ProductVO> products, Partner partner, String date) {
		try {
			WishlistDist wishlist = new WishlistDist();
			wishlist.setPartner(partner);
			wishlist.setInsDate(new Date());
			wishlist.setModDate(new Date());
			wishlist.setName("Vevőkód: " + partner.getVevokod() + ", Mentés dátuma: " + date);

			List<WishlistItemDist> items = new ArrayList<WishlistItemDist>();
			for (ProductVO tmp : products) {
				AruTemp aru = getProductService().getAruTempById(tmp.getArukod());
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
				logger.error("ERROR SAVING PRODUCTS BY SESSION TIMEOUT");
				return false;
			}
			for (WishlistItemDist wishlistItem : items) {
				wishlistItem = saveOrUpdateWishlistItem(wishlistItem);
				if (wishlistItem == null) {
					return false;
				}
			}
		} catch (Exception e) {
			//addErrorMessage("", "Hiba történt a termékek mentésénél, session lejártakor.");
			logger.error("An error has been occured during the wishlist saving method.", e);
			return false;
		}
		return true;
	}

	public ProductServiceBase getProductService() {
		return productService;
	}

	public void setProductService(ProductServiceBase productService) {
		this.productService = productService;
	}
}

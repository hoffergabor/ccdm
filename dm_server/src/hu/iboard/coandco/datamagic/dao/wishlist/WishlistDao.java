package hu.iboard.coandco.datamagic.dao.wishlist;

import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;
import hu.iboard.coandco.datamagic.model.wishlistitem.WishlistItem;

import java.util.List;

import org.hibernate.FlushMode;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class WishlistDao extends HibernateDaoSupport implements IWishlistDao {

	@Override
	public Wishlist getWishlistById(Integer id) {
		return (Wishlist) getHibernateTemplate().get(Wishlist.class, id);
	}

	@Override
	public Wishlist saveOrUpdateWishlist(Wishlist wishlist) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(wishlist);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return wishlist;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Wishlist> getWishlistsByCustomer(Customer customer) {
		List<Wishlist> wishlists;
		try {
			String query = "from Wishlist w where w.customer.lakosKod=:lakosKod";
			wishlists = getHibernateTemplate().findByNamedParam(query, "lakosKod", customer.getLakosKod());
			if (wishlists == null)
				return null;

		} catch (Exception e) {
			logger.error("ERROR AT GETTING WISHLIST FOR CUSTOMER!!!", e);
			return null;
		}
		return wishlists;
	}

	@Override
	public WishlistItem saveOrUpdateWishlistItem(WishlistItem item) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().saveOrUpdate(item);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
		return item;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WishlistItem> getWishlistItemsByWishlistId(Integer id) {
		List<WishlistItem> items;
		try {
			String query = "from WishlistItem w where w.wishlist.wishlistId=:wishlistId";
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
	public void deleteWishlist(Wishlist wishlist) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(wishlist);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

	@Override
	public void deleteWishlistItem(WishlistItem item) {
		getHibernateTemplate().getSessionFactory().getCurrentSession().setFlushMode(FlushMode.AUTO);
		getHibernateTemplate().delete(item);
		getHibernateTemplate().getSessionFactory().getCurrentSession().flush();
	}

}

package hu.iboard.coandco.datamagic.model.wishlistitem;

import hu.iboard.coandco.datamagic.model.aru.AruKisker;
import hu.iboard.coandco.datamagic.model.wishlist.Wishlist;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WISHLIST_ITEMS")
public class WishlistItem implements Serializable {

	private static final long serialVersionUID = 1701296848178989090L;
	private Integer wishlistItemId;
	private BigDecimal quantity;
	private AruKisker aru;
	private Wishlist wishlist;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WISHLIST_ITEM_ID", unique = true, nullable = false)
	public Integer getWishlistItemId() {
		return wishlistItemId;
	}

	public void setWishlistItemId(Integer wishlistItemId) {
		this.wishlistItemId = wishlistItemId;
	}

	@Column(name = "QUANTITY")
	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "ARUKOD", name = "PRODUCT_ID")
	public AruKisker getAru() {
		return aru;
	}

	public void setAru(AruKisker aru) {
		this.aru = aru;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "WISHLIST_ID", name = "WISHLIST_ID")
	public Wishlist getWishlist() {
		return wishlist;
	}

	public void setWishlist(Wishlist wishlist) {
		this.wishlist = wishlist;
	}
}

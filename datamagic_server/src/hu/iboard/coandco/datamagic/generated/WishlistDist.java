package hu.iboard.coandco.datamagic.generated;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "WISHLIST_DIST")
public class WishlistDist implements Serializable {

	private static final long serialVersionUID = -414150391635456826L;
	private Integer wishlistId;
	private Date insDate;
	private Date modDate;
	private String name;
	private Partner partner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WISHLIST_ID", unique = true, nullable = false)
	public Integer getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(Integer wishlistId) {
		this.wishlistId = wishlistId;
	}

	@Column(name = "INSDATE")
	public Date getInsDate() {
		return insDate;
	}

	public void setInsDate(Date insDate) {
		this.insDate = insDate;
	}

	@Column(name = "MODDATE")
	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	@Column(name = "NAME", length = 60)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(columnDefinition = "VEVOKOD", name = "PARTNER_ID", insertable = true, updatable = true)
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

}

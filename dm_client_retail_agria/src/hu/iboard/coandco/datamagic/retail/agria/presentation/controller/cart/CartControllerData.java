package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.cart;

import hu.iboard.coandco.datamagic.vo.product.ProductVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "cartControllerData")
@SessionScoped
public class CartControllerData implements Serializable {

	private static final long serialVersionUID = -2920341115090438812L;

	private Integer itemsSize;
	private BigDecimal allBrutto;
	private List<ProductVO> items = new ArrayList<ProductVO>();
	private ProductVO selectedItem;
	private BigDecimal menny;
	private String wishlistName;

	public Integer getItemsSize() {
		return itemsSize;
	}

	public void setItemsSize(Integer itemsSize) {
		this.itemsSize = itemsSize;
	}

	public BigDecimal getAllBrutto() {
		return allBrutto;
	}

	public void setAllBrutto(BigDecimal allBrutto) {
		this.allBrutto = allBrutto;
	}

	public List<ProductVO> getItems() {
		return items;
	}

	public void setItems(List<ProductVO> items) {
		this.items = items;
	}

	public ProductVO getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(ProductVO selectedItem) {
		this.selectedItem = selectedItem;
	}

	public BigDecimal getMenny() {
		return menny;
	}

	public void setMenny(BigDecimal menny) {
		this.menny = menny;
	}

	public String getWishlistName() {
		return wishlistName;
	}

	public void setWishlistName(String wishlistName) {
		this.wishlistName = wishlistName;
	}
}

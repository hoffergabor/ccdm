package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.shippingservice;

import hu.iboard.coandco.datamagic.vo.shipping.ShippingItemVO;
import hu.iboard.coandco.datamagic.vo.shipping.ShippingVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "shippingserviceData")
@SessionScoped
public class ShippingServiceData implements Serializable{

	private static final long serialVersionUID = -5570577213920565254L;

	private List<ShippingVO> shippings = new ArrayList<ShippingVO>();
	private List<ShippingItemVO> shippingItems = new ArrayList<ShippingItemVO>();
	private ShippingVO selectedShipping;
	private Date from;
	private Date to;
	private Date chartFrom;
	private Date chartTo;
	private BigDecimal netto = null;
	private BigDecimal brutto = null;
	private Integer vevokod;
	private BigDecimal sum;
	private BigDecimal debit;
	private BigDecimal payed;
	private BigDecimal expired;
	private List<Object[]> chartList = new ArrayList<Object[]>();
	private String partnerSearchText;
	private Integer selectedPartnerId;
	private List<Object[]> allPartner = new ArrayList<Object[]>();
	
	public List<ShippingVO> getShippings() {
		return shippings;
	}
	public void setShippings(List<ShippingVO> shippings) {
		this.shippings = shippings;
	}
	public List<ShippingItemVO> getShippingItems() {
		return shippingItems;
	}
	public void setShippingItems(List<ShippingItemVO> shippingItems) {
		this.shippingItems = shippingItems;
	}
	public ShippingVO getSelectedShipping() {
		return selectedShipping;
	}
	public void setSelectedShipping(ShippingVO selectedShipping) {
		this.selectedShipping = selectedShipping;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public Date getChartFrom() {
		return chartFrom;
	}
	public void setChartFrom(Date chartFrom) {
		this.chartFrom = chartFrom;
	}
	public Date getChartTo() {
		return chartTo;
	}
	public void setChartTo(Date chartTo) {
		this.chartTo = chartTo;
	}
	public BigDecimal getNetto() {
		return netto;
	}
	public void setNetto(BigDecimal netto) {
		this.netto = netto;
	}
	public BigDecimal getBrutto() {
		return brutto;
	}
	public void setBrutto(BigDecimal brutto) {
		this.brutto = brutto;
	}
	public Integer getVevokod() {
		return vevokod;
	}
	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}
	public BigDecimal getSum() {
		return sum;
	}
	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}
	public BigDecimal getDebit() {
		return debit;
	}
	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}
	public BigDecimal getPayed() {
		return payed;
	}
	public void setPayed(BigDecimal payed) {
		this.payed = payed;
	}
	public BigDecimal getExpired() {
		return expired;
	}
	public void setExpired(BigDecimal expired) {
		this.expired = expired;
	}
	public List<Object[]> getChartList() {
		return chartList;
	}
	public void setChartList(List<Object[]> chartList) {
		this.chartList = chartList;
	}
	public String getPartnerSearchText() {
		return partnerSearchText;
	}
	public void setPartnerSearchText(String partnerSearchText) {
		this.partnerSearchText = partnerSearchText;
	}
	public Integer getSelectedPartnerId() {
		return selectedPartnerId;
	}
	public void setSelectedPartnerId(Integer selectedPartnerId) {
		this.selectedPartnerId = selectedPartnerId;
	}
	public List<Object[]> getAllPartner() {
		return allPartner;
	}
	public void setAllPartner(List<Object[]> allPartner) {
		this.allPartner = allPartner;
	}
	
}

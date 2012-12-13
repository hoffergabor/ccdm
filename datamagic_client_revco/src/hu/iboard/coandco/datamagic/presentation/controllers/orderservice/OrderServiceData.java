package hu.iboard.coandco.datamagic.presentation.controllers.orderservice;

import hu.iboard.coandco.datamagic.generated.Bizkomment;
import hu.iboard.coandco.datamagic.vo.order.OrderItemVO;
import hu.iboard.coandco.datamagic.vo.order.OrderVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "orderservicedata")
@SessionScoped
public class OrderServiceData implements Serializable {

	private static final long serialVersionUID = 7801113110014477919L;

	private List<Object[]> orders = new ArrayList<Object[]>();
	private List<OrderItemVO> orderItems = new ArrayList<OrderItemVO>();
	private Object[] selectedOrder;
	private Date from;
	private Date to;
	private Date chartFrom;
	private Date chartTo;
	private Boolean allOrder;
	private BigDecimal netto = null;
	private BigDecimal brutto = null;
	private Integer vevokod;
	private String mailTo;
	private List<Object[]> chartVOList = new ArrayList<Object[]>();
	private BigDecimal sumIn;
	private BigDecimal debitIn;
	private BigDecimal payedIn;
	private BigDecimal expiredIn;
	private Bizkomment bizkomment;
	private List<Object[]> allPartner = new ArrayList<Object[]>();
	private Integer selectedPartnerId;
	private String partnerSearchText;

	public List<OrderItemVO> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemVO> orderItems) {
		this.orderItems = orderItems;
	}

	public Object[] getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Object[] selectedOrder) {
		this.selectedOrder = selectedOrder;
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

	public Boolean getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(Boolean allOrder) {
		this.allOrder = allOrder;
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

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public List<Object[]> getChartVOList() {
		return chartVOList;
	}

	public void setChartVOList(List<Object[]> chartVOList) {
		this.chartVOList = chartVOList;
	}

	public BigDecimal getSumIn() {
		return sumIn;
	}

	public void setSumIn(BigDecimal sumIn) {
		this.sumIn = sumIn;
	}

	public BigDecimal getDebitIn() {
		return debitIn;
	}

	public void setDebitIn(BigDecimal debitIn) {
		this.debitIn = debitIn;
	}

	public BigDecimal getPayedIn() {
		return payedIn;
	}

	public void setPayedIn(BigDecimal payedIn) {
		this.payedIn = payedIn;
	}

	public BigDecimal getExpiredIn() {
		return expiredIn;
	}

	public void setExpiredIn(BigDecimal expiredIn) {
		this.expiredIn = expiredIn;
	}

	public Bizkomment getBizkomment() {
		return bizkomment;
	}

	public void setBizkomment(Bizkomment bizkomment) {
		this.bizkomment = bizkomment;
	}

	public List<Object[]> getAllPartner() {
		return allPartner;
	}

	public void setAllPartner(List<Object[]> allPartner) {
		this.allPartner = allPartner;
	}

	public Integer getSelectedPartnerId() {
		return selectedPartnerId;
	}

	public void setSelectedPartnerId(Integer selectedPartnerId) {
		this.selectedPartnerId = selectedPartnerId;
	}

	public String getPartnerSearchText() {
		return partnerSearchText;
	}

	public void setPartnerSearchText(String partnerSearchText) {
		this.partnerSearchText = partnerSearchText;
	}

	public List<Object[]> getOrders() {
		return orders;
	}

	public void setOrders(List<Object[]> orders) {
		this.orders = orders;
	}

}

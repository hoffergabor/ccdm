package hu.iboard.coandco.datamagic.presentation.controllers.invoiceservice;

import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "invoiceserviceData")
@SessionScoped
public class InvoiceServiceData implements Serializable {

	private static final long serialVersionUID = -6339257840126927260L;
	private List<InvoiceVO> invoice = new ArrayList<InvoiceVO>();
	private List<InvoiceItemVO> invoiceItems = new ArrayList<InvoiceItemVO>();
	private InvoiceVO selectedInvoice;
	private Date from;
	private Date to;
	private Date chartFrom;
	private Date chartTo;
	private Boolean allInvoice = false;
	private Boolean in = true;
	private Boolean out = true;
	private BigDecimal netto = new BigDecimal(0);
	private BigDecimal brutto = new BigDecimal(0);
	private Integer vevokod;
	private BigDecimal sumIn;
	private BigDecimal debitIn;
	private BigDecimal payedIn;
	private BigDecimal expiredIn;
	private BigDecimal sumOut;
	private BigDecimal debitOut;
	private BigDecimal payedOut;
	private BigDecimal expiredOut;
	private List<Object[]> chartInList = new ArrayList<Object[]>();
	private List<Object[]> chartOutList = new ArrayList<Object[]>();
	private List<InvoiceVO> tempInvoices = new ArrayList<InvoiceVO>();
	private String partnerSearchText;
	private Integer selectedPartnerId;
	private List<Object[]> allPartner = new ArrayList<Object[]>();

	public List<InvoiceVO> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceVO> invoice) {
		this.invoice = invoice;
	}

	public List<InvoiceItemVO> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(List<InvoiceItemVO> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public InvoiceVO getSelectedInvoice() {
		return selectedInvoice;
	}

	public void setSelectedInvoice(InvoiceVO selectedInvoice) {
		this.selectedInvoice = selectedInvoice;
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

	public Boolean getAllInvoice() {
		return allInvoice;
	}

	public void setAllInvoice(Boolean allInvoice) {
		this.allInvoice = allInvoice;
	}

	public Boolean getIn() {
		return in;
	}

	public void setIn(Boolean in) {
		this.in = in;
	}

	public Boolean getOut() {
		return out;
	}

	public void setOut(Boolean out) {
		this.out = out;
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

	public BigDecimal getSumOut() {
		return sumOut;
	}

	public void setSumOut(BigDecimal sumOut) {
		this.sumOut = sumOut;
	}

	public BigDecimal getDebitOut() {
		return debitOut;
	}

	public void setDebitOut(BigDecimal debitOut) {
		this.debitOut = debitOut;
	}

	public BigDecimal getPayedOut() {
		return payedOut;
	}

	public void setPayedOut(BigDecimal payedOut) {
		this.payedOut = payedOut;
	}

	public BigDecimal getExpiredOut() {
		return expiredOut;
	}

	public void setExpiredOut(BigDecimal expiredOut) {
		this.expiredOut = expiredOut;
	}

	public List<Object[]> getChartInList() {
		return chartInList;
	}

	public void setChartInList(List<Object[]> chartInList) {
		this.chartInList = chartInList;
	}

	public List<Object[]> getChartOutList() {
		return chartOutList;
	}

	public void setChartOutList(List<Object[]> chartOutList) {
		this.chartOutList = chartOutList;
	}

	public List<InvoiceVO> getTempInvoices() {
		return tempInvoices;
	}

	public void setTempInvoices(List<InvoiceVO> tempInvoices) {
		this.tempInvoices = tempInvoices;
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

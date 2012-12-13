package hu.iboard.coandco.datamagic.presentation.controllers.realtyinvoice;

import hu.iboard.coandco.datamagic.vo.invoice.InvoiceItemVO;
import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "realtyinvoiceserviceData")
@SessionScoped
public class RealtyInvoiceServiceData implements Serializable {

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
	private BigDecimal sumSum = null;
	private BigDecimal sumDebit = null;
	private BigDecimal sumPayed = null;
	private BigDecimal sumExpired = null;
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

	private Integer selectedOwnerId;
	private String ownerSearchText;
	private List<SelectItem> ownerItems;
	private List<SelectItem> allOwnerItems;
	private String renterSearchText;
	private Integer selectedRenterId;
	private List<SelectItem> renterItems;
	private List<SelectItem> allRentalItems;
	private String realtySearchText;
	private Integer selectedProjectId;
	private List<SelectItem> flatItems;
	private List<SelectItem> allFlatItems;
	private String filteredText;
	private List<InvoiceVO> invoiceFiltered = new ArrayList<InvoiceVO>();

	private List<Integer> names = new ArrayList<Integer>();
	private Float progress;

	public Float getProgress() {
		if (progress == null)
			progress = new Float(0);
		return progress;
	}

	public void setProgress(Float progress) {
		this.progress = progress;
	}

	public List<InvoiceVO> getInvoice() {
		return invoice;
	}

	public void setInvoice(List<InvoiceVO> invoice) {
		this.invoice = invoice;
	}

	public void setInvoiceFiltered(List<InvoiceVO> invoiceFiltered) {
		this.invoiceFiltered = invoiceFiltered;
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

	public BigDecimal getSumSum() {
		return sumSum;
	}

	public void setSumSum(BigDecimal sumSum) {
		this.sumSum = sumSum;
	}

	public BigDecimal getSumDebit() {
		return sumDebit;
	}

	public void setSumDebit(BigDecimal sumDebit) {
		this.sumDebit = sumDebit;
	}

	public BigDecimal getSumPayed() {
		return sumPayed;
	}

	public void setSumPayed(BigDecimal sumPayed) {
		this.sumPayed = sumPayed;
	}

	public BigDecimal getSumExpired() {
		return sumExpired;
	}

	public void setSumExpired(BigDecimal sumExpired) {
		this.sumExpired = sumExpired;
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

	public Integer getSelectedOwnerId() {
		return selectedOwnerId;
	}

	public void setSelectedOwnerId(Integer selectedOwnerId) {
		this.selectedOwnerId = selectedOwnerId;
	}

	public String getOwnerSearchText() {
		return ownerSearchText;
	}

	public void setOwnerSearchText(String ownerSearchText) {
		this.ownerSearchText = ownerSearchText;
	}

	public List<SelectItem> getOwnerItems() {
		return ownerItems;
	}

	public void setOwnerItems(List<SelectItem> ownerItems) {
		this.ownerItems = ownerItems;
	}

	public String getRenterSearchText() {
		return renterSearchText;
	}

	public void setRenterSearchText(String renterSearchText) {
		this.renterSearchText = renterSearchText;
	}

	public Integer getSelectedRenterId() {
		return selectedRenterId;
	}

	public void setSelectedRenterId(Integer selectedRenterId) {
		this.selectedRenterId = selectedRenterId;
	}

	public List<SelectItem> getRenterItems() {
		return renterItems;
	}

	public void setRenterItems(List<SelectItem> renterItems) {
		this.renterItems = renterItems;
	}

	public String getRealtySearchText() {
		return realtySearchText;
	}

	public void setRealtySearchText(String realtySearchText) {
		this.realtySearchText = realtySearchText;
	}

	public Integer getSelectedProjectId() {
		return selectedProjectId;
	}

	public void setSelectedProjectId(Integer selectedProjectId) {
		this.selectedProjectId = selectedProjectId;
	}

	public List<SelectItem> getFlatItems() {
		return flatItems;
	}

	public void setFlatItems(List<SelectItem> flatItems) {
		this.flatItems = flatItems;
	}

	public String getFilteredText() {
		return filteredText;
	}

	public void setFilteredText(String filteredText) {
		this.filteredText = filteredText;
	}

	public List<SelectItem> getAllRentalItems() {
		return allRentalItems;
	}

	public void setAllRentalItems(List<SelectItem> allRentalItems) {
		this.allRentalItems = allRentalItems;
	}

	public List<SelectItem> getAllOwnerItems() {
		return allOwnerItems;
	}

	public void setAllOwnerItems(List<SelectItem> allOwnerItems) {
		this.allOwnerItems = allOwnerItems;
	}

	public List<SelectItem> getAllFlatItems() {
		return allFlatItems;
	}

	public void setAllFlatItems(List<SelectItem> allFlatItems) {
		this.allFlatItems = allFlatItems;
	}

	public List<InvoiceVO> getInvoiceFiltered() {
		return invoiceFiltered;
	}

	public List<Integer> getNames() {
		return names;
	}

	public void setNames(List<Integer> names) {
		this.names = names;
	}

}

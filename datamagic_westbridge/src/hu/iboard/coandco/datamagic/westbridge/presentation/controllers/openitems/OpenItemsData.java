package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.openitems;

import hu.iboard.coandco.datamagic.vo.invoice.InvoiceVO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "openitemsData")
@SessionScoped
public class OpenItemsData implements Serializable {

	private static final long serialVersionUID = 1268888174683900599L;

	private Integer vevokod;
	private List<InvoiceVO> openItemList = new ArrayList<InvoiceVO>();
	private BigDecimal netto = null;
	private BigDecimal brutto = null;
	private BigDecimal arrears = null;
	private Date from;
	private Date to;
	private String partnerSearchText;
	private Integer selectedPartnerId;
	private List<Object[]> allPartner = new ArrayList<Object[]>();

	public Integer getVevokod() {
		return vevokod;
	}

	public void setVevokod(Integer vevokod) {
		this.vevokod = vevokod;
	}

	public List<InvoiceVO> getOpenItemList() {
		return openItemList;
	}

	public void setOpenItemList(List<InvoiceVO> openItemList) {
		this.openItemList = openItemList;
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

	public BigDecimal getArrears() {
		return arrears;
	}

	public void setArrears(BigDecimal arrears) {
		this.arrears = arrears;
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

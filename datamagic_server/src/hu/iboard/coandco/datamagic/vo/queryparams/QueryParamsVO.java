package hu.iboard.coandco.datamagic.vo.queryparams;

import hu.iboard.coandco.datamagic.generated.Partner;

public class QueryParamsVO {

	private Partner partner;
	private Integer defaultArkod;
	private Boolean adaptDiscount;
	private Integer megnevezesKod;

	public QueryParamsVO(Partner partner, Integer defaultArkod, Boolean adaptDiscount, Integer megnevezesKod) {
		this.partner = partner;
		this.defaultArkod = defaultArkod;
		this.adaptDiscount = adaptDiscount;
		this.megnevezesKod = megnevezesKod;
	}
	
	public Partner getPartner() {
		return partner;
	}
	
	public void setPartner(Partner partner) {
		this.partner = partner;
	}
	
	public Integer getDefaultArkod() {
		return defaultArkod;
	}
	
	public void setDefaultArkod(Integer defaultArkod) {
		this.defaultArkod = defaultArkod;
	}

	public Boolean getAdaptDiscount() {
		return adaptDiscount;
	}

	public void setAdaptDiscount(Boolean adaptDiscount) {
		this.adaptDiscount = adaptDiscount;
	}


	public Integer getMegnevezesKod() {
		return megnevezesKod;
	}

	public void setMegnevezesKod(Integer megnevezesKod) {
		this.megnevezesKod = megnevezesKod;
	}	
}

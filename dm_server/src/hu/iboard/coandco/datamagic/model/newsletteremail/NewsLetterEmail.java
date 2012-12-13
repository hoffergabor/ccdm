package hu.iboard.coandco.datamagic.model.newsletteremail;

import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.model.customer.Customer;

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
@Table(name = "NEWSLETTER_EMAIL")
public class NewsLetterEmail implements Serializable {

	private static final long serialVersionUID = 5314600542204681694L;

	private Integer newsLetterEmailId;
	private String email;
	private Date created;
	private Boolean active;
	private Customer customer;
	private Partner partner;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NEWSLETTER_EMAIL_ID", unique = true, nullable = false)
	public Integer getNewsLetterEmailId() {
		return newsLetterEmailId;
	}

	public void setNewsLetterEmailId(Integer newsLetterEmailId) {
		this.newsLetterEmailId = newsLetterEmailId;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "CREATED")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Column(name = "ACTIVE")
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "LAKOSKOD", name = "LAKOSVEVO_ID", insertable = true, updatable = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "VEVOKOD", name = "VEVOKOD_ID", insertable = true, updatable = false)
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;

	}
}

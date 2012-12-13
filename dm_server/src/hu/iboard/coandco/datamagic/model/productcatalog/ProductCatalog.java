package hu.iboard.coandco.datamagic.model.productcatalog;

import hu.iboard.coandco.datamagic.model.admin.Admin;

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
@Table(name = "PRODUCT_CATALOG")
public class ProductCatalog implements Serializable {

	private static final long serialVersionUID = 6525982089622338037L;

	private Integer productCatalogId;
	private Date insDate;
	private Date modDate;
	private String name;
	private String fileName;
	private Admin admin;
	private Integer arukod;
	private Integer sequence;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PRODUCT_CATALOG_ID", unique = true, nullable = false)
	public Integer getProductCatalogId() {
		return productCatalogId;
	}

	public void setProductCatalogId(Integer productCatalogId) {
		this.productCatalogId = productCatalogId;
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

	@Column(name = "FILENAME", length = 60)
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(columnDefinition = "ADMIN_ID", name = "ADMIN_ID", insertable = true, updatable = true)
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Column(name = "ARUKOD")
	public Integer getArukod() {
		return arukod;
	}

	public void setArukod(Integer arukod) {
		this.arukod = arukod;
	}

	@Column(name = "SEQUENCE")
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}

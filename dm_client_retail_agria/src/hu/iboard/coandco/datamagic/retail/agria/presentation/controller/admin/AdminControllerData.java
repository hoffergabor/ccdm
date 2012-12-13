package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.admin;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.content.Content;
import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.UploadedFile;

@ManagedBean(name = "adminControllerData")
@SessionScoped
public class AdminControllerData implements Serializable {

	private static final long serialVersionUID = -7522503773410132957L;

	private Content content;
	private List<Content> contentList = new ArrayList<Content>();
	private Boolean isUpdate = false;
	private Content selectedContent;
	private Boolean visiblePanel = false;
	private Boolean visibleAdminPanel = false;
	private List<Admin> admins = new ArrayList<Admin>();
	private Admin selectedAdmin;
	private Admin admin;
	private Boolean isAdminUpdate = false;
	private Boolean activeUser = false;
	private String userPassword1;
	private String userPassword2;
	private ProductCatalog productCatalog;
	private UploadedFile productCatalogFile;
	private List<ProductCatalog> productCatalogs = new ArrayList<ProductCatalog>();
	private ProductCatalog selectedProductCatalog;
	private Boolean updateProductCatalog;

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public List<Content> getContentList() {
		return contentList;
	}

	public void setContentList(List<Content> contentList) {
		this.contentList = contentList;
	}

	public Boolean getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}

	public Content getSelectedContent() {
		return selectedContent;
	}

	public void setSelectedContent(Content selectedContent) {
		this.selectedContent = selectedContent;
	}

	public Boolean getVisiblePanel() {
		return visiblePanel;
	}

	public void setVisiblePanel(Boolean visiblePanel) {
		this.visiblePanel = visiblePanel;
	}

	public List<Admin> getAdmins() {
		return admins;
	}

	public void setAdmins(List<Admin> admins) {
		this.admins = admins;
	}

	public Admin getSelectedAdmin() {
		return selectedAdmin;
	}

	public void setSelectedAdmin(Admin selectedAdmin) {
		this.selectedAdmin = selectedAdmin;
	}

	public Boolean getVisibleAdminPanel() {
		return visibleAdminPanel;
	}

	public void setVisibleAdminPanel(Boolean visibleAdminPanel) {
		this.visibleAdminPanel = visibleAdminPanel;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Boolean getIsAdminUpdate() {
		return isAdminUpdate;
	}

	public void setIsAdminUpdate(Boolean isAdminUpdate) {
		this.isAdminUpdate = isAdminUpdate;
	}

	public Boolean getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(Boolean activeUser) {
		this.activeUser = activeUser;
	}

	public String getUserPassword1() {
		return userPassword1;
	}

	public void setUserPassword1(String userPassword1) {
		this.userPassword1 = userPassword1;
	}

	public String getUserPassword2() {
		return userPassword2;
	}

	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}

	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}

	public void setProductCatalog(ProductCatalog productCatalog) {
		this.productCatalog = productCatalog;
	}

	public UploadedFile getProductCatalogFile() {
		return productCatalogFile;
	}

	public void setProductCatalogFile(UploadedFile productCatalogFile) {
		this.productCatalogFile = productCatalogFile;
	}

	public List<ProductCatalog> getProductCatalogs() {
		return productCatalogs;
	}

	public void setProductCatalogs(List<ProductCatalog> productCatalogs) {
		this.productCatalogs = productCatalogs;
	}

	public ProductCatalog getSelectedProductCatalog() {
		return selectedProductCatalog;
	}

	public void setSelectedProductCatalog(ProductCatalog selectedProductCatalog) {
		this.selectedProductCatalog = selectedProductCatalog;
	}

	public Boolean getUpdateProductCatalog() {
		return updateProductCatalog;
	}

	public void setUpdateProductCatalog(Boolean updateProductCatalog) {
		this.updateProductCatalog = updateProductCatalog;
	}

}

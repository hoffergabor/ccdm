package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.admin;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.content.Content;
import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;
import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.contentservice.ContentService;
import hu.iboard.coandco.datamagic.service.userservice.UserService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.TabChangeEvent;

@ManagedBean
@RequestScoped
public class AdminController extends AbstractController {

	@ManagedProperty(value = "#{adminControllerData}")
	private AdminControllerData data;
	@ManagedProperty(value = "#{contentService}")
	private ContentService contentService;
	@ManagedProperty(value = "#{userService}")
	private UserService userService;

	@PostConstruct
	public void init() {
		if (getManagedCustomer() != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index");
			} catch (IOException e) {
			}
		}
		if (!isPostback()) {
			resetData();
			getData().setContentList(new ArrayList<Content>());
			getData().setIsUpdate(false);
			getData().setVisiblePanel(false);
			getData().setUpdateProductCatalog(false);
			getData().setProductCatalog(new ProductCatalog());
			getData().getProductCatalog().setSequence(1);
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));

		}
	}

	private void resetData() {
		getData().setContent(new Content());
		getData().getContent().setVisible(true);
		getData().getContent().setPageName("news");
		getData().getContent().setLanguage("hu");
		getData().setAdmin(new Admin());
	}

	public void changeContentParam(ActionEvent event) {

		getData().setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
	}

	public String selectContentAction() {
		if (getData().getSelectedContent() == null)
			return null;
		getData().setVisiblePanel(true);
		getData().setContent(getData().getSelectedContent());
		getData().setIsUpdate(true);
		return null;
	}

	public String selectAdminAction() {
		if (getData().getSelectedAdmin() == null)
			return null;
		getData().setVisibleAdminPanel(true);
		getData().setAdmin(getData().getSelectedAdmin());
		getData().setIsAdminUpdate(true);
		return null;
	}

	public String saveContent() {
		if (getData().getContent() == null)
			return null;
		try {
			if (getData().getContent().getTitle() == null || getData().getContent().getSequence() == null || getData().getContent().getContent() == null
					|| getData().getContent().getTitle().equals("") || getData().getContent().getSequence().equals("")
					|| getData().getContent().getContent().equals("")) {
				addErrorMessage("", "A csillaggal jelölt mezők megadása kötelező.");
				return null;
			}
			getData().getContent().setAdmin(getManagedAdmin());
			if (!getData().getIsUpdate())
				getData().getContent().setCreated(new Date());
			getData().getContent().setModified(new Date());
			Content content = getContentService().saveOrUpdateContent(getData().getContent());
			if (content == null) {
				addErrorMessage("", "Tartalom mentése sikertelen.");
				return null;
			}
			addInfoMessage("", "Tartalom mentése sikeres.");
			if (!getData().getIsUpdate()) {
				resetData();
			}
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
		} catch (Exception e) {
			logger.error("ERROR BY SAVING CONTENT ON ADMIN PAGE.", e);
			addErrorMessage("", "Hiba történt a tartalom mentése közben.");
			return null;
		}
		getData().setVisiblePanel(false);
		return null;
	}

	public void deleteContent(ActionEvent event) {
		try {
			if (getData().getSelectedContent() == null)
				return;
			getContentService().deleteContent(getData().getSelectedContent());
			addInfoMessage("", "Tartalom törlése sikeres.");
			getData().setVisiblePanel(false);
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
		} catch (Exception e) {
			logger.error("ERROR BY DELETING CONTENT ON ADMIN PAGE.", e);
			addErrorMessage("", "Hiba történt a tartalom törlése közben.");
			return;
		}
	}

	public void saveAdmin(ActionEvent event) {

		try {
			if (getData().getAdmin() == null)
				return;
			if (getData().getUserPassword1().equals("") || getData().getUserPassword1() == null || getData().getUserPassword2().equals("")
					|| getData().getUserPassword2() == null) {
				addErrorMessage("", "Jelszómezők megadása kötelező");
				return;
			}
			if (!getData().getUserPassword1().equals(getData().getUserPassword2())) {
				addErrorMessage("", "A két jelszó nem egyezik!");
				return;
			}
			getData().getAdmin().setPassword(encodeWithMD5(getData().getUserPassword1()));
			if (getData().getAdmin().getName() == null || getData().getAdmin().getEmail() == null || getData().getAdmin().getPassword() == null
					|| getData().getAdmin().getName().equals("") || getData().getAdmin().getEmail().equals("") || getData().getAdmin().getPassword().equals("")) {
				addErrorMessage("", "A csillaggal jelölt mezők megadása kötelező!");
				return;
			}
			getData().getAdmin().setModDate(new Date());
			if (!getData().getIsAdminUpdate()) {
				getData().getAdmin().setInsDate(new Date());
			}
			Admin admin = getUserService().saveOrUpdateAdmin(getData().getAdmin());
			if (admin == null) {
				addErrorMessage("", "Admin mentése sikertelen.");
				return;
			}
			addInfoMessage("", "Admin mentése sikeres.");
			if (!getData().getIsAdminUpdate())
				getData().getAdmins().add(admin);
		} catch (Exception e) {
			logger.error("ERROR BY SAVING CONTENT ON ADMIN PAGE.", e);
			addErrorMessage("", "Hiba történt az admin mentése közben.");
			return;
		}
		getData().setVisibleAdminPanel(false);
	}

	public void addNewContent(ActionEvent event) {
		resetData();
		getData().setIsUpdate(false);
		getData().setVisiblePanel(true);
	}

	public void addNewAdmin(ActionEvent event) {
		getData().setAdmin(new Admin());
		getData().getAdmin().setActive(true);
		getData().setIsUpdate(false);
		getData().setUserPassword1(null);
		getData().setUserPassword2(null);
		getData().setVisibleAdminPanel(true);
	}

	public void onTabChange(TabChangeEvent event) {
		getData().setIsUpdate(false);
		if (event.getTab().getId().equals("contenttab")) {
			resetData();
			getData().setVisiblePanel(false);
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
		}

		if (event.getTab().getId().equals("usertab")) {
			getData().setVisibleAdminPanel(false);
			getData().setAdmins(new ArrayList<Admin>());
			getData().setAdmins(getUserService().getAllAdmin());
		}

		if (event.getTab().getId().equals("mainpagemanagertab")) {
			getData().setProductCatalogs(getContentService().getAllProductCatalog());
		}
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {
			String path = "";
			Integer index = event.getFile().getFileName().indexOf(".");
			if (event.getFile().getFileName().substring(index + 1).equals("pdf"))
				path = getRealPath() + "/files/pdf/agria_akcios_katalogus.pdf";
			if (event.getFile().getFileName().substring(index + 1).equals("xls"))
				path = getRealPath() + "/files/excel/agria_arlista.xls";
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(event.getFile().getContents());
			fos.close();
		} catch (FileNotFoundException ex) {
			logger.error("File not found by uploading file.", ex);
			addErrorMessage("Hiba", "Fájl nem található.");
			return;
		} catch (IOException ioe) {
			logger.error("Writing error by uploading file.", ioe);
			addErrorMessage("Hiba", "Hiba történt a fájl mentése közben.");
			return;
		} catch (Exception e) {
			logger.error("Writing error by uploading file.", e);
			addErrorMessage("Hiba", "Hiba történt a fájl mentése közben.");
			return;
		}
		addInfoMessage("", event.getFile().getFileName() + " feltöltése sikeres.");
		getData().setProductCatalogs(getContentService().getAllProductCatalog());
	}

	public void saveProductCatalog(ActionEvent event) {

		if (getData().getProductCatalog() == null)
			return;
		if (!getData().getUpdateProductCatalog()) {
			if (getData().getProductCatalogFile() == null || getData().getProductCatalogFile().getFileName().equals("")) {
				addErrorMessage("", "Kérem, válasszon fájlt!");
				return;
			}
		}
		try {
			if (!getData().getUpdateProductCatalog()) {
				FileOutputStream fos = new FileOutputStream(getRealPath() + "/files/catalog/" + getData().getProductCatalogFile().getFileName());
				fos.write(getData().getProductCatalogFile().getContents());
				fos.close();
				getData().getProductCatalog().setInsDate(new Date());
				getData().getProductCatalog().setFileName(getData().getProductCatalogFile().getFileName());
			}
			getData().getProductCatalog().setModDate(new Date());
			getData().getProductCatalog().setAdmin(getManagedAdmin());
			ProductCatalog catalog = getContentService().saveOrUpdateProductCatalog(getData().getProductCatalog());
			if (catalog == null) {
				addErrorMessage("", "Mentés sikeretelen.");
				logger.error("ERROR BY SAVING PRODUCT CATALOG ON ADMIN PAGE");
				return;
			}
			addInfoMessage("", "Mentés sikeres.");
			getData().setProductCatalog(new ProductCatalog());
			getData().getProductCatalog().setSequence(1);
			getData().setUpdateProductCatalog(false);
			getData().setProductCatalogs(getContentService().getAllProductCatalog());
		} catch (Exception e) {
			addErrorMessage("", "Mentés sikeretelen.");
			logger.error("ERROR BY SAVING PRODUCT CATALOG ON ADMIN PAGE", e);
			return;
		}
	}

	public String updateProductCatalog() {
		getData().setProductCatalog(getData().getSelectedProductCatalog());
		getData().setUpdateProductCatalog(true);
		return null;
	}

	public String deleteProductCatalog() {
		if (getData().getSelectedProductCatalog() == null)
			return null;
		getData().setProductCatalog(getData().getSelectedProductCatalog());
		try {
			getContentService().deleteProductcatalog(getData().getProductCatalog());
			String fileName = getRealPath() + "/files/catalog/" + getData().getProductCatalog().getFileName();
			File f = new File(fileName);
			if (!f.exists()) {
				addErrorMessage("", "Nincs meg a fájl.");
				throw new IllegalArgumentException("Delete: no such file or directory: " + fileName);
			}
			if (!f.canWrite()) {
				addErrorMessage("", "Nincs írási joga.");
				throw new IllegalArgumentException("Delete: write protected: " + fileName);
			}
			if (f.isDirectory()) {
				String[] files = f.list();
				if (files.length > 0)
					throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
				return null;
			}
			boolean success = f.delete();
			if (!success) {
				addErrorMessage("", "Fájl törlése sikeretelen.");
				logger.error("ERROR BY DELETE " + fileName + " ADMIN PRODUCT CATALOG PAGE!");
				throw new IllegalArgumentException("Delete: delete failed");
			} else {
				addInfoMessage("", "Fájl törlése sikeres.");
				getData().setUpdateProductCatalog(false);
				getData().setProductCatalogs(getContentService().getAllProductCatalog());
			}
		} catch (Exception e) {
			addErrorMessage("", "Fájl törlése sikeretelen!");
			logger.error("ERROR BY DELETE FILE ON ADMIN PRODUCT CATALOG PAGE!");
			return null;
		}
		return null;
	}

	public AdminControllerData getData() {
		return data;
	}

	public void setData(AdminControllerData data) {
		this.data = data;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}

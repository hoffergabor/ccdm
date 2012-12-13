package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.admin;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.content.Content;
import hu.iboard.coandco.datamagic.model.productcatalog.ProductCatalog;
import hu.iboard.coandco.datamagic.retail.veszmont.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.contentservice.ContentServiceBase;
import hu.iboard.coandco.datamagic.service.userservice.UserServiceBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
	private ContentServiceBase contentService;
	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;

	@PostConstruct
	public void init() {
		if (getManagedPartner() != null) {
			try {
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index");
			} catch (IOException e) {
			}
		}
		if (!isPostback()) {
			resetData();
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

	public void saveContent(ActionEvent event) {
		if (getData().getContent() == null)
			return;
		try {

			getData().getContent().setAdmin(getManagedAdmin());
			if (!getData().getIsUpdate())
				getData().getContent().setCreated(new Date());
			getData().getContent().setModified(new Date());
			Content content = getContentService().saveOrUpdateContent(getData().getContent());
			if (content == null) {
				addErrorMessage("", "Tartalom mentése sikertelen.");
				return;
			}
			addInfoMessage("", "Tartalom mentése sikeres.");
			if (!getData().getIsUpdate())
				resetData();
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
		} catch (Exception e) {
			logger.error("ERROR BY SAVING CONTENT ON ADMIN PAGE!", e);
			addErrorMessage("", "Hiba történt a tartalom mentése közben.");
			return;
		}
	}

	public void saveAdmin(ActionEvent event) {
		if (getData().getAdmin() == null)
			return;
		try {

		} catch (Exception e) {
			logger.error("ERROR BY SAVING CONTENT ON ADMIN PAGE!", e);
			addErrorMessage("", "Hiba történt a tartalom mentése közben.");
			return;
		}
	}

	public void addNewContent(ActionEvent event) {
		resetData();
		getData().setIsUpdate(false);
		getData().setVisiblePanel(true);
	}

	public void addNewAdmin(ActionEvent event) {
		getData().setAdmin(new Admin());
		getData().setIsUpdate(false);
		getData().setVisibleAdminPanel(true);
	}

	public void onTabChange(TabChangeEvent event) {

		if (event.getTab().getId().equals("contenttab")) {
			resetData();
			getData().setIsUpdate(false);
			getData().setVisiblePanel(false);
			getData()
					.setContentList(getContentService().getContentByPageName(getData().getContent().getPageName(), getData().getContent().getLanguage(), true));
		}

		if (event.getTab().getId().equals("usertab")) {
			getData().setVisibleAdminPanel(false);
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
			logger.error("File not found by uloadin file", ex);
			addErrorMessage("Hiba", "Fájl nem található.");
			return;
		} catch (IOException ioe) {
			logger.error("Writing error by uploading file", ioe);
			addErrorMessage("Hiba", "Hiba történt a fájl mentése közben.");
			return;
		} catch (Exception e) {
			logger.error("Writing error by uploading file", e);
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
			addErrorMessage("", "Fájl törlése sikeretelen.");
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

	public ContentServiceBase getContentService() {
		return contentService;
	}

	public void setContentService(ContentServiceBase contentService) {
		this.contentService = contentService;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}
}

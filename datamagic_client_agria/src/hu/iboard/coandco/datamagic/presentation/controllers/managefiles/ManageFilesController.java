package hu.iboard.coandco.datamagic.presentation.controllers.managefiles;

import hu.iboard.coandco.datamagic.presentation.util.AbstractController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

@ManagedBean(name = "managefilesController")
@RequestScoped
public class ManageFilesController extends AbstractController {

	public static final String MANAGEFILES_ACTION = "managefiles";

	@ManagedProperty(value = "#{managefilesData}")
	private ManageFilesData data;

	@Override
	public void loadData() {
		initTreeNode();
		getData().setDir("");
		getData().setPath("");
		getData().setSelectedNode(null);
		getData().setLastSelectedNode(null);

	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(MANAGEFILES_CONTROLLER);

	}

	private void initTreeNode() {

		getData().setRoot(new DefaultTreeNode("Root", null));
		File dir = new File(getRealPath() + "/files");
		if (!dir.exists())
			return;
		File[] list = dir.listFiles();
		for (File f : list) {
			TreeNode uj = new DefaultTreeNode(f.getName(), getData().getRoot());
			rekurzio(f, uj);
		}
	}

	public void rekurzio(File f, TreeNode uj) {
		if (f.listFiles() != null) {
			for (File file : f.listFiles()) {
				TreeNode uj2 = new DefaultTreeNode(file.getName(), uj);
				rekurzio(file, uj2);
			}
		}
	}

	public void onNodeexpand(NodeExpandEvent event) {

		getData().setPath(getData().getPath() + "/" + event.getTreeNode().getData().toString());
	}

	public void onNodeSelect(NodeSelectEvent event) {
		if (getData().getSelectedNode().equals(getData().getLastSelectedNode()))
			return;
		TreeNode temp = getData().getSelectedNode();
		getData().setLastSelectedNode(getData().getSelectedNode());
		String path = "";
		do {
			if (temp.getParent().getData().equals("Root")) {
				path = path + getData().getSelectedNode();
				break;
			} else {
				temp = temp.getParent();
				path = temp.getData() + "/" + path;
			}
		} while (!temp.getData().equals("Root"));

		File dir = new File(getRealPath() + "/files/" + path);
		getData().setPath(path);
		File[] list = dir.listFiles();

		if (dir.isFile()) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "KIJELOLVE", event.getTreeNode().getData()
					.toString());
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}

		if (list == null)
			return;

		// for (File f : list) {
		// new DefaultTreeNode(f.getName(), getData().getSelectedNode());
		// }
	}

	public void deleteItem(ActionEvent event) {

		String fileName = getRealPath() + "/files/" + getData().getPath();
		File f = new File(fileName);

		if (!f.exists())
			throw new IllegalArgumentException("Delete: no such file or directory: " + fileName);

		if (!f.canWrite())
			throw new IllegalArgumentException("Delete: write protected: " + fileName);

		// If it is a directory, make sure it is empty
		if (f.isDirectory()) {
			String[] files = f.list();
			if (files.length > 0)
				throw new IllegalArgumentException("Delete: directory not empty: " + fileName);
		}

		// Attempt to delete it
		boolean success = f.delete();

		if (!success)
			throw new IllegalArgumentException("Delete: deletion failed");
		initTreeNode();
		addInfoMessage("", String.format("A %s nevű fálj sikeresen törlésre került!", fileName));
	}

	public String createDir() {

		if (getData().getDir().equals("") || getData().getDir() == null)
			return null;
		try {
			String tempdir = getData().getPath() + "/" + getData().getDir();
			int number = count(tempdir, '/');
			if (number > 5) {
				addErrorMessage("", "Több mappát nem hozhat létre.");
				return null;
			}

			String dir = getRealPath() + "/files/" + getData().getPath() + "/" + getData().getDir();

			boolean success = (new File(dir)).mkdir();
			if (success) {
				addInfoMessage("", getData().getDir() + "A mappa létrehozása megtörtént.");
				initTreeNode();
			} else {
				addErrorMessage("", "A mappa létrehozása közben hiba történt. Kérem, próbálja meg újra.");
				logger.error("error by creating directory " + getData().getDir());
			}
		} catch (Exception e) {// Catch exception if any
			addErrorMessage("", "A mappa létrehozása közben hiba történt. Kérem, próbálja meg újra.");
			logger.error("error by creating directory " + getData().getDir(), e);
			return null;
		}

		return null;
	}

	public String fileDownload() {
		try {
			File file = new File(getRealPath() + "/files/" + getData().getPath());
			if (file.isFile()) {
				commonFileDownloader(file.getName(), file.getParent());
			} else {
				addErrorMessage("", "A kiválasztott elemnek fájlnak kell lennie.");
				return null;
			}
			addInfoMessage("", "A fálj letöltése befejeződött.");
		} catch (Exception e) {
			addErrorMessage("", "File letöltése közben hiba történt.");
			return null;
		}
		return null;
	}

	public int count(String sourceString, char lookFor) {
		if (sourceString == null) {
			return -1;
		}
		int count = 0;
		for (int i = 0; i < sourceString.length(); i++) {
			final char c = sourceString.charAt(i);
			if (c == lookFor) {
				count++;
			}
		}
		return count;
	}

	public void initCreateDir(ActionEvent event) {
		getData().setDir("");
	}

	public void gotoRoot(ActionEvent event) {
		getData().setDir("");
		getData().setPath("");
	}

	public void handleFileUpload(FileUploadEvent event) {

		try {
			if (event.getFile().getFileName().equals(""))
				return;
			UploadedFile item = event.getFile();

			if (item == null) {
				return;
			}
			String path = getRealPath() + "/files/" + getData().getPath() + "/" + item.getFileName();
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(item.getContents());
			fos.close();
			initTreeNode();

		} catch (FileNotFoundException ex) {
			logger.error("FileNotFoundException by upload file: " + ex);
			addErrorMessage("", "Nem található a fájl.");
			return;
		} catch (IOException ioe) {
			logger.error("IOException by upload file: " + ioe);
			addErrorMessage("", String.format("Nincs írási joga ehhez a könyvtárhoz: %s", getRealPath() + "/files/" + getData().getPath() + "/"));
			return;
		} catch (Exception ex) {
			logger.error("Error by uploading file: ", ex);
			addErrorMessage("", "Hiba történt a fájl feltöltése közben.");
			return;
		}

	}

	public ManageFilesData getData() {
		return data;
	}

	public void setData(ManageFilesData data) {
		this.data = data;
	}

}

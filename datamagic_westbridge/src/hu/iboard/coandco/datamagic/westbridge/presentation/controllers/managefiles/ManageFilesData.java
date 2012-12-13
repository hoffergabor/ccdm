package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.managefiles;

import java.io.File;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;


@ManagedBean(name = "managefilesData")
@SessionScoped
public class ManageFilesData implements Serializable{

	private static final long serialVersionUID = -7298040291145492388L;
	
	private TreeNode root;
	private TreeNode selectedNode;
	private TreeNode lastSelectedNode;
	private String path;
	private String dir;
	private File file;
	private StreamedContent downloadableFile;  

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public TreeNode getLastSelectedNode() {
		return lastSelectedNode;
	}

	public void setLastSelectedNode(TreeNode lastSelectedNode) {
		this.lastSelectedNode = lastSelectedNode;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public StreamedContent getDownloadableFile() {
		return downloadableFile;
	}

	public void setDownloadableFile(StreamedContent downloadableFile) {
		this.downloadableFile = downloadableFile;
	}
	
}

package hu.iboard.coandco.datamagic.westbridge.presentation.util;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * A preview manager oszt�ly
 * 
 * 
 */
@ManagedBean(name = "previewManager")
@RequestScoped
public class PreviewManager {

	private List<String> prevPages = new ArrayList<String>();

	/**
	 * �j oldal hozz�ad�sa a list�hoz
	 * 
	 * @param page
	 */
	public void addNewPage(String page) {
		if (!isPrevPageActivated(page)) {
			prevPages.add(page);
		}
	}

	/**
	 * Megadja az utolj�r megn�zett oldal nev�t
	 * 
	 * @return
	 */
	public String getLastPage() {
		// if there is no previous page
		if (prevPages.size() < 2) {
			return null;
		}
		return prevPages.get(prevPages.size() - 2);
	}

	/**
	 * Megadja, hogy az el�z� oldal aktiv�lva lett-e
	 * 
	 * @param actPage
	 * @return
	 */
	public boolean isPrevPageActivated(String actPage) {
		if (actPage == null) {
			return false;
		}
		// if there is no previous page
		if (prevPages.size() < 2) {
			return false;
		}
		return actPage.equalsIgnoreCase(prevPages.get(prevPages.size() - 2));
	}

	/**
	 * Visszair�ny�t az el�z� oldalra
	 * 
	 * @return
	 */
	public String cancelAction() {
		return getLastPage();
	}
}

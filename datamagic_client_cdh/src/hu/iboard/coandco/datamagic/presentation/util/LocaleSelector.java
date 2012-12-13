package hu.iboard.coandco.datamagic.presentation.util;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;

@ManagedBean
@SessionScoped
public class LocaleSelector extends AbstractController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5608527341737045948L;
	private static Logger logger = Logger.getLogger(LocaleSelector.class);
	private Locale selectedLocale;
	private String lang = "hu";

	/**
	 * 
	 */
	public LocaleSelector() {
		selectedLocale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
	}

	public Locale getSelectedLocale() {
		return selectedLocale;

	}

	public void setSelectedLocale(Locale selectedLocale) {
		this.selectedLocale = selectedLocale;
	}

	public void setLocale(ValueChangeEvent event) {
		String lang = event.getNewValue().toString();
		this.selectedLocale = new Locale(lang);
		logger.info("New locale has been set: " + this.selectedLocale.getLanguage());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.selectedLocale);
	}

	public String setLocaleHU() {
		this.selectedLocale = new Locale("hu");
		logger.info("New locale has been set: " + this.selectedLocale.getLanguage());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.selectedLocale);
		return null;
	}

	public String setLocaleEN() {
		this.selectedLocale = new Locale("en");
		logger.info("New locale has been set: " + this.selectedLocale.getLanguage());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.selectedLocale);
		return null;
	}

	public void select() {
		getSelectedLocale();
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	@Override
	public void loadData() {
		setSelectedLocale(new Locale("hu"));

	}

	@Override
	public void reloadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetData() {
		// TODO Auto-generated method stub

	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}

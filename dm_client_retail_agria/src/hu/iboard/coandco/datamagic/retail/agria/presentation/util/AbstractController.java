package hu.iboard.coandco.datamagic.retail.agria.presentation.util;

import hu.iboard.coandco.datamagic.model.admin.Admin;
import hu.iboard.coandco.datamagic.model.customer.Customer;
import hu.iboard.coandco.datamagic.model.partner.Partner;
import hu.iboard.coandco.datamagic.retail.agria.presentation.controller.login.LoginManager;
import hu.iboard.coandco.datamagic.vo.queryparams.QueryParamsVO;

import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public abstract class AbstractController implements Constants {

	protected Logger logger = Logger.getLogger(this.getClass());

	public boolean isPostback() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if (facesContext.getRenderResponse())
			return false;
		else
			return true;
	}

	public String getRealPath() {
		FacesContext context = FacesContext.getCurrentInstance();
		String realPath = ((ServletContext) context.getExternalContext().getContext()).getRealPath("/");

		return realPath;
	}

	public String getCurrentUrl() {
		FacesContext context = FacesContext.getCurrentInstance();
		return context.getExternalContext().getRequestContextPath();
	}

	public Partner getManagedPartner() {
		return getLoginManager().getData().getLoggedInPartner();
	}

	public Customer getManagedCustomer() {
		return getLoginManager().getData().getLoggedInCustomer();

	}

	public Admin getManagedAdmin() {
		return getLoginManager().getData().getLoggedInAdmin();

	}

	protected LoginManager getLoginManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		LoginManager loginManager = (LoginManager) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "loginManager");
		return loginManager;
	}

	protected String encodeWithMD5(String text) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(text.getBytes());
		byte byteData[] = md.digest();

		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}

		// convert the byte to hex format method 2
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			String hex = Integer.toHexString(0xff & byteData[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}

	protected String stringKonverter(String s) {
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++)
			switch (c[i]) {
			case 'á':
				sb.append("a");
				break;
			case 'Á':
				sb.append("A");
				break;
			case 'é':
				sb.append("e");
				break;
			case 'É':
				sb.append("E");
				break;
			case 'í':
				sb.append("i");
				break;
			case 'Í':
				sb.append("I");
				break;
			case 'ö':
				sb.append("o");
				break;
			case 'Ö':
				sb.append("O");
				break;
			case 'ó':
				sb.append("o");
				break;
			case 'Ó':
				sb.append("O");
				break;
			case 'ő':
				sb.append("o");
				break;
			case 'Ő':
				sb.append("O");
				break;
			case 'ü':
				sb.append("u");
				break;
			case 'Ü':
				sb.append("u");
				break;
			case 'ú':
				sb.append("u");
				break;
			case 'Ú':
				sb.append("u");
				break;
			case 'ű':
				sb.append("u");
				break;
			case 'Ű':
				sb.append("u");
				break;
			default:
				sb.append(c[i]);
			}
		return sb.toString();
	}

	protected void addErrorMessage(String summary, Exception e) {
		addFatalMessage(summary, e.getMessage());
	}

	protected void addErrorMessage(String summary) {
		addFatalMessage(summary, "");
	}

	protected void addErrorMessage(Exception e) {
		addFatalMessage("Error occured", e.getMessage());
	}

	protected void addFatalMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
	}

	protected void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	protected void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	protected void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	@SuppressWarnings("deprecation")
	public String convertDateTosString(Date date) {

		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "-" + dateFromMonth + "-" + dateFromDay;

		return convertedDate;
	}

	protected void fileDownloader(byte[] fileContent, String fileName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext facesContext = context.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getResponse();
		response.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		response.setContentLength(fileContent.length);
		response.setContentType("application/pdf");
		OutputStream output = null;

		try {
			output = response.getOutputStream();
		} catch (IOException e) {
			addFatalMessage("", e.getMessage());
			return;
		}
		try {
			output.write(fileContent);
		} catch (IOException e) {
			addFatalMessage("", e.getMessage());
			return;
		}
		try {
			output.flush();
			output.close();
		} catch (IOException e) {

			addFatalMessage("", e.getMessage());
			return;
		}
		// close context
		context.responseComplete();
	}

	public Date getCurrentDate() {

		return new Date();
	}

	public String getLanguage() {
		LocaleSelector local = new LocaleSelector();
		return local.getLang();
	}

	public QueryParamsVO getQueryParamsVO() {
		return new QueryParamsVO(getManagedPartner(), ConfigurationHandler.getDefaultArnevKod(),ConfigurationHandler.getAdaptDiscount(), ConfigurationHandler.getMegnevezesKod());
	}	
	
	protected String getTextFromPropertiesFile(String textKey, String fileName) {
		return getTextFromPropertiesFile(textKey, fileName, null);
		}

	protected String getTextFromPropertiesFile(String textKey, String fileName, Object[] parameter) {
		return Messages.getString(fileName, textKey, parameter);
		}

	protected String getTextFromPropertiesFileByLocale(String textKey, String fileName, Locale locale) {
		return getTextFromPropertiesFileByLocale(textKey, fileName, locale, null);
		}

	protected String getTextFromPropertiesFileByLocale(String textKey, String fileName, Locale locale, Object[] parameter) {
		return Messages.getStringByLocale(fileName, textKey, locale, parameter);
		}

}

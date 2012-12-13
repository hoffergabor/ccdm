package hu.iboard.coandco.datamagic.presentation.util;

import hu.iboard.coandco.datamagic.generated.Dolgozo;
import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.presentation.controllers.loginservice.LoginManager;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Base Class for all Controller classes
 * 
 * @author Cydrel Developer
 * 
 */
/**
 * 
 */
public abstract class AbstractController extends AbstractBaseDatabase {

	/**
	 * The method is called on every page load (if isPostback is false)
	 */
	public abstract void loadData();

	/**
	 * The method is called on every page reload (if isPostback is true)
	 */
	public abstract void reloadData();

	/**
	 * 
	 */
	public abstract void resetData();

	public String getRealPath() {

		FacesContext context = FacesContext.getCurrentInstance();
		String realPath = ((ServletContext) context.getExternalContext().getContext()).getRealPath("/");

		return realPath;
	}

	/**
	 * Get the remote IP adress
	 * 
	 * @return
	 */
	public String getReomoteIP() {
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());

		String ipAddr = request.getHeader("X-FORWARDED-FOR");
		if (ipAddr == null || ipAddr.trim().length() == 0) {
			ipAddr = request.getRemoteHost();
		}
		return ipAddr;
	}

	/**
	 * Lek�rdezi a managed system user-t
	 * 
	 * @return
	 */
	public UserVO getManagedUser() {

		return getLoginManager().getData().getLoggedInUser();
	}

	public Partner getManagedPartner() {

		return getLoginManager().getData().getLoggedInPartner();

	}

	public Dolgozo getManagedWorker() {

		return getLoginManager().getData().getLoggedInWorker();

	}

	public Partner getManagedRenter() {

		return getLoginManager().getData().getLoggedInRenter();

	}

	public Boolean getEmergency() {

		return getLoginManager().getData().getEmergency();
	}

	/**
	 * Return the loginManager
	 * 
	 * @return
	 */
	protected LoginManager getLoginManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		LoginManager loginManager = (LoginManager) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, "loginManager");
		return loginManager;
	}

	/**
	 * Visszaadja a request param�tereit egy map-ben
	 * 
	 * @return
	 */
	protected Map getRequestMap() {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	}

	/**
	 * Lek�r egy param�tert a request-b�l
	 * 
	 * @param key
	 *            - a k�rt param�ter neve
	 * 
	 * @return
	 */
	protected String getDataFromRequestMap(String key) {
		Map reqMap = getRequestMap();
		if ((reqMap != null) && (reqMap.get(key) != null)) {
			return reqMap.get(key).toString();
		}

		return null;
	}

	/**
	 * Az elozo oldal nevet adja vissza
	 * 
	 * @return
	 */
	protected String previousPage() {
		FacesContext ctx = FacesContext.getCurrentInstance();
		PreviewManager previewManager = (PreviewManager) ctx.getApplication().getELResolver().getValue(ctx.getELContext(), null, PREVIEW_MANAGER);
		return previewManager.getLastPage();
	}

	/**
	 * Jelszďż˝ kďż˝dolďż˝sa
	 * 
	 * @param text
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
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

	protected String getWorkerPassword(String password) {

		String WSzov = String.format("%1$-" + 10 + "s", password.toUpperCase());
		String XPar2 = "9182736455";
		int wh = 10;
		String ellenjelszo = "";
		for (int i = 0; i < wh; i++) {
			// ellenjelszo=ellenjelszo+

		}

		return WSzov;
	}

	/**
	 * Store data in the context
	 * 
	 * @param key
	 * @param value
	 */
	protected void storeDataBetweenPages(Map<String, Object> storedData) {
		for (String key : storedData.keySet()) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, storedData.get(key));
		}
	}

	/**
	 * Store data in the context
	 * 
	 * @param key
	 * @param value
	 */
	protected void storeDataBetweenPages(String key, Object value) {
		// storedData hashmap is used for 'post' data between pages
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(key, value);
	}

	/**
	 * Get stored data from context
	 * 
	 * @param key
	 * 
	 * @return
	 */
	protected Object getDataFromPreviousPage(String key) {
		// get the data which the previous page 'posted'
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(key);
	}

	/**
	 * Remove data from context
	 * 
	 * @param key
	 */
	protected void removeDataFromPreviousPage(String key) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(key);
	}

	/**
	 * Add error message to the context
	 * 
	 * @param summary
	 * @param e
	 */
	protected void addErrorMessage(String summary, Exception e) {
		addFatalMessage(summary, e.getMessage());
	}

	/**
	 * Add error message to the context
	 * 
	 * @param summary
	 * @param e
	 */
	protected void addErrorMessage(String summary) {
		addFatalMessage(summary, "");
	}

	/**
	 * Hozz�ad egy hiba�zenetet a context-hez
	 * 
	 * @param e
	 */
	protected void addErrorMessage(Exception e) {
		addFatalMessage("Error occured", e.getMessage());
	}

	/**
	 * Add fatal message to the context
	 * 
	 * @param summary
	 * @param detail
	 */
	protected void addFatalMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_FATAL, summary, detail);
	}

	/**
	 * Add error message to the context
	 * 
	 * @param summary
	 * @param detail
	 */
	protected void addErrorMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
	}

	/**
	 * Add info message to the context
	 * 
	 * @param summary
	 * @param detail
	 */
	protected void addInfoMessage(String summary, String detail) {
		addMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	}

	/**
	 * Add info message to the context
	 * 
	 * @param summary
	 * @param detail
	 */
	protected void addInfoMessage(String detail) {
		addInfoMessage(getTextFromPropertiesFile("info", MSGFILE), detail);
	}

	/**
	 * Hozz�ad egy �zenetet a context-hez
	 * 
	 * @param severity
	 * @param summary
	 * @param detail
	 */
	protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
		FacesMessage message = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Hozz�ad egy �zenetet a context-hez
	 * 
	 * @param message
	 */
	protected void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	/**
	 * Remove the bean from sesson
	 * 
	 * @param beanName
	 */
	protected void removeBeanFromSession(String beanName) {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(beanName);
	}

	/**
	 * Get the ben from session
	 * 
	 * @param beanName
	 * @return
	 */
	protected Object getBeanFromSession(String beanName) {
		return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(beanName);
	}

	protected Object getControllerFromContext(String controllerName) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		return ctx.getApplication().getELResolver().getValue(ctx.getELContext(), null, controllerName);
	}

	/**
	 * Add bean to the session
	 * 
	 * @param beanName
	 * @return
	 */
	protected Object addNewBeanForSession(String beanName) {
		return FacesContext.getCurrentInstance().getApplication().getExpressionFactory()
				.createValueExpression(FacesContext.getCurrentInstance().getELContext(), "#{" + beanName + "}", Object.class)
				.getValue(FacesContext.getCurrentInstance().getELContext());
	}

	/**
	 * Return the last page name
	 * 
	 * @return
	 */
	protected String lastPage() {
		// return with the previous page's name
		FacesContext fc = FacesContext.getCurrentInstance();
		PreviewManager pvm = (PreviewManager) fc.getApplication().getELResolver().getValue(fc.getELContext(), null, PREVIEW_MANAGER);
		return pvm.getLastPage();
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

	@SuppressWarnings("deprecation")
	public String convertDateTosString2(Date date) {

		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "." + dateFromMonth + "." + dateFromDay + ".";

		return convertedDate;
	}

	@SuppressWarnings("deprecation")
	public String convertDateTosString3(Date date) {

		String dateFromYear = Integer.valueOf(1900 + date.getYear()).toString();
		String dateFromMonth = Integer.valueOf(1 + date.getMonth()).toString();
		if (dateFromMonth.length() == 1) {
			dateFromMonth = "0" + dateFromMonth;
		}
		String dateFromDay = Integer.valueOf(date.getDate()).toString();
		if (dateFromDay.length() == 1) {
			dateFromDay = "0" + dateFromDay;
		}
		String convertedDate = dateFromYear + "-" + dateFromMonth + "-" + dateFromDay + " 00:00:00.0";

		return convertedDate;
	}

	/**
	 * Writes the byte[] arry to the HttpServletResponse
	 * 
	 * @param fileContent
	 * @param fileName
	 */
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
			String err = getStandardErrorMessage("downloadError");
			addFatalMessage(err, e.getMessage());
			return;
		}
		try {
			output.write(fileContent);
		} catch (IOException e) {
			String err = getStandardErrorMessage("downloadError");
			addFatalMessage(err, e.getMessage());
			return;
		}
		try {
			output.flush();
			output.close();
		} catch (IOException e) {
			String err = getStandardErrorMessage("downloadError");
			addFatalMessage(err, e.getMessage());
			return;
		}
		// close context
		context.responseComplete();
	}

	protected void commonFileDownloader(String fileName, String path) {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext facesContext = context.getExternalContext();
		HttpServletResponse response = (HttpServletResponse) facesContext.getResponse();
		response.reset();
		response.addHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		response.setContentType("application/force-download");
		byte[] buf = new byte[8096];
		try {
			File file = new File(path + "/" + fileName);
			long length = file.length();
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			OutputStream out = response.getOutputStream();
			response.setContentLength((int) length);
			while ((in != null) && ((length = in.read(buf)) != -1)) {
				out.write(buf, 0, (int) length);
			}
			in.close();
			out.flush();
			out.close();
		} catch (Exception exc) {
			logger.error("Error by download common file!", exc);
		}
		context.responseComplete();
	}
	
	public String URLstringKonverter(String s) {
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++)
			switch (c[i]) {
			case 'á':
				break;
			case 'Á':
				break;
			case 'é':
				break;
			case 'É':
				break;
			case 'í':
				break;
			case 'Í':
				break;
			case 'ó':
				break;
			case 'Ó':
				break;
			case 'ö':
				break;
			case 'Ö':
				break;
			case 'ő':
				break;
			case 'Ő':
				break;
			case 'ú':
				break;
			case 'Ú':
				break;
			case 'ű':
				break;
			case 'Ű':
				break;
			case '?':
				break;				
			default:
				if ((byte)c[i] >= 0)
					sb.append(c[i]);
			}
		return sb.toString();
	}	
	
	public String stringKonverter(String s) {
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
			case 'ó':
				sb.append("o");
				break;
			case 'Ó':
				sb.append("O");
				break;
			case 'ö':
				sb.append("o");
				break;
			case 'Ö':
				sb.append("O");
				break;
			case 'ő':
				sb.append("o");
				break;
			case 'Ő':
				sb.append("O");
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
			case '1':
				sb.append("X");
			case '0':
				sb.append("X");				
			case '?':
				break;				
			default:
				if ((byte)c[i] >= 0)
					sb.append(c[i]);
			}
		return sb.toString();
	}

	public Date getCurrentDate() {

		return new Date();
	}

}

package hu.iboard.coandco.datamagic.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Configurator {

	protected Logger logger = Logger.getLogger(this.getClass());
	public static final Configurator INSTANCE = new Configurator();

	// config file
	public final String CONFIGFILE = "config/config.xml";

	// ftp parameters
	public String ftpHost = "";
	public String ftpPort = "0";
	public String ftpUser = "";
	public String ftpPass = "";
	public String ftpDownloadPath = "";

	public Configurator() {
		updateVars();
	}

	public Configurator(String fileName) {
		updateVars(fileName);
	}

	public static Configurator getInstance() {
		return INSTANCE;
	}

	private Boolean updateVars() {
		return updateVars(CONFIGFILE);
	}

	private Boolean updateVars(String fileName) {
		try {
			// loading the xml file
			File xmlfile = new File(fileName);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlfile);
			// normalize text representation
			doc.getDocumentElement().normalize();

			// ftp parameters
			setFtpHost(doc.getElementsByTagName("ftp_host").item(0).getFirstChild().getNodeValue());
			setFtpPort(doc.getElementsByTagName("ftp_port").item(0).getFirstChild().getNodeValue());
			setFtpUser(doc.getElementsByTagName("ftp_user").item(0).getFirstChild().getNodeValue());
			setFtpPass(doc.getElementsByTagName("ftp_pass").item(0).getFirstChild().getNodeValue());
			setFtpDownloadPath(doc.getElementsByTagName("ftp_download_path").item(0).getFirstChild().getNodeValue());

		} catch (SAXParseException pe) {
			logger.error("Error by parsing config.xml", pe);
			return false;
		} catch (SAXException e) {
			logger.error("Sax exception on loading config.xml", e);
			return false;
		} catch (IllegalArgumentException ae) {
			logger.error("illegal argument on loading config.xml", ae);
			return false;
		} catch (IOException ie) {
			logger.error("IO exception on loading config.xml", ie);
			return false;

		} catch (Throwable t) {
			logger.error("could not load config.xml", t);
			t.printStackTrace();
			return false;
		}
		return true;
	}

	public String getFtpHost() {
		return ftpHost;
	}

	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUser() {
		return ftpUser;
	}

	public void setFtpUser(String ftpUser) {
		this.ftpUser = ftpUser;
	}

	public String getFtpPass() {
		return ftpPass;
	}

	public void setFtpPass(String ftpPass) {
		this.ftpPass = ftpPass;
	}

	public String getFtpDownloadPath() {
		return ftpDownloadPath;
	}

	public void setFtpDownloadPath(String ftpDownloadPath) {
		this.ftpDownloadPath = ftpDownloadPath;
	}

}

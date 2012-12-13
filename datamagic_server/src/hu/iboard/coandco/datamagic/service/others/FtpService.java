package hu.iboard.coandco.datamagic.service.others;

import hu.iboard.coandco.datamagic.util.Configurator;
import hu.iboard.coandco.datamagic.util.Decoder;
import hu.iboard.coandco.datamagic.util.InputStreamDecoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.primefaces.model.UploadedFile;

import de.schlichtherle.util.zip.ZipEntry;
import de.schlichtherle.util.zip.ZipFile;
import de.schlichtherle.util.zip.ZipOutputStream;

public class FtpService {

	protected Logger logger = Logger.getLogger(this.getClass());
	private Configurator config;
	private FTPClient client;

	private String server = "";
	private int port = 0;
	private String user = "";
	private String pass = "";
	private String filePath = "";

	/**
	 * @throws IOException
	 * 
	 */
	public FtpService() {

	}

	private void initFTPService(String path) throws IOException {
		setConfig(new Configurator(path));
		setServer(getConfig().getFtpHost());
		setPort(Integer.parseInt(getConfig().getFtpPort()));
		setUser(getConfig().getFtpUser());
		setPass(getConfig().getFtpPass());
		setFilePath(getConfig().getFtpDownloadPath());

		setClient(new FTPClient());
		getClient().connect(getServer(), getPort());
		int reply = getClient().getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			getClient().disconnect();
		}
		getClient().login(getUser(), getPass());
		getClient().syst();
		// getClient().setRemoteVerificationEnabled(false);
		// getClient().type(FTPClient.STREAM_TRANSFER_MODE);
		// getClient().setFileType(FTP.STREAM_TRANSFER_MODE ,
		// FTP.STREAM_TRANSFER_MODE );
		getClient().addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		getClient().enterLocalActiveMode();
		getClient().setFileType(FTPClient.BINARY_FILE_TYPE);

	}

	public void disconnect() throws IOException {
		getClient().disconnect();
	}

	public List<String> downloadDecodeUnzip(String md5FileName, Decoder decoder, String path) throws Exception {
		initFTPService(path + "config/config.xml");
		logger.info("downloadDecodeUnzip()");

		List<String> files = new ArrayList<String>();

		getClient().enterLocalPassiveMode();
		InputStream is = getClient().retrieveFileStream(md5FileName);
		if (is == null)
			return null;
		BufferedInputStream bis = new BufferedInputStream(is);
		// set up decoder
		InputStreamDecoder isd = new InputStreamDecoder(bis, decoder);
		// unzip
		int size = 2048;
		int bytes;
		byte[] b = new byte[size];
		File target = new File(path + "downloads/" + md5FileName);
		target.createNewFile();

		FileOutputStream zip = new FileOutputStream(target);
		zip.write(isd.read());
		while ((bytes = isd.read(b, 0, size)) != -1) {
			zip.write(b, 0, bytes);
		}
		zip.flush();
		zip.close();
		// isd.close();
		// bis.close();
		is.close();
		getClient().logout();

		//ZipFile file = new ZipFile(path + "downloads/" + md5FileName);
		ZipFile file = new ZipFile(path + "downloads/" + md5FileName, "UTF-8");

		Enumeration<?> entries = file.entries();

		// unzip all the files, and put the names into a list
		while (entries.hasMoreElements()) {
			ZipEntry entry = (ZipEntry) entries.nextElement();
			files.add(stringKonverter(entry.getName()));
			InputStream entryis = file.getInputStream(entry);
			BufferedInputStream entrybis = new BufferedInputStream(entryis);
			FileOutputStream unzipped = new FileOutputStream(path + "downloads/" + stringKonverter(entry.getName()));
			BufferedOutputStream unzippedbos = new BufferedOutputStream(unzipped);
			int read = 0;
			int len = 2048;
			byte[] data = new byte[len];
			while ((read = entrybis.read(data, 0, len)) != -1) {
				unzippedbos.write(data, 0, read);
			}
			unzipped.flush();
			unzippedbos.close();
			unzippedbos.flush();
			unzippedbos.close();
		}
		return files;
	}

	public boolean downloadWithDecode(String fileName, Decoder decoder) throws IOException {
		InputStream is = getClient().retrieveFileStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(is);
		// set up decoder
		InputStreamDecoder isd = new InputStreamDecoder(bis, decoder);
		// unzip
		int size = 2048;
		int bytes;
		byte[] b = new byte[size];
		FileOutputStream fos = new FileOutputStream(getFilePath() + fileName);
		while ((bytes = isd.read(b, 0, size)) != -1) {
			fos.write(b, 0, bytes);
		}
		fos.flush();
		fos.close();
		isd.close();
		bis.close();
		is.close();
		return true;
	}

	public boolean download(String fileName) throws IOException {
		InputStream is = getClient().retrieveFileStream(fileName);
		BufferedInputStream bis = new BufferedInputStream(is);

		int size = 2048;
		int bytes;
		byte[] b = new byte[size];
		FileOutputStream fos = new FileOutputStream(getFilePath() + fileName);
		while ((bytes = bis.read(b, 0, size)) != -1) {
			fos.write(b, 0, bytes);
		}
		fos.flush();
		fos.close();
		bis.close();
		is.close();
		return true;
	}
	
	public String uploadCode(List<UploadedFile> files, Decoder decoder, String path) throws Exception {

		String name = encodeWithMD5(Long.valueOf(new Date().getTime()).toString());

		try {

			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path + "downloads/" + name));

			for (UploadedFile file : files) {
				ZipEntry entry = new ZipEntry(file.getFileName());
				out.putNextEntry(entry);
				out.write(file.getContents());
				out.closeEntry();
			}
			out.finish();
			out.close();

			int size = 2048;
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path + "downloads/" + name));
			InputStreamDecoder isd = new InputStreamDecoder(bis, decoder);

			int bytes;
			byte[] b = new byte[size];
			File target = new File(path + "downloads/" + name + ".encrypted");
			target.createNewFile();

			FileOutputStream zip = new FileOutputStream(target);
			zip.write(isd.read());
			while ((bytes = isd.read(b, 0, size)) != -1) {
				zip.write(b, 0, bytes);
			}
			zip.flush();
			zip.close();
			// bis.close();
			// isd.close();

			initFTPService(path + "config/config.xml");
			logger.info("uploadCode()");
			getClient().enterLocalActiveMode();
			getClient().setFileType(FTPClient.BINARY_FILE_TYPE);
			getClient().storeFile(name + ".encrypted", new FileInputStream(path + "downloads/" + name + ".encrypted"));
			getClient().logout();

			File zipFile = new File(path + "downloads/" + name);
			if (zipFile.exists()) {
				zipFile.delete();
			}
			File codedFile = new File(path + "downloads/" + name + ".encrypted");
			if (codedFile.exists()) {
				codedFile.delete();
			}
		} catch (Exception e) {
			logger.error("Error by uploading notification file", e);
			return null;
		}
		return name;
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
			case 'ü':
				sb.append("u");
				break;
			case 'Ü':
				sb.append("u");
				break;
			case 'ű':
				sb.append("u");
				break;
			case 'Ű':
				sb.append("u");
				break;	
			case '?':
				break;
			default:
				if ((byte)c[i] >= 0)
					sb.append(c[i]);
			}
		return sb.toString();
	}
	
	public Configurator getConfig() {
		return config;
	}

	public void setConfig(Configurator config) {
		this.config = config;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public FTPClient getClient() {
		return client;
	}

	public void setClient(FTPClient client) {
		this.client = client;
	}

}

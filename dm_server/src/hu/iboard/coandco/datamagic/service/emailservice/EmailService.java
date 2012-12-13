package hu.iboard.coandco.datamagic.service.emailservice;

import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;
import hu.iboard.coandco.datamagic.util.Constants;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

public class EmailService extends EmailServiceBase implements Constants {

	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public NewsLetterEmail getNewsLetterEmailByEmail(String email) {
		if (email == null)
			return null;
		return getNewsLetterEmailDao().getNewsLetterEmailByEmail(email);
	}

	@Override
	public NewsLetterEmail saveOrUpdateNewsLetterEmail(NewsLetterEmail email) {
		if (email == null)
			return null;
		return getNewsLetterEmailDao().saveOrUpdateNewsLetterEmail(email);
	}

	@Override
	public boolean sendEmail(String host, String user, String pass, String from, String to, String subject, String body) {
		logger.info("Try to send e-mail");
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", host);
			props.setProperty("mail.user", user);
			props.setProperty("mail.password", pass);
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.submitter", user);

			// if (port != null)
			// props.setProperty("mail.smtp.port", port);

			Authenticator authenticator = new Authenticator(user, pass);

			Session mailSession = Session.getDefaultInstance(props, authenticator);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSender(new InternetAddress(from));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(body, "text/html; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("EMAIL SENDING WAS SUCCESSFUL!");
		} catch (Exception e) {
			logger.error("EMAIL SENDING FAILED!", e);
			return false;
		}
		return true;
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator(String user, String password) {
			authentication = new PasswordAuthentication(user, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}

	@Override
	public boolean sendNewPasswordEmail(String host, String port, String user, String pass, String from, String to, String subject, String body, String newPass) {
		logger.info("Try to send new password e-mail");
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", host);
			props.setProperty("mail.user", user);
			props.setProperty("mail.password", pass);
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.submitter", user);
			props.setProperty("mail.smtp.port", port);
			/*props.setProperty("mail.smtp.starttls.enable", "true");*/

			/*
			 * Customer customer = getUserService().getCustomerByEmail(to); if
			 * (customer == null) {
			 * logger.error("NEW PASSWORD SENDING - NO SUCH USER IN DATABASE: "
			 * + to); return false; }
			 * customer.setWebPassw(encodeWithMD5(newpass)); customer =
			 * getUserService().saveOrUpdateCustomer(customer); if (user ==
			 * null) {
			 * logger.error("NEW PASSWORD SENDING - ERROR SAVING PASSWORD: " +
			 * to); return false; }
			 */

			body += "<br /><strong>Új jelszava:</strong> " + newPass;
			body += "<br /><br />Üdvözlettel: Agria Drink Kft.";

			Authenticator authenticator = new Authenticator(user, pass);

			Session mailSession = Session.getDefaultInstance(props, authenticator);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSender(new InternetAddress(from));
			message.setFrom(new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(body, "text/html; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("PASSWORD EMAIL SENDING WAS SUCCESSFUL!");
		} catch (Exception e) {
			logger.error("PASSWORD EMAIL SENDING FAILED!", e);
			return false;
		}
		return true;
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
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

}

package hu.iboard.coandco.datamagic.service.email;

import hu.iboard.coandco.datamagic.util.DataMagicConstants;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

import org.apache.log4j.Logger;

public class EmailService extends EmailServiceBase implements DataMagicConstants {

	protected Logger logger = Logger.getLogger(this.getClass());

	public Boolean sendEmail(String host, String user, String pass, String emailFrom, String emailTo, String subject, String body) {
		return sendEmail(host, user, pass, emailFrom, emailTo, subject, body, null);
	}
	
	public Boolean sendEmail(String host, String user, String pass, String emailFrom, String emailTo, String subject, String body, String port) {
		logger.info("Try to send e-mail");
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.host", host);
			props.setProperty("mail.user", user);
			props.setProperty("mail.password", pass);			
			props.setProperty("mail.smtp.auth", "true");
			props.setProperty("mail.smtp.submitter", user);
			
			if (port != null)
				props.setProperty("mail.smtp.port", port);

			Authenticator authenticator = new Authenticator(user, pass);
			
			
			Session mailSession = Session.getDefaultInstance(props, authenticator);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSender(new InternetAddress(emailFrom));
			message.setFrom(new InternetAddress(emailFrom));
			message.setSubject(subject);
			message.setContent(body, "text/html; charset=UTF-8");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("EMAIL SENDING WAS SUCCESSFUL!");
		} catch (Exception e) {
			logger.error("EMAIL SENDING FAILED!", e);
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
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

}

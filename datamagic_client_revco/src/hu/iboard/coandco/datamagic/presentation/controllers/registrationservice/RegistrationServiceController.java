package hu.iboard.coandco.datamagic.presentation.controllers.registrationservice;

import hu.iboard.coandco.datamagic.generated.Partner;
import hu.iboard.coandco.datamagic.presentation.util.AbstractController;
import hu.iboard.coandco.datamagic.service.user.UserServiceBase;
import hu.iboard.coandco.datamagic.vo.user.UserVO;

import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@ManagedBean(name = "registrationserviceController")
@RequestScoped
public class RegistrationServiceController extends AbstractController {

	public static final String REGISTRATIONSERVICE_ACTION = "registration";

	@ManagedProperty(value = "#{registrationserviceData}")
	private RegistrationServiceData data;

	@ManagedProperty(value = "#{userService}")
	private UserServiceBase userService;

	@Override
	public void loadData() {
		getData().setPartner(new Partner());
		getData().setUser(new UserVO());

	}

	@Override
	public void reloadData() {

	}

	@Override
	public void resetData() {
		removeBeanFromSession(REGISTRATIONSEERVICE_CONTROLLER);

	}

	public String registrationAction() {

		if (!getData().getPassword1().equals(getData().getPassword2())) {
			addErrorMessage("A KET JELSZO NEM EGYEZIK!", "");
			getData().setPassword1("");
			getData().setPassword2("");
			return null;
		}

		// getData().getUser().setUserPassword(getData().getPassword1());

		if (!isCheckDataOK()) {
			addErrorMessage("AZ OSSZES MEZO KITOLTESE KOTELEZO!");
			return null;
		}

		// UserVO vo =
		// getUserService().getuserByUserName(getData().getUser().getUserName());
		// if (vo != null) {
		// addErrorMessage("ILYEN NEVU FELHASZNALO VAN MAR AZ ADATBAZISBAN!KEREM VALASSZON UJ FELHASZNALONEVET");
		// return null;
		// }

		try {
			getData().getUser().setUserType(PARTNER);
			boolean isSuccess = getUserService().registerUser(getData().getUser(), getData().getPartner());
			if (isSuccess) {
				addInfoMessage("REGISZTRACIO SIKERES!", "");
				logger.info("REGISTRATION WAS SUCCESSFUL");
				boolean isEmailSend = sendMail(getData().getPartner().getEmail(), getData().getPartner().getNev());
				if (isEmailSend) {
					addInfoMessage("AKTIVALO EMAILT ELKULDTUK!", "");
					logger.info("registration activate email ws sending");
				} else {
					addErrorMessage("AKTIVALO EMAIL KULDESE SIKERTELEN", "");
					logger.error("error by sending activate registration email!");
				}
			} else {
				addErrorMessage("REGISZTRACIO SIKERTELEN!", "");
				logger.error("error by registration!");
				return null;
			}
		} catch (Exception e) {
			logger.error("error by registration!", e);
			addErrorMessage("REGISZTRACIO SIKERTELEN!", "");
			return null;
		}
		return null;
	}

	private Boolean sendMail(String email, String name) throws MessagingException {
		logger.info("Try to send e-mail");

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.host", LOCALHOSTHOST);
		props.setProperty("mail.user", "emailuser");
		props.setProperty("mail.password", "");

		Session mailSession = Session.getDefaultInstance(props, null);
		Transport transport = mailSession.getTransport();

		MimeMessage message = new MimeMessage(mailSession);
		message.setSender(new InternetAddress(EMAIL_ADDRESS));
		message.setSubject("AKTIVACIOS EMAIL");

		String content = "TESZT MAIL";
		// content = content + getRealPath() + "?user=" + getEmail() + "&code="
		// + getActivateCode();

		message.setContent(content, "text/html");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

		try {
			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
			logger.info("Sending registration e-mail was successful");
		} catch (Exception e) {

			logger.error("Sending reg email failed!");
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	private boolean isCheckDataOK() {

		if (getData().getPartner().getNev().equals("") || getData().getPartner().getIrsz().equals("")
				|| getData().getPartner().getVaros().equals("") || getData().getPartner().getCim().equals("")
				|| getData().getPartner().getEmail().equals("") || getData().getPartner().getTel1().equals("")) {
			return false;
		}

		return true;

	}

	public RegistrationServiceData getData() {
		return data;
	}

	public void setData(RegistrationServiceData data) {
		this.data = data;
	}

	public UserServiceBase getUserService() {
		return userService;
	}

	public void setUserService(UserServiceBase userService) {
		this.userService = userService;
	}

}

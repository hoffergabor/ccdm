package hu.iboard.coandco.datamagic.service.email;


public abstract class EmailServiceBase {

	public abstract Boolean sendEmail(String host, String user, String pass, String emailFrom, String emailTo, String subject, String body);
	
	public abstract Boolean sendEmail(String host, String user, String pass, String emailFrom, String emailTo, String subject, String body, String port);

}

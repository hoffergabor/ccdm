package hu.iboard.coandco.datamagic.service.emailservice;

import hu.iboard.coandco.datamagic.dao.newsletteremail.INewsLetterEmailDao;
import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;

public abstract class EmailServiceBase {

	private INewsLetterEmailDao newsLetterEmailDao;

	public abstract NewsLetterEmail saveOrUpdateNewsLetterEmail(NewsLetterEmail email);

	public abstract NewsLetterEmail getNewsLetterEmailByEmail(String email);

	public abstract boolean sendEmail(String host, String user, String pass, String from, String to, String subject, String body);

	public abstract boolean sendNewPasswordEmail(String host, String port, String user, String pass, String from, String to, String subject, String body,
			String newPass);

	public INewsLetterEmailDao getNewsLetterEmailDao() {
		return this.newsLetterEmailDao;
	}

	public void setNewsLetterEmailDao(INewsLetterEmailDao newsLetterEmailDao) {
		this.newsLetterEmailDao = newsLetterEmailDao;
	}

}

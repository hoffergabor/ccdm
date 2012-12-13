package hu.iboard.coandco.datamagic.dao.newsletteremail;

import hu.iboard.coandco.datamagic.model.newsletteremail.NewsLetterEmail;

public interface INewsLetterEmailDao {

	public NewsLetterEmail saveOrUpdateNewsLetterEmail(NewsLetterEmail email);

	public NewsLetterEmail getNewsLetterEmailByEmail(String email);

}

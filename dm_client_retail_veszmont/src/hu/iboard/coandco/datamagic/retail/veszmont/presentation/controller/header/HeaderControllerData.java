package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.header;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "headerControllerData")
@SessionScoped
public class HeaderControllerData implements Serializable{
	
	
	private static final long serialVersionUID = -200704695813635842L;
	
	private String newsLetterEmail;
	private String zipcode;
	private String forgottenPasswordEmail;
	
	public String getNewsLetterEmail() {
		return newsLetterEmail;
	}
	public void setNewsLetterEmail(String newsLetterEmail) {
		this.newsLetterEmail = newsLetterEmail;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getForgottenPasswordEmail() {
		return forgottenPasswordEmail;
	}
	public void setForgottenPasswordEmail(String forgottenPasswordEmail) {
		this.forgottenPasswordEmail = forgottenPasswordEmail;
	}
	

}

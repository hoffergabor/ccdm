package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.login;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "loginControllerData")
@SessionScoped
public class LoginControllerData implements Serializable{

	private static final long serialVersionUID = 5720772836889941733L;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}

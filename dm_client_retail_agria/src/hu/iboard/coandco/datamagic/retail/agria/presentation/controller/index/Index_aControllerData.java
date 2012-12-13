package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.index;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "index_aControllerData")
@SessionScoped
public class Index_aControllerData implements Serializable {

	private static final long serialVersionUID = -9007229304128412390L;
	private String userName = null;
	private String password = null;	
	
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

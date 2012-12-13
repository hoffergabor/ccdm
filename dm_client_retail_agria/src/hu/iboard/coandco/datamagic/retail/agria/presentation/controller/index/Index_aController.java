package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.index;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "index_aController")
@RequestScoped
public class Index_aController extends AbstractController {

	@ManagedProperty(value = "#{index_aControllerData}")
	private Index_aControllerData data;	
	
	public String login() {
		
		String userName = getData().getUserName();
		String password = getData().getPassword();
		
		if (userName != null && password != null && userName.equals("AgriaTeszt") && password.equals("Teszt2345"))
			return "index";
		else
			return null;
	}

	public Index_aControllerData getData() {
		return data;
	}

	public void setData(Index_aControllerData data) {
		this.data = data;
	}
}

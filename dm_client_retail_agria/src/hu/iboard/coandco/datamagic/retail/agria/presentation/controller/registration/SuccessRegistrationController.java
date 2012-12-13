package hu.iboard.coandco.datamagic.retail.agria.presentation.controller.registration;

import hu.iboard.coandco.datamagic.retail.agria.presentation.util.AbstractController;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class SuccessRegistrationController extends AbstractController {

	@ManagedProperty(value = "#{successRegistrationData}")
	private SuccessRegistrationData data;

	@PostConstruct
	public void init() {
		if (!isPostback()) {
		}
	}

	public SuccessRegistrationData getData() {
		return data;
	}

	public void setData(SuccessRegistrationData data) {
		this.data = data;
	}

}

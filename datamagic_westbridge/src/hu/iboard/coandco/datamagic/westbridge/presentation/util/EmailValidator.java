package hu.iboard.coandco.datamagic.westbridge.presentation.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class EmailValidator implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object o) {
		String email = (String) o;

		if (!email.matches(".+@.+\\.[a-z]+")) {
			((UIInput) uic).setValid(false);
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "HELYTELEN EMAIL CIM!"));
		}

	}

}

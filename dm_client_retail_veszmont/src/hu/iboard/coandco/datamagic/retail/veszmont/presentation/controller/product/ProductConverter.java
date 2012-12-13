package hu.iboard.coandco.datamagic.retail.veszmont.presentation.controller.product;

import hu.iboard.coandco.datamagic.model.aru.Aru;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "productConverter")
public class ProductConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		Aru aru = new Aru(arg2);
		return aru;

	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Aru aru = new Aru();
		aru = (Aru) arg2;
		return aru.getMegn();
	}
}

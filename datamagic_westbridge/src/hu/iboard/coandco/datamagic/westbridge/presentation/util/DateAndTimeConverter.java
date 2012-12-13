package hu.iboard.coandco.datamagic.westbridge.presentation.util;

import javax.faces.bean.ManagedBean;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "dateAndTimeconverter")
@ManagedBean
public class DateAndTimeConverter extends DateTimeConverter implements Converter {

	public DateAndTimeConverter() {
		this.setPattern("M/d/yy");
	}
}

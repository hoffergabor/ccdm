package hu.iboard.coandco.datamagic.presentation.controllers.mainpage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "mainpageData")
@SessionScoped
public class MainPageData implements Serializable{

	private static final long serialVersionUID = -5611404928516273393L;
	
	private List<Date> avaibleDates;

	public List<Date> getAvaibleDates() {
		return avaibleDates;
	}

	public void setAvaibleDates(List<Date> avaibleDates) {
		this.avaibleDates = avaibleDates;
	}
	
}

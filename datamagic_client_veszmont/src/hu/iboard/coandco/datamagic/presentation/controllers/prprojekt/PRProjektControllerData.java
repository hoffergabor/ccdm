package hu.iboard.coandco.datamagic.presentation.controllers.prprojekt;

import hu.iboard.coandco.datamagic.vo.prprojekt.PRProjekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name = "prProjektControllerData")
@SessionScoped
public class PRProjektControllerData implements Serializable {

	private static final long serialVersionUID = 264740006059277505L;

	private List<PRProjekt> allProjekt = new ArrayList<PRProjekt>();
	private PRProjekt selectedPRProjekt;
	private PRProjekt selectedAlPRProjekt;
	private Object[] prProjekt;
	private Object[] alPrProjekt;
	private List<PRProjekt> alProjekt = new ArrayList<PRProjekt>();
	private List<Object[]> products = new ArrayList<Object[]>();
	private ScheduleEvent event = new DefaultScheduleEvent();  
	private ScheduleModel eventModel;  

	public List<PRProjekt> getAllProjekt() {
		return allProjekt;
	}

	public void setAllProjekt(List<PRProjekt> allProjekt) {
		this.allProjekt = allProjekt;
	}

	public PRProjekt getSelectedPRProjekt() {
		return selectedPRProjekt;
	}

	public void setSelectedPRProjekt(PRProjekt selectedPRProjekt) {
		this.selectedPRProjekt = selectedPRProjekt;
	}

	public Object[] getPrProjekt() {
		return prProjekt;
	}

	public void setPrProjekt(Object[] prProjekt) {
		this.prProjekt = prProjekt;
	}

	public List<PRProjekt> getAlProjekt() {
		return alProjekt;
	}

	public void setAlProjekt(List<PRProjekt> alProjekt) {
		this.alProjekt = alProjekt;
	}

	public List<Object[]> getProducts() {
		return products;
	}

	public void setProducts(List<Object[]> products) {
		this.products = products;
	}

	public PRProjekt getSelectedAlPRProjekt() {
		return selectedAlPRProjekt;
	}

	public void setSelectedAlPRProjekt(PRProjekt selectedAlPRProjekt) {
		this.selectedAlPRProjekt = selectedAlPRProjekt;
	}

	public Object[] getAlPrProjekt() {
		return alPrProjekt;
	}

	public void setAlPrProjekt(Object[] alPrProjekt) {
		this.alPrProjekt = alPrProjekt;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

}

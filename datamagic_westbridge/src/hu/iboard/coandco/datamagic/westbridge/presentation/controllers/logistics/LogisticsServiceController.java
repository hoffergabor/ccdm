package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.logistics;

import hu.iboard.coandco.datamagic.westbridge.presentation.util.AbstractController;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;

import com.google.gson.Gson;

@ManagedBean(name = "logisticsserviceController")
@RequestScoped
public class LogisticsServiceController extends AbstractController {

	public static final String LOGISTICSSERVICE_ACTION = "logistics";

	@ManagedProperty(value = "#{logisticsData}")
	private LogisticsData data;

	@Override
	public void loadData() {
		/*
		 * getData().setSimpleModel(new DefaultMapModel());
		 * 
		 * getData().setCoord1(new LatLng(47.879466, 19.667648));
		 * getData().setCoord2(new LatLng(46.383707, 19.689216));
		 * getData().setCoord3(new LatLng(46.879703, 17.706707));
		 * getData().setCoord4(new LatLng(47.485233, 18.702323));
		 * 
		 * // Basic marker getData().getSimpleModel().addOverlay(new
		 * Marker(getData().getCoord1(), "ABC-123", "data",
		 * "http://ccteszt.iboard.hu/westbridge/img/szemely.png"));
		 * getData().getSimpleModel().addOverlay(new
		 * Marker(getData().getCoord2(), "BCD-234", "data",
		 * "http://ccteszt.iboard.hu/westbridge/img/kisteher.png"));
		 * getData().getSimpleModel().addOverlay(new
		 * Marker(getData().getCoord3(), "CDE-345", "data",
		 * "http://ccteszt.iboard.hu/westbridge/img/teher.png"));
		 * getData().getSimpleModel().addOverlay(new
		 * Marker(getData().getCoord4(), "DEF-456", "data",
		 * "http://ccteszt.iboard.hu/westbridge/img/nagyteher.png"));
		 * 
		 * LatLng line1 = new LatLng(47.879466, 19.667648); LatLng line2 = new
		 * LatLng(46.383707, 19.689216); LatLng line3 = new LatLng(46.879703,
		 * 17.706707); LatLng line4 = new LatLng(47.485233, 18.702323);
		 */
	}

	@Override
	public void reloadData() {
	}

	@Override
	public void resetData() {
		removeBeanFromSession(LOGISTICSSERVICE_CONTROLLER);

	}

	public void initMarkers(ActionEvent event) {
		Vehicle vehicle1 = new Vehicle(1, "ABC-123", 47.879466, 19.667648, "http://ccteszt.iboard.hu/westbridge/img/szemely.png");
		Vehicle vehicle2 = new Vehicle(2, "BCD-234", 46.383707, 19.689216, "http://ccteszt.iboard.hu/westbridge/img/kisteher.png");
		Vehicle vehicle3 = new Vehicle(3, "CDE-345", 46.879703, 17.706707, "http://ccteszt.iboard.hu/westbridge/img/teher.png");
		Vehicle vehicle4 = new Vehicle(4, "DEF-456", 47.485233, 18.702323, "http://ccteszt.iboard.hu/westbridge/img/nagyteher.png");
		getData().setVehicles(new ArrayList<Vehicle>());
		getData().getVehicles().add(vehicle1);
		getData().getVehicles().add(vehicle2);
		getData().getVehicles().add(vehicle3);
		getData().getVehicles().add(vehicle4);

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("vehicles", new Gson().toJson(getData().getVehicles()));
	}

	public void getPositions(ActionEvent event) {
		for (Vehicle vehicle : getData().getVehicles()) {
			vehicle.setLat(47 + Math.random());
			vehicle.setLng(18 + Math.random());
		}
		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("vehicles", new Gson().toJson(getData().getVehicles()));
	}

	public void showVehicle(ActionEvent event) {
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("hidden1");
		if (id.equals("")) {
			return;
		}
		getData().setVehicleId(Integer.valueOf(id));
		for(Vehicle vehicle: getData().getVehicles()){
			if(vehicle.getId().equals(getData().getVehicleId())){
				getData().setSelectedVehicle(vehicle);
			}
		}
	}

	public LogisticsData getData() {
		return data;
	}

	public void setData(LogisticsData data) {
		this.data = data;
	}

}

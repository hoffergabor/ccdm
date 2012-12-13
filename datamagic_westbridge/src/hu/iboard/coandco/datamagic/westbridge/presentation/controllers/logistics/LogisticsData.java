package hu.iboard.coandco.datamagic.westbridge.presentation.controllers.logistics;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


@ManagedBean(name = "logisticsData")
@SessionScoped
public class LogisticsData implements Serializable {

	private static final long serialVersionUID = 1268888174683900599L;

	private MapModel simpleModel;  
	private Marker marker;
	private LatLng coord1;
	private LatLng coord2;
	private LatLng coord3;
	private LatLng coord4;
	private List<Vehicle> vehicles;
	private Integer vehicleId;
	private Vehicle selectedVehicle;
	
	public MapModel getSimpleModel() {
		return simpleModel;
	}
	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}
	public Marker getMarker() {
		return marker;
	}
	public void setMarker(Marker marker) {
		this.marker = marker;
	}
	public LatLng getCoord1() {
		return coord1;
	}
	public void setCoord1(LatLng coord1) {
		this.coord1 = coord1;
	}
	public LatLng getCoord2() {
		return coord2;
	}
	public void setCoord2(LatLng coord2) {
		this.coord2 = coord2;
	}
	public LatLng getCoord3() {
		return coord3;
	}
	public void setCoord3(LatLng coord3) {
		this.coord3 = coord3;
	}
	public LatLng getCoord4() {
		return coord4;
	}
	public void setCoord4(LatLng coord4) {
		this.coord4 = coord4;
	}
	public List<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Vehicle getSelectedVehicle() {
		return selectedVehicle;
	}
	public void setSelectedVehicle(Vehicle selectedVehicle) {
		this.selectedVehicle = selectedVehicle;
	} 

}

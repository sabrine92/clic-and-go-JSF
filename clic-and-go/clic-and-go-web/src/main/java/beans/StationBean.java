package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.interfaces.StationServicesLocal;
import entities.Station;

@ManagedBean
@SessionScoped
public class StationBean {

	// Models
	private Station station = new Station();
	private List<Station> stations;
	private List<Station> filtredStations;
	private Boolean displayform = false;
	// Injection

	@EJB
	private StationServicesLocal stationServicesLocal;

	public String doAddStation() {
		stationServicesLocal.addStation(station);
		String navigateTo = "listStations";

		return navigateTo;

	}

	public String doUpdate() {
		stationServicesLocal.updateStation(station);
		displayform = false;
		return "";
	}

	public String doDelete() {
		stationServicesLocal.deleteStation(station);
		displayform = false;
		return "";
	}

	public void doSelect() {
		setDisplayform(true);
	}

	public StationServicesLocal getStationServicesLocal() {
		return stationServicesLocal;
	}

	public void setStationServicesLocal(
			StationServicesLocal stationServicesLocal) {
		this.stationServicesLocal = stationServicesLocal;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public List<Station> getStations() {

		stations = stationServicesLocal.findAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public List<Station> getFiltredStations() {
		return filtredStations;
	}

	public void setFiltredStations(List<Station> filtredStations) {
		this.filtredStations = filtredStations;
	}

	public Boolean getDisplayform() {
		return displayform;
	}

	public void setDisplayform(Boolean displayform) {
		this.displayform = displayform;
	}

}

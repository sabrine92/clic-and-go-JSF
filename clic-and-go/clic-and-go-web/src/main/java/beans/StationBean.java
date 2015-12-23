package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;

import services.interfaces.StationServicesLocal;
import entities.Station;

@ManagedBean
@SessionScoped
public class StationBean {

	// Models
	private Station station = new Station();
	private List<Station> stations;

	private List<Station> stations2;
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

	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Car Edited",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Boolean getDisplayform() {
		return displayform;
	}

	public void setDisplayform(Boolean displayform) {
		this.displayform = displayform;
	}

	public List<Station> getStations2() {
		return stations2;
	}

	public void setStations2(List<Station> stations2) {
		this.stations2 = stations2;
	}

}

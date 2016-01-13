package beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import services.interfaces.StationServicesLocal;
import entities.Line;
import entities.Station;

@ManagedBean
@SessionScoped
public class StationBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Models
	private Station station = new Station();
	private List<Station> stations;
	private Station selectedstaStation;
	private List<Station> stations2;
	private Boolean displayform = false;
	
	private Boolean displaydetails = false;
	private List<Station> filteredStations;
	// Injection

	@EJB
	private StationServicesLocal stationServicesLocal;

	
	@PostConstruct
	public void init() {
	    setStation(new Station());
		setDisplayform(false);
		setDisplaydetails(false);

	}
	public String doAddStation() {
		stationServicesLocal.addStation(station);
		String navigateTo = "listStations";
		setDisplayform(false);
		return navigateTo;

		
	}
	
	
	public String doUpdate(){
		stationServicesLocal.updateStation(selectedstaStation);
		String navigateTo = "listStations";
	    setDisplaydetails(false);
		return navigateTo;
		
		
		}
	public void doNew(){
		station = new Station();
		displayform=true;
	}
	public void doCancel() {
		station = new Station();
		displaydetails = false;
	}
	public void doCancel1() {
		station = new Station();

		displayform = false;
	}
	public String doDeleteStation() {
		System.out.println(selectedstaStation);
		stationServicesLocal.deleteStation(selectedstaStation);
		return "";
	}

	public void onRowSelect(SelectEvent event) {
		setDisplaydetails(true);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Station Unselected",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}



	public String doDelete() {
		stationServicesLocal.deleteStation(station);
		displayform = false;
		return "";
	}

	public void saveChanges(RowEditEvent event) {
		selectedstaStation = (Station) event.getObject();
		stationServicesLocal.updateStation(selectedstaStation);
		FacesMessage msg = new FacesMessage("Station Edited",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Cancel",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		stationServicesLocal.deleteStation(selectedstaStation);
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

	public Station getSelectedstaStation() {
		return selectedstaStation;
	}

	public void setSelectedstaStation(Station selectedstaStation) {
		this.selectedstaStation = selectedstaStation;
	}

	public List<Station> getFilteredStations() {
		return filteredStations;
	}

	public void setFilteredStations(List<Station> filteredStations) {
		this.filteredStations = filteredStations;
	}
	public Boolean getDisplaydetails() {
		return displaydetails;
	}
	public void setDisplaydetails(Boolean displaydetails) {
		this.displaydetails = displaydetails;
	}

}

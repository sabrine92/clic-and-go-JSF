package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import java.util.Locale;
import services.interfaces.StationServicesLocal;
import entities.Place;
import entities.Station;

@ManagedBean
@SessionScoped
public class StationBean {

	// Models
	private Station station = new Station();
	private List<Station> stations;
	  private Station selectedstaStation;
	private List<Station> stations2;
	private Boolean displayform = false;
	  private List<Station> filteredStations;
	// Injection

	@EJB
	private StationServicesLocal stationServicesLocal;

	public String doAddStation() {
		stationServicesLocal.addStation(station);
		String navigateTo = "listStations";
		setDisplayform(false);
		return navigateTo;

	}
	
	
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Station Selected",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Station Unselected",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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
		FacesMessage msg = new FacesMessage("Station Edited",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Station) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	 public void onCellEdit(CellEditEvent event) {
	        Object oldValue = event.getOldValue();
	        Object newValue = event.getNewValue();
	         
	        if(newValue != null && !newValue.equals(oldValue)) {
	            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
	            FacesContext.getCurrentInstance().addMessage(null, msg);
	        }
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

}

package beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import services.interfaces.PlaceServicesLocal;
import services.interfaces.StationServicesLocal;
import entities.Place;
import entities.Station;

@ManagedBean
@ViewScoped
public class PlaceBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String navigateTo = "";

	// Models
	private Place place = new Place();
	private List<Place> places;
	private List<Station> stations;
	private Place selectedPlace;
	private Boolean displayform = false;

	// Injection
	@EJB
	private PlaceServicesLocal placeServicesLocal;
	@EJB
	private StationServicesLocal stationServicesLocal;

	public PlaceBean() {
		// TODO Auto-generated constructor stub
	}

	public String doAddPlace() {
		System.out.println(place);
		placeServicesLocal.addPlace(place);
		navigateTo = "addPlace";
		return navigateTo;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<Place> getPlaces() {
		places = placeServicesLocal.findAllPlaces();
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Boolean getDisplayform() {
		return displayform;
	}

	public void setDisplayform(Boolean displayform) {
		this.displayform = displayform;
	}

	public List<Station> getStations() {
		stations = stationServicesLocal.findAllStations();
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}

	public Place getSelectedPlace() {
		return selectedPlace;
	}

	public void setSelectedPlace(Place selectedPlace) {
		this.selectedPlace = selectedPlace;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Place Selected",
				((Place) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowUnselect(UnselectEvent event) {
		FacesMessage msg = new FacesMessage("Place Unselected",
				((Place) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

}

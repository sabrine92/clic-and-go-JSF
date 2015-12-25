package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import services.interfaces.PlaceServicesLocal;
import services.interfaces.StationServicesLocal;
import entities.Place;
import entities.Station;

@ManagedBean
@ViewScoped
public class PlaceBean {

	String navigateTo = "";

	// Models
	private Place place = new Place();
	private List<Place> places;
	private List<Station> stations;
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

}

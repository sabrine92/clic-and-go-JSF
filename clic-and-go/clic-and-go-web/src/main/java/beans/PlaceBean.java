package beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
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
	private List<Place> filteredPlaces;
	private List<Station> stations;
	private Place selectedPlace;
	private Place modifiedPlace;
	private Boolean displayformadd = false;
	private final static String[] categories;
	static {
		categories = new String[5];
		categories[0] = "Fast Food";
		categories[1] = "Coffee shop";
		categories[2] = "Lounge";
		categories[3] = "Bar";
		categories[4] = "Meseum";
		categories[4] = "Other";
	}

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	// Injection
	@EJB
	private PlaceServicesLocal placeServicesLocal;
	@EJB
	private StationServicesLocal stationServicesLocal;

	public PlaceBean() {
		// TODO Auto-generated constructor stub
	}

	// CRUD&Display stuff
	public String doAddPlace() {
		System.out.println(place);
		place.setRate(0);
		place.setRating(0);
		place.setNbRaters(0);
		placeServicesLocal.addPlace(place);
		navigateTo = "listPlaces";
		setDisplayformadd(false);
		place = new Place();

		return navigateTo;
	}

	public String doUpdatePlace() {
		placeServicesLocal.updatePlace(modifiedPlace);
		navigateTo = "listPlaces";
		return "";
	}

	public String doDeletePlace() {
		System.out.println(selectedPlace);
		placeServicesLocal.deletePlace(selectedPlace);
		return "";
	}

	public void doDisplayAdd() {
		displayformadd = true;
	}

	public void doUndisplayAdd() {
		displayformadd = false;
	}

	// Event Handling
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

	public void onRowEdit(RowEditEvent event) {
		doUpdatePlace();
		System.out.println(place + " selected " + selectedPlace + "modified "
				+ modifiedPlace);
		FacesMessage msg = new FacesMessage("Place edited",
				((Place) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled",
				((Place) event.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();
		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Cell Changed", "Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}

	// GETTERS&SETTERS

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
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

	public Boolean getDisplayformadd() {
		return displayformadd;
	}

	public void setDisplayformadd(Boolean displayformadd) {
		this.displayformadd = displayformadd;
	}

	public Place getModifiedPlace() {
		return modifiedPlace;
	}

	public void setModifiedPlace(Place modifiedPlace) {
		this.modifiedPlace = modifiedPlace;
	}

	public List<Place> getFilteredPlaces() {
		return filteredPlaces;
	}

	public void setFilteredPlaces(List<Place> filteredPlaces) {
		this.filteredPlaces = filteredPlaces;
	}

	public List<String> getCategories() {
		return Arrays.asList(categories);
	}

}

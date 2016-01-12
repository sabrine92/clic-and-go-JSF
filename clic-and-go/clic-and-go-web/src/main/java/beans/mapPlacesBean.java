package beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import services.interfaces.PlaceServicesLocal;
import entities.Place;

@ManagedBean
@ViewScoped
public class mapPlacesBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Models
	private Place place;
	private List<Place> places;

	private MapModel simpleModel;
	private Marker marker;
	private String title;
	private double lat;
	private double lng;
	private String type;
	private Map<String, String> types = new HashMap<String, String>();

	// Injection
	@EJB
	private PlaceServicesLocal placeServicesLocal;

	// Getters & Setters
	public PlaceServicesLocal getPlaceServicesLocal() {
		return placeServicesLocal;
	}

	public void setPlaceServicesLocal(PlaceServicesLocal placeServicesLocal) {
		this.placeServicesLocal = placeServicesLocal;
	}

	public List<Place> getPlaces() {
		return places = placeServicesLocal.findAllPlaces();
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public MapModel getSimpleModel() {
		return simpleModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getTypes() {
		return types;
	}

	public void setTypes(Map<String, String> types) {
		this.types = types;
	}

	// MAP

	@PostConstruct
	public void init() {

		simpleModel = new DefaultMapModel();
		places = placeServicesLocal.findAllPlaces();

		for (Place n : places) {
			simpleModel.addOverlay(new Marker(
					new LatLng(n.getLat(), n.getLng()), n.getName(), n));

			types = new HashMap<String, String>();
			types.put("Place", "Place");
			types.put("Place", "Place");
		}
	}

	public void onMarkerSelect(OverlaySelectEvent event) {

		marker = (Marker) event.getOverlay();

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected",
						marker.getTitle()));

	}

	public void addMarker() {
		Marker marker = new Marker(new LatLng(lat, lng), place.getName(), place);
		simpleModel.addOverlay(marker);
		place.setLat(lat);
		place.setLng(lng);
		placeServicesLocal.addPlace(place);
		places.add(place);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Alert Added",
						place.getName()));

	}

}

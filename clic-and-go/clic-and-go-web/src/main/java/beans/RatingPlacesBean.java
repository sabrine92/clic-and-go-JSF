package beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TabCloseEvent;

import services.interfaces.PlaceServicesLocal;
import entities.Place;

@ManagedBean
@SessionScoped
public class RatingPlacesBean {
	private Place selectedPlace;
	private Boolean displayformrate = false;
	//private Boolean rated = false;
	private List<Place> placesRated = new ArrayList<Place>();

	private Integer rating;
	private Integer nbRaters;
	private Integer rate;

	private Integer newrating;
	private Integer newnbRaters;
	private Integer newrate;

	@EJB
	private PlaceServicesLocal placeServicesLocal;

	// fonctions

	public void doDisplayRate() {
		displayformrate = true;

	}

	public void doUndisplayRate() {
		//rated=false;
		displayformrate = false;
	}

	public void doConfirmRating() {
		placeServicesLocal.ratePlace(selectedPlace.getPlaceId(), newrating,
				newnbRaters, newrate);
		RatingInfo(selectedPlace);
		displayformrate = false;
		placesRated.add(selectedPlace);
		System.out.println(placesRated);
		//rated=true;
	}

	public void onTabChange(TabChangeEvent event) {
		//rated=false;
		FacesMessage msg = new FacesMessage("Tab opened", "Active Tab: "
				+ event.getTab().getTitle());
		selectedPlace = placeServicesLocal.findPlaceByPlaceName(event.getTab()
				.getTitle().toString().trim());
		RatingInfo(selectedPlace);
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onTabClose(TabCloseEvent event) {
		FacesMessage msg = new FacesMessage("Tab Closed", "Closed tab: "
				+ event.getTab().getTitle());
		FacesContext.getCurrentInstance().addMessage(null, msg);
		selectedPlace = new Place();
		rating = 0;
		nbRaters = 0;
		rate = 0;
		//rated=false;
	}

	public void onrate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Rate Event", "You rated:"
						+ ((Integer) rateEvent.getRating()).intValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		RatingInfo(selectedPlace);
		if (nbRaters == 0) {
			newrating = ((Integer) rateEvent.getRating()).intValue();
			newrate = newrating;
			newnbRaters = 1;
		} else {
			newrating = rating + ((Integer) rateEvent.getRating()).intValue();
			newnbRaters = nbRaters + 1;
			newrate = (int) (newrating / newnbRaters);
		}
		System.out.println("after rating" + newrating);
		System.out.println("after nbR" + newnbRaters);
		System.out.println("after rate " + newrate);
	}

	public void oncancel() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Cancel Event", "Rate Reset");
		FacesContext.getCurrentInstance().addMessage(null, message);
		doUndisplayRate();
	}

	public void RatingInfo(Place p) {
		Place place = placeServicesLocal.findPlaceByPlaceId(p.getPlaceId());
		rating = place.getRating();
		nbRaters = place.getNbRaters();
		rate = place.getRate();
		System.out.println("rating " + rating);
		System.out.println("nbR " + nbRaters);
		System.out.println("rate " + rate);
	}

	// Getters et Setters

	public Boolean getDisplayformrate() {
		return displayformrate;
	}

	public void setDisplayformrate(Boolean displayformrate) {
		this.displayformrate = displayformrate;
	}

	public Place getSelectedPlace() {
		return selectedPlace;
	}

	public void setSelectedPlace(Place selectedPlace) {
		this.selectedPlace = selectedPlace;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getNbRaters() {
		return nbRaters;
	}

	public void setNbRaters(Integer nbRaters) {
		this.nbRaters = nbRaters;
	}

	public List<Place> getPlacesRated() {
		placesRated = placeServicesLocal.findAllPlaces();
		return placesRated;
	}

	public void setPlacesRated(List<Place> placesRated) {
		this.placesRated = placesRated;
	}

	public Integer getNewrating() {
		return newrating;
	}

	public void setNewrating(Integer newrating) {
		this.newrating = newrating;
	}

	public Integer getNewnbRaters() {
		return newnbRaters;
	}

	public void setNewnbRaters(Integer newnbRaters) {
		this.newnbRaters = newnbRaters;
	}

	public Integer getNewrate() {
		return newrate;
	}

	public void setNewrate(Integer newrate) {
		this.newrate = newrate;
	}

//	public Boolean getRated() {
//		return rated;
//	}
//
//	public void setRated(Boolean rated) {
//		this.rated = rated;
//	}
	
	

}

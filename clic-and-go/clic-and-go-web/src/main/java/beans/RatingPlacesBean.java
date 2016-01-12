package beans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

import services.interfaces.PlaceServicesLocal;

@ManagedBean
@SessionScoped
public class RatingPlacesBean {

	private Integer rating;
	private Integer nbRaters;
	private Integer rate;

	private Boolean displayformrate = false;

	@EJB
	private PlaceServicesLocal placeServicesLocal;
	
	// fonctions
//	public void doUndisplayRate() {
//		displayformrate = false;
//	}
//
//	public void doConfirmRating() {
//		displayformrate = false;
//	}
	
//	public void doDisplayRate() {
//		rating = selectedPlace.getRating();
//		nbRaters = selectedPlace.getNbRaters();
//		rate = selectedPlace.getRate();
//		System.out.println("the current rate " + rate);
//		displayformrate = true;
//	}
//
//	public void onrate(RateEvent rateEvent) {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
//				"Rate Event", "You rated:"
//						+ ((Integer) rateEvent.getRating()).intValue());
//		System.out.println("current: " + rate + "new rate: "
//				+ ((Integer) rateEvent.getRating()).intValue());
//		placeServicesLocal.ratePlace(selectedPlace.getPlaceId(),
//				((Integer) rateEvent.getRating()).intValue());
//		FacesContext.getCurrentInstance().addMessage(null, message);
//		System.out.println("selected place new rate: "
//				+ selectedPlace.getRating());
//		doConfirmRating();
//	}
//
//	public void oncancel() {
//		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
//				"Cancel Event", "Rate Reset");
//		FacesContext.getCurrentInstance().addMessage(null, message);
//		System.out.println("restored :" + selectedPlace.getRating());
//		doUndisplayRate();
//	}
//
//	// Getters et Setters
	
//	public Boolean getDisplayformrate() {
//		return displayformrate;
//	}
//
//	public void setDisplayformrate(Boolean displayformrate) {
//		this.displayformrate = displayformrate;
//	}
//	public Integer getRate() {
//		return rate;
//	}
//
//	public void setRate(Integer rate) {
//		this.rate = rate;
//	}
//
//	public Integer getRating() {
//		return rating;
//	}
//
//	public void setRating(Integer rating) {
//		this.rating = rating;
//	}
//
//	public Integer getNbRaters() {
//		return nbRaters;
//	}
//
//	public void setNbRaters(Integer nbRaters) {
//		this.nbRaters = nbRaters;
//	}

}

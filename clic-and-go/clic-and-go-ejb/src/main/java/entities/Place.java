package entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: Place
 *
 */
@Entity
public class Place implements Serializable {

	private Integer placeId;
	private String name;
	private String description;
	private String Category;
	private Integer x;
	private Integer y;
	private double lat;
	private double lng;
	private Integer rating;
	private Integer nbRaters;
	private Integer rate;
	private static final long serialVersionUID = 1L;

	private Station station;

	public Place() {
		super();
	}

	public Place(String name, String description,
			String category, Integer x, Integer y, Station station) {
		super();
		this.name = name;
		this.description = description;
		Category = category;
		this.x = x;
		this.y = y;
		this.station = station;
		this.rating=0;
		this.nbRaters=0;
		this.rate=0;
	}
	

	@Override
	public String toString() {
		return "Place [placeId=" + placeId + ", name=" + name
				+ ", description=" + description + ", Category=" + Category
				+ ", x=" + x + ", y=" + y + ", station=" + station + "]";
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getPlaceId() {
		return this.placeId;
	}

	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return this.Category;
	}

	public void setCategory(String Category) {
		this.Category = Category;
	}

	@ManyToOne
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
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

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
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

}

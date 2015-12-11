package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

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
	private static final long serialVersionUID = 1L;
	
	private Station station;

	public Place() {
		super();
	}   
	@Id    
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
   
}

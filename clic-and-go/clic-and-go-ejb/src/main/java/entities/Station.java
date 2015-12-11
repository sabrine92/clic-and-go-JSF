package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Station
 *
 */
@Entity
public class Station implements Serializable {

	private Integer stationId;
	private String name;
	private Integer reference;
	private Integer x;
	private Integer y;
	private static final long serialVersionUID = 1L;

	private List<StationLine> stationLines;

	public Station() {
	}

	public Station(String name, Integer reference) {
		super();
		this.name = name;
		this.reference = reference;
	}

	public Station(String name, Integer reference,
			List<StationLine> stationLines) {
		super();
		this.name = name;
		this.reference = reference;
		this.stationLines = stationLines;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "station")
	public List<StationLine> getStationLines() {
		return stationLines;
	}

	public void setStationLines(List<StationLine> stationLines) {
		this.stationLines = stationLines;
	}

	@Override
	public String toString() {
		return "Station [stationId=" + stationId + ", name=" + name + "]";
	}

	public Integer getReference() {
		return reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((reference == null) ? 0 : reference.hashCode());
		result = prime * result
				+ ((stationId == null) ? 0 : stationId.hashCode());
		result = prime * result
				+ ((stationLines == null) ? 0 : stationLines.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (reference == null) {
			if (other.reference != null)
				return false;
		} else if (!reference.equals(other.reference))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		if (stationLines == null) {
			if (other.stationLines != null)
				return false;
		} else if (!stationLines.equals(other.stationLines))
			return false;
		return true;

	}

}
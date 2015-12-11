package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Line
 *
 */
@Entity
public class Line implements Serializable {

	private Integer lineId;
	private String name;
	private Integer nbStations;
	private static final long serialVersionUID = 1L;

	private List<StationLine> stationLines;
	private List<MeanOfTransport> meansOftransport;

	// private List<Ticket> tickets;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNbStations() {
		return nbStations;
	}

	public void setNbStations(Integer nbStations) {
		this.nbStations = nbStations;
	}

	@OneToMany(mappedBy = "line")
	public List<StationLine> getStationLines() {
		return stationLines;
	}

	public void setStationLines(List<StationLine> stationLines) {
		this.stationLines = stationLines;
	}

	@OneToMany(mappedBy = "line")
	public List<MeanOfTransport> getMeansOftransport() {
		return meansOftransport;
	}

	public void setMeansOftransport(List<MeanOfTransport> meansOftransport) {
		this.meansOftransport = meansOftransport;
	}

	public void linkMeanOfTransportToThisLine(
			List<MeanOfTransport> meansOftransport) {
		this.meansOftransport = meansOftransport;
		for (MeanOfTransport m : meansOftransport) {
			m.setLine(this);
		}
	}

	@Override
	public String toString() {
		return "Line [lineId=" + lineId + ", name=" + name + ", nbStations="
				+ nbStations + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lineId == null) ? 0 : lineId.hashCode());
		result = prime
				* result
				+ ((meansOftransport == null) ? 0 : meansOftransport.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((nbStations == null) ? 0 : nbStations.hashCode());
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
		Line other = (Line) obj;
		if (lineId == null) {
			if (other.lineId != null)
				return false;
		} else if (!lineId.equals(other.lineId))
			return false;
		if (meansOftransport == null) {
			if (other.meansOftransport != null)
				return false;
		} else if (!meansOftransport.equals(other.meansOftransport))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nbStations == null) {
			if (other.nbStations != null)
				return false;
		} else if (!nbStations.equals(other.nbStations))
			return false;
		if (stationLines == null) {
			if (other.stationLines != null)
				return false;
		} else if (!stationLines.equals(other.stationLines))
			return false;
		return true;
	}

}

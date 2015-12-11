package entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class StationLineId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer lineId;
	private Integer stationId;

	public StationLineId() {

	}

	public StationLineId(Integer lineId, Integer stationId) {
		super();
		this.lineId = lineId;
		this.stationId = stationId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((lineId == null) ? 0 : lineId.hashCode());
		result = prime * result
				+ ((stationId == null) ? 0 : stationId.hashCode());
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
		StationLineId other = (StationLineId) obj;
		if (lineId == null) {
			if (other.lineId != null)
				return false;
		} else if (!lineId.equals(other.lineId))
			return false;
		if (stationId == null) {
			if (other.stationId != null)
				return false;
		} else if (!stationId.equals(other.stationId))
			return false;
		return true;
	}

	public Integer getLineId() {
		return lineId;
	}

	public void setLineId(Integer lineId) {
		this.lineId = lineId;
	}

	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

}

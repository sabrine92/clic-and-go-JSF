package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: StationLine
 *
 */
@Entity
@Table(name = "station_line")
public class StationLine implements Serializable {

	private StationLineId StationLineId;
	private Integer position;
	private Integer duration;
	private Integer distance;
	private static final long serialVersionUID = 1L;

	private Station station;
	private Line line;

	public StationLine() {
		super();
	}

	public StationLine(Station station, Line line, Integer position,
			Integer duration, Integer distance) {
		super();
		this.StationLineId = new StationLineId(line.getLineId(),
				station.getStationId());
		this.position = position;
		this.setDuration(duration);
		this.station = station;
		this.line = line;
		this.distance = distance;
	}

	@EmbeddedId
	public StationLineId getStationLineId() {
		return StationLineId;
	}

	public void setStationLineId(StationLineId stationLineId) {
		StationLineId = stationLineId;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "stationId", referencedColumnName = "stationId", insertable = false, updatable = false)
	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "lineId", referencedColumnName = "lineId", insertable = false, updatable = false)
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "StationLine [StationLineId=" + StationLineId + ", position="
				+ position + ", duration=" + duration + ", distance="
				+ distance + "]";
	}

}

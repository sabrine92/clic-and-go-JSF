package entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Entity implementation class for Entity: MeanOfTransport
 *
 */
@Entity
public class MeanOfTransport implements Serializable {

	private String registrationNumber;
	private Integer nbOfWagons;
	private Integer capacity;
	private static final long serialVersionUID = 1L;

	private Line line;

	// private List<Ticket> tickets;

	public MeanOfTransport() {
		super();
	}

	@Id
	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public Integer getNbOfWagons() {
		return this.nbOfWagons;
	}

	public void setNbOfWagons(Integer nbOfWagons) {
		this.nbOfWagons = nbOfWagons;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@ManyToOne(cascade = CascadeType.MERGE)
	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "MeanOfTransport [registrationNumber=" + registrationNumber
				+ ", nbOfWagons=" + nbOfWagons + ", capacity=" + capacity
				+ ", line=" + line + "]";
	}

}

package entities;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

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
	private List<Ticket> tickets;

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
	@ManyToOne(cascade=CascadeType.MERGE)
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
	
	@OneToMany(mappedBy = "meanOfTransport")
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	
	 

	
   
}

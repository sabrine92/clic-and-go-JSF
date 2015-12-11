package entities;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: Traveler
 *
 */
@Entity
public class Traveler extends User implements Serializable {

	private Integer travelsKlm;
	private static final long serialVersionUID = 1L;

	public Traveler() {
	}

	public Traveler(String name, String surname, String email, String password,
			Integer travelsKlm) {
		this.setName(name);
		this.setSurname(surname);
		this.setPassword(password);
		this.travelsKlm = travelsKlm;
	}

	public Integer getTravelsKlm() {
		return travelsKlm;
	}

	public void setTravelsKlm(Integer travelsKlm) {
		this.travelsKlm = travelsKlm;
	}

}

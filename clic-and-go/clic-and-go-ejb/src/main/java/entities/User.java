package entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
public class User implements Serializable {

	private Integer userId;
	private String name;
	private String surname;
	private String email;
	private String password;
	private Integer nbOfWordsPerMinute;

	private static final long serialVersionUID = 1L;

	private List<Ticket> tickets;

	public User() {
		super();
	}

	public User(String name, String surname, String email, String password,
			List<Ticket> tickets) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.tickets = tickets;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", email="
				+ email + ", password=" + password + ", nbOfWordsPerMinute="
				+ nbOfWordsPerMinute + ", tickets=" + tickets + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToMany(mappedBy="user")
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Integer getNbOfWordsPerMinute() {
		return nbOfWordsPerMinute;
	}

	public void setNbOfWordsPerMinute(Integer nbOfWordsPerMinute) {
		this.nbOfWordsPerMinute = nbOfWordsPerMinute;
	}

}

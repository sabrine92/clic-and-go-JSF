package entities;

import java.io.Serializable;

import javax.persistence.Entity;

/**
 * Entity implementation class for Entity: ContentManager
 *
 */
@Entity
public class ContentManager extends User implements Serializable {

	private String companyName;
	private static final long serialVersionUID = 1L;

	public ContentManager() {
		super();
	}

	public ContentManager(String name, String surname, String email,
			String password, String companyName) {
		this.setName(name);
		this.setSurname(surname);
		this.setPassword(password);
		this.setEmail(email);
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

}

package entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

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
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
   
}

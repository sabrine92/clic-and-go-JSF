package entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Traveler
 *
 */
@Entity

public class Traveler extends User implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Traveler() {
		super();
	}
   
}

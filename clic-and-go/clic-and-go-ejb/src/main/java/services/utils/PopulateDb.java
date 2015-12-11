package services.utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import services.interfaces.UserServicesLocal;
import entities.Traveler;

/**
 * Session Bean implementation class PopulateDb
 */
@Singleton
@LocalBean
@Startup
public class PopulateDb {

	@EJB
	private UserServicesLocal userServicesLocal;

	/**
	 * Default constructor.
	 */
	public PopulateDb() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void initDb() {
		Traveler traveler = new Traveler("jousem", "k", "k@r.y", "k", 10214);

		userServicesLocal.addUser(traveler);
	}
}

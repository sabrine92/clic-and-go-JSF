package services.utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import services.interfaces.UserServicesLocal;
import entities.ContentManager;
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
		// /Users
		Traveler traveler = new Traveler("houcem", "houcem",
				"Houcem@esprit.tn", "houcem", 10214);
		ContentManager contentManager = new ContentManager("yosr", "yosr",
				"y@esprit.tn", "yosr", "esprit");
		userServicesLocal.addUser(traveler);
		userServicesLocal.addUser(contentManager);

	}
}

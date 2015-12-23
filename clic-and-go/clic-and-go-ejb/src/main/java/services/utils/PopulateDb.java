package services.utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import services.interfaces.StationServicesLocal;
import services.interfaces.UserServicesLocal;
import entities.ContentManager;
import entities.Station;
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

	@EJB
	private StationServicesLocal stationServicesLocal;
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

		//Stations
		Station st0 = new Station();
		st0.setName("Ariana");
		st0.setReference(0);
		st0.setX(50);
		st0.setY(120);

		Station st1 = new Station();
		st1.setName("Cite Olympique");
		st1.setReference(1);
		st1.setX(80);
		st1.setY(150);

		Station st2 = new Station();
		st2.setName("Mohamed 5");
		st2.setReference(2);
		st2.setX(120);
		st2.setY(170);

		Station st3 = new Station();
		st3.setName("Passage");
		st3.setReference(3);
		st3.setX(150);
		st3.setY(190);

		Station st4 = new Station();
		st4.setName("Barcelone");
		st4.setReference(4);
		st4.setX(170);
		st4.setY(210);

		Station st5 = new Station();
		st5.setName("Megrine");
		st5.setReference(5);
		st5.setX(220);
		st5.setY(150);

		Station st6 = new Station();
		st6.setName("Rades");
		st6.setReference(6);
		st6.setX(270);
		st6.setY(110);

		Station st7 = new Station();
		st7.setName("Ezzahra");
		st7.setReference(7);
		st7.setX(300);
		st7.setY(80);

		Station st8 = new Station();
		st8.setName("Hammam Lif");
		st8.setReference(8);
		st8.setX(300);
		st8.setY(30);
		
		Station st9 = new Station();
		st9.setName("Manar 2");
		st9.setReference(9);
		st9.setX(90);
		st9.setY(250);
		
		Station st10 = new Station();
		st10.setName("Menzeh 9");
		st10.setReference(10);
		st10.setX(40);
		st10.setY(250);
		
		Station st11 = new Station();
		st11.setName("La marsa");
		st11.setReference(11);
		st11.setX(130);
		st11.setY(20);
		
		
        stationServicesLocal.addStation(st0);
        stationServicesLocal.addStation(st1);
        stationServicesLocal.addStation(st2);
        stationServicesLocal.addStation(st3);
        stationServicesLocal.addStation(st4);
        stationServicesLocal.addStation(st5);
        stationServicesLocal.addStation(st6);
        stationServicesLocal.addStation(st7);
        stationServicesLocal.addStation(st8);
        stationServicesLocal.addStation(st9);
        stationServicesLocal.addStation(st10);
        stationServicesLocal.addStation(st11);
	}
}

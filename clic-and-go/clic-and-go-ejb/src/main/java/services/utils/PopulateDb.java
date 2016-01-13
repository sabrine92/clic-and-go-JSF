package services.utils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import services.interfaces.MeanOfTransportServicesLocal;
import services.interfaces.NewsServicesLocal;
import services.interfaces.PlaceServicesLocal;
import services.interfaces.StationLineManagementLocal;
import services.interfaces.StationServicesLocal;
import services.interfaces.TicketsServicesLocal;
import services.interfaces.UserServicesLocal;
import entities.Card;
import entities.ContentManager;
import entities.Line;
import entities.MeanOfTransport;
import entities.News;
import entities.Place;
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

	@EJB
	private PlaceServicesLocal placeServicesLocal;

	@EJB
	private StationLineManagementLocal stationLineManagementLocal;

	@EJB
	private TicketsServicesLocal ticketsServicesLocal;

	@EJB
	private NewsServicesLocal newsServicesLocal;
	@EJB 
	private MeanOfTransportServicesLocal meanOfTransportServicesLocal;

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

		// Card
		Card c = new Card();
		c.setCardId("card100");
		c.setPwd(100);
		c.setAmount(10D);

		Card c1 = new Card();
		c1.setCardId("card200");
		c1.setPwd(200);
		c1.setAmount(20D);

		Card c2 = new Card();
		c2.setCardId("card300");
		c2.setPwd(300);
		c2.setAmount(30D);

		Card c3 = new Card();
		c3.setCardId("card400");
		c3.setPwd(400);
		c3.setAmount(40D);

		ticketsServicesLocal.addCard(c);
		ticketsServicesLocal.addCard(c1);
		ticketsServicesLocal.addCard(c2);
		ticketsServicesLocal.addCard(c3);

		// Stations
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

		// //Populating line

		Line line1 = new Line();
		line1.setName("Ariana-Passage");
		line1.setPrice(0.850D);

		Line line2 = new Line();
		line2.setName("Passage-Barcelone");
		line2.setPrice(0.850D);

		Line line3 = new Line();
		line3.setName("Barcelone-Hammam Lif");
		line3.setPrice(1.150D);

		Line line4 = new Line();
		line4.setName("Barcelone-La Marsa");
		line4.setPrice(1.850D);

		Line line5 = new Line();
		line5.setName("Passage-Menzeh 9");
		line5.setPrice(1.250D);

		System.out.println(stationLineManagementLocal.addLine(line1));
		System.out.println(stationLineManagementLocal.addLine(line2));
		System.out.println(stationLineManagementLocal.addLine(line3));
		System.out.println(stationLineManagementLocal.addLine(line4));
		System.out.println(stationLineManagementLocal.addLine(line5));
		System.out.println(stationLineManagementLocal.findAllLines());

		// Assign Stations To Lines
		stationLineManagementLocal.assignStationToLine(1, 1, 1, 5, 5);
		stationLineManagementLocal.assignStationToLine(2, 1, 2, 5, 5);

		stationLineManagementLocal.assignStationToLine(3, 1, 3, 4, 4);
		stationLineManagementLocal.assignStationToLine(4, 1, 4, 2, 2);

		stationLineManagementLocal.assignStationToLine(4, 2, 1, 1, 1);

		stationLineManagementLocal.assignStationToLine(5, 2, 2, 1, 1);

		stationLineManagementLocal.assignStationToLine(5, 3, 1, 5, 5);

		stationLineManagementLocal.assignStationToLine(6, 3, 2, 5, 5);

		stationLineManagementLocal.assignStationToLine(7, 3, 3, 4, 4);

		stationLineManagementLocal.assignStationToLine(8, 3, 4, 4, 4);

		stationLineManagementLocal.assignStationToLine(9, 3, 5, 3, 3);

		stationLineManagementLocal.assignStationToLine(5, 4, 1, 15, 15);

		stationLineManagementLocal.assignStationToLine(12, 4, 2, 15, 15);

		stationLineManagementLocal.assignStationToLine(4, 5, 1, 8, 8);

		stationLineManagementLocal.assignStationToLine(10, 5, 2, 8, 8);

		stationLineManagementLocal.assignStationToLine(11, 5, 3, 3, 3);

		// Places
		Place place1 = new Place("foccacia", "pizza italienne à volenté",
				"Fast Food", 45, 255, st10);
		Place place2 = new Place("Barista's", "Cheese cake et moccachino",
				"Coffee shop", 140, 17, st11);
		Place place3 = new Place("Al7attab", "Lablebi, Kafteji, s7an tounsi",
				"Fast Food", 155, 190, st3);
		Place place4 = new Place("Chillis", "American Dream on a plate",
				"Restaurant", 95, 255, st9);
		place1.setRating(108);
		place1.setNbRaters(36);
		place1.setRate(3);
		place2.setRating(600);
		place2.setRate(4);
		place2.setNbRaters(150);
		place3.setRating(3100);
		place3.setRate(5);
		place3.setNbRaters(620);
		placeServicesLocal.addPlace(place1);
		placeServicesLocal.addPlace(place2);
		placeServicesLocal.addPlace(place3);
		placeServicesLocal.addPlace(place4);

		// NEWS

		News news1 = new News();
		news1.setTitle("Bab El Khadhra");
		news1.setDescription("Bus breakdown to 4:40 p.m. Waiting for towing");
		news1.setType("Breakdown");
		news1.setLat(36.809590);
		news1.setLng(10.162847);

		News news2 = new News();
		news2.setTitle("Avenue Habib Bourguiba");
		news2.setDescription("Serious accident at 4:40 p.m. Bus. Waiting ambulance");
		news2.setType("Accident");
		news2.setLat(36.799663);
		news2.setLng(10.180025);

		News news3 = new News();
		news3.setTitle("Bab Alouia");
		news3.setDescription("Trafic Jam");
		news3.setType("Jam");
		news3.setLat(36.784016);
		news3.setLng(10.177511);

		System.out.println(newsServicesLocal.addNews(news1));
		System.out.println(newsServicesLocal.addNews(news2));
		System.out.println(newsServicesLocal.addNews(news3));

		
		
		//meanoftransport
		MeanOfTransport meanOfTransport1 = new MeanOfTransport();
		meanOfTransport1.setRegistrationNumber("TCV1");
		meanOfTransport1.setCapacity(30);
		meanOfTransport1.setNbOfWagons(1);
		System.out.println(meanOfTransportServicesLocal
				.addMeanOfTransport(meanOfTransport1));

		MeanOfTransport meanOfTransport2 = new MeanOfTransport();
		meanOfTransport2.setRegistrationNumber("TCV2");
		meanOfTransport2.setCapacity(30);
		meanOfTransport2.setNbOfWagons(1);
		System.out.println(meanOfTransportServicesLocal
				.addMeanOfTransport(meanOfTransport2));
		MeanOfTransport meanOfTransport4 = new MeanOfTransport();
		meanOfTransport4.setRegistrationNumber("Train");
		meanOfTransport4.setCapacity(120);
		meanOfTransport4.setNbOfWagons(3);
		System.out.println(meanOfTransportServicesLocal
				.addMeanOfTransport(meanOfTransport4));

		MeanOfTransport meanOfTransport5 = new MeanOfTransport();
		meanOfTransport5.setRegistrationNumber("Metro 4");
		meanOfTransport5.setCapacity(60);
		meanOfTransport5.setNbOfWagons(2);
		System.out.println(meanOfTransportServicesLocal
				.addMeanOfTransport(meanOfTransport5));
		
		System.out.println(meanOfTransportServicesLocal.assignMeanOfTransportToLine("TCV1", 1));
		System.out.println(meanOfTransportServicesLocal.assignMeanOfTransportToLine("Metro 4", 3));
		System.out.println(meanOfTransportServicesLocal.assignMeanOfTransportToLine("Train", 3));
	}
}

package services.impl;

import java.util.List;
import java.util.Vector;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import services.interfaces.DijkestraLocal;
import services.interfaces.DijkestraRemote;
import services.interfaces.SessionLocal;
import services.interfaces.StationServicesLocal;
import entities.Station;

/**
 * Session Bean implementation class Dijkestra
 */
@Stateless
public class Dijkestra implements DijkestraRemote, DijkestraLocal {

	public static final int INFINITE = 1000;// Integer.MAX_VALUE;
	public final static int ALPHA_NOTDEF = -999;// on met final psk c'est une
												// constante
	private int x0;
	private int[] S;// ensemble de sommets dont les distances les plus courtes à
					// la source sont connues au départ seulement Source
	private int[] R;// ensemble des prédécesseur des sommets de 0 à N-1;
	private Graphe g0;
	private int[] D;// tableau des valeurs du meilleur raccourci pour se rendre
					// à chaque sommet
	// rajout
	private boolean[] noeudsMarqués;
	private static int dimensionDeLaMatrice;
	@EJB
	private StationServicesLocal stationServices;
	@EJB
	private SessionLocal session;
	
	

	public Dijkestra() {
		
	}

	public Dijkestra(int x, Graphe g) {
		x0 = x;
		g0 = g;
		dimensionDeLaMatrice = g0.nbSommet();
		S = new int[dimensionDeLaMatrice];// sommets atteints
		D = new int[dimensionDeLaMatrice];// distances
		noeudsMarqués = new boolean[dimensionDeLaMatrice];
		R = new int[dimensionDeLaMatrice];
		calculePlusCourtChemin();
	}
	
	
	


	@Override
	public void calculePlusCourtChemin() {
		int n = 0;
		for (int a = 0; a < dimensionDeLaMatrice; a++) {
			noeudsMarqués[a] = false;
			S[a] = -1; // en supposant qu'on numérote les sommets de 0 ou de 1 à
						// n.
			R[a] = -1; // pour les noeuds qui n'ont pas de prédecésseur
		}

		S[n] = x0;
		D[x0] = 0;
		R[x0] = x0;
		initDistMin();
		for (int i = 0; i < dimensionDeLaMatrice; i++) {
			if (!contains(S, i)) {// à revoir
				int t = choixSommet();
				noeudsMarqués[t] = true;
				n++;
				S[n] = t;
				majDistMin(t);
			} // end if
		}
	}

	@Override
	public void initDistMin() {
		noeudsMarqués[x0] = true;
		for (int i = 0; i < dimensionDeLaMatrice; i++) {
			if (g0.existeArc(x0, i)) {
				D[i] = g0.getU()[x0][i];
				R[i] = x0;
			} else {
				if (x0 != i)
					D[i] = -g0.ALPHA_NOTDEF + 1;
			}
		}
	}

	@Override
	public void majDistMin(int n) {
		for (int i = 0; i < dimensionDeLaMatrice; i++) {
			if (!contains(S, i)) {
				// D[i] = min(D[i], D[n] + distanceDsGraphe(n,i));
				if (D[n] + distanceDsGraphe(n, i) < D[i]) {
					D[i] = D[n] + distanceDsGraphe(n, i);
					R[i] = n;
				}
			}
		}

	}

	@Override
	public int distanceDsGraphe(int t, int s) {
		if (g0.existeArc(t, s)) {
			return g0.getU()[t][s];
		} else {
			return INFINITE;
		}
	}

	@Override
	public int choixSommet() {
		int valeurMin = INFINITE;
		int min = x0;
		for (int i = 0; i < dimensionDeLaMatrice; i++) {
			if (!noeudsMarqués[i])
				if (D[i] <= valeurMin) {
					min = i;
					valeurMin = D[i];
				}
		}
		return min;
	}

	@Override
	public boolean contains(int[] S, int i) {
		return noeudsMarqués[i];
	}

	@Override
	public int longueurChemin(int i) {
		return D[i];

	}

	@Override
	public int min(int i, int j) {
		if (i <= j)
			return i;
		else
			return j;
	}

	@Override
	public String afficheCheminByStation(String depart, String arrivee) {
		String ch = null;
		String sh = "";
		Station stationdepart = stationServices
				.findStationByStationName(depart);
		Station stationarrivee = stationServices
				.findStationByStationName(arrivee);
		int i = stationarrivee.getReference();
		int source = stationdepart.getReference();

		int antécédent = i;
		Vector<Integer> lesNoeudsIntermediaires = new Vector<Integer>();
		;
		System.out.println(" de " + depart + " à " + arrivee + ":");
		while (antécédent != source) {
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];

		}
		lesNoeudsIntermediaires.add(source);
		for (int j = lesNoeudsIntermediaires.size() - 1; j > 0; j--) {
			Station stationvariable = stationServices
					.findStationByStationByReference(lesNoeudsIntermediaires
							.get(j));

			System.out.print("-->" + stationvariable.getName());
			sh = sh + "-->" + stationvariable.getName();
		}
		System.out.print("-->" + stationarrivee.getName());
		System.out.println();
		String yy = "-->" + arrivee;
		ch = "To go from  " + depart + " to " + arrivee + ": \n" + sh + yy;
		return ch;
	}
	public String afficheCheminByStationId(int Id1, int Id2) {
		String ch = null;
		String sh = "";
	Station	StatDepart = stationServices.findStationById(Id1);
		
				
		Station StatArriv= stationServices.findStationById(Id2);
				
		int i = StatArriv.getReference();
		int source = StatDepart.getReference();

		int antécédent = i;
		Vector<Integer> lesNoeudsIntermediaires = new Vector<Integer>();
		;
		System.out.println(" le chemin de " + StatDepart.getName() + " à " + StatArriv.getName() + ":");
		while (antécédent != source) {
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];

		}
		lesNoeudsIntermediaires.add(source);
		for (int j = lesNoeudsIntermediaires.size() - 1; j > 0; j--) {
			Station stationvariable = stationServices
					.findStationByStationByReference(lesNoeudsIntermediaires
							.get(j));

			System.out.print("-->" + stationvariable.getName());
			sh = sh + "-->" + stationvariable.getName();
		}
		System.out.print("-->" + StatArriv.getName());
		System.out.println();
		String yy = "-->" + StatArriv.getName();
		ch = "To go from  " + StatArriv.getName() + " to " + StatDepart.getName() + ": \n" + sh + yy;
		return ch;
	}
	public String afficheCheminByStationHoucem() {
		String ch = null;
		String sh = "";
		Station StatArriv=stationServices.findStationByStationByReference(0);
	Station	StatDepart=stationServices.findStationByStationByReference(3);
		int i = StatArriv.getReference();
		int source = StatDepart.getReference();

		int antécédent = i;
		Vector<Integer> lesNoeudsIntermediaires = new Vector<Integer>();
		;
		System.out.println(" le chemin de " + StatDepart.getName() + " à " + StatArriv.getName() + ":");
		while (antécédent != source) {
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];

		}
		lesNoeudsIntermediaires.add(source);
		for (int j = lesNoeudsIntermediaires.size() - 1; j > 0; j--) {
			Station stationvariable = stationServices
					.findStationByStationByReference(lesNoeudsIntermediaires
							.get(j));

			System.out.print("-->" + stationvariable.getName());
			sh = sh + "-->" + stationvariable.getName();
		}
		System.out.print("-->" + StatArriv.getName());
		System.out.println();
		String yy = "-->" + StatArriv.getName();
		ch = "To go from  " + StatArriv.getName() + " to " + StatDepart.getName() + ": \n" + sh + yy;
		return ch;
	}

	@Override
	public List<Station> afficheListStations(String depart, String arrivee) {
		List<Station> stationsToDraw = new Vector<Station>();
		Station stationdepart = stationServices
				.findStationByStationName(depart);
		Station stationarrivee = stationServices
				.findStationByStationName(arrivee);
		int i = stationarrivee.getReference();
		int source = stationdepart.getReference();

		int antécédent = i;
		Vector<Integer> lesNoeudsIntermediaires = new Vector<Integer>();
		;
		System.out.println(" de " + depart + " à " + arrivee + ":");
		while (antécédent != source) {
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];

		}
		lesNoeudsIntermediaires.add(source);

		for (int j = lesNoeudsIntermediaires.size() - 1; j > 0; j--) {
			Station stationvariable = stationServices.findStationByStationByReference(lesNoeudsIntermediaires.get(j));
					
			stationsToDraw.add(stationvariable);
			System.out.print("-->" + stationvariable.getName());

		}
		System.out.print("-->" + stationarrivee.getName());
		System.out.println();
		String yy = "-->" + arrivee;
		stationsToDraw.add(stationarrivee);
		return stationsToDraw;
	}

	@Override
	public String afficheChemin(int i) {
		String ch = null;
		String sh = null;
		int source = x0;
		int antécédent = i;
		Vector<Integer> lesNoeudsIntermediaires = new Vector<Integer>();
		;
		System.out.println("Chemin de " + x0 + " à " + i + ":");
		while (antécédent != source) {
			lesNoeudsIntermediaires.add(antécédent);
			antécédent = R[antécédent];

		}
		lesNoeudsIntermediaires.add(source);
		for (int j = lesNoeudsIntermediaires.size() - 1; j > 0; j--) {
			System.out.print("-->" + lesNoeudsIntermediaires.get(j));
			sh = sh + "-->" + lesNoeudsIntermediaires.get(j);
		}
		System.out.print("-->" + i);
		System.out.println();
		ch = "Your Itinerary  from " + x0 + " to " + i + ": " + sh;

		return ch;
	}

	@Override
	public String AfficherDestinationEtCout(String depart, String arrivee) {
		int time = 0;
		String ch = null;
		String sh = null;// stationdepart=StationDelegate.findStationByStationName(depart);
		Station stationarrivee = stationServices
				.findStationByStationName(arrivee);
		System.out.print("le cout du chemin est ");
		int duree = longueurChemin(stationarrivee.getReference());
		session.setDuration(duree);
		System.out.println(session.getDeparture());
		System.out.println(+duree);
		System.out.print("/n");
		System.out.print("le chemain a prendre");
		sh = afficheCheminByStation(depart, arrivee);

		// SessionDelegate.doSetDeparture(depart);
		// SessionDelegate.doSetDuration(duree);

		time = (int) (duree / 0.75);

		String timer = "";
		timer = "Your itinerary takes about " + time + " minutes";

		ch = "Your itinerary is  " + duree + " km long .\n " + sh + " \n"
				+ timer;
		return ch;
	}

}

package beans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import services.impl.Dijkestra;
import services.impl.Graphe;
import services.interfaces.DijkestraLocal;
import services.interfaces.StationLineManagementLocal;
import entities.Station;

@ManagedBean(name = "dij")
@SessionScoped
public class AllDijkstraBean {


	@EJB
	public DijkestraLocal dijkstralocal;
	@EJB
	public StationLineManagementLocal stationLineManagement;

	 String depart = "Ariana";
	 String arrivee = "Passage";

	public DijkestraLocal getDijkstralocal() {
		return dijkstralocal;
	}

	public void setDijkstralocal(DijkestraLocal dijkstralocal) {
		this.dijkstralocal = dijkstralocal;
	}

	public StationLineManagementLocal getStationLineManagement() {
		return stationLineManagement;
	}

	public void setStationLineManagement(
			StationLineManagementLocal stationLineManagement) {
		this.stationLineManagement = stationLineManagement;
	}

	

	public void DoAfficheChemin() {
		int[][] mat = stationLineManagement.RemplirMatrice();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.print("\n");
		}
		System.out.println(" \n success remplir matrice");
		Graphe g0 = new Graphe(mat);
		System.out.println(g0.toString());
		Station sta = (Station) stationLineManagement
				.findStationByName("Ariana");
		System.out.println(sta.getY() + "  affich");
		Dijkestra dij = new Dijkestra(sta.getReference(), g0);
		System.out.println(dij.longueurChemin(11));
	//	System.out.println(dij.afficheCheminByStationHoucem());
	//	System.out.println(dij.afficheCheminByStation(depart,arrivee));
		// System.out.println( dij.afficheChemin(11));
//		List<Station> stations = dij.afficheListStations(StatDepart, StatArriv);
//		for (Station s : stations) {
//			System.out.println(s.getName() + "  ");
//		}

		// System.out.println("hello nou");
		// String sh=dijkstralocal.afficheCheminByStation("Ariana","Passage");
		// System.out.println(sh);
		// System.out.println("hello 33 ");
	}



}

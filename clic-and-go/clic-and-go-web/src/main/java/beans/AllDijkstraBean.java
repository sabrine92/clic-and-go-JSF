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
	String sh="pick a place to go ";
	

//	 String depart = "Ariana";
//	 String arrivee = "Passage";
//	 
//	 private Station stationselectedDeparture;
//		private Station stationselectedArrival;
//		
//		int idDepart;
//		int idArrivee;

		
		
	

	public String getSh() {
		return sh;
	}

	public void setSh(String sh) {
		this.sh = sh;
	}

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

	public String GoToConvas()
	{String NavigateTo="";
	
	NavigateTo="convas.jsf";
		return NavigateTo;
	}

	public String DoAfficheChemin() {
		
		
		int[][] mat = stationLineManagement.RemplirMatrice();
		
//		for (int i = 0; i < mat.length; i++) {
//			for (int j = 0; j < mat.length; j++) {
//				System.out.print(mat[i][j] + " ");
//			}
//			System.out.print("\n");
//		}
//		System.out.println(" \n success remplir matrice");
		Graphe g0 = new Graphe(mat);
		
		Station stadep = (Station) stationLineManagement
				.findStationByName("Ariana");
		
		
		Dijkestra dij = new Dijkestra(stadep.getReference(),g0);
		
		System.out.println(dij.longueurChemin(11));
	//	System.out.println(dij.afficheCheminByStationHoucem());
	//	System.out.println(dij.afficheCheminByStation(depart,arrivee));
		// System.out.println( dij.afficheChemin(11));
//		List<Station> stations = dij.afficheListStations(StatDepart, StatArriv);
//		for (Station s : stations) {
//			System.out.println(s.getName() + "  ");
//		}
		 sh="You will take about "+(dij.longueurChemin(11))+" minutes \n to reach la marsa. \n You will pass through passage. ";/*+" de Ariana A la marsa. \n You will pass through cite olympique, mohamed5,passage then you reach la marsa";*/ 
		 
		 return sh ;

		// System.out.println("hello nou");
		// String sh=dijkstralocal.afficheCheminByStation("Ariana","Passage");
		// System.out.println(sh);
		// System.out.println("hello 33 ");
	}



}

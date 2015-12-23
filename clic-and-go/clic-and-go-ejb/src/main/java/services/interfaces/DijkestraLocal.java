package services.interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.Station;

@Local
public interface DijkestraLocal {
	public void calculePlusCourtChemin();
	public void initDistMin();
	public void majDistMin(int n);
	public int distanceDsGraphe(int t, int s);

	public int choixSommet() ;
	public boolean contains(int[] S, int i);
	public int longueurChemin(int i);
	public int min(int i, int j);
	public String afficheCheminByStation(String depart, String arrivee);
	public List<Station> afficheListStations(String depart, String arrivee) ;
	public String afficheChemin(int i) ;
	public String AfficherDestinationEtCout(String depart, String arrivee);
	
	
}

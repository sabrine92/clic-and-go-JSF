package services.interfaces;

import javax.ejb.Local;

@Local
public interface grapheLocal {
	public boolean existeSommet(int i);

	public int nbSommet();

	public boolean existeArc(int i, int j);

	public int getValArc(int i, int j);

	public boolean ajoutSommet(int i);

	public boolean supprimeSommet(int i);

	public boolean ajoutArc(int i, int j, int val);

	public boolean supprimerArc(int i, int j);

	public int degreEntrant(int i);

	public int degreSortant(int i);

	public int degre(int i);

	public int[] lst_succ(int i);
	
}
